package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;

public final class LaissezFaireSubTypeValidator extends PolymorphicTypeValidator.Base {
  private static final long serialVersionUID = 1L;
  
  public static final LaissezFaireSubTypeValidator instance = new LaissezFaireSubTypeValidator();
  
  public PolymorphicTypeValidator.Validity validateBaseType(MapperConfig<?> ctxt, JavaType baseType) {
    return PolymorphicTypeValidator.Validity.INDETERMINATE;
  }
  
  public PolymorphicTypeValidator.Validity validateSubClassName(MapperConfig<?> ctxt, JavaType baseType, String subClassName) {
    return PolymorphicTypeValidator.Validity.ALLOWED;
  }
  
  public PolymorphicTypeValidator.Validity validateSubType(MapperConfig<?> ctxt, JavaType baseType, JavaType subType) {
    return PolymorphicTypeValidator.Validity.ALLOWED;
  }
}
