package cn.hutool.http.server.action;

import cn.hutool.http.server.HttpServerRequest;
import cn.hutool.http.server.HttpServerResponse;
import java.io.IOException;

@FunctionalInterface
public interface Action {
  void doAction(HttpServerRequest paramHttpServerRequest, HttpServerResponse paramHttpServerResponse) throws IOException;
}
