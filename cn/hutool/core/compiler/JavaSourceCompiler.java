package cn.hutool.core.compiler;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.resource.FileResource;
import cn.hutool.core.io.resource.Resource;
import cn.hutool.core.io.resource.StringResource;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.ClassLoaderUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.URLUtil;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardLocation;

public class JavaSourceCompiler {
  private final List<Resource> sourceList = new ArrayList<>();
  
  private final List<File> libraryFileList = new ArrayList<>();
  
  private final ClassLoader parentClassLoader;
  
  public static JavaSourceCompiler create(ClassLoader parent) {
    return new JavaSourceCompiler(parent);
  }
  
  private JavaSourceCompiler(ClassLoader parent) {
    this.parentClassLoader = (ClassLoader)ObjectUtil.defaultIfNull(parent, ClassLoaderUtil::getClassLoader);
  }
  
  public JavaSourceCompiler addSource(Resource... resources) {
    if (ArrayUtil.isNotEmpty((Object[])resources))
      this.sourceList.addAll(Arrays.asList(resources)); 
    return this;
  }
  
  public JavaSourceCompiler addSource(File... files) {
    if (ArrayUtil.isNotEmpty((Object[])files))
      for (File file : files)
        this.sourceList.add(new FileResource(file));  
    return this;
  }
  
  public JavaSourceCompiler addSource(Map<String, String> sourceCodeMap) {
    if (MapUtil.isNotEmpty(sourceCodeMap))
      sourceCodeMap.forEach(this::addSource); 
    return this;
  }
  
  public JavaSourceCompiler addSource(String className, String sourceCode) {
    if (className != null && sourceCode != null)
      this.sourceList.add(new StringResource(sourceCode, className)); 
    return this;
  }
  
  public JavaSourceCompiler addLibrary(File... files) {
    if (ArrayUtil.isNotEmpty((Object[])files))
      this.libraryFileList.addAll(Arrays.asList(files)); 
    return this;
  }
  
  public ClassLoader compile() {
    return compile(null);
  }
  
  public ClassLoader compile(List<String> options) {
    List<File> classPath = getClassPath();
    URL[] urLs = URLUtil.getURLs(classPath.<File>toArray(new File[0]));
    URLClassLoader ucl = URLClassLoader.newInstance(urLs, this.parentClassLoader);
    if (this.sourceList.isEmpty())
      return ucl; 
    JavaClassFileManager javaFileManager = new JavaClassFileManager(ucl, CompilerUtil.getFileManager());
    if (null == options)
      options = new ArrayList<>(); 
    if (false == classPath.isEmpty()) {
      List<String> cp = CollUtil.map(classPath, File::getAbsolutePath, true);
      options.add("-cp");
      options.add(CollUtil.join(cp, FileUtil.isWindows() ? ";" : ":"));
    } 
    DiagnosticCollector<? super JavaFileObject> diagnosticCollector = new DiagnosticCollector<>();
    List<JavaFileObject> javaFileObjectList = getJavaFileObject();
    JavaCompiler.CompilationTask task = CompilerUtil.getTask(javaFileManager, diagnosticCollector, options, javaFileObjectList);
    try {
      if (task.call().booleanValue())
        return javaFileManager.getClassLoader(StandardLocation.CLASS_OUTPUT); 
    } finally {
      IoUtil.close(javaFileManager);
    } 
    throw new CompilerException(DiagnosticUtil.getMessages(diagnosticCollector));
  }
  
  private List<File> getClassPath() {
    List<File> classPathFileList = new ArrayList<>();
    for (File file : this.libraryFileList) {
      List<File> jarOrZipFile = FileUtil.loopFiles(file, subFile -> JavaFileObjectUtil.isJarOrZipFile(subFile.getName()));
      classPathFileList.addAll(jarOrZipFile);
      if (file.isDirectory())
        classPathFileList.add(file); 
    } 
    return classPathFileList;
  }
  
  private List<JavaFileObject> getJavaFileObject() {
    List<JavaFileObject> list = new ArrayList<>();
    for (Resource resource : this.sourceList) {
      if (resource instanceof FileResource) {
        File file = ((FileResource)resource).getFile();
        FileUtil.walkFiles(file, subFile -> list.addAll(JavaFileObjectUtil.getJavaFileObjects(subFile)));
        continue;
      } 
      list.add(new JavaSourceFileObject(resource.getName(), resource.getStream()));
    } 
    return list;
  }
  
  private Collection<JavaFileObject> getJavaFileObjectByMap(Map<String, String> sourceCodeMap) {
    if (MapUtil.isNotEmpty(sourceCodeMap))
      return (Collection<JavaFileObject>)sourceCodeMap.entrySet().stream()
        .map(entry -> new JavaSourceFileObject((String)entry.getKey(), (String)entry.getValue(), CharsetUtil.CHARSET_UTF_8))
        .collect(Collectors.toList()); 
    return Collections.emptySet();
  }
  
  private JavaFileObject getJavaFileObjectByJavaFile(File file) {
    return new JavaSourceFileObject(file.toURI());
  }
}
