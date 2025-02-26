package com.fasterxml.jackson.databind.util;

import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

@Deprecated
public class ISO8601Utils {
  protected static final int DEF_8601_LEN = "yyyy-MM-ddThh:mm:ss.SSS+00:00".length();
  
  private static final TimeZone TIMEZONE_Z = TimeZone.getTimeZone("UTC");
  
  public static String format(Date date) {
    return format(date, false, TIMEZONE_Z);
  }
  
  public static String format(Date date, boolean millis) {
    return format(date, millis, TIMEZONE_Z);
  }
  
  @Deprecated
  public static String format(Date date, boolean millis, TimeZone tz) {
    return format(date, millis, tz, Locale.US);
  }
  
  public static String format(Date date, boolean millis, TimeZone tz, Locale loc) {
    Calendar calendar = new GregorianCalendar(tz, loc);
    calendar.setTime(date);
    StringBuilder sb = new StringBuilder(30);
    sb.append(String.format("%04d-%02d-%02dT%02d:%02d:%02d", new Object[] { Integer.valueOf(calendar.get(1)), 
            Integer.valueOf(calendar.get(2) + 1), 
            Integer.valueOf(calendar.get(5)), 
            Integer.valueOf(calendar.get(11)), 
            Integer.valueOf(calendar.get(12)), 
            Integer.valueOf(calendar.get(13)) }));
    if (millis)
      sb.append(String.format(".%03d", new Object[] { Integer.valueOf(calendar.get(14)) })); 
    int offset = tz.getOffset(calendar.getTimeInMillis());
    if (offset != 0) {
      int hours = Math.abs(offset / 60000 / 60);
      int minutes = Math.abs(offset / 60000 % 60);
      sb.append(String.format("%c%02d:%02d", new Object[] { Character.valueOf((offset < 0) ? 45 : 43), 
              Integer.valueOf(hours), Integer.valueOf(minutes) }));
    } else {
      sb.append('Z');
    } 
    return sb.toString();
  }
  
  public static Date parse(String date, ParsePosition pos) throws ParseException {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic requireNonNull : (Ljava/lang/Object;)Ljava/lang/Object;
    //   4: pop
    //   5: aconst_null
    //   6: astore_2
    //   7: aload_1
    //   8: invokevirtual getIndex : ()I
    //   11: istore_3
    //   12: aload_0
    //   13: iload_3
    //   14: iinc #3, 4
    //   17: iload_3
    //   18: invokestatic parseInt : (Ljava/lang/String;II)I
    //   21: istore #4
    //   23: aload_0
    //   24: iload_3
    //   25: bipush #45
    //   27: invokestatic checkOffset : (Ljava/lang/String;IC)Z
    //   30: ifeq -> 36
    //   33: iinc #3, 1
    //   36: aload_0
    //   37: iload_3
    //   38: iinc #3, 2
    //   41: iload_3
    //   42: invokestatic parseInt : (Ljava/lang/String;II)I
    //   45: istore #5
    //   47: aload_0
    //   48: iload_3
    //   49: bipush #45
    //   51: invokestatic checkOffset : (Ljava/lang/String;IC)Z
    //   54: ifeq -> 60
    //   57: iinc #3, 1
    //   60: aload_0
    //   61: iload_3
    //   62: iinc #3, 2
    //   65: iload_3
    //   66: invokestatic parseInt : (Ljava/lang/String;II)I
    //   69: istore #6
    //   71: iconst_0
    //   72: istore #7
    //   74: iconst_0
    //   75: istore #8
    //   77: iconst_0
    //   78: istore #9
    //   80: iconst_0
    //   81: istore #10
    //   83: aload_0
    //   84: iload_3
    //   85: bipush #84
    //   87: invokestatic checkOffset : (Ljava/lang/String;IC)Z
    //   90: istore #11
    //   92: iload #11
    //   94: ifne -> 133
    //   97: aload_0
    //   98: invokevirtual length : ()I
    //   101: iload_3
    //   102: if_icmpgt -> 133
    //   105: new java/util/GregorianCalendar
    //   108: dup
    //   109: iload #4
    //   111: iload #5
    //   113: iconst_1
    //   114: isub
    //   115: iload #6
    //   117: invokespecial <init> : (III)V
    //   120: astore #12
    //   122: aload_1
    //   123: iload_3
    //   124: invokevirtual setIndex : (I)V
    //   127: aload #12
    //   129: invokevirtual getTime : ()Ljava/util/Date;
    //   132: areturn
    //   133: iload #11
    //   135: ifeq -> 351
    //   138: aload_0
    //   139: iinc #3, 1
    //   142: iload_3
    //   143: iinc #3, 2
    //   146: iload_3
    //   147: invokestatic parseInt : (Ljava/lang/String;II)I
    //   150: istore #7
    //   152: aload_0
    //   153: iload_3
    //   154: bipush #58
    //   156: invokestatic checkOffset : (Ljava/lang/String;IC)Z
    //   159: ifeq -> 165
    //   162: iinc #3, 1
    //   165: aload_0
    //   166: iload_3
    //   167: iinc #3, 2
    //   170: iload_3
    //   171: invokestatic parseInt : (Ljava/lang/String;II)I
    //   174: istore #8
    //   176: aload_0
    //   177: iload_3
    //   178: bipush #58
    //   180: invokestatic checkOffset : (Ljava/lang/String;IC)Z
    //   183: ifeq -> 189
    //   186: iinc #3, 1
    //   189: aload_0
    //   190: invokevirtual length : ()I
    //   193: iload_3
    //   194: if_icmple -> 351
    //   197: aload_0
    //   198: iload_3
    //   199: invokevirtual charAt : (I)C
    //   202: istore #12
    //   204: iload #12
    //   206: bipush #90
    //   208: if_icmpeq -> 351
    //   211: iload #12
    //   213: bipush #43
    //   215: if_icmpeq -> 351
    //   218: iload #12
    //   220: bipush #45
    //   222: if_icmpeq -> 351
    //   225: aload_0
    //   226: iload_3
    //   227: iinc #3, 2
    //   230: iload_3
    //   231: invokestatic parseInt : (Ljava/lang/String;II)I
    //   234: istore #9
    //   236: iload #9
    //   238: bipush #59
    //   240: if_icmple -> 254
    //   243: iload #9
    //   245: bipush #63
    //   247: if_icmpge -> 254
    //   250: bipush #59
    //   252: istore #9
    //   254: aload_0
    //   255: iload_3
    //   256: bipush #46
    //   258: invokestatic checkOffset : (Ljava/lang/String;IC)Z
    //   261: ifeq -> 351
    //   264: iinc #3, 1
    //   267: aload_0
    //   268: iload_3
    //   269: iconst_1
    //   270: iadd
    //   271: invokestatic indexOfNonDigit : (Ljava/lang/String;I)I
    //   274: istore #13
    //   276: iload #13
    //   278: iload_3
    //   279: iconst_3
    //   280: iadd
    //   281: invokestatic min : (II)I
    //   284: istore #14
    //   286: aload_0
    //   287: iload_3
    //   288: iload #14
    //   290: invokestatic parseInt : (Ljava/lang/String;II)I
    //   293: istore #15
    //   295: iload #14
    //   297: iload_3
    //   298: isub
    //   299: lookupswitch default -> 344, 1 -> 334, 2 -> 324
    //   324: iload #15
    //   326: bipush #10
    //   328: imul
    //   329: istore #10
    //   331: goto -> 348
    //   334: iload #15
    //   336: bipush #100
    //   338: imul
    //   339: istore #10
    //   341: goto -> 348
    //   344: iload #15
    //   346: istore #10
    //   348: iload #13
    //   350: istore_3
    //   351: aload_0
    //   352: invokevirtual length : ()I
    //   355: iload_3
    //   356: if_icmpgt -> 369
    //   359: new java/lang/IllegalArgumentException
    //   362: dup
    //   363: ldc 'No time zone indicator'
    //   365: invokespecial <init> : (Ljava/lang/String;)V
    //   368: athrow
    //   369: aconst_null
    //   370: astore #12
    //   372: aload_0
    //   373: iload_3
    //   374: invokevirtual charAt : (I)C
    //   377: istore #13
    //   379: iload #13
    //   381: bipush #90
    //   383: if_icmpne -> 397
    //   386: getstatic com/fasterxml/jackson/databind/util/ISO8601Utils.TIMEZONE_Z : Ljava/util/TimeZone;
    //   389: astore #12
    //   391: iinc #3, 1
    //   394: goto -> 598
    //   397: iload #13
    //   399: bipush #43
    //   401: if_icmpeq -> 411
    //   404: iload #13
    //   406: bipush #45
    //   408: if_icmpne -> 565
    //   411: aload_0
    //   412: iload_3
    //   413: invokevirtual substring : (I)Ljava/lang/String;
    //   416: astore #14
    //   418: iload_3
    //   419: aload #14
    //   421: invokevirtual length : ()I
    //   424: iadd
    //   425: istore_3
    //   426: ldc '+0000'
    //   428: aload #14
    //   430: invokevirtual equals : (Ljava/lang/Object;)Z
    //   433: ifne -> 446
    //   436: ldc '+00:00'
    //   438: aload #14
    //   440: invokevirtual equals : (Ljava/lang/Object;)Z
    //   443: ifeq -> 454
    //   446: getstatic com/fasterxml/jackson/databind/util/ISO8601Utils.TIMEZONE_Z : Ljava/util/TimeZone;
    //   449: astore #12
    //   451: goto -> 562
    //   454: new java/lang/StringBuilder
    //   457: dup
    //   458: invokespecial <init> : ()V
    //   461: ldc 'GMT'
    //   463: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   466: aload #14
    //   468: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   471: invokevirtual toString : ()Ljava/lang/String;
    //   474: astore #15
    //   476: aload #15
    //   478: invokestatic getTimeZone : (Ljava/lang/String;)Ljava/util/TimeZone;
    //   481: astore #12
    //   483: aload #12
    //   485: invokevirtual getID : ()Ljava/lang/String;
    //   488: astore #16
    //   490: aload #16
    //   492: aload #15
    //   494: invokevirtual equals : (Ljava/lang/Object;)Z
    //   497: ifne -> 562
    //   500: aload #16
    //   502: ldc ':'
    //   504: ldc ''
    //   506: invokevirtual replace : (Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
    //   509: astore #17
    //   511: aload #17
    //   513: aload #15
    //   515: invokevirtual equals : (Ljava/lang/Object;)Z
    //   518: ifne -> 562
    //   521: new java/lang/IndexOutOfBoundsException
    //   524: dup
    //   525: new java/lang/StringBuilder
    //   528: dup
    //   529: invokespecial <init> : ()V
    //   532: ldc 'Mismatching time zone indicator: '
    //   534: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   537: aload #15
    //   539: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   542: ldc ' given, resolves to '
    //   544: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   547: aload #12
    //   549: invokevirtual getID : ()Ljava/lang/String;
    //   552: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   555: invokevirtual toString : ()Ljava/lang/String;
    //   558: invokespecial <init> : (Ljava/lang/String;)V
    //   561: athrow
    //   562: goto -> 598
    //   565: new java/lang/IndexOutOfBoundsException
    //   568: dup
    //   569: new java/lang/StringBuilder
    //   572: dup
    //   573: invokespecial <init> : ()V
    //   576: ldc 'Invalid time zone indicator ''
    //   578: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   581: iload #13
    //   583: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   586: ldc '''
    //   588: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   591: invokevirtual toString : ()Ljava/lang/String;
    //   594: invokespecial <init> : (Ljava/lang/String;)V
    //   597: athrow
    //   598: new java/util/GregorianCalendar
    //   601: dup
    //   602: aload #12
    //   604: invokespecial <init> : (Ljava/util/TimeZone;)V
    //   607: astore #14
    //   609: aload #14
    //   611: iconst_0
    //   612: invokevirtual setLenient : (Z)V
    //   615: aload #14
    //   617: iconst_1
    //   618: iload #4
    //   620: invokevirtual set : (II)V
    //   623: aload #14
    //   625: iconst_2
    //   626: iload #5
    //   628: iconst_1
    //   629: isub
    //   630: invokevirtual set : (II)V
    //   633: aload #14
    //   635: iconst_5
    //   636: iload #6
    //   638: invokevirtual set : (II)V
    //   641: aload #14
    //   643: bipush #11
    //   645: iload #7
    //   647: invokevirtual set : (II)V
    //   650: aload #14
    //   652: bipush #12
    //   654: iload #8
    //   656: invokevirtual set : (II)V
    //   659: aload #14
    //   661: bipush #13
    //   663: iload #9
    //   665: invokevirtual set : (II)V
    //   668: aload #14
    //   670: bipush #14
    //   672: iload #10
    //   674: invokevirtual set : (II)V
    //   677: aload_1
    //   678: iload_3
    //   679: invokevirtual setIndex : (I)V
    //   682: aload #14
    //   684: invokevirtual getTime : ()Ljava/util/Date;
    //   687: areturn
    //   688: astore_3
    //   689: aload_3
    //   690: astore_2
    //   691: aload_0
    //   692: ifnonnull -> 699
    //   695: aconst_null
    //   696: goto -> 723
    //   699: new java/lang/StringBuilder
    //   702: dup
    //   703: invokespecial <init> : ()V
    //   706: bipush #34
    //   708: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   711: aload_0
    //   712: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   715: bipush #34
    //   717: invokevirtual append : (C)Ljava/lang/StringBuilder;
    //   720: invokevirtual toString : ()Ljava/lang/String;
    //   723: astore_3
    //   724: aload_2
    //   725: invokevirtual getMessage : ()Ljava/lang/String;
    //   728: astore #4
    //   730: aload #4
    //   732: ifnull -> 743
    //   735: aload #4
    //   737: invokevirtual isEmpty : ()Z
    //   740: ifeq -> 775
    //   743: new java/lang/StringBuilder
    //   746: dup
    //   747: invokespecial <init> : ()V
    //   750: ldc '('
    //   752: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   755: aload_2
    //   756: invokevirtual getClass : ()Ljava/lang/Class;
    //   759: invokevirtual getName : ()Ljava/lang/String;
    //   762: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   765: ldc ')'
    //   767: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   770: invokevirtual toString : ()Ljava/lang/String;
    //   773: astore #4
    //   775: new java/text/ParseException
    //   778: dup
    //   779: new java/lang/StringBuilder
    //   782: dup
    //   783: invokespecial <init> : ()V
    //   786: ldc 'Failed to parse date '
    //   788: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   791: aload_3
    //   792: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   795: ldc ': '
    //   797: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   800: aload #4
    //   802: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   805: invokevirtual toString : ()Ljava/lang/String;
    //   808: aload_1
    //   809: invokevirtual getIndex : ()I
    //   812: invokespecial <init> : (Ljava/lang/String;I)V
    //   815: astore #5
    //   817: aload #5
    //   819: aload_2
    //   820: invokevirtual initCause : (Ljava/lang/Throwable;)Ljava/lang/Throwable;
    //   823: pop
    //   824: aload #5
    //   826: athrow
    // Line number table:
    //   Java source line number -> byte code offset
    //   #116	-> 0
    //   #117	-> 5
    //   #119	-> 7
    //   #122	-> 12
    //   #123	-> 23
    //   #124	-> 33
    //   #128	-> 36
    //   #129	-> 47
    //   #130	-> 57
    //   #134	-> 60
    //   #136	-> 71
    //   #137	-> 74
    //   #138	-> 77
    //   #139	-> 80
    //   #142	-> 83
    //   #144	-> 92
    //   #145	-> 105
    //   #147	-> 122
    //   #148	-> 127
    //   #151	-> 133
    //   #154	-> 138
    //   #155	-> 152
    //   #156	-> 162
    //   #159	-> 165
    //   #160	-> 176
    //   #161	-> 186
    //   #164	-> 189
    //   #165	-> 197
    //   #166	-> 204
    //   #167	-> 225
    //   #168	-> 236
    //   #170	-> 254
    //   #171	-> 264
    //   #172	-> 267
    //   #173	-> 276
    //   #174	-> 286
    //   #176	-> 295
    //   #178	-> 324
    //   #179	-> 331
    //   #181	-> 334
    //   #182	-> 341
    //   #184	-> 344
    //   #186	-> 348
    //   #193	-> 351
    //   #194	-> 359
    //   #197	-> 369
    //   #198	-> 372
    //   #200	-> 379
    //   #201	-> 386
    //   #202	-> 391
    //   #203	-> 397
    //   #204	-> 411
    //   #205	-> 418
    //   #207	-> 426
    //   #208	-> 446
    //   #214	-> 454
    //   #217	-> 476
    //   #219	-> 483
    //   #220	-> 490
    //   #226	-> 500
    //   #227	-> 511
    //   #228	-> 521
    //   #229	-> 549
    //   #233	-> 562
    //   #234	-> 565
    //   #237	-> 598
    //   #238	-> 609
    //   #239	-> 615
    //   #240	-> 623
    //   #241	-> 633
    //   #242	-> 641
    //   #243	-> 650
    //   #244	-> 659
    //   #245	-> 668
    //   #247	-> 677
    //   #248	-> 682
    //   #251	-> 688
    //   #252	-> 689
    //   #254	-> 691
    //   #255	-> 724
    //   #256	-> 730
    //   #257	-> 743
    //   #259	-> 775
    //   #260	-> 817
    //   #261	-> 824
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   122	11	12	calendar	Ljava/util/Calendar;
    //   276	75	13	endOffset	I
    //   286	65	14	parseEndOffset	I
    //   295	56	15	fraction	I
    //   204	147	12	c	C
    //   511	51	17	cleaned	Ljava/lang/String;
    //   476	86	15	timezoneId	Ljava/lang/String;
    //   490	72	16	act	Ljava/lang/String;
    //   418	144	14	timezoneOffset	Ljava/lang/String;
    //   12	676	3	offset	I
    //   23	665	4	year	I
    //   47	641	5	month	I
    //   71	617	6	day	I
    //   74	614	7	hour	I
    //   77	611	8	minutes	I
    //   80	608	9	seconds	I
    //   83	605	10	milliseconds	I
    //   92	596	11	hasT	Z
    //   372	316	12	timezone	Ljava/util/TimeZone;
    //   379	309	13	timezoneIndicator	C
    //   609	79	14	calendar	Ljava/util/Calendar;
    //   689	2	3	e	Ljava/lang/Exception;
    //   0	827	0	date	Ljava/lang/String;
    //   0	827	1	pos	Ljava/text/ParsePosition;
    //   7	820	2	fail	Ljava/lang/Exception;
    //   724	103	3	input	Ljava/lang/String;
    //   730	97	4	msg	Ljava/lang/String;
    //   817	10	5	ex	Ljava/text/ParseException;
    // Exception table:
    //   from	to	target	type
    //   7	132	688	java/lang/Exception
    //   133	687	688	java/lang/Exception
  }
  
  private static boolean checkOffset(String value, int offset, char expected) {
    return (offset < value.length() && value.charAt(offset) == expected);
  }
  
  private static int parseInt(String value, int beginIndex, int endIndex) throws NumberFormatException {
    if (beginIndex < 0 || endIndex > value.length() || beginIndex > endIndex)
      throw new NumberFormatException(value); 
    int i = beginIndex;
    int result = 0;
    if (i < endIndex) {
      int digit = Character.digit(value.charAt(i++), 10);
      if (digit < 0)
        throw new NumberFormatException("Invalid number: " + value.substring(beginIndex, endIndex)); 
      result = -digit;
    } 
    while (i < endIndex) {
      int digit = Character.digit(value.charAt(i++), 10);
      if (digit < 0)
        throw new NumberFormatException("Invalid number: " + value.substring(beginIndex, endIndex)); 
      result *= 10;
      result -= digit;
    } 
    return -result;
  }
  
  private static int indexOfNonDigit(String string, int offset) {
    for (int i = offset; i < string.length(); i++) {
      char c = string.charAt(i);
      if (c < '0' || c > '9')
        return i; 
    } 
    return string.length();
  }
}
