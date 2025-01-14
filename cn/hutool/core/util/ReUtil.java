package cn.hutool.core.util;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.comparator.LengthComparator;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.exceptions.UtilException;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.PatternPool;
import cn.hutool.core.lang.func.Func1;
import cn.hutool.core.lang.mutable.Mutable;
import cn.hutool.core.lang.mutable.MutableObj;
import cn.hutool.core.map.MapUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReUtil {
  public static final String RE_CHINESE = "[⺀-⻿⼀-⿟㇀-㇯㐀-䶿一-鿿豈-﫿𠀀-𪛟𪜀-𫜿𫝀-𫠟𫠠-𬺯丽-𯨟]";
  
  public static final String RE_CHINESES = "[⺀-⻿⼀-⿟㇀-㇯㐀-䶿一-鿿豈-﫿𠀀-𪛟𪜀-𫜿𫝀-𫠟𫠠-𬺯丽-𯨟]+";
  
  public static final Set<Character> RE_KEYS = CollUtil.newHashSet((Object[])new Character[] { 
        Character.valueOf('$'), Character.valueOf('('), Character.valueOf(')'), Character.valueOf('*'), Character.valueOf('+'), Character.valueOf('.'), Character.valueOf('['), Character.valueOf(']'), Character.valueOf('?'), Character.valueOf('\\'), 
        Character.valueOf('^'), Character.valueOf('{'), Character.valueOf('}'), Character.valueOf('|') });
  
  public static String getGroup0(String regex, CharSequence content) {
    return get(regex, content, 0);
  }
  
  public static String getGroup1(String regex, CharSequence content) {
    return get(regex, content, 1);
  }
  
  public static String get(String regex, CharSequence content, int groupIndex) {
    if (null == content || null == regex)
      return null; 
    Pattern pattern = PatternPool.get(regex, 32);
    return get(pattern, content, groupIndex);
  }
  
  public static String get(String regex, CharSequence content, String groupName) {
    if (null == content || null == regex)
      return null; 
    Pattern pattern = PatternPool.get(regex, 32);
    return get(pattern, content, groupName);
  }
  
  public static String getGroup0(Pattern pattern, CharSequence content) {
    return get(pattern, content, 0);
  }
  
  public static String getGroup1(Pattern pattern, CharSequence content) {
    return get(pattern, content, 1);
  }
  
  public static String get(Pattern pattern, CharSequence content, int groupIndex) {
    if (null == content || null == pattern)
      return null; 
    MutableObj<String> result = new MutableObj();
    get(pattern, content, matcher -> result.set(matcher.group(groupIndex)));
    return (String)result.get();
  }
  
  public static String get(Pattern pattern, CharSequence content, String groupName) {
    if (null == content || null == pattern || null == groupName)
      return null; 
    MutableObj<String> result = new MutableObj();
    get(pattern, content, matcher -> result.set(matcher.group(groupName)));
    return (String)result.get();
  }
  
  public static void get(Pattern pattern, CharSequence content, Consumer<Matcher> consumer) {
    if (null == content || null == pattern || null == consumer)
      return; 
    Matcher m = pattern.matcher(content);
    if (m.find())
      consumer.accept(m); 
  }
  
  public static List<String> getAllGroups(Pattern pattern, CharSequence content) {
    return getAllGroups(pattern, content, true);
  }
  
  public static List<String> getAllGroups(Pattern pattern, CharSequence content, boolean withGroup0) {
    return getAllGroups(pattern, content, withGroup0, false);
  }
  
  public static List<String> getAllGroups(Pattern pattern, CharSequence content, boolean withGroup0, boolean findAll) {
    if (null == content || null == pattern)
      return null; 
    ArrayList<String> result = new ArrayList<>();
    Matcher matcher = pattern.matcher(content);
    while (matcher.find()) {
      int startGroup = withGroup0 ? 0 : 1;
      int groupCount = matcher.groupCount();
      for (int i = startGroup; i <= groupCount; i++)
        result.add(matcher.group(i)); 
      if (false == findAll)
        break; 
    } 
    return result;
  }
  
  public static Map<String, String> getAllGroupNames(Pattern pattern, CharSequence content) {
    if (null == content || null == pattern)
      return null; 
    Matcher m = pattern.matcher(content);
    Map<String, String> result = MapUtil.newHashMap(m.groupCount());
    if (m.find()) {
      Map<String, Integer> map = ReflectUtil.<Map<String, Integer>>invoke(pattern, "namedGroups", new Object[0]);
      map.forEach((key, value) -> (String)result.put(key, m.group(value.intValue())));
    } 
    return result;
  }
  
  public static String extractMulti(Pattern pattern, CharSequence content, String template) {
    if (null == content || null == pattern || null == template)
      return null; 
    TreeSet<Integer> varNums = new TreeSet<>((o1, o2) -> ObjectUtil.compare(o2, o1));
    Matcher matcherForTemplate = PatternPool.GROUP_VAR.matcher(template);
    while (matcherForTemplate.find())
      varNums.add(Integer.valueOf(Integer.parseInt(matcherForTemplate.group(1)))); 
    Matcher matcher = pattern.matcher(content);
    if (matcher.find()) {
      for (Integer group : varNums)
        template = template.replace("$" + group, matcher.group(group.intValue())); 
      return template;
    } 
    return null;
  }
  
  public static String extractMulti(String regex, CharSequence content, String template) {
    if (null == content || null == regex || null == template)
      return null; 
    Pattern pattern = PatternPool.get(regex, 32);
    return extractMulti(pattern, content, template);
  }
  
  public static String extractMultiAndDelPre(Pattern pattern, Mutable<CharSequence> contentHolder, String template) {
    if (null == contentHolder || null == pattern || null == template)
      return null; 
    HashSet<String> varNums = findAll(PatternPool.GROUP_VAR, template, 1, new HashSet<>());
    CharSequence content = (CharSequence)contentHolder.get();
    Matcher matcher = pattern.matcher(content);
    if (matcher.find()) {
      for (String var : varNums) {
        int group = Integer.parseInt(var);
        template = template.replace("$" + var, matcher.group(group));
      } 
      contentHolder.set(StrUtil.sub(content, matcher.end(), content.length()));
      return template;
    } 
    return null;
  }
  
  public static String extractMultiAndDelPre(String regex, Mutable<CharSequence> contentHolder, String template) {
    if (null == contentHolder || null == regex || null == template)
      return null; 
    Pattern pattern = PatternPool.get(regex, 32);
    return extractMultiAndDelPre(pattern, contentHolder, template);
  }
  
  public static String delFirst(String regex, CharSequence content) {
    if (StrUtil.hasBlank(new CharSequence[] { regex, content }))
      return StrUtil.str(content); 
    Pattern pattern = PatternPool.get(regex, 32);
    return delFirst(pattern, content);
  }
  
  public static String delFirst(Pattern pattern, CharSequence content) {
    return replaceFirst(pattern, content, "");
  }
  
  public static String replaceFirst(Pattern pattern, CharSequence content, String replacement) {
    if (null == pattern || StrUtil.isEmpty(content))
      return StrUtil.str(content); 
    return pattern.matcher(content).replaceFirst(replacement);
  }
  
  public static String delLast(String regex, CharSequence str) {
    if (StrUtil.hasBlank(new CharSequence[] { regex, str }))
      return StrUtil.str(str); 
    Pattern pattern = PatternPool.get(regex, 32);
    return delLast(pattern, str);
  }
  
  public static String delLast(Pattern pattern, CharSequence str) {
    if (null != pattern && StrUtil.isNotEmpty(str)) {
      MatchResult matchResult = lastIndexOf(pattern, str);
      if (null != matchResult)
        return StrUtil.subPre(str, matchResult.start()) + StrUtil.subSuf(str, matchResult.end()); 
    } 
    return StrUtil.str(str);
  }
  
  public static String delAll(String regex, CharSequence content) {
    if (StrUtil.hasEmpty(new CharSequence[] { regex, content }))
      return StrUtil.str(content); 
    Pattern pattern = PatternPool.get(regex, 32);
    return delAll(pattern, content);
  }
  
  public static String delAll(Pattern pattern, CharSequence content) {
    if (null == pattern || StrUtil.isEmpty(content))
      return StrUtil.str(content); 
    return pattern.matcher(content).replaceAll("");
  }
  
  public static String delPre(String regex, CharSequence content) {
    if (null == content || null == regex)
      return StrUtil.str(content); 
    Pattern pattern = PatternPool.get(regex, 32);
    return delPre(pattern, content);
  }
  
  public static String delPre(Pattern pattern, CharSequence content) {
    if (null == content || null == pattern)
      return StrUtil.str(content); 
    Matcher matcher = pattern.matcher(content);
    if (matcher.find())
      return StrUtil.sub(content, matcher.end(), content.length()); 
    return StrUtil.str(content);
  }
  
  public static List<String> findAllGroup0(String regex, CharSequence content) {
    return findAll(regex, content, 0);
  }
  
  public static List<String> findAllGroup1(String regex, CharSequence content) {
    return findAll(regex, content, 1);
  }
  
  public static List<String> findAll(String regex, CharSequence content, int group) {
    return findAll(regex, content, group, new ArrayList<>());
  }
  
  public static <T extends Collection<String>> T findAll(String regex, CharSequence content, int group, T collection) {
    if (null == regex)
      return collection; 
    return findAll(PatternPool.get(regex, 32), content, group, collection);
  }
  
  public static List<String> findAllGroup0(Pattern pattern, CharSequence content) {
    return findAll(pattern, content, 0);
  }
  
  public static List<String> findAllGroup1(Pattern pattern, CharSequence content) {
    return findAll(pattern, content, 1);
  }
  
  public static List<String> findAll(Pattern pattern, CharSequence content, int group) {
    return findAll(pattern, content, group, new ArrayList<>());
  }
  
  public static <T extends Collection<String>> T findAll(Pattern pattern, CharSequence content, int group, T collection) {
    if (null == pattern || null == content)
      return null; 
    Assert.notNull(collection, "Collection must be not null !", new Object[0]);
    findAll(pattern, content, matcher -> collection.add(matcher.group(group)));
    return collection;
  }
  
  public static void findAll(Pattern pattern, CharSequence content, Consumer<Matcher> consumer) {
    if (null == pattern || null == content)
      return; 
    Matcher matcher = pattern.matcher(content);
    while (matcher.find())
      consumer.accept(matcher); 
  }
  
  public static int count(String regex, CharSequence content) {
    if (null == regex || null == content)
      return 0; 
    Pattern pattern = PatternPool.get(regex, 32);
    return count(pattern, content);
  }
  
  public static int count(Pattern pattern, CharSequence content) {
    if (null == pattern || null == content)
      return 0; 
    int count = 0;
    Matcher matcher = pattern.matcher(content);
    while (matcher.find())
      count++; 
    return count;
  }
  
  public static boolean contains(String regex, CharSequence content) {
    if (null == regex || null == content)
      return false; 
    Pattern pattern = PatternPool.get(regex, 32);
    return contains(pattern, content);
  }
  
  public static boolean contains(Pattern pattern, CharSequence content) {
    if (null == pattern || null == content)
      return false; 
    return pattern.matcher(content).find();
  }
  
  public static MatchResult indexOf(String regex, CharSequence content) {
    if (null == regex || null == content)
      return null; 
    Pattern pattern = PatternPool.get(regex, 32);
    return indexOf(pattern, content);
  }
  
  public static MatchResult indexOf(Pattern pattern, CharSequence content) {
    if (null != pattern && null != content) {
      Matcher matcher = pattern.matcher(content);
      if (matcher.find())
        return matcher.toMatchResult(); 
    } 
    return null;
  }
  
  public static MatchResult lastIndexOf(String regex, CharSequence content) {
    if (null == regex || null == content)
      return null; 
    Pattern pattern = PatternPool.get(regex, 32);
    return lastIndexOf(pattern, content);
  }
  
  public static MatchResult lastIndexOf(Pattern pattern, CharSequence content) {
    MatchResult result = null;
    if (null != pattern && null != content) {
      Matcher matcher = pattern.matcher(content);
      while (matcher.find())
        result = matcher.toMatchResult(); 
    } 
    return result;
  }
  
  public static Integer getFirstNumber(CharSequence StringWithNumber) {
    return Convert.toInt(get(PatternPool.NUMBERS, StringWithNumber, 0), null);
  }
  
  public static boolean isMatch(String regex, CharSequence content) {
    if (content == null)
      return false; 
    if (StrUtil.isEmpty(regex))
      return true; 
    Pattern pattern = PatternPool.get(regex, 32);
    return isMatch(pattern, content);
  }
  
  public static boolean isMatch(Pattern pattern, CharSequence content) {
    if (content == null || pattern == null)
      return false; 
    return pattern.matcher(content).matches();
  }
  
  public static String replaceAll(CharSequence content, String regex, String replacementTemplate) {
    Pattern pattern = Pattern.compile(regex, 32);
    return replaceAll(content, pattern, replacementTemplate);
  }
  
  public static String replaceAll(CharSequence content, Pattern pattern, String replacementTemplate) {
    if (StrUtil.isEmpty(content))
      return StrUtil.str(content); 
    Matcher matcher = pattern.matcher(content);
    boolean result = matcher.find();
    if (result) {
      Set<String> varNums = findAll(PatternPool.GROUP_VAR, replacementTemplate, 1, new TreeSet<>(LengthComparator.INSTANCE.reversed()));
      StringBuffer sb = new StringBuffer();
      while (true) {
        String replacement = replacementTemplate;
        for (String var : varNums) {
          int group = Integer.parseInt(var);
          replacement = replacement.replace("$" + var, matcher.group(group));
        } 
        matcher.appendReplacement(sb, escape(replacement));
        result = matcher.find();
        if (!result) {
          matcher.appendTail(sb);
          return sb.toString();
        } 
      } 
    } 
    return StrUtil.str(content);
  }
  
  public static String replaceAll(CharSequence str, String regex, Func1<Matcher, String> replaceFun) {
    return replaceAll(str, Pattern.compile(regex), replaceFun);
  }
  
  public static String replaceAll(CharSequence str, Pattern pattern, Func1<Matcher, String> replaceFun) {
    if (StrUtil.isEmpty(str))
      return StrUtil.str(str); 
    Matcher matcher = pattern.matcher(str);
    StringBuffer buffer = new StringBuffer();
    while (matcher.find()) {
      try {
        matcher.appendReplacement(buffer, (String)replaceFun.call(matcher));
      } catch (Exception e) {
        throw new UtilException(e);
      } 
    } 
    matcher.appendTail(buffer);
    return buffer.toString();
  }
  
  public static String escape(char c) {
    StringBuilder builder = new StringBuilder();
    if (RE_KEYS.contains(Character.valueOf(c)))
      builder.append('\\'); 
    builder.append(c);
    return builder.toString();
  }
  
  public static String escape(CharSequence content) {
    if (StrUtil.isBlank(content))
      return StrUtil.str(content); 
    StringBuilder builder = new StringBuilder();
    int len = content.length();
    for (int i = 0; i < len; i++) {
      char current = content.charAt(i);
      if (RE_KEYS.contains(Character.valueOf(current)))
        builder.append('\\'); 
      builder.append(current);
    } 
    return builder.toString();
  }
}
