package javazoom.spi.mpeg.sampled.convert;

import java.util.Arrays;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import org.tritonus.share.TDebug;
import org.tritonus.share.sampled.Encodings;
import org.tritonus.share.sampled.convert.TEncodingFormatConversionProvider;

public class MpegFormatConversionProvider extends TEncodingFormatConversionProvider {
  private static final AudioFormat.Encoding MP3 = Encodings.getEncoding("MP3");
  
  private static final AudioFormat.Encoding PCM_SIGNED = Encodings.getEncoding("PCM_SIGNED");
  
  private static final AudioFormat[] INPUT_FORMATS = new AudioFormat[] { new AudioFormat(MP3, -1.0F, -1, 1, -1, -1.0F, false), new AudioFormat(MP3, -1.0F, -1, 1, -1, -1.0F, true), new AudioFormat(MP3, -1.0F, -1, 2, -1, -1.0F, false), new AudioFormat(MP3, -1.0F, -1, 2, -1, -1.0F, true) };
  
  private static final AudioFormat[] OUTPUT_FORMATS = new AudioFormat[] { new AudioFormat(PCM_SIGNED, -1.0F, 16, 1, 2, -1.0F, false), new AudioFormat(PCM_SIGNED, -1.0F, 16, 1, 2, -1.0F, true), new AudioFormat(PCM_SIGNED, -1.0F, 16, 2, 4, -1.0F, false), new AudioFormat(PCM_SIGNED, -1.0F, 16, 2, 4, -1.0F, true) };
  
  public MpegFormatConversionProvider() {
    super(Arrays.asList(INPUT_FORMATS), Arrays.asList(OUTPUT_FORMATS));
    if (TDebug.TraceAudioConverter)
      TDebug.out(">MpegFormatConversionProvider()"); 
  }
  
  public AudioInputStream getAudioInputStream(AudioFormat targetFormat, AudioInputStream audioInputStream) {
    if (TDebug.TraceAudioConverter)
      TDebug.out(">MpegFormatConversionProvider.getAudioInputStream(AudioFormat targetFormat, AudioInputStream audioInputStream):"); 
    return (AudioInputStream)new DecodedMpegAudioInputStream(targetFormat, audioInputStream);
  }
  
  public boolean isConversionSupported(AudioFormat targetFormat, AudioFormat sourceFormat) {
    if (TDebug.TraceAudioConverter) {
      TDebug.out(">MpegFormatConversionProvider.isConversionSupported(AudioFormat targetFormat, AudioFormat sourceFormat):");
      TDebug.out("checking if conversion possible");
      TDebug.out("from: " + sourceFormat);
      TDebug.out("to: " + targetFormat);
    } 
    boolean conversion = super.isConversionSupported(targetFormat, sourceFormat);
    if (!conversion) {
      AudioFormat.Encoding enc = sourceFormat.getEncoding();
      if (enc instanceof javazoom.spi.mpeg.sampled.file.MpegEncoding)
        if (sourceFormat.getFrameRate() != -1.0F || sourceFormat.getFrameSize() != -1)
          conversion = true;  
    } 
    return conversion;
  }
}
