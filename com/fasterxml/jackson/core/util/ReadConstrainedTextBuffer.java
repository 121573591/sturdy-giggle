package com.fasterxml.jackson.core.util;

import com.fasterxml.jackson.core.StreamReadConstraints;
import com.fasterxml.jackson.core.exc.StreamConstraintsException;

public final class ReadConstrainedTextBuffer extends TextBuffer {
  private final StreamReadConstraints _streamReadConstraints;
  
  public ReadConstrainedTextBuffer(StreamReadConstraints streamReadConstraints, BufferRecycler bufferRecycler) {
    super(bufferRecycler);
    this._streamReadConstraints = streamReadConstraints;
  }
  
  protected void validateStringLength(int length) throws StreamConstraintsException {
    this._streamReadConstraints.validateStringLength(length);
  }
}
