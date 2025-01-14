package tech.ordinaryroad.live.chat.client.kuaishou.config;

import tech.ordinaryroad.live.chat.client.codec.kuaishou.constant.RoomInfoGetTypeEnum;
import tech.ordinaryroad.live.chat.client.commons.client.config.BaseLiveChatClientConfig;
import tech.ordinaryroad.live.chat.client.servers.netty.client.config.BaseNettyClientConfig;

public class KuaishouLiveChatClientConfig extends BaseNettyClientConfig {
  private long heartbeatPeriod;
  
  private RoomInfoGetTypeEnum roomInfoGetType;
  
  public void setHeartbeatPeriod(long heartbeatPeriod) {
    this.heartbeatPeriod = heartbeatPeriod;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof KuaishouLiveChatClientConfig))
      return false; 
    KuaishouLiveChatClientConfig other = (KuaishouLiveChatClientConfig)o;
    if (!other.canEqual(this))
      return false; 
    if (getHeartbeatPeriod() != other.getHeartbeatPeriod())
      return false; 
    Object this$roomInfoGetType = getRoomInfoGetType(), other$roomInfoGetType = other.getRoomInfoGetType();
    return !((this$roomInfoGetType == null) ? (other$roomInfoGetType != null) : !this$roomInfoGetType.equals(other$roomInfoGetType));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof KuaishouLiveChatClientConfig;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    long $heartbeatPeriod = getHeartbeatPeriod();
    result = result * 59 + (int)($heartbeatPeriod >>> 32L ^ $heartbeatPeriod);
    Object $roomInfoGetType = getRoomInfoGetType();
    return result * 59 + (($roomInfoGetType == null) ? 43 : $roomInfoGetType.hashCode());
  }
  
  public String toString() {
    return "KuaishouLiveChatClientConfig(heartbeatPeriod=" + getHeartbeatPeriod() + ", roomInfoGetType=" + getRoomInfoGetType() + ")";
  }
  
  public KuaishouLiveChatClientConfig() {
    this.heartbeatPeriod = $default$heartbeatPeriod();
    this.roomInfoGetType = $default$roomInfoGetType();
  }
  
  public KuaishouLiveChatClientConfig(long heartbeatPeriod, RoomInfoGetTypeEnum roomInfoGetType) {
    this.heartbeatPeriod = heartbeatPeriod;
    this.roomInfoGetType = roomInfoGetType;
  }
  
  private static long $default$heartbeatPeriod() {
    return 20L;
  }
  
  private static RoomInfoGetTypeEnum $default$roomInfoGetType() {
    return RoomInfoGetTypeEnum.COOKIE;
  }
  
  protected KuaishouLiveChatClientConfig(KuaishouLiveChatClientConfigBuilder<?, ?> b) {
    super(b);
    if (b.heartbeatPeriod$set) {
      this.heartbeatPeriod = b.heartbeatPeriod$value;
    } else {
      this.heartbeatPeriod = $default$heartbeatPeriod();
    } 
    if (b.roomInfoGetType$set) {
      this.roomInfoGetType = b.roomInfoGetType$value;
    } else {
      this.roomInfoGetType = $default$roomInfoGetType();
    } 
  }
  
  public static KuaishouLiveChatClientConfigBuilder<?, ?> builder() {
    return new KuaishouLiveChatClientConfigBuilderImpl();
  }
  
  public KuaishouLiveChatClientConfigBuilder<?, ?> toBuilder() {
    return (new KuaishouLiveChatClientConfigBuilderImpl()).$fillValuesFrom(this);
  }
  
  public static abstract class KuaishouLiveChatClientConfigBuilder<C extends KuaishouLiveChatClientConfig, B extends KuaishouLiveChatClientConfigBuilder<C, B>> extends BaseNettyClientConfig.BaseNettyClientConfigBuilder<C, B> {
    private boolean heartbeatPeriod$set;
    
    private long heartbeatPeriod$value;
    
    private boolean roomInfoGetType$set;
    
    private RoomInfoGetTypeEnum roomInfoGetType$value;
    
    protected B $fillValuesFrom(C instance) {
      super.$fillValuesFrom((BaseNettyClientConfig)instance);
      $fillValuesFromInstanceIntoBuilder((KuaishouLiveChatClientConfig)instance, this);
      return self();
    }
    
    private static void $fillValuesFromInstanceIntoBuilder(KuaishouLiveChatClientConfig instance, KuaishouLiveChatClientConfigBuilder<?, ?> b) {
      b.heartbeatPeriod(instance.heartbeatPeriod);
      b.roomInfoGetType(instance.roomInfoGetType);
    }
    
    public B heartbeatPeriod(long heartbeatPeriod) {
      this.heartbeatPeriod$value = heartbeatPeriod;
      this.heartbeatPeriod$set = true;
      return self();
    }
    
    public B roomInfoGetType(RoomInfoGetTypeEnum roomInfoGetType) {
      this.roomInfoGetType$value = roomInfoGetType;
      this.roomInfoGetType$set = true;
      return self();
    }
    
    protected abstract B self();
    
    public abstract C build();
    
    public String toString() {
      return "KuaishouLiveChatClientConfig.KuaishouLiveChatClientConfigBuilder(super=" + super.toString() + ", heartbeatPeriod$value=" + this.heartbeatPeriod$value + ", roomInfoGetType$value=" + this.roomInfoGetType$value + ")";
    }
  }
  
  private static final class KuaishouLiveChatClientConfigBuilderImpl extends KuaishouLiveChatClientConfigBuilder<KuaishouLiveChatClientConfig, KuaishouLiveChatClientConfigBuilderImpl> {
    private KuaishouLiveChatClientConfigBuilderImpl() {}
    
    protected KuaishouLiveChatClientConfigBuilderImpl self() {
      return this;
    }
    
    public KuaishouLiveChatClientConfig build() {
      return new KuaishouLiveChatClientConfig(this);
    }
  }
  
  public long getHeartbeatPeriod() {
    return this.heartbeatPeriod;
  }
  
  public RoomInfoGetTypeEnum getRoomInfoGetType() {
    return this.roomInfoGetType;
  }
  
  public void setRoomInfoGetType(RoomInfoGetTypeEnum roomInfoGetType) {
    RoomInfoGetTypeEnum oldValue = this.roomInfoGetType;
    this.roomInfoGetType = roomInfoGetType;
    this.propertyChangeSupport.firePropertyChange("roomInfoGetType", oldValue, roomInfoGetType);
  }
}
