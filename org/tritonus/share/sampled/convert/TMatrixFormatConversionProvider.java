package org.tritonus.share.sampled.convert;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.sound.sampled.AudioFormat;
import org.tritonus.share.ArraySet;
import org.tritonus.share.sampled.AudioFormats;

public abstract class TMatrixFormatConversionProvider extends TSimpleFormatConversionProvider {
  private Map m_targetEncodingsFromSourceFormat;
  
  private Map m_targetFormatsFromSourceFormat;
  
  protected TMatrixFormatConversionProvider(List<AudioFormat> sourceFormats, List<AudioFormat> targetFormats, boolean[][] abConversionPossible) {
    super(sourceFormats, targetFormats);
    this.m_targetEncodingsFromSourceFormat = new HashMap<Object, Object>();
    this.m_targetFormatsFromSourceFormat = new HashMap<Object, Object>();
    int nSourceFormat = 0;
    for (; nSourceFormat < sourceFormats.size(); 
      nSourceFormat++) {
      AudioFormat sourceFormat = sourceFormats.get(nSourceFormat);
      ArraySet<AudioFormat.Encoding> arraySet = new ArraySet();
      this.m_targetEncodingsFromSourceFormat.put(sourceFormat, arraySet);
      Map<Object, Object> targetFormatsFromTargetEncodings = new HashMap<Object, Object>();
      this.m_targetFormatsFromSourceFormat.put(sourceFormat, targetFormatsFromTargetEncodings);
      int nTargetFormat = 0;
      for (; nTargetFormat < targetFormats.size(); 
        nTargetFormat++) {
        AudioFormat targetFormat = targetFormats.get(nTargetFormat);
        if (abConversionPossible[nSourceFormat][nTargetFormat]) {
          ArraySet<AudioFormat> arraySet1;
          AudioFormat.Encoding targetEncoding = targetFormat.getEncoding();
          arraySet.add(targetEncoding);
          Collection supportedTargetFormats = (Collection)targetFormatsFromTargetEncodings.get(targetEncoding);
          if (supportedTargetFormats == null) {
            arraySet1 = new ArraySet();
            targetFormatsFromTargetEncodings.put(targetEncoding, arraySet1);
          } 
          arraySet1.add(targetFormat);
        } 
      } 
    } 
  }
  
  public AudioFormat.Encoding[] getTargetEncodings(AudioFormat sourceFormat) {
    Iterator<Map.Entry> iterator = this.m_targetEncodingsFromSourceFormat.entrySet().iterator();
    while (iterator.hasNext()) {
      Map.Entry entry = iterator.next();
      AudioFormat format = (AudioFormat)entry.getKey();
      if (AudioFormats.matches(format, sourceFormat)) {
        Collection targetEncodings = (Collection)entry.getValue();
        return (AudioFormat.Encoding[])targetEncodings.toArray((Object[])EMPTY_ENCODING_ARRAY);
      } 
    } 
    return EMPTY_ENCODING_ARRAY;
  }
  
  public AudioFormat[] getTargetFormats(AudioFormat.Encoding targetEncoding, AudioFormat sourceFormat) {
    Iterator<Map.Entry> iterator = this.m_targetFormatsFromSourceFormat.entrySet().iterator();
    while (iterator.hasNext()) {
      Map.Entry entry = iterator.next();
      AudioFormat format = (AudioFormat)entry.getKey();
      if (AudioFormats.matches(format, sourceFormat)) {
        Map targetEncodings = (Map)entry.getValue();
        Collection targetFormats = (Collection)targetEncodings.get(targetEncoding);
        if (targetFormats != null)
          return (AudioFormat[])targetFormats.toArray((Object[])EMPTY_FORMAT_ARRAY); 
        return EMPTY_FORMAT_ARRAY;
      } 
    } 
    return EMPTY_FORMAT_ARRAY;
  }
}
