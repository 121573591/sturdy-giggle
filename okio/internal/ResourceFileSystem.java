package okio.internal;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okio.FileHandle;
import okio.FileMetadata;
import okio.FileSystem;
import okio.Okio;
import okio.Path;
import okio.Sink;
import okio.Source;
import okio.ZipFileSystem;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000d\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\020\013\n\002\b\004\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\004\n\002\020\002\n\002\b\f\n\002\020 \n\002\b\003\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\005\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\003\n\002\020\016\n\002\b\013\b\000\030\000 >2\0020\001:\001>B#\b\000\022\006\020\003\032\0020\002\022\006\020\005\032\0020\004\022\b\b\002\020\006\032\0020\001¢\006\004\b\007\020\bJ\037\020\r\032\0020\f2\006\020\n\032\0020\t2\006\020\013\032\0020\004H\026¢\006\004\b\r\020\016J\037\020\022\032\0020\0212\006\020\017\032\0020\t2\006\020\020\032\0020\tH\026¢\006\004\b\022\020\023J\027\020\025\032\0020\t2\006\020\024\032\0020\tH\026¢\006\004\b\025\020\026J\027\020\027\032\0020\t2\006\020\024\032\0020\tH\002¢\006\004\b\027\020\026J\037\020\032\032\0020\0212\006\020\030\032\0020\t2\006\020\031\032\0020\004H\026¢\006\004\b\032\020\033J\037\020\034\032\0020\0212\006\020\017\032\0020\t2\006\020\020\032\0020\tH\026¢\006\004\b\034\020\023J\037\020\035\032\0020\0212\006\020\024\032\0020\t2\006\020\013\032\0020\004H\026¢\006\004\b\035\020\033J\035\020\037\032\b\022\004\022\0020\t0\0362\006\020\030\032\0020\tH\026¢\006\004\b\037\020 J\037\020!\032\n\022\004\022\0020\t\030\0010\0362\006\020\030\032\0020\tH\026¢\006\004\b!\020 J\031\020#\032\004\030\0010\"2\006\020\024\032\0020\tH\026¢\006\004\b#\020$J\027\020&\032\0020%2\006\020\n\032\0020\tH\026¢\006\004\b&\020'J'\020(\032\0020%2\006\020\n\032\0020\t2\006\020\031\032\0020\0042\006\020\013\032\0020\004H\026¢\006\004\b(\020)J\037\020*\032\0020\f2\006\020\n\032\0020\t2\006\020\031\032\0020\004H\026¢\006\004\b*\020\016J\027\020\017\032\0020+2\006\020\n\032\0020\tH\026¢\006\004\b\017\020,J%\020.\032\024\022\020\022\016\022\004\022\0020\001\022\004\022\0020\t0-0\036*\0020\002H\002¢\006\004\b.\020/J!\0201\032\020\022\004\022\0020\001\022\004\022\0020\t\030\0010-*\00200H\002¢\006\004\b1\0202J!\0203\032\020\022\004\022\0020\001\022\004\022\0020\t\030\0010-*\00200H\002¢\006\004\b3\0202J\023\0205\032\00204*\0020\tH\002¢\006\004\b5\0206R\024\020\003\032\0020\0028\002X\004¢\006\006\n\004\b\003\0207R-\020<\032\024\022\020\022\016\022\004\022\0020\001\022\004\022\0020\t0-0\0368BX\002¢\006\f\n\004\b8\0209\032\004\b:\020;R\024\020\006\032\0020\0018\002X\004¢\006\006\n\004\b\006\020=¨\006?"}, d2 = {"Lokio/internal/ResourceFileSystem;", "Lokio/FileSystem;", "Ljava/lang/ClassLoader;", "classLoader", "", "indexEagerly", "systemFileSystem", "<init>", "(Ljava/lang/ClassLoader;ZLokio/FileSystem;)V", "Lokio/Path;", "file", "mustExist", "Lokio/Sink;", "appendingSink", "(Lokio/Path;Z)Lokio/Sink;", "source", "target", "", "atomicMove", "(Lokio/Path;Lokio/Path;)V", "path", "canonicalize", "(Lokio/Path;)Lokio/Path;", "canonicalizeInternal", "dir", "mustCreate", "createDirectory", "(Lokio/Path;Z)V", "createSymlink", "delete", "", "list", "(Lokio/Path;)Ljava/util/List;", "listOrNull", "Lokio/FileMetadata;", "metadataOrNull", "(Lokio/Path;)Lokio/FileMetadata;", "Lokio/FileHandle;", "openReadOnly", "(Lokio/Path;)Lokio/FileHandle;", "openReadWrite", "(Lokio/Path;ZZ)Lokio/FileHandle;", "sink", "Lokio/Source;", "(Lokio/Path;)Lokio/Source;", "Lkotlin/Pair;", "toClasspathRoots", "(Ljava/lang/ClassLoader;)Ljava/util/List;", "Ljava/net/URL;", "toFileRoot", "(Ljava/net/URL;)Lkotlin/Pair;", "toJarRoot", "", "toRelativePath", "(Lokio/Path;)Ljava/lang/String;", "Ljava/lang/ClassLoader;", "roots$delegate", "Lkotlin/Lazy;", "getRoots", "()Ljava/util/List;", "roots", "Lokio/FileSystem;", "Companion", "okio"})
@SourceDebugExtension({"SMAP\nResourceFileSystem.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ResourceFileSystem.kt\nokio/internal/ResourceFileSystem\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,210:1\n766#2:211\n857#2,2:212\n1549#2:214\n1620#2,3:215\n766#2:218\n857#2,2:219\n1549#2:221\n1620#2,3:222\n1603#2,9:225\n1855#2:234\n1856#2:236\n1612#2:237\n1603#2,9:238\n1855#2:247\n1856#2:249\n1612#2:250\n1#3:235\n1#3:248\n*S KotlinDebug\n*F\n+ 1 ResourceFileSystem.kt\nokio/internal/ResourceFileSystem\n*L\n74#1:211\n74#1:212,2\n75#1:214\n75#1:215,3\n90#1:218\n90#1:219,2\n91#1:221\n91#1:222,3\n173#1:225,9\n173#1:234\n173#1:236\n173#1:237\n174#1:238,9\n174#1:247\n174#1:249\n174#1:250\n173#1:235\n174#1:248\n*E\n"})
public final class ResourceFileSystem extends FileSystem {
  public ResourceFileSystem(@NotNull ClassLoader classLoader, boolean indexEagerly, @NotNull FileSystem systemFileSystem) {
    this.classLoader = classLoader;
    this.systemFileSystem = systemFileSystem;
    this.roots$delegate = LazyKt.lazy(new ResourceFileSystem$roots$2());
    if (indexEagerly)
      getRoots().size(); 
  }
  
  private final List<Pair<FileSystem, Path>> getRoots() {
    Lazy lazy = this.roots$delegate;
    return (List<Pair<FileSystem, Path>>)lazy.getValue();
  }
  
  @Metadata(mv = {1, 9, 0}, k = 3, xi = 48, d1 = {"\000\024\n\002\020 \n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\003\020\006\032\024\022\020\022\016\022\004\022\0020\002\022\004\022\0020\0030\0010\000H\n¢\006\004\b\004\020\005"}, d2 = {"", "Lkotlin/Pair;", "Lokio/FileSystem;", "Lokio/Path;", "invoke", "()Ljava/util/List;", "<anonymous>"})
  static final class ResourceFileSystem$roots$2 extends Lambda implements Function0<List<? extends Pair<? extends FileSystem, ? extends Path>>> {
    ResourceFileSystem$roots$2() {
      super(0);
    }
    
    @NotNull
    public final List<Pair<FileSystem, Path>> invoke() {
      return ResourceFileSystem.this.toClasspathRoots(ResourceFileSystem.this.classLoader);
    }
  }
  
  @NotNull
  public Path canonicalize(@NotNull Path path) {
    Intrinsics.checkNotNullParameter(path, "path");
    return canonicalizeInternal(path);
  }
  
  private final Path canonicalizeInternal(Path path) {
    return ROOT.resolve(path, true);
  }
  
  @NotNull
  public List<Path> list(@NotNull Path dir) {
    Intrinsics.checkNotNullParameter(dir, "dir");
    String relativePath = toRelativePath(dir);
    Set result = new LinkedHashSet();
    boolean foundAny = false;
    for (Pair<FileSystem, Path> pair : getRoots()) {
      FileSystem fileSystem = (FileSystem)pair.component1();
      Path base = (Path)pair.component2();
      try {
        Set set = result;
        List list1 = fileSystem.list(base.resolve(relativePath));
        int $i$f$filter = 0;
        List list2 = list1;
        Collection<Object> collection = new ArrayList();
        int $i$f$filterTo = 0;
        for (Object element$iv$iv : list2) {
          Path it = (Path)element$iv$iv;
          int $i$a$-filter-ResourceFileSystem$list$1 = 0;
          if (Companion.keepPath(it))
            collection.add(element$iv$iv); 
        } 
        Iterable $this$filter$iv = collection;
        int $i$f$map = 0;
        Iterable $this$filterTo$iv$iv = $this$filter$iv;
        Collection<Path> destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$filter$iv, 10));
        int $i$f$mapTo = 0;
        for (Object item$iv$iv : $this$filterTo$iv$iv) {
          Path path = (Path)item$iv$iv;
          Collection<Path> collection1 = destination$iv$iv;
          int $i$a$-map-ResourceFileSystem$list$2 = 0;
          collection1.add(Companion.removeBase(path, base));
        } 
        Iterable $this$map$iv = destination$iv$iv;
        CollectionsKt.addAll(set, $this$map$iv);
        foundAny = true;
      } catch (IOException iOException) {}
    } 
    if (!foundAny)
      throw new FileNotFoundException("file not found: " + dir); 
    return CollectionsKt.toList(result);
  }
  
  @Nullable
  public List<Path> listOrNull(@NotNull Path dir) {
    Intrinsics.checkNotNullParameter(dir, "dir");
    String relativePath = toRelativePath(dir);
    Set result = new LinkedHashSet();
    boolean foundAny = false;
    for (Pair<FileSystem, Path> pair : getRoots()) {
      FileSystem fileSystem = (FileSystem)pair.component1();
      Path base = (Path)pair.component2();
      List list1 = fileSystem.listOrNull(base.resolve(relativePath)), list2 = list1;
      int $i$f$filter = 0;
      List list3 = list2;
      Collection<Object> collection = new ArrayList();
      int $i$f$filterTo = 0;
      for (Object element$iv$iv : list3) {
        Path it = (Path)element$iv$iv;
        int $i$a$-filter-ResourceFileSystem$listOrNull$baseResult$1 = 0;
        if (Companion.keepPath(it))
          collection.add(element$iv$iv); 
      } 
      Iterable $this$filter$iv = collection;
      int $i$f$map = 0;
      Iterable $this$filterTo$iv$iv = $this$filter$iv;
      Collection<Path> destination$iv$iv = new ArrayList(CollectionsKt.collectionSizeOrDefault($this$filter$iv, 10));
      int $i$f$mapTo = 0;
      for (Object item$iv$iv : $this$filterTo$iv$iv) {
        Path path = (Path)item$iv$iv;
        Collection<Path> collection1 = destination$iv$iv;
        int $i$a$-map-ResourceFileSystem$listOrNull$baseResult$2 = 0;
        collection1.add(Companion.removeBase(path, base));
      } 
      List baseResult = (list1 != null) ? (List)destination$iv$iv : null;
      if (baseResult != null) {
        CollectionsKt.addAll(result, baseResult);
        foundAny = true;
      } 
    } 
    return foundAny ? CollectionsKt.toList(result) : null;
  }
  
  @NotNull
  public FileHandle openReadOnly(@NotNull Path file) {
    Intrinsics.checkNotNullParameter(file, "file");
    if (!Companion.keepPath(file))
      throw new FileNotFoundException("file not found: " + file); 
    String relativePath = toRelativePath(file);
    for (Pair<FileSystem, Path> pair : getRoots()) {
      FileSystem fileSystem = (FileSystem)pair.component1();
      Path base = (Path)pair.component2();
      try {
        return fileSystem.openReadOnly(base.resolve(relativePath));
      } catch (FileNotFoundException fileNotFoundException) {}
    } 
    throw new FileNotFoundException("file not found: " + file);
  }
  
  @NotNull
  public FileHandle openReadWrite(@NotNull Path file, boolean mustCreate, boolean mustExist) {
    Intrinsics.checkNotNullParameter(file, "file");
    throw new IOException("resources are not writable");
  }
  
  @Nullable
  public FileMetadata metadataOrNull(@NotNull Path path) {
    Intrinsics.checkNotNullParameter(path, "path");
    if (!Companion.keepPath(path))
      return null; 
    String relativePath = toRelativePath(path);
    for (Pair<FileSystem, Path> pair : getRoots()) {
      FileSystem fileSystem = (FileSystem)pair.component1();
      Path base = (Path)pair.component2();
      if (fileSystem.metadataOrNull(base.resolve(relativePath)) == null) {
        fileSystem.metadataOrNull(base.resolve(relativePath));
        continue;
      } 
      return fileSystem.metadataOrNull(base.resolve(relativePath));
    } 
    return null;
  }
  
  @NotNull
  public Source source(@NotNull Path file) {
    Intrinsics.checkNotNullParameter(file, "file");
    if (!Companion.keepPath(file))
      throw new FileNotFoundException("file not found: " + file); 
    Path relativePath = Path.resolve$default(ROOT, file, false, 2, null).relativeTo(ROOT);
    if (this.classLoader.getResourceAsStream(relativePath.toString()) == null || Okio.source(this.classLoader.getResourceAsStream(relativePath.toString())) == null) {
      Okio.source(this.classLoader.getResourceAsStream(relativePath.toString()));
      throw new FileNotFoundException("file not found: " + file);
    } 
    return Okio.source(this.classLoader.getResourceAsStream(relativePath.toString()));
  }
  
  @NotNull
  public Sink sink(@NotNull Path file, boolean mustCreate) {
    Intrinsics.checkNotNullParameter(file, "file");
    throw new IOException(this + " is read-only");
  }
  
  @NotNull
  public Sink appendingSink(@NotNull Path file, boolean mustExist) {
    Intrinsics.checkNotNullParameter(file, "file");
    throw new IOException(this + " is read-only");
  }
  
  public void createDirectory(@NotNull Path dir, boolean mustCreate) {
    Intrinsics.checkNotNullParameter(dir, "dir");
    throw new IOException(this + " is read-only");
  }
  
  public void atomicMove(@NotNull Path source, @NotNull Path target) {
    Intrinsics.checkNotNullParameter(source, "source");
    Intrinsics.checkNotNullParameter(target, "target");
    throw new IOException(this + " is read-only");
  }
  
  public void delete(@NotNull Path path, boolean mustExist) {
    Intrinsics.checkNotNullParameter(path, "path");
    throw new IOException(this + " is read-only");
  }
  
  public void createSymlink(@NotNull Path source, @NotNull Path target) {
    Intrinsics.checkNotNullParameter(source, "source");
    Intrinsics.checkNotNullParameter(target, "target");
    throw new IOException(this + " is read-only");
  }
  
  private final String toRelativePath(Path $this$toRelativePath) {
    Path canonicalThis = canonicalizeInternal($this$toRelativePath);
    return canonicalThis.relativeTo(ROOT).toString();
  }
  
  private final List<Pair<FileSystem, Path>> toClasspathRoots(ClassLoader $this$toClasspathRoots) {
    Intrinsics.checkNotNullExpressionValue($this$toClasspathRoots.getResources(""), "getResources(...)");
    Intrinsics.checkNotNullExpressionValue(Collections.list($this$toClasspathRoots.getResources("")), "list(this)");
    ArrayList<URL> arrayList1 = Collections.list($this$toClasspathRoots.getResources(""));
    int $i$f$mapNotNull = 0;
    ArrayList<URL> arrayList2 = arrayList1;
    Collection destination$iv$iv = new ArrayList();
    int $i$f$mapNotNullTo = 0;
    Iterable<URL> iterable1 = arrayList2;
    int $i$f$forEach = 0;
    Iterator<URL> iterator = iterable1.iterator();
    if (iterator.hasNext()) {
      Object element$iv$iv$iv = iterator.next(), element$iv$iv = element$iv$iv$iv;
      int $i$a$-forEach-CollectionsKt___CollectionsKt$mapNotNullTo$1$iv$iv = 0;
      URL it = (URL)element$iv$iv;
      int $i$a$-mapNotNull-ResourceFileSystem$toClasspathRoots$1 = 0;
    } 
    Intrinsics.checkNotNullExpressionValue($this$toClasspathRoots.getResources("META-INF/MANIFEST.MF"), "getResources(...)");
    Intrinsics.checkNotNullExpressionValue(Collections.list($this$toClasspathRoots.getResources("META-INF/MANIFEST.MF")), "list(this)");
    Iterable<URL> $this$mapNotNull$iv = Collections.list($this$toClasspathRoots.getResources("META-INF/MANIFEST.MF"));
    List list = (List)destination$iv$iv;
    $i$f$mapNotNull = 0;
    Iterable<URL> $this$mapNotNullTo$iv$iv = $this$mapNotNull$iv;
    destination$iv$iv = new ArrayList();
    $i$f$mapNotNullTo = 0;
    Iterable<URL> $this$forEach$iv$iv$iv = $this$mapNotNullTo$iv$iv;
    $i$f$forEach = 0;
    iterator = $this$forEach$iv$iv$iv.iterator();
    if (iterator.hasNext()) {
      Object element$iv$iv$iv = iterator.next(), element$iv$iv = element$iv$iv$iv;
      int $i$a$-forEach-CollectionsKt___CollectionsKt$mapNotNullTo$1$iv$iv = 0;
      URL it = (URL)element$iv$iv;
      int $i$a$-mapNotNull-ResourceFileSystem$toClasspathRoots$2 = 0;
    } 
    return CollectionsKt.plus(list, destination$iv$iv);
  }
  
  private final Pair<FileSystem, Path> toFileRoot(URL $this$toFileRoot) {
    if (!Intrinsics.areEqual($this$toFileRoot.getProtocol(), "file"))
      return null; 
    return TuplesKt.to(this.systemFileSystem, Path.Companion.get$default(Path.Companion, new File($this$toFileRoot.toURI()), false, 1, null));
  }
  
  private final Pair<FileSystem, Path> toJarRoot(URL $this$toJarRoot) {
    Intrinsics.checkNotNullExpressionValue($this$toJarRoot.toString(), "toString(...)");
    String urlString = $this$toJarRoot.toString();
    if (!StringsKt.startsWith$default(urlString, "jar:file:", false, 2, null))
      return null; 
    int suffixStart = StringsKt.lastIndexOf$default(urlString, "!", 0, false, 6, null);
    if (suffixStart == -1)
      return null; 
    Intrinsics.checkNotNullExpressionValue(urlString.substring(4, suffixStart), "this as java.lang.String…ing(startIndex, endIndex)");
    Path path = Path.Companion.get$default(Path.Companion, new File(URI.create(urlString.substring(4, suffixStart))), false, 1, null);
    ZipFileSystem zip = ZipFilesKt.openZip(path, this.systemFileSystem, ResourceFileSystem$toJarRoot$zip$1.INSTANCE);
    return TuplesKt.to(zip, ROOT);
  }
  
  @Metadata(mv = {1, 9, 0}, k = 3, xi = 48, d1 = {"\000\016\n\002\030\002\n\000\n\002\020\013\n\002\b\003\020\005\032\0020\0022\006\020\001\032\0020\000H\n¢\006\004\b\003\020\004"}, d2 = {"Lokio/internal/ZipEntry;", "entry", "", "invoke", "(Lokio/internal/ZipEntry;)Ljava/lang/Boolean;", "<anonymous>"})
  static final class ResourceFileSystem$toJarRoot$zip$1 extends Lambda implements Function1<ZipEntry, Boolean> {
    public static final ResourceFileSystem$toJarRoot$zip$1 INSTANCE = new ResourceFileSystem$toJarRoot$zip$1();
    
    @NotNull
    public final Boolean invoke(@NotNull ZipEntry entry) {
      Intrinsics.checkNotNullParameter(entry, "entry");
      return Boolean.valueOf(ResourceFileSystem.Companion.keepPath(entry.getCanonicalPath()));
    }
    
    ResourceFileSystem$toJarRoot$zip$1() {
      super(1);
    }
  }
  
  @Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000\032\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\000\n\002\020\013\n\002\b\n\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J\027\020\007\032\0020\0062\006\020\005\032\0020\004H\002¢\006\004\b\007\020\bJ\031\020\n\032\0020\004*\0020\0042\006\020\t\032\0020\004¢\006\004\b\n\020\013R\027\020\f\032\0020\0048\006¢\006\f\n\004\b\f\020\r\032\004\b\016\020\017¨\006\020"}, d2 = {"Lokio/internal/ResourceFileSystem$Companion;", "", "<init>", "()V", "Lokio/Path;", "path", "", "keepPath", "(Lokio/Path;)Z", "base", "removeBase", "(Lokio/Path;Lokio/Path;)Lokio/Path;", "ROOT", "Lokio/Path;", "getROOT", "()Lokio/Path;", "okio"})
  private static final class Companion {
    private Companion() {}
    
    @NotNull
    public final Path getROOT() {
      return ResourceFileSystem.ROOT;
    }
    
    @NotNull
    public final Path removeBase(@NotNull Path $this$removeBase, @NotNull Path base) {
      Intrinsics.checkNotNullParameter($this$removeBase, "<this>");
      Intrinsics.checkNotNullParameter(base, "base");
      String prefix = base.toString();
      return getROOT().resolve(StringsKt.replace$default(StringsKt.removePrefix($this$removeBase.toString(), prefix), '\\', '/', false, 4, null));
    }
    
    private final boolean keepPath(Path path) {
      return !StringsKt.endsWith(path.name(), ".class", true);
    }
  }
  
  @NotNull
  private static final Companion Companion = new Companion(null);
  
  @NotNull
  private final ClassLoader classLoader;
  
  @NotNull
  private final FileSystem systemFileSystem;
  
  @NotNull
  private final Lazy roots$delegate;
  
  @NotNull
  private static final Path ROOT = Path.Companion.get$default(Path.Companion, "/", false, 1, null);
}
