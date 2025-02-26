package com.fasterxml.jackson.core.filter;

import com.fasterxml.jackson.core.Base64Variant;
import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.util.JsonParserDelegate;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.BigInteger;

public class FilteringParserDelegate extends JsonParserDelegate {
  protected TokenFilter rootFilter;
  
  protected boolean _allowMultipleMatches;
  
  protected TokenFilter.Inclusion _inclusion;
  
  protected JsonToken _currToken;
  
  protected JsonToken _lastClearedToken;
  
  protected TokenFilterContext _headContext;
  
  protected TokenFilterContext _exposedContext;
  
  protected TokenFilter _itemFilter;
  
  protected int _matchCount;
  
  @Deprecated
  public FilteringParserDelegate(JsonParser p, TokenFilter f, boolean includePath, boolean allowMultipleMatches) {
    this(p, f, includePath ? TokenFilter.Inclusion.INCLUDE_ALL_AND_PATH : TokenFilter.Inclusion.ONLY_INCLUDE_ALL, allowMultipleMatches);
  }
  
  public FilteringParserDelegate(JsonParser p, TokenFilter f, TokenFilter.Inclusion inclusion, boolean allowMultipleMatches) {
    super(p);
    this.rootFilter = f;
    this._itemFilter = f;
    this._headContext = TokenFilterContext.createRootContext(f);
    this._inclusion = inclusion;
    this._allowMultipleMatches = allowMultipleMatches;
  }
  
  public TokenFilter getFilter() {
    return this.rootFilter;
  }
  
  public int getMatchCount() {
    return this._matchCount;
  }
  
  public JsonToken getCurrentToken() {
    return this._currToken;
  }
  
  public JsonToken currentToken() {
    return this._currToken;
  }
  
  @Deprecated
  public final int getCurrentTokenId() {
    return currentTokenId();
  }
  
  public final int currentTokenId() {
    JsonToken t = this._currToken;
    return (t == null) ? 0 : t.id();
  }
  
  public boolean hasCurrentToken() {
    return (this._currToken != null);
  }
  
  public boolean hasTokenId(int id) {
    JsonToken t = this._currToken;
    if (t == null)
      return (0 == id); 
    return (t.id() == id);
  }
  
  public final boolean hasToken(JsonToken t) {
    return (this._currToken == t);
  }
  
  public boolean isExpectedStartArrayToken() {
    return (this._currToken == JsonToken.START_ARRAY);
  }
  
  public boolean isExpectedStartObjectToken() {
    return (this._currToken == JsonToken.START_OBJECT);
  }
  
  public JsonLocation getCurrentLocation() {
    return this.delegate.getCurrentLocation();
  }
  
  public JsonStreamContext getParsingContext() {
    return _filterContext();
  }
  
  public String getCurrentName() throws IOException {
    JsonStreamContext ctxt = _filterContext();
    if (this._currToken == JsonToken.START_OBJECT || this._currToken == JsonToken.START_ARRAY) {
      JsonStreamContext parent = ctxt.getParent();
      return (parent == null) ? null : parent.getCurrentName();
    } 
    return ctxt.getCurrentName();
  }
  
  public String currentName() throws IOException {
    JsonStreamContext ctxt = _filterContext();
    if (this._currToken == JsonToken.START_OBJECT || this._currToken == JsonToken.START_ARRAY) {
      JsonStreamContext parent = ctxt.getParent();
      return (parent == null) ? null : parent.getCurrentName();
    } 
    return ctxt.getCurrentName();
  }
  
  public void clearCurrentToken() {
    if (this._currToken != null) {
      this._lastClearedToken = this._currToken;
      this._currToken = null;
    } 
  }
  
  public JsonToken getLastClearedToken() {
    return this._lastClearedToken;
  }
  
  public void overrideCurrentName(String name) {
    throw new UnsupportedOperationException("Can not currently override name during filtering read");
  }
  
  public JsonToken nextToken() throws IOException {
    boolean returnEnd;
    String name;
    if (!this._allowMultipleMatches && this._currToken != null && this._exposedContext == null)
      if (this._currToken.isScalarValue() && !this._headContext.isStartHandled() && this._inclusion == TokenFilter.Inclusion.ONLY_INCLUDE_ALL && this._itemFilter == TokenFilter.INCLUDE_ALL)
        return this._currToken = null;  
    TokenFilterContext ctxt = this._exposedContext;
    if (ctxt != null)
      while (true) {
        JsonToken jsonToken = ctxt.nextTokenToRead();
        if (jsonToken != null) {
          this._currToken = jsonToken;
          return jsonToken;
        } 
        if (ctxt == this._headContext) {
          this._exposedContext = null;
          if (ctxt.inArray()) {
            jsonToken = this.delegate.getCurrentToken();
            this._currToken = jsonToken;
            if (this._currToken == JsonToken.END_ARRAY) {
              this._headContext = this._headContext.getParent();
              this._itemFilter = this._headContext.getFilter();
            } 
            return jsonToken;
          } 
          jsonToken = this.delegate.currentToken();
          if (jsonToken == JsonToken.END_OBJECT) {
            this._headContext = this._headContext.getParent();
            this._itemFilter = this._headContext.getFilter();
          } 
          if (jsonToken != JsonToken.FIELD_NAME) {
            this._currToken = jsonToken;
            return jsonToken;
          } 
          break;
        } 
        ctxt = this._headContext.findChildOf(ctxt);
        this._exposedContext = ctxt;
        if (ctxt == null)
          throw _constructError("Unexpected problem: chain of filtered context broken"); 
      }  
    JsonToken t = this.delegate.nextToken();
    if (t == null) {
      this._currToken = t;
      return t;
    } 
    switch (t.id()) {
      case 3:
        f = this._itemFilter;
        if (f == TokenFilter.INCLUDE_ALL) {
          this._headContext = this._headContext.createChildArrayContext(f, true);
          return this._currToken = t;
        } 
        if (f == null) {
          this.delegate.skipChildren();
        } else {
          f = this._headContext.checkValue(f);
          if (f == null) {
            this.delegate.skipChildren();
          } else {
            if (f != TokenFilter.INCLUDE_ALL)
              f = f.filterStartArray(); 
            this._itemFilter = f;
            if (f == TokenFilter.INCLUDE_ALL) {
              this._headContext = this._headContext.createChildArrayContext(f, true);
              return this._currToken = t;
            } 
            if (f != null && this._inclusion == TokenFilter.Inclusion.INCLUDE_NON_NULL) {
              this._headContext = this._headContext.createChildArrayContext(f, true);
              return this._currToken = t;
            } 
            this._headContext = this._headContext.createChildArrayContext(f, false);
            if (this._inclusion == TokenFilter.Inclusion.INCLUDE_ALL_AND_PATH) {
              t = _nextTokenWithBuffering(this._headContext);
              if (t != null) {
                this._currToken = t;
                return t;
              } 
            } 
          } 
        } 
        return _nextToken2();
      case 1:
        f = this._itemFilter;
        if (f == TokenFilter.INCLUDE_ALL) {
          this._headContext = this._headContext.createChildObjectContext(f, true);
          return this._currToken = t;
        } 
        if (f == null) {
          this.delegate.skipChildren();
        } else {
          f = this._headContext.checkValue(f);
          if (f == null) {
            this.delegate.skipChildren();
          } else {
            if (f != TokenFilter.INCLUDE_ALL)
              f = f.filterStartObject(); 
            this._itemFilter = f;
            if (f == TokenFilter.INCLUDE_ALL) {
              this._headContext = this._headContext.createChildObjectContext(f, true);
              return this._currToken = t;
            } 
            if (f != null && this._inclusion == TokenFilter.Inclusion.INCLUDE_NON_NULL) {
              this._headContext = this._headContext.createChildObjectContext(f, true);
              return this._currToken = t;
            } 
            this._headContext = this._headContext.createChildObjectContext(f, false);
            if (this._inclusion == TokenFilter.Inclusion.INCLUDE_ALL_AND_PATH) {
              t = _nextTokenWithBuffering(this._headContext);
              if (t != null) {
                this._currToken = t;
                return t;
              } 
            } 
          } 
        } 
        return _nextToken2();
      case 2:
      case 4:
        returnEnd = this._headContext.isStartHandled();
        f = this._headContext.getFilter();
        if (f != null && f != TokenFilter.INCLUDE_ALL)
          if (t.id() == 4) {
            f.filterFinishArray();
          } else {
            f.filterFinishObject();
          }  
        this._headContext = this._headContext.getParent();
        this._itemFilter = this._headContext.getFilter();
        if (returnEnd)
          return this._currToken = t; 
        return _nextToken2();
      case 5:
        name = this.delegate.getCurrentName();
        f = this._headContext.setFieldName(name);
        if (f == TokenFilter.INCLUDE_ALL) {
          this._itemFilter = f;
          return this._currToken = t;
        } 
        if (f == null) {
          this.delegate.nextToken();
          this.delegate.skipChildren();
        } else {
          f = f.includeProperty(name);
          if (f == null) {
            this.delegate.nextToken();
            this.delegate.skipChildren();
          } else {
            this._itemFilter = f;
            if (f == TokenFilter.INCLUDE_ALL)
              if (_verifyAllowedMatches()) {
                if (this._inclusion == TokenFilter.Inclusion.INCLUDE_ALL_AND_PATH)
                  return this._currToken = t; 
              } else {
                this.delegate.nextToken();
                this.delegate.skipChildren();
              }  
            if (this._inclusion != TokenFilter.Inclusion.ONLY_INCLUDE_ALL) {
              t = _nextTokenWithBuffering(this._headContext);
              if (t != null) {
                this._currToken = t;
                return t;
              } 
            } 
          } 
        } 
        return _nextToken2();
    } 
    TokenFilter f = this._itemFilter;
    if (f == TokenFilter.INCLUDE_ALL)
      return this._currToken = t; 
    if (f != null) {
      f = this._headContext.checkValue(f);
      if ((f == TokenFilter.INCLUDE_ALL || (f != null && f.includeValue(this.delegate))) && _verifyAllowedMatches())
        return this._currToken = t; 
    } 
    return _nextToken2();
  }
  
  protected final JsonToken _nextToken2() throws IOException {
    while (true) {
      boolean returnEnd;
      String name;
      JsonToken t = this.delegate.nextToken();
      if (t == null) {
        this._currToken = t;
        return t;
      } 
      switch (t.id()) {
        case 3:
          f = this._itemFilter;
          if (f == TokenFilter.INCLUDE_ALL) {
            this._headContext = this._headContext.createChildArrayContext(f, true);
            return this._currToken = t;
          } 
          if (f == null) {
            this.delegate.skipChildren();
            continue;
          } 
          f = this._headContext.checkValue(f);
          if (f == null) {
            this.delegate.skipChildren();
            continue;
          } 
          if (f != TokenFilter.INCLUDE_ALL)
            f = f.filterStartArray(); 
          this._itemFilter = f;
          if (f == TokenFilter.INCLUDE_ALL) {
            this._headContext = this._headContext.createChildArrayContext(f, true);
            return this._currToken = t;
          } 
          if (f != null && this._inclusion == TokenFilter.Inclusion.INCLUDE_NON_NULL) {
            this._headContext = this._headContext.createChildArrayContext(f, true);
            return this._currToken = t;
          } 
          this._headContext = this._headContext.createChildArrayContext(f, false);
          if (this._inclusion == TokenFilter.Inclusion.INCLUDE_ALL_AND_PATH) {
            t = _nextTokenWithBuffering(this._headContext);
            if (t != null) {
              this._currToken = t;
              return t;
            } 
          } 
          continue;
        case 1:
          f = this._itemFilter;
          if (f == TokenFilter.INCLUDE_ALL) {
            this._headContext = this._headContext.createChildObjectContext(f, true);
            return this._currToken = t;
          } 
          if (f == null) {
            this.delegate.skipChildren();
            continue;
          } 
          f = this._headContext.checkValue(f);
          if (f == null) {
            this.delegate.skipChildren();
            continue;
          } 
          if (f != TokenFilter.INCLUDE_ALL)
            f = f.filterStartObject(); 
          this._itemFilter = f;
          if (f == TokenFilter.INCLUDE_ALL) {
            this._headContext = this._headContext.createChildObjectContext(f, true);
            return this._currToken = t;
          } 
          if (f != null && this._inclusion == TokenFilter.Inclusion.INCLUDE_NON_NULL) {
            this._headContext = this._headContext.createChildObjectContext(f, true);
            return this._currToken = t;
          } 
          this._headContext = this._headContext.createChildObjectContext(f, false);
          if (this._inclusion == TokenFilter.Inclusion.INCLUDE_ALL_AND_PATH) {
            t = _nextTokenWithBuffering(this._headContext);
            if (t != null) {
              this._currToken = t;
              return t;
            } 
          } 
          continue;
        case 4:
          returnEnd = this._headContext.isStartHandled();
          f = this._headContext.getFilter();
          if (f != null && f != TokenFilter.INCLUDE_ALL) {
            boolean includeEmpty = f.includeEmptyArray(this._headContext.hasCurrentIndex());
            f.filterFinishArray();
            if (includeEmpty)
              return _nextBuffered(this._headContext); 
          } 
          this._headContext = this._headContext.getParent();
          this._itemFilter = this._headContext.getFilter();
          if (returnEnd)
            return this._currToken = t; 
          continue;
        case 2:
          returnEnd = this._headContext.isStartHandled();
          f = this._headContext.getFilter();
          if (f != null && f != TokenFilter.INCLUDE_ALL) {
            boolean includeEmpty = f.includeEmptyArray(this._headContext.hasCurrentName());
            f.filterFinishObject();
            if (includeEmpty)
              return _nextBuffered(this._headContext); 
          } 
          this._headContext = this._headContext.getParent();
          this._itemFilter = this._headContext.getFilter();
          if (returnEnd)
            return this._currToken = t; 
          continue;
        case 5:
          name = this.delegate.getCurrentName();
          f = this._headContext.setFieldName(name);
          if (f == TokenFilter.INCLUDE_ALL) {
            this._itemFilter = f;
            return this._currToken = t;
          } 
          if (f == null) {
            this.delegate.nextToken();
            this.delegate.skipChildren();
            continue;
          } 
          f = f.includeProperty(name);
          if (f == null) {
            this.delegate.nextToken();
            this.delegate.skipChildren();
            continue;
          } 
          this._itemFilter = f;
          if (f == TokenFilter.INCLUDE_ALL) {
            if (_verifyAllowedMatches()) {
              if (this._inclusion == TokenFilter.Inclusion.INCLUDE_ALL_AND_PATH)
                return this._currToken = t; 
              continue;
            } 
            this.delegate.nextToken();
            this.delegate.skipChildren();
            continue;
          } 
          if (this._inclusion != TokenFilter.Inclusion.ONLY_INCLUDE_ALL) {
            t = _nextTokenWithBuffering(this._headContext);
            if (t != null) {
              this._currToken = t;
              return t;
            } 
          } 
          continue;
      } 
      TokenFilter f = this._itemFilter;
      if (f == TokenFilter.INCLUDE_ALL)
        return this._currToken = t; 
      if (f != null) {
        f = this._headContext.checkValue(f);
        if ((f == TokenFilter.INCLUDE_ALL || (f != null && f
          .includeValue(this.delegate))) && 
          _verifyAllowedMatches())
          return this._currToken = t; 
      } 
    } 
  }
  
  protected final JsonToken _nextTokenWithBuffering(TokenFilterContext buffRoot) throws IOException {
    while (true) {
      boolean gotEnd;
      String name;
      boolean returnEnd;
      JsonToken t = this.delegate.nextToken();
      if (t == null)
        return t; 
      switch (t.id()) {
        case 3:
          f = this._headContext.checkValue(this._itemFilter);
          if (f == null) {
            this.delegate.skipChildren();
            continue;
          } 
          if (f != TokenFilter.INCLUDE_ALL)
            f = f.filterStartArray(); 
          this._itemFilter = f;
          if (f == TokenFilter.INCLUDE_ALL) {
            this._headContext = this._headContext.createChildArrayContext(f, true);
            return _nextBuffered(buffRoot);
          } 
          if (f != null && this._inclusion == TokenFilter.Inclusion.INCLUDE_NON_NULL) {
            this._headContext = this._headContext.createChildArrayContext(f, true);
            return _nextBuffered(buffRoot);
          } 
          this._headContext = this._headContext.createChildArrayContext(f, false);
          continue;
        case 1:
          f = this._itemFilter;
          if (f == TokenFilter.INCLUDE_ALL) {
            this._headContext = this._headContext.createChildObjectContext(f, true);
            return t;
          } 
          if (f == null) {
            this.delegate.skipChildren();
            continue;
          } 
          f = this._headContext.checkValue(f);
          if (f == null) {
            this.delegate.skipChildren();
            continue;
          } 
          if (f != TokenFilter.INCLUDE_ALL)
            f = f.filterStartObject(); 
          this._itemFilter = f;
          if (f == TokenFilter.INCLUDE_ALL) {
            this._headContext = this._headContext.createChildObjectContext(f, true);
            return _nextBuffered(buffRoot);
          } 
          if (f != null && this._inclusion == TokenFilter.Inclusion.INCLUDE_NON_NULL) {
            this._headContext = this._headContext.createChildArrayContext(f, true);
            return _nextBuffered(buffRoot);
          } 
          this._headContext = this._headContext.createChildObjectContext(f, false);
          continue;
        case 4:
          f = this._headContext.getFilter();
          if (f != null && f != TokenFilter.INCLUDE_ALL) {
            boolean includeEmpty = f.includeEmptyArray(this._headContext.hasCurrentIndex());
            f.filterFinishArray();
            if (includeEmpty)
              return _nextBuffered(buffRoot); 
          } 
          gotEnd = (this._headContext == buffRoot);
          returnEnd = (gotEnd && this._headContext.isStartHandled());
          this._headContext = this._headContext.getParent();
          this._itemFilter = this._headContext.getFilter();
          if (returnEnd)
            return t; 
          if (gotEnd)
            return null; 
          continue;
        case 2:
          f = this._headContext.getFilter();
          if (f != null && f != TokenFilter.INCLUDE_ALL) {
            boolean includeEmpty = f.includeEmptyObject(this._headContext.hasCurrentName());
            f.filterFinishObject();
            if (includeEmpty) {
              this._headContext._currentName = (this._headContext._parent == null) ? null : this._headContext._parent._currentName;
              this._headContext._needToHandleName = false;
              return _nextBuffered(buffRoot);
            } 
          } 
          gotEnd = (this._headContext == buffRoot);
          returnEnd = (gotEnd && this._headContext.isStartHandled());
          this._headContext = this._headContext.getParent();
          this._itemFilter = this._headContext.getFilter();
          if (returnEnd)
            return t; 
          if (gotEnd)
            return null; 
          continue;
        case 5:
          name = this.delegate.getCurrentName();
          f = this._headContext.setFieldName(name);
          if (f == TokenFilter.INCLUDE_ALL) {
            this._itemFilter = f;
            return _nextBuffered(buffRoot);
          } 
          if (f == null) {
            this.delegate.nextToken();
            this.delegate.skipChildren();
            continue;
          } 
          f = f.includeProperty(name);
          if (f == null) {
            this.delegate.nextToken();
            this.delegate.skipChildren();
            continue;
          } 
          this._itemFilter = f;
          if (f == TokenFilter.INCLUDE_ALL) {
            if (_verifyAllowedMatches())
              return _nextBuffered(buffRoot); 
            this._itemFilter = this._headContext.setFieldName(name);
          } 
          continue;
      } 
      TokenFilter f = this._itemFilter;
      if (f == TokenFilter.INCLUDE_ALL)
        return _nextBuffered(buffRoot); 
      if (f != null) {
        f = this._headContext.checkValue(f);
        if ((f == TokenFilter.INCLUDE_ALL || (f != null && f
          .includeValue(this.delegate))) && 
          _verifyAllowedMatches())
          return _nextBuffered(buffRoot); 
      } 
    } 
  }
  
  private JsonToken _nextBuffered(TokenFilterContext buffRoot) throws IOException {
    this._exposedContext = buffRoot;
    TokenFilterContext ctxt = buffRoot;
    JsonToken t = ctxt.nextTokenToRead();
    if (t != null)
      return t; 
    while (true) {
      if (ctxt == this._headContext)
        throw _constructError("Internal error: failed to locate expected buffered tokens"); 
      ctxt = this._exposedContext.findChildOf(ctxt);
      this._exposedContext = ctxt;
      if (ctxt == null)
        throw _constructError("Unexpected problem: chain of filtered context broken"); 
      t = this._exposedContext.nextTokenToRead();
      if (t != null)
        return t; 
    } 
  }
  
  private final boolean _verifyAllowedMatches() throws IOException {
    if (this._matchCount == 0 || this._allowMultipleMatches) {
      this._matchCount++;
      return true;
    } 
    return false;
  }
  
  public JsonToken nextValue() throws IOException {
    JsonToken t = nextToken();
    if (t == JsonToken.FIELD_NAME)
      t = nextToken(); 
    return t;
  }
  
  public JsonParser skipChildren() throws IOException {
    if (this._currToken != JsonToken.START_OBJECT && this._currToken != JsonToken.START_ARRAY)
      return (JsonParser)this; 
    int open = 1;
    while (true) {
      JsonToken t = nextToken();
      if (t == null)
        return (JsonParser)this; 
      if (t.isStructStart()) {
        open++;
        continue;
      } 
      if (t.isStructEnd() && 
        --open == 0)
        return (JsonParser)this; 
    } 
  }
  
  public String getText() throws IOException {
    if (this._currToken == JsonToken.FIELD_NAME)
      return currentName(); 
    return this.delegate.getText();
  }
  
  public boolean hasTextCharacters() {
    if (this._currToken == JsonToken.FIELD_NAME)
      return false; 
    return this.delegate.hasTextCharacters();
  }
  
  public char[] getTextCharacters() throws IOException {
    if (this._currToken == JsonToken.FIELD_NAME)
      return currentName().toCharArray(); 
    return this.delegate.getTextCharacters();
  }
  
  public int getTextLength() throws IOException {
    if (this._currToken == JsonToken.FIELD_NAME)
      return currentName().length(); 
    return this.delegate.getTextLength();
  }
  
  public int getTextOffset() throws IOException {
    if (this._currToken == JsonToken.FIELD_NAME)
      return 0; 
    return this.delegate.getTextOffset();
  }
  
  public BigInteger getBigIntegerValue() throws IOException {
    return this.delegate.getBigIntegerValue();
  }
  
  public boolean getBooleanValue() throws IOException {
    return this.delegate.getBooleanValue();
  }
  
  public byte getByteValue() throws IOException {
    return this.delegate.getByteValue();
  }
  
  public short getShortValue() throws IOException {
    return this.delegate.getShortValue();
  }
  
  public BigDecimal getDecimalValue() throws IOException {
    return this.delegate.getDecimalValue();
  }
  
  public double getDoubleValue() throws IOException {
    return this.delegate.getDoubleValue();
  }
  
  public float getFloatValue() throws IOException {
    return this.delegate.getFloatValue();
  }
  
  public int getIntValue() throws IOException {
    return this.delegate.getIntValue();
  }
  
  public long getLongValue() throws IOException {
    return this.delegate.getLongValue();
  }
  
  public JsonParser.NumberType getNumberType() throws IOException {
    return this.delegate.getNumberType();
  }
  
  public Number getNumberValue() throws IOException {
    return this.delegate.getNumberValue();
  }
  
  public int getValueAsInt() throws IOException {
    return this.delegate.getValueAsInt();
  }
  
  public int getValueAsInt(int defaultValue) throws IOException {
    return this.delegate.getValueAsInt(defaultValue);
  }
  
  public long getValueAsLong() throws IOException {
    return this.delegate.getValueAsLong();
  }
  
  public long getValueAsLong(long defaultValue) throws IOException {
    return this.delegate.getValueAsLong(defaultValue);
  }
  
  public double getValueAsDouble() throws IOException {
    return this.delegate.getValueAsDouble();
  }
  
  public double getValueAsDouble(double defaultValue) throws IOException {
    return this.delegate.getValueAsDouble(defaultValue);
  }
  
  public boolean getValueAsBoolean() throws IOException {
    return this.delegate.getValueAsBoolean();
  }
  
  public boolean getValueAsBoolean(boolean defaultValue) throws IOException {
    return this.delegate.getValueAsBoolean(defaultValue);
  }
  
  public String getValueAsString() throws IOException {
    if (this._currToken == JsonToken.FIELD_NAME)
      return currentName(); 
    return this.delegate.getValueAsString();
  }
  
  public String getValueAsString(String defaultValue) throws IOException {
    if (this._currToken == JsonToken.FIELD_NAME)
      return currentName(); 
    return this.delegate.getValueAsString(defaultValue);
  }
  
  public Object getEmbeddedObject() throws IOException {
    return this.delegate.getEmbeddedObject();
  }
  
  public byte[] getBinaryValue(Base64Variant b64variant) throws IOException {
    return this.delegate.getBinaryValue(b64variant);
  }
  
  public int readBinaryValue(Base64Variant b64variant, OutputStream out) throws IOException {
    return this.delegate.readBinaryValue(b64variant, out);
  }
  
  public JsonLocation getTokenLocation() {
    return this.delegate.getTokenLocation();
  }
  
  protected JsonStreamContext _filterContext() {
    if (this._exposedContext != null)
      return this._exposedContext; 
    return this._headContext;
  }
}
