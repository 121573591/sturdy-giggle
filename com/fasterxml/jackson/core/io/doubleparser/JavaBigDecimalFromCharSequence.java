package com.fasterxml.jackson.core.io.doubleparser;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.NavigableMap;

final class JavaBigDecimalFromCharSequence extends AbstractBigDecimalParser {
  public BigDecimal parseBigDecimalString(CharSequence str, int offset, int length) {
    // Byte code:
    //   0: aload_1
    //   1: invokeinterface length : ()I
    //   6: istore #4
    //   8: iload #4
    //   10: iload_2
    //   11: iload_3
    //   12: invokestatic checkBounds : (III)I
    //   15: istore #5
    //   17: iload_3
    //   18: invokestatic hasManyDigits : (I)Z
    //   21: ifeq -> 32
    //   24: aload_0
    //   25: aload_1
    //   26: iload_2
    //   27: iload_3
    //   28: invokevirtual parseBigDecimalStringWithManyDigits : (Ljava/lang/CharSequence;II)Ljava/math/BigDecimal;
    //   31: areturn
    //   32: lconst_0
    //   33: lstore #6
    //   35: iconst_m1
    //   36: istore #9
    //   38: iload_2
    //   39: istore #11
    //   41: aload_1
    //   42: iload #11
    //   44: iload #5
    //   46: invokestatic charAt : (Ljava/lang/CharSequence;II)C
    //   49: istore #12
    //   51: iconst_0
    //   52: istore #13
    //   54: iload #12
    //   56: bipush #45
    //   58: if_icmpne -> 65
    //   61: iconst_1
    //   62: goto -> 66
    //   65: iconst_0
    //   66: istore #14
    //   68: iload #14
    //   70: ifne -> 80
    //   73: iload #12
    //   75: bipush #43
    //   77: if_icmpne -> 108
    //   80: aload_1
    //   81: iinc #11, 1
    //   84: iload #11
    //   86: iload #5
    //   88: invokestatic charAt : (Ljava/lang/CharSequence;II)C
    //   91: istore #12
    //   93: iload #12
    //   95: ifne -> 108
    //   98: new java/lang/NumberFormatException
    //   101: dup
    //   102: ldc 'illegal syntax'
    //   104: invokespecial <init> : (Ljava/lang/String;)V
    //   107: athrow
    //   108: iload #11
    //   110: istore #8
    //   112: iload #11
    //   114: iload #5
    //   116: if_icmpge -> 233
    //   119: aload_1
    //   120: iload #11
    //   122: invokeinterface charAt : (I)C
    //   127: istore #12
    //   129: iload #12
    //   131: invokestatic isDigit : (C)Z
    //   134: ifeq -> 156
    //   137: ldc2_w 10
    //   140: lload #6
    //   142: lmul
    //   143: iload #12
    //   145: i2l
    //   146: ladd
    //   147: ldc2_w 48
    //   150: lsub
    //   151: lstore #6
    //   153: goto -> 227
    //   156: iload #12
    //   158: bipush #46
    //   160: if_icmpne -> 233
    //   163: iload #13
    //   165: iload #9
    //   167: iflt -> 174
    //   170: iconst_1
    //   171: goto -> 175
    //   174: iconst_0
    //   175: ior
    //   176: istore #13
    //   178: iload #11
    //   180: istore #9
    //   182: iload #11
    //   184: iload #5
    //   186: iconst_4
    //   187: isub
    //   188: if_icmpge -> 227
    //   191: aload_1
    //   192: iload #11
    //   194: iconst_1
    //   195: iadd
    //   196: invokestatic tryToParseFourDigits : (Ljava/lang/CharSequence;I)I
    //   199: istore #15
    //   201: iload #15
    //   203: ifge -> 209
    //   206: goto -> 227
    //   209: ldc2_w 10000
    //   212: lload #6
    //   214: lmul
    //   215: iload #15
    //   217: i2l
    //   218: ladd
    //   219: lstore #6
    //   221: iinc #11, 4
    //   224: goto -> 182
    //   227: iinc #11, 1
    //   230: goto -> 112
    //   233: iload #11
    //   235: istore #16
    //   237: iload #9
    //   239: ifge -> 259
    //   242: iload #16
    //   244: iload #8
    //   246: isub
    //   247: istore #15
    //   249: iload #16
    //   251: istore #9
    //   253: lconst_0
    //   254: lstore #17
    //   256: goto -> 278
    //   259: iload #16
    //   261: iload #8
    //   263: isub
    //   264: iconst_1
    //   265: isub
    //   266: istore #15
    //   268: iload #9
    //   270: iload #16
    //   272: isub
    //   273: iconst_1
    //   274: iadd
    //   275: i2l
    //   276: lstore #17
    //   278: lconst_0
    //   279: lstore #19
    //   281: iload #12
    //   283: bipush #32
    //   285: ior
    //   286: bipush #101
    //   288: if_icmpne -> 431
    //   291: iload #11
    //   293: istore #10
    //   295: aload_1
    //   296: iinc #11, 1
    //   299: iload #11
    //   301: iload #5
    //   303: invokestatic charAt : (Ljava/lang/CharSequence;II)C
    //   306: istore #12
    //   308: iload #12
    //   310: bipush #45
    //   312: if_icmpne -> 319
    //   315: iconst_1
    //   316: goto -> 320
    //   319: iconst_0
    //   320: istore #21
    //   322: iload #21
    //   324: ifne -> 334
    //   327: iload #12
    //   329: bipush #43
    //   331: if_icmpne -> 347
    //   334: aload_1
    //   335: iinc #11, 1
    //   338: iload #11
    //   340: iload #5
    //   342: invokestatic charAt : (Ljava/lang/CharSequence;II)C
    //   345: istore #12
    //   347: iload #13
    //   349: iload #12
    //   351: invokestatic isDigit : (C)Z
    //   354: ifne -> 361
    //   357: iconst_1
    //   358: goto -> 362
    //   361: iconst_0
    //   362: ior
    //   363: istore #13
    //   365: lload #19
    //   367: ldc2_w 2147483647
    //   370: lcmp
    //   371: ifge -> 390
    //   374: ldc2_w 10
    //   377: lload #19
    //   379: lmul
    //   380: iload #12
    //   382: i2l
    //   383: ladd
    //   384: ldc2_w 48
    //   387: lsub
    //   388: lstore #19
    //   390: aload_1
    //   391: iinc #11, 1
    //   394: iload #11
    //   396: iload #5
    //   398: invokestatic charAt : (Ljava/lang/CharSequence;II)C
    //   401: istore #12
    //   403: iload #12
    //   405: invokestatic isDigit : (C)Z
    //   408: ifne -> 365
    //   411: iload #21
    //   413: ifeq -> 421
    //   416: lload #19
    //   418: lneg
    //   419: lstore #19
    //   421: lload #17
    //   423: lload #19
    //   425: ladd
    //   426: lstore #17
    //   428: goto -> 435
    //   431: iload #5
    //   433: istore #10
    //   435: iload #13
    //   437: iload #15
    //   439: ifne -> 446
    //   442: iconst_1
    //   443: goto -> 447
    //   446: iconst_0
    //   447: ior
    //   448: istore #13
    //   450: iload #13
    //   452: iload #11
    //   454: iload #5
    //   456: iload #15
    //   458: lload #17
    //   460: invokestatic checkParsedBigDecimalBounds : (ZIIIJ)V
    //   463: iload #15
    //   465: bipush #19
    //   467: if_icmpge -> 497
    //   470: new java/math/BigDecimal
    //   473: dup
    //   474: iload #14
    //   476: ifeq -> 485
    //   479: lload #6
    //   481: lneg
    //   482: goto -> 487
    //   485: lload #6
    //   487: invokespecial <init> : (J)V
    //   490: lload #17
    //   492: l2i
    //   493: invokevirtual scaleByPowerOfTen : (I)Ljava/math/BigDecimal;
    //   496: areturn
    //   497: aload_0
    //   498: aload_1
    //   499: iload #8
    //   501: iload #9
    //   503: iload #9
    //   505: iconst_1
    //   506: iadd
    //   507: iload #10
    //   509: iload #14
    //   511: lload #17
    //   513: l2i
    //   514: invokevirtual valueOfBigDecimalString : (Ljava/lang/CharSequence;IIIIZI)Ljava/math/BigDecimal;
    //   517: areturn
    //   518: astore #4
    //   520: new java/lang/NumberFormatException
    //   523: dup
    //   524: ldc 'value exceeds limits'
    //   526: invokespecial <init> : (Ljava/lang/String;)V
    //   529: astore #5
    //   531: aload #5
    //   533: aload #4
    //   535: invokevirtual initCause : (Ljava/lang/Throwable;)Ljava/lang/Throwable;
    //   538: pop
    //   539: aload #5
    //   541: athrow
    // Line number table:
    //   Java source line number -> byte code offset
    //   #39	-> 0
    //   #40	-> 8
    //   #41	-> 17
    //   #42	-> 24
    //   #44	-> 32
    //   #46	-> 35
    //   #49	-> 38
    //   #50	-> 41
    //   #51	-> 51
    //   #56	-> 54
    //   #57	-> 68
    //   #58	-> 80
    //   #59	-> 93
    //   #60	-> 98
    //   #65	-> 108
    //   #66	-> 112
    //   #67	-> 119
    //   #68	-> 129
    //   #70	-> 137
    //   #71	-> 156
    //   #72	-> 163
    //   #73	-> 178
    //   #74	-> 182
    //   #75	-> 191
    //   #76	-> 201
    //   #77	-> 206
    //   #80	-> 209
    //   #74	-> 221
    //   #66	-> 227
    //   #88	-> 233
    //   #90	-> 237
    //   #91	-> 242
    //   #92	-> 249
    //   #93	-> 253
    //   #95	-> 259
    //   #96	-> 268
    //   #101	-> 278
    //   #102	-> 281
    //   #103	-> 291
    //   #104	-> 295
    //   #105	-> 308
    //   #106	-> 322
    //   #107	-> 334
    //   #109	-> 347
    //   #112	-> 365
    //   #113	-> 374
    //   #115	-> 390
    //   #116	-> 403
    //   #117	-> 411
    //   #118	-> 416
    //   #120	-> 421
    //   #121	-> 428
    //   #122	-> 431
    //   #124	-> 435
    //   #125	-> 450
    //   #126	-> 463
    //   #127	-> 470
    //   #129	-> 497
    //   #130	-> 518
    //   #131	-> 520
    //   #132	-> 531
    //   #133	-> 539
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   201	20	15	digits	I
    //   249	10	15	digitCount	I
    //   256	3	17	exponent	J
    //   322	106	21	isExponentNegative	Z
    //   295	136	10	exponentIndicatorIndex	I
    //   8	510	4	size	I
    //   17	501	5	endIndex	I
    //   35	483	6	significand	J
    //   112	406	8	integerPartIndex	I
    //   38	480	9	decimalPointIndex	I
    //   435	83	10	exponentIndicatorIndex	I
    //   41	477	11	index	I
    //   51	467	12	ch	C
    //   54	464	13	illegal	Z
    //   68	450	14	isNegative	Z
    //   268	250	15	digitCount	I
    //   237	281	16	significandEndIndex	I
    //   278	240	17	exponent	J
    //   281	237	19	expNumber	J
    //   531	11	5	nfe	Ljava/lang/NumberFormatException;
    //   520	22	4	e	Ljava/lang/ArithmeticException;
    //   0	542	0	this	Lcom/fasterxml/jackson/core/io/doubleparser/JavaBigDecimalFromCharSequence;
    //   0	542	1	str	Ljava/lang/CharSequence;
    //   0	542	2	offset	I
    //   0	542	3	length	I
    // Exception table:
    //   from	to	target	type
    //   0	31	518	java/lang/ArithmeticException
    //   32	496	518	java/lang/ArithmeticException
    //   497	517	518	java/lang/ArithmeticException
  }
  
  BigDecimal parseBigDecimalStringWithManyDigits(CharSequence str, int offset, int length) {
    // Byte code:
    //   0: iconst_m1
    //   1: istore #6
    //   3: iconst_m1
    //   4: istore #7
    //   6: iload_2
    //   7: iload_3
    //   8: iadd
    //   9: istore #9
    //   11: iload_2
    //   12: istore #10
    //   14: aload_1
    //   15: iload #10
    //   17: iload #9
    //   19: invokestatic charAt : (Ljava/lang/CharSequence;II)C
    //   22: istore #11
    //   24: iconst_0
    //   25: istore #12
    //   27: iload #11
    //   29: bipush #45
    //   31: if_icmpne -> 38
    //   34: iconst_1
    //   35: goto -> 39
    //   38: iconst_0
    //   39: istore #13
    //   41: iload #13
    //   43: ifne -> 53
    //   46: iload #11
    //   48: bipush #43
    //   50: if_icmpne -> 81
    //   53: aload_1
    //   54: iinc #10, 1
    //   57: iload #10
    //   59: iload #9
    //   61: invokestatic charAt : (Ljava/lang/CharSequence;II)C
    //   64: istore #11
    //   66: iload #11
    //   68: ifne -> 81
    //   71: new java/lang/NumberFormatException
    //   74: dup
    //   75: ldc 'illegal syntax'
    //   77: invokespecial <init> : (Ljava/lang/String;)V
    //   80: athrow
    //   81: iload #10
    //   83: istore #4
    //   85: iload #10
    //   87: iload #9
    //   89: bipush #8
    //   91: isub
    //   92: if_icmpge -> 110
    //   95: aload_1
    //   96: iload #10
    //   98: invokestatic isEightZeroes : (Ljava/lang/CharSequence;I)Z
    //   101: ifeq -> 110
    //   104: iinc #10, 8
    //   107: goto -> 85
    //   110: iload #10
    //   112: iload #9
    //   114: if_icmpge -> 136
    //   117: aload_1
    //   118: iload #10
    //   120: invokeinterface charAt : (I)C
    //   125: bipush #48
    //   127: if_icmpne -> 136
    //   130: iinc #10, 1
    //   133: goto -> 110
    //   136: iload #10
    //   138: istore #5
    //   140: iload #10
    //   142: iload #9
    //   144: bipush #8
    //   146: isub
    //   147: if_icmpge -> 165
    //   150: aload_1
    //   151: iload #10
    //   153: invokestatic isEightDigits : (Ljava/lang/CharSequence;I)Z
    //   156: ifeq -> 165
    //   159: iinc #10, 8
    //   162: goto -> 140
    //   165: iload #10
    //   167: iload #9
    //   169: if_icmpge -> 195
    //   172: aload_1
    //   173: iload #10
    //   175: invokeinterface charAt : (I)C
    //   180: dup
    //   181: istore #11
    //   183: invokestatic isDigit : (C)Z
    //   186: ifeq -> 195
    //   189: iinc #10, 1
    //   192: goto -> 165
    //   195: iload #11
    //   197: bipush #46
    //   199: if_icmpne -> 319
    //   202: iload #10
    //   204: iinc #10, 1
    //   207: istore #6
    //   209: iload #10
    //   211: iload #9
    //   213: bipush #8
    //   215: isub
    //   216: if_icmpge -> 234
    //   219: aload_1
    //   220: iload #10
    //   222: invokestatic isEightZeroes : (Ljava/lang/CharSequence;I)Z
    //   225: ifeq -> 234
    //   228: iinc #10, 8
    //   231: goto -> 209
    //   234: iload #10
    //   236: iload #9
    //   238: if_icmpge -> 260
    //   241: aload_1
    //   242: iload #10
    //   244: invokeinterface charAt : (I)C
    //   249: bipush #48
    //   251: if_icmpne -> 260
    //   254: iinc #10, 1
    //   257: goto -> 234
    //   260: iload #10
    //   262: istore #7
    //   264: iload #10
    //   266: iload #9
    //   268: bipush #8
    //   270: isub
    //   271: if_icmpge -> 289
    //   274: aload_1
    //   275: iload #10
    //   277: invokestatic isEightDigits : (Ljava/lang/CharSequence;I)Z
    //   280: ifeq -> 289
    //   283: iinc #10, 8
    //   286: goto -> 264
    //   289: iload #10
    //   291: iload #9
    //   293: if_icmpge -> 319
    //   296: aload_1
    //   297: iload #10
    //   299: invokeinterface charAt : (I)C
    //   304: dup
    //   305: istore #11
    //   307: invokestatic isDigit : (C)Z
    //   310: ifeq -> 319
    //   313: iinc #10, 1
    //   316: goto -> 289
    //   319: iload #10
    //   321: istore #15
    //   323: iload #6
    //   325: ifge -> 349
    //   328: iload #15
    //   330: iload #5
    //   332: isub
    //   333: istore #14
    //   335: iload #15
    //   337: istore #6
    //   339: iload #15
    //   341: istore #7
    //   343: lconst_0
    //   344: lstore #16
    //   346: goto -> 383
    //   349: iload #5
    //   351: iload #6
    //   353: if_icmpne -> 364
    //   356: iload #15
    //   358: iload #7
    //   360: isub
    //   361: goto -> 371
    //   364: iload #15
    //   366: iload #5
    //   368: isub
    //   369: iconst_1
    //   370: isub
    //   371: istore #14
    //   373: iload #6
    //   375: iload #15
    //   377: isub
    //   378: iconst_1
    //   379: iadd
    //   380: i2l
    //   381: lstore #16
    //   383: lconst_0
    //   384: lstore #18
    //   386: iload #11
    //   388: bipush #32
    //   390: ior
    //   391: bipush #101
    //   393: if_icmpne -> 533
    //   396: iload #10
    //   398: istore #8
    //   400: aload_1
    //   401: iinc #10, 1
    //   404: iload #10
    //   406: iload #9
    //   408: invokestatic charAt : (Ljava/lang/CharSequence;II)C
    //   411: istore #11
    //   413: iload #11
    //   415: bipush #45
    //   417: if_icmpne -> 424
    //   420: iconst_1
    //   421: goto -> 425
    //   424: iconst_0
    //   425: istore #20
    //   427: iload #20
    //   429: ifne -> 439
    //   432: iload #11
    //   434: bipush #43
    //   436: if_icmpne -> 452
    //   439: aload_1
    //   440: iinc #10, 1
    //   443: iload #10
    //   445: iload #9
    //   447: invokestatic charAt : (Ljava/lang/CharSequence;II)C
    //   450: istore #11
    //   452: iload #11
    //   454: invokestatic isDigit : (C)Z
    //   457: ifne -> 464
    //   460: iconst_1
    //   461: goto -> 465
    //   464: iconst_0
    //   465: istore #12
    //   467: lload #18
    //   469: ldc2_w 2147483647
    //   472: lcmp
    //   473: ifge -> 492
    //   476: ldc2_w 10
    //   479: lload #18
    //   481: lmul
    //   482: iload #11
    //   484: i2l
    //   485: ladd
    //   486: ldc2_w 48
    //   489: lsub
    //   490: lstore #18
    //   492: aload_1
    //   493: iinc #10, 1
    //   496: iload #10
    //   498: iload #9
    //   500: invokestatic charAt : (Ljava/lang/CharSequence;II)C
    //   503: istore #11
    //   505: iload #11
    //   507: invokestatic isDigit : (C)Z
    //   510: ifne -> 467
    //   513: iload #20
    //   515: ifeq -> 523
    //   518: lload #18
    //   520: lneg
    //   521: lstore #18
    //   523: lload #16
    //   525: lload #18
    //   527: ladd
    //   528: lstore #16
    //   530: goto -> 537
    //   533: iload #9
    //   535: istore #8
    //   537: iload #12
    //   539: iload #4
    //   541: iload #6
    //   543: if_icmpne -> 557
    //   546: iload #6
    //   548: iload #8
    //   550: if_icmpne -> 557
    //   553: iconst_1
    //   554: goto -> 558
    //   557: iconst_0
    //   558: ior
    //   559: istore #12
    //   561: iload #12
    //   563: iload #10
    //   565: iload #9
    //   567: iload #14
    //   569: lload #16
    //   571: invokestatic checkParsedBigDecimalBounds : (ZIIIJ)V
    //   574: aload_0
    //   575: aload_1
    //   576: iload #5
    //   578: iload #6
    //   580: iload #7
    //   582: iload #8
    //   584: iload #13
    //   586: lload #16
    //   588: l2i
    //   589: invokevirtual valueOfBigDecimalString : (Ljava/lang/CharSequence;IIIIZI)Ljava/math/BigDecimal;
    //   592: areturn
    // Line number table:
    //   Java source line number -> byte code offset
    //   #144	-> 0
    //   #145	-> 3
    //   #148	-> 6
    //   #149	-> 11
    //   #150	-> 14
    //   #151	-> 24
    //   #155	-> 27
    //   #156	-> 41
    //   #157	-> 53
    //   #158	-> 66
    //   #159	-> 71
    //   #166	-> 81
    //   #167	-> 85
    //   #168	-> 104
    //   #170	-> 110
    //   #171	-> 130
    //   #174	-> 136
    //   #175	-> 140
    //   #176	-> 159
    //   #178	-> 165
    //   #179	-> 189
    //   #181	-> 195
    //   #182	-> 202
    //   #184	-> 209
    //   #185	-> 228
    //   #187	-> 234
    //   #188	-> 254
    //   #190	-> 260
    //   #192	-> 264
    //   #193	-> 283
    //   #195	-> 289
    //   #196	-> 313
    //   #201	-> 319
    //   #203	-> 323
    //   #204	-> 328
    //   #205	-> 335
    //   #206	-> 339
    //   #207	-> 343
    //   #209	-> 349
    //   #210	-> 356
    //   #211	-> 364
    //   #212	-> 373
    //   #217	-> 383
    //   #218	-> 386
    //   #219	-> 396
    //   #220	-> 400
    //   #221	-> 413
    //   #222	-> 427
    //   #223	-> 439
    //   #225	-> 452
    //   #228	-> 467
    //   #229	-> 476
    //   #231	-> 492
    //   #232	-> 505
    //   #233	-> 513
    //   #234	-> 518
    //   #236	-> 523
    //   #237	-> 530
    //   #238	-> 533
    //   #240	-> 537
    //   #241	-> 561
    //   #243	-> 574
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   335	14	14	digitCountWithoutLeadingZeros	I
    //   346	3	16	exponent	J
    //   427	103	20	isExponentNegative	Z
    //   400	133	8	exponentIndicatorIndex	I
    //   0	593	0	this	Lcom/fasterxml/jackson/core/io/doubleparser/JavaBigDecimalFromCharSequence;
    //   0	593	1	str	Ljava/lang/CharSequence;
    //   0	593	2	offset	I
    //   0	593	3	length	I
    //   85	508	4	integerPartIndex	I
    //   140	453	5	nonZeroIntegerPartIndex	I
    //   3	590	6	decimalPointIndex	I
    //   6	587	7	nonZeroFractionalPartIndex	I
    //   537	56	8	exponentIndicatorIndex	I
    //   11	582	9	endIndex	I
    //   14	579	10	index	I
    //   24	569	11	ch	C
    //   27	566	12	illegal	Z
    //   41	552	13	isNegative	Z
    //   373	220	14	digitCountWithoutLeadingZeros	I
    //   323	270	15	significandEndIndex	I
    //   383	210	16	exponent	J
    //   386	207	18	expNumber	J
  }
  
  BigDecimal valueOfBigDecimalString(CharSequence str, int integerPartIndex, int decimalPointIndex, int nonZeroFractionalPartIndex, int exponentIndicatorIndex, boolean isNegative, int exponent) {
    BigInteger significand, integerPart;
    int fractionDigitsCount = exponentIndicatorIndex - decimalPointIndex - 1;
    int nonZeroFractionDigitsCount = exponentIndicatorIndex - nonZeroFractionalPartIndex;
    int integerDigitsCount = decimalPointIndex - integerPartIndex;
    NavigableMap<Integer, BigInteger> powersOfTen = null;
    if (integerDigitsCount > 0) {
      if (integerDigitsCount > 400) {
        powersOfTen = FastIntegerMath.createPowersOfTenFloor16Map();
        FastIntegerMath.fillPowersOfNFloor16Recursive(powersOfTen, integerPartIndex, decimalPointIndex);
        integerPart = ParseDigitsTaskCharSequence.parseDigitsRecursive(str, integerPartIndex, decimalPointIndex, powersOfTen, 400);
      } else {
        integerPart = ParseDigitsTaskCharSequence.parseDigitsIterative(str, integerPartIndex, decimalPointIndex);
      } 
    } else {
      integerPart = BigInteger.ZERO;
    } 
    if (fractionDigitsCount > 0) {
      BigInteger fractionalPart;
      if (nonZeroFractionDigitsCount > 400) {
        if (powersOfTen == null)
          powersOfTen = FastIntegerMath.createPowersOfTenFloor16Map(); 
        FastIntegerMath.fillPowersOfNFloor16Recursive(powersOfTen, nonZeroFractionalPartIndex, exponentIndicatorIndex);
        fractionalPart = ParseDigitsTaskCharSequence.parseDigitsRecursive(str, nonZeroFractionalPartIndex, exponentIndicatorIndex, powersOfTen, 400);
      } else {
        fractionalPart = ParseDigitsTaskCharSequence.parseDigitsIterative(str, nonZeroFractionalPartIndex, exponentIndicatorIndex);
      } 
      if (integerPart.signum() == 0) {
        significand = fractionalPart;
      } else {
        BigInteger integerFactor = FastIntegerMath.computePowerOfTen(powersOfTen, fractionDigitsCount);
        significand = FftMultiplier.multiply(integerPart, integerFactor).add(fractionalPart);
      } 
    } else {
      significand = integerPart;
    } 
    return new BigDecimal(isNegative ? significand.negate() : significand, -exponent);
  }
}
