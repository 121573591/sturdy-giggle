package javazoom.spi.mpeg.sampled.file.tag;

public abstract class MP3Tag {
  protected String name;
  
  protected Object value;
  
  public MP3Tag(String name, Object value) {
    this.name = name;
    this.value = value;
  }
  
  public String getName() {
    return this.name;
  }
  
  public Object getValue() {
    return this.value;
  }
  
  public String toString() {
    return getClass().getName() + " -- " + 
      
      getName() + ":" + 
      
      getValue().toString();
  }
}
