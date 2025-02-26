package cn.hutool.extra.spring;

import cn.hutool.core.exceptions.UtilException;
import cn.hutool.core.lang.TypeReference;
import cn.hutool.core.util.ArrayUtil;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Map;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.ResolvableType;
import org.springframework.stereotype.Component;

@Component
public class SpringUtil implements BeanFactoryPostProcessor, ApplicationContextAware {
  private static ConfigurableListableBeanFactory beanFactory;
  
  private static ApplicationContext applicationContext;
  
  public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
    SpringUtil.beanFactory = beanFactory;
  }
  
  public void setApplicationContext(ApplicationContext applicationContext) {
    SpringUtil.applicationContext = applicationContext;
  }
  
  public static ApplicationContext getApplicationContext() {
    return applicationContext;
  }
  
  public static ListableBeanFactory getBeanFactory() {
    ListableBeanFactory factory = (null == beanFactory) ? (ListableBeanFactory)applicationContext : (ListableBeanFactory)beanFactory;
    if (null == factory)
      throw new UtilException("No ConfigurableListableBeanFactory or ApplicationContext injected, maybe not in the Spring environment?"); 
    return factory;
  }
  
  public static ConfigurableListableBeanFactory getConfigurableBeanFactory() throws UtilException {
    ConfigurableListableBeanFactory factory;
    if (null != beanFactory) {
      factory = beanFactory;
    } else if (applicationContext instanceof ConfigurableApplicationContext) {
      factory = ((ConfigurableApplicationContext)applicationContext).getBeanFactory();
    } else {
      throw new UtilException("No ConfigurableListableBeanFactory from context!");
    } 
    return factory;
  }
  
  public static <T> T getBean(String name) {
    return (T)getBeanFactory().getBean(name);
  }
  
  public static <T> T getBean(Class<T> clazz) {
    return (T)getBeanFactory().getBean(clazz);
  }
  
  public static <T> T getBean(String name, Class<T> clazz) {
    return (T)getBeanFactory().getBean(name, clazz);
  }
  
  public static <T> T getBean(TypeReference<T> reference) {
    ParameterizedType parameterizedType = (ParameterizedType)reference.getType();
    Class<T> rawType = (Class<T>)parameterizedType.getRawType();
    Class<?>[] genericTypes = (Class[])Arrays.<Type>stream(parameterizedType.getActualTypeArguments()).map(type -> (Class)type).toArray(x$0 -> new Class[x$0]);
    String[] beanNames = getBeanFactory().getBeanNamesForType(ResolvableType.forClassWithGenerics(rawType, genericTypes));
    return getBean(beanNames[0], rawType);
  }
  
  public static <T> Map<String, T> getBeansOfType(Class<T> type) {
    return getBeanFactory().getBeansOfType(type);
  }
  
  public static String[] getBeanNamesForType(Class<?> type) {
    return getBeanFactory().getBeanNamesForType(type);
  }
  
  public static String getProperty(String key) {
    if (null == applicationContext)
      return null; 
    return applicationContext.getEnvironment().getProperty(key);
  }
  
  public static String getProperty(String key, String defaultValue) {
    if (null == applicationContext)
      return null; 
    return applicationContext.getEnvironment().getProperty(key, defaultValue);
  }
  
  public static <T> T getProperty(String key, Class<T> targetType, T defaultValue) {
    if (null == applicationContext)
      return null; 
    return (T)applicationContext.getEnvironment().getProperty(key, targetType, defaultValue);
  }
  
  public static String getApplicationName() {
    return getProperty("spring.application.name");
  }
  
  public static String[] getActiveProfiles() {
    if (null == applicationContext)
      return null; 
    return applicationContext.getEnvironment().getActiveProfiles();
  }
  
  public static String getActiveProfile() {
    String[] activeProfiles = getActiveProfiles();
    return ArrayUtil.isNotEmpty((Object[])activeProfiles) ? activeProfiles[0] : null;
  }
  
  public static <T> void registerBean(String beanName, T bean) {
    ConfigurableListableBeanFactory factory = getConfigurableBeanFactory();
    factory.autowireBean(bean);
    factory.registerSingleton(beanName, bean);
  }
  
  public static void unregisterBean(String beanName) {
    ConfigurableListableBeanFactory factory = getConfigurableBeanFactory();
    if (factory instanceof DefaultSingletonBeanRegistry) {
      DefaultSingletonBeanRegistry registry = (DefaultSingletonBeanRegistry)factory;
      registry.destroySingleton(beanName);
    } else {
      throw new UtilException("Can not unregister bean, the factory is not a DefaultSingletonBeanRegistry!");
    } 
  }
  
  public static void publishEvent(ApplicationEvent event) {
    if (null != applicationContext)
      applicationContext.publishEvent(event); 
  }
  
  public static void publishEvent(Object event) {
    if (null != applicationContext)
      applicationContext.publishEvent(event); 
  }
}
