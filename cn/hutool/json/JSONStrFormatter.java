package cn.hutool.json;

import cn.hutool.core.util.StrUtil;

public class JSONStrFormatter {
  private static final String SPACE = "    ";
  
  private static final char NEW_LINE = '\n';
  
  public static String format(String json) {
    // Byte code:
    //   0: new java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial <init> : ()V
    //   7: astore_1
    //   8: aconst_null
    //   9: astore_2
    //   10: iconst_0
    //   11: istore_3
    //   12: aload_0
    //   13: invokevirtual length : ()I
    //   16: istore #4
    //   18: iconst_0
    //   19: istore #5
    //   21: iconst_0
    //   22: istore #7
    //   24: iload #7
    //   26: iload #4
    //   28: if_icmpge -> 374
    //   31: aload_0
    //   32: iload #7
    //   34: invokevirtual charAt : (I)C
    //   37: istore #6
    //   39: bipush #34
    //   41: iload #6
    //   43: if_icmpeq -> 53
    //   46: bipush #39
    //   48: iload #6
    //   50: if_icmpne -> 123
    //   53: aconst_null
    //   54: aload_2
    //   55: if_acmpne -> 67
    //   58: iload #6
    //   60: invokestatic valueOf : (C)Ljava/lang/Character;
    //   63: astore_2
    //   64: goto -> 87
    //   67: aload_2
    //   68: iload #6
    //   70: invokestatic valueOf : (C)Ljava/lang/Character;
    //   73: invokevirtual equals : (Ljava/lang/Object;)Z
    //   76: ifeq -> 87
    //   79: iload_3
    //   80: ifeq -> 85
    //   83: iconst_0
    //   84: istore_3
    //   85: aconst_null
    //   86: astore_2
    //   87: iload #7
    //   89: iconst_1
    //   90: if_icmple -> 113
    //   93: aload_0
    //   94: iload #7
    //   96: iconst_1
    //   97: isub
    //   98: invokevirtual charAt : (I)C
    //   101: bipush #58
    //   103: if_icmpne -> 113
    //   106: aload_1
    //   107: bipush #32
    //   109: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   112: pop
    //   113: aload_1
    //   114: iload #6
    //   116: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   119: pop
    //   120: goto -> 368
    //   123: bipush #92
    //   125: iload #6
    //   127: if_icmpne -> 162
    //   130: aconst_null
    //   131: aload_2
    //   132: if_acmpeq -> 155
    //   135: iload_3
    //   136: ifne -> 143
    //   139: iconst_1
    //   140: goto -> 144
    //   143: iconst_0
    //   144: istore_3
    //   145: aload_1
    //   146: iload #6
    //   148: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   151: pop
    //   152: goto -> 368
    //   155: aload_1
    //   156: iload #6
    //   158: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   161: pop
    //   162: aconst_null
    //   163: aload_2
    //   164: if_acmpeq -> 177
    //   167: aload_1
    //   168: iload #6
    //   170: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   173: pop
    //   174: goto -> 368
    //   177: iload #6
    //   179: bipush #91
    //   181: if_icmpeq -> 191
    //   184: iload #6
    //   186: bipush #123
    //   188: if_icmpne -> 257
    //   191: iload #7
    //   193: iconst_1
    //   194: if_icmple -> 227
    //   197: aload_0
    //   198: iload #7
    //   200: iconst_1
    //   201: isub
    //   202: invokevirtual charAt : (I)C
    //   205: bipush #58
    //   207: if_icmpne -> 227
    //   210: aload_1
    //   211: bipush #10
    //   213: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   216: pop
    //   217: aload_1
    //   218: iload #5
    //   220: invokestatic indent : (I)Ljava/lang/String;
    //   223: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   226: pop
    //   227: aload_1
    //   228: iload #6
    //   230: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   233: pop
    //   234: aload_1
    //   235: bipush #10
    //   237: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   240: pop
    //   241: iinc #5, 1
    //   244: aload_1
    //   245: iload #5
    //   247: invokestatic indent : (I)Ljava/lang/String;
    //   250: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   253: pop
    //   254: goto -> 368
    //   257: iload #6
    //   259: bipush #93
    //   261: if_icmpeq -> 271
    //   264: iload #6
    //   266: bipush #125
    //   268: if_icmpne -> 301
    //   271: aload_1
    //   272: bipush #10
    //   274: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   277: pop
    //   278: iinc #5, -1
    //   281: aload_1
    //   282: iload #5
    //   284: invokestatic indent : (I)Ljava/lang/String;
    //   287: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   290: pop
    //   291: aload_1
    //   292: iload #6
    //   294: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   297: pop
    //   298: goto -> 368
    //   301: iload #6
    //   303: bipush #44
    //   305: if_icmpne -> 335
    //   308: aload_1
    //   309: iload #6
    //   311: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   314: pop
    //   315: aload_1
    //   316: bipush #10
    //   318: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   321: pop
    //   322: aload_1
    //   323: iload #5
    //   325: invokestatic indent : (I)Ljava/lang/String;
    //   328: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   331: pop
    //   332: goto -> 368
    //   335: iload #7
    //   337: iconst_1
    //   338: if_icmple -> 361
    //   341: aload_0
    //   342: iload #7
    //   344: iconst_1
    //   345: isub
    //   346: invokevirtual charAt : (I)C
    //   349: bipush #58
    //   351: if_icmpne -> 361
    //   354: aload_1
    //   355: bipush #32
    //   357: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   360: pop
    //   361: aload_1
    //   362: iload #6
    //   364: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   367: pop
    //   368: iinc #7, 1
    //   371: goto -> 24
    //   374: aload_1
    //   375: invokevirtual toString : ()Ljava/lang/String;
    //   378: areturn
    // Line number table:
    //   Java source line number -> byte code offset
    //   #31	-> 0
    //   #33	-> 8
    //   #34	-> 10
    //   #35	-> 12
    //   #36	-> 18
    //   #38	-> 21
    //   #39	-> 31
    //   #41	-> 39
    //   #42	-> 53
    //   #44	-> 58
    //   #45	-> 67
    //   #46	-> 79
    //   #48	-> 83
    //   #52	-> 85
    //   #55	-> 87
    //   #56	-> 106
    //   #59	-> 113
    //   #60	-> 120
    //   #63	-> 123
    //   #64	-> 130
    //   #66	-> 135
    //   #67	-> 145
    //   #68	-> 152
    //   #70	-> 155
    //   #74	-> 162
    //   #76	-> 167
    //   #77	-> 174
    //   #81	-> 177
    //   #83	-> 191
    //   #84	-> 210
    //   #85	-> 217
    //   #87	-> 227
    //   #89	-> 234
    //   #91	-> 241
    //   #92	-> 244
    //   #94	-> 254
    //   #98	-> 257
    //   #100	-> 271
    //   #102	-> 278
    //   #103	-> 281
    //   #105	-> 291
    //   #111	-> 298
    //   #115	-> 301
    //   #116	-> 308
    //   #117	-> 315
    //   #118	-> 322
    //   #119	-> 332
    //   #122	-> 335
    //   #123	-> 354
    //   #127	-> 361
    //   #38	-> 368
    //   #130	-> 374
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   39	335	6	key	C
    //   24	350	7	i	I
    //   0	379	0	json	Ljava/lang/String;
    //   8	371	1	result	Ljava/lang/StringBuilder;
    //   10	369	2	wrapChar	Ljava/lang/Character;
    //   12	367	3	isEscapeMode	Z
    //   18	361	4	length	I
    //   21	358	5	number	I
  }
  
  private static String indent(int number) {
    return StrUtil.repeat("    ", number);
  }
}
