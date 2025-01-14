package org.tritonus.share.sampled;

import javax.sound.sampled.AudioFileFormat;
import org.tritonus.share.StringHashedSet;

public class AudioFileTypes extends AudioFileFormat.Type {
  private static StringHashedSet<AudioFileFormat.Type> types = new StringHashedSet();
  
  static {
    types.add(AudioFileFormat.Type.AIFF);
    types.add(AudioFileFormat.Type.AIFC);
    types.add(AudioFileFormat.Type.AU);
    types.add(AudioFileFormat.Type.SND);
    types.add(AudioFileFormat.Type.WAVE);
  }
  
  AudioFileTypes(String name, String ext) {
    super(name, ext);
  }
  
  public static AudioFileFormat.Type getType(String name) {
    return getType(name, null);
  }
  
  public static AudioFileFormat.Type getType(String name, String extension) {
    AudioFileFormat.Type res = (AudioFileFormat.Type)types.get(name);
    if (res == null) {
      if (extension == null)
        return null; 
      res = new AudioFileTypes(name, extension);
      types.add(res);
    } 
    return res;
  }
  
  public static boolean equals(AudioFileFormat.Type t1, AudioFileFormat.Type t2) {
    return t2.toString().equals(t1.toString());
  }
}
