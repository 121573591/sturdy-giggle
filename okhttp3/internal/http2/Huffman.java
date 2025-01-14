package okhttp3.internal.http2;

import java.io.IOException;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000P\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\b\n\002\b\003\n\002\020\002\n\002\b\002\n\002\030\002\n\000\n\002\020\t\n\000\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\005\n\002\020\025\n\002\b\002\n\002\020\022\n\002\b\002\n\002\030\002\n\002\b\004\bÆ\002\030\0002\0020\001:\001\"B\t\b\002¢\006\004\b\002\020\003J'\020\t\032\0020\b2\006\020\005\032\0020\0042\006\020\006\032\0020\0042\006\020\007\032\0020\004H\002¢\006\004\b\t\020\nJ%\020\021\032\0020\b2\006\020\f\032\0020\0132\006\020\016\032\0020\r2\006\020\020\032\0020\017¢\006\004\b\021\020\022J\035\020\024\032\0020\b2\006\020\f\032\0020\0232\006\020\020\032\0020\017¢\006\004\b\024\020\025J\025\020\027\032\0020\0042\006\020\026\032\0020\023¢\006\004\b\027\020\030R\024\020\032\032\0020\0318\002X\004¢\006\006\n\004\b\032\020\033R\024\020\035\032\0020\0348\002X\004¢\006\006\n\004\b\035\020\036R\024\020 \032\0020\0378\002X\004¢\006\006\n\004\b \020!¨\006#"}, d2 = {"Lokhttp3/internal/http2/Huffman;", "", "<init>", "()V", "", "symbol", "code", "codeBitCount", "", "addCode", "(III)V", "Lokio/BufferedSource;", "source", "", "byteCount", "Lokio/BufferedSink;", "sink", "decode", "(Lokio/BufferedSource;JLokio/BufferedSink;)V", "Lokio/ByteString;", "encode", "(Lokio/ByteString;Lokio/BufferedSink;)V", "bytes", "encodedLength", "(Lokio/ByteString;)I", "", "CODES", "[I", "", "CODE_BIT_COUNTS", "[B", "Lokhttp3/internal/http2/Huffman$Node;", "root", "Lokhttp3/internal/http2/Huffman$Node;", "Node", "okhttp"})
public final class Huffman {
  @NotNull
  public static final Huffman INSTANCE = new Huffman();
  
  @NotNull
  private static final int[] CODES;
  
  @NotNull
  private static final byte[] CODE_BIT_COUNTS;
  
  static {
    int[] arrayOfInt = new int[256];
    arrayOfInt[0] = 8184;
    arrayOfInt[1] = 8388568;
    arrayOfInt[2] = 268435426;
    arrayOfInt[3] = 268435427;
    arrayOfInt[4] = 268435428;
    arrayOfInt[5] = 268435429;
    arrayOfInt[6] = 268435430;
    arrayOfInt[7] = 
      268435431;
    arrayOfInt[8] = 268435432;
    arrayOfInt[9] = 16777194;
    arrayOfInt[10] = 1073741820;
    arrayOfInt[11] = 268435433;
    arrayOfInt[12] = 268435434;
    arrayOfInt[13] = 1073741821;
    arrayOfInt[14] = 268435435;
    arrayOfInt[15] = 268435436;
    arrayOfInt[16] = 268435437;
    arrayOfInt[17] = 268435438;
    arrayOfInt[18] = 268435439;
    arrayOfInt[19] = 268435440;
    arrayOfInt[20] = 268435441;
    arrayOfInt[21] = 268435442;
    arrayOfInt[22] = 1073741822;
    arrayOfInt[23] = 268435443;
    arrayOfInt[24] = 268435444;
    arrayOfInt[25] = 268435445;
    arrayOfInt[26] = 268435446;
    arrayOfInt[27] = 268435447;
    arrayOfInt[28] = 268435448;
    arrayOfInt[29] = 268435449;
    arrayOfInt[30] = 268435450;
    arrayOfInt[31] = 268435451;
    arrayOfInt[32] = 20;
    arrayOfInt[33] = 1016;
    arrayOfInt[34] = 1017;
    arrayOfInt[35] = 4090;
    arrayOfInt[36] = 8185;
    arrayOfInt[37] = 21;
    arrayOfInt[38] = 248;
    arrayOfInt[39] = 2042;
    arrayOfInt[40] = 1018;
    arrayOfInt[41] = 1019;
    arrayOfInt[42] = 249;
    arrayOfInt[43] = 2043;
    arrayOfInt[44] = 250;
    arrayOfInt[45] = 22;
    arrayOfInt[46] = 23;
    arrayOfInt[47] = 24;
    arrayOfInt[48] = 0;
    arrayOfInt[49] = 1;
    arrayOfInt[50] = 2;
    arrayOfInt[51] = 25;
    arrayOfInt[52] = 26;
    arrayOfInt[53] = 27;
    arrayOfInt[54] = 28;
    arrayOfInt[55] = 29;
    arrayOfInt[56] = 30;
    arrayOfInt[57] = 31;
    arrayOfInt[58] = 92;
    arrayOfInt[59] = 251;
    arrayOfInt[60] = 32764;
    arrayOfInt[61] = 32;
    arrayOfInt[62] = 4091;
    arrayOfInt[63] = 1020;
    arrayOfInt[64] = 8186;
    arrayOfInt[65] = 33;
    arrayOfInt[66] = 93;
    arrayOfInt[67] = 94;
    arrayOfInt[68] = 95;
    arrayOfInt[69] = 96;
    arrayOfInt[70] = 97;
    arrayOfInt[71] = 98;
    arrayOfInt[72] = 99;
    arrayOfInt[73] = 100;
    arrayOfInt[74] = 101;
    arrayOfInt[75] = 102;
    arrayOfInt[76] = 103;
    arrayOfInt[77] = 104;
    arrayOfInt[78] = 105;
    arrayOfInt[79] = 106;
    arrayOfInt[80] = 107;
    arrayOfInt[81] = 108;
    arrayOfInt[82] = 109;
    arrayOfInt[83] = 110;
    arrayOfInt[84] = 111;
    arrayOfInt[85] = 112;
    arrayOfInt[86] = 113;
    arrayOfInt[87] = 114;
    arrayOfInt[88] = 252;
    arrayOfInt[89] = 115;
    arrayOfInt[90] = 253;
    arrayOfInt[91] = 8187;
    arrayOfInt[92] = 524272;
    arrayOfInt[93] = 8188;
    arrayOfInt[94] = 16380;
    arrayOfInt[95] = 34;
    arrayOfInt[96] = 32765;
    arrayOfInt[97] = 3;
    arrayOfInt[98] = 35;
    arrayOfInt[99] = 4;
    arrayOfInt[100] = 36;
    arrayOfInt[101] = 5;
    arrayOfInt[102] = 37;
    arrayOfInt[103] = 38;
    arrayOfInt[104] = 39;
    arrayOfInt[105] = 6;
    arrayOfInt[106] = 116;
    arrayOfInt[107] = 117;
    arrayOfInt[108] = 40;
    arrayOfInt[109] = 41;
    arrayOfInt[110] = 42;
    arrayOfInt[111] = 7;
    arrayOfInt[112] = 43;
    arrayOfInt[113] = 118;
    arrayOfInt[114] = 44;
    arrayOfInt[115] = 8;
    arrayOfInt[116] = 9;
    arrayOfInt[117] = 45;
    arrayOfInt[118] = 119;
    arrayOfInt[119] = 120;
    arrayOfInt[120] = 121;
    arrayOfInt[121] = 122;
    arrayOfInt[122] = 123;
    arrayOfInt[123] = 32766;
    arrayOfInt[124] = 2044;
    arrayOfInt[125] = 16381;
    arrayOfInt[126] = 8189;
    arrayOfInt[127] = 268435452;
    arrayOfInt[128] = 1048550;
    arrayOfInt[129] = 4194258;
    arrayOfInt[130] = 1048551;
    arrayOfInt[131] = 1048552;
    arrayOfInt[132] = 4194259;
    arrayOfInt[133] = 4194260;
    arrayOfInt[134] = 4194261;
    arrayOfInt[135] = 8388569;
    arrayOfInt[136] = 4194262;
    arrayOfInt[137] = 8388570;
    arrayOfInt[138] = 8388571;
    arrayOfInt[139] = 8388572;
    arrayOfInt[140] = 8388573;
    arrayOfInt[141] = 8388574;
    arrayOfInt[142] = 16777195;
    arrayOfInt[143] = 8388575;
    arrayOfInt[144] = 16777196;
    arrayOfInt[145] = 16777197;
    arrayOfInt[146] = 4194263;
    arrayOfInt[147] = 8388576;
    arrayOfInt[148] = 16777198;
    arrayOfInt[149] = 8388577;
    arrayOfInt[150] = 8388578;
    arrayOfInt[151] = 8388579;
    arrayOfInt[152] = 8388580;
    arrayOfInt[153] = 2097116;
    arrayOfInt[154] = 4194264;
    arrayOfInt[155] = 8388581;
    arrayOfInt[156] = 4194265;
    arrayOfInt[157] = 8388582;
    arrayOfInt[158] = 8388583;
    arrayOfInt[159] = 16777199;
    arrayOfInt[160] = 4194266;
    arrayOfInt[161] = 2097117;
    arrayOfInt[162] = 1048553;
    arrayOfInt[163] = 4194267;
    arrayOfInt[164] = 4194268;
    arrayOfInt[165] = 8388584;
    arrayOfInt[166] = 8388585;
    arrayOfInt[167] = 2097118;
    arrayOfInt[168] = 8388586;
    arrayOfInt[169] = 4194269;
    arrayOfInt[170] = 4194270;
    arrayOfInt[171] = 16777200;
    arrayOfInt[172] = 2097119;
    arrayOfInt[173] = 4194271;
    arrayOfInt[174] = 8388587;
    arrayOfInt[175] = 8388588;
    arrayOfInt[176] = 2097120;
    arrayOfInt[177] = 2097121;
    arrayOfInt[178] = 4194272;
    arrayOfInt[179] = 2097122;
    arrayOfInt[180] = 8388589;
    arrayOfInt[181] = 4194273;
    arrayOfInt[182] = 8388590;
    arrayOfInt[183] = 8388591;
    arrayOfInt[184] = 1048554;
    arrayOfInt[185] = 4194274;
    arrayOfInt[186] = 4194275;
    arrayOfInt[187] = 4194276;
    arrayOfInt[188] = 8388592;
    arrayOfInt[189] = 4194277;
    arrayOfInt[190] = 4194278;
    arrayOfInt[191] = 8388593;
    arrayOfInt[192] = 67108832;
    arrayOfInt[193] = 67108833;
    arrayOfInt[194] = 1048555;
    arrayOfInt[195] = 524273;
    arrayOfInt[196] = 4194279;
    arrayOfInt[197] = 8388594;
    arrayOfInt[198] = 4194280;
    arrayOfInt[199] = 33554412;
    arrayOfInt[200] = 67108834;
    arrayOfInt[201] = 67108835;
    arrayOfInt[202] = 67108836;
    arrayOfInt[203] = 134217694;
    arrayOfInt[204] = 134217695;
    arrayOfInt[205] = 67108837;
    arrayOfInt[206] = 16777201;
    arrayOfInt[207] = 33554413;
    arrayOfInt[208] = 524274;
    arrayOfInt[209] = 2097123;
    arrayOfInt[210] = 67108838;
    arrayOfInt[211] = 134217696;
    arrayOfInt[212] = 134217697;
    arrayOfInt[213] = 67108839;
    arrayOfInt[214] = 134217698;
    arrayOfInt[215] = 16777202;
    arrayOfInt[216] = 2097124;
    arrayOfInt[217] = 2097125;
    arrayOfInt[218] = 67108840;
    arrayOfInt[219] = 67108841;
    arrayOfInt[220] = 268435453;
    arrayOfInt[221] = 134217699;
    arrayOfInt[222] = 134217700;
    arrayOfInt[223] = 134217701;
    arrayOfInt[224] = 1048556;
    arrayOfInt[225] = 16777203;
    arrayOfInt[226] = 1048557;
    arrayOfInt[227] = 2097126;
    arrayOfInt[228] = 4194281;
    arrayOfInt[229] = 2097127;
    arrayOfInt[230] = 2097128;
    arrayOfInt[231] = 8388595;
    arrayOfInt[232] = 4194282;
    arrayOfInt[233] = 4194283;
    arrayOfInt[234] = 33554414;
    arrayOfInt[235] = 33554415;
    arrayOfInt[236] = 16777204;
    arrayOfInt[237] = 16777205;
    arrayOfInt[238] = 67108842;
    arrayOfInt[239] = 8388596;
    arrayOfInt[240] = 67108843;
    arrayOfInt[241] = 134217702;
    arrayOfInt[242] = 67108844;
    arrayOfInt[243] = 67108845;
    arrayOfInt[244] = 134217703;
    arrayOfInt[245] = 134217704;
    arrayOfInt[246] = 134217705;
    arrayOfInt[247] = 134217706;
    arrayOfInt[248] = 134217707;
    arrayOfInt[249] = 268435454;
    arrayOfInt[250] = 134217708;
    arrayOfInt[251] = 134217709;
    arrayOfInt[252] = 134217710;
    arrayOfInt[253] = 134217711;
    arrayOfInt[254] = 134217712;
    arrayOfInt[255] = 67108846;
    CODES = arrayOfInt;
    byte[] arrayOfByte = new byte[256];
    arrayOfByte[0] = 13;
    arrayOfByte[1] = 23;
    arrayOfByte[2] = 28;
    arrayOfByte[3] = 28;
    arrayOfByte[4] = 28;
    arrayOfByte[5] = 28;
    arrayOfByte[6] = 28;
    arrayOfByte[7] = 28;
    arrayOfByte[8] = 28;
    arrayOfByte[9] = 24;
    arrayOfByte[10] = 30;
    arrayOfByte[11] = 28;
    arrayOfByte[12] = 28;
    arrayOfByte[13] = 30;
    arrayOfByte[14] = 28;
    arrayOfByte[15] = 28;
    arrayOfByte[16] = 28;
    arrayOfByte[17] = 28;
    arrayOfByte[18] = 28;
    arrayOfByte[19] = 28;
    arrayOfByte[20] = 
      28;
    arrayOfByte[21] = 28;
    arrayOfByte[22] = 30;
    arrayOfByte[23] = 28;
    arrayOfByte[24] = 28;
    arrayOfByte[25] = 28;
    arrayOfByte[26] = 28;
    arrayOfByte[27] = 28;
    arrayOfByte[28] = 28;
    arrayOfByte[29] = 28;
    arrayOfByte[30] = 28;
    arrayOfByte[31] = 28;
    arrayOfByte[32] = 6;
    arrayOfByte[33] = 10;
    arrayOfByte[34] = 10;
    arrayOfByte[35] = 12;
    arrayOfByte[36] = 13;
    arrayOfByte[37] = 6;
    arrayOfByte[38] = 8;
    arrayOfByte[39] = 11;
    arrayOfByte[40] = 10;
    arrayOfByte[41] = 10;
    arrayOfByte[42] = 8;
    arrayOfByte[43] = 11;
    arrayOfByte[44] = 8;
    arrayOfByte[45] = 6;
    arrayOfByte[46] = 6;
    arrayOfByte[47] = 6;
    arrayOfByte[48] = 5;
    arrayOfByte[49] = 5;
    arrayOfByte[50] = 5;
    arrayOfByte[51] = 6;
    arrayOfByte[52] = 6;
    arrayOfByte[53] = 6;
    arrayOfByte[54] = 6;
    arrayOfByte[55] = 6;
    arrayOfByte[56] = 6;
    arrayOfByte[57] = 6;
    arrayOfByte[58] = 7;
    arrayOfByte[59] = 8;
    arrayOfByte[60] = 15;
    arrayOfByte[61] = 6;
    arrayOfByte[62] = 12;
    arrayOfByte[63] = 10;
    arrayOfByte[64] = 13;
    arrayOfByte[65] = 6;
    arrayOfByte[66] = 7;
    arrayOfByte[67] = 7;
    arrayOfByte[68] = 7;
    arrayOfByte[69] = 7;
    arrayOfByte[70] = 7;
    arrayOfByte[71] = 7;
    arrayOfByte[72] = 7;
    arrayOfByte[73] = 7;
    arrayOfByte[74] = 7;
    arrayOfByte[75] = 7;
    arrayOfByte[76] = 7;
    arrayOfByte[77] = 7;
    arrayOfByte[78] = 7;
    arrayOfByte[79] = 7;
    arrayOfByte[80] = 7;
    arrayOfByte[81] = 7;
    arrayOfByte[82] = 7;
    arrayOfByte[83] = 7;
    arrayOfByte[84] = 7;
    arrayOfByte[85] = 7;
    arrayOfByte[86] = 7;
    arrayOfByte[87] = 7;
    arrayOfByte[88] = 8;
    arrayOfByte[89] = 7;
    arrayOfByte[90] = 8;
    arrayOfByte[91] = 13;
    arrayOfByte[92] = 19;
    arrayOfByte[93] = 13;
    arrayOfByte[94] = 14;
    arrayOfByte[95] = 6;
    arrayOfByte[96] = 15;
    arrayOfByte[97] = 5;
    arrayOfByte[98] = 6;
    arrayOfByte[99] = 5;
    arrayOfByte[100] = 6;
    arrayOfByte[101] = 5;
    arrayOfByte[102] = 6;
    arrayOfByte[103] = 6;
    arrayOfByte[104] = 6;
    arrayOfByte[105] = 5;
    arrayOfByte[106] = 7;
    arrayOfByte[107] = 7;
    arrayOfByte[108] = 6;
    arrayOfByte[109] = 6;
    arrayOfByte[110] = 6;
    arrayOfByte[111] = 5;
    arrayOfByte[112] = 6;
    arrayOfByte[113] = 7;
    arrayOfByte[114] = 6;
    arrayOfByte[115] = 5;
    arrayOfByte[116] = 5;
    arrayOfByte[117] = 6;
    arrayOfByte[118] = 7;
    arrayOfByte[119] = 7;
    arrayOfByte[120] = 7;
    arrayOfByte[121] = 7;
    arrayOfByte[122] = 7;
    arrayOfByte[123] = 15;
    arrayOfByte[124] = 11;
    arrayOfByte[125] = 14;
    arrayOfByte[126] = 13;
    arrayOfByte[127] = 28;
    arrayOfByte[128] = 20;
    arrayOfByte[129] = 22;
    arrayOfByte[130] = 20;
    arrayOfByte[131] = 20;
    arrayOfByte[132] = 22;
    arrayOfByte[133] = 22;
    arrayOfByte[134] = 22;
    arrayOfByte[135] = 23;
    arrayOfByte[136] = 22;
    arrayOfByte[137] = 23;
    arrayOfByte[138] = 23;
    arrayOfByte[139] = 23;
    arrayOfByte[140] = 23;
    arrayOfByte[141] = 23;
    arrayOfByte[142] = 24;
    arrayOfByte[143] = 23;
    arrayOfByte[144] = 24;
    arrayOfByte[145] = 24;
    arrayOfByte[146] = 22;
    arrayOfByte[147] = 23;
    arrayOfByte[148] = 24;
    arrayOfByte[149] = 23;
    arrayOfByte[150] = 23;
    arrayOfByte[151] = 23;
    arrayOfByte[152] = 23;
    arrayOfByte[153] = 21;
    arrayOfByte[154] = 22;
    arrayOfByte[155] = 23;
    arrayOfByte[156] = 22;
    arrayOfByte[157] = 23;
    arrayOfByte[158] = 23;
    arrayOfByte[159] = 24;
    arrayOfByte[160] = 22;
    arrayOfByte[161] = 21;
    arrayOfByte[162] = 20;
    arrayOfByte[163] = 22;
    arrayOfByte[164] = 22;
    arrayOfByte[165] = 23;
    arrayOfByte[166] = 23;
    arrayOfByte[167] = 21;
    arrayOfByte[168] = 23;
    arrayOfByte[169] = 22;
    arrayOfByte[170] = 22;
    arrayOfByte[171] = 24;
    arrayOfByte[172] = 21;
    arrayOfByte[173] = 22;
    arrayOfByte[174] = 23;
    arrayOfByte[175] = 23;
    arrayOfByte[176] = 21;
    arrayOfByte[177] = 21;
    arrayOfByte[178] = 22;
    arrayOfByte[179] = 21;
    arrayOfByte[180] = 23;
    arrayOfByte[181] = 22;
    arrayOfByte[182] = 23;
    arrayOfByte[183] = 23;
    arrayOfByte[184] = 20;
    arrayOfByte[185] = 22;
    arrayOfByte[186] = 22;
    arrayOfByte[187] = 22;
    arrayOfByte[188] = 23;
    arrayOfByte[189] = 22;
    arrayOfByte[190] = 22;
    arrayOfByte[191] = 23;
    arrayOfByte[192] = 26;
    arrayOfByte[193] = 26;
    arrayOfByte[194] = 20;
    arrayOfByte[195] = 19;
    arrayOfByte[196] = 22;
    arrayOfByte[197] = 23;
    arrayOfByte[198] = 22;
    arrayOfByte[199] = 25;
    arrayOfByte[200] = 26;
    arrayOfByte[201] = 26;
    arrayOfByte[202] = 26;
    arrayOfByte[203] = 27;
    arrayOfByte[204] = 27;
    arrayOfByte[205] = 26;
    arrayOfByte[206] = 24;
    arrayOfByte[207] = 25;
    arrayOfByte[208] = 19;
    arrayOfByte[209] = 21;
    arrayOfByte[210] = 26;
    arrayOfByte[211] = 27;
    arrayOfByte[212] = 27;
    arrayOfByte[213] = 26;
    arrayOfByte[214] = 27;
    arrayOfByte[215] = 24;
    arrayOfByte[216] = 21;
    arrayOfByte[217] = 21;
    arrayOfByte[218] = 26;
    arrayOfByte[219] = 26;
    arrayOfByte[220] = 28;
    arrayOfByte[221] = 27;
    arrayOfByte[222] = 27;
    arrayOfByte[223] = 27;
    arrayOfByte[224] = 20;
    arrayOfByte[225] = 24;
    arrayOfByte[226] = 20;
    arrayOfByte[227] = 21;
    arrayOfByte[228] = 22;
    arrayOfByte[229] = 21;
    arrayOfByte[230] = 21;
    arrayOfByte[231] = 23;
    arrayOfByte[232] = 22;
    arrayOfByte[233] = 22;
    arrayOfByte[234] = 25;
    arrayOfByte[235] = 25;
    arrayOfByte[236] = 24;
    arrayOfByte[237] = 24;
    arrayOfByte[238] = 26;
    arrayOfByte[239] = 23;
    arrayOfByte[240] = 26;
    arrayOfByte[241] = 27;
    arrayOfByte[242] = 26;
    arrayOfByte[243] = 26;
    arrayOfByte[244] = 27;
    arrayOfByte[245] = 27;
    arrayOfByte[246] = 27;
    arrayOfByte[247] = 27;
    arrayOfByte[248] = 27;
    arrayOfByte[249] = 28;
    arrayOfByte[250] = 27;
    arrayOfByte[251] = 27;
    arrayOfByte[252] = 27;
    arrayOfByte[253] = 27;
    arrayOfByte[254] = 27;
    arrayOfByte[255] = 26;
    CODE_BIT_COUNTS = arrayOfByte;
  }
  
  @NotNull
  private static final Node root = new Node();
  
  static {
    for (int i = 0, j = CODE_BIT_COUNTS.length; i < j; i++)
      INSTANCE.addCode(i, CODES[i], CODE_BIT_COUNTS[i]); 
  }
  
  public final void encode(@NotNull ByteString source, @NotNull BufferedSink sink) throws IOException {
    Intrinsics.checkNotNullParameter(source, "source");
    Intrinsics.checkNotNullParameter(sink, "sink");
    long accumulator = 0L;
    int accumulatorBitCount = 0;
    for (int i = 0, j = source.size(); i < j; i++) {
      int symbol = Util.and(source.getByte(i), 255);
      int code = CODES[symbol];
      int codeBitCount = CODE_BIT_COUNTS[symbol];
      accumulator = accumulator << codeBitCount | code;
      accumulatorBitCount += codeBitCount;
      while (accumulatorBitCount >= 8) {
        accumulatorBitCount -= 8;
        sink.writeByte((int)(accumulator >> accumulatorBitCount));
      } 
    } 
    if (accumulatorBitCount > 0) {
      accumulator <<= 8 - accumulatorBitCount;
      accumulator |= 255L >>> accumulatorBitCount;
      sink.writeByte((int)accumulator);
    } 
  }
  
  public final int encodedLength(@NotNull ByteString bytes) {
    Intrinsics.checkNotNullParameter(bytes, "bytes");
    long bitCount = 0L;
    for (int i = 0, j = bytes.size(); i < j; i++) {
      int byteIn = Util.and(bytes.getByte(i), 255);
      bitCount += CODE_BIT_COUNTS[byteIn];
    } 
    return (int)(bitCount + 7L >> 3L);
  }
  
  public final void decode(@NotNull BufferedSource source, long byteCount, @NotNull BufferedSink sink) {
    Intrinsics.checkNotNullParameter(source, "source");
    Intrinsics.checkNotNullParameter(sink, "sink");
    Node node = root;
    int accumulator = 0;
    int accumulatorBitCount = 0;
    long i;
    for (i = 0L; i < byteCount; i++) {
      int byteIn = Util.and(source.readByte(), 255);
      accumulator = accumulator << 8 | byteIn;
      accumulatorBitCount += 8;
      while (accumulatorBitCount >= 8) {
        int childIndex = accumulator >>> accumulatorBitCount - 8 & 0xFF;
        Intrinsics.checkNotNull(node.getChildren());
        Intrinsics.checkNotNull(node.getChildren()[childIndex]);
        node = node.getChildren()[childIndex];
        if (node.getChildren() == null) {
          sink.writeByte(node.getSymbol());
          accumulatorBitCount -= node.getTerminalBitCount();
          node = root;
          continue;
        } 
        accumulatorBitCount -= 8;
      } 
    } 
    while (accumulatorBitCount > 0) {
      int childIndex = accumulator << 8 - accumulatorBitCount & 0xFF;
      Intrinsics.checkNotNull(node.getChildren());
      Intrinsics.checkNotNull(node.getChildren()[childIndex]);
      node = node.getChildren()[childIndex];
      if (node.getChildren() != null || node.getTerminalBitCount() > accumulatorBitCount)
        break; 
      sink.writeByte(node.getSymbol());
      accumulatorBitCount -= node.getTerminalBitCount();
      node = root;
    } 
  }
  
  private final void addCode(int symbol, int code, int codeBitCount) {
    Node terminal = new Node(symbol, codeBitCount);
    int accumulatorBitCount = codeBitCount;
    Node node = root;
    while (accumulatorBitCount > 8) {
      accumulatorBitCount -= 8;
      int childIndex = code >>> accumulatorBitCount & 0xFF;
      Intrinsics.checkNotNull(node.getChildren());
      Node[] children = node.getChildren();
      Node child = children[childIndex];
      if (child == null) {
        child = new Node();
        children[childIndex] = child;
      } 
      node = child;
    } 
    int shift = 8 - accumulatorBitCount;
    int start = code << shift & 0xFF;
    int end = 1 << shift;
    Intrinsics.checkNotNull(node.getChildren());
    ArraysKt.fill((Object[])node.getChildren(), terminal, start, start + end);
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\034\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\b\n\002\b\003\n\002\020\021\n\002\b\n\b\002\030\0002\0020\001B\t\b\026¢\006\004\b\002\020\003B\031\b\026\022\006\020\005\032\0020\004\022\006\020\006\032\0020\004¢\006\004\b\002\020\007R!\020\t\032\f\022\006\022\004\030\0010\000\030\0010\b8\006¢\006\f\n\004\b\t\020\n\032\004\b\013\020\fR\027\020\005\032\0020\0048\006¢\006\f\n\004\b\005\020\r\032\004\b\016\020\017R\027\020\020\032\0020\0048\006¢\006\f\n\004\b\020\020\r\032\004\b\021\020\017¨\006\022"}, d2 = {"Lokhttp3/internal/http2/Huffman$Node;", "", "<init>", "()V", "", "symbol", "bits", "(II)V", "", "children", "[Lokhttp3/internal/http2/Huffman$Node;", "getChildren", "()[Lokhttp3/internal/http2/Huffman$Node;", "I", "getSymbol", "()I", "terminalBitCount", "getTerminalBitCount", "okhttp"})
  private static final class Node {
    @Nullable
    private final Node[] children;
    
    private final int symbol;
    
    private final int terminalBitCount;
    
    @Nullable
    public final Node[] getChildren() {
      return this.children;
    }
    
    public final int getSymbol() {
      return this.symbol;
    }
    
    public final int getTerminalBitCount() {
      return this.terminalBitCount;
    }
    
    public Node() {
      this.children = new Node[256];
      this.symbol = 0;
      this.terminalBitCount = 0;
    }
    
    public Node(int symbol, int bits) {
      this.children = null;
      this.symbol = symbol;
      int b = bits & 0x7;
      this.terminalBitCount = (b == 0) ? 8 : b;
    }
  }
}
