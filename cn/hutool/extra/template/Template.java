package cn.hutool.extra.template;

import java.io.File;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Map;

public interface Template {
  void render(Map<?, ?> paramMap, Writer paramWriter);
  
  void render(Map<?, ?> paramMap, OutputStream paramOutputStream);
  
  void render(Map<?, ?> paramMap, File paramFile);
  
  String render(Map<?, ?> paramMap);
}
