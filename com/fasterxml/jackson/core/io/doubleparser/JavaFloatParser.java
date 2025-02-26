package com.fasterxml.jackson.core.io.doubleparser;

public class JavaFloatParser {
  private static final JavaFloatBitsFromByteArray BYTE_ARRAY_PARSER = new JavaFloatBitsFromByteArray();
  
  private static final JavaFloatBitsFromCharArray CHAR_ARRAY_PARSER = new JavaFloatBitsFromCharArray();
  
  private static final JavaFloatBitsFromCharSequence CHAR_SEQUENCE_PARSER = new JavaFloatBitsFromCharSequence();
  
  public static float parseFloat(CharSequence str) throws NumberFormatException {
    return parseFloat(str, 0, str.length());
  }
  
  public static float parseFloat(CharSequence str, int offset, int length) throws NumberFormatException {
    long bitPattern = CHAR_SEQUENCE_PARSER.parseFloatingPointLiteral(str, offset, length);
    return Float.intBitsToFloat((int)bitPattern);
  }
  
  public static float parseFloat(byte[] str) throws NumberFormatException {
    return parseFloat(str, 0, str.length);
  }
  
  public static float parseFloat(byte[] str, int offset, int length) throws NumberFormatException {
    long bitPattern = BYTE_ARRAY_PARSER.parseFloatingPointLiteral(str, offset, length);
    return Float.intBitsToFloat((int)bitPattern);
  }
  
  public static float parseFloat(char[] str) throws NumberFormatException {
    return parseFloat(str, 0, str.length);
  }
  
  public static float parseFloat(char[] str, int offset, int length) throws NumberFormatException {
    long bitPattern = CHAR_ARRAY_PARSER.parseFloatingPointLiteral(str, offset, length);
    return Float.intBitsToFloat((int)bitPattern);
  }
}
