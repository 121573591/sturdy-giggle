package org.java_websocket.extensions;

import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.exceptions.InvalidFrameException;
import org.java_websocket.framing.Framedata;

public abstract class CompressionExtension extends DefaultExtension {
  public void isFrameValid(Framedata inputFrame) throws InvalidDataException {
    if (inputFrame instanceof org.java_websocket.framing.DataFrame && (inputFrame.isRSV2() || inputFrame.isRSV3()))
      throw new InvalidFrameException("bad rsv RSV1: " + inputFrame
          .isRSV1() + " RSV2: " + inputFrame.isRSV2() + " RSV3: " + inputFrame
          .isRSV3()); 
    if (inputFrame instanceof org.java_websocket.framing.ControlFrame && (inputFrame.isRSV1() || inputFrame.isRSV2() || inputFrame
      .isRSV3()))
      throw new InvalidFrameException("bad rsv RSV1: " + inputFrame
          .isRSV1() + " RSV2: " + inputFrame.isRSV2() + " RSV3: " + inputFrame
          .isRSV3()); 
  }
}
