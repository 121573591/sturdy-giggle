package javazoom.jl.player;

import java.util.Enumeration;
import java.util.Hashtable;
import javazoom.jl.decoder.JavaLayerException;

public class FactoryRegistry extends AudioDeviceFactory {
  private static FactoryRegistry instance = null;
  
  public static synchronized FactoryRegistry systemRegistry() {
    if (instance == null) {
      instance = new FactoryRegistry();
      instance.registerDefaultFactories();
    } 
    return instance;
  }
  
  protected Hashtable factories = new Hashtable<Object, Object>();
  
  public void addFactory(AudioDeviceFactory factory) {
    this.factories.put(factory.getClass(), factory);
  }
  
  public void removeFactoryType(Class cls) {
    this.factories.remove(cls);
  }
  
  public void removeFactory(AudioDeviceFactory factory) {
    this.factories.remove(factory.getClass());
  }
  
  public AudioDevice createAudioDevice() throws JavaLayerException {
    AudioDevice device = null;
    AudioDeviceFactory[] factories = getFactoriesPriority();
    if (factories == null)
      throw new JavaLayerException(this + ": no factories registered"); 
    JavaLayerException lastEx = null;
    for (int i = 0; device == null && i < factories.length; i++) {
      try {
        device = factories[i].createAudioDevice();
      } catch (JavaLayerException ex) {
        lastEx = ex;
      } 
    } 
    if (device == null && lastEx != null)
      throw new JavaLayerException("Cannot create AudioDevice", lastEx); 
    return device;
  }
  
  protected AudioDeviceFactory[] getFactoriesPriority() {
    AudioDeviceFactory[] fa = null;
    synchronized (this.factories) {
      int size = this.factories.size();
      if (size != 0) {
        fa = new AudioDeviceFactory[size];
        int idx = 0;
        Enumeration<AudioDeviceFactory> e = this.factories.elements();
        while (e.hasMoreElements()) {
          AudioDeviceFactory factory = e.nextElement();
          fa[idx++] = factory;
        } 
      } 
    } 
    return fa;
  }
  
  protected void registerDefaultFactories() {
    addFactory(new JavaSoundAudioDeviceFactory());
  }
}
