package com.fasterxml.jackson.core.io.doubleparser;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.NavigableMap;

final class JavaBigDecimalFromByteArray extends AbstractBigDecimalParser {
  public BigDecimal parseBigDecimalString(byte[] str, int offset, int length) {
    // Byte code:
    //   0: aload_1
    //   1: arraylength
    //   2: iload_2
    //   3: iload_3
    //   4: invokestatic checkBounds : (III)I
    //   7: istore #4
    //   9: iload_3
    //   10: invokestatic hasManyDigits : (I)Z
    //   13: ifeq -> 24
    //   16: aload_0
    //   17: aload_1
    //   18: iload_2
    //   19: iload_3
    //   20: invokevirtual parseBigDecimalStringWithManyDigits : ([BII)Ljava/math/BigDecimal;
    //   23: areturn
    //   24: lconst_0
    //   25: lstore #5
    //   27: iconst_m1
    //   28: istore #8
    //   30: iload_2
    //   31: istore #10
    //   33: aload_1
    //   34: iload #10
    //   36: iload #4
    //   38: invokestatic charAt : ([BII)B
    //   41: istore #11
    //   43: iconst_0
    //   44: istore #12
    //   46: iload #11
    //   48: bipush #45
    //   50: if_icmpne -> 57
    //   53: iconst_1
    //   54: goto -> 58
    //   57: iconst_0
    //   58: istore #13
    //   60: iload #13
    //   62: ifne -> 72
    //   65: iload #11
    //   67: bipush #43
    //   69: if_icmpne -> 100
    //   72: aload_1
    //   73: iinc #10, 1
    //   76: iload #10
    //   78: iload #4
    //   80: invokestatic charAt : ([BII)B
    //   83: istore #11
    //   85: iload #11
    //   87: ifne -> 100
    //   90: new java/lang/NumberFormatException
    //   93: dup
    //   94: ldc 'illegal syntax'
    //   96: invokespecial <init> : (Ljava/lang/String;)V
    //   99: athrow
    //   100: iload #10
    //   102: istore #7
    //   104: iload #10
    //   106: iload #4
    //   108: if_icmpge -> 221
    //   111: aload_1
    //   112: iload #10
    //   114: baload
    //   115: istore #11
    //   117: iload #11
    //   119: invokestatic isDigit : (B)Z
    //   122: ifeq -> 144
    //   125: ldc2_w 10
    //   128: lload #5
    //   130: lmul
    //   131: iload #11
    //   133: i2l
    //   134: ladd
    //   135: ldc2_w 48
    //   138: lsub
    //   139: lstore #5
    //   141: goto -> 215
    //   144: iload #11
    //   146: bipush #46
    //   148: if_icmpne -> 221
    //   151: iload #12
    //   153: iload #8
    //   155: iflt -> 162
    //   158: iconst_1
    //   159: goto -> 163
    //   162: iconst_0
    //   163: ior
    //   164: istore #12
    //   166: iload #10
    //   168: istore #8
    //   170: iload #10
    //   172: iload #4
    //   174: iconst_4
    //   175: isub
    //   176: if_icmpge -> 215
    //   179: aload_1
    //   180: iload #10
    //   182: iconst_1
    //   183: iadd
    //   184: invokestatic tryToParseFourDigits : ([BI)I
    //   187: istore #14
    //   189: iload #14
    //   191: ifge -> 197
    //   194: goto -> 215
    //   197: ldc2_w 10000
    //   200: lload #5
    //   202: lmul
    //   203: iload #14
    //   205: i2l
    //   206: ladd
    //   207: lstore #5
    //   209: iinc #10, 4
    //   212: goto -> 170
    //   215: iinc #10, 1
    //   218: goto -> 104
    //   221: iload #10
    //   223: istore #15
    //   225: iload #8
    //   227: ifge -> 247
    //   230: iload #15
    //   232: iload #7
    //   234: isub
    //   235: istore #14
    //   237: iload #15
    //   239: istore #8
    //   241: lconst_0
    //   242: lstore #16
    //   244: goto -> 266
    //   247: iload #15
    //   249: iload #7
    //   251: isub
    //   252: iconst_1
    //   253: isub
    //   254: istore #14
    //   256: iload #8
    //   258: iload #15
    //   260: isub
    //   261: iconst_1
    //   262: iadd
    //   263: i2l
    //   264: lstore #16
    //   266: lconst_0
    //   267: lstore #18
    //   269: iload #11
    //   271: bipush #32
    //   273: ior
    //   274: bipush #101
    //   276: if_icmpne -> 419
    //   279: iload #10
    //   281: istore #9
    //   283: aload_1
    //   284: iinc #10, 1
    //   287: iload #10
    //   289: iload #4
    //   291: invokestatic charAt : ([BII)B
    //   294: istore #11
    //   296: iload #11
    //   298: bipush #45
    //   300: if_icmpne -> 307
    //   303: iconst_1
    //   304: goto -> 308
    //   307: iconst_0
    //   308: istore #20
    //   310: iload #20
    //   312: ifne -> 322
    //   315: iload #11
    //   317: bipush #43
    //   319: if_icmpne -> 335
    //   322: aload_1
    //   323: iinc #10, 1
    //   326: iload #10
    //   328: iload #4
    //   330: invokestatic charAt : ([BII)B
    //   333: istore #11
    //   335: iload #12
    //   337: iload #11
    //   339: invokestatic isDigit : (B)Z
    //   342: ifne -> 349
    //   345: iconst_1
    //   346: goto -> 350
    //   349: iconst_0
    //   350: ior
    //   351: istore #12
    //   353: lload #18
    //   355: ldc2_w 2147483647
    //   358: lcmp
    //   359: ifge -> 378
    //   362: ldc2_w 10
    //   365: lload #18
    //   367: lmul
    //   368: iload #11
    //   370: i2l
    //   371: ladd
    //   372: ldc2_w 48
    //   375: lsub
    //   376: lstore #18
    //   378: aload_1
    //   379: iinc #10, 1
    //   382: iload #10
    //   384: iload #4
    //   386: invokestatic charAt : ([BII)B
    //   389: istore #11
    //   391: iload #11
    //   393: invokestatic isDigit : (B)Z
    //   396: ifne -> 353
    //   399: iload #20
    //   401: ifeq -> 409
    //   404: lload #18
    //   406: lneg
    //   407: lstore #18
    //   409: lload #16
    //   411: lload #18
    //   413: ladd
    //   414: lstore #16
    //   416: goto -> 423
    //   419: iload #4
    //   421: istore #9
    //   423: iload #12
    //   425: iload #14
    //   427: ifne -> 434
    //   430: iconst_1
    //   431: goto -> 435
    //   434: iconst_0
    //   435: ior
    //   436: istore #12
    //   438: iload #12
    //   440: iload #10
    //   442: iload #4
    //   444: iload #14
    //   446: lload #16
    //   448: invokestatic checkParsedBigDecimalBounds : (ZIIIJ)V
    //   451: iload #14
    //   453: bipush #19
    //   455: if_icmpge -> 485
    //   458: new java/math/BigDecimal
    //   461: dup
    //   462: iload #13
    //   464: ifeq -> 473
    //   467: lload #5
    //   469: lneg
    //   470: goto -> 475
    //   473: lload #5
    //   475: invokespecial <init> : (J)V
    //   478: lload #16
    //   480: l2i
    //   481: invokevirtual scaleByPowerOfTen : (I)Ljava/math/BigDecimal;
    //   484: areturn
    //   485: aload_0
    //   486: aload_1
    //   487: iload #7
    //   489: iload #8
    //   491: iload #8
    //   493: iconst_1
    //   494: iadd
    //   495: iload #9
    //   497: iload #13
    //   499: lload #16
    //   501: l2i
    //   502: invokevirtual valueOfBigDecimalString : ([BIIIIZI)Ljava/math/BigDecimal;
    //   505: areturn
    //   506: astore #4
    //   508: new java/lang/NumberFormatException
    //   511: dup
    //   512: ldc 'value exceeds limits'
    //   514: invokespecial <init> : (Ljava/lang/String;)V
    //   517: astore #5
    //   519: aload #5
    //   521: aload #4
    //   523: invokevirtual initCause : (Ljava/lang/Throwable;)Ljava/lang/Throwable;
    //   526: pop
    //   527: aload #5
    //   529: athrow
    // Line number table:
    //   Java source line number -> byte code offset
    //   #41	-> 0
    //   #42	-> 9
    //   #43	-> 16
    //   #45	-> 24
    //   #47	-> 27
    //   #50	-> 30
    //   #51	-> 33
    //   #52	-> 43
    //   #57	-> 46
    //   #58	-> 60
    //   #59	-> 72
    //   #60	-> 85
    //   #61	-> 90
    //   #66	-> 100
    //   #67	-> 104
    //   #68	-> 111
    //   #69	-> 117
    //   #71	-> 125
    //   #72	-> 144
    //   #73	-> 151
    //   #74	-> 166
    //   #75	-> 170
    //   #76	-> 179
    //   #77	-> 189
    //   #78	-> 194
    //   #81	-> 197
    //   #75	-> 209
    //   #67	-> 215
    //   #89	-> 221
    //   #91	-> 225
    //   #92	-> 230
    //   #93	-> 237
    //   #94	-> 241
    //   #96	-> 247
    //   #97	-> 256
    //   #102	-> 266
    //   #103	-> 269
    //   #104	-> 279
    //   #105	-> 283
    //   #106	-> 296
    //   #107	-> 310
    //   #108	-> 322
    //   #110	-> 335
    //   #113	-> 353
    //   #114	-> 362
    //   #116	-> 378
    //   #117	-> 391
    //   #118	-> 399
    //   #119	-> 404
    //   #121	-> 409
    //   #122	-> 416
    //   #123	-> 419
    //   #125	-> 423
    //   #126	-> 438
    //   #127	-> 451
    //   #128	-> 458
    //   #130	-> 485
    //   #131	-> 506
    //   #132	-> 508
    //   #133	-> 519
    //   #134	-> 527
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   189	20	14	digits	I
    //   237	10	14	digitCount	I
    //   244	3	16	exponent	J
    //   310	106	20	isExponentNegative	Z
    //   283	136	9	exponentIndicatorIndex	I
    //   9	497	4	endIndex	I
    //   27	479	5	significand	J
    //   104	402	7	integerPartIndex	I
    //   30	476	8	decimalPointIndex	I
    //   423	83	9	exponentIndicatorIndex	I
    //   33	473	10	index	I
    //   43	463	11	ch	B
    //   46	460	12	illegal	Z
    //   60	446	13	isNegative	Z
    //   256	250	14	digitCount	I
    //   225	281	15	significandEndIndex	I
    //   266	240	16	exponent	J
    //   269	237	18	expNumber	J
    //   519	11	5	nfe	Ljava/lang/NumberFormatException;
    //   508	22	4	e	Ljava/lang/ArithmeticException;
    //   0	530	0	this	Lcom/fasterxml/jackson/core/io/doubleparser/JavaBigDecimalFromByteArray;
    //   0	530	1	str	[B
    //   0	530	2	offset	I
    //   0	530	3	length	I
    // Exception table:
    //   from	to	target	type
    //   0	23	506	java/lang/ArithmeticException
    //   24	484	506	java/lang/ArithmeticException
    //   485	505	506	java/lang/ArithmeticException
  }
  
  BigDecimal parseBigDecimalStringWithManyDigits(byte[] str, int offset, int length) {
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
    //   19: invokestatic charAt : ([BII)B
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
    //   61: invokestatic charAt : ([BII)B
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
    //   98: invokestatic isEightZeroes : ([BI)Z
    //   101: ifeq -> 110
    //   104: iinc #10, 8
    //   107: goto -> 85
    //   110: iload #10
    //   112: iload #9
    //   114: if_icmpge -> 132
    //   117: aload_1
    //   118: iload #10
    //   120: baload
    //   121: bipush #48
    //   123: if_icmpne -> 132
    //   126: iinc #10, 1
    //   129: goto -> 110
    //   132: iload #10
    //   134: istore #5
    //   136: iload #10
    //   138: iload #9
    //   140: bipush #8
    //   142: isub
    //   143: if_icmpge -> 161
    //   146: aload_1
    //   147: iload #10
    //   149: invokestatic isEightDigits : ([BI)Z
    //   152: ifeq -> 161
    //   155: iinc #10, 8
    //   158: goto -> 136
    //   161: iload #10
    //   163: iload #9
    //   165: if_icmpge -> 187
    //   168: aload_1
    //   169: iload #10
    //   171: baload
    //   172: dup
    //   173: istore #11
    //   175: invokestatic isDigit : (B)Z
    //   178: ifeq -> 187
    //   181: iinc #10, 1
    //   184: goto -> 161
    //   187: iload #11
    //   189: bipush #46
    //   191: if_icmpne -> 303
    //   194: iload #10
    //   196: iinc #10, 1
    //   199: istore #6
    //   201: iload #10
    //   203: iload #9
    //   205: bipush #8
    //   207: isub
    //   208: if_icmpge -> 226
    //   211: aload_1
    //   212: iload #10
    //   214: invokestatic isEightZeroes : ([BI)Z
    //   217: ifeq -> 226
    //   220: iinc #10, 8
    //   223: goto -> 201
    //   226: iload #10
    //   228: iload #9
    //   230: if_icmpge -> 248
    //   233: aload_1
    //   234: iload #10
    //   236: baload
    //   237: bipush #48
    //   239: if_icmpne -> 248
    //   242: iinc #10, 1
    //   245: goto -> 226
    //   248: iload #10
    //   250: istore #7
    //   252: iload #10
    //   254: iload #9
    //   256: bipush #8
    //   258: isub
    //   259: if_icmpge -> 277
    //   262: aload_1
    //   263: iload #10
    //   265: invokestatic isEightDigits : ([BI)Z
    //   268: ifeq -> 277
    //   271: iinc #10, 8
    //   274: goto -> 252
    //   277: iload #10
    //   279: iload #9
    //   281: if_icmpge -> 303
    //   284: aload_1
    //   285: iload #10
    //   287: baload
    //   288: dup
    //   289: istore #11
    //   291: invokestatic isDigit : (B)Z
    //   294: ifeq -> 303
    //   297: iinc #10, 1
    //   300: goto -> 277
    //   303: iload #10
    //   305: istore #15
    //   307: iload #6
    //   309: ifge -> 333
    //   312: iload #15
    //   314: iload #5
    //   316: isub
    //   317: istore #14
    //   319: iload #15
    //   321: istore #6
    //   323: iload #15
    //   325: istore #7
    //   327: lconst_0
    //   328: lstore #16
    //   330: goto -> 367
    //   333: iload #5
    //   335: iload #6
    //   337: if_icmpne -> 348
    //   340: iload #15
    //   342: iload #7
    //   344: isub
    //   345: goto -> 355
    //   348: iload #15
    //   350: iload #5
    //   352: isub
    //   353: iconst_1
    //   354: isub
    //   355: istore #14
    //   357: iload #6
    //   359: iload #15
    //   361: isub
    //   362: iconst_1
    //   363: iadd
    //   364: i2l
    //   365: lstore #16
    //   367: lconst_0
    //   368: lstore #18
    //   370: iload #11
    //   372: bipush #32
    //   374: ior
    //   375: bipush #101
    //   377: if_icmpne -> 517
    //   380: iload #10
    //   382: istore #8
    //   384: aload_1
    //   385: iinc #10, 1
    //   388: iload #10
    //   390: iload #9
    //   392: invokestatic charAt : ([BII)B
    //   395: istore #11
    //   397: iload #11
    //   399: bipush #45
    //   401: if_icmpne -> 408
    //   404: iconst_1
    //   405: goto -> 409
    //   408: iconst_0
    //   409: istore #20
    //   411: iload #20
    //   413: ifne -> 423
    //   416: iload #11
    //   418: bipush #43
    //   420: if_icmpne -> 436
    //   423: aload_1
    //   424: iinc #10, 1
    //   427: iload #10
    //   429: iload #9
    //   431: invokestatic charAt : ([BII)B
    //   434: istore #11
    //   436: iload #11
    //   438: invokestatic isDigit : (B)Z
    //   441: ifne -> 448
    //   444: iconst_1
    //   445: goto -> 449
    //   448: iconst_0
    //   449: istore #12
    //   451: lload #18
    //   453: ldc2_w 2147483647
    //   456: lcmp
    //   457: ifge -> 476
    //   460: ldc2_w 10
    //   463: lload #18
    //   465: lmul
    //   466: iload #11
    //   468: i2l
    //   469: ladd
    //   470: ldc2_w 48
    //   473: lsub
    //   474: lstore #18
    //   476: aload_1
    //   477: iinc #10, 1
    //   480: iload #10
    //   482: iload #9
    //   484: invokestatic charAt : ([BII)B
    //   487: istore #11
    //   489: iload #11
    //   491: invokestatic isDigit : (B)Z
    //   494: ifne -> 451
    //   497: iload #20
    //   499: ifeq -> 507
    //   502: lload #18
    //   504: lneg
    //   505: lstore #18
    //   507: lload #16
    //   509: lload #18
    //   511: ladd
    //   512: lstore #16
    //   514: goto -> 521
    //   517: iload #9
    //   519: istore #8
    //   521: iload #12
    //   523: iload #4
    //   525: iload #6
    //   527: if_icmpne -> 541
    //   530: iload #6
    //   532: iload #8
    //   534: if_icmpne -> 541
    //   537: iconst_1
    //   538: goto -> 542
    //   541: iconst_0
    //   542: ior
    //   543: istore #12
    //   545: iload #12
    //   547: iload #10
    //   549: iload #9
    //   551: iload #14
    //   553: lload #16
    //   555: invokestatic checkParsedBigDecimalBounds : (ZIIIJ)V
    //   558: aload_0
    //   559: aload_1
    //   560: iload #5
    //   562: iload #6
    //   564: iload #7
    //   566: iload #8
    //   568: iload #13
    //   570: lload #16
    //   572: l2i
    //   573: invokevirtual valueOfBigDecimalString : ([BIIIIZI)Ljava/math/BigDecimal;
    //   576: areturn
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
    //   #171	-> 126
    //   #174	-> 132
    //   #175	-> 136
    //   #176	-> 155
    //   #178	-> 161
    //   #179	-> 181
    //   #181	-> 187
    //   #182	-> 194
    //   #184	-> 201
    //   #185	-> 220
    //   #187	-> 226
    //   #188	-> 242
    //   #190	-> 248
    //   #192	-> 252
    //   #193	-> 271
    //   #195	-> 277
    //   #196	-> 297
    //   #201	-> 303
    //   #203	-> 307
    //   #204	-> 312
    //   #205	-> 319
    //   #206	-> 323
    //   #207	-> 327
    //   #209	-> 333
    //   #210	-> 340
    //   #211	-> 348
    //   #212	-> 357
    //   #217	-> 367
    //   #218	-> 370
    //   #219	-> 380
    //   #220	-> 384
    //   #221	-> 397
    //   #222	-> 411
    //   #223	-> 423
    //   #225	-> 436
    //   #228	-> 451
    //   #229	-> 460
    //   #231	-> 476
    //   #232	-> 489
    //   #233	-> 497
    //   #234	-> 502
    //   #236	-> 507
    //   #237	-> 514
    //   #238	-> 517
    //   #240	-> 521
    //   #241	-> 545
    //   #243	-> 558
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   319	14	14	digitCountWithoutLeadingZeros	I
    //   330	3	16	exponent	J
    //   411	103	20	isExponentNegative	Z
    //   384	133	8	exponentIndicatorIndex	I
    //   0	577	0	this	Lcom/fasterxml/jackson/core/io/doubleparser/JavaBigDecimalFromByteArray;
    //   0	577	1	str	[B
    //   0	577	2	offset	I
    //   0	577	3	length	I
    //   85	492	4	integerPartIndex	I
    //   136	441	5	nonZeroIntegerPartIndex	I
    //   3	574	6	decimalPointIndex	I
    //   6	571	7	nonZeroFractionalPartIndex	I
    //   521	56	8	exponentIndicatorIndex	I
    //   11	566	9	endIndex	I
    //   14	563	10	index	I
    //   24	553	11	ch	B
    //   27	550	12	illegal	Z
    //   41	536	13	isNegative	Z
    //   357	220	14	digitCountWithoutLeadingZeros	I
    //   307	270	15	significandEndIndex	I
    //   367	210	16	exponent	J
    //   370	207	18	expNumber	J
  }
  
  BigDecimal valueOfBigDecimalString(byte[] str, int integerPartIndex, int decimalPointIndex, int nonZeroFractionalPartIndex, int exponentIndicatorIndex, boolean isNegative, int exponent) {
    BigInteger significand, integerPart;
    int fractionDigitsCount = exponentIndicatorIndex - decimalPointIndex - 1;
    int nonZeroFractionDigitsCount = exponentIndicatorIndex - nonZeroFractionalPartIndex;
    int integerDigitsCount = decimalPointIndex - integerPartIndex;
    NavigableMap<Integer, BigInteger> powersOfTen = null;
    if (integerDigitsCount > 0) {
      if (integerDigitsCount > 400) {
        powersOfTen = FastIntegerMath.createPowersOfTenFloor16Map();
        FastIntegerMath.fillPowersOfNFloor16Recursive(powersOfTen, integerPartIndex, decimalPointIndex);
        integerPart = ParseDigitsTaskByteArray.parseDigitsRecursive(str, integerPartIndex, decimalPointIndex, powersOfTen, 400);
      } else {
        integerPart = ParseDigitsTaskByteArray.parseDigitsIterative(str, integerPartIndex, decimalPointIndex);
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
        fractionalPart = ParseDigitsTaskByteArray.parseDigitsRecursive(str, nonZeroFractionalPartIndex, exponentIndicatorIndex, powersOfTen, 400);
      } else {
        fractionalPart = ParseDigitsTaskByteArray.parseDigitsIterative(str, nonZeroFractionalPartIndex, exponentIndicatorIndex);
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
