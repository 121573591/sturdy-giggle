package cn.hutool.extra.pinyin.engine;

import cn.hutool.core.lang.Singleton;
import cn.hutool.core.util.ServiceLoaderUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.pinyin.PinyinEngine;
import cn.hutool.extra.pinyin.PinyinException;
import cn.hutool.log.StaticLog;
import java.lang.invoke.SerializedLambda;

public class PinyinFactory {
  public static PinyinEngine get() {
    return (PinyinEngine)Singleton.get(PinyinEngine.class.getName(), PinyinFactory::create);
  }
  
  public static PinyinEngine create() {
    PinyinEngine engine = doCreate();
    StaticLog.debug("Use [{}] Engine As Default.", new Object[] { StrUtil.removeSuffix(engine.getClass().getSimpleName(), "Engine") });
    return engine;
  }
  
  private static PinyinEngine doCreate() {
    PinyinEngine engine = (PinyinEngine)ServiceLoaderUtil.loadFirstAvailable(PinyinEngine.class);
    if (null != engine)
      return engine; 
    throw new PinyinException("No pinyin jar found ! Please add one of it to your project !");
  }
}
