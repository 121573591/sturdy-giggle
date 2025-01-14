package okhttp3.internal.http2;

import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000*\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\002\n\000\n\002\020\b\n\002\b\003\n\002\020\013\n\002\b\025\n\002\020\025\n\002\b\004\030\000 #2\0020\001:\001#B\007¢\006\004\b\002\020\003J\r\020\005\032\0020\004¢\006\004\b\005\020\003J\030\020\b\032\0020\0062\006\020\007\032\0020\006H\002¢\006\004\b\b\020\tJ\025\020\f\032\0020\n2\006\020\013\032\0020\n¢\006\004\b\f\020\rJ\r\020\016\032\0020\006¢\006\004\b\016\020\017J\025\020\020\032\0020\0062\006\020\013\032\0020\006¢\006\004\b\020\020\tJ\025\020\021\032\0020\0062\006\020\013\032\0020\006¢\006\004\b\021\020\tJ\025\020\022\032\0020\n2\006\020\007\032\0020\006¢\006\004\b\022\020\023J\025\020\025\032\0020\0042\006\020\024\032\0020\000¢\006\004\b\025\020\026J \020\030\032\0020\0002\006\020\007\032\0020\0062\006\020\027\032\0020\006H\002¢\006\004\b\030\020\031J\r\020\032\032\0020\006¢\006\004\b\032\020\017R\021\020\034\032\0020\0068F¢\006\006\032\004\b\033\020\017R\021\020\036\032\0020\0068F¢\006\006\032\004\b\035\020\017R\026\020\030\032\0020\0068\002@\002X\016¢\006\006\n\004\b\030\020\037R\024\020!\032\0020 8\002X\004¢\006\006\n\004\b!\020\"¨\006$"}, d2 = {"Lokhttp3/internal/http2/Settings;", "", "<init>", "()V", "", "clear", "", "id", "get", "(I)I", "", "defaultValue", "getEnablePush", "(Z)Z", "getMaxConcurrentStreams", "()I", "getMaxFrameSize", "getMaxHeaderListSize", "isSet", "(I)Z", "other", "merge", "(Lokhttp3/internal/http2/Settings;)V", "value", "set", "(II)Lokhttp3/internal/http2/Settings;", "size", "getHeaderTableSize", "headerTableSize", "getInitialWindowSize", "initialWindowSize", "I", "", "values", "[I", "Companion", "okhttp"})
public final class Settings {
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  private int set;
  
  @NotNull
  private final int[] values = new int[10];
  
  public static final int DEFAULT_INITIAL_WINDOW_SIZE = 65535;
  
  public static final int HEADER_TABLE_SIZE = 1;
  
  public static final int ENABLE_PUSH = 2;
  
  public static final int MAX_CONCURRENT_STREAMS = 4;
  
  public static final int MAX_FRAME_SIZE = 5;
  
  public static final int MAX_HEADER_LIST_SIZE = 6;
  
  public static final int INITIAL_WINDOW_SIZE = 7;
  
  public static final int COUNT = 10;
  
  public final int getHeaderTableSize() {
    int bit = 2;
    return ((bit & this.set) != 0) ? this.values[1] : -1;
  }
  
  public final int getInitialWindowSize() {
    int bit = 128;
    return ((bit & this.set) != 0) ? this.values[7] : 65535;
  }
  
  public final void clear() {
    this.set = 0;
    ArraysKt.fill$default(this.values, 0, 0, 0, 6, null);
  }
  
  @NotNull
  public final Settings set(int id, int value) {
    if (id < 0 || id >= this.values.length)
      return this; 
    int bit = 1 << id;
    this.set |= bit;
    this.values[id] = value;
    return this;
  }
  
  public final boolean isSet(int id) {
    int bit = 1 << id;
    return ((this.set & bit) != 0);
  }
  
  public final int get(int id) {
    return this.values[id];
  }
  
  public final int size() {
    return Integer.bitCount(this.set);
  }
  
  public final boolean getEnablePush(boolean defaultValue) {
    int bit = 4;
    return ((bit & this.set) != 0) ? ((this.values[2] == 1)) : defaultValue;
  }
  
  public final int getMaxConcurrentStreams() {
    int bit = 16;
    return ((bit & this.set) != 0) ? this.values[4] : Integer.MAX_VALUE;
  }
  
  public final int getMaxFrameSize(int defaultValue) {
    int bit = 32;
    return ((bit & this.set) != 0) ? this.values[5] : defaultValue;
  }
  
  public final int getMaxHeaderListSize(int defaultValue) {
    int bit = 64;
    return ((bit & this.set) != 0) ? this.values[6] : defaultValue;
  }
  
  public final void merge(@NotNull Settings other) {
    Intrinsics.checkNotNullParameter(other, "other");
    for (int i = 0; i < 10; i++) {
      if (other.isSet(i))
        set(i, other.get(i)); 
    } 
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\024\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\b\n\002\b\n\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003R\024\020\005\032\0020\0048\006XT¢\006\006\n\004\b\005\020\006R\024\020\007\032\0020\0048\006XT¢\006\006\n\004\b\007\020\006R\024\020\b\032\0020\0048\006XT¢\006\006\n\004\b\b\020\006R\024\020\t\032\0020\0048\006XT¢\006\006\n\004\b\t\020\006R\024\020\n\032\0020\0048\006XT¢\006\006\n\004\b\n\020\006R\024\020\013\032\0020\0048\006XT¢\006\006\n\004\b\013\020\006R\024\020\f\032\0020\0048\006XT¢\006\006\n\004\b\f\020\006R\024\020\r\032\0020\0048\006XT¢\006\006\n\004\b\r\020\006¨\006\016"}, d2 = {"Lokhttp3/internal/http2/Settings$Companion;", "", "<init>", "()V", "", "COUNT", "I", "DEFAULT_INITIAL_WINDOW_SIZE", "ENABLE_PUSH", "HEADER_TABLE_SIZE", "INITIAL_WINDOW_SIZE", "MAX_CONCURRENT_STREAMS", "MAX_FRAME_SIZE", "MAX_HEADER_LIST_SIZE", "okhttp"})
  public static final class Companion {
    private Companion() {}
  }
}
