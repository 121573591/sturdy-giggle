package org.tritonus.share.sampled;

import java.util.Collection;
import java.util.Iterator;
import javax.sound.sampled.AudioFormat;
import org.tritonus.share.ArraySet;

public class AudioFormatSet extends ArraySet<AudioFormat> {
  private static final long serialVersionUID = 1L;
  
  protected static final AudioFormat[] EMPTY_FORMAT_ARRAY = new AudioFormat[0];
  
  public AudioFormatSet() {}
  
  public AudioFormatSet(Collection<AudioFormat> c) {
    super(c);
  }
  
  public boolean add(AudioFormat elem) {
    if (elem == null)
      return false; 
    return super.add(elem);
  }
  
  public boolean contains(AudioFormat elem) {
    if (elem == null)
      return false; 
    AudioFormat comp = elem;
    Iterator<AudioFormat> it = iterator();
    while (it.hasNext()) {
      if (AudioFormats.equals(comp, it.next()))
        return true; 
    } 
    return false;
  }
  
  public AudioFormat get(AudioFormat elem) {
    if (elem == null)
      return null; 
    AudioFormat comp = elem;
    Iterator<AudioFormat> it = iterator();
    while (it.hasNext()) {
      AudioFormat thisElem = it.next();
      if (AudioFormats.equals(comp, thisElem))
        return thisElem; 
    } 
    return null;
  }
  
  public AudioFormat getAudioFormat(AudioFormat elem) {
    return get(elem);
  }
  
  public AudioFormat matches(AudioFormat elem) {
    if (elem == null)
      return null; 
    Iterator<AudioFormat> it = iterator();
    while (it.hasNext()) {
      AudioFormat thisElem = it.next();
      if (AudioFormats.matches(elem, thisElem))
        return thisElem; 
    } 
    return null;
  }
  
  public AudioFormat[] toAudioFormatArray() {
    return (AudioFormat[])toArray((Object[])EMPTY_FORMAT_ARRAY);
  }
  
  public void add(int index, AudioFormat element) {
    throw new UnsupportedOperationException("unsupported");
  }
  
  public AudioFormat set(int index, AudioFormat element) {
    throw new UnsupportedOperationException("unsupported");
  }
}
