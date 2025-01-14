package org.openjdk.nashorn.internal.runtime.regexp;

import java.util.regex.MatchResult;

public interface RegExpMatcher extends MatchResult {
  boolean search(int paramInt);
  
  String getInput();
}
