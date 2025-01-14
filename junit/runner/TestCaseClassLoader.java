package junit.runner;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class TestCaseClassLoader extends ClassLoader {
  private Vector fPathItems;
  
  private String[] defaultExclusions = new String[] { "junit.framework.", "junit.extensions.", "junit.runner." };
  
  static final String EXCLUDED_FILE = "excluded.properties";
  
  private Vector fExcluded;
  
  public TestCaseClassLoader() {
    this(System.getProperty("java.class.path"));
  }
  
  public TestCaseClassLoader(String classPath) {
    scanPath(classPath);
    readExcludedPackages();
  }
  
  private void scanPath(String classPath) {
    String separator = System.getProperty("path.separator");
    this.fPathItems = new Vector(10);
    StringTokenizer st = new StringTokenizer(classPath, separator);
    while (st.hasMoreTokens())
      this.fPathItems.addElement(st.nextToken()); 
  }
  
  public URL getResource(String name) {
    return ClassLoader.getSystemResource(name);
  }
  
  public InputStream getResourceAsStream(String name) {
    return ClassLoader.getSystemResourceAsStream(name);
  }
  
  public boolean isExcluded(String name) {
    for (int i = 0; i < this.fExcluded.size(); i++) {
      if (name.startsWith(this.fExcluded.elementAt(i)))
        return true; 
    } 
    return false;
  }
  
  public synchronized Class loadClass(String name, boolean resolve) throws ClassNotFoundException {
    Class c = findLoadedClass(name);
    if (c != null)
      return c; 
    if (isExcluded(name))
      try {
        c = findSystemClass(name);
        return c;
      } catch (ClassNotFoundException e) {} 
    if (c == null) {
      byte[] data = lookupClassData(name);
      if (data == null)
        throw new ClassNotFoundException(); 
      c = defineClass(name, data, 0, data.length);
    } 
    if (resolve)
      resolveClass(c); 
    return c;
  }
  
  private byte[] lookupClassData(String className) throws ClassNotFoundException {
    byte[] data = null;
    for (int i = 0; i < this.fPathItems.size(); i++) {
      String path = this.fPathItems.elementAt(i);
      String fileName = className.replace('.', '/') + ".class";
      if (isJar(path)) {
        data = loadJarData(path, fileName);
      } else {
        data = loadFileData(path, fileName);
      } 
      if (data != null)
        return data; 
    } 
    throw new ClassNotFoundException(className);
  }
  
  boolean isJar(String pathEntry) {
    return (pathEntry.endsWith(".jar") || pathEntry.endsWith(".zip"));
  }
  
  private byte[] loadFileData(String path, String fileName) {
    File file = new File(path, fileName);
    if (file.exists())
      return getClassData(file); 
    return null;
  }
  
  private byte[] getClassData(File f) {
    FileInputStream stream = null;
    try {
      stream = new FileInputStream(f);
      ByteArrayOutputStream out = new ByteArrayOutputStream(1000);
      byte[] b = new byte[1000];
      int n;
      while ((n = stream.read(b)) != -1)
        out.write(b, 0, n); 
      stream.close();
      out.close();
      return out.toByteArray();
    } catch (IOException e) {
    
    } finally {
      if (stream != null)
        try {
          stream.close();
        } catch (IOException e1) {} 
    } 
    return null;
  }
  
  private byte[] loadJarData(String path, String fileName) {
    ZipFile zipFile = null;
    InputStream stream = null;
    File archive = new File(path);
    if (!archive.exists())
      return null; 
    try {
      zipFile = new ZipFile(archive);
    } catch (IOException io) {
      return null;
    } 
    ZipEntry entry = zipFile.getEntry(fileName);
    if (entry == null)
      return null; 
    int size = (int)entry.getSize();
    try {
      stream = zipFile.getInputStream(entry);
      byte[] data = new byte[size];
      int pos = 0;
      while (pos < size) {
        int n = stream.read(data, pos, data.length - pos);
        pos += n;
      } 
      zipFile.close();
      return data;
    } catch (IOException e) {
    
    } finally {
      try {
        if (stream != null)
          stream.close(); 
      } catch (IOException e) {}
    } 
    return null;
  }
  
  private void readExcludedPackages() {
    this.fExcluded = new Vector(10);
    for (int i = 0; i < this.defaultExclusions.length; i++)
      this.fExcluded.addElement(this.defaultExclusions[i]); 
    InputStream is = getClass().getResourceAsStream("excluded.properties");
    if (is == null)
      return; 
    Properties p = new Properties();
    try {
      p.load(is);
    } catch (IOException iOException) {
      return;
    } finally {
      try {
        is.close();
      } catch (IOException iOException) {}
    } 
    for (Enumeration e = p.propertyNames(); e.hasMoreElements(); ) {
      String key = (String)e.nextElement();
      if (key.startsWith("excluded.")) {
        String path = p.getProperty(key);
        path = path.trim();
        if (path.endsWith("*"))
          path = path.substring(0, path.length() - 1); 
        if (path.length() > 0)
          this.fExcluded.addElement(path); 
      } 
    } 
  }
}
