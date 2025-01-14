package cn.hutool.json;

import cn.hutool.core.lang.Filter;
import cn.hutool.core.lang.mutable.Mutable;
import cn.hutool.core.lang.mutable.MutablePair;

public class JSONParser {
  private final JSONTokener tokener;
  
  public static JSONParser of(JSONTokener tokener) {
    return new JSONParser(tokener);
  }
  
  public JSONParser(JSONTokener tokener) {
    this.tokener = tokener;
  }
  
  public void parseTo(JSONObject jsonObject, Filter<MutablePair<String, Object>> filter) {
    JSONTokener tokener = this.tokener;
    if (tokener.nextClean() != '{')
      throw tokener.syntaxError("A JSONObject text must begin with '{'"); 
    while (true) {
      char prev = tokener.getPrevious();
      char c = tokener.nextClean();
      switch (c) {
        case '\000':
          throw tokener.syntaxError("A JSONObject text must end with '}'");
        case '}':
          return;
        case '[':
        case '{':
          if (prev == '{')
            throw tokener.syntaxError("A JSONObject can not directly nest another JSONObject or JSONArray."); 
          break;
      } 
      tokener.back();
      String key = tokener.nextStringValue();
      c = tokener.nextClean();
      if (c != ':')
        throw tokener.syntaxError("Expected a ':' after a key"); 
      jsonObject.set(key, tokener.nextValue(), filter, jsonObject.getConfig().isCheckDuplicate());
      switch (tokener.nextClean()) {
        case ',':
        case ';':
          if (tokener.nextClean() == '}')
            return; 
          tokener.back();
          continue;
        case '}':
          return;
      } 
      break;
    } 
    throw tokener.syntaxError("Expected a ',' or '}'");
  }
  
  public void parseTo(JSONArray jsonArray, Filter<Mutable<Object>> filter) {
    JSONTokener x = this.tokener;
    if (x.nextClean() != '[')
      throw x.syntaxError("A JSONArray text must start with '['"); 
    if (x.nextClean() != ']') {
      x.back();
      while (true) {
        if (x.nextClean() == ',') {
          x.back();
          jsonArray.addRaw(JSONNull.NULL, filter);
        } else {
          x.back();
          jsonArray.addRaw(x.nextValue(), filter);
        } 
        switch (x.nextClean()) {
          case ',':
            if (x.nextClean() == ']')
              return; 
            x.back();
            continue;
          case ']':
            return;
        } 
        break;
      } 
      throw x.syntaxError("Expected a ',' or ']'");
    } 
  }
}
