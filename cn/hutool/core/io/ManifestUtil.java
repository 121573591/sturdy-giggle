package cn.hutool.core.io;

import cn.hutool.core.io.resource.ResourceUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class ManifestUtil {
  private static final String[] MANIFEST_NAMES = new String[] { "Manifest.mf", "manifest.mf", "MANIFEST.MF" };
  
  public static Manifest getManifest(Class<?> cls) throws IORuntimeException {
    URLConnection connection;
    URL url = ResourceUtil.getResource(null, cls);
    try {
      connection = url.openConnection();
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
    if (connection instanceof JarURLConnection) {
      JarURLConnection conn = (JarURLConnection)connection;
      return getManifest(conn);
    } 
    return null;
  }
  
  public static Manifest getManifest(File classpathItem) throws IORuntimeException {
    Manifest manifest = null;
    if (classpathItem.isFile()) {
      try (JarFile jarFile = new JarFile(classpathItem)) {
        manifest = getManifest(jarFile);
      } catch (IOException e) {
        throw new IORuntimeException(e);
      } 
    } else {
      File metaDir = new File(classpathItem, "META-INF");
      File manifestFile = null;
      if (metaDir.isDirectory())
        for (String name : MANIFEST_NAMES) {
          File mFile = new File(metaDir, name);
          if (mFile.isFile()) {
            manifestFile = mFile;
            break;
          } 
        }  
      if (null != manifestFile)
        try (FileInputStream fis = new FileInputStream(manifestFile)) {
          manifest = new Manifest(fis);
        } catch (IOException e) {
          throw new IORuntimeException(e);
        }  
    } 
    return manifest;
  }
  
  public static Manifest getManifest(JarURLConnection connection) throws IORuntimeException {
    JarFile jarFile;
    try {
      jarFile = connection.getJarFile();
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
    return getManifest(jarFile);
  }
  
  public static Manifest getManifest(JarFile jarFile) throws IORuntimeException {
    try {
      return jarFile.getManifest();
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
  }
}
