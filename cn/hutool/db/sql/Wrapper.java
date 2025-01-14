package cn.hutool.db.sql;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.db.Entity;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

public class Wrapper implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private Character preWrapQuote;
  
  private Character sufWrapQuote;
  
  public Wrapper() {}
  
  public Wrapper(Character wrapQuote) {
    this.preWrapQuote = wrapQuote;
    this.sufWrapQuote = wrapQuote;
  }
  
  public Wrapper(Character preWrapQuote, Character sufWrapQuote) {
    this.preWrapQuote = preWrapQuote;
    this.sufWrapQuote = sufWrapQuote;
  }
  
  public char getPreWrapQuote() {
    return this.preWrapQuote.charValue();
  }
  
  public void setPreWrapQuote(Character preWrapQuote) {
    this.preWrapQuote = preWrapQuote;
  }
  
  public char getSufWrapQuote() {
    return this.sufWrapQuote.charValue();
  }
  
  public void setSufWrapQuote(Character sufWrapQuote) {
    this.sufWrapQuote = sufWrapQuote;
  }
  
  public String wrap(String field) {
    if (this.preWrapQuote == null || this.sufWrapQuote == null || StrUtil.isBlank(field))
      return field; 
    if (StrUtil.isSurround(field, this.preWrapQuote.charValue(), this.sufWrapQuote.charValue()))
      return field; 
    if (StrUtil.containsAnyIgnoreCase(field, new CharSequence[] { "*", "(", " ", " as " }))
      return field; 
    if (field.contains(".")) {
      Collection<String> target = CollUtil.edit(StrUtil.split(field, '.', 2), t -> StrUtil.format("{}{}{}", new Object[] { this.preWrapQuote, t, this.sufWrapQuote }));
      return CollectionUtil.join(target, ".");
    } 
    return StrUtil.format("{}{}{}", new Object[] { this.preWrapQuote, field, this.sufWrapQuote });
  }
  
  public String[] wrap(String... fields) {
    if (ArrayUtil.isEmpty((Object[])fields))
      return fields; 
    String[] wrappedFields = new String[fields.length];
    for (int i = 0; i < fields.length; i++)
      wrappedFields[i] = wrap(fields[i]); 
    return wrappedFields;
  }
  
  public Collection<String> wrap(Collection<String> fields) {
    if (CollectionUtil.isEmpty(fields))
      return fields; 
    return Arrays.asList(wrap(fields.<String>toArray(new String[0])));
  }
  
  public Entity wrap(Entity entity) {
    if (null == entity)
      return null; 
    Entity wrapedEntity = new Entity();
    wrapedEntity.setTableName(wrap(entity.getTableName()));
    for (Map.Entry<String, Object> entry : (Iterable<Map.Entry<String, Object>>)entity.entrySet())
      wrapedEntity.set(wrap(entry.getKey()), entry.getValue()); 
    return wrapedEntity;
  }
  
  public Condition[] wrap(Condition... conditions) {
    Condition[] clonedConditions = new Condition[conditions.length];
    if (ArrayUtil.isNotEmpty((Object[])conditions))
      for (int i = 0; i < conditions.length; i++) {
        Condition clonedCondition = (Condition)conditions[i].clone();
        clonedCondition.setField(wrap(clonedCondition.getField()));
        clonedConditions[i] = clonedCondition;
      }  
    return clonedConditions;
  }
}
