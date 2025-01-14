package cn.hutool.http;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.FastByteArrayOutputStream;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.StreamProgress;
import cn.hutool.core.io.resource.BytesResource;
import cn.hutool.core.io.resource.Resource;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.URLUtil;
import cn.hutool.http.cookie.GlobalCookieManager;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpCookie;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

public class HttpResponse extends HttpBase<HttpResponse> implements Closeable {
  protected HttpConfig config;
  
  protected HttpConnection httpConnection;
  
  protected InputStream in;
  
  private volatile boolean isAsync;
  
  protected int status;
  
  private final boolean ignoreBody;
  
  private Charset charsetFromResponse;
  
  protected HttpResponse(HttpConnection httpConnection, HttpConfig config, Charset charset, boolean isAsync, boolean isIgnoreBody) {
    this.httpConnection = httpConnection;
    this.config = config;
    this.charset = charset;
    this.isAsync = isAsync;
    this.ignoreBody = isIgnoreBody;
    initWithDisconnect();
  }
  
  public int getStatus() {
    return this.status;
  }
  
  public boolean isOk() {
    return (this.status >= 200 && this.status < 300);
  }
  
  public HttpResponse sync() {
    return this.isAsync ? forceSync() : this;
  }
  
  public String contentEncoding() {
    return header(Header.CONTENT_ENCODING);
  }
  
  public long contentLength() {
    long contentLength = Convert.toLong(header(Header.CONTENT_LENGTH), Long.valueOf(-1L)).longValue();
    if (contentLength > 0L && (isChunked() || StrUtil.isNotBlank(contentEncoding())))
      contentLength = -1L; 
    return contentLength;
  }
  
  public boolean isGzip() {
    String contentEncoding = contentEncoding();
    return "gzip".equalsIgnoreCase(contentEncoding);
  }
  
  public boolean isDeflate() {
    String contentEncoding = contentEncoding();
    return "deflate".equalsIgnoreCase(contentEncoding);
  }
  
  public boolean isChunked() {
    String transferEncoding = header(Header.TRANSFER_ENCODING);
    return "Chunked".equalsIgnoreCase(transferEncoding);
  }
  
  public String getCookieStr() {
    return header(Header.SET_COOKIE);
  }
  
  public List<HttpCookie> getCookies() {
    return GlobalCookieManager.getCookies(this.httpConnection);
  }
  
  public HttpCookie getCookie(String name) {
    List<HttpCookie> cookie = getCookies();
    if (null != cookie)
      for (HttpCookie httpCookie : cookie) {
        if (httpCookie.getName().equals(name))
          return httpCookie; 
      }  
    return null;
  }
  
  public String getCookieValue(String name) {
    HttpCookie cookie = getCookie(name);
    return (null == cookie) ? null : cookie.getValue();
  }
  
  public InputStream bodyStream() {
    if (this.isAsync)
      return this.in; 
    return (null == this.body) ? null : this.body.getStream();
  }
  
  public byte[] bodyBytes() {
    sync();
    return super.bodyBytes();
  }
  
  public HttpResponse body(byte[] bodyBytes) {
    sync();
    if (null != bodyBytes)
      this.body = (Resource)new BytesResource(bodyBytes); 
    return this;
  }
  
  public String body() throws HttpException {
    return HttpUtil.getString(bodyBytes(), this.charset, (null == this.charsetFromResponse));
  }
  
  public long writeBody(OutputStream out, boolean isCloseOut, StreamProgress streamProgress) {
    Assert.notNull(out, "[out] must be not null!", new Object[0]);
    long contentLength = contentLength();
    try {
      return copyBody(bodyStream(), out, contentLength, streamProgress, this.config.ignoreEOFError);
    } finally {
      IoUtil.close(this);
      if (isCloseOut)
        IoUtil.close(out); 
    } 
  }
  
  public long writeBody(File targetFileOrDir, StreamProgress streamProgress) {
    Assert.notNull(targetFileOrDir, "[targetFileOrDir] must be not null!", new Object[0]);
    File outFile = completeFileNameFromHeader(targetFileOrDir);
    return writeBody(FileUtil.getOutputStream(outFile), true, streamProgress);
  }
  
  public long writeBody(File targetFileOrDir, String tempFileSuffix, StreamProgress streamProgress) {
    long length;
    Assert.notNull(targetFileOrDir, "[targetFileOrDir] must be not null!", new Object[0]);
    File outFile = completeFileNameFromHeader(targetFileOrDir);
    if (StrUtil.isBlank(tempFileSuffix)) {
      tempFileSuffix = ".temp";
    } else {
      tempFileSuffix = StrUtil.addPrefixIfNot(tempFileSuffix, ".");
    } 
    String fileName = outFile.getName();
    String tempFileName = fileName + tempFileSuffix;
    outFile = new File(outFile.getParentFile(), tempFileName);
    try {
      length = writeBody(outFile, streamProgress);
      FileUtil.rename(outFile, fileName, true);
    } catch (Throwable e) {
      FileUtil.del(outFile);
      throw new HttpException(e);
    } 
    return length;
  }
  
  public File writeBodyForFile(File targetFileOrDir, StreamProgress streamProgress) {
    Assert.notNull(targetFileOrDir, "[targetFileOrDir] must be not null!", new Object[0]);
    File outFile = completeFileNameFromHeader(targetFileOrDir);
    writeBody(FileUtil.getOutputStream(outFile), true, streamProgress);
    return outFile;
  }
  
  public long writeBody(File targetFileOrDir) {
    return writeBody(targetFileOrDir, (StreamProgress)null);
  }
  
  public long writeBody(String targetFileOrDir) {
    return writeBody(FileUtil.file(targetFileOrDir));
  }
  
  public void close() {
    IoUtil.close(this.in);
    this.in = null;
    this.httpConnection.disconnectQuietly();
  }
  
  public String toString() {
    StringBuilder sb = StrUtil.builder();
    sb.append("Response Headers: ").append("\r\n");
    for (Map.Entry<String, List<String>> entry : this.headers.entrySet())
      sb.append("    ").append(entry).append("\r\n"); 
    sb.append("Response Body: ").append("\r\n");
    sb.append("    ").append(body()).append("\r\n");
    return sb.toString();
  }
  
  public File completeFileNameFromHeader(File targetFileOrDir) {
    if (false == targetFileOrDir.isDirectory())
      return targetFileOrDir; 
    String fileName = getFileNameFromDisposition((String)null);
    if (StrUtil.isBlank(fileName)) {
      String path = this.httpConnection.getUrl().getPath();
      fileName = StrUtil.subSuf(path, path.lastIndexOf('/') + 1);
      if (StrUtil.isBlank(fileName)) {
        fileName = URLUtil.encodeQuery(path, this.charset);
      } else {
        fileName = URLUtil.decode(fileName, this.charset);
      } 
    } 
    return FileUtil.file(targetFileOrDir, fileName);
  }
  
  public String getFileNameFromDisposition(String paramName) {
    paramName = (String)ObjUtil.defaultIfNull(paramName, "filename");
    String fileName = null;
    String disposition = header(Header.CONTENT_DISPOSITION);
    if (StrUtil.isNotBlank(disposition)) {
      fileName = ReUtil.get(paramName + "=\"(.*?)\"", disposition, 1);
      if (StrUtil.isBlank(fileName))
        fileName = StrUtil.subAfter(disposition, paramName + "=", true); 
    } 
    return fileName;
  }
  
  private HttpResponse initWithDisconnect() throws HttpException {
    try {
      init();
    } catch (HttpException e) {
      this.httpConnection.disconnectQuietly();
      throw e;
    } 
    return this;
  }
  
  private HttpResponse init() throws HttpException {
    try {
      this.status = this.httpConnection.responseCode();
    } catch (IOException e) {
      if (false == e instanceof java.io.FileNotFoundException)
        throw new HttpException(e); 
    } 
    try {
      this.headers = this.httpConnection.headers();
    } catch (IllegalArgumentException illegalArgumentException) {}
    GlobalCookieManager.store(this.httpConnection);
    Charset charset = this.httpConnection.getCharset();
    this.charsetFromResponse = charset;
    if (null != charset)
      this.charset = charset; 
    this.in = new HttpInputStream(this);
    return this.isAsync ? this : forceSync();
  }
  
  private HttpResponse forceSync() {
    try {
      readBody(this.in);
    } catch (IORuntimeException e) {
      if (e.getCause() instanceof java.io.FileNotFoundException)
        return this; 
      throw new HttpException(e);
    } finally {
      if (this.isAsync)
        this.isAsync = false; 
      close();
    } 
    return this;
  }
  
  private void readBody(InputStream in) throws IORuntimeException {
    if (this.ignoreBody)
      return; 
    long contentLength = contentLength();
    FastByteArrayOutputStream out = new FastByteArrayOutputStream((int)contentLength);
    copyBody(in, (OutputStream)out, contentLength, (StreamProgress)null, this.config.ignoreEOFError);
    this.body = (Resource)new BytesResource(out.toByteArray());
  }
  
  private static long copyBody(InputStream in, OutputStream out, long contentLength, StreamProgress streamProgress, boolean isIgnoreEOFError) {
    if (null == out)
      throw new NullPointerException("[out] is null!"); 
    long copyLength = -1L;
    try {
      copyLength = IoUtil.copy(in, out, 8192, contentLength, streamProgress);
    } catch (IORuntimeException e) {
      if (!isIgnoreEOFError || (
        !(e.getCause() instanceof java.io.EOFException) && !StrUtil.containsIgnoreCase(e.getMessage(), "Premature EOF")))
        throw e; 
    } 
    return copyLength;
  }
}
