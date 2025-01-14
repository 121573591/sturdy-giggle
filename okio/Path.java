package okio;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okio.internal.-Path;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000L\n\002\030\002\n\002\020\017\n\002\030\002\n\002\b\004\n\002\020\b\n\002\b\002\n\002\020\016\n\002\b\006\n\002\020\000\n\002\020\013\n\002\b\013\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\020\n\002\020 \n\002\b\005\n\002\020\f\n\002\b\004\030\000 ;2\b\022\004\022\0020\0000\001:\001;B\021\b\000\022\006\020\003\032\0020\002¢\006\004\b\004\020\005J\030\020\b\032\0020\0072\006\020\006\032\0020\000H\002¢\006\004\b\b\020\tJ\030\020\016\032\0020\0002\006\020\013\032\0020\nH\002¢\006\004\b\f\020\rJ\030\020\016\032\0020\0002\006\020\013\032\0020\002H\002¢\006\004\b\f\020\017J\030\020\016\032\0020\0002\006\020\013\032\0020\000H\002¢\006\004\b\f\020\020J\032\020\023\032\0020\0222\b\020\006\032\004\030\0010\021H\002¢\006\004\b\023\020\024J\017\020\025\032\0020\007H\026¢\006\004\b\025\020\026J\r\020\027\032\0020\000¢\006\004\b\027\020\030J\025\020\031\032\0020\0002\006\020\006\032\0020\000¢\006\004\b\031\020\020J\037\020\f\032\0020\0002\006\020\013\032\0020\n2\b\b\002\020\032\032\0020\022¢\006\004\b\f\020\033J\037\020\f\032\0020\0002\006\020\013\032\0020\0022\b\b\002\020\032\032\0020\022¢\006\004\b\f\020\034J\037\020\f\032\0020\0002\006\020\013\032\0020\0002\b\b\002\020\032\032\0020\022¢\006\004\b\f\020\035J\r\020\037\032\0020\036¢\006\004\b\037\020 J\r\020\"\032\0020!¢\006\004\b\"\020#J\017\020$\032\0020\nH\026¢\006\004\b$\020%R\032\020\003\032\0020\0028\000X\004¢\006\f\n\004\b\003\020&\032\004\b'\020(R\021\020)\032\0020\0228F¢\006\006\032\004\b)\020*R\021\020+\032\0020\0228F¢\006\006\032\004\b+\020*R\021\020,\032\0020\0228F¢\006\006\032\004\b,\020*R\021\020-\032\0020\n8G¢\006\006\032\004\b-\020%R\021\020.\032\0020\0028G¢\006\006\032\004\b.\020(R\023\020/\032\004\030\0010\0008G¢\006\006\032\004\b/\020\030R\023\0201\032\004\030\0010\0008F¢\006\006\032\004\b0\020\030R\027\0205\032\b\022\004\022\0020\n028F¢\006\006\032\004\b3\0204R\027\0207\032\b\022\004\022\0020\002028F¢\006\006\032\004\b6\0204R\023\0209\032\004\030\001088G¢\006\006\032\004\b9\020:¨\006<"}, d2 = {"Lokio/Path;", "", "Lokio/ByteString;", "bytes", "<init>", "(Lokio/ByteString;)V", "other", "", "compareTo", "(Lokio/Path;)I", "", "child", "resolve", "(Ljava/lang/String;)Lokio/Path;", "div", "(Lokio/ByteString;)Lokio/Path;", "(Lokio/Path;)Lokio/Path;", "", "", "equals", "(Ljava/lang/Object;)Z", "hashCode", "()I", "normalized", "()Lokio/Path;", "relativeTo", "normalize", "(Ljava/lang/String;Z)Lokio/Path;", "(Lokio/ByteString;Z)Lokio/Path;", "(Lokio/Path;Z)Lokio/Path;", "Ljava/io/File;", "toFile", "()Ljava/io/File;", "Ljava/nio/file/Path;", "toNioPath", "()Ljava/nio/file/Path;", "toString", "()Ljava/lang/String;", "Lokio/ByteString;", "getBytes$okio", "()Lokio/ByteString;", "isAbsolute", "()Z", "isRelative", "isRoot", "name", "nameBytes", "parent", "getRoot", "root", "", "getSegments", "()Ljava/util/List;", "segments", "getSegmentsBytes", "segmentsBytes", "", "volumeLetter", "()Ljava/lang/Character;", "Companion", "okio"})
@SourceDebugExtension({"SMAP\nPath.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Path.kt\nokio/Path\n+ 2 Path.kt\nokio/internal/-Path\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,132:1\n45#2,3:133\n53#2,28:136\n59#2,22:168\n112#2:190\n117#2:191\n122#2,6:192\n139#2,5:198\n149#2:203\n154#2,25:204\n194#2:229\n199#2,11:230\n204#2,6:241\n199#2,11:247\n204#2,6:258\n228#2,36:264\n268#2:300\n282#2:301\n287#2:302\n292#2:303\n297#2:304\n1549#3:164\n1620#3,3:165\n*S KotlinDebug\n*F\n+ 1 Path.kt\nokio/Path\n*L\n44#1:133,3\n47#1:136,28\n50#1:168,22\n53#1:190\n56#1:191\n60#1:192,6\n64#1:198,5\n68#1:203\n72#1:204,25\n75#1:229\n78#1:230,11\n81#1:241,6\n87#1:247,11\n90#1:258,6\n95#1:264,36\n97#1:300\n104#1:301\n106#1:302\n108#1:303\n110#1:304\n47#1:164\n47#1:165,3\n*E\n"})
public final class Path implements Comparable<Path> {
  public Path(@NotNull ByteString bytes) {
    this.bytes = bytes;
  }
  
  @NotNull
  public final ByteString getBytes$okio() {
    return this.bytes;
  }
  
  @Nullable
  public final Path getRoot() {
    Path $this$commonRoot$iv = this;
    int $i$f$commonRoot = 0;
    int rootLength$iv = -Path.access$rootLength($this$commonRoot$iv);
    return (rootLength$iv == -1) ? null : 
      new Path($this$commonRoot$iv.getBytes$okio().substring(0, rootLength$iv));
  }
  
  @NotNull
  public final List<String> getSegments() {
    Path $this$commonSegments$iv = this;
    int $i$f$commonSegments = 0;
    Path $this$commonSegmentsBytes$iv$iv = $this$commonSegments$iv;
    int $i$f$commonSegmentsBytes = 0;
    List<ByteString> result$iv$iv = new ArrayList();
    int segmentStart$iv$iv = -Path.access$rootLength($this$commonSegmentsBytes$iv$iv);
    if (segmentStart$iv$iv == -1) {
      segmentStart$iv$iv = 0;
    } else if (segmentStart$iv$iv < $this$commonSegmentsBytes$iv$iv.getBytes$okio().size() && $this$commonSegmentsBytes$iv$iv.getBytes$okio().getByte(segmentStart$iv$iv) == 92) {
      segmentStart$iv$iv++;
    } 
    for (int i$iv$iv = segmentStart$iv$iv, i = $this$commonSegmentsBytes$iv$iv.getBytes$okio().size(); i$iv$iv < i; i$iv$iv++) {
      if ($this$commonSegmentsBytes$iv$iv.getBytes$okio().getByte(i$iv$iv) == 47 || $this$commonSegmentsBytes$iv$iv.getBytes$okio().getByte(i$iv$iv) == 92) {
        result$iv$iv.add($this$commonSegmentsBytes$iv$iv.getBytes$okio().substring(segmentStart$iv$iv, i$iv$iv));
        segmentStart$iv$iv = i$iv$iv + 1;
      } 
    } 
    if (segmentStart$iv$iv < $this$commonSegmentsBytes$iv$iv.getBytes$okio().size())
      result$iv$iv.add($this$commonSegmentsBytes$iv$iv.getBytes$okio().substring(segmentStart$iv$iv, $this$commonSegmentsBytes$iv$iv.getBytes$okio().size())); 
    List<ByteString> list1 = result$iv$iv;
    int $i$f$map = 0;
    result$iv$iv = list1;
    Collection<String> destination$iv$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault(list1, 10));
    int $i$f$mapTo = 0;
    for (ByteString item$iv$iv$iv : result$iv$iv) {
      ByteString byteString1 = item$iv$iv$iv;
      Collection<String> collection = destination$iv$iv$iv;
      int $i$a$-map--Path$commonSegments$1$iv = 0;
      collection.add(byteString1.utf8());
    } 
    return (List<String>)destination$iv$iv$iv;
  }
  
  @NotNull
  public final List<ByteString> getSegmentsBytes() {
    Path $this$commonSegmentsBytes$iv = this;
    int $i$f$commonSegmentsBytes = 0;
    List<ByteString> result$iv = new ArrayList();
    int segmentStart$iv = -Path.access$rootLength($this$commonSegmentsBytes$iv);
    if (segmentStart$iv == -1) {
      segmentStart$iv = 0;
    } else if (segmentStart$iv < $this$commonSegmentsBytes$iv.getBytes$okio().size() && $this$commonSegmentsBytes$iv.getBytes$okio().getByte(segmentStart$iv) == 92) {
      segmentStart$iv++;
    } 
    for (int i$iv = segmentStart$iv, i = $this$commonSegmentsBytes$iv.getBytes$okio().size(); i$iv < i; i$iv++) {
      if ($this$commonSegmentsBytes$iv.getBytes$okio().getByte(i$iv) == 47 || $this$commonSegmentsBytes$iv.getBytes$okio().getByte(i$iv) == 92) {
        result$iv.add($this$commonSegmentsBytes$iv.getBytes$okio().substring(segmentStart$iv, i$iv));
        segmentStart$iv = i$iv + 1;
      } 
    } 
    if (segmentStart$iv < $this$commonSegmentsBytes$iv.getBytes$okio().size())
      result$iv.add($this$commonSegmentsBytes$iv.getBytes$okio().substring(segmentStart$iv, $this$commonSegmentsBytes$iv.getBytes$okio().size())); 
    return result$iv;
  }
  
  public final boolean isAbsolute() {
    Path $this$commonIsAbsolute$iv = this;
    int $i$f$commonIsAbsolute = 0;
    return (-Path.access$rootLength($this$commonIsAbsolute$iv) != -1);
  }
  
  public final boolean isRelative() {
    Path $this$commonIsRelative$iv = this;
    int $i$f$commonIsRelative = 0;
    return (-Path.access$rootLength($this$commonIsRelative$iv) == -1);
  }
  
  @JvmName(name = "volumeLetter")
  @Nullable
  public final Character volumeLetter() {
    Path $this$commonVolumeLetter$iv = this;
    int $i$f$commonVolumeLetter = 0;
    char c$iv = (char)$this$commonVolumeLetter$iv.getBytes$okio().getByte(0);
    if (!(('a' <= c$iv) ? ((c$iv < '{') ? 1 : 0) : 0))
      if (!(('A' <= c$iv) ? ((c$iv < '[') ? 1 : 0) : 0)); 
    return (ByteString.indexOf$default($this$commonVolumeLetter$iv.getBytes$okio(), -Path.access$getSLASH$p(), 0, 2, (Object)null) != -1) ? null : (($this$commonVolumeLetter$iv.getBytes$okio().size() < 2) ? null : (($this$commonVolumeLetter$iv.getBytes$okio().getByte(1) != 58) ? null : Character.valueOf(c$iv)));
  }
  
  @JvmName(name = "nameBytes")
  @NotNull
  public final ByteString nameBytes() {
    Path $this$commonNameBytes$iv = this;
    int $i$f$commonNameBytes = 0;
    int lastSlash$iv = -Path.access$getIndexOfLastSlash($this$commonNameBytes$iv);
    return (lastSlash$iv != -1) ? ByteString.substring$default($this$commonNameBytes$iv.getBytes$okio(), lastSlash$iv + 1, 0, 2, null) : (
      ($this$commonNameBytes$iv.volumeLetter() != null && $this$commonNameBytes$iv.getBytes$okio().size() == 2) ? ByteString.EMPTY : 
      $this$commonNameBytes$iv.getBytes$okio());
  }
  
  @JvmName(name = "name")
  @NotNull
  public final String name() {
    Path $this$commonName$iv = this;
    int $i$f$commonName = 0;
    return $this$commonName$iv.nameBytes().utf8();
  }
  
  @JvmName(name = "parent")
  @Nullable
  public final Path parent() {
    Path $this$commonParent$iv = this;
    int $i$f$commonParent = 0;
    int lastSlash$iv = -Path.access$getIndexOfLastSlash($this$commonParent$iv);
    return (Intrinsics.areEqual($this$commonParent$iv.getBytes$okio(), -Path.access$getDOT$p()) || Intrinsics.areEqual($this$commonParent$iv.getBytes$okio(), -Path.access$getSLASH$p()) || Intrinsics.areEqual($this$commonParent$iv.getBytes$okio(), -Path.access$getBACKSLASH$p()) || -Path.access$lastSegmentIsDotDot($this$commonParent$iv)) ? null : ((
      lastSlash$iv == 2 && $this$commonParent$iv.volumeLetter() != null) ? (
      ($this$commonParent$iv.getBytes$okio().size() == 3) ? null : 
      new Path(ByteString.substring$default($this$commonParent$iv.getBytes$okio(), 0, 3, 1, null))) : (
      
      (lastSlash$iv == 1 && $this$commonParent$iv.getBytes$okio().startsWith(-Path.access$getBACKSLASH$p())) ? 
      null : (
      
      (lastSlash$iv == -1 && $this$commonParent$iv.volumeLetter() != null) ? (
      ($this$commonParent$iv.getBytes$okio().size() == 2) ? null : 
      new Path(ByteString.substring$default($this$commonParent$iv.getBytes$okio(), 0, 2, 1, null))) : (
      
      (lastSlash$iv == -1) ? 
      new Path(-Path.access$getDOT$p()) : (
      
      (lastSlash$iv == 0) ? 
      new Path(ByteString.substring$default($this$commonParent$iv.getBytes$okio(), 0, 1, 1, null)) : 
      
      new Path(ByteString.substring$default($this$commonParent$iv.getBytes$okio(), 0, lastSlash$iv, 1, null)))))));
  }
  
  public final boolean isRoot() {
    Path $this$commonIsRoot$iv = this;
    int $i$f$commonIsRoot = 0;
    return (-Path.access$rootLength($this$commonIsRoot$iv) == $this$commonIsRoot$iv.getBytes$okio().size());
  }
  
  @JvmName(name = "resolve")
  @NotNull
  public final Path resolve(@NotNull String child) {
    Intrinsics.checkNotNullParameter(child, "child");
    Path path1 = this;
    boolean normalize$iv = false;
    int $i$f$commonResolve = 0;
    Path path2 = path1;
    Buffer child$iv$iv = (new Buffer()).writeUtf8(child);
    int i = 0;
    return -Path.commonResolve(path2, -Path.toPath(child$iv$iv, false), normalize$iv);
  }
  
  @JvmName(name = "resolve")
  @NotNull
  public final Path resolve(@NotNull ByteString child) {
    Intrinsics.checkNotNullParameter(child, "child");
    Path path1 = this;
    boolean normalize$iv = false;
    int $i$f$commonResolve = 0;
    Path path2 = path1;
    Buffer child$iv$iv = (new Buffer()).write(child);
    int i = 0;
    return -Path.commonResolve(path2, -Path.toPath(child$iv$iv, false), normalize$iv);
  }
  
  @JvmName(name = "resolve")
  @NotNull
  public final Path resolve(@NotNull Path child) {
    Intrinsics.checkNotNullParameter(child, "child");
    return -Path.commonResolve(this, child, false);
  }
  
  @NotNull
  public final Path resolve(@NotNull String child, boolean normalize) {
    Intrinsics.checkNotNullParameter(child, "child");
    Path $this$commonResolve$iv = this;
    int $i$f$commonResolve = 0;
    Path path1 = $this$commonResolve$iv;
    Buffer child$iv$iv = (new Buffer()).writeUtf8(child);
    int i = 0;
    return -Path.commonResolve(path1, -Path.toPath(child$iv$iv, false), normalize);
  }
  
  @NotNull
  public final Path resolve(@NotNull ByteString child, boolean normalize) {
    Intrinsics.checkNotNullParameter(child, "child");
    Path $this$commonResolve$iv = this;
    int $i$f$commonResolve = 0;
    Path path1 = $this$commonResolve$iv;
    Buffer child$iv$iv = (new Buffer()).write(child);
    int i = 0;
    return -Path.commonResolve(path1, -Path.toPath(child$iv$iv, false), normalize);
  }
  
  @NotNull
  public final Path resolve(@NotNull Path child, boolean normalize) {
    Intrinsics.checkNotNullParameter(child, "child");
    return -Path.commonResolve(this, child, normalize);
  }
  
  @NotNull
  public final Path relativeTo(@NotNull Path other) {
    ByteString slash$iv;
    Intrinsics.checkNotNullParameter(other, "other");
    Path $this$commonRelativeTo$iv = this;
    int $i$f$commonRelativeTo = 0;
    if (!Intrinsics.areEqual($this$commonRelativeTo$iv.getRoot(), other.getRoot())) {
      int $i$a$-require--Path$commonRelativeTo$1$iv = 0;
      String str = 
        "Paths of different roots cannot be relative to each other: " + $this$commonRelativeTo$iv + " and " + other;
      throw new IllegalArgumentException(str.toString());
    } 
    List<ByteString> thisSegments$iv = $this$commonRelativeTo$iv.getSegmentsBytes();
    List<ByteString> otherSegments$iv = other.getSegmentsBytes();
    int firstNewSegmentIndex$iv = 0;
    int minSegmentsSize$iv = Math.min(thisSegments$iv.size(), otherSegments$iv.size());
    while (firstNewSegmentIndex$iv < minSegmentsSize$iv && 
      Intrinsics.areEqual(thisSegments$iv.get(firstNewSegmentIndex$iv), otherSegments$iv.get(firstNewSegmentIndex$iv)))
      firstNewSegmentIndex$iv++; 
    if (!((otherSegments$iv.subList(firstNewSegmentIndex$iv, otherSegments$iv.size()).indexOf(-Path.access$getDOT_DOT$p()) == -1) ? 1 : 0)) {
      int $i$a$-require--Path$commonRelativeTo$2$iv = 0;
      String str = 
        "Impossible relative path to resolve: " + $this$commonRelativeTo$iv + " and " + other;
      throw new IllegalArgumentException(str.toString());
    } 
    Buffer buffer$iv = new Buffer();
    if (-Path.access$getSlash(other) == null) {
      -Path.access$getSlash(other);
      if (-Path.access$getSlash($this$commonRelativeTo$iv) == null)
        -Path.access$getSlash($this$commonRelativeTo$iv); 
    } 
    int i$iv, i;
    for (i$iv = firstNewSegmentIndex$iv, i = otherSegments$iv.size(); i$iv < i; i$iv++) {
      buffer$iv.write(-Path.access$getDOT_DOT$p());
      buffer$iv.write(slash$iv);
    } 
    for (i$iv = firstNewSegmentIndex$iv, i = thisSegments$iv.size(); i$iv < i; i$iv++) {
      buffer$iv.write(thisSegments$iv.get(i$iv));
      buffer$iv.write(slash$iv);
    } 
    return (firstNewSegmentIndex$iv == minSegmentsSize$iv && $this$commonRelativeTo$iv.getBytes$okio().size() == other.getBytes$okio().size()) ? Companion.get$default(Companion, ".", false, 1, (Object)null) : -Path.toPath(buffer$iv, false);
  }
  
  @NotNull
  public final Path normalized() {
    Path $this$commonNormalized$iv = this;
    int $i$f$commonNormalized = 0;
    return Companion.get($this$commonNormalized$iv.toString(), true);
  }
  
  @NotNull
  public final File toFile() {
    return new File(toString());
  }
  
  @NotNull
  public final java.nio.file.Path toNioPath() {
    Intrinsics.checkNotNullExpressionValue(Paths.get(toString(), new String[0]), "get(...)");
    return Paths.get(toString(), new String[0]);
  }
  
  public int compareTo(@NotNull Path other) {
    Intrinsics.checkNotNullParameter(other, "other");
    Path $this$commonCompareTo$iv = this;
    int $i$f$commonCompareTo = 0;
    return $this$commonCompareTo$iv.getBytes$okio().compareTo(other.getBytes$okio());
  }
  
  public boolean equals(@Nullable Object other) {
    Path $this$commonEquals$iv = this;
    int $i$f$commonEquals = 0;
    return (other instanceof Path && Intrinsics.areEqual(((Path)other).getBytes$okio(), $this$commonEquals$iv.getBytes$okio()));
  }
  
  public int hashCode() {
    Path $this$commonHashCode$iv = this;
    int $i$f$commonHashCode = 0;
    return $this$commonHashCode$iv.getBytes$okio().hashCode();
  }
  
  @NotNull
  public String toString() {
    Path $this$commonToString$iv = this;
    int $i$f$commonToString = 0;
    return $this$commonToString$iv.getBytes$okio().utf8();
  }
  
  @Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000,\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\020\013\n\000\n\002\030\002\n\002\b\003\n\002\030\002\n\000\n\002\020\016\n\002\b\005\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J\035\020\n\032\0020\007*\0020\0042\b\b\002\020\006\032\0020\005H\007¢\006\004\b\b\020\tJ\035\020\n\032\0020\007*\0020\0132\b\b\002\020\006\032\0020\005H\007¢\006\004\b\b\020\fJ\035\020\017\032\0020\007*\0020\r2\b\b\002\020\006\032\0020\005H\007¢\006\004\b\b\020\016R\024\020\020\032\0020\r8\006X\004¢\006\006\n\004\b\020\020\021¨\006\022"}, d2 = {"Lokio/Path$Companion;", "", "<init>", "()V", "Ljava/io/File;", "", "normalize", "Lokio/Path;", "get", "(Ljava/io/File;Z)Lokio/Path;", "toOkioPath", "Ljava/nio/file/Path;", "(Ljava/nio/file/Path;Z)Lokio/Path;", "", "(Ljava/lang/String;Z)Lokio/Path;", "toPath", "DIRECTORY_SEPARATOR", "Ljava/lang/String;", "okio"})
  public static final class Companion {
    private Companion() {}
    
    @JvmStatic
    @JvmName(name = "get")
    @JvmOverloads
    @NotNull
    public final Path get(@NotNull String $this$toPath, boolean normalize) {
      Intrinsics.checkNotNullParameter($this$toPath, "<this>");
      return -Path.commonToPath($this$toPath, normalize);
    }
    
    @JvmStatic
    @JvmName(name = "get")
    @JvmOverloads
    @NotNull
    public final Path get(@NotNull File $this$toOkioPath, boolean normalize) {
      Intrinsics.checkNotNullParameter($this$toOkioPath, "<this>");
      Intrinsics.checkNotNullExpressionValue($this$toOkioPath.toString(), "toString(...)");
      return get($this$toOkioPath.toString(), normalize);
    }
    
    @JvmStatic
    @JvmName(name = "get")
    @JvmOverloads
    @NotNull
    public final Path get(@NotNull java.nio.file.Path $this$toOkioPath, boolean normalize) {
      Intrinsics.checkNotNullParameter($this$toOkioPath, "<this>");
      return get($this$toOkioPath.toString(), normalize);
    }
    
    @JvmStatic
    @JvmName(name = "get")
    @JvmOverloads
    @NotNull
    public final Path get(@NotNull String $this$toPath) {
      Intrinsics.checkNotNullParameter($this$toPath, "<this>");
      return get$default(this, $this$toPath, false, 1, (Object)null);
    }
    
    @JvmStatic
    @JvmName(name = "get")
    @JvmOverloads
    @NotNull
    public final Path get(@NotNull File $this$toOkioPath) {
      Intrinsics.checkNotNullParameter($this$toOkioPath, "<this>");
      return get$default(this, $this$toOkioPath, false, 1, (Object)null);
    }
    
    @JvmStatic
    @JvmName(name = "get")
    @JvmOverloads
    @NotNull
    public final Path get(@NotNull java.nio.file.Path $this$toOkioPath) {
      Intrinsics.checkNotNullParameter($this$toOkioPath, "<this>");
      return get$default(this, $this$toOkioPath, false, 1, (Object)null);
    }
  }
  
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  @NotNull
  private final ByteString bytes;
  
  @JvmField
  @NotNull
  public static final String DIRECTORY_SEPARATOR = File.separator;
  
  static {
    Intrinsics.checkNotNullExpressionValue(File.separator, "separator");
  }
  
  @JvmStatic
  @JvmName(name = "get")
  @JvmOverloads
  @NotNull
  public static final Path get(@NotNull String $this$get, boolean normalize) {
    return Companion.get($this$get, normalize);
  }
  
  @JvmStatic
  @JvmName(name = "get")
  @JvmOverloads
  @NotNull
  public static final Path get(@NotNull File $this$get, boolean normalize) {
    return Companion.get($this$get, normalize);
  }
  
  @JvmStatic
  @JvmName(name = "get")
  @JvmOverloads
  @NotNull
  public static final Path get(@NotNull java.nio.file.Path $this$get, boolean normalize) {
    return Companion.get($this$get, normalize);
  }
  
  @JvmStatic
  @JvmName(name = "get")
  @JvmOverloads
  @NotNull
  public static final Path get(@NotNull String $this$get) {
    return Companion.get($this$get);
  }
  
  @JvmStatic
  @JvmName(name = "get")
  @JvmOverloads
  @NotNull
  public static final Path get(@NotNull File $this$get) {
    return Companion.get($this$get);
  }
  
  @JvmStatic
  @JvmName(name = "get")
  @JvmOverloads
  @NotNull
  public static final Path get(@NotNull java.nio.file.Path $this$get) {
    return Companion.get($this$get);
  }
}
