package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

@JacksonStdImpl
public class DateSerializer extends DateTimeSerializerBase<Date> {
  public static final DateSerializer instance = new DateSerializer();
  
  public DateSerializer() {
    this((Boolean)null, (DateFormat)null);
  }
  
  public DateSerializer(Boolean useTimestamp, DateFormat customFormat) {
    super(Date.class, useTimestamp, customFormat);
  }
  
  public DateSerializer withFormat(Boolean timestamp, DateFormat customFormat) {
    return new DateSerializer(timestamp, customFormat);
  }
  
  protected long _timestamp(Date value) {
    return (value == null) ? 0L : value.getTime();
  }
  
  public void serialize(Date value, JsonGenerator g, SerializerProvider provider) throws IOException {
    if (_asTimestamp(provider)) {
      g.writeNumber(_timestamp(value));
      return;
    } 
    _serializeAsString(value, g, provider);
  }
}
