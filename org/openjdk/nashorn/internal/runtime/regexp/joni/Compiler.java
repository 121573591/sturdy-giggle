package org.openjdk.nashorn.internal.runtime.regexp.joni;

import org.openjdk.nashorn.internal.runtime.regexp.joni.ast.AnchorNode;
import org.openjdk.nashorn.internal.runtime.regexp.joni.ast.BackRefNode;
import org.openjdk.nashorn.internal.runtime.regexp.joni.ast.CClassNode;
import org.openjdk.nashorn.internal.runtime.regexp.joni.ast.ConsAltNode;
import org.openjdk.nashorn.internal.runtime.regexp.joni.ast.EncloseNode;
import org.openjdk.nashorn.internal.runtime.regexp.joni.ast.Node;
import org.openjdk.nashorn.internal.runtime.regexp.joni.ast.QuantifierNode;
import org.openjdk.nashorn.internal.runtime.regexp.joni.ast.StringNode;
import org.openjdk.nashorn.internal.runtime.regexp.joni.exception.ErrorMessages;
import org.openjdk.nashorn.internal.runtime.regexp.joni.exception.InternalException;
import org.openjdk.nashorn.internal.runtime.regexp.joni.exception.SyntaxException;

abstract class Compiler implements ErrorMessages {
  protected final Analyser analyser;
  
  protected final Regex regex;
  
  protected Compiler(Analyser analyser) {
    this.analyser = analyser;
    this.regex = analyser.regex;
  }
  
  final void compile() {
    prepare();
    compileTree(this.analyser.root);
    finish();
  }
  
  protected abstract void prepare();
  
  protected abstract void finish();
  
  protected abstract void compileAltNode(ConsAltNode paramConsAltNode);
  
  private void compileStringRawNode(StringNode sn) {
    if (sn.length() <= 0)
      return; 
    addCompileString(sn.chars, sn.p, sn.length(), false);
  }
  
  private void compileStringNode(StringNode node) {
    StringNode sn = node;
    if (sn.length() <= 0)
      return; 
    boolean ambig = sn.isAmbig();
    int prev = sn.p, p = prev;
    int end = sn.end;
    char[] chars = sn.chars;
    p++;
    int slen = 1;
    while (p < end) {
      slen++;
      p++;
    } 
    addCompileString(chars, prev, slen, ambig);
  }
  
  protected abstract void addCompileString(char[] paramArrayOfchar, int paramInt1, int paramInt2, boolean paramBoolean);
  
  protected abstract void compileCClassNode(CClassNode paramCClassNode);
  
  protected abstract void compileAnyCharNode();
  
  protected abstract void compileBackrefNode(BackRefNode paramBackRefNode);
  
  protected abstract void compileNonCECQuantifierNode(QuantifierNode paramQuantifierNode);
  
  protected abstract void compileOptionNode(EncloseNode paramEncloseNode);
  
  protected abstract void compileEncloseNode(EncloseNode paramEncloseNode);
  
  protected abstract void compileAnchorNode(AnchorNode paramAnchorNode);
  
  protected final void compileTree(Node node) {
    ConsAltNode lin;
    StringNode sn;
    EncloseNode enode;
    switch (node.getType()) {
      case 8:
        lin = (ConsAltNode)node;
        do {
          compileTree(lin.car);
        } while ((lin = lin.cdr) != null);
        return;
      case 9:
        compileAltNode((ConsAltNode)node);
        return;
      case 0:
        sn = (StringNode)node;
        if (sn.isRaw()) {
          compileStringRawNode(sn);
        } else {
          compileStringNode(sn);
        } 
        return;
      case 1:
        compileCClassNode((CClassNode)node);
        return;
      case 3:
        compileAnyCharNode();
        return;
      case 4:
        compileBackrefNode((BackRefNode)node);
        return;
      case 5:
        compileNonCECQuantifierNode((QuantifierNode)node);
        return;
      case 6:
        enode = (EncloseNode)node;
        if (enode.isOption()) {
          compileOptionNode(enode);
        } else {
          compileEncloseNode(enode);
        } 
        return;
      case 7:
        compileAnchorNode((AnchorNode)node);
        return;
    } 
    newInternalException("internal parser error (bug)");
  }
  
  protected final void compileTreeNTimes(Node node, int n) {
    for (int i = 0; i < n; i++)
      compileTree(node); 
  }
  
  protected void newSyntaxException(String message) {
    throw new SyntaxException(message);
  }
  
  protected void newInternalException(String message) {
    throw new InternalException(message);
  }
}
