package org.openjdk.nashorn.internal.ir.debug;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.objectweb.asm.Attribute;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.Label;

public class NashornClassReader extends ClassReader {
  private final Map<String, List<Label>> labelMap = new HashMap<>();
  
  public NashornClassReader(byte[] bytecode) {
    super(bytecode);
    parse(bytecode);
  }
  
  List<Label> getExtraLabels(String className, String methodName, String methodDesc) {
    String key = fullyQualifiedName(className, methodName, methodDesc);
    return this.labelMap.get(key);
  }
  
  private static int readByte(byte[] bytecode, int index) {
    return (byte)(bytecode[index] & 0xFF);
  }
  
  private static int readShort(byte[] bytecode, int index) {
    return (short)((bytecode[index] & 0xFF) << 8) | bytecode[index + 1] & 0xFF;
  }
  
  private static int readInt(byte[] bytecode, int index) {
    return (bytecode[index] & 0xFF) << 24 | (bytecode[index + 1] & 0xFF) << 16 | (bytecode[index + 2] & 0xFF) << 8 | bytecode[index + 3] & 0xFF;
  }
  
  private static long readLong(byte[] bytecode, int index) {
    int hi = readInt(bytecode, index);
    int lo = readInt(bytecode, index + 4);
    return hi << 32L | lo;
  }
  
  private static String readUTF(int index, int utfLen, byte[] bytecode) {
    int endIndex = index + utfLen;
    char[] buf = new char[utfLen * 2];
    int strLen = 0;
    int st = 0;
    char cc = Character.MIN_VALUE;
    int i = index;
    while (i < endIndex) {
      int c = bytecode[i++];
      switch (st) {
        case 0:
          c &= 0xFF;
          if (c < 128) {
            buf[strLen++] = (char)c;
            continue;
          } 
          if (c < 224 && c > 191) {
            cc = (char)(c & 0x1F);
            st = 1;
            continue;
          } 
          cc = (char)(c & 0xF);
          st = 2;
        case 1:
          buf[strLen++] = (char)(cc << 6 | c & 0x3F);
          st = 0;
        case 2:
          cc = (char)(cc << 6 | c & 0x3F);
          st = 1;
      } 
    } 
    return new String(buf, 0, strLen);
  }
  
  private String parse(byte[] bytecode) {
    int u = 0;
    int magic = readInt(bytecode, u);
    u += 4;
    assert magic == -889275714 : Integer.toHexString(magic);
    readShort(bytecode, u);
    u += 2;
    readShort(bytecode, u);
    u += 2;
    int cpc = readShort(bytecode, u);
    u += 2;
    ArrayList<Constant> cp = new ArrayList<>(cpc);
    cp.add(null);
    for (int i = 1; i < cpc; i++) {
      int len, kind, tag = readByte(bytecode, u);
      u++;
      switch (tag) {
        case 7:
          cp.add(new IndexInfo(cp, tag, readShort(bytecode, u)));
          u += 2;
          break;
        case 9:
        case 10:
        case 11:
          cp.add(new IndexInfo2(cp, tag, readShort(bytecode, u), readShort(bytecode, u + 2)));
          u += 4;
          break;
        case 8:
          cp.add(new IndexInfo(cp, tag, readShort(bytecode, u)));
          u += 2;
          break;
        case 3:
          cp.add(new DirectInfo<>(cp, tag, Integer.valueOf(readInt(bytecode, u))));
          u += 4;
          break;
        case 4:
          cp.add(new DirectInfo<>(cp, tag, Float.valueOf(Float.intBitsToFloat(readInt(bytecode, u)))));
          u += 4;
          break;
        case 5:
          cp.add(new DirectInfo<>(cp, tag, Long.valueOf(readLong(bytecode, u))));
          cp.add(null);
          i++;
          u += 8;
          break;
        case 6:
          cp.add(new DirectInfo<>(cp, tag, Double.valueOf(Double.longBitsToDouble(readLong(bytecode, u)))));
          cp.add(null);
          i++;
          u += 8;
          break;
        case 12:
          cp.add(new IndexInfo2(cp, tag, readShort(bytecode, u), readShort(bytecode, u + 2)));
          u += 4;
          break;
        case 1:
          len = readShort(bytecode, u);
          u += 2;
          cp.add(new DirectInfo<>(cp, tag, readUTF(u, len, bytecode)));
          u += len;
          break;
        case 16:
          cp.add(new IndexInfo(cp, tag, readShort(bytecode, u)));
          u += 2;
          break;
        case 18:
          cp.add(new IndexInfo2(cp, tag, readShort(bytecode, u), readShort(bytecode, u + 2)) {
                public String toString() {
                  return "#" + this.index + " " + ((NashornClassReader.Constant)this.cp.get(this.index2)).toString();
                }
              });
          u += 4;
          break;
        case 15:
          kind = readByte(bytecode, u);
          assert kind >= 1 && kind <= 9 : kind;
          cp.add(new IndexInfo2(cp, tag, kind, readShort(bytecode, u + 1)) {
                public String toString() {
                  return "#" + this.index + " " + ((NashornClassReader.Constant)this.cp.get(this.index2)).toString();
                }
              });
          u += 3;
          break;
        default:
          assert false : tag;
          break;
      } 
    } 
    readShort(bytecode, u);
    u += 2;
    int cls = readShort(bytecode, u);
    u += 2;
    String thisClassName = ((Constant)cp.get(cls)).toString();
    u += 2;
    int ifc = readShort(bytecode, u);
    u += 2;
    u += ifc * 2;
    int fc = readShort(bytecode, u);
    u += 2;
    for (int j = 0; j < fc; j++) {
      u += 2;
      readShort(bytecode, u);
      u += 2;
      u += 2;
      int n = readShort(bytecode, u);
      u += 2;
      for (int i1 = 0; i1 < n; i1++) {
        u += 2;
        int len = readInt(bytecode, u);
        u += 4;
        u += len;
      } 
    } 
    int mc = readShort(bytecode, u);
    u += 2;
    for (int k = 0; k < mc; k++) {
      readShort(bytecode, u);
      u += 2;
      int methodNameIndex = readShort(bytecode, u);
      u += 2;
      String methodName = ((Constant)cp.get(methodNameIndex)).toString();
      int methodDescIndex = readShort(bytecode, u);
      u += 2;
      String methodDesc = ((Constant)cp.get(methodDescIndex)).toString();
      int n = readShort(bytecode, u);
      u += 2;
      for (int i1 = 0; i1 < n; i1++) {
        int nameIndex = readShort(bytecode, u);
        u += 2;
        String attrName = ((Constant)cp.get(nameIndex)).toString();
        int attrLen = readInt(bytecode, u);
        u += 4;
        if ("Code".equals(attrName)) {
          readShort(bytecode, u);
          u += 2;
          readShort(bytecode, u);
          u += 2;
          int len = readInt(bytecode, u);
          u += 4;
          parseCode(bytecode, u, len, fullyQualifiedName(thisClassName, methodName, methodDesc));
          u += len;
          int elen = readShort(bytecode, u);
          u += 2;
          u += elen * 8;
          int ac2 = readShort(bytecode, u);
          u += 2;
          for (int i2 = 0; i2 < ac2; i2++) {
            u += 2;
            int aclen = readInt(bytecode, u);
            u += 4;
            u += aclen;
          } 
        } else {
          u += attrLen;
        } 
      } 
    } 
    int ac = readShort(bytecode, u);
    u += 2;
    for (int m = 0; m < ac; m++) {
      readShort(bytecode, u);
      u += 2;
      int len = readInt(bytecode, u);
      u += 4;
      u += len;
    } 
    return thisClassName;
  }
  
  private static String fullyQualifiedName(String className, String methodName, String methodDesc) {
    return className + "." + className + methodName;
  }
  
  private void parseCode(byte[] bytecode, int index, int len, String desc) {
    List<Label> labels = new ArrayList<>();
    this.labelMap.put(desc, labels);
    boolean wide = false;
    for (int i = index; i < index + len; ) {
      int npairs, lo, hi, opcode = bytecode[i];
      labels.add(new NashornTextifier.NashornLabel(opcode, i - index));
      switch (opcode & 0xFF) {
        case 196:
          wide = true;
          i++;
          break;
        case 169:
          i += wide ? 4 : 2;
          break;
        case 171:
          i++;
          while ((i - index & 0x3) != 0)
            i++; 
          readInt(bytecode, i);
          i += 4;
          npairs = readInt(bytecode, i);
          i += 4;
          i += 8 * npairs;
          break;
        case 170:
          i++;
          while ((i - index & 0x3) != 0)
            i++; 
          readInt(bytecode, i);
          i += 4;
          lo = readInt(bytecode, i);
          i += 4;
          hi = readInt(bytecode, i);
          i += 4;
          i += 4 * (hi - lo + 1);
          break;
        case 197:
          i += 4;
          break;
        case 21:
        case 22:
        case 23:
        case 24:
        case 25:
        case 54:
        case 55:
        case 56:
        case 57:
        case 58:
          i += wide ? 3 : 2;
          break;
        case 16:
        case 18:
        case 188:
          i += 2;
          break;
        case 17:
        case 19:
        case 20:
        case 153:
        case 154:
        case 155:
        case 156:
        case 157:
        case 158:
        case 159:
        case 160:
        case 161:
        case 162:
        case 163:
        case 164:
        case 165:
        case 166:
        case 167:
        case 168:
        case 178:
        case 179:
        case 180:
        case 181:
        case 182:
        case 183:
        case 184:
        case 187:
        case 189:
        case 192:
        case 193:
        case 198:
        case 199:
          i += 3;
          break;
        case 132:
          i += wide ? 5 : 3;
          break;
        case 185:
        case 186:
        case 200:
        case 201:
          i += 5;
          break;
        default:
          i++;
          break;
      } 
      if (wide)
        wide = false; 
    } 
  }
  
  public void accept(ClassVisitor classVisitor, Attribute[] attrs, int flags) {
    super.accept(classVisitor, attrs, flags);
  }
  
  protected Label readLabel(int offset, Label[] labels) {
    Label label = super.readLabel(offset, labels);
    label.info = Integer.valueOf(offset);
    return label;
  }
  
  private static abstract class Constant {
    protected ArrayList<Constant> cp;
    
    protected int tag;
    
    protected Constant(ArrayList<Constant> cp, int tag) {
      this.cp = cp;
      this.tag = tag;
    }
    
    final String getType() {
      String str = NashornClassReader.TYPE[this.tag];
      while (str.length() < 16)
        str = str + " "; 
      return str;
    }
  }
  
  private static class IndexInfo extends Constant {
    protected final int index;
    
    IndexInfo(ArrayList<NashornClassReader.Constant> cp, int tag, int index) {
      super(cp, tag);
      this.index = index;
    }
    
    public String toString() {
      return ((NashornClassReader.Constant)this.cp.get(this.index)).toString();
    }
  }
  
  private static class IndexInfo2 extends IndexInfo {
    protected final int index2;
    
    IndexInfo2(ArrayList<NashornClassReader.Constant> cp, int tag, int index, int index2) {
      super(cp, tag, index);
      this.index2 = index2;
    }
    
    public String toString() {
      return super.toString() + " " + super.toString();
    }
  }
  
  private static class DirectInfo<T> extends Constant {
    protected final T info;
    
    DirectInfo(ArrayList<NashornClassReader.Constant> cp, int tag, T info) {
      super(cp, tag);
      this.info = info;
    }
    
    public String toString() {
      return this.info.toString();
    }
  }
  
  private static final String[] TYPE = new String[] { 
      "<error>", "UTF8", "<error>", "Integer", "Float", "Long", "Double", "Class", "String", "Fieldref", 
      "Methodref", "InterfaceMethodRef", "NameAndType", "<error>", "<error>", "MethodHandle", "MethodType", "<error>", "Invokedynamic" };
}
