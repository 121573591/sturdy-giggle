package org.apache.commons.io.output;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import org.apache.commons.io.Charsets;
import org.apache.commons.io.FileUtils;

public class LockableFileWriter extends Writer {
  private static final String LCK = ".lck";
  
  private final Writer out;
  
  private final File lockFile;
  
  public LockableFileWriter(String fileName) throws IOException {
    this(fileName, false, (String)null);
  }
  
  public LockableFileWriter(String fileName, boolean append) throws IOException {
    this(fileName, append, (String)null);
  }
  
  public LockableFileWriter(String fileName, boolean append, String lockDir) throws IOException {
    this(new File(fileName), append, lockDir);
  }
  
  public LockableFileWriter(File file) throws IOException {
    this(file, false, (String)null);
  }
  
  public LockableFileWriter(File file, boolean append) throws IOException {
    this(file, append, (String)null);
  }
  
  @Deprecated
  public LockableFileWriter(File file, boolean append, String lockDir) throws IOException {
    this(file, Charset.defaultCharset(), append, lockDir);
  }
  
  public LockableFileWriter(File file, Charset charset) throws IOException {
    this(file, charset, false, (String)null);
  }
  
  public LockableFileWriter(File file, String charsetName) throws IOException {
    this(file, charsetName, false, (String)null);
  }
  
  public LockableFileWriter(File file, Charset charset, boolean append, String lockDir) throws IOException {
    file = file.getAbsoluteFile();
    if (file.getParentFile() != null)
      FileUtils.forceMkdir(file.getParentFile()); 
    if (file.isDirectory())
      throw new IOException("File specified is a directory"); 
    if (lockDir == null)
      lockDir = System.getProperty("java.io.tmpdir"); 
    File lockDirFile = new File(lockDir);
    FileUtils.forceMkdir(lockDirFile);
    testLockDir(lockDirFile);
    this.lockFile = new File(lockDirFile, file.getName() + ".lck");
    createLock();
    this.out = initWriter(file, charset, append);
  }
  
  public LockableFileWriter(File file, String charsetName, boolean append, String lockDir) throws IOException {
    this(file, Charsets.toCharset(charsetName), append, lockDir);
  }
  
  private void testLockDir(File lockDir) throws IOException {
    if (!lockDir.exists())
      throw new IOException("Could not find lockDir: " + lockDir
          .getAbsolutePath()); 
    if (!lockDir.canWrite())
      throw new IOException("Could not write to lockDir: " + lockDir
          .getAbsolutePath()); 
  }
  
  private void createLock() throws IOException {
    synchronized (LockableFileWriter.class) {
      if (!this.lockFile.createNewFile())
        throw new IOException("Can't write file, lock " + this.lockFile
            .getAbsolutePath() + " exists"); 
      this.lockFile.deleteOnExit();
    } 
  }
  
  private Writer initWriter(File file, Charset charset, boolean append) throws IOException {
    boolean fileExistedAlready = file.exists();
    try {
      return new OutputStreamWriter(new FileOutputStream(file.getAbsolutePath(), append), 
          Charsets.toCharset(charset));
    } catch (IOException|RuntimeException ex) {
      FileUtils.deleteQuietly(this.lockFile);
      if (!fileExistedAlready)
        FileUtils.deleteQuietly(file); 
      throw ex;
    } 
  }
  
  public void close() throws IOException {
    try {
      this.out.close();
    } finally {
      FileUtils.delete(this.lockFile);
    } 
  }
  
  public void write(int c) throws IOException {
    this.out.write(c);
  }
  
  public void write(char[] cbuf) throws IOException {
    this.out.write(cbuf);
  }
  
  public void write(char[] cbuf, int off, int len) throws IOException {
    this.out.write(cbuf, off, len);
  }
  
  public void write(String str) throws IOException {
    this.out.write(str);
  }
  
  public void write(String str, int off, int len) throws IOException {
    this.out.write(str, off, len);
  }
  
  public void flush() throws IOException {
    this.out.flush();
  }
}
