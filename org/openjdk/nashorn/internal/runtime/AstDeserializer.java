package org.openjdk.nashorn.internal.runtime;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.security.AccessController;
import java.util.zip.InflaterInputStream;
import org.openjdk.nashorn.internal.ir.FunctionNode;

final class AstDeserializer {
  static FunctionNode deserialize(byte[] serializedAst) {
    return AccessController.<FunctionNode>doPrivileged(() -> {
          try {
            return (FunctionNode)(new ObjectInputStream(new InflaterInputStream(new ByteArrayInputStream(serializedAst)))).readObject();
          } catch (ClassNotFoundException|java.io.IOException e) {
            throw new AssertionError("Unexpected exception deserializing function", e);
          } 
        });
  }
}
