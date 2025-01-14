package org.openjdk.nashorn.internal.ir;

import java.util.Collections;
import java.util.List;
import org.openjdk.nashorn.internal.codegen.types.Type;
import org.openjdk.nashorn.internal.ir.annotations.Ignore;
import org.openjdk.nashorn.internal.ir.annotations.Immutable;
import org.openjdk.nashorn.internal.ir.visitor.NodeVisitor;

@Immutable
public final class ObjectNode extends Expression implements LexicalContextNode, Splittable {
  private static final long serialVersionUID = 1L;
  
  private final List<PropertyNode> elements;
  
  @Ignore
  private final List<Splittable.SplitRange> splitRanges;
  
  public ObjectNode(long token, int finish, List<PropertyNode> elements) {
    super(token, finish);
    this.elements = elements;
    this.splitRanges = null;
    assert elements instanceof java.util.RandomAccess : "Splitting requires random access lists";
  }
  
  private ObjectNode(ObjectNode objectNode, List<PropertyNode> elements, List<Splittable.SplitRange> splitRanges) {
    super(objectNode);
    this.elements = elements;
    this.splitRanges = splitRanges;
  }
  
  public Node accept(NodeVisitor<? extends LexicalContext> visitor) {
    return LexicalContextNode.Acceptor.accept(this, visitor);
  }
  
  public Node accept(LexicalContext lc, NodeVisitor<? extends LexicalContext> visitor) {
    if (visitor.enterObjectNode(this))
      return visitor.leaveObjectNode(setElements(lc, Node.accept(visitor, this.elements))); 
    return this;
  }
  
  public Type getType() {
    return Type.OBJECT;
  }
  
  public void toString(StringBuilder sb, boolean printType) {
    sb.append('{');
    if (!this.elements.isEmpty()) {
      sb.append(' ');
      boolean first = true;
      for (Node element : this.elements) {
        if (!first)
          sb.append(", "); 
        first = false;
        element.toString(sb, printType);
      } 
      sb.append(' ');
    } 
    sb.append('}');
  }
  
  public List<PropertyNode> getElements() {
    return Collections.unmodifiableList(this.elements);
  }
  
  private ObjectNode setElements(LexicalContext lc, List<PropertyNode> elements) {
    if (this.elements == elements)
      return this; 
    return Node.<ObjectNode>replaceInLexicalContext(lc, this, new ObjectNode(this, elements, this.splitRanges));
  }
  
  public ObjectNode setSplitRanges(LexicalContext lc, List<Splittable.SplitRange> splitRanges) {
    if (this.splitRanges == splitRanges)
      return this; 
    return Node.<ObjectNode>replaceInLexicalContext(lc, this, new ObjectNode(this, this.elements, splitRanges));
  }
  
  public List<Splittable.SplitRange> getSplitRanges() {
    return (this.splitRanges == null) ? null : Collections.<Splittable.SplitRange>unmodifiableList(this.splitRanges);
  }
}
