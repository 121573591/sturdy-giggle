package cn.hutool.core.compiler;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.URLUtil;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.Charset;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;

class JavaSourceFileObject extends SimpleJavaFileObject {
  private InputStream inputStream;
  
  private String sourceCode;
  
  protected JavaSourceFileObject(URI uri) {
    super(uri, JavaFileObject.Kind.SOURCE);
  }
  
  protected JavaSourceFileObject(String className, String code, Charset charset) {
    this(className, IoUtil.toStream(code, charset));
  }
  
  protected JavaSourceFileObject(String name, InputStream inputStream) {
    this(URLUtil.getStringURI(name.replace('.', '/') + JavaFileObject.Kind.SOURCE.extension));
    this.inputStream = inputStream;
  }
  
  public InputStream openInputStream() throws IOException {
    if (this.inputStream == null)
      this.inputStream = toUri().toURL().openStream(); 
    return new BufferedInputStream(this.inputStream);
  }
  
  public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
    if (this.sourceCode == null)
      try (InputStream in = openInputStream()) {
        this.sourceCode = IoUtil.readUtf8(in);
      }  
    return this.sourceCode;
  }
}
