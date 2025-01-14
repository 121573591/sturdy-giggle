package org.tritonus.share.sampled.mixer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.sound.sampled.Mixer;
import javax.sound.sampled.spi.MixerProvider;
import org.tritonus.share.TDebug;

public abstract class TMixerProvider extends MixerProvider {
  private static final Mixer.Info[] EMPTY_MIXER_INFO_ARRAY = new Mixer.Info[0];
  
  private static Map<Class, MixerProviderStruct> sm_mixerProviderStructs = (Map)new HashMap<Class<?>, MixerProviderStruct>();
  
  private boolean m_bDisabled = false;
  
  public TMixerProvider() {
    if (TDebug.TraceMixerProvider)
      TDebug.out("TMixerProvider.<init>(): begin"); 
    if (TDebug.TraceMixerProvider)
      TDebug.out("TMixerProvider.<init>(): end"); 
  }
  
  protected void staticInit() {}
  
  private MixerProviderStruct getMixerProviderStruct() {
    if (TDebug.TraceMixerProvider)
      TDebug.out("TMixerProvider.getMixerProviderStruct(): begin"); 
    Class<?> cls = getClass();
    if (TDebug.TraceMixerProvider)
      TDebug.out("TMixerProvider.getMixerProviderStruct(): called from " + cls); 
    synchronized (TMixerProvider.class) {
      MixerProviderStruct struct = sm_mixerProviderStructs.get(cls);
      if (struct == null) {
        if (TDebug.TraceMixerProvider)
          TDebug.out("TMixerProvider.getMixerProviderStruct(): creating new MixerProviderStruct for " + cls); 
        struct = new MixerProviderStruct();
        sm_mixerProviderStructs.put(cls, struct);
      } 
      if (TDebug.TraceMixerProvider)
        TDebug.out("TMixerProvider.getMixerProviderStruct(): end"); 
      return struct;
    } 
  }
  
  protected void disable() {
    if (TDebug.TraceMixerProvider)
      TDebug.out("disabling " + getClass().getName()); 
    this.m_bDisabled = true;
  }
  
  protected boolean isDisabled() {
    return this.m_bDisabled;
  }
  
  protected void addMixer(Mixer mixer) {
    if (TDebug.TraceMixerProvider)
      TDebug.out("TMixerProvider.addMixer(): begin"); 
    MixerProviderStruct struct = getMixerProviderStruct();
    synchronized (struct) {
      struct.m_mixers.add(mixer);
      if (struct.m_defaultMixer == null)
        struct.m_defaultMixer = mixer; 
    } 
    if (TDebug.TraceMixerProvider)
      TDebug.out("TMixerProvider.addMixer(): end"); 
  }
  
  protected void removeMixer(Mixer mixer) {
    if (TDebug.TraceMixerProvider)
      TDebug.out("TMixerProvider.removeMixer(): begin"); 
    MixerProviderStruct struct = getMixerProviderStruct();
    synchronized (struct) {
      struct.m_mixers.remove(mixer);
      if (struct.m_defaultMixer == mixer)
        struct.m_defaultMixer = null; 
    } 
    if (TDebug.TraceMixerProvider)
      TDebug.out("TMixerProvider.removeMixer(): end"); 
  }
  
  public boolean isMixerSupported(Mixer.Info info) {
    if (TDebug.TraceMixerProvider)
      TDebug.out("TMixerProvider.isMixerSupported(): begin"); 
    boolean bIsSupported = false;
    Mixer.Info[] infos = getMixerInfo();
    for (int i = 0; i < infos.length; i++) {
      if (infos[i].equals(info)) {
        bIsSupported = true;
        break;
      } 
    } 
    if (TDebug.TraceMixerProvider)
      TDebug.out("TMixerProvider.isMixerSupported(): end"); 
    return bIsSupported;
  }
  
  public Mixer getMixer(Mixer.Info info) {
    if (TDebug.TraceMixerProvider)
      TDebug.out("TMixerProvider.getMixer(): begin"); 
    MixerProviderStruct struct = getMixerProviderStruct();
    Mixer mixerResult = null;
    synchronized (struct) {
      if (info == null) {
        mixerResult = struct.m_defaultMixer;
      } else {
        Iterator<Mixer> mixers = struct.m_mixers.iterator();
        while (mixers.hasNext()) {
          Mixer mixer = mixers.next();
          if (mixer.getMixerInfo().equals(info)) {
            mixerResult = mixer;
            break;
          } 
        } 
      } 
    } 
    if (mixerResult == null)
      throw new IllegalArgumentException("no mixer available for " + info); 
    if (TDebug.TraceMixerProvider)
      TDebug.out("TMixerProvider.getMixer(): end"); 
    return mixerResult;
  }
  
  public Mixer.Info[] getMixerInfo() {
    if (TDebug.TraceMixerProvider)
      TDebug.out("TMixerProvider.getMixerInfo(): begin"); 
    Set<Mixer.Info> mixerInfos = new HashSet<Mixer.Info>();
    MixerProviderStruct struct = getMixerProviderStruct();
    synchronized (struct) {
      Iterator<Mixer> mixers = struct.m_mixers.iterator();
      while (mixers.hasNext()) {
        Mixer mixer = mixers.next();
        mixerInfos.add(mixer.getMixerInfo());
      } 
    } 
    if (TDebug.TraceMixerProvider)
      TDebug.out("TMixerProvider.getMixerInfo(): end"); 
    return mixerInfos.<Mixer.Info>toArray(EMPTY_MIXER_INFO_ARRAY);
  }
  
  private class MixerProviderStruct {
    public List<Mixer> m_mixers = new ArrayList<Mixer>();
    
    public Mixer m_defaultMixer = null;
  }
}
