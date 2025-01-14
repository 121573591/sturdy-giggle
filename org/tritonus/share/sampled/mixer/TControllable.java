package org.tritonus.share.sampled.mixer;

public interface TControllable {
  void setParentControl(TCompoundControl paramTCompoundControl);
  
  TCompoundControl getParentControl();
  
  void commit();
}
