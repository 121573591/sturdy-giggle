package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.JsonLocation;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonStreamContext;
import com.fasterxml.jackson.core.io.ContentReference;

public final class JsonReadContext extends JsonStreamContext {
  protected final JsonReadContext _parent;
  
  protected DupDetector _dups;
  
  protected JsonReadContext _child;
  
  protected String _currentName;
  
  protected Object _currentValue;
  
  protected int _lineNr;
  
  protected int _columnNr;
  
  public JsonReadContext(JsonReadContext parent, int nestingDepth, DupDetector dups, int type, int lineNr, int colNr) {
    this._parent = parent;
    this._dups = dups;
    this._type = type;
    this._lineNr = lineNr;
    this._columnNr = colNr;
    this._index = -1;
    this._nestingDepth = nestingDepth;
  }
  
  @Deprecated
  public JsonReadContext(JsonReadContext parent, DupDetector dups, int type, int lineNr, int colNr) {
    this._parent = parent;
    this._dups = dups;
    this._type = type;
    this._lineNr = lineNr;
    this._columnNr = colNr;
    this._index = -1;
    this._nestingDepth = (parent == null) ? 0 : (parent._nestingDepth + 1);
  }
  
  public void reset(int type, int lineNr, int colNr) {
    this._type = type;
    this._index = -1;
    this._lineNr = lineNr;
    this._columnNr = colNr;
    this._currentName = null;
    this._currentValue = null;
    if (this._dups != null)
      this._dups.reset(); 
  }
  
  public JsonReadContext withDupDetector(DupDetector dups) {
    this._dups = dups;
    return this;
  }
  
  public Object getCurrentValue() {
    return this._currentValue;
  }
  
  public void setCurrentValue(Object v) {
    this._currentValue = v;
  }
  
  public static JsonReadContext createRootContext(int lineNr, int colNr, DupDetector dups) {
    return new JsonReadContext(null, 0, dups, 0, lineNr, colNr);
  }
  
  public static JsonReadContext createRootContext(DupDetector dups) {
    return new JsonReadContext(null, 0, dups, 0, 1, 0);
  }
  
  public JsonReadContext createChildArrayContext(int lineNr, int colNr) {
    JsonReadContext ctxt = this._child;
    if (ctxt == null) {
      this
        ._child = ctxt = new JsonReadContext(this, this._nestingDepth + 1, (this._dups == null) ? null : this._dups.child(), 1, lineNr, colNr);
    } else {
      ctxt.reset(1, lineNr, colNr);
    } 
    return ctxt;
  }
  
  public JsonReadContext createChildObjectContext(int lineNr, int colNr) {
    JsonReadContext ctxt = this._child;
    if (ctxt == null) {
      this
        ._child = ctxt = new JsonReadContext(this, this._nestingDepth + 1, (this._dups == null) ? null : this._dups.child(), 2, lineNr, colNr);
      return ctxt;
    } 
    ctxt.reset(2, lineNr, colNr);
    return ctxt;
  }
  
  public String getCurrentName() {
    return this._currentName;
  }
  
  public boolean hasCurrentName() {
    return (this._currentName != null);
  }
  
  public JsonReadContext getParent() {
    return this._parent;
  }
  
  public JsonLocation startLocation(ContentReference srcRef) {
    long totalChars = -1L;
    return new JsonLocation(srcRef, totalChars, this._lineNr, this._columnNr);
  }
  
  @Deprecated
  public JsonLocation getStartLocation(Object rawSrc) {
    return startLocation(ContentReference.rawReference(rawSrc));
  }
  
  public JsonReadContext clearAndGetParent() {
    this._currentValue = null;
    return this._parent;
  }
  
  public DupDetector getDupDetector() {
    return this._dups;
  }
  
  public boolean expectComma() {
    int ix = ++this._index;
    return (this._type != 0 && ix > 0);
  }
  
  public void setCurrentName(String name) throws JsonProcessingException {
    this._currentName = name;
    if (this._dups != null)
      _checkDup(this._dups, name); 
  }
  
  private void _checkDup(DupDetector dd, String name) throws JsonProcessingException {
    if (dd.isDup(name)) {
      Object src = dd.getSource();
      throw new JsonParseException((src instanceof JsonParser) ? (JsonParser)src : null, "Duplicate field '" + name + "'");
    } 
  }
}
