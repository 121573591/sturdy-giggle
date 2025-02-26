package com.fasterxml.jackson.core;

import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.SerializedString;

public class JsonpCharacterEscapes extends CharacterEscapes {
  private static final long serialVersionUID = 1L;
  
  private static final int[] asciiEscapes = CharacterEscapes.standardAsciiEscapesForJSON();
  
  private static final SerializedString escapeFor2028 = new SerializedString("\\u2028");
  
  private static final SerializedString escapeFor2029 = new SerializedString("\\u2029");
  
  private static final JsonpCharacterEscapes sInstance = new JsonpCharacterEscapes();
  
  public static JsonpCharacterEscapes instance() {
    return sInstance;
  }
  
  public SerializableString getEscapeSequence(int ch) {
    switch (ch) {
      case 8232:
        return (SerializableString)escapeFor2028;
      case 8233:
        return (SerializableString)escapeFor2029;
    } 
    return null;
  }
  
  public int[] getEscapeCodesForAscii() {
    return asciiEscapes;
  }
}
