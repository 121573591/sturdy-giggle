package org.tritonus.share.sampled.mixer;

import javax.sound.sampled.CompoundControl;
import javax.sound.sampled.Control;
import org.tritonus.share.TDebug;

public class TCompoundControl extends CompoundControl implements TControllable {
  private TControlController m_controller;
  
  public TCompoundControl(CompoundControl.Type type, Control[] aMemberControls) {
    super(type, aMemberControls);
    if (TDebug.TraceControl)
      TDebug.out("TCompoundControl.<init>: begin"); 
    this.m_controller = new TControlController();
    if (TDebug.TraceControl)
      TDebug.out("TCompoundControl.<init>: end"); 
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
