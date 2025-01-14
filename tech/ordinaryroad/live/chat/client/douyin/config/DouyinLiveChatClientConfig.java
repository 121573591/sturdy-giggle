package tech.ordinaryroad.live.chat.client.douyin.config;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.system.JavaInfo;
import java.lang.reflect.Method;
import java.util.List;
import javax.script.ScriptEngine;
import tech.ordinaryroad.live.chat.client.codec.douyin.constant.DouyinGiftCountCalculationTimeEnum;
import tech.ordinaryroad.live.chat.client.commons.client.config.BaseLiveChatClientConfig;
import tech.ordinaryroad.live.chat.client.commons.util.OrLiveChatHttpUtil;
import tech.ordinaryroad.live.chat.client.servers.netty.client.config.BaseNettyClientConfig;

public class DouyinLiveChatClientConfig extends BaseNettyClientConfig {
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
  
  public void setScriptEngine(ScriptEngine scriptEngine) {
    this.scriptEngine = scriptEngine;
  }
  
  public void setGiftCountCalculationTime(DouyinGiftCountCalculationTimeEnum giftCountCalculationTime) {
    this.giftCountCalculationTime = giftCountCalculationTime;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof DouyinLiveChatClientConfig))
      return false; 
    DouyinLiveChatClientConfig other = (DouyinLiveChatClientConfig)o;
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
    Object this$scriptEngine = getScriptEngine(), other$scriptEngine = other.getScriptEngine();
    if ((this$scriptEngine == null) ? (other$scriptEngine != null) : !this$scriptEngine.equals(other$scriptEngine))
      return false; 
    Object this$giftCountCalculationTime = getGiftCountCalculationTime(), other$giftCountCalculationTime = other.getGiftCountCalculationTime();
    return !((this$giftCountCalculationTime == null) ? (other$giftCountCalculationTime != null) : !this$giftCountCalculationTime.equals(other$giftCountCalculationTime));
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof DouyinLiveChatClientConfig;
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
    Object $scriptEngine = getScriptEngine();
    result = result * 59 + (($scriptEngine == null) ? 43 : $scriptEngine.hashCode());
    Object $giftCountCalculationTime = getGiftCountCalculationTime();
    return result * 59 + (($giftCountCalculationTime == null) ? 43 : $giftCountCalculationTime.hashCode());
  }
  
  public String toString() {
    return "DouyinLiveChatClientConfig(heartbeatInitialDelay=" + getHeartbeatInitialDelay() + ", heartbeatPeriod=" + getHeartbeatPeriod() + ", aggregatorMaxContentLength=" + getAggregatorMaxContentLength() + ", maxFramePayloadLength=" + getMaxFramePayloadLength() + ", versionCode=" + getVersionCode() + ", webcastSdkVersion=" + getWebcastSdkVersion() + ", updateVersionCode=" + getUpdateVersionCode() + ", userAgent=" + getUserAgent() + ", scriptEngine=" + getScriptEngine() + ", giftCountCalculationTime=" + getGiftCountCalculationTime() + ")";
  }
  
  public DouyinLiveChatClientConfig() {
    this.heartbeatInitialDelay = $default$heartbeatInitialDelay();
    this.heartbeatPeriod = $default$heartbeatPeriod();
    this.aggregatorMaxContentLength = $default$aggregatorMaxContentLength();
    this.maxFramePayloadLength = $default$maxFramePayloadLength();
    this.versionCode = $default$versionCode();
    this.webcastSdkVersion = $default$webcastSdkVersion();
    this.updateVersionCode = $default$updateVersionCode();
    this.userAgent = $default$userAgent();
    this.scriptEngine = $default$scriptEngine();
    this.giftCountCalculationTime = $default$giftCountCalculationTime();
  }
  
  public DouyinLiveChatClientConfig(long heartbeatInitialDelay, long heartbeatPeriod, int aggregatorMaxContentLength, int maxFramePayloadLength, String versionCode, String webcastSdkVersion, String updateVersionCode, String userAgent, ScriptEngine scriptEngine, DouyinGiftCountCalculationTimeEnum giftCountCalculationTime) {
    this.heartbeatInitialDelay = heartbeatInitialDelay;
    this.heartbeatPeriod = heartbeatPeriod;
    this.aggregatorMaxContentLength = aggregatorMaxContentLength;
    this.maxFramePayloadLength = maxFramePayloadLength;
    this.versionCode = versionCode;
    this.webcastSdkVersion = webcastSdkVersion;
    this.updateVersionCode = updateVersionCode;
    this.userAgent = userAgent;
    this.scriptEngine = scriptEngine;
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
    return "180800";
  }
  
  private static String $default$webcastSdkVersion() {
    return "1.0.14-beta.0";
  }
  
  private static String $default$updateVersionCode() {
    return "1.0.14-beta.0";
  }
  
  private static String $default$userAgent() {
    return OrLiveChatHttpUtil.USER_AGENT;
  }
  
  private static ScriptEngine $default$scriptEngine() {
    return DEFAULT_ENGINE;
  }
  
  private static DouyinGiftCountCalculationTimeEnum $default$giftCountCalculationTime() {
    return DouyinGiftCountCalculationTimeEnum.IMMEDIATELY;
  }
  
  protected DouyinLiveChatClientConfig(DouyinLiveChatClientConfigBuilder<?, ?> b) {
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
    if (b.scriptEngine$set) {
      this.scriptEngine = b.scriptEngine$value;
    } else {
      this.scriptEngine = $default$scriptEngine();
    } 
    if (b.giftCountCalculationTime$set) {
      this.giftCountCalculationTime = b.giftCountCalculationTime$value;
    } else {
      this.giftCountCalculationTime = $default$giftCountCalculationTime();
    } 
  }
  
  public static DouyinLiveChatClientConfigBuilder<?, ?> builder() {
    return new DouyinLiveChatClientConfigBuilderImpl();
  }
  
  public DouyinLiveChatClientConfigBuilder<?, ?> toBuilder() {
    return (new DouyinLiveChatClientConfigBuilderImpl()).$fillValuesFrom(this);
  }
  
  public static abstract class DouyinLiveChatClientConfigBuilder<C extends DouyinLiveChatClientConfig, B extends DouyinLiveChatClientConfigBuilder<C, B>> extends BaseNettyClientConfig.BaseNettyClientConfigBuilder<C, B> {
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
    
    private boolean scriptEngine$set;
    
    private ScriptEngine scriptEngine$value;
    
    private boolean giftCountCalculationTime$set;
    
    private DouyinGiftCountCalculationTimeEnum giftCountCalculationTime$value;
    
    protected B $fillValuesFrom(C instance) {
      super.$fillValuesFrom((BaseNettyClientConfig)instance);
      $fillValuesFromInstanceIntoBuilder((DouyinLiveChatClientConfig)instance, this);
      return self();
    }
    
    private static void $fillValuesFromInstanceIntoBuilder(DouyinLiveChatClientConfig instance, DouyinLiveChatClientConfigBuilder<?, ?> b) {
      b.heartbeatInitialDelay(instance.heartbeatInitialDelay);
      b.heartbeatPeriod(instance.heartbeatPeriod);
      b.aggregatorMaxContentLength(instance.aggregatorMaxContentLength);
      b.maxFramePayloadLength(instance.maxFramePayloadLength);
      b.versionCode(instance.versionCode);
      b.webcastSdkVersion(instance.webcastSdkVersion);
      b.updateVersionCode(instance.updateVersionCode);
      b.userAgent(instance.userAgent);
      b.scriptEngine(instance.scriptEngine);
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
    
    public B scriptEngine(ScriptEngine scriptEngine) {
      this.scriptEngine$value = scriptEngine;
      this.scriptEngine$set = true;
      return self();
    }
    
    public B giftCountCalculationTime(DouyinGiftCountCalculationTimeEnum giftCountCalculationTime) {
      this.giftCountCalculationTime$value = giftCountCalculationTime;
      this.giftCountCalculationTime$set = true;
      return self();
    }
    
    protected abstract B self();
    
    public abstract C build();
    
    public String toString() {
      return "DouyinLiveChatClientConfig.DouyinLiveChatClientConfigBuilder(super=" + super.toString() + ", heartbeatInitialDelay$value=" + this.heartbeatInitialDelay$value + ", heartbeatPeriod$value=" + this.heartbeatPeriod$value + ", aggregatorMaxContentLength$value=" + this.aggregatorMaxContentLength$value + ", maxFramePayloadLength$value=" + this.maxFramePayloadLength$value + ", versionCode$value=" + this.versionCode$value + ", webcastSdkVersion$value=" + this.webcastSdkVersion$value + ", updateVersionCode$value=" + this.updateVersionCode$value + ", userAgent$value=" + this.userAgent$value + ", scriptEngine$value=" + this.scriptEngine$value + ", giftCountCalculationTime$value=" + this.giftCountCalculationTime$value + ")";
    }
  }
  
  private static final class DouyinLiveChatClientConfigBuilderImpl extends DouyinLiveChatClientConfigBuilder<DouyinLiveChatClientConfig, DouyinLiveChatClientConfigBuilderImpl> {
    private DouyinLiveChatClientConfigBuilderImpl() {}
    
    protected DouyinLiveChatClientConfigBuilderImpl self() {
      return this;
    }
    
    public DouyinLiveChatClientConfig build() {
      return new DouyinLiveChatClientConfig(this);
    }
  }
  
  private static final ScriptEngine DEFAULT_ENGINE = createScriptEngine();
  
  public static final List<String> WEB_SOCKET_URIS = CollUtil.newArrayList((Object[])new String[] { "wss://webcast5-ws-web-lq.douyin.com/webcast/im/push/v2/", "wss://webcast5-ws-web-lf.douyin.com/webcast/im/push/v2/", "wss://webcast5-ws-web-hl.douyin.com/webcast/im/push/v2/" });
  
  private long heartbeatInitialDelay;
  
  private long heartbeatPeriod;
  
  private int aggregatorMaxContentLength;
  
  private int maxFramePayloadLength;
  
  private String versionCode;
  
  private String webcastSdkVersion;
  
  private String updateVersionCode;
  
  private String userAgent;
  
  private ScriptEngine scriptEngine;
  
  private DouyinGiftCountCalculationTimeEnum giftCountCalculationTime;
  
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
  
  public ScriptEngine getScriptEngine() {
    return this.scriptEngine;
  }
  
  public DouyinGiftCountCalculationTimeEnum getGiftCountCalculationTime() {
    return this.giftCountCalculationTime;
  }
  
  public static ScriptEngine createScriptEngine() {
    try {
      Class<?> nashornScriptEngineFactoryClass;
      JavaInfo javaInfo = new JavaInfo();
      if (javaInfo.getVersionFloat() >= 11.0F) {
        nashornScriptEngineFactoryClass = Class.forName("org.openjdk.nashorn.api.scripting.NashornScriptEngineFactory");
      } else {
        nashornScriptEngineFactoryClass = Class.forName("jdk.nashorn.api.scripting.NashornScriptEngineFactory");
      } 
      Object factory = nashornScriptEngineFactoryClass.getConstructor(new Class[0]).newInstance(new Object[0]);
      Method getScriptEngine = nashornScriptEngineFactoryClass.getDeclaredMethod("getScriptEngine", new Class[] { String[].class });
      return (ScriptEngine)getScriptEngine.invoke(factory, new Object[] { { "--language=es6" } });
    } catch (Throwable $ex) {
      throw $ex;
    } 
  }
}
