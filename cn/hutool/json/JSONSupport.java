package cn.hutool.json;

import cn.hutool.core.bean.BeanUtil;

public class JSONSupport implements JSONString, JSONBeanParser<JSON> {
  public void parse(String jsonString) {
    parse(new JSONObject(jsonString));
  }
  
  public void parse(JSON json) {
    JSONSupport support = JSONConverter.<JSONSupport>jsonToBean(getClass(), json, false);
    BeanUtil.copyProperties(support, this, new String[0]);
  }
  
  public JSONObject toJSON() {
    return new JSONObject(this);
  }
  
  public String toJSONString() {
    return toJSON().toString();
  }
  
  public String toPrettyString() {
    return toJSON().toStringPretty();
  }
  
  public String toString() {
    return toJSONString();
  }
}
