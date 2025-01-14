package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.ByteString;
import com.google.protobuf.MessageOrBuilder;

public interface RspOrBuilder extends MessageOrBuilder {
  int getA();
  
  int getB();
  
  int getC();
  
  String getD();
  
  ByteString getDBytes();
  
  int getE();
  
  boolean hasF();
  
  Rsp.F getF();
  
  Rsp.FOrBuilder getFOrBuilder();
  
  String getG();
  
  ByteString getGBytes();
  
  long getH();
  
  long getI();
  
  long getJ();
}
