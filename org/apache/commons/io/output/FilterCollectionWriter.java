package org.apache.commons.io.output;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.apache.commons.io.IOExceptionList;
import org.apache.commons.io.IOIndexedException;

public class FilterCollectionWriter extends Writer {
  protected final Collection<Writer> EMPTY_WRITERS = Collections.emptyList();
  
  protected final Collection<Writer> writers;
  
  protected FilterCollectionWriter(Collection<Writer> writers) {
    this.writers = (writers == null) ? this.EMPTY_WRITERS : writers;
  }
  
  protected FilterCollectionWriter(Writer... writers) {
    this.writers = (writers == null) ? this.EMPTY_WRITERS : Arrays.<Writer>asList(writers);
  }
  
  private List<Exception> add(List<Exception> causeList, int i, IOException e) {
    if (causeList == null)
      causeList = new ArrayList<>(); 
    causeList.add(new IOIndexedException(i, e));
    return causeList;
  }
  
  public Writer append(char c) throws IOException {
    List<Exception> causeList = null;
    int i = 0;
    for (Writer w : this.writers) {
      if (w != null)
        try {
          w.append(c);
        } catch (IOException e) {
          causeList = add(causeList, i, e);
        }  
      i++;
    } 
    if (notEmpty(causeList))
      throw new IOExceptionList("append", causeList); 
    return this;
  }
  
  public Writer append(CharSequence csq) throws IOException {
    List<Exception> causeList = null;
    int i = 0;
    for (Writer w : this.writers) {
      if (w != null)
        try {
          w.append(csq);
        } catch (IOException e) {
          causeList = add(causeList, i, e);
        }  
      i++;
    } 
    if (notEmpty(causeList))
      throw new IOExceptionList("append", causeList); 
    return this;
  }
  
  public Writer append(CharSequence csq, int start, int end) throws IOException {
    List<Exception> causeList = null;
    int i = 0;
    for (Writer w : this.writers) {
      if (w != null)
        try {
          w.append(csq, start, end);
        } catch (IOException e) {
          causeList = add(causeList, i, e);
        }  
      i++;
    } 
    if (notEmpty(causeList))
      throw new IOExceptionList("append", causeList); 
    return this;
  }
  
  public void close() throws IOException {
    List<Exception> causeList = null;
    int i = 0;
    for (Writer w : this.writers) {
      if (w != null)
        try {
          w.close();
        } catch (IOException e) {
          causeList = add(causeList, i, e);
        }  
      i++;
    } 
    if (notEmpty(causeList))
      throw new IOExceptionList("close", causeList); 
  }
  
  public void flush() throws IOException {
    List<Exception> causeList = null;
    int i = 0;
    for (Writer w : this.writers) {
      if (w != null)
        try {
          w.flush();
        } catch (IOException e) {
          causeList = add(causeList, i, e);
        }  
      i++;
    } 
    if (notEmpty(causeList))
      throw new IOExceptionList("flush", causeList); 
  }
  
  private boolean notEmpty(List<Exception> causeList) {
    return (causeList != null && !causeList.isEmpty());
  }
  
  public void write(char[] cbuf) throws IOException {
    List<Exception> causeList = null;
    int i = 0;
    for (Writer w : this.writers) {
      if (w != null)
        try {
          w.write(cbuf);
        } catch (IOException e) {
          causeList = add(causeList, i, e);
        }  
      i++;
    } 
    if (notEmpty(causeList))
      throw new IOExceptionList("write", causeList); 
  }
  
  public void write(char[] cbuf, int off, int len) throws IOException {
    List<Exception> causeList = null;
    int i = 0;
    for (Writer w : this.writers) {
      if (w != null)
        try {
          w.write(cbuf, off, len);
        } catch (IOException e) {
          causeList = add(causeList, i, e);
        }  
      i++;
    } 
    if (notEmpty(causeList))
      throw new IOExceptionList("write", causeList); 
  }
  
  public void write(int c) throws IOException {
    List<Exception> causeList = null;
    int i = 0;
    for (Writer w : this.writers) {
      if (w != null)
        try {
          w.write(c);
        } catch (IOException e) {
          causeList = add(causeList, i, e);
        }  
      i++;
    } 
    if (notEmpty(causeList))
      throw new IOExceptionList("write", causeList); 
  }
  
  public void write(String str) throws IOException {
    List<Exception> causeList = null;
    int i = 0;
    for (Writer w : this.writers) {
      if (w != null)
        try {
          w.write(str);
        } catch (IOException e) {
          causeList = add(causeList, i, e);
        }  
      i++;
    } 
    if (notEmpty(causeList))
      throw new IOExceptionList("write", causeList); 
  }
  
  public void write(String str, int off, int len) throws IOException {
    List<Exception> causeList = null;
    int i = 0;
    for (Writer w : this.writers) {
      if (w != null)
        try {
          w.write(str, off, len);
        } catch (IOException e) {
          causeList = add(causeList, i, e);
        }  
      i++;
    } 
    if (notEmpty(causeList))
      throw new IOExceptionList("write", causeList); 
  }
}
