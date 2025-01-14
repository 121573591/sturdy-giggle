package tech.ordinaryroad.live.chat.client.codec.douyin.api;

import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpResponse;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tech.ordinaryroad.live.chat.client.codec.douyin.constant.DouyinGiftCountCalculationTimeEnum;
import tech.ordinaryroad.live.chat.client.codec.douyin.constant.DouyinRoomStatusEnum;
import tech.ordinaryroad.live.chat.client.codec.douyin.msg.DouyinGiftMsg;
import tech.ordinaryroad.live.chat.client.codec.douyin.protobuf.GiftMessage;
import tech.ordinaryroad.live.chat.client.commons.util.OrLiveChatCookieUtil;
import tech.ordinaryroad.live.chat.client.commons.util.OrLiveChatHttpUtil;

public class DouyinApis {
  private static final Logger log = LoggerFactory.getLogger(DouyinApis.class);
  
  public static final String KEY_COOKIE_TTWID = "ttwid";
  
  public static final String KEY_COOKIE_MS_TOKEN = "msToken";
  
  public static final String KEY_COOKIE_AC_NONCE = "__ac_nonce";
  
  public static final String MS_TOKEN_BASE_STRING = "abcdefghijklmnopqrstuvwxyz0123456789=_";
  
  public static final int MS_TOKEN_LENGTH = 116;
  
  public static final int AC_NONCE_LENGTH = 21;
  
  public static final String PATTERN_USER_UNIQUE_ID = "\\\\\"user_unique_id\\\\\":\\\\\"(\\d+)\\\\\"";
  
  public static final String PATTERN_ROOM_ID = "\\\\\"roomId\\\\\":\\\\\"(\\d+)\\\\\"";
  
  public static final String PATTERN_ROOM_STATUS = "\\\\\"status_str\\\\\":\\\\\"(\\d+)\\\\\"";
  
  private static final TimedCache<String, GiftMessage> DOUYIN_GIFT_MSG_CACHE = new TimedCache(300000L, new ConcurrentHashMap<>());
  
  public static RoomInitResult roomInit(Object roomId, String cookie, RoomInitResult roomInitResult) {
    Map<String, String> cookieMap = OrLiveChatCookieUtil.parseCookieString(cookie);
    HttpResponse response1 = OrLiveChatHttpUtil.createGet("https://live.douyin.com/").cookie(cookie).execute();
    try {
      String ttwid = OrLiveChatCookieUtil.getCookieByName(cookieMap, "ttwid", () -> response1.getCookie("ttwid").getValue());
      String msToken = OrLiveChatCookieUtil.getCookieByName(cookieMap, "msToken", () -> RandomUtil.randomString("abcdefghijklmnopqrstuvwxyz0123456789=_", 116));
      String __ac_nonce = OrLiveChatCookieUtil.getCookieByName(cookieMap, "__ac_nonce", () -> RandomUtil.randomString(21));
      HttpResponse response2 = OrLiveChatHttpUtil.createGet("https://live.douyin.com/" + roomId).cookie(StrUtil.emptyToDefault(cookie, "ttwid=" + ttwid + "; " + "msToken" + "=" + msToken + "; " + "__ac_nonce" + "=" + __ac_nonce)).execute();
    } finally {
      if (Collections.<HttpResponse>singletonList(response1).get(0) != null)
        response1.close(); 
    } 
  }
  
  public static RoomInitResult roomInit(Object roomId, String cookie) {
    return roomInit(roomId, cookie, null);
  }
  
  public static RoomInitResult roomInit(Object roomId) {
    return roomInit(roomId, null, null);
  }
  
  public static RoomInitResult roomInit(Object roomId, RoomInitResult roomInitResult) {
    return roomInit(roomId, null, roomInitResult);
  }
  
  public static int calculateGiftCount(DouyinGiftMsg msg, DouyinGiftCountCalculationTimeEnum calculationTimeEnum) {
    if (msg == null || msg.getMsg() == null)
      return 0; 
    GiftMessage giftMessage = msg.getMsg();
    long giftCount = 0L;
    if (calculationTimeEnum == DouyinGiftCountCalculationTimeEnum.COMBO_END) {
      if (!giftMessage.getGift().getCombo() || giftMessage.getRepeatEnd() == 1) {
        long comboCount = giftMessage.getComboCount();
        if (giftMessage.getGroupCount() != 1L)
          comboCount = giftMessage.getGroupCount() * comboCount; 
        giftCount = comboCount;
      } 
    } else {
      long groupId = giftMessage.getGroupId();
      long giftId = giftMessage.getGiftId();
      String key = groupId + "-" + msg.getUid() + "-" + giftId;
      if (DOUYIN_GIFT_MSG_CACHE.containsKey(key)) {
        GiftMessage giftMessageByGroupId = (GiftMessage)DOUYIN_GIFT_MSG_CACHE.get(key);
        long repeatCountByGroupId = giftMessageByGroupId.getRepeatCount();
        giftCount = giftMessage.getRepeatCount() - repeatCountByGroupId;
      } else {
        giftCount = giftMessage.getRepeatCount();
      } 
      if (giftCount > 0L)
        DOUYIN_GIFT_MSG_CACHE.put(key, giftMessage); 
    } 
    msg.setCalculatedGiftCount((int)giftCount);
    return (int)giftCount;
  }
  
  public static class RoomInitResult {
    private String ttwid;
    
    private String msToken;
    
    private String acNonce;
    
    private long realRoomId;
    
    private String userUniqueId;
    
    private DouyinRoomStatusEnum roomStatus;
    
    public void setTtwid(String ttwid) {
      this.ttwid = ttwid;
    }
    
    public void setMsToken(String msToken) {
      this.msToken = msToken;
    }
    
    public void setAcNonce(String acNonce) {
      this.acNonce = acNonce;
    }
    
    public void setRealRoomId(long realRoomId) {
      this.realRoomId = realRoomId;
    }
    
    public void setUserUniqueId(String userUniqueId) {
      this.userUniqueId = userUniqueId;
    }
    
    public void setRoomStatus(DouyinRoomStatusEnum roomStatus) {
      this.roomStatus = roomStatus;
    }
    
    public RoomInitResult(String ttwid, String msToken, String acNonce, long realRoomId, String userUniqueId, DouyinRoomStatusEnum roomStatus) {
      this.ttwid = ttwid;
      this.msToken = msToken;
      this.acNonce = acNonce;
      this.realRoomId = realRoomId;
      this.userUniqueId = userUniqueId;
      this.roomStatus = roomStatus;
    }
    
    public RoomInitResult() {}
    
    public static RoomInitResultBuilder builder() {
      return new RoomInitResultBuilder();
    }
    
    public static class RoomInitResultBuilder {
      private String ttwid;
      
      private String msToken;
      
      private String acNonce;
      
      private long realRoomId;
      
      private String userUniqueId;
      
      private DouyinRoomStatusEnum roomStatus;
      
      public RoomInitResultBuilder ttwid(String ttwid) {
        this.ttwid = ttwid;
        return this;
      }
      
      public RoomInitResultBuilder msToken(String msToken) {
        this.msToken = msToken;
        return this;
      }
      
      public RoomInitResultBuilder acNonce(String acNonce) {
        this.acNonce = acNonce;
        return this;
      }
      
      public RoomInitResultBuilder realRoomId(long realRoomId) {
        this.realRoomId = realRoomId;
        return this;
      }
      
      public RoomInitResultBuilder userUniqueId(String userUniqueId) {
        this.userUniqueId = userUniqueId;
        return this;
      }
      
      public RoomInitResultBuilder roomStatus(DouyinRoomStatusEnum roomStatus) {
        this.roomStatus = roomStatus;
        return this;
      }
      
      public DouyinApis.RoomInitResult build() {
        return new DouyinApis.RoomInitResult(this.ttwid, this.msToken, this.acNonce, this.realRoomId, this.userUniqueId, this.roomStatus);
      }
      
      public String toString() {
        return "DouyinApis.RoomInitResult.RoomInitResultBuilder(ttwid=" + this.ttwid + ", msToken=" + this.msToken + ", acNonce=" + this.acNonce + ", realRoomId=" + this.realRoomId + ", userUniqueId=" + this.userUniqueId + ", roomStatus=" + this.roomStatus + ")";
      }
    }
    
    public String getTtwid() {
      return this.ttwid;
    }
    
    public String getMsToken() {
      return this.msToken;
    }
    
    public String getAcNonce() {
      return this.acNonce;
    }
    
    public long getRealRoomId() {
      return this.realRoomId;
    }
    
    public String getUserUniqueId() {
      return this.userUniqueId;
    }
    
    public DouyinRoomStatusEnum getRoomStatus() {
      return this.roomStatus;
    }
  }
  
  public static class RoomInitResultBuilder {
    private String ttwid;
    
    private String msToken;
    
    private String acNonce;
    
    private long realRoomId;
    
    private String userUniqueId;
    
    private DouyinRoomStatusEnum roomStatus;
    
    public RoomInitResultBuilder ttwid(String ttwid) {
      this.ttwid = ttwid;
      return this;
    }
    
    public RoomInitResultBuilder msToken(String msToken) {
      this.msToken = msToken;
      return this;
    }
    
    public RoomInitResultBuilder acNonce(String acNonce) {
      this.acNonce = acNonce;
      return this;
    }
    
    public RoomInitResultBuilder realRoomId(long realRoomId) {
      this.realRoomId = realRoomId;
      return this;
    }
    
    public RoomInitResultBuilder userUniqueId(String userUniqueId) {
      this.userUniqueId = userUniqueId;
      return this;
    }
    
    public RoomInitResultBuilder roomStatus(DouyinRoomStatusEnum roomStatus) {
      this.roomStatus = roomStatus;
      return this;
    }
    
    public DouyinApis.RoomInitResult build() {
      return new DouyinApis.RoomInitResult(this.ttwid, this.msToken, this.acNonce, this.realRoomId, this.userUniqueId, this.roomStatus);
    }
    
    public String toString() {
      return "DouyinApis.RoomInitResult.RoomInitResultBuilder(ttwid=" + this.ttwid + ", msToken=" + this.msToken + ", acNonce=" + this.acNonce + ", realRoomId=" + this.realRoomId + ", userUniqueId=" + this.userUniqueId + ", roomStatus=" + this.roomStatus + ")";
    }
  }
}
