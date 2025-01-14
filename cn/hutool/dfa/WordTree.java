package cn.hutool.dfa;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Filter;
import cn.hutool.core.util.StrUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordTree extends HashMap<Character, WordTree> {
  private static final long serialVersionUID = -4646423269465809276L;
  
  private final Set<Character> endCharacterSet = new HashSet<>();
  
  private Filter<Character> charFilter = StopChar::isNotStopChar;
  
  public WordTree setCharFilter(Filter<Character> charFilter) {
    this.charFilter = charFilter;
    return this;
  }
  
  public WordTree addWords(Collection<String> words) {
    if (false == words instanceof Set)
      words = new HashSet<>(words); 
    for (String word : words)
      addWord(word); 
    return this;
  }
  
  public WordTree addWords(String... words) {
    for (String word : CollUtil.newHashSet((Object[])words))
      addWord(word); 
    return this;
  }
  
  public WordTree addWord(String word) {
    Filter<Character> charFilter = this.charFilter;
    WordTree parent = null;
    WordTree current = this;
    char currentChar = Character.MIN_VALUE;
    int length = word.length();
    for (int i = 0; i < length; i++) {
      currentChar = word.charAt(i);
      if (charFilter.accept(Character.valueOf(currentChar))) {
        WordTree child = current.get(Character.valueOf(currentChar));
        if (child == null) {
          child = new WordTree();
          current.put(Character.valueOf(currentChar), child);
        } 
        parent = current;
        current = child;
      } 
    } 
    if (null != parent)
      parent.setEnd(Character.valueOf(currentChar)); 
    return this;
  }
  
  public boolean isMatch(String text) {
    if (null == text)
      return false; 
    return (null != matchWord(text));
  }
  
  public String match(String text) {
    FoundWord foundWord = matchWord(text);
    return (null != foundWord) ? foundWord.toString() : null;
  }
  
  public FoundWord matchWord(String text) {
    if (null == text)
      return null; 
    List<FoundWord> matchAll = matchAllWords(text, 1);
    return (FoundWord)CollUtil.get(matchAll, 0);
  }
  
  public List<String> matchAll(String text) {
    return matchAll(text, -1);
  }
  
  public List<FoundWord> matchAllWords(String text) {
    return matchAllWords(text, -1);
  }
  
  public List<String> matchAll(String text, int limit) {
    return matchAll(text, limit, false, false);
  }
  
  public List<FoundWord> matchAllWords(String text, int limit) {
    return matchAllWords(text, limit, false, false);
  }
  
  public List<String> matchAll(String text, int limit, boolean isDensityMatch, boolean isGreedMatch) {
    List<FoundWord> matchAllWords = matchAllWords(text, limit, isDensityMatch, isGreedMatch);
    return CollUtil.map(matchAllWords, FoundWord::toString, true);
  }
  
  public List<FoundWord> matchAllWords(String text, int limit, boolean isDensityMatch, boolean isGreedMatch) {
    if (null == text)
      return null; 
    List<FoundWord> foundWords = new ArrayList<>();
    WordTree current = this;
    int length = text.length();
    Filter<Character> charFilter = this.charFilter;
    StringBuilder wordBuffer = StrUtil.builder();
    StringBuilder keyBuffer = StrUtil.builder();
    for (int i = 0; i < length; i++) {
      wordBuffer.setLength(0);
      keyBuffer.setLength(0);
      for (int j = i; j < length; j++) {
        char currentChar = text.charAt(j);
        if (false == charFilter.accept(Character.valueOf(currentChar))) {
          if (wordBuffer.length() > 0) {
            wordBuffer.append(currentChar);
          } else {
            i++;
          } 
        } else {
          if (false == current.containsKey(Character.valueOf(currentChar)))
            break; 
          wordBuffer.append(currentChar);
          keyBuffer.append(currentChar);
          if (current.isEnd(Character.valueOf(currentChar))) {
            foundWords.add(new FoundWord(keyBuffer.toString(), wordBuffer.toString(), i, j));
            if (limit > 0 && foundWords.size() >= limit)
              return foundWords; 
            if (false == isDensityMatch) {
              i = j;
              break;
            } 
            if (false == isGreedMatch)
              break; 
          } 
          current = current.get(Character.valueOf(currentChar));
          if (null == current)
            break; 
        } 
      } 
      current = this;
    } 
    return foundWords;
  }
  
  private boolean isEnd(Character c) {
    return this.endCharacterSet.contains(c);
  }
  
  private void setEnd(Character c) {
    if (null != c)
      this.endCharacterSet.add(c); 
  }
  
  public void clear() {
    super.clear();
    this.endCharacterSet.clear();
  }
}
