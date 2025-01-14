package javazoom.spi.mpeg.sampled.file;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.security.AccessControlException;
import java.util.HashMap;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.UnsupportedAudioFileException;
import javazoom.spi.mpeg.sampled.file.tag.IcyInputStream;
import javazoom.spi.mpeg.sampled.file.tag.MP3Tag;
import org.tritonus.share.TDebug;
import org.tritonus.share.sampled.file.TAudioFileReader;

public class MpegAudioFileReader extends TAudioFileReader {
  public static final String VERSION = "MP3SPI 1.9.5";
  
  private final int SYNC = -2097152;
  
  private String weak = null;
  
  private final AudioFormat.Encoding[][] sm_aEncodings = new AudioFormat.Encoding[][] { { MpegEncoding.MPEG2L1, MpegEncoding.MPEG2L2, MpegEncoding.MPEG2L3 }, { MpegEncoding.MPEG1L1, MpegEncoding.MPEG1L2, MpegEncoding.MPEG1L3 }, { MpegEncoding.MPEG2DOT5L1, MpegEncoding.MPEG2DOT5L2, MpegEncoding.MPEG2DOT5L3 } };
  
  public static int INITAL_READ_LENGTH = 4096000;
  
  private static int MARK_LIMIT = INITAL_READ_LENGTH + 1;
  
  static {
    String s = System.getProperty("marklimit");
    if (s != null)
      try {
        INITAL_READ_LENGTH = Integer.parseInt(s);
        MARK_LIMIT = INITAL_READ_LENGTH + 1;
      } catch (NumberFormatException e) {
        e.printStackTrace();
      }  
  }
  
  private static final String[] id3v1genres = new String[] { 
      "Blues", "Classic Rock", "Country", "Dance", "Disco", "Funk", "Grunge", "Hip-Hop", "Jazz", "Metal", 
      "New Age", "Oldies", "Other", "Pop", "R&B", "Rap", "Reggae", "Rock", "Techno", "Industrial", 
      "Alternative", "Ska", "Death Metal", "Pranks", "Soundtrack", "Euro-Techno", "Ambient", "Trip-Hop", "Vocal", "Jazz+Funk", 
      "Fusion", "Trance", "Classical", "Instrumental", "Acid", "House", "Game", "Sound Clip", "Gospel", "Noise", 
      "AlternRock", "Bass", "Soul", "Punk", "Space", "Meditative", "Instrumental Pop", "Instrumental Rock", "Ethnic", "Gothic", 
      "Darkwave", "Techno-Industrial", "Electronic", "Pop-Folk", "Eurodance", "Dream", "Southern Rock", "Comedy", "Cult", "Gangsta", 
      "Top 40", "Christian Rap", "Pop/Funk", "Jungle", "Native American", "Cabaret", "New Wave", "Psychadelic", "Rave", "Showtunes", 
      "Trailer", "Lo-Fi", "Tribal", "Acid Punk", "Acid Jazz", "Polka", "Retro", "Musical", "Rock & Roll", "Hard Rock", 
      "Folk", "Folk-Rock", "National Folk", "Swing", "Fast Fusion", "Bebob", "Latin", "Revival", "Celtic", "Bluegrass", 
      "Avantgarde", "Gothic Rock", "Progressive Rock", "Psychedelic Rock", "Symphonic Rock", "Slow Rock", "Big Band", "Chorus", "Easy Listening", "Acoustic", 
      "Humour", "Speech", "Chanson", "Opera", "Chamber Music", "Sonata", "Symphony", "Booty Brass", "Primus", "Porn Groove", 
      "Satire", "Slow Jam", "Club", "Tango", "Samba", "Folklore", "Ballad", "Power Ballad", "Rhythmic Soul", "Freestyle", 
      "Duet", "Punk Rock", "Drum Solo", "A Capela", "Euro-House", "Dance Hall", "Goa", "Drum & Bass", "Club-House", "Hardcore", 
      "Terror", "Indie", "BritPop", "Negerpunk", "Polsk Punk", "Beat", "Christian Gangsta Rap", "Heavy Metal", "Black Metal", "Crossover", 
      "Contemporary Christian", "Christian Rock", "Merengue", "Salsa", "Thrash Metal", "Anime", "JPop", "SynthPop" };
  
  public MpegAudioFileReader() {
    super(MARK_LIMIT, true);
    if (TDebug.TraceAudioFileReader)
      TDebug.out("MP3SPI 1.9.5"); 
    try {
      this.weak = System.getProperty("mp3spi.weak");
    } catch (AccessControlException accessControlException) {}
  }
  
  public AudioFileFormat getAudioFileFormat(File file) throws UnsupportedAudioFileException, IOException {
    return super.getAudioFileFormat(file);
  }
  
  public AudioFileFormat getAudioFileFormat(URL url) throws UnsupportedAudioFileException, IOException {
    if (TDebug.TraceAudioFileReader)
      TDebug.out("MpegAudioFileReader.getAudioFileFormat(URL): begin"); 
    long lFileLengthInBytes = -1L;
    URLConnection conn = url.openConnection();
    conn.setRequestProperty("Icy-Metadata", "1");
    InputStream inputStream = conn.getInputStream();
    AudioFileFormat audioFileFormat = null;
    try {
      audioFileFormat = getAudioFileFormat(inputStream, lFileLengthInBytes);
    } finally {
      inputStream.close();
    } 
    if (TDebug.TraceAudioFileReader)
      TDebug.out("MpegAudioFileReader.getAudioFileFormat(URL): end"); 
    return audioFileFormat;
  }
  
  public AudioFileFormat getAudioFileFormat(InputStream inputStream, long mediaLength) throws UnsupportedAudioFileException, IOException {
    // Byte code:
    //   0: getstatic org/tritonus/share/TDebug.TraceAudioFileReader : Z
    //   3: ifeq -> 11
    //   6: ldc '>MpegAudioFileReader.getAudioFileFormat(InputStream inputStream, long mediaLength): begin'
    //   8: invokestatic out : (Ljava/lang/String;)V
    //   11: new java/util/HashMap
    //   14: dup
    //   15: invokespecial <init> : ()V
    //   18: astore #4
    //   20: new java/util/HashMap
    //   23: dup
    //   24: invokespecial <init> : ()V
    //   27: astore #5
    //   29: lload_2
    //   30: l2i
    //   31: istore #6
    //   33: aload_1
    //   34: invokevirtual available : ()I
    //   37: istore #7
    //   39: new java/io/PushbackInputStream
    //   42: dup
    //   43: aload_1
    //   44: getstatic javazoom/spi/mpeg/sampled/file/MpegAudioFileReader.MARK_LIMIT : I
    //   47: invokespecial <init> : (Ljava/io/InputStream;I)V
    //   50: astore #8
    //   52: bipush #22
    //   54: newarray byte
    //   56: astore #9
    //   58: aload #8
    //   60: aload #9
    //   62: invokevirtual read : ([B)I
    //   65: pop
    //   66: getstatic org/tritonus/share/TDebug.TraceAudioFileReader : Z
    //   69: ifeq -> 111
    //   72: new java/lang/StringBuilder
    //   75: dup
    //   76: invokespecial <init> : ()V
    //   79: ldc 'InputStream : '
    //   81: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   84: aload_1
    //   85: invokevirtual append : (Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   88: ldc ' =>'
    //   90: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   93: new java/lang/String
    //   96: dup
    //   97: aload #9
    //   99: invokespecial <init> : ([B)V
    //   102: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   105: invokevirtual toString : ()Ljava/lang/String;
    //   108: invokestatic out : (Ljava/lang/String;)V
    //   111: aload #9
    //   113: iconst_0
    //   114: baload
    //   115: bipush #82
    //   117: if_icmpne -> 247
    //   120: aload #9
    //   122: iconst_1
    //   123: baload
    //   124: bipush #73
    //   126: if_icmpne -> 247
    //   129: aload #9
    //   131: iconst_2
    //   132: baload
    //   133: bipush #70
    //   135: if_icmpne -> 247
    //   138: aload #9
    //   140: iconst_3
    //   141: baload
    //   142: bipush #70
    //   144: if_icmpne -> 247
    //   147: aload #9
    //   149: bipush #8
    //   151: baload
    //   152: bipush #87
    //   154: if_icmpne -> 247
    //   157: aload #9
    //   159: bipush #9
    //   161: baload
    //   162: bipush #65
    //   164: if_icmpne -> 247
    //   167: aload #9
    //   169: bipush #10
    //   171: baload
    //   172: bipush #86
    //   174: if_icmpne -> 247
    //   177: aload #9
    //   179: bipush #11
    //   181: baload
    //   182: bipush #69
    //   184: if_icmpne -> 247
    //   187: getstatic org/tritonus/share/TDebug.TraceAudioFileReader : Z
    //   190: ifeq -> 198
    //   193: ldc 'RIFF/WAV stream found'
    //   195: invokestatic out : (Ljava/lang/String;)V
    //   198: aload #9
    //   200: bipush #21
    //   202: baload
    //   203: bipush #8
    //   205: ishl
    //   206: ldc 65280
    //   208: iand
    //   209: aload #9
    //   211: bipush #20
    //   213: baload
    //   214: sipush #255
    //   217: iand
    //   218: ior
    //   219: istore #10
    //   221: aload_0
    //   222: getfield weak : Ljava/lang/String;
    //   225: ifnonnull -> 244
    //   228: iload #10
    //   230: iconst_1
    //   231: if_icmpne -> 244
    //   234: new javax/sound/sampled/UnsupportedAudioFileException
    //   237: dup
    //   238: ldc 'WAV PCM stream found'
    //   240: invokespecial <init> : (Ljava/lang/String;)V
    //   243: athrow
    //   244: goto -> 940
    //   247: aload #9
    //   249: iconst_0
    //   250: baload
    //   251: bipush #46
    //   253: if_icmpne -> 311
    //   256: aload #9
    //   258: iconst_1
    //   259: baload
    //   260: bipush #115
    //   262: if_icmpne -> 311
    //   265: aload #9
    //   267: iconst_2
    //   268: baload
    //   269: bipush #110
    //   271: if_icmpne -> 311
    //   274: aload #9
    //   276: iconst_3
    //   277: baload
    //   278: bipush #100
    //   280: if_icmpne -> 311
    //   283: getstatic org/tritonus/share/TDebug.TraceAudioFileReader : Z
    //   286: ifeq -> 294
    //   289: ldc 'AU stream found'
    //   291: invokestatic out : (Ljava/lang/String;)V
    //   294: aload_0
    //   295: getfield weak : Ljava/lang/String;
    //   298: ifnonnull -> 940
    //   301: new javax/sound/sampled/UnsupportedAudioFileException
    //   304: dup
    //   305: ldc 'AU stream found'
    //   307: invokespecial <init> : (Ljava/lang/String;)V
    //   310: athrow
    //   311: aload #9
    //   313: iconst_0
    //   314: baload
    //   315: bipush #70
    //   317: if_icmpne -> 415
    //   320: aload #9
    //   322: iconst_1
    //   323: baload
    //   324: bipush #79
    //   326: if_icmpne -> 415
    //   329: aload #9
    //   331: iconst_2
    //   332: baload
    //   333: bipush #82
    //   335: if_icmpne -> 415
    //   338: aload #9
    //   340: iconst_3
    //   341: baload
    //   342: bipush #77
    //   344: if_icmpne -> 415
    //   347: aload #9
    //   349: bipush #8
    //   351: baload
    //   352: bipush #65
    //   354: if_icmpne -> 415
    //   357: aload #9
    //   359: bipush #9
    //   361: baload
    //   362: bipush #73
    //   364: if_icmpne -> 415
    //   367: aload #9
    //   369: bipush #10
    //   371: baload
    //   372: bipush #70
    //   374: if_icmpne -> 415
    //   377: aload #9
    //   379: bipush #11
    //   381: baload
    //   382: bipush #70
    //   384: if_icmpne -> 415
    //   387: getstatic org/tritonus/share/TDebug.TraceAudioFileReader : Z
    //   390: ifeq -> 398
    //   393: ldc 'AIFF stream found'
    //   395: invokestatic out : (Ljava/lang/String;)V
    //   398: aload_0
    //   399: getfield weak : Ljava/lang/String;
    //   402: ifnonnull -> 940
    //   405: new javax/sound/sampled/UnsupportedAudioFileException
    //   408: dup
    //   409: ldc 'AIFF stream found'
    //   411: invokespecial <init> : (Ljava/lang/String;)V
    //   414: athrow
    //   415: aload #9
    //   417: iconst_0
    //   418: baload
    //   419: bipush #77
    //   421: if_icmpne -> 428
    //   424: iconst_1
    //   425: goto -> 429
    //   428: iconst_0
    //   429: aload #9
    //   431: iconst_0
    //   432: baload
    //   433: bipush #109
    //   435: if_icmpne -> 442
    //   438: iconst_1
    //   439: goto -> 443
    //   442: iconst_0
    //   443: ior
    //   444: ifeq -> 539
    //   447: aload #9
    //   449: iconst_1
    //   450: baload
    //   451: bipush #65
    //   453: if_icmpne -> 460
    //   456: iconst_1
    //   457: goto -> 461
    //   460: iconst_0
    //   461: aload #9
    //   463: iconst_1
    //   464: baload
    //   465: bipush #97
    //   467: if_icmpne -> 474
    //   470: iconst_1
    //   471: goto -> 475
    //   474: iconst_0
    //   475: ior
    //   476: ifeq -> 539
    //   479: aload #9
    //   481: iconst_2
    //   482: baload
    //   483: bipush #67
    //   485: if_icmpne -> 492
    //   488: iconst_1
    //   489: goto -> 493
    //   492: iconst_0
    //   493: aload #9
    //   495: iconst_2
    //   496: baload
    //   497: bipush #99
    //   499: if_icmpne -> 506
    //   502: iconst_1
    //   503: goto -> 507
    //   506: iconst_0
    //   507: ior
    //   508: ifeq -> 539
    //   511: getstatic org/tritonus/share/TDebug.TraceAudioFileReader : Z
    //   514: ifeq -> 522
    //   517: ldc 'APE stream found'
    //   519: invokestatic out : (Ljava/lang/String;)V
    //   522: aload_0
    //   523: getfield weak : Ljava/lang/String;
    //   526: ifnonnull -> 940
    //   529: new javax/sound/sampled/UnsupportedAudioFileException
    //   532: dup
    //   533: ldc 'APE stream found'
    //   535: invokespecial <init> : (Ljava/lang/String;)V
    //   538: athrow
    //   539: aload #9
    //   541: iconst_0
    //   542: baload
    //   543: bipush #70
    //   545: if_icmpne -> 552
    //   548: iconst_1
    //   549: goto -> 553
    //   552: iconst_0
    //   553: aload #9
    //   555: iconst_0
    //   556: baload
    //   557: bipush #102
    //   559: if_icmpne -> 566
    //   562: iconst_1
    //   563: goto -> 567
    //   566: iconst_0
    //   567: ior
    //   568: ifeq -> 695
    //   571: aload #9
    //   573: iconst_1
    //   574: baload
    //   575: bipush #76
    //   577: if_icmpne -> 584
    //   580: iconst_1
    //   581: goto -> 585
    //   584: iconst_0
    //   585: aload #9
    //   587: iconst_1
    //   588: baload
    //   589: bipush #108
    //   591: if_icmpne -> 598
    //   594: iconst_1
    //   595: goto -> 599
    //   598: iconst_0
    //   599: ior
    //   600: ifeq -> 695
    //   603: aload #9
    //   605: iconst_2
    //   606: baload
    //   607: bipush #65
    //   609: if_icmpne -> 616
    //   612: iconst_1
    //   613: goto -> 617
    //   616: iconst_0
    //   617: aload #9
    //   619: iconst_2
    //   620: baload
    //   621: bipush #97
    //   623: if_icmpne -> 630
    //   626: iconst_1
    //   627: goto -> 631
    //   630: iconst_0
    //   631: ior
    //   632: ifeq -> 695
    //   635: aload #9
    //   637: iconst_3
    //   638: baload
    //   639: bipush #67
    //   641: if_icmpne -> 648
    //   644: iconst_1
    //   645: goto -> 649
    //   648: iconst_0
    //   649: aload #9
    //   651: iconst_3
    //   652: baload
    //   653: bipush #99
    //   655: if_icmpne -> 662
    //   658: iconst_1
    //   659: goto -> 663
    //   662: iconst_0
    //   663: ior
    //   664: ifeq -> 695
    //   667: getstatic org/tritonus/share/TDebug.TraceAudioFileReader : Z
    //   670: ifeq -> 678
    //   673: ldc 'FLAC stream found'
    //   675: invokestatic out : (Ljava/lang/String;)V
    //   678: aload_0
    //   679: getfield weak : Ljava/lang/String;
    //   682: ifnonnull -> 940
    //   685: new javax/sound/sampled/UnsupportedAudioFileException
    //   688: dup
    //   689: ldc 'FLAC stream found'
    //   691: invokespecial <init> : (Ljava/lang/String;)V
    //   694: athrow
    //   695: aload #9
    //   697: iconst_0
    //   698: baload
    //   699: bipush #73
    //   701: if_icmpne -> 708
    //   704: iconst_1
    //   705: goto -> 709
    //   708: iconst_0
    //   709: aload #9
    //   711: iconst_0
    //   712: baload
    //   713: bipush #105
    //   715: if_icmpne -> 722
    //   718: iconst_1
    //   719: goto -> 723
    //   722: iconst_0
    //   723: ior
    //   724: ifeq -> 809
    //   727: aload #9
    //   729: iconst_1
    //   730: baload
    //   731: bipush #67
    //   733: if_icmpne -> 740
    //   736: iconst_1
    //   737: goto -> 741
    //   740: iconst_0
    //   741: aload #9
    //   743: iconst_1
    //   744: baload
    //   745: bipush #99
    //   747: if_icmpne -> 754
    //   750: iconst_1
    //   751: goto -> 755
    //   754: iconst_0
    //   755: ior
    //   756: ifeq -> 809
    //   759: aload #9
    //   761: iconst_2
    //   762: baload
    //   763: bipush #89
    //   765: if_icmpne -> 772
    //   768: iconst_1
    //   769: goto -> 773
    //   772: iconst_0
    //   773: aload #9
    //   775: iconst_2
    //   776: baload
    //   777: bipush #121
    //   779: if_icmpne -> 786
    //   782: iconst_1
    //   783: goto -> 787
    //   786: iconst_0
    //   787: ior
    //   788: ifeq -> 809
    //   791: aload #8
    //   793: aload #9
    //   795: invokevirtual unread : ([B)V
    //   798: aload_0
    //   799: aload #8
    //   801: aload #4
    //   803: invokevirtual loadShoutcastInfo : (Ljava/io/InputStream;Ljava/util/HashMap;)V
    //   806: goto -> 940
    //   809: aload #9
    //   811: iconst_0
    //   812: baload
    //   813: bipush #79
    //   815: if_icmpne -> 822
    //   818: iconst_1
    //   819: goto -> 823
    //   822: iconst_0
    //   823: aload #9
    //   825: iconst_0
    //   826: baload
    //   827: bipush #111
    //   829: if_icmpne -> 836
    //   832: iconst_1
    //   833: goto -> 837
    //   836: iconst_0
    //   837: ior
    //   838: ifeq -> 933
    //   841: aload #9
    //   843: iconst_1
    //   844: baload
    //   845: bipush #71
    //   847: if_icmpne -> 854
    //   850: iconst_1
    //   851: goto -> 855
    //   854: iconst_0
    //   855: aload #9
    //   857: iconst_1
    //   858: baload
    //   859: bipush #103
    //   861: if_icmpne -> 868
    //   864: iconst_1
    //   865: goto -> 869
    //   868: iconst_0
    //   869: ior
    //   870: ifeq -> 933
    //   873: aload #9
    //   875: iconst_2
    //   876: baload
    //   877: bipush #71
    //   879: if_icmpne -> 886
    //   882: iconst_1
    //   883: goto -> 887
    //   886: iconst_0
    //   887: aload #9
    //   889: iconst_2
    //   890: baload
    //   891: bipush #103
    //   893: if_icmpne -> 900
    //   896: iconst_1
    //   897: goto -> 901
    //   900: iconst_0
    //   901: ior
    //   902: ifeq -> 933
    //   905: getstatic org/tritonus/share/TDebug.TraceAudioFileReader : Z
    //   908: ifeq -> 916
    //   911: ldc 'Ogg stream found'
    //   913: invokestatic out : (Ljava/lang/String;)V
    //   916: aload_0
    //   917: getfield weak : Ljava/lang/String;
    //   920: ifnonnull -> 940
    //   923: new javax/sound/sampled/UnsupportedAudioFileException
    //   926: dup
    //   927: ldc 'Ogg stream found'
    //   929: invokespecial <init> : (Ljava/lang/String;)V
    //   932: athrow
    //   933: aload #8
    //   935: aload #9
    //   937: invokevirtual unread : ([B)V
    //   940: iconst_m1
    //   941: istore #10
    //   943: iconst_m1
    //   944: istore #11
    //   946: iconst_m1
    //   947: istore #12
    //   949: iconst_m1
    //   950: istore #13
    //   952: iconst_m1
    //   953: istore #14
    //   955: iconst_m1
    //   956: istore #15
    //   958: iconst_m1
    //   959: istore #16
    //   961: iconst_m1
    //   962: istore #17
    //   964: ldc -1.0
    //   966: fstore #18
    //   968: iconst_m1
    //   969: istore #19
    //   971: iconst_m1
    //   972: istore #20
    //   974: iconst_m1
    //   975: istore #21
    //   977: iconst_m1
    //   978: istore #22
    //   980: iconst_0
    //   981: istore #23
    //   983: aconst_null
    //   984: astore #24
    //   986: new javazoom/jl/decoder/Bitstream
    //   989: dup
    //   990: aload #8
    //   992: invokespecial <init> : (Ljava/io/InputStream;)V
    //   995: astore #25
    //   997: aload #25
    //   999: invokevirtual header_pos : ()I
    //   1002: istore #26
    //   1004: aload #4
    //   1006: ldc 'mp3.header.pos'
    //   1008: new java/lang/Integer
    //   1011: dup
    //   1012: iload #26
    //   1014: invokespecial <init> : (I)V
    //   1017: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1020: pop
    //   1021: aload #25
    //   1023: invokevirtual readFrame : ()Ljavazoom/jl/decoder/Header;
    //   1026: astore #27
    //   1028: aload #27
    //   1030: invokevirtual version : ()I
    //   1033: istore #10
    //   1035: iload #10
    //   1037: iconst_2
    //   1038: if_icmpne -> 1057
    //   1041: aload #4
    //   1043: ldc 'mp3.version.mpeg'
    //   1045: ldc 2.5
    //   1047: invokestatic toString : (F)Ljava/lang/String;
    //   1050: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1053: pop
    //   1054: goto -> 1072
    //   1057: aload #4
    //   1059: ldc 'mp3.version.mpeg'
    //   1061: iconst_2
    //   1062: iload #10
    //   1064: isub
    //   1065: invokestatic toString : (I)Ljava/lang/String;
    //   1068: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1071: pop
    //   1072: aload #27
    //   1074: invokevirtual layer : ()I
    //   1077: istore #11
    //   1079: aload #4
    //   1081: ldc_w 'mp3.version.layer'
    //   1084: iload #11
    //   1086: invokestatic toString : (I)Ljava/lang/String;
    //   1089: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1092: pop
    //   1093: aload #27
    //   1095: invokevirtual sample_frequency : ()I
    //   1098: istore #12
    //   1100: aload #27
    //   1102: invokevirtual mode : ()I
    //   1105: istore #13
    //   1107: aload #4
    //   1109: ldc_w 'mp3.mode'
    //   1112: new java/lang/Integer
    //   1115: dup
    //   1116: iload #13
    //   1118: invokespecial <init> : (I)V
    //   1121: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1124: pop
    //   1125: iload #13
    //   1127: iconst_3
    //   1128: if_icmpne -> 1135
    //   1131: iconst_1
    //   1132: goto -> 1136
    //   1135: iconst_2
    //   1136: istore #20
    //   1138: aload #4
    //   1140: ldc_w 'mp3.channels'
    //   1143: new java/lang/Integer
    //   1146: dup
    //   1147: iload #20
    //   1149: invokespecial <init> : (I)V
    //   1152: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1155: pop
    //   1156: aload #27
    //   1158: invokevirtual vbr : ()Z
    //   1161: istore #23
    //   1163: aload #5
    //   1165: ldc_w 'vbr'
    //   1168: new java/lang/Boolean
    //   1171: dup
    //   1172: iload #23
    //   1174: invokespecial <init> : (Z)V
    //   1177: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1180: pop
    //   1181: aload #4
    //   1183: ldc_w 'mp3.vbr'
    //   1186: new java/lang/Boolean
    //   1189: dup
    //   1190: iload #23
    //   1192: invokespecial <init> : (Z)V
    //   1195: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1198: pop
    //   1199: aload #4
    //   1201: ldc_w 'mp3.vbr.scale'
    //   1204: new java/lang/Integer
    //   1207: dup
    //   1208: aload #27
    //   1210: invokevirtual vbr_scale : ()I
    //   1213: invokespecial <init> : (I)V
    //   1216: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1219: pop
    //   1220: aload #27
    //   1222: invokevirtual calculate_framesize : ()I
    //   1225: istore #14
    //   1227: aload #4
    //   1229: ldc_w 'mp3.framesize.bytes'
    //   1232: new java/lang/Integer
    //   1235: dup
    //   1236: iload #14
    //   1238: invokespecial <init> : (I)V
    //   1241: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1244: pop
    //   1245: iload #14
    //   1247: ifge -> 1279
    //   1250: new javax/sound/sampled/UnsupportedAudioFileException
    //   1253: dup
    //   1254: new java/lang/StringBuilder
    //   1257: dup
    //   1258: invokespecial <init> : ()V
    //   1261: ldc_w 'Invalid FrameSize : '
    //   1264: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1267: iload #14
    //   1269: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   1272: invokevirtual toString : ()Ljava/lang/String;
    //   1275: invokespecial <init> : (Ljava/lang/String;)V
    //   1278: athrow
    //   1279: aload #27
    //   1281: invokevirtual frequency : ()I
    //   1284: istore #16
    //   1286: aload #4
    //   1288: ldc_w 'mp3.frequency.hz'
    //   1291: new java/lang/Integer
    //   1294: dup
    //   1295: iload #16
    //   1297: invokespecial <init> : (I)V
    //   1300: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1303: pop
    //   1304: dconst_1
    //   1305: aload #27
    //   1307: invokevirtual ms_per_frame : ()F
    //   1310: f2d
    //   1311: ddiv
    //   1312: ldc2_w 1000.0
    //   1315: dmul
    //   1316: d2f
    //   1317: fstore #18
    //   1319: aload #4
    //   1321: ldc_w 'mp3.framerate.fps'
    //   1324: new java/lang/Float
    //   1327: dup
    //   1328: fload #18
    //   1330: invokespecial <init> : (F)V
    //   1333: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1336: pop
    //   1337: fload #18
    //   1339: fconst_0
    //   1340: fcmpg
    //   1341: ifge -> 1373
    //   1344: new javax/sound/sampled/UnsupportedAudioFileException
    //   1347: dup
    //   1348: new java/lang/StringBuilder
    //   1351: dup
    //   1352: invokespecial <init> : ()V
    //   1355: ldc_w 'Invalid FrameRate : '
    //   1358: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1361: fload #18
    //   1363: invokevirtual append : (F)Ljava/lang/StringBuilder;
    //   1366: invokevirtual toString : ()Ljava/lang/String;
    //   1369: invokespecial <init> : (Ljava/lang/String;)V
    //   1372: athrow
    //   1373: iload #6
    //   1375: istore #28
    //   1377: iload #26
    //   1379: ifle -> 1402
    //   1382: iload #6
    //   1384: iconst_m1
    //   1385: if_icmpeq -> 1402
    //   1388: iload #26
    //   1390: iload #6
    //   1392: if_icmpge -> 1402
    //   1395: iload #28
    //   1397: iload #26
    //   1399: isub
    //   1400: istore #28
    //   1402: iload #6
    //   1404: iconst_m1
    //   1405: if_icmpeq -> 1453
    //   1408: aload #4
    //   1410: ldc_w 'mp3.length.bytes'
    //   1413: new java/lang/Integer
    //   1416: dup
    //   1417: iload #6
    //   1419: invokespecial <init> : (I)V
    //   1422: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1425: pop
    //   1426: aload #27
    //   1428: iload #28
    //   1430: invokevirtual max_number_of_frames : (I)I
    //   1433: istore #17
    //   1435: aload #4
    //   1437: ldc_w 'mp3.length.frames'
    //   1440: new java/lang/Integer
    //   1443: dup
    //   1444: iload #17
    //   1446: invokespecial <init> : (I)V
    //   1449: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1452: pop
    //   1453: aload #27
    //   1455: invokevirtual bitrate : ()I
    //   1458: istore #19
    //   1460: aload #5
    //   1462: ldc_w 'bitrate'
    //   1465: new java/lang/Integer
    //   1468: dup
    //   1469: iload #19
    //   1471: invokespecial <init> : (I)V
    //   1474: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1477: pop
    //   1478: aload #4
    //   1480: ldc_w 'mp3.bitrate.nominal.bps'
    //   1483: new java/lang/Integer
    //   1486: dup
    //   1487: iload #19
    //   1489: invokespecial <init> : (I)V
    //   1492: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1495: pop
    //   1496: aload #27
    //   1498: invokevirtual getSyncHeader : ()I
    //   1501: istore #21
    //   1503: aload_0
    //   1504: getfield sm_aEncodings : [[Ljavax/sound/sampled/AudioFormat$Encoding;
    //   1507: iload #10
    //   1509: aaload
    //   1510: iload #11
    //   1512: iconst_1
    //   1513: isub
    //   1514: aaload
    //   1515: astore #24
    //   1517: aload #4
    //   1519: ldc_w 'mp3.version.encoding'
    //   1522: aload #24
    //   1524: invokevirtual toString : ()Ljava/lang/String;
    //   1527: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1530: pop
    //   1531: iload #6
    //   1533: iconst_m1
    //   1534: if_icmpeq -> 1572
    //   1537: aload #27
    //   1539: iload #28
    //   1541: invokevirtual total_ms : (I)F
    //   1544: invokestatic round : (F)I
    //   1547: istore #22
    //   1549: aload #4
    //   1551: ldc_w 'duration'
    //   1554: new java/lang/Long
    //   1557: dup
    //   1558: iload #22
    //   1560: i2l
    //   1561: ldc2_w 1000
    //   1564: lmul
    //   1565: invokespecial <init> : (J)V
    //   1568: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1571: pop
    //   1572: aload #4
    //   1574: ldc_w 'mp3.copyright'
    //   1577: new java/lang/Boolean
    //   1580: dup
    //   1581: aload #27
    //   1583: invokevirtual copyright : ()Z
    //   1586: invokespecial <init> : (Z)V
    //   1589: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1592: pop
    //   1593: aload #4
    //   1595: ldc_w 'mp3.original'
    //   1598: new java/lang/Boolean
    //   1601: dup
    //   1602: aload #27
    //   1604: invokevirtual original : ()Z
    //   1607: invokespecial <init> : (Z)V
    //   1610: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1613: pop
    //   1614: aload #4
    //   1616: ldc_w 'mp3.crc'
    //   1619: new java/lang/Boolean
    //   1622: dup
    //   1623: aload #27
    //   1625: invokevirtual checksums : ()Z
    //   1628: invokespecial <init> : (Z)V
    //   1631: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1634: pop
    //   1635: aload #4
    //   1637: ldc_w 'mp3.padding'
    //   1640: new java/lang/Boolean
    //   1643: dup
    //   1644: aload #27
    //   1646: invokevirtual padding : ()Z
    //   1649: invokespecial <init> : (Z)V
    //   1652: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1655: pop
    //   1656: aload #25
    //   1658: invokevirtual getRawID3v2 : ()Ljava/io/InputStream;
    //   1661: astore #29
    //   1663: aload #29
    //   1665: ifnull -> 1687
    //   1668: aload #4
    //   1670: ldc_w 'mp3.id3tag.v2'
    //   1673: aload #29
    //   1675: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   1678: pop
    //   1679: aload_0
    //   1680: aload #29
    //   1682: aload #4
    //   1684: invokevirtual parseID3v2Frames : (Ljava/io/InputStream;Ljava/util/HashMap;)V
    //   1687: getstatic org/tritonus/share/TDebug.TraceAudioFileReader : Z
    //   1690: ifeq -> 1701
    //   1693: aload #27
    //   1695: invokevirtual toString : ()Ljava/lang/String;
    //   1698: invokestatic out : (Ljava/lang/String;)V
    //   1701: goto -> 1771
    //   1704: astore #25
    //   1706: getstatic org/tritonus/share/TDebug.TraceAudioFileReader : Z
    //   1709: ifeq -> 1739
    //   1712: new java/lang/StringBuilder
    //   1715: dup
    //   1716: invokespecial <init> : ()V
    //   1719: ldc_w 'not a MPEG stream:'
    //   1722: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1725: aload #25
    //   1727: invokevirtual getMessage : ()Ljava/lang/String;
    //   1730: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1733: invokevirtual toString : ()Ljava/lang/String;
    //   1736: invokestatic out : (Ljava/lang/String;)V
    //   1739: new javax/sound/sampled/UnsupportedAudioFileException
    //   1742: dup
    //   1743: new java/lang/StringBuilder
    //   1746: dup
    //   1747: invokespecial <init> : ()V
    //   1750: ldc_w 'not a MPEG stream:'
    //   1753: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1756: aload #25
    //   1758: invokevirtual getMessage : ()Ljava/lang/String;
    //   1761: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   1764: invokevirtual toString : ()Ljava/lang/String;
    //   1767: invokespecial <init> : (Ljava/lang/String;)V
    //   1770: athrow
    //   1771: iload #21
    //   1773: bipush #19
    //   1775: ishr
    //   1776: iconst_3
    //   1777: iand
    //   1778: istore #25
    //   1780: iload #25
    //   1782: iconst_1
    //   1783: if_icmpne -> 1809
    //   1786: getstatic org/tritonus/share/TDebug.TraceAudioFileReader : Z
    //   1789: ifeq -> 1798
    //   1792: ldc_w 'not a MPEG stream: wrong version'
    //   1795: invokestatic out : (Ljava/lang/String;)V
    //   1798: new javax/sound/sampled/UnsupportedAudioFileException
    //   1801: dup
    //   1802: ldc_w 'not a MPEG stream: wrong version'
    //   1805: invokespecial <init> : (Ljava/lang/String;)V
    //   1808: athrow
    //   1809: iload #21
    //   1811: bipush #10
    //   1813: ishr
    //   1814: iconst_3
    //   1815: iand
    //   1816: istore #26
    //   1818: iload #26
    //   1820: iconst_3
    //   1821: if_icmpne -> 1847
    //   1824: getstatic org/tritonus/share/TDebug.TraceAudioFileReader : Z
    //   1827: ifeq -> 1836
    //   1830: ldc_w 'not a MPEG stream: wrong sampling rate'
    //   1833: invokestatic out : (Ljava/lang/String;)V
    //   1836: new javax/sound/sampled/UnsupportedAudioFileException
    //   1839: dup
    //   1840: ldc_w 'not a MPEG stream: wrong sampling rate'
    //   1843: invokespecial <init> : (Ljava/lang/String;)V
    //   1846: athrow
    //   1847: iload #7
    //   1849: i2l
    //   1850: lload_2
    //   1851: lcmp
    //   1852: ifne -> 1940
    //   1855: lload_2
    //   1856: ldc2_w -1
    //   1859: lcmp
    //   1860: ifeq -> 1940
    //   1863: aload_1
    //   1864: checkcast java/io/FileInputStream
    //   1867: astore #27
    //   1869: sipush #128
    //   1872: newarray byte
    //   1874: astore #28
    //   1876: aload #27
    //   1878: aload_1
    //   1879: invokevirtual available : ()I
    //   1882: aload #28
    //   1884: arraylength
    //   1885: isub
    //   1886: i2l
    //   1887: invokevirtual skip : (J)J
    //   1890: lstore #29
    //   1892: aload #27
    //   1894: aload #28
    //   1896: iconst_0
    //   1897: aload #28
    //   1899: arraylength
    //   1900: invokevirtual read : ([BII)I
    //   1903: istore #31
    //   1905: aload #28
    //   1907: iconst_0
    //   1908: baload
    //   1909: bipush #84
    //   1911: if_icmpne -> 1940
    //   1914: aload #28
    //   1916: iconst_1
    //   1917: baload
    //   1918: bipush #65
    //   1920: if_icmpne -> 1940
    //   1923: aload #28
    //   1925: iconst_2
    //   1926: baload
    //   1927: bipush #71
    //   1929: if_icmpne -> 1940
    //   1932: aload_0
    //   1933: aload #28
    //   1935: aload #4
    //   1937: invokevirtual parseID3v1Frames : ([BLjava/util/HashMap;)V
    //   1940: new javazoom/spi/mpeg/sampled/file/MpegAudioFormat
    //   1943: dup
    //   1944: aload #24
    //   1946: iload #16
    //   1948: i2f
    //   1949: iconst_m1
    //   1950: iload #20
    //   1952: iconst_m1
    //   1953: fload #18
    //   1955: iconst_1
    //   1956: aload #5
    //   1958: invokespecial <init> : (Ljavax/sound/sampled/AudioFormat$Encoding;FIIIFZLjava/util/Map;)V
    //   1961: astore #27
    //   1963: new javazoom/spi/mpeg/sampled/file/MpegAudioFileFormat
    //   1966: dup
    //   1967: getstatic javazoom/spi/mpeg/sampled/file/MpegFileFormatType.MP3 : Ljavax/sound/sampled/AudioFileFormat$Type;
    //   1970: aload #27
    //   1972: iload #17
    //   1974: iload #6
    //   1976: aload #4
    //   1978: invokespecial <init> : (Ljavax/sound/sampled/AudioFileFormat$Type;Ljavax/sound/sampled/AudioFormat;IILjava/util/Map;)V
    //   1981: areturn
    // Line number table:
    //   Java source line number -> byte code offset
    //   #305	-> 0
    //   #306	-> 11
    //   #307	-> 20
    //   #308	-> 29
    //   #309	-> 33
    //   #310	-> 39
    //   #311	-> 52
    //   #312	-> 58
    //   #313	-> 66
    //   #315	-> 72
    //   #320	-> 111
    //   #322	-> 187
    //   #323	-> 198
    //   #324	-> 221
    //   #326	-> 228
    //   #329	-> 244
    //   #330	-> 247
    //   #332	-> 283
    //   #333	-> 294
    //   #335	-> 311
    //   #337	-> 387
    //   #338	-> 398
    //   #340	-> 415
    //   #342	-> 511
    //   #343	-> 522
    //   #345	-> 539
    //   #347	-> 667
    //   #348	-> 678
    //   #351	-> 695
    //   #353	-> 791
    //   #355	-> 798
    //   #358	-> 809
    //   #360	-> 905
    //   #361	-> 916
    //   #366	-> 933
    //   #369	-> 940
    //   #370	-> 943
    //   #371	-> 946
    //   #372	-> 949
    //   #373	-> 952
    //   #374	-> 955
    //   #375	-> 958
    //   #376	-> 961
    //   #377	-> 964
    //   #378	-> 968
    //   #379	-> 971
    //   #380	-> 974
    //   #381	-> 977
    //   #382	-> 980
    //   #383	-> 983
    //   #386	-> 986
    //   #387	-> 997
    //   #388	-> 1004
    //   #389	-> 1021
    //   #391	-> 1028
    //   #392	-> 1035
    //   #393	-> 1057
    //   #395	-> 1072
    //   #396	-> 1079
    //   #397	-> 1093
    //   #398	-> 1100
    //   #399	-> 1107
    //   #400	-> 1125
    //   #401	-> 1138
    //   #402	-> 1156
    //   #403	-> 1163
    //   #404	-> 1181
    //   #405	-> 1199
    //   #406	-> 1220
    //   #407	-> 1227
    //   #408	-> 1245
    //   #409	-> 1279
    //   #410	-> 1286
    //   #411	-> 1304
    //   #412	-> 1319
    //   #413	-> 1337
    //   #415	-> 1373
    //   #416	-> 1377
    //   #417	-> 1402
    //   #419	-> 1408
    //   #420	-> 1426
    //   #421	-> 1435
    //   #423	-> 1453
    //   #424	-> 1460
    //   #425	-> 1478
    //   #426	-> 1496
    //   #427	-> 1503
    //   #428	-> 1517
    //   #429	-> 1531
    //   #431	-> 1537
    //   #432	-> 1549
    //   #434	-> 1572
    //   #435	-> 1593
    //   #436	-> 1614
    //   #437	-> 1635
    //   #438	-> 1656
    //   #439	-> 1663
    //   #441	-> 1668
    //   #442	-> 1679
    //   #444	-> 1687
    //   #450	-> 1701
    //   #446	-> 1704
    //   #448	-> 1706
    //   #449	-> 1739
    //   #452	-> 1771
    //   #453	-> 1780
    //   #455	-> 1786
    //   #456	-> 1798
    //   #458	-> 1809
    //   #459	-> 1818
    //   #461	-> 1824
    //   #462	-> 1836
    //   #465	-> 1847
    //   #467	-> 1863
    //   #468	-> 1869
    //   #469	-> 1876
    //   #470	-> 1892
    //   #471	-> 1905
    //   #473	-> 1932
    //   #476	-> 1940
    //   #481	-> 1963
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   221	23	10	isPCM	I
    //   997	704	25	m_bitstream	Ljavazoom/jl/decoder/Bitstream;
    //   1004	697	26	streamPos	I
    //   1028	673	27	m_header	Ljavazoom/jl/decoder/Header;
    //   1377	324	28	tmpLength	I
    //   1663	38	29	id3v2	Ljava/io/InputStream;
    //   1706	65	25	e	Ljava/lang/Exception;
    //   1869	71	27	fis	Ljava/io/FileInputStream;
    //   1876	64	28	id3v1	[B
    //   1892	48	29	bytesSkipped	J
    //   1905	35	31	read	I
    //   0	1982	0	this	Ljavazoom/spi/mpeg/sampled/file/MpegAudioFileReader;
    //   0	1982	1	inputStream	Ljava/io/InputStream;
    //   0	1982	2	mediaLength	J
    //   20	1962	4	aff_properties	Ljava/util/HashMap;
    //   29	1953	5	af_properties	Ljava/util/HashMap;
    //   33	1949	6	mLength	I
    //   39	1943	7	size	I
    //   52	1930	8	pis	Ljava/io/PushbackInputStream;
    //   58	1924	9	head	[B
    //   943	1039	10	nVersion	I
    //   946	1036	11	nLayer	I
    //   949	1033	12	nSFIndex	I
    //   952	1030	13	nMode	I
    //   955	1027	14	FrameSize	I
    //   958	1024	15	nFrameSize	I
    //   961	1021	16	nFrequency	I
    //   964	1018	17	nTotalFrames	I
    //   968	1014	18	FrameRate	F
    //   971	1011	19	BitRate	I
    //   974	1008	20	nChannels	I
    //   977	1005	21	nHeader	I
    //   980	1002	22	nTotalMS	I
    //   983	999	23	nVBR	Z
    //   986	996	24	encoding	Ljavax/sound/sampled/AudioFormat$Encoding;
    //   1780	202	25	cVersion	I
    //   1818	164	26	cSFIndex	I
    //   1963	19	27	format	Ljavax/sound/sampled/AudioFormat;
    // Exception table:
    //   from	to	target	type
    //   986	1701	1704	java/lang/Exception
  }
  
  public AudioInputStream getAudioInputStream(File file) throws UnsupportedAudioFileException, IOException {
    if (TDebug.TraceAudioFileReader)
      TDebug.out("getAudioInputStream(File file)"); 
    InputStream inputStream = new FileInputStream(file);
    try {
      return getAudioInputStream(inputStream);
    } catch (UnsupportedAudioFileException e) {
      if (inputStream != null)
        inputStream.close(); 
      throw e;
    } catch (IOException e) {
      if (inputStream != null)
        inputStream.close(); 
      throw e;
    } 
  }
  
  public AudioInputStream getAudioInputStream(URL url) throws UnsupportedAudioFileException, IOException {
    if (TDebug.TraceAudioFileReader)
      TDebug.out("MpegAudioFileReader.getAudioInputStream(URL): begin"); 
    long lFileLengthInBytes = -1L;
    URLConnection conn = url.openConnection();
    boolean isShout = false;
    int toRead = 4;
    byte[] head = new byte[toRead];
    conn.setRequestProperty("Icy-Metadata", "1");
    BufferedInputStream bInputStream = new BufferedInputStream(conn.getInputStream());
    bInputStream.mark(toRead);
    int read = bInputStream.read(head, 0, toRead);
    if (read > 2)
      if ((((head[0] == 73) ? 1 : 0) | ((head[0] == 105) ? 1 : 0)) != 0)
        if ((((head[1] == 67) ? 1 : 0) | ((head[1] == 99) ? 1 : 0)) != 0)
          if ((((head[2] == 89) ? 1 : 0) | ((head[2] == 121) ? 1 : 0)) != 0)
            isShout = true;    
    bInputStream.reset();
    InputStream inputStream = null;
    if (isShout == true) {
      IcyInputStream icyStream = new IcyInputStream(bInputStream);
      icyStream.addTagParseListener(IcyListener.getInstance());
      IcyInputStream icyInputStream1 = icyStream;
    } else {
      String metaint = conn.getHeaderField("icy-metaint");
      if (metaint != null) {
        IcyInputStream icyStream = new IcyInputStream(bInputStream, metaint);
        icyStream.addTagParseListener(IcyListener.getInstance());
        IcyInputStream icyInputStream1 = icyStream;
      } else {
        inputStream = bInputStream;
      } 
    } 
    AudioInputStream audioInputStream = null;
    try {
      audioInputStream = getAudioInputStream(inputStream, lFileLengthInBytes);
    } catch (UnsupportedAudioFileException e) {
      inputStream.close();
      throw e;
    } catch (IOException e) {
      inputStream.close();
      throw e;
    } 
    if (TDebug.TraceAudioFileReader)
      TDebug.out("MpegAudioFileReader.getAudioInputStream(URL): end"); 
    return audioInputStream;
  }
  
  public AudioInputStream getAudioInputStream(InputStream inputStream) throws UnsupportedAudioFileException, IOException {
    if (TDebug.TraceAudioFileReader)
      TDebug.out("MpegAudioFileReader.getAudioInputStream(InputStream inputStream)"); 
    if (!inputStream.markSupported())
      inputStream = new BufferedInputStream(inputStream); 
    return super.getAudioInputStream(inputStream);
  }
  
  protected void parseID3v1Frames(byte[] frames, HashMap props) {
    // Byte code:
    //   0: getstatic org/tritonus/share/TDebug.TraceAudioFileReader : Z
    //   3: ifeq -> 12
    //   6: ldc_w 'Parsing ID3v1'
    //   9: invokestatic out : (Ljava/lang/String;)V
    //   12: aconst_null
    //   13: astore_3
    //   14: new java/lang/String
    //   17: dup
    //   18: aload_1
    //   19: iconst_0
    //   20: aload_1
    //   21: arraylength
    //   22: ldc_w 'ISO-8859-1'
    //   25: invokespecial <init> : ([BIILjava/lang/String;)V
    //   28: astore_3
    //   29: goto -> 58
    //   32: astore #4
    //   34: new java/lang/String
    //   37: dup
    //   38: aload_1
    //   39: iconst_0
    //   40: aload_1
    //   41: arraylength
    //   42: invokespecial <init> : ([BII)V
    //   45: astore_3
    //   46: getstatic org/tritonus/share/TDebug.TraceAudioFileReader : Z
    //   49: ifeq -> 58
    //   52: ldc_w 'Cannot use ISO-8859-1'
    //   55: invokestatic out : (Ljava/lang/String;)V
    //   58: getstatic org/tritonus/share/TDebug.TraceAudioFileReader : Z
    //   61: ifeq -> 93
    //   64: new java/lang/StringBuilder
    //   67: dup
    //   68: invokespecial <init> : ()V
    //   71: ldc_w 'ID3v1 frame dump=''
    //   74: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   77: aload_3
    //   78: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   81: ldc_w '''
    //   84: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   87: invokevirtual toString : ()Ljava/lang/String;
    //   90: invokestatic out : (Ljava/lang/String;)V
    //   93: iconst_3
    //   94: istore #4
    //   96: aload_0
    //   97: aload_3
    //   98: iload #4
    //   100: iinc #4, 30
    //   103: iload #4
    //   105: invokespecial chopSubstring : (Ljava/lang/String;II)Ljava/lang/String;
    //   108: astore #5
    //   110: aload_2
    //   111: ldc_w 'title'
    //   114: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   117: checkcast java/lang/String
    //   120: astore #6
    //   122: aload #6
    //   124: ifnull -> 135
    //   127: aload #6
    //   129: invokevirtual length : ()I
    //   132: ifne -> 150
    //   135: aload #5
    //   137: ifnull -> 150
    //   140: aload_2
    //   141: ldc_w 'title'
    //   144: aload #5
    //   146: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   149: pop
    //   150: aload_0
    //   151: aload_3
    //   152: iload #4
    //   154: iinc #4, 30
    //   157: iload #4
    //   159: invokespecial chopSubstring : (Ljava/lang/String;II)Ljava/lang/String;
    //   162: astore #7
    //   164: aload_2
    //   165: ldc_w 'author'
    //   168: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   171: checkcast java/lang/String
    //   174: astore #8
    //   176: aload #8
    //   178: ifnull -> 189
    //   181: aload #8
    //   183: invokevirtual length : ()I
    //   186: ifne -> 204
    //   189: aload #7
    //   191: ifnull -> 204
    //   194: aload_2
    //   195: ldc_w 'author'
    //   198: aload #7
    //   200: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   203: pop
    //   204: aload_0
    //   205: aload_3
    //   206: iload #4
    //   208: iinc #4, 30
    //   211: iload #4
    //   213: invokespecial chopSubstring : (Ljava/lang/String;II)Ljava/lang/String;
    //   216: astore #9
    //   218: aload_2
    //   219: ldc_w 'album'
    //   222: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   225: checkcast java/lang/String
    //   228: astore #10
    //   230: aload #10
    //   232: ifnull -> 243
    //   235: aload #10
    //   237: invokevirtual length : ()I
    //   240: ifne -> 258
    //   243: aload #9
    //   245: ifnull -> 258
    //   248: aload_2
    //   249: ldc_w 'album'
    //   252: aload #9
    //   254: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   257: pop
    //   258: aload_0
    //   259: aload_3
    //   260: iload #4
    //   262: iinc #4, 4
    //   265: iload #4
    //   267: invokespecial chopSubstring : (Ljava/lang/String;II)Ljava/lang/String;
    //   270: astore #11
    //   272: aload_2
    //   273: ldc_w 'year'
    //   276: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   279: checkcast java/lang/String
    //   282: astore #12
    //   284: aload #12
    //   286: ifnull -> 297
    //   289: aload #12
    //   291: invokevirtual length : ()I
    //   294: ifne -> 312
    //   297: aload #11
    //   299: ifnull -> 312
    //   302: aload_2
    //   303: ldc_w 'date'
    //   306: aload #11
    //   308: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   311: pop
    //   312: aload_0
    //   313: aload_3
    //   314: iload #4
    //   316: iinc #4, 28
    //   319: iload #4
    //   321: invokespecial chopSubstring : (Ljava/lang/String;II)Ljava/lang/String;
    //   324: astore #13
    //   326: aload_2
    //   327: ldc_w 'comment'
    //   330: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   333: checkcast java/lang/String
    //   336: astore #14
    //   338: aload #14
    //   340: ifnull -> 351
    //   343: aload #14
    //   345: invokevirtual length : ()I
    //   348: ifne -> 366
    //   351: aload #13
    //   353: ifnull -> 366
    //   356: aload_2
    //   357: ldc_w 'comment'
    //   360: aload #13
    //   362: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   365: pop
    //   366: new java/lang/StringBuilder
    //   369: dup
    //   370: invokespecial <init> : ()V
    //   373: ldc_w ''
    //   376: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   379: aload_1
    //   380: bipush #126
    //   382: baload
    //   383: sipush #255
    //   386: iand
    //   387: invokevirtual append : (I)Ljava/lang/StringBuilder;
    //   390: invokevirtual toString : ()Ljava/lang/String;
    //   393: astore #15
    //   395: aload_2
    //   396: ldc_w 'mp3.id3tag.track'
    //   399: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   402: checkcast java/lang/String
    //   405: astore #16
    //   407: aload #16
    //   409: ifnull -> 420
    //   412: aload #16
    //   414: invokevirtual length : ()I
    //   417: ifne -> 435
    //   420: aload #15
    //   422: ifnull -> 435
    //   425: aload_2
    //   426: ldc_w 'mp3.id3tag.track'
    //   429: aload #15
    //   431: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   434: pop
    //   435: aload_1
    //   436: bipush #127
    //   438: baload
    //   439: sipush #255
    //   442: iand
    //   443: istore #17
    //   445: iload #17
    //   447: iflt -> 498
    //   450: iload #17
    //   452: getstatic javazoom/spi/mpeg/sampled/file/MpegAudioFileReader.id3v1genres : [Ljava/lang/String;
    //   455: arraylength
    //   456: if_icmpge -> 498
    //   459: aload_2
    //   460: ldc_w 'mp3.id3tag.genre'
    //   463: invokevirtual get : (Ljava/lang/Object;)Ljava/lang/Object;
    //   466: checkcast java/lang/String
    //   469: astore #18
    //   471: aload #18
    //   473: ifnull -> 484
    //   476: aload #18
    //   478: invokevirtual length : ()I
    //   481: ifne -> 498
    //   484: aload_2
    //   485: ldc_w 'mp3.id3tag.genre'
    //   488: getstatic javazoom/spi/mpeg/sampled/file/MpegAudioFileReader.id3v1genres : [Ljava/lang/String;
    //   491: iload #17
    //   493: aaload
    //   494: invokevirtual put : (Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   497: pop
    //   498: getstatic org/tritonus/share/TDebug.TraceAudioFileReader : Z
    //   501: ifeq -> 510
    //   504: ldc_w 'ID3v1 parsed'
    //   507: invokestatic out : (Ljava/lang/String;)V
    //   510: return
    // Line number table:
    //   Java source line number -> byte code offset
    //   #593	-> 0
    //   #594	-> 12
    //   #597	-> 14
    //   #603	-> 29
    //   #599	-> 32
    //   #601	-> 34
    //   #602	-> 46
    //   #604	-> 58
    //   #605	-> 93
    //   #606	-> 96
    //   #607	-> 110
    //   #608	-> 122
    //   #609	-> 150
    //   #610	-> 164
    //   #611	-> 176
    //   #612	-> 204
    //   #613	-> 218
    //   #614	-> 230
    //   #615	-> 258
    //   #616	-> 272
    //   #617	-> 284
    //   #618	-> 312
    //   #619	-> 326
    //   #620	-> 338
    //   #621	-> 366
    //   #622	-> 395
    //   #623	-> 407
    //   #624	-> 435
    //   #625	-> 445
    //   #627	-> 459
    //   #628	-> 471
    //   #630	-> 498
    //   #631	-> 510
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   34	24	4	e	Ljava/io/UnsupportedEncodingException;
    //   471	27	18	genrev2	Ljava/lang/String;
    //   0	511	0	this	Ljavazoom/spi/mpeg/sampled/file/MpegAudioFileReader;
    //   0	511	1	frames	[B
    //   0	511	2	props	Ljava/util/HashMap;
    //   14	497	3	tag	Ljava/lang/String;
    //   96	415	4	start	I
    //   110	401	5	titlev1	Ljava/lang/String;
    //   122	389	6	titlev2	Ljava/lang/String;
    //   164	347	7	artistv1	Ljava/lang/String;
    //   176	335	8	artistv2	Ljava/lang/String;
    //   218	293	9	albumv1	Ljava/lang/String;
    //   230	281	10	albumv2	Ljava/lang/String;
    //   272	239	11	yearv1	Ljava/lang/String;
    //   284	227	12	yearv2	Ljava/lang/String;
    //   326	185	13	commentv1	Ljava/lang/String;
    //   338	173	14	commentv2	Ljava/lang/String;
    //   395	116	15	trackv1	Ljava/lang/String;
    //   407	104	16	trackv2	Ljava/lang/String;
    //   445	66	17	genrev1	I
    // Exception table:
    //   from	to	target	type
    //   14	29	32	java/io/UnsupportedEncodingException
  }
  
  private String chopSubstring(String s, int start, int end) {
    String str = null;
    try {
      str = s.substring(start, end);
      int loc = str.indexOf(false);
      if (loc != -1)
        str = str.substring(0, loc); 
    } catch (StringIndexOutOfBoundsException e) {
      if (TDebug.TraceAudioFileReader)
        TDebug.out("Cannot chopSubString " + e.getMessage()); 
    } 
    return str;
  }
  
  protected void parseID3v2Frames(InputStream frames, HashMap<String, String> props) {
    if (TDebug.TraceAudioFileReader)
      TDebug.out("Parsing ID3v2"); 
    byte[] bframes = null;
    int size = -1;
    try {
      size = frames.available();
      bframes = new byte[size];
      frames.mark(size);
      frames.read(bframes);
      frames.reset();
    } catch (IOException e) {
      if (TDebug.TraceAudioFileReader)
        TDebug.out("Cannot parse ID3v2 :" + e.getMessage()); 
    } 
    if (!"ID3".equals(new String(bframes, 0, 3))) {
      TDebug.out("No ID3v2 header found!");
      return;
    } 
    int v2version = bframes[3] & 0xFF;
    props.put("mp3.id3tag.v2.version", String.valueOf(v2version));
    if (v2version < 2 || v2version > 4) {
      TDebug.out("Unsupported ID3v2 version " + v2version + "!");
      return;
    } 
    try {
      if (TDebug.TraceAudioFileReader)
        TDebug.out("ID3v2 frame dump='" + new String(bframes, 0, bframes.length) + "'"); 
      String value = null;
      int i;
      for (i = 10; i < bframes.length && bframes[i] > 0; i += size) {
        if (v2version == 3 || v2version == 4) {
          String code = new String(bframes, i, 4);
          size = bframes[i + 4] << 24 & 0xFF000000 | bframes[i + 5] << 16 & 0xFF0000 | bframes[i + 6] << 8 & 0xFF00 | bframes[i + 7] & 0xFF;
          i += 10;
          if (code.equals("TALB") || code.equals("TIT2") || code.equals("TYER") || code
            .equals("TPE1") || code.equals("TCOP") || code.equals("COMM") || code
            .equals("TCON") || code.equals("TRCK") || code.equals("TPOS") || code
            .equals("TDRC") || code.equals("TCOM") || code.equals("TIT1") || code
            .equals("TENC") || code.equals("TPUB") || code.equals("TPE2") || code
            .equals("TLEN")) {
            if (code.equals("COMM")) {
              value = parseText(bframes, i, size, 5);
            } else {
              value = parseText(bframes, i, size, 1);
            } 
            if (value != null && value.length() > 0)
              if (code.equals("TALB")) {
                props.put("album", value);
              } else if (code.equals("TIT2")) {
                props.put("title", value);
              } else if (code.equals("TYER")) {
                props.put("date", value);
              } else if (code.equals("TDRC")) {
                props.put("date", value);
              } else if (code.equals("TPE1")) {
                props.put("author", value);
              } else if (code.equals("TCOP")) {
                props.put("copyright", value);
              } else if (code.equals("COMM")) {
                props.put("comment", value);
              } else if (code.equals("TCON")) {
                props.put("mp3.id3tag.genre", value);
              } else if (code.equals("TRCK")) {
                props.put("mp3.id3tag.track", value);
              } else if (code.equals("TPOS")) {
                props.put("mp3.id3tag.disc", value);
              } else if (code.equals("TCOM")) {
                props.put("mp3.id3tag.composer", value);
              } else if (code.equals("TIT1")) {
                props.put("mp3.id3tag.grouping", value);
              } else if (code.equals("TENC")) {
                props.put("mp3.id3tag.encoded", value);
              } else if (code.equals("TPUB")) {
                props.put("mp3.id3tag.publisher", value);
              } else if (code.equals("TPE2")) {
                props.put("mp3.id3tag.orchestra", value);
              } else if (code.equals("TLEN")) {
                props.put("mp3.id3tag.length", value);
              }  
          } 
        } else {
          String scode = new String(bframes, i, 3);
          size = 0 + (bframes[i + 3] << 16) + (bframes[i + 4] << 8) + bframes[i + 5];
          i += 6;
          if (scode.equals("TAL") || scode.equals("TT2") || scode.equals("TP1") || scode
            .equals("TYE") || scode.equals("TRK") || scode.equals("TPA") || scode
            .equals("TCR") || scode.equals("TCO") || scode.equals("TCM") || scode
            .equals("COM") || scode.equals("TT1") || scode.equals("TEN") || scode
            .equals("TPB") || scode.equals("TP2") || scode.equals("TLE")) {
            if (scode.equals("COM")) {
              value = parseText(bframes, i, size, 5);
            } else {
              value = parseText(bframes, i, size, 1);
            } 
            if (value != null && value.length() > 0)
              if (scode.equals("TAL")) {
                props.put("album", value);
              } else if (scode.equals("TT2")) {
                props.put("title", value);
              } else if (scode.equals("TYE")) {
                props.put("date", value);
              } else if (scode.equals("TP1")) {
                props.put("author", value);
              } else if (scode.equals("TCR")) {
                props.put("copyright", value);
              } else if (scode.equals("COM")) {
                props.put("comment", value);
              } else if (scode.equals("TCO")) {
                props.put("mp3.id3tag.genre", value);
              } else if (scode.equals("TRK")) {
                props.put("mp3.id3tag.track", value);
              } else if (scode.equals("TPA")) {
                props.put("mp3.id3tag.disc", value);
              } else if (scode.equals("TCM")) {
                props.put("mp3.id3tag.composer", value);
              } else if (scode.equals("TT1")) {
                props.put("mp3.id3tag.grouping", value);
              } else if (scode.equals("TEN")) {
                props.put("mp3.id3tag.encoded", value);
              } else if (scode.equals("TPB")) {
                props.put("mp3.id3tag.publisher", value);
              } else if (scode.equals("TP2")) {
                props.put("mp3.id3tag.orchestra", value);
              } else if (scode.equals("TLE")) {
                props.put("mp3.id3tag.length", value);
              }  
          } 
        } 
      } 
    } catch (RuntimeException e) {
      if (TDebug.TraceAudioFileReader)
        TDebug.out("Cannot parse ID3v2 :" + e.getMessage()); 
    } 
    if (TDebug.TraceAudioFileReader)
      TDebug.out("ID3v2 parsed"); 
  }
  
  protected String parseText(byte[] bframes, int offset, int size, int skip) {
    String value = null;
    try {
      String[] ENC_TYPES = { "ISO-8859-1", "UTF16", "UTF-16BE", "UTF-8" };
      value = new String(bframes, offset + skip, size - skip, ENC_TYPES[bframes[offset]]);
      value = chopSubstring(value, 0, value.length());
    } catch (UnsupportedEncodingException e) {
      if (TDebug.TraceAudioFileReader)
        TDebug.out("ID3v2 Encoding error :" + e.getMessage()); 
    } 
    return value;
  }
  
  protected void loadShoutcastInfo(InputStream input, HashMap<String, String> props) throws IOException {
    IcyInputStream icy = new IcyInputStream(new BufferedInputStream(input));
    HashMap metadata = icy.getTagHash();
    MP3Tag titleMP3Tag = icy.getTag("icy-name");
    if (titleMP3Tag != null)
      props.put("title", ((String)titleMP3Tag.getValue()).trim()); 
    MP3Tag[] meta = icy.getTags();
    if (meta != null) {
      StringBuffer metaStr = new StringBuffer();
      for (int i = 0; i < meta.length; i++) {
        String key = meta[i].getName();
        String value = ((String)icy.getTag(key).getValue()).trim();
        props.put("mp3.shoutcast.metadata." + key, value);
      } 
    } 
  }
}
