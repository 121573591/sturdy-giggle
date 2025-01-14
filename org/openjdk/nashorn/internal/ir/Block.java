package org.openjdk.nashorn.internal.ir;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.openjdk.nashorn.internal.codegen.Label;
import org.openjdk.nashorn.internal.ir.annotations.Immutable;
import org.openjdk.nashorn.internal.ir.visitor.NodeVisitor;

@Immutable
public class Block extends Node implements BreakableNode, Terminal, Flags<Block> {
  private static final Comparator<Symbol> SYMBOL_NAME_COMPARATOR = Comparator.comparing(Symbol::getName);
  
  private static final long serialVersionUID = 1L;
  
  protected final List<Statement> statements;
  
  protected final Map<String, Symbol> symbols;
  
  private final Label entryLabel;
  
  private final Label breakLabel;
  
  protected final int flags;
  
  private final LocalVariableConversion conversion;
  
  public static final int NEEDS_SCOPE = 1;
  
  public static final int IS_TERMINAL = 4;
  
  public static final int IS_GLOBAL_SCOPE = 8;
  
  public static final int IS_SYNTHETIC = 16;
  
  public static final int IS_BODY = 32;
  
  public static final int IS_PARAMETER_BLOCK = 64;
  
  public static final int IS_SWITCH_BLOCK = 128;
  
  public static final int IS_BREAKABLE = 256;
  
  public Block(long token, int finish, int flags, Statement... statements) {
    super(token, finish);
    this.statements = List.of(statements);
    this.symbols = new LinkedHashMap<>();
    this.entryLabel = new Label("block_entry");
    this.breakLabel = new Label("block_break");
    int len = statements.length;
    int terminalFlags = (len > 0 && statements[len - 1].hasTerminalFlags()) ? 4 : 0;
    this.flags = terminalFlags | flags;
    this.conversion = null;
  }
  
  public Block(long token, int finish, Statement... statements) {
    this(token, finish, 16, statements);
  }
  
  public Block(long token, int finish, List<Statement> statements) {
    this(token, finish, 16, statements);
  }
  
  public Block(long token, int finish, int flags, List<Statement> statements) {
    this(token, finish, flags, statements.<Statement>toArray(new Statement[0]));
  }
  
  private Block(Block block, int finish, List<Statement> statements, int flags, Map<String, Symbol> symbols, LocalVariableConversion conversion) {
    super(block, finish);
    this.statements = statements;
    this.flags = flags;
    this.symbols = new LinkedHashMap<>(symbols);
    this.entryLabel = new Label(block.entryLabel);
    this.breakLabel = new Label(block.breakLabel);
    this.conversion = conversion;
  }
  
  public boolean isGlobalScope() {
    return getFlag(8);
  }
  
  public boolean hasSymbols() {
    return !this.symbols.isEmpty();
  }
  
  public Block replaceSymbols(LexicalContext lc, Map<Symbol, Symbol> replacements) {
    if (this.symbols.isEmpty())
      return this; 
    LinkedHashMap<String, Symbol> newSymbols = new LinkedHashMap<>(this.symbols);
    for (Map.Entry<String, Symbol> entry : newSymbols.entrySet()) {
      Symbol newSymbol = replacements.get(entry.getValue());
      assert newSymbol != null : "Missing replacement for " + (String)entry.getKey();
      entry.setValue(newSymbol);
    } 
    return Node.<Block>replaceInLexicalContext(lc, this, new Block(this, this.finish, this.statements, this.flags, newSymbols, this.conversion));
  }
  
  public Block copyWithNewSymbols() {
    return new Block(this, this.finish, this.statements, this.flags, new LinkedHashMap<>(this.symbols), this.conversion);
  }
  
  public Node ensureUniqueLabels(LexicalContext lc) {
    return Node.<Node>replaceInLexicalContext(lc, this, new Block(this, this.finish, this.statements, this.flags, this.symbols, this.conversion));
  }
  
  public Node accept(LexicalContext lc, NodeVisitor<? extends LexicalContext> visitor) {
    if (visitor.enterBlock(this))
      return visitor.leaveBlock(setStatements(lc, Node.accept(visitor, this.statements))); 
    return this;
  }
  
  public List<Symbol> getSymbols() {
    return this.symbols.isEmpty() ? List.<Symbol>of() : List.<Symbol>copyOf(this.symbols.values());
  }
  
  public Symbol getExistingSymbol(String name) {
    return this.symbols.get(name);
  }
  
  public boolean isCatchBlock() {
    return (this.statements.size() == 1 && this.statements.get(0) instanceof CatchNode);
  }
  
  public void toString(StringBuilder sb, boolean printType) {
    for (Node statement : this.statements) {
      statement.toString(sb, printType);
      sb.append(';');
    } 
  }
  
  public boolean printSymbols(PrintWriter stream) {
    List<Symbol> values = new ArrayList<>(this.symbols.values());
    values.sort(SYMBOL_NAME_COMPARATOR);
    for (Symbol symbol : values)
      symbol.print(stream); 
    return !values.isEmpty();
  }
  
  public Block setIsTerminal(LexicalContext lc, boolean isTerminal) {
    return isTerminal ? setFlag(lc, 4) : clearFlag(lc, 4);
  }
  
  public int getFlags() {
    return this.flags;
  }
  
  public boolean isTerminal() {
    return (getFlag(4) && !getFlag(256));
  }
  
  public Label getEntryLabel() {
    return this.entryLabel;
  }
  
  public Label getBreakLabel() {
    return this.breakLabel;
  }
  
  public Block setLocalVariableConversion(LexicalContext lc, LocalVariableConversion conversion) {
    if (this.conversion == conversion)
      return this; 
    return Node.<Block>replaceInLexicalContext(lc, this, new Block(this, this.finish, this.statements, this.flags, this.symbols, conversion));
  }
  
  public LocalVariableConversion getLocalVariableConversion() {
    return this.conversion;
  }
  
  public List<Statement> getStatements() {
    return Collections.unmodifiableList(this.statements);
  }
  
  public int getStatementCount() {
    return this.statements.size();
  }
  
  public int getFirstStatementLineNumber() {
    if (this.statements == null || this.statements.isEmpty())
      return -1; 
    return ((Statement)this.statements.get(0)).getLineNumber();
  }
  
  public Statement getLastStatement() {
    return this.statements.isEmpty() ? null : this.statements.get(this.statements.size() - 1);
  }
  
  public Block setStatements(LexicalContext lc, List<Statement> statements) {
    if (this.statements == statements)
      return this; 
    int lastFinish = 0;
    if (!statements.isEmpty())
      lastFinish = ((Statement)statements.get(statements.size() - 1)).getFinish(); 
    return Node.<Block>replaceInLexicalContext(lc, this, new Block(this, Math.max(this.finish, lastFinish), statements, this.flags, this.symbols, this.conversion));
  }
  
  public void putSymbol(Symbol symbol) {
    this.symbols.put(symbol.getName(), symbol);
  }
  
  public boolean needsScope() {
    return ((this.flags & 0x1) == 1);
  }
  
  public boolean isSynthetic() {
    return ((this.flags & 0x10) == 16);
  }
  
  public Block setFlags(LexicalContext lc, int flags) {
    if (this.flags == flags)
      return this; 
    return Node.<Block>replaceInLexicalContext(lc, this, new Block(this, this.finish, this.statements, flags, this.symbols, this.conversion));
  }
  
  public Block clearFlag(LexicalContext lc, int flag) {
    return setFlags(lc, this.flags & (flag ^ 0xFFFFFFFF));
  }
  
  public Block setFlag(LexicalContext lc, int flag) {
    return setFlags(lc, this.flags | flag);
  }
  
  public boolean getFlag(int flag) {
    return ((this.flags & flag) == flag);
  }
  
  public Block setNeedsScope(LexicalContext lc) {
    if (needsScope())
      return this; 
    return Node.<Block>replaceInLexicalContext(lc, this, new Block(this, this.finish, this.statements, this.flags | 0x1, this.symbols, this.conversion));
  }
  
  public int nextSlot() {
    int next = 0;
    for (Symbol symbol : getSymbols()) {
      if (symbol.hasSlot())
        next += symbol.slotCount(); 
    } 
    return next;
  }
  
  public boolean providesScopeCreator() {
    return (needsScope() && isSynthetic() && 
      getLastStatement() instanceof ForNode && ((ForNode)
      getLastStatement()).needsScopeCreator());
  }
  
  public boolean isBreakableWithoutLabel() {
    return false;
  }
  
  public List<Label> getLabels() {
    return List.of(this.entryLabel, this.breakLabel);
  }
  
  public Node accept(NodeVisitor<? extends LexicalContext> visitor) {
    return LexicalContextNode.Acceptor.accept(this, visitor);
  }
  
  public boolean isFunctionBody() {
    return getFlag(32);
  }
  
  public boolean isParameterBlock() {
    return getFlag(64);
  }
  
  public boolean isSwitchBlock() {
    return getFlag(128);
  }
}
