package io.netty.handler.codec.http.websocketx.extensions;

import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpHeaderValues;
import io.netty.handler.codec.http.HttpHeaders;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class WebSocketExtensionUtil {
  private static final String EXTENSION_SEPARATOR = ",";
  
  private static final String PARAMETER_SEPARATOR = ";";
  
  private static final char PARAMETER_EQUAL = '=';
  
  private static final Pattern PARAMETER = Pattern.compile("^([^=]+)(=[\\\"]?([^\\\"]+)[\\\"]?)?$");
  
  static boolean isWebsocketUpgrade(HttpHeaders headers) {
    return (headers.contains((CharSequence)HttpHeaderNames.UPGRADE) && headers
      .containsValue((CharSequence)HttpHeaderNames.CONNECTION, (CharSequence)HttpHeaderValues.UPGRADE, true) && headers
      .contains((CharSequence)HttpHeaderNames.UPGRADE, (CharSequence)HttpHeaderValues.WEBSOCKET, true));
  }
  
  public static List<WebSocketExtensionData> extractExtensions(String extensionHeader) {
    String[] rawExtensions = extensionHeader.split(",");
    if (rawExtensions.length > 0) {
      List<WebSocketExtensionData> extensions = new ArrayList<WebSocketExtensionData>(rawExtensions.length);
      for (String rawExtension : rawExtensions) {
        Map<String, String> parameters;
        String[] extensionParameters = rawExtension.split(";");
        String name = extensionParameters[0].trim();
        if (extensionParameters.length > 1) {
          parameters = new HashMap<String, String>(extensionParameters.length - 1);
          for (int i = 1; i < extensionParameters.length; i++) {
            String parameter = extensionParameters[i].trim();
            Matcher parameterMatcher = PARAMETER.matcher(parameter);
            if (parameterMatcher.matches() && parameterMatcher.group(1) != null)
              parameters.put(parameterMatcher.group(1), parameterMatcher.group(3)); 
          } 
        } else {
          parameters = Collections.emptyMap();
        } 
        extensions.add(new WebSocketExtensionData(name, parameters));
      } 
      return extensions;
    } 
    return Collections.emptyList();
  }
  
  static String computeMergeExtensionsHeaderValue(String userDefinedHeaderValue, List<WebSocketExtensionData> extraExtensions) {
    List<WebSocketExtensionData> userDefinedExtensions = (userDefinedHeaderValue != null) ? extractExtensions(userDefinedHeaderValue) : Collections.<WebSocketExtensionData>emptyList();
    for (WebSocketExtensionData userDefined : userDefinedExtensions) {
      WebSocketExtensionData matchingExtra = null;
      int i;
      for (i = 0; i < extraExtensions.size(); i++) {
        WebSocketExtensionData extra = extraExtensions.get(i);
        if (extra.name().equals(userDefined.name())) {
          matchingExtra = extra;
          break;
        } 
      } 
      if (matchingExtra == null) {
        extraExtensions.add(userDefined);
        continue;
      } 
      Map<String, String> mergedParameters = new HashMap<String, String>(matchingExtra.parameters());
      mergedParameters.putAll(userDefined.parameters());
      extraExtensions.set(i, new WebSocketExtensionData(matchingExtra.name(), mergedParameters));
    } 
    StringBuilder sb = new StringBuilder(150);
    for (WebSocketExtensionData data : extraExtensions) {
      sb.append(data.name());
      for (Map.Entry<String, String> parameter : data.parameters().entrySet()) {
        sb.append(";");
        sb.append(parameter.getKey());
        if (parameter.getValue() != null) {
          sb.append('=');
          sb.append(parameter.getValue());
        } 
      } 
      sb.append(",");
    } 
    if (!extraExtensions.isEmpty())
      sb.setLength(sb.length() - ",".length()); 
    return sb.toString();
  }
}
