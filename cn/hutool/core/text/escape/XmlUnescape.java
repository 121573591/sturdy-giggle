package cn.hutool.core.text.escape;

import cn.hutool.core.text.replacer.LookupReplacer;
import cn.hutool.core.text.replacer.ReplacerChain;
import cn.hutool.core.text.replacer.StrReplacer;

public class XmlUnescape extends ReplacerChain {
  private static final long serialVersionUID = 1L;
  
  protected static final String[][] BASIC_UNESCAPE = InternalEscapeUtil.invert(XmlEscape.BASIC_ESCAPE);
  
  protected static final String[][] OTHER_UNESCAPE = new String[][] { { "&apos;", "'" } };
  
  public XmlUnescape() {
    super(new StrReplacer[0]);
    addChain((StrReplacer)new LookupReplacer(BASIC_UNESCAPE));
    addChain(new NumericEntityUnescaper());
    addChain((StrReplacer)new LookupReplacer(OTHER_UNESCAPE));
  }
}
