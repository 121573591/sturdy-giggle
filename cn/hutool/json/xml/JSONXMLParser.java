package cn.hutool.json.xml;

import cn.hutool.json.InternalJSONUtil;
import cn.hutool.json.JSONException;
import cn.hutool.json.JSONObject;
import cn.hutool.json.XML;
import cn.hutool.json.XMLTokener;

public class JSONXMLParser {
  public static void parseJSONObject(JSONObject jo, String xmlStr, boolean keepStrings) throws JSONException {
    parseJSONObject(jo, xmlStr, ParseConfig.of().setKeepStrings(keepStrings));
  }
  
  public static void parseJSONObject(JSONObject jo, String xmlStr, ParseConfig parseConfig) throws JSONException {
    XMLTokener x = new XMLTokener(xmlStr, jo.getConfig());
    while (x.more() && x.skipPast("<"))
      parse(x, jo, null, parseConfig, 0); 
  }
  
  private static boolean parse(XMLTokener x, JSONObject context, String name, ParseConfig parseConfig, int currentNestingDepth) throws JSONException {
    Object token = x.nextToken();
    if (token == XML.BANG) {
      char c = x.next();
      if (c == '-') {
        if (x.next() == '-') {
          x.skipPast("-->");
          return false;
        } 
        x.back();
      } else if (c == '[') {
        token = x.nextToken();
        if ("CDATA".equals(token) && 
          x.next() == '[') {
          String string = x.nextCDATA();
          if (string.length() > 0)
            context.accumulate("content", string); 
          return false;
        } 
        throw x.syntaxError("Expected 'CDATA['");
      } 
      int i = 1;
      while (true) {
        token = x.nextMeta();
        if (token == null)
          throw x.syntaxError("Missing '>' after '<!'."); 
        if (token == XML.LT) {
          i++;
        } else if (token == XML.GT) {
          i--;
        } 
        if (i <= 0)
          return false; 
      } 
    } 
    if (token == XML.QUEST) {
      x.skipPast("?>");
      return false;
    } 
    if (token == XML.SLASH) {
      token = x.nextToken();
      if (name == null)
        throw x.syntaxError("Mismatched close tag " + token); 
      if (!token.equals(name))
        throw x.syntaxError("Mismatched " + name + " and " + token); 
      if (x.nextToken() != XML.GT)
        throw x.syntaxError("Misshaped close tag"); 
      return true;
    } 
    if (token instanceof Character)
      throw x.syntaxError("Misshaped tag"); 
    String tagName = (String)token;
    token = null;
    JSONObject jsonobject = new JSONObject();
    boolean keepStrings = parseConfig.isKeepStrings();
    while (true) {
      if (token == null)
        token = x.nextToken(); 
      if (token instanceof String) {
        String string = (String)token;
        token = x.nextToken();
        if (token == XML.EQ) {
          token = x.nextToken();
          if (!(token instanceof String))
            throw x.syntaxError("Missing value"); 
          jsonobject.accumulate(string, keepStrings ? token : InternalJSONUtil.stringToValue((String)token));
          token = null;
          continue;
        } 
        jsonobject.accumulate(string, "");
        continue;
      } 
      break;
    } 
    if (token == XML.SLASH) {
      if (x.nextToken() != XML.GT)
        throw x.syntaxError("Misshaped tag"); 
      if (jsonobject.size() > 0) {
        context.accumulate(tagName, jsonobject);
      } else {
        context.accumulate(tagName, "");
      } 
      return false;
    } 
    if (token == XML.GT)
      while (true) {
        token = x.nextContent();
        if (token == null) {
          if (tagName != null)
            throw x.syntaxError("Unclosed tag " + tagName); 
          return false;
        } 
        if (token instanceof String) {
          String string = (String)token;
          if (!string.isEmpty())
            jsonobject.accumulate("content", keepStrings ? token : InternalJSONUtil.stringToValue(string)); 
          continue;
        } 
        if (token == XML.LT) {
          int maxNestingDepth = parseConfig.getMaxNestingDepth();
          if (maxNestingDepth > -1 && currentNestingDepth >= maxNestingDepth)
            throw x.syntaxError("Maximum nesting depth of " + maxNestingDepth + " reached"); 
          if (parse(x, jsonobject, tagName, parseConfig, currentNestingDepth + 1)) {
            if (jsonobject.isEmpty()) {
              context.accumulate(tagName, "");
            } else if (jsonobject.size() == 1 && jsonobject.get("content") != null) {
              context.accumulate(tagName, jsonobject.get("content"));
            } else {
              context.accumulate(tagName, jsonobject);
            } 
            return false;
          } 
        } 
      }  
    throw x.syntaxError("Misshaped tag");
  }
}
