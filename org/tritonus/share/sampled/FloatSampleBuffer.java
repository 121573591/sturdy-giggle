package org.tritonus.share.sampled;

import javax.sound.sampled.AudioFormat;

public class FloatSampleBuffer {
  private static final boolean LAZY_DEFAULT = true;
  
  private Object[] channels = new Object[2];
  
  private int sampleCount = 0;
  
  private int channelCount = 0;
  
  private float sampleRate = 0.0F;
  
  private int originalFormatType = 0;
  
  public static final int DITHER_MODE_AUTOMATIC = 0;
  
  public static final int DITHER_MODE_ON = 1;
  
  public static final int DITHER_MODE_OFF = 2;
  
  private float ditherBits = 0.7F;
  
  private int ditherMode = 0;
  
  private AudioFormat lastConvertToByteArrayFormat;
  
  private int lastConvertToByteArrayFormatCode;
  
  public FloatSampleBuffer() {
    this(0, 0, 1.0F);
  }
  
  public FloatSampleBuffer(byte[] buffer, int offset, int byteCount, AudioFormat format) {
    this(format.getChannels(), byteCount / format
        .getSampleSizeInBits() / 8 * format.getChannels(), format
        .getSampleRate());
    initFromByteArray(buffer, offset, byteCount, format);
  }
  
  public void init(int newChannelCount, int newSampleCount, float newSampleRate) {
    init(newChannelCount, newSampleCount, newSampleRate, true);
  }
  
  public void init(int newChannelCount, int newSampleCount, float newSampleRate, boolean lazy) {
    if (newChannelCount < 0 || newSampleCount < 0 || newSampleRate <= 0.0F)
      throw new IllegalArgumentException("invalid parameters in initialization of FloatSampleBuffer."); 
    setSampleRate(newSampleRate);
    if (this.sampleCount != newSampleCount || this.channelCount != newChannelCount)
      createChannels(newChannelCount, newSampleCount, lazy); 
  }
  
  public static void checkFormatSupported(AudioFormat format) {
    FloatSampleTools.getFormatType(format);
  }
  
  private final void grow(int newChannelCount, boolean lazy) {
    if (this.channels.length < newChannelCount || !lazy) {
      Object[] newChannels = new Object[newChannelCount];
      System.arraycopy(this.channels, 0, newChannels, 0, (this.channelCount < newChannelCount) ? this.channelCount : newChannelCount);
      this.channels = newChannels;
    } 
  }
  
  private final void createChannels(int newChannelCount, int newSampleCount, boolean lazy) {
    if (lazy && newChannelCount <= this.channelCount && newSampleCount <= this.sampleCount) {
      setSampleCountImpl(newSampleCount);
      setChannelCountImpl(newChannelCount);
      return;
    } 
    setSampleCountImpl(newSampleCount);
    grow(newChannelCount, true);
    setChannelCountImpl(0);
    for (int ch = 0; ch < newChannelCount; ch++)
      insertChannel(ch, false, lazy); 
    grow(newChannelCount, lazy);
  }
  
  public void initFromByteArray(byte[] buffer, int offset, int byteCount, AudioFormat format) {
    initFromByteArray(buffer, offset, byteCount, format, true);
  }
  
  public void initFromByteArray(byte[] buffer, int offset, int byteCount, AudioFormat format, boolean lazy) {
    if (offset + byteCount > buffer.length)
      throw new IllegalArgumentException("FloatSampleBuffer.initFromByteArray: buffer too small."); 
    int thisSampleCount = byteCount / format.getFrameSize();
    init(format.getChannels(), thisSampleCount, format.getSampleRate(), lazy);
    this.originalFormatType = FloatSampleTools.getFormatType(format);
    FloatSampleTools.byte2float(buffer, offset, this.channels, 0, this.sampleCount, format);
  }
  
  public void initFromFloatSampleBuffer(FloatSampleBuffer source) {
    init(source.getChannelCount(), source.getSampleCount(), source
        .getSampleRate());
    for (int ch = 0; ch < getChannelCount(); ch++)
      System.arraycopy(source.getChannel(ch), 0, getChannel(ch), 0, this.sampleCount); 
  }
  
  public int writeByteBuffer(byte[] buffer, int srcByteOffset, AudioFormat format, int dstSampleOffset, int aSampleCount) {
    if (dstSampleOffset + aSampleCount > getSampleCount())
      aSampleCount = getSampleCount() - dstSampleOffset; 
    int lChannels = format.getChannels();
    if (lChannels > getChannelCount())
      lChannels = getChannelCount(); 
    if (lChannels > format.getChannels())
      lChannels = format.getChannels(); 
    for (int channel = 0; channel < lChannels; channel++) {
      float[] data = getChannel(channel);
      FloatSampleTools.byte2floatGeneric(buffer, srcByteOffset, format
          .getFrameSize(), data, dstSampleOffset, aSampleCount, format);
      srcByteOffset += format.getFrameSize() / format.getChannels();
    } 
    return aSampleCount;
  }
  
  public void reset() {
    init(0, 0, 1.0F, false);
  }
  
  public void reset(int newChannels, int newSampleCount, float newSampleRate) {
    init(newChannels, newSampleCount, newSampleRate, false);
  }
  
  public int getByteArrayBufferSize(AudioFormat format) {
    return getByteArrayBufferSize(format, getSampleCount());
  }
  
  public int getByteArrayBufferSize(AudioFormat format, int lenInSamples) {
    checkFormatSupported(format);
    return format.getFrameSize() * lenInSamples;
  }
  
  public int convertToByteArray(byte[] buffer, int offset, AudioFormat format) {
    return convertToByteArray(0, getSampleCount(), buffer, offset, format);
  }
  
  public FloatSampleBuffer(int channelCount, int sampleCount, float sampleRate) {
    this.lastConvertToByteArrayFormat = null;
    this.lastConvertToByteArrayFormatCode = 0;
    init(channelCount, sampleCount, sampleRate, true);
  }
  
  public int convertToByteArray(int readOffset, int lenInSamples, byte[] buffer, int writeOffset, AudioFormat format) {
    int byteCount = format.getFrameSize() * lenInSamples;
    if (writeOffset + byteCount > buffer.length)
      throw new IllegalArgumentException("FloatSampleBuffer.convertToByteArray: buffer too small."); 
    if (format != this.lastConvertToByteArrayFormat) {
      if (format.getSampleRate() != getSampleRate())
        throw new IllegalArgumentException("FloatSampleBuffer.convertToByteArray: different samplerates."); 
      if (format.getChannels() != getChannelCount())
        throw new IllegalArgumentException("FloatSampleBuffer.convertToByteArray: different channel count."); 
      this.lastConvertToByteArrayFormat = format;
      this.lastConvertToByteArrayFormatCode = FloatSampleTools.getFormatType(format);
    } 
    FloatSampleTools.float2byte(this.channels, readOffset, buffer, writeOffset, lenInSamples, this.lastConvertToByteArrayFormatCode, format
        
        .getChannels(), format.getFrameSize(), 
        getConvertDitherBits(this.lastConvertToByteArrayFormatCode));
    return byteCount;
  }
  
  public byte[] convertToByteArray(AudioFormat format) {
    byte[] res = new byte[getByteArrayBufferSize(format)];
    convertToByteArray(res, 0, format);
    return res;
  }
  
  public void changeSampleCount(int newSampleCount, boolean keepOldSamples) {
    int oldSampleCount = getSampleCount();
    if (oldSampleCount >= newSampleCount) {
      setSampleCountImpl(newSampleCount);
      return;
    } 
    if (this.channelCount == 1 || this.channelCount == 2) {
      float[] ch = getChannel(0);
      if (ch.length < newSampleCount) {
        float[] newCh = new float[newSampleCount];
        if (keepOldSamples && oldSampleCount > 0)
          System.arraycopy(ch, 0, newCh, 0, oldSampleCount); 
        this.channels[0] = newCh;
      } else if (keepOldSamples) {
        for (int i = oldSampleCount; i < newSampleCount; i++)
          ch[i] = 0.0F; 
      } 
      if (this.channelCount == 2) {
        ch = getChannel(1);
        if (ch.length < newSampleCount) {
          float[] newCh = new float[newSampleCount];
          if (keepOldSamples && oldSampleCount > 0)
            System.arraycopy(ch, 0, newCh, 0, oldSampleCount); 
          this.channels[1] = newCh;
        } else if (keepOldSamples) {
          for (int i = oldSampleCount; i < newSampleCount; i++)
            ch[i] = 0.0F; 
        } 
      } 
      setSampleCountImpl(newSampleCount);
      return;
    } 
    Object[] oldChannels = null;
    if (keepOldSamples)
      oldChannels = getAllChannels(); 
    init(getChannelCount(), newSampleCount, getSampleRate());
    if (keepOldSamples) {
      int copyCount = (newSampleCount < oldSampleCount) ? newSampleCount : oldSampleCount;
      for (int ch = 0; ch < this.channelCount; ch++) {
        float[] oldSamples = (float[])oldChannels[ch];
        float[] newSamples = (float[])this.channels[ch];
        if (oldSamples != newSamples)
          System.arraycopy(oldSamples, 0, newSamples, 0, copyCount); 
        if (oldSampleCount < newSampleCount)
          for (int i = oldSampleCount; i < newSampleCount; i++)
            newSamples[i] = 0.0F;  
      } 
    } 
  }
  
  public void makeSilence() {
    makeSilence(0, getSampleCount());
  }
  
  public void makeSilence(int offset, int count) {
    if (offset < 0 || count + offset > getSampleCount() || count < 0)
      throw new IllegalArgumentException("offset and/or sampleCount out of bounds"); 
    int localChannelCount = getChannelCount();
    for (int ch = 0; ch < localChannelCount; ch++)
      makeSilence(getChannel(ch), offset, count); 
  }
  
  public void makeSilence(int channel) {
    makeSilence(channel, 0, getSampleCount());
  }
  
  public void makeSilence(int channel, int offset, int count) {
    if (offset < 0 || count + offset > getSampleCount() || count < 0)
      throw new IllegalArgumentException("offset and/or sampleCount out of bounds"); 
    makeSilence(getChannel(channel), offset, count);
  }
  
  private void makeSilence(float[] samples, int offset, int count) {
    count += offset;
    for (int i = offset; i < count; i++)
      samples[i] = 0.0F; 
  }
  
  public void linearFade(float startVol, float endVol) {
    linearFade(startVol, endVol, 0, getSampleCount());
  }
  
  public void linearFade(float startVol, float endVol, int offset, int count) {
    for (int channel = 0; channel < getChannelCount(); channel++)
      linearFade(channel, startVol, endVol, offset, count); 
  }
  
  public void linearFade(int channel, float startVol, float endVol, int offset, int count) {
    if (count <= 0)
      return; 
    float end = (count + offset);
    float inc = (endVol - startVol) / count;
    float[] samples = getChannel(channel);
    float curr = startVol;
    for (int i = offset; i < end; i++) {
      samples[i] = samples[i] * curr;
      curr += inc;
    } 
  }
  
  public void addChannel(boolean silent) {
    insertChannel(getChannelCount(), silent);
  }
  
  public void insertChannel(int index, boolean silent) {
    insertChannel(index, silent, true);
  }
  
  public void insertChannel(int index, boolean silent, boolean lazy) {
    grow(this.channelCount + 1, true);
    int physSize = this.channels.length;
    int virtSize = this.channelCount;
    float[] newChannel = null;
    if (physSize > virtSize)
      for (int ch = virtSize; ch < physSize; ch++) {
        float[] thisChannel = (float[])this.channels[ch];
        if (thisChannel != null && ((lazy && thisChannel.length >= 
          getSampleCount()) || (!lazy && thisChannel.length == getSampleCount()))) {
          newChannel = thisChannel;
          this.channels[ch] = null;
          break;
        } 
      }  
    if (newChannel == null)
      newChannel = new float[getSampleCount()]; 
    for (int i = index; i < virtSize; i++)
      this.channels[i + 1] = this.channels[i]; 
    this.channels[index] = newChannel;
    setChannelCountImpl(this.channelCount + 1);
    if (silent)
      makeSilence(index); 
    grow(this.channelCount, lazy);
  }
  
  public void removeChannel(int channel) {
    removeChannel(channel, true);
  }
  
  public void removeChannel(int channel, boolean lazy) {
    float[] toBeDeleted = (float[])this.channels[channel];
    for (int i = channel; i < this.channelCount - 1; i++)
      this.channels[i] = this.channels[i + 1]; 
    if (!lazy) {
      grow(this.channelCount - 1, true);
    } else {
      this.channels[this.channelCount - 1] = toBeDeleted;
    } 
    setChannelCountImpl(this.channelCount - 1);
  }
  
  public void copyChannel(int sourceChannel, int targetChannel) {
    float[] source = getChannel(sourceChannel);
    float[] target = getChannel(targetChannel);
    System.arraycopy(source, 0, target, 0, getSampleCount());
  }
  
  public void copyChannel(int sourceChannel, int sourceOffset, int targetChannel, int targetOffset, int aSampleCount) {
    float[] source = getChannel(sourceChannel);
    float[] target = getChannel(targetChannel);
    System.arraycopy(source, sourceOffset, target, targetOffset, aSampleCount);
  }
  
  public void copy(int sourceIndex, int destIndex, int length) {
    int count = getChannelCount();
    for (int i = 0; i < count; i++)
      copy(i, sourceIndex, destIndex, length); 
  }
  
  public void copy(int channel, int sourceIndex, int destIndex, int length) {
    float[] data = getChannel(channel);
    int bufferCount = getSampleCount();
    if (sourceIndex + length > bufferCount || destIndex + length > bufferCount || sourceIndex < 0 || destIndex < 0 || length < 0)
      throw new IndexOutOfBoundsException("parameters exceed buffer size"); 
    System.arraycopy(data, sourceIndex, data, destIndex, length);
  }
  
  public void expandChannel(int targetChannelCount) {
    if (getChannelCount() != 1)
      throw new IllegalArgumentException("FloatSampleBuffer: can only expand channels for mono signals."); 
    for (int ch = 1; ch < targetChannelCount; ch++) {
      addChannel(false);
      copyChannel(0, ch);
    } 
  }
  
  public void mixDownChannels() {
    float[] firstChannel = getChannel(0);
    int localSampleCount = getSampleCount();
    for (int ch = getChannelCount() - 1; ch > 0; ch--) {
      float[] thisChannel = getChannel(ch);
      for (int i = 0; i < localSampleCount; i++)
        firstChannel[i] = firstChannel[i] + thisChannel[i]; 
      removeChannel(ch);
    } 
  }
  
  public void mix(FloatSampleBuffer source) {
    int count = getSampleCount();
    if (count > source.getSampleCount())
      count = source.getSampleCount(); 
    int localChannelCount = getChannelCount();
    if (localChannelCount > source.getChannelCount())
      localChannelCount = source.getChannelCount(); 
    for (int ch = 0; ch < localChannelCount; ch++) {
      float[] thisChannel = getChannel(ch);
      float[] otherChannel = source.getChannel(ch);
      for (int i = 0; i < count; i++)
        thisChannel[i] = thisChannel[i] + otherChannel[i]; 
    } 
  }
  
  public void mix(FloatSampleBuffer source, int sourceOffset, int thisOffset, int count) {
    int localChannelCount = getChannelCount();
    for (int ch = 0; ch < localChannelCount; ch++) {
      float[] thisChannel = getChannel(ch);
      float[] otherChannel = source.getChannel(ch);
      for (int i = 0; i < count; i++)
        thisChannel[i + thisOffset] = thisChannel[i + thisOffset] + otherChannel[i + sourceOffset]; 
    } 
  }
  
  public int copyTo(FloatSampleBuffer dest, int destOffset, int count) {
    return copyTo(0, dest, destOffset, count);
  }
  
  public int copyTo(int srcOffset, FloatSampleBuffer dest, int destOffset, int count) {
    if (srcOffset + count > getSampleCount())
      count = getSampleCount() - srcOffset; 
    if (count + destOffset > dest.getSampleCount())
      count = dest.getSampleCount() - destOffset; 
    int localChannelCount = getChannelCount();
    if (localChannelCount > dest.getChannelCount())
      localChannelCount = dest.getChannelCount(); 
    for (int ch = 0; ch < localChannelCount; ch++)
      System.arraycopy(getChannel(ch), srcOffset, dest.getChannel(ch), destOffset, count); 
    return count;
  }
  
  public void setSamplesFromBytes(byte[] input, int inByteOffset, AudioFormat format, int floatOffset, int frameCount) {
    if (floatOffset < 0 || frameCount < 0 || inByteOffset < 0)
      throw new IllegalArgumentException("FloatSampleBuffer.setSamplesFromBytes: negative inByteOffset, floatOffset, or frameCount"); 
    if (inByteOffset + frameCount * format.getFrameSize() > input.length)
      throw new IllegalArgumentException("FloatSampleBuffer.setSamplesFromBytes: input buffer too small."); 
    if (floatOffset + frameCount > getSampleCount())
      throw new IllegalArgumentException("FloatSampleBuffer.setSamplesFromBytes: frameCount too large"); 
    FloatSampleTools.byte2float(input, inByteOffset, this.channels, floatOffset, frameCount, format, false);
  }
  
  public int getChannelCount() {
    return this.channelCount;
  }
  
  public int getSampleCount() {
    return this.sampleCount;
  }
  
  public float getSampleRate() {
    return this.sampleRate;
  }
  
  protected void setChannelCountImpl(int newChannelCount) {
    if (this.channelCount != newChannelCount) {
      this.channelCount = newChannelCount;
      this.lastConvertToByteArrayFormat = null;
    } 
  }
  
  protected void setSampleCountImpl(int newSampleCount) {
    if (this.sampleCount != newSampleCount)
      this.sampleCount = newSampleCount; 
  }
  
  public void setSampleCount(int newSampleCount, boolean keepOldSamples) {
    changeSampleCount(newSampleCount, keepOldSamples);
  }
  
  public void setSampleRate(float sampleRate) {
    if (sampleRate <= 0.0F)
      throw new IllegalArgumentException("Invalid samplerate for FloatSampleBuffer."); 
    if (this.sampleRate != sampleRate) {
      this.sampleRate = sampleRate;
      this.lastConvertToByteArrayFormat = null;
    } 
  }
  
  public float[] getChannel(int channel) {
    if (channel >= this.channelCount)
      throw new IllegalArgumentException("FloatSampleBuffer: invalid channel number."); 
    return (float[])this.channels[channel];
  }
  
  public float[] setRawChannel(int channel, float[] data) {
    if (data == null)
      throw new IllegalArgumentException("cannot set a channel to a null array"); 
    float[] ret = getChannel(channel);
    this.channels[channel] = data;
    return ret;
  }
  
  public Object[] getAllChannels() {
    Object[] res = new Object[getChannelCount()];
    for (int ch = 0; ch < getChannelCount(); ch++)
      res[ch] = getChannel(ch); 
    return res;
  }
  
  public void setDitherBits(float ditherBits) {
    if (ditherBits <= 0.0F)
      throw new IllegalArgumentException("DitherBits must be greater than 0"); 
    this.ditherBits = ditherBits;
  }
  
  public float getDitherBits() {
    return this.ditherBits;
  }
  
  public void setDitherMode(int mode) {
    if (mode != 0 && mode != 1 && mode != 2)
      throw new IllegalArgumentException("Illegal DitherMode"); 
    this.ditherMode = mode;
  }
  
  public int getDitherMode() {
    return this.ditherMode;
  }
  
  protected float getConvertDitherBits(int newFormatType) {
    boolean doDither = false;
    switch (this.ditherMode) {
      case 0:
        doDither = ((this.originalFormatType & 0x7) > (newFormatType & 0x7));
        break;
      case 1:
        doDither = true;
        break;
      case 2:
        doDither = false;
        break;
    } 
    return doDither ? this.ditherBits : 0.0F;
  }
}
