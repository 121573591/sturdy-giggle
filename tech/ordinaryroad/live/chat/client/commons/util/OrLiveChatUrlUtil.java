package tech.ordinaryroad.live.chat.client.commons.util;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import java.util.function.Function;

public class OrLiveChatUrlUtil extends URLUtil {
  private static final String PATTERN_URI_SCHEME = "([a-zA-Z]+):\\/\\/";
  
  private static final String PATTERN_URI_PORT = "[a-zA-Z]+:\\/\\/.+:([0-9]+)";
  
  public static String getScheme(String location) {
    return ReUtil.getGroup1("([a-zA-Z]+):\\/\\/", location);
  }
  
  public static int getPort(String location, Function<String, Integer> defaultPortSupplier) {
    location = StrUtil.subBefore(location, "?", false);
    int port = -1;
    int i = NumberUtil.parseInt(ReUtil.getGroup1("[a-zA-Z]+:\\/\\/.+:([0-9]+)", location));
    if (i > 0) {
      port = i;
    } else if (defaultPortSupplier != null) {
      port = ((Integer)defaultPortSupplier.apply(location)).intValue();
    } 
    return port;
  }
  
  public static int getPort(String location) {
    return getPort(location, null);
  }
  
  public static int getWebSocketUriPort(String location) {
    return getPort(location, string -> {
          String scheme = getScheme(location);
          return "wss".equalsIgnoreCase(scheme) ? Integer.valueOf(443) : ("ws".equalsIgnoreCase(scheme) ? Integer.valueOf(80) : Integer.valueOf(-1));
        });
  }
}
