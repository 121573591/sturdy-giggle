package com.fasterxml.jackson.core.json;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.StreamWriteCapability;
import com.fasterxml.jackson.core.StreamWriteConstraints;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.base.GeneratorBase;
import com.fasterxml.jackson.core.io.CharTypes;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.IOContext;
import com.fasterxml.jackson.core.util.JacksonFeatureSet;
import com.fasterxml.jackson.core.util.VersionUtil;
import java.io.IOException;

public abstract class JsonGeneratorImpl extends GeneratorBase {
  protected static final int[] sOutputEscapes = CharTypes.get7BitOutputEscapes();
  
  protected static final JacksonFeatureSet<StreamWriteCapability> JSON_WRITE_CAPABILITIES = DEFAULT_TEXTUAL_WRITE_CAPABILITIES;
  
  protected final StreamWriteConstraints _streamWriteConstraints;
  
  protected int[] _outputEscapes = sOutputEscapes;
  
  protected int _maximumNonEscapedChar;
  
  protected CharacterEscapes _characterEscapes;
  
  protected SerializableString _rootValueSeparator = JsonFactory.DEFAULT_ROOT_VALUE_SEPARATOR;
  
  protected boolean _cfgUnqNames;
  
  protected boolean _cfgWriteHexUppercase;
  
  public JsonGeneratorImpl(IOContext ctxt, int features, ObjectCodec codec) {
    super(features, codec, ctxt);
    this._streamWriteConstraints = ctxt.streamWriteConstraints();
    if (JsonGenerator.Feature.ESCAPE_NON_ASCII.enabledIn(features))
      this._maximumNonEscapedChar = 127; 
    this._cfgWriteHexUppercase = JsonGenerator.Feature.WRITE_HEX_UPPER_CASE.enabledIn(features);
    this._cfgUnqNames = !JsonGenerator.Feature.QUOTE_FIELD_NAMES.enabledIn(features);
  }
  
  public Version version() {
    return VersionUtil.versionFor(getClass());
  }
  
  public StreamWriteConstraints streamWriteConstraints() {
    return this._streamWriteConstraints;
  }
  
  public JsonGenerator enable(JsonGenerator.Feature f) {
    super.enable(f);
    if (f == JsonGenerator.Feature.QUOTE_FIELD_NAMES) {
      this._cfgUnqNames = false;
    } else if (f == JsonGenerator.Feature.WRITE_HEX_UPPER_CASE) {
      this._cfgWriteHexUppercase = true;
    } 
    return (JsonGenerator)this;
  }
  
  public JsonGenerator disable(JsonGenerator.Feature f) {
    super.disable(f);
    if (f == JsonGenerator.Feature.QUOTE_FIELD_NAMES) {
      this._cfgUnqNames = true;
    } else if (f == JsonGenerator.Feature.WRITE_HEX_UPPER_CASE) {
      this._cfgWriteHexUppercase = false;
    } 
    return (JsonGenerator)this;
  }
  
  protected void _checkStdFeatureChanges(int newFeatureFlags, int changedFeatures) {
    super._checkStdFeatureChanges(newFeatureFlags, changedFeatures);
    this._cfgUnqNames = !JsonGenerator.Feature.QUOTE_FIELD_NAMES.enabledIn(newFeatureFlags);
    this._cfgWriteHexUppercase = JsonGenerator.Feature.WRITE_HEX_UPPER_CASE.enabledIn(newFeatureFlags);
  }
  
  public JsonGenerator setHighestNonEscapedChar(int charCode) {
    this._maximumNonEscapedChar = (charCode < 0) ? 0 : charCode;
    return (JsonGenerator)this;
  }
  
  public int getHighestEscapedChar() {
    return this._maximumNonEscapedChar;
  }
  
  public JsonGenerator setCharacterEscapes(CharacterEscapes esc) {
    this._characterEscapes = esc;
    if (esc == null) {
      this._outputEscapes = sOutputEscapes;
    } else {
      this._outputEscapes = esc.getEscapeCodesForAscii();
    } 
    return (JsonGenerator)this;
  }
  
  public CharacterEscapes getCharacterEscapes() {
    return this._characterEscapes;
  }
  
  public JsonGenerator setRootValueSeparator(SerializableString sep) {
    this._rootValueSeparator = sep;
    return (JsonGenerator)this;
  }
  
  public JacksonFeatureSet<StreamWriteCapability> getWriteCapabilities() {
    return JSON_WRITE_CAPABILITIES;
  }
  
  public IOContext ioContext() {
    return this._ioContext;
  }
  
  protected void _verifyPrettyValueWrite(String typeMsg, int status) throws IOException {
    switch (status) {
      case 1:
        this._cfgPrettyPrinter.writeArrayValueSeparator((JsonGenerator)this);
        return;
      case 2:
        this._cfgPrettyPrinter.writeObjectFieldValueSeparator((JsonGenerator)this);
        return;
      case 3:
        this._cfgPrettyPrinter.writeRootValueSeparator((JsonGenerator)this);
        return;
      case 0:
        if (this._writeContext.inArray()) {
          this._cfgPrettyPrinter.beforeArrayValues((JsonGenerator)this);
        } else if (this._writeContext.inObject()) {
          this._cfgPrettyPrinter.beforeObjectEntries((JsonGenerator)this);
        } 
        return;
      case 5:
        _reportCantWriteValueExpectName(typeMsg);
        return;
    } 
    _throwInternal();
  }
  
  protected void _reportCantWriteValueExpectName(String typeMsg) throws IOException {
    _reportError(String.format("Can not %s, expecting field name (context: %s)", new Object[] { typeMsg, this._writeContext
            .typeDesc() }));
  }
}
