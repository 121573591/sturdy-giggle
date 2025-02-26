package org.apache.commons.io.input;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;
import java.util.function.IntPredicate;

public abstract class AbstractCharacterFilterReader extends FilterReader {
  protected static final IntPredicate SKIP_NONE = ch -> false;
  
  private final IntPredicate skip;
  
  protected AbstractCharacterFilterReader(Reader reader) {
    this(reader, SKIP_NONE);
  }
  
  protected AbstractCharacterFilterReader(Reader reader, IntPredicate skip) {
    super(reader);
    this.skip = (skip == null) ? SKIP_NONE : skip;
  }
  
  protected boolean filter(int ch) {
    return this.skip.test(ch);
  }
  
  public int read() throws IOException {
    int ch;
    do {
      ch = this.in.read();
    } while (ch != -1 && filter(ch));
    return ch;
  }
  
  public int read(char[] cbuf, int off, int len) throws IOException {
    int read = super.read(cbuf, off, len);
    if (read == -1)
      return -1; 
    int pos = off - 1;
    for (int readPos = off; readPos < off + read; readPos++) {
      if (!filter(cbuf[readPos])) {
        pos++;
        if (pos < readPos)
          cbuf[pos] = cbuf[readPos]; 
      } 
    } 
    return pos - off + 1;
  }
}
