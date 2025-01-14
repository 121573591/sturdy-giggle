package cn.hutool.core.lang;

import java.io.Serializable;

public interface EnumItem<E extends EnumItem<E>> extends Serializable {
  String name();
  
  default String text() {
    return name();
  }
  
  int intVal();
  
  default E[] items() {
    return (E[])getClass().getEnumConstants();
  }
  
  default E fromInt(Integer intVal) {
    if (intVal == null)
      return null; 
    E[] vs = items();
    for (E enumItem : vs) {
      if (enumItem.intVal() == intVal.intValue())
        return enumItem; 
    } 
    return null;
  }
  
  default E fromStr(String strVal) {
    if (strVal == null)
      return null; 
    E[] vs = items();
    for (E enumItem : vs) {
      if (strVal.equalsIgnoreCase(enumItem.name()))
        return enumItem; 
    } 
    return null;
  }
}
