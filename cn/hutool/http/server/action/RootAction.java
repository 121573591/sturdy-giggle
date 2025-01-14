package cn.hutool.http.server.action;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.http.server.HttpServerRequest;
import cn.hutool.http.server.HttpServerResponse;
import java.io.File;
import java.util.List;

public class RootAction implements Action {
  public static final String DEFAULT_INDEX_FILE_NAME = "index.html";
  
  private final File rootDir;
  
  private final List<String> indexFileNames;
  
  public RootAction(String rootDir) {
    this(new File(rootDir));
  }
  
  public RootAction(File rootDir) {
    this(rootDir, new String[] { "index.html" });
  }
  
  public RootAction(String rootDir, String... indexFileNames) {
    this(new File(rootDir), indexFileNames);
  }
  
  public RootAction(File rootDir, String... indexFileNames) {
    this.rootDir = rootDir;
    this.indexFileNames = CollUtil.toList((Object[])indexFileNames);
  }
  
  public void doAction(HttpServerRequest request, HttpServerResponse response) {
    String path = request.getPath();
    File file = FileUtil.file(this.rootDir, path);
    if (file.exists())
      if (file.isDirectory()) {
        for (String indexFileName : this.indexFileNames) {
          file = FileUtil.file(file, indexFileName);
          if (file.exists() && file.isFile()) {
            response.write(file);
            return;
          } 
        } 
      } else {
        String name = request.getParam("name");
        response.write(file, name);
        return;
      }  
    response.send404("404 Not Found !");
  }
}
