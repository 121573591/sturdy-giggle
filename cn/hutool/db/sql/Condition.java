package cn.hutool.db.sql;

import cn.hutool.core.clone.CloneSupport;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.text.StrSplitter;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Condition extends CloneSupport<Condition> {
  private static final String OPERATOR_LIKE = "LIKE";
  
  private static final String OPERATOR_IN = "IN";
  
  private static final String OPERATOR_IS = "IS";
  
  private static final String OPERATOR_IS_NOT = "IS NOT";
  
  private static final String OPERATOR_BETWEEN = "BETWEEN";
  
  public enum LikeType {
    StartWith, EndWith, Contains;
  }
  
  private static final List<String> OPERATORS = Arrays.asList(new String[] { "<>", "<=", "<", ">=", ">", "=", "!=", "IN" });
  
  private static final String VALUE_NULL = "NULL";
  
  private String field;
  
  private String operator;
  
  private Object value;
  
  private boolean isPlaceHolder = true;
  
  private Object secondValue;
  
  private LogicalOperator linkOperator = LogicalOperator.AND;
  
  public static Condition parse(String field, Object expression) {
    return new Condition(field, expression);
  }
  
  public Condition() {}
  
  public Condition(boolean isPlaceHolder) {
    this.isPlaceHolder = isPlaceHolder;
  }
  
  public Condition(String field, Object value) {
    this(field, "=", value);
    parseValue();
  }
  
  public Condition(String field, String operator, Object value) {
    this.field = field;
    this.operator = operator;
    this.value = value;
  }
  
  public Condition(String field, String value, LikeType likeType) {
    this.field = field;
    this.operator = "LIKE";
    this.value = SqlUtil.buildLikeValue(value, likeType, false);
  }
  
  public String getField() {
    return this.field;
  }
  
  public void setField(String field) {
    this.field = field;
  }
  
  public String getOperator() {
    return this.operator;
  }
  
  public void setOperator(String operator) {
    this.operator = operator;
  }
  
  public Object getValue() {
    return this.value;
  }
  
  public void setValue(Object value) {
    setValue(value, false);
  }
  
  public void setValue(Object value, boolean isParse) {
    this.value = value;
    if (isParse)
      parseValue(); 
  }
  
  public boolean isPlaceHolder() {
    return this.isPlaceHolder;
  }
  
  public void setPlaceHolder(boolean isPlaceHolder) {
    this.isPlaceHolder = isPlaceHolder;
  }
  
  public boolean isOperatorBetween() {
    return "BETWEEN".equalsIgnoreCase(this.operator);
  }
  
  public boolean isOperatorIn() {
    return "IN".equalsIgnoreCase(this.operator);
  }
  
  public boolean isOperatorIs() {
    return "IS".equalsIgnoreCase(this.operator);
  }
  
  public boolean isOperatorLike() {
    return "LIKE".equalsIgnoreCase(this.operator);
  }
  
  public Condition checkValueNull() {
    if (null == this.value) {
      this.operator = "IS";
      this.value = "NULL";
    } 
    return this;
  }
  
  public Object getSecondValue() {
    return this.secondValue;
  }
  
  public void setSecondValue(Object secondValue) {
    this.secondValue = secondValue;
  }
  
  public LogicalOperator getLinkOperator() {
    return this.linkOperator;
  }
  
  public void setLinkOperator(LogicalOperator linkOperator) {
    this.linkOperator = linkOperator;
  }
  
  public String toString() {
    return toString(null);
  }
  
  public String toString(List<Object> paramValues) {
    StringBuilder conditionStrBuilder = StrUtil.builder();
    checkValueNull();
    conditionStrBuilder.append(this.field).append(" ").append(this.operator);
    if (isOperatorBetween()) {
      buildValuePartForBETWEEN(conditionStrBuilder, paramValues);
    } else if (isOperatorIn()) {
      buildValuePartForIN(conditionStrBuilder, paramValues);
    } else if (isPlaceHolder() && false == isOperatorIs()) {
      conditionStrBuilder.append(" ?");
      if (null != paramValues)
        paramValues.add(this.value); 
    } else {
      String valueStr = String.valueOf(this.value);
      conditionStrBuilder.append(" ").append(isOperatorLike() ? 
          StrUtil.wrap(valueStr, "'") : valueStr);
    } 
    return conditionStrBuilder.toString();
  }
  
  private void buildValuePartForBETWEEN(StringBuilder conditionStrBuilder, List<Object> paramValues) {
    if (isPlaceHolder()) {
      conditionStrBuilder.append(" ?");
      if (null != paramValues)
        paramValues.add(this.value); 
    } else {
      conditionStrBuilder.append(' ').append(this.value);
    } 
    conditionStrBuilder.append(" ").append(LogicalOperator.AND);
    if (isPlaceHolder()) {
      conditionStrBuilder.append(" ?");
      if (null != paramValues)
        paramValues.add(this.secondValue); 
    } else {
      conditionStrBuilder.append(' ').append(this.secondValue);
    } 
  }
  
  private void buildValuePartForIN(StringBuilder conditionStrBuilder, List<Object> paramValues) {
    conditionStrBuilder.append(" (");
    Object value = this.value;
    if (isPlaceHolder()) {
      Collection<?> valuesForIn;
      if (value instanceof Collection) {
        valuesForIn = (Collection)value;
      } else if (value instanceof CharSequence) {
        valuesForIn = StrUtil.split((CharSequence)value, ',');
      } else {
        valuesForIn = Arrays.asList((Object[])Convert.convert(Object[].class, value));
      } 
      conditionStrBuilder.append(StrUtil.repeatAndJoin("?", valuesForIn.size(), ","));
      if (null != paramValues)
        paramValues.addAll(valuesForIn); 
    } else {
      conditionStrBuilder.append(StrUtil.join(",", new Object[] { value }));
    } 
    conditionStrBuilder.append(')');
  }
  
  private void parseValue() {
    if (null == this.value) {
      this.operator = "IS";
      this.value = "NULL";
      return;
    } 
    if (this.value instanceof Collection || ArrayUtil.isArray(this.value)) {
      this.operator = "IN";
      return;
    } 
    if (false == this.value instanceof String)
      return; 
    String valueStr = (String)this.value;
    if (StrUtil.isBlank(valueStr))
      return; 
    valueStr = StrUtil.trim(valueStr);
    if (StrUtil.endWithIgnoreCase(valueStr, "null")) {
      if (StrUtil.equalsIgnoreCase("= null", valueStr) || StrUtil.equalsIgnoreCase("is null", valueStr)) {
        this.operator = "IS";
        this.value = "NULL";
        this.isPlaceHolder = false;
        return;
      } 
      if (StrUtil.equalsIgnoreCase("!= null", valueStr) || StrUtil.equalsIgnoreCase("is not null", valueStr)) {
        this.operator = "IS NOT";
        this.value = "NULL";
        this.isPlaceHolder = false;
        return;
      } 
    } 
    List<String> strs = StrUtil.split(valueStr, ' ', 2);
    if (strs.size() < 2)
      return; 
    String firstPart = ((String)strs.get(0)).trim().toUpperCase();
    if (OPERATORS.contains(firstPart)) {
      this.operator = firstPart;
      String valuePart = strs.get(1);
      this.value = isOperatorIn() ? valuePart : tryToNumber(valuePart);
      return;
    } 
    if ("LIKE".equals(firstPart)) {
      this.operator = "LIKE";
      this.value = unwrapQuote(strs.get(1));
      return;
    } 
    if ("BETWEEN".equals(firstPart)) {
      List<String> betweenValueStrs = StrSplitter.splitTrimIgnoreCase(strs.get(1), LogicalOperator.AND.toString(), 2, true);
      if (betweenValueStrs.size() < 2)
        return; 
      this.operator = "BETWEEN";
      this.value = unwrapQuote(betweenValueStrs.get(0));
      this.secondValue = unwrapQuote(betweenValueStrs.get(1));
    } 
  }
  
  private static String unwrapQuote(String value) {
    if (null == value)
      return null; 
    value = value.trim();
    int from = 0;
    int to = value.length();
    char startChar = value.charAt(0);
    char endChar = value.charAt(value.length() - 1);
    if (startChar == endChar && (
      '\'' == startChar || '"' == startChar)) {
      from = 1;
      to--;
    } 
    if (from == 0)
      return value; 
    return value.substring(from, to);
  }
  
  private static Object tryToNumber(String value) {
    value = StrUtil.trim(value);
    if (false == NumberUtil.isNumber(value))
      return value; 
    try {
      return NumberUtil.parseNumber(value);
    } catch (Exception ignore) {
      return value;
    } 
  }
}
