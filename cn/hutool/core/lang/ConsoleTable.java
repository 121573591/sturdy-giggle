package cn.hutool.core.lang;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConsoleTable {
  private static final char ROW_LINE = '－';
  
  private static final char COLUMN_LINE = '|';
  
  private static final char CORNER = '+';
  
  private static final char SPACE = '　';
  
  private static final char LF = '\n';
  
  private boolean isSBCMode = true;
  
  public static ConsoleTable create() {
    return new ConsoleTable();
  }
  
  private final List<List<String>> headerList = new ArrayList<>();
  
  private final List<List<String>> bodyList = new ArrayList<>();
  
  private List<Integer> columnCharNumber;
  
  public ConsoleTable setSBCMode(boolean isSBCMode) {
    this.isSBCMode = isSBCMode;
    return this;
  }
  
  public ConsoleTable addHeader(String... titles) {
    if (this.columnCharNumber == null)
      this.columnCharNumber = new ArrayList<>(Collections.nCopies(titles.length, Integer.valueOf(0))); 
    List<String> l = new ArrayList<>();
    fillColumns(l, titles);
    this.headerList.add(l);
    return this;
  }
  
  public ConsoleTable addBody(String... values) {
    List<String> l = new ArrayList<>();
    this.bodyList.add(l);
    fillColumns(l, values);
    return this;
  }
  
  private void fillColumns(List<String> l, String[] columns) {
    for (int i = 0; i < columns.length; i++) {
      String column = StrUtil.toString(columns[i]);
      if (this.isSBCMode)
        column = Convert.toSBC(column); 
      l.add(column);
      int width = column.length();
      if (width > ((Integer)this.columnCharNumber.get(i)).intValue())
        this.columnCharNumber.set(i, Integer.valueOf(width)); 
    } 
  }
  
  public String toString() {
    StringBuilder sb = new StringBuilder();
    fillBorder(sb);
    fillRows(sb, this.headerList);
    fillBorder(sb);
    fillRows(sb, this.bodyList);
    fillBorder(sb);
    return sb.toString();
  }
  
  private void fillRows(StringBuilder sb, List<List<String>> list) {
    for (List<String> row : list) {
      sb.append('|');
      fillRow(sb, row);
      sb.append('\n');
    } 
  }
  
  private void fillRow(StringBuilder sb, List<String> row) {
    int size = row.size();
    for (int i = 0; i < size; i++) {
      String value = row.get(i);
      sb.append('　');
      sb.append(value);
      int length = value.length();
      int sbcCount = sbcCount(value);
      if (sbcCount % 2 == 1)
        sb.append(' '); 
      sb.append('　');
      int maxLength = ((Integer)this.columnCharNumber.get(i)).intValue();
      for (int j = 0; j < maxLength - length + sbcCount / 2; j++)
        sb.append('　'); 
      sb.append('|');
    } 
  }
  
  private void fillBorder(StringBuilder sb) {
    sb.append('+');
    for (Integer width : this.columnCharNumber) {
      sb.append(StrUtil.repeat('－', width.intValue() + 2));
      sb.append('+');
    } 
    sb.append('\n');
  }
  
  public void print() {
    Console.print(toString());
  }
  
  private int sbcCount(String value) {
    int count = 0;
    for (int i = 0; i < value.length(); i++) {
      if (value.charAt(i) < '')
        count++; 
    } 
    return count;
  }
}
