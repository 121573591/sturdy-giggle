package org.tritonus.share.midi;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import org.tritonus.share.TDebug;

public class MidiUtils {
  public static int getUnsignedInteger(byte b) {
    return (b < 0) ? (b + 256) : b;
  }
  
  public static int get14bitValue(int nLSB, int nMSB) {
    return nLSB & 0x7F | (nMSB & 0x7F) << 7;
  }
  
  public static int get14bitMSB(int nValue) {
    return nValue >> 7 & 0x7F;
  }
  
  public static int get14bitLSB(int nValue) {
    return nValue & 0x7F;
  }
  
  public static byte[] getVariableLengthQuantity(long lValue) {
    ByteArrayOutputStream data = new ByteArrayOutputStream();
    try {
      writeVariableLengthQuantity(lValue, data);
    } catch (IOException e) {
      if (TDebug.TraceAllExceptions)
        TDebug.out(e); 
    } 
    return data.toByteArray();
  }
  
  public static int writeVariableLengthQuantity(long lValue, OutputStream outputStream) throws IOException {
    int nLength = 0;
    boolean bWritingStarted = false;
    int nByte = (int)(lValue >> 21L & 0x7FL);
    if (nByte != 0) {
      if (outputStream != null)
        outputStream.write(nByte | 0x80); 
      nLength++;
      bWritingStarted = true;
    } 
    nByte = (int)(lValue >> 14L & 0x7FL);
    if (nByte != 0 || bWritingStarted) {
      if (outputStream != null)
        outputStream.write(nByte | 0x80); 
      nLength++;
      bWritingStarted = true;
    } 
    nByte = (int)(lValue >> 7L & 0x7FL);
    if (nByte != 0 || bWritingStarted) {
      if (outputStream != null)
        outputStream.write(nByte | 0x80); 
      nLength++;
    } 
    nByte = (int)(lValue & 0x7FL);
    if (outputStream != null)
      outputStream.write(nByte); 
    nLength++;
    return nLength;
  }
}
