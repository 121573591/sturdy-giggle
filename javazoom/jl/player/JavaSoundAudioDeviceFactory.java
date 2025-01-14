package javazoom.jl.player;

import javazoom.jl.decoder.JavaLayerException;

public class JavaSoundAudioDeviceFactory extends AudioDeviceFactory {
  private boolean tested = false;
  
  private static final String DEVICE_CLASS_NAME = "javazoom.jl.player.JavaSoundAudioDevice";
  
  public synchronized AudioDevice createAudioDevice() throws JavaLayerException {
    if (!this.tested) {
      testAudioDevice();
      this.tested = true;
    } 
    try {
      return createAudioDeviceImpl();
    } catch (Exception ex) {
      throw new JavaLayerException("unable to create JavaSound device: " + ex);
    } catch (LinkageError ex) {
      throw new JavaLayerException("unable to create JavaSound device: " + ex);
    } 
  }
  
  protected JavaSoundAudioDevice createAudioDeviceImpl() throws JavaLayerException {
    ClassLoader loader = getClass().getClassLoader();
    try {
      JavaSoundAudioDevice dev = (JavaSoundAudioDevice)instantiate(loader, "javazoom.jl.player.JavaSoundAudioDevice");
      return dev;
    } catch (Exception ex) {
      throw new JavaLayerException("Cannot create JavaSound device", ex);
    } catch (LinkageError ex) {
      throw new JavaLayerException("Cannot create JavaSound device", ex);
    } 
  }
  
  public void testAudioDevice() throws JavaLayerException {
    JavaSoundAudioDevice dev = createAudioDeviceImpl();
    dev.test();
  }
}
