package cn.hutool.http.body;

import cn.hutool.core.io.resource.Resource;
import java.io.OutputStream;

public class ResourceBody implements RequestBody {
  private final Resource resource;
  
  public static ResourceBody create(Resource resource) {
    return new ResourceBody(resource);
  }
  
  public ResourceBody(Resource resource) {
    this.resource = resource;
  }
  
  public void write(OutputStream out) {
    if (null != this.resource)
      this.resource.writeTo(out); 
  }
  
  public String toString() {
    return this.resource.readUtf8Str();
  }
}
