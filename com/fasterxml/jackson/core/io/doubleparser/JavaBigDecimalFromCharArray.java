package com.fasterxml.jackson.core.io.doubleparser;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.NavigableMap;

final class JavaBigDecimalFromCharArray extends AbstractBigDecimalParser {
  public BigDecimal parseBigDecimalString(char[] str, int offset, int length) {
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
    //   20: invokevirtual parseBigDecimalStringWithManyDigits : ([CII)Ljava/math/BigDecimal;
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
    //   38: invokestatic charAt : ([CII)C
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
    //   80: invokestatic charAt : ([CII)C
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
    //   114: caload
    //   115: istore #11
    //   117: iload #11
    //   119: invokestatic isDigit : (C)Z
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
    //   184: invokestatic tryToParseFourDigits : ([CI)I
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
    //   291: invokestatic charAt : ([CII)C
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
    //   330: invokestatic charAt : ([CII)C
    //   333: istore #11
    //   335: iload #12
    //   337: iload #11
    //   339: invokestatic isDigit : (C)Z
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
    //   386: invokestatic charAt : ([CII)C
    //   389: istore #11
    //   391: iload #11
    //   393: invokestatic isDigit : (C)Z
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
    //   502: invokevirtual valueOfBigDecimalString : ([CIIIIZI)Ljava/math/BigDecimal;
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
    //   #38	-> 0
    //   #39	-> 9
    //   #40	-> 16
    //   #42	-> 24
    //   #44	-> 27
    //   #47	-> 30
    //   #48	-> 33
    //   #49	-> 43
    //   #54	-> 46
    //   #55	-> 60
    //   #56	-> 72
    //   #57	-> 85
    //   #58	-> 90
    //   #63	-> 100
    //   #64	-> 104
    //   #65	-> 111
    //   #66	-> 117
    //   #68	-> 125
    //   #69	-> 144
    //   #70	-> 151
    //   #71	-> 166
    //   #72	-> 170
    //   #73	-> 179
    //   #74	-> 189
    //   #75	-> 194
    //   #78	-> 197
    //   #72	-> 209
    //   #64	-> 215
    //   #86	-> 221
    //   #88	-> 225
    //   #89	-> 230
    //   #90	-> 237
    //   #91	-> 241
    //   #93	-> 247
    //   #94	-> 256
    //   #99	-> 266
    //   #100	-> 269
    //   #101	-> 279
    //   #102	-> 283
    //   #103	-> 296
    //   #104	-> 310
    //   #105	-> 322
    //   #107	-> 335
    //   #110	-> 353
    //   #111	-> 362
    //   #113	-> 378
    //   #114	-> 391
    //   #115	-> 399
    //   #116	-> 404
    //   #118	-> 409
    //   #119	-> 416
    //   #120	-> 419
    //   #122	-> 423
    //   #123	-> 438
    //   #124	-> 451
    //   #125	-> 458
    //   #127	-> 485
    //   #128	-> 506
    //   #129	-> 508
    //   #130	-> 519
    //   #131	-> 527
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
    //   43	463	11	ch	C
    //   46	460	12	illegal	Z
    //   60	446	13	isNegative	Z
    //   256	250	14	digitCount	I
    //   225	281	15	significandEndIndex	I
    //   266	240	16	exponent	J
    //   269	237	18	expNumber	J
    //   519	11	5	nfe	Ljava/lang/NumberFormatException;
    //   508	22	4	e	Ljava/lang/ArithmeticException;
    //   0	530	0	this	Lcom/fasterxml/jackson/core/io/doubleparser/JavaBigDecimalFromCharArray;
    //   0	530	1	str	[C
    //   0	530	2	offset	I
    //   0	530	3	length	I
    // Exception table:
    //   from	to	target	type
    //   0	23	506	java/lang/ArithmeticException
    //   24	484	506	java/lang/ArithmeticException
    //   485	505	506	java/lang/ArithmeticException
  }
  
  BigDecimal parseBigDecimalStringWithManyDigits(char[] str, int offset, int length) {
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
    //   19: invokestatic charAt : ([CII)C
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
    //   61: invokestatic charAt : ([CII)C
    //   64: istore #11
    //   66: iload #11
    //   68: ifne -> 81
    //   71: new java/lang/NumberFormatException
    //   74: dup
    //   75: ldc 'illegal syntax'
    //   77: invokespecial <init> : (Ljava/lang/String;)V
    //   80: athrow
    //   81: iload #10
    //   83: istore #5
    //   85: iload #9
    //   87: bipush #8
    //   89: isub
    //   90: ldc 1073741824
    //   92: invokestatic min : (II)I
    //   95: istore #14
    //   97: iload #10
    //   99: iload #14
    //   101: if_icmpge -> 119
    //   104: aload_1
    //   105: iload #10
    //   107: invokestatic isEightZeroes : ([CI)Z
    //   110: ifeq -> 119
    //   113: iinc #10, 8
    //   116: goto -> 97
    //   119: iload #10
    //   121: iload #9
    //   123: if_icmpge -> 141
    //   126: aload_1
    //   127: iload #10
    //   129: caload
    //   130: bipush #48
    //   132: if_icmpne -> 141
    //   135: iinc #10, 1
    //   138: goto -> 119
    //   141: iload #10
    //   143: istore #4
    //   145: iload #10
    //   147: iload #14
    //   149: if_icmpge -> 167
    //   152: aload_1
    //   153: iload #10
    //   155: invokestatic isEightDigits : ([CI)Z
    //   158: ifeq -> 167
    //   161: iinc #10, 8
    //   164: goto -> 145
    //   167: iload #10
    //   169: iload #9
    //   171: if_icmpge -> 193
    //   174: aload_1
    //   175: iload #10
    //   177: caload
    //   178: dup
    //   179: istore #11
    //   181: invokestatic isDigit : (C)Z
    //   184: ifeq -> 193
    //   187: iinc #10, 1
    //   190: goto -> 167
    //   193: iload #11
    //   195: bipush #46
    //   197: if_icmpne -> 303
    //   200: iload #10
    //   202: iinc #10, 1
    //   205: istore #7
    //   207: iload #10
    //   209: iload #14
    //   211: if_icmpge -> 229
    //   214: aload_1
    //   215: iload #10
    //   217: invokestatic isEightZeroes : ([CI)Z
    //   220: ifeq -> 229
    //   223: iinc #10, 8
    //   226: goto -> 207
    //   229: iload #10
    //   231: iload #9
    //   233: if_icmpge -> 251
    //   236: aload_1
    //   237: iload #10
    //   239: caload
    //   240: bipush #48
    //   242: if_icmpne -> 251
    //   245: iinc #10, 1
    //   248: goto -> 229
    //   251: iload #10
    //   253: istore #6
    //   255: iload #10
    //   257: iload #14
    //   259: if_icmpge -> 277
    //   262: aload_1
    //   263: iload #10
    //   265: invokestatic isEightDigits : ([CI)Z
    //   268: ifeq -> 277
    //   271: iinc #10, 8
    //   274: goto -> 255
    //   277: iload #10
    //   279: iload #9
    //   281: if_icmpge -> 303
    //   284: aload_1
    //   285: iload #10
    //   287: caload
    //   288: dup
    //   289: istore #11
    //   291: invokestatic isDigit : (C)Z
    //   294: ifeq -> 303
    //   297: iinc #10, 1
    //   300: goto -> 277
    //   303: iload #10
    //   305: istore #16
    //   307: iload #7
    //   309: ifge -> 333
    //   312: iload #16
    //   314: iload #4
    //   316: isub
    //   317: istore #15
    //   319: iload #16
    //   321: istore #7
    //   323: iload #16
    //   325: istore #6
    //   327: lconst_0
    //   328: lstore #17
    //   330: goto -> 367
    //   333: iload #4
    //   335: iload #7
    //   337: if_icmpne -> 348
    //   340: iload #16
    //   342: iload #6
    //   344: isub
    //   345: goto -> 355
    //   348: iload #16
    //   350: iload #4
    //   352: isub
    //   353: iconst_1
    //   354: isub
    //   355: istore #15
    //   357: iload #7
    //   359: iload #16
    //   361: isub
    //   362: iconst_1
    //   363: iadd
    //   364: i2l
    //   365: lstore #17
    //   367: lconst_0
    //   368: lstore #19
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
    //   392: invokestatic charAt : ([CII)C
    //   395: istore #11
    //   397: iload #11
    //   399: bipush #45
    //   401: if_icmpne -> 408
    //   404: iconst_1
    //   405: goto -> 409
    //   408: iconst_0
    //   409: istore #21
    //   411: iload #21
    //   413: ifne -> 423
    //   416: iload #11
    //   418: bipush #43
    //   420: if_icmpne -> 436
    //   423: aload_1
    //   424: iinc #10, 1
    //   427: iload #10
    //   429: iload #9
    //   431: invokestatic charAt : ([CII)C
    //   434: istore #11
    //   436: iload #11
    //   438: invokestatic isDigit : (C)Z
    //   441: ifne -> 448
    //   444: iconst_1
    //   445: goto -> 449
    //   448: iconst_0
    //   449: istore #12
    //   451: lload #19
    //   453: ldc2_w 2147483647
    //   456: lcmp
    //   457: ifge -> 476
    //   460: ldc2_w 10
    //   463: lload #19
    //   465: lmul
    //   466: iload #11
    //   468: i2l
    //   469: ladd
    //   470: ldc2_w 48
    //   473: lsub
    //   474: lstore #19
    //   476: aload_1
    //   477: iinc #10, 1
    //   480: iload #10
    //   482: iload #9
    //   484: invokestatic charAt : ([CII)C
    //   487: istore #11
    //   489: iload #11
    //   491: invokestatic isDigit : (C)Z
    //   494: ifne -> 451
    //   497: iload #21
    //   499: ifeq -> 507
    //   502: lload #19
    //   504: lneg
    //   505: lstore #19
    //   507: lload #17
    //   509: lload #19
    //   511: ladd
    //   512: lstore #17
    //   514: goto -> 521
    //   517: iload #9
    //   519: istore #8
    //   521: iload #12
    //   523: iload #5
    //   525: iload #7
    //   527: if_icmpne -> 541
    //   530: iload #7
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
    //   551: iload #15
    //   553: lload #17
    //   555: invokestatic checkParsedBigDecimalBounds : (ZIIIJ)V
    //   558: aload_0
    //   559: aload_1
    //   560: iload #4
    //   562: iload #7
    //   564: iload #6
    //   566: iload #8
    //   568: iload #13
    //   570: lload #17
    //   572: l2i
    //   573: invokevirtual valueOfBigDecimalString : ([CIIIIZI)Ljava/math/BigDecimal;
    //   576: areturn
    // Line number table:
    //   Java source line number -> byte code offset
    //   #141	-> 0
    //   #142	-> 3
    //   #145	-> 6
    //   #146	-> 11
    //   #147	-> 14
    //   #148	-> 24
    //   #152	-> 27
    //   #153	-> 41
    //   #154	-> 53
    //   #155	-> 66
    //   #156	-> 71
    //   #162	-> 81
    //   #163	-> 85
    //   #164	-> 97
    //   #165	-> 113
    //   #167	-> 119
    //   #168	-> 135
    //   #171	-> 141
    //   #172	-> 145
    //   #173	-> 161
    //   #175	-> 167
    //   #176	-> 187
    //   #178	-> 193
    //   #179	-> 200
    //   #181	-> 207
    //   #182	-> 223
    //   #184	-> 229
    //   #185	-> 245
    //   #187	-> 251
    //   #189	-> 255
    //   #190	-> 271
    //   #192	-> 277
    //   #193	-> 297
    //   #198	-> 303
    //   #200	-> 307
    //   #201	-> 312
    //   #202	-> 319
    //   #203	-> 323
    //   #204	-> 327
    //   #206	-> 333
    //   #207	-> 340
    //   #208	-> 348
    //   #209	-> 357
    //   #214	-> 367
    //   #215	-> 370
    //   #216	-> 380
    //   #217	-> 384
    //   #218	-> 397
    //   #219	-> 411
    //   #220	-> 423
    //   #222	-> 436
    //   #225	-> 451
    //   #226	-> 460
    //   #228	-> 476
    //   #229	-> 489
    //   #230	-> 497
    //   #231	-> 502
    //   #233	-> 507
    //   #234	-> 514
    //   #235	-> 517
    //   #237	-> 521
    //   #238	-> 545
    //   #240	-> 558
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   319	14	15	digitCountWithoutLeadingZeros	I
    //   330	3	17	exponent	J
    //   411	103	21	isExponentNegative	Z
    //   384	133	8	exponentIndicatorIndex	I
    //   0	577	0	this	Lcom/fasterxml/jackson/core/io/doubleparser/JavaBigDecimalFromCharArray;
    //   0	577	1	str	[C
    //   0	577	2	offset	I
    //   0	577	3	length	I
    //   145	432	4	nonZeroIntegerPartIndex	I
    //   85	492	5	integerPartIndex	I
    //   3	574	6	nonZeroFractionalPartIndex	I
    //   6	571	7	decimalPointIndex	I
    //   521	56	8	exponentIndicatorIndex	I
    //   11	566	9	endIndex	I
    //   14	563	10	index	I
    //   24	553	11	ch	C
    //   27	550	12	illegal	Z
    //   41	536	13	isNegative	Z
    //   97	480	14	swarLimit	I
    //   357	220	15	digitCountWithoutLeadingZeros	I
    //   307	270	16	significandEndIndex	I
    //   367	210	17	exponent	J
    //   370	207	19	expNumber	J
  }
  
  BigDecimal valueOfBigDecimalString(char[] str, int integerPartIndex, int decimalPointIndex, int nonZeroFractionalPartIndex, int exponentIndicatorIndex, boolean isNegative, int exponent) {
    BigInteger significand, integerPart;
    int integerExponent = exponentIndicatorIndex - decimalPointIndex - 1;
    int fractionDigitsCount = exponentIndicatorIndex - nonZeroFractionalPartIndex;
    int nonZeroFractionDigitsCount = exponentIndicatorIndex - nonZeroFractionalPartIndex;
    int integerDigitsCount = decimalPointIndex - integerPartIndex;
    NavigableMap<Integer, BigInteger> powersOfTen = null;
    if (integerDigitsCount > 0) {
      if (integerDigitsCount > 400) {
        powersOfTen = FastIntegerMath.createPowersOfTenFloor16Map();
        FastIntegerMath.fillPowersOfNFloor16Recursive(powersOfTen, integerPartIndex, decimalPointIndex);
        integerPart = ParseDigitsTaskCharArray.parseDigitsRecursive(str, integerPartIndex, decimalPointIndex, powersOfTen, 400);
      } else {
        integerPart = ParseDigitsTaskCharArray.parseDigitsIterative(str, integerPartIndex, decimalPointIndex);
      } 
    } else {
      integerPart = BigInteger.ZERO;
    } 
    if (fractionDigitsCount > 0) {
      BigInteger fractionalPart;
      if (nonZeroFractionDigitsCount > 400) {
        if (powersOfTen == null)
          powersOfTen = FastIntegerMath.createPowersOfTenFloor16Map(); 
        FastIntegerMath.fillPowersOfNFloor16Recursive(powersOfTen, decimalPointIndex + 1, exponentIndicatorIndex);
        fractionalPart = ParseDigitsTaskCharArray.parseDigitsRecursive(str, decimalPointIndex + 1, exponentIndicatorIndex, powersOfTen, 400);
      } else {
        fractionalPart = ParseDigitsTaskCharArray.parseDigitsIterative(str, decimalPointIndex + 1, exponentIndicatorIndex);
      } 
      if (integerPart.signum() == 0) {
        significand = fractionalPart;
      } else {
        BigInteger integerFactor = FastIntegerMath.computePowerOfTen(powersOfTen, integerExponent);
        significand = FftMultiplier.multiply(integerPart, integerFactor).add(fractionalPart);
      } 
    } else {
      significand = integerPart;
    } 
    return new BigDecimal(isNegative ? significand.negate() : significand, -exponent);
  }
}
