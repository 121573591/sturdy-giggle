package org.tritonus.share.sampled.file;

import java.io.DataOutput;
import java.io.IOException;

public interface TDataOutputStream extends DataOutput {
  boolean supportsSeek();
  
  void seek(long paramLong) throws IOException;
  
  long getFilePointer() throws IOException;
  
  long length() throws IOException;
  
  void writeLittleEndian32(int paramInt) throws IOException;
  
  void writeLittleEndian16(short paramShort) throws IOException;
  
  void close() throws IOException;
}
