package javazoom.spi.mpeg.sampled.file.tag;

import java.util.EventListener;

public interface TagParseListener extends EventListener {
  void tagParsed(TagParseEvent paramTagParseEvent);
}
