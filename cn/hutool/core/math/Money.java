package cn.hutool.core.math;

import cn.hutool.core.util.StrUtil;
import java.io.File;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;

public class Money implements Serializable, Comparable<Money> {
  private static final long serialVersionUID = -1004117971993390293L;
  
  public static final String DEFAULT_CURRENCY_CODE = "CNY";
  
  public static final RoundingMode DEFAULT_ROUNDING_MODE = RoundingMode.HALF_EVEN;
  
  private static final int[] CENT_FACTORS = new int[] { 1, 10, 100, 1000 };
  
  private long cent;
  
  private final Currency currency;
  
  public Money() {
    this(0.0D);
  }
  
  public Money(long yuan, int cent) {
    this(yuan, cent, Currency.getInstance("CNY"));
  }
  
  public Money(long yuan, int cent, Currency currency) {
    this.currency = currency;
    if (0L == yuan) {
      this.cent = cent;
    } else {
      this.cent = yuan * getCentFactor() + (cent % getCentFactor());
    } 
  }
  
  public Money(String amount) {
    this(amount, Currency.getInstance("CNY"));
  }
  
  public Money(String amount, Currency currency) {
    this(new BigDecimal(amount), currency);
  }
  
  public Money(String amount, Currency currency, RoundingMode roundingMode) {
    this(new BigDecimal(amount), currency, roundingMode);
  }
  
  public Money(double amount) {
    this(amount, Currency.getInstance("CNY"));
  }
  
  public Money(double amount, Currency currency) {
    this.currency = currency;
    this.cent = Math.round(amount * getCentFactor());
  }
  
  public Money(BigDecimal amount) {
    this(amount, Currency.getInstance("CNY"));
  }
  
  public Money(BigDecimal amount, RoundingMode roundingMode) {
    this(amount, Currency.getInstance("CNY"), roundingMode);
  }
  
  public Money(BigDecimal amount, Currency currency) {
    this(amount, currency, DEFAULT_ROUNDING_MODE);
  }
  
  public Money(BigDecimal amount, Currency currency, RoundingMode roundingMode) {
    this.currency = currency;
    this.cent = rounding(amount.movePointRight(currency.getDefaultFractionDigits()), roundingMode);
  }
  
  public BigDecimal getAmount() {
    return BigDecimal.valueOf(this.cent, this.currency.getDefaultFractionDigits());
  }
  
  public void setAmount(BigDecimal amount) {
    if (amount != null)
      this.cent = rounding(amount.movePointRight(2), DEFAULT_ROUNDING_MODE); 
  }
  
  public long getCent() {
    return this.cent;
  }
  
  public Currency getCurrency() {
    return this.currency;
  }
  
  public int getCentFactor() {
    return CENT_FACTORS[this.currency.getDefaultFractionDigits()];
  }
  
  public boolean equals(Object other) {
    return (other instanceof Money && equals((Money)other));
  }
  
  public boolean equals(Money other) {
    return (this.currency.equals(other.currency) && this.cent == other.cent);
  }
  
  public int hashCode() {
    return (int)(this.cent ^ this.cent >>> 32L);
  }
  
  public int compareTo(Money other) {
    assertSameCurrencyAs(other);
    return Long.compare(this.cent, other.cent);
  }
  
  public boolean greaterThan(Money other) {
    return (compareTo(other) > 0);
  }
  
  public Money add(Money other) {
    assertSameCurrencyAs(other);
    return newMoneyWithSameCurrency(this.cent + other.cent);
  }
  
  public Money addTo(Money other) {
    assertSameCurrencyAs(other);
    this.cent += other.cent;
    return this;
  }
  
  public Money subtract(Money other) {
    assertSameCurrencyAs(other);
    return newMoneyWithSameCurrency(this.cent - other.cent);
  }
  
  public Money subtractFrom(Money other) {
    assertSameCurrencyAs(other);
    this.cent -= other.cent;
    return this;
  }
  
  public Money multiply(long val) {
    return newMoneyWithSameCurrency(this.cent * val);
  }
  
  public Money multiplyBy(long val) {
    this.cent *= val;
    return this;
  }
  
  public Money multiply(double val) {
    return newMoneyWithSameCurrency(Math.round(this.cent * val));
  }
  
  public Money multiplyBy(double val) {
    this.cent = Math.round(this.cent * val);
    return this;
  }
  
  public Money multiply(BigDecimal val) {
    return multiply(val, DEFAULT_ROUNDING_MODE);
  }
  
  public Money multiplyBy(BigDecimal val) {
    return multiplyBy(val, DEFAULT_ROUNDING_MODE);
  }
  
  public Money multiply(BigDecimal val, RoundingMode roundingMode) {
    BigDecimal newCent = BigDecimal.valueOf(this.cent).multiply(val);
    return newMoneyWithSameCurrency(rounding(newCent, roundingMode));
  }
  
  public Money multiplyBy(BigDecimal val, RoundingMode roundingMode) {
    BigDecimal newCent = BigDecimal.valueOf(this.cent).multiply(val);
    this.cent = rounding(newCent, roundingMode);
    return this;
  }
  
  public Money divide(double val) {
    return newMoneyWithSameCurrency(Math.round(this.cent / val));
  }
  
  public Money divideBy(double val) {
    this.cent = Math.round(this.cent / val);
    return this;
  }
  
  public Money divide(BigDecimal val) {
    return divide(val, DEFAULT_ROUNDING_MODE);
  }
  
  public Money divide(BigDecimal val, RoundingMode roundingMode) {
    BigDecimal newCent = BigDecimal.valueOf(this.cent).divide(val, roundingMode);
    return newMoneyWithSameCurrency(newCent.longValue());
  }
  
  public Money divideBy(BigDecimal val) {
    return divideBy(val, DEFAULT_ROUNDING_MODE);
  }
  
  public Money divideBy(BigDecimal val, RoundingMode roundingMode) {
    BigDecimal newCent = BigDecimal.valueOf(this.cent).divide(val, roundingMode);
    this.cent = newCent.longValue();
    return this;
  }
  
  public Money[] allocate(int targets) {
    Money[] results = new Money[targets];
    Money lowResult = newMoneyWithSameCurrency(this.cent / targets);
    Money highResult = newMoneyWithSameCurrency(lowResult.cent + 1L);
    int remainder = (int)this.cent % targets;
    int i;
    for (i = 0; i < remainder; i++)
      results[i] = highResult; 
    for (i = remainder; i < targets; i++)
      results[i] = lowResult; 
    return results;
  }
  
  public Money[] allocate(long[] ratios) {
    Money[] results = new Money[ratios.length];
    long total = 0L;
    for (long element : ratios)
      total += element; 
    long remainder = this.cent;
    int i;
    for (i = 0; i < results.length; i++) {
      results[i] = newMoneyWithSameCurrency(this.cent * ratios[i] / total);
      remainder -= (results[i]).cent;
    } 
    for (i = 0; i < remainder; i++)
      (results[i]).cent++; 
    return results;
  }
  
  public String toString() {
    return getAmount().toString();
  }
  
  protected void assertSameCurrencyAs(Money other) {
    if (!this.currency.equals(other.currency))
      throw new IllegalArgumentException("Money math currency mismatch."); 
  }
  
  protected long rounding(BigDecimal val, RoundingMode roundingMode) {
    return val.setScale(0, roundingMode).longValue();
  }
  
  protected Money newMoneyWithSameCurrency(long cent) {
    Money money = new Money(0.0D, this.currency);
    money.cent = cent;
    return money;
  }
  
  public String dump() {
    return StrUtil.builder()
      .append("cent = ")
      .append(this.cent)
      .append(File.separatorChar)
      .append("currency = ")
      .append(this.currency)
      .toString();
  }
  
  public void setCent(long cent) {
    this.cent = cent;
  }
}
