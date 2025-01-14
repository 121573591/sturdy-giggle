package cn.hutool.http.server;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpPrincipal;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;

public class HttpExchangeWrapper extends HttpExchange {
  private final HttpExchange raw;
  
  private final HttpServerRequest request;
  
  private final HttpServerResponse response;
  
  public HttpExchangeWrapper(HttpExchange raw) {
    this.raw = raw;
    this.request = new HttpServerRequest(this);
    this.response = new HttpServerResponse(this);
  }
  
  public HttpExchange getRaw() {
    return this.raw;
  }
  
  public HttpServerRequest getRequest() {
    return this.request;
  }
  
  public HttpServerResponse getResponse() {
    return this.response;
  }
  
  public Headers getRequestHeaders() {
    return this.raw.getRequestHeaders();
  }
  
  public Headers getResponseHeaders() {
    return this.raw.getResponseHeaders();
  }
  
  public URI getRequestURI() {
    return this.raw.getRequestURI();
  }
  
  public String getRequestMethod() {
    return this.raw.getRequestMethod();
  }
  
  public HttpContext getHttpContext() {
    return this.raw.getHttpContext();
  }
  
  public void close() {
    this.raw.close();
  }
  
  public InputStream getRequestBody() {
    return this.raw.getRequestBody();
  }
  
  public OutputStream getResponseBody() {
    return this.raw.getResponseBody();
  }
  
  public void sendResponseHeaders(int rCode, long responseLength) throws IOException {
    this.raw.sendResponseHeaders(rCode, responseLength);
  }
  
  public InetSocketAddress getRemoteAddress() {
    return this.raw.getRemoteAddress();
  }
  
  public int getResponseCode() {
    return this.raw.getResponseCode();
  }
  
  public InetSocketAddress getLocalAddress() {
    return this.raw.getLocalAddress();
  }
  
  public String getProtocol() {
    return this.raw.getProtocol();
  }
  
  public Object getAttribute(String name) {
    return this.raw.getAttribute(name);
  }
  
  public void setAttribute(String name, Object value) {
    this.raw.setAttribute(name, value);
  }
  
  public void setStreams(InputStream i, OutputStream o) {
    this.raw.setStreams(i, o);
  }
  
  public HttpPrincipal getPrincipal() {
    return this.raw.getPrincipal();
  }
}
