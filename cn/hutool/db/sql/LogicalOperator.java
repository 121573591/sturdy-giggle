package cn.hutool.db.sql;

import cn.hutool.core.util.StrUtil;

public enum LogicalOperator {
  AND, OR;
  
  public boolean isSame(String logicalOperatorStr) {
    if (StrUtil.isBlank(logicalOperatorStr))
      return false; 
    return name().equalsIgnoreCase(logicalOperatorStr.trim());
  }
}
