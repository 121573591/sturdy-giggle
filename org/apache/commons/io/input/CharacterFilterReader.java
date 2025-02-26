package org.apache.commons.io.input;

import java.io.Reader;
import java.util.function.IntPredicate;

public class CharacterFilterReader extends AbstractCharacterFilterReader {
  public CharacterFilterReader(Reader reader, int skip) {
    super(reader, c -> (c == skip));
  }
  
  public CharacterFilterReader(Reader reader, IntPredicate skip) {
    super(reader, skip);
  }
}
