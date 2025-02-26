package com.fasterxml.jackson.core;

import java.io.IOException;

public abstract class TreeCodec {
  public abstract <T extends TreeNode> T readTree(JsonParser paramJsonParser) throws IOException, JsonProcessingException;
  
  public abstract void writeTree(JsonGenerator paramJsonGenerator, TreeNode paramTreeNode) throws IOException, JsonProcessingException;
  
  public TreeNode missingNode() {
    return null;
  }
  
  public TreeNode nullNode() {
    return null;
  }
  
  public abstract TreeNode createArrayNode();
  
  public abstract TreeNode createObjectNode();
  
  public abstract JsonParser treeAsTokens(TreeNode paramTreeNode);
}
