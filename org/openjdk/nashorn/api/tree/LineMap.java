package org.openjdk.nashorn.api.tree;

public interface LineMap {
  long getLineNumber(long paramLong);
  
  long getColumnNumber(long paramLong);
}
