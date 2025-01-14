package com.fasterxml.jackson.core.async;

import java.io.IOException;

public interface ByteArrayFeeder extends NonBlockingInputFeeder {
  void feedInput(byte[] paramArrayOfbyte, int paramInt1, int paramInt2) throws IOException;
}
