package cn.hutool.core.lang;

import cn.hutool.core.exceptions.UtilException;
import cn.hutool.core.util.StrUtil;
import java.lang.management.ManagementFactory;

public enum Pid {
  INSTANCE;
  
  private final int pid;
  
  Pid() {
    this.pid = getPid();
  }
  
  public int get() {
    return this.pid;
  }
  
  private static int getPid() throws UtilException {
    String processName = ManagementFactory.getRuntimeMXBean().getName();
    if (StrUtil.isBlank(processName))
      throw new UtilException("Process name is blank!"); 
    int atIndex = processName.indexOf('@');
    if (atIndex > 0)
      return Integer.parseInt(processName.substring(0, atIndex)); 
    return processName.hashCode();
  }
}
