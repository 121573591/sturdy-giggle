package okhttp3;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ReplaceWith;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.ArrayIteratorKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.text.StringsKt;
import okhttp3.internal.Util;
import okhttp3.internal.http.DatesKt;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000j\n\002\030\002\n\002\020\034\n\002\030\002\n\002\020\016\n\002\020\021\n\002\b\003\n\002\020\t\n\002\b\002\n\002\020\000\n\000\n\002\020\013\n\002\b\005\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\020\b\n\002\b\002\n\002\020(\n\002\b\004\n\002\020\"\n\002\b\002\n\002\030\002\n\002\b\004\n\002\020$\n\002\020 \n\002\b\013\030\000 32\024\022\020\022\016\022\004\022\0020\003\022\004\022\0020\0030\0020\001:\00243B\027\b\002\022\f\020\005\032\b\022\004\022\0020\0030\004¢\006\004\b\006\020\007J\r\020\t\032\0020\b¢\006\004\b\t\020\nJ\032\020\016\032\0020\r2\b\020\f\032\004\030\0010\013H\002¢\006\004\b\016\020\017J\032\020\021\032\004\030\0010\0032\006\020\020\032\0020\003H\002¢\006\004\b\021\020\022J\027\020\024\032\004\030\0010\0232\006\020\020\032\0020\003¢\006\004\b\024\020\025J\031\020\027\032\004\030\0010\0262\006\020\020\032\0020\003H\007¢\006\004\b\027\020\030J\017\020\032\032\0020\031H\026¢\006\004\b\032\020\033J\"\020\035\032\024\022\020\022\016\022\004\022\0020\003\022\004\022\0020\0030\0020\034H\002¢\006\004\b\035\020\036J\025\020\020\032\0020\0032\006\020\037\032\0020\031¢\006\004\b\020\020 J\023\020\"\032\b\022\004\022\0020\0030!¢\006\004\b\"\020#J\r\020%\032\0020$¢\006\004\b%\020&J\017\020(\032\0020\031H\007¢\006\004\b'\020\033J\037\020+\032\024\022\004\022\0020\003\022\n\022\b\022\004\022\0020\0030*0)¢\006\004\b+\020,J\017\020-\032\0020\003H\026¢\006\004\b-\020.J\025\020/\032\0020\0032\006\020\037\032\0020\031¢\006\004\b/\020 J\033\0200\032\b\022\004\022\0020\0030*2\006\020\020\032\0020\003¢\006\004\b0\0201R\032\020\005\032\b\022\004\022\0020\0030\0048\002X\004¢\006\006\n\004\b\005\0202R\021\020(\032\0020\0318G¢\006\006\032\004\b(\020\033¨\0065"}, d2 = {"Lokhttp3/Headers;", "", "Lkotlin/Pair;", "", "", "namesAndValues", "<init>", "([Ljava/lang/String;)V", "", "byteCount", "()J", "", "other", "", "equals", "(Ljava/lang/Object;)Z", "name", "get", "(Ljava/lang/String;)Ljava/lang/String;", "Ljava/util/Date;", "getDate", "(Ljava/lang/String;)Ljava/util/Date;", "Ljava/time/Instant;", "getInstant", "(Ljava/lang/String;)Ljava/time/Instant;", "", "hashCode", "()I", "", "iterator", "()Ljava/util/Iterator;", "index", "(I)Ljava/lang/String;", "", "names", "()Ljava/util/Set;", "Lokhttp3/Headers$Builder;", "newBuilder", "()Lokhttp3/Headers$Builder;", "-deprecated_size", "size", "", "", "toMultimap", "()Ljava/util/Map;", "toString", "()Ljava/lang/String;", "value", "values", "(Ljava/lang/String;)Ljava/util/List;", "[Ljava/lang/String;", "Companion", "Builder", "okhttp"})
public final class Headers implements Iterable<Pair<? extends String, ? extends String>>, KMappedMarker {
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  @NotNull
  private final String[] namesAndValues;
  
  private Headers(String[] namesAndValues) {
    this.namesAndValues = namesAndValues;
  }
  
  @Nullable
  public final String get(@NotNull String name) {
    Intrinsics.checkNotNullParameter(name, "name");
    return Companion.get(this.namesAndValues, name);
  }
  
  @Nullable
  public final Date getDate(@NotNull String name) {
    Intrinsics.checkNotNullParameter(name, "name");
    get(name);
    return (get(name) != null) ? DatesKt.toHttpDateOrNull(get(name)) : null;
  }
  
  @IgnoreJRERequirement
  @Nullable
  public final Instant getInstant(@NotNull String name) {
    Intrinsics.checkNotNullParameter(name, "name");
    Date value = getDate(name);
    return (value != null) ? value.toInstant() : null;
  }
  
  @JvmName(name = "size")
  public final int size() {
    return this.namesAndValues.length / 2;
  }
  
  @Deprecated(message = "moved to val", replaceWith = @ReplaceWith(expression = "size", imports = {}), level = DeprecationLevel.ERROR)
  @JvmName(name = "-deprecated_size")
  public final int -deprecated_size() {
    return size();
  }
  
  @NotNull
  public final String name(int index) {
    return this.namesAndValues[index * 2];
  }
  
  @NotNull
  public final String value(int index) {
    return this.namesAndValues[index * 2 + 1];
  }
  
  @NotNull
  public final Set<String> names() {
    TreeSet<String> result = new TreeSet(StringsKt.getCASE_INSENSITIVE_ORDER(StringCompanionObject.INSTANCE));
    for (int i = 0, j = size(); i < j; i++)
      result.add(name(i)); 
    Intrinsics.checkNotNullExpressionValue(Collections.unmodifiableSet(result), "unmodifiableSet(result)");
    return Collections.unmodifiableSet(result);
  }
  
  @NotNull
  public final List<String> values(@NotNull String name) {
    Intrinsics.checkNotNullParameter(name, "name");
    List<String> result = null;
    for (int i = 0, j = size(); i < j; i++) {
      if (StringsKt.equals(name, name(i), true)) {
        if (result == null)
          result = new ArrayList(2); 
        result.add(value(i));
      } 
    } 
    if (result != null) {
      Intrinsics.checkNotNullExpressionValue(
          Collections.unmodifiableList(result), "{\n      Collections.unmodifiableList(result)\n    }");
    } else {
    
    } 
    return CollectionsKt.emptyList();
  }
  
  public final long byteCount() {
    long result = (this.namesAndValues.length * 2);
    for (int i = 0, j = this.namesAndValues.length; i < j; i++)
      result += this.namesAndValues[i].length(); 
    return result;
  }
  
  @NotNull
  public Iterator<Pair<String, String>> iterator() {
    byte b;
    int i;
    Pair[] arrayOfPair;
    for (b = 0, i = size(), arrayOfPair = new Pair[i]; b < i; ) {
      byte b1 = b;
      arrayOfPair[b1] = TuplesKt.to(name(b1), value(b1));
      b++;
    } 
    return ArrayIteratorKt.iterator((Object[])arrayOfPair);
  }
  
  @NotNull
  public final Builder newBuilder() {
    Builder result = new Builder();
    CollectionsKt.addAll(result.getNamesAndValues$okhttp(), (Object[])this.namesAndValues);
    return result;
  }
  
  public boolean equals(@Nullable Object other) {
    return (other instanceof Headers && Arrays.equals((Object[])this.namesAndValues, (Object[])((Headers)other).namesAndValues));
  }
  
  public int hashCode() {
    return Arrays.hashCode((Object[])this.namesAndValues);
  }
  
  @NotNull
  public String toString() {
    StringBuilder stringBuilder1 = new StringBuilder(), $this$toString_u24lambda_u240 = stringBuilder1;
    int $i$a$-buildString-Headers$toString$1 = 0;
    for (int i = 0, j = size(); i < j; i++) {
      String name = name(i);
      String value = value(i);
      $this$toString_u24lambda_u240.append(name);
      $this$toString_u24lambda_u240.append(": ");
      $this$toString_u24lambda_u240.append(Util.isSensitiveHeader(name) ? "██" : value);
      $this$toString_u24lambda_u240.append("\n");
    } 
    Intrinsics.checkNotNullExpressionValue(stringBuilder1.toString(), "StringBuilder().apply(builderAction).toString()");
    return stringBuilder1.toString();
  }
  
  @NotNull
  public final Map<String, List<String>> toMultimap() {
    TreeMap<Object, Object> result = new TreeMap<>(StringsKt.getCASE_INSENSITIVE_ORDER(StringCompanionObject.INSTANCE));
    for (int i = 0, j = size(); i < j; i++) {
      String str1 = name(i);
      Intrinsics.checkNotNullExpressionValue(Locale.US, "US");
      Intrinsics.checkNotNullExpressionValue(str1.toLowerCase(Locale.US), "this as java.lang.String).toLowerCase(locale)");
      String name = str1.toLowerCase(Locale.US);
      List<String> values = (List)result.get(name);
      if (values == null) {
        values = new ArrayList(2);
        result.put(name, values);
      } 
      values.add(value(i));
    } 
    return (Map)result;
  }
  
  @JvmStatic
  @JvmName(name = "of")
  @NotNull
  public static final Headers of(@NotNull String... namesAndValues) {
    return Companion.of(namesAndValues);
  }
  
  @JvmStatic
  @JvmName(name = "of")
  @NotNull
  public static final Headers of(@NotNull Map<String, String> $this$of) {
    return Companion.of($this$of);
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\0004\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\016\n\002\b\004\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\f\n\002\020!\n\002\b\005\030\0002\0020\001B\007¢\006\004\b\002\020\003J\025\020\006\032\0020\0002\006\020\005\032\0020\004¢\006\004\b\006\020\007J\037\020\006\032\0020\0002\006\020\b\032\0020\0042\006\020\n\032\0020\tH\007¢\006\004\b\006\020\013J\035\020\006\032\0020\0002\006\020\b\032\0020\0042\006\020\n\032\0020\f¢\006\004\b\006\020\rJ\035\020\006\032\0020\0002\006\020\b\032\0020\0042\006\020\n\032\0020\004¢\006\004\b\006\020\016J\025\020\021\032\0020\0002\006\020\020\032\0020\017¢\006\004\b\021\020\022J\027\020\024\032\0020\0002\006\020\005\032\0020\004H\000¢\006\004\b\023\020\007J\037\020\024\032\0020\0002\006\020\b\032\0020\0042\006\020\n\032\0020\004H\000¢\006\004\b\023\020\016J\035\020\025\032\0020\0002\006\020\b\032\0020\0042\006\020\n\032\0020\004¢\006\004\b\025\020\016J\r\020\026\032\0020\017¢\006\004\b\026\020\027J\032\020\030\032\004\030\0010\0042\006\020\b\032\0020\004H\002¢\006\004\b\030\020\031J\025\020\032\032\0020\0002\006\020\b\032\0020\004¢\006\004\b\032\020\007J \020\033\032\0020\0002\006\020\b\032\0020\0042\006\020\n\032\0020\tH\002¢\006\004\b\033\020\013J \020\033\032\0020\0002\006\020\b\032\0020\0042\006\020\n\032\0020\fH\002¢\006\004\b\033\020\rJ \020\033\032\0020\0002\006\020\b\032\0020\0042\006\020\n\032\0020\004H\002¢\006\004\b\033\020\016R \020\035\032\b\022\004\022\0020\0040\0348\000X\004¢\006\f\n\004\b\035\020\036\032\004\b\037\020 ¨\006!"}, d2 = {"Lokhttp3/Headers$Builder;", "", "<init>", "()V", "", "line", "add", "(Ljava/lang/String;)Lokhttp3/Headers$Builder;", "name", "Ljava/time/Instant;", "value", "(Ljava/lang/String;Ljava/time/Instant;)Lokhttp3/Headers$Builder;", "Ljava/util/Date;", "(Ljava/lang/String;Ljava/util/Date;)Lokhttp3/Headers$Builder;", "(Ljava/lang/String;Ljava/lang/String;)Lokhttp3/Headers$Builder;", "Lokhttp3/Headers;", "headers", "addAll", "(Lokhttp3/Headers;)Lokhttp3/Headers$Builder;", "addLenient$okhttp", "addLenient", "addUnsafeNonAscii", "build", "()Lokhttp3/Headers;", "get", "(Ljava/lang/String;)Ljava/lang/String;", "removeAll", "set", "", "namesAndValues", "Ljava/util/List;", "getNamesAndValues$okhttp", "()Ljava/util/List;", "okhttp"})
  @SourceDebugExtension({"SMAP\nHeaders.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Headers.kt\nokhttp3/Headers$Builder\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,458:1\n1#2:459\n37#3,2:460\n*S KotlinDebug\n*F\n+ 1 Headers.kt\nokhttp3/Headers$Builder\n*L\n359#1:460,2\n*E\n"})
  public static final class Builder {
    @NotNull
    private final List<String> namesAndValues = new ArrayList<>(20);
    
    @NotNull
    public final List<String> getNamesAndValues$okhttp() {
      return this.namesAndValues;
    }
    
    @NotNull
    public final Builder addLenient$okhttp(@NotNull String line) {
      Intrinsics.checkNotNullParameter(line, "line");
      Builder builder1 = this, $this$addLenient_u24lambda_u240 = builder1;
      int $i$a$-apply-Headers$Builder$addLenient$1 = 0;
      int index = StringsKt.indexOf$default(line, ':', 1, false, 4, null);
      if (index != -1) {
        Intrinsics.checkNotNullExpressionValue(line.substring(0, index), "this as java.lang.String…ing(startIndex, endIndex)");
        Intrinsics.checkNotNullExpressionValue(line.substring(index + 1), "this as java.lang.String).substring(startIndex)");
        $this$addLenient_u24lambda_u240.addLenient$okhttp(line.substring(0, index), line.substring(index + 1));
      } else if (line.charAt(0) == ':') {
        Intrinsics.checkNotNullExpressionValue(line.substring(1), "this as java.lang.String).substring(startIndex)");
        $this$addLenient_u24lambda_u240.addLenient$okhttp("", line.substring(1));
      } else {
        $this$addLenient_u24lambda_u240.addLenient$okhttp("", line);
      } 
      return builder1;
    }
    
    @NotNull
    public final Builder add(@NotNull String line) {
      Intrinsics.checkNotNullParameter(line, "line");
      Builder builder1 = this, $this$add_u24lambda_u242 = builder1;
      int $i$a$-apply-Headers$Builder$add$1 = 0;
      int index = StringsKt.indexOf$default(line, ':', 0, false, 6, null);
      if (!((index != -1) ? 1 : 0)) {
        int $i$a$-require-Headers$Builder$add$1$1 = 0;
        String str = "Unexpected header: " + line;
        throw new IllegalArgumentException(str.toString());
      } 
      Intrinsics.checkNotNullExpressionValue(line.substring(0, index), "this as java.lang.String…ing(startIndex, endIndex)");
      Intrinsics.checkNotNullExpressionValue(line.substring(index + 1), "this as java.lang.String).substring(startIndex)");
      $this$add_u24lambda_u242.add(StringsKt.trim(line.substring(0, index)).toString(), line.substring(index + 1));
      return builder1;
    }
    
    @NotNull
    public final Builder add(@NotNull String name, @NotNull String value) {
      Intrinsics.checkNotNullParameter(name, "name");
      Intrinsics.checkNotNullParameter(value, "value");
      Builder builder1 = this, $this$add_u24lambda_u243 = builder1;
      int $i$a$-apply-Headers$Builder$add$2 = 0;
      Headers.Companion.checkName(name);
      Headers.Companion.checkValue(value, name);
      $this$add_u24lambda_u243.addLenient$okhttp(name, value);
      return builder1;
    }
    
    @NotNull
    public final Builder addUnsafeNonAscii(@NotNull String name, @NotNull String value) {
      Intrinsics.checkNotNullParameter(name, "name");
      Intrinsics.checkNotNullParameter(value, "value");
      Builder builder1 = this, $this$addUnsafeNonAscii_u24lambda_u244 = builder1;
      int $i$a$-apply-Headers$Builder$addUnsafeNonAscii$1 = 0;
      Headers.Companion.checkName(name);
      $this$addUnsafeNonAscii_u24lambda_u244.addLenient$okhttp(name, value);
      return builder1;
    }
    
    @NotNull
    public final Builder addAll(@NotNull Headers headers) {
      Intrinsics.checkNotNullParameter(headers, "headers");
      Builder builder1 = this, $this$addAll_u24lambda_u245 = builder1;
      int $i$a$-apply-Headers$Builder$addAll$1 = 0;
      for (int i = 0, j = headers.size(); i < j; i++)
        $this$addAll_u24lambda_u245.addLenient$okhttp(headers.name(i), headers.value(i)); 
      return builder1;
    }
    
    @NotNull
    public final Builder add(@NotNull String name, @NotNull Date value) {
      Intrinsics.checkNotNullParameter(name, "name");
      Intrinsics.checkNotNullParameter(value, "value");
      Builder builder1 = this, $this$add_u24lambda_u246 = builder1;
      int $i$a$-apply-Headers$Builder$add$3 = 0;
      $this$add_u24lambda_u246.add(name, DatesKt.toHttpDateString(value));
      return builder1;
    }
    
    @IgnoreJRERequirement
    @NotNull
    public final Builder add(@NotNull String name, @NotNull Instant value) {
      Intrinsics.checkNotNullParameter(name, "name");
      Intrinsics.checkNotNullParameter(value, "value");
      Builder builder1 = this, $this$add_u24lambda_u247 = builder1;
      int $i$a$-apply-Headers$Builder$add$4 = 0;
      $this$add_u24lambda_u247.add(name, new Date(value.toEpochMilli()));
      return builder1;
    }
    
    @NotNull
    public final Builder set(@NotNull String name, @NotNull Date value) {
      Intrinsics.checkNotNullParameter(name, "name");
      Intrinsics.checkNotNullParameter(value, "value");
      Builder builder1 = this, $this$set_u24lambda_u248 = builder1;
      int $i$a$-apply-Headers$Builder$set$1 = 0;
      $this$set_u24lambda_u248.set(name, DatesKt.toHttpDateString(value));
      return builder1;
    }
    
    @IgnoreJRERequirement
    @NotNull
    public final Builder set(@NotNull String name, @NotNull Instant value) {
      Intrinsics.checkNotNullParameter(name, "name");
      Intrinsics.checkNotNullParameter(value, "value");
      Builder $this$set_u24lambda_u249 = this;
      int $i$a$-apply-Headers$Builder$set$2 = 0;
      return $this$set_u24lambda_u249.set(name, new Date(value.toEpochMilli()));
    }
    
    @NotNull
    public final Builder addLenient$okhttp(@NotNull String name, @NotNull String value) {
      Intrinsics.checkNotNullParameter(name, "name");
      Intrinsics.checkNotNullParameter(value, "value");
      Builder builder1 = this, $this$addLenient_u24lambda_u2410 = builder1;
      int $i$a$-apply-Headers$Builder$addLenient$2 = 0;
      $this$addLenient_u24lambda_u2410.namesAndValues.add(name);
      $this$addLenient_u24lambda_u2410.namesAndValues.add(StringsKt.trim(value).toString());
      return builder1;
    }
    
    @NotNull
    public final Builder removeAll(@NotNull String name) {
      Intrinsics.checkNotNullParameter(name, "name");
      Builder builder1 = this, $this$removeAll_u24lambda_u2411 = builder1;
      int $i$a$-apply-Headers$Builder$removeAll$1 = 0;
      int i = 0;
      while (i < $this$removeAll_u24lambda_u2411.namesAndValues.size()) {
        if (StringsKt.equals(name, $this$removeAll_u24lambda_u2411.namesAndValues.get(i), true)) {
          $this$removeAll_u24lambda_u2411.namesAndValues.remove(i);
          $this$removeAll_u24lambda_u2411.namesAndValues.remove(i);
          i -= 2;
        } 
        i += 2;
      } 
      return builder1;
    }
    
    @NotNull
    public final Builder set(@NotNull String name, @NotNull String value) {
      Intrinsics.checkNotNullParameter(name, "name");
      Intrinsics.checkNotNullParameter(value, "value");
      Builder builder1 = this, $this$set_u24lambda_u2412 = builder1;
      int $i$a$-apply-Headers$Builder$set$3 = 0;
      Headers.Companion.checkName(name);
      Headers.Companion.checkValue(value, name);
      $this$set_u24lambda_u2412.removeAll(name);
      $this$set_u24lambda_u2412.addLenient$okhttp(name, value);
      return builder1;
    }
    
    @Nullable
    public final String get(@NotNull String name) {
      Intrinsics.checkNotNullParameter(name, "name");
      int j = this.namesAndValues.size() - 2, i = j, k = ProgressionUtilKt.getProgressionLastElement(j, 0, -2);
      if (k <= i)
        while (true) {
          if (StringsKt.equals(name, this.namesAndValues.get(i), true))
            return this.namesAndValues.get(i + 1); 
          if (i != k) {
            i -= 2;
            continue;
          } 
          break;
        }  
      return null;
    }
    
    @NotNull
    public final Headers build() {
      Collection<String> $this$toTypedArray$iv = this.namesAndValues;
      int $i$f$toTypedArray = 0;
      Collection<String> thisCollection$iv = $this$toTypedArray$iv;
      return new Headers(thisCollection$iv.<String>toArray(new String[0]), null);
    }
  }
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\0002\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\016\n\000\n\002\020\002\n\002\b\005\n\002\020\021\n\002\b\003\n\002\030\002\n\002\b\004\n\002\020$\n\002\b\004\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J\027\020\007\032\0020\0062\006\020\005\032\0020\004H\002¢\006\004\b\007\020\bJ\037\020\n\032\0020\0062\006\020\t\032\0020\0042\006\020\005\032\0020\004H\002¢\006\004\b\n\020\013J'\020\016\032\004\030\0010\0042\f\020\r\032\b\022\004\022\0020\0040\f2\006\020\005\032\0020\004H\002¢\006\004\b\016\020\017J#\020\023\032\0020\0202\022\020\r\032\n\022\006\b\001\022\0020\0040\f\"\0020\004H\007¢\006\004\b\021\020\022J#\020\021\032\0020\0202\022\020\r\032\n\022\006\b\001\022\0020\0040\f\"\0020\004H\007¢\006\004\b\024\020\022J#\020\021\032\0020\0202\022\020\026\032\016\022\004\022\0020\004\022\004\022\0020\0040\025H\007¢\006\004\b\024\020\027J\037\020\030\032\0020\020*\016\022\004\022\0020\004\022\004\022\0020\0040\025H\007¢\006\004\b\021\020\027¨\006\031"}, d2 = {"Lokhttp3/Headers$Companion;", "", "<init>", "()V", "", "name", "", "checkName", "(Ljava/lang/String;)V", "value", "checkValue", "(Ljava/lang/String;Ljava/lang/String;)V", "", "namesAndValues", "get", "([Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;", "Lokhttp3/Headers;", "of", "([Ljava/lang/String;)Lokhttp3/Headers;", "headersOf", "-deprecated_of", "", "headers", "(Ljava/util/Map;)Lokhttp3/Headers;", "toHeaders", "okhttp"})
  @SourceDebugExtension({"SMAP\nHeaders.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Headers.kt\nokhttp3/Headers$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,458:1\n1#2:459\n*E\n"})
  public static final class Companion {
    private Companion() {}
    
    private final String get(String[] namesAndValues, String name) {
      int j = namesAndValues.length - 2, i = j, k = ProgressionUtilKt.getProgressionLastElement(j, 0, -2);
      if (k <= i)
        while (true) {
          if (StringsKt.equals(name, namesAndValues[i], true))
            return namesAndValues[i + 1]; 
          if (i != k) {
            i -= 2;
            continue;
          } 
          break;
        }  
      return null;
    }
    
    @JvmStatic
    @JvmName(name = "of")
    @NotNull
    public final Headers of(@NotNull String... namesAndValues) {
      Intrinsics.checkNotNullParameter(namesAndValues, "namesAndValues");
      if (!((namesAndValues.length % 2 == 0) ? 1 : 0)) {
        int $i$a$-require-Headers$Companion$headersOf$1 = 0;
        String str = "Expected alternating header names and values";
        throw new IllegalArgumentException(str.toString());
      } 
      String[] arrayOfString = (String[])namesAndValues.clone();
      int i, k;
      for (i = 0, k = arrayOfString.length; i < k; i++) {
        if (!((arrayOfString[i] != null) ? 1 : 0)) {
          int $i$a$-require-Headers$Companion$headersOf$2 = 0;
          String str = "Headers cannot be null";
          throw new IllegalArgumentException(str.toString());
        } 
        arrayOfString[i] = StringsKt.trim(arrayOfString[i]).toString();
      } 
      i = arrayOfString.length + -1;
      int j = 0, m = ProgressionUtilKt.getProgressionLastElement(0, i, 2);
      if (j <= m)
        while (true) {
          String name = arrayOfString[j];
          String value = arrayOfString[j + 1];
          checkName(name);
          checkValue(value, name);
          if (j != m) {
            j += 2;
            continue;
          } 
          break;
        }  
      return new Headers(arrayOfString, null);
    }
    
    @Deprecated(message = "function name changed", replaceWith = @ReplaceWith(expression = "headersOf(*namesAndValues)", imports = {}), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_of")
    @NotNull
    public final Headers -deprecated_of(@NotNull String... namesAndValues) {
      Intrinsics.checkNotNullParameter(namesAndValues, "namesAndValues");
      return of(Arrays.<String>copyOf(namesAndValues, namesAndValues.length));
    }
    
    @JvmStatic
    @JvmName(name = "of")
    @NotNull
    public final Headers of(@NotNull Map $this$toHeaders) {
      // Byte code:
      //   0: aload_1
      //   1: ldc '<this>'
      //   3: invokestatic checkNotNullParameter : (Ljava/lang/Object;Ljava/lang/String;)V
      //   6: aload_1
      //   7: invokeinterface size : ()I
      //   12: iconst_2
      //   13: imul
      //   14: anewarray java/lang/String
      //   17: astore_2
      //   18: iconst_0
      //   19: istore_3
      //   20: aload_1
      //   21: invokeinterface entrySet : ()Ljava/util/Set;
      //   26: invokeinterface iterator : ()Ljava/util/Iterator;
      //   31: astore #4
      //   33: aload #4
      //   35: invokeinterface hasNext : ()Z
      //   40: ifeq -> 138
      //   43: aload #4
      //   45: invokeinterface next : ()Ljava/lang/Object;
      //   50: checkcast java/util/Map$Entry
      //   53: astore #5
      //   55: aload #5
      //   57: invokeinterface getKey : ()Ljava/lang/Object;
      //   62: checkcast java/lang/String
      //   65: astore #6
      //   67: aload #5
      //   69: invokeinterface getValue : ()Ljava/lang/Object;
      //   74: checkcast java/lang/String
      //   77: astore #7
      //   79: aload #6
      //   81: checkcast java/lang/CharSequence
      //   84: invokestatic trim : (Ljava/lang/CharSequence;)Ljava/lang/CharSequence;
      //   87: invokevirtual toString : ()Ljava/lang/String;
      //   90: astore #8
      //   92: aload #7
      //   94: checkcast java/lang/CharSequence
      //   97: invokestatic trim : (Ljava/lang/CharSequence;)Ljava/lang/CharSequence;
      //   100: invokevirtual toString : ()Ljava/lang/String;
      //   103: astore #9
      //   105: aload_0
      //   106: aload #8
      //   108: invokespecial checkName : (Ljava/lang/String;)V
      //   111: aload_0
      //   112: aload #9
      //   114: aload #8
      //   116: invokespecial checkValue : (Ljava/lang/String;Ljava/lang/String;)V
      //   119: aload_2
      //   120: iload_3
      //   121: aload #8
      //   123: aastore
      //   124: aload_2
      //   125: iload_3
      //   126: iconst_1
      //   127: iadd
      //   128: aload #9
      //   130: aastore
      //   131: iinc #3, 2
      //   134: nop
      //   135: goto -> 33
      //   138: new okhttp3/Headers
      //   141: dup
      //   142: aload_2
      //   143: aconst_null
      //   144: invokespecial <init> : ([Ljava/lang/String;Lkotlin/jvm/internal/DefaultConstructorMarker;)V
      //   147: areturn
      // Line number table:
      //   Java source line number -> byte code offset
      //   #413	-> 6
      //   #414	-> 18
      //   #415	-> 20
      //   #415	-> 31
      //   #415	-> 65
      //   #415	-> 77
      //   #416	-> 79
      //   #416	-> 90
      //   #417	-> 92
      //   #417	-> 103
      //   #418	-> 105
      //   #419	-> 111
      //   #420	-> 119
      //   #421	-> 124
      //   #422	-> 134
      //   #425	-> 138
      // Local variable table:
      //   start	length	slot	name	descriptor
      //   92	43	8	name	Ljava/lang/String;
      //   105	30	9	value	Ljava/lang/String;
      //   67	68	6	k	Ljava/lang/String;
      //   79	56	7	v	Ljava/lang/String;
      //   18	130	2	namesAndValues	[Ljava/lang/String;
      //   20	128	3	i	I
      //   0	148	0	this	Lokhttp3/Headers$Companion;
      //   0	148	1	$this$toHeaders	Ljava/util/Map;
    }
    
    @Deprecated(message = "function moved to extension", replaceWith = @ReplaceWith(expression = "headers.toHeaders()", imports = {}), level = DeprecationLevel.ERROR)
    @JvmName(name = "-deprecated_of")
    @NotNull
    public final Headers -deprecated_of(@NotNull Map<String, String> headers) {
      Intrinsics.checkNotNullParameter(headers, "headers");
      return of(headers);
    }
    
    private final void checkName(String name) {
      if (!((((CharSequence)name).length() > 0) ? 1 : 0)) {
        int $i$a$-require-Headers$Companion$checkName$1 = 0;
        String str = "name is empty";
        throw new IllegalArgumentException(str.toString());
      } 
      for (int i = 0, j = name.length(); i < j; i++) {
        char c = name.charAt(i);
        if (!(('!' <= c) ? ((c < '') ? 1 : 0) : 0)) {
          int $i$a$-require-Headers$Companion$checkName$2 = 0;
          Object[] arrayOfObject = new Object[3];
          arrayOfObject[0] = Integer.valueOf(c);
          arrayOfObject[1] = Integer.valueOf(i);
          arrayOfObject[2] = name;
          String str = Util.format("Unexpected char %#04x at %d in header name: %s", arrayOfObject);
          throw new IllegalArgumentException(str.toString());
        } 
      } 
    }
    
    private final void checkValue(String value, String name) {
      // Byte code:
      //   0: iconst_0
      //   1: istore_3
      //   2: aload_1
      //   3: invokevirtual length : ()I
      //   6: istore #4
      //   8: iload_3
      //   9: iload #4
      //   11: if_icmpge -> 168
      //   14: aload_1
      //   15: iload_3
      //   16: invokevirtual charAt : (I)C
      //   19: istore #5
      //   21: iload #5
      //   23: bipush #9
      //   25: if_icmpeq -> 54
      //   28: bipush #32
      //   30: iload #5
      //   32: if_icmpgt -> 50
      //   35: iload #5
      //   37: bipush #127
      //   39: if_icmpge -> 46
      //   42: iconst_1
      //   43: goto -> 51
      //   46: iconst_0
      //   47: goto -> 51
      //   50: iconst_0
      //   51: ifeq -> 58
      //   54: iconst_1
      //   55: goto -> 59
      //   58: iconst_0
      //   59: ifne -> 162
      //   62: iconst_0
      //   63: istore #6
      //   65: new java/lang/StringBuilder
      //   68: dup
      //   69: invokespecial <init> : ()V
      //   72: ldc 'Unexpected char %#04x at %d in %s value'
      //   74: iconst_3
      //   75: anewarray java/lang/Object
      //   78: astore #7
      //   80: aload #7
      //   82: iconst_0
      //   83: iload #5
      //   85: invokestatic valueOf : (I)Ljava/lang/Integer;
      //   88: aastore
      //   89: aload #7
      //   91: iconst_1
      //   92: iload_3
      //   93: invokestatic valueOf : (I)Ljava/lang/Integer;
      //   96: aastore
      //   97: aload #7
      //   99: iconst_2
      //   100: aload_2
      //   101: aastore
      //   102: aload #7
      //   104: invokestatic format : (Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
      //   107: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   110: aload_2
      //   111: invokestatic isSensitiveHeader : (Ljava/lang/String;)Z
      //   114: ifeq -> 122
      //   117: ldc ''
      //   119: goto -> 141
      //   122: new java/lang/StringBuilder
      //   125: dup
      //   126: invokespecial <init> : ()V
      //   129: ldc ': '
      //   131: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   134: aload_1
      //   135: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   138: invokevirtual toString : ()Ljava/lang/String;
      //   141: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   144: invokevirtual toString : ()Ljava/lang/String;
      //   147: astore #6
      //   149: new java/lang/IllegalArgumentException
      //   152: dup
      //   153: aload #6
      //   155: invokevirtual toString : ()Ljava/lang/String;
      //   158: invokespecial <init> : (Ljava/lang/String;)V
      //   161: athrow
      //   162: iinc #3, 1
      //   165: goto -> 8
      //   168: return
      // Line number table:
      //   Java source line number -> byte code offset
      //   #448	-> 0
      //   #449	-> 14
      //   #450	-> 21
      //   #451	-> 65
      //   #452	-> 110
      //   #451	-> 141
      //   #450	-> 147
      //   #448	-> 162
      //   #455	-> 168
      // Local variable table:
      //   start	length	slot	name	descriptor
      //   65	82	6	$i$a$-require-Headers$Companion$checkValue$1	I
      //   21	141	5	c	C
      //   2	166	3	i	I
      //   0	169	0	this	Lokhttp3/Headers$Companion;
      //   0	169	1	value	Ljava/lang/String;
      //   0	169	2	name	Ljava/lang/String;
    }
  }
}
