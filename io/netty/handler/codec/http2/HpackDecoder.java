package io.netty.handler.codec.http2;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.HttpHeaderValidationUtil;
import io.netty.util.AsciiString;
import io.netty.util.internal.ObjectUtil;

final class HpackDecoder {
  private static final Http2Exception DECODE_ULE_128_DECOMPRESSION_EXCEPTION = Http2Exception.newStatic(Http2Error.COMPRESSION_ERROR, "HPACK - decompression failure", Http2Exception.ShutdownHint.HARD_SHUTDOWN, HpackDecoder.class, "decodeULE128(..)");
  
  private static final Http2Exception DECODE_ULE_128_TO_LONG_DECOMPRESSION_EXCEPTION = Http2Exception.newStatic(Http2Error.COMPRESSION_ERROR, "HPACK - long overflow", Http2Exception.ShutdownHint.HARD_SHUTDOWN, HpackDecoder.class, "decodeULE128(..)");
  
  private static final Http2Exception DECODE_ULE_128_TO_INT_DECOMPRESSION_EXCEPTION = Http2Exception.newStatic(Http2Error.COMPRESSION_ERROR, "HPACK - int overflow", Http2Exception.ShutdownHint.HARD_SHUTDOWN, HpackDecoder.class, "decodeULE128ToInt(..)");
  
  private static final Http2Exception DECODE_ILLEGAL_INDEX_VALUE = Http2Exception.newStatic(Http2Error.COMPRESSION_ERROR, "HPACK - illegal index value", Http2Exception.ShutdownHint.HARD_SHUTDOWN, HpackDecoder.class, "decode(..)");
  
  private static final Http2Exception INDEX_HEADER_ILLEGAL_INDEX_VALUE = Http2Exception.newStatic(Http2Error.COMPRESSION_ERROR, "HPACK - illegal index value", Http2Exception.ShutdownHint.HARD_SHUTDOWN, HpackDecoder.class, "indexHeader(..)");
  
  private static final Http2Exception READ_NAME_ILLEGAL_INDEX_VALUE = Http2Exception.newStatic(Http2Error.COMPRESSION_ERROR, "HPACK - illegal index value", Http2Exception.ShutdownHint.HARD_SHUTDOWN, HpackDecoder.class, "readName(..)");
  
  private static final Http2Exception INVALID_MAX_DYNAMIC_TABLE_SIZE = Http2Exception.newStatic(Http2Error.COMPRESSION_ERROR, "HPACK - invalid max dynamic table size", Http2Exception.ShutdownHint.HARD_SHUTDOWN, HpackDecoder.class, "setDynamicTableSize(..)");
  
  private static final Http2Exception MAX_DYNAMIC_TABLE_SIZE_CHANGE_REQUIRED = Http2Exception.newStatic(Http2Error.COMPRESSION_ERROR, "HPACK - max dynamic table size change required", Http2Exception.ShutdownHint.HARD_SHUTDOWN, HpackDecoder.class, "decode(..)");
  
  private static final byte READ_HEADER_REPRESENTATION = 0;
  
  private static final byte READ_INDEXED_HEADER = 1;
  
  private static final byte READ_INDEXED_HEADER_NAME = 2;
  
  private static final byte READ_LITERAL_HEADER_NAME_LENGTH_PREFIX = 3;
  
  private static final byte READ_LITERAL_HEADER_NAME_LENGTH = 4;
  
  private static final byte READ_LITERAL_HEADER_NAME = 5;
  
  private static final byte READ_LITERAL_HEADER_VALUE_LENGTH_PREFIX = 6;
  
  private static final byte READ_LITERAL_HEADER_VALUE_LENGTH = 7;
  
  private static final byte READ_LITERAL_HEADER_VALUE = 8;
  
  private final HpackHuffmanDecoder huffmanDecoder = new HpackHuffmanDecoder();
  
  private final HpackDynamicTable hpackDynamicTable;
  
  private long maxHeaderListSize;
  
  private long maxDynamicTableSize;
  
  private long encoderMaxDynamicTableSize;
  
  private boolean maxDynamicTableSizeChangeRequired;
  
  HpackDecoder(long maxHeaderListSize) {
    this(maxHeaderListSize, 4096);
  }
  
  HpackDecoder(long maxHeaderListSize, int maxHeaderTableSize) {
    this.maxHeaderListSize = ObjectUtil.checkPositive(maxHeaderListSize, "maxHeaderListSize");
    this.maxDynamicTableSize = this.encoderMaxDynamicTableSize = maxHeaderTableSize;
    this.maxDynamicTableSizeChangeRequired = false;
    this.hpackDynamicTable = new HpackDynamicTable(maxHeaderTableSize);
  }
  
  void decode(int streamId, ByteBuf in, Http2Headers headers, boolean validateHeaders) throws Http2Exception {
    Http2HeadersSink sink = new Http2HeadersSink(streamId, headers, this.maxHeaderListSize, validateHeaders);
    decodeDynamicTableSizeUpdates(in);
    decode(in, sink);
    sink.finish();
  }
  
  private void decodeDynamicTableSizeUpdates(ByteBuf in) throws Http2Exception {
    byte b;
    while (in.isReadable() && ((b = in.getByte(in.readerIndex())) & 0x20) == 32 && (b & 0xC0) == 0) {
      in.readByte();
      int index = b & 0x1F;
      if (index == 31) {
        setDynamicTableSize(decodeULE128(in, index));
        continue;
      } 
      setDynamicTableSize(index);
    } 
  }
  
  private void decode(ByteBuf in, Http2HeadersSink sink) throws Http2Exception {
    int index = 0;
    int nameLength = 0;
    int valueLength = 0;
    byte state = 0;
    boolean huffmanEncoded = false;
    AsciiString name = null;
    HpackUtil.IndexType indexType = HpackUtil.IndexType.NONE;
    while (in.isReadable()) {
      byte b;
      HpackHeaderField indexedHeader;
      AsciiString value;
      switch (state) {
        case 0:
          b = in.readByte();
          if (this.maxDynamicTableSizeChangeRequired && (b & 0xE0) != 32)
            throw MAX_DYNAMIC_TABLE_SIZE_CHANGE_REQUIRED; 
          if (b < 0) {
            index = b & Byte.MAX_VALUE;
            switch (index) {
              case 0:
                throw DECODE_ILLEGAL_INDEX_VALUE;
              case 127:
                state = 1;
                continue;
            } 
            HpackHeaderField hpackHeaderField = getIndexedHeader(index);
            sink.appendToHeaderList((AsciiString)hpackHeaderField.name, (AsciiString)hpackHeaderField.value);
            continue;
          } 
          if ((b & 0x40) == 64) {
            indexType = HpackUtil.IndexType.INCREMENTAL;
            index = b & 0x3F;
            switch (index) {
              case 0:
                state = 3;
                continue;
              case 63:
                state = 2;
                continue;
            } 
            name = readName(index);
            nameLength = name.length();
            state = 6;
            continue;
          } 
          if ((b & 0x20) == 32)
            throw Http2Exception.connectionError(Http2Error.COMPRESSION_ERROR, "Dynamic table size update must happen at the beginning of the header block", new Object[0]); 
          indexType = ((b & 0x10) == 16) ? HpackUtil.IndexType.NEVER : HpackUtil.IndexType.NONE;
          index = b & 0xF;
          switch (index) {
            case 0:
              state = 3;
              continue;
            case 15:
              state = 2;
              continue;
          } 
          name = readName(index);
          nameLength = name.length();
          state = 6;
          continue;
        case 1:
          indexedHeader = getIndexedHeader(decodeULE128(in, index));
          sink.appendToHeaderList((AsciiString)indexedHeader.name, (AsciiString)indexedHeader.value);
          state = 0;
          continue;
        case 2:
          name = readName(decodeULE128(in, index));
          nameLength = name.length();
          state = 6;
          continue;
        case 3:
          b = in.readByte();
          huffmanEncoded = ((b & 0x80) == 128);
          index = b & Byte.MAX_VALUE;
          if (index == 127) {
            state = 4;
            continue;
          } 
          nameLength = index;
          state = 5;
          continue;
        case 4:
          nameLength = decodeULE128(in, index);
          state = 5;
          continue;
        case 5:
          if (in.readableBytes() < nameLength)
            throw notEnoughDataException(in); 
          name = readStringLiteral(in, nameLength, huffmanEncoded);
          state = 6;
          continue;
        case 6:
          b = in.readByte();
          huffmanEncoded = ((b & 0x80) == 128);
          index = b & Byte.MAX_VALUE;
          switch (index) {
            case 127:
              state = 7;
              continue;
            case 0:
              insertHeader(sink, name, AsciiString.EMPTY_STRING, indexType);
              state = 0;
              continue;
          } 
          valueLength = index;
          state = 8;
          continue;
        case 7:
          valueLength = decodeULE128(in, index);
          state = 8;
          continue;
        case 8:
          if (in.readableBytes() < valueLength)
            throw notEnoughDataException(in); 
          value = readStringLiteral(in, valueLength, huffmanEncoded);
          insertHeader(sink, name, value, indexType);
          state = 0;
          continue;
      } 
      throw new Error("should not reach here state: " + state);
    } 
    if (state != 0)
      throw Http2Exception.connectionError(Http2Error.COMPRESSION_ERROR, "Incomplete header block fragment.", new Object[0]); 
  }
  
  void setMaxHeaderTableSize(long maxHeaderTableSize) throws Http2Exception {
    if (maxHeaderTableSize < 0L || maxHeaderTableSize > 4294967295L)
      throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Header Table Size must be >= %d and <= %d but was %d", new Object[] { Long.valueOf(0L), Long.valueOf(4294967295L), Long.valueOf(maxHeaderTableSize) }); 
    this.maxDynamicTableSize = maxHeaderTableSize;
    if (this.maxDynamicTableSize < this.encoderMaxDynamicTableSize) {
      this.maxDynamicTableSizeChangeRequired = true;
      this.hpackDynamicTable.setCapacity(this.maxDynamicTableSize);
    } 
  }
  
  void setMaxHeaderListSize(long maxHeaderListSize) throws Http2Exception {
    if (maxHeaderListSize < 0L || maxHeaderListSize > 4294967295L)
      throw Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Header List Size must be >= %d and <= %d but was %d", new Object[] { Long.valueOf(0L), Long.valueOf(4294967295L), Long.valueOf(maxHeaderListSize) }); 
    this.maxHeaderListSize = maxHeaderListSize;
  }
  
  long getMaxHeaderListSize() {
    return this.maxHeaderListSize;
  }
  
  long getMaxHeaderTableSize() {
    return this.hpackDynamicTable.capacity();
  }
  
  int length() {
    return this.hpackDynamicTable.length();
  }
  
  long size() {
    return this.hpackDynamicTable.size();
  }
  
  HpackHeaderField getHeaderField(int index) {
    return this.hpackDynamicTable.getEntry(index + 1);
  }
  
  private void setDynamicTableSize(long dynamicTableSize) throws Http2Exception {
    if (dynamicTableSize > this.maxDynamicTableSize)
      throw INVALID_MAX_DYNAMIC_TABLE_SIZE; 
    this.encoderMaxDynamicTableSize = dynamicTableSize;
    this.maxDynamicTableSizeChangeRequired = false;
    this.hpackDynamicTable.setCapacity(dynamicTableSize);
  }
  
  private static HeaderType validateHeader(int streamId, AsciiString name, CharSequence value, HeaderType previousHeaderType) throws Http2Exception {
    if (Http2Headers.PseudoHeaderName.hasPseudoHeaderFormat((CharSequence)name)) {
      if (previousHeaderType == HeaderType.REGULAR_HEADER)
        throw Http2Exception.streamError(streamId, Http2Error.PROTOCOL_ERROR, "Pseudo-header field '%s' found after regular header.", new Object[] { name }); 
      Http2Headers.PseudoHeaderName pseudoHeader = Http2Headers.PseudoHeaderName.getPseudoHeader(name);
      HeaderType currentHeaderType = pseudoHeader.isRequestOnly() ? HeaderType.REQUEST_PSEUDO_HEADER : HeaderType.RESPONSE_PSEUDO_HEADER;
      if (previousHeaderType != null && currentHeaderType != previousHeaderType)
        throw Http2Exception.streamError(streamId, Http2Error.PROTOCOL_ERROR, "Mix of request and response pseudo-headers.", new Object[0]); 
      return currentHeaderType;
    } 
    if (HttpHeaderValidationUtil.isConnectionHeader((CharSequence)name, true))
      throw Http2Exception.streamError(streamId, Http2Error.PROTOCOL_ERROR, "Illegal connection-specific header '%s' encountered.", new Object[] { name }); 
    if (HttpHeaderValidationUtil.isTeNotTrailers((CharSequence)name, value))
      throw Http2Exception.streamError(streamId, Http2Error.PROTOCOL_ERROR, "Illegal value specified for the 'TE' header (only 'trailers' is allowed).", new Object[0]); 
    return HeaderType.REGULAR_HEADER;
  }
  
  private AsciiString readName(int index) throws Http2Exception {
    if (index <= HpackStaticTable.length) {
      HpackHeaderField hpackHeaderField = HpackStaticTable.getEntry(index);
      return (AsciiString)hpackHeaderField.name;
    } 
    if (index - HpackStaticTable.length <= this.hpackDynamicTable.length()) {
      HpackHeaderField hpackHeaderField = this.hpackDynamicTable.getEntry(index - HpackStaticTable.length);
      return (AsciiString)hpackHeaderField.name;
    } 
    throw READ_NAME_ILLEGAL_INDEX_VALUE;
  }
  
  private HpackHeaderField getIndexedHeader(int index) throws Http2Exception {
    if (index <= HpackStaticTable.length)
      return HpackStaticTable.getEntry(index); 
    if (index - HpackStaticTable.length <= this.hpackDynamicTable.length())
      return this.hpackDynamicTable.getEntry(index - HpackStaticTable.length); 
    throw INDEX_HEADER_ILLEGAL_INDEX_VALUE;
  }
  
  private void insertHeader(Http2HeadersSink sink, AsciiString name, AsciiString value, HpackUtil.IndexType indexType) {
    sink.appendToHeaderList(name, value);
    switch (indexType) {
      case NONE:
      case NEVER:
        return;
      case INCREMENTAL:
        this.hpackDynamicTable.add(new HpackHeaderField((CharSequence)name, (CharSequence)value));
    } 
    throw new Error("should not reach here");
  }
  
  private AsciiString readStringLiteral(ByteBuf in, int length, boolean huffmanEncoded) throws Http2Exception {
    if (huffmanEncoded)
      return this.huffmanDecoder.decode(in, length); 
    byte[] buf = new byte[length];
    in.readBytes(buf);
    return new AsciiString(buf, false);
  }
  
  private static IllegalArgumentException notEnoughDataException(ByteBuf in) {
    return new IllegalArgumentException("decode only works with an entire header block! " + in);
  }
  
  static int decodeULE128(ByteBuf in, int result) throws Http2Exception {
    int readerIndex = in.readerIndex();
    long v = decodeULE128(in, result);
    if (v > 2147483647L) {
      in.readerIndex(readerIndex);
      throw DECODE_ULE_128_TO_INT_DECOMPRESSION_EXCEPTION;
    } 
    return (int)v;
  }
  
  static long decodeULE128(ByteBuf in, long result) throws Http2Exception {
    assert result <= 127L && result >= 0L;
    boolean resultStartedAtZero = (result == 0L);
    int writerIndex = in.writerIndex();
    for (int readerIndex = in.readerIndex(), shift = 0; readerIndex < writerIndex; readerIndex++, shift += 7) {
      byte b = in.getByte(readerIndex);
      if (shift == 56 && ((b & 0x80) != 0 || (b == Byte.MAX_VALUE && !resultStartedAtZero)))
        throw DECODE_ULE_128_TO_LONG_DECOMPRESSION_EXCEPTION; 
      if ((b & 0x80) == 0) {
        in.readerIndex(readerIndex + 1);
        return result + ((b & 0x7FL) << shift);
      } 
      result += (b & 0x7FL) << shift;
    } 
    throw DECODE_ULE_128_DECOMPRESSION_EXCEPTION;
  }
  
  private enum HeaderType {
    REGULAR_HEADER, REQUEST_PSEUDO_HEADER, RESPONSE_PSEUDO_HEADER;
  }
  
  private static final class Http2HeadersSink {
    private final Http2Headers headers;
    
    private final long maxHeaderListSize;
    
    private final int streamId;
    
    private final boolean validateHeaders;
    
    private long headersLength;
    
    private boolean exceededMaxLength;
    
    private HpackDecoder.HeaderType previousType;
    
    private Http2Exception validationException;
    
    Http2HeadersSink(int streamId, Http2Headers headers, long maxHeaderListSize, boolean validateHeaders) {
      this.headers = headers;
      this.maxHeaderListSize = maxHeaderListSize;
      this.streamId = streamId;
      this.validateHeaders = validateHeaders;
    }
    
    void finish() throws Http2Exception {
      if (this.exceededMaxLength) {
        Http2CodecUtil.headerListSizeExceeded(this.streamId, this.maxHeaderListSize, true);
      } else if (this.validationException != null) {
        throw this.validationException;
      } 
    }
    
    void appendToHeaderList(AsciiString name, AsciiString value) {
      this.headersLength += HpackHeaderField.sizeOf((CharSequence)name, (CharSequence)value);
      this.exceededMaxLength |= (this.headersLength > this.maxHeaderListSize) ? 1 : 0;
      if (this.exceededMaxLength || this.validationException != null)
        return; 
      try {
        this.headers.add(name, value);
        if (this.validateHeaders)
          this.previousType = HpackDecoder.validateHeader(this.streamId, name, (CharSequence)value, this.previousType); 
      } catch (IllegalArgumentException ex) {
        this.validationException = Http2Exception.streamError(this.streamId, Http2Error.PROTOCOL_ERROR, ex, "Validation failed for header '%s': %s", new Object[] { name, ex
              .getMessage() });
      } catch (Http2Exception ex) {
        this.validationException = Http2Exception.streamError(this.streamId, Http2Error.PROTOCOL_ERROR, ex, ex.getMessage(), new Object[0]);
      } 
    }
  }
}
