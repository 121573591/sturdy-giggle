package org.tritonus.share.sampled.convert;

import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;

public class TAudioInputStream extends AudioInputStream {
  private Map<String, Object> m_properties;
  
  private Map<String, Object> m_unmodifiableProperties;
  
  public TAudioInputStream(InputStream inputStream, AudioFormat audioFormat, long lLengthInFrames) {
    super(inputStream, audioFormat, lLengthInFrames);
    initMaps(new HashMap<String, Object>());
  }
  
  public TAudioInputStream(InputStream inputStream, AudioFormat audioFormat, long lLengthInFrames, Map<String, Object> properties) {
    super(inputStream, audioFormat, lLengthInFrames);
    initMaps(properties);
  }
  
  private void initMaps(Map<String, Object> properties) {
    this.m_properties = properties;
    this.m_unmodifiableProperties = Collections.unmodifiableMap(this.m_properties);
  }
  
  public Map<String, Object> properties() {
    return this.m_unmodifiableProperties;
  }
  
  protected void setProperty(String key, Object value) {
    this.m_properties.put(key, value);
  }
}
