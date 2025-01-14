package org.tritonus.share.sampled.file;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class TSeekableDataOutputStream extends RandomAccessFile implements TDataOutputStream {
  public TSeekableDataOutputStream(File file) throws IOException {
    super(file, "rw");
  }
  
  public boolean supportsSeek() {
    return true;
  }
  
  public void writeLittleEndian32(int value) throws IOException {
    writeByte(value & 0xFF);
    writeByte(value >> 8 & 0xFF);
    writeByte(value >> 16 & 0xFF);
    writeByte(value >> 24 & 0xFF);
  }
  
  public void writeLittleEndian16(short value) throws IOException {
    writeByte(value & 0xFF);
    writeByte(value >> 8 & 0xFF);
  }
}
