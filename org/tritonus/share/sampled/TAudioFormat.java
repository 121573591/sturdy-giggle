package org.tritonus.share.sampled;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.sound.sampled.AudioFormat;

public class TAudioFormat extends AudioFormat {
  private Map<String, Object> m_properties;
  
  private Map<String, Object> m_unmodifiableProperties;
  
  public TAudioFormat(AudioFormat.Encoding encoding, float sampleRate, int sampleSizeInBits, int channels, int frameSize, float frameRate, boolean bigEndian, Map<String, Object> properties) {
    super(encoding, sampleRate, sampleSizeInBits, channels, frameSize, frameRate, bigEndian);
    initMaps(properties);
  }
  
  public TAudioFormat(AudioFormat format) {
    this(format.getEncoding(), format
        .getSampleRate(), format
        .getSampleSizeInBits(), format
        .getChannels(), format
        .getFrameSize(), format
        .getFrameRate(), format
        .isBigEndian(), format
        .properties());
  }
  
  public TAudioFormat(AudioFormat format, Map<String, Object> properties) {
    this(format);
    this.m_properties.putAll(properties);
  }
  
  public TAudioFormat(float sampleRate, int sampleSizeInBits, int channels, boolean signed, boolean bigEndian, Map<String, Object> properties) {
    super(sampleRate, sampleSizeInBits, channels, signed, bigEndian);
    initMaps(properties);
  }
  
  private void initMaps(Map<String, Object> properties) {
    this.m_properties = new HashMap<String, Object>();
    if (properties != null)
      this.m_properties.putAll(properties); 
    this.m_unmodifiableProperties = Collections.unmodifiableMap(this.m_properties);
  }
  
  public Map<String, Object> properties() {
    if (this.m_properties == null)
      initMaps(null); 
    return this.m_unmodifiableProperties;
  }
  
  public Object getProperty(String key) {
    if (this.m_properties == null)
      return null; 
    return this.m_properties.get(key);
  }
  
  protected void setProperty(String key, Object value) {
    if (this.m_properties == null)
      initMaps(null); 
    this.m_properties.put(key, value);
  }
}
