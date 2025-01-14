package org.tritonus.share.sampled.convert;

import java.util.Collection;
import java.util.Iterator;
import javax.sound.sampled.AudioFormat;
import org.tritonus.share.ArraySet;
import org.tritonus.share.TDebug;
import org.tritonus.share.sampled.AudioFormats;

public abstract class TSimpleFormatConversionProvider extends TFormatConversionProvider {
  private Collection<AudioFormat.Encoding> m_sourceEncodings;
  
  private Collection<AudioFormat.Encoding> m_targetEncodings;
  
  private Collection<AudioFormat> m_sourceFormats;
  
  private Collection<AudioFormat> m_targetFormats;
  
  protected TSimpleFormatConversionProvider(Collection<AudioFormat> sourceFormats, Collection<AudioFormat> targetFormats) {
    ArraySet arraySet1, arraySet2;
    this.m_sourceEncodings = (Collection<AudioFormat.Encoding>)new ArraySet();
    this.m_targetEncodings = (Collection<AudioFormat.Encoding>)new ArraySet();
    if (sourceFormats == null)
      arraySet1 = new ArraySet(); 
    if (targetFormats == null)
      arraySet2 = new ArraySet(); 
    this.m_sourceFormats = (Collection<AudioFormat>)arraySet1;
    this.m_targetFormats = (Collection<AudioFormat>)arraySet2;
    collectEncodings(this.m_sourceFormats, this.m_sourceEncodings);
    collectEncodings(this.m_targetFormats, this.m_targetEncodings);
  }
  
  protected void disable() {
    if (TDebug.TraceAudioConverter)
      TDebug.out("TSimpleFormatConversionProvider.disable(): disabling " + getClass().getName()); 
    this.m_sourceEncodings = (Collection<AudioFormat.Encoding>)new ArraySet();
    this.m_targetEncodings = (Collection<AudioFormat.Encoding>)new ArraySet();
    this.m_sourceFormats = (Collection<AudioFormat>)new ArraySet();
    this.m_targetFormats = (Collection<AudioFormat>)new ArraySet();
  }
  
  private static void collectEncodings(Collection<AudioFormat> formats, Collection<AudioFormat.Encoding> encodings) {
    Iterator<AudioFormat> iterator = formats.iterator();
    while (iterator.hasNext()) {
      AudioFormat format = iterator.next();
      encodings.add(format.getEncoding());
    } 
  }
  
  public AudioFormat.Encoding[] getSourceEncodings() {
    return this.m_sourceEncodings.<AudioFormat.Encoding>toArray(EMPTY_ENCODING_ARRAY);
  }
  
  public AudioFormat.Encoding[] getTargetEncodings() {
    return this.m_targetEncodings.<AudioFormat.Encoding>toArray(EMPTY_ENCODING_ARRAY);
  }
  
  public boolean isSourceEncodingSupported(AudioFormat.Encoding sourceEncoding) {
    return this.m_sourceEncodings.contains(sourceEncoding);
  }
  
  public boolean isTargetEncodingSupported(AudioFormat.Encoding targetEncoding) {
    return this.m_targetEncodings.contains(targetEncoding);
  }
  
  public AudioFormat.Encoding[] getTargetEncodings(AudioFormat sourceFormat) {
    if (isAllowedSourceFormat(sourceFormat))
      return getTargetEncodings(); 
    return EMPTY_ENCODING_ARRAY;
  }
  
  public AudioFormat[] getTargetFormats(AudioFormat.Encoding targetEncoding, AudioFormat sourceFormat) {
    if (isConversionSupported(targetEncoding, sourceFormat))
      return this.m_targetFormats.<AudioFormat>toArray(EMPTY_FORMAT_ARRAY); 
    return EMPTY_FORMAT_ARRAY;
  }
  
  protected boolean isAllowedSourceEncoding(AudioFormat.Encoding sourceEncoding) {
    return this.m_sourceEncodings.contains(sourceEncoding);
  }
  
  protected boolean isAllowedTargetEncoding(AudioFormat.Encoding targetEncoding) {
    return this.m_targetEncodings.contains(targetEncoding);
  }
  
  protected boolean isAllowedSourceFormat(AudioFormat sourceFormat) {
    Iterator<AudioFormat> iterator = this.m_sourceFormats.iterator();
    while (iterator.hasNext()) {
      AudioFormat format = iterator.next();
      if (AudioFormats.matches(format, sourceFormat))
        return true; 
    } 
    return false;
  }
  
  protected boolean isAllowedTargetFormat(AudioFormat targetFormat) {
    Iterator<AudioFormat> iterator = this.m_targetFormats.iterator();
    while (iterator.hasNext()) {
      AudioFormat format = iterator.next();
      if (AudioFormats.matches(format, targetFormat))
        return true; 
    } 
    return false;
  }
  
  protected Collection<AudioFormat.Encoding> getCollectionSourceEncodings() {
    return this.m_sourceEncodings;
  }
  
  protected Collection<AudioFormat.Encoding> getCollectionTargetEncodings() {
    return this.m_targetEncodings;
  }
  
  protected Collection<AudioFormat> getCollectionSourceFormats() {
    return this.m_sourceFormats;
  }
  
  protected Collection<AudioFormat> getCollectionTargetFormats() {
    return this.m_targetFormats;
  }
  
  protected static boolean doMatch(int i1, int i2) {
    return (i1 == -1 || i2 == -1 || i1 == i2);
  }
  
  protected static boolean doMatch(float f1, float f2) {
    return (f1 == -1.0F || f2 == -1.0F || 
      
      Math.abs(f1 - f2) < 1.0E-9D);
  }
  
  protected AudioFormat replaceNotSpecified(AudioFormat sourceFormat, AudioFormat targetFormat) {
    boolean bSetSampleSize = false;
    boolean bSetChannels = false;
    boolean bSetSampleRate = false;
    boolean bSetFrameRate = false;
    if (targetFormat.getSampleSizeInBits() == -1 && sourceFormat
      .getSampleSizeInBits() != -1)
      bSetSampleSize = true; 
    if (targetFormat.getChannels() == -1 && sourceFormat
      .getChannels() != -1)
      bSetChannels = true; 
    if (targetFormat.getSampleRate() == -1.0F && sourceFormat
      .getSampleRate() != -1.0F)
      bSetSampleRate = true; 
    if (targetFormat.getFrameRate() == -1.0F && sourceFormat
      .getFrameRate() != -1.0F)
      bSetFrameRate = true; 
    if (bSetSampleSize || bSetChannels || bSetSampleRate || bSetFrameRate || (targetFormat
      .getFrameSize() == -1 && sourceFormat
      .getFrameSize() != -1)) {
      float sampleRate = bSetSampleRate ? sourceFormat.getSampleRate() : targetFormat.getSampleRate();
      float frameRate = bSetFrameRate ? sourceFormat.getFrameRate() : targetFormat.getFrameRate();
      int sampleSize = bSetSampleSize ? sourceFormat.getSampleSizeInBits() : targetFormat.getSampleSizeInBits();
      int channels = bSetChannels ? sourceFormat.getChannels() : targetFormat.getChannels();
      int frameSize = getFrameSize(targetFormat
          .getEncoding(), sampleRate, sampleSize, channels, frameRate, targetFormat
          
          .isBigEndian(), targetFormat
          .getFrameSize());
      targetFormat = new AudioFormat(targetFormat.getEncoding(), sampleRate, sampleSize, channels, frameSize, frameRate, targetFormat.isBigEndian());
    } 
    return targetFormat;
  }
  
  protected int getFrameSize(AudioFormat.Encoding encoding, float sampleRate, int sampleSize, int channels, float frameRate, boolean bigEndian, int oldFrameSize) {
    if (sampleSize == -1 || channels == -1)
      return -1; 
    return (sampleSize + 7) / 8 * channels;
  }
}
