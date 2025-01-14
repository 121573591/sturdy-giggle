package org.tritonus.share.sampled.convert;

import java.util.Collection;
import java.util.Iterator;
import javax.sound.sampled.AudioFormat;
import org.tritonus.share.ArraySet;
import org.tritonus.share.TDebug;

public abstract class TEncodingFormatConversionProvider extends TSimpleFormatConversionProvider {
  protected TEncodingFormatConversionProvider(Collection<AudioFormat> sourceFormats, Collection<AudioFormat> targetFormats) {
    super(sourceFormats, targetFormats);
  }
  
  public AudioFormat[] getTargetFormats(AudioFormat.Encoding targetEncoding, AudioFormat sourceFormat) {
    if (TDebug.TraceAudioConverter) {
      TDebug.out(">TEncodingFormatConversionProvider.getTargetFormats(AudioFormat.Encoding, AudioFormat):");
      TDebug.out("checking if conversion possible");
      TDebug.out("from: " + sourceFormat);
      TDebug.out("to: " + targetEncoding);
    } 
    if (isConversionSupported(targetEncoding, sourceFormat)) {
      ArraySet<AudioFormat> result = new ArraySet();
      Iterator<AudioFormat> iterator = getCollectionTargetFormats().iterator();
      while (iterator.hasNext()) {
        AudioFormat targetFormat = iterator.next();
        targetFormat = replaceNotSpecified(sourceFormat, targetFormat);
        result.add(targetFormat);
      } 
      if (TDebug.TraceAudioConverter)
        TDebug.out("< returning " + result.size() + " elements."); 
      return (AudioFormat[])result.toArray((Object[])EMPTY_FORMAT_ARRAY);
    } 
    if (TDebug.TraceAudioConverter)
      TDebug.out("< returning empty array."); 
    return EMPTY_FORMAT_ARRAY;
  }
}
