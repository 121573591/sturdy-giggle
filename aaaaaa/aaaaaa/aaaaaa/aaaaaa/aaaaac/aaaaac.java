package aaaaaa.aaaaaa.aaaaaa.aaaaaa.aaaaac;

import java.util.Comparator;
import java.util.Map;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.SourceDebugExtension;

@SourceDebugExtension({"SMAP\nComparisons.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Comparisons.kt\nkotlin/comparisons/ComparisonsKt__ComparisonsKt$compareByDescending$1\n+ 2 MonsterBattleStage.kt\ncn/pixellive/mc/game/stage/MonsterBattleStage\n*L\n1#1,328:1\n1155#2:329\n*E\n"})
public final class aaaaac<T> implements Comparator {
  public final int compare(T paramT1, T paramT2) {
    Map.Entry entry = (Map.Entry)paramT2;
    boolean bool = false;
    entry = (Map.Entry)paramT1;
    Integer integer = (Integer)entry.getValue();
    bool = false;
    return ComparisonsKt.compareValues(integer, (Integer)entry.getValue());
  }
  
  static {
  
  }
}
