package okio;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Deprecated(message = "changed in Okio 2.x")
@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000\"\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\016\n\000\n\002\020\t\n\002\b\002\n\002\020\b\n\002\b\004\bÇ\002\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J\027\020\007\032\0020\0062\006\020\005\032\0020\004H\007¢\006\004\b\007\020\bJ'\020\007\032\0020\0062\006\020\005\032\0020\0042\006\020\n\032\0020\t2\006\020\013\032\0020\tH\007¢\006\004\b\007\020\f¨\006\r"}, d2 = {"Lokio/-DeprecatedUtf8;", "", "<init>", "()V", "", "string", "", "size", "(Ljava/lang/String;)J", "", "beginIndex", "endIndex", "(Ljava/lang/String;II)J", "okio"})
public final class -DeprecatedUtf8 {
  @NotNull
  public static final -DeprecatedUtf8 INSTANCE = new -DeprecatedUtf8();
  
  @Deprecated(message = "moved to extension function", replaceWith = @ReplaceWith(expression = "string.utf8Size()", imports = {"okio.utf8Size"}), level = DeprecationLevel.ERROR)
  public final long size(@NotNull String string) {
    Intrinsics.checkNotNullParameter(string, "string");
    return Utf8.size$default(string, 0, 0, 3, null);
  }
  
  @Deprecated(message = "moved to extension function", replaceWith = @ReplaceWith(expression = "string.utf8Size(beginIndex, endIndex)", imports = {"okio.utf8Size"}), level = DeprecationLevel.ERROR)
  public final long size(@NotNull String string, int beginIndex, int endIndex) {
    Intrinsics.checkNotNullParameter(string, "string");
    return Utf8.size(string, beginIndex, endIndex);
  }
}
