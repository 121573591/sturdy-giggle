package tech.ordinaryroad.live.chat.client.commons.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import java.util.List;

public class OrLiveChatCollUtil extends CollUtil {
  public static <T> T getRandom(List<T> list) {
    return (T)CollUtil.get(list, RandomUtil.randomInt(CollUtil.size(list)));
  }
}
