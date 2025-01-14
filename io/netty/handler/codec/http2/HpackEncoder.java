package io.netty.handler.codec.http2;

import io.netty.buffer.ByteBuf;
import io.netty.util.AsciiString;
import io.netty.util.CharsetUtil;
import io.netty.util.internal.MathUtil;
import java.util.Map;

final class HpackEncoder {
  static final int NOT_FOUND = -1;
  
  static final int HUFF_CODE_THRESHOLD = 512;
  
  private final NameEntry[] nameEntries;
  
  private final NameValueEntry[] nameValueEntries;
  
  private final NameValueEntry head = new NameValueEntry(-1, (CharSequence)AsciiString.EMPTY_STRING, (CharSequence)AsciiString.EMPTY_STRING, 2147483647, null);
  
  private NameValueEntry latest = this.head;
  
  private final HpackHuffmanEncoder hpackHuffmanEncoder = new HpackHuffmanEncoder();
  
  private final byte hashMask;
  
  private final boolean ignoreMaxHeaderListSize;
  
  private final int huffCodeThreshold;
  
  private long size;
  
  private long maxHeaderTableSize;
  
  private long maxHeaderListSize;
  
  HpackEncoder() {
    this(false);
  }
  
  HpackEncoder(boolean ignoreMaxHeaderListSize) {
    this(ignoreMaxHeaderListSize, 64, 512);
  }
  
  HpackEncoder(boolean ignoreMaxHeaderListSize, int arraySizeHint, int huffCodeThreshold) {
    this.ignoreMaxHeaderListSize = ignoreMaxHeaderListSize;
    this.maxHeaderTableSize = 4096L;
    this.maxHeaderListSize = 4294967295L;
    this.nameEntries = new NameEntry[MathUtil.findNextPositivePowerOfTwo(Math.max(2, Math.min(arraySizeHint, 128)))];
    this.nameValueEntries = new NameValueEntry[this.nameEntries.length];
    this.hashMask = (byte)(this.nameEntries.length - 1);
    this.huffCodeThreshold = huffCodeThreshold;
  }
  
  public void encodeHeaders(int streamId, ByteBuf out, Http2Headers headers, Http2HeadersEncoder.SensitivityDetector sensitivityDetector) throws Http2Exception {
    if (this.ignoreMaxHeaderListSize) {
      encodeHeadersIgnoreMaxHeaderListSize(out, headers, sensitivityDetector);
    } else {
      encodeHeadersEnforceMaxHeaderListSize(streamId, out, headers, sensitivityDetector);
    } 
  }
  
  private void encodeHeadersEnforceMaxHeaderListSize(int streamId, ByteBuf out, Http2Headers headers, Http2HeadersEncoder.SensitivityDetector sensitivityDetector) throws Http2Exception {
    long headerSize = 0L;
    for (Map.Entry<CharSequence, CharSequence> header : (Iterable<Map.Entry<CharSequence, CharSequence>>)headers) {
      CharSequence name = header.getKey();
      CharSequence value = header.getValue();
      headerSize += HpackHeaderField.sizeOf(name, value);
      if (headerSize > this.maxHeaderListSize)
        Http2CodecUtil.headerListSizeExceeded(streamId, this.maxHeaderListSize, false); 
    } 
    encodeHeadersIgnoreMaxHeaderListSize(out, headers, sensitivityDetector);
  }
  
  private void encodeHeadersIgnoreMaxHeaderListSize(ByteBuf out, Http2Headers headers, Http2HeadersEncoder.SensitivityDetector sensitivityDetector) {
    for (Map.Entry<CharSequence, CharSequence> header : (Iterable<Map.Entry<CharSequence, CharSequence>>)headers) {
      CharSequence name = header.getKey();
      CharSequence value = header.getValue();
      encodeHeader(out, name, value, sensitivityDetector.isSensitive(name, value), 
          HpackHeaderField.sizeOf(name, value));
    } 
  }
  
  private void encodeHeader(ByteBuf out, CharSequence name, CharSequence value, boolean sensitive, long headerSize) {
    if (sensitive) {
      int nameIndex = getNameIndex(name);
      encodeLiteral(out, name, value, HpackUtil.IndexType.NEVER, nameIndex);
      return;
    } 
    if (this.maxHeaderTableSize == 0L) {
      int staticTableIndex = HpackStaticTable.getIndexInsensitive(name, value);
      if (staticTableIndex == -1) {
        int nameIndex = HpackStaticTable.getIndex(name);
        encodeLiteral(out, name, value, HpackUtil.IndexType.NONE, nameIndex);
      } else {
        encodeInteger(out, 128, 7, staticTableIndex);
      } 
      return;
    } 
    if (headerSize > this.maxHeaderTableSize) {
      int nameIndex = getNameIndex(name);
      encodeLiteral(out, name, value, HpackUtil.IndexType.NONE, nameIndex);
      return;
    } 
    int nameHash = AsciiString.hashCode(name);
    int valueHash = AsciiString.hashCode(value);
    NameValueEntry headerField = getEntryInsensitive(name, nameHash, value, valueHash);
    if (headerField != null) {
      encodeInteger(out, 128, 7, getIndexPlusOffset(headerField.counter));
    } else {
      int staticTableIndex = HpackStaticTable.getIndexInsensitive(name, value);
      if (staticTableIndex != -1) {
        encodeInteger(out, 128, 7, staticTableIndex);
      } else {
        ensureCapacity(headerSize);
        encodeAndAddEntries(out, name, nameHash, value, valueHash);
        this.size += headerSize;
      } 
    } 
  }
  
  private void encodeAndAddEntries(ByteBuf out, CharSequence name, int nameHash, CharSequence value, int valueHash) {
    int staticTableIndex = HpackStaticTable.getIndex(name);
    int nextCounter = latestCounter() - 1;
    if (staticTableIndex == -1) {
      NameEntry e = getEntry(name, nameHash);
      if (e == null) {
        encodeLiteral(out, name, value, HpackUtil.IndexType.INCREMENTAL, -1);
        addNameEntry(name, nameHash, nextCounter);
        addNameValueEntry(name, value, nameHash, valueHash, nextCounter);
      } else {
        encodeLiteral(out, name, value, HpackUtil.IndexType.INCREMENTAL, getIndexPlusOffset(e.counter));
        addNameValueEntry(e.name, value, nameHash, valueHash, nextCounter);
        e.counter = nextCounter;
      } 
    } else {
      encodeLiteral(out, name, value, HpackUtil.IndexType.INCREMENTAL, staticTableIndex);
      addNameValueEntry(
          (HpackStaticTable.getEntry(staticTableIndex)).name, value, nameHash, valueHash, nextCounter);
    } 
  }
  
  public void setMaxHeaderTableSize(ByteBuf out, long maxHeaderTableSize) throws Http2Exception {
    if (maxHeaderTableSize < 0L || maxHeaderTableSize > 4294967295L)
      throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Header Table Size must be >= %d and <= %d but was %d", new Object[] { Long.valueOf(0L), Long.valueOf(4294967295L), Long.valueOf(maxHeaderTableSize) }); 
    if (this.maxHeaderTableSize == maxHeaderTableSize)
      return; 
    this.maxHeaderTableSize = maxHeaderTableSize;
    ensureCapacity(0L);
    encodeInteger(out, 32, 5, maxHeaderTableSize);
  }
  
  public long getMaxHeaderTableSize() {
    return this.maxHeaderTableSize;
  }
  
  public void setMaxHeaderListSize(long maxHeaderListSize) throws Http2Exception {
    if (maxHeaderListSize < 0L || maxHeaderListSize > 4294967295L)
      throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Header List Size must be >= %d and <= %d but was %d", new Object[] { Long.valueOf(0L), Long.valueOf(4294967295L), Long.valueOf(maxHeaderListSize) }); 
    this.maxHeaderListSize = maxHeaderListSize;
  }
  
  public long getMaxHeaderListSize() {
    return this.maxHeaderListSize;
  }
  
  private static void encodeInteger(ByteBuf out, int mask, int n, int i) {
    encodeInteger(out, mask, n, i);
  }
  
  private static void encodeInteger(ByteBuf out, int mask, int n, long i) {
    assert n >= 0 && n <= 8 : "N: " + n;
    int nbits = 255 >>> 8 - n;
    if (i < nbits) {
      out.writeByte((int)(mask | i));
    } else {
      out.writeByte(mask | nbits);
      long length = i - nbits;
      for (; (length & 0xFFFFFFFFFFFFFF80L) != 0L; length >>>= 7L)
        out.writeByte((int)(length & 0x7FL | 0x80L)); 
      out.writeByte((int)length);
    } 
  }
  
  private void encodeStringLiteral(ByteBuf out, CharSequence string) {
    int huffmanLength;
    if (string.length() >= this.huffCodeThreshold && (
      huffmanLength = this.hpackHuffmanEncoder.getEncodedLength(string)) < string.length()) {
      encodeInteger(out, 128, 7, huffmanLength);
      this.hpackHuffmanEncoder.encode(out, string);
    } else {
      encodeInteger(out, 0, 7, string.length());
      if (string instanceof AsciiString) {
        AsciiString asciiString = (AsciiString)string;
        out.writeBytes(asciiString.array(), asciiString.arrayOffset(), asciiString.length());
      } else {
        out.writeCharSequence(string, CharsetUtil.ISO_8859_1);
      } 
    } 
  }
  
  private void encodeLiteral(ByteBuf out, CharSequence name, CharSequence value, HpackUtil.IndexType indexType, int nameIndex) {
    boolean nameIndexValid = (nameIndex != -1);
    switch (indexType) {
      case INCREMENTAL:
        encodeInteger(out, 64, 6, nameIndexValid ? nameIndex : 0);
        break;
      case NONE:
        encodeInteger(out, 0, 4, nameIndexValid ? nameIndex : 0);
        break;
      case NEVER:
        encodeInteger(out, 16, 4, nameIndexValid ? nameIndex : 0);
        break;
      default:
        throw new Error("should not reach here");
    } 
    if (!nameIndexValid)
      encodeStringLiteral(out, name); 
    encodeStringLiteral(out, value);
  }
  
  private int getNameIndex(CharSequence name) {
    int index = HpackStaticTable.getIndex(name);
    if (index != -1)
      return index; 
    NameEntry e = getEntry(name, AsciiString.hashCode(name));
    return (e == null) ? -1 : getIndexPlusOffset(e.counter);
  }
  
  private void ensureCapacity(long headerSize) {
    while (this.maxHeaderTableSize - this.size < headerSize)
      remove(); 
  }
  
  int length() {
    return isEmpty() ? 0 : getIndex(this.head.after.counter);
  }
  
  long size() {
    return this.size;
  }
  
  HpackHeaderField getHeaderField(int index) {
    NameValueEntry entry = this.head;
    while (index++ < length())
      entry = entry.after; 
    return entry;
  }
  
  private NameValueEntry getEntryInsensitive(CharSequence name, int nameHash, CharSequence value, int valueHash) {
    int h = hash(nameHash, valueHash);
    for (NameValueEntry e = this.nameValueEntries[bucket(h)]; e != null; e = e.next) {
      if (e.hash == h && HpackUtil.equalsVariableTime(value, e.value) && HpackUtil.equalsVariableTime(name, e.name))
        return e; 
    } 
    return null;
  }
  
  private NameEntry getEntry(CharSequence name, int nameHash) {
    for (NameEntry e = this.nameEntries[bucket(nameHash)]; e != null; e = e.next) {
      if (e.hash == nameHash && HpackUtil.equalsConstantTime(name, e.name) != 0)
        return e; 
    } 
    return null;
  }
  
  private int getIndexPlusOffset(int counter) {
    return getIndex(counter) + HpackStaticTable.length;
  }
  
  private int getIndex(int counter) {
    return counter - latestCounter() + 1;
  }
  
  private int latestCounter() {
    return this.latest.counter;
  }
  
  private void addNameEntry(CharSequence name, int nameHash, int nextCounter) {
    int bucket = bucket(nameHash);
    this.nameEntries[bucket] = new NameEntry(nameHash, name, nextCounter, this.nameEntries[bucket]);
  }
  
  private void addNameValueEntry(CharSequence name, CharSequence value, int nameHash, int valueHash, int nextCounter) {
    int hash = hash(nameHash, valueHash);
    int bucket = bucket(hash);
    NameValueEntry e = new NameValueEntry(hash, name, value, nextCounter, this.nameValueEntries[bucket]);
    this.nameValueEntries[bucket] = e;
    this.latest.after = e;
    this.latest = e;
  }
  
  private void remove() {
    NameValueEntry eldest = this.head.after;
    removeNameValueEntry(eldest);
    removeNameEntryMatchingCounter(eldest.name, eldest.counter);
    this.head.after = eldest.after;
    eldest.unlink();
    this.size -= eldest.size();
    if (isEmpty())
      this.latest = this.head; 
  }
  
  private boolean isEmpty() {
    return (this.size == 0L);
  }
  
  private void removeNameValueEntry(NameValueEntry eldest) {
    int bucket = bucket(eldest.hash);
    NameValueEntry e = this.nameValueEntries[bucket];
    if (e == eldest) {
      this.nameValueEntries[bucket] = eldest.next;
    } else {
      while (e.next != eldest)
        e = e.next; 
      e.next = eldest.next;
    } 
  }
  
  private void removeNameEntryMatchingCounter(CharSequence name, int counter) {
    int hash = AsciiString.hashCode(name);
    int bucket = bucket(hash);
    NameEntry e = this.nameEntries[bucket];
    if (e == null)
      return; 
    if (counter == e.counter) {
      this.nameEntries[bucket] = e.next;
      e.unlink();
    } else {
      NameEntry prev = e;
      e = e.next;
      while (e != null) {
        if (counter == e.counter) {
          prev.next = e.next;
          e.unlink();
          break;
        } 
        prev = e;
        e = e.next;
      } 
    } 
  }
  
  private int bucket(int h) {
    return h & this.hashMask;
  }
  
  private static int hash(int nameHash, int valueHash) {
    return 31 * nameHash + valueHash;
  }
  
  private static final class NameEntry {
    NameEntry next;
    
    final CharSequence name;
    
    final int hash;
    
    int counter;
    
    NameEntry(int hash, CharSequence name, int counter, NameEntry next) {
      this.hash = hash;
      this.name = name;
      this.counter = counter;
      this.next = next;
    }
    
    void unlink() {
      this.next = null;
    }
  }
  
  private static final class NameValueEntry extends HpackHeaderField {
    NameValueEntry after;
    
    NameValueEntry next;
    
    final int hash;
    
    final int counter;
    
    NameValueEntry(int hash, CharSequence name, CharSequence value, int counter, NameValueEntry next) {
      super(name, value);
      this.next = next;
      this.hash = hash;
      this.counter = counter;
    }
    
    void unlink() {
      this.after = null;
      this.next = null;
    }
  }
}
