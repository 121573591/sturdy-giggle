package okhttp3.internal.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import okio.Okio;
import okio.Sink;
import okio.Source;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\0006\n\002\030\002\n\002\020\000\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\002\n\002\b\004\n\002\020\013\n\002\b\007\n\002\020\t\n\002\b\002\n\002\030\002\n\002\b\004\bf\030\000 \0322\0020\001:\001\032J\027\020\005\032\0020\0042\006\020\003\032\0020\002H&¢\006\004\b\005\020\006J\027\020\b\032\0020\0072\006\020\003\032\0020\002H&¢\006\004\b\b\020\tJ\027\020\013\032\0020\0072\006\020\n\032\0020\002H&¢\006\004\b\013\020\tJ\027\020\r\032\0020\f2\006\020\003\032\0020\002H&¢\006\004\b\r\020\016J\037\020\021\032\0020\0072\006\020\017\032\0020\0022\006\020\020\032\0020\002H&¢\006\004\b\021\020\022J\027\020\023\032\0020\0042\006\020\003\032\0020\002H&¢\006\004\b\023\020\006J\027\020\025\032\0020\0242\006\020\003\032\0020\002H&¢\006\004\b\025\020\026J\027\020\030\032\0020\0272\006\020\003\032\0020\002H&¢\006\004\b\030\020\031¨\006\033"}, d2 = {"Lokhttp3/internal/io/FileSystem;", "", "Ljava/io/File;", "file", "Lokio/Sink;", "appendingSink", "(Ljava/io/File;)Lokio/Sink;", "", "delete", "(Ljava/io/File;)V", "directory", "deleteContents", "", "exists", "(Ljava/io/File;)Z", "from", "to", "rename", "(Ljava/io/File;Ljava/io/File;)V", "sink", "", "size", "(Ljava/io/File;)J", "Lokio/Source;", "source", "(Ljava/io/File;)Lokio/Source;", "Companion", "okhttp"})
public interface FileSystem {
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\024\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\004\b\003\030\0002\0020\001:\001\007B\t\b\002¢\006\004\b\002\020\003R\027\020\005\032\0020\0048\006X\004¢\006\006\n\004\b\005\020\006¨\006\001¨\006\b"}, d2 = {"Lokhttp3/internal/io/FileSystem$Companion;", "", "<init>", "()V", "Lokhttp3/internal/io/FileSystem;", "SYSTEM", "Lokhttp3/internal/io/FileSystem;", "SystemFileSystem", "okhttp"})
  public static final class Companion {
    @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000B\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\002\n\002\b\004\n\002\020\013\n\002\b\007\n\002\020\t\n\002\b\002\n\002\030\002\n\002\b\002\n\002\020\016\n\002\b\003\b\002\030\0002\0020\001B\007¢\006\004\b\002\020\003J\027\020\007\032\0020\0062\006\020\005\032\0020\004H\026¢\006\004\b\007\020\bJ\027\020\n\032\0020\t2\006\020\005\032\0020\004H\026¢\006\004\b\n\020\013J\027\020\r\032\0020\t2\006\020\f\032\0020\004H\026¢\006\004\b\r\020\013J\027\020\017\032\0020\0162\006\020\005\032\0020\004H\026¢\006\004\b\017\020\020J\037\020\023\032\0020\t2\006\020\021\032\0020\0042\006\020\022\032\0020\004H\026¢\006\004\b\023\020\024J\027\020\025\032\0020\0062\006\020\005\032\0020\004H\026¢\006\004\b\025\020\bJ\027\020\027\032\0020\0262\006\020\005\032\0020\004H\026¢\006\004\b\027\020\030J\027\020\032\032\0020\0312\006\020\005\032\0020\004H\026¢\006\004\b\032\020\033J\017\020\035\032\0020\034H\026¢\006\004\b\035\020\036¨\006\037"}, d2 = {"Lokhttp3/internal/io/FileSystem$Companion$SystemFileSystem;", "Lokhttp3/internal/io/FileSystem;", "<init>", "()V", "Ljava/io/File;", "file", "Lokio/Sink;", "appendingSink", "(Ljava/io/File;)Lokio/Sink;", "", "delete", "(Ljava/io/File;)V", "directory", "deleteContents", "", "exists", "(Ljava/io/File;)Z", "from", "to", "rename", "(Ljava/io/File;Ljava/io/File;)V", "sink", "", "size", "(Ljava/io/File;)J", "Lokio/Source;", "source", "(Ljava/io/File;)Lokio/Source;", "", "toString", "()Ljava/lang/String;", "okhttp"})
    private static final class SystemFileSystem implements FileSystem {
      @NotNull
      public Source source(@NotNull File file) throws FileNotFoundException {
        Intrinsics.checkNotNullParameter(file, "file");
        return Okio.source(file);
      }
      
      @NotNull
      public Sink sink(@NotNull File file) throws FileNotFoundException {
        Sink sink;
        Intrinsics.checkNotNullParameter(file, "file");
        try {
          sink = Okio.sink$default(file, false, 1, null);
        } catch (FileNotFoundException _) {
          file.getParentFile().mkdirs();
          sink = Okio.sink$default(file, false, 1, null);
        } 
        return sink;
      }
      
      @NotNull
      public Sink appendingSink(@NotNull File file) throws FileNotFoundException {
        Sink sink;
        Intrinsics.checkNotNullParameter(file, "file");
        try {
          sink = Okio.appendingSink(file);
        } catch (FileNotFoundException _) {
          file.getParentFile().mkdirs();
          sink = Okio.appendingSink(file);
        } 
        return sink;
      }
      
      public void delete(@NotNull File file) throws IOException {
        Intrinsics.checkNotNullParameter(file, "file");
        if (!file.delete() && file.exists())
          throw new IOException("failed to delete " + file); 
      }
      
      public boolean exists(@NotNull File file) {
        Intrinsics.checkNotNullParameter(file, "file");
        return file.exists();
      }
      
      public long size(@NotNull File file) {
        Intrinsics.checkNotNullParameter(file, "file");
        return file.length();
      }
      
      public void rename(@NotNull File from, @NotNull File to) throws IOException {
        Intrinsics.checkNotNullParameter(from, "from");
        Intrinsics.checkNotNullParameter(to, "to");
        delete(to);
        if (!from.renameTo(to))
          throw new IOException("failed to rename " + from + " to " + to); 
      }
      
      public void deleteContents(@NotNull File directory) throws IOException {
        File[] files;
        Intrinsics.checkNotNullParameter(directory, "directory");
        if (directory.listFiles() == null) {
          directory.listFiles();
          throw new IOException("not a readable directory: " + directory);
        } 
        byte b;
        int i;
        for (b = 0, i = files.length; b < i; ) {
          File file = files[b];
          if (file.isDirectory()) {
            Intrinsics.checkNotNullExpressionValue(file, "file");
            deleteContents(file);
          } 
          if (!file.delete())
            throw new IOException("failed to delete " + file); 
          b++;
        } 
      }
      
      @NotNull
      public String toString() {
        return "FileSystem.SYSTEM";
      }
    }
  }
  
  @NotNull
  public static final Companion Companion = Companion.$$INSTANCE;
  
  @JvmField
  @NotNull
  public static final FileSystem SYSTEM = new Companion.SystemFileSystem();
  
  @NotNull
  Source source(@NotNull File paramFile) throws FileNotFoundException;
  
  @NotNull
  Sink sink(@NotNull File paramFile) throws FileNotFoundException;
  
  @NotNull
  Sink appendingSink(@NotNull File paramFile) throws FileNotFoundException;
  
  void delete(@NotNull File paramFile) throws IOException;
  
  boolean exists(@NotNull File paramFile);
  
  long size(@NotNull File paramFile);
  
  void rename(@NotNull File paramFile1, @NotNull File paramFile2) throws IOException;
  
  void deleteContents(@NotNull File paramFile) throws IOException;
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000B\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\002\n\002\b\004\n\002\020\013\n\002\b\007\n\002\020\t\n\002\b\002\n\002\030\002\n\002\b\002\n\002\020\016\n\002\b\003\b\002\030\0002\0020\001B\007¢\006\004\b\002\020\003J\027\020\007\032\0020\0062\006\020\005\032\0020\004H\026¢\006\004\b\007\020\bJ\027\020\n\032\0020\t2\006\020\005\032\0020\004H\026¢\006\004\b\n\020\013J\027\020\r\032\0020\t2\006\020\f\032\0020\004H\026¢\006\004\b\r\020\013J\027\020\017\032\0020\0162\006\020\005\032\0020\004H\026¢\006\004\b\017\020\020J\037\020\023\032\0020\t2\006\020\021\032\0020\0042\006\020\022\032\0020\004H\026¢\006\004\b\023\020\024J\027\020\025\032\0020\0062\006\020\005\032\0020\004H\026¢\006\004\b\025\020\bJ\027\020\027\032\0020\0262\006\020\005\032\0020\004H\026¢\006\004\b\027\020\030J\027\020\032\032\0020\0312\006\020\005\032\0020\004H\026¢\006\004\b\032\020\033J\017\020\035\032\0020\034H\026¢\006\004\b\035\020\036¨\006\037"}, d2 = {"Lokhttp3/internal/io/FileSystem$Companion$SystemFileSystem;", "Lokhttp3/internal/io/FileSystem;", "<init>", "()V", "Ljava/io/File;", "file", "Lokio/Sink;", "appendingSink", "(Ljava/io/File;)Lokio/Sink;", "", "delete", "(Ljava/io/File;)V", "directory", "deleteContents", "", "exists", "(Ljava/io/File;)Z", "from", "to", "rename", "(Ljava/io/File;Ljava/io/File;)V", "sink", "", "size", "(Ljava/io/File;)J", "Lokio/Source;", "source", "(Ljava/io/File;)Lokio/Source;", "", "toString", "()Ljava/lang/String;", "okhttp"})
  private static final class SystemFileSystem implements FileSystem {
    @NotNull
    public Source source(@NotNull File file) throws FileNotFoundException {
      Intrinsics.checkNotNullParameter(file, "file");
      return Okio.source(file);
    }
    
    @NotNull
    public Sink sink(@NotNull File file) throws FileNotFoundException {
      Sink sink;
      Intrinsics.checkNotNullParameter(file, "file");
      try {
        sink = Okio.sink$default(file, false, 1, null);
      } catch (FileNotFoundException _) {
        file.getParentFile().mkdirs();
        sink = Okio.sink$default(file, false, 1, null);
      } 
      return sink;
    }
    
    @NotNull
    public Sink appendingSink(@NotNull File file) throws FileNotFoundException {
      Sink sink;
      Intrinsics.checkNotNullParameter(file, "file");
      try {
        sink = Okio.appendingSink(file);
      } catch (FileNotFoundException _) {
        file.getParentFile().mkdirs();
        sink = Okio.appendingSink(file);
      } 
      return sink;
    }
    
    public void delete(@NotNull File file) throws IOException {
      Intrinsics.checkNotNullParameter(file, "file");
      if (!file.delete() && file.exists())
        throw new IOException("failed to delete " + file); 
    }
    
    public boolean exists(@NotNull File file) {
      Intrinsics.checkNotNullParameter(file, "file");
      return file.exists();
    }
    
    public long size(@NotNull File file) {
      Intrinsics.checkNotNullParameter(file, "file");
      return file.length();
    }
    
    public void rename(@NotNull File from, @NotNull File to) throws IOException {
      Intrinsics.checkNotNullParameter(from, "from");
      Intrinsics.checkNotNullParameter(to, "to");
      delete(to);
      if (!from.renameTo(to))
        throw new IOException("failed to rename " + from + " to " + to); 
    }
    
    public void deleteContents(@NotNull File directory) throws IOException {
      File[] files;
      Intrinsics.checkNotNullParameter(directory, "directory");
      if (directory.listFiles() == null) {
        directory.listFiles();
        throw new IOException("not a readable directory: " + directory);
      } 
      byte b;
      int i;
      for (b = 0, i = files.length; b < i; ) {
        File file = files[b];
        if (file.isDirectory()) {
          Intrinsics.checkNotNullExpressionValue(file, "file");
          deleteContents(file);
        } 
        if (!file.delete())
          throw new IOException("failed to delete " + file); 
        b++;
      } 
    }
    
    @NotNull
    public String toString() {
      return "FileSystem.SYSTEM";
    }
  }
}
