package cn.hutool.http.server.handler;

import cn.hutool.http.server.HttpExchangeWrapper;
import cn.hutool.http.server.HttpServerRequest;
import cn.hutool.http.server.HttpServerResponse;
import cn.hutool.http.server.action.Action;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.IOException;

public class ActionHandler implements HttpHandler {
  private final Action action;
  
  public ActionHandler(Action action) {
    this.action = action;
  }
  
  public void handle(HttpExchange httpExchange) throws IOException {
    HttpServerRequest request;
    HttpServerResponse response;
    if (httpExchange instanceof HttpExchangeWrapper) {
      HttpExchangeWrapper wrapper = (HttpExchangeWrapper)httpExchange;
      request = wrapper.getRequest();
      response = wrapper.getResponse();
    } else {
      request = new HttpServerRequest(httpExchange);
      response = new HttpServerResponse(httpExchange);
    } 
    this.action.doAction(request, response);
    httpExchange.close();
  }
}
