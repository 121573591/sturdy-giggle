package org.java_websocket.framing;

import org.java_websocket.enums.Opcode;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.exceptions.InvalidFrameException;

public abstract class ControlFrame extends FramedataImpl1 {
  public ControlFrame(Opcode opcode) {
    super(opcode);
  }
  
  public void isValid() throws InvalidDataException {
    if (!isFin())
      throw new InvalidFrameException("Control frame can't have fin==false set"); 
    if (isRSV1())
      throw new InvalidFrameException("Control frame can't have rsv1==true set"); 
    if (isRSV2())
      throw new InvalidFrameException("Control frame can't have rsv2==true set"); 
    if (isRSV3())
      throw new InvalidFrameException("Control frame can't have rsv3==true set"); 
  }
}
