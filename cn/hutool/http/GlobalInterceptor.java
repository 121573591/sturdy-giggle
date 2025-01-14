package cn.hutool.http;

public enum GlobalInterceptor {
  INSTANCE;
  
  private final HttpInterceptor.Chain<HttpResponse> responseInterceptors;
  
  private final HttpInterceptor.Chain<HttpRequest> requestInterceptors;
  
  GlobalInterceptor() {
    this.requestInterceptors = new HttpInterceptor.Chain<>();
    this.responseInterceptors = new HttpInterceptor.Chain<>();
  }
  
  public synchronized GlobalInterceptor addRequestInterceptor(HttpInterceptor<HttpRequest> interceptor) {
    this.requestInterceptors.addChain(interceptor);
    return this;
  }
  
  public synchronized GlobalInterceptor addResponseInterceptor(HttpInterceptor<HttpResponse> interceptor) {
    this.responseInterceptors.addChain(interceptor);
    return this;
  }
  
  public GlobalInterceptor clear() {
    clearRequest();
    clearResponse();
    return this;
  }
  
  public synchronized GlobalInterceptor clearRequest() {
    this.requestInterceptors.clear();
    return this;
  }
  
  public synchronized GlobalInterceptor clearResponse() {
    this.responseInterceptors.clear();
    return this;
  }
  
  HttpInterceptor.Chain<HttpRequest> getCopiedRequestInterceptor() {
    HttpInterceptor.Chain<HttpRequest> copied = new HttpInterceptor.Chain<>();
    for (HttpInterceptor<HttpRequest> interceptor : this.requestInterceptors)
      copied.addChain(interceptor); 
    return copied;
  }
  
  HttpInterceptor.Chain<HttpResponse> getCopiedResponseInterceptor() {
    HttpInterceptor.Chain<HttpResponse> copied = new HttpInterceptor.Chain<>();
    for (HttpInterceptor<HttpResponse> interceptor : this.responseInterceptors)
      copied.addChain(interceptor); 
    return copied;
  }
}
