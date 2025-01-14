package cn.hutool.core.lang.ansi;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import java.util.Objects;

public class AnsiColorWrapper {
  private final int code;
  
  private final AnsiColors.BitDepth bitDepth;
  
  public AnsiColorWrapper(int code, AnsiColors.BitDepth bitDepth) {
    if (bitDepth == AnsiColors.BitDepth.FOUR)
      Assert.isTrue(((30 <= code && code <= 37) || (90 <= code && code <= 97)), "The value of 4 bit color only supported [30~37],[90~97].", new Object[0]); 
    Assert.isTrue((0 <= code && code <= 255), "The value of 8 bit color only supported [0~255].", new Object[0]);
    this.code = code;
    this.bitDepth = bitDepth;
  }
  
  public AnsiElement toAnsiElement(ForeOrBack foreOrBack) {
    if (this.bitDepth == AnsiColors.BitDepth.FOUR) {
      if (foreOrBack == ForeOrBack.FORE) {
        for (AnsiColor item : AnsiColor.values()) {
          if (item.getCode() == this.code)
            return item; 
        } 
        throw new IllegalArgumentException(StrUtil.format("No matched AnsiColor instance,code={}", new Object[] { Integer.valueOf(this.code) }));
      } 
      for (AnsiBackground item : AnsiBackground.values()) {
        if (item.getCode() == this.code + 10)
          return item; 
      } 
      throw new IllegalArgumentException(StrUtil.format("No matched AnsiBackground instance,code={}", new Object[] { Integer.valueOf(this.code) }));
    } 
    if (foreOrBack == ForeOrBack.FORE)
      return Ansi8BitColor.foreground(this.code); 
    return Ansi8BitColor.background(this.code);
  }
  
  public boolean equals(Object o) {
    if (this == o)
      return true; 
    if (o == null || getClass() != o.getClass())
      return false; 
    AnsiColorWrapper that = (AnsiColorWrapper)o;
    return (this.code == that.code && this.bitDepth == that.bitDepth);
  }
  
  public int hashCode() {
    return Objects.hash(new Object[] { Integer.valueOf(this.code), this.bitDepth });
  }
}
