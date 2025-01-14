package cn.hutool.http.body;

import cn.hutool.core.io.IoUtil;
import java.io.OutputStream;

public interface RequestBody {
  void write(OutputStream paramOutputStream);
  
  default void writeClose(OutputStream out) {
    try {
      write(out);
    } finally {
      IoUtil.close(out);
    } 
  }
}
