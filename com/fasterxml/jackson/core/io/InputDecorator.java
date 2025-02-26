package com.fasterxml.jackson.core.io;

import java.io.DataInput;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.Serializable;

public abstract class InputDecorator implements Serializable {
  private static final long serialVersionUID = 1L;
  
  public abstract InputStream decorate(IOContext paramIOContext, InputStream paramInputStream) throws IOException;
  
  public abstract InputStream decorate(IOContext paramIOContext, byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException;
  
  public DataInput decorate(IOContext ctxt, DataInput input) throws IOException {
    throw new UnsupportedOperationException();
  }
  
  public abstract Reader decorate(IOContext paramIOContext, Reader paramReader) throws IOException;
}
