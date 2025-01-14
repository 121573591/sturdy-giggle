package okhttp3.internal.http;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(mv = {1, 8, 0}, k = 2, xi = 48, d1 = {"\000'\n\002\020\016\n\002\030\002\n\002\b\004\n\002\020\021\n\002\030\002\n\002\b\004\n\002\020\t\n\002\b\002\n\002\b\004*\001\017\032\025\020\002\032\004\030\0010\001*\0020\000H\000¢\006\004\b\002\020\003\032\023\020\004\032\0020\000*\0020\001H\000¢\006\004\b\004\020\005\"\034\020\b\032\n\022\006\022\004\030\0010\0070\0068\002X\004¢\006\006\n\004\b\b\020\t\"\032\020\n\032\b\022\004\022\0020\0000\0068\002X\004¢\006\006\n\004\b\n\020\013\"\024\020\r\032\0020\f8\000XT¢\006\006\n\004\b\r\020\016\"\024\020\020\032\0020\0178\002X\004¢\006\006\n\004\b\020\020\021¨\006\022"}, d2 = {"", "Ljava/util/Date;", "toHttpDateOrNull", "(Ljava/lang/String;)Ljava/util/Date;", "toHttpDateString", "(Ljava/util/Date;)Ljava/lang/String;", "", "Ljava/text/DateFormat;", "BROWSER_COMPATIBLE_DATE_FORMATS", "[Ljava/text/DateFormat;", "BROWSER_COMPATIBLE_DATE_FORMAT_STRINGS", "[Ljava/lang/String;", "", "MAX_DATE", "J", "okhttp3/internal/http/DatesKt.STANDARD_DATE_FORMAT.1", "STANDARD_DATE_FORMAT", "Lokhttp3/internal/http/DatesKt$STANDARD_DATE_FORMAT$1;", "okhttp"})
public final class DatesKt {
  public static final long MAX_DATE = 253402300799999L;
  
  @NotNull
  private static final DatesKt$STANDARD_DATE_FORMAT$1 STANDARD_DATE_FORMAT = new DatesKt$STANDARD_DATE_FORMAT$1();
  
  @NotNull
  private static final String[] BROWSER_COMPATIBLE_DATE_FORMAT_STRINGS;
  
  @Metadata(mv = {1, 8, 0}, k = 1, xi = 48, d1 = {"\000\021\n\000\n\002\030\002\n\002\030\002\n\002\b\003*\001\000\b\n\030\0002\b\022\004\022\0020\0020\001J\017\020\003\032\0020\002H\024¢\006\004\b\003\020\004¨\006\005"}, d2 = {"okhttp3/internal/http/DatesKt.STANDARD_DATE_FORMAT.1", "Ljava/lang/ThreadLocal;", "Ljava/text/DateFormat;", "initialValue", "()Ljava/text/DateFormat;", "okhttp"})
  public static final class DatesKt$STANDARD_DATE_FORMAT$1 extends ThreadLocal<DateFormat> {
    @NotNull
    protected DateFormat initialValue() {
      SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US), $this$initialValue_u24lambda_u240 = simpleDateFormat1;
      int $i$a$-apply-DatesKt$STANDARD_DATE_FORMAT$1$initialValue$1 = 0;
      $this$initialValue_u24lambda_u240.setLenient(false);
      $this$initialValue_u24lambda_u240.setTimeZone(Util.UTC);
      return simpleDateFormat1;
    }
  }
  
  static {
    String[] arrayOfString = new String[15];
    arrayOfString[0] = "EEE, dd MMM yyyy HH:mm:ss zzz";
    arrayOfString[1] = 
      "EEEE, dd-MMM-yy HH:mm:ss zzz";
    arrayOfString[2] = "EEE MMM d HH:mm:ss yyyy";
    arrayOfString[3] = "EEE, dd-MMM-yyyy HH:mm:ss z";
    arrayOfString[4] = "EEE, dd-MMM-yyyy HH-mm-ss z";
    arrayOfString[5] = "EEE, dd MMM yy HH:mm:ss z";
    arrayOfString[6] = "EEE dd-MMM-yyyy HH:mm:ss z";
    arrayOfString[7] = "EEE dd MMM yyyy HH:mm:ss z";
    arrayOfString[8] = "EEE dd-MMM-yyyy HH-mm-ss z";
    arrayOfString[9] = "EEE dd-MMM-yy HH:mm:ss z";
    arrayOfString[10] = "EEE dd MMM yy HH:mm:ss z";
    arrayOfString[11] = "EEE,dd-MMM-yy HH:mm:ss z";
    arrayOfString[12] = "EEE,dd-MMM-yyyy HH:mm:ss z";
    arrayOfString[13] = "EEE, dd-MM-yyyy HH:mm:ss z";
    arrayOfString[14] = "EEE MMM d yyyy HH:mm:ss z";
    BROWSER_COMPATIBLE_DATE_FORMAT_STRINGS = arrayOfString;
  }
  
  @NotNull
  private static final DateFormat[] BROWSER_COMPATIBLE_DATE_FORMATS = new DateFormat[BROWSER_COMPATIBLE_DATE_FORMAT_STRINGS.length];
  
  @Nullable
  public static final Date toHttpDateOrNull(@NotNull String $this$toHttpDateOrNull) {
    Intrinsics.checkNotNullParameter($this$toHttpDateOrNull, "<this>");
    if ((((CharSequence)$this$toHttpDateOrNull).length() == 0))
      return null; 
    ParsePosition position = new ParsePosition(0);
    Object result = null;
    result = STANDARD_DATE_FORMAT.get().parse($this$toHttpDateOrNull, position);
    if (position.getIndex() == $this$toHttpDateOrNull.length())
      return (Date)result; 
    synchronized (BROWSER_COMPATIBLE_DATE_FORMAT_STRINGS) {
      int $i$a$-synchronized-DatesKt$toHttpDateOrNull$1 = 0;
      for (int i = 0, j = BROWSER_COMPATIBLE_DATE_FORMAT_STRINGS.length; i < j; i++) {
        DateFormat format = BROWSER_COMPATIBLE_DATE_FORMATS[i];
        if (format == null) {
          SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat(BROWSER_COMPATIBLE_DATE_FORMAT_STRINGS[i], Locale.US), $this$toHttpDateOrNull_u24lambda_u241_u24lambda_u240 = simpleDateFormat1;
          int $i$a$-apply-DatesKt$toHttpDateOrNull$1$1 = 0;
          $this$toHttpDateOrNull_u24lambda_u241_u24lambda_u240.setTimeZone(Util.UTC);
          format = simpleDateFormat1;
          BROWSER_COMPATIBLE_DATE_FORMATS[i] = format;
        } 
        position.setIndex(0);
        result = format.parse($this$toHttpDateOrNull, position);
        if (position.getIndex() != 0)
          return (Date)result; 
      } 
      Unit unit = Unit.INSTANCE;
    } 
    return null;
  }
  
  @NotNull
  public static final String toHttpDateString(@NotNull Date $this$toHttpDateString) {
    Intrinsics.checkNotNullParameter($this$toHttpDateString, "<this>");
    Intrinsics.checkNotNullExpressionValue(STANDARD_DATE_FORMAT.get().format($this$toHttpDateString), "STANDARD_DATE_FORMAT.get().format(this)");
    return STANDARD_DATE_FORMAT.get().format($this$toHttpDateString);
  }
}
