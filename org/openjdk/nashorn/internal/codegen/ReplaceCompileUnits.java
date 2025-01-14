package org.openjdk.nashorn.internal.codegen;

import java.util.ArrayList;
import java.util.List;
import org.openjdk.nashorn.internal.ir.CompileUnitHolder;
import org.openjdk.nashorn.internal.ir.FunctionNode;
import org.openjdk.nashorn.internal.ir.LiteralNode;
import org.openjdk.nashorn.internal.ir.Node;
import org.openjdk.nashorn.internal.ir.ObjectNode;
import org.openjdk.nashorn.internal.ir.Splittable;
import org.openjdk.nashorn.internal.ir.visitor.SimpleNodeVisitor;

abstract class ReplaceCompileUnits extends SimpleNodeVisitor {
  abstract CompileUnit getReplacement(CompileUnit paramCompileUnit);
  
  CompileUnit getExistingReplacement(CompileUnitHolder node) {
    CompileUnit oldUnit = node.getCompileUnit();
    assert oldUnit != null;
    CompileUnit newUnit = getReplacement(oldUnit);
    assert newUnit != null;
    return newUnit;
  }
  
  public Node leaveFunctionNode(FunctionNode node) {
    return (Node)node.setCompileUnit(this.lc, getExistingReplacement((CompileUnitHolder)node));
  }
  
  public Node leaveLiteralNode(LiteralNode<?> node) {
    if (node instanceof LiteralNode.ArrayLiteralNode) {
      LiteralNode.ArrayLiteralNode aln = (LiteralNode.ArrayLiteralNode)node;
      if (aln.getSplitRanges() == null)
        return (Node)node; 
      List<Splittable.SplitRange> newArrayUnits = new ArrayList<>();
      for (Splittable.SplitRange au : aln.getSplitRanges())
        newArrayUnits.add(new Splittable.SplitRange(getExistingReplacement((CompileUnitHolder)au), au.getLow(), au.getHigh())); 
      return (Node)aln.setSplitRanges(this.lc, newArrayUnits);
    } 
    return (Node)node;
  }
  
  public Node leaveObjectNode(ObjectNode objectNode) {
    List<Splittable.SplitRange> ranges = objectNode.getSplitRanges();
    if (ranges != null) {
      List<Splittable.SplitRange> newRanges = new ArrayList<>();
      for (Splittable.SplitRange range : ranges)
        newRanges.add(new Splittable.SplitRange(getExistingReplacement((CompileUnitHolder)range), range.getLow(), range.getHigh())); 
      return (Node)objectNode.setSplitRanges(this.lc, newRanges);
    } 
    return super.leaveObjectNode(objectNode);
  }
}
