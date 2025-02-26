package io.netty.handler.codec.http;

import io.netty.handler.codec.DefaultHeaders;
import io.netty.handler.codec.Headers;
import io.netty.handler.codec.ValueConverter;
import io.netty.util.AsciiString;
import io.netty.util.HashingStrategy;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.StringUtil;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CombinedHttpHeaders extends DefaultHttpHeaders {
  @Deprecated
  public CombinedHttpHeaders(boolean validate) {
    super(new CombinedHttpHeadersImpl(AsciiString.CASE_INSENSITIVE_HASHER, valueConverter(), nameValidator(validate), 
          valueValidator(validate)));
  }
  
  CombinedHttpHeaders(DefaultHeaders.NameValidator<CharSequence> nameValidator, DefaultHeaders.ValueValidator<CharSequence> valueValidator) {
    super(new CombinedHttpHeadersImpl(AsciiString.CASE_INSENSITIVE_HASHER, 
          
          valueConverter(), 
          (DefaultHeaders.NameValidator<CharSequence>)ObjectUtil.checkNotNull(nameValidator, "nameValidator"), 
          (DefaultHeaders.ValueValidator<CharSequence>)ObjectUtil.checkNotNull(valueValidator, "valueValidator")));
  }
  
  CombinedHttpHeaders(DefaultHeaders.NameValidator<CharSequence> nameValidator, DefaultHeaders.ValueValidator<CharSequence> valueValidator, int sizeHint) {
    super(new CombinedHttpHeadersImpl(AsciiString.CASE_INSENSITIVE_HASHER, 
          
          valueConverter(), 
          (DefaultHeaders.NameValidator<CharSequence>)ObjectUtil.checkNotNull(nameValidator, "nameValidator"), 
          (DefaultHeaders.ValueValidator<CharSequence>)ObjectUtil.checkNotNull(valueValidator, "valueValidator"), sizeHint));
  }
  
  public boolean containsValue(CharSequence name, CharSequence value, boolean ignoreCase) {
    return super.containsValue(name, StringUtil.trimOws(value), ignoreCase);
  }
  
  private static final class CombinedHttpHeadersImpl extends DefaultHeaders<CharSequence, CharSequence, CombinedHttpHeadersImpl> {
    private static final int VALUE_LENGTH_ESTIMATE = 10;
    
    private CsvValueEscaper<Object> objectEscaper;
    
    private CsvValueEscaper<CharSequence> charSequenceEscaper;
    
    private CsvValueEscaper<Object> objectEscaper() {
      if (this.objectEscaper == null)
        this.objectEscaper = new CsvValueEscaper() {
            public CharSequence escape(CharSequence name, Object value) {
              CharSequence converted;
              try {
                converted = (CharSequence)CombinedHttpHeaders.CombinedHttpHeadersImpl.this.valueConverter().convertObject(value);
              } catch (IllegalArgumentException e) {
                throw new IllegalArgumentException("Failed to convert object value for header '" + name + '\'', e);
              } 
              return StringUtil.escapeCsv(converted, true);
            }
          }; 
      return this.objectEscaper;
    }
    
    private CsvValueEscaper<CharSequence> charSequenceEscaper() {
      if (this.charSequenceEscaper == null)
        this.charSequenceEscaper = new CsvValueEscaper<CharSequence>() {
            public CharSequence escape(CharSequence name, CharSequence value) {
              return StringUtil.escapeCsv(value, true);
            }
          }; 
      return this.charSequenceEscaper;
    }
    
    CombinedHttpHeadersImpl(HashingStrategy<CharSequence> nameHashingStrategy, ValueConverter<CharSequence> valueConverter, DefaultHeaders.NameValidator<CharSequence> nameValidator, DefaultHeaders.ValueValidator<CharSequence> valueValidator) {
      this(nameHashingStrategy, valueConverter, nameValidator, valueValidator, 16);
    }
    
    CombinedHttpHeadersImpl(HashingStrategy<CharSequence> nameHashingStrategy, ValueConverter<CharSequence> valueConverter, DefaultHeaders.NameValidator<CharSequence> nameValidator, DefaultHeaders.ValueValidator<CharSequence> valueValidator, int sizeHint) {
      super(nameHashingStrategy, valueConverter, nameValidator, sizeHint, valueValidator);
    }
    
    public Iterator<CharSequence> valueIterator(CharSequence name) {
      Iterator<CharSequence> itr = super.valueIterator(name);
      if (!itr.hasNext() || cannotBeCombined(name))
        return itr; 
      Iterator<CharSequence> unescapedItr = StringUtil.unescapeCsvFields(itr.next()).iterator();
      if (itr.hasNext())
        throw new IllegalStateException("CombinedHttpHeaders should only have one value"); 
      return unescapedItr;
    }
    
    public List<CharSequence> getAll(CharSequence name) {
      List<CharSequence> values = super.getAll(name);
      if (values.isEmpty() || cannotBeCombined(name))
        return values; 
      if (values.size() != 1)
        throw new IllegalStateException("CombinedHttpHeaders should only have one value"); 
      return StringUtil.unescapeCsvFields(values.get(0));
    }
    
    public CombinedHttpHeadersImpl add(Headers<? extends CharSequence, ? extends CharSequence, ?> headers) {
      if (headers == this)
        throw new IllegalArgumentException("can't add to itself."); 
      if (headers instanceof CombinedHttpHeadersImpl) {
        if (isEmpty()) {
          addImpl(headers);
        } else {
          for (Map.Entry<? extends CharSequence, ? extends CharSequence> header : headers)
            addEscapedValue(header.getKey(), header.getValue()); 
        } 
      } else {
        for (Map.Entry<? extends CharSequence, ? extends CharSequence> header : headers)
          add(header.getKey(), header.getValue()); 
      } 
      return this;
    }
    
    public CombinedHttpHeadersImpl set(Headers<? extends CharSequence, ? extends CharSequence, ?> headers) {
      if (headers == this)
        return this; 
      clear();
      return add(headers);
    }
    
    public CombinedHttpHeadersImpl setAll(Headers<? extends CharSequence, ? extends CharSequence, ?> headers) {
      if (headers == this)
        return this; 
      for (CharSequence key : headers.names())
        remove(key); 
      return add(headers);
    }
    
    public CombinedHttpHeadersImpl add(CharSequence name, CharSequence value) {
      return addEscapedValue(name, charSequenceEscaper().escape(name, value));
    }
    
    public CombinedHttpHeadersImpl add(CharSequence name, CharSequence... values) {
      return addEscapedValue(name, commaSeparate(name, charSequenceEscaper(), values));
    }
    
    public CombinedHttpHeadersImpl add(CharSequence name, Iterable<? extends CharSequence> values) {
      return addEscapedValue(name, commaSeparate(name, charSequenceEscaper(), values));
    }
    
    public CombinedHttpHeadersImpl addObject(CharSequence name, Object value) {
      return addEscapedValue(name, commaSeparate(name, objectEscaper(), new Object[] { value }));
    }
    
    public CombinedHttpHeadersImpl addObject(CharSequence name, Iterable<?> values) {
      return addEscapedValue(name, commaSeparate(name, objectEscaper(), values));
    }
    
    public CombinedHttpHeadersImpl addObject(CharSequence name, Object... values) {
      return addEscapedValue(name, commaSeparate(name, objectEscaper(), values));
    }
    
    public CombinedHttpHeadersImpl set(CharSequence name, CharSequence... values) {
      set(name, commaSeparate(name, charSequenceEscaper(), values));
      return this;
    }
    
    public CombinedHttpHeadersImpl set(CharSequence name, Iterable<? extends CharSequence> values) {
      set(name, commaSeparate(name, charSequenceEscaper(), values));
      return this;
    }
    
    public CombinedHttpHeadersImpl setObject(CharSequence name, Object value) {
      set(name, commaSeparate(name, objectEscaper(), new Object[] { value }));
      return this;
    }
    
    public CombinedHttpHeadersImpl setObject(CharSequence name, Object... values) {
      set(name, commaSeparate(name, objectEscaper(), values));
      return this;
    }
    
    public CombinedHttpHeadersImpl setObject(CharSequence name, Iterable<?> values) {
      set(name, commaSeparate(name, objectEscaper(), values));
      return this;
    }
    
    private static boolean cannotBeCombined(CharSequence name) {
      return HttpHeaderNames.SET_COOKIE.contentEqualsIgnoreCase(name);
    }
    
    private CombinedHttpHeadersImpl addEscapedValue(CharSequence name, CharSequence escapedValue) {
      CharSequence currentValue = (CharSequence)get(name);
      if (currentValue == null || cannotBeCombined(name)) {
        super.add(name, escapedValue);
      } else {
        set(name, commaSeparateEscapedValues(currentValue, escapedValue));
      } 
      return this;
    }
    
    private static <T> CharSequence commaSeparate(CharSequence name, CsvValueEscaper<T> escaper, T... values) {
      StringBuilder sb = new StringBuilder(values.length * 10);
      if (values.length > 0) {
        int end = values.length - 1;
        for (int i = 0; i < end; i++)
          sb.append(escaper.escape(name, values[i])).append(','); 
        sb.append(escaper.escape(name, values[end]));
      } 
      return sb;
    }
    
    private static <T> CharSequence commaSeparate(CharSequence name, CsvValueEscaper<T> escaper, Iterable<? extends T> values) {
      StringBuilder sb = (values instanceof Collection) ? new StringBuilder(((Collection)values).size() * 10) : new StringBuilder();
      Iterator<? extends T> iterator = values.iterator();
      if (iterator.hasNext()) {
        T next = iterator.next();
        while (iterator.hasNext()) {
          sb.append(escaper.escape(name, next)).append(',');
          next = iterator.next();
        } 
        sb.append(escaper.escape(name, next));
      } 
      return sb;
    }
    
    private static CharSequence commaSeparateEscapedValues(CharSequence currentValue, CharSequence value) {
      return (new StringBuilder(currentValue.length() + 1 + value.length()))
        .append(currentValue)
        .append(',')
        .append(value);
    }
    
    private static interface CsvValueEscaper<T> {
      CharSequence escape(CharSequence param2CharSequence, T param2T);
    }
  }
}
