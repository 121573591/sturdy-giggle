package okio;

import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\0000\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\030\002\n\002\b\003\n\002\020\002\n\002\b\004\n\002\020\b\n\002\b\t\n\002\020\021\n\002\b\003\bÀ\002\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J\027\020\006\032\n\022\006\022\004\030\0010\0050\004H\002¢\006\004\b\006\020\007J\027\020\n\032\0020\t2\006\020\b\032\0020\005H\007¢\006\004\b\n\020\013J\017\020\f\032\0020\005H\007¢\006\004\b\f\020\rR\024\020\017\032\0020\0168\002X\004¢\006\006\n\004\b\017\020\020R\024\020\021\032\0020\0058\002X\004¢\006\006\n\004\b\021\020\022R\032\020\023\032\0020\0168\006XD¢\006\f\n\004\b\023\020\020\032\004\b\024\020\025R\021\020\027\032\0020\0168F¢\006\006\032\004\b\026\020\025R\"\020\031\032\020\022\f\022\n\022\006\022\004\030\0010\0050\0040\0308\002X\004¢\006\006\n\004\b\031\020\032¨\006\033"}, d2 = {"Lokio/SegmentPool;", "", "<init>", "()V", "Ljava/util/concurrent/atomic/AtomicReference;", "Lokio/Segment;", "firstRef", "()Ljava/util/concurrent/atomic/AtomicReference;", "segment", "", "recycle", "(Lokio/Segment;)V", "take", "()Lokio/Segment;", "", "HASH_BUCKET_COUNT", "I", "LOCK", "Lokio/Segment;", "MAX_SIZE", "getMAX_SIZE", "()I", "getByteCount", "byteCount", "", "hashBuckets", "[Ljava/util/concurrent/atomic/AtomicReference;", "okio"})
public final class SegmentPool {
  @NotNull
  public static final SegmentPool INSTANCE = new SegmentPool();
  
  private static final int MAX_SIZE = 65536;
  
  public final int getMAX_SIZE() {
    return MAX_SIZE;
  }
  
  @NotNull
  private static final Segment LOCK = new Segment(new byte[0], 0, 0, false, false);
  
  private static final int HASH_BUCKET_COUNT = Integer.highestOneBit(Runtime.getRuntime().availableProcessors() * 2 - 1);
  
  @NotNull
  private static final AtomicReference<Segment>[] hashBuckets;
  
  static {
    byte b;
    int i;
    AtomicReference[] arrayOfAtomicReference;
    for (b = 0, i = HASH_BUCKET_COUNT, arrayOfAtomicReference = new AtomicReference[i]; b < i; ) {
      byte b1 = b;
      arrayOfAtomicReference[b1] = 
        new AtomicReference();
      b++;
    } 
    hashBuckets = (AtomicReference<Segment>[])arrayOfAtomicReference;
  }
  
  public final int getByteCount() {
    Segment first;
    if ((Segment)firstRef().get() == null) {
      (Segment)firstRef().get();
      return 0;
    } 
    return first.limit;
  }
  
  @JvmStatic
  @NotNull
  public static final Segment take() {
    AtomicReference<Segment> firstRef = INSTANCE.firstRef();
    Segment first = firstRef.getAndSet(LOCK);
    if (first == LOCK)
      return new Segment(); 
    if (first == null) {
      firstRef.set(null);
      return new Segment();
    } 
    firstRef.set(first.next);
    first.next = null;
    first.limit = 0;
    return first;
  }
  
  @JvmStatic
  public static final void recycle(@NotNull Segment segment) {
    Intrinsics.checkNotNullParameter(segment, "segment");
    if (!((segment.next == null && segment.prev == null) ? 1 : 0)) {
      String str = "Failed requirement.";
      throw new IllegalArgumentException(str.toString());
    } 
    if (segment.shared)
      return; 
    AtomicReference<Segment> firstRef = INSTANCE.firstRef();
    Segment first = firstRef.getAndSet(LOCK);
    if (first == LOCK)
      return; 
    int firstLimit = (first != null) ? first.limit : 0;
    if (firstLimit >= MAX_SIZE) {
      firstRef.set(first);
      return;
    } 
    segment.next = first;
    segment.pos = 0;
    segment.limit = firstLimit + 8192;
    firstRef.set(segment);
  }
  
  private final AtomicReference<Segment> firstRef() {
    int hashBucket = (int)(Thread.currentThread().getId() & HASH_BUCKET_COUNT - 1L);
    return hashBuckets[hashBucket];
  }
}
