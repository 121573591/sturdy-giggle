package cn.hutool.core.codec;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Morse {
  private static final Map<Integer, String> ALPHABETS = new HashMap<>();
  
  private static final Map<String, Integer> DICTIONARIES = new HashMap<>();
  
  private final char dit;
  
  private final char dah;
  
  private final char split;
  
  private static void registerMorse(Character abc, String dict) {
    ALPHABETS.put(Integer.valueOf(abc.charValue()), dict);
    DICTIONARIES.put(dict, Integer.valueOf(abc.charValue()));
  }
  
  static {
    registerMorse(Character.valueOf('A'), "01");
    registerMorse(Character.valueOf('B'), "1000");
    registerMorse(Character.valueOf('C'), "1010");
    registerMorse(Character.valueOf('D'), "100");
    registerMorse(Character.valueOf('E'), "0");
    registerMorse(Character.valueOf('F'), "0010");
    registerMorse(Character.valueOf('G'), "110");
    registerMorse(Character.valueOf('H'), "0000");
    registerMorse(Character.valueOf('I'), "00");
    registerMorse(Character.valueOf('J'), "0111");
    registerMorse(Character.valueOf('K'), "101");
    registerMorse(Character.valueOf('L'), "0100");
    registerMorse(Character.valueOf('M'), "11");
    registerMorse(Character.valueOf('N'), "10");
    registerMorse(Character.valueOf('O'), "111");
    registerMorse(Character.valueOf('P'), "0110");
    registerMorse(Character.valueOf('Q'), "1101");
    registerMorse(Character.valueOf('R'), "010");
    registerMorse(Character.valueOf('S'), "000");
    registerMorse(Character.valueOf('T'), "1");
    registerMorse(Character.valueOf('U'), "001");
    registerMorse(Character.valueOf('V'), "0001");
    registerMorse(Character.valueOf('W'), "011");
    registerMorse(Character.valueOf('X'), "1001");
    registerMorse(Character.valueOf('Y'), "1011");
    registerMorse(Character.valueOf('Z'), "1100");
    registerMorse(Character.valueOf('0'), "11111");
    registerMorse(Character.valueOf('1'), "01111");
    registerMorse(Character.valueOf('2'), "00111");
    registerMorse(Character.valueOf('3'), "00011");
    registerMorse(Character.valueOf('4'), "00001");
    registerMorse(Character.valueOf('5'), "00000");
    registerMorse(Character.valueOf('6'), "10000");
    registerMorse(Character.valueOf('7'), "11000");
    registerMorse(Character.valueOf('8'), "11100");
    registerMorse(Character.valueOf('9'), "11110");
    registerMorse(Character.valueOf('.'), "010101");
    registerMorse(Character.valueOf(','), "110011");
    registerMorse(Character.valueOf('?'), "001100");
    registerMorse(Character.valueOf('\''), "011110");
    registerMorse(Character.valueOf('!'), "101011");
    registerMorse(Character.valueOf('/'), "10010");
    registerMorse(Character.valueOf('('), "10110");
    registerMorse(Character.valueOf(')'), "101101");
    registerMorse(Character.valueOf('&'), "01000");
    registerMorse(Character.valueOf(':'), "111000");
    registerMorse(Character.valueOf(';'), "101010");
    registerMorse(Character.valueOf('='), "10001");
    registerMorse(Character.valueOf('+'), "01010");
    registerMorse(Character.valueOf('-'), "100001");
    registerMorse(Character.valueOf('_'), "001101");
    registerMorse(Character.valueOf('"'), "010010");
    registerMorse(Character.valueOf('$'), "0001001");
    registerMorse(Character.valueOf('@'), "011010");
  }
  
  public Morse() {
    this('.', '-', '/');
  }
  
  public Morse(char dit, char dah, char split) {
    this.dit = dit;
    this.dah = dah;
    this.split = split;
  }
  
  public String encode(String text) {
    Assert.notNull(text, "Text should not be null.", new Object[0]);
    text = text.toUpperCase();
    StringBuilder morseBuilder = new StringBuilder();
    int len = text.codePointCount(0, text.length());
    for (int i = 0; i < len; i++) {
      int codePoint = text.codePointAt(i);
      String word = ALPHABETS.get(Integer.valueOf(codePoint));
      if (word == null)
        word = Integer.toBinaryString(codePoint); 
      morseBuilder.append(word.replace('0', this.dit).replace('1', this.dah)).append(this.split);
    } 
    return morseBuilder.toString();
  }
  
  public String decode(String morse) {
    Assert.notNull(morse, "Morse should not be null.", new Object[0]);
    char dit = this.dit;
    char dah = this.dah;
    char split = this.split;
    if (false == StrUtil.containsOnly(morse, new char[] { dit, dah, split }))
      throw new IllegalArgumentException("Incorrect morse."); 
    List<String> words = StrUtil.split(morse, split);
    StringBuilder textBuilder = new StringBuilder();
    for (String word : words) {
      if (StrUtil.isEmpty(word))
        continue; 
      word = word.replace(dit, '0').replace(dah, '1');
      Integer codePoint = DICTIONARIES.get(word);
      if (codePoint == null)
        codePoint = Integer.valueOf(word, 2); 
      textBuilder.appendCodePoint(codePoint.intValue());
    } 
    return textBuilder.toString();
  }
}
