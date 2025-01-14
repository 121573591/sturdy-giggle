package cn.hutool.core.math;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Combination implements Serializable {
  private static final long serialVersionUID = 1L;
  
  private final String[] datas;
  
  public Combination(String[] datas) {
    this.datas = datas;
  }
  
  public static long count(int n, int m) {
    if (0 == m || n == m)
      return 1L; 
    return (n > m) ? (NumberUtil.factorial(n, (n - m)) / NumberUtil.factorial(m)) : 0L;
  }
  
  public static long countAll(int n) {
    if (n < 0 || n > 63)
      throw new IllegalArgumentException(StrUtil.format("countAll must have n >= 0 and n <= 63, but got n={}", new Object[] { Integer.valueOf(n) })); 
    return (n == 63) ? Long.MAX_VALUE : ((1L << n) - 1L);
  }
  
  public List<String[]> select(int m) {
    List<String[]> result = (List)new ArrayList<>((int)count(this.datas.length, m));
    select(0, new String[m], 0, result);
    return result;
  }
  
  public List<String[]> selectAll() {
    List<String[]> result = (List)new ArrayList<>((int)countAll(this.datas.length));
    for (int i = 1; i <= this.datas.length; i++)
      result.addAll(select(i)); 
    return result;
  }
  
  private void select(int dataIndex, String[] resultList, int resultIndex, List<String[]> result) {
    int resultLen = resultList.length;
    int resultCount = resultIndex + 1;
    if (resultCount > resultLen) {
      result.add(Arrays.copyOf(resultList, resultList.length));
      return;
    } 
    for (int i = dataIndex; i < this.datas.length + resultCount - resultLen; i++) {
      resultList[resultIndex] = this.datas[i];
      select(i + 1, resultList, resultIndex + 1, result);
    } 
  }
}
