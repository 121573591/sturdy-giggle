package org.apache.commons.io.file.spi;

import java.net.URI;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.spi.FileSystemProvider;
import java.util.List;
import java.util.Objects;

public class FileSystemProviders {
  private static final FileSystemProviders INSTALLED = new FileSystemProviders(FileSystemProvider.installedProviders());
  
  private final List<FileSystemProvider> providers;
  
  public static FileSystemProvider getFileSystemProvider(Path path) {
    return ((Path)Objects.<Path>requireNonNull(path, "path")).getFileSystem().provider();
  }
  
  public static FileSystemProviders installed() {
    return INSTALLED;
  }
  
  private FileSystemProviders(List<FileSystemProvider> providers) {
    this.providers = providers;
  }
  
  public FileSystemProvider getFileSystemProvider(String scheme) {
    Objects.requireNonNull(scheme, "scheme");
    if (scheme.equalsIgnoreCase("file"))
      return FileSystems.getDefault().provider(); 
    if (this.providers != null)
      for (FileSystemProvider provider : this.providers) {
        if (provider.getScheme().equalsIgnoreCase(scheme))
          return provider; 
      }  
    return null;
  }
  
  public FileSystemProvider getFileSystemProvider(URI uri) {
    return getFileSystemProvider(((URI)Objects.<URI>requireNonNull(uri, "uri")).getScheme());
  }
  
  public FileSystemProvider getFileSystemProvider(URL url) {
    return getFileSystemProvider(((URL)Objects.<URL>requireNonNull(url, "url")).getProtocol());
  }
}
