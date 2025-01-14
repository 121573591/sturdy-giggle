package cn.hutool.extra.ssh;

import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.net.LocalPortGenerater;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

public class JschUtil {
  public static final String SSH_NONE = "none";
  
  private static final LocalPortGenerater portGenerater = new LocalPortGenerater(10000);
  
  public static int generateLocalPort() {
    return portGenerater.generate();
  }
  
  public static Session getSession(String sshHost, int sshPort, String sshUser, String sshPass) {
    return JschSessionPool.INSTANCE.getSession(sshHost, sshPort, sshUser, sshPass);
  }
  
  public static Session getSession(String sshHost, int sshPort, String sshUser, String privateKeyPath, byte[] passphrase) {
    return JschSessionPool.INSTANCE.getSession(sshHost, sshPort, sshUser, privateKeyPath, passphrase);
  }
  
  public static Session getSession(String sshHost, int sshPort, String sshUser, byte[] privateKey, byte[] passphrase) {
    return JschSessionPool.INSTANCE.getSession(sshHost, sshPort, sshUser, privateKey, passphrase);
  }
  
  public static Session openSession(String sshHost, int sshPort, String sshUser, String sshPass) {
    return openSession(sshHost, sshPort, sshUser, sshPass, 0);
  }
  
  public static Session openSession(String sshHost, int sshPort, String sshUser, String sshPass, int timeout) {
    Session session = createSession(sshHost, sshPort, sshUser, sshPass);
    try {
      session.connect(timeout);
    } catch (JSchException e) {
      throw new JschRuntimeException(e);
    } 
    return session;
  }
  
  public static Session openSession(String sshHost, int sshPort, String sshUser, String privateKeyPath, byte[] passphrase) {
    return openSession(sshHost, sshPort, sshUser, privateKeyPath, passphrase, 0);
  }
  
  public static Session openSession(String sshHost, int sshPort, String sshUser, byte[] privateKey, byte[] passphrase) {
    return openSession(sshHost, sshPort, sshUser, privateKey, passphrase, 0);
  }
  
  public static Session openSession(String sshHost, int sshPort, String sshUser, byte[] privateKey, byte[] passphrase, int timeOut) {
    Session session = createSession(sshHost, sshPort, sshUser, privateKey, passphrase);
    try {
      session.connect(timeOut);
    } catch (JSchException e) {
      throw new JschRuntimeException(e);
    } 
    return session;
  }
  
  public static Session openSession(String sshHost, int sshPort, String sshUser, String privateKeyPath, byte[] passphrase, int timeOut) {
    Session session = createSession(sshHost, sshPort, sshUser, privateKeyPath, passphrase);
    try {
      session.connect(timeOut);
    } catch (JSchException e) {
      throw new JschRuntimeException(e);
    } 
    return session;
  }
  
  public static Session createSession(String sshHost, int sshPort, String sshUser, String sshPass) {
    JSch jsch = new JSch();
    Session session = createSession(jsch, sshHost, sshPort, sshUser);
    if (StrUtil.isNotEmpty(sshPass))
      session.setPassword(sshPass); 
    return session;
  }
  
  public static Session createSession(String sshHost, int sshPort, String sshUser, String privateKeyPath, byte[] passphrase) {
    Assert.notEmpty(privateKeyPath, "PrivateKey Path must be not empty!", new Object[0]);
    JSch jsch = new JSch();
    try {
      jsch.addIdentity(privateKeyPath, passphrase);
    } catch (JSchException e) {
      throw new JschRuntimeException(e);
    } 
    return createSession(jsch, sshHost, sshPort, sshUser);
  }
  
  public static Session createSession(String sshHost, int sshPort, String sshUser, byte[] privateKey, byte[] passphrase) {
    Assert.isTrue((privateKey != null && privateKey.length > 0), "PrivateKey must be not empty!", new Object[0]);
    JSch jsch = new JSch();
    String identityName = StrUtil.format("{}@{}:{}", new Object[] { sshUser, sshHost, Integer.valueOf(sshPort) });
    try {
      jsch.addIdentity(identityName, privateKey, null, passphrase);
    } catch (JSchException e) {
      throw new JschRuntimeException(e);
    } 
    return createSession(jsch, sshHost, sshPort, sshUser);
  }
  
  public static Session createSession(JSch jsch, String sshHost, int sshPort, String sshUser) {
    Session session;
    Assert.notEmpty(sshHost, "SSH Host must be not empty!", new Object[0]);
    Assert.isTrue((sshPort > 0), "SSH port must be > 0", new Object[0]);
    if (StrUtil.isEmpty(sshUser))
      sshUser = "root"; 
    if (null == jsch)
      jsch = new JSch(); 
    try {
      session = jsch.getSession(sshUser, sshHost, sshPort);
    } catch (JSchException e) {
      throw new JschRuntimeException(e);
    } 
    session.setConfig("StrictHostKeyChecking", "no");
    return session;
  }
  
  public static boolean bindPort(Session session, String remoteHost, int remotePort, int localPort) throws JschRuntimeException {
    return bindPort(session, remoteHost, remotePort, "127.0.0.1", localPort);
  }
  
  public static boolean bindPort(Session session, String remoteHost, int remotePort, String localHost, int localPort) throws JschRuntimeException {
    if (session != null && session.isConnected()) {
      try {
        session.setPortForwardingL(localHost, localPort, remoteHost, remotePort);
      } catch (JSchException e) {
        throw new JschRuntimeException(e, "From [{}:{}] mapping to [{}:{}] error！", new Object[] { remoteHost, Integer.valueOf(remotePort), localHost, Integer.valueOf(localPort) });
      } 
      return true;
    } 
    return false;
  }
  
  public static boolean bindRemotePort(Session session, int bindPort, String host, int port) throws JschRuntimeException {
    if (session != null && session.isConnected()) {
      try {
        session.setPortForwardingR(bindPort, host, port);
      } catch (JSchException e) {
        throw new JschRuntimeException(e, "From [{}] mapping to [{}] error！", new Object[] { Integer.valueOf(bindPort), Integer.valueOf(port) });
      } 
      return true;
    } 
    return false;
  }
  
  public static boolean unBindPort(Session session, int localPort) {
    try {
      session.delPortForwardingL(localPort);
    } catch (JSchException e) {
      throw new JschRuntimeException(e);
    } 
    return true;
  }
  
  public static int openAndBindPortToLocal(Connector sshConn, String remoteHost, int remotePort) throws JschRuntimeException {
    Session session = openSession(sshConn.getHost(), sshConn.getPort(), sshConn.getUser(), sshConn.getPassword());
    int localPort = generateLocalPort();
    bindPort(session, remoteHost, remotePort, localPort);
    return localPort;
  }
  
  public static ChannelSftp openSftp(Session session) {
    return openSftp(session, 0);
  }
  
  public static ChannelSftp openSftp(Session session, int timeout) {
    return (ChannelSftp)openChannel(session, ChannelType.SFTP, timeout);
  }
  
  public static Sftp createSftp(String sshHost, int sshPort, String sshUser, String sshPass) {
    return new Sftp(sshHost, sshPort, sshUser, sshPass);
  }
  
  public static Sftp createSftp(Session session) {
    return new Sftp(session);
  }
  
  public static ChannelShell openShell(Session session) {
    return (ChannelShell)openChannel(session, ChannelType.SHELL);
  }
  
  public static Channel openChannel(Session session, ChannelType channelType) {
    return openChannel(session, channelType, 0);
  }
  
  public static Channel openChannel(Session session, ChannelType channelType, int timeout) {
    Channel channel = createChannel(session, channelType);
    try {
      channel.connect(Math.max(timeout, 0));
    } catch (JSchException e) {
      throw new JschRuntimeException(e);
    } 
    return channel;
  }
  
  public static Channel createChannel(Session session, ChannelType channelType) {
    Channel channel;
    try {
      if (false == session.isConnected())
        session.connect(); 
      channel = session.openChannel(channelType.getValue());
    } catch (JSchException e) {
      throw new JschRuntimeException(e);
    } 
    return channel;
  }
  
  public static String exec(Session session, String cmd, Charset charset) {
    return exec(session, cmd, charset, System.err);
  }
  
  public static String exec(Session session, String cmd, Charset charset, OutputStream errStream) {
    if (null == charset)
      charset = CharsetUtil.CHARSET_UTF_8; 
    ChannelExec channel = (ChannelExec)createChannel(session, ChannelType.EXEC);
    channel.setCommand(StrUtil.bytes(cmd, charset));
    channel.setInputStream(null);
    channel.setErrStream(errStream);
    InputStream in = null;
    try {
      channel.connect();
      in = channel.getInputStream();
      return IoUtil.read(in, charset);
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } catch (JSchException e) {
      throw new JschRuntimeException(e);
    } finally {
      IoUtil.close(in);
      close((Channel)channel);
    } 
  }
  
  public static String execByShell(Session session, String cmd, Charset charset) {
    ChannelShell shell = openShell(session);
    shell.setPty(true);
    OutputStream out = null;
    InputStream in = null;
    try {
      out = shell.getOutputStream();
      in = shell.getInputStream();
      out.write(StrUtil.bytes(cmd, charset));
      out.flush();
      return IoUtil.read(in, charset);
    } catch (IOException e) {
      throw new IORuntimeException(e);
    } finally {
      IoUtil.close(out);
      IoUtil.close(in);
      close((Channel)shell);
    } 
  }
  
  public static void close(Session session) {
    if (session != null && session.isConnected())
      session.disconnect(); 
    JschSessionPool.INSTANCE.remove(session);
  }
  
  public static void close(Channel channel) {
    if (channel != null && channel.isConnected())
      channel.disconnect(); 
  }
  
  public static void close(String key) {
    JschSessionPool.INSTANCE.close(key);
  }
  
  public static void closeAll() {
    JschSessionPool.INSTANCE.closeAll();
  }
}
