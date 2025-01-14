package cn.hutool.extra.ftp;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import java.io.Closeable;
import java.io.File;
import java.nio.charset.Charset;
import java.util.List;

public abstract class AbstractFtp implements Closeable {
  public static final Charset DEFAULT_CHARSET = CharsetUtil.CHARSET_UTF_8;
  
  protected FtpConfig ftpConfig;
  
  protected AbstractFtp(FtpConfig config) {
    this.ftpConfig = config;
  }
  
  public abstract AbstractFtp reconnectIfTimeout();
  
  public abstract boolean cd(String paramString);
  
  public boolean toParent() {
    return cd("..");
  }
  
  public abstract String pwd();
  
  public boolean isDir(String dir) {
    String workDir = pwd();
    try {
      return cd(dir);
    } finally {
      cd(workDir);
    } 
  }
  
  public abstract boolean mkdir(String paramString);
  
  public boolean exist(String path) {
    List<String> names;
    if (StrUtil.isBlank(path))
      return false; 
    if (isDir(path))
      return true; 
    if (CharUtil.isFileSeparator(path.charAt(path.length() - 1)))
      return false; 
    String fileName = FileUtil.getName(path);
    if (".".equals(fileName) || "..".equals(fileName))
      return false; 
    String dir = StrUtil.emptyToDefault(StrUtil.removeSuffix(path, fileName), ".");
    if (false == isDir(dir))
      return false; 
    try {
      names = ls(dir);
    } catch (FtpException ignore) {
      return false;
    } 
    return containsIgnoreCase(names, fileName);
  }
  
  public abstract List<String> ls(String paramString);
  
  public abstract boolean delFile(String paramString);
  
  public abstract boolean delDir(String paramString);
  
  public void mkDirs(String dir) {
    String[] dirs = StrUtil.trim(dir).split("[\\\\/]+");
    String now = pwd();
    if (dirs.length > 0 && StrUtil.isEmpty(dirs[0]))
      cd("/"); 
    for (String s : dirs) {
      if (StrUtil.isNotEmpty(s)) {
        boolean exist = true;
        try {
          if (false == cd(s))
            exist = false; 
        } catch (FtpException e) {
          exist = false;
        } 
        if (false == exist) {
          mkdir(s);
          cd(s);
        } 
      } 
    } 
    cd(now);
  }
  
  public abstract boolean upload(String paramString, File paramFile);
  
  public abstract void download(String paramString, File paramFile);
  
  public void download(String path, File outFile, String tempFileSuffix) {
    if (StrUtil.isBlank(tempFileSuffix)) {
      tempFileSuffix = ".temp";
    } else {
      tempFileSuffix = StrUtil.addPrefixIfNot(tempFileSuffix, ".");
    } 
    String fileName = outFile.isDirectory() ? FileUtil.getName(path) : outFile.getName();
    String tempFileName = fileName + tempFileSuffix;
    outFile = new File(outFile.isDirectory() ? outFile : outFile.getParentFile(), tempFileName);
    try {
      download(path, outFile);
      FileUtil.rename(outFile, fileName, true);
    } catch (Throwable e) {
      FileUtil.del(outFile);
      throw new FtpException(e);
    } 
  }
  
  public abstract void recursiveDownloadFolder(String paramString, File paramFile);
  
  private static boolean containsIgnoreCase(List<String> names, String nameToFind) {
    if (CollUtil.isEmpty(names))
      return false; 
    if (StrUtil.isEmpty(nameToFind))
      return false; 
    for (String name : names) {
      if (nameToFind.equalsIgnoreCase(name))
        return true; 
    } 
    return false;
  }
}
