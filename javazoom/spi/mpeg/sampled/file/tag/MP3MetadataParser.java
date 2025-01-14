package javazoom.spi.mpeg.sampled.file.tag;

public interface MP3MetadataParser {
  void addTagParseListener(TagParseListener paramTagParseListener);
  
  void removeTagParseListener(TagParseListener paramTagParseListener);
  
  MP3Tag[] getTags();
}
