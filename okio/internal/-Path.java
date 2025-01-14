package okio.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okio.Buffer;
import okio.ByteString;
import okio.Path;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\000J\n\002\030\002\n\000\n\002\020\b\n\002\b\002\n\002\020\000\n\002\020\013\n\002\b\b\n\002\020\016\n\002\b\002\n\002\030\002\n\002\b\013\n\002\030\002\n\002\b\004\n\002\020 \n\002\b\006\n\002\020\f\n\002\b\t\n\002\020\005\n\002\b\023\032\034\020\003\032\0020\002*\0020\0002\006\020\001\032\0020\000H\b¢\006\004\b\003\020\004\032\036\020\007\032\0020\006*\0020\0002\b\020\001\032\004\030\0010\005H\b¢\006\004\b\007\020\b\032\024\020\t\032\0020\002*\0020\000H\b¢\006\004\b\t\020\n\032\024\020\013\032\0020\006*\0020\000H\b¢\006\004\b\013\020\f\032\024\020\r\032\0020\006*\0020\000H\b¢\006\004\b\r\020\f\032\024\020\016\032\0020\006*\0020\000H\b¢\006\004\b\016\020\f\032\024\020\020\032\0020\017*\0020\000H\b¢\006\004\b\020\020\021\032\024\020\023\032\0020\022*\0020\000H\b¢\006\004\b\023\020\024\032\024\020\025\032\0020\000*\0020\000H\b¢\006\004\b\025\020\026\032\026\020\027\032\004\030\0010\000*\0020\000H\b¢\006\004\b\027\020\026\032\034\020\030\032\0020\000*\0020\0002\006\020\001\032\0020\000H\b¢\006\004\b\030\020\031\032$\020\034\032\0020\000*\0020\0002\006\020\032\032\0020\0172\006\020\033\032\0020\006H\b¢\006\004\b\034\020\035\032$\020\034\032\0020\000*\0020\0002\006\020\032\032\0020\0362\006\020\033\032\0020\006H\b¢\006\004\b\034\020\037\032$\020\034\032\0020\000*\0020\0002\006\020\032\032\0020\0222\006\020\033\032\0020\006H\b¢\006\004\b\034\020 \032#\020\034\032\0020\000*\0020\0002\006\020\032\032\0020\0002\006\020\033\032\0020\006H\000¢\006\004\b\034\020!\032\026\020\"\032\004\030\0010\000*\0020\000H\b¢\006\004\b\"\020\026\032\032\020$\032\b\022\004\022\0020\0170#*\0020\000H\b¢\006\004\b$\020%\032\032\020&\032\b\022\004\022\0020\0220#*\0020\000H\b¢\006\004\b&\020%\032\033\020'\032\0020\000*\0020\0172\006\020\033\032\0020\006H\000¢\006\004\b'\020(\032\024\020)\032\0020\017*\0020\000H\b¢\006\004\b)\020\021\032\026\020+\032\004\030\0010**\0020\000H\b¢\006\004\b+\020,\032\023\020-\032\0020\006*\0020\000H\002¢\006\004\b-\020\f\032\023\020.\032\0020\002*\0020\000H\002¢\006\004\b.\020\n\032\033\0200\032\0020\006*\0020\0362\006\020/\032\0020\022H\002¢\006\004\b0\0201\032\033\0202\032\0020\000*\0020\0362\006\020\033\032\0020\006H\000¢\006\004\b2\0203\032\023\0205\032\0020\022*\00204H\002¢\006\004\b5\0206\032\023\0205\032\0020\022*\0020\017H\002¢\006\004\b5\0207\"\032\0208\032\0020\0228\002X\004¢\006\f\n\004\b8\0209\022\004\b:\020;\"\032\020<\032\0020\0228\002X\004¢\006\f\n\004\b<\0209\022\004\b=\020;\"\032\020>\032\0020\0228\002X\004¢\006\f\n\004\b>\0209\022\004\b?\020;\"\032\020@\032\0020\0228\002X\004¢\006\f\n\004\b@\0209\022\004\bA\020;\"\032\020B\032\0020\0228\002X\004¢\006\f\n\004\bB\0209\022\004\bC\020;\"\030\020E\032\0020\002*\0020\0008BX\004¢\006\006\032\004\bD\020\n\"\032\020/\032\004\030\0010\022*\0020\0008BX\004¢\006\006\032\004\bF\020\024¨\006G"}, d2 = {"Lokio/Path;", "other", "", "commonCompareTo", "(Lokio/Path;Lokio/Path;)I", "", "", "commonEquals", "(Lokio/Path;Ljava/lang/Object;)Z", "commonHashCode", "(Lokio/Path;)I", "commonIsAbsolute", "(Lokio/Path;)Z", "commonIsRelative", "commonIsRoot", "", "commonName", "(Lokio/Path;)Ljava/lang/String;", "Lokio/ByteString;", "commonNameBytes", "(Lokio/Path;)Lokio/ByteString;", "commonNormalized", "(Lokio/Path;)Lokio/Path;", "commonParent", "commonRelativeTo", "(Lokio/Path;Lokio/Path;)Lokio/Path;", "child", "normalize", "commonResolve", "(Lokio/Path;Ljava/lang/String;Z)Lokio/Path;", "Lokio/Buffer;", "(Lokio/Path;Lokio/Buffer;Z)Lokio/Path;", "(Lokio/Path;Lokio/ByteString;Z)Lokio/Path;", "(Lokio/Path;Lokio/Path;Z)Lokio/Path;", "commonRoot", "", "commonSegments", "(Lokio/Path;)Ljava/util/List;", "commonSegmentsBytes", "commonToPath", "(Ljava/lang/String;Z)Lokio/Path;", "commonToString", "", "commonVolumeLetter", "(Lokio/Path;)Ljava/lang/Character;", "lastSegmentIsDotDot", "rootLength", "slash", "startsWithVolumeLetterAndColon", "(Lokio/Buffer;Lokio/ByteString;)Z", "toPath", "(Lokio/Buffer;Z)Lokio/Path;", "", "toSlash", "(B)Lokio/ByteString;", "(Ljava/lang/String;)Lokio/ByteString;", "ANY_SLASH", "Lokio/ByteString;", "getANY_SLASH$annotations", "()V", "BACKSLASH", "getBACKSLASH$annotations", "DOT", "getDOT$annotations", "DOT_DOT", "getDOT_DOT$annotations", "SLASH", "getSLASH$annotations", "getIndexOfLastSlash", "indexOfLastSlash", "getSlash", "okio"})
@JvmName(name = "-Path")
@SourceDebugExtension({"SMAP\nPath.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Path.kt\nokio/internal/-Path\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,406:1\n59#1,22:407\n209#1:433\n209#1:434\n1549#2:429\n1620#2,3:430\n*S KotlinDebug\n*F\n+ 1 Path.kt\nokio/internal/-Path\n*L\n53#1:407,22\n199#1:433\n204#1:434\n53#1:429\n53#1:430,3\n*E\n"})
public final class -Path {
  @NotNull
  private static final ByteString SLASH = ByteString.Companion.encodeUtf8("/");
  
  @NotNull
  private static final ByteString BACKSLASH = ByteString.Companion.encodeUtf8("\\");
  
  @NotNull
  private static final ByteString ANY_SLASH = ByteString.Companion.encodeUtf8("/\\");
  
  @NotNull
  private static final ByteString DOT = ByteString.Companion.encodeUtf8(".");
  
  @NotNull
  private static final ByteString DOT_DOT = ByteString.Companion.encodeUtf8("..");
  
  @Nullable
  public static final Path commonRoot(@NotNull Path $this$commonRoot) {
    Intrinsics.checkNotNullParameter($this$commonRoot, "<this>");
    int $i$f$commonRoot = 0, rootLength = access$rootLength($this$commonRoot);
    return 
      (rootLength == -1) ? null : 
      new Path($this$commonRoot.getBytes$okio().substring(0, rootLength));
  }
  
  @NotNull
  public static final List<String> commonSegments(@NotNull Path $this$commonSegments) {
    Intrinsics.checkNotNullParameter($this$commonSegments, "<this>");
    int $i$f$commonSegments = 0;
    Path $this$commonSegmentsBytes$iv = $this$commonSegments;
    int $i$f$commonSegmentsBytes = 0;
    List<ByteString> result$iv = new ArrayList();
    int segmentStart$iv = access$rootLength($this$commonSegmentsBytes$iv);
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
    List<ByteString> list1 = result$iv;
    int $i$f$map = 0;
    result$iv = list1;
    Collection<String> destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault(list1, 10));
    int $i$f$mapTo = 0;
    for (ByteString item$iv$iv : result$iv) {
      ByteString byteString1 = item$iv$iv;
      Collection<String> collection = destination$iv$iv;
      int $i$a$-map--Path$commonSegments$1 = 0;
      collection.add(byteString1.utf8());
    } 
    return (List<String>)destination$iv$iv;
  }
  
  @NotNull
  public static final List<ByteString> commonSegmentsBytes(@NotNull Path $this$commonSegmentsBytes) {
    Intrinsics.checkNotNullParameter($this$commonSegmentsBytes, "<this>");
    int $i$f$commonSegmentsBytes = 0;
    List<ByteString> result = new ArrayList();
    int segmentStart = access$rootLength($this$commonSegmentsBytes);
    if (segmentStart == -1) {
      segmentStart = 0;
    } else if (segmentStart < $this$commonSegmentsBytes.getBytes$okio().size() && $this$commonSegmentsBytes.getBytes$okio().getByte(segmentStart) == 92) {
      segmentStart++;
    } 
    for (int i = segmentStart, j = $this$commonSegmentsBytes.getBytes$okio().size(); i < j; i++) {
      if ($this$commonSegmentsBytes.getBytes$okio().getByte(i) == 47 || $this$commonSegmentsBytes.getBytes$okio().getByte(i) == 92) {
        result.add($this$commonSegmentsBytes.getBytes$okio().substring(segmentStart, i));
        segmentStart = i + 1;
      } 
    } 
    if (segmentStart < $this$commonSegmentsBytes.getBytes$okio().size())
      result.add($this$commonSegmentsBytes.getBytes$okio().substring(segmentStart, $this$commonSegmentsBytes.getBytes$okio().size())); 
    return result;
  }
  
  private static final int rootLength(Path $this$rootLength) {
    if ($this$rootLength.getBytes$okio().size() == 0)
      return -1; 
    if ($this$rootLength.getBytes$okio().getByte(0) == 47)
      return 1; 
    if ($this$rootLength.getBytes$okio().getByte(0) == 92) {
      if ($this$rootLength.getBytes$okio().size() > 2 && $this$rootLength.getBytes$okio().getByte(1) == 92) {
        int uncRootEnd = $this$rootLength.getBytes$okio().indexOf(BACKSLASH, 2);
        if (uncRootEnd == -1)
          uncRootEnd = $this$rootLength.getBytes$okio().size(); 
        return uncRootEnd;
      } 
      return 1;
    } 
    if ($this$rootLength.getBytes$okio().size() > 2 && $this$rootLength.getBytes$okio().getByte(1) == 58 && $this$rootLength.getBytes$okio().getByte(2) == 92) {
      char c = (char)$this$rootLength.getBytes$okio().getByte(0);
      if (!(('a' <= c) ? ((c < '{') ? 1 : 0) : 0))
        if (!(('A' <= c) ? ((c < '[') ? 1 : 0) : 0))
          return -1;  
      return 3;
    } 
    return -1;
  }
  
  public static final boolean commonIsAbsolute(@NotNull Path $this$commonIsAbsolute) {
    Intrinsics.checkNotNullParameter($this$commonIsAbsolute, "<this>");
    int $i$f$commonIsAbsolute = 0;
    return (access$rootLength($this$commonIsAbsolute) != -1);
  }
  
  public static final boolean commonIsRelative(@NotNull Path $this$commonIsRelative) {
    Intrinsics.checkNotNullParameter($this$commonIsRelative, "<this>");
    int $i$f$commonIsRelative = 0;
    return (access$rootLength($this$commonIsRelative) == -1);
  }
  
  @Nullable
  public static final Character commonVolumeLetter(@NotNull Path $this$commonVolumeLetter) {
    Intrinsics.checkNotNullParameter($this$commonVolumeLetter, "<this>");
    int $i$f$commonVolumeLetter = 0;
    if (ByteString.indexOf$default($this$commonVolumeLetter.getBytes$okio(), access$getSLASH$p(), 0, 2, null) != -1)
      return null; 
    if ($this$commonVolumeLetter.getBytes$okio().size() < 2)
      return null; 
    if ($this$commonVolumeLetter.getBytes$okio().getByte(1) != 58)
      return null; 
    char c = (char)$this$commonVolumeLetter.getBytes$okio().getByte(0);
    if (!(('a' <= c) ? ((c < '{') ? 1 : 0) : 0))
      if (!(('A' <= c) ? ((c < '[') ? 1 : 0) : 0))
        return null;  
    return Character.valueOf(c);
  }
  
  private static final int getIndexOfLastSlash(Path $this$indexOfLastSlash) {
    int lastSlash = ByteString.lastIndexOf$default($this$indexOfLastSlash.getBytes$okio(), SLASH, 0, 2, null);
    if (lastSlash != -1)
      return lastSlash; 
    return ByteString.lastIndexOf$default($this$indexOfLastSlash.getBytes$okio(), BACKSLASH, 0, 2, null);
  }
  
  @NotNull
  public static final ByteString commonNameBytes(@NotNull Path $this$commonNameBytes) {
    Intrinsics.checkNotNullParameter($this$commonNameBytes, "<this>");
    int $i$f$commonNameBytes = 0, lastSlash = access$getIndexOfLastSlash($this$commonNameBytes);
    return (lastSlash != -1) ? ByteString.substring$default($this$commonNameBytes.getBytes$okio(), lastSlash + 1, 0, 2, null) : (($this$commonNameBytes.volumeLetter() != null && $this$commonNameBytes.getBytes$okio().size() == 2) ? ByteString.EMPTY : $this$commonNameBytes.getBytes$okio());
  }
  
  @NotNull
  public static final String commonName(@NotNull Path $this$commonName) {
    Intrinsics.checkNotNullParameter($this$commonName, "<this>");
    int $i$f$commonName = 0;
    return $this$commonName.nameBytes().utf8();
  }
  
  @Nullable
  public static final Path commonParent(@NotNull Path $this$commonParent) {
    Intrinsics.checkNotNullParameter($this$commonParent, "<this>");
    int $i$f$commonParent = 0;
    if (Intrinsics.areEqual($this$commonParent.getBytes$okio(), access$getDOT$p()) || Intrinsics.areEqual($this$commonParent.getBytes$okio(), access$getSLASH$p()) || Intrinsics.areEqual($this$commonParent.getBytes$okio(), access$getBACKSLASH$p()) || access$lastSegmentIsDotDot($this$commonParent))
      return null; 
    int lastSlash = access$getIndexOfLastSlash($this$commonParent);
    if (lastSlash == 2 && $this$commonParent.volumeLetter() != null) {
      if ($this$commonParent.getBytes$okio().size() == 3)
        return null; 
      return new Path(ByteString.substring$default($this$commonParent.getBytes$okio(), 0, 3, 1, null));
    } 
    if (lastSlash == 1 && $this$commonParent.getBytes$okio().startsWith(access$getBACKSLASH$p()))
      return null; 
    if (lastSlash == -1 && $this$commonParent.volumeLetter() != null) {
      if ($this$commonParent.getBytes$okio().size() == 2)
        return null; 
      return new Path(ByteString.substring$default($this$commonParent.getBytes$okio(), 0, 2, 1, null));
    } 
    if (lastSlash == -1)
      return new Path(access$getDOT$p()); 
    if (lastSlash == 0)
      return new Path(ByteString.substring$default($this$commonParent.getBytes$okio(), 0, 1, 1, null)); 
    return new Path(ByteString.substring$default($this$commonParent.getBytes$okio(), 0, lastSlash, 1, null));
  }
  
  private static final boolean lastSegmentIsDotDot(Path $this$lastSegmentIsDotDot) {
    if ($this$lastSegmentIsDotDot.getBytes$okio().endsWith(DOT_DOT)) {
      if ($this$lastSegmentIsDotDot.getBytes$okio().size() == 2)
        return true; 
      if ($this$lastSegmentIsDotDot.getBytes$okio().rangeEquals($this$lastSegmentIsDotDot.getBytes$okio().size() - 3, SLASH, 0, 1))
        return true; 
      if ($this$lastSegmentIsDotDot.getBytes$okio().rangeEquals($this$lastSegmentIsDotDot.getBytes$okio().size() - 3, BACKSLASH, 0, 1))
        return true; 
    } 
    return false;
  }
  
  public static final boolean commonIsRoot(@NotNull Path $this$commonIsRoot) {
    Intrinsics.checkNotNullParameter($this$commonIsRoot, "<this>");
    int $i$f$commonIsRoot = 0;
    return (access$rootLength($this$commonIsRoot) == $this$commonIsRoot.getBytes$okio().size());
  }
  
  @NotNull
  public static final Path commonResolve(@NotNull Path $this$commonResolve, @NotNull String child, boolean normalize) {
    Intrinsics.checkNotNullParameter($this$commonResolve, "<this>");
    Intrinsics.checkNotNullParameter(child, "child");
    int $i$f$commonResolve = 0;
    Path path = $this$commonResolve;
    Buffer child$iv = (new Buffer()).writeUtf8(child);
    int i = 0;
    return commonResolve(path, toPath(child$iv, false), normalize);
  }
  
  @NotNull
  public static final Path commonResolve(@NotNull Path $this$commonResolve, @NotNull ByteString child, boolean normalize) {
    Intrinsics.checkNotNullParameter($this$commonResolve, "<this>");
    Intrinsics.checkNotNullParameter(child, "child");
    int $i$f$commonResolve = 0;
    Path path = $this$commonResolve;
    Buffer child$iv = (new Buffer()).write(child);
    int i = 0;
    return commonResolve(path, toPath(child$iv, false), normalize);
  }
  
  @NotNull
  public static final Path commonResolve(@NotNull Path $this$commonResolve, @NotNull Buffer child, boolean normalize) {
    Intrinsics.checkNotNullParameter($this$commonResolve, "<this>");
    Intrinsics.checkNotNullParameter(child, "child");
    int $i$f$commonResolve = 0;
    return commonResolve($this$commonResolve, toPath(child, false), normalize);
  }
  
  @NotNull
  public static final Path commonResolve(@NotNull Path $this$commonResolve, @NotNull Path child, boolean normalize) {
    ByteString slash;
    Intrinsics.checkNotNullParameter($this$commonResolve, "<this>");
    Intrinsics.checkNotNullParameter(child, "child");
    if (child.isAbsolute() || child.volumeLetter() != null)
      return child; 
    if (getSlash($this$commonResolve) == null) {
      getSlash($this$commonResolve);
      if (getSlash(child) == null)
        getSlash(child); 
    } 
    Buffer buffer = new Buffer();
    buffer.write($this$commonResolve.getBytes$okio());
    if (buffer.size() > 0L)
      buffer.write(slash); 
    buffer.write(child.getBytes$okio());
    return toPath(buffer, normalize);
  }
  
  @NotNull
  public static final Path commonRelativeTo(@NotNull Path $this$commonRelativeTo, @NotNull Path other) {
    ByteString slash;
    Intrinsics.checkNotNullParameter($this$commonRelativeTo, "<this>");
    Intrinsics.checkNotNullParameter(other, "other");
    int $i$f$commonRelativeTo = 0;
    if (!Intrinsics.areEqual($this$commonRelativeTo.getRoot(), other.getRoot())) {
      int $i$a$-require--Path$commonRelativeTo$1 = 0;
      String str = "Paths of different roots cannot be relative to each other: " + $this$commonRelativeTo + " and " + other;
      throw new IllegalArgumentException(str.toString());
    } 
    List<ByteString> thisSegments = $this$commonRelativeTo.getSegmentsBytes();
    List otherSegments = other.getSegmentsBytes();
    int firstNewSegmentIndex = 0;
    int minSegmentsSize = Math.min(thisSegments.size(), otherSegments.size());
    while (firstNewSegmentIndex < minSegmentsSize && Intrinsics.areEqual(thisSegments.get(firstNewSegmentIndex), otherSegments.get(firstNewSegmentIndex)))
      firstNewSegmentIndex++; 
    if (firstNewSegmentIndex == minSegmentsSize && $this$commonRelativeTo.getBytes$okio().size() == other.getBytes$okio().size())
      return Path.Companion.get$default(Path.Companion, ".", false, 1, null); 
    if (!((otherSegments.subList(firstNewSegmentIndex, otherSegments.size()).indexOf(access$getDOT_DOT$p()) == -1) ? 1 : 0)) {
      int $i$a$-require--Path$commonRelativeTo$2 = 0;
      String str = "Impossible relative path to resolve: " + $this$commonRelativeTo + " and " + other;
      throw new IllegalArgumentException(str.toString());
    } 
    Buffer buffer = new Buffer();
    if (access$getSlash(other) == null) {
      access$getSlash(other);
      if (access$getSlash($this$commonRelativeTo) == null)
        access$getSlash($this$commonRelativeTo); 
    } 
    int i, j;
    for (i = firstNewSegmentIndex, j = otherSegments.size(); i < j; i++) {
      buffer.write(access$getDOT_DOT$p());
      buffer.write(slash);
    } 
    for (i = firstNewSegmentIndex, j = thisSegments.size(); i < j; i++) {
      buffer.write(thisSegments.get(i));
      buffer.write(slash);
    } 
    return toPath(buffer, false);
  }
  
  @NotNull
  public static final Path commonNormalized(@NotNull Path $this$commonNormalized) {
    Intrinsics.checkNotNullParameter($this$commonNormalized, "<this>");
    int $i$f$commonNormalized = 0;
    return Path.Companion.get($this$commonNormalized.toString(), true);
  }
  
  private static final ByteString getSlash(Path $this$slash) {
    return (ByteString.indexOf$default($this$slash.getBytes$okio(), SLASH, 0, 2, null) != -1) ? SLASH : ((ByteString.indexOf$default($this$slash.getBytes$okio(), BACKSLASH, 0, 2, null) != -1) ? BACKSLASH : null);
  }
  
  public static final int commonCompareTo(@NotNull Path $this$commonCompareTo, @NotNull Path other) {
    Intrinsics.checkNotNullParameter($this$commonCompareTo, "<this>");
    Intrinsics.checkNotNullParameter(other, "other");
    int $i$f$commonCompareTo = 0;
    return $this$commonCompareTo.getBytes$okio().compareTo(other.getBytes$okio());
  }
  
  public static final boolean commonEquals(@NotNull Path $this$commonEquals, @Nullable Object other) {
    Intrinsics.checkNotNullParameter($this$commonEquals, "<this>");
    int $i$f$commonEquals = 0;
    return (other instanceof Path && Intrinsics.areEqual(((Path)other).getBytes$okio(), $this$commonEquals.getBytes$okio()));
  }
  
  public static final int commonHashCode(@NotNull Path $this$commonHashCode) {
    Intrinsics.checkNotNullParameter($this$commonHashCode, "<this>");
    int $i$f$commonHashCode = 0;
    return $this$commonHashCode.getBytes$okio().hashCode();
  }
  
  @NotNull
  public static final String commonToString(@NotNull Path $this$commonToString) {
    Intrinsics.checkNotNullParameter($this$commonToString, "<this>");
    int $i$f$commonToString = 0;
    return $this$commonToString.getBytes$okio().utf8();
  }
  
  @NotNull
  public static final Path commonToPath(@NotNull String $this$commonToPath, boolean normalize) {
    Intrinsics.checkNotNullParameter($this$commonToPath, "<this>");
    return toPath((new Buffer()).writeUtf8($this$commonToPath), normalize);
  }
  
  @NotNull
  public static final Path toPath(@NotNull Buffer $this$toPath, boolean normalize) {
    Intrinsics.checkNotNullParameter($this$toPath, "<this>");
    ByteString slash = null;
    Buffer result = new Buffer();
    int leadingSlashCount = 0;
    while ($this$toPath.rangeEquals(0L, SLASH) || $this$toPath.rangeEquals(0L, BACKSLASH)) {
      byte byte = $this$toPath.readByte();
      if (slash == null);
      slash = toSlash(byte);
      leadingSlashCount++;
    } 
    boolean windowsUncPath = (leadingSlashCount >= 2 && Intrinsics.areEqual(slash, BACKSLASH));
    if (windowsUncPath) {
      Intrinsics.checkNotNull(slash);
      result.write(slash);
      result.write(slash);
    } else if (leadingSlashCount > 0) {
      Intrinsics.checkNotNull(slash);
      result.write(slash);
    } else {
      long limit = $this$toPath.indexOfElement(ANY_SLASH);
      if (slash == null);
      slash = (limit == -1L) ? toSlash(Path.DIRECTORY_SEPARATOR) : toSlash($this$toPath.getByte(limit));
      if (startsWithVolumeLetterAndColon($this$toPath, slash))
        if (limit == 2L) {
          result.write($this$toPath, 3L);
        } else {
          result.write($this$toPath, 2L);
        }  
    } 
    boolean absolute = (result.size() > 0L);
    List<ByteString> canonicalParts = new ArrayList();
    while (!$this$toPath.exhausted()) {
      long limit = $this$toPath.indexOfElement(ANY_SLASH);
      ByteString part = null;
      if (limit == -1L) {
        part = $this$toPath.readByteString();
      } else {
        part = $this$toPath.readByteString(limit);
        $this$toPath.readByte();
      } 
      if (Intrinsics.areEqual(part, DOT_DOT)) {
        if (!absolute || !canonicalParts.isEmpty()) {
          if (!normalize || (!absolute && (canonicalParts.isEmpty() || Intrinsics.areEqual(CollectionsKt.last(canonicalParts), DOT_DOT)))) {
            canonicalParts.add(part);
            continue;
          } 
          if (!windowsUncPath || canonicalParts.size() != 1)
            CollectionsKt.removeLastOrNull(canonicalParts); 
        } 
        continue;
      } 
      if (!Intrinsics.areEqual(part, DOT) && !Intrinsics.areEqual(part, ByteString.EMPTY))
        canonicalParts.add(part); 
    } 
    for (int i = 0, j = canonicalParts.size(); i < j; i++) {
      if (i > 0)
        result.write(slash); 
      result.write(canonicalParts.get(i));
    } 
    if (result.size() == 0L)
      result.write(DOT); 
    return new Path(result.readByteString());
  }
  
  private static final ByteString toSlash(String $this$toSlash) {
    String str = $this$toSlash;
    if (Intrinsics.areEqual(str, "\\")) {
    
    } else {
      throw new IllegalArgumentException("not a directory separator: " + $this$toSlash);
    } 
    return Intrinsics.areEqual(str, "/") ? SLASH : (ByteString)"JD-Core does not support Kotlin";
  }
  
  private static final ByteString toSlash(byte $this$toSlash) {
    switch ($this$toSlash) {
      case 47:
      
      case 92:
      
    } 
    throw new IllegalArgumentException("not a directory separator: " + $this$toSlash);
  }
  
  private static final boolean startsWithVolumeLetterAndColon(Buffer $this$startsWithVolumeLetterAndColon, ByteString slash) {
    if (!Intrinsics.areEqual(slash, BACKSLASH))
      return false; 
    if ($this$startsWithVolumeLetterAndColon.size() < 2L)
      return false; 
    if ($this$startsWithVolumeLetterAndColon.getByte(1L) != 58)
      return false; 
    char b = (char)$this$startsWithVolumeLetterAndColon.getByte(0L);
    if (!(('a' <= b) ? ((b < '{') ? 1 : 0) : 0)) {
      if (('A' <= b) ? ((b < '[')) : false);
      return false;
    } 
  }
}
