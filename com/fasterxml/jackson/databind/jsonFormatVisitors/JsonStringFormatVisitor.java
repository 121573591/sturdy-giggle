package com.fasterxml.jackson.databind.jsonFormatVisitors;

public interface JsonStringFormatVisitor extends JsonValueFormatVisitor {
  public static class Base extends JsonValueFormatVisitor.Base implements JsonStringFormatVisitor {}
}
