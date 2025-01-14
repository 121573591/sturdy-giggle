package org.openjdk.nashorn.api.tree;

import org.openjdk.nashorn.internal.runtime.Source;

final class LineMapImpl implements LineMap {
  private final Source source;
  
  LineMapImpl(Source source) {
    this.source = source;
  }
  
  public long getLineNumber(long pos) {
    return this.source.getLine((int)pos);
  }
  
  public long getColumnNumber(long pos) {
    return this.source.getColumn((int)pos);
  }
}
