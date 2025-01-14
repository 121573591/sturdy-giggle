package cn.hutool.core.annotation;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjectUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class AbstractWrappedAnnotationAttribute implements WrappedAnnotationAttribute {
  protected final AnnotationAttribute original;
  
  protected final AnnotationAttribute linked;
  
  protected AbstractWrappedAnnotationAttribute(AnnotationAttribute original, AnnotationAttribute linked) {
    Assert.notNull(original, "target must not null", new Object[0]);
    Assert.notNull(linked, "linked must not null", new Object[0]);
    this.original = original;
    this.linked = linked;
  }
  
  public AnnotationAttribute getOriginal() {
    return this.original;
  }
  
  public AnnotationAttribute getLinked() {
    return this.linked;
  }
  
  public AnnotationAttribute getNonWrappedOriginal() {
    AnnotationAttribute curr = null;
    AnnotationAttribute next = this.original;
    while (next != null) {
      curr = next;
      next = next.isWrapped() ? ((WrappedAnnotationAttribute)curr).getOriginal() : null;
    } 
    return curr;
  }
  
  public Collection<AnnotationAttribute> getAllLinkedNonWrappedAttributes() {
    List<AnnotationAttribute> leafAttributes = new ArrayList<>();
    collectLeafAttribute(this, leafAttributes);
    return leafAttributes;
  }
  
  private void collectLeafAttribute(AnnotationAttribute curr, List<AnnotationAttribute> leafAttributes) {
    if (ObjectUtil.isNull(curr))
      return; 
    if (!curr.isWrapped()) {
      leafAttributes.add(curr);
      return;
    } 
    WrappedAnnotationAttribute wrappedAttribute = (WrappedAnnotationAttribute)curr;
    collectLeafAttribute(wrappedAttribute.getOriginal(), leafAttributes);
    collectLeafAttribute(wrappedAttribute.getLinked(), leafAttributes);
  }
}
