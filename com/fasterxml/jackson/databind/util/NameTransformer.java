package com.fasterxml.jackson.databind.util;

import java.io.Serializable;

public abstract class NameTransformer {
  public static final NameTransformer NOP = new NopTransformer();
  
  protected static final class NopTransformer extends NameTransformer implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public String transform(String name) {
      return name;
    }
    
    public String reverse(String transformed) {
      return transformed;
    }
  }
  
  public static NameTransformer simpleTransformer(final String prefix, final String suffix) {
    boolean hasPrefix = (prefix != null && !prefix.isEmpty());
    boolean hasSuffix = (suffix != null && !suffix.isEmpty());
    if (hasPrefix) {
      if (hasSuffix)
        return new NameTransformer() {
            public String transform(String name) {
              return prefix + name + suffix;
            }
            
            public String reverse(String transformed) {
              if (transformed.startsWith(prefix)) {
                String str = transformed.substring(prefix.length());
                if (str.endsWith(suffix))
                  return str.substring(0, str.length() - suffix.length()); 
              } 
              return null;
            }
            
            public String toString() {
              return "[PreAndSuffixTransformer('" + prefix + "','" + suffix + "')]";
            }
          }; 
      return new NameTransformer() {
          public String transform(String name) {
            return prefix + name;
          }
          
          public String reverse(String transformed) {
            if (transformed.startsWith(prefix))
              return transformed.substring(prefix.length()); 
            return null;
          }
          
          public String toString() {
            return "[PrefixTransformer('" + prefix + "')]";
          }
        };
    } 
    if (hasSuffix)
      return new NameTransformer() {
          public String transform(String name) {
            return name + suffix;
          }
          
          public String reverse(String transformed) {
            if (transformed.endsWith(suffix))
              return transformed.substring(0, transformed.length() - suffix.length()); 
            return null;
          }
          
          public String toString() {
            return "[SuffixTransformer('" + suffix + "')]";
          }
        }; 
    return NOP;
  }
  
  public static NameTransformer chainedTransformer(NameTransformer t1, NameTransformer t2) {
    return new Chained(t1, t2);
  }
  
  public abstract String transform(String paramString);
  
  public abstract String reverse(String paramString);
  
  public static class Chained extends NameTransformer implements Serializable {
    private static final long serialVersionUID = 1L;
    
    protected final NameTransformer _t1;
    
    protected final NameTransformer _t2;
    
    public Chained(NameTransformer t1, NameTransformer t2) {
      this._t1 = t1;
      this._t2 = t2;
    }
    
    public String transform(String name) {
      return this._t1.transform(this._t2.transform(name));
    }
    
    public String reverse(String transformed) {
      transformed = this._t1.reverse(transformed);
      if (transformed != null)
        transformed = this._t2.reverse(transformed); 
      return transformed;
    }
    
    public String toString() {
      return "[ChainedTransformer(" + this._t1 + ", " + this._t2 + ")]";
    }
  }
}
