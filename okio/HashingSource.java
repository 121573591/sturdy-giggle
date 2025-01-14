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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000<\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\003\n\002\020\016\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\005\n\002\030\002\n\000\n\002\020\t\n\002\b\b\030\000 \0352\0020\0012\0020\002:\001\035B\031\b\020\022\006\020\003\032\0020\002\022\006\020\005\032\0020\004¢\006\004\b\006\020\007B\031\b\020\022\006\020\003\032\0020\002\022\006\020\t\032\0020\b¢\006\004\b\006\020\nB\031\b\020\022\006\020\003\032\0020\002\022\006\020\f\032\0020\013¢\006\004\b\006\020\rB!\b\020\022\006\020\003\032\0020\002\022\006\020\017\032\0020\016\022\006\020\t\032\0020\b¢\006\004\b\006\020\020J\017\020\023\032\0020\016H\007¢\006\004\b\021\020\022J\037\020\030\032\0020\0262\006\020\025\032\0020\0242\006\020\027\032\0020\026H\026¢\006\004\b\030\020\031R\021\020\023\032\0020\0168G¢\006\006\032\004\b\023\020\022R\026\020\f\032\004\030\0010\0138\002X\004¢\006\006\n\004\b\f\020\032R\026\020\033\032\004\030\0010\0048\002X\004¢\006\006\n\004\b\033\020\034¨\006\036"}, d2 = {"Lokio/HashingSource;", "Lokio/ForwardingSource;", "Lokio/Source;", "source", "Ljava/security/MessageDigest;", "digest", "<init>", "(Lokio/Source;Ljava/security/MessageDigest;)V", "", "algorithm", "(Lokio/Source;Ljava/lang/String;)V", "Ljavax/crypto/Mac;", "mac", "(Lokio/Source;Ljavax/crypto/Mac;)V", "Lokio/ByteString;", "key", "(Lokio/Source;Lokio/ByteString;Ljava/lang/String;)V", "-deprecated_hash", "()Lokio/ByteString;", "hash", "Lokio/Buffer;", "sink", "", "byteCount", "read", "(Lokio/Buffer;J)J", "Ljavax/crypto/Mac;", "messageDigest", "Ljava/security/MessageDigest;", "Companion", "okio"})
public final class HashingSource extends ForwardingSource implements Source {
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  @Nullable
  private final MessageDigest messageDigest;
  
  @Nullable
  private final Mac mac;
  
  public HashingSource(@NotNull Source source, @NotNull MessageDigest digest) {
    super(source);
    this.messageDigest = digest;
    this.mac = null;
  }
  
  public HashingSource(@NotNull Source source, @NotNull String algorithm) {
    this(source, MessageDigest.getInstance(algorithm));
  }
  
  public HashingSource(@NotNull Source source, @NotNull Mac mac) {
    super(source);
    this.mac = mac;
    this.messageDigest = null;
  }
  
  public HashingSource(@NotNull Source source, @NotNull ByteString key, @NotNull String algorithm) {
    this(
        
        source1, mac1);
  }
  
  public long read(@NotNull Buffer sink, long byteCount) throws IOException {
    Intrinsics.checkNotNullParameter(sink, "sink");
    long result = super.read(sink, byteCount);
    if (result != -1L) {
      long start = sink.size() - result;
      long offset = sink.size();
      Intrinsics.checkNotNull(sink.head);
      Segment s = sink.head;
      while (offset > start) {
        Intrinsics.checkNotNull(s.prev);
        s = s.prev;
        offset -= (s.limit - s.pos);
      } 
      while (offset < sink.size()) {
        int pos = (int)(s.pos + start - offset);
        if (this.messageDigest != null) {
          this.messageDigest.update(s.data, pos, s.limit - pos);
        } else {
          Intrinsics.checkNotNull(this.mac);
          this.mac.update(s.data, pos, s.limit - pos);
        } 
        offset += (s.limit - s.pos);
        start = offset;
        Intrinsics.checkNotNull(s.next);
        s = s.next;
      } 
    } 
    return result;
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
  public static final HashingSource md5(@NotNull Source source) {
    return Companion.md5(source);
  }
  
  @JvmStatic
  @NotNull
  public static final HashingSource sha1(@NotNull Source source) {
    return Companion.sha1(source);
  }
  
  @JvmStatic
  @NotNull
  public static final HashingSource sha256(@NotNull Source source) {
    return Companion.sha256(source);
  }
  
  @JvmStatic
  @NotNull
  public static final HashingSource sha512(@NotNull Source source) {
    return Companion.sha512(source);
  }
  
  @JvmStatic
  @NotNull
  public static final HashingSource hmacSha1(@NotNull Source source, @NotNull ByteString key) {
    return Companion.hmacSha1(source, key);
  }
  
  @JvmStatic
  @NotNull
  public static final HashingSource hmacSha256(@NotNull Source source, @NotNull ByteString key) {
    return Companion.hmacSha256(source, key);
  }
  
  @JvmStatic
  @NotNull
  public static final HashingSource hmacSha512(@NotNull Source source, @NotNull ByteString key) {
    return Companion.hmacSha512(source, key);
  }
  
  @Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000 \n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\n\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J\037\020\t\032\0020\b2\006\020\005\032\0020\0042\006\020\007\032\0020\006H\007¢\006\004\b\t\020\nJ\037\020\013\032\0020\b2\006\020\005\032\0020\0042\006\020\007\032\0020\006H\007¢\006\004\b\013\020\nJ\037\020\f\032\0020\b2\006\020\005\032\0020\0042\006\020\007\032\0020\006H\007¢\006\004\b\f\020\nJ\027\020\r\032\0020\b2\006\020\005\032\0020\004H\007¢\006\004\b\r\020\016J\027\020\017\032\0020\b2\006\020\005\032\0020\004H\007¢\006\004\b\017\020\016J\027\020\020\032\0020\b2\006\020\005\032\0020\004H\007¢\006\004\b\020\020\016J\027\020\021\032\0020\b2\006\020\005\032\0020\004H\007¢\006\004\b\021\020\016¨\006\022"}, d2 = {"Lokio/HashingSource$Companion;", "", "<init>", "()V", "Lokio/Source;", "source", "Lokio/ByteString;", "key", "Lokio/HashingSource;", "hmacSha1", "(Lokio/Source;Lokio/ByteString;)Lokio/HashingSource;", "hmacSha256", "hmacSha512", "md5", "(Lokio/Source;)Lokio/HashingSource;", "sha1", "sha256", "sha512", "okio"})
  public static final class Companion {
    private Companion() {}
    
    @JvmStatic
    @NotNull
    public final HashingSource md5(@NotNull Source source) {
      Intrinsics.checkNotNullParameter(source, "source");
      return new HashingSource(source, "MD5");
    }
    
    @JvmStatic
    @NotNull
    public final HashingSource sha1(@NotNull Source source) {
      Intrinsics.checkNotNullParameter(source, "source");
      return new HashingSource(source, "SHA-1");
    }
    
    @JvmStatic
    @NotNull
    public final HashingSource sha256(@NotNull Source source) {
      Intrinsics.checkNotNullParameter(source, "source");
      return new HashingSource(source, "SHA-256");
    }
    
    @JvmStatic
    @NotNull
    public final HashingSource sha512(@NotNull Source source) {
      Intrinsics.checkNotNullParameter(source, "source");
      return new HashingSource(source, "SHA-512");
    }
    
    @JvmStatic
    @NotNull
    public final HashingSource hmacSha1(@NotNull Source source, @NotNull ByteString key) {
      Intrinsics.checkNotNullParameter(source, "source");
      Intrinsics.checkNotNullParameter(key, "key");
      return new HashingSource(source, key, "HmacSHA1");
    }
    
    @JvmStatic
    @NotNull
    public final HashingSource hmacSha256(@NotNull Source source, @NotNull ByteString key) {
      Intrinsics.checkNotNullParameter(source, "source");
      Intrinsics.checkNotNullParameter(key, "key");
      return new HashingSource(source, key, "HmacSHA256");
    }
    
    @JvmStatic
    @NotNull
    public final HashingSource hmacSha512(@NotNull Source source, @NotNull ByteString key) {
      Intrinsics.checkNotNullParameter(source, "source");
      Intrinsics.checkNotNullParameter(key, "key");
      return new HashingSource(source, key, "HmacSHA512");
    }
  }
}
