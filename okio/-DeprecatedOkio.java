package okio;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.Arrays;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Deprecated(message = "changed in Okio 2.x")
@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000X\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\020\021\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\005\bÇ\002\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J\027\020\007\032\0020\0062\006\020\005\032\0020\004H\007¢\006\004\b\007\020\bJ\017\020\t\032\0020\006H\007¢\006\004\b\t\020\nJ\027\020\r\032\0020\f2\006\020\013\032\0020\006H\007¢\006\004\b\r\020\016J\027\020\r\032\0020\0212\006\020\020\032\0020\017H\007¢\006\004\b\r\020\022J\027\020\013\032\0020\0062\006\020\005\032\0020\004H\007¢\006\004\b\013\020\bJ\027\020\013\032\0020\0062\006\020\024\032\0020\023H\007¢\006\004\b\013\020\025J\027\020\013\032\0020\0062\006\020\027\032\0020\026H\007¢\006\004\b\013\020\030J+\020\013\032\0020\0062\006\020\032\032\0020\0312\022\020\035\032\n\022\006\b\001\022\0020\0340\033\"\0020\034H\007¢\006\004\b\013\020\036J\027\020\020\032\0020\0172\006\020\005\032\0020\004H\007¢\006\004\b\020\020\037J\027\020\020\032\0020\0172\006\020!\032\0020 H\007¢\006\004\b\020\020\"J\027\020\020\032\0020\0172\006\020\027\032\0020\026H\007¢\006\004\b\020\020#J+\020\020\032\0020\0172\006\020\032\032\0020\0312\022\020\035\032\n\022\006\b\001\022\0020\0340\033\"\0020\034H\007¢\006\004\b\020\020$¨\006%"}, d2 = {"Lokio/-DeprecatedOkio;", "", "<init>", "()V", "Ljava/io/File;", "file", "Lokio/Sink;", "appendingSink", "(Ljava/io/File;)Lokio/Sink;", "blackhole", "()Lokio/Sink;", "sink", "Lokio/BufferedSink;", "buffer", "(Lokio/Sink;)Lokio/BufferedSink;", "Lokio/Source;", "source", "Lokio/BufferedSource;", "(Lokio/Source;)Lokio/BufferedSource;", "Ljava/io/OutputStream;", "outputStream", "(Ljava/io/OutputStream;)Lokio/Sink;", "Ljava/net/Socket;", "socket", "(Ljava/net/Socket;)Lokio/Sink;", "Ljava/nio/file/Path;", "path", "", "Ljava/nio/file/OpenOption;", "options", "(Ljava/nio/file/Path;[Ljava/nio/file/OpenOption;)Lokio/Sink;", "(Ljava/io/File;)Lokio/Source;", "Ljava/io/InputStream;", "inputStream", "(Ljava/io/InputStream;)Lokio/Source;", "(Ljava/net/Socket;)Lokio/Source;", "(Ljava/nio/file/Path;[Ljava/nio/file/OpenOption;)Lokio/Source;", "okio"})
public final class -DeprecatedOkio {
  @NotNull
  public static final -DeprecatedOkio INSTANCE = new -DeprecatedOkio();
  
  @Deprecated(message = "moved to extension function", replaceWith = @ReplaceWith(expression = "file.appendingSink()", imports = {"okio.appendingSink"}), level = DeprecationLevel.ERROR)
  @NotNull
  public final Sink appendingSink(@NotNull File file) {
    Intrinsics.checkNotNullParameter(file, "file");
    return Okio.appendingSink(file);
  }
  
  @Deprecated(message = "moved to extension function", replaceWith = @ReplaceWith(expression = "sink.buffer()", imports = {"okio.buffer"}), level = DeprecationLevel.ERROR)
  @NotNull
  public final BufferedSink buffer(@NotNull Sink sink) {
    Intrinsics.checkNotNullParameter(sink, "sink");
    return Okio.buffer(sink);
  }
  
  @Deprecated(message = "moved to extension function", replaceWith = @ReplaceWith(expression = "source.buffer()", imports = {"okio.buffer"}), level = DeprecationLevel.ERROR)
  @NotNull
  public final BufferedSource buffer(@NotNull Source source) {
    Intrinsics.checkNotNullParameter(source, "source");
    return Okio.buffer(source);
  }
  
  @Deprecated(message = "moved to extension function", replaceWith = @ReplaceWith(expression = "file.sink()", imports = {"okio.sink"}), level = DeprecationLevel.ERROR)
  @NotNull
  public final Sink sink(@NotNull File file) {
    Intrinsics.checkNotNullParameter(file, "file");
    return Okio.sink$default(file, false, 1, null);
  }
  
  @Deprecated(message = "moved to extension function", replaceWith = @ReplaceWith(expression = "outputStream.sink()", imports = {"okio.sink"}), level = DeprecationLevel.ERROR)
  @NotNull
  public final Sink sink(@NotNull OutputStream outputStream) {
    Intrinsics.checkNotNullParameter(outputStream, "outputStream");
    return Okio.sink(outputStream);
  }
  
  @Deprecated(message = "moved to extension function", replaceWith = @ReplaceWith(expression = "path.sink(*options)", imports = {"okio.sink"}), level = DeprecationLevel.ERROR)
  @NotNull
  public final Sink sink(@NotNull Path path, @NotNull OpenOption... options) {
    Intrinsics.checkNotNullParameter(path, "path");
    Intrinsics.checkNotNullParameter(options, "options");
    return Okio.sink(path, Arrays.<OpenOption>copyOf(options, options.length));
  }
  
  @Deprecated(message = "moved to extension function", replaceWith = @ReplaceWith(expression = "socket.sink()", imports = {"okio.sink"}), level = DeprecationLevel.ERROR)
  @NotNull
  public final Sink sink(@NotNull Socket socket) {
    Intrinsics.checkNotNullParameter(socket, "socket");
    return Okio.sink(socket);
  }
  
  @Deprecated(message = "moved to extension function", replaceWith = @ReplaceWith(expression = "file.source()", imports = {"okio.source"}), level = DeprecationLevel.ERROR)
  @NotNull
  public final Source source(@NotNull File file) {
    Intrinsics.checkNotNullParameter(file, "file");
    return Okio.source(file);
  }
  
  @Deprecated(message = "moved to extension function", replaceWith = @ReplaceWith(expression = "inputStream.source()", imports = {"okio.source"}), level = DeprecationLevel.ERROR)
  @NotNull
  public final Source source(@NotNull InputStream inputStream) {
    Intrinsics.checkNotNullParameter(inputStream, "inputStream");
    return Okio.source(inputStream);
  }
  
  @Deprecated(message = "moved to extension function", replaceWith = @ReplaceWith(expression = "path.source(*options)", imports = {"okio.source"}), level = DeprecationLevel.ERROR)
  @NotNull
  public final Source source(@NotNull Path path, @NotNull OpenOption... options) {
    Intrinsics.checkNotNullParameter(path, "path");
    Intrinsics.checkNotNullParameter(options, "options");
    return Okio.source(path, Arrays.<OpenOption>copyOf(options, options.length));
  }
  
  @Deprecated(message = "moved to extension function", replaceWith = @ReplaceWith(expression = "socket.source()", imports = {"okio.source"}), level = DeprecationLevel.ERROR)
  @NotNull
  public final Source source(@NotNull Socket socket) {
    Intrinsics.checkNotNullParameter(socket, "socket");
    return Okio.source(socket);
  }
  
  @Deprecated(message = "moved to extension function", replaceWith = @ReplaceWith(expression = "blackholeSink()", imports = {"okio.blackholeSink"}), level = DeprecationLevel.ERROR)
  @NotNull
  public final Sink blackhole() {
    return Okio.blackhole();
  }
}
