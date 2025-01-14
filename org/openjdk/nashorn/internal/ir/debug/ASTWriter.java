package org.openjdk.nashorn.internal.ir.debug;

import java.lang.reflect.Field;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.openjdk.nashorn.internal.ir.Block;
import org.openjdk.nashorn.internal.ir.Expression;
import org.openjdk.nashorn.internal.ir.IdentNode;
import org.openjdk.nashorn.internal.ir.Node;
import org.openjdk.nashorn.internal.ir.Statement;
import org.openjdk.nashorn.internal.ir.Symbol;
import org.openjdk.nashorn.internal.ir.Terminal;
import org.openjdk.nashorn.internal.ir.annotations.Ignore;
import org.openjdk.nashorn.internal.ir.annotations.Reference;
import org.openjdk.nashorn.internal.parser.Token;
import org.openjdk.nashorn.internal.runtime.Context;

public final class ASTWriter {
  private static final ClassValue<Field[]> accessibleFields = new ClassValue<Field[]>() {
      protected Field[] computeValue(Class<?> type) {
        Field[] fields = type.getDeclaredFields();
        for (Field f : fields)
          f.setAccessible(true); 
        return fields;
      }
    };
  
  private final Node root;
  
  private static final int TABWIDTH = 4;
  
  public ASTWriter(Node root) {
    this.root = root;
  }
  
  public String toString() {
    StringBuilder sb = new StringBuilder();
    printAST(sb, null, null, "root", this.root, 0);
    return sb.toString();
  }
  
  public Node[] toArray() {
    List<Node> preorder = new ArrayList<>();
    printAST(new StringBuilder(), preorder, null, "root", this.root, 0);
    return preorder.<Node>toArray(new Node[0]);
  }
  
  private void printAST(StringBuilder sb, List<Node> preorder, Field field, String name, Node node, int indent) {
    Symbol symbol;
    indent(sb, indent);
    if (node == null) {
      sb.append("[Object ");
      sb.append(name);
      sb.append(" null]\n");
      return;
    } 
    if (preorder != null)
      preorder.add(node); 
    boolean isReference = (field != null && field.isAnnotationPresent((Class)Reference.class));
    Class<?> clazz = node.getClass();
    String type = clazz.getName();
    type = type.substring(type.lastIndexOf('.') + 1, type.length());
    int truncate = type.indexOf("Node");
    if (truncate == -1)
      truncate = type.indexOf("Statement"); 
    if (truncate != -1)
      type = type.substring(0, truncate); 
    type = type.toLowerCase();
    if (isReference)
      type = "ref: " + type; 
    if (node instanceof IdentNode) {
      symbol = ((IdentNode)node).getSymbol();
    } else {
      symbol = null;
    } 
    if (symbol != null)
      type = type + ">" + type; 
    if (node instanceof Block && ((Block)node).needsScope())
      type = type + " <scope>"; 
    List<Field> children = new LinkedList<>();
    if (!isReference)
      enqueueChildren(node, clazz, children); 
    String status = "";
    if (node instanceof Terminal && ((Terminal)node).isTerminal())
      status = status + " Terminal"; 
    if (node instanceof Statement && ((Statement)node).hasGoto())
      status = status + " Goto "; 
    if (symbol != null)
      status = status + status; 
    status = status.trim();
    if (!"".equals(status))
      status = " [" + status + "]"; 
    if (symbol != null) {
      String tname = ((Expression)node).getType().toString();
      if (tname.indexOf('.') != -1)
        tname = tname.substring(tname.lastIndexOf('.') + 1, tname.length()); 
      status = status + " (" + status + ")";
    } 
    status = status + " @" + status;
    if (children.isEmpty()) {
      sb.append("[")
        .append(type)
        .append(' ')
        .append(name)
        .append(" = '")
        .append(node)
        .append("'")
        .append(status)
        .append("] ")
        .append('\n');
    } else {
      sb.append("[")
        .append(type)
        .append(' ')
        .append(name)
        .append(' ')
        .append(Token.toString(node.getToken()))
        .append(status)
        .append("]")
        .append('\n');
      for (Field child : children) {
        Object value;
        if (child.isAnnotationPresent((Class)Ignore.class))
          continue; 
        try {
          value = child.get(node);
        } catch (IllegalArgumentException|IllegalAccessException e) {
          Context.printStackTrace(e);
          return;
        } 
        if (value instanceof Node) {
          printAST(sb, preorder, child, child.getName(), (Node)value, indent + 1);
          continue;
        } 
        if (value instanceof Collection) {
          int pos = 0;
          indent(sb, indent + 1);
          sb.append('[')
            .append(child.getName())
            .append("[0..")
            .append(((Collection)value).size())
            .append("]]")
            .append('\n');
          for (Node member : value)
            printAST(sb, preorder, child, child.getName() + "[" + child.getName() + "]", member, indent + 2); 
        } 
      } 
    } 
  }
  
  private static void enqueueChildren(Node node, Class<?> nodeClass, List<Field> children) {
    Deque<Class<?>> stack = new ArrayDeque<>();
    Class<?> clazz = nodeClass;
    do {
      stack.push(clazz);
      clazz = clazz.getSuperclass();
    } while (clazz != null);
    if (node instanceof org.openjdk.nashorn.internal.ir.TernaryNode)
      stack.push(stack.removeLast()); 
    Iterator<Class<?>> iter = (node instanceof org.openjdk.nashorn.internal.ir.BinaryNode) ? stack.descendingIterator() : stack.iterator();
    while (iter.hasNext()) {
      Class<?> c = iter.next();
      for (Field f : (Field[])accessibleFields.get(c)) {
        try {
          Object child = f.get(node);
          if (child != null)
            if (child instanceof Node) {
              children.add(f);
            } else if (child instanceof Collection && 
              !((Collection)child).isEmpty()) {
              children.add(f);
            }  
        } catch (IllegalArgumentException|IllegalAccessException e) {
          return;
        } 
      } 
    } 
  }
  
  private static void indent(StringBuilder sb, int indent) {
    for (int i = 0; i < indent; i++) {
      for (int j = 0; j < 4; j++)
        sb.append(' '); 
    } 
  }
}
