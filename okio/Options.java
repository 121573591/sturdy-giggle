package okio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.RandomAccess;
import kotlin.Metadata;
import kotlin.collections.AbstractList;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;

@Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000*\n\002\030\002\n\002\030\002\n\002\030\002\n\002\030\002\n\002\030\002\n\002\020\021\n\000\n\002\020\025\n\002\b\003\n\002\020\b\n\002\b\016\030\000 \0302\b\022\004\022\0020\0020\0012\0060\003j\002`\004:\001\030B!\b\002\022\016\020\006\032\n\022\006\b\001\022\0020\0020\005\022\006\020\b\032\0020\007¢\006\004\b\t\020\nJ\030\020\r\032\0020\0022\006\020\f\032\0020\013H\002¢\006\004\b\r\020\016R\"\020\006\032\n\022\006\b\001\022\0020\0020\0058\000X\004¢\006\f\n\004\b\006\020\017\032\004\b\020\020\021R\024\020\024\032\0020\0138VX\004¢\006\006\032\004\b\022\020\023R\032\020\b\032\0020\0078\000X\004¢\006\f\n\004\b\b\020\025\032\004\b\026\020\027¨\006\031"}, d2 = {"Lokio/Options;", "Lkotlin/collections/AbstractList;", "Lokio/ByteString;", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "", "byteStrings", "", "trie", "<init>", "([Lokio/ByteString;[I)V", "", "index", "get", "(I)Lokio/ByteString;", "[Lokio/ByteString;", "getByteStrings$okio", "()[Lokio/ByteString;", "getSize", "()I", "size", "[I", "getTrie$okio", "()[I", "Companion", "okio"})
public final class Options extends AbstractList<ByteString> implements RandomAccess {
  @NotNull
  public static final Companion Companion = new Companion(null);
  
  @NotNull
  private final ByteString[] byteStrings;
  
  @NotNull
  private final int[] trie;
  
  @NotNull
  public final ByteString[] getByteStrings$okio() {
    return this.byteStrings;
  }
  
  @NotNull
  public final int[] getTrie$okio() {
    return this.trie;
  }
  
  private Options(ByteString[] byteStrings, int[] trie) {
    this.byteStrings = byteStrings;
    this.trie = trie;
  }
  
  public int getSize() {
    return this.byteStrings.length;
  }
  
  @NotNull
  public ByteString get(int index) {
    return this.byteStrings[index];
  }
  
  @JvmStatic
  @NotNull
  public static final Options of(@NotNull ByteString... byteStrings) {
    return Companion.of(byteStrings);
  }
  
  @Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\000>\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\t\n\000\n\002\030\002\n\000\n\002\020\b\n\000\n\002\020 \n\002\030\002\n\002\b\004\n\002\020\002\n\002\b\002\n\002\020\021\n\002\030\002\n\002\b\006\b\003\030\0002\0020\001B\t\b\002¢\006\004\b\002\020\003J[\020\021\032\0020\0202\b\b\002\020\005\032\0020\0042\006\020\007\032\0020\0062\b\b\002\020\t\032\0020\b2\f\020\f\032\b\022\004\022\0020\0130\n2\b\b\002\020\r\032\0020\b2\b\b\002\020\016\032\0020\b2\f\020\017\032\b\022\004\022\0020\b0\nH\002¢\006\004\b\021\020\022J#\020\025\032\0020\0242\022\020\f\032\n\022\006\b\001\022\0020\0130\023\"\0020\013H\007¢\006\004\b\025\020\026R\030\020\031\032\0020\004*\0020\0068BX\004¢\006\006\032\004\b\027\020\030¨\006\032"}, d2 = {"Lokio/Options$Companion;", "", "<init>", "()V", "", "nodeOffset", "Lokio/Buffer;", "node", "", "byteStringOffset", "", "Lokio/ByteString;", "byteStrings", "fromIndex", "toIndex", "indexes", "", "buildTrieRecursive", "(JLokio/Buffer;ILjava/util/List;IILjava/util/List;)V", "", "Lokio/Options;", "of", "([Lokio/ByteString;)Lokio/Options;", "getIntCount", "(Lokio/Buffer;)J", "intCount", "okio"})
  @SourceDebugExtension({"SMAP\nOptions.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Options.kt\nokio/Options$Companion\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 3 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 5 Util.kt\nokio/-SegmentedByteString\n*L\n1#1,236:1\n11065#2:237\n11400#2,3:238\n13374#2,3:243\n37#3,2:241\n1#4:246\n74#5:247\n74#5:248\n*S KotlinDebug\n*F\n+ 1 Options.kt\nokio/Options$Companion\n*L\n43#1:237\n43#1:238,3\n44#1:243,3\n43#1:241,2\n151#1:247\n208#1:248\n*E\n"})
  public static final class Companion {
    private Companion() {}
    
    @JvmStatic
    @NotNull
    public final Options of(@NotNull ByteString... byteStrings) {
      Intrinsics.checkNotNullParameter(byteStrings, "byteStrings");
      if ((byteStrings.length == 0)) {
        int[] arrayOfInt = new int[2];
        arrayOfInt[0] = 0;
        arrayOfInt[1] = -1;
        return new Options(new ByteString[0], arrayOfInt, null);
      } 
      List<ByteString> list = ArraysKt.toMutableList((Object[])byteStrings);
      CollectionsKt.sort(list);
      ByteString[] arrayOfByteString2 = byteStrings;
      int $i$f$map = 0;
      ByteString[] arrayOfByteString3 = arrayOfByteString2;
      Collection<Integer> destination$iv$iv = new ArrayList(arrayOfByteString2.length);
      int $i$f$mapTo = 0;
      byte b2;
      int k;
      for (b2 = 0, k = arrayOfByteString3.length; b2 < k; ) {
        Object item$iv$iv = arrayOfByteString3[b2];
        Object object1 = item$iv$iv;
        Collection<Integer> collection = destination$iv$iv;
        int $i$a$-map-Options$Companion$of$indexes$1 = 0;
        collection.add(Integer.valueOf(-1));
      } 
      List list1 = (List)destination$iv$iv;
      int $i$f$toTypedArray = 0;
      Collection thisCollection$iv = list1;
      Integer[] arrayOfInteger = (Integer[])thisCollection$iv.toArray((Object[])new Integer[0]);
      List<Integer> indexes = CollectionsKt.mutableListOf(Arrays.copyOf((Object[])arrayOfInteger, arrayOfInteger.length));
      ByteString[] arrayOfByteString1 = byteStrings;
      int $i$f$forEachIndexed = 0;
      int index$iv = 0;
      byte b1 = 0;
      int j = arrayOfByteString1.length;
      if (b1 < j) {
        Object item$iv = arrayOfByteString1[b1], object1 = item$iv;
        int callerIndex = index$iv++, $i$a$-forEachIndexed-Options$Companion$of$1 = 0;
        int sortedIndex = CollectionsKt.binarySearch$default(list, (Comparable)object1, 0, 0, 6, null);
        indexes.set(sortedIndex, Integer.valueOf(callerIndex));
      } 
      if (!((((ByteString)list.get(0)).size() > 0) ? 1 : 0)) {
        int $i$a$-require-Options$Companion$of$2 = 0;
        String str = "the empty byte string is not a supported option";
        throw new IllegalArgumentException(str.toString());
      } 
      int a = 0;
      while (a < list.size()) {
        ByteString prefix = list.get(a);
        int b = a + 1;
        while (b < list.size()) {
          ByteString byteString = list.get(b);
          if (byteString.startsWith(prefix)) {
            if (!((byteString.size() != prefix.size()) ? 1 : 0)) {
              int $i$a$-require-Options$Companion$of$3 = 0;
              String str = "duplicate option: " + byteString;
              throw new IllegalArgumentException(str.toString());
            } 
            if (((Number)indexes.get(b)).intValue() > ((Number)indexes.get(a)).intValue()) {
              list.remove(b);
              indexes.remove(b);
              continue;
            } 
            b++;
          } 
        } 
        a++;
      } 
      Buffer trieBytes = new Buffer();
      buildTrieRecursive$default(this, 0L, trieBytes, 0, list, 0, 0, indexes, 53, null);
      int[] trie = new int[(int)getIntCount(trieBytes)];
      int i = 0;
      while (!trieBytes.exhausted())
        trie[i++] = trieBytes.readInt(); 
      Intrinsics.checkNotNullExpressionValue(Arrays.copyOf(byteStrings, byteStrings.length), "copyOf(this, size)");
      return new Options(Arrays.copyOf(byteStrings, byteStrings.length), trie, null);
    }
    
    private final void buildTrieRecursive(long nodeOffset, Buffer node, int byteStringOffset, List<ByteString> byteStrings, int fromIndex, int toIndex, List<Number> indexes) {
      if (!((fromIndex < toIndex) ? 1 : 0)) {
        String str = "Failed requirement.";
        throw new IllegalArgumentException(str.toString());
      } 
      for (int i = fromIndex; i < toIndex; i++) {
        if (!((((ByteString)byteStrings.get(i)).size() >= byteStringOffset) ? 1 : 0)) {
          String str = "Failed requirement.";
          throw new IllegalArgumentException(str.toString());
        } 
      } 
      int j = fromIndex;
      ByteString from = byteStrings.get(j);
      ByteString to = byteStrings.get(toIndex - 1);
      int prefixIndex = -1;
      if (byteStringOffset == from.size()) {
        prefixIndex = ((Number)indexes.get(j)).intValue();
        j++;
        from = byteStrings.get(j);
      } 
      if (from.getByte(byteStringOffset) != to.getByte(byteStringOffset)) {
        int selectChoiceCount = 1;
        for (int k = j + 1; k < toIndex; k++) {
          if (((ByteString)byteStrings.get(k - 1)).getByte(byteStringOffset) != ((ByteString)byteStrings.get(k)).getByte(byteStringOffset))
            selectChoiceCount++; 
        } 
        long childNodesOffset = nodeOffset + getIntCount(node) + 2L + (selectChoiceCount * 2);
        node.writeInt(selectChoiceCount);
        node.writeInt(prefixIndex);
        for (int m = j; m < toIndex; m++) {
          byte rangeByte = ((ByteString)byteStrings.get(m)).getByte(byteStringOffset);
          if (m == j || rangeByte != ((ByteString)byteStrings.get(m - 1)).getByte(byteStringOffset)) {
            byte b = rangeByte;
            int other$iv = 255, $i$f$and = 0;
            node.writeInt(b & other$iv);
          } 
        } 
        Buffer childNodes = new Buffer();
        int rangeStart = j;
        while (rangeStart < toIndex) {
          byte rangeByte = ((ByteString)byteStrings.get(rangeStart)).getByte(byteStringOffset);
          int rangeEnd = toIndex;
          for (int n = rangeStart + 1; n < toIndex; n++) {
            if (rangeByte != ((ByteString)byteStrings.get(n)).getByte(byteStringOffset)) {
              rangeEnd = n;
              break;
            } 
          } 
          if (rangeStart + 1 == rangeEnd && byteStringOffset + 1 == ((ByteString)byteStrings.get(rangeStart)).size()) {
            node.writeInt(((Number)indexes.get(rangeStart)).intValue());
          } else {
            node.writeInt(-1 * (int)(childNodesOffset + getIntCount(childNodes)));
            buildTrieRecursive(childNodesOffset, childNodes, byteStringOffset + 1, byteStrings, rangeStart, rangeEnd, (List)indexes);
          } 
          rangeStart = rangeEnd;
        } 
        node.writeAll(childNodes);
      } else {
        int scanByteCount = 0;
        for (int k = byteStringOffset, m = Math.min(from.size(), to.size()); k < m && from.getByte(k) == to.getByte(k); k++)
          scanByteCount++; 
        long childNodesOffset = nodeOffset + getIntCount(node) + 2L + scanByteCount + 1L;
        node.writeInt(-scanByteCount);
        node.writeInt(prefixIndex);
        for (int n = byteStringOffset, i1 = byteStringOffset + scanByteCount; n < i1; n++) {
          byte $this$and$iv = from.getByte(n);
          int other$iv = 255, $i$f$and = 0;
          node.writeInt($this$and$iv & other$iv);
        } 
        if (j + 1 == toIndex) {
          if (!((byteStringOffset + scanByteCount == ((ByteString)byteStrings.get(j)).size()) ? 1 : 0)) {
            String str = "Check failed.";
            throw new IllegalStateException(str.toString());
          } 
          node.writeInt(((Number)indexes.get(j)).intValue());
        } else {
          Buffer childNodes = new Buffer();
          node.writeInt(-1 * (int)(childNodesOffset + getIntCount(childNodes)));
          buildTrieRecursive(childNodesOffset, childNodes, byteStringOffset + scanByteCount, byteStrings, j, toIndex, (List)indexes);
          node.writeAll(childNodes);
        } 
      } 
    }
    
    private final long getIntCount(Buffer $this$intCount) {
      return $this$intCount.size() / 4L;
    }
  }
}
