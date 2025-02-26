package io.netty.handler.codec.http2;

import io.netty.handler.codec.CharSequenceValueConverter;
import io.netty.handler.codec.DefaultHeaders;
import io.netty.handler.codec.Headers;
import io.netty.handler.codec.ValueConverter;
import io.netty.handler.codec.http.HttpHeaderValidationUtil;
import io.netty.util.AsciiString;
import io.netty.util.ByteProcessor;
import io.netty.util.internal.PlatformDependent;
import java.util.Iterator;

public class DefaultHttp2Headers extends DefaultHeaders<CharSequence, CharSequence, Http2Headers> implements Http2Headers {
  private static final ByteProcessor HTTP2_NAME_VALIDATOR_PROCESSOR = new ByteProcessor() {
      public boolean process(byte value) {
        return !AsciiString.isUpperCase(value);
      }
    };
  
  static final DefaultHeaders.NameValidator<CharSequence> HTTP2_NAME_VALIDATOR = new DefaultHeaders.NameValidator<CharSequence>() {
      public void validateName(CharSequence name) {
        if (name == null || name.length() == 0)
          PlatformDependent.throwException(Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "empty headers are not allowed [%s]", new Object[] { name })); 
        if (Http2Headers.PseudoHeaderName.hasPseudoHeaderFormat(name)) {
          if (!Http2Headers.PseudoHeaderName.isPseudoHeader(name))
            PlatformDependent.throwException(Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Invalid HTTP/2 pseudo-header '%s' encountered.", new Object[] { name })); 
          return;
        } 
        if (name instanceof AsciiString) {
          int index;
          try {
            index = ((AsciiString)name).forEachByte(DefaultHttp2Headers.HTTP2_NAME_VALIDATOR_PROCESSOR);
          } catch (Http2Exception e) {
            PlatformDependent.throwException(e);
            return;
          } catch (Throwable t) {
            PlatformDependent.throwException(Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, t, "unexpected error. invalid header name [%s]", new Object[] { name }));
            return;
          } 
          if (index != -1)
            PlatformDependent.throwException(Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "invalid header name [%s]", new Object[] { name })); 
        } else {
          for (int i = 0; i < name.length(); i++) {
            if (AsciiString.isUpperCase(name.charAt(i)))
              PlatformDependent.throwException(Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "invalid header name [%s]", new Object[] { name })); 
          } 
        } 
      }
    };
  
  private static final DefaultHeaders.ValueValidator<CharSequence> VALUE_VALIDATOR = new DefaultHeaders.ValueValidator<CharSequence>() {
      public void validate(CharSequence value) {
        int index = HttpHeaderValidationUtil.validateValidHeaderValue(value);
        if (index != -1)
          throw new IllegalArgumentException("a header value contains prohibited character 0x" + 
              Integer.toHexString(value.charAt(index)) + " at index " + index + '.'); 
      }
    };
  
  private DefaultHeaders.HeaderEntry<CharSequence, CharSequence> firstNonPseudo = this.head;
  
  public DefaultHttp2Headers() {
    this(true);
  }
  
  public DefaultHttp2Headers(boolean validate) {
    super(AsciiString.CASE_SENSITIVE_HASHER, (ValueConverter)CharSequenceValueConverter.INSTANCE, validate ? HTTP2_NAME_VALIDATOR : DefaultHeaders.NameValidator.NOT_NULL);
  }
  
  public DefaultHttp2Headers(boolean validate, int arraySizeHint) {
    super(AsciiString.CASE_SENSITIVE_HASHER, (ValueConverter)CharSequenceValueConverter.INSTANCE, validate ? HTTP2_NAME_VALIDATOR : DefaultHeaders.NameValidator.NOT_NULL, arraySizeHint);
  }
  
  public DefaultHttp2Headers(boolean validate, boolean validateValues, int arraySizeHint) {
    super(AsciiString.CASE_SENSITIVE_HASHER, (ValueConverter)CharSequenceValueConverter.INSTANCE, validate ? HTTP2_NAME_VALIDATOR : DefaultHeaders.NameValidator.NOT_NULL, arraySizeHint, validateValues ? VALUE_VALIDATOR : DefaultHeaders.ValueValidator.NO_VALIDATION);
  }
  
  protected void validateName(DefaultHeaders.NameValidator<CharSequence> validator, boolean forAdd, CharSequence name) {
    super.validateName(validator, forAdd, name);
    if (nameValidator() == HTTP2_NAME_VALIDATOR && forAdd && Http2Headers.PseudoHeaderName.hasPseudoHeaderFormat(name) && 
      contains(name))
      PlatformDependent.throwException(Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "Duplicate HTTP/2 pseudo-header '%s' encountered.", new Object[] { name })); 
  }
  
  protected void validateValue(DefaultHeaders.ValueValidator<CharSequence> validator, CharSequence name, CharSequence value) {
    super.validateValue(validator, name, value);
    if (nameValidator() == HTTP2_NAME_VALIDATOR && (value == null || value.length() == 0) && 
      Http2Headers.PseudoHeaderName.hasPseudoHeaderFormat(name))
      PlatformDependent.throwException(Http2Exception.connectionError(Http2Error.PROTOCOL_ERROR, "HTTP/2 pseudo-header '%s' must not be empty.", new Object[] { name })); 
  }
  
  public Http2Headers clear() {
    this.firstNonPseudo = this.head;
    return (Http2Headers)super.clear();
  }
  
  public boolean equals(Object o) {
    return (o instanceof Http2Headers && equals((Http2Headers)o, AsciiString.CASE_SENSITIVE_HASHER));
  }
  
  public int hashCode() {
    return hashCode(AsciiString.CASE_SENSITIVE_HASHER);
  }
  
  public Http2Headers method(CharSequence value) {
    set(Http2Headers.PseudoHeaderName.METHOD.value(), value);
    return this;
  }
  
  public Http2Headers scheme(CharSequence value) {
    set(Http2Headers.PseudoHeaderName.SCHEME.value(), value);
    return this;
  }
  
  public Http2Headers authority(CharSequence value) {
    set(Http2Headers.PseudoHeaderName.AUTHORITY.value(), value);
    return this;
  }
  
  public Http2Headers path(CharSequence value) {
    set(Http2Headers.PseudoHeaderName.PATH.value(), value);
    return this;
  }
  
  public Http2Headers status(CharSequence value) {
    set(Http2Headers.PseudoHeaderName.STATUS.value(), value);
    return this;
  }
  
  public CharSequence method() {
    return (CharSequence)get(Http2Headers.PseudoHeaderName.METHOD.value());
  }
  
  public CharSequence scheme() {
    return (CharSequence)get(Http2Headers.PseudoHeaderName.SCHEME.value());
  }
  
  public CharSequence authority() {
    return (CharSequence)get(Http2Headers.PseudoHeaderName.AUTHORITY.value());
  }
  
  public CharSequence path() {
    return (CharSequence)get(Http2Headers.PseudoHeaderName.PATH.value());
  }
  
  public CharSequence status() {
    return (CharSequence)get(Http2Headers.PseudoHeaderName.STATUS.value());
  }
  
  public boolean contains(CharSequence name, CharSequence value) {
    return contains(name, value, false);
  }
  
  public boolean contains(CharSequence name, CharSequence value, boolean caseInsensitive) {
    return contains(name, value, caseInsensitive ? AsciiString.CASE_INSENSITIVE_HASHER : AsciiString.CASE_SENSITIVE_HASHER);
  }
  
  protected final DefaultHeaders.HeaderEntry<CharSequence, CharSequence> newHeaderEntry(int h, CharSequence name, CharSequence value, DefaultHeaders.HeaderEntry<CharSequence, CharSequence> next) {
    return new Http2HeaderEntry(h, name, value, next);
  }
  
  private final class Http2HeaderEntry extends DefaultHeaders.HeaderEntry<CharSequence, CharSequence> {
    Http2HeaderEntry(int hash, CharSequence key, CharSequence value, DefaultHeaders.HeaderEntry<CharSequence, CharSequence> next) {
      super(hash, key);
      this.value = value;
      this.next = next;
      if (Http2Headers.PseudoHeaderName.hasPseudoHeaderFormat(key)) {
        this.after = DefaultHttp2Headers.this.firstNonPseudo;
        this.before = DefaultHttp2Headers.this.firstNonPseudo.before();
      } else {
        this.after = DefaultHttp2Headers.this.head;
        this.before = DefaultHttp2Headers.this.head.before();
        if (DefaultHttp2Headers.this.firstNonPseudo == DefaultHttp2Headers.this.head)
          DefaultHttp2Headers.this.firstNonPseudo = this; 
      } 
      pointNeighborsToThis();
    }
    
    protected void remove() {
      if (this == DefaultHttp2Headers.this.firstNonPseudo)
        DefaultHttp2Headers.this.firstNonPseudo = DefaultHttp2Headers.this.firstNonPseudo.after(); 
      super.remove();
    }
  }
}
