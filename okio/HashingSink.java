package okio;

import java.io.IOException;
import java.security.MessageDigest;
import javax.crypto.Mac;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000B\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\003\n\002\020\016\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\005\n\002\030\002\n\000\n\002\020\t\n\000\n\002\020\002\n\002\b\007\030\000 \0362\0020\0012\0020\002:\001\036B\031\b\020\022\006\020\003\032\0020\002\022\006\020\005\032\0020\004¢\006\004\b\006\020\007B\031\b\020\022\006\020\003\032\0020\002\022\006\020\t\032\0020\b¢\006\004\b\006\020\nB\031\b\020\022\006\020\003\032\0020\002\022\006\020\f\032\0020\013¢\006\004\b\006\020\rB!\b\020\022\006\020\003\032\0020\002\022\006\020\017\032\0020\016\022\006\020\t\032\0020\b¢\006\004\b\006\020\020J\017\020\023\032\0020\016H\007¢\006\004\b\021\020\022J\037\020\031\032\0020\0302\006\020\025\032\0020\0242\006\020\027\032\0020\026H\026¢\006\004\b\031\020\032R\021\020\023\032\0020\0168G¢\006\006\032\004\b\023\020\022R\026\020\f\032\004\030\0010\0138\002X\004¢\006\006\n\004\b\f\020\033R\026\020\034\032\004\030\0010\0048\002X\004¢\006\006\n\004\b\034\020\035¨\006\037"}, d2 = {"Lokio/HashingSink;", "Lokio/ForwardingSink;", "Lokio/Sink;", "sink", "Ljava/security/MessageDigest;", "digest", "<init>", "(Lokio/Sink;Ljava/security/MessageDigest;)V", "", "algorithm", "(Lokio/Sink;Ljava/lang/String;)V", "Ljavax/crypto/Mac;", "mac", "(Lokio/Sink;Ljavax/crypto/Mac;)V", "Lokio/ByteString;", "key", "(Lokio/Sink;Lokio/ByteString;Ljava/lang/String;)V", "-deprecated_hash", "()Lokio/ByteString;", "hash", "Lokio/Buffer;", "source", "", "byteCount", "", "write", "(Lokio/Buffer;J)V", "Ljavax/crypto/Mac;", "messageDigest", "Ljava/security/MessageDigest;", "Companion", "okio"})
@SourceDebugExtension({"SMAP\nHashingSink.kt\nKotlin\n*S Kotlin\n*F\n+ 1 HashingSink.kt\nokio/HashingSink\n+ 2 Util.kt\nokio/-SegmentedByteString\n*L\n1#1,148:1\n86#2:149\n*S KotlinDebug\n*F\n+ 1 HashingSink.kt\nokio/HashingSink\n*L\n75#1:149\n*E\n"})
public final class HashingSink extends ForwardingSink implements Sink {
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  @Nullable
  private final MessageDigest messageDigest;
  
  @Nullable
  private final Mac mac;
  
  public HashingSink(@NotNull Sink sink, @NotNull MessageDigest digest) {
    super(sink);
    this.messageDigest = digest;
    this.mac = null;
  }
  
  public HashingSink(@NotNull Sink sink, @NotNull String algorithm) {
    this(sink, MessageDigest.getInstance(algorithm));
  }
  
  public HashingSink(@NotNull Sink sink, @NotNull Mac mac) {
    super(sink);
    this.mac = mac;
    this.messageDigest = null;
  }
  
  public HashingSink(@NotNull Sink sink, @NotNull ByteString key, @NotNull String algorithm) {
    this(
        
        sink1, mac1);
  }
  
  public void write(@NotNull Buffer source, long byteCount) throws IOException {
    Intrinsics.checkNotNullParameter(source, "source");
    -SegmentedByteString.checkOffsetAndCount(source.size(), 0L, byteCount);
    long hashedCount = 0L;
    Intrinsics.checkNotNull(source.head);
    Segment s = source.head;
    while (hashedCount < byteCount) {
      long l = byteCount - hashedCount;
      int b$iv = s.limit - s.pos, $i$f$minOf = 0, toHash = (int)
        
        Math.min(l, b$iv);
      if (this.messageDigest != null) {
        this.messageDigest.update(s.data, s.pos, toHash);
      } else {
        Intrinsics.checkNotNull(this.mac);
        this.mac.update(s.data, s.pos, toHash);
      } 
      hashedCount += toHash;
      Intrinsics.checkNotNull(s.next);
      s = s.next;
    } 
    super.write(source, byteCount);
  }
  
  @JvmName(name = "hash")
  @NotNull
  public final ByteString hash() {
    Intrinsics.checkNotNull(this.mac);
    byte[] result = (this.messageDigest != null) ? this.messageDigest.digest() : this.mac.doFinal();
    Intrinsics.checkNotNull(result);
    return new ByteString(result);
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "hash", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_hash")
  @NotNull
  public final ByteString -deprecated_hash() {
    return hash();
  }
  
  @JvmStatic
  @NotNull
  public static final HashingSink md5(@NotNull Sink sink) {
    return Companion.md5(sink);
  }
  
  @JvmStatic
  @NotNull
  public static final HashingSink sha1(@NotNull Sink sink) {
    return Companion.sha1(sink);
  }
  
  @JvmStatic
  @NotNull
  public static final HashingSink sha256(@NotNull Sink sink) {
    return Companion.sha256(sink);
  }
  
  @JvmStatic
  @NotNull
  public static final HashingSink sha512(@NotNull Sink sink) {
    return Companion.sha512(sink);
  }
  
  @JvmStatic
  @NotNull
  public static final HashingSink hmacSha1(@NotNull Sink sink, @NotNull ByteString key) {
    return Companion.hmacSha1(sink, key);
  }
  
  @JvmStatic
  @NotNull
  public static final HashingSink hmacSha256(@NotNull Sink sink, @NotNull ByteString key) {
    return Companion.hmacSha256(sink, key);
  }
  
  @JvmStatic
  @NotNull
  public static final HashingSink hmacSha512(@NotNull Sink sink, @NotNull ByteString key) {
    return Companion.hmacSha512(sink, key);
  }
  
  @Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000 \n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\n\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J\037\020\t\032\0020\b2\006\020\005\032\0020\0042\006\020\007\032\0020\006H\007¢\006\004\b\t\020\nJ\037\020\013\032\0020\b2\006\020\005\032\0020\0042\006\020\007\032\0020\006H\007¢\006\004\b\013\020\nJ\037\020\f\032\0020\b2\006\020\005\032\0020\0042\006\020\007\032\0020\006H\007¢\006\004\b\f\020\nJ\027\020\r\032\0020\b2\006\020\005\032\0020\004H\007¢\006\004\b\r\020\016J\027\020\017\032\0020\b2\006\020\005\032\0020\004H\007¢\006\004\b\017\020\016J\027\020\020\032\0020\b2\006\020\005\032\0020\004H\007¢\006\004\b\020\020\016J\027\020\021\032\0020\b2\006\020\005\032\0020\004H\007¢\006\004\b\021\020\016¨\006\022"}, d2 = {"Lokio/HashingSink$Companion;", "", "<init>", "()V", "Lokio/Sink;", "sink", "Lokio/ByteString;", "key", "Lokio/HashingSink;", "hmacSha1", "(Lokio/Sink;Lokio/ByteString;)Lokio/HashingSink;", "hmacSha256", "hmacSha512", "md5", "(Lokio/Sink;)Lokio/HashingSink;", "sha1", "sha256", "sha512", "okio"})
  public static final class Companion {
    private Companion() {}
    
    @JvmStatic
    @NotNull
    public final HashingSink md5(@NotNull Sink sink) {
      Intrinsics.checkNotNullParameter(sink, "sink");
      return new HashingSink(sink, "MD5");
    }
    
    @JvmStatic
    @NotNull
    public final HashingSink sha1(@NotNull Sink sink) {
      Intrinsics.checkNotNullParameter(sink, "sink");
      return new HashingSink(sink, "SHA-1");
    }
    
    @JvmStatic
    @NotNull
    public final HashingSink sha256(@NotNull Sink sink) {
      Intrinsics.checkNotNullParameter(sink, "sink");
      return new HashingSink(sink, "SHA-256");
    }
    
    @JvmStatic
    @NotNull
    public final HashingSink sha512(@NotNull Sink sink) {
      Intrinsics.checkNotNullParameter(sink, "sink");
      return new HashingSink(sink, "SHA-512");
    }
    
    @JvmStatic
    @NotNull
    public final HashingSink hmacSha1(@NotNull Sink sink, @NotNull ByteString key) {
      Intrinsics.checkNotNullParameter(sink, "sink");
      Intrinsics.checkNotNullParameter(key, "key");
      return new HashingSink(sink, key, "HmacSHA1");
    }
    
    @JvmStatic
    @NotNull
    public final HashingSink hmacSha256(@NotNull Sink sink, @NotNull ByteString key) {
      Intrinsics.checkNotNullParameter(sink, "sink");
      Intrinsics.checkNotNullParameter(key, "key");
      return new HashingSink(sink, key, "HmacSHA256");
    }
    
    @JvmStatic
    @NotNull
    public final HashingSink hmacSha512(@NotNull Sink sink, @NotNull ByteString key) {
      Intrinsics.checkNotNullParameter(sink, "sink");
      Intrinsics.checkNotNullParameter(key, "key");
      return new HashingSink(sink, key, "HmacSHA512");
    }
  }
}
