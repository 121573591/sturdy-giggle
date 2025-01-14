package com.fasterxml.jackson.core.io;

import java.util.Arrays;

public final class CharTypes {
  protected static final char[] HC = "0123456789ABCDEF".toCharArray();
  
  protected static final char[] HClower = "0123456789abcdef".toCharArray();
  
  protected static final byte[] HB;
  
  protected static final byte[] HBlower;
  
  protected static final int[] sInputCodes;
  
  protected static final int[] sInputCodesUTF8;
  
  protected static final int[] sInputCodesJsNames;
  
  protected static final int[] sInputCodesUtf8JsNames;
  
  protected static final int[] sInputCodesComment;
  
  protected static final int[] sInputCodesWS;
  
  protected static final int[] sOutputEscapes128;
  
  static {
    int len = HC.length;
    HB = new byte[len];
    HBlower = new byte[len];
    int k;
    for (k = 0; k < len; k++) {
      HB[k] = (byte)HC[k];
      HBlower[k] = (byte)HClower[k];
    } 
    int[] arrayOfInt1 = new int[256];
    for (k = 0; k < 32; k++)
      arrayOfInt1[k] = -1; 
    arrayOfInt1[34] = 1;
    arrayOfInt1[92] = 1;
    sInputCodes = arrayOfInt1;
    arrayOfInt1 = new int[sInputCodes.length];
    System.arraycopy(sInputCodes, 0, arrayOfInt1, 0, arrayOfInt1.length);
    for (int c = 128; c < 256; c++) {
      int code;
      if ((c & 0xE0) == 192) {
        code = 2;
      } else if ((c & 0xF0) == 224) {
        code = 3;
      } else if ((c & 0xF8) == 240) {
        code = 4;
      } else {
        code = -1;
      } 
      arrayOfInt1[c] = code;
    } 
    sInputCodesUTF8 = arrayOfInt1;
    arrayOfInt1 = new int[256];
    Arrays.fill(arrayOfInt1, -1);
    int j;
    for (j = 33; j < 256; j++) {
      if (Character.isJavaIdentifierPart((char)j))
        arrayOfInt1[j] = 0; 
    } 
    arrayOfInt1[64] = 0;
    arrayOfInt1[35] = 0;
    arrayOfInt1[42] = 0;
    arrayOfInt1[45] = 0;
    arrayOfInt1[43] = 0;
    sInputCodesJsNames = arrayOfInt1;
    arrayOfInt1 = new int[256];
    System.arraycopy(sInputCodesJsNames, 0, arrayOfInt1, 0, arrayOfInt1.length);
    Arrays.fill(arrayOfInt1, 128, 128, 0);
    sInputCodesUtf8JsNames = arrayOfInt1;
    int[] buf = new int[256];
    System.arraycopy(sInputCodesUTF8, 128, buf, 128, 128);
    Arrays.fill(buf, 0, 32, -1);
    buf[9] = 0;
    buf[10] = 10;
    buf[13] = 13;
    buf[42] = 42;
    sInputCodesComment = buf;
    buf = new int[256];
    System.arraycopy(sInputCodesUTF8, 128, buf, 128, 128);
    Arrays.fill(buf, 0, 32, -1);
    buf[32] = 1;
    buf[9] = 1;
    buf[10] = 10;
    buf[13] = 13;
    buf[47] = 47;
    buf[35] = 35;
    sInputCodesWS = buf;
    int[] table = new int[128];
    for (j = 0; j < 32; j++)
      table[j] = -1; 
    table[34] = 34;
    table[92] = 92;
    table[8] = 98;
    table[9] = 116;
    table[12] = 102;
    table[10] = 110;
    table[13] = 114;
    sOutputEscapes128 = table;
  }
  
  protected static final int[] sHexValues = new int[256];
  
  static {
    Arrays.fill(sHexValues, -1);
    int i;
    for (i = 0; i < 10; i++)
      sHexValues[48 + i] = i; 
    for (i = 0; i < 6; i++) {
      sHexValues[97 + i] = 10 + i;
      sHexValues[65 + i] = 10 + i;
    } 
  }
  
  public static int[] getInputCodeLatin1() {
    return sInputCodes;
  }
  
  public static int[] getInputCodeUtf8() {
    return sInputCodesUTF8;
  }
  
  public static int[] getInputCodeLatin1JsNames() {
    return sInputCodesJsNames;
  }
  
  public static int[] getInputCodeUtf8JsNames() {
    return sInputCodesUtf8JsNames;
  }
  
  public static int[] getInputCodeComment() {
    return sInputCodesComment;
  }
  
  public static int[] getInputCodeWS() {
    return sInputCodesWS;
  }
  
  public static int[] get7BitOutputEscapes() {
    return sOutputEscapes128;
  }
  
  public static int[] get7BitOutputEscapes(int quoteChar) {
    if (quoteChar == 34)
      return sOutputEscapes128; 
    return AltEscapes.instance.escapesFor(quoteChar);
  }
  
  public static int charToHex(int ch) {
    return sHexValues[ch & 0xFF];
  }
  
  public static char hexToChar(int ch) {
    return HC[ch];
  }
  
  public static void appendQuoted(StringBuilder sb, String content) {
    int[] escCodes = sOutputEscapes128;
    int escLen = escCodes.length;
    for (int i = 0, len = content.length(); i < len; i++) {
      char c = content.charAt(i);
      if (c >= escLen || escCodes[c] == 0) {
        sb.append(c);
      } else {
        sb.append('\\');
        int escCode = escCodes[c];
        if (escCode < 0) {
          sb.append('u');
          sb.append('0');
          sb.append('0');
          int value = c;
          sb.append(HC[value >> 4]);
          sb.append(HC[value & 0xF]);
        } else {
          sb.append((char)escCode);
        } 
      } 
    } 
  }
  
  @Deprecated
  public static char[] copyHexChars() {
    return copyHexChars(true);
  }
  
  public static char[] copyHexChars(boolean uppercase) {
    return uppercase ? (char[])HC.clone() : (char[])HClower.clone();
  }
  
  @Deprecated
  public static byte[] copyHexBytes() {
    return copyHexBytes(true);
  }
  
  public static byte[] copyHexBytes(boolean uppercase) {
    return uppercase ? (byte[])HB.clone() : (byte[])HBlower.clone();
  }
  
  private static class AltEscapes {
    public static final AltEscapes instance = new AltEscapes();
    
    private int[][] _altEscapes = new int[128][];
    
    public int[] escapesFor(int quoteChar) {
      int[] esc = this._altEscapes[quoteChar];
      if (esc == null) {
        esc = Arrays.copyOf(CharTypes.sOutputEscapes128, 128);
        if (esc[quoteChar] == 0)
          esc[quoteChar] = -1; 
        this._altEscapes[quoteChar] = esc;
      } 
      return esc;
    }
  }
}
