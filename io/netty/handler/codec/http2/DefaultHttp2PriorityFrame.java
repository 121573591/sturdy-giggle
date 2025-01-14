package io.netty.handler.codec.http2;

public final class DefaultHttp2PriorityFrame extends AbstractHttp2StreamFrame implements Http2PriorityFrame {
  private final int streamDependency;
  
  private final short weight;
  
  private final boolean exclusive;
  
  public DefaultHttp2PriorityFrame(int streamDependency, short weight, boolean exclusive) {
    this.streamDependency = streamDependency;
    this.weight = weight;
    this.exclusive = exclusive;
  }
  
  public int streamDependency() {
    return this.streamDependency;
  }
  
  public short weight() {
    return this.weight;
  }
  
  public boolean exclusive() {
    return this.exclusive;
  }
  
  public DefaultHttp2PriorityFrame stream(Http2FrameStream stream) {
    super.stream(stream);
    return this;
  }
  
  public String name() {
    return "PRIORITY_FRAME";
  }
  
  public boolean equals(Object o) {
    if (!(o instanceof DefaultHttp2PriorityFrame))
      return false; 
    DefaultHttp2PriorityFrame other = (DefaultHttp2PriorityFrame)o;
    boolean same = super.equals(other);
    return (same && this.streamDependency == other.streamDependency && this.weight == other.weight && this.exclusive == other.exclusive);
  }
  
  public int hashCode() {
    int hash = super.hashCode();
    hash = hash * 31 + this.streamDependency;
    hash = hash * 31 + this.weight;
    hash = hash * 31 + (this.exclusive ? 1 : 0);
    return hash;
  }
  
  public String toString() {
    return "DefaultHttp2PriorityFrame(stream=" + 
      stream() + ", streamDependency=" + this.streamDependency + ", weight=" + this.weight + ", exclusive=" + this.exclusive + ')';
  }
}
