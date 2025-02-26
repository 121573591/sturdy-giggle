package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.StreamReadCapability;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.cfg.DatatypeFeature;
import com.fasterxml.jackson.databind.cfg.DatatypeFeatures;
import com.fasterxml.jackson.databind.cfg.JsonNodeFeature;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.BooleanNode;
import com.fasterxml.jackson.databind.node.ContainerNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.NullNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.fasterxml.jackson.databind.type.LogicalType;
import com.fasterxml.jackson.databind.util.RawValue;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;

abstract class BaseNodeDeserializer<T extends JsonNode> extends StdDeserializer<T> implements ContextualDeserializer {
  protected final Boolean _supportsUpdates;
  
  protected final boolean _mergeArrays;
  
  protected final boolean _mergeObjects;
  
  public BaseNodeDeserializer(Class<T> vc, Boolean supportsUpdates) {
    super(vc);
    this._supportsUpdates = supportsUpdates;
    this._mergeArrays = true;
    this._mergeObjects = true;
  }
  
  protected BaseNodeDeserializer(BaseNodeDeserializer<?> base, boolean mergeArrays, boolean mergeObjects) {
    super(base);
    this._supportsUpdates = base._supportsUpdates;
    this._mergeArrays = mergeArrays;
    this._mergeObjects = mergeObjects;
  }
  
  public Object deserializeWithType(JsonParser p, DeserializationContext ctxt, TypeDeserializer typeDeserializer) throws IOException {
    return typeDeserializer.deserializeTypedFromAny(p, ctxt);
  }
  
  public LogicalType logicalType() {
    return LogicalType.Untyped;
  }
  
  public boolean isCachable() {
    return true;
  }
  
  public Boolean supportsUpdate(DeserializationConfig config) {
    return this._supportsUpdates;
  }
  
  public JsonDeserializer<?> createContextual(DeserializationContext ctxt, BeanProperty property) throws JsonMappingException {
    DeserializationConfig cfg = ctxt.getConfig();
    Boolean mergeArr = cfg.getDefaultMergeable(ArrayNode.class);
    Boolean mergeObj = cfg.getDefaultMergeable(ObjectNode.class);
    Boolean mergeNode = cfg.getDefaultMergeable(JsonNode.class);
    boolean mergeArrays = _shouldMerge(mergeArr, mergeNode);
    boolean mergeObjects = _shouldMerge(mergeObj, mergeNode);
    if (mergeArrays != this._mergeArrays || mergeObjects != this._mergeObjects)
      return _createWithMerge(mergeArrays, mergeObjects); 
    return this;
  }
  
  private static boolean _shouldMerge(Boolean specificMerge, Boolean generalMerge) {
    if (specificMerge != null)
      return specificMerge.booleanValue(); 
    if (generalMerge != null)
      return generalMerge.booleanValue(); 
    return true;
  }
  
  protected abstract JsonDeserializer<?> _createWithMerge(boolean paramBoolean1, boolean paramBoolean2);
  
  protected void _handleDuplicateField(JsonParser p, DeserializationContext ctxt, JsonNodeFactory nodeFactory, String fieldName, ObjectNode objectNode, JsonNode oldValue, JsonNode newValue) throws IOException {
    if (ctxt.isEnabled(DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY))
      ctxt.reportInputMismatch(JsonNode.class, "Duplicate field '%s' for `ObjectNode`: not allowed when `DeserializationFeature.FAIL_ON_READING_DUP_TREE_KEY` enabled", new Object[] { fieldName }); 
    if (ctxt.isEnabled(StreamReadCapability.DUPLICATE_PROPERTIES))
      if (oldValue.isArray()) {
        ((ArrayNode)oldValue).add(newValue);
        objectNode.replace(fieldName, oldValue);
      } else {
        ArrayNode arr = nodeFactory.arrayNode();
        arr.add(oldValue);
        arr.add(newValue);
        objectNode.replace(fieldName, (JsonNode)arr);
      }  
  }
  
  protected final ObjectNode _deserializeObjectAtName(JsonParser p, DeserializationContext ctxt, JsonNodeFactory nodeFactory, ContainerStack stack) throws IOException {
    ObjectNode node = nodeFactory.objectNode();
    String key = p.currentName();
    for (; key != null; key = p.nextFieldName()) {
      ContainerNode<?> containerNode2, containerNode1;
      JsonNode value;
      JsonToken t = p.nextToken();
      if (t == null)
        t = JsonToken.NOT_AVAILABLE; 
      switch (t.id()) {
        case 1:
          containerNode2 = _deserializeContainerNoRecursion(p, ctxt, nodeFactory, stack, (ContainerNode<?>)nodeFactory
              .objectNode());
          break;
        case 3:
          containerNode1 = _deserializeContainerNoRecursion(p, ctxt, nodeFactory, stack, (ContainerNode<?>)nodeFactory
              .arrayNode());
          break;
        default:
          value = _deserializeAnyScalar(p, ctxt);
          break;
      } 
      JsonNode old = node.replace(key, value);
      if (old != null)
        _handleDuplicateField(p, ctxt, nodeFactory, key, node, old, value); 
    } 
    return node;
  }
  
  protected final JsonNode updateObject(JsonParser p, DeserializationContext ctxt, ObjectNode node, ContainerStack stack) throws IOException {
    if (p.isExpectedStartObjectToken()) {
      str = p.nextFieldName();
    } else {
      if (!p.hasToken(JsonToken.FIELD_NAME))
        return (JsonNode)deserialize(p, ctxt); 
      str = p.currentName();
    } 
    JsonNodeFactory nodeFactory = ctxt.getNodeFactory();
    String str;
    for (; str != null; str = p.nextFieldName()) {
      ContainerNode<?> containerNode2, containerNode1;
      TextNode textNode;
      JsonNode jsonNode1;
      BooleanNode booleanNode2, booleanNode1;
      NullNode nullNode;
      JsonNode value;
      JsonToken t = p.nextToken();
      JsonNode old = node.get(str);
      if (old != null)
        if (old instanceof ObjectNode) {
          if (t == JsonToken.START_OBJECT && this._mergeObjects) {
            JsonNode newValue = updateObject(p, ctxt, (ObjectNode)old, stack);
            if (newValue != old)
              node.set(str, newValue); 
            continue;
          } 
        } else if (old instanceof ArrayNode) {
          if (t == JsonToken.START_ARRAY && this._mergeArrays) {
            _deserializeContainerNoRecursion(p, ctxt, nodeFactory, stack, (ContainerNode<?>)old);
            continue;
          } 
        }  
      if (t == null)
        t = JsonToken.NOT_AVAILABLE; 
      switch (t.id()) {
        case 1:
          containerNode2 = _deserializeContainerNoRecursion(p, ctxt, nodeFactory, stack, (ContainerNode<?>)nodeFactory
              .objectNode());
          node.set(str, (JsonNode)containerNode2);
          break;
        case 3:
          containerNode1 = _deserializeContainerNoRecursion(p, ctxt, nodeFactory, stack, (ContainerNode<?>)nodeFactory.arrayNode());
          node.set(str, (JsonNode)containerNode1);
          break;
        case 6:
          textNode = nodeFactory.textNode(p.getText());
          node.set(str, (JsonNode)textNode);
          break;
        case 7:
          jsonNode1 = _fromInt(p, ctxt, nodeFactory);
          node.set(str, jsonNode1);
          break;
        case 9:
          booleanNode2 = nodeFactory.booleanNode(true);
          node.set(str, (JsonNode)booleanNode2);
          break;
        case 10:
          booleanNode1 = nodeFactory.booleanNode(false);
          node.set(str, (JsonNode)booleanNode1);
          break;
        case 11:
          if (!ctxt.isEnabled((DatatypeFeature)JsonNodeFeature.READ_NULL_PROPERTIES))
            break; 
          nullNode = nodeFactory.nullNode();
          node.set(str, (JsonNode)nullNode);
          break;
        default:
          value = _deserializeRareScalar(p, ctxt);
          node.set(str, value);
          break;
      } 
      continue;
    } 
    return (JsonNode)node;
  }
  
  protected final ContainerNode<?> _deserializeContainerNoRecursion(JsonParser p, DeserializationContext ctxt, JsonNodeFactory nodeFactory, ContainerStack stack, ContainerNode<?> root) throws IOException {
    // Byte code:
    //   0: aload #5
    //   2: astore #6
    //   4: aload_2
    //   5: invokevirtual getDeserializationFeatures : ()I
    //   8: getstatic com/fasterxml/jackson/databind/deser/std/BaseNodeDeserializer.F_MASK_INT_COERCIONS : I
    //   11: iand
    //   12: istore #7
    //   14: aload #6
    //   16: instanceof com/fasterxml/jackson/databind/node/ObjectNode
    //   19: ifeq -> 356
    //   22: aload #6
    //   24: checkcast com/fasterxml/jackson/databind/node/ObjectNode
    //   27: astore #8
    //   29: aload_1
    //   30: invokevirtual nextFieldName : ()Ljava/lang/String;
    //   33: astore #9
    //   35: aload #9
    //   37: ifnull -> 353
    //   40: aload_1
    //   41: invokevirtual nextToken : ()Lcom/fasterxml/jackson/core/JsonToken;
    //   44: astore #11
    //   46: aload #11
    //   48: ifnonnull -> 56
    //   51: getstatic com/fasterxml/jackson/core/JsonToken.NOT_AVAILABLE : Lcom/fasterxml/jackson/core/JsonToken;
    //   54: astore #11
    //   56: aload #11
    //   58: invokevirtual id : ()I
    //   61: tableswitch default -> 305, 1 -> 120, 2 -> 305, 3 -> 174, 4 -> 305, 5 -> 305, 6 -> 225, 7 -> 238, 8 -> 251, 9 -> 263, 10 -> 273, 11 -> 283
    //   120: aload_3
    //   121: invokevirtual objectNode : ()Lcom/fasterxml/jackson/databind/node/ObjectNode;
    //   124: astore #12
    //   126: aload #8
    //   128: aload #9
    //   130: aload #12
    //   132: invokevirtual replace : (Ljava/lang/String;Lcom/fasterxml/jackson/databind/JsonNode;)Lcom/fasterxml/jackson/databind/JsonNode;
    //   135: astore #13
    //   137: aload #13
    //   139: ifnull -> 157
    //   142: aload_0
    //   143: aload_1
    //   144: aload_2
    //   145: aload_3
    //   146: aload #9
    //   148: aload #8
    //   150: aload #13
    //   152: aload #12
    //   154: invokevirtual _handleDuplicateField : (Lcom/fasterxml/jackson/core/JsonParser;Lcom/fasterxml/jackson/databind/DeserializationContext;Lcom/fasterxml/jackson/databind/node/JsonNodeFactory;Ljava/lang/String;Lcom/fasterxml/jackson/databind/node/ObjectNode;Lcom/fasterxml/jackson/databind/JsonNode;Lcom/fasterxml/jackson/databind/JsonNode;)V
    //   157: aload #4
    //   159: aload #6
    //   161: invokevirtual push : (Lcom/fasterxml/jackson/databind/node/ContainerNode;)V
    //   164: aload #12
    //   166: dup
    //   167: astore #8
    //   169: astore #6
    //   171: goto -> 344
    //   174: aload_3
    //   175: invokevirtual arrayNode : ()Lcom/fasterxml/jackson/databind/node/ArrayNode;
    //   178: astore #12
    //   180: aload #8
    //   182: aload #9
    //   184: aload #12
    //   186: invokevirtual replace : (Ljava/lang/String;Lcom/fasterxml/jackson/databind/JsonNode;)Lcom/fasterxml/jackson/databind/JsonNode;
    //   189: astore #13
    //   191: aload #13
    //   193: ifnull -> 211
    //   196: aload_0
    //   197: aload_1
    //   198: aload_2
    //   199: aload_3
    //   200: aload #9
    //   202: aload #8
    //   204: aload #13
    //   206: aload #12
    //   208: invokevirtual _handleDuplicateField : (Lcom/fasterxml/jackson/core/JsonParser;Lcom/fasterxml/jackson/databind/DeserializationContext;Lcom/fasterxml/jackson/databind/node/JsonNodeFactory;Ljava/lang/String;Lcom/fasterxml/jackson/databind/node/ObjectNode;Lcom/fasterxml/jackson/databind/JsonNode;Lcom/fasterxml/jackson/databind/JsonNode;)V
    //   211: aload #4
    //   213: aload #6
    //   215: invokevirtual push : (Lcom/fasterxml/jackson/databind/node/ContainerNode;)V
    //   218: aload #12
    //   220: astore #6
    //   222: goto -> 608
    //   225: aload_3
    //   226: aload_1
    //   227: invokevirtual getText : ()Ljava/lang/String;
    //   230: invokevirtual textNode : (Ljava/lang/String;)Lcom/fasterxml/jackson/databind/node/TextNode;
    //   233: astore #10
    //   235: goto -> 313
    //   238: aload_0
    //   239: aload_1
    //   240: iload #7
    //   242: aload_3
    //   243: invokevirtual _fromInt : (Lcom/fasterxml/jackson/core/JsonParser;ILcom/fasterxml/jackson/databind/node/JsonNodeFactory;)Lcom/fasterxml/jackson/databind/JsonNode;
    //   246: astore #10
    //   248: goto -> 313
    //   251: aload_0
    //   252: aload_1
    //   253: aload_2
    //   254: aload_3
    //   255: invokevirtual _fromFloat : (Lcom/fasterxml/jackson/core/JsonParser;Lcom/fasterxml/jackson/databind/DeserializationContext;Lcom/fasterxml/jackson/databind/node/JsonNodeFactory;)Lcom/fasterxml/jackson/databind/JsonNode;
    //   258: astore #10
    //   260: goto -> 313
    //   263: aload_3
    //   264: iconst_1
    //   265: invokevirtual booleanNode : (Z)Lcom/fasterxml/jackson/databind/node/BooleanNode;
    //   268: astore #10
    //   270: goto -> 313
    //   273: aload_3
    //   274: iconst_0
    //   275: invokevirtual booleanNode : (Z)Lcom/fasterxml/jackson/databind/node/BooleanNode;
    //   278: astore #10
    //   280: goto -> 313
    //   283: aload_2
    //   284: getstatic com/fasterxml/jackson/databind/cfg/JsonNodeFeature.READ_NULL_PROPERTIES : Lcom/fasterxml/jackson/databind/cfg/JsonNodeFeature;
    //   287: invokevirtual isEnabled : (Lcom/fasterxml/jackson/databind/cfg/DatatypeFeature;)Z
    //   290: ifne -> 296
    //   293: goto -> 344
    //   296: aload_3
    //   297: invokevirtual nullNode : ()Lcom/fasterxml/jackson/databind/node/NullNode;
    //   300: astore #10
    //   302: goto -> 313
    //   305: aload_0
    //   306: aload_1
    //   307: aload_2
    //   308: invokevirtual _deserializeRareScalar : (Lcom/fasterxml/jackson/core/JsonParser;Lcom/fasterxml/jackson/databind/DeserializationContext;)Lcom/fasterxml/jackson/databind/JsonNode;
    //   311: astore #10
    //   313: aload #8
    //   315: aload #9
    //   317: aload #10
    //   319: invokevirtual replace : (Ljava/lang/String;Lcom/fasterxml/jackson/databind/JsonNode;)Lcom/fasterxml/jackson/databind/JsonNode;
    //   322: astore #12
    //   324: aload #12
    //   326: ifnull -> 344
    //   329: aload_0
    //   330: aload_1
    //   331: aload_2
    //   332: aload_3
    //   333: aload #9
    //   335: aload #8
    //   337: aload #12
    //   339: aload #10
    //   341: invokevirtual _handleDuplicateField : (Lcom/fasterxml/jackson/core/JsonParser;Lcom/fasterxml/jackson/databind/DeserializationContext;Lcom/fasterxml/jackson/databind/node/JsonNodeFactory;Ljava/lang/String;Lcom/fasterxml/jackson/databind/node/ObjectNode;Lcom/fasterxml/jackson/databind/JsonNode;Lcom/fasterxml/jackson/databind/JsonNode;)V
    //   344: aload_1
    //   345: invokevirtual nextFieldName : ()Ljava/lang/String;
    //   348: astore #9
    //   350: goto -> 35
    //   353: goto -> 601
    //   356: aload #6
    //   358: checkcast com/fasterxml/jackson/databind/node/ArrayNode
    //   361: astore #8
    //   363: aload_1
    //   364: invokevirtual nextToken : ()Lcom/fasterxml/jackson/core/JsonToken;
    //   367: astore #9
    //   369: aload #9
    //   371: ifnonnull -> 379
    //   374: getstatic com/fasterxml/jackson/core/JsonToken.NOT_AVAILABLE : Lcom/fasterxml/jackson/core/JsonToken;
    //   377: astore #9
    //   379: aload #9
    //   381: invokevirtual id : ()I
    //   384: tableswitch default -> 586, 1 -> 444, 2 -> 586, 3 -> 468, 4 -> 492, 5 -> 586, 6 -> 495, 7 -> 512, 8 -> 529, 9 -> 545, 10 -> 559, 11 -> 573
    //   444: aload #4
    //   446: aload #6
    //   448: invokevirtual push : (Lcom/fasterxml/jackson/databind/node/ContainerNode;)V
    //   451: aload_3
    //   452: invokevirtual objectNode : ()Lcom/fasterxml/jackson/databind/node/ObjectNode;
    //   455: astore #6
    //   457: aload #8
    //   459: aload #6
    //   461: invokevirtual add : (Lcom/fasterxml/jackson/databind/JsonNode;)Lcom/fasterxml/jackson/databind/node/ArrayNode;
    //   464: pop
    //   465: goto -> 608
    //   468: aload #4
    //   470: aload #6
    //   472: invokevirtual push : (Lcom/fasterxml/jackson/databind/node/ContainerNode;)V
    //   475: aload_3
    //   476: invokevirtual arrayNode : ()Lcom/fasterxml/jackson/databind/node/ArrayNode;
    //   479: astore #6
    //   481: aload #8
    //   483: aload #6
    //   485: invokevirtual add : (Lcom/fasterxml/jackson/databind/JsonNode;)Lcom/fasterxml/jackson/databind/node/ArrayNode;
    //   488: pop
    //   489: goto -> 608
    //   492: goto -> 601
    //   495: aload #8
    //   497: aload_3
    //   498: aload_1
    //   499: invokevirtual getText : ()Ljava/lang/String;
    //   502: invokevirtual textNode : (Ljava/lang/String;)Lcom/fasterxml/jackson/databind/node/TextNode;
    //   505: invokevirtual add : (Lcom/fasterxml/jackson/databind/JsonNode;)Lcom/fasterxml/jackson/databind/node/ArrayNode;
    //   508: pop
    //   509: goto -> 363
    //   512: aload #8
    //   514: aload_0
    //   515: aload_1
    //   516: iload #7
    //   518: aload_3
    //   519: invokevirtual _fromInt : (Lcom/fasterxml/jackson/core/JsonParser;ILcom/fasterxml/jackson/databind/node/JsonNodeFactory;)Lcom/fasterxml/jackson/databind/JsonNode;
    //   522: invokevirtual add : (Lcom/fasterxml/jackson/databind/JsonNode;)Lcom/fasterxml/jackson/databind/node/ArrayNode;
    //   525: pop
    //   526: goto -> 363
    //   529: aload #8
    //   531: aload_0
    //   532: aload_1
    //   533: aload_2
    //   534: aload_3
    //   535: invokevirtual _fromFloat : (Lcom/fasterxml/jackson/core/JsonParser;Lcom/fasterxml/jackson/databind/DeserializationContext;Lcom/fasterxml/jackson/databind/node/JsonNodeFactory;)Lcom/fasterxml/jackson/databind/JsonNode;
    //   538: invokevirtual add : (Lcom/fasterxml/jackson/databind/JsonNode;)Lcom/fasterxml/jackson/databind/node/ArrayNode;
    //   541: pop
    //   542: goto -> 363
    //   545: aload #8
    //   547: aload_3
    //   548: iconst_1
    //   549: invokevirtual booleanNode : (Z)Lcom/fasterxml/jackson/databind/node/BooleanNode;
    //   552: invokevirtual add : (Lcom/fasterxml/jackson/databind/JsonNode;)Lcom/fasterxml/jackson/databind/node/ArrayNode;
    //   555: pop
    //   556: goto -> 363
    //   559: aload #8
    //   561: aload_3
    //   562: iconst_0
    //   563: invokevirtual booleanNode : (Z)Lcom/fasterxml/jackson/databind/node/BooleanNode;
    //   566: invokevirtual add : (Lcom/fasterxml/jackson/databind/JsonNode;)Lcom/fasterxml/jackson/databind/node/ArrayNode;
    //   569: pop
    //   570: goto -> 363
    //   573: aload #8
    //   575: aload_3
    //   576: invokevirtual nullNode : ()Lcom/fasterxml/jackson/databind/node/NullNode;
    //   579: invokevirtual add : (Lcom/fasterxml/jackson/databind/JsonNode;)Lcom/fasterxml/jackson/databind/node/ArrayNode;
    //   582: pop
    //   583: goto -> 363
    //   586: aload #8
    //   588: aload_0
    //   589: aload_1
    //   590: aload_2
    //   591: invokevirtual _deserializeRareScalar : (Lcom/fasterxml/jackson/core/JsonParser;Lcom/fasterxml/jackson/databind/DeserializationContext;)Lcom/fasterxml/jackson/databind/JsonNode;
    //   594: invokevirtual add : (Lcom/fasterxml/jackson/databind/JsonNode;)Lcom/fasterxml/jackson/databind/node/ArrayNode;
    //   597: pop
    //   598: goto -> 363
    //   601: aload #4
    //   603: invokevirtual popOrNull : ()Lcom/fasterxml/jackson/databind/node/ContainerNode;
    //   606: astore #6
    //   608: aload #6
    //   610: ifnonnull -> 14
    //   613: aload #5
    //   615: areturn
    // Line number table:
    //   Java source line number -> byte code offset
    //   #529	-> 0
    //   #530	-> 4
    //   #534	-> 14
    //   #535	-> 22
    //   #536	-> 29
    //   #539	-> 35
    //   #541	-> 40
    //   #542	-> 46
    //   #543	-> 51
    //   #545	-> 56
    //   #548	-> 120
    //   #549	-> 126
    //   #550	-> 137
    //   #551	-> 142
    //   #554	-> 157
    //   #555	-> 164
    //   #557	-> 171
    //   #561	-> 174
    //   #562	-> 180
    //   #563	-> 191
    //   #564	-> 196
    //   #567	-> 211
    //   #568	-> 218
    //   #570	-> 222
    //   #572	-> 225
    //   #573	-> 235
    //   #575	-> 238
    //   #576	-> 248
    //   #578	-> 251
    //   #579	-> 260
    //   #581	-> 263
    //   #582	-> 270
    //   #584	-> 273
    //   #585	-> 280
    //   #588	-> 283
    //   #589	-> 293
    //   #591	-> 296
    //   #592	-> 302
    //   #594	-> 305
    //   #596	-> 313
    //   #597	-> 324
    //   #598	-> 329
    //   #539	-> 344
    //   #603	-> 353
    //   #605	-> 356
    //   #609	-> 363
    //   #610	-> 369
    //   #611	-> 374
    //   #613	-> 379
    //   #615	-> 444
    //   #616	-> 451
    //   #617	-> 457
    //   #618	-> 465
    //   #620	-> 468
    //   #621	-> 475
    //   #622	-> 481
    //   #623	-> 489
    //   #625	-> 492
    //   #627	-> 495
    //   #628	-> 509
    //   #630	-> 512
    //   #631	-> 526
    //   #633	-> 529
    //   #634	-> 542
    //   #636	-> 545
    //   #637	-> 556
    //   #639	-> 559
    //   #640	-> 570
    //   #642	-> 573
    //   #643	-> 583
    //   #645	-> 586
    //   #646	-> 598
    //   #653	-> 601
    //   #654	-> 608
    //   #655	-> 613
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   126	48	12	newOb	Lcom/fasterxml/jackson/databind/node/ObjectNode;
    //   137	37	13	old	Lcom/fasterxml/jackson/databind/JsonNode;
    //   180	42	12	newOb	Lcom/fasterxml/jackson/databind/node/ArrayNode;
    //   191	31	13	old	Lcom/fasterxml/jackson/databind/JsonNode;
    //   235	3	10	value	Lcom/fasterxml/jackson/databind/JsonNode;
    //   248	3	10	value	Lcom/fasterxml/jackson/databind/JsonNode;
    //   260	3	10	value	Lcom/fasterxml/jackson/databind/JsonNode;
    //   270	3	10	value	Lcom/fasterxml/jackson/databind/JsonNode;
    //   280	3	10	value	Lcom/fasterxml/jackson/databind/JsonNode;
    //   302	3	10	value	Lcom/fasterxml/jackson/databind/JsonNode;
    //   313	31	10	value	Lcom/fasterxml/jackson/databind/JsonNode;
    //   46	298	11	t	Lcom/fasterxml/jackson/core/JsonToken;
    //   324	20	12	old	Lcom/fasterxml/jackson/databind/JsonNode;
    //   29	324	8	currObject	Lcom/fasterxml/jackson/databind/node/ObjectNode;
    //   35	318	9	propName	Ljava/lang/String;
    //   369	232	9	t	Lcom/fasterxml/jackson/core/JsonToken;
    //   363	238	8	currArray	Lcom/fasterxml/jackson/databind/node/ArrayNode;
    //   0	616	0	this	Lcom/fasterxml/jackson/databind/deser/std/BaseNodeDeserializer;
    //   0	616	1	p	Lcom/fasterxml/jackson/core/JsonParser;
    //   0	616	2	ctxt	Lcom/fasterxml/jackson/databind/DeserializationContext;
    //   0	616	3	nodeFactory	Lcom/fasterxml/jackson/databind/node/JsonNodeFactory;
    //   0	616	4	stack	Lcom/fasterxml/jackson/databind/deser/std/BaseNodeDeserializer$ContainerStack;
    //   0	616	5	root	Lcom/fasterxml/jackson/databind/node/ContainerNode;
    //   4	612	6	curr	Lcom/fasterxml/jackson/databind/node/ContainerNode;
    //   14	602	7	intCoercionFeats	I
    // Local variable type table:
    //   start	length	slot	name	signature
    //   0	616	0	this	Lcom/fasterxml/jackson/databind/deser/std/BaseNodeDeserializer<TT;>;
    //   0	616	5	root	Lcom/fasterxml/jackson/databind/node/ContainerNode<*>;
    //   4	612	6	curr	Lcom/fasterxml/jackson/databind/node/ContainerNode<*>;
  }
  
  protected final JsonNode _deserializeAnyScalar(JsonParser p, DeserializationContext ctxt) throws IOException {
    JsonNodeFactory nodeF = ctxt.getNodeFactory();
    switch (p.currentTokenId()) {
      case 2:
        return (JsonNode)nodeF.objectNode();
      case 6:
        return (JsonNode)nodeF.textNode(p.getText());
      case 7:
        return _fromInt(p, ctxt, nodeF);
      case 8:
        return _fromFloat(p, ctxt, nodeF);
      case 9:
        return (JsonNode)nodeF.booleanNode(true);
      case 10:
        return (JsonNode)nodeF.booleanNode(false);
      case 11:
        return (JsonNode)nodeF.nullNode();
      case 12:
        return _fromEmbedded(p, ctxt);
    } 
    return (JsonNode)ctxt.handleUnexpectedToken(handledType(), p);
  }
  
  protected final JsonNode _deserializeRareScalar(JsonParser p, DeserializationContext ctxt) throws IOException {
    switch (p.currentTokenId()) {
      case 2:
        return (JsonNode)ctxt.getNodeFactory().objectNode();
      case 8:
        return _fromFloat(p, ctxt, ctxt.getNodeFactory());
      case 12:
        return _fromEmbedded(p, ctxt);
    } 
    return (JsonNode)ctxt.handleUnexpectedToken(handledType(), p);
  }
  
  protected final JsonNode _fromInt(JsonParser p, int coercionFeatures, JsonNodeFactory nodeFactory) throws IOException {
    if (coercionFeatures != 0) {
      if (DeserializationFeature.USE_BIG_INTEGER_FOR_INTS.enabledIn(coercionFeatures))
        return (JsonNode)nodeFactory.numberNode(p.getBigIntegerValue()); 
      return (JsonNode)nodeFactory.numberNode(p.getLongValue());
    } 
    JsonParser.NumberType nt = p.getNumberType();
    if (nt == JsonParser.NumberType.INT)
      return (JsonNode)nodeFactory.numberNode(p.getIntValue()); 
    if (nt == JsonParser.NumberType.LONG)
      return (JsonNode)nodeFactory.numberNode(p.getLongValue()); 
    return (JsonNode)nodeFactory.numberNode(p.getBigIntegerValue());
  }
  
  protected final JsonNode _fromInt(JsonParser p, DeserializationContext ctxt, JsonNodeFactory nodeFactory) throws IOException {
    JsonParser.NumberType nt;
    int feats = ctxt.getDeserializationFeatures();
    if ((feats & F_MASK_INT_COERCIONS) != 0) {
      if (DeserializationFeature.USE_BIG_INTEGER_FOR_INTS.enabledIn(feats)) {
        nt = JsonParser.NumberType.BIG_INTEGER;
      } else if (DeserializationFeature.USE_LONG_FOR_INTS.enabledIn(feats)) {
        nt = JsonParser.NumberType.LONG;
      } else {
        nt = p.getNumberType();
      } 
    } else {
      nt = p.getNumberType();
    } 
    if (nt == JsonParser.NumberType.INT)
      return (JsonNode)nodeFactory.numberNode(p.getIntValue()); 
    if (nt == JsonParser.NumberType.LONG)
      return (JsonNode)nodeFactory.numberNode(p.getLongValue()); 
    return (JsonNode)nodeFactory.numberNode(p.getBigIntegerValue());
  }
  
  protected final JsonNode _fromFloat(JsonParser p, DeserializationContext ctxt, JsonNodeFactory nodeFactory) throws IOException {
    JsonParser.NumberType nt = p.getNumberType();
    if (nt == JsonParser.NumberType.BIG_DECIMAL)
      return _fromBigDecimal(ctxt, nodeFactory, p.getDecimalValue()); 
    if (ctxt.isEnabled(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS))
      try {
        return _fromBigDecimal(ctxt, nodeFactory, p.getDecimalValue());
      } catch (NumberFormatException numberFormatException) {} 
    if (nt == JsonParser.NumberType.FLOAT)
      return (JsonNode)nodeFactory.numberNode(p.getFloatValue()); 
    return (JsonNode)nodeFactory.numberNode(p.getDoubleValue());
  }
  
  protected final JsonNode _fromBigDecimal(DeserializationContext ctxt, JsonNodeFactory nodeFactory, BigDecimal bigDec) {
    boolean normalize;
    DatatypeFeatures dtf = ctxt.getDatatypeFeatures();
    if (dtf.isExplicitlySet((DatatypeFeature)JsonNodeFeature.STRIP_TRAILING_BIGDECIMAL_ZEROES)) {
      normalize = dtf.isEnabled((DatatypeFeature)JsonNodeFeature.STRIP_TRAILING_BIGDECIMAL_ZEROES);
    } else {
      normalize = nodeFactory.willStripTrailingBigDecimalZeroes();
    } 
    if (normalize)
      try {
        bigDec = bigDec.stripTrailingZeros();
      } catch (ArithmeticException arithmeticException) {} 
    return (JsonNode)nodeFactory.numberNode(bigDec);
  }
  
  protected final JsonNode _fromEmbedded(JsonParser p, DeserializationContext ctxt) throws IOException {
    JsonNodeFactory nodeF = ctxt.getNodeFactory();
    Object ob = p.getEmbeddedObject();
    if (ob == null)
      return (JsonNode)nodeF.nullNode(); 
    Class<?> type = ob.getClass();
    if (type == byte[].class)
      return (JsonNode)nodeF.binaryNode((byte[])ob); 
    if (ob instanceof RawValue)
      return (JsonNode)nodeF.rawValueNode((RawValue)ob); 
    if (ob instanceof JsonNode)
      return (JsonNode)ob; 
    return (JsonNode)nodeF.pojoNode(ob);
  }
  
  static final class ContainerStack {
    private ContainerNode[] _stack;
    
    private int _top;
    
    private int _end;
    
    public int size() {
      return this._top;
    }
    
    public void push(ContainerNode node) {
      if (this._top < this._end) {
        this._stack[this._top++] = node;
        return;
      } 
      if (this._stack == null) {
        this._end = 10;
        this._stack = new ContainerNode[this._end];
      } else {
        this._end += Math.min(4000, Math.max(20, this._end >> 1));
        this._stack = Arrays.<ContainerNode>copyOf(this._stack, this._end);
      } 
      this._stack[this._top++] = node;
    }
    
    public ContainerNode popOrNull() {
      if (this._top == 0)
        return null; 
      return this._stack[--this._top];
    }
  }
}
