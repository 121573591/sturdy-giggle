package org.openjdk.nashorn.internal.objects;

import java.lang.invoke.MethodHandle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openjdk.nashorn.internal.runtime.BitVector;
import org.openjdk.nashorn.internal.runtime.ECMAErrors;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.ParserException;
import org.openjdk.nashorn.internal.runtime.PropertyMap;
import org.openjdk.nashorn.internal.runtime.ScriptObject;
import org.openjdk.nashorn.internal.runtime.ScriptRuntime;
import org.openjdk.nashorn.internal.runtime.linker.Bootstrap;
import org.openjdk.nashorn.internal.runtime.regexp.RegExp;
import org.openjdk.nashorn.internal.runtime.regexp.RegExpFactory;
import org.openjdk.nashorn.internal.runtime.regexp.RegExpMatcher;
import org.openjdk.nashorn.internal.runtime.regexp.RegExpResult;

public final class NativeRegExp extends ScriptObject {
  public Object lastIndex;
  
  private RegExp regexp;
  
  private final Global globalObject;
  
  private static PropertyMap $nasgenmap$;
  
  private NativeRegExp(Global global) {
    super(global.getRegExpPrototype(), $nasgenmap$);
    this.globalObject = global;
  }
  
  NativeRegExp(String input, String flagString, Global global, ScriptObject proto) {
    super(proto, $nasgenmap$);
    try {
      this.regexp = RegExpFactory.create(input, flagString);
    } catch (ParserException e) {
      e.throwAsEcmaException();
      throw new AssertionError();
    } 
    this.globalObject = global;
    setLastIndex(0);
  }
  
  NativeRegExp(String input, String flagString, Global global) {
    this(input, flagString, global, global.getRegExpPrototype());
  }
  
  NativeRegExp(String input, String flagString) {
    this(input, flagString, Global.instance());
  }
  
  NativeRegExp(String string, Global global) {
    this(string, "", global);
  }
  
  NativeRegExp(String string) {
    this(string, Global.instance());
  }
  
  NativeRegExp(NativeRegExp regExp) {
    this(Global.instance());
    this.lastIndex = regExp.getLastIndexObject();
    this.regexp = regExp.getRegExp();
  }
  
  public String getClassName() {
    return "RegExp";
  }
  
  public static NativeRegExp constructor(boolean isNew, Object self, Object... args) {
    if (args.length > 1)
      return newRegExp(args[0], args[1]); 
    if (args.length > 0)
      return newRegExp(args[0], ScriptRuntime.UNDEFINED); 
    return newRegExp(ScriptRuntime.UNDEFINED, ScriptRuntime.UNDEFINED);
  }
  
  public static NativeRegExp constructor(boolean isNew, Object self) {
    return new NativeRegExp("", "");
  }
  
  public static NativeRegExp constructor(boolean isNew, Object self, Object pattern) {
    return newRegExp(pattern, ScriptRuntime.UNDEFINED);
  }
  
  public static NativeRegExp constructor(boolean isNew, Object self, Object pattern, Object flags) {
    return newRegExp(pattern, flags);
  }
  
  public static NativeRegExp newRegExp(Object regexp, Object flags) {
    String patternString = "";
    String flagString = "";
    if (regexp != ScriptRuntime.UNDEFINED) {
      if (regexp instanceof NativeRegExp) {
        if (flags != ScriptRuntime.UNDEFINED)
          throw ECMAErrors.typeError("regex.cant.supply.flags", new String[0]); 
        return (NativeRegExp)regexp;
      } 
      patternString = JSType.toString(regexp);
    } 
    if (flags != ScriptRuntime.UNDEFINED)
      flagString = JSType.toString(flags); 
    return new NativeRegExp(patternString, flagString);
  }
  
  static NativeRegExp flatRegExp(String string) {
    StringBuilder sb = null;
    int length = string.length();
    for (int i = 0; i < length; i++) {
      char c = string.charAt(i);
      switch (c) {
        case '$':
        case '(':
        case ')':
        case '*':
        case '+':
        case '.':
        case '?':
        case '[':
        case '\\':
        case '^':
        case '{':
        case '|':
          if (sb == null) {
            sb = new StringBuilder(length * 2);
            sb.append(string, 0, i);
          } 
          sb.append('\\');
          sb.append(c);
          break;
        default:
          if (sb != null)
            sb.append(c); 
          break;
      } 
    } 
    return new NativeRegExp((sb == null) ? string : sb.toString(), "");
  }
  
  private String getFlagString() {
    StringBuilder sb = new StringBuilder(3);
    if (this.regexp.isGlobal())
      sb.append('g'); 
    if (this.regexp.isIgnoreCase())
      sb.append('i'); 
    if (this.regexp.isMultiline())
      sb.append('m'); 
    return sb.toString();
  }
  
  public String safeToString() {
    return "[RegExp " + toString() + "]";
  }
  
  public String toString() {
    return "/" + this.regexp.getSource() + "/" + getFlagString();
  }
  
  public static ScriptObject compile(Object self, Object pattern, Object flags) {
    NativeRegExp regExp = checkRegExp(self);
    NativeRegExp compiled = newRegExp(pattern, flags);
    regExp.setRegExp(compiled.getRegExp());
    return regExp;
  }
  
  public static ScriptObject exec(Object self, Object string) {
    return checkRegExp(self).exec(JSType.toString(string));
  }
  
  public static boolean test(Object self, Object string) {
    return checkRegExp(self).test(JSType.toString(string));
  }
  
  public static String toString(Object self) {
    return checkRegExp(self).toString();
  }
  
  public static Object source(Object self) {
    return checkRegExp(self).getRegExp().getSource();
  }
  
  public static Object global(Object self) {
    return Boolean.valueOf(checkRegExp(self).getRegExp().isGlobal());
  }
  
  public static Object ignoreCase(Object self) {
    return Boolean.valueOf(checkRegExp(self).getRegExp().isIgnoreCase());
  }
  
  public static Object multiline(Object self) {
    return Boolean.valueOf(checkRegExp(self).getRegExp().isMultiline());
  }
  
  public static Object getLastInput(Object self) {
    RegExpResult match = Global.instance().getLastRegExpResult();
    return (match == null) ? "" : match.getInput();
  }
  
  public static Object getLastMultiline(Object self) {
    return Boolean.valueOf(false);
  }
  
  public static Object getLastMatch(Object self) {
    RegExpResult match = Global.instance().getLastRegExpResult();
    return (match == null) ? "" : match.getGroup(0);
  }
  
  public static Object getLastParen(Object self) {
    RegExpResult match = Global.instance().getLastRegExpResult();
    return (match == null) ? "" : match.getLastParen();
  }
  
  public static Object getLeftContext(Object self) {
    RegExpResult match = Global.instance().getLastRegExpResult();
    return (match == null) ? "" : match.getInput().substring(0, match.getIndex());
  }
  
  public static Object getRightContext(Object self) {
    RegExpResult match = Global.instance().getLastRegExpResult();
    return (match == null) ? "" : match.getInput().substring(match.getIndex() + match.length());
  }
  
  public static Object getGroup1(Object self) {
    RegExpResult match = Global.instance().getLastRegExpResult();
    return (match == null) ? "" : match.getGroup(1);
  }
  
  public static Object getGroup2(Object self) {
    RegExpResult match = Global.instance().getLastRegExpResult();
    return (match == null) ? "" : match.getGroup(2);
  }
  
  public static Object getGroup3(Object self) {
    RegExpResult match = Global.instance().getLastRegExpResult();
    return (match == null) ? "" : match.getGroup(3);
  }
  
  public static Object getGroup4(Object self) {
    RegExpResult match = Global.instance().getLastRegExpResult();
    return (match == null) ? "" : match.getGroup(4);
  }
  
  public static Object getGroup5(Object self) {
    RegExpResult match = Global.instance().getLastRegExpResult();
    return (match == null) ? "" : match.getGroup(5);
  }
  
  public static Object getGroup6(Object self) {
    RegExpResult match = Global.instance().getLastRegExpResult();
    return (match == null) ? "" : match.getGroup(6);
  }
  
  public static Object getGroup7(Object self) {
    RegExpResult match = Global.instance().getLastRegExpResult();
    return (match == null) ? "" : match.getGroup(7);
  }
  
  public static Object getGroup8(Object self) {
    RegExpResult match = Global.instance().getLastRegExpResult();
    return (match == null) ? "" : match.getGroup(8);
  }
  
  public static Object getGroup9(Object self) {
    RegExpResult match = Global.instance().getLastRegExpResult();
    return (match == null) ? "" : match.getGroup(9);
  }
  
  private RegExpResult execInner(String string) {
    boolean isGlobal = this.regexp.isGlobal();
    int start = getLastIndex();
    if (!isGlobal)
      start = 0; 
    if (start < 0 || start > string.length()) {
      if (isGlobal)
        setLastIndex(0); 
      return null;
    } 
    RegExpMatcher matcher = this.regexp.match(string);
    if (matcher == null || !matcher.search(start)) {
      if (isGlobal)
        setLastIndex(0); 
      return null;
    } 
    if (isGlobal)
      setLastIndex(matcher.end()); 
    RegExpResult match = new RegExpResult(string, matcher.start(), groups(matcher));
    this.globalObject.setLastRegExpResult(match);
    return match;
  }
  
  private RegExpResult execSplit(String string, int start) {
    if (start < 0 || start > string.length())
      return null; 
    RegExpMatcher matcher = this.regexp.match(string);
    if (matcher == null || !matcher.search(start))
      return null; 
    RegExpResult match = new RegExpResult(string, matcher.start(), groups(matcher));
    this.globalObject.setLastRegExpResult(match);
    return match;
  }
  
  private Object[] groups(RegExpMatcher matcher) {
    int groupCount = matcher.groupCount();
    Object[] groups = new Object[groupCount + 1];
    BitVector groupsInNegativeLookahead = this.regexp.getGroupsInNegativeLookahead();
    for (int i = 0, lastGroupStart = matcher.start(); i <= groupCount; i++) {
      int groupStart = matcher.start(i);
      if (lastGroupStart > groupStart || (groupsInNegativeLookahead != null && groupsInNegativeLookahead
        .isSet(i))) {
        groups[i] = ScriptRuntime.UNDEFINED;
      } else {
        String group = matcher.group(i);
        groups[i] = (group == null) ? ScriptRuntime.UNDEFINED : group;
        lastGroupStart = groupStart;
      } 
    } 
    return groups;
  }
  
  public NativeRegExpExecResult exec(String string) {
    RegExpResult match = execInner(string);
    if (match == null)
      return null; 
    return new NativeRegExpExecResult(match, this.globalObject);
  }
  
  public boolean test(String string) {
    return (execInner(string) != null);
  }
  
  String replace(String string, String replacement, Object function) throws Throwable {
    int previousLastIndex;
    RegExpMatcher matcher = this.regexp.match(string);
    if (matcher == null)
      return string; 
    if (!this.regexp.isGlobal()) {
      if (!matcher.search(0))
        return string; 
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(string, 0, matcher.start());
      if (function != null) {
        Object object = Bootstrap.isStrictCallable(function) ? ScriptRuntime.UNDEFINED : Global.instance();
        stringBuilder.append(callReplaceValue(getReplaceValueInvoker(), function, object, matcher, string));
      } else {
        appendReplacement(matcher, string, replacement, stringBuilder);
      } 
      stringBuilder.append(string, matcher.end(), string.length());
      return stringBuilder.toString();
    } 
    setLastIndex(0);
    if (!matcher.search(0))
      return string; 
    int thisIndex = 0;
    StringBuilder sb = new StringBuilder();
    MethodHandle invoker = (function == null) ? null : getReplaceValueInvoker();
    Object self = (function == null || Bootstrap.isStrictCallable(function)) ? ScriptRuntime.UNDEFINED : Global.instance();
    do {
      sb.append(string, thisIndex, matcher.start());
      if (function != null) {
        sb.append(callReplaceValue(invoker, function, self, matcher, string));
      } else {
        appendReplacement(matcher, string, replacement, sb);
      } 
      thisIndex = matcher.end();
      if (matcher.start() == matcher.end()) {
        setLastIndex(thisIndex + 1);
        previousLastIndex = thisIndex + 1;
      } else {
        previousLastIndex = thisIndex;
      } 
    } while (previousLastIndex <= string.length() && matcher.search(previousLastIndex));
    sb.append(string, thisIndex, string.length());
    return sb.toString();
  }
  
  private void appendReplacement(RegExpMatcher matcher, String text, String replacement, StringBuilder sb) {
    int cursor = 0;
    Object[] groups = null;
    while (cursor < replacement.length()) {
      char nextChar = replacement.charAt(cursor);
      if (nextChar == '$') {
        cursor++;
        if (cursor == replacement.length()) {
          sb.append('$');
          break;
        } 
        nextChar = replacement.charAt(cursor);
        int firstDigit = nextChar - 48;
        if (firstDigit >= 0 && firstDigit <= 9 && firstDigit <= matcher.groupCount()) {
          int refNum = firstDigit;
          cursor++;
          if (cursor < replacement.length() && firstDigit < matcher.groupCount()) {
            int secondDigit = replacement.charAt(cursor) - 48;
            if (secondDigit >= 0 && secondDigit <= 9) {
              int newRefNum = firstDigit * 10 + secondDigit;
              if (newRefNum <= matcher.groupCount() && newRefNum > 0) {
                refNum = newRefNum;
                cursor++;
              } 
            } 
          } 
          if (refNum > 0) {
            if (groups == null)
              groups = groups(matcher); 
            if (groups[refNum] != ScriptRuntime.UNDEFINED)
              sb.append((String)groups[refNum]); 
            continue;
          } 
          assert refNum == 0;
          sb.append("$0");
          continue;
        } 
        if (nextChar == '$') {
          sb.append('$');
          cursor++;
          continue;
        } 
        if (nextChar == '&') {
          sb.append(matcher.group());
          cursor++;
          continue;
        } 
        if (nextChar == '`') {
          sb.append(text, 0, matcher.start());
          cursor++;
          continue;
        } 
        if (nextChar == '\'') {
          sb.append(text, matcher.end(), text.length());
          cursor++;
          continue;
        } 
        sb.append('$');
        continue;
      } 
      sb.append(nextChar);
      cursor++;
    } 
  }
  
  private static final Object REPLACE_VALUE = new Object();
  
  static {
    $clinit$();
  }
  
  private static MethodHandle getReplaceValueInvoker() {
    return Global.instance().getDynamicInvoker(REPLACE_VALUE, () -> Bootstrap.createDynamicCallInvoker(String.class, new Class[] { Object.class, Object.class, Object[].class }));
  }
  
  private String callReplaceValue(MethodHandle invoker, Object function, Object self, RegExpMatcher matcher, String string) throws Throwable {
    Object[] groups = groups(matcher);
    Object[] args = Arrays.copyOf(groups, groups.length + 2);
    args[groups.length] = Integer.valueOf(matcher.start());
    args[groups.length + 1] = string;
    return invoker.invokeExact(function, self, args);
  }
  
  NativeArray split(String string, long limit) {
    if (limit == 0L)
      return new NativeArray(); 
    List<Object> matches = new ArrayList();
    int inputLength = string.length();
    int splitLastLength = -1;
    int splitLastIndex = 0;
    int splitLastLastIndex = 0;
    RegExpResult match;
    while ((match = execSplit(string, splitLastIndex)) != null) {
      splitLastIndex = match.getIndex() + match.length();
      if (splitLastIndex > splitLastLastIndex) {
        matches.add(string.substring(splitLastLastIndex, match.getIndex()));
        Object[] groups = match.getGroups();
        if (groups.length > 1 && match.getIndex() < inputLength)
          for (int index = 1; index < groups.length && matches.size() < limit; index++)
            matches.add(groups[index]);  
        splitLastLength = match.length();
        if (matches.size() >= limit)
          break; 
      } 
      if (splitLastIndex == splitLastLastIndex) {
        splitLastIndex++;
        continue;
      } 
      splitLastLastIndex = splitLastIndex;
    } 
    if (matches.size() < limit)
      if (splitLastLastIndex == string.length()) {
        if (splitLastLength > 0 || execSplit("", 0) == null)
          matches.add(""); 
      } else {
        matches.add(string.substring(splitLastLastIndex, inputLength));
      }  
    return new NativeArray(matches.toArray());
  }
  
  int search(String string) {
    RegExpResult match = execInner(string);
    if (match == null)
      return -1; 
    return match.getIndex();
  }
  
  public int getLastIndex() {
    return JSType.toInteger(this.lastIndex);
  }
  
  public Object getLastIndexObject() {
    return this.lastIndex;
  }
  
  public void setLastIndex(int lastIndex) {
    this.lastIndex = JSType.toObject(lastIndex);
  }
  
  private static NativeRegExp checkRegExp(Object self) {
    if (self instanceof NativeRegExp)
      return (NativeRegExp)self; 
    if (self != null && self == Global.instance().getRegExpPrototype())
      return Global.instance().getDefaultRegExp(); 
    throw ECMAErrors.typeError("not.a.regexp", new String[] { ScriptRuntime.safeToString(self) });
  }
  
  boolean getGlobal() {
    return this.regexp.isGlobal();
  }
  
  private RegExp getRegExp() {
    return this.regexp;
  }
  
  private void setRegExp(RegExp regexp) {
    this.regexp = regexp;
  }
  
  public static void $clinit$() {
    // Byte code:
    //   0: new java/util/ArrayList
    //   3: dup
    //   4: iconst_5
    //   5: invokespecial <init> : (I)V
    //   8: dup
    //   9: ldc_w 'lastIndex'
    //   12: bipush #6
    //   14: ldc_w
    //   17: ldc_w
    //   20: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   23: invokeinterface add : (Ljava/lang/Object;)Z
    //   28: pop
    //   29: dup
    //   30: ldc_w 'source'
    //   33: bipush #7
    //   35: ldc_w
    //   38: aconst_null
    //   39: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   42: invokeinterface add : (Ljava/lang/Object;)Z
    //   47: pop
    //   48: dup
    //   49: ldc_w 'global'
    //   52: bipush #7
    //   54: ldc_w
    //   57: aconst_null
    //   58: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   61: invokeinterface add : (Ljava/lang/Object;)Z
    //   66: pop
    //   67: dup
    //   68: ldc_w 'ignoreCase'
    //   71: bipush #7
    //   73: ldc_w
    //   76: aconst_null
    //   77: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   80: invokeinterface add : (Ljava/lang/Object;)Z
    //   85: pop
    //   86: dup
    //   87: ldc_w 'multiline'
    //   90: bipush #7
    //   92: ldc_w
    //   95: aconst_null
    //   96: invokestatic create : (Ljava/lang/Object;ILjava/lang/invoke/MethodHandle;Ljava/lang/invoke/MethodHandle;)Lorg/openjdk/nashorn/internal/runtime/AccessorProperty;
    //   99: invokeinterface add : (Ljava/lang/Object;)Z
    //   104: pop
    //   105: invokestatic newMap : (Ljava/util/Collection;)Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   108: putstatic org/openjdk/nashorn/internal/objects/NativeRegExp.$nasgenmap$ : Lorg/openjdk/nashorn/internal/runtime/PropertyMap;
    //   111: return
  }
  
  public Object G$lastIndex() {
    return this.lastIndex;
  }
  
  public void S$lastIndex(Object paramObject) {
    this.lastIndex = paramObject;
  }
}
