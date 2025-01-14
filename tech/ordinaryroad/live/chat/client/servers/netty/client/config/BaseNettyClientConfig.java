package tech.ordinaryroad.live.chat.client.servers.netty.client.config;

import tech.ordinaryroad.live.chat.client.commons.client.config.BaseLiveChatClientConfig;

public abstract class BaseNettyClientConfig extends BaseLiveChatClientConfig {
  private int aggregatorMaxContentLength;
  
  private int maxFramePayloadLength;
  
  public void setAggregatorMaxContentLength(int aggregatorMaxContentLength) {
    this.aggregatorMaxContentLength = aggregatorMaxContentLength;
  }
  
  public void setMaxFramePayloadLength(int maxFramePayloadLength) {
    this.maxFramePayloadLength = maxFramePayloadLength;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof BaseNettyClientConfig))
      return false; 
    BaseNettyClientConfig other = (BaseNettyClientConfig)o;
    return !other.canEqual(this) ? false : ((getAggregatorMaxContentLength() != other.getAggregatorMaxContentLength()) ? false : (!(getMaxFramePayloadLength() != other.getMaxFramePayloadLength())));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof BaseNettyClientConfig;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    result = result * 59 + getAggregatorMaxContentLength();
    return result * 59 + getMaxFramePayloadLength();
  }
  
  public String toString() {
    return "BaseNettyClientConfig(aggregatorMaxContentLength=" + getAggregatorMaxContentLength() + ", maxFramePayloadLength=" + getMaxFramePayloadLength() + ")";
  }
  
  public BaseNettyClientConfig() {
    this.aggregatorMaxContentLength = $default$aggregatorMaxContentLength();
    this.maxFramePayloadLength = $default$maxFramePayloadLength();
  }
  
  public BaseNettyClientConfig(int aggregatorMaxContentLength, int maxFramePayloadLength) {
    this.aggregatorMaxContentLength = aggregatorMaxContentLength;
    this.maxFramePayloadLength = maxFramePayloadLength;
  }
  
  private static int $default$aggregatorMaxContentLength() {
    return 65536;
  }
  
  private static int $default$maxFramePayloadLength() {
    return 65536;
  }
  
  protected BaseNettyClientConfig(BaseNettyClientConfigBuilder<?, ?> b) {
    super(b);
    if (b.aggregatorMaxContentLength$set) {
      this.aggregatorMaxContentLength = b.aggregatorMaxContentLength$value;
    } else {
      this.aggregatorMaxContentLength = $default$aggregatorMaxContentLength();
    } 
    if (b.maxFramePayloadLength$set) {
      this.maxFramePayloadLength = b.maxFramePayloadLength$value;
    } else {
      this.maxFramePayloadLength = $default$maxFramePayloadLength();
    } 
  }
  
  public static abstract class BaseNettyClientConfigBuilder<C extends BaseNettyClientConfig, B extends BaseNettyClientConfigBuilder<C, B>> extends BaseLiveChatClientConfig.BaseLiveChatClientConfigBuilder<C, B> {
    private boolean aggregatorMaxContentLength$set;
    
    private int aggregatorMaxContentLength$value;
    
    private boolean maxFramePayloadLength$set;
    
    private int maxFramePayloadLength$value;
    
    protected B $fillValuesFrom(C instance) {
      super.$fillValuesFrom((BaseLiveChatClientConfig)instance);
      $fillValuesFromInstanceIntoBuilder((BaseNettyClientConfig)instance, this);
      return self();
    }
    
    private static void $fillValuesFromInstanceIntoBuilder(BaseNettyClientConfig instance, BaseNettyClientConfigBuilder<?, ?> b) {
      b.aggregatorMaxContentLength(instance.aggregatorMaxContentLength);
      b.maxFramePayloadLength(instance.maxFramePayloadLength);
    }
    
    public B aggregatorMaxContentLength(int aggregatorMaxContentLength) {
      this.aggregatorMaxContentLength$value = aggregatorMaxContentLength;
      this.aggregatorMaxContentLength$set = true;
      return self();
    }
    
    public B maxFramePayloadLength(int maxFramePayloadLength) {
      this.maxFramePayloadLength$value = maxFramePayloadLength;
      this.maxFramePayloadLength$set = true;
      return self();
    }
    
    protected abstract B self();
    
    public abstract C build();
    
    public String toString() {
      return "BaseNettyClientConfig.BaseNettyClientConfigBuilder(super=" + super.toString() + ", aggregatorMaxContentLength$value=" + this.aggregatorMaxContentLength$value + ", maxFramePayloadLength$value=" + this.maxFramePayloadLength$value + ")";
    }
  }
  
  public int getAggregatorMaxContentLength() {
    return this.aggregatorMaxContentLength;
  }
  
  public int getMaxFramePayloadLength() {
    return this.maxFramePayloadLength;
  }
}
