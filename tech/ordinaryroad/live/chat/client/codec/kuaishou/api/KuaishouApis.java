package tech.ordinaryroad.live.chat.client.codec.kuaishou.api;

import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.http.ContentType;
import cn.hutool.http.GlobalHeaders;
import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.Method;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import tech.ordinaryroad.live.chat.client.codec.kuaishou.constant.RoomInfoGetTypeEnum;
import tech.ordinaryroad.live.chat.client.codec.kuaishou.msg.KuaishouGiftMsg;
import tech.ordinaryroad.live.chat.client.codec.kuaishou.protobuf.LiveAudienceStateOuterClass;
import tech.ordinaryroad.live.chat.client.codec.kuaishou.protobuf.WebGiftFeedOuterClass;
import tech.ordinaryroad.live.chat.client.commons.base.exception.BaseException;
import tech.ordinaryroad.live.chat.client.commons.util.OrJacksonUtil;
import tech.ordinaryroad.live.chat.client.commons.util.OrLiveChatCookieUtil;
import tech.ordinaryroad.live.chat.client.commons.util.OrLiveChatHttpUtil;

public class KuaishouApis {
  public static final TimedCache<String, Map<String, GiftInfo>> RESULT_CACHE = new TimedCache(TimeUnit.DAYS.toMillis(1L));
  
  public static final String KEY_RESULT_CACHE_GIFT_ITEMS = "GIFT_ITEMS";
  
  public static final String PATTERN_LIVE_STREAM_ID = "\"liveStream\":\\{\"id\":\"([\\w\\d-_]+)\"";
  
  public static final String USER_AGENT = GlobalHeaders.INSTANCE.header(Header.USER_AGENT).replace("Hutool", "");
  
  private static final TimedCache<String, WebGiftFeedOuterClass.WebGiftFeed> WEB_GIFT_FEED_CACHE = new TimedCache(300000L, new ConcurrentHashMap<>());
  
  public static RoomInitResult roomInitSetCookie(Object roomId, String cookie, RoomInitResult roomInitResult) {
    HttpResponse response = createGetRequest("https://live.kuaishou.com/u/" + roomId, cookie).execute();
    try {
      if (StrUtil.isBlank(cookie))
        cookie = OrLiveChatCookieUtil.toString(response.getCookies()); 
      String body = response.body();
      String liveStreamId = ReUtil.getGroup1("\"liveStream\":\\{\"id\":\"([\\w\\d-_]+)\"", body);
      JsonNode websocketinfo = websocketinfo(roomId, liveStreamId, cookie);
      if (!websocketinfo.has("token"))
        throwExceptionWithTip("主播未开播，token获取失败 " + websocketinfo); 
      ArrayNode websocketUrls = (ArrayNode)websocketinfo.withArray("websocketUrls");
      ArrayList<String> websocketUrlList = CollUtil.newArrayList((Object[])new String[0]);
      for (JsonNode websocketUrl : websocketUrls)
        websocketUrlList.add(websocketUrl.asText()); 
      roomInitResult = Optional.<RoomInitResult>ofNullable(roomInitResult).orElseGet(() -> RoomInitResult.builder().build());
      roomInitResult.setToken(websocketinfo.required("token").asText());
      roomInitResult.setWebsocketUrls(websocketUrlList);
      roomInitResult.setLiveStreamId(liveStreamId);
      return roomInitResult;
    } finally {
      if (Collections.<HttpResponse>singletonList(response).get(0) != null)
        response.close(); 
    } 
  }
  
  public static RoomInitResult roomInitSetCookie(Object roomId, String cookie) {
    return roomInitSetCookie(roomId, cookie, null);
  }
  
  public static RoomInitResult roomInitGet(Object roomId, RoomInitResult roomInitResult) {
    HttpResponse response = createGetRequest("https://live.kuaishou.com/live_api/liveroom/livedetail?principalId=" + roomId, "").execute();
    try {
      JsonNode bodyDataNode = responseInterceptor(response.body());
      JsonNode websocketInfoNode = bodyDataNode.get("websocketInfo");
      String liveStreamId = bodyDataNode.get("liveStream").required("id").asText("");
      if (StrUtil.isBlankIfStr(liveStreamId))
        throwExceptionWithTip("主播未开播，liveStreamId为空"); 
      String token = websocketInfoNode.required("token").asText("");
      if (StrUtil.isBlankIfStr(token))
        throwExceptionWithTip("主播未开播，token获取失败"); 
      JsonNode webSocketAddressesNode = websocketInfoNode.get("webSocketAddresses");
      List<String> websocketUrlList = new ArrayList<>(webSocketAddressesNode.size());
      for (JsonNode tempJsonNode : webSocketAddressesNode)
        websocketUrlList.add(tempJsonNode.asText()); 
      roomInitResult = Optional.<RoomInitResult>ofNullable(roomInitResult).orElseGet(() -> RoomInitResult.builder().build());
      roomInitResult.setToken(token);
      roomInitResult.setWebsocketUrls(websocketUrlList);
      roomInitResult.setLiveStreamId(liveStreamId);
      return roomInitResult;
    } finally {
      if (Collections.<HttpResponse>singletonList(response).get(0) != null)
        response.close(); 
    } 
  }
  
  public static RoomInitResult roomInitGet(Object roomId) {
    return roomInitGet(roomId, null);
  }
  
  public static RoomInitResult roomInit(Object roomId, RoomInfoGetTypeEnum roomInfoGetType, String cookie, RoomInitResult roomInitResult) {
    switch (roomInfoGetType) {
      case COOKIE:
        return roomInitSetCookie(roomId, cookie, roomInitResult);
      case NOT_COOKIE:
        return roomInitGet(roomId, roomInitResult);
    } 
    throwExceptionWithTip("错误获取类型");
    return null;
  }
  
  public static RoomInitResult roomInit(Object roomId, RoomInfoGetTypeEnum roomInfoGetType, String cookie) {
    return roomInit(roomId, roomInfoGetType, cookie, null);
  }
  
  public static RoomInitResult roomInit(Object roomId) {
    return roomInit(roomId, RoomInfoGetTypeEnum.NOT_COOKIE, null);
  }
  
  public static RoomInitResult roomInit(Object roomId, RoomInitResult roomInitResult) {
    return roomInit(roomId, RoomInfoGetTypeEnum.NOT_COOKIE, null, roomInitResult);
  }
  
  public static JsonNode websocketinfo(Object roomId, String liveStreamId, String cookie) {
    if (StrUtil.isBlank(liveStreamId))
      throwExceptionWithTip("主播未开播，liveStreamId为空"); 
    HttpResponse response = ((HttpRequest)createGetRequest("https://live.kuaishou.com/live_api/liveroom/websocketinfo?liveStreamId=" + liveStreamId, cookie).header(Header.REFERER, "https://live.kuaishou.com/u/" + roomId)).execute();
    try {
      return responseInterceptor(response.body());
    } finally {
      if (Collections.<HttpResponse>singletonList(response).get(0) != null)
        response.close(); 
    } 
  }
  
  public static Map<String, GiftInfo> allgifts() {
    Map<String, GiftInfo> map = new HashMap<>();
    HttpResponse response = createGetRequest("https://live.kuaishou.com/live_api/emoji/allgifts", null).execute();
    try {
      JsonNode jsonNode = responseInterceptor(response.body());
      jsonNode.fields().forEachRemaining(stringJsonNodeEntry -> map.put((String)stringJsonNodeEntry.getKey(), (GiftInfo)OrJacksonUtil.getInstance().convertValue(stringJsonNodeEntry.getValue(), GiftInfo.class)));
      return map;
    } finally {
      if (Collections.<HttpResponse>singletonList(response).get(0) != null)
        response.close(); 
    } 
  }
  
  public static GiftInfo getGiftInfoById(String id) {
    if (!RESULT_CACHE.containsKey("GIFT_ITEMS"))
      RESULT_CACHE.put("GIFT_ITEMS", allgifts()); 
    return (GiftInfo)((Map)RESULT_CACHE.get("GIFT_ITEMS")).get(id);
  }
  
  public static JsonNode sendComment(String cookie, Object roomId, SendCommentRequest request) {
    try {
      HttpResponse response = ((HttpRequest)createPostRequest("https://live.kuaishou.com/live_api/liveroom/sendComment", cookie).body(OrJacksonUtil.getInstance().writeValueAsString(request), ContentType.JSON.getValue()).header(Header.REFERER, "https://live.kuaishou.com/u/" + roomId)).execute();
      try {
        return responseInterceptor(response.body());
      } finally {
        if (Collections.<HttpResponse>singletonList(response).get(0) != null)
          response.close(); 
      } 
    } catch (Throwable $ex) {
      throw $ex;
    } 
  }
  
  public static JsonNode clickLike(String cookie, Object roomId, String liveStreamId, int count) {
    try {
      HttpResponse response = ((HttpRequest)((HttpRequest)createPostRequest("https://live.kuaishou.com/live_api/liveroom/like", cookie).body(OrJacksonUtil.getInstance().createObjectNode().put("liveStreamId", liveStreamId).put("count", count).toString(), ContentType.JSON.getValue()).header(Header.ORIGIN, "https://live.kuaishou.com")).header(Header.REFERER, "https://live.kuaishou.com/u/" + roomId)).execute();
      try {
        return responseInterceptor(response.body());
      } finally {
        if (Collections.<HttpResponse>singletonList(response).get(0) != null)
          response.close(); 
      } 
    } catch (Throwable $ex) {
      throw $ex;
    } 
  }
  
  public static HttpRequest createRequest(Method method, String url, String cookie) {
    return (HttpRequest)((HttpRequest)OrLiveChatHttpUtil.createRequest(method, url)
      .cookie(cookie)
      .header(Header.HOST, URLUtil.url(url).getHost()))
      .header(Header.USER_AGENT, USER_AGENT);
  }
  
  public static HttpRequest createGetRequest(String url, String cookie) {
    return createRequest(Method.GET, url, cookie);
  }
  
  public static HttpRequest createPostRequest(String url, String cookie) {
    return createRequest(Method.POST, url, cookie);
  }
  
  private static JsonNode responseInterceptor(String responseString) {
    try {
      JsonNode jsonNode = OrJacksonUtil.getInstance().readTree(responseString);
      JsonNode data = jsonNode.required("data");
      if (data.has("result")) {
        int result = data.get("result").asInt();
        if (result != 1) {
          String message = "";
          switch (result) {
            case 2:
              message = "请求过快，请稍后重试";
              break;
            case 400002:
              message = "需要进行验证";
              break;
            default:
              message = "";
              break;
          } 
          throwExceptionWithTip("接口访问失败：" + message + "，返回结果：" + jsonNode);
        } 
      } 
      return data;
    } catch (JsonProcessingException e) {
      throw new BaseException(e);
    } 
  }
  
  private static void throwExceptionWithTip(String message) {
    throw new BaseException("『可能已触发滑块验证，建议配置Cookie或打开浏览器进行滑块验证后重试』" + message);
  }
  
  public static int calculateGiftCount(KuaishouGiftMsg msg) {
    int giftCount;
    if (msg == null || msg.getMsg() == null)
      return 0; 
    WebGiftFeedOuterClass.WebGiftFeed webGiftFeed = msg.getMsg();
    String mergeKey = webGiftFeed.getMergeKey();
    if (WEB_GIFT_FEED_CACHE.containsKey(mergeKey)) {
      WebGiftFeedOuterClass.WebGiftFeed webGiftFeedByMergeKey = (WebGiftFeedOuterClass.WebGiftFeed)WEB_GIFT_FEED_CACHE.get(mergeKey);
      int comboCountByMergeKey = webGiftFeedByMergeKey.getComboCount();
      giftCount = webGiftFeed.getComboCount() - comboCountByMergeKey;
    } else {
      int batchSize = webGiftFeed.getBatchSize();
      int comboCount = webGiftFeed.getComboCount();
      if (comboCount == 1) {
        giftCount = batchSize;
      } else {
        giftCount = comboCount;
      } 
    } 
    WEB_GIFT_FEED_CACHE.put(mergeKey, webGiftFeed);
    msg.setCalculatedGiftCount(giftCount);
    return giftCount;
  }
  
  public static String getBadgeName(LiveAudienceStateOuterClass.LiveAudienceState liveAudienceState) {
    String badgeName = null;
    try {
      for (LiveAudienceStateOuterClass.LiveAudienceState.LiveAudienceState_11 liveAudienceState11 : liveAudienceState.getLiveAudienceState11List()) {
        String badgeIcon = liveAudienceState11.getLiveAudienceState111().getBadgeIcon();
        if (StrUtil.startWithIgnoreCase(badgeIcon, "fans")) {
          badgeName = liveAudienceState11.getLiveAudienceState111().getBadgeName();
          break;
        } 
      } 
    } catch (Exception exception) {}
    return badgeName;
  }
  
  public static byte getBadgeLevel(LiveAudienceStateOuterClass.LiveAudienceState liveAudienceState) {
    byte badgeLevel = 0;
    try {
      badgeLevel = (byte)liveAudienceState.getLiveFansGroupState().getIntimacyLevel();
    } catch (Exception exception) {}
    return badgeLevel;
  }
  
  public static class SendCommentRequest {
    private String liveStreamId;
    
    private String content;
    
    private String color;
    
    public void setLiveStreamId(String liveStreamId) {
      this.liveStreamId = liveStreamId;
    }
    
    public void setContent(String content) {
      this.content = content;
    }
    
    public void setColor(String color) {
      this.color = color;
    }
    
    public boolean equals(Object o) {
      if (o == this)
        return true; 
      if (!(o instanceof SendCommentRequest))
        return false; 
      SendCommentRequest other = (SendCommentRequest)o;
      if (!other.canEqual(this))
        return false; 
      Object this$liveStreamId = getLiveStreamId(), other$liveStreamId = other.getLiveStreamId();
      if ((this$liveStreamId == null) ? (other$liveStreamId != null) : !this$liveStreamId.equals(other$liveStreamId))
        return false; 
      Object this$content = getContent(), other$content = other.getContent();
      if ((this$content == null) ? (other$content != null) : !this$content.equals(other$content))
        return false; 
      Object this$color = getColor(), other$color = other.getColor();
      return !((this$color == null) ? (other$color != null) : !this$color.equals(other$color));
    }
    
    protected boolean canEqual(Object other) {
      return other instanceof SendCommentRequest;
    }
    
    public int hashCode() {
      int PRIME = 59;
      result = 1;
      Object $liveStreamId = getLiveStreamId();
      result = result * 59 + (($liveStreamId == null) ? 43 : $liveStreamId.hashCode());
      Object $content = getContent();
      result = result * 59 + (($content == null) ? 43 : $content.hashCode());
      Object $color = getColor();
      return result * 59 + (($color == null) ? 43 : $color.hashCode());
    }
    
    public String toString() {
      return "KuaishouApis.SendCommentRequest(liveStreamId=" + getLiveStreamId() + ", content=" + getContent() + ", color=" + getColor() + ")";
    }
    
    public SendCommentRequest(String liveStreamId, String content, String color) {
      this.liveStreamId = liveStreamId;
      this.content = content;
      this.color = color;
    }
    
    public SendCommentRequest() {}
    
    public static SendCommentRequestBuilder builder() {
      return new SendCommentRequestBuilder();
    }
    
    public static class SendCommentRequestBuilder {
      private String liveStreamId;
      
      private String content;
      
      private String color;
      
      public SendCommentRequestBuilder liveStreamId(String liveStreamId) {
        this.liveStreamId = liveStreamId;
        return this;
      }
      
      public SendCommentRequestBuilder content(String content) {
        this.content = content;
        return this;
      }
      
      public SendCommentRequestBuilder color(String color) {
        this.color = color;
        return this;
      }
      
      public KuaishouApis.SendCommentRequest build() {
        return new KuaishouApis.SendCommentRequest(this.liveStreamId, this.content, this.color);
      }
      
      public String toString() {
        return "KuaishouApis.SendCommentRequest.SendCommentRequestBuilder(liveStreamId=" + this.liveStreamId + ", content=" + this.content + ", color=" + this.color + ")";
      }
    }
    
    public String getLiveStreamId() {
      return this.liveStreamId;
    }
    
    public String getContent() {
      return this.content;
    }
    
    public String getColor() {
      return this.color;
    }
  }
  
  public static class RoomInitResult {
    private String token;
    
    private String liveStreamId;
    
    private List<String> websocketUrls;
    
    public void setToken(String token) {
      this.token = token;
    }
    
    public void setLiveStreamId(String liveStreamId) {
      this.liveStreamId = liveStreamId;
    }
    
    public void setWebsocketUrls(List<String> websocketUrls) {
      this.websocketUrls = websocketUrls;
    }
    
    public boolean equals(Object o) {
      if (o == this)
        return true; 
      if (!(o instanceof RoomInitResult))
        return false; 
      RoomInitResult other = (RoomInitResult)o;
      if (!other.canEqual(this))
        return false; 
      Object this$token = getToken(), other$token = other.getToken();
      if ((this$token == null) ? (other$token != null) : !this$token.equals(other$token))
        return false; 
      Object this$liveStreamId = getLiveStreamId(), other$liveStreamId = other.getLiveStreamId();
      if ((this$liveStreamId == null) ? (other$liveStreamId != null) : !this$liveStreamId.equals(other$liveStreamId))
        return false; 
      Object<String> this$websocketUrls = (Object<String>)getWebsocketUrls(), other$websocketUrls = (Object<String>)other.getWebsocketUrls();
      return !((this$websocketUrls == null) ? (other$websocketUrls != null) : !this$websocketUrls.equals(other$websocketUrls));
    }
    
    protected boolean canEqual(Object other) {
      return other instanceof RoomInitResult;
    }
    
    public int hashCode() {
      int PRIME = 59;
      result = 1;
      Object $token = getToken();
      result = result * 59 + (($token == null) ? 43 : $token.hashCode());
      Object $liveStreamId = getLiveStreamId();
      result = result * 59 + (($liveStreamId == null) ? 43 : $liveStreamId.hashCode());
      Object<String> $websocketUrls = (Object<String>)getWebsocketUrls();
      return result * 59 + (($websocketUrls == null) ? 43 : $websocketUrls.hashCode());
    }
    
    public String toString() {
      return "KuaishouApis.RoomInitResult(token=" + getToken() + ", liveStreamId=" + getLiveStreamId() + ", websocketUrls=" + getWebsocketUrls() + ")";
    }
    
    public RoomInitResult(String token, String liveStreamId, List<String> websocketUrls) {
      this.token = token;
      this.liveStreamId = liveStreamId;
      this.websocketUrls = websocketUrls;
    }
    
    public RoomInitResult() {}
    
    public static RoomInitResultBuilder builder() {
      return new RoomInitResultBuilder();
    }
    
    public static class RoomInitResultBuilder {
      private String token;
      
      private String liveStreamId;
      
      private List<String> websocketUrls;
      
      public RoomInitResultBuilder token(String token) {
        this.token = token;
        return this;
      }
      
      public RoomInitResultBuilder liveStreamId(String liveStreamId) {
        this.liveStreamId = liveStreamId;
        return this;
      }
      
      public RoomInitResultBuilder websocketUrls(List<String> websocketUrls) {
        this.websocketUrls = websocketUrls;
        return this;
      }
      
      public KuaishouApis.RoomInitResult build() {
        return new KuaishouApis.RoomInitResult(this.token, this.liveStreamId, this.websocketUrls);
      }
      
      public String toString() {
        return "KuaishouApis.RoomInitResult.RoomInitResultBuilder(token=" + this.token + ", liveStreamId=" + this.liveStreamId + ", websocketUrls=" + this.websocketUrls + ")";
      }
    }
    
    public String getToken() {
      return this.token;
    }
    
    public String getLiveStreamId() {
      return this.liveStreamId;
    }
    
    public List<String> getWebsocketUrls() {
      return this.websocketUrls;
    }
  }
  
  public static class RoomInitResultBuilder {
    private String token;
    
    private String liveStreamId;
    
    private List<String> websocketUrls;
    
    public RoomInitResultBuilder token(String token) {
      this.token = token;
      return this;
    }
    
    public RoomInitResultBuilder liveStreamId(String liveStreamId) {
      this.liveStreamId = liveStreamId;
      return this;
    }
    
    public RoomInitResultBuilder websocketUrls(List<String> websocketUrls) {
      this.websocketUrls = websocketUrls;
      return this;
    }
    
    public KuaishouApis.RoomInitResult build() {
      return new KuaishouApis.RoomInitResult(this.token, this.liveStreamId, this.websocketUrls);
    }
    
    public String toString() {
      return "KuaishouApis.RoomInitResult.RoomInitResultBuilder(token=" + this.token + ", liveStreamId=" + this.liveStreamId + ", websocketUrls=" + this.websocketUrls + ")";
    }
  }
  
  public static class GiftInfo {
    private String giftName;
    
    private String giftUrl;
    
    public void setGiftName(String giftName) {
      this.giftName = giftName;
    }
    
    public void setGiftUrl(String giftUrl) {
      this.giftUrl = giftUrl;
    }
    
    public boolean equals(Object o) {
      if (o == this)
        return true; 
      if (!(o instanceof GiftInfo))
        return false; 
      GiftInfo other = (GiftInfo)o;
      if (!other.canEqual(this))
        return false; 
      Object this$giftName = getGiftName(), other$giftName = other.getGiftName();
      if ((this$giftName == null) ? (other$giftName != null) : !this$giftName.equals(other$giftName))
        return false; 
      Object this$giftUrl = getGiftUrl(), other$giftUrl = other.getGiftUrl();
      return !((this$giftUrl == null) ? (other$giftUrl != null) : !this$giftUrl.equals(other$giftUrl));
    }
    
    protected boolean canEqual(Object other) {
      return other instanceof GiftInfo;
    }
    
    public int hashCode() {
      int PRIME = 59;
      result = 1;
      Object $giftName = getGiftName();
      result = result * 59 + (($giftName == null) ? 43 : $giftName.hashCode());
      Object $giftUrl = getGiftUrl();
      return result * 59 + (($giftUrl == null) ? 43 : $giftUrl.hashCode());
    }
    
    public String toString() {
      return "KuaishouApis.GiftInfo(giftName=" + getGiftName() + ", giftUrl=" + getGiftUrl() + ")";
    }
    
    public GiftInfo(String giftName, String giftUrl) {
      this.giftName = giftName;
      this.giftUrl = giftUrl;
    }
    
    public GiftInfo() {}
    
    public String getGiftName() {
      return this.giftName;
    }
    
    public String getGiftUrl() {
      return this.giftUrl;
    }
  }
}
