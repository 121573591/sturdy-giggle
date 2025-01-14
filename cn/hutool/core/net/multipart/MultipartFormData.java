package cn.hutool.core.net.multipart;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.map.multi.ListValueMap;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MultipartFormData {
  private final ListValueMap<String, String> requestParameters = new ListValueMap();
  
  private final ListValueMap<String, UploadFile> requestFiles = new ListValueMap();
  
  private final UploadSetting setting;
  
  private boolean loaded;
  
  public MultipartFormData() {
    this(null);
  }
  
  public MultipartFormData(UploadSetting uploadSetting) {
    this.setting = (uploadSetting == null) ? new UploadSetting() : uploadSetting;
  }
  
  public void parseRequestStream(InputStream inputStream, Charset charset) throws IOException {
    setLoaded();
    MultipartRequestInputStream input = new MultipartRequestInputStream(inputStream);
    input.readBoundary();
    while (true) {
      UploadFileHeader header = input.readDataHeader(charset);
      if (header == null)
        break; 
      if (header.isFile == true) {
        String fileName = header.fileName;
        if (fileName.length() > 0 && header.contentType.contains("application/x-macbinary"))
          input.skipBytes(128L); 
        UploadFile newFile = new UploadFile(header, this.setting);
        if (newFile.processStream(input))
          putFile(header.formFieldName, newFile); 
      } else {
        putParameter(header.formFieldName, input.readString(charset));
      } 
      input.skipBytes(1L);
      input.mark(1);
      int nextByte = input.read();
      if (nextByte == -1 || nextByte == 45) {
        input.reset();
        break;
      } 
      input.reset();
    } 
  }
  
  public String getParam(String paramName) {
    List<String> values = getListParam(paramName);
    if (CollUtil.isNotEmpty(values))
      return values.get(0); 
    return null;
  }
  
  public Set<String> getParamNames() {
    return this.requestParameters.keySet();
  }
  
  public String[] getArrayParam(String paramName) {
    List<String> listParam = getListParam(paramName);
    if (null != listParam)
      return listParam.<String>toArray(new String[0]); 
    return null;
  }
  
  public List<String> getListParam(String paramName) {
    return (List<String>)this.requestParameters.get(paramName);
  }
  
  public Map<String, String[]> getParamMap() {
    return Convert.toMap(String.class, String[].class, getParamListMap());
  }
  
  public ListValueMap<String, String> getParamListMap() {
    return this.requestParameters;
  }
  
  public UploadFile getFile(String paramName) {
    UploadFile[] values = getFiles(paramName);
    if (values != null && values.length > 0)
      return values[0]; 
    return null;
  }
  
  public UploadFile[] getFiles(String paramName) {
    List<UploadFile> fileList = getFileList(paramName);
    if (null != fileList)
      return fileList.<UploadFile>toArray(new UploadFile[0]); 
    return null;
  }
  
  public List<UploadFile> getFileList(String paramName) {
    return (List<UploadFile>)this.requestFiles.get(paramName);
  }
  
  public Set<String> getFileParamNames() {
    return this.requestFiles.keySet();
  }
  
  public Map<String, UploadFile[]> getFileMap() {
    return Convert.toMap(String.class, UploadFile[].class, getFileListValueMap());
  }
  
  public ListValueMap<String, UploadFile> getFileListValueMap() {
    return this.requestFiles;
  }
  
  public boolean isLoaded() {
    return this.loaded;
  }
  
  private void putFile(String name, UploadFile uploadFile) {
    this.requestFiles.putValue(name, uploadFile);
  }
  
  private void putParameter(String name, String value) {
    this.requestParameters.putValue(name, value);
  }
  
  private void setLoaded() throws IOException {
    if (this.loaded == true)
      throw new IOException("Multi-part request already parsed."); 
    this.loaded = true;
  }
}
