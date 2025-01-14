package org.openjdk.nashorn.internal.runtime.options;

import java.io.PrintWriter;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.Permissions;
import java.security.ProtectionDomain;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.Objects;
import java.util.PropertyPermission;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.TreeSet;
import org.openjdk.nashorn.internal.runtime.QuotedStringTokenizer;

public final class Options {
  private static AccessControlContext createPropertyReadAccCtxt() {
    Permissions perms = new Permissions();
    perms.add(new PropertyPermission("nashorn.*", "read"));
    return new AccessControlContext(new ProtectionDomain[] { new ProtectionDomain(null, perms) });
  }
  
  private static final AccessControlContext READ_PROPERTY_ACC_CTXT = createPropertyReadAccCtxt();
  
  private final String resource;
  
  private final PrintWriter err;
  
  private final List<String> files;
  
  private final List<String> arguments;
  
  private final TreeMap<String, Option<?>> options;
  
  private static final String NASHORN_ARGS_PREPEND_PROPERTY = "nashorn.args.prepend";
  
  private static final String NASHORN_ARGS_PROPERTY = "nashorn.args";
  
  private static final String MESSAGES_RESOURCE = "org.openjdk.nashorn.internal.runtime.resources.Options";
  
  public Options(String resource) {
    this(resource, new PrintWriter(System.err, true));
  }
  
  public Options(String resource, PrintWriter err) {
    this.resource = resource;
    this.err = err;
    this.files = new ArrayList<>();
    this.arguments = new ArrayList<>();
    this.options = new TreeMap<>();
    for (OptionTemplate t : validOptions) {
      if (t.getDefaultValue() != null) {
        String v = getStringProperty(t.getKey(), null);
        if (v != null) {
          set(t.getKey(), createOption(t, v));
          continue;
        } 
        if (t.getDefaultValue() != null)
          set(t.getKey(), createOption(t, t.getDefaultValue())); 
      } 
    } 
  }
  
  public String getResource() {
    return this.resource;
  }
  
  public String toString() {
    return this.options.toString();
  }
  
  private static void checkPropertyName(String name) {
    if (!((String)Objects.<String>requireNonNull(name)).startsWith("nashorn."))
      throw new IllegalArgumentException(name); 
  }
  
  public static boolean getBooleanProperty(String name, Boolean defValue) {
    checkPropertyName(name);
    return ((Boolean)AccessController.<Boolean>doPrivileged(() -> {
          try {
            String property = System.getProperty(name);
            return (property == null && defValue != null) ? defValue : Boolean.valueOf(
                
                (property != null && !"false".equalsIgnoreCase(property)));
          } catch (SecurityException e) {
            return Boolean.valueOf(false);
          } 
        }READ_PROPERTY_ACC_CTXT)).booleanValue();
  }
  
  public static boolean getBooleanProperty(String name) {
    return getBooleanProperty(name, null);
  }
  
  public static String getStringProperty(String name, String defValue) {
    checkPropertyName(name);
    return AccessController.<String>doPrivileged(() -> {
          try {
            return System.getProperty(name, defValue);
          } catch (SecurityException e) {
            return defValue;
          } 
        }READ_PROPERTY_ACC_CTXT);
  }
  
  public static int getIntProperty(String name, int defValue) {
    checkPropertyName(name);
    return ((Integer)AccessController.<Integer>doPrivileged(() -> {
          try {
            return Integer.getInteger(name, defValue);
          } catch (SecurityException e) {
            return Integer.valueOf(defValue);
          } 
        }READ_PROPERTY_ACC_CTXT)).intValue();
  }
  
  public Option<?> get(String key) {
    return this.options.get(key(key));
  }
  
  public boolean getBoolean(String key) {
    Option<?> option = get(key);
    return (option != null) ? ((Boolean)option.getValue()).booleanValue() : false;
  }
  
  public int getInteger(String key) {
    Option<?> option = get(key);
    return (option != null) ? ((Integer)option.getValue()).intValue() : 0;
  }
  
  public String getString(String key) {
    Option<?> option = get(key);
    if (option != null) {
      String value = (String)option.getValue();
      if (value != null)
        return value.intern(); 
    } 
    return null;
  }
  
  public void set(String key, Option<?> option) {
    this.options.put(key(key), option);
  }
  
  public void set(String key, boolean option) {
    set(key, new Option(Boolean.valueOf(option)));
  }
  
  public void set(String key, String option) {
    set(key, new Option(option));
  }
  
  public List<String> getArguments() {
    return Collections.unmodifiableList(this.arguments);
  }
  
  public List<String> getFiles() {
    return Collections.unmodifiableList(this.files);
  }
  
  public static Collection<OptionTemplate> getValidOptions() {
    return Collections.unmodifiableCollection(validOptions);
  }
  
  private String key(String shortKey) {
    String key = shortKey;
    while (key.startsWith("-"))
      key = key.substring(1); 
    key = key.replace("-", ".");
    String keyPrefix = this.resource + ".option.";
    if (key.startsWith(keyPrefix))
      return key; 
    return keyPrefix + keyPrefix;
  }
  
  static String getMsg(String msgId, String... args) {
    try {
      String msg = bundle.getString(msgId);
      if (args.length == 0)
        return msg; 
      return (new MessageFormat(msg)).format(args);
    } catch (MissingResourceException e) {
      throw new IllegalArgumentException(e);
    } 
  }
  
  public void displayHelp(IllegalArgumentException e) {
    if (e instanceof IllegalOptionException) {
      OptionTemplate template = ((IllegalOptionException)e).getTemplate();
      if (template.isXHelp()) {
        displayHelp(true);
      } else {
        this.err.println(((IllegalOptionException)e).getTemplate());
      } 
      return;
    } 
    if (e != null && e.getMessage() != null) {
      this.err.println(getMsg("option.error.invalid.option", new String[] { e
              .getMessage(), helpOptionTemplate
              .getShortName(), helpOptionTemplate
              .getName() }));
      this.err.println();
      return;
    } 
    displayHelp(false);
  }
  
  public void displayHelp(boolean extended) {
    for (OptionTemplate t : validOptions) {
      if ((extended || !t.isUndocumented()) && t.getResource().equals(this.resource)) {
        this.err.println(t);
        this.err.println();
      } 
    } 
  }
  
  public void process(String[] args) {
    LinkedList<String> argList = new LinkedList<>();
    addSystemProperties("nashorn.args.prepend", argList);
    processArgList(argList);
    assert argList.isEmpty();
    Collections.addAll(argList, args);
    processArgList(argList);
    assert argList.isEmpty();
    addSystemProperties("nashorn.args", argList);
    processArgList(argList);
    assert argList.isEmpty();
  }
  
  private void processArgList(LinkedList<String> argList) {
    while (!argList.isEmpty()) {
      String arg = argList.remove(0);
      Objects.requireNonNull(arg);
      if (arg.isEmpty())
        continue; 
      if ("--".equals(arg)) {
        this.arguments.addAll(argList);
        argList.clear();
        continue;
      } 
      if (!arg.startsWith("-") || arg.length() == 1) {
        this.files.add(arg);
        continue;
      } 
      if (arg.startsWith(definePropPrefix)) {
        String value = arg.substring(definePropPrefix.length());
        int eq = value.indexOf('=');
        if (eq != -1) {
          System.setProperty(value.substring(0, eq), value.substring(eq + 1));
          continue;
        } 
        if (!value.isEmpty()) {
          System.setProperty(value, "");
          continue;
        } 
        throw new IllegalOptionException(definePropTemplate);
      } 
      ParsedArg parg = new ParsedArg(arg);
      if (parg.template.isValueNextArg()) {
        if (argList.isEmpty())
          throw new IllegalOptionException(parg.template); 
        parg.value = argList.remove(0);
      } 
      if (parg.template.isHelp()) {
        if (!argList.isEmpty()) {
          OptionTemplate t = (new ParsedArg((String)argList.get(0))).template;
          throw new IllegalOptionException(t);
        } 
        throw new IllegalArgumentException();
      } 
      if (parg.template.isXHelp())
        throw new IllegalOptionException(parg.template); 
      if (parg.template.isRepeated()) {
        assert parg.template.getType().equals("string");
        String key = key(parg.template.getKey());
        String value = this.options.containsKey(key) ? ("" + ((Option<String>)this.options.get(key)).getValue() + "," + ((Option<String>)this.options.get(key)).getValue()) : Objects.toString(parg.value);
        this.options.put(key, new Option(value));
      } else {
        set(parg.template.getKey(), createOption(parg.template, parg.value));
      } 
      if (parg.template.getDependency() != null)
        argList.addFirst(parg.template.getDependency()); 
    } 
  }
  
  private static void addSystemProperties(String sysPropName, List<String> argList) {
    String sysArgs = getStringProperty(sysPropName, null);
    if (sysArgs != null) {
      StringTokenizer st = new StringTokenizer(sysArgs);
      while (st.hasMoreTokens())
        argList.add(st.nextToken()); 
    } 
  }
  
  public OptionTemplate getOptionTemplateByKey(String shortKey) {
    String fullKey = key(shortKey);
    for (OptionTemplate t : validOptions) {
      if (t.getKey().equals(fullKey))
        return t; 
    } 
    throw new IllegalArgumentException(shortKey);
  }
  
  private static OptionTemplate getOptionTemplateByName(String name) {
    for (OptionTemplate t : validOptions) {
      if (t.nameMatches(name))
        return t; 
    } 
    return null;
  }
  
  private static Option<?> createOption(OptionTemplate t, String value) {
    switch (t.getType()) {
      case "string":
        return new Option(value);
      case "timezone":
        return new Option(TimeZone.getTimeZone(value));
      case "locale":
        return new Option(Locale.forLanguageTag(value));
      case "keyvalues":
        return new KeyValueOption(value);
      case "log":
        return new LoggingOption(value);
      case "boolean":
        return new Option(Boolean.valueOf(Boolean.parseBoolean(value)));
      case "integer":
        try {
          return new Option(Integer.valueOf((value == null) ? 0 : Integer.parseInt(value)));
        } catch (NumberFormatException nfe) {
          throw new IllegalOptionException(t);
        } 
      case "properties":
        initProps(new KeyValueOption(value));
        return null;
    } 
    throw new IllegalArgumentException(value);
  }
  
  private static void initProps(KeyValueOption kv) {
    for (Map.Entry<String, String> entry : kv.getValues().entrySet())
      System.setProperty(entry.getKey(), entry.getValue()); 
  }
  
  private static ResourceBundle bundle = ResourceBundle.getBundle("org.openjdk.nashorn.internal.runtime.resources.Options", Locale.getDefault());
  
  private static HashMap<Object, Object> usage;
  
  private static Collection<OptionTemplate> validOptions = new TreeSet<>();
  
  private static OptionTemplate helpOptionTemplate;
  
  private static OptionTemplate definePropTemplate;
  
  private static String definePropPrefix;
  
  static {
    usage = new HashMap<>();
    for (Enumeration<String> keys = bundle.getKeys(); keys.hasMoreElements(); ) {
      String key = keys.nextElement();
      StringTokenizer st = new StringTokenizer(key, ".");
      String resource = null;
      String type = null;
      if (st.countTokens() > 0)
        resource = st.nextToken(); 
      if (st.countTokens() > 0)
        type = st.nextToken(); 
      if ("option".equals(type)) {
        String helpKey = null;
        String xhelpKey = null;
        String definePropKey = null;
        try {
          helpKey = bundle.getString(resource + ".options.help.key");
          xhelpKey = bundle.getString(resource + ".options.xhelp.key");
          definePropKey = bundle.getString(resource + ".options.D.key");
        } catch (MissingResourceException missingResourceException) {}
        boolean isHelp = key.equals(helpKey);
        boolean isXHelp = key.equals(xhelpKey);
        OptionTemplate t = new OptionTemplate(resource, key, bundle.getString(key), isHelp, isXHelp);
        validOptions.add(t);
        if (isHelp)
          helpOptionTemplate = t; 
        if (key.equals(definePropKey)) {
          definePropPrefix = t.getName();
          definePropTemplate = t;
        } 
        continue;
      } 
      if (resource != null && "options".equals(type))
        usage.put(resource, bundle.getObject(key)); 
    } 
  }
  
  private static class IllegalOptionException extends IllegalArgumentException {
    private final OptionTemplate template;
    
    IllegalOptionException(OptionTemplate t) {
      this.template = t;
    }
    
    OptionTemplate getTemplate() {
      return this.template;
    }
  }
  
  private static class ParsedArg {
    OptionTemplate template;
    
    String value;
    
    ParsedArg(String argument) {
      QuotedStringTokenizer st = new QuotedStringTokenizer(argument, "=");
      if (!st.hasMoreTokens())
        throw new IllegalArgumentException(); 
      String token = st.nextToken();
      this.template = Options.getOptionTemplateByName(token);
      if (this.template == null)
        throw new IllegalArgumentException(argument); 
      this.value = "";
      if (st.hasMoreTokens()) {
        while (st.hasMoreTokens()) {
          this.value += this.value;
          if (st.hasMoreTokens())
            this.value += ":"; 
        } 
      } else if ("boolean".equals(this.template.getType())) {
        this.value = "true";
      } 
    }
  }
}
