package cn.hutool.extra.ftp;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.lang.Filter;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPClientConfig;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

public class Ftp extends AbstractFtp {
  public static final int DEFAULT_PORT = 21;
  
  private FTPClient client;
  
  private FtpMode mode;
  
  private boolean backToPwd;
  
  public Ftp(String host) {
    this(host, 21);
  }
  
  public Ftp(String host, int port) {
    this(host, port, "anonymous", "");
  }
  
  public Ftp(String host, int port, String user, String password) {
    this(host, port, user, password, CharsetUtil.CHARSET_UTF_8);
  }
  
  public Ftp(String host, int port, String user, String password, Charset charset) {
    this(host, port, user, password, charset, (String)null, (String)null);
  }
  
  public Ftp(String host, int port, String user, String password, Charset charset, String serverLanguageCode, String systemKey) {
    this(host, port, user, password, charset, serverLanguageCode, systemKey, (FtpMode)null);
  }
  
  public Ftp(String host, int port, String user, String password, Charset charset, String serverLanguageCode, String systemKey, FtpMode mode) {
    this(new FtpConfig(host, port, user, password, charset, serverLanguageCode, systemKey), mode);
  }
  
  public Ftp(FtpConfig config, FtpMode mode) {
    super(config);
    this.mode = mode;
    init();
  }
  
  public Ftp(FTPClient client) {
    super(FtpConfig.create());
    this.client = client;
  }
  
  public Ftp init() {
    return init(this.ftpConfig, this.mode);
  }
  
  public Ftp init(String host, int port, String user, String password) {
    return init(host, port, user, password, (FtpMode)null);
  }
  
  public Ftp init(String host, int port, String user, String password, FtpMode mode) {
    return init(new FtpConfig(host, port, user, password, this.ftpConfig.getCharset(), null, null), mode);
  }
  
  public Ftp init(FtpConfig config, FtpMode mode) {
    FTPClient client = new FTPClient();
    client.setRemoteVerificationEnabled(false);
    Charset charset = config.getCharset();
    if (null != charset)
      client.setControlEncoding(charset.toString()); 
    client.setConnectTimeout((int)config.getConnectionTimeout());
    String systemKey = config.getSystemKey();
    if (StrUtil.isNotBlank(systemKey)) {
      FTPClientConfig conf = new FTPClientConfig(systemKey);
      String serverLanguageCode = config.getServerLanguageCode();
      if (StrUtil.isNotBlank(serverLanguageCode))
        conf.setServerLanguageCode(config.getServerLanguageCode()); 
      client.configure(conf);
    } 
    try {
      client.connect(config.getHost(), config.getPort());
      client.setSoTimeout((int)config.getSoTimeout());
      client.login(config.getUser(), config.getPassword());
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
    int replyCode = client.getReplyCode();
    if (false == FTPReply.isPositiveCompletion(replyCode)) {
      try {
        client.disconnect();
      } catch (IOException iOException) {}
      throw new FtpException("Login failed for user [{}], reply code is: [{}]", new Object[] { config.getUser(), Integer.valueOf(replyCode) });
    } 
    this.client = client;
    if (mode != null)
      setMode(mode); 
    return this;
  }
  
  public Ftp setMode(FtpMode mode) {
    this.mode = mode;
    switch (mode) {
      case Active:
        this.client.enterLocalActiveMode();
        break;
      case Passive:
        this.client.enterLocalPassiveMode();
        break;
    } 
    return this;
  }
  
  public Ftp setBackToPwd(boolean backToPwd) {
    this.backToPwd = backToPwd;
    return this;
  }
  
  public boolean isBackToPwd() {
    return this.backToPwd;
  }
  
  public Ftp reconnectIfTimeout() {
    String pwd = null;
    try {
      pwd = pwd();
    } catch (IORuntimeException iORuntimeException) {}
    if (pwd == null)
      return init(); 
    return this;
  }
  
  public synchronized boolean cd(String directory) {
    if (StrUtil.isBlank(directory))
      return true; 
    try {
      return this.client.changeWorkingDirectory(directory);
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
  }
  
  public String pwd() {
    try {
      return this.client.printWorkingDirectory();
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
  }
  
  public List<String> ls(String path) {
    return ArrayUtil.map((Object[])lsFiles(path), FTPFile::getName);
  }
  
  public List<FTPFile> lsFiles(String path, Filter<FTPFile> filter) {
    FTPFile[] ftpFiles = lsFiles(path);
    if (ArrayUtil.isEmpty((Object[])ftpFiles))
      return ListUtil.empty(); 
    List<FTPFile> result = new ArrayList<>((ftpFiles.length - 2 <= 0) ? ftpFiles.length : (ftpFiles.length - 2));
    for (FTPFile ftpFile : ftpFiles) {
      String fileName = ftpFile.getName();
      if (false == StrUtil.equals(".", fileName) && false == StrUtil.equals("..", fileName) && (
        null == filter || filter.accept(ftpFile)))
        result.add(ftpFile); 
    } 
    return result;
  }
  
  public FTPFile[] lsFiles(String path) throws FtpException, IORuntimeException {
    FTPFile[] ftpFiles;
    String pwd = null;
    if (StrUtil.isNotBlank(path)) {
      pwd = pwd();
      if (false == cd(path))
        throw new FtpException("Change dir to [{}] error, maybe path not exist!", new Object[] { path }); 
    } 
    try {
      ftpFiles = this.client.listFiles();
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } finally {
      cd(pwd);
    } 
    return ftpFiles;
  }
  
  public boolean mkdir(String dir) throws IORuntimeException {
    try {
      return this.client.makeDirectory(dir);
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
  }
  
  public int stat(String path) throws IORuntimeException {
    try {
      return this.client.stat(path);
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
  }
  
  public boolean existFile(String path) throws IORuntimeException {
    FTPFile[] ftpFileArr;
    try {
      ftpFileArr = this.client.listFiles(path);
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
    return ArrayUtil.isNotEmpty((Object[])ftpFileArr);
  }
  
  public boolean delFile(String path) throws IORuntimeException {
    boolean isSuccess;
    String pwd = pwd();
    String fileName = FileUtil.getName(path);
    String dir = StrUtil.removeSuffix(path, fileName);
    if (false == cd(dir))
      throw new FtpException("Change dir to [{}] error, maybe dir not exist!", new Object[] { path }); 
    try {
      isSuccess = this.client.deleteFile(fileName);
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } finally {
      cd(pwd);
    } 
    return isSuccess;
  }
  
  public boolean delDir(String dirPath) throws IORuntimeException {
    FTPFile[] dirs;
    try {
      dirs = this.client.listFiles(dirPath);
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
    for (FTPFile ftpFile : dirs) {
      String name = ftpFile.getName();
      String childPath = StrUtil.format("{}/{}", new Object[] { dirPath, name });
      if (ftpFile.isDirectory()) {
        if (false == ".".equals(name) && false == "..".equals(name))
          delDir(childPath); 
      } else {
        delFile(childPath);
      } 
    } 
    try {
      return this.client.removeDirectory(dirPath);
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
  }
  
  public boolean upload(String destPath, File file) {
    Assert.notNull(file, "file to upload is null !", new Object[0]);
    return upload(destPath, file.getName(), file);
  }
  
  public boolean upload(String destPath, String fileName, File file) throws IORuntimeException {
    try (InputStream in = FileUtil.getInputStream(file)) {
      return upload(destPath, fileName, in);
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
  }
  
  public boolean upload(String destPath, String fileName, InputStream fileStream) throws IORuntimeException {
    try {
      this.client.setFileType(2);
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
    String pwd = null;
    if (this.backToPwd)
      pwd = pwd(); 
    if (StrUtil.isNotBlank(destPath)) {
      mkDirs(destPath);
      if (false == cd(destPath))
        throw new FtpException("Change dir to [{}] error, maybe dir not exist!", new Object[] { destPath }); 
    } 
    try {
      return this.client.storeFile(fileName, fileStream);
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } finally {
      if (this.backToPwd)
        cd(pwd); 
    } 
  }
  
  public void uploadFileOrDirectory(String remotePath, File uploadFile) {
    if (false == FileUtil.isDirectory(uploadFile)) {
      upload(remotePath, uploadFile);
      return;
    } 
    File[] files = uploadFile.listFiles();
    if (ArrayUtil.isEmpty((Object[])files))
      return; 
    List<File> dirs = new ArrayList<>(files.length);
    for (File f : files) {
      if (f.isDirectory()) {
        dirs.add(f);
      } else {
        upload(remotePath, f);
      } 
    } 
    for (File f : dirs) {
      String dir = FileUtil.normalize(remotePath + "/" + f.getName());
      uploadFileOrDirectory(dir, f);
    } 
  }
  
  public void download(String path, File outFile) {
    String fileName = FileUtil.getName(path);
    String dir = StrUtil.removeSuffix(path, fileName);
    download(dir, fileName, outFile);
  }
  
  public void recursiveDownloadFolder(String sourcePath, File destDir) {
    for (FTPFile ftpFile : lsFiles(sourcePath, (Filter<FTPFile>)null)) {
      String fileName = ftpFile.getName();
      String srcFile = StrUtil.format("{}/{}", new Object[] { sourcePath, fileName });
      File destFile = FileUtil.file(destDir, fileName);
      if (false == ftpFile.isDirectory()) {
        if (false == FileUtil.exist(destFile) || ftpFile
          .getTimestamp().getTimeInMillis() > destFile.lastModified())
          download(srcFile, destFile); 
        continue;
      } 
      FileUtil.mkdir(destFile);
      recursiveDownloadFolder(srcFile, destFile);
    } 
  }
  
  public void download(String path, String fileName, File outFile) throws IORuntimeException {
    if (outFile.isDirectory())
      outFile = new File(outFile, fileName); 
    if (false == outFile.exists())
      FileUtil.touch(outFile); 
    try (OutputStream out = FileUtil.getOutputStream(outFile)) {
      download(path, fileName, out);
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } 
  }
  
  public void download(String path, String fileName, OutputStream out) {
    download(path, fileName, out, (Charset)null);
  }
  
  public void download(String path, String fileName, OutputStream out, Charset fileNameCharset) throws IORuntimeException {
    String pwd = null;
    if (this.backToPwd)
      pwd = pwd(); 
    if (false == cd(path))
      throw new FtpException("Change dir to [{}] error, maybe dir not exist!", new Object[] { path }); 
    if (null != fileNameCharset)
      fileName = new String(fileName.getBytes(fileNameCharset), StandardCharsets.ISO_8859_1); 
    try {
      this.client.setFileType(2);
      this.client.retrieveFile(fileName, out);
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } finally {
      if (this.backToPwd)
        cd(pwd); 
    } 
  }
  
  public FTPClient getClient() {
    return this.client;
  }
  
  public void close() throws IOException {
    if (null != this.client) {
      this.client.logout();
      if (this.client.isConnected())
        this.client.disconnect(); 
      this.client = null;
    } 
  }
}
