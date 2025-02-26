package cn.hutool.dfa;

import cn.hutool.core.collection.CollUtil;
import java.util.Set;

public class StopChar {
  public static final Set<Character> STOP_WORD = CollUtil.newHashSet((Object[])new Character[] { 
        Character.valueOf(' '), Character.valueOf('\''), Character.valueOf('、'), Character.valueOf('。'), 
        Character.valueOf('·'), Character.valueOf('ˉ'), Character.valueOf('ˇ'), Character.valueOf('々'), Character.valueOf('—'), Character.valueOf('～'), 
        Character.valueOf('‖'), Character.valueOf('…'), Character.valueOf('‘'), Character.valueOf('’'), Character.valueOf('“'), Character.valueOf('”'), Character.valueOf('〔'), Character.valueOf('〕'), Character.valueOf('〈'), Character.valueOf('〉'), 
        Character.valueOf('《'), Character.valueOf('》'), Character.valueOf('「'), Character.valueOf('」'), Character.valueOf('『'), 
        Character.valueOf('』'), Character.valueOf('〖'), Character.valueOf('〗'), Character.valueOf('【'), Character.valueOf('】'), 
        Character.valueOf('±'), Character.valueOf('＋'), Character.valueOf('－'), Character.valueOf('×'), Character.valueOf('÷'), Character.valueOf('∧'), Character.valueOf('∨'), Character.valueOf('∑'), Character.valueOf('∏'), Character.valueOf('∪'), 
        Character.valueOf('∩'), Character.valueOf('∈'), Character.valueOf('√'), Character.valueOf('⊥'), Character.valueOf('⊙'), Character.valueOf('∫'), 
        Character.valueOf('∮'), Character.valueOf('≡'), Character.valueOf('≌'), Character.valueOf('≈'), 
        Character.valueOf('∽'), Character.valueOf('∝'), Character.valueOf('≠'), Character.valueOf('≮'), Character.valueOf('≯'), Character.valueOf('≤'), Character.valueOf('≥'), Character.valueOf('∞'), Character.valueOf('∶'), Character.valueOf('∵'), 
        Character.valueOf('∴'), Character.valueOf('∷'), Character.valueOf('♂'), Character.valueOf('♀'), Character.valueOf('°'), Character.valueOf('′'), Character.valueOf('〃'), 
        Character.valueOf('℃'), Character.valueOf('＄'), Character.valueOf('¤'), 
        Character.valueOf('￠'), Character.valueOf('￡'), Character.valueOf('‰'), Character.valueOf('§'), Character.valueOf('☆'), Character.valueOf('★'), Character.valueOf('〇'), Character.valueOf('○'), Character.valueOf('●'), Character.valueOf('◎'), 
        Character.valueOf('◇'), Character.valueOf('◆'), Character.valueOf('□'), Character.valueOf('■'), Character.valueOf('△'), Character.valueOf('▽'), Character.valueOf('⊿'), Character.valueOf('▲'), 
        Character.valueOf('▼'), Character.valueOf('◣'), 
        Character.valueOf('◤'), Character.valueOf('◢'), Character.valueOf('◥'), Character.valueOf('▁'), Character.valueOf('▂'), Character.valueOf('▃'), Character.valueOf('▄'), Character.valueOf('▅'), Character.valueOf('▆'), Character.valueOf('▇'), 
        Character.valueOf('█'), Character.valueOf('▉'), Character.valueOf('▊'), Character.valueOf('▋'), Character.valueOf('▌'), Character.valueOf('▍'), Character.valueOf('▎'), Character.valueOf('▏'), Character.valueOf('▓'), 
        Character.valueOf('※'), 
        Character.valueOf('→'), Character.valueOf('←'), Character.valueOf('↑'), Character.valueOf('↓'), Character.valueOf('↖'), Character.valueOf('↗'), Character.valueOf('↘'), Character.valueOf('↙'), Character.valueOf('〓'), Character.valueOf('ⅰ'), 
        Character.valueOf('ⅱ'), Character.valueOf('ⅲ'), Character.valueOf('ⅳ'), Character.valueOf('ⅴ'), Character.valueOf('ⅵ'), Character.valueOf('ⅶ'), Character.valueOf('ⅷ'), Character.valueOf('ⅸ'), Character.valueOf('ⅹ'), Character.valueOf('①'), 
        Character.valueOf('②'), Character.valueOf('③'), Character.valueOf('④'), Character.valueOf('⑤'), Character.valueOf('⑥'), Character.valueOf('⑦'), Character.valueOf('⑧'), Character.valueOf('⑨'), Character.valueOf('⑩'), Character.valueOf('⒈'), 
        Character.valueOf('⒉'), Character.valueOf('⒊'), Character.valueOf('⒋'), Character.valueOf('⒌'), Character.valueOf('⒍'), Character.valueOf('⒎'), Character.valueOf('⒏'), Character.valueOf('⒐'), Character.valueOf('⒑'), Character.valueOf('⒒'), 
        Character.valueOf('⒓'), 
        Character.valueOf('⒔'), Character.valueOf('⒕'), Character.valueOf('⒖'), Character.valueOf('⒗'), Character.valueOf('⒘'), Character.valueOf('⒙'), Character.valueOf('⒚'), Character.valueOf('⒛'), Character.valueOf('⑴'), 
        Character.valueOf('⑵'), Character.valueOf('⑶'), Character.valueOf('⑷'), Character.valueOf('⑸'), Character.valueOf('⑹'), Character.valueOf('⑺'), Character.valueOf('⑻'), Character.valueOf('⑼'), Character.valueOf('⑽'), Character.valueOf('⑾'), 
        Character.valueOf('⑿'), Character.valueOf('⒀'), 
        Character.valueOf('⒁'), Character.valueOf('⒂'), Character.valueOf('⒃'), Character.valueOf('⒄'), Character.valueOf('⒅'), Character.valueOf('⒆'), Character.valueOf('⒇'), Character.valueOf('Ⅰ'), 
        Character.valueOf('Ⅱ'), Character.valueOf('Ⅲ'), Character.valueOf('Ⅳ'), Character.valueOf('Ⅴ'), Character.valueOf('Ⅵ'), Character.valueOf('Ⅶ'), Character.valueOf('Ⅷ'), Character.valueOf('Ⅸ'), Character.valueOf('Ⅹ'), Character.valueOf('Ⅺ'), 
        Character.valueOf('Ⅻ'), Character.valueOf('！'), Character.valueOf('”'), 
        Character.valueOf('＃'), Character.valueOf('￥'), Character.valueOf('％'), Character.valueOf('＆'), Character.valueOf('’'), Character.valueOf('（'), Character.valueOf('）'), 
        Character.valueOf('＊'), Character.valueOf('＋'), Character.valueOf('，'), Character.valueOf('－'), Character.valueOf('．'), Character.valueOf('／'), Character.valueOf('０'), Character.valueOf('１'), Character.valueOf('２'), Character.valueOf('３'), 
        Character.valueOf('４'), Character.valueOf('５'), Character.valueOf('６'), Character.valueOf('７'), 
        Character.valueOf('８'), Character.valueOf('９'), Character.valueOf('：'), Character.valueOf('；'), Character.valueOf('＜'), Character.valueOf('＝'), 
        Character.valueOf('＞'), Character.valueOf('？'), Character.valueOf('＠'), Character.valueOf('〔'), Character.valueOf('＼'), Character.valueOf('〕'), Character.valueOf('＾'), Character.valueOf('＿'), Character.valueOf('‘'), Character.valueOf('｛'), 
        Character.valueOf('｜'), Character.valueOf('｝'), Character.valueOf('∏'), Character.valueOf('Ρ'), Character.valueOf('∑'), 
        Character.valueOf('Υ'), Character.valueOf('Φ'), Character.valueOf('Χ'), Character.valueOf('Ψ'), Character.valueOf('Ω'), 
        Character.valueOf('α'), Character.valueOf('β'), Character.valueOf('γ'), Character.valueOf('δ'), Character.valueOf('ε'), Character.valueOf('ζ'), Character.valueOf('η'), Character.valueOf('θ'), Character.valueOf('ι'), Character.valueOf('κ'), 
        Character.valueOf('λ'), Character.valueOf('μ'), Character.valueOf('ν'), Character.valueOf('ξ'), Character.valueOf('ο'), Character.valueOf('π'), 
        Character.valueOf('ρ'), Character.valueOf('σ'), Character.valueOf('τ'), Character.valueOf('υ'), 
        Character.valueOf('φ'), Character.valueOf('χ'), Character.valueOf('ψ'), Character.valueOf('ω'), Character.valueOf('（'), Character.valueOf('）'), Character.valueOf('〔'), Character.valueOf('〕'), Character.valueOf('＾'), Character.valueOf('﹊'), 
        Character.valueOf('﹍'), Character.valueOf('╭'), Character.valueOf('╮'), Character.valueOf('╰'), Character.valueOf('╯'), Character.valueOf(''), Character.valueOf('_'), 
        Character.valueOf(''), Character.valueOf('^'), Character.valueOf('（'), 
        Character.valueOf('^'), Character.valueOf('：'), Character.valueOf('！'), Character.valueOf('/'), Character.valueOf('\\'), Character.valueOf('"'), Character.valueOf('<'), Character.valueOf('>'), Character.valueOf('`'), Character.valueOf('·'), 
        Character.valueOf('。'), Character.valueOf('{'), Character.valueOf('}'), Character.valueOf('~'), Character.valueOf('～'), Character.valueOf('('), Character.valueOf(')'), Character.valueOf('-'), 
        Character.valueOf('√'), Character.valueOf('$'), 
        Character.valueOf('@'), Character.valueOf('*'), Character.valueOf('&'), Character.valueOf('#'), Character.valueOf('卐'), Character.valueOf('㎎'), Character.valueOf('㎏'), Character.valueOf('㎜'), Character.valueOf('㎝'), Character.valueOf('㎞'), 
        Character.valueOf('㎡'), Character.valueOf('㏄'), Character.valueOf('㏎'), Character.valueOf('㏑'), Character.valueOf('㏒'), Character.valueOf('㏕'), Character.valueOf('+'), Character.valueOf('='), Character.valueOf('?'), 
        Character.valueOf(':'), 
        Character.valueOf('.'), Character.valueOf('!'), Character.valueOf(';'), Character.valueOf(']'), Character.valueOf('|'), Character.valueOf('%') });
  
  public static boolean isStopChar(char ch) {
    return (Character.isWhitespace(ch) || STOP_WORD.contains(Character.valueOf(ch)));
  }
  
  public static boolean isNotStopChar(char ch) {
    return (false == isStopChar(ch));
  }
}
