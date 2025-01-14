package org.openjdk.nashorn.internal.parser;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.openjdk.nashorn.internal.codegen.Namespace;
import org.openjdk.nashorn.internal.ir.Expression;
import org.openjdk.nashorn.internal.ir.FunctionNode;
import org.openjdk.nashorn.internal.ir.IdentNode;
import org.openjdk.nashorn.internal.ir.Module;

class ParserContextFunctionNode extends ParserContextBaseNode {
  private final String name;
  
  private final IdentNode ident;
  
  private final Namespace namespace;
  
  private final int line;
  
  private final FunctionNode.Kind kind;
  
  private List<IdentNode> parameters;
  
  private final long token;
  
  private long lastToken;
  
  private Object endParserState;
  
  private HashSet<String> parameterBoundNames;
  
  private IdentNode duplicateParameterBinding;
  
  private boolean simpleParameterList = true;
  
  private Module module;
  
  private int debugFlags;
  
  private Map<IdentNode, Expression> parameterExpressions;
  
  public ParserContextFunctionNode(long token, IdentNode ident, String name, Namespace namespace, int line, FunctionNode.Kind kind, List<IdentNode> parameters) {
    this.ident = ident;
    this.namespace = namespace;
    this.line = line;
    this.kind = kind;
    this.name = name;
    this.parameters = parameters;
    this.token = token;
  }
  
  public String getName() {
    return this.name;
  }
  
  public IdentNode getIdent() {
    return this.ident;
  }
  
  public boolean isProgram() {
    return (getFlag(8192) != 0);
  }
  
  public boolean isStrict() {
    return (getFlag(4) != 0);
  }
  
  public boolean hasNestedEval() {
    return (getFlag(64) != 0);
  }
  
  public boolean hasScopeBlock() {
    return (getFlag(128) != 0);
  }
  
  public String uniqueName(String base) {
    return this.namespace.uniqueName(base);
  }
  
  public int getLineNumber() {
    return this.line;
  }
  
  public FunctionNode.Kind getKind() {
    return this.kind;
  }
  
  public List<IdentNode> getParameters() {
    return this.parameters;
  }
  
  void setParameters(List<IdentNode> parameters) {
    this.parameters = parameters;
  }
  
  public Map<IdentNode, Expression> getParameterExpressions() {
    return this.parameterExpressions;
  }
  
  void addParameterExpression(IdentNode ident, Expression node) {
    if (this.parameterExpressions == null)
      this.parameterExpressions = new HashMap<>(); 
    this.parameterExpressions.put(ident, node);
  }
  
  public void setLastToken(long token) {
    this.lastToken = token;
  }
  
  public long getLastToken() {
    return this.lastToken;
  }
  
  public Object getEndParserState() {
    return this.endParserState;
  }
  
  public void setEndParserState(Object endParserState) {
    this.endParserState = endParserState;
  }
  
  public int getId() {
    return isProgram() ? -1 : Token.descPosition(this.token);
  }
  
  int getDebugFlags() {
    return this.debugFlags;
  }
  
  void setDebugFlag(int debugFlag) {
    this.debugFlags |= debugFlag;
  }
  
  public boolean isMethod() {
    return (getFlag(2097152) != 0);
  }
  
  public boolean isClassConstructor() {
    return (getFlag(4194304) != 0);
  }
  
  public boolean isSubclassConstructor() {
    return (getFlag(8388608) != 0);
  }
  
  void addParameterBinding(IdentNode bindingIdentifier) {
    if (Parser.isArguments(bindingIdentifier))
      setFlag(256); 
    if (this.parameterBoundNames == null)
      this.parameterBoundNames = new HashSet<>(); 
    if (!this.parameterBoundNames.add(bindingIdentifier.getName()))
      this.duplicateParameterBinding = bindingIdentifier; 
  }
  
  public IdentNode getDuplicateParameterBinding() {
    return this.duplicateParameterBinding;
  }
  
  public boolean isSimpleParameterList() {
    return this.simpleParameterList;
  }
  
  public void setSimpleParameterList(boolean simpleParameterList) {
    this.simpleParameterList = simpleParameterList;
  }
  
  public Module getModule() {
    return this.module;
  }
  
  public void setModule(Module module) {
    this.module = module;
  }
}
