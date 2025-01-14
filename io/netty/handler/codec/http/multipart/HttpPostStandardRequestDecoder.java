package io.netty.handler.codec.http.multipart;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.HttpConstants;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;
import io.netty.util.ByteProcessor;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.StringUtil;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class HttpPostStandardRequestDecoder implements InterfaceHttpPostRequestDecoder {
  private final HttpDataFactory factory;
  
  private final HttpRequest request;
  
  private final Charset charset;
  
  private boolean isLastChunk;
  
  private final List<InterfaceHttpData> bodyListHttpData = new ArrayList<InterfaceHttpData>();
  
  private final Map<String, List<InterfaceHttpData>> bodyMapHttpData = new TreeMap<String, List<InterfaceHttpData>>(CaseIgnoringComparator.INSTANCE);
  
  private ByteBuf undecodedChunk;
  
  private int bodyListHttpDataRank;
  
  private HttpPostRequestDecoder.MultiPartStatus currentStatus = HttpPostRequestDecoder.MultiPartStatus.NOTSTARTED;
  
  private Attribute currentAttribute;
  
  private boolean destroyed;
  
  private int discardThreshold = 10485760;
  
  public HttpPostStandardRequestDecoder(HttpRequest request) {
    this(new DefaultHttpDataFactory(16384L), request, HttpConstants.DEFAULT_CHARSET);
  }
  
  public HttpPostStandardRequestDecoder(HttpDataFactory factory, HttpRequest request) {
    this(factory, request, HttpConstants.DEFAULT_CHARSET);
  }
  
  public HttpPostStandardRequestDecoder(HttpDataFactory factory, HttpRequest request, Charset charset) {
    this.request = (HttpRequest)ObjectUtil.checkNotNull(request, "request");
    this.charset = (Charset)ObjectUtil.checkNotNull(charset, "charset");
    this.factory = (HttpDataFactory)ObjectUtil.checkNotNull(factory, "factory");
    try {
      if (request instanceof HttpContent) {
        offer((HttpContent)request);
      } else {
        parseBody();
      } 
    } catch (Throwable e) {
      destroy();
      PlatformDependent.throwException(e);
    } 
  }
  
  private void checkDestroyed() {
    if (this.destroyed)
      throw new IllegalStateException(HttpPostStandardRequestDecoder.class.getSimpleName() + " was destroyed already"); 
  }
  
  public boolean isMultipart() {
    checkDestroyed();
    return false;
  }
  
  public void setDiscardThreshold(int discardThreshold) {
    this.discardThreshold = ObjectUtil.checkPositiveOrZero(discardThreshold, "discardThreshold");
  }
  
  public int getDiscardThreshold() {
    return this.discardThreshold;
  }
  
  public List<InterfaceHttpData> getBodyHttpDatas() {
    checkDestroyed();
    if (!this.isLastChunk)
      throw new HttpPostRequestDecoder.NotEnoughDataDecoderException(); 
    return this.bodyListHttpData;
  }
  
  public List<InterfaceHttpData> getBodyHttpDatas(String name) {
    checkDestroyed();
    if (!this.isLastChunk)
      throw new HttpPostRequestDecoder.NotEnoughDataDecoderException(); 
    return this.bodyMapHttpData.get(name);
  }
  
  public InterfaceHttpData getBodyHttpData(String name) {
    checkDestroyed();
    if (!this.isLastChunk)
      throw new HttpPostRequestDecoder.NotEnoughDataDecoderException(); 
    List<InterfaceHttpData> list = this.bodyMapHttpData.get(name);
    if (list != null)
      return list.get(0); 
    return null;
  }
  
  public HttpPostStandardRequestDecoder offer(HttpContent content) {
    checkDestroyed();
    if (content instanceof io.netty.handler.codec.http.LastHttpContent)
      this.isLastChunk = true; 
    ByteBuf buf = content.content();
    if (this.undecodedChunk == null) {
      this
        
        .undecodedChunk = buf.alloc().buffer(buf.readableBytes()).writeBytes(buf);
    } else {
      this.undecodedChunk.writeBytes(buf);
    } 
    parseBody();
    if (this.undecodedChunk != null && this.undecodedChunk.writerIndex() > this.discardThreshold)
      if (this.undecodedChunk.refCnt() == 1) {
        this.undecodedChunk.discardReadBytes();
      } else {
        ByteBuf buffer = this.undecodedChunk.alloc().buffer(this.undecodedChunk.readableBytes());
        buffer.writeBytes(this.undecodedChunk);
        this.undecodedChunk.release();
        this.undecodedChunk = buffer;
      }  
    return this;
  }
  
  public boolean hasNext() {
    checkDestroyed();
    if (this.currentStatus == HttpPostRequestDecoder.MultiPartStatus.EPILOGUE)
      if (this.bodyListHttpDataRank >= this.bodyListHttpData.size())
        throw new HttpPostRequestDecoder.EndOfDataDecoderException();  
    return (!this.bodyListHttpData.isEmpty() && this.bodyListHttpDataRank < this.bodyListHttpData.size());
  }
  
  public InterfaceHttpData next() {
    checkDestroyed();
    if (hasNext())
      return this.bodyListHttpData.get(this.bodyListHttpDataRank++); 
    return null;
  }
  
  public InterfaceHttpData currentPartialHttpData() {
    return this.currentAttribute;
  }
  
  private void parseBody() {
    if (this.currentStatus == HttpPostRequestDecoder.MultiPartStatus.PREEPILOGUE || this.currentStatus == HttpPostRequestDecoder.MultiPartStatus.EPILOGUE) {
      if (this.isLastChunk)
        this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.EPILOGUE; 
      return;
    } 
    parseBodyAttributes();
  }
  
  protected void addHttpData(InterfaceHttpData data) {
    if (data == null)
      return; 
    List<InterfaceHttpData> datas = this.bodyMapHttpData.get(data.getName());
    if (datas == null) {
      datas = new ArrayList<InterfaceHttpData>(1);
      this.bodyMapHttpData.put(data.getName(), datas);
    } 
    datas.add(data);
    this.bodyListHttpData.add(data);
  }
  
  private void parseBodyAttributesStandard() {
    int firstpos = this.undecodedChunk.readerIndex();
    int currentpos = firstpos;
    if (this.currentStatus == HttpPostRequestDecoder.MultiPartStatus.NOTSTARTED)
      this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.DISPOSITION; 
    boolean contRead = true;
    try {
      while (this.undecodedChunk.isReadable() && contRead) {
        char read = (char)this.undecodedChunk.readUnsignedByte();
        currentpos++;
        switch (this.currentStatus) {
          case DISPOSITION:
            if (read == '=') {
              this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.FIELD;
              int equalpos = currentpos - 1;
              String key = decodeAttribute(this.undecodedChunk.toString(firstpos, equalpos - firstpos, this.charset), this.charset);
              this.currentAttribute = this.factory.createAttribute(this.request, key);
              firstpos = currentpos;
              continue;
            } 
            if (read == '&') {
              this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.DISPOSITION;
              int ampersandpos = currentpos - 1;
              String key = decodeAttribute(this.undecodedChunk
                  .toString(firstpos, ampersandpos - firstpos, this.charset), this.charset);
              if (!key.isEmpty()) {
                this.currentAttribute = this.factory.createAttribute(this.request, key);
                this.currentAttribute.setValue("");
                addHttpData(this.currentAttribute);
              } 
              this.currentAttribute = null;
              firstpos = currentpos;
              contRead = true;
            } 
            continue;
          case FIELD:
            if (read == '&') {
              this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.DISPOSITION;
              int ampersandpos = currentpos - 1;
              setFinalBuffer(this.undecodedChunk.retainedSlice(firstpos, ampersandpos - firstpos));
              firstpos = currentpos;
              contRead = true;
              continue;
            } 
            if (read == '\r') {
              if (this.undecodedChunk.isReadable()) {
                read = (char)this.undecodedChunk.readUnsignedByte();
                currentpos++;
                if (read == '\n') {
                  this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.PREEPILOGUE;
                  int ampersandpos = currentpos - 2;
                  setFinalBuffer(this.undecodedChunk.retainedSlice(firstpos, ampersandpos - firstpos));
                  firstpos = currentpos;
                  contRead = false;
                  continue;
                } 
                throw new HttpPostRequestDecoder.ErrorDataDecoderException("Bad end of line");
              } 
              currentpos--;
              continue;
            } 
            if (read == '\n') {
              this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.PREEPILOGUE;
              int ampersandpos = currentpos - 1;
              setFinalBuffer(this.undecodedChunk.retainedSlice(firstpos, ampersandpos - firstpos));
              firstpos = currentpos;
              contRead = false;
            } 
            continue;
        } 
        contRead = false;
      } 
      if (this.isLastChunk && this.currentAttribute != null) {
        int ampersandpos = currentpos;
        if (ampersandpos > firstpos) {
          setFinalBuffer(this.undecodedChunk.retainedSlice(firstpos, ampersandpos - firstpos));
        } else if (!this.currentAttribute.isCompleted()) {
          setFinalBuffer(Unpooled.EMPTY_BUFFER);
        } 
        firstpos = currentpos;
        this.currentStatus = HttpPostRequestDecoder.MultiPartStatus.EPILOGUE;
      } else if (contRead && this.currentAttribute != null && this.currentStatus == HttpPostRequestDecoder.MultiPartStatus.FIELD) {
        this.currentAttribute.addContent(this.undecodedChunk.retainedSlice(firstpos, currentpos - firstpos), false);
        firstpos = currentpos;
      } 
      this.undecodedChunk.readerIndex(firstpos);
    } catch (ErrorDataDecoderException e) {
      this.undecodedChunk.readerIndex(firstpos);
      throw e;
    } catch (IOException e) {
      this.undecodedChunk.readerIndex(firstpos);
      throw new HttpPostRequestDecoder.ErrorDataDecoderException(e);
    } catch (IllegalArgumentException e) {
      this.undecodedChunk.readerIndex(firstpos);
      throw new HttpPostRequestDecoder.ErrorDataDecoderException(e);
    } 
  }
  
  private void parseBodyAttributes() {
    // Byte code:
    //   0: aload_0
    //   1: getfield undecodedChunk : Lio/netty/buffer/ByteBuf;
    //   4: ifnonnull -> 8
    //   7: return
    //   8: aload_0
    //   9: getfield undecodedChunk : Lio/netty/buffer/ByteBuf;
    //   12: invokevirtual hasArray : ()Z
    //   15: ifne -> 23
    //   18: aload_0
    //   19: invokespecial parseBodyAttributesStandard : ()V
    //   22: return
    //   23: new io/netty/handler/codec/http/multipart/HttpPostBodyUtil$SeekAheadOptimize
    //   26: dup
    //   27: aload_0
    //   28: getfield undecodedChunk : Lio/netty/buffer/ByteBuf;
    //   31: invokespecial <init> : (Lio/netty/buffer/ByteBuf;)V
    //   34: astore_1
    //   35: aload_0
    //   36: getfield undecodedChunk : Lio/netty/buffer/ByteBuf;
    //   39: invokevirtual readerIndex : ()I
    //   42: istore_2
    //   43: iload_2
    //   44: istore_3
    //   45: aload_0
    //   46: getfield currentStatus : Lio/netty/handler/codec/http/multipart/HttpPostRequestDecoder$MultiPartStatus;
    //   49: getstatic io/netty/handler/codec/http/multipart/HttpPostRequestDecoder$MultiPartStatus.NOTSTARTED : Lio/netty/handler/codec/http/multipart/HttpPostRequestDecoder$MultiPartStatus;
    //   52: if_acmpne -> 62
    //   55: aload_0
    //   56: getstatic io/netty/handler/codec/http/multipart/HttpPostRequestDecoder$MultiPartStatus.DISPOSITION : Lio/netty/handler/codec/http/multipart/HttpPostRequestDecoder$MultiPartStatus;
    //   59: putfield currentStatus : Lio/netty/handler/codec/http/multipart/HttpPostRequestDecoder$MultiPartStatus;
    //   62: iconst_1
    //   63: istore #6
    //   65: aload_1
    //   66: getfield pos : I
    //   69: aload_1
    //   70: getfield limit : I
    //   73: if_icmpge -> 538
    //   76: aload_1
    //   77: getfield bytes : [B
    //   80: aload_1
    //   81: dup
    //   82: getfield pos : I
    //   85: dup_x1
    //   86: iconst_1
    //   87: iadd
    //   88: putfield pos : I
    //   91: baload
    //   92: sipush #255
    //   95: iand
    //   96: i2c
    //   97: istore #7
    //   99: iinc #3, 1
    //   102: getstatic io/netty/handler/codec/http/multipart/HttpPostStandardRequestDecoder$1.$SwitchMap$io$netty$handler$codec$http$multipart$HttpPostRequestDecoder$MultiPartStatus : [I
    //   105: aload_0
    //   106: getfield currentStatus : Lio/netty/handler/codec/http/multipart/HttpPostRequestDecoder$MultiPartStatus;
    //   109: invokevirtual ordinal : ()I
    //   112: iaload
    //   113: lookupswitch default -> 524, 1 -> 140, 2 -> 312
    //   140: iload #7
    //   142: bipush #61
    //   144: if_icmpne -> 208
    //   147: aload_0
    //   148: getstatic io/netty/handler/codec/http/multipart/HttpPostRequestDecoder$MultiPartStatus.FIELD : Lio/netty/handler/codec/http/multipart/HttpPostRequestDecoder$MultiPartStatus;
    //   151: putfield currentStatus : Lio/netty/handler/codec/http/multipart/HttpPostRequestDecoder$MultiPartStatus;
    //   154: iload_3
    //   155: iconst_1
    //   156: isub
    //   157: istore #4
    //   159: aload_0
    //   160: getfield undecodedChunk : Lio/netty/buffer/ByteBuf;
    //   163: iload_2
    //   164: iload #4
    //   166: iload_2
    //   167: isub
    //   168: aload_0
    //   169: getfield charset : Ljava/nio/charset/Charset;
    //   172: invokevirtual toString : (IILjava/nio/charset/Charset;)Ljava/lang/String;
    //   175: aload_0
    //   176: getfield charset : Ljava/nio/charset/Charset;
    //   179: invokestatic decodeAttribute : (Ljava/lang/String;Ljava/nio/charset/Charset;)Ljava/lang/String;
    //   182: astore #8
    //   184: aload_0
    //   185: aload_0
    //   186: getfield factory : Lio/netty/handler/codec/http/multipart/HttpDataFactory;
    //   189: aload_0
    //   190: getfield request : Lio/netty/handler/codec/http/HttpRequest;
    //   193: aload #8
    //   195: invokeinterface createAttribute : (Lio/netty/handler/codec/http/HttpRequest;Ljava/lang/String;)Lio/netty/handler/codec/http/multipart/Attribute;
    //   200: putfield currentAttribute : Lio/netty/handler/codec/http/multipart/Attribute;
    //   203: iload_3
    //   204: istore_2
    //   205: goto -> 535
    //   208: iload #7
    //   210: bipush #38
    //   212: if_icmpne -> 535
    //   215: aload_0
    //   216: getstatic io/netty/handler/codec/http/multipart/HttpPostRequestDecoder$MultiPartStatus.DISPOSITION : Lio/netty/handler/codec/http/multipart/HttpPostRequestDecoder$MultiPartStatus;
    //   219: putfield currentStatus : Lio/netty/handler/codec/http/multipart/HttpPostRequestDecoder$MultiPartStatus;
    //   222: iload_3
    //   223: iconst_1
    //   224: isub
    //   225: istore #5
    //   227: aload_0
    //   228: getfield undecodedChunk : Lio/netty/buffer/ByteBuf;
    //   231: iload_2
    //   232: iload #5
    //   234: iload_2
    //   235: isub
    //   236: aload_0
    //   237: getfield charset : Ljava/nio/charset/Charset;
    //   240: invokevirtual toString : (IILjava/nio/charset/Charset;)Ljava/lang/String;
    //   243: aload_0
    //   244: getfield charset : Ljava/nio/charset/Charset;
    //   247: invokestatic decodeAttribute : (Ljava/lang/String;Ljava/nio/charset/Charset;)Ljava/lang/String;
    //   250: astore #8
    //   252: aload #8
    //   254: invokevirtual isEmpty : ()Z
    //   257: ifne -> 299
    //   260: aload_0
    //   261: aload_0
    //   262: getfield factory : Lio/netty/handler/codec/http/multipart/HttpDataFactory;
    //   265: aload_0
    //   266: getfield request : Lio/netty/handler/codec/http/HttpRequest;
    //   269: aload #8
    //   271: invokeinterface createAttribute : (Lio/netty/handler/codec/http/HttpRequest;Ljava/lang/String;)Lio/netty/handler/codec/http/multipart/Attribute;
    //   276: putfield currentAttribute : Lio/netty/handler/codec/http/multipart/Attribute;
    //   279: aload_0
    //   280: getfield currentAttribute : Lio/netty/handler/codec/http/multipart/Attribute;
    //   283: ldc_w ''
    //   286: invokeinterface setValue : (Ljava/lang/String;)V
    //   291: aload_0
    //   292: aload_0
    //   293: getfield currentAttribute : Lio/netty/handler/codec/http/multipart/Attribute;
    //   296: invokevirtual addHttpData : (Lio/netty/handler/codec/http/multipart/InterfaceHttpData;)V
    //   299: aload_0
    //   300: aconst_null
    //   301: putfield currentAttribute : Lio/netty/handler/codec/http/multipart/Attribute;
    //   304: iload_3
    //   305: istore_2
    //   306: iconst_1
    //   307: istore #6
    //   309: goto -> 535
    //   312: iload #7
    //   314: bipush #38
    //   316: if_icmpne -> 355
    //   319: aload_0
    //   320: getstatic io/netty/handler/codec/http/multipart/HttpPostRequestDecoder$MultiPartStatus.DISPOSITION : Lio/netty/handler/codec/http/multipart/HttpPostRequestDecoder$MultiPartStatus;
    //   323: putfield currentStatus : Lio/netty/handler/codec/http/multipart/HttpPostRequestDecoder$MultiPartStatus;
    //   326: iload_3
    //   327: iconst_1
    //   328: isub
    //   329: istore #5
    //   331: aload_0
    //   332: aload_0
    //   333: getfield undecodedChunk : Lio/netty/buffer/ByteBuf;
    //   336: iload_2
    //   337: iload #5
    //   339: iload_2
    //   340: isub
    //   341: invokevirtual retainedSlice : (II)Lio/netty/buffer/ByteBuf;
    //   344: invokespecial setFinalBuffer : (Lio/netty/buffer/ByteBuf;)V
    //   347: iload_3
    //   348: istore_2
    //   349: iconst_1
    //   350: istore #6
    //   352: goto -> 535
    //   355: iload #7
    //   357: bipush #13
    //   359: if_icmpne -> 476
    //   362: aload_1
    //   363: getfield pos : I
    //   366: aload_1
    //   367: getfield limit : I
    //   370: if_icmpge -> 463
    //   373: aload_1
    //   374: getfield bytes : [B
    //   377: aload_1
    //   378: dup
    //   379: getfield pos : I
    //   382: dup_x1
    //   383: iconst_1
    //   384: iadd
    //   385: putfield pos : I
    //   388: baload
    //   389: sipush #255
    //   392: iand
    //   393: i2c
    //   394: istore #7
    //   396: iinc #3, 1
    //   399: iload #7
    //   401: bipush #10
    //   403: if_icmpne -> 447
    //   406: aload_0
    //   407: getstatic io/netty/handler/codec/http/multipart/HttpPostRequestDecoder$MultiPartStatus.PREEPILOGUE : Lio/netty/handler/codec/http/multipart/HttpPostRequestDecoder$MultiPartStatus;
    //   410: putfield currentStatus : Lio/netty/handler/codec/http/multipart/HttpPostRequestDecoder$MultiPartStatus;
    //   413: iload_3
    //   414: iconst_2
    //   415: isub
    //   416: istore #5
    //   418: aload_1
    //   419: iconst_0
    //   420: invokevirtual setReadPosition : (I)V
    //   423: aload_0
    //   424: aload_0
    //   425: getfield undecodedChunk : Lio/netty/buffer/ByteBuf;
    //   428: iload_2
    //   429: iload #5
    //   431: iload_2
    //   432: isub
    //   433: invokevirtual retainedSlice : (II)Lio/netty/buffer/ByteBuf;
    //   436: invokespecial setFinalBuffer : (Lio/netty/buffer/ByteBuf;)V
    //   439: iload_3
    //   440: istore_2
    //   441: iconst_0
    //   442: istore #6
    //   444: goto -> 538
    //   447: aload_1
    //   448: iconst_0
    //   449: invokevirtual setReadPosition : (I)V
    //   452: new io/netty/handler/codec/http/multipart/HttpPostRequestDecoder$ErrorDataDecoderException
    //   455: dup
    //   456: ldc_w 'Bad end of line'
    //   459: invokespecial <init> : (Ljava/lang/String;)V
    //   462: athrow
    //   463: aload_1
    //   464: getfield limit : I
    //   467: ifle -> 535
    //   470: iinc #3, -1
    //   473: goto -> 535
    //   476: iload #7
    //   478: bipush #10
    //   480: if_icmpne -> 535
    //   483: aload_0
    //   484: getstatic io/netty/handler/codec/http/multipart/HttpPostRequestDecoder$MultiPartStatus.PREEPILOGUE : Lio/netty/handler/codec/http/multipart/HttpPostRequestDecoder$MultiPartStatus;
    //   487: putfield currentStatus : Lio/netty/handler/codec/http/multipart/HttpPostRequestDecoder$MultiPartStatus;
    //   490: iload_3
    //   491: iconst_1
    //   492: isub
    //   493: istore #5
    //   495: aload_1
    //   496: iconst_0
    //   497: invokevirtual setReadPosition : (I)V
    //   500: aload_0
    //   501: aload_0
    //   502: getfield undecodedChunk : Lio/netty/buffer/ByteBuf;
    //   505: iload_2
    //   506: iload #5
    //   508: iload_2
    //   509: isub
    //   510: invokevirtual retainedSlice : (II)Lio/netty/buffer/ByteBuf;
    //   513: invokespecial setFinalBuffer : (Lio/netty/buffer/ByteBuf;)V
    //   516: iload_3
    //   517: istore_2
    //   518: iconst_0
    //   519: istore #6
    //   521: goto -> 538
    //   524: aload_1
    //   525: iconst_0
    //   526: invokevirtual setReadPosition : (I)V
    //   529: iconst_0
    //   530: istore #6
    //   532: goto -> 538
    //   535: goto -> 65
    //   538: aload_0
    //   539: getfield isLastChunk : Z
    //   542: ifeq -> 611
    //   545: aload_0
    //   546: getfield currentAttribute : Lio/netty/handler/codec/http/multipart/Attribute;
    //   549: ifnull -> 611
    //   552: iload_3
    //   553: istore #5
    //   555: iload #5
    //   557: iload_2
    //   558: if_icmple -> 580
    //   561: aload_0
    //   562: aload_0
    //   563: getfield undecodedChunk : Lio/netty/buffer/ByteBuf;
    //   566: iload_2
    //   567: iload #5
    //   569: iload_2
    //   570: isub
    //   571: invokevirtual retainedSlice : (II)Lio/netty/buffer/ByteBuf;
    //   574: invokespecial setFinalBuffer : (Lio/netty/buffer/ByteBuf;)V
    //   577: goto -> 599
    //   580: aload_0
    //   581: getfield currentAttribute : Lio/netty/handler/codec/http/multipart/Attribute;
    //   584: invokeinterface isCompleted : ()Z
    //   589: ifne -> 599
    //   592: aload_0
    //   593: getstatic io/netty/buffer/Unpooled.EMPTY_BUFFER : Lio/netty/buffer/ByteBuf;
    //   596: invokespecial setFinalBuffer : (Lio/netty/buffer/ByteBuf;)V
    //   599: iload_3
    //   600: istore_2
    //   601: aload_0
    //   602: getstatic io/netty/handler/codec/http/multipart/HttpPostRequestDecoder$MultiPartStatus.EPILOGUE : Lio/netty/handler/codec/http/multipart/HttpPostRequestDecoder$MultiPartStatus;
    //   605: putfield currentStatus : Lio/netty/handler/codec/http/multipart/HttpPostRequestDecoder$MultiPartStatus;
    //   608: goto -> 656
    //   611: iload #6
    //   613: ifeq -> 656
    //   616: aload_0
    //   617: getfield currentAttribute : Lio/netty/handler/codec/http/multipart/Attribute;
    //   620: ifnull -> 656
    //   623: aload_0
    //   624: getfield currentStatus : Lio/netty/handler/codec/http/multipart/HttpPostRequestDecoder$MultiPartStatus;
    //   627: getstatic io/netty/handler/codec/http/multipart/HttpPostRequestDecoder$MultiPartStatus.FIELD : Lio/netty/handler/codec/http/multipart/HttpPostRequestDecoder$MultiPartStatus;
    //   630: if_acmpne -> 656
    //   633: aload_0
    //   634: getfield currentAttribute : Lio/netty/handler/codec/http/multipart/Attribute;
    //   637: aload_0
    //   638: getfield undecodedChunk : Lio/netty/buffer/ByteBuf;
    //   641: iload_2
    //   642: iload_3
    //   643: iload_2
    //   644: isub
    //   645: invokevirtual retainedSlice : (II)Lio/netty/buffer/ByteBuf;
    //   648: iconst_0
    //   649: invokeinterface addContent : (Lio/netty/buffer/ByteBuf;Z)V
    //   654: iload_3
    //   655: istore_2
    //   656: aload_0
    //   657: getfield undecodedChunk : Lio/netty/buffer/ByteBuf;
    //   660: iload_2
    //   661: invokevirtual readerIndex : (I)Lio/netty/buffer/ByteBuf;
    //   664: pop
    //   665: goto -> 724
    //   668: astore #7
    //   670: aload_0
    //   671: getfield undecodedChunk : Lio/netty/buffer/ByteBuf;
    //   674: iload_2
    //   675: invokevirtual readerIndex : (I)Lio/netty/buffer/ByteBuf;
    //   678: pop
    //   679: aload #7
    //   681: athrow
    //   682: astore #7
    //   684: aload_0
    //   685: getfield undecodedChunk : Lio/netty/buffer/ByteBuf;
    //   688: iload_2
    //   689: invokevirtual readerIndex : (I)Lio/netty/buffer/ByteBuf;
    //   692: pop
    //   693: new io/netty/handler/codec/http/multipart/HttpPostRequestDecoder$ErrorDataDecoderException
    //   696: dup
    //   697: aload #7
    //   699: invokespecial <init> : (Ljava/lang/Throwable;)V
    //   702: athrow
    //   703: astore #7
    //   705: aload_0
    //   706: getfield undecodedChunk : Lio/netty/buffer/ByteBuf;
    //   709: iload_2
    //   710: invokevirtual readerIndex : (I)Lio/netty/buffer/ByteBuf;
    //   713: pop
    //   714: new io/netty/handler/codec/http/multipart/HttpPostRequestDecoder$ErrorDataDecoderException
    //   717: dup
    //   718: aload #7
    //   720: invokespecial <init> : (Ljava/lang/Throwable;)V
    //   723: athrow
    //   724: return
    // Line number table:
    //   Java source line number -> byte code offset
    //   #526	-> 0
    //   #527	-> 7
    //   #529	-> 8
    //   #530	-> 18
    //   #531	-> 22
    //   #533	-> 23
    //   #534	-> 35
    //   #535	-> 43
    //   #538	-> 45
    //   #539	-> 55
    //   #541	-> 62
    //   #543	-> 65
    //   #544	-> 76
    //   #545	-> 99
    //   #546	-> 102
    //   #548	-> 140
    //   #549	-> 147
    //   #550	-> 154
    //   #551	-> 159
    //   #553	-> 184
    //   #554	-> 203
    //   #555	-> 205
    //   #556	-> 215
    //   #557	-> 222
    //   #558	-> 227
    //   #559	-> 240
    //   #558	-> 247
    //   #564	-> 252
    //   #565	-> 260
    //   #566	-> 279
    //   #567	-> 291
    //   #569	-> 299
    //   #570	-> 304
    //   #571	-> 306
    //   #572	-> 309
    //   #575	-> 312
    //   #576	-> 319
    //   #577	-> 326
    //   #578	-> 331
    //   #579	-> 347
    //   #580	-> 349
    //   #581	-> 355
    //   #582	-> 362
    //   #583	-> 373
    //   #584	-> 396
    //   #585	-> 399
    //   #586	-> 406
    //   #587	-> 413
    //   #588	-> 418
    //   #589	-> 423
    //   #590	-> 439
    //   #591	-> 441
    //   #592	-> 444
    //   #595	-> 447
    //   #596	-> 452
    //   #599	-> 463
    //   #600	-> 470
    //   #603	-> 476
    //   #604	-> 483
    //   #605	-> 490
    //   #606	-> 495
    //   #607	-> 500
    //   #608	-> 516
    //   #609	-> 518
    //   #610	-> 521
    //   #615	-> 524
    //   #616	-> 529
    //   #617	-> 532
    //   #619	-> 535
    //   #620	-> 538
    //   #622	-> 552
    //   #623	-> 555
    //   #624	-> 561
    //   #625	-> 580
    //   #626	-> 592
    //   #628	-> 599
    //   #629	-> 601
    //   #630	-> 611
    //   #632	-> 633
    //   #634	-> 654
    //   #636	-> 656
    //   #649	-> 665
    //   #637	-> 668
    //   #639	-> 670
    //   #640	-> 679
    //   #641	-> 682
    //   #643	-> 684
    //   #644	-> 693
    //   #645	-> 703
    //   #647	-> 705
    //   #648	-> 714
    //   #650	-> 724
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   184	21	8	key	Ljava/lang/String;
    //   159	49	4	equalpos	I
    //   252	57	8	key	Ljava/lang/String;
    //   227	85	5	ampersandpos	I
    //   331	24	5	ampersandpos	I
    //   418	29	5	ampersandpos	I
    //   495	29	5	ampersandpos	I
    //   99	436	7	read	C
    //   555	56	5	ampersandpos	I
    //   670	12	7	e	Lio/netty/handler/codec/http/multipart/HttpPostRequestDecoder$ErrorDataDecoderException;
    //   684	19	7	e	Ljava/io/IOException;
    //   705	19	7	e	Ljava/lang/IllegalArgumentException;
    //   0	725	0	this	Lio/netty/handler/codec/http/multipart/HttpPostStandardRequestDecoder;
    //   35	690	1	sao	Lio/netty/handler/codec/http/multipart/HttpPostBodyUtil$SeekAheadOptimize;
    //   43	682	2	firstpos	I
    //   45	680	3	currentpos	I
    //   65	660	6	contRead	Z
    // Exception table:
    //   from	to	target	type
    //   65	665	668	io/netty/handler/codec/http/multipart/HttpPostRequestDecoder$ErrorDataDecoderException
    //   65	665	682	java/io/IOException
    //   65	665	703	java/lang/IllegalArgumentException
  }
  
  private void setFinalBuffer(ByteBuf buffer) throws IOException {
    this.currentAttribute.addContent(buffer, true);
    ByteBuf decodedBuf = decodeAttribute(this.currentAttribute.getByteBuf(), this.charset);
    if (decodedBuf != null)
      this.currentAttribute.setContent(decodedBuf); 
    addHttpData(this.currentAttribute);
    this.currentAttribute = null;
  }
  
  private static String decodeAttribute(String s, Charset charset) {
    try {
      return QueryStringDecoder.decodeComponent(s, charset);
    } catch (IllegalArgumentException e) {
      throw new HttpPostRequestDecoder.ErrorDataDecoderException("Bad string: '" + s + '\'', e);
    } 
  }
  
  private static ByteBuf decodeAttribute(ByteBuf b, Charset charset) {
    int firstEscaped = b.forEachByte(new UrlEncodedDetector());
    if (firstEscaped == -1)
      return null; 
    ByteBuf buf = b.alloc().buffer(b.readableBytes());
    UrlDecoder urlDecode = new UrlDecoder(buf);
    int idx = b.forEachByte(urlDecode);
    if (urlDecode.nextEscapedIdx != 0) {
      if (idx == -1)
        idx = b.readableBytes() - 1; 
      idx -= urlDecode.nextEscapedIdx - 1;
      buf.release();
      throw new HttpPostRequestDecoder.ErrorDataDecoderException(
          String.format("Invalid hex byte at index '%d' in string: '%s'", new Object[] { Integer.valueOf(idx), b.toString(charset) }));
    } 
    return buf;
  }
  
  public void destroy() {
    cleanFiles();
    for (InterfaceHttpData httpData : this.bodyListHttpData) {
      if (httpData.refCnt() > 0)
        httpData.release(); 
    } 
    this.destroyed = true;
    if (this.undecodedChunk != null && this.undecodedChunk.refCnt() > 0) {
      this.undecodedChunk.release();
      this.undecodedChunk = null;
    } 
  }
  
  public void cleanFiles() {
    checkDestroyed();
    this.factory.cleanRequestHttpData(this.request);
  }
  
  public void removeHttpDataFromClean(InterfaceHttpData data) {
    checkDestroyed();
    this.factory.removeHttpDataFromClean(this.request, data);
  }
  
  private static final class UrlEncodedDetector implements ByteProcessor {
    private UrlEncodedDetector() {}
    
    public boolean process(byte value) throws Exception {
      return (value != 37 && value != 43);
    }
  }
  
  private static final class UrlDecoder implements ByteProcessor {
    private final ByteBuf output;
    
    private int nextEscapedIdx;
    
    private byte hiByte;
    
    UrlDecoder(ByteBuf output) {
      this.output = output;
    }
    
    public boolean process(byte value) {
      if (this.nextEscapedIdx != 0) {
        if (this.nextEscapedIdx == 1) {
          this.hiByte = value;
          this.nextEscapedIdx++;
        } else {
          int hi = StringUtil.decodeHexNibble((char)this.hiByte);
          int lo = StringUtil.decodeHexNibble((char)value);
          if (hi == -1 || lo == -1) {
            this.nextEscapedIdx++;
            return false;
          } 
          this.output.writeByte((hi << 4) + lo);
          this.nextEscapedIdx = 0;
        } 
      } else if (value == 37) {
        this.nextEscapedIdx = 1;
      } else if (value == 43) {
        this.output.writeByte(32);
      } else {
        this.output.writeByte(value);
      } 
      return true;
    }
  }
}
