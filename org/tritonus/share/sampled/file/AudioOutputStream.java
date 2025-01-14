package org.tritonus.share.sampled.file;

import java.io.IOException;
import javax.sound.sampled.AudioFormat;

public interface AudioOutputStream {
  AudioFormat getFormat();
  
  long getLength();
  
  int write(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException;
  
  void close() throws IOException;
}
