package tech.ordinaryroad.live.chat.client.tiktok.config;

import cn.hutool.core.util.StrUtil;
import tech.ordinaryroad.live.chat.client.codec.tiktok.constant.TiktokGiftCountCalculationTimeEnum;
import tech.ordinaryroad.live.chat.client.commons.client.config.BaseLiveChatClientConfig;
import tech.ordinaryroad.live.chat.client.commons.util.OrLiveChatHttpUtil;
import tech.ordinaryroad.live.chat.client.servers.netty.client.config.BaseNettyClientConfig;

public class TiktokLiveChatClientConfig extends BaseNettyClientConfig {
  private long heartbeatInitialDelay;
  
  private long heartbeatPeriod;
  
  private int aggregatorMaxContentLength;
  
  private int maxFramePayloadLength;
  
  private String versionCode;
  
  private String webcastSdkVersion;
  
  private String updateVersionCode;
  
  private String userAgent;
  
  private TiktokGiftCountCalculationTimeEnum giftCountCalculationTime;
  
  public void setHeartbeatInitialDelay(long heartbeatInitialDelay) {
    this.heartbeatInitialDelay = heartbeatInitialDelay;
  }
  
  public void setHeartbeatPeriod(long heartbeatPeriod) {
    this.heartbeatPeriod = heartbeatPeriod;
  }
  
  public void setAggregatorMaxContentLength(int aggregatorMaxContentLength) {
    this.aggregatorMaxContentLength = aggregatorMaxContentLength;
  }
  
  public void setMaxFramePayloadLength(int maxFramePayloadLength) {
    this.maxFramePayloadLength = maxFramePayloadLength;
  }
  
  public void setVersionCode(String versionCode) {
    this.versionCode = versionCode;
  }
  
  public void setWebcastSdkVersion(String webcastSdkVersion) {
    this.webcastSdkVersion = webcastSdkVersion;
  }
  
  public void setUpdateVersionCode(String updateVersionCode) {
    this.updateVersionCode = updateVersionCode;
  }
  
  public void setUserAgent(String userAgent) {
    this.userAgent = userAgent;
  }
  
  public void setGiftCountCalculationTime(TiktokGiftCountCalculationTimeEnum giftCountCalculationTime) {
    this.giftCountCalculationTime = giftCountCalculationTime;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof TiktokLiveChatClientConfig))
      return false; 
    TiktokLiveChatClientConfig other = (TiktokLiveChatClientConfig)o;
    if (!other.canEqual(this))
      return false; 
    if (getHeartbeatInitialDelay() != other.getHeartbeatInitialDelay())
      return false; 
    if (getHeartbeatPeriod() != other.getHeartbeatPeriod())
      return false; 
    if (getAggregatorMaxContentLength() != other.getAggregatorMaxContentLength())
      return false; 
    if (getMaxFramePayloadLength() != other.getMaxFramePayloadLength())
      return false; 
    Object this$versionCode = getVersionCode(), other$versionCode = other.getVersionCode();
    if ((this$versionCode == null) ? (other$versionCode != null) : !this$versionCode.equals(other$versionCode))
      return false; 
    Object this$webcastSdkVersion = getWebcastSdkVersion(), other$webcastSdkVersion = other.getWebcastSdkVersion();
    if ((this$webcastSdkVersion == null) ? (other$webcastSdkVersion != null) : !this$webcastSdkVersion.equals(other$webcastSdkVersion))
      return false; 
    Object this$updateVersionCode = getUpdateVersionCode(), other$updateVersionCode = other.getUpdateVersionCode();
    if ((this$updateVersionCode == null) ? (other$updateVersionCode != null) : !this$updateVersionCode.equals(other$updateVersionCode))
      return false; 
    Object this$userAgent = getUserAgent(), other$userAgent = other.getUserAgent();
    if ((this$userAgent == null) ? (other$userAgent != null) : !this$userAgent.equals(other$userAgent))
      return false; 
    Object this$giftCountCalculationTime = getGiftCountCalculationTime(), other$giftCountCalculationTime = other.getGiftCountCalculationTime();
    return !((this$giftCountCalculationTime == null) ? (other$giftCountCalculationTime != null) : !this$giftCountCalculationTime.equals(other$giftCountCalculationTime));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof TiktokLiveChatClientConfig;
  }
  
  public int hashCode() {
    int PRIME = 59;
    result = 1;
    long $heartbeatInitialDelay = getHeartbeatInitialDelay();
    result = result * 59 + (int)($heartbeatInitialDelay >>> 32L ^ $heartbeatInitialDelay);
    long $heartbeatPeriod = getHeartbeatPeriod();
    result = result * 59 + (int)($heartbeatPeriod >>> 32L ^ $heartbeatPeriod);
    result = result * 59 + getAggregatorMaxContentLength();
    result = result * 59 + getMaxFramePayloadLength();
    Object $versionCode = getVersionCode();
    result = result * 59 + (($versionCode == null) ? 43 : $versionCode.hashCode());
    Object $webcastSdkVersion = getWebcastSdkVersion();
    result = result * 59 + (($webcastSdkVersion == null) ? 43 : $webcastSdkVersion.hashCode());
    Object $updateVersionCode = getUpdateVersionCode();
    result = result * 59 + (($updateVersionCode == null) ? 43 : $updateVersionCode.hashCode());
    Object $userAgent = getUserAgent();
    result = result * 59 + (($userAgent == null) ? 43 : $userAgent.hashCode());
    Object $giftCountCalculationTime = getGiftCountCalculationTime();
    return result * 59 + (($giftCountCalculationTime == null) ? 43 : $giftCountCalculationTime.hashCode());
  }
  
  public String toString() {
    return "TiktokLiveChatClientConfig(heartbeatInitialDelay=" + getHeartbeatInitialDelay() + ", heartbeatPeriod=" + getHeartbeatPeriod() + ", aggregatorMaxContentLength=" + getAggregatorMaxContentLength() + ", maxFramePayloadLength=" + getMaxFramePayloadLength() + ", versionCode=" + getVersionCode() + ", webcastSdkVersion=" + getWebcastSdkVersion() + ", updateVersionCode=" + getUpdateVersionCode() + ", userAgent=" + getUserAgent() + ", giftCountCalculationTime=" + getGiftCountCalculationTime() + ")";
  }
  
  public TiktokLiveChatClientConfig() {
    this.heartbeatInitialDelay = $default$heartbeatInitialDelay();
    this.heartbeatPeriod = $default$heartbeatPeriod();
    this.aggregatorMaxContentLength = $default$aggregatorMaxContentLength();
    this.maxFramePayloadLength = $default$maxFramePayloadLength();
    this.versionCode = $default$versionCode();
    this.webcastSdkVersion = $default$webcastSdkVersion();
    this.updateVersionCode = $default$updateVersionCode();
    this.userAgent = $default$userAgent();
    this.giftCountCalculationTime = $default$giftCountCalculationTime();
  }
  
  public TiktokLiveChatClientConfig(long heartbeatInitialDelay, long heartbeatPeriod, int aggregatorMaxContentLength, int maxFramePayloadLength, String versionCode, String webcastSdkVersion, String updateVersionCode, String userAgent, TiktokGiftCountCalculationTimeEnum giftCountCalculationTime) {
    this.heartbeatInitialDelay = heartbeatInitialDelay;
    this.heartbeatPeriod = heartbeatPeriod;
    this.aggregatorMaxContentLength = aggregatorMaxContentLength;
    this.maxFramePayloadLength = maxFramePayloadLength;
    this.versionCode = versionCode;
    this.webcastSdkVersion = webcastSdkVersion;
    this.updateVersionCode = updateVersionCode;
    this.userAgent = userAgent;
    this.giftCountCalculationTime = giftCountCalculationTime;
  }
  
  private static long $default$heartbeatInitialDelay() {
    return 5L;
  }
  
  private static long $default$heartbeatPeriod() {
    return 10L;
  }
  
  private static int $default$aggregatorMaxContentLength() {
    return 67108864;
  }
  
  private static int $default$maxFramePayloadLength() {
    return 67108864;
  }
  
  private static String $default$versionCode() {
    return "270000";
  }
  
  private static String $default$webcastSdkVersion() {
    return "1.3.0";
  }
  
  private static String $default$updateVersionCode() {
    return "1.3.0";
  }
  
  private static String $default$userAgent() {
    return OrLiveChatHttpUtil.USER_AGENT;
  }
  
  private static TiktokGiftCountCalculationTimeEnum $default$giftCountCalculationTime() {
    return TiktokGiftCountCalculationTimeEnum.IMMEDIATELY;
  }
  
  protected TiktokLiveChatClientConfig(TiktokLiveChatClientConfigBuilder<?, ?> b) {
    super(b);
    if (b.heartbeatInitialDelay$set) {
      this.heartbeatInitialDelay = b.heartbeatInitialDelay$value;
    } else {
      this.heartbeatInitialDelay = $default$heartbeatInitialDelay();
    } 
    if (b.heartbeatPeriod$set) {
      this.heartbeatPeriod = b.heartbeatPeriod$value;
    } else {
      this.heartbeatPeriod = $default$heartbeatPeriod();
    } 
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
    if (b.versionCode$set) {
      this.versionCode = b.versionCode$value;
    } else {
      this.versionCode = $default$versionCode();
    } 
    if (b.webcastSdkVersion$set) {
      this.webcastSdkVersion = b.webcastSdkVersion$value;
    } else {
      this.webcastSdkVersion = $default$webcastSdkVersion();
    } 
    if (b.updateVersionCode$set) {
      this.updateVersionCode = b.updateVersionCode$value;
    } else {
      this.updateVersionCode = $default$updateVersionCode();
    } 
    if (b.userAgent$set) {
      this.userAgent = b.userAgent$value;
    } else {
      this.userAgent = $default$userAgent();
    } 
    if (b.giftCountCalculationTime$set) {
      this.giftCountCalculationTime = b.giftCountCalculationTime$value;
    } else {
      this.giftCountCalculationTime = $default$giftCountCalculationTime();
    } 
  }
  
  public static TiktokLiveChatClientConfigBuilder<?, ?> builder() {
    return new TiktokLiveChatClientConfigBuilderImpl();
  }
  
  public TiktokLiveChatClientConfigBuilder<?, ?> toBuilder() {
    return (new TiktokLiveChatClientConfigBuilderImpl()).$fillValuesFrom(this);
  }
  
  public static abstract class TiktokLiveChatClientConfigBuilder<C extends TiktokLiveChatClientConfig, B extends TiktokLiveChatClientConfigBuilder<C, B>> extends BaseNettyClientConfig.BaseNettyClientConfigBuilder<C, B> {
    private boolean heartbeatInitialDelay$set;
    
    private long heartbeatInitialDelay$value;
    
    private boolean heartbeatPeriod$set;
    
    private long heartbeatPeriod$value;
    
    private boolean aggregatorMaxContentLength$set;
    
    private int aggregatorMaxContentLength$value;
    
    private boolean maxFramePayloadLength$set;
    
    private int maxFramePayloadLength$value;
    
    private boolean versionCode$set;
    
    private String versionCode$value;
    
    private boolean webcastSdkVersion$set;
    
    private String webcastSdkVersion$value;
    
    private boolean updateVersionCode$set;
    
    private String updateVersionCode$value;
    
    private boolean userAgent$set;
    
    private String userAgent$value;
    
    private boolean giftCountCalculationTime$set;
    
    private TiktokGiftCountCalculationTimeEnum giftCountCalculationTime$value;
    
    protected B $fillValuesFrom(C instance) {
      super.$fillValuesFrom((BaseNettyClientConfig)instance);
      $fillValuesFromInstanceIntoBuilder((TiktokLiveChatClientConfig)instance, this);
      return self();
    }
    
    private static void $fillValuesFromInstanceIntoBuilder(TiktokLiveChatClientConfig instance, TiktokLiveChatClientConfigBuilder<?, ?> b) {
      b.heartbeatInitialDelay(instance.heartbeatInitialDelay);
      b.heartbeatPeriod(instance.heartbeatPeriod);
      b.aggregatorMaxContentLength(instance.aggregatorMaxContentLength);
      b.maxFramePayloadLength(instance.maxFramePayloadLength);
      b.versionCode(instance.versionCode);
      b.webcastSdkVersion(instance.webcastSdkVersion);
      b.updateVersionCode(instance.updateVersionCode);
      b.userAgent(instance.userAgent);
      b.giftCountCalculationTime(instance.giftCountCalculationTime);
    }
    
    public B heartbeatInitialDelay(long heartbeatInitialDelay) {
      this.heartbeatInitialDelay$value = heartbeatInitialDelay;
      this.heartbeatInitialDelay$set = true;
      return self();
    }
    
    public B heartbeatPeriod(long heartbeatPeriod) {
      this.heartbeatPeriod$value = heartbeatPeriod;
      this.heartbeatPeriod$set = true;
      return self();
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
    
    public B versionCode(String versionCode) {
      this.versionCode$value = versionCode;
      this.versionCode$set = true;
      return self();
    }
    
    public B webcastSdkVersion(String webcastSdkVersion) {
      this.webcastSdkVersion$value = webcastSdkVersion;
      this.webcastSdkVersion$set = true;
      return self();
    }
    
    public B updateVersionCode(String updateVersionCode) {
      this.updateVersionCode$value = updateVersionCode;
      this.updateVersionCode$set = true;
      return self();
    }
    
    public B userAgent(String userAgent) {
      this.userAgent$value = userAgent;
      this.userAgent$set = true;
      return self();
    }
    
    public B giftCountCalculationTime(TiktokGiftCountCalculationTimeEnum giftCountCalculationTime) {
      this.giftCountCalculationTime$value = giftCountCalculationTime;
      this.giftCountCalculationTime$set = true;
      return self();
    }
    
    protected abstract B self();
    
    public abstract C build();
    
    public String toString() {
      return "TiktokLiveChatClientConfig.TiktokLiveChatClientConfigBuilder(super=" + super.toString() + ", heartbeatInitialDelay$value=" + this.heartbeatInitialDelay$value + ", heartbeatPeriod$value=" + this.heartbeatPeriod$value + ", aggregatorMaxContentLength$value=" + this.aggregatorMaxContentLength$value + ", maxFramePayloadLength$value=" + this.maxFramePayloadLength$value + ", versionCode$value=" + this.versionCode$value + ", webcastSdkVersion$value=" + this.webcastSdkVersion$value + ", updateVersionCode$value=" + this.updateVersionCode$value + ", userAgent$value=" + this.userAgent$value + ", giftCountCalculationTime$value=" + this.giftCountCalculationTime$value + ")";
    }
  }
  
  private static final class TiktokLiveChatClientConfigBuilderImpl extends TiktokLiveChatClientConfigBuilder<TiktokLiveChatClientConfig, TiktokLiveChatClientConfigBuilderImpl> {
    private TiktokLiveChatClientConfigBuilderImpl() {}
    
    protected TiktokLiveChatClientConfigBuilderImpl self() {
      return this;
    }
    
    public TiktokLiveChatClientConfig build() {
      return new TiktokLiveChatClientConfig(this);
    }
  }
  
  public long getHeartbeatInitialDelay() {
    return this.heartbeatInitialDelay;
  }
  
  public long getHeartbeatPeriod() {
    return this.heartbeatPeriod;
  }
  
  public int getAggregatorMaxContentLength() {
    return this.aggregatorMaxContentLength;
  }
  
  public int getMaxFramePayloadLength() {
    return this.maxFramePayloadLength;
  }
  
  public String getVersionCode() {
    return this.versionCode;
  }
  
  public String getWebcastSdkVersion() {
    return this.webcastSdkVersion;
  }
  
  public String getUpdateVersionCode() {
    return this.updateVersionCode;
  }
  
  public String getUserAgent() {
    return this.userAgent;
  }
  
  public String getBrowserVersion() {
    return StrUtil.removePrefix(getUserAgent(), "Mozilla/");
  }
  
  public TiktokGiftCountCalculationTimeEnum getGiftCountCalculationTime() {
    return this.giftCountCalculationTime;
  }
}
