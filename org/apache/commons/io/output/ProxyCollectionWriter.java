package org.apache.commons.io.output;

import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import org.apache.commons.io.IOUtils;

public class ProxyCollectionWriter extends FilterCollectionWriter {
  public ProxyCollectionWriter(Collection<Writer> writers) {
    super(writers);
  }
  
  public ProxyCollectionWriter(Writer... writers) {
    super(writers);
  }
  
  protected void afterWrite(int n) throws IOException {}
  
  public Writer append(char c) throws IOException {
    try {
      beforeWrite(1);
      super.append(c);
      afterWrite(1);
    } catch (IOException e) {
      handleIOException(e);
    } 
    return this;
  }
  
  public Writer append(CharSequence csq) throws IOException {
    try {
      int len = IOUtils.length(csq);
      beforeWrite(len);
      super.append(csq);
      afterWrite(len);
    } catch (IOException e) {
      handleIOException(e);
    } 
    return this;
  }
  
  public Writer append(CharSequence csq, int start, int end) throws IOException {
    try {
      beforeWrite(end - start);
      super.append(csq, start, end);
      afterWrite(end - start);
    } catch (IOException e) {
      handleIOException(e);
    } 
    return this;
  }
  
  protected void beforeWrite(int n) throws IOException {}
  
  public void close() throws IOException {
    try {
      super.close();
    } catch (IOException e) {
      handleIOException(e);
    } 
  }
  
  public void flush() throws IOException {
    try {
      super.flush();
    } catch (IOException e) {
      handleIOException(e);
    } 
  }
  
  protected void handleIOException(IOException e) throws IOException {
    throw e;
  }
  
  public void write(char[] cbuf) throws IOException {
    try {
      int len = IOUtils.length(cbuf);
      beforeWrite(len);
      super.write(cbuf);
      afterWrite(len);
    } catch (IOException e) {
      handleIOException(e);
    } 
  }
  
  public void write(char[] cbuf, int off, int len) throws IOException {
    try {
      beforeWrite(len);
      super.write(cbuf, off, len);
      afterWrite(len);
    } catch (IOException e) {
      handleIOException(e);
    } 
  }
  
  public void write(int c) throws IOException {
    try {
      beforeWrite(1);
      super.write(c);
      afterWrite(1);
    } catch (IOException e) {
      handleIOException(e);
    } 
  }
  
  public void write(String str) throws IOException {
    try {
      int len = IOUtils.length(str);
      beforeWrite(len);
      super.write(str);
      afterWrite(len);
    } catch (IOException e) {
      handleIOException(e);
    } 
  }
  
  public void write(String str, int off, int len) throws IOException {
    try {
      beforeWrite(len);
      super.write(str, off, len);
      afterWrite(len);
    } catch (IOException e) {
      handleIOException(e);
    } 
  }
}
