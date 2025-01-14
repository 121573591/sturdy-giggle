package cn.hutool.jwt;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONConfig;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.util.Map;

public class Claims implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private final JSONConfig CONFIG = JSONConfig.create().setDateFormat("#sss");
  
  private JSONObject claimJSON;
  
  protected void setClaim(String name, Object value) {
    init();
    Assert.notNull(name, "Name must be not null!", new Object[0]);
    if (value == null) {
      this.claimJSON.remove(name);
      return;
    } 
    this.claimJSON.set(name, value);
  }
  
  protected void putAll(Map<String, ?> headerClaims) {
    if (MapUtil.isNotEmpty(headerClaims))
      for (Map.Entry<String, ?> entry : headerClaims.entrySet())
        setClaim(entry.getKey(), entry.getValue());  
  }
  
  public Object getClaim(String name) {
    init();
    return this.claimJSON.getObj(name);
  }
  
  public JSONObject getClaimsJson() {
    init();
    return this.claimJSON;
  }
  
  public void parse(String tokenPart, Charset charset) {
    this.claimJSON = JSONUtil.parseObj(Base64.decodeStr(tokenPart, charset), this.CONFIG);
  }
  
  public String toString() {
    init();
    return this.claimJSON.toString();
  }
  
  private void init() {
    if (null == this.claimJSON)
      this.claimJSON = new JSONObject(this.CONFIG); 
  }
}
