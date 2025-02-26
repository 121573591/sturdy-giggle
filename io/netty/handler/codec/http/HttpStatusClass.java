package io.netty.handler.codec.http;

import io.netty.util.AsciiString;

public enum HttpStatusClass {
  INFORMATIONAL(100, 200, "Informational"),
  SUCCESS(200, 300, "Success"),
  REDIRECTION(300, 400, "Redirection"),
  CLIENT_ERROR(400, 500, "Client Error"),
  SERVER_ERROR(500, 600, "Server Error"),
  UNKNOWN(0, 0, "Unknown Status") {
    public boolean contains(int code) {
      return (code < 100 || code >= 600);
    }
  };
  
  private static final HttpStatusClass[] statusArray;
  
  private final int min;
  
  private final int max;
  
  private final AsciiString defaultReasonPhrase;
  
  static {
    statusArray = new HttpStatusClass[6];
    statusArray[1] = INFORMATIONAL;
    statusArray[2] = SUCCESS;
    statusArray[3] = REDIRECTION;
    statusArray[4] = CLIENT_ERROR;
    statusArray[5] = SERVER_ERROR;
  }
  
  private static int fast_div100(int dividend) {
    return (int)(dividend * 1374389535L >> 37L);
  }
  
  private static int digit(char c) {
    return c - 48;
  }
  
  private static boolean isDigit(char c) {
    return (c >= '0' && c <= '9');
  }
  
  HttpStatusClass(int min, int max, String defaultReasonPhrase) {
    this.min = min;
    this.max = max;
    this.defaultReasonPhrase = AsciiString.cached(defaultReasonPhrase);
  }
  
  public boolean contains(int code) {
    return (code >= this.min && code < this.max);
  }
  
  AsciiString defaultReasonPhrase() {
    return this.defaultReasonPhrase;
  }
}
