package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.io.NumberInput;
import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

public class TextBuffer {
  static final char[] NO_CHARS = new char[0];
  
  static final int MIN_SEGMENT_LEN = 500;
  
  static final int MAX_SEGMENT_LEN = 65536;
  
  private final BufferRecycler _allocator;
  
  private char[] _inputBuffer;
  
  private int _inputStart;
  
  private int _inputLen;
  
  private ArrayList<char[]> _segments;
  
  private boolean _hasSegments;
  
  private int _segmentSize;
  
  private char[] _currentSegment;
  
  private int _currentSize;
  
  private String _resultString;
  
  private char[] _resultArray;
  
  public TextBuffer(BufferRecycler allocator) {
    this._allocator = allocator;
  }
  
  protected TextBuffer(BufferRecycler allocator, char[] initialSegment) {
    this(allocator);
    this._currentSegment = initialSegment;
    this._currentSize = initialSegment.length;
    this._inputStart = -1;
  }
  
  public static TextBuffer fromInitial(char[] initialSegment) {
    return new TextBuffer(null, initialSegment);
  }
  
  public void releaseBuffers() {
    this._inputStart = -1;
    this._currentSize = 0;
    this._inputLen = 0;
    this._inputBuffer = null;
    this._resultArray = null;
    if (this._hasSegments)
      clearSegments(); 
    if (this._allocator != null && 
      this._currentSegment != null) {
      char[] buf = this._currentSegment;
      this._currentSegment = null;
      this._allocator.releaseCharBuffer(2, buf);
    } 
  }
  
  public void resetWithEmpty() {
    this._inputStart = -1;
    this._currentSize = 0;
    this._inputLen = 0;
    this._inputBuffer = null;
    this._resultString = null;
    this._resultArray = null;
    if (this._hasSegments)
      clearSegments(); 
  }
  
  public void resetWith(char ch) {
    this._inputStart = -1;
    this._inputLen = 0;
    this._resultString = null;
    this._resultArray = null;
    if (this._hasSegments) {
      clearSegments();
    } else if (this._currentSegment == null) {
      this._currentSegment = buf(1);
    } 
    this._currentSegment[0] = ch;
    this._currentSize = this._segmentSize = 1;
  }
  
  public void resetWithShared(char[] buf, int offset, int len) {
    this._resultString = null;
    this._resultArray = null;
    this._inputBuffer = buf;
    this._inputStart = offset;
    this._inputLen = len;
    if (this._hasSegments)
      clearSegments(); 
  }
  
  public void resetWithCopy(char[] buf, int offset, int len) throws IOException {
    this._inputBuffer = null;
    this._inputStart = -1;
    this._inputLen = 0;
    this._resultString = null;
    this._resultArray = null;
    if (this._hasSegments) {
      clearSegments();
    } else if (this._currentSegment == null) {
      this._currentSegment = buf(len);
    } 
    this._currentSize = this._segmentSize = 0;
    append(buf, offset, len);
  }
  
  public void resetWithCopy(String text, int start, int len) throws IOException {
    this._inputBuffer = null;
    this._inputStart = -1;
    this._inputLen = 0;
    this._resultString = null;
    this._resultArray = null;
    if (this._hasSegments) {
      clearSegments();
    } else if (this._currentSegment == null) {
      this._currentSegment = buf(len);
    } 
    this._currentSize = this._segmentSize = 0;
    append(text, start, len);
  }
  
  public void resetWithString(String value) throws IOException {
    this._inputBuffer = null;
    this._inputStart = -1;
    this._inputLen = 0;
    validateStringLength(value.length());
    this._resultString = value;
    this._resultArray = null;
    if (this._hasSegments)
      clearSegments(); 
    this._currentSize = 0;
  }
  
  public char[] getBufferWithoutReset() {
    return this._currentSegment;
  }
  
  private char[] buf(int needed) {
    if (this._allocator != null)
      return this._allocator.allocCharBuffer(2, needed); 
    return new char[Math.max(needed, 500)];
  }
  
  private void clearSegments() {
    this._hasSegments = false;
    this._segments.clear();
    this._currentSize = this._segmentSize = 0;
  }
  
  public int size() {
    if (this._inputStart >= 0)
      return this._inputLen; 
    if (this._resultArray != null)
      return this._resultArray.length; 
    if (this._resultString != null)
      return this._resultString.length(); 
    return this._segmentSize + this._currentSize;
  }
  
  public int getTextOffset() {
    return (this._inputStart >= 0) ? this._inputStart : 0;
  }
  
  public boolean hasTextAsCharacters() {
    if (this._inputStart >= 0 || this._resultArray != null)
      return true; 
    if (this._resultString != null)
      return false; 
    return true;
  }
  
  public char[] getTextBuffer() throws IOException {
    if (this._inputStart >= 0)
      return this._inputBuffer; 
    if (this._resultArray != null)
      return this._resultArray; 
    if (this._resultString != null)
      return this._resultArray = this._resultString.toCharArray(); 
    if (!this._hasSegments)
      return (this._currentSegment == null) ? NO_CHARS : this._currentSegment; 
    return contentsAsArray();
  }
  
  public String contentsAsString() throws IOException {
    if (this._resultString == null)
      if (this._resultArray != null) {
        this._resultString = new String(this._resultArray);
      } else if (this._inputStart >= 0) {
        if (this._inputLen < 1)
          return this._resultString = ""; 
        validateStringLength(this._inputLen);
        this._resultString = new String(this._inputBuffer, this._inputStart, this._inputLen);
      } else {
        int segLen = this._segmentSize;
        int currLen = this._currentSize;
        if (segLen == 0) {
          if (currLen == 0) {
            this._resultString = "";
          } else {
            validateStringLength(currLen);
            this._resultString = new String(this._currentSegment, 0, currLen);
          } 
        } else {
          int builderLen = segLen + currLen;
          if (builderLen < 0)
            _reportBufferOverflow(segLen, currLen); 
          validateStringLength(builderLen);
          StringBuilder sb = new StringBuilder(builderLen);
          if (this._segments != null)
            for (int i = 0, len = this._segments.size(); i < len; i++) {
              char[] curr = this._segments.get(i);
              sb.append(curr, 0, curr.length);
            }  
          sb.append(this._currentSegment, 0, this._currentSize);
          this._resultString = sb.toString();
        } 
      }  
    return this._resultString;
  }
  
  public char[] contentsAsArray() throws IOException {
    char[] result = this._resultArray;
    if (result == null)
      this._resultArray = result = resultArray(); 
    return result;
  }
  
  public double contentsAsDouble(boolean useFastParser) throws NumberFormatException {
    try {
      return NumberInput.parseDouble(contentsAsString(), useFastParser);
    } catch (IOException e) {
      throw new NumberFormatException(e.getMessage());
    } 
  }
  
  @Deprecated
  public double contentsAsDouble() throws NumberFormatException {
    return contentsAsDouble(false);
  }
  
  @Deprecated
  public float contentsAsFloat() throws NumberFormatException {
    return contentsAsFloat(false);
  }
  
  public float contentsAsFloat(boolean useFastParser) throws NumberFormatException {
    try {
      return NumberInput.parseFloat(contentsAsString(), useFastParser);
    } catch (IOException e) {
      throw new NumberFormatException(e.getMessage());
    } 
  }
  
  @Deprecated
  public BigDecimal contentsAsDecimal() throws NumberFormatException {
    try {
      return NumberInput.parseBigDecimal(contentsAsArray());
    } catch (IOException e) {
      throw new NumberFormatException(e.getMessage());
    } 
  }
  
  public int contentsAsInt(boolean neg) {
    if (this._inputStart >= 0 && this._inputBuffer != null) {
      if (neg)
        return -NumberInput.parseInt(this._inputBuffer, this._inputStart + 1, this._inputLen - 1); 
      return NumberInput.parseInt(this._inputBuffer, this._inputStart, this._inputLen);
    } 
    if (neg)
      return -NumberInput.parseInt(this._currentSegment, 1, this._currentSize - 1); 
    return NumberInput.parseInt(this._currentSegment, 0, this._currentSize);
  }
  
  public long contentsAsLong(boolean neg) {
    if (this._inputStart >= 0 && this._inputBuffer != null) {
      if (neg)
        return -NumberInput.parseLong(this._inputBuffer, this._inputStart + 1, this._inputLen - 1); 
      return NumberInput.parseLong(this._inputBuffer, this._inputStart, this._inputLen);
    } 
    if (neg)
      return -NumberInput.parseLong(this._currentSegment, 1, this._currentSize - 1); 
    return NumberInput.parseLong(this._currentSegment, 0, this._currentSize);
  }
  
  public int contentsToWriter(Writer w) throws IOException {
    if (this._resultArray != null) {
      w.write(this._resultArray);
      return this._resultArray.length;
    } 
    if (this._resultString != null) {
      w.write(this._resultString);
      return this._resultString.length();
    } 
    if (this._inputStart >= 0) {
      int i = this._inputLen;
      if (i > 0)
        w.write(this._inputBuffer, this._inputStart, i); 
      return i;
    } 
    int total = 0;
    if (this._segments != null)
      for (int i = 0, end = this._segments.size(); i < end; i++) {
        char[] curr = this._segments.get(i);
        int currLen = curr.length;
        total += currLen;
        w.write(curr, 0, currLen);
      }  
    int len = this._currentSize;
    if (len > 0) {
      total += len;
      w.write(this._currentSegment, 0, len);
    } 
    return total;
  }
  
  public void ensureNotShared() {
    if (this._inputStart >= 0)
      unshare(16); 
  }
  
  public void append(char c) throws IOException {
    if (this._inputStart >= 0)
      unshare(16); 
    this._resultString = null;
    this._resultArray = null;
    char[] curr = this._currentSegment;
    if (this._currentSize >= curr.length) {
      validateAppend(1);
      expand();
      curr = this._currentSegment;
    } 
    curr[this._currentSize++] = c;
  }
  
  public void append(char[] c, int start, int len) throws IOException {
    if (this._inputStart >= 0)
      unshare(len); 
    this._resultString = null;
    this._resultArray = null;
    char[] curr = this._currentSegment;
    int max = curr.length - this._currentSize;
    if (max >= len) {
      System.arraycopy(c, start, curr, this._currentSize, len);
      this._currentSize += len;
      return;
    } 
    validateAppend(len);
    if (max > 0) {
      System.arraycopy(c, start, curr, this._currentSize, max);
      start += max;
      len -= max;
    } 
    do {
      expand();
      int amount = Math.min(this._currentSegment.length, len);
      System.arraycopy(c, start, this._currentSegment, 0, amount);
      this._currentSize += amount;
      start += amount;
      len -= amount;
    } while (len > 0);
  }
  
  public void append(String str, int offset, int len) throws IOException {
    if (this._inputStart >= 0)
      unshare(len); 
    this._resultString = null;
    this._resultArray = null;
    char[] curr = this._currentSegment;
    int max = curr.length - this._currentSize;
    if (max >= len) {
      str.getChars(offset, offset + len, curr, this._currentSize);
      this._currentSize += len;
      return;
    } 
    validateAppend(len);
    if (max > 0) {
      str.getChars(offset, offset + max, curr, this._currentSize);
      len -= max;
      offset += max;
    } 
    do {
      expand();
      int amount = Math.min(this._currentSegment.length, len);
      str.getChars(offset, offset + amount, this._currentSegment, 0);
      this._currentSize += amount;
      offset += amount;
      len -= amount;
    } while (len > 0);
  }
  
  private void validateAppend(int toAppend) throws IOException {
    int newTotalLength = this._segmentSize + this._currentSize + toAppend;
    if (newTotalLength < 0)
      newTotalLength = Integer.MAX_VALUE; 
    validateStringLength(newTotalLength);
  }
  
  public char[] getCurrentSegment() {
    if (this._inputStart >= 0) {
      unshare(1);
    } else {
      char[] curr = this._currentSegment;
      if (curr == null) {
        this._currentSegment = buf(0);
      } else if (this._currentSize >= curr.length) {
        expand();
      } 
    } 
    return this._currentSegment;
  }
  
  public char[] emptyAndGetCurrentSegment() {
    this._inputStart = -1;
    this._currentSize = 0;
    this._inputLen = 0;
    this._inputBuffer = null;
    this._resultString = null;
    this._resultArray = null;
    if (this._hasSegments)
      clearSegments(); 
    char[] curr = this._currentSegment;
    if (curr == null)
      this._currentSegment = curr = buf(0); 
    return curr;
  }
  
  public int getCurrentSegmentSize() {
    return this._currentSize;
  }
  
  public void setCurrentLength(int len) {
    this._currentSize = len;
  }
  
  public String setCurrentAndReturn(int len) throws IOException {
    this._currentSize = len;
    if (this._segmentSize > 0)
      return contentsAsString(); 
    int currLen = this._currentSize;
    validateStringLength(currLen);
    String str = (currLen == 0) ? "" : new String(this._currentSegment, 0, currLen);
    this._resultString = str;
    return str;
  }
  
  public char[] finishCurrentSegment() throws IOException {
    if (this._segments == null)
      this._segments = (ArrayList)new ArrayList<>(); 
    this._hasSegments = true;
    this._segments.add(this._currentSegment);
    int oldLen = this._currentSegment.length;
    this._segmentSize += oldLen;
    if (this._segmentSize < 0)
      _reportBufferOverflow(this._segmentSize - oldLen, oldLen); 
    this._currentSize = 0;
    validateStringLength(this._segmentSize);
    int newLen = oldLen + (oldLen >> 1);
    if (newLen < 500) {
      newLen = 500;
    } else if (newLen > 65536) {
      newLen = 65536;
    } 
    char[] curr = carr(newLen);
    this._currentSegment = curr;
    return curr;
  }
  
  public String finishAndReturn(int lastSegmentEnd, boolean trimTrailingSpaces) throws IOException {
    if (trimTrailingSpaces) {
      int ptr = lastSegmentEnd - 1;
      if (ptr < 0 || this._currentSegment[ptr] <= ' ')
        return _doTrim(ptr); 
    } 
    this._currentSize = lastSegmentEnd;
    return contentsAsString();
  }
  
  private String _doTrim(int ptr) throws IOException {
    while (true) {
      char[] curr = this._currentSegment;
      while (--ptr >= 0) {
        if (curr[ptr] > ' ') {
          this._currentSize = ptr + 1;
          return contentsAsString();
        } 
      } 
      if (this._segments == null || this._segments.isEmpty())
        break; 
      this._currentSegment = this._segments.remove(this._segments.size() - 1);
      ptr = this._currentSegment.length;
    } 
    this._currentSize = 0;
    this._hasSegments = false;
    return contentsAsString();
  }
  
  public char[] expandCurrentSegment() {
    char[] curr = this._currentSegment;
    int len = curr.length;
    int newLen = len + (len >> 1);
    if (newLen > 65536)
      newLen = len + (len >> 2); 
    return this._currentSegment = Arrays.copyOf(curr, newLen);
  }
  
  public char[] expandCurrentSegment(int minSize) {
    char[] curr = this._currentSegment;
    if (curr.length >= minSize)
      return curr; 
    this._currentSegment = curr = Arrays.copyOf(curr, minSize);
    return curr;
  }
  
  public String toString() {
    try {
      return contentsAsString();
    } catch (IOException e) {
      return "TextBuffer: Exception when reading contents";
    } 
  }
  
  private void unshare(int needExtra) {
    int sharedLen = this._inputLen;
    this._inputLen = 0;
    char[] inputBuf = this._inputBuffer;
    this._inputBuffer = null;
    int start = this._inputStart;
    this._inputStart = -1;
    int needed = sharedLen + needExtra;
    if (this._currentSegment == null || needed > this._currentSegment.length)
      this._currentSegment = buf(needed); 
    if (sharedLen > 0)
      System.arraycopy(inputBuf, start, this._currentSegment, 0, sharedLen); 
    this._segmentSize = 0;
    this._currentSize = sharedLen;
  }
  
  private void expand() {
    if (this._segments == null)
      this._segments = (ArrayList)new ArrayList<>(); 
    char[] curr = this._currentSegment;
    this._hasSegments = true;
    this._segments.add(curr);
    this._segmentSize += curr.length;
    if (this._segmentSize < 0)
      _reportBufferOverflow(this._segmentSize - curr.length, curr.length); 
    this._currentSize = 0;
    int oldLen = curr.length;
    int newLen = oldLen + (oldLen >> 1);
    if (newLen < 500) {
      newLen = 500;
    } else if (newLen > 65536) {
      newLen = 65536;
    } 
    this._currentSegment = carr(newLen);
  }
  
  private char[] resultArray() throws IOException {
    if (this._resultString != null)
      return this._resultString.toCharArray(); 
    if (this._inputStart >= 0) {
      int len = this._inputLen;
      if (len < 1)
        return NO_CHARS; 
      validateStringLength(len);
      int start = this._inputStart;
      if (start == 0)
        return Arrays.copyOf(this._inputBuffer, len); 
      return Arrays.copyOfRange(this._inputBuffer, start, start + len);
    } 
    int size = size();
    if (size < 1) {
      if (size < 0)
        _reportBufferOverflow(this._segmentSize, this._currentSize); 
      return NO_CHARS;
    } 
    validateStringLength(size);
    int offset = 0;
    char[] result = carr(size);
    if (this._segments != null)
      for (int i = 0, len = this._segments.size(); i < len; i++) {
        char[] curr = this._segments.get(i);
        int currLen = curr.length;
        System.arraycopy(curr, 0, result, offset, currLen);
        offset += currLen;
      }  
    System.arraycopy(this._currentSegment, 0, result, offset, this._currentSize);
    return result;
  }
  
  private char[] carr(int len) {
    return new char[len];
  }
  
  protected void _reportBufferOverflow(int prev, int curr) {
    long newSize = prev + curr;
    throw new IllegalStateException("TextBuffer overrun: size reached (" + newSize + ") exceeds maximum of " + 2147483647);
  }
  
  protected void validateStringLength(int length) throws IOException {}
}
