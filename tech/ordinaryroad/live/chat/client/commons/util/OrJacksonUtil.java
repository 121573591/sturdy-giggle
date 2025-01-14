package tech.ordinaryroad.live.chat.client.commons.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class OrJacksonUtil extends ObjectMapper {
  private static final OrJacksonUtil INSTANCE = new OrJacksonUtil() {
    
    };
  
  public static OrJacksonUtil getInstance() {
    return INSTANCE;
  }
}
