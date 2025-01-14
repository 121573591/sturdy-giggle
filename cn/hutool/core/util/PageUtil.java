package cn.hutool.core.util;

import cn.hutool.core.lang.DefaultSegment;
import cn.hutool.core.lang.Segment;

public class PageUtil {
  private static int firstPageNo = 0;
  
  public static int getFirstPageNo() {
    return firstPageNo;
  }
  
  public static synchronized void setFirstPageNo(int customFirstPageNo) {
    firstPageNo = customFirstPageNo;
  }
  
  public static void setOneAsFirstPageNo() {
    setFirstPageNo(1);
  }
  
  public static int getStart(int pageNo, int pageSize) {
    if (pageNo < firstPageNo)
      pageNo = firstPageNo; 
    if (pageSize < 1)
      pageSize = 0; 
    return (pageNo - firstPageNo) * pageSize;
  }
  
  public static int getEnd(int pageNo, int pageSize) {
    int start = getStart(pageNo, pageSize);
    return getEndByStart(start, pageSize);
  }
  
  public static int[] transToStartEnd(int pageNo, int pageSize) {
    int start = getStart(pageNo, pageSize);
    return new int[] { start, getEndByStart(start, pageSize) };
  }
  
  public static Segment<Integer> toSegment(int pageNo, int pageSize) {
    int[] startEnd = transToStartEnd(pageNo, pageSize);
    return (Segment<Integer>)new DefaultSegment(Integer.valueOf(startEnd[0]), Integer.valueOf(startEnd[1]));
  }
  
  public static int totalPage(int totalCount, int pageSize) {
    return totalPage(totalCount, pageSize);
  }
  
  public static int totalPage(long totalCount, int pageSize) {
    if (pageSize == 0)
      return 0; 
    return Math.toIntExact((totalCount % pageSize == 0L) ? (totalCount / pageSize) : (totalCount / pageSize + 1L));
  }
  
  public static int[] rainbow(int pageNo, int totalPage, int displayCount) {
    boolean isEven = ((displayCount & 0x1) == 0);
    int left = displayCount >> 1;
    int right = displayCount >> 1;
    int length = displayCount;
    if (isEven)
      right++; 
    if (totalPage < displayCount)
      length = totalPage; 
    int[] result = new int[length];
    if (totalPage >= displayCount) {
      if (pageNo <= left) {
        for (int i = 0; i < result.length; i++)
          result[i] = i + 1; 
      } else if (pageNo > totalPage - right) {
        for (int i = 0; i < result.length; i++)
          result[i] = i + totalPage - displayCount + 1; 
      } else {
        for (int i = 0; i < result.length; i++)
          result[i] = i + pageNo - left + (isEven ? 1 : 0); 
      } 
    } else {
      for (int i = 0; i < result.length; i++)
        result[i] = i + 1; 
    } 
    return result;
  }
  
  public static int[] rainbow(int currentPage, int pageCount) {
    return rainbow(currentPage, pageCount, 10);
  }
  
  private static int getEndByStart(int start, int pageSize) {
    if (pageSize < 1)
      pageSize = 0; 
    return start + pageSize;
  }
}
