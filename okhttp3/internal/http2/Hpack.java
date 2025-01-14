package okhttp3.internal.http2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import okio.Source;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000,\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\003\n\002\020$\n\002\020\b\n\002\b\f\n\002\020\021\n\002\030\002\n\002\b\007\bÆ\002\030\0002\0020\001:\002\034\035B\t\b\002¢\006\004\b\002\020\003J\025\020\006\032\0020\0042\006\020\005\032\0020\004¢\006\004\b\006\020\007J\033\020\n\032\016\022\004\022\0020\004\022\004\022\0020\t0\bH\002¢\006\004\b\n\020\013R#\020\f\032\016\022\004\022\0020\004\022\004\022\0020\t0\b8\006¢\006\f\n\004\b\f\020\r\032\004\b\016\020\013R\024\020\017\032\0020\t8\002XT¢\006\006\n\004\b\017\020\020R\024\020\021\032\0020\t8\002XT¢\006\006\n\004\b\021\020\020R\024\020\022\032\0020\t8\002XT¢\006\006\n\004\b\022\020\020R\024\020\023\032\0020\t8\002XT¢\006\006\n\004\b\023\020\020R\024\020\024\032\0020\t8\002XT¢\006\006\n\004\b\024\020\020R\024\020\025\032\0020\t8\002XT¢\006\006\n\004\b\025\020\020R\035\020\030\032\b\022\004\022\0020\0270\0268\006¢\006\f\n\004\b\030\020\031\032\004\b\032\020\033¨\006\036"}, d2 = {"Lokhttp3/internal/http2/Hpack;", "", "<init>", "()V", "Lokio/ByteString;", "name", "checkLowercase", "(Lokio/ByteString;)Lokio/ByteString;", "", "", "nameToFirstIndex", "()Ljava/util/Map;", "NAME_TO_FIRST_INDEX", "Ljava/util/Map;", "getNAME_TO_FIRST_INDEX", "PREFIX_4_BITS", "I", "PREFIX_5_BITS", "PREFIX_6_BITS", "PREFIX_7_BITS", "SETTINGS_HEADER_TABLE_SIZE", "SETTINGS_HEADER_TABLE_SIZE_LIMIT", "", "Lokhttp3/internal/http2/Header;", "STATIC_HEADER_TABLE", "[Lokhttp3/internal/http2/Header;", "getSTATIC_HEADER_TABLE", "()[Lokhttp3/internal/http2/Header;", "Reader", "Writer", "okhttp"})
public final class Hpack {
  @NotNull
  public static final Hpack INSTANCE = new Hpack();
  
  private static final int PREFIX_4_BITS = 15;
  
  private static final int PREFIX_5_BITS = 31;
  
  private static final int PREFIX_6_BITS = 63;
  
  private static final int PREFIX_7_BITS = 127;
  
  private static final int SETTINGS_HEADER_TABLE_SIZE = 4096;
  
  private static final int SETTINGS_HEADER_TABLE_SIZE_LIMIT = 16384;
  
  @NotNull
  private static final Header[] STATIC_HEADER_TABLE;
  
  @NotNull
  public final Header[] getSTATIC_HEADER_TABLE() {
    return STATIC_HEADER_TABLE;
  }
  
  static {
    Header[] arrayOfHeader = new Header[61];
    arrayOfHeader[0] = new Header(Header.TARGET_AUTHORITY, "");
    arrayOfHeader[1] = 
      new Header(Header.TARGET_METHOD, "GET");
    arrayOfHeader[2] = new Header(Header.TARGET_METHOD, "POST");
    arrayOfHeader[3] = new Header(Header.TARGET_PATH, "/");
    arrayOfHeader[4] = new Header(Header.TARGET_PATH, "/index.html");
    arrayOfHeader[5] = new Header(Header.TARGET_SCHEME, "http");
    arrayOfHeader[6] = new Header(Header.TARGET_SCHEME, "https");
    arrayOfHeader[7] = new Header(Header.RESPONSE_STATUS, "200");
    arrayOfHeader[8] = new Header(Header.RESPONSE_STATUS, "204");
    arrayOfHeader[9] = new Header(Header.RESPONSE_STATUS, "206");
    arrayOfHeader[10] = new Header(Header.RESPONSE_STATUS, "304");
    arrayOfHeader[11] = new Header(Header.RESPONSE_STATUS, "400");
    arrayOfHeader[12] = new Header(Header.RESPONSE_STATUS, "404");
    arrayOfHeader[13] = new Header(Header.RESPONSE_STATUS, "500");
    arrayOfHeader[14] = new Header("accept-charset", "");
    arrayOfHeader[15] = new Header("accept-encoding", "gzip, deflate");
    arrayOfHeader[16] = new Header("accept-language", "");
    arrayOfHeader[17] = new Header("accept-ranges", "");
    arrayOfHeader[18] = new Header("accept", "");
    arrayOfHeader[19] = new Header("access-control-allow-origin", "");
    arrayOfHeader[20] = new Header("age", "");
    arrayOfHeader[21] = new Header("allow", "");
    arrayOfHeader[22] = new Header("authorization", "");
    arrayOfHeader[23] = new Header("cache-control", "");
    arrayOfHeader[24] = new Header("content-disposition", "");
    arrayOfHeader[25] = new Header("content-encoding", "");
    arrayOfHeader[26] = new Header("content-language", "");
    arrayOfHeader[27] = new Header("content-length", "");
    arrayOfHeader[28] = new Header("content-location", "");
    arrayOfHeader[29] = new Header("content-range", "");
    arrayOfHeader[30] = new Header("content-type", "");
    arrayOfHeader[31] = new Header("cookie", "");
    arrayOfHeader[32] = new Header("date", "");
    arrayOfHeader[33] = new Header("etag", "");
    arrayOfHeader[34] = new Header("expect", "");
    arrayOfHeader[35] = new Header("expires", "");
    arrayOfHeader[36] = new Header("from", "");
    arrayOfHeader[37] = new Header("host", "");
    arrayOfHeader[38] = new Header("if-match", "");
    arrayOfHeader[39] = new Header("if-modified-since", "");
    arrayOfHeader[40] = new Header("if-none-match", "");
    arrayOfHeader[41] = new Header("if-range", "");
    arrayOfHeader[42] = new Header("if-unmodified-since", "");
    arrayOfHeader[43] = new Header("last-modified", "");
    arrayOfHeader[44] = new Header("link", "");
    arrayOfHeader[45] = new Header("location", "");
    arrayOfHeader[46] = new Header("max-forwards", "");
    arrayOfHeader[47] = new Header("proxy-authenticate", "");
    arrayOfHeader[48] = new Header("proxy-authorization", "");
    arrayOfHeader[49] = new Header("range", "");
    arrayOfHeader[50] = new Header("referer", "");
    arrayOfHeader[51] = new Header("refresh", "");
    arrayOfHeader[52] = new Header("retry-after", "");
    arrayOfHeader[53] = new Header("server", "");
    arrayOfHeader[54] = new Header("set-cookie", "");
    arrayOfHeader[55] = new Header("strict-transport-security", "");
    arrayOfHeader[56] = new Header("transfer-encoding", "");
    arrayOfHeader[57] = new Header("user-agent", "");
    arrayOfHeader[58] = new Header("vary", "");
    arrayOfHeader[59] = new Header("via", "");
    arrayOfHeader[60] = new Header("www-authenticate", "");
    STATIC_HEADER_TABLE = arrayOfHeader;
  }
  
  @NotNull
  private static final Map<ByteString, Integer> NAME_TO_FIRST_INDEX = INSTANCE.nameToFirstIndex();
  
  @NotNull
  public final Map<ByteString, Integer> getNAME_TO_FIRST_INDEX() {
    return NAME_TO_FIRST_INDEX;
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000R\n\002\030\002\n\002\020\000\n\002\030\002\n\000\n\002\020\b\n\002\b\004\n\002\020\002\n\002\b\b\n\002\020 \n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\005\n\002\020\013\n\002\b\022\n\002\020\021\n\002\b\005\n\002\020!\n\002\b\003\n\002\030\002\n\002\b\002\030\0002\0020\001B#\b\007\022\006\020\003\032\0020\002\022\006\020\005\032\0020\004\022\b\b\002\020\006\032\0020\004¢\006\004\b\007\020\bJ\017\020\n\032\0020\tH\002¢\006\004\b\n\020\013J\017\020\f\032\0020\tH\002¢\006\004\b\f\020\013J\027\020\016\032\0020\0042\006\020\r\032\0020\004H\002¢\006\004\b\016\020\017J\027\020\021\032\0020\0042\006\020\020\032\0020\004H\002¢\006\004\b\021\020\017J\023\020\024\032\b\022\004\022\0020\0230\022¢\006\004\b\024\020\025J\027\020\027\032\0020\0262\006\020\r\032\0020\004H\002¢\006\004\b\027\020\030J\037\020\032\032\0020\t2\006\020\r\032\0020\0042\006\020\031\032\0020\023H\002¢\006\004\b\032\020\033J\027\020\035\032\0020\0342\006\020\r\032\0020\004H\002¢\006\004\b\035\020\036J\r\020\006\032\0020\004¢\006\004\b\006\020\037J\017\020 \032\0020\004H\002¢\006\004\b \020\037J\r\020!\032\0020\026¢\006\004\b!\020\"J\r\020#\032\0020\t¢\006\004\b#\020\013J\027\020$\032\0020\t2\006\020\r\032\0020\004H\002¢\006\004\b$\020%J\035\020(\032\0020\0042\006\020&\032\0020\0042\006\020'\032\0020\004¢\006\004\b(\020)J\027\020+\032\0020\t2\006\020*\032\0020\004H\002¢\006\004\b+\020%J\017\020,\032\0020\tH\002¢\006\004\b,\020\013J\027\020-\032\0020\t2\006\020\r\032\0020\004H\002¢\006\004\b-\020%J\017\020.\032\0020\tH\002¢\006\004\b.\020\013R\036\0200\032\n\022\006\022\004\030\0010\0230/8\006@\006X\016¢\006\006\n\004\b0\0201R\026\0202\032\0020\0048\006@\006X\016¢\006\006\n\004\b2\0203R\026\0204\032\0020\0048\006@\006X\016¢\006\006\n\004\b4\0203R\032\0206\032\b\022\004\022\0020\023058\002X\004¢\006\006\n\004\b6\0207R\024\020\005\032\0020\0048\002X\004¢\006\006\n\004\b\005\0203R\026\020\006\032\0020\0048\002@\002X\016¢\006\006\n\004\b\006\0203R\026\0208\032\0020\0048\002@\002X\016¢\006\006\n\004\b8\0203R\024\020\003\032\002098\002X\004¢\006\006\n\004\b\003\020:¨\006;"}, d2 = {"Lokhttp3/internal/http2/Hpack$Reader;", "", "Lokio/Source;", "source", "", "headerTableSizeSetting", "maxDynamicTableByteCount", "<init>", "(Lokio/Source;II)V", "", "adjustDynamicTableByteCount", "()V", "clearDynamicTable", "index", "dynamicTableIndex", "(I)I", "bytesToRecover", "evictToRecoverBytes", "", "Lokhttp3/internal/http2/Header;", "getAndResetHeaderList", "()Ljava/util/List;", "Lokio/ByteString;", "getName", "(I)Lokio/ByteString;", "entry", "insertIntoDynamicTable", "(ILokhttp3/internal/http2/Header;)V", "", "isStaticHeader", "(I)Z", "()I", "readByte", "readByteString", "()Lokio/ByteString;", "readHeaders", "readIndexedHeader", "(I)V", "firstByte", "prefixMask", "readInt", "(II)I", "nameIndex", "readLiteralHeaderWithIncrementalIndexingIndexedName", "readLiteralHeaderWithIncrementalIndexingNewName", "readLiteralHeaderWithoutIndexingIndexedName", "readLiteralHeaderWithoutIndexingNewName", "", "dynamicTable", "[Lokhttp3/internal/http2/Header;", "dynamicTableByteCount", "I", "headerCount", "", "headerList", "Ljava/util/List;", "nextHeaderIndex", "Lokio/BufferedSource;", "Lokio/BufferedSource;", "okhttp"})
  public static final class Reader {
    private final int headerTableSizeSetting;
    
    private int maxDynamicTableByteCount;
    
    @NotNull
    private final List<Header> headerList;
    
    @NotNull
    private final BufferedSource source;
    
    @JvmField
    @NotNull
    public Header[] dynamicTable;
    
    private int nextHeaderIndex;
    
    @JvmField
    public int headerCount;
    
    @JvmField
    public int dynamicTableByteCount;
    
    @JvmOverloads
    public Reader(@NotNull Source source, int headerTableSizeSetting, int maxDynamicTableByteCount) {
      this.headerTableSizeSetting = headerTableSizeSetting;
      this.maxDynamicTableByteCount = maxDynamicTableByteCount;
      this.headerList = new ArrayList<>();
      this.source = Okio.buffer(source);
      this.dynamicTable = new Header[8];
      this.nextHeaderIndex = this.dynamicTable.length - 1;
    }
    
    @NotNull
    public final List<Header> getAndResetHeaderList() {
      List<Header> result = CollectionsKt.toList(this.headerList);
      this.headerList.clear();
      return result;
    }
    
    public final int maxDynamicTableByteCount() {
      return this.maxDynamicTableByteCount;
    }
    
    private final void adjustDynamicTableByteCount() {
      if (this.maxDynamicTableByteCount < this.dynamicTableByteCount)
        if (this.maxDynamicTableByteCount == 0) {
          clearDynamicTable();
        } else {
          evictToRecoverBytes(this.dynamicTableByteCount - this.maxDynamicTableByteCount);
        }  
    }
    
    private final void clearDynamicTable() {
      ArraysKt.fill$default((Object[])this.dynamicTable, null, 0, 0, 6, null);
      this.nextHeaderIndex = this.dynamicTable.length - 1;
      this.headerCount = 0;
      this.dynamicTableByteCount = 0;
    }
    
    private final int evictToRecoverBytes(int bytesToRecover) {
      int i = bytesToRecover;
      int entriesToEvict = 0;
      if (i > 0) {
        int j = this.dynamicTable.length - 1;
        while (j >= this.nextHeaderIndex && i > 0) {
          Intrinsics.checkNotNull(this.dynamicTable[j]);
          Header toEvict = this.dynamicTable[j];
          i -= toEvict.hpackSize;
          this.dynamicTableByteCount -= toEvict.hpackSize;
          int k = this.headerCount;
          this.headerCount = k + -1;
          entriesToEvict++;
          j--;
        } 
        System.arraycopy(this.dynamicTable, this.nextHeaderIndex + 1, this.dynamicTable, 
            this.nextHeaderIndex + 1 + entriesToEvict, this.headerCount);
        this.nextHeaderIndex += entriesToEvict;
      } 
      return entriesToEvict;
    }
    
    public final void readHeaders() throws IOException {
      while (!this.source.exhausted()) {
        int b = Util.and(this.source.readByte(), 255);
        if (b == 128)
          throw new IOException("index == 0"); 
        if ((b & 0x80) == 128) {
          int i = readInt(b, 127);
          readIndexedHeader(i - 1);
          continue;
        } 
        if (b == 64) {
          readLiteralHeaderWithIncrementalIndexingNewName();
          continue;
        } 
        if ((b & 0x40) == 64) {
          int i = readInt(b, 63);
          readLiteralHeaderWithIncrementalIndexingIndexedName(i - 1);
          continue;
        } 
        if ((b & 0x20) == 32) {
          this.maxDynamicTableByteCount = readInt(b, 31);
          if (this.maxDynamicTableByteCount < 0 || this.maxDynamicTableByteCount > this.headerTableSizeSetting)
            throw new IOException("Invalid dynamic table size update " + this.maxDynamicTableByteCount); 
          adjustDynamicTableByteCount();
          continue;
        } 
        if (b == 16 || b == 0) {
          readLiteralHeaderWithoutIndexingNewName();
          continue;
        } 
        int index = readInt(b, 15);
        readLiteralHeaderWithoutIndexingIndexedName(index - 1);
      } 
    }
    
    private final void readIndexedHeader(int index) throws IOException {
      if (isStaticHeader(index)) {
        Header staticEntry = Hpack.INSTANCE.getSTATIC_HEADER_TABLE()[index];
        this.headerList.add(staticEntry);
      } else {
        int dynamicTableIndex = dynamicTableIndex(index - (Hpack.INSTANCE.getSTATIC_HEADER_TABLE()).length);
        if (dynamicTableIndex < 0 || dynamicTableIndex >= this.dynamicTable.length)
          throw new IOException("Header index too large " + (index + 1)); 
        Intrinsics.checkNotNull(this.dynamicTable[dynamicTableIndex]);
        this.headerList.add(this.dynamicTable[dynamicTableIndex]);
      } 
    }
    
    private final int dynamicTableIndex(int index) {
      return this.nextHeaderIndex + 1 + index;
    }
    
    private final void readLiteralHeaderWithoutIndexingIndexedName(int index) throws IOException {
      ByteString name = getName(index);
      ByteString value = readByteString();
      this.headerList.add(new Header(name, value));
    }
    
    private final void readLiteralHeaderWithoutIndexingNewName() throws IOException {
      ByteString name = Hpack.INSTANCE.checkLowercase(readByteString());
      ByteString value = readByteString();
      this.headerList.add(new Header(name, value));
    }
    
    private final void readLiteralHeaderWithIncrementalIndexingIndexedName(int nameIndex) throws IOException {
      ByteString name = getName(nameIndex);
      ByteString value = readByteString();
      insertIntoDynamicTable(-1, new Header(name, value));
    }
    
    private final void readLiteralHeaderWithIncrementalIndexingNewName() throws IOException {
      ByteString name = Hpack.INSTANCE.checkLowercase(readByteString());
      ByteString value = readByteString();
      insertIntoDynamicTable(-1, new Header(name, value));
    }
    
    private final ByteString getName(int index) throws IOException {
      int dynamicTableIndex = dynamicTableIndex(index - (Hpack.INSTANCE.getSTATIC_HEADER_TABLE()).length);
      if (dynamicTableIndex < 0 || dynamicTableIndex >= this.dynamicTable.length)
        throw new IOException("Header index too large " + (index + 1)); 
      Intrinsics.checkNotNull(this.dynamicTable[dynamicTableIndex]);
      return isStaticHeader(index) ? (Hpack.INSTANCE.getSTATIC_HEADER_TABLE()[index]).name : (this.dynamicTable[dynamicTableIndex]).name;
    }
    
    private final boolean isStaticHeader(int index) {
      return (index >= 0 && index <= (Hpack.INSTANCE.getSTATIC_HEADER_TABLE()).length - 1);
    }
    
    private final void insertIntoDynamicTable(int index, Header entry) {
      int i = index;
      this.headerList.add(entry);
      int delta = entry.hpackSize;
      if (i != -1) {
        Intrinsics.checkNotNull(this.dynamicTable[dynamicTableIndex(i)]);
        delta -= (this.dynamicTable[dynamicTableIndex(i)]).hpackSize;
      } 
      if (delta > this.maxDynamicTableByteCount) {
        clearDynamicTable();
        return;
      } 
      int bytesToRecover = this.dynamicTableByteCount + delta - this.maxDynamicTableByteCount;
      int entriesEvicted = evictToRecoverBytes(bytesToRecover);
      if (i == -1) {
        if (this.headerCount + 1 > this.dynamicTable.length) {
          Header[] doubled = new Header[this.dynamicTable.length * 2];
          System.arraycopy(this.dynamicTable, 0, doubled, this.dynamicTable.length, this.dynamicTable.length);
          this.nextHeaderIndex = this.dynamicTable.length - 1;
          this.dynamicTable = doubled;
        } 
        int j = this.nextHeaderIndex;
        this.nextHeaderIndex = j + -1;
        i = j;
        this.dynamicTable[i] = entry;
        j = this.headerCount;
        this.headerCount = j + 1;
      } else {
        i += dynamicTableIndex(i) + entriesEvicted;
        this.dynamicTable[i] = entry;
      } 
      this.dynamicTableByteCount += delta;
    }
    
    private final int readByte() throws IOException {
      return Util.and(this.source.readByte(), 255);
    }
    
    public final int readInt(int firstByte, int prefixMask) throws IOException {
      int b, prefix = firstByte & prefixMask;
      if (prefix < prefixMask)
        return prefix; 
      int result = prefixMask;
      int shift = 0;
      while (true) {
        b = readByte();
        if ((b & 0x80) != 0) {
          result += (b & 0x7F) << shift;
          shift += 7;
          continue;
        } 
        break;
      } 
      result += b << shift;
      return result;
    }
    
    @NotNull
    public final ByteString readByteString() throws IOException {
      int firstByte = readByte();
      boolean huffmanDecode = ((firstByte & 0x80) == 128);
      long length = readInt(firstByte, 127);
      Buffer decodeBuffer = new Buffer();
      Huffman.INSTANCE.decode(this.source, length, (BufferedSink)decodeBuffer);
      return huffmanDecode ? decodeBuffer.readByteString() : 
        
        this.source.readByteString(length);
    }
    
    @JvmOverloads
    public Reader(@NotNull Source source, int headerTableSizeSetting) {
      this(source, headerTableSizeSetting, 0, 4, null);
    }
  }
  
  private final Map<ByteString, Integer> nameToFirstIndex() {
    LinkedHashMap<Object, Object> result = new LinkedHashMap<>(STATIC_HEADER_TABLE.length);
    for (int i = 0, j = STATIC_HEADER_TABLE.length; i < j; i++) {
      if (!result.containsKey((STATIC_HEADER_TABLE[i]).name)) {
        Integer integer = Integer.valueOf(i);
        result.put((STATIC_HEADER_TABLE[i]).name, integer);
      } 
    } 
    Intrinsics.checkNotNullExpressionValue(Collections.unmodifiableMap(result), "unmodifiableMap(result)");
    return (Map)Collections.unmodifiableMap(result);
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000D\n\002\030\002\n\002\020\000\n\002\020\b\n\000\n\002\020\013\n\000\n\002\030\002\n\002\b\003\n\002\020\002\n\002\b\006\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\003\n\002\020 \n\002\b\b\n\002\020\021\n\002\b\f\030\0002\0020\001B%\b\007\022\b\b\002\020\003\032\0020\002\022\b\b\002\020\005\032\0020\004\022\006\020\007\032\0020\006¢\006\004\b\b\020\tJ\017\020\013\032\0020\nH\002¢\006\004\b\013\020\fJ\017\020\r\032\0020\nH\002¢\006\004\b\r\020\fJ\027\020\017\032\0020\0022\006\020\016\032\0020\002H\002¢\006\004\b\017\020\020J\027\020\023\032\0020\n2\006\020\022\032\0020\021H\002¢\006\004\b\023\020\024J\025\020\025\032\0020\n2\006\020\003\032\0020\002¢\006\004\b\025\020\026J\025\020\031\032\0020\n2\006\020\030\032\0020\027¢\006\004\b\031\020\032J\033\020\035\032\0020\n2\f\020\034\032\b\022\004\022\0020\0210\033¢\006\004\b\035\020\036J%\020\"\032\0020\n2\006\020\037\032\0020\0022\006\020 \032\0020\0022\006\020!\032\0020\002¢\006\004\b\"\020#R\036\020%\032\n\022\006\022\004\030\0010\0210$8\006@\006X\016¢\006\006\n\004\b%\020&R\026\020'\032\0020\0028\006@\006X\016¢\006\006\n\004\b'\020(R\026\020)\032\0020\0048\002@\002X\016¢\006\006\n\004\b)\020*R\026\020+\032\0020\0028\006@\006X\016¢\006\006\n\004\b+\020(R\026\020\003\032\0020\0028\006@\006X\016¢\006\006\n\004\b\003\020(R\026\020,\032\0020\0028\006@\006X\016¢\006\006\n\004\b,\020(R\026\020-\032\0020\0028\002@\002X\016¢\006\006\n\004\b-\020(R\024\020\007\032\0020\0068\002X\004¢\006\006\n\004\b\007\020.R\026\020/\032\0020\0028\002@\002X\016¢\006\006\n\004\b/\020(R\024\020\005\032\0020\0048\002X\004¢\006\006\n\004\b\005\020*¨\0060"}, d2 = {"Lokhttp3/internal/http2/Hpack$Writer;", "", "", "headerTableSizeSetting", "", "useCompression", "Lokio/Buffer;", "out", "<init>", "(IZLokio/Buffer;)V", "", "adjustDynamicTableByteCount", "()V", "clearDynamicTable", "bytesToRecover", "evictToRecoverBytes", "(I)I", "Lokhttp3/internal/http2/Header;", "entry", "insertIntoDynamicTable", "(Lokhttp3/internal/http2/Header;)V", "resizeHeaderTable", "(I)V", "Lokio/ByteString;", "data", "writeByteString", "(Lokio/ByteString;)V", "", "headerBlock", "writeHeaders", "(Ljava/util/List;)V", "value", "prefixMask", "bits", "writeInt", "(III)V", "", "dynamicTable", "[Lokhttp3/internal/http2/Header;", "dynamicTableByteCount", "I", "emitDynamicTableSizeUpdate", "Z", "headerCount", "maxDynamicTableByteCount", "nextHeaderIndex", "Lokio/Buffer;", "smallestHeaderTableSizeSetting", "okhttp"})
  public static final class Writer {
    @JvmField
    public int headerTableSizeSetting;
    
    private final boolean useCompression;
    
    @NotNull
    private final Buffer out;
    
    private int smallestHeaderTableSizeSetting;
    
    private boolean emitDynamicTableSizeUpdate;
    
    @JvmField
    public int maxDynamicTableByteCount;
    
    @JvmField
    @NotNull
    public Header[] dynamicTable;
    
    private int nextHeaderIndex;
    
    @JvmField
    public int headerCount;
    
    @JvmField
    public int dynamicTableByteCount;
    
    @JvmOverloads
    public Writer(int headerTableSizeSetting, boolean useCompression, @NotNull Buffer out) {
      this.headerTableSizeSetting = headerTableSizeSetting;
      this.useCompression = useCompression;
      this.out = out;
      this.smallestHeaderTableSizeSetting = Integer.MAX_VALUE;
      this.maxDynamicTableByteCount = this.headerTableSizeSetting;
      this.dynamicTable = new Header[8];
      this.nextHeaderIndex = this.dynamicTable.length - 1;
    }
    
    private final void clearDynamicTable() {
      ArraysKt.fill$default((Object[])this.dynamicTable, null, 0, 0, 6, null);
      this.nextHeaderIndex = this.dynamicTable.length - 1;
      this.headerCount = 0;
      this.dynamicTableByteCount = 0;
    }
    
    private final int evictToRecoverBytes(int bytesToRecover) {
      int i = bytesToRecover;
      int entriesToEvict = 0;
      if (i > 0) {
        int j = this.dynamicTable.length - 1;
        while (j >= this.nextHeaderIndex && i > 0) {
          Intrinsics.checkNotNull(this.dynamicTable[j]);
          i -= (this.dynamicTable[j]).hpackSize;
          Intrinsics.checkNotNull(this.dynamicTable[j]);
          this.dynamicTableByteCount -= (this.dynamicTable[j]).hpackSize;
          int k = this.headerCount;
          this.headerCount = k + -1;
          entriesToEvict++;
          j--;
        } 
        System.arraycopy(this.dynamicTable, this.nextHeaderIndex + 1, this.dynamicTable, 
            this.nextHeaderIndex + 1 + entriesToEvict, this.headerCount);
        Arrays.fill((Object[])this.dynamicTable, this.nextHeaderIndex + 1, this.nextHeaderIndex + 1 + entriesToEvict, (Object)null);
        this.nextHeaderIndex += entriesToEvict;
      } 
      return entriesToEvict;
    }
    
    private final void insertIntoDynamicTable(Header entry) {
      int delta = entry.hpackSize;
      if (delta > this.maxDynamicTableByteCount) {
        clearDynamicTable();
        return;
      } 
      int bytesToRecover = this.dynamicTableByteCount + delta - this.maxDynamicTableByteCount;
      evictToRecoverBytes(bytesToRecover);
      if (this.headerCount + 1 > this.dynamicTable.length) {
        Header[] doubled = new Header[this.dynamicTable.length * 2];
        System.arraycopy(this.dynamicTable, 0, doubled, this.dynamicTable.length, this.dynamicTable.length);
        this.nextHeaderIndex = this.dynamicTable.length - 1;
        this.dynamicTable = doubled;
      } 
      int i = this.nextHeaderIndex;
      this.nextHeaderIndex = i + -1;
      int index = i;
      this.dynamicTable[index] = entry;
      i = this.headerCount;
      this.headerCount = i + 1;
      this.dynamicTableByteCount += delta;
    }
    
    public final void writeHeaders(@NotNull List<Header> headerBlock) throws IOException {
      Intrinsics.checkNotNullParameter(headerBlock, "headerBlock");
      if (this.emitDynamicTableSizeUpdate) {
        if (this.smallestHeaderTableSizeSetting < this.maxDynamicTableByteCount)
          writeInt(this.smallestHeaderTableSizeSetting, 31, 32); 
        this.emitDynamicTableSizeUpdate = false;
        this.smallestHeaderTableSizeSetting = Integer.MAX_VALUE;
        writeInt(this.maxDynamicTableByteCount, 31, 32);
      } 
      for (int i = 0, j = headerBlock.size(); i < j; i++) {
        Header header = headerBlock.get(i);
        ByteString name = header.name.toAsciiLowercase();
        ByteString value = header.value;
        int headerIndex = -1;
        int headerNameIndex = -1;
        Integer staticIndex = Hpack.INSTANCE.getNAME_TO_FIRST_INDEX().get(name);
        if (staticIndex != null) {
          headerNameIndex = staticIndex.intValue() + 1;
          if ((2 <= headerNameIndex) ? ((headerNameIndex < 8)) : false)
            if (Intrinsics.areEqual((Hpack.INSTANCE.getSTATIC_HEADER_TABLE()[headerNameIndex - 1]).value, value)) {
              headerIndex = headerNameIndex;
            } else if (Intrinsics.areEqual((Hpack.INSTANCE.getSTATIC_HEADER_TABLE()[headerNameIndex]).value, value)) {
              headerIndex = headerNameIndex + 1;
            }  
        } 
        if (headerIndex == -1)
          for (int k = this.nextHeaderIndex + 1, m = this.dynamicTable.length; k < m; k++) {
            Intrinsics.checkNotNull(this.dynamicTable[k]);
            if (Intrinsics.areEqual((this.dynamicTable[k]).name, name)) {
              Intrinsics.checkNotNull(this.dynamicTable[k]);
              if (Intrinsics.areEqual((this.dynamicTable[k]).value, value)) {
                headerIndex = k - this.nextHeaderIndex + (Hpack.INSTANCE.getSTATIC_HEADER_TABLE()).length;
                break;
              } 
              if (headerNameIndex == -1)
                headerNameIndex = k - this.nextHeaderIndex + (Hpack.INSTANCE.getSTATIC_HEADER_TABLE()).length; 
            } 
          }  
        if (headerIndex != -1) {
          writeInt(headerIndex, 127, 128);
        } else if (headerNameIndex == -1) {
          this.out.writeByte(64);
          writeByteString(name);
          writeByteString(value);
          insertIntoDynamicTable(header);
        } else if (name.startsWith(Header.PSEUDO_PREFIX) && !Intrinsics.areEqual(Header.TARGET_AUTHORITY, name)) {
          writeInt(headerNameIndex, 15, 0);
          writeByteString(value);
        } else {
          writeInt(headerNameIndex, 63, 64);
          writeByteString(value);
          insertIntoDynamicTable(header);
        } 
      } 
    }
    
    public final void writeInt(int value, int prefixMask, int bits) {
      int i = value;
      if (i < prefixMask) {
        this.out.writeByte(bits | i);
        return;
      } 
      this.out.writeByte(bits | prefixMask);
      i -= prefixMask;
      while (i >= 128) {
        int b = i & 0x7F;
        this.out.writeByte(b | 0x80);
        i >>>= 7;
      } 
      this.out.writeByte(i);
    }
    
    public final void writeByteString(@NotNull ByteString data) throws IOException {
      Intrinsics.checkNotNullParameter(data, "data");
      if (this.useCompression && Huffman.INSTANCE.encodedLength(data) < data.size()) {
        Buffer huffmanBuffer = new Buffer();
        Huffman.INSTANCE.encode(data, (BufferedSink)huffmanBuffer);
        ByteString huffmanBytes = huffmanBuffer.readByteString();
        writeInt(huffmanBytes.size(), 127, 128);
        this.out.write(huffmanBytes);
      } else {
        writeInt(data.size(), 127, 0);
        this.out.write(data);
      } 
    }
    
    public final void resizeHeaderTable(int headerTableSizeSetting) {
      this.headerTableSizeSetting = headerTableSizeSetting;
      int effectiveHeaderTableSize = Math.min(headerTableSizeSetting, 16384);
      if (this.maxDynamicTableByteCount == effectiveHeaderTableSize)
        return; 
      if (effectiveHeaderTableSize < this.maxDynamicTableByteCount)
        this.smallestHeaderTableSizeSetting = 
          Math.min(this.smallestHeaderTableSizeSetting, effectiveHeaderTableSize); 
      this.emitDynamicTableSizeUpdate = true;
      this.maxDynamicTableByteCount = effectiveHeaderTableSize;
      adjustDynamicTableByteCount();
    }
    
    private final void adjustDynamicTableByteCount() {
      if (this.maxDynamicTableByteCount < this.dynamicTableByteCount)
        if (this.maxDynamicTableByteCount == 0) {
          clearDynamicTable();
        } else {
          evictToRecoverBytes(this.dynamicTableByteCount - this.maxDynamicTableByteCount);
        }  
    }
    
    @JvmOverloads
    public Writer(int headerTableSizeSetting, @NotNull Buffer out) {
      this(headerTableSizeSetting, false, out, 2, null);
    }
    
    @JvmOverloads
    public Writer(@NotNull Buffer out) {
      this(0, false, out, 3, null);
    }
  }
  
  @NotNull
  public final ByteString checkLowercase(@NotNull ByteString name) throws IOException {
    Intrinsics.checkNotNullParameter(name, "name");
    for (int i = 0, j = name.size(); i < j; i++) {
      byte b = name.getByte(i);
      if ((65 <= b) ? ((b < 91)) : false)
        throw new IOException("PROTOCOL_ERROR response malformed: mixed case name: " + name.utf8()); 
    } 
    return name;
  }
}
