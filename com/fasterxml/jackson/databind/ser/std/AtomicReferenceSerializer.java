package com.fasterxml.jackson.databind.ser.std;

import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.type.ReferenceType;
import com.fasterxml.jackson.databind.util.NameTransformer;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceSerializer extends ReferenceTypeSerializer<AtomicReference<?>> {
  private static final long serialVersionUID = 1L;
  
  public AtomicReferenceSerializer(ReferenceType fullType, boolean staticTyping, TypeSerializer vts, JsonSerializer<Object> ser) {
    super(fullType, staticTyping, vts, ser);
  }
  
  protected AtomicReferenceSerializer(AtomicReferenceSerializer base, BeanProperty property, TypeSerializer vts, JsonSerializer<?> valueSer, NameTransformer unwrapper, Object suppressableValue, boolean suppressNulls) {
    super(base, property, vts, valueSer, unwrapper, suppressableValue, suppressNulls);
  }
  
  protected ReferenceTypeSerializer<AtomicReference<?>> withResolved(BeanProperty prop, TypeSerializer vts, JsonSerializer<?> valueSer, NameTransformer unwrapper) {
    return new AtomicReferenceSerializer(this, prop, vts, valueSer, unwrapper, this._suppressableValue, this._suppressNulls);
  }
  
  public ReferenceTypeSerializer<AtomicReference<?>> withContentInclusion(Object suppressableValue, boolean suppressNulls) {
    return new AtomicReferenceSerializer(this, this._property, this._valueTypeSerializer, this._valueSerializer, this._unwrapper, suppressableValue, suppressNulls);
  }
  
  protected boolean _isValuePresent(AtomicReference<?> value) {
    return (value.get() != null);
  }
  
  protected Object _getReferenced(AtomicReference<?> value) {
    return value.get();
  }
  
  protected Object _getReferencedIfPresent(AtomicReference<?> value) {
    return value.get();
  }
}
