package cn.hutool.core.comparator;

import cn.hutool.core.lang.Version;
import cn.hutool.core.util.ObjectUtil;
import java.io.Serializable;
import java.util.Comparator;

public class VersionComparator implements Comparator<String>, Serializable {
  private static final long serialVersionUID = 8083701245147495562L;
  
  public static final VersionComparator INSTANCE = new VersionComparator();
  
  public int compare(String version1, String version2) {
    if (ObjectUtil.equal(version1, version2))
      return 0; 
    if (version1 == null && version2 == null)
      return 0; 
    if (version1 == null)
      return -1; 
    if (version2 == null)
      return 1; 
    return CompareUtil.compare(Version.of(version1), Version.of(version2));
  }
}
