package cn.hutool.core.lang;

import cn.hutool.core.lang.func.Func0;
import cn.hutool.core.lang.func.VoidFunc0;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public class Opt<T> {
  private static final Opt<?> EMPTY = new Opt(null);
  
  private final T value;
  
  private Exception exception;
  
  public static <T> Opt<T> empty() {
    return (Opt)EMPTY;
  }
  
  public static <T> Opt<T> of(T value) {
    return new Opt<>(Objects.requireNonNull(value));
  }
  
  public static <T> Opt<T> ofNullable(T value) {
    return (value == null) ? empty() : new Opt<>(value);
  }
  
  public static <T> Opt<T> ofBlankAble(T value) {
    return StrUtil.isBlankIfStr(value) ? empty() : new Opt<>(value);
  }
  
  public static <T, R extends java.util.Collection<T>> Opt<R> ofEmptyAble(R value) {
    return ObjectUtil.isEmpty(value) ? empty() : new Opt<>(value);
  }
  
  public static <T> Opt<T> ofTry(Func0<T> supplier) {
    try {
      return ofNullable((T)supplier.call());
    } catch (Exception e) {
      Opt<T> empty = new Opt<>(null);
      empty.exception = e;
      return empty;
    } 
  }
  
  private Opt(T value) {
    this.value = value;
  }
  
  public T get() {
    return this.value;
  }
  
  public boolean isEmpty() {
    return (this.value == null);
  }
  
  public Exception getException() {
    return this.exception;
  }
  
  public boolean isFail() {
    return (null != this.exception);
  }
  
  public boolean isPresent() {
    return (this.value != null);
  }
  
  public Opt<T> ifPresent(Consumer<? super T> action) {
    if (isPresent())
      action.accept(this.value); 
    return this;
  }
  
  public Opt<T> ifPresentOrElse(Consumer<? super T> action, VoidFunc0 emptyAction) {
    if (isPresent()) {
      action.accept(this.value);
    } else {
      emptyAction.callWithRuntimeException();
    } 
    return this;
  }
  
  public <U> Opt<U> mapOrElse(Function<? super T, ? extends U> mapper, VoidFunc0 emptyAction) {
    if (isPresent())
      return ofNullable(mapper.apply(this.value)); 
    emptyAction.callWithRuntimeException();
    return empty();
  }
  
  public Opt<T> filter(Predicate<? super T> predicate) {
    Objects.requireNonNull(predicate);
    if (isEmpty())
      return this; 
    return predicate.test(this.value) ? this : empty();
  }
  
  public <U> Opt<U> map(Function<? super T, ? extends U> mapper) {
    Objects.requireNonNull(mapper);
    if (isEmpty())
      return empty(); 
    return ofNullable(mapper.apply(this.value));
  }
  
  public <U> Opt<U> flatMap(Function<? super T, ? extends Opt<? extends U>> mapper) {
    Objects.requireNonNull(mapper);
    if (isEmpty())
      return empty(); 
    Opt<U> r = (Opt<U>)mapper.apply(this.value);
    return Objects.<Opt<U>>requireNonNull(r);
  }
  
  public <U> Opt<U> flattedMap(Function<? super T, ? extends Optional<? extends U>> mapper) {
    Objects.requireNonNull(mapper);
    if (isEmpty())
      return empty(); 
    return ofNullable(((Optional<U>)mapper.apply(this.value)).orElse(null));
  }
  
  public Opt<T> peek(Consumer<T> action) throws NullPointerException {
    Objects.requireNonNull(action);
    if (isEmpty())
      return empty(); 
    action.accept(this.value);
    return this;
  }
  
  @SafeVarargs
  public final Opt<T> peeks(Consumer<T>... actions) throws NullPointerException {
    return (Opt<T>)Stream.<Consumer<T>>of(actions).reduce(this, Opt::peek, (opts, opt) -> null);
  }
  
  public Opt<T> or(Supplier<? extends Opt<? extends T>> supplier) {
    Objects.requireNonNull(supplier);
    if (isPresent())
      return this; 
    Opt<T> r = (Opt<T>)supplier.get();
    return Objects.<Opt<T>>requireNonNull(r);
  }
  
  public Stream<T> stream() {
    if (isEmpty())
      return Stream.empty(); 
    return Stream.of(this.value);
  }
  
  public T orElse(T other) {
    return isPresent() ? this.value : other;
  }
  
  public T exceptionOrElse(T other) {
    return isFail() ? other : this.value;
  }
  
  public T orElseGet(Supplier<? extends T> supplier) {
    return isPresent() ? this.value : supplier.get();
  }
  
  public T orElseThrow() {
    return orElseThrow(java.util.NoSuchElementException::new, "No value present");
  }
  
  public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
    if (isPresent())
      return this.value; 
    throw (X)exceptionSupplier.get();
  }
  
  public <X extends Throwable> T orElseThrow(Function<String, ? extends X> exceptionFunction, String message) throws X {
    if (isPresent())
      return this.value; 
    throw (X)exceptionFunction.apply(message);
  }
  
  public Optional<T> toOptional() {
    return Optional.ofNullable(this.value);
  }
  
  public boolean equals(Object obj) {
    if (this == obj)
      return true; 
    if (!(obj instanceof Opt))
      return false; 
    Opt<?> other = (Opt)obj;
    return Objects.equals(this.value, other.value);
  }
  
  public int hashCode() {
    return Objects.hashCode(this.value);
  }
  
  public String toString() {
    return StrUtil.toStringOrNull(this.value);
  }
}
