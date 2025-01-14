package org.tritonus.share.sampled.convert;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.spi.FormatConversionProvider;
import org.tritonus.share.TDebug;
import org.tritonus.share.sampled.AudioFormats;

public abstract class TFormatConversionProvider extends FormatConversionProvider {
  protected static final AudioFormat.Encoding[] EMPTY_ENCODING_ARRAY = new AudioFormat.Encoding[0];
  
  protected static final AudioFormat[] EMPTY_FORMAT_ARRAY = new AudioFormat[0];
  
  public AudioInputStream getAudioInputStream(AudioFormat.Encoding targetEncoding, AudioInputStream audioInputStream) {
    AudioFormat sourceFormat = audioInputStream.getFormat();
    AudioFormat targetFormat = new AudioFormat(targetEncoding, -1.0F, -1, -1, -1, -1.0F, sourceFormat.isBigEndian());
    if (TDebug.TraceAudioConverter) {
      TDebug.out("TFormatConversionProvider.getAudioInputStream(AudioFormat.Encoding, AudioInputStream):");
      TDebug.out("trying to convert to " + targetFormat);
    } 
    return getAudioInputStream(targetFormat, audioInputStream);
  }
  
  public boolean isConversionSupported(AudioFormat targetFormat, AudioFormat sourceFormat) {
    if (TDebug.TraceAudioConverter) {
      TDebug.out(">TFormatConversionProvider.isConversionSupported(AudioFormat, AudioFormat):");
      TDebug.out("class: " + getClass().getName());
      TDebug.out("checking if conversion possible");
      TDebug.out("from: " + sourceFormat);
      TDebug.out("to: " + targetFormat);
    } 
    AudioFormat[] aTargetFormats = getTargetFormats(targetFormat.getEncoding(), sourceFormat);
    for (int i = 0; i < aTargetFormats.length; i++) {
      if (TDebug.TraceAudioConverter)
        TDebug.out("checking against possible target format: " + aTargetFormats[i]); 
      if (aTargetFormats[i] != null && 
        AudioFormats.matches(aTargetFormats[i], targetFormat)) {
        if (TDebug.TraceAudioConverter)
          TDebug.out("<result=true"); 
        return true;
      } 
    } 
    if (TDebug.TraceAudioConverter)
      TDebug.out("<result=false"); 
    return false;
  }
  
  public AudioFormat getMatchingFormat(AudioFormat targetFormat, AudioFormat sourceFormat) {
    if (TDebug.TraceAudioConverter) {
      TDebug.out(">TFormatConversionProvider.isConversionSupported(AudioFormat, AudioFormat):");
      TDebug.out("class: " + getClass().getName());
      TDebug.out("checking if conversion possible");
      TDebug.out("from: " + sourceFormat);
      TDebug.out("to: " + targetFormat);
    } 
    AudioFormat[] aTargetFormats = getTargetFormats(targetFormat.getEncoding(), sourceFormat);
    for (int i = 0; i < aTargetFormats.length; i++) {
      if (TDebug.TraceAudioConverter)
        TDebug.out("checking against possible target format: " + aTargetFormats[i]); 
      if (aTargetFormats[i] != null && 
        AudioFormats.matches(aTargetFormats[i], targetFormat)) {
        if (TDebug.TraceAudioConverter)
          TDebug.out("<result=true"); 
        return aTargetFormats[i];
      } 
    } 
    if (TDebug.TraceAudioConverter)
      TDebug.out("<result=false"); 
    return null;
  }
}
