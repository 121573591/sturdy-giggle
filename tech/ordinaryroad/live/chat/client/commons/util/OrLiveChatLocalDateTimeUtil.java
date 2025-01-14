package tech.ordinaryroad.live.chat.client.commons.util;

import cn.hutool.core.date.LocalDateTimeUtil;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class OrLiveChatLocalDateTimeUtil extends LocalDateTimeUtil {
  public static ZoneId ZONE_ID_CTT = ZoneId.of(ZoneId.SHORT_IDS.get("CTT"));
  
  public static long zonedCurrentTimeMillis() {
    ZonedDateTime now = ZonedDateTime.now(ZONE_ID_CTT);
    return now.toEpochSecond() * 1000L + (now.getNano() / 1000000);
  }
  
  public static long zonedCurrentTimeSecs() {
    return ZonedDateTime.now(ZONE_ID_CTT).toEpochSecond();
  }
}
