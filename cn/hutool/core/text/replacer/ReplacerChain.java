package cn.hutool.core.text.replacer;

import cn.hutool.core.lang.Chain;
import cn.hutool.core.text.StrBuilder;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ReplacerChain extends StrReplacer implements Chain<StrReplacer, ReplacerChain> {
  private static final long serialVersionUID = 1L;
  
  private final List<StrReplacer> replacers = new LinkedList<>();
  
  public ReplacerChain(StrReplacer... strReplacers) {
    for (StrReplacer strReplacer : strReplacers)
      addChain(strReplacer); 
  }
  
  public Iterator<StrReplacer> iterator() {
    return this.replacers.iterator();
  }
  
  public ReplacerChain addChain(StrReplacer element) {
    this.replacers.add(element);
    return this;
  }
  
  protected int replace(CharSequence str, int pos, StrBuilder out) {
    int consumed = 0;
    for (StrReplacer strReplacer : this.replacers) {
      consumed = strReplacer.replace(str, pos, out);
      if (0 != consumed)
        return consumed; 
    } 
    return consumed;
  }
}
