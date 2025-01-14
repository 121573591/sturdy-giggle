package okhttp3.internal.http2;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okio.ByteString;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000(\n\002\030\002\n\002\020\000\n\002\020\016\n\002\b\004\n\002\030\002\n\002\b\b\n\002\020\013\n\002\b\002\n\002\020\b\n\002\b\t\b\b\030\000 \0332\0020\001:\001\033B\031\b\026\022\006\020\003\032\0020\002\022\006\020\004\032\0020\002¢\006\004\b\005\020\006B\031\b\026\022\006\020\003\032\0020\007\022\006\020\004\032\0020\002¢\006\004\b\005\020\bB\027\022\006\020\003\032\0020\007\022\006\020\004\032\0020\007¢\006\004\b\005\020\tJ\020\020\n\032\0020\007HÆ\003¢\006\004\b\n\020\013J\020\020\f\032\0020\007HÆ\003¢\006\004\b\f\020\013J$\020\r\032\0020\0002\b\b\002\020\003\032\0020\0072\b\b\002\020\004\032\0020\007HÆ\001¢\006\004\b\r\020\016J\032\020\021\032\0020\0202\b\020\017\032\004\030\0010\001HÖ\003¢\006\004\b\021\020\022J\020\020\024\032\0020\023HÖ\001¢\006\004\b\024\020\025J\017\020\026\032\0020\002H\026¢\006\004\b\026\020\027R\024\020\030\032\0020\0238\006X\004¢\006\006\n\004\b\030\020\031R\024\020\003\032\0020\0078\006X\004¢\006\006\n\004\b\003\020\032R\024\020\004\032\0020\0078\006X\004¢\006\006\n\004\b\004\020\032¨\006\034"}, d2 = {"Lokhttp3/internal/http2/Header;", "", "", "name", "value", "<init>", "(Ljava/lang/String;Ljava/lang/String;)V", "Lokio/ByteString;", "(Lokio/ByteString;Ljava/lang/String;)V", "(Lokio/ByteString;Lokio/ByteString;)V", "component1", "()Lokio/ByteString;", "component2", "copy", "(Lokio/ByteString;Lokio/ByteString;)Lokhttp3/internal/http2/Header;", "other", "", "equals", "(Ljava/lang/Object;)Z", "", "hashCode", "()I", "toString", "()Ljava/lang/String;", "hpackSize", "I", "Lokio/ByteString;", "Companion", "okhttp"})
public final class Header {
  public Header(@NotNull ByteString name, @NotNull ByteString value) {
    this.name = name;
    this.value = value;
    this.hpackSize = 32 + this.name.size() + this.value.size();
  }
  
  public Header(@NotNull String name, @NotNull String value) {
    this(ByteString.Companion.encodeUtf8(name), ByteString.Companion.encodeUtf8(value));
  }
  
  public Header(@NotNull ByteString name, @NotNull String value) {
    this(name, ByteString.Companion.encodeUtf8(value));
  }
  
  @NotNull
  public String toString() {
    return this.name.utf8() + ": " + this.value.utf8();
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\034\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\003\n\002\020\016\n\002\b\013\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003R\024\020\005\032\0020\0048\006X\004¢\006\006\n\004\b\005\020\006R\024\020\007\032\0020\0048\006X\004¢\006\006\n\004\b\007\020\006R\024\020\t\032\0020\b8\006XT¢\006\006\n\004\b\t\020\nR\024\020\013\032\0020\0048\006X\004¢\006\006\n\004\b\013\020\006R\024\020\f\032\0020\b8\006XT¢\006\006\n\004\b\f\020\nR\024\020\r\032\0020\0048\006X\004¢\006\006\n\004\b\r\020\006R\024\020\016\032\0020\b8\006XT¢\006\006\n\004\b\016\020\nR\024\020\017\032\0020\0048\006X\004¢\006\006\n\004\b\017\020\006R\024\020\020\032\0020\b8\006XT¢\006\006\n\004\b\020\020\nR\024\020\021\032\0020\0048\006X\004¢\006\006\n\004\b\021\020\006R\024\020\022\032\0020\b8\006XT¢\006\006\n\004\b\022\020\n¨\006\023"}, d2 = {"Lokhttp3/internal/http2/Header$Companion;", "", "<init>", "()V", "Lokio/ByteString;", "PSEUDO_PREFIX", "Lokio/ByteString;", "RESPONSE_STATUS", "", "RESPONSE_STATUS_UTF8", "Ljava/lang/String;", "TARGET_AUTHORITY", "TARGET_AUTHORITY_UTF8", "TARGET_METHOD", "TARGET_METHOD_UTF8", "TARGET_PATH", "TARGET_PATH_UTF8", "TARGET_SCHEME", "TARGET_SCHEME_UTF8", "okhttp"})
  public static final class Companion {
    private Companion() {}
  }
  
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  @JvmField
  @NotNull
  public final ByteString name;
  
  @JvmField
  @NotNull
  public final ByteString value;
  
  @JvmField
  public final int hpackSize;
  
  @JvmField
  @NotNull
  public static final ByteString PSEUDO_PREFIX = ByteString.Companion.encodeUtf8(":");
  
  @NotNull
  public static final String RESPONSE_STATUS_UTF8 = ":status";
  
  @NotNull
  public static final String TARGET_METHOD_UTF8 = ":method";
  
  @NotNull
  public static final String TARGET_PATH_UTF8 = ":path";
  
  @NotNull
  public static final String TARGET_SCHEME_UTF8 = ":scheme";
  
  @NotNull
  public static final String TARGET_AUTHORITY_UTF8 = ":authority";
  
  @JvmField
  @NotNull
  public static final ByteString RESPONSE_STATUS = ByteString.Companion.encodeUtf8(":status");
  
  @JvmField
  @NotNull
  public static final ByteString TARGET_METHOD = ByteString.Companion.encodeUtf8(":method");
  
  @JvmField
  @NotNull
  public static final ByteString TARGET_PATH = ByteString.Companion.encodeUtf8(":path");
  
  @JvmField
  @NotNull
  public static final ByteString TARGET_SCHEME = ByteString.Companion.encodeUtf8(":scheme");
  
  @JvmField
  @NotNull
  public static final ByteString TARGET_AUTHORITY = ByteString.Companion.encodeUtf8(":authority");
  
  @NotNull
  public final ByteString component1() {
    return this.name;
  }
  
  @NotNull
  public final ByteString component2() {
    return this.value;
  }
  
  @NotNull
  public final Header copy(@NotNull ByteString name, @NotNull ByteString value) {
    Intrinsics.checkNotNullParameter(name, "name");
    Intrinsics.checkNotNullParameter(value, "value");
    return new Header(name, value);
  }
  
  public int hashCode() {
    result = this.name.hashCode();
    return result * 31 + this.value.hashCode();
  }
  
  public boolean equals(@Nullable Object other) {
    if (this == other)
      return true; 
    if (!(other instanceof Header))
      return false; 
    Header header = (Header)other;
    return !Intrinsics.areEqual(this.name, header.name) ? false : (!!Intrinsics.areEqual(this.value, header.value));
  }
}
