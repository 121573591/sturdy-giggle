package org.tritonus.share.sampled.mixer;

import javax.sound.sampled.BooleanControl;
import org.tritonus.share.TDebug;

public class TBooleanControl extends BooleanControl implements TControllable {
  private TControlController m_controller;
  
  public TBooleanControl(BooleanControl.Type type, boolean bInitialValue) {
    this(type, bInitialValue, null);
  }
  
  public TBooleanControl(BooleanControl.Type type, boolean bInitialValue, TCompoundControl parentControl) {
    super(type, bInitialValue);
    if (TDebug.TraceControl)
      TDebug.out("TBooleanControl.<init>: begin"); 
    this.m_controller = new TControlController();
    if (TDebug.TraceControl)
      TDebug.out("TBooleanControl.<init>: end"); 
  }
  
  public TBooleanControl(BooleanControl.Type type, boolean bInitialValue, String strTrueStateLabel, String strFalseStateLabel) {
    this(type, bInitialValue, strTrueStateLabel, strFalseStateLabel, null);
  }
  
  public TBooleanControl(BooleanControl.Type type, boolean bInitialValue, String strTrueStateLabel, String strFalseStateLabel, TCompoundControl parentControl) {
    super(type, bInitialValue, strTrueStateLabel, strFalseStateLabel);
    if (TDebug.TraceControl)
      TDebug.out("TBooleanControl.<init>: begin"); 
    this.m_controller = new TControlController();
    if (TDebug.TraceControl)
      TDebug.out("TBooleanControl.<init>: end"); 
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
