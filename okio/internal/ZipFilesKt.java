package okio.internal;

import java.io.Closeable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import okio.BufferedSource;
import okio.FileHandle;
import okio.FileMetadata;
import okio.FileSystem;
import okio.Okio;
import okio.Path;
import okio.ZipFileSystem;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\000j\n\002\020 \n\002\030\002\n\000\n\002\020$\n\002\030\002\n\002\b\002\n\002\020\b\n\002\b\002\n\002\020\t\n\002\b\003\n\002\030\002\n\000\n\002\030\002\n\002\020\013\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\003\n\002\030\002\n\002\020\002\n\002\b\003\n\002\030\002\n\002\b\027\n\002\020\016\n\002\b\004\032)\020\005\032\016\022\004\022\0020\004\022\004\022\0020\0010\0032\f\020\002\032\b\022\004\022\0020\0010\000H\002¢\006\004\b\005\020\006\032!\020\013\032\004\030\0010\n2\006\020\b\032\0020\0072\006\020\t\032\0020\007H\002¢\006\004\b\013\020\f\0325\020\024\032\0020\0232\006\020\r\032\0020\0042\006\020\017\032\0020\0162\024\b\002\020\022\032\016\022\004\022\0020\001\022\004\022\0020\0210\020H\000¢\006\004\b\024\020\025\032\023\020\027\032\0020\001*\0020\026H\000¢\006\004\b\027\020\030\032\023\020\032\032\0020\031*\0020\026H\002¢\006\004\b\032\020\033\0325\020 \032\0020\036*\0020\0262\006\020\034\032\0020\0072\030\020\037\032\024\022\004\022\0020\007\022\004\022\0020\n\022\004\022\0020\0360\035H\002¢\006\004\b \020!\032\033\020$\032\0020\"*\0020\0262\006\020#\032\0020\"H\000¢\006\004\b$\020%\032\037\020&\032\004\030\0010\"*\0020\0262\b\020#\032\004\030\0010\"H\002¢\006\004\b&\020%\032\033\020(\032\0020\031*\0020\0262\006\020'\032\0020\031H\002¢\006\004\b(\020)\032\023\020*\032\0020\036*\0020\026H\000¢\006\004\b*\020+\"\024\020,\032\0020\0078\002XT¢\006\006\n\004\b,\020-\"\024\020.\032\0020\0078\002XT¢\006\006\n\004\b.\020-\"\024\020/\032\0020\0078\002XT¢\006\006\n\004\b/\020-\"\024\0200\032\0020\0078\000XT¢\006\006\n\004\b0\020-\"\024\0201\032\0020\0078\000XT¢\006\006\n\004\b1\020-\"\024\0202\032\0020\0078\002XT¢\006\006\n\004\b2\020-\"\024\0203\032\0020\0078\002XT¢\006\006\n\004\b3\020-\"\024\0204\032\0020\0078\002XT¢\006\006\n\004\b4\020-\"\024\0205\032\0020\0078\002XT¢\006\006\n\004\b5\020-\"\024\0206\032\0020\n8\002XT¢\006\006\n\004\b6\0207\"\024\0208\032\0020\0078\002XT¢\006\006\n\004\b8\020-\"\024\0209\032\0020\0078\002XT¢\006\006\n\004\b9\020-\"\030\020=\032\0020:*\0020\0078BX\004¢\006\006\032\004\b;\020<¨\006>"}, d2 = {"", "Lokio/internal/ZipEntry;", "entries", "", "Lokio/Path;", "buildIndex", "(Ljava/util/List;)Ljava/util/Map;", "", "date", "time", "", "dosDateTimeToEpochMillis", "(II)Ljava/lang/Long;", "zipPath", "Lokio/FileSystem;", "fileSystem", "Lkotlin/Function1;", "", "predicate", "Lokio/ZipFileSystem;", "openZip", "(Lokio/Path;Lokio/FileSystem;Lkotlin/jvm/functions/Function1;)Lokio/ZipFileSystem;", "Lokio/BufferedSource;", "readEntry", "(Lokio/BufferedSource;)Lokio/internal/ZipEntry;", "Lokio/internal/EocdRecord;", "readEocdRecord", "(Lokio/BufferedSource;)Lokio/internal/EocdRecord;", "extraSize", "Lkotlin/Function2;", "", "block", "readExtra", "(Lokio/BufferedSource;ILkotlin/jvm/functions/Function2;)V", "Lokio/FileMetadata;", "basicMetadata", "readLocalHeader", "(Lokio/BufferedSource;Lokio/FileMetadata;)Lokio/FileMetadata;", "readOrSkipLocalHeader", "regularRecord", "readZip64EocdRecord", "(Lokio/BufferedSource;Lokio/internal/EocdRecord;)Lokio/internal/EocdRecord;", "skipLocalHeader", "(Lokio/BufferedSource;)V", "BIT_FLAG_ENCRYPTED", "I", "BIT_FLAG_UNSUPPORTED_MASK", "CENTRAL_FILE_HEADER_SIGNATURE", "COMPRESSION_METHOD_DEFLATED", "COMPRESSION_METHOD_STORED", "END_OF_CENTRAL_DIRECTORY_SIGNATURE", "HEADER_ID_EXTENDED_TIMESTAMP", "HEADER_ID_ZIP64_EXTENDED_INFO", "LOCAL_FILE_HEADER_SIGNATURE", "MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE", "J", "ZIP64_EOCD_RECORD_SIGNATURE", "ZIP64_LOCATOR_SIGNATURE", "", "getHex", "(I)Ljava/lang/String;", "hex", "okio"})
@SourceDebugExtension({"SMAP\nZipFiles.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ZipFiles.kt\nokio/internal/ZipFilesKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,459:1\n1045#2:460\n*S KotlinDebug\n*F\n+ 1 ZipFiles.kt\nokio/internal/ZipFilesKt\n*L\n156#1:460\n*E\n"})
public final class ZipFilesKt {
  private static final int LOCAL_FILE_HEADER_SIGNATURE = 67324752;
  
  private static final int CENTRAL_FILE_HEADER_SIGNATURE = 33639248;
  
  private static final int END_OF_CENTRAL_DIRECTORY_SIGNATURE = 101010256;
  
  private static final int ZIP64_LOCATOR_SIGNATURE = 117853008;
  
  private static final int ZIP64_EOCD_RECORD_SIGNATURE = 101075792;
  
  public static final int COMPRESSION_METHOD_DEFLATED = 8;
  
  public static final int COMPRESSION_METHOD_STORED = 0;
  
  private static final int BIT_FLAG_ENCRYPTED = 1;
  
  private static final int BIT_FLAG_UNSUPPORTED_MASK = 1;
  
  private static final long MAX_ZIP_ENTRY_AND_ARCHIVE_SIZE = 4294967295L;
  
  private static final int HEADER_ID_ZIP64_EXTENDED_INFO = 1;
  
  private static final int HEADER_ID_EXTENDED_TIMESTAMP = 21589;
  
  @Metadata(mv = {1, 9, 0}, k = 3, xi = 48, d1 = {"\000\016\n\002\030\002\n\000\n\002\020\013\n\002\b\003\020\005\032\0020\0022\006\020\001\032\0020\000H\n¢\006\004\b\003\020\004"}, d2 = {"Lokio/internal/ZipEntry;", "it", "", "invoke", "(Lokio/internal/ZipEntry;)Ljava/lang/Boolean;", "<anonymous>"})
  static final class ZipFilesKt$openZip$1 extends Lambda implements Function1<ZipEntry, Boolean> {
    public static final ZipFilesKt$openZip$1 INSTANCE = new ZipFilesKt$openZip$1();
    
    ZipFilesKt$openZip$1() {
      super(1);
    }
    
    @NotNull
    public final Boolean invoke(@NotNull ZipEntry it) {
      Intrinsics.checkNotNullParameter(it, "it");
      return Boolean.valueOf(true);
    }
  }
  
  @NotNull
  public static final ZipFileSystem openZip(@NotNull Path zipPath, @NotNull FileSystem fileSystem, @NotNull Function1 predicate) throws IOException {
    Intrinsics.checkNotNullParameter(zipPath, "zipPath");
    Intrinsics.checkNotNullParameter(fileSystem, "fileSystem");
    Intrinsics.checkNotNullParameter(predicate, "predicate");
    Closeable closeable = (Closeable)fileSystem.openReadOnly(zipPath);
    Throwable throwable = null;
    try {
      FileHandle fileHandle = (FileHandle)closeable;
      int $i$a$-use-ZipFilesKt$openZip$2 = 0;
      long scanOffset = fileHandle.size() - 22L;
      if (scanOffset < 0L)
        throw new IOException("not a zip: size=" + fileHandle.size()); 
      long stopOffset = Math.max(scanOffset - 65536L, 0L), eocdOffset = 0L;
      Object record = null;
      String comment = null;
      while (true) {
        BufferedSource source = Okio.buffer(fileHandle.source(scanOffset));
        try {
          if (source.readIntLe() == 101010256) {
            eocdOffset = scanOffset;
            record = readEocdRecord(source);
            comment = source.readUtf8(record.getCommentByteCount());
            source.close();
            break;
          } 
        } finally {
          source.close();
        } 
        scanOffset--;
        if (scanOffset < stopOffset)
          throw new IOException("not a zip: end of central directory signature not found"); 
      } 
      long zip64LocatorOffset = eocdOffset - 20L;
      if (zip64LocatorOffset > 0L) {
        Closeable closeable2 = (Closeable)Okio.buffer(fileHandle.source(zip64LocatorOffset));
        Throwable throwable2 = null;
        try {
          BufferedSource zip64LocatorSource = (BufferedSource)closeable2;
          int $i$a$-use-ZipFilesKt$openZip$2$1 = 0;
          if (zip64LocatorSource.readIntLe() == 117853008) {
            int diskWithCentralDir = zip64LocatorSource.readIntLe();
            long zip64EocdRecordOffset = zip64LocatorSource.readLongLe();
            int numDisks = zip64LocatorSource.readIntLe();
            if (numDisks != 1 || diskWithCentralDir != 0)
              throw new IOException("unsupported zip: spanned"); 
            Closeable closeable3 = (Closeable)Okio.buffer(fileHandle.source(zip64EocdRecordOffset));
            Throwable throwable3 = null;
            try {
              BufferedSource zip64EocdSource = (BufferedSource)closeable3;
              int $i$a$-use-ZipFilesKt$openZip$2$1$1 = 0;
              int zip64EocdSignature = zip64EocdSource.readIntLe();
              if (zip64EocdSignature != 101075792)
                throw new IOException(
                    "bad zip: expected " + getHex(101075792) + " but was " + 
                    getHex(zip64EocdSignature)); 
              record = readZip64EocdRecord(zip64EocdSource, (EocdRecord)record);
              Unit unit1 = Unit.INSTANCE;
            } catch (Throwable throwable4) {
              throwable3 = throwable4 = null;
              throw throwable4;
            } finally {
              CloseableKt.closeFinally(closeable3, throwable3);
            } 
          } 
          Unit unit = Unit.INSTANCE;
        } catch (Throwable throwable3) {
          throwable2 = throwable3 = null;
          throw throwable3;
        } finally {
          CloseableKt.closeFinally(closeable2, throwable2);
        } 
      } 
      List<ZipEntry> entries = new ArrayList();
      Closeable closeable1 = (Closeable)Okio.buffer(fileHandle.source(record.getCentralDirectoryOffset()));
      Throwable throwable1 = null;
      try {
        BufferedSource source = (BufferedSource)closeable1;
        int $i$a$-use-ZipFilesKt$openZip$2$2 = 0;
        long i, l1;
        for (i = 0L, l1 = record.getEntryCount(); i < l1; i++) {
          ZipEntry entry = readEntry(source);
          if (entry.getOffset() >= record.getCentralDirectoryOffset())
            throw new IOException("bad zip: local file header offset >= central directory offset"); 
          if (((Boolean)predicate.invoke(entry)).booleanValue())
            entries.add(entry); 
        } 
        Unit unit = Unit.INSTANCE;
      } catch (Throwable throwable2) {
        throwable1 = throwable2 = null;
        throw throwable2;
      } finally {
        CloseableKt.closeFinally(closeable1, throwable1);
      } 
      Map<Path, ZipEntry> index = buildIndex(entries);
      return new ZipFileSystem(zipPath, fileSystem, index, comment);
    } catch (Throwable throwable1) {
      throwable = throwable1 = null;
      throw throwable1;
    } finally {
      CloseableKt.closeFinally(closeable, throwable);
    } 
  }
  
  private static final Map<Path, ZipEntry> buildIndex(List entries) {
    Path root = Path.Companion.get$default(Path.Companion, "/", false, 1, null);
    Pair[] arrayOfPair = new Pair[1];
    arrayOfPair[0] = TuplesKt.to(root, new ZipEntry(root, true, null, 0L, 0L, 0L, 0, null, 0L, 508, null));
    Map<Path, ZipEntry> result = MapsKt.mutableMapOf(arrayOfPair);
    Iterable $this$sortedBy$iv = entries;
    int $i$f$sortedBy = 0;
    label22: for (ZipEntry entry : CollectionsKt.sortedWith($this$sortedBy$iv, new ZipFilesKt$buildIndex$$inlined$sortedBy$1())) {
      ZipEntry replaced = result.put(entry.getCanonicalPath(), entry);
      if (replaced == null) {
        ZipEntry child = entry;
        while (true) {
          Path parentPath;
          if (child.getCanonicalPath().parent() == null) {
            child.getCanonicalPath().parent();
            continue label22;
          } 
          ZipEntry parentEntry = result.get(parentPath);
          if (parentEntry != null) {
            parentEntry.getChildren().add(child.getCanonicalPath());
            continue label22;
          } 
          parentEntry = new ZipEntry(parentPath, true, null, 0L, 0L, 0L, 0, null, 0L, 508, null);
          result.put(parentPath, parentEntry);
          parentEntry.getChildren().add(child.getCanonicalPath());
          child = parentEntry;
        } 
      } 
    } 
    return result;
  }
  
  @NotNull
  public static final ZipEntry readEntry(@NotNull BufferedSource $this$readEntry) throws IOException {
    Intrinsics.checkNotNullParameter($this$readEntry, "<this>");
    int signature = $this$readEntry.readIntLe();
    if (signature != 33639248)
      throw new IOException("bad zip: expected " + getHex(33639248) + " but was " + getHex(signature)); 
    $this$readEntry.skip(4L);
    int bitFlag = $this$readEntry.readShortLe() & 0xFFFF;
    if ((bitFlag & 0x1) != 0)
      throw new IOException("unsupported zip: general purpose bit flag=" + getHex(bitFlag)); 
    int compressionMethod = $this$readEntry.readShortLe() & 0xFFFF;
    int time = $this$readEntry.readShortLe() & 0xFFFF;
    int date = $this$readEntry.readShortLe() & 0xFFFF;
    Long lastModifiedAtMillis = dosDateTimeToEpochMillis(date, time);
    long crc = $this$readEntry.readIntLe() & 0xFFFFFFFFL;
    Ref.LongRef compressedSize = new Ref.LongRef();
    compressedSize.element = $this$readEntry.readIntLe() & 0xFFFFFFFFL;
    Ref.LongRef size = new Ref.LongRef();
    size.element = $this$readEntry.readIntLe() & 0xFFFFFFFFL;
    int nameSize = $this$readEntry.readShortLe() & 0xFFFF;
    int extraSize = $this$readEntry.readShortLe() & 0xFFFF;
    int commentByteCount = $this$readEntry.readShortLe() & 0xFFFF;
    $this$readEntry.skip(8L);
    Ref.LongRef offset = new Ref.LongRef();
    offset.element = $this$readEntry.readIntLe() & 0xFFFFFFFFL;
    String name = $this$readEntry.readUtf8(nameSize);
    if (StringsKt.contains$default(name, false, false, 2, null))
      throw new IOException("bad zip: filename contains 0x00"); 
    BufferedSource $this$readEntry_u24lambda_u245 = $this$readEntry;
    int $i$a$-run-ZipFilesKt$readEntry$requiredZip64ExtraSize$1 = 0;
    long result = 0L;
    if (size.element == 4294967295L)
      result += 8L; 
    if (compressedSize.element == 4294967295L)
      result += 8L; 
    if (offset.element == 4294967295L)
      result += 8L; 
    long requiredZip64ExtraSize = result;
    Ref.BooleanRef hasZip64Extra = new Ref.BooleanRef();
    readExtra($this$readEntry, extraSize, new ZipFilesKt$readEntry$1(hasZip64Extra, requiredZip64ExtraSize, size, $this$readEntry, compressedSize, offset));
    if (requiredZip64ExtraSize > 0L && !hasZip64Extra.element)
      throw new IOException("bad zip: zip64 extra required but absent"); 
    String comment = $this$readEntry.readUtf8(commentByteCount);
    Path canonicalPath = Path.Companion.get$default(Path.Companion, "/", false, 1, null).resolve(name);
    boolean isDirectory = StringsKt.endsWith$default(name, "/", false, 2, null);
    return new ZipEntry(canonicalPath, isDirectory, comment, crc, compressedSize.element, size.element, compressionMethod, lastModifiedAtMillis, offset.element);
  }
  
  @Metadata(mv = {1, 9, 0}, k = 3, xi = 48, d1 = {"\000\024\n\002\020\b\n\000\n\002\020\t\n\000\n\002\020\002\n\002\b\003\020\007\032\0020\0042\006\020\001\032\0020\0002\006\020\003\032\0020\002H\n¢\006\004\b\005\020\006"}, d2 = {"", "headerId", "", "dataSize", "", "invoke", "(IJ)V", "<anonymous>"})
  static final class ZipFilesKt$readEntry$1 extends Lambda implements Function2<Integer, Long, Unit> {
    public final void invoke(int headerId, long dataSize) {
      if (headerId == 1) {
        if (this.$hasZip64Extra.element)
          throw new IOException("bad zip: zip64 extra repeated"); 
        this.$hasZip64Extra.element = true;
        if (dataSize < this.$requiredZip64ExtraSize)
          throw new IOException("bad zip: zip64 extra too short"); 
        this.$size.element = (this.$size.element == 4294967295L) ? this.$this_readEntry.readLongLe() : this.$size.element;
        this.$compressedSize.element = (this.$compressedSize.element == 4294967295L) ? this.$this_readEntry.readLongLe() : 0L;
        this.$offset.element = (this.$offset.element == 4294967295L) ? this.$this_readEntry.readLongLe() : 0L;
      } 
    }
    
    ZipFilesKt$readEntry$1(Ref.BooleanRef $hasZip64Extra, long $requiredZip64ExtraSize, Ref.LongRef $size, BufferedSource $receiver, Ref.LongRef $compressedSize, Ref.LongRef $offset) {
      super(2);
    }
  }
  
  private static final EocdRecord readEocdRecord(BufferedSource $this$readEocdRecord) throws IOException {
    int diskNumber = $this$readEocdRecord.readShortLe() & 0xFFFF;
    int diskWithCentralDir = $this$readEocdRecord.readShortLe() & 0xFFFF;
    long entryCount = ($this$readEocdRecord.readShortLe() & 0xFFFF);
    long totalEntryCount = ($this$readEocdRecord.readShortLe() & 0xFFFF);
    if (entryCount != totalEntryCount || diskNumber != 0 || diskWithCentralDir != 0)
      throw new IOException("unsupported zip: spanned"); 
    $this$readEocdRecord.skip(4L);
    long centralDirectoryOffset = $this$readEocdRecord.readIntLe() & 0xFFFFFFFFL;
    int commentByteCount = $this$readEocdRecord.readShortLe() & 0xFFFF;
    return new EocdRecord(entryCount, centralDirectoryOffset, commentByteCount);
  }
  
  private static final EocdRecord readZip64EocdRecord(BufferedSource $this$readZip64EocdRecord, EocdRecord regularRecord) throws IOException {
    $this$readZip64EocdRecord.skip(12L);
    int diskNumber = $this$readZip64EocdRecord.readIntLe();
    int diskWithCentralDirStart = $this$readZip64EocdRecord.readIntLe();
    long entryCount = $this$readZip64EocdRecord.readLongLe();
    long totalEntryCount = $this$readZip64EocdRecord.readLongLe();
    if (entryCount != totalEntryCount || diskNumber != 0 || diskWithCentralDirStart != 0)
      throw new IOException("unsupported zip: spanned"); 
    $this$readZip64EocdRecord.skip(8L);
    long centralDirectoryOffset = $this$readZip64EocdRecord.readLongLe();
    return new EocdRecord(entryCount, centralDirectoryOffset, regularRecord.getCommentByteCount());
  }
  
  private static final void readExtra(BufferedSource $this$readExtra, int extraSize, Function2 block) {
    long remaining = extraSize;
    while (remaining != 0L) {
      if (remaining < 4L)
        throw new IOException("bad zip: truncated header in extra field"); 
      int headerId = $this$readExtra.readShortLe() & 0xFFFF;
      long dataSize = $this$readExtra.readShortLe() & 0xFFFFL;
      remaining -= 4L;
      if (remaining < dataSize)
        throw new IOException("bad zip: truncated value in extra field"); 
      $this$readExtra.require(dataSize);
      long sizeBefore = $this$readExtra.getBuffer().size();
      block.invoke(Integer.valueOf(headerId), Long.valueOf(dataSize));
      long fieldRemaining = dataSize + $this$readExtra.getBuffer().size() - sizeBefore;
      if (fieldRemaining < 0L)
        throw new IOException("unsupported zip: too many bytes processed for " + headerId); 
      if (fieldRemaining > 0L)
        $this$readExtra.getBuffer().skip(fieldRemaining); 
      remaining -= dataSize;
    } 
  }
  
  public static final void skipLocalHeader(@NotNull BufferedSource $this$skipLocalHeader) {
    Intrinsics.checkNotNullParameter($this$skipLocalHeader, "<this>");
    readOrSkipLocalHeader($this$skipLocalHeader, null);
  }
  
  @NotNull
  public static final FileMetadata readLocalHeader(@NotNull BufferedSource $this$readLocalHeader, @NotNull FileMetadata basicMetadata) {
    Intrinsics.checkNotNullParameter($this$readLocalHeader, "<this>");
    Intrinsics.checkNotNullParameter(basicMetadata, "basicMetadata");
    Intrinsics.checkNotNull(readOrSkipLocalHeader($this$readLocalHeader, basicMetadata));
    return readOrSkipLocalHeader($this$readLocalHeader, basicMetadata);
  }
  
  private static final FileMetadata readOrSkipLocalHeader(BufferedSource $this$readOrSkipLocalHeader, FileMetadata basicMetadata) {
    Ref.ObjectRef<Long> lastModifiedAtMillis = new Ref.ObjectRef();
    lastModifiedAtMillis.element = (basicMetadata != null) ? basicMetadata.getLastModifiedAtMillis() : null;
    Ref.ObjectRef<Long> lastAccessedAtMillis = new Ref.ObjectRef();
    Ref.ObjectRef<Long> createdAtMillis = new Ref.ObjectRef();
    int signature = $this$readOrSkipLocalHeader.readIntLe();
    if (signature != 67324752)
      throw new IOException("bad zip: expected " + getHex(67324752) + " but was " + getHex(signature)); 
    $this$readOrSkipLocalHeader.skip(2L);
    int bitFlag = $this$readOrSkipLocalHeader.readShortLe() & 0xFFFF;
    if ((bitFlag & 0x1) != 0)
      throw new IOException("unsupported zip: general purpose bit flag=" + getHex(bitFlag)); 
    $this$readOrSkipLocalHeader.skip(18L);
    long fileNameLength = $this$readOrSkipLocalHeader.readShortLe() & 0xFFFFL;
    int extraSize = $this$readOrSkipLocalHeader.readShortLe() & 0xFFFF;
    $this$readOrSkipLocalHeader.skip(fileNameLength);
    if (basicMetadata == null) {
      $this$readOrSkipLocalHeader.skip(extraSize);
      return null;
    } 
    readExtra($this$readOrSkipLocalHeader, extraSize, new ZipFilesKt$readOrSkipLocalHeader$1($this$readOrSkipLocalHeader, lastModifiedAtMillis, lastAccessedAtMillis, createdAtMillis));
    return new FileMetadata(basicMetadata.isRegularFile(), basicMetadata.isDirectory(), null, basicMetadata.getSize(), (Long)createdAtMillis.element, (Long)lastModifiedAtMillis.element, (Long)lastAccessedAtMillis.element, null, 128, null);
  }
  
  @Metadata(mv = {1, 9, 0}, k = 3, xi = 48, d1 = {"\000\024\n\002\020\b\n\000\n\002\020\t\n\000\n\002\020\002\n\002\b\003\020\007\032\0020\0042\006\020\001\032\0020\0002\006\020\003\032\0020\002H\n¢\006\004\b\005\020\006"}, d2 = {"", "headerId", "", "dataSize", "", "invoke", "(IJ)V", "<anonymous>"})
  static final class ZipFilesKt$readOrSkipLocalHeader$1 extends Lambda implements Function2<Integer, Long, Unit> {
    public final void invoke(int headerId, long dataSize) {
      if (headerId == 21589) {
        if (dataSize < 1L)
          throw new IOException("bad zip: extended timestamp extra too short"); 
        int flags = this.$this_readOrSkipLocalHeader.readByte() & 0xFF;
        boolean hasLastModifiedAtMillis = ((flags & 0x1) == 1);
        boolean hasLastAccessedAtMillis = ((flags & 0x2) == 2);
        boolean hasCreatedAtMillis = ((flags & 0x4) == 4);
        BufferedSource $this$invoke_u24lambda_u240 = this.$this_readOrSkipLocalHeader;
        int $i$a$-run-ZipFilesKt$readOrSkipLocalHeader$1$requiredSize$1 = 0;
        long result = 1L;
        if (hasLastModifiedAtMillis)
          result += 4L; 
        if (hasLastAccessedAtMillis)
          result += 4L; 
        if (hasCreatedAtMillis)
          result += 4L; 
        long requiredSize = result;
        if (dataSize < requiredSize)
          throw new IOException("bad zip: extended timestamp extra too short"); 
        if (hasLastModifiedAtMillis)
          this.$lastModifiedAtMillis.element = Long.valueOf(this.$this_readOrSkipLocalHeader.readIntLe() * 1000L); 
        if (hasLastAccessedAtMillis)
          this.$lastAccessedAtMillis.element = Long.valueOf(this.$this_readOrSkipLocalHeader.readIntLe() * 1000L); 
        if (hasCreatedAtMillis)
          this.$createdAtMillis.element = Long.valueOf(this.$this_readOrSkipLocalHeader.readIntLe() * 1000L); 
      } 
    }
    
    ZipFilesKt$readOrSkipLocalHeader$1(BufferedSource $receiver, Ref.ObjectRef<Long> $lastModifiedAtMillis, Ref.ObjectRef<Long> $lastAccessedAtMillis, Ref.ObjectRef<Long> $createdAtMillis) {
      super(2);
    }
  }
  
  private static final Long dosDateTimeToEpochMillis(int date, int time) {
    if (time == -1)
      return null; 
    GregorianCalendar cal = new GregorianCalendar();
    cal.set(14, 0);
    int year = 1980 + (date >> 9 & 0x7F);
    int month = date >> 5 & 0xF;
    int day = date & 0x1F;
    int hour = time >> 11 & 0x1F;
    int minute = time >> 5 & 0x3F;
    int second = (time & 0x1F) << 1;
    cal.set(year, month - 1, day, hour, minute, second);
    return Long.valueOf(cal.getTime().getTime());
  }
  
  private static final String getHex(int $this$hex) {
    Intrinsics.checkNotNullExpressionValue(Integer.toString($this$hex, CharsKt.checkRadix(16)), "toString(this, checkRadix(radix))");
    return "0x" + Integer.toString($this$hex, CharsKt.checkRadix(16));
  }
}
