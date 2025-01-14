package tech.ordinaryroad.live.chat.client.websocket.config;

import tech.ordinaryroad.live.chat.client.commons.client.config.BaseLiveChatClientConfig;
import tech.ordinaryroad.live.chat.client.servers.netty.client.config.BaseNettyClientConfig;

public class WebSocketLiveChatClientConfig extends BaseNettyClientConfig {
  public String toString() {
    return "WebSocketLiveChatClientConfig()";
  }
  
  public int hashCode() {
    int result = 1;
    return 1;
  }
  
  protected boolean canEqual(Object other) {
    return other instanceof WebSocketLiveChatClientConfig;
  }
  
  public boolean equals(Object o) {
    if (o == this)
      return true; 
    if (!(o instanceof WebSocketLiveChatClientConfig))
      return false; 
    WebSocketLiveChatClientConfig other = (WebSocketLiveChatClientConfig)o;
    return !!other.canEqual(this);
  }
  
  public WebSocketLiveChatClientConfigBuilder<?, ?> toBuilder() {
    return (new WebSocketLiveChatClientConfigBuilderImpl()).$fillValuesFrom(this);
  }
  
  public static WebSocketLiveChatClientConfigBuilder<?, ?> builder() {
    return new WebSocketLiveChatClientConfigBuilderImpl();
  }
  
  protected WebSocketLiveChatClientConfig(WebSocketLiveChatClientConfigBuilder<?, ?> b) {
    super(b);
  }
  
  private static final class WebSocketLiveChatClientConfigBuilderImpl extends WebSocketLiveChatClientConfigBuilder<WebSocketLiveChatClientConfig, WebSocketLiveChatClientConfigBuilderImpl> {
    private WebSocketLiveChatClientConfigBuilderImpl() {}
    
    protected WebSocketLiveChatClientConfigBuilderImpl self() {
      return this;
    }
    
    public WebSocketLiveChatClientConfig build() {
      return new WebSocketLiveChatClientConfig(this);
    }
  }
  
  public static abstract class WebSocketLiveChatClientConfigBuilder<C extends WebSocketLiveChatClientConfig, B extends WebSocketLiveChatClientConfigBuilder<C, B>> extends BaseNettyClientConfig.BaseNettyClientConfigBuilder<C, B> {
    protected B $fillValuesFrom(C instance) {
      super.$fillValuesFrom((BaseNettyClientConfig)instance);
      $fillValuesFromInstanceIntoBuilder((WebSocketLiveChatClientConfig)instance, this);
      return self();
    }
    
    private static void $fillValuesFromInstanceIntoBuilder(WebSocketLiveChatClientConfig instance, WebSocketLiveChatClientConfigBuilder<?, ?> b) {}
    
    protected abstract B self();
    
    public abstract C build();
    
    public String toString() {
      return "WebSocketLiveChatClientConfig.WebSocketLiveChatClientConfigBuilder(super=" + super.toString() + ")";
    }
  }
}
