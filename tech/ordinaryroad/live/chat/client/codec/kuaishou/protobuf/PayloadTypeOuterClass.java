package tech.ordinaryroad.live.chat.client.codec.kuaishou.protobuf;

import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistry;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.Internal;
import com.google.protobuf.ProtocolMessageEnum;

public final class PayloadTypeOuterClass {
  private static Descriptors.FileDescriptor descriptor;
  
  public static void registerAllExtensions(ExtensionRegistryLite registry) {}
  
  public static void registerAllExtensions(ExtensionRegistry registry) {
    registerAllExtensions((ExtensionRegistryLite)registry);
  }
  
  public enum PayloadType implements ProtocolMessageEnum {
    UNKNOWN(0),
    CS_HEARTBEAT(1),
    CS_ERROR(3),
    CS_PING(4),
    PS_HOST_INFO(51),
    SC_HEARTBEAT_ACK(101),
    SC_ECHO(102),
    SC_ERROR(103),
    SC_PING_ACK(104),
    SC_INFO(105),
    CS_ENTER_ROOM(200),
    CS_USER_PAUSE(201),
    CS_USER_EXIT(202),
    CS_AUTHOR_PUSH_TRAFFIC_ZERO(203),
    CS_HORSE_RACING(204),
    CS_RACE_LOSE(205),
    CS_VOIP_SIGNAL(206),
    SC_ENTER_ROOM_ACK(300),
    SC_AUTHOR_PAUSE(301),
    SC_AUTHOR_RESUME(302),
    SC_AUTHOR_PUSH_TRAFFIC_ZERO(303),
    SC_AUTHOR_HEARTBEAT_MISS(304),
    SC_PIP_STARTED(305),
    SC_PIP_ENDED(306),
    SC_HORSE_RACING_ACK(307),
    SC_VOIP_SIGNAL(308),
    SC_FEED_PUSH(310),
    SC_ASSISTANT_STATUS(311),
    SC_REFRESH_WALLET(312),
    SC_LIVE_CHAT_CALL(320),
    SC_LIVE_CHAT_CALL_ACCEPTED(321),
    SC_LIVE_CHAT_CALL_REJECTED(322),
    SC_LIVE_CHAT_READY(323),
    SC_LIVE_CHAT_GUEST_END(324),
    SC_LIVE_CHAT_ENDED(325),
    SC_RENDERING_MAGIC_FACE_DISABLE(326),
    SC_RENDERING_MAGIC_FACE_ENABLE(327),
    SC_RED_PACK_FEED(330),
    SC_LIVE_WATCHING_LIST(340),
    SC_LIVE_QUIZ_QUESTION_ASKED(350),
    SC_LIVE_QUIZ_QUESTION_REVIEWED(351),
    SC_LIVE_QUIZ_SYNC(352),
    SC_LIVE_QUIZ_ENDED(353),
    SC_LIVE_QUIZ_WINNERS(354),
    SC_SUSPECTED_VIOLATION(355),
    SC_SHOP_OPENED(360),
    SC_SHOP_CLOSED(361),
    SC_GUESS_OPENED(370),
    SC_GUESS_CLOSED(371),
    SC_PK_INVITATION(380),
    SC_PK_STATISTIC(381),
    SC_RIDDLE_OPENED(390),
    SC_RIDDLE_CLOESED(391),
    SC_RIDE_CHANGED(412),
    SC_BET_CHANGED(441),
    SC_BET_CLOSED(442),
    SC_LIVE_SPECIAL_ACCOUNT_CONFIG_STATE(645),
    SC_LIVE_WARNING_MASK_STATUS_CHANGED_AUDIENCE(758),
    UNRECOGNIZED(-1);
    
    public static final int UNKNOWN_VALUE = 0;
    
    public static final int CS_HEARTBEAT_VALUE = 1;
    
    public static final int CS_ERROR_VALUE = 3;
    
    public static final int CS_PING_VALUE = 4;
    
    public static final int PS_HOST_INFO_VALUE = 51;
    
    public static final int SC_HEARTBEAT_ACK_VALUE = 101;
    
    public static final int SC_ECHO_VALUE = 102;
    
    public static final int SC_ERROR_VALUE = 103;
    
    public static final int SC_PING_ACK_VALUE = 104;
    
    public static final int SC_INFO_VALUE = 105;
    
    public static final int CS_ENTER_ROOM_VALUE = 200;
    
    public static final int CS_USER_PAUSE_VALUE = 201;
    
    public static final int CS_USER_EXIT_VALUE = 202;
    
    public static final int CS_AUTHOR_PUSH_TRAFFIC_ZERO_VALUE = 203;
    
    public static final int CS_HORSE_RACING_VALUE = 204;
    
    public static final int CS_RACE_LOSE_VALUE = 205;
    
    public static final int CS_VOIP_SIGNAL_VALUE = 206;
    
    public static final int SC_ENTER_ROOM_ACK_VALUE = 300;
    
    public static final int SC_AUTHOR_PAUSE_VALUE = 301;
    
    public static final int SC_AUTHOR_RESUME_VALUE = 302;
    
    public static final int SC_AUTHOR_PUSH_TRAFFIC_ZERO_VALUE = 303;
    
    public static final int SC_AUTHOR_HEARTBEAT_MISS_VALUE = 304;
    
    public static final int SC_PIP_STARTED_VALUE = 305;
    
    public static final int SC_PIP_ENDED_VALUE = 306;
    
    public static final int SC_HORSE_RACING_ACK_VALUE = 307;
    
    public static final int SC_VOIP_SIGNAL_VALUE = 308;
    
    public static final int SC_FEED_PUSH_VALUE = 310;
    
    public static final int SC_ASSISTANT_STATUS_VALUE = 311;
    
    public static final int SC_REFRESH_WALLET_VALUE = 312;
    
    public static final int SC_LIVE_CHAT_CALL_VALUE = 320;
    
    public static final int SC_LIVE_CHAT_CALL_ACCEPTED_VALUE = 321;
    
    public static final int SC_LIVE_CHAT_CALL_REJECTED_VALUE = 322;
    
    public static final int SC_LIVE_CHAT_READY_VALUE = 323;
    
    public static final int SC_LIVE_CHAT_GUEST_END_VALUE = 324;
    
    public static final int SC_LIVE_CHAT_ENDED_VALUE = 325;
    
    public static final int SC_RENDERING_MAGIC_FACE_DISABLE_VALUE = 326;
    
    public static final int SC_RENDERING_MAGIC_FACE_ENABLE_VALUE = 327;
    
    public static final int SC_RED_PACK_FEED_VALUE = 330;
    
    public static final int SC_LIVE_WATCHING_LIST_VALUE = 340;
    
    public static final int SC_LIVE_QUIZ_QUESTION_ASKED_VALUE = 350;
    
    public static final int SC_LIVE_QUIZ_QUESTION_REVIEWED_VALUE = 351;
    
    public static final int SC_LIVE_QUIZ_SYNC_VALUE = 352;
    
    public static final int SC_LIVE_QUIZ_ENDED_VALUE = 353;
    
    public static final int SC_LIVE_QUIZ_WINNERS_VALUE = 354;
    
    public static final int SC_SUSPECTED_VIOLATION_VALUE = 355;
    
    public static final int SC_SHOP_OPENED_VALUE = 360;
    
    public static final int SC_SHOP_CLOSED_VALUE = 361;
    
    public static final int SC_GUESS_OPENED_VALUE = 370;
    
    public static final int SC_GUESS_CLOSED_VALUE = 371;
    
    public static final int SC_PK_INVITATION_VALUE = 380;
    
    public static final int SC_PK_STATISTIC_VALUE = 381;
    
    public static final int SC_RIDDLE_OPENED_VALUE = 390;
    
    public static final int SC_RIDDLE_CLOESED_VALUE = 391;
    
    public static final int SC_RIDE_CHANGED_VALUE = 412;
    
    public static final int SC_BET_CHANGED_VALUE = 441;
    
    public static final int SC_BET_CLOSED_VALUE = 442;
    
    public static final int SC_LIVE_SPECIAL_ACCOUNT_CONFIG_STATE_VALUE = 645;
    
    public static final int SC_LIVE_WARNING_MASK_STATUS_CHANGED_AUDIENCE_VALUE = 758;
    
    private static final Internal.EnumLiteMap<PayloadType> internalValueMap = new Internal.EnumLiteMap<PayloadType>() {
        public PayloadTypeOuterClass.PayloadType findValueByNumber(int number) {
          return PayloadTypeOuterClass.PayloadType.forNumber(number);
        }
      };
    
    private static final PayloadType[] VALUES = values();
    
    private final int value;
    
    public final int getNumber() {
      if (this == UNRECOGNIZED)
        throw new IllegalArgumentException("Can't get the number of an unknown enum value."); 
      return this.value;
    }
    
    public static PayloadType forNumber(int value) {
      switch (value) {
        case 0:
          return UNKNOWN;
        case 1:
          return CS_HEARTBEAT;
        case 3:
          return CS_ERROR;
        case 4:
          return CS_PING;
        case 51:
          return PS_HOST_INFO;
        case 101:
          return SC_HEARTBEAT_ACK;
        case 102:
          return SC_ECHO;
        case 103:
          return SC_ERROR;
        case 104:
          return SC_PING_ACK;
        case 105:
          return SC_INFO;
        case 200:
          return CS_ENTER_ROOM;
        case 201:
          return CS_USER_PAUSE;
        case 202:
          return CS_USER_EXIT;
        case 203:
          return CS_AUTHOR_PUSH_TRAFFIC_ZERO;
        case 204:
          return CS_HORSE_RACING;
        case 205:
          return CS_RACE_LOSE;
        case 206:
          return CS_VOIP_SIGNAL;
        case 300:
          return SC_ENTER_ROOM_ACK;
        case 301:
          return SC_AUTHOR_PAUSE;
        case 302:
          return SC_AUTHOR_RESUME;
        case 303:
          return SC_AUTHOR_PUSH_TRAFFIC_ZERO;
        case 304:
          return SC_AUTHOR_HEARTBEAT_MISS;
        case 305:
          return SC_PIP_STARTED;
        case 306:
          return SC_PIP_ENDED;
        case 307:
          return SC_HORSE_RACING_ACK;
        case 308:
          return SC_VOIP_SIGNAL;
        case 310:
          return SC_FEED_PUSH;
        case 311:
          return SC_ASSISTANT_STATUS;
        case 312:
          return SC_REFRESH_WALLET;
        case 320:
          return SC_LIVE_CHAT_CALL;
        case 321:
          return SC_LIVE_CHAT_CALL_ACCEPTED;
        case 322:
          return SC_LIVE_CHAT_CALL_REJECTED;
        case 323:
          return SC_LIVE_CHAT_READY;
        case 324:
          return SC_LIVE_CHAT_GUEST_END;
        case 325:
          return SC_LIVE_CHAT_ENDED;
        case 326:
          return SC_RENDERING_MAGIC_FACE_DISABLE;
        case 327:
          return SC_RENDERING_MAGIC_FACE_ENABLE;
        case 330:
          return SC_RED_PACK_FEED;
        case 340:
          return SC_LIVE_WATCHING_LIST;
        case 350:
          return SC_LIVE_QUIZ_QUESTION_ASKED;
        case 351:
          return SC_LIVE_QUIZ_QUESTION_REVIEWED;
        case 352:
          return SC_LIVE_QUIZ_SYNC;
        case 353:
          return SC_LIVE_QUIZ_ENDED;
        case 354:
          return SC_LIVE_QUIZ_WINNERS;
        case 355:
          return SC_SUSPECTED_VIOLATION;
        case 360:
          return SC_SHOP_OPENED;
        case 361:
          return SC_SHOP_CLOSED;
        case 370:
          return SC_GUESS_OPENED;
        case 371:
          return SC_GUESS_CLOSED;
        case 380:
          return SC_PK_INVITATION;
        case 381:
          return SC_PK_STATISTIC;
        case 390:
          return SC_RIDDLE_OPENED;
        case 391:
          return SC_RIDDLE_CLOESED;
        case 412:
          return SC_RIDE_CHANGED;
        case 441:
          return SC_BET_CHANGED;
        case 442:
          return SC_BET_CLOSED;
        case 645:
          return SC_LIVE_SPECIAL_ACCOUNT_CONFIG_STATE;
        case 758:
          return SC_LIVE_WARNING_MASK_STATUS_CHANGED_AUDIENCE;
      } 
      return null;
    }
    
    public static Internal.EnumLiteMap<PayloadType> internalGetValueMap() {
      return internalValueMap;
    }
    
    static {
    
    }
    
    public final Descriptors.EnumValueDescriptor getValueDescriptor() {
      if (this == UNRECOGNIZED)
        throw new IllegalStateException("Can't get the descriptor of an unrecognized enum value."); 
      return getDescriptor().getValues().get(ordinal());
    }
    
    public final Descriptors.EnumDescriptor getDescriptorForType() {
      return getDescriptor();
    }
    
    public static final Descriptors.EnumDescriptor getDescriptor() {
      return PayloadTypeOuterClass.getDescriptor().getEnumTypes().get(0);
    }
    
    PayloadType(int value) {
      this.value = value;
    }
  }
  
  public static Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }
  
  static {
    String[] descriptorData = { "\n\021PayloadType.proto*\013\n\013PayloadType\022\013\n\007UNKNOWN\020\000\022\020\n\fCS_HEARTBEAT\020\001\022\f\n\bCS_ERROR\020\003\022\013\n\007CS_PING\020\004\022\020\n\fPS_HOST_INFO\0203\022\024\n\020SC_HEARTBEAT_ACK\020e\022\013\n\007SC_ECHO\020f\022\f\n\bSC_ERROR\020g\022\017\n\013SC_PING_ACK\020h\022\013\n\007SC_INFO\020i\022\022\n\rCS_ENTER_ROOM\020È\001\022\022\n\rCS_USER_PAUSE\020É\001\022\021\n\fCS_USER_EXIT\020Ê\001\022 \n\033CS_AUTHOR_PUSH_TRAFFIC_ZERO\020Ë\001\022\024\n\017CS_HORSE_RACING\020Ì\001\022\021\n\fCS_RACE_LOSE\020Í\001\022\023\n\016CS_VOIP_SIGNAL\020Î\001\022\026\n\021SC_ENTER_ROOM_ACK\020¬\002\022\024\n\017SC_AUTHOR_PAUSE\020­\002\022\025\n\020SC_AUTHOR_RESUME\020®\002\022 \n\033SC_AUTHOR_PUSH_TRAFFIC_ZERO\020¯\002\022\035\n\030SC_AUTHOR_HEARTBEAT_MISS\020°\002\022\023\n\016SC_PIP_STARTED\020±\002\022\021\n\fSC_PIP_ENDED\020²\002\022\030\n\023SC_HORSE_RACING_ACK\020³\002\022\023\n\016SC_VOIP_SIGNAL\020´\002\022\021\n\fSC_FEED_PUSH\020¶\002\022\030\n\023SC_ASSISTANT_STATUS\020·\002\022\026\n\021SC_REFRESH_WALLET\020¸\002\022\026\n\021SC_LIVE_CHAT_CALL\020À\002\022\037\n\032SC_LIVE_CHAT_CALL_ACCEPTED\020Á\002\022\037\n\032SC_LIVE_CHAT_CALL_REJECTED\020Â\002\022\027\n\022SC_LIVE_CHAT_READY\020Ã\002\022\033\n\026SC_LIVE_CHAT_GUEST_END\020Ä\002\022\027\n\022SC_LIVE_CHAT_ENDED\020Å\002\022$\n\037SC_RENDERING_MAGIC_FACE_DISABLE\020Æ\002\022#\n\036SC_RENDERING_MAGIC_FACE_ENABLE\020Ç\002\022\025\n\020SC_RED_PACK_FEED\020Ê\002\022\032\n\025SC_LIVE_WATCHING_LIST\020Ô\002\022 \n\033SC_LIVE_QUIZ_QUESTION_ASKED\020Þ\002\022#\n\036SC_LIVE_QUIZ_QUESTION_REVIEWED\020ß\002\022\026\n\021SC_LIVE_QUIZ_SYNC\020à\002\022\027\n\022SC_LIVE_QUIZ_ENDED\020á\002\022\031\n\024SC_LIVE_QUIZ_WINNERS\020â\002\022\033\n\026SC_SUSPECTED_VIOLATION\020ã\002\022\023\n\016SC_SHOP_OPENED\020è\002\022\023\n\016SC_SHOP_CLOSED\020é\002\022\024\n\017SC_GUESS_OPENED\020ò\002\022\024\n\017SC_GUESS_CLOSED\020ó\002\022\025\n\020SC_PK_INVITATION\020ü\002\022\024\n\017SC_PK_STATISTIC\020ý\002\022\025\n\020SC_RIDDLE_OPENED\020\003\022\026\n\021SC_RIDDLE_CLOESED\020\003\022\024\n\017SC_RIDE_CHANGED\020\003\022\023\n\016SC_BET_CHANGED\020¹\003\022\022\n\rSC_BET_CLOSED\020º\003\022)\n$SC_LIVE_SPECIAL_ACCOUNT_CONFIG_STATE\020\005\0221\n,SC_LIVE_WARNING_MASK_STATUS_CHANGED_AUDIENCE\020ö\005B<\n:tech.ordinaryroad.live.chat.client.codec.kuaishou.protobufb\006proto3" };
    descriptor = Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(descriptorData, new Descriptors.FileDescriptor[0]);
  }
}
