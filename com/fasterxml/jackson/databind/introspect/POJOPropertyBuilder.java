package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.PropertyMetadata;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.cfg.ConfigOverride;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

public class POJOPropertyBuilder extends BeanPropertyDefinition implements Comparable<POJOPropertyBuilder> {
  private static final AnnotationIntrospector.ReferenceProperty NOT_REFEFERENCE_PROP = AnnotationIntrospector.ReferenceProperty.managed("");
  
  protected final boolean _forSerialization;
  
  protected final MapperConfig<?> _config;
  
  protected final AnnotationIntrospector _annotationIntrospector;
  
  protected final PropertyName _name;
  
  protected final PropertyName _internalName;
  
  protected Linked<AnnotatedField> _fields;
  
  protected Linked<AnnotatedParameter> _ctorParameters;
  
  protected Linked<AnnotatedMethod> _getters;
  
  protected Linked<AnnotatedMethod> _setters;
  
  protected transient PropertyMetadata _metadata;
  
  protected transient AnnotationIntrospector.ReferenceProperty _referenceInfo;
  
  public POJOPropertyBuilder(MapperConfig<?> config, AnnotationIntrospector ai, boolean forSerialization, PropertyName internalName) {
    this(config, ai, forSerialization, internalName, internalName);
  }
  
  protected POJOPropertyBuilder(MapperConfig<?> config, AnnotationIntrospector ai, boolean forSerialization, PropertyName internalName, PropertyName name) {
    this._config = config;
    this._annotationIntrospector = ai;
    this._internalName = internalName;
    this._name = name;
    this._forSerialization = forSerialization;
  }
  
  protected POJOPropertyBuilder(POJOPropertyBuilder src, PropertyName newName) {
    this._config = src._config;
    this._annotationIntrospector = src._annotationIntrospector;
    this._internalName = src._internalName;
    this._name = newName;
    this._fields = src._fields;
    this._ctorParameters = src._ctorParameters;
    this._getters = src._getters;
    this._setters = src._setters;
    this._forSerialization = src._forSerialization;
  }
  
  public POJOPropertyBuilder withName(PropertyName newName) {
    return new POJOPropertyBuilder(this, newName);
  }
  
  public POJOPropertyBuilder withSimpleName(String newSimpleName) {
    PropertyName newName = this._name.withSimpleName(newSimpleName);
    return (newName == this._name) ? this : new POJOPropertyBuilder(this, newName);
  }
  
  public int compareTo(POJOPropertyBuilder other) {
    if (this._ctorParameters != null) {
      if (other._ctorParameters == null)
        return -1; 
    } else if (other._ctorParameters != null) {
      return 1;
    } 
    return getName().compareTo(other.getName());
  }
  
  public String getName() {
    return (this._name == null) ? null : this._name.getSimpleName();
  }
  
  public PropertyName getFullName() {
    return this._name;
  }
  
  public boolean hasName(PropertyName name) {
    return this._name.equals(name);
  }
  
  public String getInternalName() {
    return this._internalName.getSimpleName();
  }
  
  public PropertyName getWrapperName() {
    AnnotatedMember member = getPrimaryMember();
    return (member == null || this._annotationIntrospector == null) ? null : this._annotationIntrospector
      .findWrapperName(member);
  }
  
  public boolean isExplicitlyIncluded() {
    return (_anyExplicits(this._fields) || 
      _anyExplicits(this._getters) || 
      _anyExplicits(this._setters) || 
      
      _anyExplicitNames(this._ctorParameters));
  }
  
  public boolean isExplicitlyNamed() {
    return (_anyExplicitNames(this._fields) || 
      _anyExplicitNames(this._getters) || 
      _anyExplicitNames(this._setters) || 
      _anyExplicitNames(this._ctorParameters));
  }
  
  public PropertyMetadata getMetadata() {
    if (this._metadata == null) {
      AnnotatedMember prim = getPrimaryMemberUnchecked();
      if (prim == null) {
        this._metadata = PropertyMetadata.STD_REQUIRED_OR_OPTIONAL;
      } else {
        Boolean b = this._annotationIntrospector.hasRequiredMarker(prim);
        String desc = this._annotationIntrospector.findPropertyDescription(prim);
        Integer idx = this._annotationIntrospector.findPropertyIndex(prim);
        String def = this._annotationIntrospector.findPropertyDefaultValue(prim);
        if (b == null && idx == null && def == null) {
          this
            ._metadata = (desc == null) ? PropertyMetadata.STD_REQUIRED_OR_OPTIONAL : PropertyMetadata.STD_REQUIRED_OR_OPTIONAL.withDescription(desc);
        } else {
          this._metadata = PropertyMetadata.construct(b, desc, idx, def);
        } 
        if (!this._forSerialization)
          this._metadata = _getSetterInfo(this._metadata, prim); 
      } 
    } 
    return this._metadata;
  }
  
  protected PropertyMetadata _getSetterInfo(PropertyMetadata metadata, AnnotatedMember primary) {
    boolean needMerge = true;
    Nulls valueNulls = null;
    Nulls contentNulls = null;
    AnnotatedMember acc = getAccessor();
    if (primary != null) {
      if (this._annotationIntrospector != null) {
        if (acc != null) {
          Boolean b = this._annotationIntrospector.findMergeInfo(primary);
          if (b != null) {
            needMerge = false;
            if (b.booleanValue())
              metadata = metadata.withMergeInfo(PropertyMetadata.MergeInfo.createForPropertyOverride(acc)); 
          } 
        } 
        JsonSetter.Value setterInfo = this._annotationIntrospector.findSetterInfo(primary);
        if (setterInfo != null) {
          valueNulls = setterInfo.nonDefaultValueNulls();
          contentNulls = setterInfo.nonDefaultContentNulls();
        } 
      } 
      if (needMerge || valueNulls == null || contentNulls == null) {
        Class<?> rawType = _rawTypeOf(primary);
        ConfigOverride co = this._config.getConfigOverride(rawType);
        JsonSetter.Value setterInfo = co.getSetterInfo();
        if (setterInfo != null) {
          if (valueNulls == null)
            valueNulls = setterInfo.nonDefaultValueNulls(); 
          if (contentNulls == null)
            contentNulls = setterInfo.nonDefaultContentNulls(); 
        } 
        if (needMerge && acc != null) {
          Boolean b = co.getMergeable();
          if (b != null) {
            needMerge = false;
            if (b.booleanValue())
              metadata = metadata.withMergeInfo(PropertyMetadata.MergeInfo.createForTypeOverride(acc)); 
          } 
        } 
      } 
    } 
    if (needMerge || valueNulls == null || contentNulls == null) {
      JsonSetter.Value setterInfo = this._config.getDefaultSetterInfo();
      if (valueNulls == null)
        valueNulls = setterInfo.nonDefaultValueNulls(); 
      if (contentNulls == null)
        contentNulls = setterInfo.nonDefaultContentNulls(); 
      if (needMerge) {
        Boolean b = this._config.getDefaultMergeable();
        if (Boolean.TRUE.equals(b) && acc != null)
          metadata = metadata.withMergeInfo(PropertyMetadata.MergeInfo.createForDefaults(acc)); 
      } 
    } 
    if (valueNulls != null || contentNulls != null)
      metadata = metadata.withNulls(valueNulls, contentNulls); 
    return metadata;
  }
  
  public JavaType getPrimaryType() {
    if (this._forSerialization) {
      AnnotatedMember annotatedMember = getGetter();
      if (annotatedMember == null) {
        annotatedMember = getField();
        if (annotatedMember == null)
          return TypeFactory.unknownType(); 
      } 
      return annotatedMember.getType();
    } 
    AnnotatedMember m = getConstructorParameter();
    if (m == null) {
      m = getSetter();
      if (m != null)
        return ((AnnotatedMethod)m).getParameterType(0); 
      m = getField();
    } 
    if (m == null) {
      m = getGetter();
      if (m == null)
        return TypeFactory.unknownType(); 
    } 
    return m.getType();
  }
  
  public Class<?> getRawPrimaryType() {
    return getPrimaryType().getRawClass();
  }
  
  public boolean hasGetter() {
    return (this._getters != null);
  }
  
  public boolean hasSetter() {
    return (this._setters != null);
  }
  
  public boolean hasField() {
    return (this._fields != null);
  }
  
  public boolean hasConstructorParameter() {
    return (this._ctorParameters != null);
  }
  
  public boolean couldDeserialize() {
    return (this._ctorParameters != null || this._setters != null || this._fields != null);
  }
  
  public boolean couldSerialize() {
    return (this._getters != null || this._fields != null);
  }
  
  public AnnotatedMethod getGetter() {
    Linked<AnnotatedMethod> curr = this._getters;
    if (curr == null)
      return null; 
    Linked<AnnotatedMethod> next = curr.next;
    if (next == null)
      return (AnnotatedMethod)curr.value; 
    for (; next != null; next = next.next) {
      Class<?> currClass = ((AnnotatedMethod)curr.value).getDeclaringClass();
      Class<?> nextClass = ((AnnotatedMethod)next.value).getDeclaringClass();
      if (currClass != nextClass) {
        if (currClass.isAssignableFrom(nextClass)) {
          curr = next;
          continue;
        } 
        if (nextClass.isAssignableFrom(currClass))
          continue; 
      } 
      int priNext = _getterPriority((AnnotatedMethod)next.value);
      int priCurr = _getterPriority((AnnotatedMethod)curr.value);
      if (priNext != priCurr) {
        if (priNext < priCurr)
          curr = next; 
      } else {
        throw new IllegalArgumentException("Conflicting getter definitions for property \"" + getName() + "\": " + ((AnnotatedMethod)curr.value)
            .getFullName() + " vs " + ((AnnotatedMethod)next.value).getFullName());
      } 
      continue;
    } 
    this._getters = curr.withoutNext();
    return (AnnotatedMethod)curr.value;
  }
  
  protected AnnotatedMethod getGetterUnchecked() {
    Linked<AnnotatedMethod> curr = this._getters;
    if (curr == null)
      return null; 
    return (AnnotatedMethod)curr.value;
  }
  
  public AnnotatedMethod getSetter() {
    Linked<AnnotatedMethod> curr = this._setters;
    if (curr == null)
      return null; 
    Linked<AnnotatedMethod> next = curr.next;
    if (next == null)
      return (AnnotatedMethod)curr.value; 
    for (; next != null; next = next.next) {
      AnnotatedMethod selected = _selectSetter((AnnotatedMethod)curr.value, (AnnotatedMethod)next.value);
      if (selected != curr.value)
        if (selected == next.value) {
          curr = next;
        } else {
          return _selectSetterFromMultiple(curr, next);
        }  
    } 
    this._setters = curr.withoutNext();
    return (AnnotatedMethod)curr.value;
  }
  
  protected AnnotatedMethod getSetterUnchecked() {
    Linked<AnnotatedMethod> curr = this._setters;
    if (curr == null)
      return null; 
    return (AnnotatedMethod)curr.value;
  }
  
  protected AnnotatedMethod _selectSetterFromMultiple(Linked<AnnotatedMethod> curr, Linked<AnnotatedMethod> next) {
    List<AnnotatedMethod> conflicts = new ArrayList<>();
    conflicts.add((AnnotatedMethod)curr.value);
    conflicts.add((AnnotatedMethod)next.value);
    next = next.next;
    for (; next != null; next = next.next) {
      AnnotatedMethod selected = _selectSetter((AnnotatedMethod)curr.value, (AnnotatedMethod)next.value);
      if (selected != curr.value)
        if (selected == next.value) {
          conflicts.clear();
          curr = next;
        } else {
          conflicts.add((AnnotatedMethod)next.value);
        }  
    } 
    if (conflicts.isEmpty()) {
      this._setters = curr.withoutNext();
      return (AnnotatedMethod)curr.value;
    } 
    String desc = conflicts.stream().map(AnnotatedMethod::getFullName).collect(Collectors.joining(" vs "));
    throw new IllegalArgumentException(String.format("Conflicting setter definitions for property \"%s\": %s", new Object[] { getName(), desc }));
  }
  
  protected AnnotatedMethod _selectSetter(AnnotatedMethod currM, AnnotatedMethod nextM) {
    Class<?> currClass = currM.getDeclaringClass();
    Class<?> nextClass = nextM.getDeclaringClass();
    if (currClass != nextClass) {
      if (currClass.isAssignableFrom(nextClass))
        return nextM; 
      if (nextClass.isAssignableFrom(currClass))
        return currM; 
    } 
    int priNext = _setterPriority(nextM);
    int priCurr = _setterPriority(currM);
    if (priNext != priCurr) {
      if (priNext < priCurr)
        return nextM; 
      return currM;
    } 
    return (this._annotationIntrospector == null) ? null : this._annotationIntrospector
      .resolveSetterConflict(this._config, currM, nextM);
  }
  
  public AnnotatedField getField() {
    if (this._fields == null)
      return null; 
    AnnotatedField field = (AnnotatedField)this._fields.value;
    Linked<AnnotatedField> next = this._fields.next;
    for (; next != null; next = next.next) {
      AnnotatedField nextField = (AnnotatedField)next.value;
      Class<?> fieldClass = field.getDeclaringClass();
      Class<?> nextClass = nextField.getDeclaringClass();
      if (fieldClass != nextClass) {
        if (fieldClass.isAssignableFrom(nextClass)) {
          field = nextField;
          continue;
        } 
        if (nextClass.isAssignableFrom(fieldClass))
          continue; 
      } 
      throw new IllegalArgumentException("Multiple fields representing property \"" + getName() + "\": " + field
          .getFullName() + " vs " + nextField.getFullName());
    } 
    return field;
  }
  
  protected AnnotatedField getFieldUnchecked() {
    Linked<AnnotatedField> curr = this._fields;
    if (curr == null)
      return null; 
    return (AnnotatedField)curr.value;
  }
  
  public AnnotatedParameter getConstructorParameter() {
    if (this._ctorParameters == null)
      return null; 
    Linked<AnnotatedParameter> curr = this._ctorParameters;
    while (true) {
      if (((AnnotatedParameter)curr.value).getOwner() instanceof AnnotatedConstructor)
        return (AnnotatedParameter)curr.value; 
      curr = curr.next;
      if (curr == null)
        return (AnnotatedParameter)this._ctorParameters.value; 
    } 
  }
  
  public Iterator<AnnotatedParameter> getConstructorParameters() {
    if (this._ctorParameters == null)
      return ClassUtil.emptyIterator(); 
    return new MemberIterator<>(this._ctorParameters);
  }
  
  public AnnotatedMember getPrimaryMember() {
    if (this._forSerialization)
      return getAccessor(); 
    AnnotatedMember m = getMutator();
    if (m == null)
      m = getAccessor(); 
    return m;
  }
  
  protected AnnotatedMember getPrimaryMemberUnchecked() {
    if (this._forSerialization) {
      if (this._getters != null)
        return (AnnotatedMember)this._getters.value; 
      if (this._fields != null)
        return (AnnotatedMember)this._fields.value; 
      return null;
    } 
    if (this._ctorParameters != null)
      return (AnnotatedMember)this._ctorParameters.value; 
    if (this._setters != null)
      return (AnnotatedMember)this._setters.value; 
    if (this._fields != null)
      return (AnnotatedMember)this._fields.value; 
    if (this._getters != null)
      return (AnnotatedMember)this._getters.value; 
    return null;
  }
  
  protected int _getterPriority(AnnotatedMethod m) {
    String name = m.getName();
    if (name.startsWith("get") && name.length() > 3)
      return 1; 
    if (name.startsWith("is") && name.length() > 2)
      return 2; 
    return 3;
  }
  
  protected int _setterPriority(AnnotatedMethod m) {
    String name = m.getName();
    if (name.startsWith("set") && name.length() > 3)
      return 1; 
    return 2;
  }
  
  public Class<?>[] findViews() {
    return fromMemberAnnotations((WithMember)new WithMember<Class<?>[]>() {
          public Class<?>[] withMember(AnnotatedMember member) {
            return POJOPropertyBuilder.this._annotationIntrospector.findViews(member);
          }
        });
  }
  
  public AnnotationIntrospector.ReferenceProperty findReferenceType() {
    AnnotationIntrospector.ReferenceProperty result = this._referenceInfo;
    if (result != null) {
      if (result == NOT_REFEFERENCE_PROP)
        return null; 
      return result;
    } 
    result = fromMemberAnnotations(new WithMember<AnnotationIntrospector.ReferenceProperty>() {
          public AnnotationIntrospector.ReferenceProperty withMember(AnnotatedMember member) {
            return POJOPropertyBuilder.this._annotationIntrospector.findReferenceType(member);
          }
        });
    this._referenceInfo = (result == null) ? NOT_REFEFERENCE_PROP : result;
    return result;
  }
  
  public boolean isTypeId() {
    Boolean b = fromMemberAnnotations(new WithMember<Boolean>() {
          public Boolean withMember(AnnotatedMember member) {
            return POJOPropertyBuilder.this._annotationIntrospector.isTypeId(member);
          }
        });
    return (b != null && b.booleanValue());
  }
  
  public ObjectIdInfo findObjectIdInfo() {
    return fromMemberAnnotations(new WithMember<ObjectIdInfo>() {
          public ObjectIdInfo withMember(AnnotatedMember member) {
            ObjectIdInfo info = POJOPropertyBuilder.this._annotationIntrospector.findObjectIdInfo(member);
            if (info != null)
              info = POJOPropertyBuilder.this._annotationIntrospector.findObjectReferenceInfo(member, info); 
            return info;
          }
        });
  }
  
  public JsonInclude.Value findInclusion() {
    AnnotatedMember a = getAccessor();
    JsonInclude.Value v = (this._annotationIntrospector == null) ? null : this._annotationIntrospector.findPropertyInclusion(a);
    return (v == null) ? JsonInclude.Value.empty() : v;
  }
  
  public JsonProperty.Access findAccess() {
    return fromMemberAnnotationsExcept(new WithMember<JsonProperty.Access>() {
          public JsonProperty.Access withMember(AnnotatedMember member) {
            return POJOPropertyBuilder.this._annotationIntrospector.findPropertyAccess(member);
          }
        },  JsonProperty.Access.AUTO);
  }
  
  public void addField(AnnotatedField a, PropertyName name, boolean explName, boolean visible, boolean ignored) {
    this._fields = new Linked<>(a, this._fields, name, explName, visible, ignored);
  }
  
  public void addCtor(AnnotatedParameter a, PropertyName name, boolean explName, boolean visible, boolean ignored) {
    this._ctorParameters = new Linked<>(a, this._ctorParameters, name, explName, visible, ignored);
  }
  
  public void addGetter(AnnotatedMethod a, PropertyName name, boolean explName, boolean visible, boolean ignored) {
    this._getters = new Linked<>(a, this._getters, name, explName, visible, ignored);
  }
  
  public void addSetter(AnnotatedMethod a, PropertyName name, boolean explName, boolean visible, boolean ignored) {
    this._setters = new Linked<>(a, this._setters, name, explName, visible, ignored);
  }
  
  public void addAll(POJOPropertyBuilder src) {
    this._fields = merge(this._fields, src._fields);
    this._ctorParameters = merge(this._ctorParameters, src._ctorParameters);
    this._getters = merge(this._getters, src._getters);
    this._setters = merge(this._setters, src._setters);
  }
  
  private static <T> Linked<T> merge(Linked<T> chain1, Linked<T> chain2) {
    if (chain1 == null)
      return chain2; 
    if (chain2 == null)
      return chain1; 
    return chain1.append(chain2);
  }
  
  public void removeIgnored() {
    this._fields = _removeIgnored(this._fields);
    this._getters = _removeIgnored(this._getters);
    this._setters = _removeIgnored(this._setters);
    this._ctorParameters = _removeIgnored(this._ctorParameters);
  }
  
  @Deprecated
  public JsonProperty.Access removeNonVisible(boolean inferMutators) {
    return removeNonVisible(inferMutators, (POJOPropertiesCollector)null);
  }
  
  public JsonProperty.Access removeNonVisible(boolean inferMutators, POJOPropertiesCollector parent) {
    JsonProperty.Access acc = findAccess();
    if (acc == null)
      acc = JsonProperty.Access.AUTO; 
    switch (acc) {
      case READ_ONLY:
        if (parent != null) {
          parent._collectIgnorals(getName());
          for (PropertyName pn : findExplicitNames())
            parent._collectIgnorals(pn.getSimpleName()); 
        } 
        this._setters = null;
        this._ctorParameters = null;
        if (!this._forSerialization)
          this._fields = null; 
      case READ_WRITE:
        return acc;
      case WRITE_ONLY:
        this._getters = null;
        if (this._forSerialization)
          this._fields = null; 
    } 
    this._getters = _removeNonVisible(this._getters);
    this._ctorParameters = _removeNonVisible(this._ctorParameters);
    if (!inferMutators || this._getters == null) {
      this._fields = _removeNonVisible(this._fields);
      this._setters = _removeNonVisible(this._setters);
    } 
  }
  
  public void removeConstructors() {
    this._ctorParameters = null;
  }
  
  public void trimByVisibility() {
    this._fields = _trimByVisibility(this._fields);
    this._getters = _trimByVisibility(this._getters);
    this._setters = _trimByVisibility(this._setters);
    this._ctorParameters = _trimByVisibility(this._ctorParameters);
  }
  
  public void mergeAnnotations(boolean forSerialization) {
    if (forSerialization) {
      if (this._getters != null) {
        AnnotationMap ann = _mergeAnnotations(0, (Linked<? extends AnnotatedMember>[])new Linked[] { this._getters, this._fields, this._ctorParameters, this._setters });
        this._getters = _applyAnnotations(this._getters, ann);
      } else if (this._fields != null) {
        AnnotationMap ann = _mergeAnnotations(0, (Linked<? extends AnnotatedMember>[])new Linked[] { this._fields, this._ctorParameters, this._setters });
        this._fields = _applyAnnotations(this._fields, ann);
      } 
    } else if (this._ctorParameters != null) {
      AnnotationMap ann = _mergeAnnotations(0, (Linked<? extends AnnotatedMember>[])new Linked[] { this._ctorParameters, this._setters, this._fields, this._getters });
      this._ctorParameters = _applyAnnotations(this._ctorParameters, ann);
    } else if (this._setters != null) {
      AnnotationMap ann = _mergeAnnotations(0, (Linked<? extends AnnotatedMember>[])new Linked[] { this._setters, this._fields, this._getters });
      this._setters = _applyAnnotations(this._setters, ann);
    } else if (this._fields != null) {
      AnnotationMap ann = _mergeAnnotations(0, (Linked<? extends AnnotatedMember>[])new Linked[] { this._fields, this._getters });
      this._fields = _applyAnnotations(this._fields, ann);
    } 
  }
  
  private AnnotationMap _mergeAnnotations(int index, Linked<? extends AnnotatedMember>... nodes) {
    AnnotationMap ann = _getAllAnnotations(nodes[index]);
    while (++index < nodes.length) {
      if (nodes[index] != null)
        return AnnotationMap.merge(ann, _mergeAnnotations(index, nodes)); 
    } 
    return ann;
  }
  
  private <T extends AnnotatedMember> AnnotationMap _getAllAnnotations(Linked<T> node) {
    AnnotationMap ann = ((AnnotatedMember)node.value).getAllAnnotations();
    if (node.next != null)
      ann = AnnotationMap.merge(ann, _getAllAnnotations(node.next)); 
    return ann;
  }
  
  private <T extends AnnotatedMember> Linked<T> _applyAnnotations(Linked<T> node, AnnotationMap ann) {
    AnnotatedMember annotatedMember = (AnnotatedMember)((AnnotatedMember)node.value).withAnnotations(ann);
    if (node.next != null)
      node = node.withNext(_applyAnnotations(node.next, ann)); 
    return node.withValue((T)annotatedMember);
  }
  
  private <T> Linked<T> _removeIgnored(Linked<T> node) {
    if (node == null)
      return node; 
    return node.withoutIgnored();
  }
  
  private <T> Linked<T> _removeNonVisible(Linked<T> node) {
    if (node == null)
      return node; 
    return node.withoutNonVisible();
  }
  
  private <T> Linked<T> _trimByVisibility(Linked<T> node) {
    if (node == null)
      return node; 
    return node.trimByVisibility();
  }
  
  private <T> boolean _anyExplicits(Linked<T> n) {
    for (; n != null; n = n.next) {
      if (n.name != null && n.name.hasSimpleName())
        return true; 
    } 
    return false;
  }
  
  private <T> boolean _anyExplicitNames(Linked<T> n) {
    for (; n != null; n = n.next) {
      if (n.name != null && n.isNameExplicit)
        return true; 
    } 
    return false;
  }
  
  public boolean anyVisible() {
    return (_anyVisible(this._fields) || 
      _anyVisible(this._getters) || 
      _anyVisible(this._setters) || 
      _anyVisible(this._ctorParameters));
  }
  
  private <T> boolean _anyVisible(Linked<T> n) {
    for (; n != null; n = n.next) {
      if (n.isVisible)
        return true; 
    } 
    return false;
  }
  
  public boolean anyIgnorals() {
    return (_anyIgnorals(this._fields) || 
      _anyIgnorals(this._getters) || 
      _anyIgnorals(this._setters) || 
      _anyIgnorals(this._ctorParameters));
  }
  
  private <T> boolean _anyIgnorals(Linked<T> n) {
    for (; n != null; n = n.next) {
      if (n.isMarkedIgnored)
        return true; 
    } 
    return false;
  }
  
  public boolean anyExplicitsWithoutIgnoral() {
    return (_anyExplicitsWithoutIgnoral(this._fields) || 
      _anyExplicitsWithoutIgnoral(this._getters) || 
      _anyExplicitsWithoutIgnoral(this._setters) || 
      
      _anyExplicitNamesWithoutIgnoral(this._ctorParameters));
  }
  
  private <T> boolean _anyExplicitsWithoutIgnoral(Linked<T> n) {
    for (; n != null; n = n.next) {
      if (!n.isMarkedIgnored && n.name != null && n.name
        .hasSimpleName())
        return true; 
    } 
    return false;
  }
  
  private <T> boolean _anyExplicitNamesWithoutIgnoral(Linked<T> n) {
    for (; n != null; n = n.next) {
      if (!n.isMarkedIgnored && n.name != null && n.isNameExplicit)
        return true; 
    } 
    return false;
  }
  
  public Set<PropertyName> findExplicitNames() {
    Set<PropertyName> renamed = null;
    renamed = _findExplicitNames((Linked)this._fields, renamed);
    renamed = _findExplicitNames((Linked)this._getters, renamed);
    renamed = _findExplicitNames((Linked)this._setters, renamed);
    renamed = _findExplicitNames((Linked)this._ctorParameters, renamed);
    if (renamed == null)
      return Collections.emptySet(); 
    return renamed;
  }
  
  public Collection<POJOPropertyBuilder> explode(Collection<PropertyName> newNames) {
    HashMap<PropertyName, POJOPropertyBuilder> props = new HashMap<>();
    _explode(newNames, props, this._fields);
    _explode(newNames, props, this._getters);
    _explode(newNames, props, this._setters);
    _explode(newNames, props, this._ctorParameters);
    return props.values();
  }
  
  private void _explode(Collection<PropertyName> newNames, Map<PropertyName, POJOPropertyBuilder> props, Linked<?> accessors) {
    Linked<?> firstAcc = accessors;
    for (Linked<?> node = accessors; node != null; node = node.next) {
      PropertyName name = node.name;
      if (!node.isNameExplicit || name == null) {
        if (node.isVisible)
          throw new IllegalStateException("Conflicting/ambiguous property name definitions (implicit name " + 
              ClassUtil.name(this._name) + "): found multiple explicit names: " + newNames + ", but also implicit accessor: " + node); 
      } else {
        POJOPropertyBuilder prop = props.get(name);
        if (prop == null) {
          prop = new POJOPropertyBuilder(this._config, this._annotationIntrospector, this._forSerialization, this._internalName, name);
          props.put(name, prop);
        } 
        if (firstAcc == this._fields) {
          Linked<AnnotatedField> n2 = (Linked)node;
          prop._fields = n2.withNext(prop._fields);
        } else if (firstAcc == this._getters) {
          Linked<AnnotatedMethod> n2 = (Linked)node;
          prop._getters = n2.withNext(prop._getters);
        } else if (firstAcc == this._setters) {
          Linked<AnnotatedMethod> n2 = (Linked)node;
          prop._setters = n2.withNext(prop._setters);
        } else if (firstAcc == this._ctorParameters) {
          Linked<AnnotatedParameter> n2 = (Linked)node;
          prop._ctorParameters = n2.withNext(prop._ctorParameters);
        } else {
          throw new IllegalStateException("Internal error: mismatched accessors, property: " + this);
        } 
      } 
    } 
  }
  
  private Set<PropertyName> _findExplicitNames(Linked<? extends AnnotatedMember> node, Set<PropertyName> renamed) {
    for (; node != null; node = node.next) {
      if (node.isNameExplicit && node.name != null) {
        if (renamed == null)
          renamed = new HashSet<>(); 
        renamed.add(node.name);
      } 
    } 
    return renamed;
  }
  
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[Property '").append(this._name)
      .append("'; ctors: ").append(this._ctorParameters)
      .append(", field(s): ").append(this._fields)
      .append(", getter(s): ").append(this._getters)
      .append(", setter(s): ").append(this._setters);
    sb.append("]");
    return sb.toString();
  }
  
  protected <T> T fromMemberAnnotations(WithMember<T> func) {
    T result = null;
    if (this._annotationIntrospector != null) {
      if (this._forSerialization) {
        if (this._getters != null)
          result = func.withMember((AnnotatedMember)this._getters.value); 
      } else {
        if (this._ctorParameters != null)
          result = func.withMember((AnnotatedMember)this._ctorParameters.value); 
        if (result == null && this._setters != null)
          result = func.withMember((AnnotatedMember)this._setters.value); 
      } 
      if (result == null && this._fields != null)
        result = func.withMember((AnnotatedMember)this._fields.value); 
    } 
    return result;
  }
  
  protected <T> T fromMemberAnnotationsExcept(WithMember<T> func, T defaultValue) {
    if (this._annotationIntrospector == null)
      return null; 
    if (this._forSerialization) {
      if (this._getters != null) {
        T result = func.withMember((AnnotatedMember)this._getters.value);
        if (result != null && result != defaultValue)
          return result; 
      } 
      if (this._fields != null) {
        T result = func.withMember((AnnotatedMember)this._fields.value);
        if (result != null && result != defaultValue)
          return result; 
      } 
      if (this._ctorParameters != null) {
        T result = func.withMember((AnnotatedMember)this._ctorParameters.value);
        if (result != null && result != defaultValue)
          return result; 
      } 
      if (this._setters != null) {
        T result = func.withMember((AnnotatedMember)this._setters.value);
        if (result != null && result != defaultValue)
          return result; 
      } 
      return null;
    } 
    if (this._ctorParameters != null) {
      T result = func.withMember((AnnotatedMember)this._ctorParameters.value);
      if (result != null && result != defaultValue)
        return result; 
    } 
    if (this._setters != null) {
      T result = func.withMember((AnnotatedMember)this._setters.value);
      if (result != null && result != defaultValue)
        return result; 
    } 
    if (this._fields != null) {
      T result = func.withMember((AnnotatedMember)this._fields.value);
      if (result != null && result != defaultValue)
        return result; 
    } 
    if (this._getters != null) {
      T result = func.withMember((AnnotatedMember)this._getters.value);
      if (result != null && result != defaultValue)
        return result; 
    } 
    return null;
  }
  
  protected Class<?> _rawTypeOf(AnnotatedMember m) {
    if (m instanceof AnnotatedMethod) {
      AnnotatedMethod meh = (AnnotatedMethod)m;
      if (meh.getParameterCount() > 0)
        return meh.getParameterType(0).getRawClass(); 
    } 
    return m.getType().getRawClass();
  }
  
  protected static class MemberIterator<T extends AnnotatedMember> implements Iterator<T> {
    private POJOPropertyBuilder.Linked<T> next;
    
    public MemberIterator(POJOPropertyBuilder.Linked<T> first) {
      this.next = first;
    }
    
    public boolean hasNext() {
      return (this.next != null);
    }
    
    public T next() {
      if (this.next == null)
        throw new NoSuchElementException(); 
      AnnotatedMember annotatedMember = (AnnotatedMember)this.next.value;
      this.next = this.next.next;
      return (T)annotatedMember;
    }
    
    public void remove() {
      throw new UnsupportedOperationException();
    }
  }
  
  protected static final class Linked<T> {
    public final T value;
    
    public final Linked<T> next;
    
    public final PropertyName name;
    
    public final boolean isNameExplicit;
    
    public final boolean isVisible;
    
    public final boolean isMarkedIgnored;
    
    public Linked(T v, Linked<T> n, PropertyName name, boolean explName, boolean visible, boolean ignored) {
      this.value = v;
      this.next = n;
      this.name = (name == null || name.isEmpty()) ? null : name;
      if (explName) {
        if (this.name == null)
          throw new IllegalArgumentException("Cannot pass true for 'explName' if name is null/empty"); 
        if (!name.hasSimpleName())
          explName = false; 
      } 
      this.isNameExplicit = explName;
      this.isVisible = visible;
      this.isMarkedIgnored = ignored;
    }
    
    public Linked<T> withoutNext() {
      if (this.next == null)
        return this; 
      return new Linked(this.value, null, this.name, this.isNameExplicit, this.isVisible, this.isMarkedIgnored);
    }
    
    public Linked<T> withValue(T newValue) {
      if (newValue == this.value)
        return this; 
      return new Linked(newValue, this.next, this.name, this.isNameExplicit, this.isVisible, this.isMarkedIgnored);
    }
    
    public Linked<T> withNext(Linked<T> newNext) {
      if (newNext == this.next)
        return this; 
      return new Linked(this.value, newNext, this.name, this.isNameExplicit, this.isVisible, this.isMarkedIgnored);
    }
    
    public Linked<T> withoutIgnored() {
      if (this.isMarkedIgnored)
        return (this.next == null) ? null : this.next.withoutIgnored(); 
      if (this.next != null) {
        Linked<T> newNext = this.next.withoutIgnored();
        if (newNext != this.next)
          return withNext(newNext); 
      } 
      return this;
    }
    
    public Linked<T> withoutNonVisible() {
      Linked<T> newNext = (this.next == null) ? null : this.next.withoutNonVisible();
      return this.isVisible ? withNext(newNext) : newNext;
    }
    
    protected Linked<T> append(Linked<T> appendable) {
      if (this.next == null)
        return withNext(appendable); 
      return withNext(this.next.append(appendable));
    }
    
    public Linked<T> trimByVisibility() {
      if (this.next == null)
        return this; 
      Linked<T> newNext = this.next.trimByVisibility();
      if (this.name != null) {
        if (newNext.name == null)
          return withNext(null); 
        return withNext(newNext);
      } 
      if (newNext.name != null)
        return newNext; 
      if (this.isVisible == newNext.isVisible)
        return withNext(newNext); 
      return this.isVisible ? withNext(null) : newNext;
    }
    
    public String toString() {
      String msg = String.format("%s[visible=%b,ignore=%b,explicitName=%b]", new Object[] { this.value
            .toString(), Boolean.valueOf(this.isVisible), Boolean.valueOf(this.isMarkedIgnored), Boolean.valueOf(this.isNameExplicit) });
      if (this.next != null)
        msg = msg + ", " + this.next.toString(); 
      return msg;
    }
  }
  
  private static interface WithMember<T> {
    T withMember(AnnotatedMember param1AnnotatedMember);
  }
}
