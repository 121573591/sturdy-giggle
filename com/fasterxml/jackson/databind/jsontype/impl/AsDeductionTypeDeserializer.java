package com.fasterxml.jackson.databind.jsontype.impl;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.jsontype.NamedType;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeIdResolver;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.TokenBuffer;
import java.io.IOException;
import java.util.BitSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AsDeductionTypeDeserializer extends AsPropertyTypeDeserializer {
  private static final long serialVersionUID = 1L;
  
  private static final BitSet EMPTY_CLASS_FINGERPRINT = new BitSet(0);
  
  private final Map<String, Integer> fieldBitIndex;
  
  private final Map<BitSet, String> subtypeFingerprints;
  
  public AsDeductionTypeDeserializer(JavaType bt, TypeIdResolver idRes, JavaType defaultImpl, DeserializationConfig config, Collection<NamedType> subtypes) {
    super(bt, idRes, (String)null, false, defaultImpl, (JsonTypeInfo.As)null, true);
    this.fieldBitIndex = new HashMap<>();
    this.subtypeFingerprints = buildFingerprints(config, subtypes);
  }
  
  public AsDeductionTypeDeserializer(AsDeductionTypeDeserializer src, BeanProperty property) {
    super(src, property);
    this.fieldBitIndex = src.fieldBitIndex;
    this.subtypeFingerprints = src.subtypeFingerprints;
  }
  
  public TypeDeserializer forProperty(BeanProperty prop) {
    return (prop == this._property) ? this : new AsDeductionTypeDeserializer(this, prop);
  }
  
  protected Map<BitSet, String> buildFingerprints(DeserializationConfig config, Collection<NamedType> subtypes) {
    boolean ignoreCase = config.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES);
    int nextField = 0;
    Map<BitSet, String> fingerprints = new HashMap<>();
    for (NamedType subtype : subtypes) {
      JavaType subtyped = config.getTypeFactory().constructType(subtype.getType());
      List<BeanPropertyDefinition> properties = config.introspect(subtyped).findProperties();
      BitSet fingerprint = new BitSet(nextField + properties.size());
      for (BeanPropertyDefinition property : properties) {
        String name = property.getName();
        if (ignoreCase)
          name = name.toLowerCase(); 
        Integer bitIndex = this.fieldBitIndex.get(name);
        if (bitIndex == null) {
          bitIndex = Integer.valueOf(nextField);
          this.fieldBitIndex.put(name, Integer.valueOf(nextField++));
        } 
        fingerprint.set(bitIndex.intValue());
      } 
      String existingFingerprint = fingerprints.put(fingerprint, subtype.getType().getName());
      if (existingFingerprint != null)
        throw new IllegalStateException(
            String.format("Subtypes %s and %s have the same signature and cannot be uniquely deduced.", new Object[] { existingFingerprint, subtype.getType().getName() })); 
    } 
    return fingerprints;
  }
  
  public Object deserializeTypedFromObject(JsonParser p, DeserializationContext ctxt) throws IOException {
    JsonToken t = p.currentToken();
    if (t == JsonToken.START_OBJECT) {
      t = p.nextToken();
    } else if (t != JsonToken.FIELD_NAME) {
      return _deserializeTypedUsingDefaultImpl(p, ctxt, (TokenBuffer)null, "Unexpected input");
    } 
    if (t == JsonToken.END_OBJECT) {
      String emptySubtype = this.subtypeFingerprints.get(EMPTY_CLASS_FINGERPRINT);
      if (emptySubtype != null)
        return _deserializeTypedForId(p, ctxt, (TokenBuffer)null, emptySubtype); 
    } 
    List<BitSet> candidates = new LinkedList<>(this.subtypeFingerprints.keySet());
    TokenBuffer tb = ctxt.bufferForInputBuffering(p);
    boolean ignoreCase = ctxt.isEnabled(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES);
    for (; t == JsonToken.FIELD_NAME; t = p.nextToken()) {
      String name = p.currentName();
      if (ignoreCase)
        name = name.toLowerCase(); 
      tb.copyCurrentStructure(p);
      Integer bit = this.fieldBitIndex.get(name);
      if (bit != null) {
        prune(candidates, bit.intValue());
        if (candidates.size() == 1)
          return _deserializeTypedForId(p, ctxt, tb, this.subtypeFingerprints.get(candidates.get(0))); 
      } 
    } 
    String msgToReportIfDefaultImplFailsToo = String.format("Cannot deduce unique subtype of %s (%d candidates match)", new Object[] { ClassUtil.getTypeDescription(this._baseType), Integer.valueOf(candidates.size()) });
    return _deserializeTypedUsingDefaultImpl(p, ctxt, tb, msgToReportIfDefaultImplFailsToo);
  }
  
  private static void prune(List<BitSet> candidates, int bit) {
    for (Iterator<BitSet> iter = candidates.iterator(); iter.hasNext();) {
      if (!((BitSet)iter.next()).get(bit))
        iter.remove(); 
    } 
  }
}
