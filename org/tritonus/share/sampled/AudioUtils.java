package org.tritonus.share.sampled;

import java.nio.ByteOrder;
import java.util.Iterator;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Mixer;

public class AudioUtils {
  public static long getLengthInBytes(AudioInputStream audioInputStream) {
    return getLengthInBytes(audioInputStream.getFormat(), audioInputStream
        .getFrameLength());
  }
  
  public static long getLengthInBytes(AudioFormat audioFormat, long lLengthInFrames) {
    int nFrameSize = audioFormat.getFrameSize();
    if (lLengthInFrames >= 0L && nFrameSize >= 1)
      return lLengthInFrames * nFrameSize; 
    return -1L;
  }
  
  public static boolean containsFormat(AudioFormat sourceFormat, Iterator<AudioFormat> possibleFormats) {
    while (possibleFormats.hasNext()) {
      AudioFormat format = possibleFormats.next();
      if (AudioFormats.matches(format, sourceFormat))
        return true; 
    } 
    return false;
  }
  
  public static int getFrameSize(int channels, int sampleSizeInBits) {
    if (channels < 0 || sampleSizeInBits < 0)
      return -1; 
    return (sampleSizeInBits + 7) / 8 * channels;
  }
  
  public static long millis2Bytes(long ms, AudioFormat format) {
    return millis2Bytes(ms, format.getFrameRate(), format.getFrameSize());
  }
  
  public static long millis2Bytes(long ms, double frameRate, int frameSize) {
    return (long)(ms * frameRate / 1000.0D * frameSize);
  }
  
  public static int millis2Bytes(int ms, AudioFormat format) {
    return millis2Bytes(ms, format.getFrameRate(), format.getFrameSize());
  }
  
  public static int millis2Bytes(int ms, double frameRate, int frameSize) {
    return (int)(ms * frameRate / 1000.0D * frameSize);
  }
  
  public static long millis2Bytes(double ms, AudioFormat format) {
    return millis2Bytes(ms, format.getFrameRate(), format.getFrameSize());
  }
  
  public static long millis2Bytes(double ms, double frameRate, int frameSize) {
    return (long)(ms * frameRate / 1000.0D * frameSize);
  }
  
  public static long millis2BytesFrameAligned(long ms, AudioFormat format) {
    return millis2BytesFrameAligned(ms, format.getFrameRate(), format.getFrameSize());
  }
  
  public static long millis2BytesFrameAligned(long ms, double frameRate, int frameSize) {
    return (long)(ms * frameRate / 1000.0D) * frameSize;
  }
  
  public static int millis2BytesFrameAligned(int ms, AudioFormat format) {
    return millis2BytesFrameAligned(ms, format.getFrameRate(), format.getFrameSize());
  }
  
  public static int millis2BytesFrameAligned(int ms, double frameRate, int frameSize) {
    return (int)(ms * frameRate / 1000.0D) * frameSize;
  }
  
  public static long millis2BytesFrameAligned(double ms, AudioFormat format) {
    return millis2BytesFrameAligned(ms, format.getFrameRate(), format.getFrameSize());
  }
  
  public static long millis2BytesFrameAligned(double ms, double frameRate, int frameSize) {
    return (long)(ms * frameRate / 1000.0D) * frameSize;
  }
  
  public static long millis2Frames(long ms, AudioFormat format) {
    return millis2Frames(ms, format.getFrameRate());
  }
  
  public static long millis2Frames(long ms, double frameRate) {
    return (long)(ms * frameRate / 1000.0D);
  }
  
  public static int millis2Frames(int ms, AudioFormat format) {
    return millis2Frames(ms, format.getFrameRate());
  }
  
  public static int millis2Frames(int ms, double frameRate) {
    return (int)(ms * frameRate / 1000.0D);
  }
  
  public static long millis2Frames(double ms, AudioFormat format) {
    return (long)millis2FramesD(ms, format.getFrameRate());
  }
  
  public static long millis2Frames(double ms, double frameRate) {
    return (long)millis2FramesD(ms, frameRate);
  }
  
  public static double millis2FramesD(double ms, AudioFormat format) {
    return millis2FramesD(ms, format.getFrameRate());
  }
  
  public static double millis2FramesD(double ms, double frameRate) {
    return ms * frameRate / 1000.0D;
  }
  
  public static long bytes2Millis(long bytes, AudioFormat format) {
    return (long)frames2MillisD(bytes / format.getFrameSize(), format.getFrameRate());
  }
  
  public static int bytes2Millis(int bytes, AudioFormat format) {
    return (int)frames2MillisD((bytes / format.getFrameSize()), format.getFrameRate());
  }
  
  public static double bytes2MillisD(long bytes, AudioFormat format) {
    return frames2MillisD(bytes / format.getFrameSize(), format.getFrameRate());
  }
  
  public static double bytes2MillisD(long bytes, double frameRate, int frameSize) {
    return frames2MillisD(bytes / frameSize, frameRate);
  }
  
  public static long frames2Millis(long frames, AudioFormat format) {
    return (long)frames2MillisD(frames, format.getFrameRate());
  }
  
  public static int frames2Millis(int frames, AudioFormat format) {
    return (int)frames2MillisD(frames, format.getFrameRate());
  }
  
  public static double frames2MillisD(long frames, AudioFormat format) {
    return frames2MillisD(frames, format.getFrameRate());
  }
  
  public static double frames2MillisD(long frames, double frameRate) {
    return frames / frameRate * 1000.0D;
  }
  
  public static boolean sampleRateEquals(float sr1, float sr2) {
    return (Math.abs(sr1 - sr2) < 1.0E-7D);
  }
  
  public static boolean isPCM(AudioFormat format) {
    return (format.getEncoding().equals(AudioFormat.Encoding.PCM_SIGNED) || format
      .getEncoding().equals(AudioFormat.Encoding.PCM_UNSIGNED));
  }
  
  public static boolean isJavaSoundAudioEngine(Mixer.Info mixerInfo) {
    return (mixerInfo != null && mixerInfo.getName() != null && mixerInfo
      .getName().equals("Java Sound Audio Engine"));
  }
  
  public static boolean isJavaSoundAudioEngine(DataLine line) {
    if (line != null) {
      String clazz = line.getClass().toString();
      return (clazz.indexOf("MixerSourceLine") >= 0 || clazz
        .indexOf("MixerClip") >= 0 || clazz
        .indexOf("SimpleInputDevice") >= 0);
    } 
    return false;
  }
  
  public static boolean isSystemBigEndian() {
    return ByteOrder.nativeOrder().equals(ByteOrder.BIG_ENDIAN);
  }
  
  public static String NS_or_number(int number) {
    return (number == -1) ? "NOT_SPECIFIED" : String.valueOf(number);
  }
  
  public static String NS_or_number(float number) {
    return (number == -1.0F) ? "NOT_SPECIFIED" : String.valueOf(number);
  }
  
  public static String format2ShortStr(AudioFormat format) {
    return format.getEncoding() + "-" + 
      NS_or_number(format.getChannels()) + "ch-" + 
      NS_or_number(format.getSampleSizeInBits()) + "bit-" + 
      NS_or_number((int)format.getSampleRate()) + "Hz-" + (
      format.isBigEndian() ? "be" : "le");
  }
  
  public static double SILENCE_DECIBEL = -100.0D;
  
  public static final double linear2decibel(double linearFactor) {
    if (linearFactor <= 0.0D)
      return SILENCE_DECIBEL; 
    double ret = Math.log10(linearFactor) * 20.0D;
    if (ret < SILENCE_DECIBEL)
      ret = SILENCE_DECIBEL; 
    return ret;
  }
  
  public static final double decibel2linear(double decibels) {
    if (decibels <= SILENCE_DECIBEL)
      return 0.0D; 
    return Math.pow(10.0D, decibels * 0.05D);
  }
}
