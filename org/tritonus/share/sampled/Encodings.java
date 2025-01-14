package org.tritonus.share.sampled;

import java.util.Iterator;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import org.tritonus.share.StringHashedSet;

public class Encodings extends AudioFormat.Encoding {
  private static StringHashedSet<AudioFormat.Encoding> encodings = new StringHashedSet();
  
  static {
    encodings.add(AudioFormat.Encoding.PCM_SIGNED);
    encodings.add(AudioFormat.Encoding.PCM_UNSIGNED);
    encodings.add(AudioFormat.Encoding.ULAW);
    encodings.add(AudioFormat.Encoding.ALAW);
  }
  
  Encodings(String name) {
    super(name);
  }
  
  public static AudioFormat.Encoding getEncoding(String name) {
    AudioFormat.Encoding res = (AudioFormat.Encoding)encodings.get(name);
    if (res == null) {
      res = new Encodings(name);
      encodings.add(res);
    } 
    return res;
  }
  
  public static boolean equals(AudioFormat.Encoding e1, AudioFormat.Encoding e2) {
    return e2.toString().equals(e1.toString());
  }
  
  public static AudioFormat.Encoding[] getEncodings() {
    StringHashedSet<AudioFormat.Encoding> iteratedSources = new StringHashedSet();
    StringHashedSet<AudioFormat.Encoding> retrievedTargets = new StringHashedSet();
    Iterator<AudioFormat.Encoding> sourceFormats = encodings.iterator();
    while (sourceFormats.hasNext()) {
      AudioFormat.Encoding source = sourceFormats.next();
      iterateEncodings(source, iteratedSources, retrievedTargets);
    } 
    return (AudioFormat.Encoding[])retrievedTargets.toArray(
        (Object[])new AudioFormat.Encoding[retrievedTargets.size()]);
  }
  
  private static void iterateEncodings(AudioFormat.Encoding source, StringHashedSet<AudioFormat.Encoding> iteratedSources, StringHashedSet<AudioFormat.Encoding> retrievedTargets) {
    if (!iteratedSources.contains(source)) {
      iteratedSources.add(source);
      AudioFormat.Encoding[] targets = AudioSystem.getTargetEncodings(source);
      for (int i = 0; i < targets.length; i++) {
        AudioFormat.Encoding target = targets[i];
        if (retrievedTargets.add(target))
          iterateEncodings(target, iteratedSources, retrievedTargets); 
      } 
    } 
  }
}
