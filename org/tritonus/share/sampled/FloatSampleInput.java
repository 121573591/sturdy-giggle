package org.tritonus.share.sampled;

public interface FloatSampleInput {
  void read(FloatSampleBuffer paramFloatSampleBuffer);
  
  void read(FloatSampleBuffer paramFloatSampleBuffer, int paramInt1, int paramInt2);
  
  boolean isDone();
  
  int getChannels();
  
  float getSampleRate();
}
