package org.tritonus.share.sampled.file;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;

public class TAudioFileFormat extends AudioFileFormat {
  private Map<String, Object> m_properties;
  
  private Map<String, Object> m_unmodifiableProperties;
  
  public TAudioFileFormat(AudioFileFormat.Type type, AudioFormat audioFormat, int nLengthInFrames, int nLengthInBytes) {
    super(type, nLengthInBytes, audioFormat, nLengthInFrames);
  }
  
  public TAudioFileFormat(AudioFileFormat.Type type, AudioFormat audioFormat, int nLengthInFrames, int nLengthInBytes, Map<String, Object> properties) {
    super(type, nLengthInBytes, audioFormat, nLengthInFrames);
    initMaps(properties);
  }
  
  private void initMaps(Map<String, Object> properties) {
    this.m_properties = new HashMap<String, Object>();
    this.m_properties.putAll(properties);
    this.m_unmodifiableProperties = Collections.unmodifiableMap(this.m_properties);
  }
  
  public Map<String, Object> properties() {
    return this.m_unmodifiableProperties;
  }
  
  protected void setProperty(String key, Object value) {
    this.m_properties.put(key, value);
  }
}
