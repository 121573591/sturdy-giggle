package org.tritonus.share.sampled;

import java.util.List;
import java.util.Random;
import javax.sound.sampled.AudioFormat;

public class FloatSampleTools {
  public static final float DEFAULT_DITHER_BITS = 0.7F;
  
  private static Random random = null;
  
  static final int F_8 = 1;
  
  static final int F_16 = 2;
  
  static final int F_24_3 = 3;
  
  static final int F_24_4 = 4;
  
  static final int F_32 = 5;
  
  static final int F_SAMPLE_WIDTH_MASK = 7;
  
  static final int F_SIGNED = 8;
  
  static final int F_BIGENDIAN = 16;
  
  static final int CT_8S = 9;
  
  static final int CT_8U = 1;
  
  static final int CT_16SB = 26;
  
  static final int CT_16SL = 10;
  
  static final int CT_24_3SB = 27;
  
  static final int CT_24_3SL = 11;
  
  static final int CT_24_4SB = 28;
  
  static final int CT_24_4SL = 12;
  
  static final int CT_32SB = 29;
  
  static final int CT_32SL = 13;
  
  private static final float twoPower7 = 128.0F;
  
  private static final float twoPower15 = 32768.0F;
  
  private static final float twoPower23 = 8388608.0F;
  
  private static final float twoPower31 = 2.1474836E9F;
  
  private static final float invTwoPower7 = 0.0078125F;
  
  private static final float invTwoPower15 = 3.0517578E-5F;
  
  private static final float invTwoPower23 = 1.1920929E-7F;
  
  private static final float invTwoPower31 = 4.656613E-10F;
  
  static void checkSupportedSampleSize(int ssib, int channels, int frameSize) {
    if (ssib == 24 && frameSize == 4 * channels)
      return; 
    if (ssib * channels != frameSize * 8)
      throw new IllegalArgumentException("unsupported sample size: " + ssib + " bits stored in " + (frameSize / channels) + " bytes."); 
  }
  
  static int getFormatType(AudioFormat format) {
    boolean signed = format.getEncoding().equals(AudioFormat.Encoding.PCM_SIGNED);
    if (!signed && 
      !format.getEncoding().equals(AudioFormat.Encoding.PCM_UNSIGNED))
      throw new IllegalArgumentException("unsupported encoding: only PCM encoding supported."); 
    if (!signed && format.getSampleSizeInBits() != 8)
      throw new IllegalArgumentException("unsupported encoding: only 8-bit can be unsigned"); 
    checkSupportedSampleSize(format.getSampleSizeInBits(), format
        .getChannels(), format.getFrameSize());
    int formatType = getFormatType(format.getSampleSizeInBits(), format
        .getFrameSize() / format.getChannels(), signed, format
        .isBigEndian());
    return formatType;
  }
  
  static int getFormatType(int ssib, int bytesPerSample, boolean signed, boolean bigEndian) {
    int res = 0;
    if (ssib == 24 || bytesPerSample == ssib / 8)
      if (ssib == 8) {
        res = 1;
      } else if (ssib == 16) {
        res = 2;
      } else if (ssib == 24) {
        if (bytesPerSample == 3) {
          res = 3;
        } else if (bytesPerSample == 4) {
          res = 4;
        } 
      } else if (ssib == 32) {
        res = 5;
      }  
    if (res == 0)
      throw new IllegalArgumentException("ConversionTool: unsupported sample size of " + ssib + " bits per sample in " + bytesPerSample + " bytes."); 
    if (!signed && bytesPerSample > 1)
      throw new IllegalArgumentException("ConversionTool: unsigned samples larger than 8 bit are not supported"); 
    if (signed)
      res |= 0x8; 
    if (bigEndian && ssib != 8)
      res |= 0x10; 
    return res;
  }
  
  static int getSampleSize(int formatType) {
    switch (formatType & 0x7) {
      case 1:
        return 1;
      case 2:
        return 2;
      case 3:
        return 3;
      case 4:
        return 4;
      case 5:
        return 4;
    } 
    return 0;
  }
  
  static String formatType2Str(int formatType) {
    String res = "" + formatType + ": ";
    switch (formatType & 0x7) {
      case 1:
        res = res + "8bit";
        break;
      case 2:
        res = res + "16bit";
        break;
      case 3:
        res = res + "24_3bit";
        break;
      case 4:
        res = res + "24_4bit";
        break;
      case 5:
        res = res + "32bit";
        break;
    } 
    res = res + (((formatType & 0x8) == 8) ? " signed" : " unsigned");
    if ((formatType & 0x7) != 1)
      res = res + (((formatType & 0x10) == 16) ? " big endian" : " little endian"); 
    return res;
  }
  
  public static void byte2float(byte[] input, int inByteOffset, List<float[]> output, int outOffset, int frameCount, AudioFormat format) {
    byte2float(input, inByteOffset, output, outOffset, frameCount, format, true);
  }
  
  public static void byte2float(byte[] input, int inByteOffset, Object[] output, int outOffset, int frameCount, AudioFormat format) {
    byte2float(input, inByteOffset, output, outOffset, frameCount, format, true);
  }
  
  public static void byte2float(byte[] input, int inByteOffset, Object[] output, int outOffset, int frameCount, AudioFormat format, boolean allowAddChannel) {
    int channels = format.getChannels();
    if (!allowAddChannel && channels > output.length)
      channels = output.length; 
    if (output.length < channels)
      throw new ArrayIndexOutOfBoundsException("too few channel output array"); 
    for (int channel = 0; channel < channels; channel++) {
      float[] data = (float[])output[channel];
      if (data.length < frameCount + outOffset) {
        data = new float[frameCount + outOffset];
        output[channel] = data;
      } 
      byte2floatGeneric(input, inByteOffset, format.getFrameSize(), data, outOffset, frameCount, format);
      inByteOffset += format.getFrameSize() / format.getChannels();
    } 
  }
  
  public static void byte2float(byte[] input, int inByteOffset, List<float[]> output, int outOffset, int frameCount, AudioFormat format, boolean allowAddChannel) {
    int channels = format.getChannels();
    if (!allowAddChannel && channels > output.size())
      channels = output.size(); 
    for (int channel = 0; channel < channels; channel++) {
      float[] data;
      if (output.size() < channel) {
        data = new float[frameCount + outOffset];
        output.add(data);
      } else {
        data = output.get(channel);
        if (data.length < frameCount + outOffset) {
          data = new float[frameCount + outOffset];
          output.set(channel, data);
        } 
      } 
      byte2floatGeneric(input, inByteOffset, format.getFrameSize(), data, outOffset, frameCount, format);
      inByteOffset += format.getFrameSize() / format.getChannels();
    } 
  }
  
  public static void byte2float(int channel, byte[] input, int inByteOffset, float[] output, int outOffset, int frameCount, AudioFormat format) {
    if (channel >= format.getChannels())
      throw new IllegalArgumentException("channel out of bounds"); 
    if (output.length < frameCount + outOffset)
      throw new IllegalArgumentException("data is too small"); 
    inByteOffset += format.getFrameSize() / format.getChannels() * channel;
    byte2floatGeneric(input, inByteOffset, format.getFrameSize(), output, outOffset, frameCount, format);
  }
  
  public static void byte2floatInterleaved(byte[] input, int inByteOffset, float[] output, int outOffset, int frameCount, AudioFormat format) {
    byte2floatGeneric(input, inByteOffset, format.getFrameSize() / format
        .getChannels(), output, outOffset, frameCount * format
        .getChannels(), format);
  }
  
  static void byte2floatGeneric(byte[] input, int inByteOffset, int inByteStep, float[] output, int outOffset, int sampleCount, AudioFormat format) {
    int formatType = getFormatType(format);
    byte2floatGeneric(input, inByteOffset, inByteStep, output, outOffset, sampleCount, formatType);
  }
  
  static void byte2floatGeneric(byte[] input, int inByteOffset, int inByteStep, float[] output, int outOffset, int sampleCount, int formatType) {
    int endCount = outOffset + sampleCount;
    int inIndex = inByteOffset;
    for (int outIndex = outOffset; outIndex < endCount; outIndex++, inIndex += inByteStep) {
      switch (formatType) {
        case 9:
          output[outIndex] = input[inIndex] * 0.0078125F;
          break;
        case 1:
          output[outIndex] = ((input[inIndex] & 0xFF) - 128) * 0.0078125F;
          break;
        case 26:
          output[outIndex] = (input[inIndex] << 8 | input[inIndex + 1] & 0xFF) * 3.0517578E-5F;
          break;
        case 10:
          output[outIndex] = (input[inIndex + 1] << 8 | input[inIndex] & 0xFF) * 3.0517578E-5F;
          break;
        case 27:
          output[outIndex] = (input[inIndex] << 16 | (input[inIndex + 1] & 0xFF) << 8 | input[inIndex + 2] & 0xFF) * 1.1920929E-7F;
          break;
        case 11:
          output[outIndex] = (input[inIndex + 2] << 16 | (input[inIndex + 1] & 0xFF) << 8 | input[inIndex] & 0xFF) * 1.1920929E-7F;
          break;
        case 28:
          output[outIndex] = (input[inIndex + 1] << 16 | (input[inIndex + 2] & 0xFF) << 8 | input[inIndex + 3] & 0xFF) * 1.1920929E-7F;
          break;
        case 12:
          output[outIndex] = (input[inIndex + 3] << 16 | (input[inIndex + 2] & 0xFF) << 8 | input[inIndex + 1] & 0xFF) * 1.1920929E-7F;
          break;
        case 29:
          output[outIndex] = (input[inIndex] << 24 | (input[inIndex + 1] & 0xFF) << 16 | (input[inIndex + 2] & 0xFF) << 8 | input[inIndex + 3] & 0xFF) * 4.656613E-10F;
          break;
        case 13:
          output[outIndex] = (input[inIndex + 3] << 24 | (input[inIndex + 2] & 0xFF) << 16 | (input[inIndex + 1] & 0xFF) << 8 | input[inIndex] & 0xFF) * 4.656613E-10F;
          break;
        default:
          throw new IllegalArgumentException("unsupported format=" + 
              formatType2Str(formatType));
      } 
    } 
  }
  
  private static byte quantize8(float sample, float ditherBits) {
    if (ditherBits != 0.0F)
      sample += random.nextFloat() * ditherBits; 
    if (sample >= 127.0F)
      return Byte.MAX_VALUE; 
    if (sample <= -128.0F)
      return Byte.MIN_VALUE; 
    return (byte)(int)((sample < 0.0F) ? (sample - 0.5F) : (sample + 0.5F));
  }
  
  private static int quantize16(float sample, float ditherBits) {
    if (ditherBits != 0.0F)
      sample += random.nextFloat() * ditherBits; 
    if (sample >= 32767.0F)
      return 32767; 
    if (sample <= -32768.0F)
      return -32768; 
    return (int)((sample < 0.0F) ? (sample - 0.5F) : (sample + 0.5F));
  }
  
  private static int quantize24(float sample, float ditherBits) {
    if (ditherBits != 0.0F)
      sample += random.nextFloat() * ditherBits; 
    if (sample >= 8388607.0F)
      return 8388607; 
    if (sample <= -8388608.0F)
      return -8388608; 
    return (int)((sample < 0.0F) ? (sample - 0.5F) : (sample + 0.5F));
  }
  
  private static int quantize32(float sample, float ditherBits) {
    if (ditherBits != 0.0F)
      sample += random.nextFloat() * ditherBits; 
    if (sample >= 2.1474836E9F)
      return Integer.MAX_VALUE; 
    if (sample <= -2.1474836E9F)
      return Integer.MIN_VALUE; 
    return (int)((sample < 0.0F) ? (sample - 0.5F) : (sample + 0.5F));
  }
  
  public static void float2byte(List<float[]> input, int inOffset, byte[] output, int outByteOffset, int frameCount, AudioFormat format, float ditherBits) {
    for (int channel = 0; channel < format.getChannels(); channel++) {
      float[] data = input.get(channel);
      float2byteGeneric(data, inOffset, output, outByteOffset, format
          .getFrameSize(), frameCount, format, ditherBits);
      outByteOffset += format.getFrameSize() / format.getChannels();
    } 
  }
  
  public static void float2byte(Object[] input, int inOffset, byte[] output, int outByteOffset, int frameCount, AudioFormat format, float ditherBits) {
    int channels = format.getChannels();
    for (int channel = 0; channel < channels; channel++) {
      float[] data = (float[])input[channel];
      float2byteGeneric(data, inOffset, output, outByteOffset, format
          .getFrameSize(), frameCount, format, ditherBits);
      outByteOffset += format.getFrameSize() / format.getChannels();
    } 
  }
  
  static void float2byte(Object[] input, int inOffset, byte[] output, int outByteOffset, int frameCount, int formatCode, int channels, int frameSize, float ditherBits) {
    int sampleSize = frameSize / channels;
    for (int channel = 0; channel < channels; channel++) {
      float[] data = (float[])input[channel];
      float2byteGeneric(data, inOffset, output, outByteOffset, frameSize, frameCount, formatCode, ditherBits);
      outByteOffset += sampleSize;
    } 
  }
  
  public static void float2byteInterleaved(float[] input, int inOffset, byte[] output, int outByteOffset, int frameCount, AudioFormat format, float ditherBits) {
    float2byteGeneric(input, inOffset, output, outByteOffset, format
        .getFrameSize() / format.getChannels(), frameCount * format
        .getChannels(), format, ditherBits);
  }
  
  static void float2byteGeneric(float[] input, int inOffset, byte[] output, int outByteOffset, int outByteStep, int sampleCount, AudioFormat format, float ditherBits) {
    int formatType = getFormatType(format);
    float2byteGeneric(input, inOffset, output, outByteOffset, outByteStep, sampleCount, formatType, ditherBits);
  }
  
  static void float2byteGeneric(float[] input, int inOffset, byte[] output, int outByteOffset, int outByteStep, int sampleCount, int formatType, float ditherBits) {
    if (inOffset < 0 || inOffset + sampleCount > input.length || sampleCount < 0)
      throw new IllegalArgumentException("invalid input index: input.length=" + input.length + " inOffset=" + inOffset + " sampleCount=" + sampleCount); 
    if (outByteOffset < 0 || outByteOffset + sampleCount * outByteStep >= output.length + outByteStep || outByteStep < 
      
      getSampleSize(formatType))
      throw new IllegalArgumentException("invalid output index: output.length=" + output.length + " outByteOffset=" + outByteOffset + " outByteStep=" + outByteStep + " sampleCount=" + sampleCount + " format=" + 
          
          formatType2Str(formatType)); 
    if (ditherBits != 0.0F && random == null)
      random = new Random(); 
    int endSample = inOffset + sampleCount;
    int outIndex = outByteOffset;
    for (int inIndex = inOffset; inIndex < endSample; inIndex++, outIndex += outByteStep) {
      int iSample;
      switch (formatType) {
        case 9:
          output[outIndex] = quantize8(input[inIndex] * 128.0F, ditherBits);
          break;
        case 1:
          output[outIndex] = (byte)(quantize8(input[inIndex] * 128.0F, ditherBits) + 128);
          break;
        case 26:
          iSample = quantize16(input[inIndex] * 32768.0F, ditherBits);
          output[outIndex] = (byte)(iSample >> 8);
          output[outIndex + 1] = (byte)(iSample & 0xFF);
          break;
        case 10:
          iSample = quantize16(input[inIndex] * 32768.0F, ditherBits);
          output[outIndex + 1] = (byte)(iSample >> 8);
          output[outIndex] = (byte)(iSample & 0xFF);
          break;
        case 27:
          iSample = quantize24(input[inIndex] * 8388608.0F, ditherBits);
          output[outIndex] = (byte)(iSample >> 16);
          output[outIndex + 1] = (byte)(iSample >>> 8 & 0xFF);
          output[outIndex + 2] = (byte)(iSample & 0xFF);
          break;
        case 11:
          iSample = quantize24(input[inIndex] * 8388608.0F, ditherBits);
          output[outIndex + 2] = (byte)(iSample >> 16);
          output[outIndex + 1] = (byte)(iSample >>> 8 & 0xFF);
          output[outIndex] = (byte)(iSample & 0xFF);
          break;
        case 28:
          iSample = quantize24(input[inIndex] * 8388608.0F, ditherBits);
          output[outIndex + 0] = 0;
          output[outIndex + 1] = (byte)(iSample >> 16);
          output[outIndex + 2] = (byte)(iSample >>> 8 & 0xFF);
          output[outIndex + 3] = (byte)(iSample & 0xFF);
          break;
        case 12:
          iSample = quantize24(input[inIndex] * 8388608.0F, ditherBits);
          output[outIndex + 3] = (byte)(iSample >> 16);
          output[outIndex + 2] = (byte)(iSample >>> 8 & 0xFF);
          output[outIndex + 1] = (byte)(iSample & 0xFF);
          output[outIndex + 0] = 0;
          break;
        case 29:
          iSample = quantize32(input[inIndex] * 2.1474836E9F, ditherBits);
          output[outIndex] = (byte)(iSample >> 24);
          output[outIndex + 1] = (byte)(iSample >>> 16 & 0xFF);
          output[outIndex + 2] = (byte)(iSample >>> 8 & 0xFF);
          output[outIndex + 3] = (byte)(iSample & 0xFF);
          break;
        case 13:
          iSample = quantize32(input[inIndex] * 2.1474836E9F, ditherBits);
          output[outIndex + 3] = (byte)(iSample >> 24);
          output[outIndex + 2] = (byte)(iSample >>> 16 & 0xFF);
          output[outIndex + 1] = (byte)(iSample >>> 8 & 0xFF);
          output[outIndex] = (byte)(iSample & 0xFF);
          break;
        default:
          throw new IllegalArgumentException("unsupported format=" + 
              formatType2Str(formatType));
      } 
    } 
  }
}
