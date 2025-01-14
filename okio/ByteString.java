package okio;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.util.Arrays;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import okio.internal.-ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000t\n\002\030\002\n\002\030\002\n\002\020\017\n\002\020\022\n\002\b\003\n\002\030\002\n\002\b\002\n\002\020\016\n\002\b\004\n\002\020\b\n\002\b\006\n\002\020\002\n\002\b\007\n\002\020\013\n\002\b\003\n\002\020\000\n\002\b\003\n\002\020\005\n\002\b\"\n\002\030\002\n\002\b\n\n\002\030\002\n\002\b\f\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\016\b\026\030\000 v2\0020\0012\b\022\004\022\0020\0000\002:\001vB\021\b\000\022\006\020\004\032\0020\003¢\006\004\b\005\020\006J\017\020\b\032\0020\007H\026¢\006\004\b\b\020\tJ\017\020\013\032\0020\nH\026¢\006\004\b\013\020\fJ\017\020\r\032\0020\nH\026¢\006\004\b\r\020\fJ\030\020\020\032\0020\0172\006\020\016\032\0020\000H\002¢\006\004\b\020\020\021J3\020\027\032\0020\0262\b\b\002\020\022\032\0020\0172\006\020\023\032\0020\0032\b\b\002\020\024\032\0020\0172\006\020\025\032\0020\017H\026¢\006\004\b\027\020\030J\027\020\034\032\0020\0002\006\020\031\032\0020\nH\020¢\006\004\b\032\020\033J\025\020\037\032\0020\0362\006\020\035\032\0020\003¢\006\004\b\037\020 J\025\020\037\032\0020\0362\006\020\035\032\0020\000¢\006\004\b\037\020!J\032\020#\032\0020\0362\b\020\016\032\004\030\0010\"H\002¢\006\004\b#\020$J\030\020)\032\0020&2\006\020%\032\0020\017H\002¢\006\004\b'\020(J\027\020'\032\0020&2\006\020%\032\0020\017H\007¢\006\004\b*\020(J\017\020-\032\0020\017H\020¢\006\004\b+\020,J\017\020.\032\0020\017H\026¢\006\004\b.\020,J\017\020/\032\0020\nH\026¢\006\004\b/\020\fJ\037\0203\032\0020\0002\006\020\031\032\0020\n2\006\0200\032\0020\000H\020¢\006\004\b1\0202J\027\0204\032\0020\0002\006\0200\032\0020\000H\026¢\006\004\b4\0205J\027\0206\032\0020\0002\006\0200\032\0020\000H\026¢\006\004\b6\0205J\027\0207\032\0020\0002\006\0200\032\0020\000H\026¢\006\004\b7\0205J!\0209\032\0020\0172\006\020\016\032\0020\0032\b\b\002\0208\032\0020\017H\027¢\006\004\b9\020:J!\0209\032\0020\0172\006\020\016\032\0020\0002\b\b\002\0208\032\0020\017H\007¢\006\004\b9\020;J\017\020>\032\0020\003H\020¢\006\004\b<\020=J\027\020A\032\0020&2\006\020?\032\0020\017H\020¢\006\004\b@\020(J!\020B\032\0020\0172\006\020\016\032\0020\0032\b\b\002\0208\032\0020\017H\027¢\006\004\bB\020:J!\020B\032\0020\0172\006\020\016\032\0020\0002\b\b\002\0208\032\0020\017H\007¢\006\004\bB\020;J\r\020C\032\0020\000¢\006\004\bC\020DJ/\020F\032\0020\0362\006\020\022\032\0020\0172\006\020\016\032\0020\0032\006\020E\032\0020\0172\006\020\025\032\0020\017H\026¢\006\004\bF\020GJ/\020F\032\0020\0362\006\020\022\032\0020\0172\006\020\016\032\0020\0002\006\020E\032\0020\0172\006\020\025\032\0020\017H\026¢\006\004\bF\020HJ\027\020K\032\0020\0262\006\020J\032\0020IH\002¢\006\004\bK\020LJ\r\020M\032\0020\000¢\006\004\bM\020DJ\r\020N\032\0020\000¢\006\004\bN\020DJ\r\020O\032\0020\000¢\006\004\bO\020DJ\017\020Q\032\0020\017H\007¢\006\004\bP\020,J\025\020S\032\0020\0362\006\020R\032\0020\003¢\006\004\bS\020 J\025\020S\032\0020\0362\006\020R\032\0020\000¢\006\004\bS\020!J\027\020V\032\0020\n2\006\020U\032\0020TH\026¢\006\004\bV\020WJ#\020Z\032\0020\0002\b\b\002\020X\032\0020\0172\b\b\002\020Y\032\0020\017H\027¢\006\004\bZ\020[J\017\020\\\032\0020\000H\026¢\006\004\b\\\020DJ\017\020]\032\0020\000H\026¢\006\004\b]\020DJ\017\020^\032\0020\003H\026¢\006\004\b^\020=J\017\020_\032\0020\nH\026¢\006\004\b_\020\fJ\017\020`\032\0020\nH\026¢\006\004\b`\020\fJ\027\020c\032\0020\0262\006\020b\032\0020aH\026¢\006\004\bc\020dJ'\020c\032\0020\0262\006\020f\032\0020e2\006\020\022\032\0020\0172\006\020\025\032\0020\017H\020¢\006\004\bg\020hJ\027\020j\032\0020\0262\006\020b\032\0020iH\002¢\006\004\bj\020kR\032\020\004\032\0020\0038\000X\004¢\006\f\n\004\b\004\020l\032\004\bm\020=R\"\020.\032\0020\0178\000@\000X\016¢\006\022\n\004\b.\020n\032\004\bo\020,\"\004\bp\020qR\021\020Q\032\0020\0178G¢\006\006\032\004\bQ\020,R$\020`\032\004\030\0010\n8\000@\000X\016¢\006\022\n\004\b`\020r\032\004\bs\020\f\"\004\bt\020u¨\006w"}, d2 = {"Lokio/ByteString;", "Ljava/io/Serializable;", "", "", "data", "<init>", "([B)V", "Ljava/nio/ByteBuffer;", "asByteBuffer", "()Ljava/nio/ByteBuffer;", "", "base64", "()Ljava/lang/String;", "base64Url", "other", "", "compareTo", "(Lokio/ByteString;)I", "offset", "target", "targetOffset", "byteCount", "", "copyInto", "(I[BII)V", "algorithm", "digest$okio", "(Ljava/lang/String;)Lokio/ByteString;", "digest", "suffix", "", "endsWith", "([B)Z", "(Lokio/ByteString;)Z", "", "equals", "(Ljava/lang/Object;)Z", "index", "", "getByte", "(I)B", "get", "-deprecated_getByte", "getSize$okio", "()I", "getSize", "hashCode", "hex", "key", "hmac$okio", "(Ljava/lang/String;Lokio/ByteString;)Lokio/ByteString;", "hmac", "hmacSha1", "(Lokio/ByteString;)Lokio/ByteString;", "hmacSha256", "hmacSha512", "fromIndex", "indexOf", "([BI)I", "(Lokio/ByteString;I)I", "internalArray$okio", "()[B", "internalArray", "pos", "internalGet$okio", "internalGet", "lastIndexOf", "md5", "()Lokio/ByteString;", "otherOffset", "rangeEquals", "(I[BII)Z", "(ILokio/ByteString;II)Z", "Ljava/io/ObjectInputStream;", "in", "readObject", "(Ljava/io/ObjectInputStream;)V", "sha1", "sha256", "sha512", "-deprecated_size", "size", "prefix", "startsWith", "Ljava/nio/charset/Charset;", "charset", "string", "(Ljava/nio/charset/Charset;)Ljava/lang/String;", "beginIndex", "endIndex", "substring", "(II)Lokio/ByteString;", "toAsciiLowercase", "toAsciiUppercase", "toByteArray", "toString", "utf8", "Ljava/io/OutputStream;", "out", "write", "(Ljava/io/OutputStream;)V", "Lokio/Buffer;", "buffer", "write$okio", "(Lokio/Buffer;II)V", "Ljava/io/ObjectOutputStream;", "writeObject", "(Ljava/io/ObjectOutputStream;)V", "[B", "getData$okio", "I", "getHashCode$okio", "setHashCode$okio", "(I)V", "Ljava/lang/String;", "getUtf8$okio", "setUtf8$okio", "(Ljava/lang/String;)V", "Companion", "okio"})
@SourceDebugExtension({"SMAP\nByteString.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ByteString.kt\nokio/ByteString\n+ 2 ByteString.kt\nokio/internal/-ByteString\n+ 3 Util.kt\nokio/-SegmentedByteString\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,364:1\n43#2,7:365\n53#2:372\n56#2:373\n64#2,4:374\n68#2:379\n70#2:381\n76#2,23:382\n104#2,23:405\n131#2,2:428\n133#2,9:431\n145#2:440\n148#2:441\n151#2:442\n154#2:443\n162#2:444\n172#2,3:445\n171#2:448\n185#2,2:449\n190#2:451\n194#2:452\n198#2:453\n202#2:454\n206#2,7:455\n219#2:462\n223#2,8:463\n235#2,4:471\n244#2,5:475\n253#2,6:480\n259#2,9:487\n322#2,8:496\n131#2,2:504\n133#2,9:507\n333#2,9:516\n68#3:378\n74#3:380\n74#3:486\n1#4:430\n1#4:506\n*S KotlinDebug\n*F\n+ 1 ByteString.kt\nokio/ByteString\n*L\n66#1:365,7\n71#1:372\n108#1:373\n110#1:374,4\n110#1:379\n110#1:381\n112#1:382,23\n114#1:405,23\n118#1:428,2\n118#1:431,9\n120#1:440\n129#1:441\n131#1:442\n133#1:443\n152#1:444\n159#1:445,3\n159#1:448\n166#1:449,2\n168#1:451\n170#1:452\n172#1:453\n174#1:454\n180#1:455,7\n183#1:462\n186#1:463,8\n188#1:471,4\n190#1:475,5\n192#1:480,6\n192#1:487,9\n194#1:496,8\n194#1:504,2\n194#1:507,9\n194#1:516,9\n110#1:378\n110#1:380\n192#1:486\n118#1:430\n194#1:506\n*E\n"})
public class ByteString implements Serializable, Comparable<ByteString> {
  public ByteString(@NotNull byte[] data) {
    this.data = data;
  }
  
  @NotNull
  public final byte[] getData$okio() {
    return this.data;
  }
  
  public final int getHashCode$okio() {
    return this.hashCode;
  }
  
  public final void setHashCode$okio(int <set-?>) {
    this.hashCode = <set-?>;
  }
  
  @Nullable
  public final String getUtf8$okio() {
    return this.utf8;
  }
  
  public final void setUtf8$okio(@Nullable String <set-?>) {
    this.utf8 = <set-?>;
  }
  
  @NotNull
  public String utf8() {
    ByteString $this$commonUtf8$iv = this;
    int $i$f$commonUtf8 = 0;
    String result$iv = $this$commonUtf8$iv.getUtf8$okio();
    if (result$iv == null) {
      result$iv = _JvmPlatformKt.toUtf8String($this$commonUtf8$iv.internalArray$okio());
      $this$commonUtf8$iv.setUtf8$okio(result$iv);
    } 
    return result$iv;
  }
  
  @NotNull
  public String string(@NotNull Charset charset) {
    Intrinsics.checkNotNullParameter(charset, "charset");
    return new String(this.data, charset);
  }
  
  @NotNull
  public String base64() {
    ByteString $this$commonBase64$iv = this;
    int $i$f$commonBase64 = 0;
    return -Base64.encodeBase64$default($this$commonBase64$iv.getData$okio(), null, 1, null);
  }
  
  @NotNull
  public final ByteString md5() {
    return digest$okio("MD5");
  }
  
  @NotNull
  public final ByteString sha1() {
    return digest$okio("SHA-1");
  }
  
  @NotNull
  public final ByteString sha256() {
    return digest$okio("SHA-256");
  }
  
  @NotNull
  public final ByteString sha512() {
    return digest$okio("SHA-512");
  }
  
  @NotNull
  public ByteString digest$okio(@NotNull String algorithm) {
    Intrinsics.checkNotNullParameter(algorithm, "algorithm");
    MessageDigest $this$digest_u24lambda_u240 = MessageDigest.getInstance(algorithm);
    int $i$a$-run-ByteString$digest$digestBytes$1 = 0;
    $this$digest_u24lambda_u240.update(this.data, 0, size());
    byte[] digestBytes = $this$digest_u24lambda_u240.digest();
    Intrinsics.checkNotNull(digestBytes);
    return new ByteString(digestBytes);
  }
  
  @NotNull
  public ByteString hmacSha1(@NotNull ByteString key) {
    Intrinsics.checkNotNullParameter(key, "key");
    return hmac$okio("HmacSHA1", key);
  }
  
  @NotNull
  public ByteString hmacSha256(@NotNull ByteString key) {
    Intrinsics.checkNotNullParameter(key, "key");
    return hmac$okio("HmacSHA256", key);
  }
  
  @NotNull
  public ByteString hmacSha512(@NotNull ByteString key) {
    Intrinsics.checkNotNullParameter(key, "key");
    return hmac$okio("HmacSHA512", key);
  }
  
  @NotNull
  public ByteString hmac$okio(@NotNull String algorithm, @NotNull ByteString key) {
    Intrinsics.checkNotNullParameter(algorithm, "algorithm");
    Intrinsics.checkNotNullParameter(key, "key");
    try {
      Mac mac = Mac.getInstance(algorithm);
      mac.init(new SecretKeySpec(key.toByteArray(), algorithm));
      Intrinsics.checkNotNullExpressionValue(mac.doFinal(this.data), "doFinal(...)");
      return new ByteString(mac.doFinal(this.data));
    } catch (InvalidKeyException e) {
      throw new IllegalArgumentException((Throwable)e);
    } 
  }
  
  @NotNull
  public String base64Url() {
    ByteString $this$commonBase64Url$iv = this;
    int $i$f$commonBase64Url = 0;
    return -Base64.encodeBase64($this$commonBase64Url$iv.getData$okio(), -Base64.getBASE64_URL_SAFE());
  }
  
  @NotNull
  public String hex() {
    ByteString $this$commonHex$iv = this;
    int $i$f$commonHex = 0;
    char[] result$iv = new char[($this$commonHex$iv.getData$okio()).length * 2];
    int c$iv = 0;
    byte[] arrayOfByte;
    byte b;
    int i;
    for (arrayOfByte = $this$commonHex$iv.getData$okio(), b = 0, i = arrayOfByte.length; b < i; ) {
      byte b$iv = arrayOfByte[b];
      byte b1 = b$iv;
      int other$iv$iv = 4, $i$f$shr = 0;
      result$iv[c$iv++] = -ByteString.getHEX_DIGIT_CHARS()[
          b1 >> other$iv$iv & 0xF];
      byte $this$shr$iv$iv = b$iv;
      other$iv$iv = 15;
      int $i$f$and = 0;
      result$iv[c$iv++] = -ByteString.getHEX_DIGIT_CHARS()[
          $this$shr$iv$iv & other$iv$iv];
      b++;
    } 
    return StringsKt.concatToString(result$iv);
  }
  
  @NotNull
  public ByteString toAsciiLowercase() {
    // Byte code:
    //   0: aload_0
    //   1: astore_1
    //   2: iconst_0
    //   3: istore_2
    //   4: iconst_0
    //   5: istore_3
    //   6: iload_3
    //   7: aload_1
    //   8: invokevirtual getData$okio : ()[B
    //   11: arraylength
    //   12: if_icmpge -> 135
    //   15: aload_1
    //   16: invokevirtual getData$okio : ()[B
    //   19: iload_3
    //   20: baload
    //   21: istore #4
    //   23: iload #4
    //   25: bipush #65
    //   27: if_icmplt -> 37
    //   30: iload #4
    //   32: bipush #90
    //   34: if_icmple -> 43
    //   37: iinc #3, 1
    //   40: goto -> 6
    //   43: aload_1
    //   44: invokevirtual getData$okio : ()[B
    //   47: dup
    //   48: arraylength
    //   49: invokestatic copyOf : ([BI)[B
    //   52: dup
    //   53: ldc_w 'copyOf(this, size)'
    //   56: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
    //   59: astore #5
    //   61: aload #5
    //   63: iload_3
    //   64: iinc #3, 1
    //   67: iload #4
    //   69: bipush #-32
    //   71: isub
    //   72: i2b
    //   73: bastore
    //   74: iload_3
    //   75: aload #5
    //   77: arraylength
    //   78: if_icmpge -> 123
    //   81: aload #5
    //   83: iload_3
    //   84: baload
    //   85: istore #4
    //   87: iload #4
    //   89: bipush #65
    //   91: if_icmplt -> 101
    //   94: iload #4
    //   96: bipush #90
    //   98: if_icmple -> 107
    //   101: iinc #3, 1
    //   104: goto -> 74
    //   107: aload #5
    //   109: iload_3
    //   110: iload #4
    //   112: bipush #-32
    //   114: isub
    //   115: i2b
    //   116: bastore
    //   117: iinc #3, 1
    //   120: goto -> 74
    //   123: new okio/ByteString
    //   126: dup
    //   127: aload #5
    //   129: invokespecial <init> : ([B)V
    //   132: goto -> 136
    //   135: aload_1
    //   136: areturn
    // Line number table:
    //   Java source line number -> byte code offset
    //   #112	-> 0
    //   #382	-> 4
    //   #383	-> 6
    //   #384	-> 15
    //   #385	-> 23
    //   #386	-> 37
    //   #387	-> 40
    //   #391	-> 43
    //   #391	-> 59
    //   #392	-> 61
    //   #393	-> 74
    //   #394	-> 81
    //   #395	-> 87
    //   #396	-> 101
    //   #397	-> 104
    //   #399	-> 107
    //   #400	-> 117
    //   #402	-> 123
    //   #404	-> 135
    //   #112	-> 136
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   23	112	4	c$iv	B
    //   61	74	5	lowercase$iv	[B
    //   4	132	2	$i$f$commonToAsciiLowercase	I
    //   6	130	3	i$iv	I
    //   2	134	1	$this$commonToAsciiLowercase$iv	Lokio/ByteString;
    //   0	137	0	this	Lokio/ByteString;
  }
  
  @NotNull
  public ByteString toAsciiUppercase() {
    // Byte code:
    //   0: aload_0
    //   1: astore_1
    //   2: iconst_0
    //   3: istore_2
    //   4: iconst_0
    //   5: istore_3
    //   6: iload_3
    //   7: aload_1
    //   8: invokevirtual getData$okio : ()[B
    //   11: arraylength
    //   12: if_icmpge -> 135
    //   15: aload_1
    //   16: invokevirtual getData$okio : ()[B
    //   19: iload_3
    //   20: baload
    //   21: istore #4
    //   23: iload #4
    //   25: bipush #97
    //   27: if_icmplt -> 37
    //   30: iload #4
    //   32: bipush #122
    //   34: if_icmple -> 43
    //   37: iinc #3, 1
    //   40: goto -> 6
    //   43: aload_1
    //   44: invokevirtual getData$okio : ()[B
    //   47: dup
    //   48: arraylength
    //   49: invokestatic copyOf : ([BI)[B
    //   52: dup
    //   53: ldc_w 'copyOf(this, size)'
    //   56: invokestatic checkNotNullExpressionValue : (Ljava/lang/Object;Ljava/lang/String;)V
    //   59: astore #5
    //   61: aload #5
    //   63: iload_3
    //   64: iinc #3, 1
    //   67: iload #4
    //   69: bipush #32
    //   71: isub
    //   72: i2b
    //   73: bastore
    //   74: iload_3
    //   75: aload #5
    //   77: arraylength
    //   78: if_icmpge -> 123
    //   81: aload #5
    //   83: iload_3
    //   84: baload
    //   85: istore #4
    //   87: iload #4
    //   89: bipush #97
    //   91: if_icmplt -> 101
    //   94: iload #4
    //   96: bipush #122
    //   98: if_icmple -> 107
    //   101: iinc #3, 1
    //   104: goto -> 74
    //   107: aload #5
    //   109: iload_3
    //   110: iload #4
    //   112: bipush #32
    //   114: isub
    //   115: i2b
    //   116: bastore
    //   117: iinc #3, 1
    //   120: goto -> 74
    //   123: new okio/ByteString
    //   126: dup
    //   127: aload #5
    //   129: invokespecial <init> : ([B)V
    //   132: goto -> 136
    //   135: aload_1
    //   136: areturn
    // Line number table:
    //   Java source line number -> byte code offset
    //   #114	-> 0
    //   #405	-> 4
    //   #406	-> 6
    //   #407	-> 15
    //   #408	-> 23
    //   #409	-> 37
    //   #410	-> 40
    //   #414	-> 43
    //   #414	-> 59
    //   #415	-> 61
    //   #416	-> 74
    //   #417	-> 81
    //   #418	-> 87
    //   #419	-> 101
    //   #420	-> 104
    //   #422	-> 107
    //   #423	-> 117
    //   #425	-> 123
    //   #427	-> 135
    //   #114	-> 136
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   23	112	4	c$iv	B
    //   61	74	5	lowercase$iv	[B
    //   4	132	2	$i$f$commonToAsciiUppercase	I
    //   6	130	3	i$iv	I
    //   2	134	1	$this$commonToAsciiUppercase$iv	Lokio/ByteString;
    //   0	137	0	this	Lokio/ByteString;
  }
  
  @JvmOverloads
  @NotNull
  public ByteString substring(int beginIndex, int endIndex) {
    ByteString $this$commonSubstring$iv = this;
    int $i$f$commonSubstring = 0;
    int endIndex$iv = -SegmentedByteString.resolveDefaultParameter($this$commonSubstring$iv, endIndex);
    if (!((beginIndex >= 0) ? 1 : 0)) {
      int $i$a$-require--ByteString$commonSubstring$1$iv = 0;
      String str = "beginIndex < 0";
      throw new IllegalArgumentException(str.toString());
    } 
    if (!((endIndex$iv <= ($this$commonSubstring$iv.getData$okio()).length) ? 1 : 0)) {
      int $i$a$-require--ByteString$commonSubstring$2$iv = 0;
      String str = "endIndex > length(" + ($this$commonSubstring$iv.getData$okio()).length + ')';
      throw new IllegalArgumentException(str.toString());
    } 
    int subLen$iv = endIndex$iv - beginIndex;
    if (!((subLen$iv >= 0) ? 1 : 0)) {
      int $i$a$-require--ByteString$commonSubstring$3$iv = 0;
      String str = "endIndex < beginIndex";
      throw new IllegalArgumentException(str.toString());
    } 
    byte[] arrayOfByte = $this$commonSubstring$iv.getData$okio();
    return (beginIndex == 0 && endIndex$iv == ($this$commonSubstring$iv.getData$okio()).length) ? $this$commonSubstring$iv : new ByteString(ArraysKt.copyOfRange(arrayOfByte, beginIndex, endIndex$iv));
  }
  
  public byte internalGet$okio(int pos) {
    ByteString $this$commonGetByte$iv = this;
    int $i$f$commonGetByte = 0;
    return $this$commonGetByte$iv.getData$okio()[pos];
  }
  
  @JvmName(name = "getByte")
  public final byte getByte(int index) {
    return internalGet$okio(index);
  }
  
  @JvmName(name = "size")
  public final int size() {
    return getSize$okio();
  }
  
  public int getSize$okio() {
    ByteString $this$commonGetSize$iv = this;
    int $i$f$commonGetSize = 0;
    return ($this$commonGetSize$iv.getData$okio()).length;
  }
  
  @NotNull
  public byte[] toByteArray() {
    ByteString $this$commonToByteArray$iv = this;
    int $i$f$commonToByteArray = 0;
    Intrinsics.checkNotNullExpressionValue(Arrays.copyOf($this$commonToByteArray$iv.getData$okio(), ($this$commonToByteArray$iv.getData$okio()).length), "copyOf(this, size)");
    return Arrays.copyOf($this$commonToByteArray$iv.getData$okio(), ($this$commonToByteArray$iv.getData$okio()).length);
  }
  
  @NotNull
  public byte[] internalArray$okio() {
    ByteString $this$commonInternalArray$iv = this;
    int $i$f$commonInternalArray = 0;
    return $this$commonInternalArray$iv.getData$okio();
  }
  
  @NotNull
  public ByteBuffer asByteBuffer() {
    Intrinsics.checkNotNullExpressionValue(ByteBuffer.wrap(this.data).asReadOnlyBuffer(), "asReadOnlyBuffer(...)");
    return ByteBuffer.wrap(this.data).asReadOnlyBuffer();
  }
  
  public void write(@NotNull OutputStream out) throws IOException {
    Intrinsics.checkNotNullParameter(out, "out");
    out.write(this.data);
  }
  
  public void write$okio(@NotNull Buffer buffer, int offset, int byteCount) {
    Intrinsics.checkNotNullParameter(buffer, "buffer");
    -ByteString.commonWrite(this, buffer, offset, byteCount);
  }
  
  public boolean rangeEquals(int offset, @NotNull ByteString other, int otherOffset, int byteCount) {
    Intrinsics.checkNotNullParameter(other, "other");
    ByteString $this$commonRangeEquals$iv = this;
    int $i$f$commonRangeEquals = 0;
    return other.rangeEquals(otherOffset, $this$commonRangeEquals$iv.getData$okio(), offset, byteCount);
  }
  
  public boolean rangeEquals(int offset, @NotNull byte[] other, int otherOffset, int byteCount) {
    Intrinsics.checkNotNullParameter(other, "other");
    ByteString $this$commonRangeEquals$iv = this;
    int $i$f$commonRangeEquals = 0;
    return (offset >= 0 && offset <= ($this$commonRangeEquals$iv.getData$okio()).length - byteCount && 
      otherOffset >= 0 && otherOffset <= other.length - byteCount && 
      -SegmentedByteString.arrayRangeEquals($this$commonRangeEquals$iv.getData$okio(), offset, other, otherOffset, byteCount));
  }
  
  public void copyInto(int offset, @NotNull byte[] target, int targetOffset, int byteCount) {
    Intrinsics.checkNotNullParameter(target, "target");
    ByteString $this$commonCopyInto$iv = this;
    int $i$f$commonCopyInto = 0;
    ArraysKt.copyInto($this$commonCopyInto$iv.getData$okio(), target, targetOffset, offset, offset + byteCount);
  }
  
  public final boolean startsWith(@NotNull ByteString prefix) {
    Intrinsics.checkNotNullParameter(prefix, "prefix");
    ByteString $this$commonStartsWith$iv = this;
    int $i$f$commonStartsWith = 0;
    return $this$commonStartsWith$iv.rangeEquals(0, prefix, 0, prefix.size());
  }
  
  public final boolean startsWith(@NotNull byte[] prefix) {
    Intrinsics.checkNotNullParameter(prefix, "prefix");
    ByteString $this$commonStartsWith$iv = this;
    int $i$f$commonStartsWith = 0;
    return $this$commonStartsWith$iv.rangeEquals(0, prefix, 0, prefix.length);
  }
  
  public final boolean endsWith(@NotNull ByteString suffix) {
    Intrinsics.checkNotNullParameter(suffix, "suffix");
    ByteString $this$commonEndsWith$iv = this;
    int $i$f$commonEndsWith = 0;
    return $this$commonEndsWith$iv.rangeEquals($this$commonEndsWith$iv.size() - suffix.size(), suffix, 0, suffix.size());
  }
  
  public final boolean endsWith(@NotNull byte[] suffix) {
    Intrinsics.checkNotNullParameter(suffix, "suffix");
    ByteString $this$commonEndsWith$iv = this;
    int $i$f$commonEndsWith = 0;
    return $this$commonEndsWith$iv.rangeEquals($this$commonEndsWith$iv.size() - suffix.length, suffix, 0, suffix.length);
  }
  
  @JvmOverloads
  public final int indexOf(@NotNull ByteString other, int fromIndex) {
    Intrinsics.checkNotNullParameter(other, "other");
    return indexOf(other.internalArray$okio(), fromIndex);
  }
  
  @JvmOverloads
  public int indexOf(@NotNull byte[] other, int fromIndex) {
    // Byte code:
    //   0: aload_1
    //   1: ldc_w 'other'
    //   4: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
    //   7: aload_0
    //   8: astore_3
    //   9: iconst_0
    //   10: istore #4
    //   12: aload_3
    //   13: invokevirtual getData$okio : ()[B
    //   16: arraylength
    //   17: aload_1
    //   18: arraylength
    //   19: isub
    //   20: istore #5
    //   22: iload_2
    //   23: iconst_0
    //   24: invokestatic max : (II)I
    //   27: istore #6
    //   29: iload #6
    //   31: iload #5
    //   33: if_icmpgt -> 70
    //   36: aload_3
    //   37: invokevirtual getData$okio : ()[B
    //   40: iload #6
    //   42: aload_1
    //   43: iconst_0
    //   44: aload_1
    //   45: arraylength
    //   46: invokestatic arrayRangeEquals : ([BI[BII)Z
    //   49: ifeq -> 57
    //   52: iload #6
    //   54: goto -> 71
    //   57: iload #6
    //   59: iload #5
    //   61: if_icmpeq -> 70
    //   64: iinc #6, 1
    //   67: goto -> 36
    //   70: iconst_m1
    //   71: ireturn
    // Line number table:
    //   Java source line number -> byte code offset
    //   #180	-> 7
    //   #455	-> 12
    //   #456	-> 23
    //   #456	-> 27
    //   #457	-> 36
    //   #458	-> 52
    //   #456	-> 57
    //   #461	-> 70
    //   #180	-> 71
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   29	41	6	i$iv	I
    //   12	59	4	$i$f$commonIndexOf	I
    //   22	49	5	limit$iv	I
    //   9	62	3	$this$commonIndexOf$iv	Lokio/ByteString;
    //   0	72	0	this	Lokio/ByteString;
    //   0	72	1	other	[B
    //   0	72	2	fromIndex	I
  }
  
  @JvmOverloads
  public final int lastIndexOf(@NotNull ByteString other, int fromIndex) {
    Intrinsics.checkNotNullParameter(other, "other");
    ByteString $this$commonLastIndexOf$iv = this;
    int $i$f$commonLastIndexOf = 0;
    return $this$commonLastIndexOf$iv.lastIndexOf(other.internalArray$okio(), fromIndex);
  }
  
  @JvmOverloads
  public int lastIndexOf(@NotNull byte[] other, int fromIndex) {
    // Byte code:
    //   0: aload_1
    //   1: ldc_w 'other'
    //   4: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
    //   7: aload_0
    //   8: astore_3
    //   9: iconst_0
    //   10: istore #4
    //   12: aload_3
    //   13: iload_2
    //   14: invokestatic resolveDefaultParameter : (Lokio/ByteString;I)I
    //   17: istore #5
    //   19: aload_3
    //   20: invokevirtual getData$okio : ()[B
    //   23: arraylength
    //   24: aload_1
    //   25: arraylength
    //   26: isub
    //   27: istore #6
    //   29: iload #5
    //   31: iload #6
    //   33: invokestatic min : (II)I
    //   36: istore #7
    //   38: iconst_m1
    //   39: iload #7
    //   41: if_icmpge -> 71
    //   44: aload_3
    //   45: invokevirtual getData$okio : ()[B
    //   48: iload #7
    //   50: aload_1
    //   51: iconst_0
    //   52: aload_1
    //   53: arraylength
    //   54: invokestatic arrayRangeEquals : ([BI[BII)Z
    //   57: ifeq -> 65
    //   60: iload #7
    //   62: goto -> 72
    //   65: iinc #7, -1
    //   68: goto -> 38
    //   71: iconst_m1
    //   72: ireturn
    // Line number table:
    //   Java source line number -> byte code offset
    //   #186	-> 7
    //   #463	-> 12
    //   #464	-> 19
    //   #465	-> 29
    //   #465	-> 36
    //   #466	-> 44
    //   #467	-> 60
    //   #465	-> 65
    //   #470	-> 71
    //   #186	-> 72
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   38	33	7	i$iv	I
    //   12	60	4	$i$f$commonLastIndexOf	I
    //   19	53	5	fromIndex$iv	I
    //   29	43	6	limit$iv	I
    //   9	63	3	$this$commonLastIndexOf$iv	Lokio/ByteString;
    //   0	73	0	this	Lokio/ByteString;
    //   0	73	1	other	[B
    //   0	73	2	fromIndex	I
  }
  
  public boolean equals(@Nullable Object other) {
    ByteString $this$commonEquals$iv = this;
    int $i$f$commonEquals = 0;
    return (other == $this$commonEquals$iv) ? true : (
      (other instanceof ByteString) ? ((((ByteString)other).size() == ($this$commonEquals$iv.getData$okio()).length && ((ByteString)other).rangeEquals(0, $this$commonEquals$iv.getData$okio(), 0, ($this$commonEquals$iv.getData$okio()).length))) : false);
  }
  
  public int hashCode() {
    ByteString $this$commonHashCode$iv = this;
    int $i$f$commonHashCode = 0;
    int result$iv = $this$commonHashCode$iv.getHashCode$okio();
    int i = Arrays.hashCode($this$commonHashCode$iv.getData$okio()), it$iv = i, $i$a$-also--ByteString$commonHashCode$1$iv = 0;
    $this$commonHashCode$iv.setHashCode$okio(it$iv);
    return (result$iv != 0) ? result$iv : i;
  }
  
  public int compareTo(@NotNull ByteString other) {
    Intrinsics.checkNotNullParameter(other, "other");
    ByteString $this$commonCompareTo$iv = this;
    int $i$f$commonCompareTo = 0;
    int sizeA$iv = $this$commonCompareTo$iv.size();
    int sizeB$iv = other.size();
    int i$iv = 0;
    int size$iv = Math.min(sizeA$iv, sizeB$iv);
    while (i$iv < size$iv) {
      byte b = $this$commonCompareTo$iv.getByte(i$iv);
      int other$iv$iv = 255, $i$f$and = 0, byteA$iv = 
        b & other$iv$iv;
      other$iv$iv = other.getByte(i$iv);
      int i = 255, j = 0, byteB$iv = other$iv$iv & i;
      if (byteA$iv == byteB$iv) {
        i$iv++;
        continue;
      } 
      return (byteA$iv < byteB$iv) ? -1 : 1;
    } 
    return (sizeA$iv == sizeB$iv) ? 0 : (
      (sizeA$iv < sizeB$iv) ? -1 : 1);
  }
  
  @NotNull
  public String toString() {
    ByteString $this$commonToString$iv = this;
    int $i$f$commonToString = 0;
    int i$iv = -ByteString.access$codePointIndexToCharIndex($this$commonToString$iv.getData$okio(), 64);
    ByteString byteString1 = $this$commonToString$iv;
    byte b = 0;
    int endIndex$iv$iv = 64, $i$f$commonSubstring = 0;
    int i = -SegmentedByteString.resolveDefaultParameter(byteString1, endIndex$iv$iv);
    if (!((i <= (byteString1.getData$okio()).length) ? 1 : 0)) {
      int $i$a$-require--ByteString$commonSubstring$2$iv$iv = 0;
      String str = "endIndex > length(" + (byteString1.getData$okio()).length + ')';
      throw new IllegalArgumentException(str.toString());
    } 
    int subLen$iv$iv = i - b;
    if (!((subLen$iv$iv >= 0) ? 1 : 0)) {
      int $i$a$-require--ByteString$commonSubstring$3$iv$iv = 0;
      String str = "endIndex < beginIndex";
      throw new IllegalArgumentException(str.toString());
    } 
    byte[] arrayOfByte = byteString1.getData$okio();
    String text$iv = $this$commonToString$iv.utf8();
    Intrinsics.checkNotNullExpressionValue(text$iv.substring(0, i$iv), "this as java.lang.String…ing(startIndex, endIndex)");
    String safeText$iv = 
      
      StringsKt.replace$default(StringsKt.replace$default(StringsKt.replace$default(text$iv.substring(0, i$iv), "\\", "\\\\", false, 4, null), "\n", "\\n", false, 4, null), "\r", "\\r", false, 4, null);
    return ((($this$commonToString$iv.getData$okio()).length == 0)) ? "[size=0]" : ((i$iv == -1) ? ((($this$commonToString$iv.getData$okio()).length <= 64) ? ("[hex=" + $this$commonToString$iv.hex() + ']') : ("[size=" + ($this$commonToString$iv.getData$okio()).length + " hex=" + ((i == (byteString1.getData$okio()).length) ? byteString1 : new ByteString(ArraysKt.copyOfRange(arrayOfByte, b, i))).hex() + "…]")) : ((i$iv < text$iv.length()) ? (
      "[size=" + ($this$commonToString$iv.getData$okio()).length + " text=" + safeText$iv + "…]") : (
      
      "[text=" + safeText$iv + ']')));
  }
  
  private final void readObject(ObjectInputStream in) throws IOException {
    int dataLength = in.readInt();
    ByteString byteString = Companion.read(in, dataLength);
    Field field = ByteString.class.getDeclaredField("data");
    field.setAccessible(true);
    field.set(this, byteString.data);
  }
  
  private final void writeObject(ObjectOutputStream out) throws IOException {
    out.writeInt(this.data.length);
    out.write(this.data);
  }
  
  @Deprecated(message = "moved to operator function", replaceWith = @ReplaceWith(expression = "this[index]", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_getByte")
  public final byte -deprecated_getByte(int index) {
    return getByte(index);
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "size", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_size")
  public final int -deprecated_size() {
    return size();
  }
  
  @Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000N\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\016\n\000\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\006\n\002\030\002\n\002\b\004\n\002\020\022\n\002\020\005\n\002\b\003\n\002\020\b\n\002\b\003\n\002\030\002\n\002\b\t\n\002\020\t\n\002\b\003\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J\031\020\t\032\004\030\0010\0062\006\020\005\032\0020\004H\007¢\006\004\b\007\020\bJ\027\020\013\032\0020\0062\006\020\005\032\0020\004H\007¢\006\004\b\n\020\bJ\037\020\020\032\0020\0062\006\020\005\032\0020\0042\006\020\r\032\0020\fH\007¢\006\004\b\016\020\017J\027\020\022\032\0020\0062\006\020\005\032\0020\004H\007¢\006\004\b\021\020\bJ\027\020\027\032\0020\0062\006\020\024\032\0020\023H\007¢\006\004\b\025\020\026J\033\020\027\032\0020\0062\n\020\032\032\0020\030\"\0020\031H\007¢\006\004\b\027\020\033J'\020\027\032\0020\0062\006\020\034\032\0020\0302\006\020\036\032\0020\0352\006\020\037\032\0020\035H\007¢\006\004\b\025\020 J\037\020%\032\0020\0062\006\020\"\032\0020!2\006\020\037\032\0020\035H\007¢\006\004\b#\020$J\025\020\t\032\004\030\0010\006*\0020\004H\007¢\006\004\b\t\020\bJ\023\020\013\032\0020\006*\0020\004H\007¢\006\004\b\013\020\bJ\035\020&\032\0020\006*\0020\0042\b\b\002\020\r\032\0020\fH\007¢\006\004\b\020\020\017J\023\020\022\032\0020\006*\0020\004H\007¢\006\004\b\022\020\bJ\033\020'\032\0020\006*\0020!2\006\020\037\032\0020\035H\007¢\006\004\b%\020$J\023\020(\032\0020\006*\0020\023H\007¢\006\004\b\027\020\026J'\020(\032\0020\006*\0020\0302\b\b\002\020\036\032\0020\0352\b\b\002\020\037\032\0020\035H\007¢\006\004\b\027\020 R\024\020)\032\0020\0068\006X\004¢\006\006\n\004\b)\020*R\024\020,\032\0020+8\002XT¢\006\006\n\004\b,\020-¨\006."}, d2 = {"Lokio/ByteString$Companion;", "", "<init>", "()V", "", "string", "Lokio/ByteString;", "-deprecated_decodeBase64", "(Ljava/lang/String;)Lokio/ByteString;", "decodeBase64", "-deprecated_decodeHex", "decodeHex", "Ljava/nio/charset/Charset;", "charset", "-deprecated_encodeString", "(Ljava/lang/String;Ljava/nio/charset/Charset;)Lokio/ByteString;", "encodeString", "-deprecated_encodeUtf8", "encodeUtf8", "Ljava/nio/ByteBuffer;", "buffer", "-deprecated_of", "(Ljava/nio/ByteBuffer;)Lokio/ByteString;", "of", "", "", "data", "([B)Lokio/ByteString;", "array", "", "offset", "byteCount", "([BII)Lokio/ByteString;", "Ljava/io/InputStream;", "inputstream", "-deprecated_read", "(Ljava/io/InputStream;I)Lokio/ByteString;", "read", "encode", "readByteString", "toByteString", "EMPTY", "Lokio/ByteString;", "", "serialVersionUID", "J", "okio"})
  @SourceDebugExtension({"SMAP\nByteString.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ByteString.kt\nokio/ByteString$Companion\n+ 2 ByteString.kt\nokio/internal/-ByteString\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,364:1\n271#2:365\n275#2,3:366\n282#2,3:369\n289#2,2:372\n295#2:374\n297#2,7:376\n1#3:375\n1#3:383\n*S KotlinDebug\n*F\n+ 1 ByteString.kt\nokio/ByteString$Companion\n*L\n234#1:365\n239#1:366,3\n251#1:369,3\n259#1:372,2\n262#1:374\n262#1:376,7\n262#1:375\n*E\n"})
  public static final class Companion {
    private Companion() {}
    
    @JvmStatic
    @NotNull
    public final ByteString of(@NotNull byte... data) {
      Intrinsics.checkNotNullParameter(data, "data");
      int $i$f$commonOf = 0;
      Intrinsics.checkNotNullExpressionValue(Arrays.copyOf(data, data.length), "copyOf(this, size)");
      return new ByteString(Arrays.copyOf(data, data.length));
    }
    
    @JvmStatic
    @JvmName(name = "of")
    @NotNull
    public final ByteString of(@NotNull byte[] $this$toByteString, int offset, int byteCount) {
      Intrinsics.checkNotNullParameter($this$toByteString, "<this>");
      byte[] $this$commonToByteString$iv = $this$toByteString;
      int $i$f$commonToByteString = 0;
      int byteCount$iv = -SegmentedByteString.resolveDefaultParameter($this$commonToByteString$iv, byteCount);
      -SegmentedByteString.checkOffsetAndCount($this$commonToByteString$iv.length, offset, byteCount$iv);
      byte[] arrayOfByte1 = $this$commonToByteString$iv;
      int i = offset + byteCount$iv;
      return new ByteString(ArraysKt.copyOfRange(arrayOfByte1, offset, i));
    }
    
    @JvmStatic
    @JvmName(name = "of")
    @NotNull
    public final ByteString of(@NotNull ByteBuffer $this$toByteString) {
      Intrinsics.checkNotNullParameter($this$toByteString, "<this>");
      byte[] copy = new byte[$this$toByteString.remaining()];
      $this$toByteString.get(copy);
      return new ByteString(copy);
    }
    
    @JvmStatic
    @NotNull
    public final ByteString encodeUtf8(@NotNull String $this$encodeUtf8) {
      Intrinsics.checkNotNullParameter($this$encodeUtf8, "<this>");
      String $this$commonEncodeUtf8$iv = $this$encodeUtf8;
      int $i$f$commonEncodeUtf8 = 0;
      ByteString byteString$iv = new ByteString(_JvmPlatformKt.asUtf8ToByteArray($this$commonEncodeUtf8$iv));
      byteString$iv.setUtf8$okio($this$commonEncodeUtf8$iv);
      return byteString$iv;
    }
    
    @JvmStatic
    @JvmName(name = "encodeString")
    @NotNull
    public final ByteString encodeString(@NotNull String $this$encode, @NotNull Charset charset) {
      Intrinsics.checkNotNullParameter($this$encode, "<this>");
      Intrinsics.checkNotNullParameter(charset, "charset");
      Intrinsics.checkNotNullExpressionValue($this$encode.getBytes(charset), "this as java.lang.String).getBytes(charset)");
      return new ByteString($this$encode.getBytes(charset));
    }
    
    @JvmStatic
    @Nullable
    public final ByteString decodeBase64(@NotNull String $this$decodeBase64) {
      Intrinsics.checkNotNullParameter($this$decodeBase64, "<this>");
      String $this$commonDecodeBase64$iv = $this$decodeBase64;
      int $i$f$commonDecodeBase64 = 0;
      byte[] decoded$iv = -Base64.decodeBase64ToArray($this$commonDecodeBase64$iv);
      return (decoded$iv != null) ? new ByteString(decoded$iv) : null;
    }
    
    @JvmStatic
    @NotNull
    public final ByteString decodeHex(@NotNull String $this$decodeHex) {
      Intrinsics.checkNotNullParameter($this$decodeHex, "<this>");
      String $this$commonDecodeHex$iv = $this$decodeHex;
      int $i$f$commonDecodeHex = 0;
      if (!(($this$commonDecodeHex$iv.length() % 2 == 0) ? 1 : 0)) {
        int $i$a$-require--ByteString$commonDecodeHex$1$iv = 0;
        String str = "Unexpected hex string: " + $this$commonDecodeHex$iv;
        throw new IllegalArgumentException(str.toString());
      } 
      byte[] result$iv = new byte[$this$commonDecodeHex$iv.length() / 2];
      for (int i$iv = 0, i = result$iv.length; i$iv < i; i$iv++) {
        int d1$iv = -ByteString.access$decodeHexDigit($this$commonDecodeHex$iv.charAt(i$iv * 2)) << 4;
        int d2$iv = -ByteString.access$decodeHexDigit($this$commonDecodeHex$iv.charAt(i$iv * 2 + 1));
        result$iv[i$iv] = (byte)(d1$iv + d2$iv);
      } 
      return new ByteString(result$iv);
    }
    
    @JvmStatic
    @JvmName(name = "read")
    @NotNull
    public final ByteString read(@NotNull InputStream $this$readByteString, int byteCount) throws IOException {
      Intrinsics.checkNotNullParameter($this$readByteString, "<this>");
      if (!((byteCount >= 0) ? 1 : 0)) {
        int $i$a$-require-ByteString$Companion$readByteString$1 = 0;
        String str = "byteCount < 0: " + byteCount;
        throw new IllegalArgumentException(str.toString());
      } 
      byte[] result = new byte[byteCount];
      int offset = 0, read = 0;
      while (offset < byteCount) {
        read = $this$readByteString.read(result, offset, byteCount - offset);
        if (read == -1)
          throw new EOFException(); 
        offset += read;
      } 
      return new ByteString(result);
    }
    
    @Deprecated(message = "moved to extension function", replaceWith = @ReplaceWith(expression = "string.decodeBase64()", imports = {"okio.ByteString.Companion.decodeBase64"}), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_decodeBase64")
    @Nullable
    public final ByteString -deprecated_decodeBase64(@NotNull String string) {
      Intrinsics.checkNotNullParameter(string, "string");
      return decodeBase64(string);
    }
    
    @Deprecated(message = "moved to extension function", replaceWith = @ReplaceWith(expression = "string.decodeHex()", imports = {"okio.ByteString.Companion.decodeHex"}), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_decodeHex")
    @NotNull
    public final ByteString -deprecated_decodeHex(@NotNull String string) {
      Intrinsics.checkNotNullParameter(string, "string");
      return decodeHex(string);
    }
    
    @Deprecated(message = "moved to extension function", replaceWith = @ReplaceWith(expression = "string.encode(charset)", imports = {"okio.ByteString.Companion.encode"}), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_encodeString")
    @NotNull
    public final ByteString -deprecated_encodeString(@NotNull String string, @NotNull Charset charset) {
      Intrinsics.checkNotNullParameter(string, "string");
      Intrinsics.checkNotNullParameter(charset, "charset");
      return encodeString(string, charset);
    }
    
    @Deprecated(message = "moved to extension function", replaceWith = @ReplaceWith(expression = "string.encodeUtf8()", imports = {"okio.ByteString.Companion.encodeUtf8"}), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_encodeUtf8")
    @NotNull
    public final ByteString -deprecated_encodeUtf8(@NotNull String string) {
      Intrinsics.checkNotNullParameter(string, "string");
      return encodeUtf8(string);
    }
    
    @Deprecated(message = "moved to extension function", replaceWith = @ReplaceWith(expression = "buffer.toByteString()", imports = {"okio.ByteString.Companion.toByteString"}), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_of")
    @NotNull
    public final ByteString -deprecated_of(@NotNull ByteBuffer buffer) {
      Intrinsics.checkNotNullParameter(buffer, "buffer");
      return of(buffer);
    }
    
    @Deprecated(message = "moved to extension function", replaceWith = @ReplaceWith(expression = "array.toByteString(offset, byteCount)", imports = {"okio.ByteString.Companion.toByteString"}), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_of")
    @NotNull
    public final ByteString -deprecated_of(@NotNull byte[] array, int offset, int byteCount) {
      Intrinsics.checkNotNullParameter(array, "array");
      return of(array, offset, byteCount);
    }
    
    @Deprecated(message = "moved to extension function", replaceWith = @ReplaceWith(expression = "inputstream.readByteString(byteCount)", imports = {"okio.ByteString.Companion.readByteString"}), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_read")
    @NotNull
    public final ByteString -deprecated_read(@NotNull InputStream inputstream, int byteCount) {
      Intrinsics.checkNotNullParameter(inputstream, "inputstream");
      return read(inputstream, byteCount);
    }
  }
  
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  @NotNull
  private final byte[] data;
  
  private transient int hashCode;
  
  @Nullable
  private transient String utf8;
  
  private static final long serialVersionUID = 1L;
  
  @JvmField
  @NotNull
  public static final ByteString EMPTY = new ByteString(new byte[0]);
  
  @JvmOverloads
  @NotNull
  public final ByteString substring(int beginIndex) {
    return substring$default(this, beginIndex, 0, 2, null);
  }
  
  @JvmOverloads
  @NotNull
  public final ByteString substring() {
    return substring$default(this, 0, 0, 3, null);
  }
  
  @JvmOverloads
  public final int indexOf(@NotNull ByteString other) {
    Intrinsics.checkNotNullParameter(other, "other");
    return indexOf$default(this, other, 0, 2, (Object)null);
  }
  
  @JvmOverloads
  public final int indexOf(@NotNull byte[] other) {
    Intrinsics.checkNotNullParameter(other, "other");
    return indexOf$default(this, other, 0, 2, (Object)null);
  }
  
  @JvmOverloads
  public final int lastIndexOf(@NotNull ByteString other) {
    Intrinsics.checkNotNullParameter(other, "other");
    return lastIndexOf$default(this, other, 0, 2, (Object)null);
  }
  
  @JvmOverloads
  public final int lastIndexOf(@NotNull byte[] other) {
    Intrinsics.checkNotNullParameter(other, "other");
    return lastIndexOf$default(this, other, 0, 2, (Object)null);
  }
  
  @JvmStatic
  @NotNull
  public static final ByteString of(@NotNull byte... data) {
    return Companion.of(data);
  }
  
  @JvmStatic
  @JvmName(name = "of")
  @NotNull
  public static final ByteString of(@NotNull byte[] $this$of, int offset, int byteCount) {
    return Companion.of($this$of, offset, byteCount);
  }
  
  @JvmStatic
  @JvmName(name = "of")
  @NotNull
  public static final ByteString of(@NotNull ByteBuffer $this$of) {
    return Companion.of($this$of);
  }
  
  @JvmStatic
  @NotNull
  public static final ByteString encodeUtf8(@NotNull String $this$encodeUtf8) {
    return Companion.encodeUtf8($this$encodeUtf8);
  }
  
  @JvmStatic
  @JvmName(name = "encodeString")
  @NotNull
  public static final ByteString encodeString(@NotNull String $this$encodeString, @NotNull Charset charset) {
    return Companion.encodeString($this$encodeString, charset);
  }
  
  @JvmStatic
  @Nullable
  public static final ByteString decodeBase64(@NotNull String $this$decodeBase64) {
    return Companion.decodeBase64($this$decodeBase64);
  }
  
  @JvmStatic
  @NotNull
  public static final ByteString decodeHex(@NotNull String $this$decodeHex) {
    return Companion.decodeHex($this$decodeHex);
  }
  
  @JvmStatic
  @JvmName(name = "read")
  @NotNull
  public static final ByteString read(@NotNull InputStream $this$read, int byteCount) throws IOException {
    return Companion.read($this$read, byteCount);
  }
}
