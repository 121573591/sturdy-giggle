package org.openjdk.nashorn.internal.ir;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.openjdk.nashorn.internal.codegen.types.ArrayType;
import org.openjdk.nashorn.internal.codegen.types.BitwiseType;
import org.openjdk.nashorn.internal.codegen.types.Type;
import org.openjdk.nashorn.internal.ir.annotations.Ignore;
import org.openjdk.nashorn.internal.ir.annotations.Immutable;
import org.openjdk.nashorn.internal.ir.visitor.NodeVisitor;
import org.openjdk.nashorn.internal.objects.NativeArray;
import org.openjdk.nashorn.internal.parser.Lexer;
import org.openjdk.nashorn.internal.parser.Token;
import org.openjdk.nashorn.internal.parser.TokenType;
import org.openjdk.nashorn.internal.runtime.JSType;
import org.openjdk.nashorn.internal.runtime.ScriptRuntime;
import org.openjdk.nashorn.internal.runtime.Undefined;

@Immutable
public abstract class LiteralNode<T> extends Expression implements PropertyKey {
  private static final long serialVersionUID = 1L;
  
  protected final T value;
  
  public static final Object POSTSET_MARKER = new Object();
  
  protected LiteralNode(long token, int finish, T value) {
    super(token, finish);
    this.value = value;
  }
  
  protected LiteralNode(LiteralNode<T> literalNode) {
    this(literalNode, literalNode.value);
  }
  
  protected LiteralNode(LiteralNode<T> literalNode, T newValue) {
    super(literalNode);
    this.value = newValue;
  }
  
  public LiteralNode<?> initialize(LexicalContext lc) {
    return this;
  }
  
  public boolean isNull() {
    return (this.value == null);
  }
  
  public Type getType() {
    return Type.typeFor(this.value.getClass());
  }
  
  public String getPropertyName() {
    return JSType.toString(getObject());
  }
  
  public boolean getBoolean() {
    return JSType.toBoolean(this.value);
  }
  
  public int getInt32() {
    return JSType.toInt32(this.value);
  }
  
  public long getUint32() {
    return JSType.toUint32(this.value);
  }
  
  public long getLong() {
    return JSType.toLong(this.value);
  }
  
  public double getNumber() {
    return JSType.toNumber(this.value);
  }
  
  public String getString() {
    return JSType.toString(this.value);
  }
  
  public Object getObject() {
    return this.value;
  }
  
  public boolean isArray() {
    return false;
  }
  
  public List<Expression> getElementExpressions() {
    return null;
  }
  
  public boolean isBoolean() {
    return this.value instanceof Boolean;
  }
  
  public boolean isString() {
    return this.value instanceof String;
  }
  
  public boolean isNumeric() {
    return this.value instanceof Number;
  }
  
  public Node accept(NodeVisitor<? extends LexicalContext> visitor) {
    if (visitor.enterLiteralNode(this))
      return visitor.leaveLiteralNode(this); 
    return this;
  }
  
  public void toString(StringBuilder sb, boolean printType) {
    if (this.value == null) {
      sb.append("null");
    } else {
      sb.append(this.value.toString());
    } 
  }
  
  public final T getValue() {
    return this.value;
  }
  
  private static Expression[] valueToArray(List<Expression> value) {
    return value.<Expression>toArray(new Expression[0]);
  }
  
  public static LiteralNode<Object> newInstance(long token, int finish) {
    return new NullLiteralNode(token, finish);
  }
  
  public static LiteralNode<Object> newInstance(Node parent) {
    return new NullLiteralNode(parent.getToken(), parent.getFinish());
  }
  
  public static class PrimitiveLiteralNode<T> extends LiteralNode<T> {
    private static final long serialVersionUID = 1L;
    
    private PrimitiveLiteralNode(long token, int finish, T value) {
      super(token, finish, value);
    }
    
    public boolean isTrue() {
      return JSType.toBoolean(this.value);
    }
    
    public boolean isLocal() {
      return true;
    }
    
    public boolean isAlwaysFalse() {
      return !isTrue();
    }
    
    public boolean isAlwaysTrue() {
      return isTrue();
    }
  }
  
  @Immutable
  private static final class BooleanLiteralNode extends PrimitiveLiteralNode<Boolean> {
    private static final long serialVersionUID = 1L;
    
    private BooleanLiteralNode(long token, int finish, boolean value) {
      super(Token.recast(token, value ? TokenType.TRUE : TokenType.FALSE), finish, Boolean.valueOf(value));
    }
    
    public boolean isTrue() {
      return this.value.booleanValue();
    }
    
    public Type getType() {
      return Type.BOOLEAN;
    }
    
    public Type getWidestOperationType() {
      return Type.BOOLEAN;
    }
  }
  
  public static LiteralNode<Boolean> newInstance(long token, int finish, boolean value) {
    return new BooleanLiteralNode(token, finish, value);
  }
  
  public static LiteralNode<?> newInstance(Node parent, boolean value) {
    return new BooleanLiteralNode(parent.getToken(), parent.getFinish(), value);
  }
  
  @Immutable
  private static final class NumberLiteralNode extends PrimitiveLiteralNode<Number> {
    private static final long serialVersionUID = 1L;
    
    private final Type type = numberGetType(this.value);
    
    private NumberLiteralNode(long token, int finish, Number value) {
      super(Token.recast(token, TokenType.DECIMAL), finish, value);
    }
    
    private static Type numberGetType(Number number) {
      if (number instanceof Integer)
        return (Type)Type.INT; 
      if (number instanceof Double)
        return (Type)Type.NUMBER; 
      assert false;
      return null;
    }
    
    public Type getType() {
      return this.type;
    }
    
    public Type getWidestOperationType() {
      return getType();
    }
  }
  
  public static LiteralNode<Number> newInstance(long token, int finish, Number value) {
    assert !(value instanceof Long);
    return new NumberLiteralNode(token, finish, value);
  }
  
  public static LiteralNode<?> newInstance(Node parent, Number value) {
    return new NumberLiteralNode(parent.getToken(), parent.getFinish(), value);
  }
  
  private static class UndefinedLiteralNode extends PrimitiveLiteralNode<Undefined> {
    private static final long serialVersionUID = 1L;
    
    private UndefinedLiteralNode(long token, int finish) {
      super(Token.recast(token, TokenType.OBJECT), finish, ScriptRuntime.UNDEFINED);
    }
  }
  
  public static LiteralNode<Undefined> newInstance(long token, int finish, Undefined value) {
    return new UndefinedLiteralNode(token, finish);
  }
  
  public static LiteralNode<?> newInstance(Node parent, Undefined value) {
    return new UndefinedLiteralNode(parent.getToken(), parent.getFinish());
  }
  
  @Immutable
  private static class StringLiteralNode extends PrimitiveLiteralNode<String> {
    private static final long serialVersionUID = 1L;
    
    private StringLiteralNode(long token, int finish, String value) {
      super(Token.recast(token, TokenType.STRING), finish, value);
    }
    
    public void toString(StringBuilder sb, boolean printType) {
      sb.append('"');
      sb.append(this.value);
      sb.append('"');
    }
  }
  
  public static LiteralNode<String> newInstance(long token, int finish, String value) {
    return new StringLiteralNode(token, finish, value);
  }
  
  public static LiteralNode<?> newInstance(Node parent, String value) {
    return new StringLiteralNode(parent.getToken(), parent.getFinish(), value);
  }
  
  @Immutable
  private static class LexerTokenLiteralNode extends LiteralNode<Lexer.LexerToken> {
    private static final long serialVersionUID = 1L;
    
    private LexerTokenLiteralNode(long token, int finish, Lexer.LexerToken value) {
      super(Token.recast(token, TokenType.STRING), finish, value);
    }
    
    public Type getType() {
      return Type.OBJECT;
    }
    
    public void toString(StringBuilder sb, boolean printType) {
      sb.append(this.value.toString());
    }
  }
  
  public static LiteralNode<Lexer.LexerToken> newInstance(long token, int finish, Lexer.LexerToken value) {
    return new LexerTokenLiteralNode(token, finish, value);
  }
  
  public static LiteralNode<?> newInstance(Node parent, Lexer.LexerToken value) {
    return new LexerTokenLiteralNode(parent.getToken(), parent.getFinish(), value);
  }
  
  public static Object objectAsConstant(Object object) {
    if (object == null)
      return null; 
    if (object instanceof Number || object instanceof String || object instanceof Boolean)
      return object; 
    if (object instanceof LiteralNode)
      return objectAsConstant(((LiteralNode)object).getValue()); 
    return POSTSET_MARKER;
  }
  
  public static boolean isConstant(Object object) {
    return (objectAsConstant(object) != POSTSET_MARKER);
  }
  
  private static final class NullLiteralNode extends PrimitiveLiteralNode<Object> {
    private static final long serialVersionUID = 1L;
    
    private NullLiteralNode(long token, int finish) {
      super(Token.recast(token, TokenType.OBJECT), finish, null);
    }
    
    public Node accept(NodeVisitor<? extends LexicalContext> visitor) {
      if (visitor.enterLiteralNode(this))
        return visitor.leaveLiteralNode(this); 
      return this;
    }
    
    public Type getType() {
      return Type.OBJECT;
    }
    
    public Type getWidestOperationType() {
      return Type.OBJECT;
    }
  }
  
  @Immutable
  public static final class ArrayLiteralNode extends LiteralNode<Expression[]> implements LexicalContextNode, Splittable {
    private static final long serialVersionUID = 1L;
    
    private final Type elementType;
    
    private final Object presets;
    
    private final int[] postsets;
    
    @Ignore
    private final List<Splittable.SplitRange> splitRanges;
    
    private final boolean hasSpread;
    
    private final boolean hasTrailingComma;
    
    public boolean isArray() {
      return true;
    }
    
    private static final class ArrayLiteralInitializer {
      static LiteralNode.ArrayLiteralNode initialize(LiteralNode.ArrayLiteralNode node) {
        Type elementType = computeElementType(node.value);
        int[] postsets = computePostsets(node.value);
        Object presets = computePresets(node.value, elementType, postsets);
        return new LiteralNode.ArrayLiteralNode(node, node.value, elementType, postsets, presets, node.splitRanges);
      }
      
      private static Type computeElementType(Expression[] value) {
        Type type;
        BitwiseType bitwiseType = Type.INT;
        for (Expression elem : value) {
          if (elem == null) {
            type = bitwiseType.widest(Type.OBJECT);
            break;
          } 
          Type type1 = elem.getType().isUnknown() ? Type.OBJECT : elem.getType();
          if (type1.isBoolean()) {
            type = type.widest(Type.OBJECT);
            break;
          } 
          type = type.widest(type1);
          if (type.isObject())
            break; 
        } 
        return type;
      }
      
      private static int[] computePostsets(Expression[] value) {
        int[] computed = new int[value.length];
        int nComputed = 0;
        for (int i = 0; i < value.length; i++) {
          Expression element = value[i];
          if (element == null || !LiteralNode.isConstant(element))
            computed[nComputed++] = i; 
        } 
        return Arrays.copyOf(computed, nComputed);
      }
      
      private static boolean setArrayElement(int[] array, int i, Object n) {
        if (n instanceof Number) {
          array[i] = ((Number)n).intValue();
          return true;
        } 
        return false;
      }
      
      private static boolean setArrayElement(double[] array, int i, Object n) {
        if (n instanceof Number) {
          array[i] = ((Number)n).doubleValue();
          return true;
        } 
        return false;
      }
      
      private static int[] presetIntArray(Expression[] value, int[] postsets) {
        int[] array = new int[value.length];
        int nComputed = 0;
        for (int i = 0; i < value.length; i++) {
          if (!setArrayElement(array, i, LiteralNode.objectAsConstant(value[i])) && 
            !$assertionsDisabled && postsets[nComputed++] != i)
            throw new AssertionError(); 
        } 
        assert postsets.length == nComputed;
        return array;
      }
      
      private static double[] presetDoubleArray(Expression[] value, int[] postsets) {
        double[] array = new double[value.length];
        int nComputed = 0;
        for (int i = 0; i < value.length; i++) {
          if (!setArrayElement(array, i, LiteralNode.objectAsConstant(value[i])) && 
            !$assertionsDisabled && postsets[nComputed++] != i)
            throw new AssertionError(); 
        } 
        assert postsets.length == nComputed;
        return array;
      }
      
      private static Object[] presetObjectArray(Expression[] value, int[] postsets) {
        Object[] array = new Object[value.length];
        int nComputed = 0;
        for (int i = 0; i < value.length; i++) {
          Node node = value[i];
          if (node == null) {
            assert postsets[nComputed++] == i;
          } else {
            Object element = LiteralNode.objectAsConstant(node);
            if (element != LiteralNode.POSTSET_MARKER) {
              array[i] = element;
            } else {
              assert postsets[nComputed++] == i;
            } 
          } 
        } 
        assert postsets.length == nComputed;
        return array;
      }
      
      static Object computePresets(Expression[] value, Type elementType, int[] postsets) {
        assert !elementType.isUnknown();
        if (elementType.isInteger())
          return presetIntArray(value, postsets); 
        if (elementType.isNumeric())
          return presetDoubleArray(value, postsets); 
        return presetObjectArray(value, postsets);
      }
    }
    
    protected ArrayLiteralNode(long token, int finish, Expression[] value) {
      this(token, finish, value, false, false);
    }
    
    protected ArrayLiteralNode(long token, int finish, Expression[] value, boolean hasSpread, boolean hasTrailingComma) {
      super(Token.recast(token, TokenType.ARRAY), finish, value);
      this.elementType = Type.UNKNOWN;
      this.presets = null;
      this.postsets = null;
      this.splitRanges = null;
      this.hasSpread = hasSpread;
      this.hasTrailingComma = hasTrailingComma;
    }
    
    private ArrayLiteralNode(ArrayLiteralNode node, Expression[] value, Type elementType, int[] postsets, Object presets, List<Splittable.SplitRange> splitRanges) {
      super(node, value);
      this.elementType = elementType;
      this.postsets = postsets;
      this.presets = presets;
      this.splitRanges = splitRanges;
      this.hasSpread = node.hasSpread;
      this.hasTrailingComma = node.hasTrailingComma;
    }
    
    public boolean hasSpread() {
      return this.hasSpread;
    }
    
    public boolean hasTrailingComma() {
      return this.hasTrailingComma;
    }
    
    public List<Expression> getElementExpressions() {
      return Collections.unmodifiableList(Arrays.asList(this.value));
    }
    
    public ArrayLiteralNode initialize(LexicalContext lc) {
      return Node.<ArrayLiteralNode>replaceInLexicalContext(lc, this, ArrayLiteralInitializer.initialize(this));
    }
    
    public ArrayType getArrayType() {
      return getArrayType(getElementType());
    }
    
    private static ArrayType getArrayType(Type elementType) {
      if (elementType.isInteger())
        return Type.INT_ARRAY; 
      if (elementType.isNumeric())
        return Type.NUMBER_ARRAY; 
      return Type.OBJECT_ARRAY;
    }
    
    public Type getType() {
      return Type.typeFor(NativeArray.class);
    }
    
    public Type getElementType() {
      assert !this.elementType.isUnknown() : "" + this + " has elementType=unknown";
      return this.elementType;
    }
    
    public int[] getPostsets() {
      assert this.postsets != null : "" + this + " elementType=" + this + " has no postsets";
      return this.postsets;
    }
    
    private boolean presetsMatchElementType() {
      if (this.elementType == Type.INT)
        return this.presets instanceof int[]; 
      if (this.elementType == Type.NUMBER)
        return this.presets instanceof double[]; 
      return this.presets instanceof Object[];
    }
    
    public Object getPresets() {
      assert this.presets != null && presetsMatchElementType() : "" + this + " doesn't have presets, or invalid preset type: " + this;
      return this.presets;
    }
    
    public List<Splittable.SplitRange> getSplitRanges() {
      return (this.splitRanges == null) ? null : Collections.<Splittable.SplitRange>unmodifiableList(this.splitRanges);
    }
    
    public ArrayLiteralNode setSplitRanges(LexicalContext lc, List<Splittable.SplitRange> splitRanges) {
      if (this.splitRanges == splitRanges)
        return this; 
      return Node.<ArrayLiteralNode>replaceInLexicalContext(lc, this, new ArrayLiteralNode(this, this.value, this.elementType, this.postsets, this.presets, splitRanges));
    }
    
    public Node accept(NodeVisitor<? extends LexicalContext> visitor) {
      return LexicalContextNode.Acceptor.accept(this, visitor);
    }
    
    public Node accept(LexicalContext lc, NodeVisitor<? extends LexicalContext> visitor) {
      if (visitor.enterLiteralNode(this)) {
        List<Expression> oldValue = Arrays.asList(this.value);
        List<Expression> newValue = Node.accept(visitor, oldValue);
        return visitor.leaveLiteralNode((oldValue != newValue) ? setValue(lc, newValue) : this);
      } 
      return this;
    }
    
    private ArrayLiteralNode setValue(LexicalContext lc, Expression[] value) {
      if (this.value == value)
        return this; 
      return Node.<ArrayLiteralNode>replaceInLexicalContext(lc, this, new ArrayLiteralNode(this, value, this.elementType, this.postsets, this.presets, this.splitRanges));
    }
    
    private ArrayLiteralNode setValue(LexicalContext lc, List<Expression> value) {
      return setValue(lc, value.<Expression>toArray(new Expression[0]));
    }
    
    public void toString(StringBuilder sb, boolean printType) {
      sb.append('[');
      boolean first = true;
      for (Node node : this.value) {
        if (!first) {
          sb.append(',');
          sb.append(' ');
        } 
        if (node == null) {
          sb.append("undefined");
        } else {
          node.toString(sb, printType);
        } 
        first = false;
      } 
      sb.append(']');
    }
  }
  
  private static final class ArrayLiteralInitializer {
    static LiteralNode.ArrayLiteralNode initialize(LiteralNode.ArrayLiteralNode node) {
      Type elementType = computeElementType(node.value);
      int[] postsets = computePostsets(node.value);
      Object presets = computePresets(node.value, elementType, postsets);
      return new LiteralNode.ArrayLiteralNode(node, node.value, elementType, postsets, presets, node.splitRanges);
    }
    
    private static Type computeElementType(Expression[] value) {
      Type type;
      BitwiseType bitwiseType = Type.INT;
      for (Expression elem : value) {
        if (elem == null) {
          type = bitwiseType.widest(Type.OBJECT);
          break;
        } 
        Type type1 = elem.getType().isUnknown() ? Type.OBJECT : elem.getType();
        if (type1.isBoolean()) {
          type = type.widest(Type.OBJECT);
          break;
        } 
        type = type.widest(type1);
        if (type.isObject())
          break; 
      } 
      return type;
    }
    
    private static int[] computePostsets(Expression[] value) {
      int[] computed = new int[value.length];
      int nComputed = 0;
      for (int i = 0; i < value.length; i++) {
        Expression element = value[i];
        if (element == null || !LiteralNode.isConstant(element))
          computed[nComputed++] = i; 
      } 
      return Arrays.copyOf(computed, nComputed);
    }
    
    private static boolean setArrayElement(int[] array, int i, Object n) {
      if (n instanceof Number) {
        array[i] = ((Number)n).intValue();
        return true;
      } 
      return false;
    }
    
    private static boolean setArrayElement(double[] array, int i, Object n) {
      if (n instanceof Number) {
        array[i] = ((Number)n).doubleValue();
        return true;
      } 
      return false;
    }
    
    private static int[] presetIntArray(Expression[] value, int[] postsets) {
      int[] array = new int[value.length];
      int nComputed = 0;
      for (int i = 0; i < value.length; i++) {
        if (!setArrayElement(array, i, LiteralNode.objectAsConstant(value[i])) && !$assertionsDisabled && postsets[nComputed++] != i)
          throw new AssertionError(); 
      } 
      assert postsets.length == nComputed;
      return array;
    }
    
    private static double[] presetDoubleArray(Expression[] value, int[] postsets) {
      double[] array = new double[value.length];
      int nComputed = 0;
      for (int i = 0; i < value.length; i++) {
        if (!setArrayElement(array, i, LiteralNode.objectAsConstant(value[i])) && !$assertionsDisabled && postsets[nComputed++] != i)
          throw new AssertionError(); 
      } 
      assert postsets.length == nComputed;
      return array;
    }
    
    private static Object[] presetObjectArray(Expression[] value, int[] postsets) {
      Object[] array = new Object[value.length];
      int nComputed = 0;
      for (int i = 0; i < value.length; i++) {
        Node node = value[i];
        if (node == null) {
          assert postsets[nComputed++] == i;
        } else {
          Object element = LiteralNode.objectAsConstant(node);
          if (element != LiteralNode.POSTSET_MARKER) {
            array[i] = element;
          } else {
            assert postsets[nComputed++] == i;
          } 
        } 
      } 
      assert postsets.length == nComputed;
      return array;
    }
    
    static Object computePresets(Expression[] value, Type elementType, int[] postsets) {
      assert !elementType.isUnknown();
      if (elementType.isInteger())
        return presetIntArray(value, postsets); 
      if (elementType.isNumeric())
        return presetDoubleArray(value, postsets); 
      return presetObjectArray(value, postsets);
    }
  }
  
  public static LiteralNode<Expression[]> newInstance(long token, int finish, List<Expression> value) {
    return new ArrayLiteralNode(token, finish, valueToArray(value));
  }
  
  public static LiteralNode<?> newInstance(Node parent, List<Expression> value) {
    return new ArrayLiteralNode(parent.getToken(), parent.getFinish(), valueToArray(value));
  }
  
  public static LiteralNode<Expression[]> newInstance(long token, int finish, List<Expression> value, boolean hasSpread, boolean hasTrailingComma) {
    return new ArrayLiteralNode(token, finish, valueToArray(value), hasSpread, hasTrailingComma);
  }
  
  public static LiteralNode<Expression[]> newInstance(long token, int finish, Expression[] value) {
    return new ArrayLiteralNode(token, finish, value);
  }
}
