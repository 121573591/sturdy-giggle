package okio;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
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
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Charsets;
import okio.internal.-Buffer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000´\001\n\002\030\002\n\002\030\002\n\002\030\002\n\002\020\032\n\002\030\002\n\002\b\004\n\002\020\002\n\002\b\003\n\002\020\t\n\002\b\003\n\002\030\002\n\002\b\007\n\002\020\016\n\000\n\002\030\002\n\002\b\004\n\002\020\000\n\000\n\002\020\013\n\002\b\006\n\002\020\005\n\002\b\005\n\002\020\b\n\002\b\025\n\002\030\002\n\002\b\r\n\002\030\002\n\002\b\003\n\002\020\022\n\002\b\003\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\031\n\002\020\n\n\002\b\003\n\002\030\002\n\002\b\020\n\002\030\002\n\002\b\013\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\n\n\002\030\002\n\002\b&\030\0002\0020\0012\0020\0022\0020\0032\0020\004:\002Î\001B\007¢\006\004\b\005\020\006J\017\020\007\032\0020\000H\026¢\006\004\b\007\020\bJ\r\020\n\032\0020\t¢\006\004\b\n\020\006J\017\020\013\032\0020\000H\026¢\006\004\b\013\020\bJ\017\020\f\032\0020\tH\026¢\006\004\b\f\020\006J\r\020\016\032\0020\r¢\006\004\b\016\020\017J\r\020\020\032\0020\000¢\006\004\b\020\020\bJ+\020\025\032\0020\0002\006\020\022\032\0020\0212\b\b\002\020\023\032\0020\r2\b\b\002\020\024\032\0020\rH\007¢\006\004\b\025\020\026J\037\020\025\032\0020\0002\006\020\022\032\0020\0002\b\b\002\020\023\032\0020\r¢\006\004\b\025\020\027J'\020\025\032\0020\0002\006\020\022\032\0020\0002\b\b\002\020\023\032\0020\r2\006\020\024\032\0020\r¢\006\004\b\025\020\030J\027\020\034\032\0020\0332\006\020\032\032\0020\031H\002¢\006\004\b\034\020\035J\017\020\036\032\0020\000H\026¢\006\004\b\036\020\bJ\017\020\037\032\0020\000H\026¢\006\004\b\037\020\bJ\032\020#\032\0020\"2\b\020!\032\004\030\0010 H\002¢\006\004\b#\020$J\017\020%\032\0020\"H\026¢\006\004\b%\020&J\017\020'\032\0020\tH\026¢\006\004\b'\020\006J\030\020,\032\0020)2\006\020(\032\0020\rH\002¢\006\004\b*\020+J\027\020*\032\0020)2\006\020-\032\0020\rH\007¢\006\004\b.\020+J\017\0200\032\0020/H\026¢\006\004\b0\0201J\037\0203\032\0020\0332\006\020\032\032\0020\0312\006\0202\032\0020\033H\002¢\006\004\b3\0204J\025\0205\032\0020\0332\006\0202\032\0020\033¢\006\004\b5\0206J\025\0207\032\0020\0332\006\0202\032\0020\033¢\006\004\b7\0206J\025\0208\032\0020\0332\006\0202\032\0020\033¢\006\004\b8\0206J\027\020:\032\0020\r2\006\0209\032\0020)H\026¢\006\004\b:\020;J\037\020:\032\0020\r2\006\0209\032\0020)2\006\020<\032\0020\rH\026¢\006\004\b:\020=J'\020:\032\0020\r2\006\0209\032\0020)2\006\020<\032\0020\r2\006\020>\032\0020\rH\026¢\006\004\b:\020?J\027\020:\032\0020\r2\006\020@\032\0020\033H\026¢\006\004\b:\020AJ\037\020:\032\0020\r2\006\020@\032\0020\0332\006\020<\032\0020\rH\026¢\006\004\b:\020BJ\027\020D\032\0020\r2\006\020C\032\0020\033H\026¢\006\004\bD\020AJ\037\020D\032\0020\r2\006\020C\032\0020\0332\006\020<\032\0020\rH\026¢\006\004\bD\020BJ\017\020F\032\0020EH\026¢\006\004\bF\020GJ\017\020H\032\0020\"H\026¢\006\004\bH\020&J\r\020I\032\0020\033¢\006\004\bI\020JJ\017\020K\032\0020\021H\026¢\006\004\bK\020LJ\017\020M\032\0020\001H\026¢\006\004\bM\020NJ\037\020O\032\0020\"2\006\020\023\032\0020\r2\006\020@\032\0020\033H\026¢\006\004\bO\020PJ/\020O\032\0020\"2\006\020\023\032\0020\r2\006\020@\032\0020\0332\006\020Q\032\0020/2\006\020\024\032\0020/H\026¢\006\004\bO\020RJ\027\020U\032\0020/2\006\020T\032\0020SH\026¢\006\004\bU\020VJ\027\020U\032\0020/2\006\020T\032\0020WH\026¢\006\004\bU\020XJ'\020U\032\0020/2\006\020T\032\0020W2\006\020\023\032\0020/2\006\020\024\032\0020/H\026¢\006\004\bU\020YJ\037\020U\032\0020\r2\006\020T\032\0020\0002\006\020\024\032\0020\rH\026¢\006\004\bU\020ZJ\027\020\\\032\0020\r2\006\020T\032\0020[H\026¢\006\004\b\\\020]J\031\020`\032\0020^2\b\b\002\020_\032\0020^H\007¢\006\004\b`\020aJ\017\020b\032\0020)H\026¢\006\004\bb\020cJ\017\020d\032\0020WH\026¢\006\004\bd\020eJ\027\020d\032\0020W2\006\020\024\032\0020\rH\026¢\006\004\bd\020fJ\017\020g\032\0020\033H\026¢\006\004\bg\020JJ\027\020g\032\0020\0332\006\020\024\032\0020\rH\026¢\006\004\bg\020hJ\017\020i\032\0020\rH\026¢\006\004\bi\020\017J\025\020k\032\0020\0002\006\020j\032\0020E¢\006\004\bk\020lJ\035\020k\032\0020\0002\006\020j\032\0020E2\006\020\024\032\0020\r¢\006\004\bk\020mJ'\020k\032\0020\t2\006\020j\032\0020E2\006\020\024\032\0020\r2\006\020n\032\0020\"H\002¢\006\004\bk\020oJ\027\020p\032\0020\t2\006\020T\032\0020WH\026¢\006\004\bp\020qJ\037\020p\032\0020\t2\006\020T\032\0020\0002\006\020\024\032\0020\rH\026¢\006\004\bp\020rJ\017\020s\032\0020\rH\026¢\006\004\bs\020\017J\017\020t\032\0020/H\026¢\006\004\bt\0201J\017\020u\032\0020/H\026¢\006\004\bu\0201J\017\020v\032\0020\rH\026¢\006\004\bv\020\017J\017\020w\032\0020\rH\026¢\006\004\bw\020\017J\017\020y\032\0020xH\026¢\006\004\by\020zJ\017\020{\032\0020xH\026¢\006\004\b{\020zJ\027\020~\032\0020\0312\006\020}\032\0020|H\026¢\006\004\b~\020J \020~\032\0020\0312\006\020\024\032\0020\r2\006\020}\032\0020|H\026¢\006\005\b~\020\001J\033\020\001\032\0020^2\b\b\002\020_\032\0020^H\007¢\006\005\b\001\020aJ\022\020\001\032\0020\031H\026¢\006\006\b\001\020\001J\032\020\001\032\0020\0312\006\020\024\032\0020\rH\026¢\006\006\b\001\020\001J\021\020\001\032\0020/H\026¢\006\005\b\001\0201J\024\020\001\032\004\030\0010\031H\026¢\006\006\b\001\020\001J\022\020\001\032\0020\031H\026¢\006\006\b\001\020\001J\033\020\001\032\0020\0312\007\020\001\032\0020\rH\026¢\006\006\b\001\020\001J\032\020\001\032\0020\"2\006\020\024\032\0020\rH\026¢\006\006\b\001\020\001J\032\020\001\032\0020\t2\006\020\024\032\0020\rH\026¢\006\006\b\001\020\001J\034\020\001\032\0020/2\b\020\001\032\0030\001H\026¢\006\006\b\001\020\001J\017\020\001\032\0020\033¢\006\005\b\001\020JJ\017\020\001\032\0020\033¢\006\005\b\001\020JJ\017\020\001\032\0020\033¢\006\005\b\001\020JJ\021\020\001\032\0020\rH\007¢\006\005\b\001\020\017J\032\020\001\032\0020\t2\006\020\024\032\0020\rH\026¢\006\006\b\001\020\001J\017\020\001\032\0020\033¢\006\005\b\001\020JJ\030\020\001\032\0020\0332\006\020\024\032\0020/¢\006\006\b\001\020\001J\023\020\001\032\0030\001H\026¢\006\006\b\001\020\001J\022\020\001\032\0020\031H\026¢\006\006\b\001\020\001J\034\020¡\001\032\0030\0012\007\020\001\032\0020/H\000¢\006\006\b\001\020 \001J\032\020£\001\032\0020/2\007\020¢\001\032\0020SH\026¢\006\005\b£\001\020VJ\033\020£\001\032\0020\0002\007\020¢\001\032\0020WH\026¢\006\006\b£\001\020¤\001J+\020£\001\032\0020\0002\007\020¢\001\032\0020W2\006\020\023\032\0020/2\006\020\024\032\0020/H\026¢\006\006\b£\001\020¥\001J\"\020£\001\032\0020\t2\007\020¢\001\032\0020\0002\006\020\024\032\0020\rH\026¢\006\005\b£\001\020rJ\033\020£\001\032\0020\0002\007\020¦\001\032\0020\033H\026¢\006\006\b£\001\020§\001J+\020£\001\032\0020\0002\007\020¦\001\032\0020\0332\006\020\023\032\0020/2\006\020\024\032\0020/H\026¢\006\006\b£\001\020¨\001J$\020£\001\032\0020\0002\b\020¢\001\032\0030©\0012\006\020\024\032\0020\rH\026¢\006\006\b£\001\020ª\001J\034\020«\001\032\0020\r2\b\020¢\001\032\0030©\001H\026¢\006\006\b«\001\020¬\001J\032\020­\001\032\0020\0002\006\0209\032\0020/H\026¢\006\006\b­\001\020®\001J\033\020°\001\032\0020\0002\007\020¯\001\032\0020\rH\026¢\006\006\b°\001\020±\001J\033\020²\001\032\0020\0002\007\020¯\001\032\0020\rH\026¢\006\006\b²\001\020±\001J\033\020´\001\032\0020\0002\007\020³\001\032\0020/H\026¢\006\006\b´\001\020®\001J\033\020µ\001\032\0020\0002\007\020³\001\032\0020/H\026¢\006\006\bµ\001\020®\001J\033\020¶\001\032\0020\0002\007\020¯\001\032\0020\rH\026¢\006\006\b¶\001\020±\001J\033\020·\001\032\0020\0002\007\020¯\001\032\0020\rH\026¢\006\006\b·\001\020±\001J\033\020¹\001\032\0020\0002\007\020¸\001\032\0020/H\026¢\006\006\b¹\001\020®\001J\033\020º\001\032\0020\0002\007\020¸\001\032\0020/H\026¢\006\006\bº\001\020®\001J#\020¼\001\032\0020\0002\007\020»\001\032\0020\0312\006\020}\032\0020|H\026¢\006\006\b¼\001\020½\001J5\020¼\001\032\0020\0002\007\020»\001\032\0020\0312\007\020¾\001\032\0020/2\007\020¿\001\032\0020/2\006\020}\032\0020|H\026¢\006\006\b¼\001\020À\001J$\020Á\001\032\0020\0002\006\020\022\032\0020\0212\b\b\002\020\024\032\0020\rH\007¢\006\006\bÁ\001\020Â\001J\033\020Ã\001\032\0020\0002\007\020»\001\032\0020\031H\026¢\006\006\bÃ\001\020Ä\001J-\020Ã\001\032\0020\0002\007\020»\001\032\0020\0312\007\020¾\001\032\0020/2\007\020¿\001\032\0020/H\026¢\006\006\bÃ\001\020Å\001J\033\020Ç\001\032\0020\0002\007\020Æ\001\032\0020/H\026¢\006\006\bÇ\001\020®\001R\025\020\007\032\0020\0008VX\004¢\006\007\032\005\bÈ\001\020\bR\034\020É\001\032\005\030\0010\0018\000@\000X\016¢\006\b\n\006\bÉ\001\020Ê\001R1\020\001\032\0020\r2\007\020Ë\001\032\0020\r8G@@X\016¢\006\027\n\006\b\001\020Ì\001\032\005\b\001\020\017\"\006\bÍ\001\020\001¨\006Ï\001"}, d2 = {"Lokio/Buffer;", "Lokio/BufferedSource;", "Lokio/BufferedSink;", "", "Ljava/nio/channels/ByteChannel;", "<init>", "()V", "buffer", "()Lokio/Buffer;", "", "clear", "clone", "close", "", "completeSegmentByteCount", "()J", "copy", "Ljava/io/OutputStream;", "out", "offset", "byteCount", "copyTo", "(Ljava/io/OutputStream;JJ)Lokio/Buffer;", "(Lokio/Buffer;J)Lokio/Buffer;", "(Lokio/Buffer;JJ)Lokio/Buffer;", "", "algorithm", "Lokio/ByteString;", "digest", "(Ljava/lang/String;)Lokio/ByteString;", "emit", "emitCompleteSegments", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "exhausted", "()Z", "flush", "pos", "", "getByte", "(J)B", "get", "index", "-deprecated_getByte", "", "hashCode", "()I", "key", "hmac", "(Ljava/lang/String;Lokio/ByteString;)Lokio/ByteString;", "hmacSha1", "(Lokio/ByteString;)Lokio/ByteString;", "hmacSha256", "hmacSha512", "b", "indexOf", "(B)J", "fromIndex", "(BJ)J", "toIndex", "(BJJ)J", "bytes", "(Lokio/ByteString;)J", "(Lokio/ByteString;J)J", "targetBytes", "indexOfElement", "Ljava/io/InputStream;", "inputStream", "()Ljava/io/InputStream;", "isOpen", "md5", "()Lokio/ByteString;", "outputStream", "()Ljava/io/OutputStream;", "peek", "()Lokio/BufferedSource;", "rangeEquals", "(JLokio/ByteString;)Z", "bytesOffset", "(JLokio/ByteString;II)Z", "Ljava/nio/ByteBuffer;", "sink", "read", "(Ljava/nio/ByteBuffer;)I", "", "([B)I", "([BII)I", "(Lokio/Buffer;J)J", "Lokio/Sink;", "readAll", "(Lokio/Sink;)J", "Lokio/Buffer$UnsafeCursor;", "unsafeCursor", "readAndWriteUnsafe", "(Lokio/Buffer$UnsafeCursor;)Lokio/Buffer$UnsafeCursor;", "readByte", "()B", "readByteArray", "()[B", "(J)[B", "readByteString", "(J)Lokio/ByteString;", "readDecimalLong", "input", "readFrom", "(Ljava/io/InputStream;)Lokio/Buffer;", "(Ljava/io/InputStream;J)Lokio/Buffer;", "forever", "(Ljava/io/InputStream;JZ)V", "readFully", "([B)V", "(Lokio/Buffer;J)V", "readHexadecimalUnsignedLong", "readInt", "readIntLe", "readLong", "readLongLe", "", "readShort", "()S", "readShortLe", "Ljava/nio/charset/Charset;", "charset", "readString", "(Ljava/nio/charset/Charset;)Ljava/lang/String;", "(JLjava/nio/charset/Charset;)Ljava/lang/String;", "readUnsafe", "readUtf8", "()Ljava/lang/String;", "(J)Ljava/lang/String;", "readUtf8CodePoint", "readUtf8Line", "readUtf8LineStrict", "limit", "request", "(J)Z", "require", "(J)V", "Lokio/Options;", "options", "select", "(Lokio/Options;)I", "sha1", "sha256", "sha512", "-deprecated_size", "size", "skip", "snapshot", "(I)Lokio/ByteString;", "Lokio/Timeout;", "timeout", "()Lokio/Timeout;", "toString", "minimumCapacity", "Lokio/Segment;", "writableSegment$okio", "(I)Lokio/Segment;", "writableSegment", "source", "write", "([B)Lokio/Buffer;", "([BII)Lokio/Buffer;", "byteString", "(Lokio/ByteString;)Lokio/Buffer;", "(Lokio/ByteString;II)Lokio/Buffer;", "Lokio/Source;", "(Lokio/Source;J)Lokio/Buffer;", "writeAll", "(Lokio/Source;)J", "writeByte", "(I)Lokio/Buffer;", "v", "writeDecimalLong", "(J)Lokio/Buffer;", "writeHexadecimalUnsignedLong", "i", "writeInt", "writeIntLe", "writeLong", "writeLongLe", "s", "writeShort", "writeShortLe", "string", "writeString", "(Ljava/lang/String;Ljava/nio/charset/Charset;)Lokio/Buffer;", "beginIndex", "endIndex", "(Ljava/lang/String;IILjava/nio/charset/Charset;)Lokio/Buffer;", "writeTo", "(Ljava/io/OutputStream;J)Lokio/Buffer;", "writeUtf8", "(Ljava/lang/String;)Lokio/Buffer;", "(Ljava/lang/String;II)Lokio/Buffer;", "codePoint", "writeUtf8CodePoint", "getBuffer", "head", "Lokio/Segment;", "<set-?>", "J", "setSize$okio", "UnsafeCursor", "okio"})
@SourceDebugExtension({"SMAP\nBuffer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Buffer.kt\nokio/Buffer\n+ 2 Util.kt\nokio/-SegmentedByteString\n+ 3 Buffer.kt\nokio/internal/-Buffer\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,641:1\n89#2:642\n86#2:675\n86#2:677\n74#2:737\n74#2:763\n83#2:802\n77#2:813\n89#2:1003\n74#2:1018\n86#2:1122\n89#2:1615\n244#3,32:643\n279#3,10:678\n292#3,18:688\n414#3,2:706\n112#3:708\n416#3:709\n114#3,18:710\n313#3,9:728\n322#3,15:738\n340#3,10:753\n350#3,3:764\n348#3,25:767\n376#3,10:792\n386#3:803\n384#3,9:804\n393#3,7:814\n391#3,20:821\n682#3,60:841\n745#3,56:901\n803#3:957\n806#3:958\n807#3,6:960\n817#3,7:966\n827#3,6:973\n835#3,5:979\n867#3,6:984\n877#3:990\n878#3,11:992\n889#3,5:1004\n898#3,9:1009\n908#3,61:1019\n633#3:1080\n636#3:1081\n637#3,5:1083\n644#3:1088\n647#3,7:1089\n656#3,20:1096\n420#3:1116\n423#3,5:1117\n428#3,10:1123\n439#3,7:1133\n444#3,2:1140\n973#3:1142\n974#3,87:1144\n1064#3,48:1231\n603#3:1279\n610#3,21:1280\n1115#3,7:1301\n1125#3,7:1308\n1135#3,4:1315\n1142#3,8:1319\n1153#3,10:1327\n1166#3,14:1337\n449#3,91:1351\n543#3,40:1442\n586#3:1482\n588#3,13:1484\n1183#3:1497\n1234#3:1498\n1235#3,39:1500\n1276#3,2:1539\n1278#3,4:1542\n1285#3,3:1546\n1289#3,4:1550\n112#3:1554\n1293#3,22:1555\n114#3,18:1577\n1319#3,2:1595\n1321#3,3:1598\n112#3:1601\n1324#3,13:1602\n1337#3,13:1616\n114#3,18:1629\n1354#3,2:1647\n1357#3:1650\n112#3:1651\n1358#3,50:1652\n114#3,18:1702\n1417#3,14:1720\n1434#3,32:1734\n1469#3,12:1766\n1484#3,18:1778\n1506#3:1796\n1507#3:1798\n1512#3,34:1799\n1#4:676\n1#4:959\n1#4:991\n1#4:1082\n1#4:1143\n1#4:1483\n1#4:1499\n1#4:1541\n1#4:1549\n1#4:1597\n1#4:1649\n1#4:1797\n*S KotlinDebug\n*F\n+ 1 Buffer.kt\nokio/Buffer\n*L\n167#1:642\n197#1:675\n235#1:677\n261#1:737\n264#1:763\n267#1:802\n267#1:813\n335#1:1003\n338#1:1018\n374#1:1122\n483#1:1615\n181#1:643,32\n252#1:678,10\n255#1:688,18\n258#1:706,2\n258#1:708\n258#1:709\n258#1:710,18\n261#1:728,9\n261#1:738,15\n264#1:753,10\n264#1:764,3\n264#1:767,25\n267#1:792,10\n267#1:803\n267#1:804,9\n267#1:814,7\n267#1:821,20\n279#1:841,60\n282#1:901,56\n284#1:957\n287#1:958\n287#1:960,6\n289#1:966,7\n292#1:973,6\n295#1:979,5\n329#1:984,6\n335#1:990\n335#1:992,11\n335#1:1004,5\n338#1:1009,9\n338#1:1019,61\n340#1:1080\n343#1:1081\n343#1:1083,5\n345#1:1088\n348#1:1089,7\n351#1:1096,20\n371#1:1116\n374#1:1117,5\n374#1:1123,10\n376#1:1133,7\n379#1:1140,2\n384#1:1142\n384#1:1144,87\n387#1:1231,48\n410#1:1279\n416#1:1280,21\n437#1:1301,7\n441#1:1308,7\n443#1:1315,4\n445#1:1319,8\n449#1:1327,10\n453#1:1337,14\n457#1:1351,91\n460#1:1442,40\n463#1:1482\n463#1:1484,13\n465#1:1497\n465#1:1498\n465#1:1500,39\n467#1:1539,2\n467#1:1542,4\n477#1:1546,3\n477#1:1550,4\n477#1:1554\n477#1:1555,22\n477#1:1577,18\n483#1:1595,2\n483#1:1598,3\n483#1:1601\n483#1:1602,13\n483#1:1616,13\n483#1:1629,18\n488#1:1647,2\n488#1:1650\n488#1:1651\n488#1:1652,50\n488#1:1702,18\n498#1:1720,14\n568#1:1734,32\n570#1:1766,12\n578#1:1778,18\n586#1:1796\n586#1:1798\n588#1:1799,34\n287#1:959\n335#1:991\n343#1:1082\n384#1:1143\n463#1:1483\n465#1:1499\n467#1:1541\n477#1:1549\n483#1:1597\n488#1:1649\n586#1:1797\n*E\n"})
public final class Buffer implements BufferedSource, BufferedSink, Cloneable, ByteChannel {
  @JvmField
  @Nullable
  public Segment head;
  
  private long size;
  
  @JvmName(name = "size")
  public final long size() {
    return this.size;
  }
  
  public final void setSize$okio(long <set-?>) {
    this.size = <set-?>;
  }
  
  @NotNull
  public Buffer buffer() {
    return this;
  }
  
  @NotNull
  public Buffer getBuffer() {
    return this;
  }
  
  @NotNull
  public OutputStream outputStream() {
    return new Buffer$outputStream$1();
  }
  
  @Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000'\n\000\n\002\030\002\n\002\020\002\n\002\b\003\n\002\020\016\n\002\b\002\n\002\020\022\n\000\n\002\020\b\n\002\b\007*\001\000\b\n\030\0002\0020\001J\017\020\003\032\0020\002H\026¢\006\004\b\003\020\004J\017\020\005\032\0020\002H\026¢\006\004\b\005\020\004J\017\020\007\032\0020\006H\026¢\006\004\b\007\020\bJ'\020\016\032\0020\0022\006\020\n\032\0020\t2\006\020\f\032\0020\0132\006\020\r\032\0020\013H\026¢\006\004\b\016\020\017J\027\020\016\032\0020\0022\006\020\020\032\0020\013H\026¢\006\004\b\016\020\021¨\006\022"}, d2 = {"okio/Buffer.outputStream.1", "Ljava/io/OutputStream;", "", "close", "()V", "flush", "", "toString", "()Ljava/lang/String;", "", "data", "", "offset", "byteCount", "write", "([BII)V", "b", "(I)V", "okio"})
  public static final class Buffer$outputStream$1 extends OutputStream {
    public void write(int b) {
      Buffer.this.writeByte(b);
    }
    
    public void write(@NotNull byte[] data, int offset, int byteCount) {
      Intrinsics.checkNotNullParameter(data, "data");
      Buffer.this.write(data, offset, byteCount);
    }
    
    public void flush() {}
    
    public void close() {}
    
    @NotNull
    public String toString() {
      return Buffer.this + ".outputStream()";
    }
  }
  
  @NotNull
  public Buffer emitCompleteSegments() {
    return this;
  }
  
  @NotNull
  public Buffer emit() {
    return this;
  }
  
  public boolean exhausted() {
    return (this.size == 0L);
  }
  
  public void require(long byteCount) throws EOFException {
    if (this.size < byteCount)
      throw new EOFException(); 
  }
  
  public boolean request(long byteCount) {
    return (this.size >= byteCount);
  }
  
  @NotNull
  public BufferedSource peek() {
    return Okio.buffer(new PeekSource(this));
  }
  
  @NotNull
  public InputStream inputStream() {
    return new Buffer$inputStream$1();
  }
  
  @Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000)\n\000\n\002\030\002\n\002\020\b\n\002\b\002\n\002\020\002\n\002\b\003\n\002\020\022\n\002\b\004\n\002\020\016\n\002\b\003*\001\000\b\n\030\0002\0020\001J\017\020\003\032\0020\002H\026¢\006\004\b\003\020\004J\017\020\006\032\0020\005H\026¢\006\004\b\006\020\007J\017\020\b\032\0020\002H\026¢\006\004\b\b\020\004J'\020\b\032\0020\0022\006\020\n\032\0020\t2\006\020\013\032\0020\0022\006\020\f\032\0020\002H\026¢\006\004\b\b\020\rJ\017\020\017\032\0020\016H\026¢\006\004\b\017\020\020¨\006\021"}, d2 = {"okio/Buffer.inputStream.1", "Ljava/io/InputStream;", "", "available", "()I", "", "close", "()V", "read", "", "sink", "offset", "byteCount", "([BII)I", "", "toString", "()Ljava/lang/String;", "okio"})
  @SourceDebugExtension({"SMAP\nBuffer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Buffer.kt\nokio/Buffer$inputStream$1\n+ 2 Util.kt\nokio/-SegmentedByteString\n*L\n1#1,641:1\n74#2:642\n86#2:643\n*S KotlinDebug\n*F\n+ 1 Buffer.kt\nokio/Buffer$inputStream$1\n*L\n126#1:642\n136#1:643\n*E\n"})
  public static final class Buffer$inputStream$1 extends InputStream {
    public int read() {
      byte b = Buffer.this.readByte();
      int other$iv = 255, $i$f$and = 0;
      return (Buffer.this.size() > 0L) ? (b & other$iv) : -1;
    }
    
    public int read(@NotNull byte[] sink, int offset, int byteCount) {
      Intrinsics.checkNotNullParameter(sink, "sink");
      return Buffer.this.read(sink, offset, byteCount);
    }
    
    public int available() {
      long l = Buffer.this.size();
      int b$iv = Integer.MAX_VALUE, $i$f$minOf = 0;
      return (int)Math.min(l, b$iv);
    }
    
    public void close() {}
    
    @NotNull
    public String toString() {
      return Buffer.this + ".inputStream()";
    }
  }
  
  @JvmOverloads
  @NotNull
  public final Buffer copyTo(@NotNull OutputStream out, long offset, long byteCount) throws IOException {
    Intrinsics.checkNotNullParameter(out, "out");
    long l1 = offset;
    long l2 = byteCount;
    -SegmentedByteString.checkOffsetAndCount(this.size, l1, l2);
    if (l2 == 0L)
      return this; 
    Segment s = this.head;
    Intrinsics.checkNotNull(s);
    while (l1 >= (s.limit - s.pos)) {
      l1 -= (s.limit - s.pos);
      s = s.next;
    } 
    while (l2 > 0L) {
      Intrinsics.checkNotNull(s);
      int pos = (int)(s.pos + l1);
      int a$iv = s.limit - pos, $i$f$minOf = 0, toCopy = (int)Math.min(a$iv, l2);
      out.write(s.data, pos, toCopy);
      l2 -= toCopy;
      l1 = 0L;
      s = s.next;
    } 
    return this;
  }
  
  @NotNull
  public final Buffer copyTo(@NotNull Buffer out, long offset, long byteCount) {
    Intrinsics.checkNotNullParameter(out, "out");
    Buffer $this$commonCopyTo$iv = this;
    int $i$f$commonCopyTo = 0;
    long offset$iv = offset;
    long byteCount$iv = byteCount;
    -SegmentedByteString.checkOffsetAndCount($this$commonCopyTo$iv.size(), offset$iv, byteCount$iv);
    out.setSize$okio(out.size() + byteCount$iv);
    Segment s$iv = $this$commonCopyTo$iv.head;
    Intrinsics.checkNotNull(s$iv);
    while (offset$iv >= (s$iv.limit - s$iv.pos)) {
      offset$iv -= (s$iv.limit - s$iv.pos);
      s$iv = s$iv.next;
    } 
    while (byteCount$iv > 0L) {
      Intrinsics.checkNotNull(s$iv);
      Segment copy$iv = s$iv.sharedCopy();
      copy$iv.pos += (int)offset$iv;
      copy$iv.limit = Math.min(copy$iv.pos + (int)byteCount$iv, copy$iv.limit);
      if (out.head == null) {
        copy$iv.prev = copy$iv;
        copy$iv.next = copy$iv.prev;
        out.head = copy$iv.next;
      } else {
        Intrinsics.checkNotNull(out.head);
        Intrinsics.checkNotNull(out.head.prev);
        out.head.prev.push(copy$iv);
      } 
      byteCount$iv -= (copy$iv.limit - copy$iv.pos);
      offset$iv = 0L;
      s$iv = s$iv.next;
    } 
    return (byteCount$iv == 0L) ? $this$commonCopyTo$iv : $this$commonCopyTo$iv;
  }
  
  @NotNull
  public final Buffer copyTo(@NotNull Buffer out, long offset) {
    Intrinsics.checkNotNullParameter(out, "out");
    return copyTo(out, offset, this.size - offset);
  }
  
  @JvmOverloads
  @NotNull
  public final Buffer writeTo(@NotNull OutputStream out, long byteCount) throws IOException {
    Intrinsics.checkNotNullParameter(out, "out");
    long l = byteCount;
    -SegmentedByteString.checkOffsetAndCount(this.size, 0L, l);
    Segment s = this.head;
    while (l > 0L) {
      Intrinsics.checkNotNull(s);
      int b$iv = s.limit - s.pos, $i$f$minOf = 0, toCopy = (int)Math.min(l, b$iv);
      out.write(s.data, s.pos, toCopy);
      Segment segment = s;
      segment.pos += toCopy;
      this.size -= toCopy;
      l -= toCopy;
      if (s.pos == s.limit) {
        Segment toRecycle = s;
        s = toRecycle.pop();
        this.head = s;
        SegmentPool.recycle(toRecycle);
      } 
    } 
    return this;
  }
  
  @NotNull
  public final Buffer readFrom(@NotNull InputStream input) throws IOException {
    Intrinsics.checkNotNullParameter(input, "input");
    readFrom(input, Long.MAX_VALUE, true);
    return this;
  }
  
  @NotNull
  public final Buffer readFrom(@NotNull InputStream input, long byteCount) throws IOException {
    Intrinsics.checkNotNullParameter(input, "input");
    if (!((byteCount >= 0L) ? 1 : 0)) {
      int $i$a$-require-Buffer$readFrom$1 = 0;
      String str = "byteCount < 0: " + byteCount;
      throw new IllegalArgumentException(str.toString());
    } 
    readFrom(input, byteCount, false);
    return this;
  }
  
  private final void readFrom(InputStream input, long byteCount, boolean forever) throws IOException {
    long l = byteCount;
    while (l > 0L || forever) {
      Segment tail = writableSegment$okio(1);
      int b$iv = 8192 - tail.limit, $i$f$minOf = 0, maxToCopy = (int)Math.min(l, b$iv);
      int bytesRead = input.read(tail.data, tail.limit, maxToCopy);
      if (bytesRead == -1) {
        if (tail.pos == tail.limit) {
          this.head = tail.pop();
          SegmentPool.recycle(tail);
        } 
        if (forever)
          return; 
        throw new EOFException();
      } 
      tail.limit += bytesRead;
      this.size += bytesRead;
      l -= bytesRead;
    } 
  }
  
  public final long completeSegmentByteCount() {
    Buffer $this$commonCompleteSegmentByteCount$iv = this;
    int $i$f$commonCompleteSegmentByteCount = 0;
    long result$iv = $this$commonCompleteSegmentByteCount$iv.size();
    Intrinsics.checkNotNull($this$commonCompleteSegmentByteCount$iv.head);
    Intrinsics.checkNotNull($this$commonCompleteSegmentByteCount$iv.head.prev);
    Segment tail$iv = $this$commonCompleteSegmentByteCount$iv.head.prev;
    if (tail$iv.limit < 8192 && tail$iv.owner)
      result$iv -= (tail$iv.limit - tail$iv.pos); 
    return (result$iv == 0L) ? 0L : result$iv;
  }
  
  public byte readByte() throws EOFException {
    Buffer $this$commonReadByte$iv = this;
    int $i$f$commonReadByte = 0;
    if ($this$commonReadByte$iv.size() == 0L)
      throw new EOFException(); 
    Intrinsics.checkNotNull($this$commonReadByte$iv.head);
    Segment segment$iv = $this$commonReadByte$iv.head;
    int pos$iv = segment$iv.pos;
    int limit$iv = segment$iv.limit;
    byte[] data$iv = segment$iv.data;
    byte b$iv = data$iv[pos$iv++];
    $this$commonReadByte$iv.setSize$okio($this$commonReadByte$iv.size() - 1L);
    if (pos$iv == limit$iv) {
      $this$commonReadByte$iv.head = segment$iv.pop();
      SegmentPool.recycle(segment$iv);
    } else {
      segment$iv.pos = pos$iv;
    } 
    return b$iv;
  }
  
  @JvmName(name = "getByte")
  public final byte getByte(long pos) {
    Segment s$iv$iv;
    Buffer $this$commonGet$iv = this;
    int $i$f$commonGet = 0;
    -SegmentedByteString.checkOffsetAndCount($this$commonGet$iv.size(), pos, 1L);
    Buffer $this$seek$iv$iv = $this$commonGet$iv;
    int $i$f$seek = 0;
    long l1 = -1L;
    Segment s$iv = null;
    int $i$a$-seek--Buffer$commonGet$1$iv = 0;
    Intrinsics.checkNotNull(s$iv);
    if ($this$seek$iv$iv.size() - pos < pos) {
      long l3 = $this$seek$iv$iv.size();
      while (l3 > pos) {
        Intrinsics.checkNotNull(s$iv$iv.prev);
        s$iv$iv = s$iv$iv.prev;
        l3 -= (s$iv$iv.limit - s$iv$iv.pos);
      } 
      long l4 = l3;
      Segment segment = s$iv$iv;
      int j = 0;
      Intrinsics.checkNotNull(segment);
    } 
    long offset$iv$iv = 0L;
    while (true) {
      long nextOffset$iv$iv = offset$iv$iv + (s$iv$iv.limit - s$iv$iv.pos);
      if (nextOffset$iv$iv <= pos) {
        Intrinsics.checkNotNull(s$iv$iv.next);
        s$iv$iv = s$iv$iv.next;
        offset$iv$iv = nextOffset$iv$iv;
        continue;
      } 
      break;
    } 
    long l2 = offset$iv$iv;
    Segment segment1 = s$iv$iv;
    int i = 0;
    Intrinsics.checkNotNull(segment1);
    return ($this$seek$iv$iv.head == null) ? s$iv.data[(int)(s$iv.pos + pos - l1)] : segment1.data[(int)(segment1.pos + pos - l2)];
  }
  
  public short readShort() throws EOFException {
    Buffer $this$commonReadShort$iv = this;
    int $i$f$commonReadShort = 0;
    if ($this$commonReadShort$iv.size() < 2L)
      throw new EOFException(); 
    Intrinsics.checkNotNull($this$commonReadShort$iv.head);
    Segment segment$iv = $this$commonReadShort$iv.head;
    int pos$iv = segment$iv.pos;
    int limit$iv = segment$iv.limit;
    byte b1 = $this$commonReadShort$iv.readByte();
    int other$iv$iv = 255, $i$f$and = 0;
    byte $this$and$iv$iv = $this$commonReadShort$iv.readByte();
    other$iv$iv = 255;
    $i$f$and = 0;
    int j = (
      b1 & other$iv$iv) << 8 | $this$and$iv$iv & other$iv$iv;
    byte[] data$iv = segment$iv.data;
    other$iv$iv = data$iv[pos$iv++];
    int i = 255, k = 0;
    byte b2 = data$iv[pos$iv++];
    i = 255;
    k = 0;
    int s$iv = (other$iv$iv & i) << 8 | b2 & i;
    $this$commonReadShort$iv.setSize$okio($this$commonReadShort$iv.size() - 2L);
    if (pos$iv == limit$iv) {
      $this$commonReadShort$iv.head = segment$iv.pop();
      SegmentPool.recycle(segment$iv);
    } else {
      segment$iv.pos = pos$iv;
    } 
    return (limit$iv - pos$iv < 2) ? (short)j : (short)s$iv;
  }
  
  public int readInt() throws EOFException {
    Buffer $this$commonReadInt$iv = this;
    int $i$f$commonReadInt = 0;
    if ($this$commonReadInt$iv.size() < 4L)
      throw new EOFException(); 
    Intrinsics.checkNotNull($this$commonReadInt$iv.head);
    Segment segment$iv = $this$commonReadInt$iv.head;
    int pos$iv = segment$iv.pos;
    int limit$iv = segment$iv.limit;
    byte b1 = $this$commonReadInt$iv.readByte();
    int other$iv$iv = 255, $i$f$and = 0;
    byte $this$and$iv$iv = $this$commonReadInt$iv.readByte();
    other$iv$iv = 255;
    $i$f$and = 0;
    $this$and$iv$iv = $this$commonReadInt$iv.readByte();
    other$iv$iv = 255;
    $i$f$and = 0;
    $this$and$iv$iv = $this$commonReadInt$iv.readByte();
    other$iv$iv = 255;
    $i$f$and = 0;
    byte[] data$iv = segment$iv.data;
    $i$f$and = data$iv[pos$iv++];
    int i = 255, j = 0;
    byte b2 = data$iv[pos$iv++];
    i = 255;
    j = 0;
    b2 = data$iv[pos$iv++];
    i = 255;
    j = 0;
    b2 = data$iv[pos$iv++];
    i = 255;
    j = 0;
    int i$iv = ($i$f$and & i) << 24 | (b2 & i) << 16 | (b2 & i) << 8 | b2 & i;
    $this$commonReadInt$iv.setSize$okio($this$commonReadInt$iv.size() - 4L);
    if (pos$iv == limit$iv) {
      $this$commonReadInt$iv.head = segment$iv.pop();
      SegmentPool.recycle(segment$iv);
    } else {
      segment$iv.pos = pos$iv;
    } 
    return ((limit$iv - pos$iv) < 4L) ? ((b1 & other$iv$iv) << 24 | ($this$and$iv$iv & other$iv$iv) << 16 | ($this$and$iv$iv & other$iv$iv) << 8 | $this$and$iv$iv & other$iv$iv) : i$iv;
  }
  
  public long readLong() throws EOFException {
    Buffer $this$commonReadLong$iv = this;
    int $i$f$commonReadLong = 0;
    if ($this$commonReadLong$iv.size() < 8L)
      throw new EOFException(); 
    Intrinsics.checkNotNull($this$commonReadLong$iv.head);
    Segment segment$iv = $this$commonReadLong$iv.head;
    int pos$iv = segment$iv.pos;
    int limit$iv = segment$iv.limit;
    int i = $this$commonReadLong$iv.readInt();
    long other$iv$iv = 4294967295L;
    int $i$f$and = 0;
    int $this$and$iv$iv = $this$commonReadLong$iv.readInt();
    other$iv$iv = 4294967295L;
    $i$f$and = 0;
    byte[] data$iv = segment$iv.data;
    $i$f$and = data$iv[pos$iv++];
    long l1 = 255L;
    int j = 0;
    byte b = data$iv[pos$iv++];
    l1 = 255L;
    j = 0;
    b = data$iv[pos$iv++];
    l1 = 255L;
    j = 0;
    b = data$iv[pos$iv++];
    l1 = 255L;
    j = 0;
    b = data$iv[pos$iv++];
    l1 = 255L;
    j = 0;
    b = data$iv[pos$iv++];
    l1 = 255L;
    j = 0;
    b = data$iv[pos$iv++];
    l1 = 255L;
    j = 0;
    b = data$iv[pos$iv++];
    l1 = 255L;
    j = 0;
    long v$iv = ($i$f$and & l1) << 56L | (b & l1) << 48L | (b & l1) << 40L | (b & l1) << 32L | (b & l1) << 24L | (b & l1) << 16L | (b & l1) << 8L | b & l1;
    $this$commonReadLong$iv.setSize$okio($this$commonReadLong$iv.size() - 8L);
    if (pos$iv == limit$iv) {
      $this$commonReadLong$iv.head = segment$iv.pop();
      SegmentPool.recycle(segment$iv);
    } else {
      segment$iv.pos = pos$iv;
    } 
    return ((limit$iv - pos$iv) < 8L) ? ((i & other$iv$iv) << 32L | $this$and$iv$iv & other$iv$iv) : v$iv;
  }
  
  public short readShortLe() throws EOFException {
    return -SegmentedByteString.reverseBytes(readShort());
  }
  
  public int readIntLe() throws EOFException {
    return -SegmentedByteString.reverseBytes(readInt());
  }
  
  public long readLongLe() throws EOFException {
    return -SegmentedByteString.reverseBytes(readLong());
  }
  
  public long readDecimalLong() throws EOFException {
    Buffer $this$commonReadDecimalLong$iv = this;
    int $i$f$commonReadDecimalLong = 0;
    if ($this$commonReadDecimalLong$iv.size() == 0L)
      throw new EOFException(); 
    long value$iv = 0L;
    int seen$iv = 0;
    boolean negative$iv = false;
    boolean done$iv = false;
    long overflowDigit$iv = -7L;
    do {
      Intrinsics.checkNotNull($this$commonReadDecimalLong$iv.head);
      Segment segment$iv = $this$commonReadDecimalLong$iv.head;
      byte[] data$iv = segment$iv.data;
      int pos$iv = segment$iv.pos;
      int limit$iv = segment$iv.limit;
      while (pos$iv < limit$iv) {
        byte b$iv = data$iv[pos$iv];
        if (b$iv >= 48 && b$iv <= 57) {
          int digit$iv = 48 - b$iv;
          if (value$iv < -922337203685477580L || (value$iv == -922337203685477580L && digit$iv < overflowDigit$iv)) {
            Buffer buffer$iv = (new Buffer()).writeDecimalLong(value$iv).writeByte(b$iv);
            if (!negative$iv)
              buffer$iv.readByte(); 
            throw new NumberFormatException("Number too large: " + buffer$iv.readUtf8());
          } 
          value$iv *= 10L;
          value$iv += digit$iv;
        } else if (b$iv == 45 && seen$iv == 0) {
          negative$iv = true;
          overflowDigit$iv--;
        } else {
          done$iv = true;
          break;
        } 
        pos$iv++;
        seen$iv++;
      } 
      if (pos$iv == limit$iv) {
        $this$commonReadDecimalLong$iv.head = segment$iv.pop();
        SegmentPool.recycle(segment$iv);
      } else {
        segment$iv.pos = pos$iv;
      } 
    } while (!done$iv && $this$commonReadDecimalLong$iv.head != null);
    $this$commonReadDecimalLong$iv.setSize$okio($this$commonReadDecimalLong$iv.size() - seen$iv);
    int minimumSeen$iv = negative$iv ? 2 : 1;
    if (seen$iv < minimumSeen$iv) {
      if ($this$commonReadDecimalLong$iv.size() == 0L)
        throw new EOFException(); 
      String expected$iv = negative$iv ? "Expected a digit" : "Expected a digit or '-'";
      throw new NumberFormatException(expected$iv + " but was 0x" + -SegmentedByteString.toHexString($this$commonReadDecimalLong$iv.getByte(0L)));
    } 
    return negative$iv ? value$iv : -value$iv;
  }
  
  public long readHexadecimalUnsignedLong() throws EOFException {
    Buffer $this$commonReadHexadecimalUnsignedLong$iv = this;
    int $i$f$commonReadHexadecimalUnsignedLong = 0;
    if ($this$commonReadHexadecimalUnsignedLong$iv.size() == 0L)
      throw new EOFException(); 
    long value$iv = 0L;
    int seen$iv = 0;
    boolean done$iv = false;
    do {
      Intrinsics.checkNotNull($this$commonReadHexadecimalUnsignedLong$iv.head);
      Segment segment$iv = $this$commonReadHexadecimalUnsignedLong$iv.head;
      byte[] data$iv = segment$iv.data;
      int pos$iv = segment$iv.pos;
      int limit$iv = segment$iv.limit;
      while (pos$iv < limit$iv) {
        int digit$iv = 0;
        byte b$iv = data$iv[pos$iv];
        if (b$iv >= 48 && b$iv <= 57) {
          digit$iv = b$iv - 48;
        } else if (b$iv >= 97 && b$iv <= 102) {
          digit$iv = b$iv - 97 + 10;
        } else if (b$iv >= 65 && b$iv <= 70) {
          digit$iv = b$iv - 65 + 10;
        } else {
          if (seen$iv == 0)
            throw new NumberFormatException(
                "Expected leading [0-9a-fA-F] character but was 0x" + -SegmentedByteString.toHexString(b$iv)); 
          done$iv = true;
          break;
        } 
        if ((value$iv & 0xF000000000000000L) != 0L) {
          Buffer buffer$iv = (new Buffer()).writeHexadecimalUnsignedLong(value$iv).writeByte(b$iv);
          throw new NumberFormatException("Number too large: " + buffer$iv.readUtf8());
        } 
        value$iv <<= 4L;
        value$iv |= digit$iv;
        pos$iv++;
        seen$iv++;
      } 
      if (pos$iv == limit$iv) {
        $this$commonReadHexadecimalUnsignedLong$iv.head = segment$iv.pop();
        SegmentPool.recycle(segment$iv);
      } else {
        segment$iv.pos = pos$iv;
      } 
    } while (!done$iv && $this$commonReadHexadecimalUnsignedLong$iv.head != null);
    $this$commonReadHexadecimalUnsignedLong$iv.setSize$okio($this$commonReadHexadecimalUnsignedLong$iv.size() - seen$iv);
    return value$iv;
  }
  
  @NotNull
  public ByteString readByteString() {
    Buffer $this$commonReadByteString$iv = this;
    int $i$f$commonReadByteString = 0;
    return $this$commonReadByteString$iv.readByteString($this$commonReadByteString$iv.size());
  }
  
  @NotNull
  public ByteString readByteString(long byteCount) throws EOFException {
    Buffer $this$commonReadByteString$iv = this;
    int $i$f$commonReadByteString = 0;
    if (!((byteCount >= 0L && byteCount <= 2147483647L) ? 1 : 0)) {
      int $i$a$-require--Buffer$commonReadByteString$1$iv = 0;
      String str = "byteCount: " + byteCount;
      throw new IllegalArgumentException(str.toString());
    } 
    if ($this$commonReadByteString$iv.size() < byteCount)
      throw new EOFException(); 
    if (byteCount >= 4096L) {
      ByteString byteString1 = $this$commonReadByteString$iv.snapshot((int)byteCount), it$iv = byteString1;
      int $i$a$-also--Buffer$commonReadByteString$2$iv = 0;
      $this$commonReadByteString$iv.skip(byteCount);
    } 
    return new ByteString($this$commonReadByteString$iv.readByteArray(byteCount));
  }
  
  public int select(@NotNull Options options) {
    Intrinsics.checkNotNullParameter(options, "options");
    Buffer $this$commonSelect$iv = this;
    int $i$f$commonSelect = 0;
    int index$iv = -Buffer.selectPrefix$default($this$commonSelect$iv, options, false, 2, null);
    int selectedSize$iv = options.getByteStrings$okio()[index$iv].size();
    $this$commonSelect$iv.skip(selectedSize$iv);
    return (index$iv == -1) ? -1 : index$iv;
  }
  
  public void readFully(@NotNull Buffer sink, long byteCount) throws EOFException {
    Intrinsics.checkNotNullParameter(sink, "sink");
    Buffer $this$commonReadFully$iv = this;
    int $i$f$commonReadFully = 0;
    if ($this$commonReadFully$iv.size() < byteCount) {
      sink.write($this$commonReadFully$iv, $this$commonReadFully$iv.size());
      throw new EOFException();
    } 
    sink.write($this$commonReadFully$iv, byteCount);
  }
  
  public long readAll(@NotNull Sink sink) throws IOException {
    Intrinsics.checkNotNullParameter(sink, "sink");
    Buffer $this$commonReadAll$iv = this;
    int $i$f$commonReadAll = 0;
    long byteCount$iv = $this$commonReadAll$iv.size();
    if (byteCount$iv > 0L)
      sink.write($this$commonReadAll$iv, byteCount$iv); 
    return byteCount$iv;
  }
  
  @NotNull
  public String readUtf8() {
    return readString(this.size, Charsets.UTF_8);
  }
  
  @NotNull
  public String readUtf8(long byteCount) throws EOFException {
    return readString(byteCount, Charsets.UTF_8);
  }
  
  @NotNull
  public String readString(@NotNull Charset charset) {
    Intrinsics.checkNotNullParameter(charset, "charset");
    return readString(this.size, charset);
  }
  
  @NotNull
  public String readString(long byteCount, @NotNull Charset charset) throws EOFException {
    Intrinsics.checkNotNullParameter(charset, "charset");
    if (!((byteCount >= 0L && byteCount <= 2147483647L) ? 1 : 0)) {
      int $i$a$-require-Buffer$readString$1 = 0;
      String str = "byteCount: " + byteCount;
      throw new IllegalArgumentException(str.toString());
    } 
    if (this.size < byteCount)
      throw new EOFException(); 
    if (byteCount == 0L)
      return ""; 
    Intrinsics.checkNotNull(this.head);
    Segment s = this.head;
    if (s.pos + byteCount > s.limit)
      return new String(readByteArray(byteCount), charset); 
    String result = new String(s.data, s.pos, (int)byteCount, charset);
    s.pos += (int)byteCount;
    this.size -= byteCount;
    if (s.pos == s.limit) {
      this.head = s.pop();
      SegmentPool.recycle(s);
    } 
    return result;
  }
  
  @Nullable
  public String readUtf8Line() throws EOFException {
    Buffer $this$commonReadUtf8Line$iv = this;
    int $i$f$commonReadUtf8Line = 0;
    long newline$iv = $this$commonReadUtf8Line$iv.indexOf((byte)10);
    return (newline$iv != -1L) ? -Buffer.readUtf8Line($this$commonReadUtf8Line$iv, newline$iv) : (
      ($this$commonReadUtf8Line$iv.size() != 0L) ? $this$commonReadUtf8Line$iv.readUtf8($this$commonReadUtf8Line$iv.size()) : 
      null);
  }
  
  @NotNull
  public String readUtf8LineStrict() throws EOFException {
    return readUtf8LineStrict(Long.MAX_VALUE);
  }
  
  @NotNull
  public String readUtf8LineStrict(long limit) throws EOFException {
    Buffer $this$commonReadUtf8LineStrict$iv = this;
    int $i$f$commonReadUtf8LineStrict = 0;
    if (!((limit >= 0L) ? 1 : 0)) {
      int $i$a$-require--Buffer$commonReadUtf8LineStrict$1$iv = 0;
      String str = "limit < 0: " + limit;
      throw new IllegalArgumentException(str.toString());
    } 
    long scanLength$iv = (limit == Long.MAX_VALUE) ? Long.MAX_VALUE : (limit + 1L);
    long newline$iv = $this$commonReadUtf8LineStrict$iv.indexOf((byte)10, 0L, scanLength$iv);
    if (scanLength$iv < $this$commonReadUtf8LineStrict$iv.size() && 
      $this$commonReadUtf8LineStrict$iv.getByte(scanLength$iv - 1L) == 13 && 
      $this$commonReadUtf8LineStrict$iv.getByte(scanLength$iv) == 10) {
    
    } else {
      Buffer data$iv = new Buffer();
      byte b = 32;
      long b$iv$iv = $this$commonReadUtf8LineStrict$iv.size();
      int $i$f$minOf = 0;
      $this$commonReadUtf8LineStrict$iv.copyTo(data$iv, 0L, 
          Math.min(b, b$iv$iv));
      throw new EOFException(
          "\\n not found: limit=" + 
          Math.min($this$commonReadUtf8LineStrict$iv.size(), limit) + 
          
          " content=" + data$iv.readByteString().hex() + '…');
    } 
    return (newline$iv != -1L) ? -Buffer.readUtf8Line($this$commonReadUtf8LineStrict$iv, newline$iv) : "JD-Core does not support Kotlin";
  }
  
  public int readUtf8CodePoint() throws EOFException {
    Buffer $this$commonReadUtf8CodePoint$iv = this;
    int $i$f$commonReadUtf8CodePoint = 0;
    if ($this$commonReadUtf8CodePoint$iv.size() == 0L)
      throw new EOFException(); 
    byte b0$iv = $this$commonReadUtf8CodePoint$iv.getByte(0L);
    int codePoint$iv = 0, byteCount$iv = 0, min$iv = 0;
    byte b = b0$iv;
    int other$iv$iv = 128, $i$f$and = 0;
    if ((b & other$iv$iv) == 0) {
      byte $this$and$iv$iv = b0$iv;
      other$iv$iv = 127;
      $i$f$and = 0;
      codePoint$iv = $this$and$iv$iv & other$iv$iv;
      byteCount$iv = 1;
      min$iv = 0;
    } else {
      byte $this$and$iv$iv = b0$iv;
      other$iv$iv = 224;
      $i$f$and = 0;
      if (($this$and$iv$iv & other$iv$iv) == 192) {
        $this$and$iv$iv = b0$iv;
        other$iv$iv = 31;
        $i$f$and = 0;
        codePoint$iv = $this$and$iv$iv & other$iv$iv;
        byteCount$iv = 2;
        min$iv = 128;
      } else {
        $this$and$iv$iv = b0$iv;
        other$iv$iv = 240;
        $i$f$and = 0;
        if (($this$and$iv$iv & other$iv$iv) == 224) {
          $this$and$iv$iv = b0$iv;
          other$iv$iv = 15;
          $i$f$and = 0;
          codePoint$iv = $this$and$iv$iv & other$iv$iv;
          byteCount$iv = 3;
          min$iv = 2048;
        } else {
          $this$and$iv$iv = b0$iv;
          other$iv$iv = 248;
          $i$f$and = 0;
          if (($this$and$iv$iv & other$iv$iv) == 240) {
            $this$and$iv$iv = b0$iv;
            other$iv$iv = 7;
            $i$f$and = 0;
            codePoint$iv = $this$and$iv$iv & other$iv$iv;
            byteCount$iv = 4;
            min$iv = 65536;
          } else {
            $this$commonReadUtf8CodePoint$iv.skip(1L);
          } 
        } 
      } 
    } 
    if ($this$commonReadUtf8CodePoint$iv.size() < byteCount$iv)
      throw new EOFException("size < " + byteCount$iv + ": " + $this$commonReadUtf8CodePoint$iv.size() + " (to read code point prefixed 0x" + -SegmentedByteString.toHexString(b0$iv) + ')'); 
    for (int i$iv = 1; i$iv < byteCount$iv; ) {
      byte b$iv = $this$commonReadUtf8CodePoint$iv.getByte(i$iv);
      $i$f$and = b$iv;
      int i = 192, j = 0;
      if (($i$f$and & i) == 128) {
        codePoint$iv <<= 6;
        byte $this$and$iv$iv = b$iv;
        i = 63;
        j = 0;
        codePoint$iv |= $this$and$iv$iv & i;
      } else {
        $this$commonReadUtf8CodePoint$iv.skip(i$iv);
      } 
    } 
    $this$commonReadUtf8CodePoint$iv.skip(byteCount$iv);
    return (codePoint$iv > 1114111) ? 
      65533 : (
      
      ((55296 <= codePoint$iv) ? ((codePoint$iv < 57344)) : false) ? 
      65533 : (
      
      (codePoint$iv < min$iv) ? 
      65533 : 
      
      codePoint$iv));
  }
  
  @NotNull
  public byte[] readByteArray() {
    Buffer $this$commonReadByteArray$iv = this;
    int $i$f$commonReadByteArray = 0;
    return $this$commonReadByteArray$iv.readByteArray($this$commonReadByteArray$iv.size());
  }
  
  @NotNull
  public byte[] readByteArray(long byteCount) throws EOFException {
    Buffer $this$commonReadByteArray$iv = this;
    int $i$f$commonReadByteArray = 0;
    if (!((byteCount >= 0L && byteCount <= 2147483647L) ? 1 : 0)) {
      int $i$a$-require--Buffer$commonReadByteArray$1$iv = 0;
      String str = "byteCount: " + byteCount;
      throw new IllegalArgumentException(str.toString());
    } 
    if ($this$commonReadByteArray$iv.size() < byteCount)
      throw new EOFException(); 
    byte[] result$iv = new byte[(int)byteCount];
    $this$commonReadByteArray$iv.readFully(result$iv);
    return result$iv;
  }
  
  public int read(@NotNull byte[] sink) {
    Intrinsics.checkNotNullParameter(sink, "sink");
    Buffer $this$commonRead$iv = this;
    int $i$f$commonRead = 0;
    return $this$commonRead$iv.read(sink, 0, sink.length);
  }
  
  public void readFully(@NotNull byte[] sink) throws EOFException {
    Intrinsics.checkNotNullParameter(sink, "sink");
    Buffer $this$commonReadFully$iv = this;
    int $i$f$commonReadFully = 0;
    int offset$iv = 0;
    while (offset$iv < sink.length) {
      int read$iv = $this$commonReadFully$iv.read(sink, offset$iv, sink.length - offset$iv);
      if (read$iv == -1)
        throw new EOFException(); 
      offset$iv += read$iv;
    } 
  }
  
  public int read(@NotNull byte[] sink, int offset, int byteCount) {
    Segment s$iv;
    Intrinsics.checkNotNullParameter(sink, "sink");
    Buffer $this$commonRead$iv = this;
    int $i$f$commonRead = 0;
    -SegmentedByteString.checkOffsetAndCount(sink.length, offset, byteCount);
    int toCopy$iv = Math.min(byteCount, s$iv.limit - s$iv.pos);
    ArraysKt.copyInto(s$iv.data, 
        sink, 
        offset, 
        s$iv.pos, 
        s$iv.pos + toCopy$iv);
    s$iv.pos += toCopy$iv;
    $this$commonRead$iv.setSize$okio($this$commonRead$iv.size() - toCopy$iv);
    if (s$iv.pos == s$iv.limit) {
      $this$commonRead$iv.head = s$iv.pop();
      SegmentPool.recycle(s$iv);
    } 
    return ($this$commonRead$iv.head == null) ? -1 : toCopy$iv;
  }
  
  public int read(@NotNull ByteBuffer sink) throws IOException {
    Segment s;
    Intrinsics.checkNotNullParameter(sink, "sink");
    if (this.head == null)
      return -1; 
    int toCopy = Math.min(sink.remaining(), s.limit - s.pos);
    sink.put(s.data, s.pos, toCopy);
    s.pos += toCopy;
    this.size -= toCopy;
    if (s.pos == s.limit) {
      this.head = s.pop();
      SegmentPool.recycle(s);
    } 
    return toCopy;
  }
  
  public final void clear() {
    Buffer $this$commonClear$iv = this;
    int $i$f$commonClear = 0;
    $this$commonClear$iv.skip($this$commonClear$iv.size());
  }
  
  public void skip(long byteCount) throws EOFException {
    Buffer $this$commonSkip$iv = this;
    int $i$f$commonSkip = 0;
    long byteCount$iv = byteCount;
    while (byteCount$iv > 0L) {
      Segment head$iv;
      if ($this$commonSkip$iv.head == null)
        throw new EOFException(); 
      int b$iv$iv = head$iv.limit - head$iv.pos, $i$f$minOf = 0, toSkip$iv = (int)
        Math.min(byteCount$iv, b$iv$iv);
      $this$commonSkip$iv.setSize$okio($this$commonSkip$iv.size() - toSkip$iv);
      byteCount$iv -= toSkip$iv;
      head$iv.pos += toSkip$iv;
      if (head$iv.pos == head$iv.limit) {
        $this$commonSkip$iv.head = head$iv.pop();
        SegmentPool.recycle(head$iv);
      } 
    } 
  }
  
  @NotNull
  public Buffer write(@NotNull ByteString byteString) {
    Intrinsics.checkNotNullParameter(byteString, "byteString");
    Buffer $this$commonWrite_u24default$iv = this;
    int offset$iv = 0;
    int byteCount$iv = byteString.size();
    int $i$f$commonWrite = 0;
    byteString.write$okio($this$commonWrite_u24default$iv, offset$iv, byteCount$iv);
    return $this$commonWrite_u24default$iv;
  }
  
  @NotNull
  public Buffer write(@NotNull ByteString byteString, int offset, int byteCount) {
    Intrinsics.checkNotNullParameter(byteString, "byteString");
    Buffer $this$commonWrite$iv = this;
    int $i$f$commonWrite = 0;
    byteString.write$okio($this$commonWrite$iv, offset, byteCount);
    return $this$commonWrite$iv;
  }
  
  @NotNull
  public Buffer writeUtf8(@NotNull String string) {
    Intrinsics.checkNotNullParameter(string, "string");
    return writeUtf8(string, 0, string.length());
  }
  
  @NotNull
  public Buffer writeUtf8(@NotNull String string, int beginIndex, int endIndex) {
    Intrinsics.checkNotNullParameter(string, "string");
    Buffer $this$commonWriteUtf8$iv = this;
    int $i$f$commonWriteUtf8 = 0;
    if (!((beginIndex >= 0) ? 1 : 0)) {
      int $i$a$-require--Buffer$commonWriteUtf8$1$iv = 0;
      String str = "beginIndex < 0: " + beginIndex;
      throw new IllegalArgumentException(str.toString());
    } 
    if (!((endIndex >= beginIndex) ? 1 : 0)) {
      int $i$a$-require--Buffer$commonWriteUtf8$2$iv = 0;
      String str = "endIndex < beginIndex: " + endIndex + " < " + beginIndex;
      throw new IllegalArgumentException(str.toString());
    } 
    if (!((endIndex <= string.length()) ? 1 : 0)) {
      int $i$a$-require--Buffer$commonWriteUtf8$3$iv = 0;
      String str = "endIndex > string.length: " + endIndex + " > " + string.length();
      throw new IllegalArgumentException(str.toString());
    } 
    int i$iv = beginIndex;
    while (i$iv < endIndex) {
      int c$iv = string.charAt(i$iv);
      if (c$iv < 128) {
        Segment tail$iv = $this$commonWriteUtf8$iv.writableSegment$okio(1);
        byte[] data$iv = tail$iv.data;
        int segmentOffset$iv = tail$iv.limit - i$iv;
        int runLimit$iv = Math.min(endIndex, 8192 - segmentOffset$iv);
        data$iv[segmentOffset$iv + i$iv++] = (byte)c$iv;
        while (i$iv < runLimit$iv) {
          c$iv = string.charAt(i$iv);
          if (c$iv < 128)
            data$iv[segmentOffset$iv + i$iv++] = (byte)c$iv; 
        } 
        int runSize$iv = i$iv + segmentOffset$iv - tail$iv.limit;
        tail$iv.limit += runSize$iv;
        $this$commonWriteUtf8$iv.setSize$okio($this$commonWriteUtf8$iv.size() + runSize$iv);
        continue;
      } 
      if (c$iv < 2048) {
        Segment tail$iv = $this$commonWriteUtf8$iv.writableSegment$okio(2);
        tail$iv.data[tail$iv.limit] = (byte)(c$iv >> 6 | 0xC0);
        tail$iv.data[tail$iv.limit + 1] = (byte)(c$iv & 0x3F | 0x80);
        tail$iv.limit += 2;
        $this$commonWriteUtf8$iv.setSize$okio($this$commonWriteUtf8$iv.size() + 2L);
        i$iv++;
        continue;
      } 
      if (c$iv < 55296 || c$iv > 57343) {
        Segment tail$iv = $this$commonWriteUtf8$iv.writableSegment$okio(3);
        tail$iv.data[tail$iv.limit] = (byte)(c$iv >> 12 | 0xE0);
        tail$iv.data[tail$iv.limit + 1] = (byte)(c$iv >> 6 & 0x3F | 0x80);
        tail$iv.data[tail$iv.limit + 2] = (byte)(c$iv & 0x3F | 0x80);
        tail$iv.limit += 3;
        $this$commonWriteUtf8$iv.setSize$okio($this$commonWriteUtf8$iv.size() + 3L);
        i$iv++;
        continue;
      } 
      int low$iv = (i$iv + 1 < endIndex) ? string.charAt(i$iv + 1) : 0;
      if (c$iv <= 56319)
        if ((56320 <= low$iv) ? ((low$iv < 57344)) : false) {
          int codePoint$iv = 65536 + ((c$iv & 0x3FF) << 10 | low$iv & 0x3FF);
          Segment tail$iv = $this$commonWriteUtf8$iv.writableSegment$okio(4);
          tail$iv.data[tail$iv.limit] = (byte)(codePoint$iv >> 18 | 0xF0);
          tail$iv.data[tail$iv.limit + 1] = (byte)(codePoint$iv >> 12 & 0x3F | 0x80);
          tail$iv.data[tail$iv.limit + 2] = (byte)(codePoint$iv >> 6 & 0x3F | 0x80);
          tail$iv.data[tail$iv.limit + 3] = (byte)(codePoint$iv & 0x3F | 0x80);
          tail$iv.limit += 4;
          $this$commonWriteUtf8$iv.setSize$okio($this$commonWriteUtf8$iv.size() + 4L);
          i$iv += 2;
        }  
      $this$commonWriteUtf8$iv.writeByte(63);
      i$iv++;
    } 
    return $this$commonWriteUtf8$iv;
  }
  
  @NotNull
  public Buffer writeUtf8CodePoint(int codePoint) {
    Buffer $this$commonWriteUtf8CodePoint$iv = this;
    int $i$f$commonWriteUtf8CodePoint = 0;
    if (codePoint < 128) {
      $this$commonWriteUtf8CodePoint$iv.writeByte(codePoint);
    } else if (codePoint < 2048) {
      Segment tail$iv = $this$commonWriteUtf8CodePoint$iv.writableSegment$okio(2);
      tail$iv.data[tail$iv.limit] = (byte)(codePoint >> 6 | 0xC0);
      tail$iv.data[tail$iv.limit + 1] = (byte)(codePoint & 0x3F | 0x80);
      tail$iv.limit += 2;
      $this$commonWriteUtf8CodePoint$iv.setSize$okio($this$commonWriteUtf8CodePoint$iv.size() + 2L);
    } else if ((55296 <= codePoint) ? ((codePoint < 57344)) : false) {
      $this$commonWriteUtf8CodePoint$iv.writeByte(63);
    } else if (codePoint < 65536) {
      Segment tail$iv = $this$commonWriteUtf8CodePoint$iv.writableSegment$okio(3);
      tail$iv.data[tail$iv.limit] = (byte)(codePoint >> 12 | 0xE0);
      tail$iv.data[tail$iv.limit + 1] = (byte)(codePoint >> 6 & 0x3F | 0x80);
      tail$iv.data[tail$iv.limit + 2] = (byte)(codePoint & 0x3F | 0x80);
      tail$iv.limit += 3;
      $this$commonWriteUtf8CodePoint$iv.setSize$okio($this$commonWriteUtf8CodePoint$iv.size() + 3L);
    } else if (codePoint <= 1114111) {
      Segment tail$iv = $this$commonWriteUtf8CodePoint$iv.writableSegment$okio(4);
      tail$iv.data[tail$iv.limit] = (byte)(codePoint >> 18 | 0xF0);
      tail$iv.data[tail$iv.limit + 1] = (byte)(codePoint >> 12 & 0x3F | 0x80);
      tail$iv.data[tail$iv.limit + 2] = (byte)(codePoint >> 6 & 0x3F | 0x80);
      tail$iv.data[tail$iv.limit + 3] = (byte)(codePoint & 0x3F | 0x80);
      tail$iv.limit += 4;
      $this$commonWriteUtf8CodePoint$iv.setSize$okio($this$commonWriteUtf8CodePoint$iv.size() + 4L);
    } else {
      throw new IllegalArgumentException("Unexpected code point: 0x" + -SegmentedByteString.toHexString(codePoint));
    } 
    return $this$commonWriteUtf8CodePoint$iv;
  }
  
  @NotNull
  public Buffer writeString(@NotNull String string, @NotNull Charset charset) {
    Intrinsics.checkNotNullParameter(string, "string");
    Intrinsics.checkNotNullParameter(charset, "charset");
    return writeString(string, 0, string.length(), charset);
  }
  
  @NotNull
  public Buffer writeString(@NotNull String string, int beginIndex, int endIndex, @NotNull Charset charset) {
    Intrinsics.checkNotNullParameter(string, "string");
    Intrinsics.checkNotNullParameter(charset, "charset");
    if (!((beginIndex >= 0) ? 1 : 0)) {
      int $i$a$-require-Buffer$writeString$1 = 0;
      String str = "beginIndex < 0: " + beginIndex;
      throw new IllegalArgumentException(str.toString());
    } 
    if (!((endIndex >= beginIndex) ? 1 : 0)) {
      int $i$a$-require-Buffer$writeString$2 = 0;
      String str = "endIndex < beginIndex: " + endIndex + " < " + beginIndex;
      throw new IllegalArgumentException(str.toString());
    } 
    if (!((endIndex <= string.length()) ? 1 : 0)) {
      int $i$a$-require-Buffer$writeString$3 = 0;
      String str = "endIndex > string.length: " + endIndex + " > " + string.length();
      throw new IllegalArgumentException(str.toString());
    } 
    if (Intrinsics.areEqual(charset, Charsets.UTF_8))
      return writeUtf8(string, beginIndex, endIndex); 
    Intrinsics.checkNotNullExpressionValue(string.substring(beginIndex, endIndex), "this as java.lang.String…ing(startIndex, endIndex)");
    Intrinsics.checkNotNullExpressionValue(string.substring(beginIndex, endIndex).getBytes(charset), "this as java.lang.String).getBytes(charset)");
    byte[] data = string.substring(beginIndex, endIndex).getBytes(charset);
    return write(data, 0, data.length);
  }
  
  @NotNull
  public Buffer write(@NotNull byte[] source) {
    Intrinsics.checkNotNullParameter(source, "source");
    Buffer $this$commonWrite$iv = this;
    int $i$f$commonWrite = 0;
    return $this$commonWrite$iv.write(source, 0, source.length);
  }
  
  @NotNull
  public Buffer write(@NotNull byte[] source, int offset, int byteCount) {
    Intrinsics.checkNotNullParameter(source, "source");
    Buffer $this$commonWrite$iv = this;
    int $i$f$commonWrite = 0;
    int offset$iv = offset;
    -SegmentedByteString.checkOffsetAndCount(source.length, offset$iv, byteCount);
    int limit$iv = offset$iv + byteCount;
    while (offset$iv < limit$iv) {
      Segment tail$iv = $this$commonWrite$iv.writableSegment$okio(1);
      int toCopy$iv = Math.min(limit$iv - offset$iv, 8192 - tail$iv.limit);
      ArraysKt.copyInto(source, 
          tail$iv.data, 
          tail$iv.limit, 
          offset$iv, 
          offset$iv + toCopy$iv);
      offset$iv += toCopy$iv;
      tail$iv.limit += toCopy$iv;
    } 
    $this$commonWrite$iv.setSize$okio($this$commonWrite$iv.size() + byteCount);
    return $this$commonWrite$iv;
  }
  
  public int write(@NotNull ByteBuffer source) throws IOException {
    Intrinsics.checkNotNullParameter(source, "source");
    int byteCount = source.remaining();
    int remaining = byteCount;
    while (remaining > 0) {
      Segment tail = writableSegment$okio(1);
      int toCopy = Math.min(remaining, 8192 - tail.limit);
      source.get(tail.data, tail.limit, toCopy);
      remaining -= toCopy;
      tail.limit += toCopy;
    } 
    this.size += byteCount;
    return byteCount;
  }
  
  public long writeAll(@NotNull Source source) throws IOException {
    Intrinsics.checkNotNullParameter(source, "source");
    Buffer $this$commonWriteAll$iv = this;
    int $i$f$commonWriteAll = 0;
    long totalBytesRead$iv = 0L;
    while (true) {
      long readCount$iv = source.read($this$commonWriteAll$iv, 8192L);
      if (readCount$iv != -1L) {
        totalBytesRead$iv += readCount$iv;
        continue;
      } 
      break;
    } 
    return totalBytesRead$iv;
  }
  
  @NotNull
  public Buffer write(@NotNull Source source, long byteCount) throws IOException {
    Intrinsics.checkNotNullParameter(source, "source");
    Buffer $this$commonWrite$iv = this;
    int $i$f$commonWrite = 0;
    long byteCount$iv = byteCount;
    while (byteCount$iv > 0L) {
      long read$iv = source.read($this$commonWrite$iv, byteCount$iv);
      if (read$iv == -1L)
        throw new EOFException(); 
      byteCount$iv -= read$iv;
    } 
    return $this$commonWrite$iv;
  }
  
  @NotNull
  public Buffer writeByte(int b) {
    Buffer $this$commonWriteByte$iv = this;
    int $i$f$commonWriteByte = 0;
    Segment tail$iv = $this$commonWriteByte$iv.writableSegment$okio(1);
    int i = tail$iv.limit;
    tail$iv.limit = i + 1;
    tail$iv.data[i] = (byte)b;
    $this$commonWriteByte$iv.setSize$okio($this$commonWriteByte$iv.size() + 1L);
    return $this$commonWriteByte$iv;
  }
  
  @NotNull
  public Buffer writeShort(int s) {
    Buffer $this$commonWriteShort$iv = this;
    int $i$f$commonWriteShort = 0;
    Segment tail$iv = $this$commonWriteShort$iv.writableSegment$okio(2);
    byte[] data$iv = tail$iv.data;
    int limit$iv = tail$iv.limit;
    data$iv[limit$iv++] = (byte)(s >>> 8 & 0xFF);
    data$iv[limit$iv++] = (byte)(s & 0xFF);
    tail$iv.limit = limit$iv;
    $this$commonWriteShort$iv.setSize$okio($this$commonWriteShort$iv.size() + 2L);
    return $this$commonWriteShort$iv;
  }
  
  @NotNull
  public Buffer writeShortLe(int s) {
    return writeShort(-SegmentedByteString.reverseBytes((short)s));
  }
  
  @NotNull
  public Buffer writeInt(int i) {
    Buffer $this$commonWriteInt$iv = this;
    int $i$f$commonWriteInt = 0;
    Segment tail$iv = $this$commonWriteInt$iv.writableSegment$okio(4);
    byte[] data$iv = tail$iv.data;
    int limit$iv = tail$iv.limit;
    data$iv[limit$iv++] = (byte)(i >>> 24 & 0xFF);
    data$iv[limit$iv++] = (byte)(i >>> 16 & 0xFF);
    data$iv[limit$iv++] = (byte)(i >>> 8 & 0xFF);
    data$iv[limit$iv++] = (byte)(i & 0xFF);
    tail$iv.limit = limit$iv;
    $this$commonWriteInt$iv.setSize$okio($this$commonWriteInt$iv.size() + 4L);
    return $this$commonWriteInt$iv;
  }
  
  @NotNull
  public Buffer writeIntLe(int i) {
    return writeInt(-SegmentedByteString.reverseBytes(i));
  }
  
  @NotNull
  public Buffer writeLong(long v) {
    Buffer $this$commonWriteLong$iv = this;
    int $i$f$commonWriteLong = 0;
    Segment tail$iv = $this$commonWriteLong$iv.writableSegment$okio(8);
    byte[] data$iv = tail$iv.data;
    int limit$iv = tail$iv.limit;
    data$iv[limit$iv++] = (byte)(int)(v >>> 56L & 0xFFL);
    data$iv[limit$iv++] = (byte)(int)(v >>> 48L & 0xFFL);
    data$iv[limit$iv++] = (byte)(int)(v >>> 40L & 0xFFL);
    data$iv[limit$iv++] = (byte)(int)(v >>> 32L & 0xFFL);
    data$iv[limit$iv++] = (byte)(int)(v >>> 24L & 0xFFL);
    data$iv[limit$iv++] = (byte)(int)(v >>> 16L & 0xFFL);
    data$iv[limit$iv++] = (byte)(int)(v >>> 8L & 0xFFL);
    data$iv[limit$iv++] = (byte)(int)(v & 0xFFL);
    tail$iv.limit = limit$iv;
    $this$commonWriteLong$iv.setSize$okio($this$commonWriteLong$iv.size() + 8L);
    return $this$commonWriteLong$iv;
  }
  
  @NotNull
  public Buffer writeLongLe(long v) {
    return writeLong(-SegmentedByteString.reverseBytes(v));
  }
  
  @NotNull
  public Buffer writeDecimalLong(long v) {
    // Byte code:
    //   0: aload_0
    //   1: astore_3
    //   2: iconst_0
    //   3: istore #4
    //   5: lload_1
    //   6: lstore #5
    //   8: lload #5
    //   10: lconst_0
    //   11: lcmp
    //   12: ifne -> 24
    //   15: aload_3
    //   16: bipush #48
    //   18: invokevirtual writeByte : (I)Lokio/Buffer;
    //   21: goto -> 426
    //   24: iconst_0
    //   25: istore #7
    //   27: lload #5
    //   29: lconst_0
    //   30: lcmp
    //   31: ifge -> 59
    //   34: lload #5
    //   36: lneg
    //   37: lstore #5
    //   39: lload #5
    //   41: lconst_0
    //   42: lcmp
    //   43: ifge -> 56
    //   46: aload_3
    //   47: ldc_w '-9223372036854775808'
    //   50: invokevirtual writeUtf8 : (Ljava/lang/String;)Lokio/Buffer;
    //   53: goto -> 426
    //   56: iconst_1
    //   57: istore #7
    //   59: lload #5
    //   61: ldc2_w 100000000
    //   64: lcmp
    //   65: ifge -> 166
    //   68: lload #5
    //   70: ldc2_w 10000
    //   73: lcmp
    //   74: ifge -> 120
    //   77: lload #5
    //   79: ldc2_w 100
    //   82: lcmp
    //   83: ifge -> 103
    //   86: lload #5
    //   88: ldc2_w 10
    //   91: lcmp
    //   92: ifge -> 99
    //   95: iconst_1
    //   96: goto -> 308
    //   99: iconst_2
    //   100: goto -> 308
    //   103: lload #5
    //   105: ldc2_w 1000
    //   108: lcmp
    //   109: ifge -> 116
    //   112: iconst_3
    //   113: goto -> 308
    //   116: iconst_4
    //   117: goto -> 308
    //   120: lload #5
    //   122: ldc2_w 1000000
    //   125: lcmp
    //   126: ifge -> 147
    //   129: lload #5
    //   131: ldc2_w 100000
    //   134: lcmp
    //   135: ifge -> 142
    //   138: iconst_5
    //   139: goto -> 308
    //   142: bipush #6
    //   144: goto -> 308
    //   147: lload #5
    //   149: ldc2_w 10000000
    //   152: lcmp
    //   153: ifge -> 161
    //   156: bipush #7
    //   158: goto -> 308
    //   161: bipush #8
    //   163: goto -> 308
    //   166: lload #5
    //   168: ldc2_w 1000000000000
    //   171: lcmp
    //   172: ifge -> 222
    //   175: lload #5
    //   177: ldc2_w 10000000000
    //   180: lcmp
    //   181: ifge -> 203
    //   184: lload #5
    //   186: ldc2_w 1000000000
    //   189: lcmp
    //   190: ifge -> 198
    //   193: bipush #9
    //   195: goto -> 308
    //   198: bipush #10
    //   200: goto -> 308
    //   203: lload #5
    //   205: ldc2_w 100000000000
    //   208: lcmp
    //   209: ifge -> 217
    //   212: bipush #11
    //   214: goto -> 308
    //   217: bipush #12
    //   219: goto -> 308
    //   222: lload #5
    //   224: ldc2_w 1000000000000000
    //   227: lcmp
    //   228: ifge -> 264
    //   231: lload #5
    //   233: ldc2_w 10000000000000
    //   236: lcmp
    //   237: ifge -> 245
    //   240: bipush #13
    //   242: goto -> 308
    //   245: lload #5
    //   247: ldc2_w 100000000000000
    //   250: lcmp
    //   251: ifge -> 259
    //   254: bipush #14
    //   256: goto -> 308
    //   259: bipush #15
    //   261: goto -> 308
    //   264: lload #5
    //   266: ldc2_w 100000000000000000
    //   269: lcmp
    //   270: ifge -> 292
    //   273: lload #5
    //   275: ldc2_w 10000000000000000
    //   278: lcmp
    //   279: ifge -> 287
    //   282: bipush #16
    //   284: goto -> 308
    //   287: bipush #17
    //   289: goto -> 308
    //   292: lload #5
    //   294: ldc2_w 1000000000000000000
    //   297: lcmp
    //   298: ifge -> 306
    //   301: bipush #18
    //   303: goto -> 308
    //   306: bipush #19
    //   308: istore #8
    //   310: iload #7
    //   312: ifeq -> 319
    //   315: iinc #8, 1
    //   318: nop
    //   319: aload_3
    //   320: iload #8
    //   322: invokevirtual writableSegment$okio : (I)Lokio/Segment;
    //   325: astore #9
    //   327: aload #9
    //   329: getfield data : [B
    //   332: astore #10
    //   334: aload #9
    //   336: getfield limit : I
    //   339: iload #8
    //   341: iadd
    //   342: istore #11
    //   344: lload #5
    //   346: lconst_0
    //   347: lcmp
    //   348: ifeq -> 385
    //   351: lload #5
    //   353: bipush #10
    //   355: i2l
    //   356: lrem
    //   357: l2i
    //   358: istore #12
    //   360: aload #10
    //   362: iinc #11, -1
    //   365: iload #11
    //   367: invokestatic getHEX_DIGIT_BYTES : ()[B
    //   370: iload #12
    //   372: baload
    //   373: bastore
    //   374: lload #5
    //   376: bipush #10
    //   378: i2l
    //   379: ldiv
    //   380: lstore #5
    //   382: goto -> 344
    //   385: iload #7
    //   387: ifeq -> 400
    //   390: aload #10
    //   392: iinc #11, -1
    //   395: iload #11
    //   397: bipush #45
    //   399: bastore
    //   400: aload #9
    //   402: aload #9
    //   404: getfield limit : I
    //   407: iload #8
    //   409: iadd
    //   410: putfield limit : I
    //   413: aload_3
    //   414: aload_3
    //   415: invokevirtual size : ()J
    //   418: iload #8
    //   420: i2l
    //   421: ladd
    //   422: invokevirtual setSize$okio : (J)V
    //   425: aload_3
    //   426: areturn
    // Line number table:
    //   Java source line number -> byte code offset
    //   #457	-> 0
    //   #1351	-> 5
    //   #1352	-> 8
    //   #1354	-> 15
    //   #1357	-> 24
    //   #1358	-> 27
    //   #1359	-> 34
    //   #1360	-> 39
    //   #1361	-> 46
    //   #1363	-> 56
    //   #1368	-> 59
    //   #1369	-> 68
    //   #1370	-> 77
    //   #1371	-> 86
    //   #1372	-> 95
    //   #1374	-> 99
    //   #1376	-> 103
    //   #1377	-> 112
    //   #1379	-> 116
    //   #1381	-> 120
    //   #1382	-> 129
    //   #1383	-> 138
    //   #1385	-> 142
    //   #1387	-> 147
    //   #1388	-> 156
    //   #1390	-> 161
    //   #1392	-> 166
    //   #1393	-> 175
    //   #1394	-> 184
    //   #1395	-> 193
    //   #1397	-> 198
    //   #1399	-> 203
    //   #1400	-> 212
    //   #1402	-> 217
    //   #1404	-> 222
    //   #1405	-> 231
    //   #1406	-> 240
    //   #1407	-> 245
    //   #1408	-> 254
    //   #1410	-> 259
    //   #1412	-> 264
    //   #1413	-> 273
    //   #1414	-> 282
    //   #1416	-> 287
    //   #1418	-> 292
    //   #1419	-> 301
    //   #1421	-> 306
    //   #1368	-> 308
    //   #1367	-> 308
    //   #1423	-> 310
    //   #1424	-> 318
    //   #1427	-> 319
    //   #1428	-> 327
    //   #1429	-> 334
    //   #1430	-> 344
    //   #1431	-> 351
    //   #1432	-> 360
    //   #1433	-> 374
    //   #1435	-> 385
    //   #1436	-> 390
    //   #1439	-> 400
    //   #1440	-> 413
    //   #1441	-> 425
    //   #457	-> 426
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   360	22	12	digit$iv	I
    //   5	421	4	$i$f$commonWriteDecimalLong	I
    //   8	418	5	v$iv	J
    //   27	399	7	negative$iv	Z
    //   310	116	8	width$iv	I
    //   327	99	9	tail$iv	Lokio/Segment;
    //   334	92	10	data$iv	[B
    //   344	82	11	pos$iv	I
    //   2	424	3	$this$commonWriteDecimalLong$iv	Lokio/Buffer;
    //   0	427	0	this	Lokio/Buffer;
    //   0	427	1	v	J
  }
  
  @NotNull
  public Buffer writeHexadecimalUnsignedLong(long v) {
    Buffer $this$commonWriteHexadecimalUnsignedLong$iv = this;
    int $i$f$commonWriteHexadecimalUnsignedLong = 0;
    long v$iv = v;
    long x$iv = v$iv;
    x$iv |= x$iv >>> 1L;
    x$iv |= x$iv >>> 2L;
    x$iv |= x$iv >>> 4L;
    x$iv |= x$iv >>> 8L;
    x$iv |= x$iv >>> 16L;
    x$iv |= x$iv >>> 32L;
    x$iv -= x$iv >>> 1L & 0x5555555555555555L;
    x$iv = (x$iv >>> 2L & 0x3333333333333333L) + (x$iv & 0x3333333333333333L);
    x$iv = (x$iv >>> 4L) + x$iv & 0xF0F0F0F0F0F0F0FL;
    x$iv += x$iv >>> 8L;
    x$iv += x$iv >>> 16L;
    x$iv = (x$iv & 0x3FL) + (x$iv >>> 32L & 0x3FL);
    int width$iv = (int)((x$iv + 3L) / 4L);
    Segment tail$iv = $this$commonWriteHexadecimalUnsignedLong$iv.writableSegment$okio(width$iv);
    byte[] data$iv = tail$iv.data;
    int pos$iv = tail$iv.limit + width$iv - 1;
    int start$iv = tail$iv.limit;
    while (pos$iv >= start$iv) {
      data$iv[pos$iv] = -Buffer.getHEX_DIGIT_BYTES()[(int)(v$iv & 0xFL)];
      v$iv >>>= 4L;
      pos$iv--;
    } 
    tail$iv.limit += width$iv;
    $this$commonWriteHexadecimalUnsignedLong$iv.setSize$okio($this$commonWriteHexadecimalUnsignedLong$iv.size() + width$iv);
    return (v$iv == 0L) ? $this$commonWriteHexadecimalUnsignedLong$iv.writeByte(48) : $this$commonWriteHexadecimalUnsignedLong$iv;
  }
  
  @NotNull
  public final Segment writableSegment$okio(int minimumCapacity) {
    Buffer $this$commonWritableSegment$iv = this;
    int $i$f$commonWritableSegment = 0;
    if (!((minimumCapacity >= 1 && minimumCapacity <= 8192) ? 1 : 0)) {
      int $i$a$-require--Buffer$commonWritableSegment$1$iv = 0;
      String str = "unexpected capacity";
      throw new IllegalArgumentException(str.toString());
    } 
    Segment result$iv = SegmentPool.take();
    $this$commonWritableSegment$iv.head = result$iv;
    result$iv.prev = result$iv;
    result$iv.next = result$iv;
    Intrinsics.checkNotNull($this$commonWritableSegment$iv.head);
    Segment tail$iv = $this$commonWritableSegment$iv.head.prev;
    Intrinsics.checkNotNull(tail$iv);
    if (tail$iv.limit + minimumCapacity > 8192 || !tail$iv.owner)
      tail$iv = tail$iv.push(SegmentPool.take()); 
    return ($this$commonWritableSegment$iv.head == null) ? result$iv : tail$iv;
  }
  
  public void write(@NotNull Buffer source, long byteCount) {
    Intrinsics.checkNotNullParameter(source, "source");
    Buffer $this$commonWrite$iv = this;
    int $i$f$commonWrite = 0;
    long byteCount$iv = byteCount;
    if (!((source != $this$commonWrite$iv) ? 1 : 0)) {
      int $i$a$-require--Buffer$commonWrite$1$iv = 0;
      String str = "source == this";
      throw new IllegalArgumentException(str.toString());
    } 
    -SegmentedByteString.checkOffsetAndCount(source.size(), 0L, byteCount$iv);
    while (true) {
      if (byteCount$iv > 0L) {
        Intrinsics.checkNotNull(source.head);
        Intrinsics.checkNotNull(source.head);
        if (byteCount$iv < (source.head.limit - source.head.pos)) {
          Intrinsics.checkNotNull($this$commonWrite$iv.head);
          Segment tail$iv = ($this$commonWrite$iv.head != null) ? $this$commonWrite$iv.head.prev : null;
          if (tail$iv != null && tail$iv.owner && 
            byteCount$iv + tail$iv.limit - (tail$iv.shared ? 0L : tail$iv.pos) <= 8192L) {
            Intrinsics.checkNotNull(source.head);
            source.head.writeTo(tail$iv, (int)byteCount$iv);
            source.setSize$okio(source.size() - byteCount$iv);
            $this$commonWrite$iv.setSize$okio($this$commonWrite$iv.size() + byteCount$iv);
            break;
          } 
          Intrinsics.checkNotNull(source.head);
          source.head = source.head.split((int)byteCount$iv);
        } 
        Segment segmentToMove$iv = source.head;
        Intrinsics.checkNotNull(segmentToMove$iv);
        long movedByteCount$iv = (segmentToMove$iv.limit - segmentToMove$iv.pos);
        source.head = segmentToMove$iv.pop();
        if ($this$commonWrite$iv.head == null) {
          $this$commonWrite$iv.head = segmentToMove$iv;
          segmentToMove$iv.prev = segmentToMove$iv;
          segmentToMove$iv.next = segmentToMove$iv.prev;
        } else {
          Intrinsics.checkNotNull($this$commonWrite$iv.head);
          Segment tail$iv = $this$commonWrite$iv.head.prev;
          Intrinsics.checkNotNull(tail$iv);
          tail$iv = tail$iv.push(segmentToMove$iv);
          tail$iv.compact();
        } 
        source.setSize$okio(source.size() - movedByteCount$iv);
        $this$commonWrite$iv.setSize$okio($this$commonWrite$iv.size() + movedByteCount$iv);
        byteCount$iv -= movedByteCount$iv;
        continue;
      } 
      break;
    } 
  }
  
  public long read(@NotNull Buffer sink, long byteCount) {
    Intrinsics.checkNotNullParameter(sink, "sink");
    Buffer $this$commonRead$iv = this;
    int $i$f$commonRead = 0;
    long byteCount$iv = 0L;
    byteCount$iv = byteCount;
    if (!((byteCount$iv >= 0L) ? 1 : 0)) {
      int $i$a$-require--Buffer$commonRead$1$iv = 0;
      String str = "byteCount < 0: " + byteCount$iv;
      throw new IllegalArgumentException(str.toString());
    } 
    if (byteCount$iv > $this$commonRead$iv.size())
      byteCount$iv = $this$commonRead$iv.size(); 
    sink.write($this$commonRead$iv, byteCount$iv);
    return ($this$commonRead$iv.size() == 0L) ? -1L : byteCount$iv;
  }
  
  public long indexOf(byte b) {
    return indexOf(b, 0L, Long.MAX_VALUE);
  }
  
  public long indexOf(byte b, long fromIndex) {
    return indexOf(b, fromIndex, Long.MAX_VALUE);
  }
  
  public long indexOf(byte b, long fromIndex, long toIndex) {
    Segment s$iv$iv, segment2;
    Buffer $this$commonIndexOf$iv = this;
    int $i$f$commonIndexOf = 0;
    long fromIndex$iv = 0L;
    fromIndex$iv = fromIndex;
    long toIndex$iv = 0L;
    toIndex$iv = toIndex;
    if (!((0L <= fromIndex$iv) ? ((fromIndex$iv <= toIndex$iv) ? 1 : 0) : 0)) {
      int $i$a$-require--Buffer$commonIndexOf$1$iv = 0;
      String str = "size=" + $this$commonIndexOf$iv.size() + " fromIndex=" + fromIndex$iv + " toIndex=" + toIndex$iv;
      throw new IllegalArgumentException(str.toString());
    } 
    if (toIndex$iv > $this$commonIndexOf$iv.size())
      toIndex$iv = $this$commonIndexOf$iv.size(); 
    Buffer buffer1 = $this$commonIndexOf$iv;
    long fromIndex$iv$iv = fromIndex$iv;
    int $i$f$seek = 0;
    long l1 = -1L;
    Segment s$iv = null;
    int $i$a$-seek--Buffer$commonIndexOf$2$iv = 0;
    if (buffer1.size() - fromIndex$iv$iv < fromIndex$iv$iv) {
      Segment segment;
      long l5 = buffer1.size();
      while (l5 > fromIndex$iv$iv) {
        Intrinsics.checkNotNull(s$iv$iv.prev);
        s$iv$iv = s$iv$iv.prev;
        l5 -= (s$iv$iv.limit - s$iv$iv.pos);
      } 
      long l3 = l5;
      s$iv = s$iv$iv;
      $i$a$-seek--Buffer$commonIndexOf$2$iv = 0;
      long l4 = l3;
      while (l4 < toIndex$iv) {
        byte[] data$iv = segment.data;
        int limit$iv = (int)Math.min(segment.limit, segment.pos + toIndex$iv - l4);
        int pos$iv = (int)(segment.pos + fromIndex$iv - l4);
        while (pos$iv < limit$iv) {
          if (data$iv[pos$iv] == b) {
          
          } else {
            pos$iv++;
            continue;
          } 
          return segment = s$iv;
        } 
        l4 += (segment.limit - segment.pos);
        fromIndex$iv = l4;
        Intrinsics.checkNotNull(segment.next);
        segment = segment.next;
      } 
    } 
    long offset$iv$iv = 0L;
    while (true) {
      long nextOffset$iv$iv = offset$iv$iv + (s$iv$iv.limit - s$iv$iv.pos);
      if (nextOffset$iv$iv <= fromIndex$iv$iv) {
        Intrinsics.checkNotNull(s$iv$iv.next);
        s$iv$iv = s$iv$iv.next;
        offset$iv$iv = nextOffset$iv$iv;
        continue;
      } 
      break;
    } 
    long l2 = offset$iv$iv;
    Segment segment1 = s$iv$iv;
    int i = 0;
    long offset$iv = l2;
    while (offset$iv < toIndex$iv) {
      byte[] data$iv = segment2.data;
      int limit$iv = (int)Math.min(segment2.limit, segment2.pos + toIndex$iv - offset$iv);
      int pos$iv = (int)(segment2.pos + fromIndex$iv - offset$iv);
      while (pos$iv < limit$iv) {
        if (data$iv[pos$iv] == b) {
        
        } else {
          pos$iv++;
          continue;
        } 
        return segment2 = segment1;
      } 
      offset$iv += (segment2.limit - segment2.pos);
      fromIndex$iv = offset$iv;
      Intrinsics.checkNotNull(segment2.next);
      segment2 = segment2.next;
    } 
    return (fromIndex$iv == toIndex$iv) ? -1L : ((buffer1.head == null) ? -1L : ((segment1 == null) ? -1L : -1L));
  }
  
  public long indexOf(@NotNull ByteString bytes) throws IOException {
    Intrinsics.checkNotNullParameter(bytes, "bytes");
    return indexOf(bytes, 0L);
  }
  
  public long indexOf(@NotNull ByteString bytes, long fromIndex) throws IOException {
    Segment s$iv$iv, segment2;
    Intrinsics.checkNotNullParameter(bytes, "bytes");
    Buffer $this$commonIndexOf$iv = this;
    int $i$f$commonIndexOf = 0;
    long fromIndex$iv = 0L;
    fromIndex$iv = fromIndex;
    if (!((bytes.size() > 0) ? 1 : 0)) {
      int $i$a$-require--Buffer$commonIndexOf$3$iv = 0;
      String str = "bytes is empty";
      throw new IllegalArgumentException(str.toString());
    } 
    if (!((fromIndex$iv >= 0L) ? 1 : 0)) {
      int $i$a$-require--Buffer$commonIndexOf$4$iv = 0;
      String str = "fromIndex < 0: " + fromIndex$iv;
      throw new IllegalArgumentException(str.toString());
    } 
    Buffer buffer1 = $this$commonIndexOf$iv;
    long fromIndex$iv$iv = fromIndex$iv;
    int $i$f$seek = 0;
    long l1 = -1L;
    Segment s$iv = null;
    int $i$a$-seek--Buffer$commonIndexOf$5$iv = 0;
    if (buffer1.size() - fromIndex$iv$iv < fromIndex$iv$iv) {
      Segment segment;
      long l6 = buffer1.size();
      while (l6 > fromIndex$iv$iv) {
        Intrinsics.checkNotNull(s$iv$iv.prev);
        s$iv$iv = s$iv$iv.prev;
        l6 -= (s$iv$iv.limit - s$iv$iv.pos);
      } 
      long l3 = l6;
      s$iv = s$iv$iv;
      $i$a$-seek--Buffer$commonIndexOf$5$iv = 0;
      long l4 = l3;
      byte[] arrayOfByte = bytes.internalArray$okio();
      byte b = arrayOfByte[0];
      int j = bytes.size();
      long l5 = $this$commonIndexOf$iv.size() - j + 1L;
      while (l4 < l5) {
        byte[] data$iv = segment.data;
        int k = segment.limit;
        long b$iv$iv = segment.pos + l5 - l4;
        int $i$f$minOf = 0, segmentLimit$iv = (int)Math.min(k, b$iv$iv);
        for (int pos$iv = (int)(segment.pos + fromIndex$iv - l4); pos$iv < segmentLimit$iv; ) {
          if (data$iv[pos$iv] == b && -Buffer.rangeEquals(segment, pos$iv + 1, arrayOfByte, 1, j)) {
          
          } else {
            pos$iv++;
            continue;
          } 
          return segment = s$iv;
        } 
        l4 += (segment.limit - segment.pos);
        fromIndex$iv = l4;
        Intrinsics.checkNotNull(segment.next);
        segment = segment.next;
      } 
    } 
    long offset$iv$iv = 0L;
    while (true) {
      long nextOffset$iv$iv = offset$iv$iv + (s$iv$iv.limit - s$iv$iv.pos);
      if (nextOffset$iv$iv <= fromIndex$iv$iv) {
        Intrinsics.checkNotNull(s$iv$iv.next);
        s$iv$iv = s$iv$iv.next;
        offset$iv$iv = nextOffset$iv$iv;
        continue;
      } 
      break;
    } 
    long l2 = offset$iv$iv;
    Segment segment1 = s$iv$iv;
    int i = 0;
    long offset$iv = l2;
    byte[] targetByteArray$iv = bytes.internalArray$okio();
    byte b0$iv = targetByteArray$iv[0];
    int bytesSize$iv = bytes.size();
    long resultLimit$iv = $this$commonIndexOf$iv.size() - bytesSize$iv + 1L;
    while (offset$iv < resultLimit$iv) {
      byte[] data$iv = segment2.data;
      int j = segment2.limit;
      long b$iv$iv = segment2.pos + resultLimit$iv - offset$iv;
      int $i$f$minOf = 0, segmentLimit$iv = (int)Math.min(j, b$iv$iv);
      for (int pos$iv = (int)(segment2.pos + fromIndex$iv - offset$iv); pos$iv < segmentLimit$iv; ) {
        if (data$iv[pos$iv] == b0$iv && -Buffer.rangeEquals(segment2, pos$iv + 1, targetByteArray$iv, 1, bytesSize$iv)) {
        
        } else {
          pos$iv++;
          continue;
        } 
        return segment2 = segment1;
      } 
      offset$iv += (segment2.limit - segment2.pos);
      fromIndex$iv = offset$iv;
      Intrinsics.checkNotNull(segment2.next);
      segment2 = segment2.next;
    } 
    return (buffer1.head == null) ? -1L : ((segment1 == null) ? -1L : -1L);
  }
  
  public long indexOfElement(@NotNull ByteString targetBytes) {
    Intrinsics.checkNotNullParameter(targetBytes, "targetBytes");
    return indexOfElement(targetBytes, 0L);
  }
  
  public long indexOfElement(@NotNull ByteString targetBytes, long fromIndex) {
    Segment s$iv$iv, segment2;
    Intrinsics.checkNotNullParameter(targetBytes, "targetBytes");
    Buffer $this$commonIndexOfElement$iv = this;
    int $i$f$commonIndexOfElement = 0;
    long fromIndex$iv = 0L;
    fromIndex$iv = fromIndex;
    if (!((fromIndex$iv >= 0L) ? 1 : 0)) {
      int $i$a$-require--Buffer$commonIndexOfElement$1$iv = 0;
      String str = "fromIndex < 0: " + fromIndex$iv;
      throw new IllegalArgumentException(str.toString());
    } 
    Buffer buffer1 = $this$commonIndexOfElement$iv;
    long fromIndex$iv$iv = fromIndex$iv;
    int $i$f$seek = 0;
    long l1 = -1L;
    Segment s$iv = null;
    int $i$a$-seek--Buffer$commonIndexOfElement$2$iv = 0;
    if (buffer1.size() - fromIndex$iv$iv < fromIndex$iv$iv) {
      Segment segment;
      long l5 = buffer1.size();
      while (l5 > fromIndex$iv$iv) {
        Intrinsics.checkNotNull(s$iv$iv.prev);
        s$iv$iv = s$iv$iv.prev;
        l5 -= (s$iv$iv.limit - s$iv$iv.pos);
      } 
      long l3 = l5;
      s$iv = s$iv$iv;
      $i$a$-seek--Buffer$commonIndexOfElement$2$iv = 0;
      long l4 = l3;
      if (targetBytes.size() == 2) {
        byte b0$iv = targetBytes.getByte(0);
        byte b1$iv = targetBytes.getByte(1);
        while (l4 < $this$commonIndexOfElement$iv.size()) {
          byte[] data$iv = segment.data;
          int pos$iv = (int)(segment.pos + fromIndex$iv - l4);
          int limit$iv = segment.limit;
          while (pos$iv < limit$iv) {
            int b$iv = data$iv[pos$iv];
            if (b$iv == b0$iv || b$iv == b1$iv) {
            
            } else {
              pos$iv++;
              continue;
            } 
            return segment = s$iv;
          } 
          l4 += (segment.limit - segment.pos);
          fromIndex$iv = l4;
          Intrinsics.checkNotNull(segment.next);
          segment = segment.next;
        } 
      } else {
        byte[] targetByteArray$iv = targetBytes.internalArray$okio();
        while (l4 < $this$commonIndexOfElement$iv.size()) {
          byte[] data$iv = segment.data;
          int pos$iv = (int)(segment.pos + fromIndex$iv - l4);
          int limit$iv = segment.limit;
          while (pos$iv < limit$iv) {
            int b$iv = data$iv[pos$iv];
            byte b;
            int j;
            for (b = 0, j = targetByteArray$iv.length; b < j; ) {
              byte t$iv = targetByteArray$iv[b];
              if (b$iv == t$iv) {
              
              } else {
                b++;
                continue;
              } 
              return segment = s$iv;
            } 
            pos$iv++;
          } 
          l4 += (segment.limit - segment.pos);
          fromIndex$iv = l4;
          Intrinsics.checkNotNull(segment.next);
          segment = segment.next;
        } 
      } 
    } 
    long offset$iv$iv = 0L;
    while (true) {
      long nextOffset$iv$iv = offset$iv$iv + (s$iv$iv.limit - s$iv$iv.pos);
      if (nextOffset$iv$iv <= fromIndex$iv$iv) {
        Intrinsics.checkNotNull(s$iv$iv.next);
        s$iv$iv = s$iv$iv.next;
        offset$iv$iv = nextOffset$iv$iv;
        continue;
      } 
      break;
    } 
    long l2 = offset$iv$iv;
    Segment segment1 = s$iv$iv;
    int i = 0;
    long offset$iv = l2;
    if (targetBytes.size() == 2) {
      byte b0$iv = targetBytes.getByte(0);
      byte b1$iv = targetBytes.getByte(1);
      while (offset$iv < $this$commonIndexOfElement$iv.size()) {
        byte[] data$iv = segment2.data;
        int pos$iv = (int)(segment2.pos + fromIndex$iv - offset$iv);
        int limit$iv = segment2.limit;
        while (pos$iv < limit$iv) {
          int b$iv = data$iv[pos$iv];
          if (b$iv == b0$iv || b$iv == b1$iv) {
          
          } else {
            pos$iv++;
            continue;
          } 
          return segment2 = segment1;
        } 
        offset$iv += (segment2.limit - segment2.pos);
        fromIndex$iv = offset$iv;
        Intrinsics.checkNotNull(segment2.next);
        segment2 = segment2.next;
      } 
    } else {
      byte[] targetByteArray$iv = targetBytes.internalArray$okio();
      while (offset$iv < $this$commonIndexOfElement$iv.size()) {
        byte[] data$iv = segment2.data;
        int pos$iv = (int)(segment2.pos + fromIndex$iv - offset$iv);
        int limit$iv = segment2.limit;
        while (pos$iv < limit$iv) {
          int b$iv = data$iv[pos$iv];
          byte b;
          int j;
          for (b = 0, j = targetByteArray$iv.length; b < j; ) {
            byte t$iv = targetByteArray$iv[b];
            if (b$iv == t$iv) {
            
            } else {
              b++;
              continue;
            } 
            return segment2 = segment1;
          } 
          pos$iv++;
        } 
        offset$iv += (segment2.limit - segment2.pos);
        fromIndex$iv = offset$iv;
        Intrinsics.checkNotNull(segment2.next);
        segment2 = segment2.next;
      } 
    } 
    return (buffer1.head == null) ? -1L : ((segment1 == null) ? -1L : -1L);
  }
  
  public boolean rangeEquals(long offset, @NotNull ByteString bytes) {
    Intrinsics.checkNotNullParameter(bytes, "bytes");
    return rangeEquals(offset, bytes, 0, bytes.size());
  }
  
  public boolean rangeEquals(long offset, @NotNull ByteString bytes, int bytesOffset, int byteCount) {
    // Byte code:
    //   0: aload_3
    //   1: ldc_w 'bytes'
    //   4: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
    //   7: aload_0
    //   8: astore #6
    //   10: iconst_0
    //   11: istore #7
    //   13: lload_1
    //   14: lconst_0
    //   15: lcmp
    //   16: iflt -> 55
    //   19: iload #4
    //   21: iflt -> 55
    //   24: iload #5
    //   26: iflt -> 55
    //   29: aload #6
    //   31: invokevirtual size : ()J
    //   34: lload_1
    //   35: lsub
    //   36: iload #5
    //   38: i2l
    //   39: lcmp
    //   40: iflt -> 55
    //   43: aload_3
    //   44: invokevirtual size : ()I
    //   47: iload #4
    //   49: isub
    //   50: iload #5
    //   52: if_icmpge -> 59
    //   55: iconst_0
    //   56: goto -> 102
    //   59: iconst_0
    //   60: istore #8
    //   62: iload #8
    //   64: iload #5
    //   66: if_icmpge -> 101
    //   69: aload #6
    //   71: lload_1
    //   72: iload #8
    //   74: i2l
    //   75: ladd
    //   76: invokevirtual getByte : (J)B
    //   79: aload_3
    //   80: iload #4
    //   82: iload #8
    //   84: iadd
    //   85: invokevirtual getByte : (I)B
    //   88: if_icmpeq -> 95
    //   91: iconst_0
    //   92: goto -> 102
    //   95: iinc #8, 1
    //   98: goto -> 62
    //   101: iconst_1
    //   102: ireturn
    // Line number table:
    //   Java source line number -> byte code offset
    //   #498	-> 7
    //   #1720	-> 13
    //   #1721	-> 19
    //   #1722	-> 24
    //   #1723	-> 29
    //   #1724	-> 43
    //   #1726	-> 55
    //   #1728	-> 59
    //   #1729	-> 69
    //   #1730	-> 91
    //   #1728	-> 95
    //   #1733	-> 101
    //   #498	-> 102
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   62	39	8	i$iv	I
    //   13	89	7	$i$f$commonRangeEquals	I
    //   10	92	6	$this$commonRangeEquals$iv	Lokio/Buffer;
    //   0	103	0	this	Lokio/Buffer;
    //   0	103	1	offset	J
    //   0	103	3	bytes	Lokio/ByteString;
    //   0	103	4	bytesOffset	I
    //   0	103	5	byteCount	I
  }
  
  public void flush() {}
  
  public boolean isOpen() {
    return true;
  }
  
  public void close() {}
  
  @NotNull
  public Timeout timeout() {
    return Timeout.NONE;
  }
  
  @NotNull
  public final ByteString md5() {
    return digest("MD5");
  }
  
  @NotNull
  public final ByteString sha1() {
    return digest("SHA-1");
  }
  
  @NotNull
  public final ByteString sha256() {
    return digest("SHA-256");
  }
  
  @NotNull
  public final ByteString sha512() {
    return digest("SHA-512");
  }
  
  private final ByteString digest(String algorithm) {
    MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
    Segment head = this.head;
    int $i$a$-let-Buffer$digest$1 = 0;
    messageDigest.update(head.data, head.pos, head.limit - head.pos);
    Intrinsics.checkNotNull(head.next);
    Segment s = head.next;
    while (s != head) {
      messageDigest.update(s.data, s.pos, s.limit - s.pos);
      Intrinsics.checkNotNull(s.next);
      s = s.next;
    } 
    Intrinsics.checkNotNullExpressionValue(messageDigest.digest(), "digest(...)");
    return new ByteString(messageDigest.digest());
  }
  
  @NotNull
  public final ByteString hmacSha1(@NotNull ByteString key) {
    Intrinsics.checkNotNullParameter(key, "key");
    return hmac("HmacSHA1", key);
  }
  
  @NotNull
  public final ByteString hmacSha256(@NotNull ByteString key) {
    Intrinsics.checkNotNullParameter(key, "key");
    return hmac("HmacSHA256", key);
  }
  
  @NotNull
  public final ByteString hmacSha512(@NotNull ByteString key) {
    Intrinsics.checkNotNullParameter(key, "key");
    return hmac("HmacSHA512", key);
  }
  
  private final ByteString hmac(String algorithm, ByteString key) {
    try {
      Mac mac = Mac.getInstance(algorithm);
      mac.init(new SecretKeySpec(key.internalArray$okio(), algorithm));
      Segment head = this.head;
      int $i$a$-let-Buffer$hmac$1 = 0;
      mac.update(head.data, head.pos, head.limit - head.pos);
      Intrinsics.checkNotNull(head.next);
      Segment s = head.next;
      while (s != head) {
        mac.update(s.data, s.pos, s.limit - s.pos);
        Intrinsics.checkNotNull(s.next);
        s = s.next;
      } 
      Intrinsics.checkNotNullExpressionValue(mac.doFinal(), "doFinal(...)");
      return new ByteString(mac.doFinal());
    } catch (InvalidKeyException e) {
      throw new IllegalArgumentException((Throwable)e);
    } 
  }
  
  public boolean equals(@Nullable Object other) {
    // Byte code:
    //   0: aload_0
    //   1: astore_2
    //   2: iconst_0
    //   3: istore_3
    //   4: aload_2
    //   5: aload_1
    //   6: if_acmpne -> 13
    //   9: iconst_1
    //   10: goto -> 251
    //   13: aload_1
    //   14: instanceof okio/Buffer
    //   17: ifne -> 24
    //   20: iconst_0
    //   21: goto -> 251
    //   24: aload_2
    //   25: invokevirtual size : ()J
    //   28: aload_1
    //   29: checkcast okio/Buffer
    //   32: invokevirtual size : ()J
    //   35: lcmp
    //   36: ifeq -> 43
    //   39: iconst_0
    //   40: goto -> 251
    //   43: aload_2
    //   44: invokevirtual size : ()J
    //   47: lconst_0
    //   48: lcmp
    //   49: ifne -> 56
    //   52: iconst_1
    //   53: goto -> 251
    //   56: aload_2
    //   57: getfield head : Lokio/Segment;
    //   60: dup
    //   61: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   64: astore #4
    //   66: aload_1
    //   67: checkcast okio/Buffer
    //   70: getfield head : Lokio/Segment;
    //   73: dup
    //   74: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   77: astore #5
    //   79: aload #4
    //   81: getfield pos : I
    //   84: istore #6
    //   86: aload #5
    //   88: getfield pos : I
    //   91: istore #7
    //   93: lconst_0
    //   94: lstore #8
    //   96: lconst_0
    //   97: lstore #10
    //   99: lload #8
    //   101: aload_2
    //   102: invokevirtual size : ()J
    //   105: lcmp
    //   106: ifge -> 250
    //   109: aload #4
    //   111: getfield limit : I
    //   114: iload #6
    //   116: isub
    //   117: aload #5
    //   119: getfield limit : I
    //   122: iload #7
    //   124: isub
    //   125: invokestatic min : (II)I
    //   128: i2l
    //   129: lstore #10
    //   131: lconst_0
    //   132: lstore #12
    //   134: lload #10
    //   136: lstore #14
    //   138: lload #12
    //   140: lload #14
    //   142: lcmp
    //   143: ifge -> 184
    //   146: aload #4
    //   148: getfield data : [B
    //   151: iload #6
    //   153: iinc #6, 1
    //   156: baload
    //   157: aload #5
    //   159: getfield data : [B
    //   162: iload #7
    //   164: iinc #7, 1
    //   167: baload
    //   168: if_icmpeq -> 175
    //   171: iconst_0
    //   172: goto -> 251
    //   175: lload #12
    //   177: lconst_1
    //   178: ladd
    //   179: lstore #12
    //   181: goto -> 138
    //   184: iload #6
    //   186: aload #4
    //   188: getfield limit : I
    //   191: if_icmpne -> 212
    //   194: aload #4
    //   196: getfield next : Lokio/Segment;
    //   199: dup
    //   200: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   203: astore #4
    //   205: aload #4
    //   207: getfield pos : I
    //   210: istore #6
    //   212: iload #7
    //   214: aload #5
    //   216: getfield limit : I
    //   219: if_icmpne -> 240
    //   222: aload #5
    //   224: getfield next : Lokio/Segment;
    //   227: dup
    //   228: invokestatic checkNotNull : (Ljava/lang/Object;)V
    //   231: astore #5
    //   233: aload #5
    //   235: getfield pos : I
    //   238: istore #7
    //   240: lload #8
    //   242: lload #10
    //   244: ladd
    //   245: lstore #8
    //   247: goto -> 99
    //   250: iconst_1
    //   251: ireturn
    // Line number table:
    //   Java source line number -> byte code offset
    //   #568	-> 0
    //   #1734	-> 4
    //   #1735	-> 13
    //   #1736	-> 24
    //   #1737	-> 43
    //   #1739	-> 56
    //   #1740	-> 66
    //   #1741	-> 79
    //   #1742	-> 86
    //   #1744	-> 93
    //   #1746	-> 99
    //   #1747	-> 109
    //   #1747	-> 128
    //   #1749	-> 131
    //   #1750	-> 146
    //   #1749	-> 175
    //   #1753	-> 184
    //   #1754	-> 194
    //   #1755	-> 205
    //   #1758	-> 212
    //   #1759	-> 222
    //   #1760	-> 233
    //   #1762	-> 240
    //   #1765	-> 250
    //   #568	-> 251
    // Local variable table:
    //   start	length	slot	name	descriptor
    //   134	50	12	i$iv	J
    //   4	247	3	$i$f$commonEquals	I
    //   66	185	4	sa$iv	Lokio/Segment;
    //   79	172	5	sb$iv	Lokio/Segment;
    //   86	165	6	posA$iv	I
    //   93	158	7	posB$iv	I
    //   96	155	8	pos$iv	J
    //   99	152	10	count$iv	J
    //   2	249	2	$this$commonEquals$iv	Lokio/Buffer;
    //   0	252	0	this	Lokio/Buffer;
    //   0	252	1	other	Ljava/lang/Object;
  }
  
  public int hashCode() {
    Buffer $this$commonHashCode$iv = this;
    int $i$f$commonHashCode = 0;
    if ($this$commonHashCode$iv.head == null) {
    
    } else {
      boolean bool = false;
      int result$iv = 1;
      while (true) {
        int pos$iv = bool.pos;
        int limit$iv = bool.limit;
        while (pos$iv < limit$iv) {
          result$iv = 31 * result$iv + bool.data[pos$iv];
          pos$iv++;
        } 
        Intrinsics.checkNotNull(bool.next);
        Segment segment = bool.next;
        if (segment == $this$commonHashCode$iv.head)
          return result$iv; 
      } 
    } 
    return $this$commonHashCode$iv.head;
  }
  
  @NotNull
  public String toString() {
    return snapshot().toString();
  }
  
  @NotNull
  public final Buffer copy() {
    Buffer $this$commonCopy$iv = this;
    int $i$f$commonCopy = 0;
    Buffer result$iv = new Buffer();
    Intrinsics.checkNotNull($this$commonCopy$iv.head);
    Segment head$iv = $this$commonCopy$iv.head;
    Segment headCopy$iv = head$iv.sharedCopy();
    result$iv.head = headCopy$iv;
    headCopy$iv.prev = result$iv.head;
    headCopy$iv.next = headCopy$iv.prev;
    Segment s$iv = head$iv.next;
    while (s$iv != head$iv) {
      Intrinsics.checkNotNull(headCopy$iv.prev);
      Intrinsics.checkNotNull(s$iv);
      headCopy$iv.prev.push(s$iv.sharedCopy());
      s$iv = s$iv.next;
    } 
    result$iv.setSize$okio($this$commonCopy$iv.size());
    return ($this$commonCopy$iv.size() == 0L) ? result$iv : result$iv;
  }
  
  @NotNull
  public Buffer clone() {
    return copy();
  }
  
  @NotNull
  public final ByteString snapshot() {
    Buffer $this$commonSnapshot$iv = this;
    int $i$f$commonSnapshot = 0;
    if (!(($this$commonSnapshot$iv.size() <= 2147483647L) ? 1 : 0)) {
      int $i$a$-check--Buffer$commonSnapshot$1$iv = 0;
      String str = "size > Int.MAX_VALUE: " + $this$commonSnapshot$iv.size();
      throw new IllegalStateException(str.toString());
    } 
    return $this$commonSnapshot$iv.snapshot((int)$this$commonSnapshot$iv.size());
  }
  
  @NotNull
  public final ByteString snapshot(int byteCount) {
    Buffer $this$commonSnapshot$iv = this;
    int $i$f$commonSnapshot = 0;
    -SegmentedByteString.checkOffsetAndCount($this$commonSnapshot$iv.size(), 0L, byteCount);
    int offset$iv = 0;
    int segmentCount$iv = 0;
    Segment s$iv = $this$commonSnapshot$iv.head;
    while (offset$iv < byteCount) {
      Intrinsics.checkNotNull(s$iv);
      if (s$iv.limit == s$iv.pos)
        throw new AssertionError("s.limit == s.pos"); 
      offset$iv += s$iv.limit - s$iv.pos;
      segmentCount$iv++;
      s$iv = s$iv.next;
    } 
    byte[][] segments$iv = new byte[segmentCount$iv][];
    int[] directory$iv = new int[segmentCount$iv * 2];
    offset$iv = 0;
    segmentCount$iv = 0;
    s$iv = $this$commonSnapshot$iv.head;
    while (offset$iv < byteCount) {
      Intrinsics.checkNotNull(s$iv);
      segments$iv[segmentCount$iv] = s$iv.data;
      offset$iv += s$iv.limit - s$iv.pos;
      directory$iv[segmentCount$iv] = Math.min(offset$iv, byteCount);
      directory$iv[segmentCount$iv + ((Object[])segments$iv).length] = s$iv.pos;
      s$iv.shared = true;
      segmentCount$iv++;
      s$iv = s$iv.next;
    } 
    return (byteCount == 0) ? ByteString.EMPTY : new SegmentedByteString(segments$iv, directory$iv);
  }
  
  @JvmOverloads
  @NotNull
  public final UnsafeCursor readUnsafe(@NotNull UnsafeCursor unsafeCursor) {
    Intrinsics.checkNotNullParameter(unsafeCursor, "unsafeCursor");
    return -Buffer.commonReadUnsafe(this, unsafeCursor);
  }
  
  @JvmOverloads
  @NotNull
  public final UnsafeCursor readAndWriteUnsafe(@NotNull UnsafeCursor unsafeCursor) {
    Intrinsics.checkNotNullParameter(unsafeCursor, "unsafeCursor");
    return -Buffer.commonReadAndWriteUnsafe(this, unsafeCursor);
  }
  
  @Deprecated(message = "moved to operator function", replaceWith = @ReplaceWith(expression = "this[index]", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_getByte")
  public final byte -deprecated_getByte(long index) {
    return getByte(index);
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "size", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_size")
  public final long -deprecated_size() {
    return this.size;
  }
  
  @JvmOverloads
  @NotNull
  public final Buffer copyTo(@NotNull OutputStream out, long offset) throws IOException {
    Intrinsics.checkNotNullParameter(out, "out");
    return copyTo$default(this, out, offset, 0L, 4, (Object)null);
  }
  
  @JvmOverloads
  @NotNull
  public final Buffer copyTo(@NotNull OutputStream out) throws IOException {
    Intrinsics.checkNotNullParameter(out, "out");
    return copyTo$default(this, out, 0L, 0L, 6, (Object)null);
  }
  
  @JvmOverloads
  @NotNull
  public final Buffer writeTo(@NotNull OutputStream out) throws IOException {
    Intrinsics.checkNotNullParameter(out, "out");
    return writeTo$default(this, out, 0L, 2, null);
  }
  
  @JvmOverloads
  @NotNull
  public final UnsafeCursor readUnsafe() {
    return readUnsafe$default(this, null, 1, null);
  }
  
  @JvmOverloads
  @NotNull
  public final UnsafeCursor readAndWriteUnsafe() {
    return readAndWriteUnsafe$default(this, null, 1, null);
  }
  
  @Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000@\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\002\n\000\n\002\020\b\n\000\n\002\020\t\n\002\b\n\n\002\030\002\n\002\b\002\n\002\020\022\n\002\b\005\n\002\020\013\n\002\b\002\n\002\030\002\n\002\b\b\030\0002\0020\001B\007¢\006\004\b\002\020\003J\017\020\005\032\0020\004H\026¢\006\004\b\005\020\003J\025\020\t\032\0020\b2\006\020\007\032\0020\006¢\006\004\b\t\020\nJ\r\020\013\032\0020\006¢\006\004\b\013\020\fJ\025\020\016\032\0020\b2\006\020\r\032\0020\b¢\006\004\b\016\020\017J\025\020\021\032\0020\0062\006\020\020\032\0020\b¢\006\004\b\021\020\022R\030\020\024\032\004\030\0010\0238\006@\006X\016¢\006\006\n\004\b\024\020\025R\030\020\027\032\004\030\0010\0268\006@\006X\016¢\006\006\n\004\b\027\020\030R\026\020\031\032\0020\0068\006@\006X\016¢\006\006\n\004\b\031\020\032R\026\020\020\032\0020\b8\006@\006X\016¢\006\006\n\004\b\020\020\033R\026\020\035\032\0020\0348\006@\006X\016¢\006\006\n\004\b\035\020\036R$\020 \032\004\030\0010\0378\000@\000X\016¢\006\022\n\004\b \020!\032\004\b\"\020#\"\004\b$\020%R\026\020&\032\0020\0068\006@\006X\016¢\006\006\n\004\b&\020\032¨\006'"}, d2 = {"Lokio/Buffer$UnsafeCursor;", "Ljava/io/Closeable;", "<init>", "()V", "", "close", "", "minByteCount", "", "expandBuffer", "(I)J", "next", "()I", "newSize", "resizeBuffer", "(J)J", "offset", "seek", "(J)I", "Lokio/Buffer;", "buffer", "Lokio/Buffer;", "", "data", "[B", "end", "I", "J", "", "readWrite", "Z", "Lokio/Segment;", "segment", "Lokio/Segment;", "getSegment$okio", "()Lokio/Segment;", "setSegment$okio", "(Lokio/Segment;)V", "start", "okio"})
  @SourceDebugExtension({"SMAP\nBuffer.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Buffer.kt\nokio/Buffer$UnsafeCursor\n+ 2 Buffer.kt\nokio/internal/-Buffer\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 Util.kt\nokio/-SegmentedByteString\n*L\n1#1,641:1\n1567#2:642\n1568#2:644\n1572#2:645\n1573#2,68:647\n1644#2:715\n1645#2,32:717\n1677#2,18:750\n1698#2:768\n1699#2,18:770\n1721#2:788\n1723#2,7:790\n1#3:643\n1#3:646\n1#3:716\n1#3:769\n1#3:789\n86#4:749\n*S KotlinDebug\n*F\n+ 1 Buffer.kt\nokio/Buffer$UnsafeCursor\n*L\n628#1:642\n628#1:644\n630#1:645\n630#1:647,68\n632#1:715\n632#1:717,32\n632#1:750,18\n634#1:768\n634#1:770,18\n637#1:788\n637#1:790,7\n628#1:643\n630#1:646\n632#1:716\n634#1:769\n637#1:789\n632#1:749\n*E\n"})
  public static final class UnsafeCursor implements Closeable {
    @JvmField
    @Nullable
    public Buffer buffer;
    
    @JvmField
    public boolean readWrite;
    
    @Nullable
    private Segment segment;
    
    @Nullable
    public final Segment getSegment$okio() {
      return this.segment;
    }
    
    public final void setSegment$okio(@Nullable Segment <set-?>) {
      this.segment = <set-?>;
    }
    
    @JvmField
    public long offset = -1L;
    
    @JvmField
    @Nullable
    public byte[] data;
    
    @JvmField
    public int start = -1;
    
    @JvmField
    public int end = -1;
    
    public final int next() {
      UnsafeCursor $this$commonNext$iv = this;
      int $i$f$commonNext = 0;
      Intrinsics.checkNotNull($this$commonNext$iv.buffer);
      if (!(($this$commonNext$iv.offset != $this$commonNext$iv.buffer.size()) ? 1 : 0)) {
        int $i$a$-check--Buffer$commonNext$1$iv = 0;
        String str = "no more bytes";
        throw new IllegalStateException(str.toString());
      } 
      return ($this$commonNext$iv.offset == -1L) ? $this$commonNext$iv.seek(0L) : $this$commonNext$iv.seek($this$commonNext$iv.offset + ($this$commonNext$iv.end - $this$commonNext$iv.start));
    }
    
    public final int seek(long offset) {
      Buffer buffer$iv;
      UnsafeCursor $this$commonSeek$iv = this;
      int $i$f$commonSeek = 0;
      if ($this$commonSeek$iv.buffer == null) {
        int $i$a$-checkNotNull--Buffer$commonSeek$buffer$1$iv = 0;
        String str = "not attached to a buffer";
        throw new IllegalStateException(str.toString());
      } 
      if (offset < -1L || offset > buffer$iv.size())
        throw new ArrayIndexOutOfBoundsException("offset=" + offset + " > size=" + buffer$iv.size()); 
      $this$commonSeek$iv.setSegment$okio(null);
      $this$commonSeek$iv.offset = offset;
      $this$commonSeek$iv.data = null;
      $this$commonSeek$iv.start = -1;
      $this$commonSeek$iv.end = -1;
      long min$iv = 0L;
      long max$iv = buffer$iv.size();
      Segment head$iv = buffer$iv.head;
      Segment tail$iv = buffer$iv.head;
      if ($this$commonSeek$iv.getSegment$okio() != null) {
        Intrinsics.checkNotNull($this$commonSeek$iv.getSegment$okio());
        long segmentOffset$iv = $this$commonSeek$iv.offset - ($this$commonSeek$iv.start - ($this$commonSeek$iv.getSegment$okio()).pos);
        if (segmentOffset$iv > offset) {
          max$iv = segmentOffset$iv;
          tail$iv = $this$commonSeek$iv.getSegment$okio();
        } else {
          min$iv = segmentOffset$iv;
          head$iv = $this$commonSeek$iv.getSegment$okio();
        } 
      } 
      Segment next$iv = null;
      long nextOffset$iv = 0L;
      if (max$iv - offset > offset - min$iv) {
        next$iv = head$iv;
        nextOffset$iv = min$iv;
        Intrinsics.checkNotNull(next$iv);
        while (offset >= nextOffset$iv + (next$iv.limit - next$iv.pos)) {
          nextOffset$iv += (next$iv.limit - next$iv.pos);
          next$iv = next$iv.next;
        } 
      } else {
        next$iv = tail$iv;
        nextOffset$iv = max$iv;
        while (nextOffset$iv > offset) {
          Intrinsics.checkNotNull(next$iv);
          next$iv = next$iv.prev;
          Intrinsics.checkNotNull(next$iv);
          nextOffset$iv -= (next$iv.limit - next$iv.pos);
        } 
      } 
      Intrinsics.checkNotNull(next$iv);
      if ($this$commonSeek$iv.readWrite && next$iv.shared) {
        Segment unsharedNext$iv = next$iv.unsharedCopy();
        if (buffer$iv.head == next$iv)
          buffer$iv.head = unsharedNext$iv; 
        next$iv = next$iv.push(unsharedNext$iv);
        Intrinsics.checkNotNull(next$iv.prev);
        next$iv.prev.pop();
      } 
      $this$commonSeek$iv.setSegment$okio(next$iv);
      $this$commonSeek$iv.offset = offset;
      Intrinsics.checkNotNull(next$iv);
      $this$commonSeek$iv.data = next$iv.data;
      $this$commonSeek$iv.start = next$iv.pos + (int)(offset - nextOffset$iv);
      $this$commonSeek$iv.end = next$iv.limit;
      return (offset == -1L || offset == buffer$iv.size()) ? -1 : ($this$commonSeek$iv.end - $this$commonSeek$iv.start);
    }
    
    public final long resizeBuffer(long newSize) {
      Buffer buffer$iv;
      UnsafeCursor $this$commonResizeBuffer$iv = this;
      int $i$f$commonResizeBuffer = 0;
      if ($this$commonResizeBuffer$iv.buffer == null) {
        int $i$a$-checkNotNull--Buffer$commonResizeBuffer$buffer$1$iv = 0;
        String str = "not attached to a buffer";
        throw new IllegalStateException(str.toString());
      } 
      if (!$this$commonResizeBuffer$iv.readWrite) {
        int $i$a$-check--Buffer$commonResizeBuffer$1$iv = 0;
        String str = "resizeBuffer() only permitted for read/write buffers";
        throw new IllegalStateException(str.toString());
      } 
      long oldSize$iv = buffer$iv.size();
      if (newSize <= oldSize$iv) {
        if (!((newSize >= 0L) ? 1 : 0)) {
          int $i$a$-require--Buffer$commonResizeBuffer$2$iv = 0;
          String str = "newSize < 0: " + newSize;
          throw new IllegalArgumentException(str.toString());
        } 
        long bytesToSubtract$iv = oldSize$iv - newSize;
        while (bytesToSubtract$iv > 0L) {
          Intrinsics.checkNotNull(buffer$iv.head);
          Segment tail$iv = buffer$iv.head.prev;
          Intrinsics.checkNotNull(tail$iv);
          int tailSize$iv = tail$iv.limit - tail$iv.pos;
          if (tailSize$iv <= bytesToSubtract$iv) {
            buffer$iv.head = tail$iv.pop();
            SegmentPool.recycle(tail$iv);
            bytesToSubtract$iv -= tailSize$iv;
            continue;
          } 
          tail$iv.limit -= (int)bytesToSubtract$iv;
        } 
        $this$commonResizeBuffer$iv.setSegment$okio(null);
        $this$commonResizeBuffer$iv.offset = newSize;
        $this$commonResizeBuffer$iv.data = null;
        $this$commonResizeBuffer$iv.start = -1;
        $this$commonResizeBuffer$iv.end = -1;
      } else if (newSize > oldSize$iv) {
        boolean needsToSeek$iv = true;
        long bytesToAdd$iv = newSize - oldSize$iv;
        while (bytesToAdd$iv > 0L) {
          Segment tail$iv = buffer$iv.writableSegment$okio(1);
          int b$iv$iv = 8192 - tail$iv.limit, $i$f$minOf = 0, segmentBytesToAdd$iv = (int)Math.min(bytesToAdd$iv, b$iv$iv);
          tail$iv.limit += segmentBytesToAdd$iv;
          bytesToAdd$iv -= segmentBytesToAdd$iv;
          if (needsToSeek$iv) {
            $this$commonResizeBuffer$iv.setSegment$okio(tail$iv);
            $this$commonResizeBuffer$iv.offset = oldSize$iv;
            $this$commonResizeBuffer$iv.data = tail$iv.data;
            $this$commonResizeBuffer$iv.start = tail$iv.limit - segmentBytesToAdd$iv;
            $this$commonResizeBuffer$iv.end = tail$iv.limit;
            needsToSeek$iv = false;
          } 
        } 
      } 
      buffer$iv.setSize$okio(newSize);
      return oldSize$iv;
    }
    
    public final long expandBuffer(int minByteCount) {
      Buffer buffer$iv;
      UnsafeCursor $this$commonExpandBuffer$iv = this;
      int $i$f$commonExpandBuffer = 0;
      if (!((minByteCount > 0) ? 1 : 0)) {
        int $i$a$-require--Buffer$commonExpandBuffer$1$iv = 0;
        String str = "minByteCount <= 0: " + minByteCount;
        throw new IllegalArgumentException(str.toString());
      } 
      if (!((minByteCount <= 8192) ? 1 : 0)) {
        int $i$a$-require--Buffer$commonExpandBuffer$2$iv = 0;
        String str = "minByteCount > Segment.SIZE: " + minByteCount;
        throw new IllegalArgumentException(str.toString());
      } 
      if ($this$commonExpandBuffer$iv.buffer == null) {
        int $i$a$-checkNotNull--Buffer$commonExpandBuffer$buffer$1$iv = 0;
        String str = "not attached to a buffer";
        throw new IllegalStateException(str.toString());
      } 
      if (!$this$commonExpandBuffer$iv.readWrite) {
        int $i$a$-check--Buffer$commonExpandBuffer$3$iv = 0;
        String str = "expandBuffer() only permitted for read/write buffers";
        throw new IllegalStateException(str.toString());
      } 
      long oldSize$iv = buffer$iv.size();
      Segment tail$iv = buffer$iv.writableSegment$okio(minByteCount);
      int result$iv = 8192 - tail$iv.limit;
      tail$iv.limit = 8192;
      buffer$iv.setSize$okio(oldSize$iv + result$iv);
      $this$commonExpandBuffer$iv.setSegment$okio(tail$iv);
      $this$commonExpandBuffer$iv.offset = oldSize$iv;
      $this$commonExpandBuffer$iv.data = tail$iv.data;
      $this$commonExpandBuffer$iv.start = 8192 - result$iv;
      $this$commonExpandBuffer$iv.end = 8192;
      return result$iv;
    }
    
    public void close() {
      UnsafeCursor $this$commonClose$iv = this;
      int $i$f$commonClose = 0;
      if (!(($this$commonClose$iv.buffer != null) ? 1 : 0)) {
        int $i$a$-check--Buffer$commonClose$1$iv = 0;
        String str = "not attached to a buffer";
        throw new IllegalStateException(str.toString());
      } 
      $this$commonClose$iv.buffer = null;
      $this$commonClose$iv.setSegment$okio(null);
      $this$commonClose$iv.offset = -1L;
      $this$commonClose$iv.data = null;
      $this$commonClose$iv.start = -1;
      $this$commonClose$iv.end = -1;
    }
  }
}
