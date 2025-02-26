package com.fasterxml.jackson.databind.ext;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.spi.FileSystemProvider;
import java.util.ServiceConfigurationError;
import java.util.ServiceLoader;

public class NioPathDeserializer extends StdScalarDeserializer<Path> {
  private static final long serialVersionUID = 1L;
  
  private static final boolean areWindowsFilePathsSupported;
  
  static {
    boolean isWindowsRootFound = false;
    for (File file : File.listRoots()) {
      String path = file.getPath();
      if (path.length() >= 2 && Character.isLetter(path.charAt(0)) && path.charAt(1) == ':') {
        isWindowsRootFound = true;
        break;
      } 
    } 
    areWindowsFilePathsSupported = isWindowsRootFound;
  }
  
  public NioPathDeserializer() {
    super(Path.class);
  }
  
  public Path deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
    URI uri;
    if (!p.hasToken(JsonToken.VALUE_STRING))
      return (Path)ctxt.handleUnexpectedToken(Path.class, p); 
    String value = p.getText();
    if (value.indexOf(':') < 0)
      return Paths.get(value, new String[0]); 
    if (areWindowsFilePathsSupported && 
      value.length() >= 2 && Character.isLetter(value.charAt(0)) && value.charAt(1) == ':')
      return Paths.get(value, new String[0]); 
    try {
      uri = new URI(value);
    } catch (URISyntaxException e) {
      return (Path)ctxt.handleInstantiationProblem(handledType(), value, e);
    } 
    try {
      return Paths.get(uri);
    } catch (FileSystemNotFoundException cause) {
      try {
        String scheme = uri.getScheme();
        for (FileSystemProvider provider : ServiceLoader.<FileSystemProvider>load(FileSystemProvider.class)) {
          if (provider.getScheme().equalsIgnoreCase(scheme))
            return provider.getPath(uri); 
        } 
        return (Path)ctxt.handleInstantiationProblem(handledType(), value, cause);
      } catch (ServiceConfigurationError e) {
        e.addSuppressed(cause);
        return (Path)ctxt.handleInstantiationProblem(handledType(), value, e);
      } 
    } catch (Exception e) {
      return (Path)ctxt.handleInstantiationProblem(handledType(), value, e);
    } 
  }
}
