package org.apache.commons.io.input;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.CharBuffer;

public class TeeReader extends ProxyReader {
  private final Writer branch;
  
  private final boolean closeBranch;
  
  public TeeReader(Reader input, Writer branch) {
    this(input, branch, false);
  }
  
  public TeeReader(Reader input, Writer branch, boolean closeBranch) {
    super(input);
    this.branch = branch;
    this.closeBranch = closeBranch;
  }
  
  public void close() throws IOException {
    try {
      super.close();
    } finally {
      if (this.closeBranch)
        this.branch.close(); 
    } 
  }
  
  public int read() throws IOException {
    int ch = super.read();
    if (ch != -1)
      this.branch.write(ch); 
    return ch;
  }
  
  public int read(char[] chr) throws IOException {
    int n = super.read(chr);
    if (n != -1)
      this.branch.write(chr, 0, n); 
    return n;
  }
  
  public int read(char[] chr, int st, int end) throws IOException {
    int n = super.read(chr, st, end);
    if (n != -1)
      this.branch.write(chr, st, n); 
    return n;
  }
  
  public int read(CharBuffer target) throws IOException {
    int originalPosition = target.position();
    int n = super.read(target);
    if (n != -1) {
      int newPosition = target.position();
      int newLimit = target.limit();
      try {
        target.position(originalPosition).limit(newPosition);
        this.branch.append(target);
      } finally {
        target.position(newPosition).limit(newLimit);
      } 
    } 
    return n;
  }
}
