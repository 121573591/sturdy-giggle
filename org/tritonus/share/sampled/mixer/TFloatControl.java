package org.tritonus.share.sampled.mixer;

import javax.sound.sampled.FloatControl;
import org.tritonus.share.TDebug;

public class TFloatControl extends FloatControl implements TControllable {
  private TControlController m_controller;
  
  public TFloatControl(FloatControl.Type type, float fMinimum, float fMaximum, float fPrecision, int nUpdatePeriod, float fInitialValue, String strUnits) {
    super(type, fMinimum, fMaximum, fPrecision, nUpdatePeriod, fInitialValue, strUnits);
    if (TDebug.TraceControl)
      TDebug.out("TFloatControl.<init>: begin"); 
    this.m_controller = new TControlController();
    if (TDebug.TraceControl)
      TDebug.out("TFloatControl.<init>: end"); 
  }
  
  public TFloatControl(FloatControl.Type type, float fMinimum, float fMaximum, float fPrecision, int nUpdatePeriod, float fInitialValue, String strUnits, String strMinLabel, String strMidLabel, String strMaxLabel) {
    super(type, fMinimum, fMaximum, fPrecision, nUpdatePeriod, fInitialValue, strUnits, strMinLabel, strMidLabel, strMaxLabel);
    if (TDebug.TraceControl)
      TDebug.out("TFloatControl.<init>: begin"); 
    this.m_controller = new TControlController();
    if (TDebug.TraceControl)
      TDebug.out("TFloatControl.<init>: end"); 
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
