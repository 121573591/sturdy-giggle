package cn.hutool.extra.ssh;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.ftp.AbstractFtp;
import cn.hutool.extra.ftp.FtpConfig;
import cn.hutool.extra.ftp.FtpException;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;
import net.schmizz.sshj.SSHClient;
import net.schmizz.sshj.connection.channel.direct.Session;
import net.schmizz.sshj.sftp.RemoteResourceInfo;
import net.schmizz.sshj.sftp.SFTPClient;
import net.schmizz.sshj.transport.verification.HostKeyVerifier;
import net.schmizz.sshj.transport.verification.PromiscuousVerifier;
import net.schmizz.sshj.xfer.FileSystemFile;
import net.schmizz.sshj.xfer.LocalDestFile;
import net.schmizz.sshj.xfer.LocalSourceFile;

public class SshjSftp extends AbstractFtp {
  private SSHClient ssh;
  
  private SFTPClient sftp;
  
  private Session session;
  
  public SshjSftp(String sshHost) {
    this(new FtpConfig(sshHost, 22, null, null, DEFAULT_CHARSET));
  }
  
  public SshjSftp(String sshHost, String sshUser, String sshPass) {
    this(new FtpConfig(sshHost, 22, sshUser, sshPass, CharsetUtil.CHARSET_UTF_8));
  }
  
  public SshjSftp(String sshHost, int sshPort, String sshUser, String sshPass) {
    this(new FtpConfig(sshHost, sshPort, sshUser, sshPass, CharsetUtil.CHARSET_UTF_8));
  }
  
  public SshjSftp(String sshHost, int sshPort, String sshUser, String sshPass, Charset charset) {
    this(new FtpConfig(sshHost, sshPort, sshUser, sshPass, charset));
  }
  
  protected SshjSftp(FtpConfig config) {
    super(config);
    init();
  }
  
  public void init() {
    this.ssh = new SSHClient();
    this.ssh.addHostKeyVerifier((HostKeyVerifier)new PromiscuousVerifier());
    try {
      this.ssh.connect(this.ftpConfig.getHost(), this.ftpConfig.getPort());
      this.ssh.authPassword(this.ftpConfig.getUser(), this.ftpConfig.getPassword());
      this.ssh.setRemoteCharset(this.ftpConfig.getCharset());
      this.sftp = this.ssh.newSFTPClient();
    } catch (IOException e) {
      throw new FtpException("sftp 初始化失败.", e);
    } 
  }
  
  public AbstractFtp reconnectIfTimeout() {
    if (StrUtil.isBlank(this.ftpConfig.getHost()))
      throw new FtpException("Host is blank!"); 
    try {
      cd("/");
    } catch (FtpException e) {
      close();
      init();
    } 
    return this;
  }
  
  public boolean cd(String directory) {
    String exec = String.format("cd %s", new Object[] { directory });
    command(exec);
    String pwd = pwd();
    return pwd.equals(directory);
  }
  
  public String pwd() {
    return command("pwd");
  }
  
  public boolean mkdir(String dir) {
    try {
      this.sftp.mkdir(dir);
    } catch (IOException e) {
      throw new FtpException(e);
    } 
    return containsFile(dir);
  }
  
  public List<String> ls(String path) {
    List<RemoteResourceInfo> infoList;
    try {
      infoList = this.sftp.ls(path);
    } catch (IOException e) {
      throw new FtpException(e);
    } 
    if (CollUtil.isNotEmpty(infoList))
      return CollUtil.map(infoList, RemoteResourceInfo::getName, true); 
    return null;
  }
  
  public boolean delFile(String path) {
    try {
      this.sftp.rm(path);
      return !containsFile(path);
    } catch (IOException e) {
      throw new FtpException(e);
    } 
  }
  
  public boolean delDir(String dirPath) {
    try {
      this.sftp.rmdir(dirPath);
      return !containsFile(dirPath);
    } catch (IOException e) {
      throw new FtpException(e);
    } 
  }
  
  public boolean upload(String destPath, File file) {
    try {
      this.sftp.put((LocalSourceFile)new FileSystemFile(file), destPath);
      return containsFile(destPath);
    } catch (IOException e) {
      throw new FtpException(e);
    } 
  }
  
  public void download(String path, File outFile) {
    try {
      this.sftp.get(path, (LocalDestFile)new FileSystemFile(outFile));
    } catch (IOException e) {
      throw new FtpException(e);
    } 
  }
  
  public void recursiveDownloadFolder(String sourcePath, File destDir) {
    List<String> files = ls(sourcePath);
    if (files != null && !files.isEmpty())
      files.forEach(path -> download(sourcePath + "/" + path, destDir)); 
  }
  
  public void close() {
    IoUtil.close((Closeable)this.session);
    IoUtil.close((Closeable)this.sftp);
    IoUtil.close((Closeable)this.ssh);
  }
  
  public boolean containsFile(String fileDir) {
    try {
      this.sftp.lstat(fileDir);
      return true;
    } catch (IOException e) {
      return false;
    } 
  }
  
  public String command(String exec) {
    Session session = initSession();
    Session.Command command = null;
    try {
      command = session.exec(exec);
      InputStream inputStream = command.getInputStream();
      return IoUtil.read(inputStream, this.ftpConfig.getCharset());
    } catch (Exception e) {
      throw new FtpException(e);
    } finally {
      IoUtil.close((Closeable)command);
    } 
  }
  
  private Session initSession() {
    Session session = this.session;
    if (null == session || !session.isOpen()) {
      IoUtil.close((Closeable)session);
      try {
        session = this.ssh.startSession();
      } catch (Exception e) {
        throw new FtpException(e);
      } 
      this.session = session;
    } 
    return session;
  }
}
