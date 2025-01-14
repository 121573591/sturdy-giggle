package org.openjdk.nashorn.internal.ir.visitor;

import org.openjdk.nashorn.internal.ir.BinaryNode;
import org.openjdk.nashorn.internal.ir.LexicalContext;
import org.openjdk.nashorn.internal.ir.Node;
import org.openjdk.nashorn.internal.ir.UnaryNode;
import org.openjdk.nashorn.internal.parser.TokenType;

public abstract class NodeOperatorVisitor<T extends LexicalContext> extends NodeVisitor<T> {
  public NodeOperatorVisitor(T lc) {
    super(lc);
  }
  
  public boolean enterUnaryNode(UnaryNode unaryNode) {
    switch (unaryNode.tokenType()) {
      case POS:
        return enterPOS(unaryNode);
      case BIT_NOT:
        return enterBIT_NOT(unaryNode);
      case DELETE:
        return enterDELETE(unaryNode);
      case NEW:
        return enterNEW(unaryNode);
      case NOT:
        return enterNOT(unaryNode);
      case NEG:
        return enterNEG(unaryNode);
      case TYPEOF:
        return enterTYPEOF(unaryNode);
      case VOID:
        return enterVOID(unaryNode);
      case DECPREFIX:
      case DECPOSTFIX:
      case INCPREFIX:
      case INCPOSTFIX:
        return enterDECINC(unaryNode);
    } 
    return super.enterUnaryNode(unaryNode);
  }
  
  public final Node leaveUnaryNode(UnaryNode unaryNode) {
    switch (unaryNode.tokenType()) {
      case POS:
        return leavePOS(unaryNode);
      case BIT_NOT:
        return leaveBIT_NOT(unaryNode);
      case DELETE:
        return leaveDELETE(unaryNode);
      case NEW:
        return leaveNEW(unaryNode);
      case NOT:
        return leaveNOT(unaryNode);
      case NEG:
        return leaveNEG(unaryNode);
      case TYPEOF:
        return leaveTYPEOF(unaryNode);
      case VOID:
        return leaveVOID(unaryNode);
      case DECPREFIX:
      case DECPOSTFIX:
      case INCPREFIX:
      case INCPOSTFIX:
        return leaveDECINC(unaryNode);
    } 
    return super.leaveUnaryNode(unaryNode);
  }
  
  public final boolean enterBinaryNode(BinaryNode binaryNode) {
    switch (binaryNode.tokenType()) {
      case ADD:
        return enterADD(binaryNode);
      case AND:
        return enterAND(binaryNode);
      case ASSIGN:
        return enterASSIGN(binaryNode);
      case ASSIGN_ADD:
        return enterASSIGN_ADD(binaryNode);
      case ASSIGN_BIT_AND:
        return enterASSIGN_BIT_AND(binaryNode);
      case ASSIGN_BIT_OR:
        return enterASSIGN_BIT_OR(binaryNode);
      case ASSIGN_BIT_XOR:
        return enterASSIGN_BIT_XOR(binaryNode);
      case ASSIGN_DIV:
        return enterASSIGN_DIV(binaryNode);
      case ASSIGN_MOD:
        return enterASSIGN_MOD(binaryNode);
      case ASSIGN_MUL:
        return enterASSIGN_MUL(binaryNode);
      case ASSIGN_SAR:
        return enterASSIGN_SAR(binaryNode);
      case ASSIGN_SHL:
        return enterASSIGN_SHL(binaryNode);
      case ASSIGN_SHR:
        return enterASSIGN_SHR(binaryNode);
      case ASSIGN_SUB:
        return enterASSIGN_SUB(binaryNode);
      case ARROW:
        return enterARROW(binaryNode);
      case BIT_AND:
        return enterBIT_AND(binaryNode);
      case BIT_OR:
        return enterBIT_OR(binaryNode);
      case BIT_XOR:
        return enterBIT_XOR(binaryNode);
      case COMMARIGHT:
        return enterCOMMARIGHT(binaryNode);
      case DIV:
        return enterDIV(binaryNode);
      case EQ:
        return enterEQ(binaryNode);
      case EQ_STRICT:
        return enterEQ_STRICT(binaryNode);
      case GE:
        return enterGE(binaryNode);
      case GT:
        return enterGT(binaryNode);
      case IN:
        return enterIN(binaryNode);
      case INSTANCEOF:
        return enterINSTANCEOF(binaryNode);
      case LE:
        return enterLE(binaryNode);
      case LT:
        return enterLT(binaryNode);
      case MOD:
        return enterMOD(binaryNode);
      case MUL:
        return enterMUL(binaryNode);
      case NE:
        return enterNE(binaryNode);
      case NE_STRICT:
        return enterNE_STRICT(binaryNode);
      case OR:
        return enterOR(binaryNode);
      case SAR:
        return enterSAR(binaryNode);
      case SHL:
        return enterSHL(binaryNode);
      case SHR:
        return enterSHR(binaryNode);
      case SUB:
        return enterSUB(binaryNode);
    } 
    return super.enterBinaryNode(binaryNode);
  }
  
  public final Node leaveBinaryNode(BinaryNode binaryNode) {
    switch (binaryNode.tokenType()) {
      case ADD:
        return leaveADD(binaryNode);
      case AND:
        return leaveAND(binaryNode);
      case ASSIGN:
        return leaveASSIGN(binaryNode);
      case ASSIGN_ADD:
        return leaveASSIGN_ADD(binaryNode);
      case ASSIGN_BIT_AND:
        return leaveASSIGN_BIT_AND(binaryNode);
      case ASSIGN_BIT_OR:
        return leaveASSIGN_BIT_OR(binaryNode);
      case ASSIGN_BIT_XOR:
        return leaveASSIGN_BIT_XOR(binaryNode);
      case ASSIGN_DIV:
        return leaveASSIGN_DIV(binaryNode);
      case ASSIGN_MOD:
        return leaveASSIGN_MOD(binaryNode);
      case ASSIGN_MUL:
        return leaveASSIGN_MUL(binaryNode);
      case ASSIGN_SAR:
        return leaveASSIGN_SAR(binaryNode);
      case ASSIGN_SHL:
        return leaveASSIGN_SHL(binaryNode);
      case ASSIGN_SHR:
        return leaveASSIGN_SHR(binaryNode);
      case ASSIGN_SUB:
        return leaveASSIGN_SUB(binaryNode);
      case ARROW:
        return leaveARROW(binaryNode);
      case BIT_AND:
        return leaveBIT_AND(binaryNode);
      case BIT_OR:
        return leaveBIT_OR(binaryNode);
      case BIT_XOR:
        return leaveBIT_XOR(binaryNode);
      case COMMARIGHT:
        return leaveCOMMARIGHT(binaryNode);
      case DIV:
        return leaveDIV(binaryNode);
      case EQ:
        return leaveEQ(binaryNode);
      case EQ_STRICT:
        return leaveEQ_STRICT(binaryNode);
      case GE:
        return leaveGE(binaryNode);
      case GT:
        return leaveGT(binaryNode);
      case IN:
        return leaveIN(binaryNode);
      case INSTANCEOF:
        return leaveINSTANCEOF(binaryNode);
      case LE:
        return leaveLE(binaryNode);
      case LT:
        return leaveLT(binaryNode);
      case MOD:
        return leaveMOD(binaryNode);
      case MUL:
        return leaveMUL(binaryNode);
      case NE:
        return leaveNE(binaryNode);
      case NE_STRICT:
        return leaveNE_STRICT(binaryNode);
      case OR:
        return leaveOR(binaryNode);
      case SAR:
        return leaveSAR(binaryNode);
      case SHL:
        return leaveSHL(binaryNode);
      case SHR:
        return leaveSHR(binaryNode);
      case SUB:
        return leaveSUB(binaryNode);
    } 
    return super.leaveBinaryNode(binaryNode);
  }
  
  public boolean enterPOS(UnaryNode unaryNode) {
    return enterDefault((Node)unaryNode);
  }
  
  public Node leavePOS(UnaryNode unaryNode) {
    return leaveDefault((Node)unaryNode);
  }
  
  public boolean enterBIT_NOT(UnaryNode unaryNode) {
    return enterDefault((Node)unaryNode);
  }
  
  public Node leaveBIT_NOT(UnaryNode unaryNode) {
    return leaveDefault((Node)unaryNode);
  }
  
  public boolean enterDECINC(UnaryNode unaryNode) {
    return enterDefault((Node)unaryNode);
  }
  
  public Node leaveDECINC(UnaryNode unaryNode) {
    return leaveDefault((Node)unaryNode);
  }
  
  public boolean enterDELETE(UnaryNode unaryNode) {
    return enterDefault((Node)unaryNode);
  }
  
  public Node leaveDELETE(UnaryNode unaryNode) {
    return leaveDefault((Node)unaryNode);
  }
  
  public boolean enterNEW(UnaryNode unaryNode) {
    return enterDefault((Node)unaryNode);
  }
  
  public Node leaveNEW(UnaryNode unaryNode) {
    return leaveDefault((Node)unaryNode);
  }
  
  public boolean enterNOT(UnaryNode unaryNode) {
    return enterDefault((Node)unaryNode);
  }
  
  public Node leaveNOT(UnaryNode unaryNode) {
    return leaveDefault((Node)unaryNode);
  }
  
  public boolean enterNEG(UnaryNode unaryNode) {
    return enterDefault((Node)unaryNode);
  }
  
  public Node leaveNEG(UnaryNode unaryNode) {
    return leaveDefault((Node)unaryNode);
  }
  
  public boolean enterTYPEOF(UnaryNode unaryNode) {
    return enterDefault((Node)unaryNode);
  }
  
  public Node leaveTYPEOF(UnaryNode unaryNode) {
    return leaveDefault((Node)unaryNode);
  }
  
  public boolean enterVOID(UnaryNode unaryNode) {
    return enterDefault((Node)unaryNode);
  }
  
  public Node leaveVOID(UnaryNode unaryNode) {
    return leaveDefault((Node)unaryNode);
  }
  
  public boolean enterADD(BinaryNode binaryNode) {
    return enterDefault((Node)binaryNode);
  }
  
  public Node leaveADD(BinaryNode binaryNode) {
    return leaveDefault((Node)binaryNode);
  }
  
  public boolean enterAND(BinaryNode binaryNode) {
    return enterDefault((Node)binaryNode);
  }
  
  public Node leaveAND(BinaryNode binaryNode) {
    return leaveDefault((Node)binaryNode);
  }
  
  public boolean enterASSIGN(BinaryNode binaryNode) {
    return enterDefault((Node)binaryNode);
  }
  
  public Node leaveASSIGN(BinaryNode binaryNode) {
    return leaveDefault((Node)binaryNode);
  }
  
  public boolean enterASSIGN_ADD(BinaryNode binaryNode) {
    return enterDefault((Node)binaryNode);
  }
  
  public Node leaveASSIGN_ADD(BinaryNode binaryNode) {
    return leaveDefault((Node)binaryNode);
  }
  
  public boolean enterASSIGN_BIT_AND(BinaryNode binaryNode) {
    return enterDefault((Node)binaryNode);
  }
  
  public Node leaveASSIGN_BIT_AND(BinaryNode binaryNode) {
    return leaveDefault((Node)binaryNode);
  }
  
  public boolean enterASSIGN_BIT_OR(BinaryNode binaryNode) {
    return enterDefault((Node)binaryNode);
  }
  
  public Node leaveASSIGN_BIT_OR(BinaryNode binaryNode) {
    return leaveDefault((Node)binaryNode);
  }
  
  public boolean enterASSIGN_BIT_XOR(BinaryNode binaryNode) {
    return enterDefault((Node)binaryNode);
  }
  
  public Node leaveASSIGN_BIT_XOR(BinaryNode binaryNode) {
    return leaveDefault((Node)binaryNode);
  }
  
  public boolean enterASSIGN_DIV(BinaryNode binaryNode) {
    return enterDefault((Node)binaryNode);
  }
  
  public Node leaveASSIGN_DIV(BinaryNode binaryNode) {
    return leaveDefault((Node)binaryNode);
  }
  
  public boolean enterASSIGN_MOD(BinaryNode binaryNode) {
    return enterDefault((Node)binaryNode);
  }
  
  public Node leaveASSIGN_MOD(BinaryNode binaryNode) {
    return leaveDefault((Node)binaryNode);
  }
  
  public boolean enterASSIGN_MUL(BinaryNode binaryNode) {
    return enterDefault((Node)binaryNode);
  }
  
  public Node leaveASSIGN_MUL(BinaryNode binaryNode) {
    return leaveDefault((Node)binaryNode);
  }
  
  public boolean enterASSIGN_SAR(BinaryNode binaryNode) {
    return enterDefault((Node)binaryNode);
  }
  
  public Node leaveASSIGN_SAR(BinaryNode binaryNode) {
    return leaveDefault((Node)binaryNode);
  }
  
  public boolean enterASSIGN_SHL(BinaryNode binaryNode) {
    return enterDefault((Node)binaryNode);
  }
  
  public Node leaveASSIGN_SHL(BinaryNode binaryNode) {
    return leaveDefault((Node)binaryNode);
  }
  
  public boolean enterASSIGN_SHR(BinaryNode binaryNode) {
    return enterDefault((Node)binaryNode);
  }
  
  public Node leaveASSIGN_SHR(BinaryNode binaryNode) {
    return leaveDefault((Node)binaryNode);
  }
  
  public boolean enterASSIGN_SUB(BinaryNode binaryNode) {
    return enterDefault((Node)binaryNode);
  }
  
  public Node leaveASSIGN_SUB(BinaryNode binaryNode) {
    return leaveDefault((Node)binaryNode);
  }
  
  public boolean enterARROW(BinaryNode binaryNode) {
    return enterDefault((Node)binaryNode);
  }
  
  public Node leaveARROW(BinaryNode binaryNode) {
    return leaveDefault((Node)binaryNode);
  }
  
  public boolean enterBIT_AND(BinaryNode binaryNode) {
    return enterDefault((Node)binaryNode);
  }
  
  public Node leaveBIT_AND(BinaryNode binaryNode) {
    return leaveDefault((Node)binaryNode);
  }
  
  public boolean enterBIT_OR(BinaryNode binaryNode) {
    return enterDefault((Node)binaryNode);
  }
  
  public Node leaveBIT_OR(BinaryNode binaryNode) {
    return leaveDefault((Node)binaryNode);
  }
  
  public boolean enterBIT_XOR(BinaryNode binaryNode) {
    return enterDefault((Node)binaryNode);
  }
  
  public Node leaveBIT_XOR(BinaryNode binaryNode) {
    return leaveDefault((Node)binaryNode);
  }
  
  public boolean enterCOMMARIGHT(BinaryNode binaryNode) {
    return enterDefault((Node)binaryNode);
  }
  
  public Node leaveCOMMARIGHT(BinaryNode binaryNode) {
    return leaveDefault((Node)binaryNode);
  }
  
  public boolean enterDIV(BinaryNode binaryNode) {
    return enterDefault((Node)binaryNode);
  }
  
  public Node leaveDIV(BinaryNode binaryNode) {
    return leaveDefault((Node)binaryNode);
  }
  
  public boolean enterEQ(BinaryNode binaryNode) {
    return enterDefault((Node)binaryNode);
  }
  
  public Node leaveEQ(BinaryNode binaryNode) {
    return leaveDefault((Node)binaryNode);
  }
  
  public boolean enterEQ_STRICT(BinaryNode binaryNode) {
    return enterDefault((Node)binaryNode);
  }
  
  public Node leaveEQ_STRICT(BinaryNode binaryNode) {
    return leaveDefault((Node)binaryNode);
  }
  
  public boolean enterGE(BinaryNode binaryNode) {
    return enterDefault((Node)binaryNode);
  }
  
  public Node leaveGE(BinaryNode binaryNode) {
    return leaveDefault((Node)binaryNode);
  }
  
  public boolean enterGT(BinaryNode binaryNode) {
    return enterDefault((Node)binaryNode);
  }
  
  public Node leaveGT(BinaryNode binaryNode) {
    return leaveDefault((Node)binaryNode);
  }
  
  public boolean enterIN(BinaryNode binaryNode) {
    return enterDefault((Node)binaryNode);
  }
  
  public Node leaveIN(BinaryNode binaryNode) {
    return leaveDefault((Node)binaryNode);
  }
  
  public boolean enterINSTANCEOF(BinaryNode binaryNode) {
    return enterDefault((Node)binaryNode);
  }
  
  public Node leaveINSTANCEOF(BinaryNode binaryNode) {
    return leaveDefault((Node)binaryNode);
  }
  
  public boolean enterLE(BinaryNode binaryNode) {
    return enterDefault((Node)binaryNode);
  }
  
  public Node leaveLE(BinaryNode binaryNode) {
    return leaveDefault((Node)binaryNode);
  }
  
  public boolean enterLT(BinaryNode binaryNode) {
    return enterDefault((Node)binaryNode);
  }
  
  public Node leaveLT(BinaryNode binaryNode) {
    return leaveDefault((Node)binaryNode);
  }
  
  public boolean enterMOD(BinaryNode binaryNode) {
    return enterDefault((Node)binaryNode);
  }
  
  public Node leaveMOD(BinaryNode binaryNode) {
    return leaveDefault((Node)binaryNode);
  }
  
  public boolean enterMUL(BinaryNode binaryNode) {
    return enterDefault((Node)binaryNode);
  }
  
  public Node leaveMUL(BinaryNode binaryNode) {
    return leaveDefault((Node)binaryNode);
  }
  
  public boolean enterNE(BinaryNode binaryNode) {
    return enterDefault((Node)binaryNode);
  }
  
  public Node leaveNE(BinaryNode binaryNode) {
    return leaveDefault((Node)binaryNode);
  }
  
  public boolean enterNE_STRICT(BinaryNode binaryNode) {
    return enterDefault((Node)binaryNode);
  }
  
  public Node leaveNE_STRICT(BinaryNode binaryNode) {
    return leaveDefault((Node)binaryNode);
  }
  
  public boolean enterOR(BinaryNode binaryNode) {
    return enterDefault((Node)binaryNode);
  }
  
  public Node leaveOR(BinaryNode binaryNode) {
    return leaveDefault((Node)binaryNode);
  }
  
  public boolean enterSAR(BinaryNode binaryNode) {
    return enterDefault((Node)binaryNode);
  }
  
  public Node leaveSAR(BinaryNode binaryNode) {
    return leaveDefault((Node)binaryNode);
  }
  
  public boolean enterSHL(BinaryNode binaryNode) {
    return enterDefault((Node)binaryNode);
  }
  
  public Node leaveSHL(BinaryNode binaryNode) {
    return leaveDefault((Node)binaryNode);
  }
  
  public boolean enterSHR(BinaryNode binaryNode) {
    return enterDefault((Node)binaryNode);
  }
  
  public Node leaveSHR(BinaryNode binaryNode) {
    return leaveDefault((Node)binaryNode);
  }
  
  public boolean enterSUB(BinaryNode binaryNode) {
    return enterDefault((Node)binaryNode);
  }
  
  public Node leaveSUB(BinaryNode binaryNode) {
    return leaveDefault((Node)binaryNode);
  }
}
