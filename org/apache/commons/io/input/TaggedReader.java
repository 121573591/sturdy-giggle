package org.apache.commons.io.input;

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.util.UUID;
import org.apache.commons.io.TaggedIOException;

public class TaggedReader extends ProxyReader {
  private final Serializable tag = UUID.randomUUID();
  
  public TaggedReader(Reader proxy) {
    super(proxy);
  }
  
  public boolean isCauseOf(Throwable exception) {
    return TaggedIOException.isTaggedWith(exception, this.tag);
  }
  
  public void throwIfCauseOf(Throwable throwable) throws IOException {
    TaggedIOException.throwCauseIfTaggedWith(throwable, this.tag);
  }
  
  protected void handleIOException(IOException e) throws IOException {
    throw new TaggedIOException(e, this.tag);
  }
}
