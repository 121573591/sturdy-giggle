package org.tritonus.share.sampled.mixer;

import javax.sound.sampled.EnumControl;
import org.tritonus.share.TDebug;

public class TEnumControl extends EnumControl implements TControllable {
  private TControlController m_controller;
  
  public TEnumControl(EnumControl.Type type, Object[] aValues, Object value) {
    super(type, aValues, value);
    if (TDebug.TraceControl)
      TDebug.out("TEnumControl.<init>: begin"); 
    this.m_controller = new TControlController();
    if (TDebug.TraceControl)
      TDebug.out("TEnumControl.<init>: end"); 
  }
  
  public void setParentControl(TCompoundControl compoundControl) {
    this.m_controller.setParentControl(compoundControl);
  }
  
  public TCompoundControl getParentControl() {
    return this.m_controller.getParentControl();
  }
  
  public void commit() {
    this.m_controller.commit();
  }
}
