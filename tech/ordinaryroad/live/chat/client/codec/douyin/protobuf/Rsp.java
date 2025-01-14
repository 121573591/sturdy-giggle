package tech.ordinaryroad.live.chat.client.codec.douyin.protobuf;

import com.google.protobuf.AbstractMessage;
import com.google.protobuf.AbstractMessageLite;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageOrBuilder;
import com.google.protobuf.Parser;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

public final class Rsp extends GeneratedMessageV3 implements RspOrBuilder {
  private static final long serialVersionUID = 0L;
  
  private int bitField0_;
  
  public static final int A_FIELD_NUMBER = 1;
  
  private int a_;
  
  public static final int B_FIELD_NUMBER = 2;
  
  private int b_;
  
  public static final int C_FIELD_NUMBER = 3;
  
  private int c_;
  
  public static final int D_FIELD_NUMBER = 4;
  
  private volatile Object d_;
  
  public static final int E_FIELD_NUMBER = 5;
  
  private int e_;
  
  public static final int F_FIELD_NUMBER = 6;
  
  private F f_;
  
  public static final int G_FIELD_NUMBER = 7;
  
  private volatile Object g_;
  
  public static final int H_FIELD_NUMBER = 10;
  
  private long h_;
  
  public static final int I_FIELD_NUMBER = 11;
  
  private long i_;
  
  public static final int J_FIELD_NUMBER = 13;
  
  private long j_;
  
  private byte memoizedIsInitialized;
  
  private Rsp(GeneratedMessageV3.Builder<?> builder) {
    super(builder);
    this.a_ = 0;
    this.b_ = 0;
    this.c_ = 0;
    this.d_ = "";
    this.e_ = 0;
    this.g_ = "";
    this.h_ = 0L;
    this.i_ = 0L;
    this.j_ = 0L;
    this.memoizedIsInitialized = -1;
  }
  
  private Rsp() {
    this.a_ = 0;
    this.b_ = 0;
    this.c_ = 0;
    this.d_ = "";
    this.e_ = 0;
    this.g_ = "";
    this.h_ = 0L;
    this.i_ = 0L;
    this.j_ = 0L;
    this.memoizedIsInitialized = -1;
    this.d_ = "";
    this.g_ = "";
  }
  
  protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
    return new Rsp();
  }
  
  public static final Descriptors.Descriptor getDescriptor() {
    return Douyin.internal_static_Rsp_descriptor;
  }
  
  protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
    return Douyin.internal_static_Rsp_fieldAccessorTable.ensureFieldAccessorsInitialized(Rsp.class, Builder.class);
  }
  
  public static final class F extends GeneratedMessageV3 implements FOrBuilder {
    private static final long serialVersionUID = 0L;
    
    public static final int Q1_FIELD_NUMBER = 1;
    
    private long q1_;
    
    public static final int Q3_FIELD_NUMBER = 3;
    
    private long q3_;
    
    public static final int Q4_FIELD_NUMBER = 4;
    
    private volatile Object q4_;
    
    public static final int Q5_FIELD_NUMBER = 5;
    
    private long q5_;
    
    private byte memoizedIsInitialized;
    
    private F(GeneratedMessageV3.Builder<?> builder) {
      super(builder);
      this.q1_ = 0L;
      this.q3_ = 0L;
      this.q4_ = "";
      this.q5_ = 0L;
      this.memoizedIsInitialized = -1;
    }
    
    private F() {
      this.q1_ = 0L;
      this.q3_ = 0L;
      this.q4_ = "";
      this.q5_ = 0L;
      this.memoizedIsInitialized = -1;
      this.q4_ = "";
    }
    
    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
      return new F();
    }
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Douyin.internal_static_Rsp_F_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Douyin.internal_static_Rsp_F_fieldAccessorTable.ensureFieldAccessorsInitialized(F.class, Builder.class);
    }
    
    public long getQ1() {
      return this.q1_;
    }
    
    public long getQ3() {
      return this.q3_;
    }
    
    public String getQ4() {
      Object ref = this.q4_;
      if (ref instanceof String)
        return (String)ref; 
      ByteString bs = (ByteString)ref;
      String s = bs.toStringUtf8();
      this.q4_ = s;
      return s;
    }
    
    public ByteString getQ4Bytes() {
      Object ref = this.q4_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.q4_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public long getQ5() {
      return this.q5_;
    }
    
    public final boolean isInitialized() {
      byte isInitialized = this.memoizedIsInitialized;
      if (isInitialized == 1)
        return true; 
      if (isInitialized == 0)
        return false; 
      this.memoizedIsInitialized = 1;
      return true;
    }
    
    public void writeTo(CodedOutputStream output) throws IOException {
      if (this.q1_ != 0L)
        output.writeUInt64(1, this.q1_); 
      if (this.q3_ != 0L)
        output.writeUInt64(3, this.q3_); 
      if (!GeneratedMessageV3.isStringEmpty(this.q4_))
        GeneratedMessageV3.writeString(output, 4, this.q4_); 
      if (this.q5_ != 0L)
        output.writeUInt64(5, this.q5_); 
      getUnknownFields().writeTo(output);
    }
    
    public int getSerializedSize() {
      int size = this.memoizedSize;
      if (size != -1)
        return size; 
      size = 0;
      if (this.q1_ != 0L)
        size += CodedOutputStream.computeUInt64Size(1, this.q1_); 
      if (this.q3_ != 0L)
        size += CodedOutputStream.computeUInt64Size(3, this.q3_); 
      if (!GeneratedMessageV3.isStringEmpty(this.q4_))
        size += GeneratedMessageV3.computeStringSize(4, this.q4_); 
      if (this.q5_ != 0L)
        size += CodedOutputStream.computeUInt64Size(5, this.q5_); 
      size += getUnknownFields().getSerializedSize();
      this.memoizedSize = size;
      return size;
    }
    
    public boolean equals(Object obj) {
      if (obj == this)
        return true; 
      if (!(obj instanceof F))
        return super.equals(obj); 
      F other = (F)obj;
      if (getQ1() != other.getQ1())
        return false; 
      if (getQ3() != other.getQ3())
        return false; 
      if (!getQ4().equals(other.getQ4()))
        return false; 
      if (getQ5() != other.getQ5())
        return false; 
      if (!getUnknownFields().equals(other.getUnknownFields()))
        return false; 
      return true;
    }
    
    public int hashCode() {
      if (this.memoizedHashCode != 0)
        return this.memoizedHashCode; 
      int hash = 41;
      hash = 19 * hash + getDescriptor().hashCode();
      hash = 37 * hash + 1;
      hash = 53 * hash + Internal.hashLong(getQ1());
      hash = 37 * hash + 3;
      hash = 53 * hash + Internal.hashLong(getQ3());
      hash = 37 * hash + 4;
      hash = 53 * hash + getQ4().hashCode();
      hash = 37 * hash + 5;
      hash = 53 * hash + Internal.hashLong(getQ5());
      hash = 29 * hash + getUnknownFields().hashCode();
      this.memoizedHashCode = hash;
      return hash;
    }
    
    public static F parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
      return (F)PARSER.parseFrom(data);
    }
    
    public static F parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (F)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static F parseFrom(ByteString data) throws InvalidProtocolBufferException {
      return (F)PARSER.parseFrom(data);
    }
    
    public static F parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (F)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static F parseFrom(byte[] data) throws InvalidProtocolBufferException {
      return (F)PARSER.parseFrom(data);
    }
    
    public static F parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
      return (F)PARSER.parseFrom(data, extensionRegistry);
    }
    
    public static F parseFrom(InputStream input) throws IOException {
      return (F)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static F parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (F)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static F parseDelimitedFrom(InputStream input) throws IOException {
      return (F)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }
    
    public static F parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (F)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    
    public static F parseFrom(CodedInputStream input) throws IOException {
      return (F)GeneratedMessageV3.parseWithIOException(PARSER, input);
    }
    
    public static F parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      return (F)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }
    
    public Builder newBuilderForType() {
      return newBuilder();
    }
    
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    
    public static Builder newBuilder(F prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    
    public Builder toBuilder() {
      return (this == DEFAULT_INSTANCE) ? new Builder() : (new Builder()).mergeFrom(this);
    }
    
    protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements Rsp.FOrBuilder {
      private int bitField0_;
      
      private long q1_;
      
      private long q3_;
      
      private Object q4_;
      
      private long q5_;
      
      public static final Descriptors.Descriptor getDescriptor() {
        return Douyin.internal_static_Rsp_F_descriptor;
      }
      
      protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return Douyin.internal_static_Rsp_F_fieldAccessorTable.ensureFieldAccessorsInitialized(Rsp.F.class, Builder.class);
      }
      
      private Builder() {
        this.q4_ = "";
      }
      
      private Builder(GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        this.q4_ = "";
      }
      
      public Builder clear() {
        super.clear();
        this.bitField0_ = 0;
        this.q1_ = 0L;
        this.q3_ = 0L;
        this.q4_ = "";
        this.q5_ = 0L;
        return this;
      }
      
      public Descriptors.Descriptor getDescriptorForType() {
        return Douyin.internal_static_Rsp_F_descriptor;
      }
      
      public Rsp.F getDefaultInstanceForType() {
        return Rsp.F.getDefaultInstance();
      }
      
      public Rsp.F build() {
        Rsp.F result = buildPartial();
        if (!result.isInitialized())
          throw newUninitializedMessageException(result); 
        return result;
      }
      
      public Rsp.F buildPartial() {
        Rsp.F result = new Rsp.F(this);
        if (this.bitField0_ != 0)
          buildPartial0(result); 
        onBuilt();
        return result;
      }
      
      private void buildPartial0(Rsp.F result) {
        int from_bitField0_ = this.bitField0_;
        if ((from_bitField0_ & 0x1) != 0)
          result.q1_ = this.q1_; 
        if ((from_bitField0_ & 0x2) != 0)
          result.q3_ = this.q3_; 
        if ((from_bitField0_ & 0x4) != 0)
          result.q4_ = this.q4_; 
        if ((from_bitField0_ & 0x8) != 0)
          result.q5_ = this.q5_; 
      }
      
      public Builder clone() {
        return (Builder)super.clone();
      }
      
      public Builder setField(Descriptors.FieldDescriptor field, Object value) {
        return (Builder)super.setField(field, value);
      }
      
      public Builder clearField(Descriptors.FieldDescriptor field) {
        return (Builder)super.clearField(field);
      }
      
      public Builder clearOneof(Descriptors.OneofDescriptor oneof) {
        return (Builder)super.clearOneof(oneof);
      }
      
      public Builder setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
        return (Builder)super.setRepeatedField(field, index, value);
      }
      
      public Builder addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
        return (Builder)super.addRepeatedField(field, value);
      }
      
      public Builder mergeFrom(Message other) {
        if (other instanceof Rsp.F)
          return mergeFrom((Rsp.F)other); 
        super.mergeFrom(other);
        return this;
      }
      
      public Builder mergeFrom(Rsp.F other) {
        if (other == Rsp.F.getDefaultInstance())
          return this; 
        if (other.getQ1() != 0L)
          setQ1(other.getQ1()); 
        if (other.getQ3() != 0L)
          setQ3(other.getQ3()); 
        if (!other.getQ4().isEmpty()) {
          this.q4_ = other.q4_;
          this.bitField0_ |= 0x4;
          onChanged();
        } 
        if (other.getQ5() != 0L)
          setQ5(other.getQ5()); 
        mergeUnknownFields(other.getUnknownFields());
        onChanged();
        return this;
      }
      
      public final boolean isInitialized() {
        return true;
      }
      
      public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        if (extensionRegistry == null)
          throw new NullPointerException(); 
        try {
          boolean done = false;
          while (!done) {
            int tag = input.readTag();
            switch (tag) {
              case 0:
                done = true;
                continue;
              case 8:
                this.q1_ = input.readUInt64();
                this.bitField0_ |= 0x1;
                continue;
              case 24:
                this.q3_ = input.readUInt64();
                this.bitField0_ |= 0x2;
                continue;
              case 34:
                this.q4_ = input.readStringRequireUtf8();
                this.bitField0_ |= 0x4;
                continue;
              case 40:
                this.q5_ = input.readUInt64();
                this.bitField0_ |= 0x8;
                continue;
            } 
            if (!parseUnknownField(input, extensionRegistry, tag))
              done = true; 
          } 
        } catch (InvalidProtocolBufferException e) {
          throw e.unwrapIOException();
        } finally {
          onChanged();
        } 
        return this;
      }
      
      public long getQ1() {
        return this.q1_;
      }
      
      public Builder setQ1(long value) {
        this.q1_ = value;
        this.bitField0_ |= 0x1;
        onChanged();
        return this;
      }
      
      public Builder clearQ1() {
        this.bitField0_ &= 0xFFFFFFFE;
        this.q1_ = 0L;
        onChanged();
        return this;
      }
      
      public long getQ3() {
        return this.q3_;
      }
      
      public Builder setQ3(long value) {
        this.q3_ = value;
        this.bitField0_ |= 0x2;
        onChanged();
        return this;
      }
      
      public Builder clearQ3() {
        this.bitField0_ &= 0xFFFFFFFD;
        this.q3_ = 0L;
        onChanged();
        return this;
      }
      
      public String getQ4() {
        Object ref = this.q4_;
        if (!(ref instanceof String)) {
          ByteString bs = (ByteString)ref;
          String s = bs.toStringUtf8();
          this.q4_ = s;
          return s;
        } 
        return (String)ref;
      }
      
      public ByteString getQ4Bytes() {
        Object ref = this.q4_;
        if (ref instanceof String) {
          ByteString b = ByteString.copyFromUtf8((String)ref);
          this.q4_ = b;
          return b;
        } 
        return (ByteString)ref;
      }
      
      public Builder setQ4(String value) {
        if (value == null)
          throw new NullPointerException(); 
        this.q4_ = value;
        this.bitField0_ |= 0x4;
        onChanged();
        return this;
      }
      
      public Builder clearQ4() {
        this.q4_ = Rsp.F.getDefaultInstance().getQ4();
        this.bitField0_ &= 0xFFFFFFFB;
        onChanged();
        return this;
      }
      
      public Builder setQ4Bytes(ByteString value) {
        if (value == null)
          throw new NullPointerException(); 
        Rsp.F.checkByteStringIsUtf8(value);
        this.q4_ = value;
        this.bitField0_ |= 0x4;
        onChanged();
        return this;
      }
      
      public long getQ5() {
        return this.q5_;
      }
      
      public Builder setQ5(long value) {
        this.q5_ = value;
        this.bitField0_ |= 0x8;
        onChanged();
        return this;
      }
      
      public Builder clearQ5() {
        this.bitField0_ &= 0xFFFFFFF7;
        this.q5_ = 0L;
        onChanged();
        return this;
      }
      
      public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
        return (Builder)super.setUnknownFields(unknownFields);
      }
      
      public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
        return (Builder)super.mergeUnknownFields(unknownFields);
      }
    }
    
    private static final F DEFAULT_INSTANCE = new F();
    
    public static F getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }
    
    private static final Parser<F> PARSER = (Parser<F>)new AbstractParser<F>() {
        public Rsp.F parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
          Rsp.F.Builder builder = Rsp.F.newBuilder();
          try {
            builder.mergeFrom(input, extensionRegistry);
          } catch (InvalidProtocolBufferException e) {
            throw e.setUnfinishedMessage(builder.buildPartial());
          } catch (UninitializedMessageException e) {
            throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
          } catch (IOException e) {
            throw (new InvalidProtocolBufferException(e)).setUnfinishedMessage(builder.buildPartial());
          } 
          return builder.buildPartial();
        }
      };
    
    public static Parser<F> parser() {
      return PARSER;
    }
    
    public Parser<F> getParserForType() {
      return PARSER;
    }
    
    public F getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }
  }
  
  public int getA() {
    return this.a_;
  }
  
  public int getB() {
    return this.b_;
  }
  
  public int getC() {
    return this.c_;
  }
  
  public String getD() {
    Object ref = this.d_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.d_ = s;
    return s;
  }
  
  public ByteString getDBytes() {
    Object ref = this.d_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.d_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public int getE() {
    return this.e_;
  }
  
  public boolean hasF() {
    return ((this.bitField0_ & 0x1) != 0);
  }
  
  public F getF() {
    return (this.f_ == null) ? F.getDefaultInstance() : this.f_;
  }
  
  public FOrBuilder getFOrBuilder() {
    return (this.f_ == null) ? F.getDefaultInstance() : this.f_;
  }
  
  public String getG() {
    Object ref = this.g_;
    if (ref instanceof String)
      return (String)ref; 
    ByteString bs = (ByteString)ref;
    String s = bs.toStringUtf8();
    this.g_ = s;
    return s;
  }
  
  public ByteString getGBytes() {
    Object ref = this.g_;
    if (ref instanceof String) {
      ByteString b = ByteString.copyFromUtf8((String)ref);
      this.g_ = b;
      return b;
    } 
    return (ByteString)ref;
  }
  
  public long getH() {
    return this.h_;
  }
  
  public long getI() {
    return this.i_;
  }
  
  public long getJ() {
    return this.j_;
  }
  
  public final boolean isInitialized() {
    byte isInitialized = this.memoizedIsInitialized;
    if (isInitialized == 1)
      return true; 
    if (isInitialized == 0)
      return false; 
    this.memoizedIsInitialized = 1;
    return true;
  }
  
  public void writeTo(CodedOutputStream output) throws IOException {
    if (this.a_ != 0)
      output.writeInt32(1, this.a_); 
    if (this.b_ != 0)
      output.writeInt32(2, this.b_); 
    if (this.c_ != 0)
      output.writeInt32(3, this.c_); 
    if (!GeneratedMessageV3.isStringEmpty(this.d_))
      GeneratedMessageV3.writeString(output, 4, this.d_); 
    if (this.e_ != 0)
      output.writeInt32(5, this.e_); 
    if ((this.bitField0_ & 0x1) != 0)
      output.writeMessage(6, (MessageLite)getF()); 
    if (!GeneratedMessageV3.isStringEmpty(this.g_))
      GeneratedMessageV3.writeString(output, 7, this.g_); 
    if (this.h_ != 0L)
      output.writeUInt64(10, this.h_); 
    if (this.i_ != 0L)
      output.writeUInt64(11, this.i_); 
    if (this.j_ != 0L)
      output.writeUInt64(13, this.j_); 
    getUnknownFields().writeTo(output);
  }
  
  public int getSerializedSize() {
    int size = this.memoizedSize;
    if (size != -1)
      return size; 
    size = 0;
    if (this.a_ != 0)
      size += 
        CodedOutputStream.computeInt32Size(1, this.a_); 
    if (this.b_ != 0)
      size += 
        CodedOutputStream.computeInt32Size(2, this.b_); 
    if (this.c_ != 0)
      size += 
        CodedOutputStream.computeInt32Size(3, this.c_); 
    if (!GeneratedMessageV3.isStringEmpty(this.d_))
      size += GeneratedMessageV3.computeStringSize(4, this.d_); 
    if (this.e_ != 0)
      size += 
        CodedOutputStream.computeInt32Size(5, this.e_); 
    if ((this.bitField0_ & 0x1) != 0)
      size += 
        CodedOutputStream.computeMessageSize(6, (MessageLite)getF()); 
    if (!GeneratedMessageV3.isStringEmpty(this.g_))
      size += GeneratedMessageV3.computeStringSize(7, this.g_); 
    if (this.h_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(10, this.h_); 
    if (this.i_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(11, this.i_); 
    if (this.j_ != 0L)
      size += 
        CodedOutputStream.computeUInt64Size(13, this.j_); 
    size += getUnknownFields().getSerializedSize();
    this.memoizedSize = size;
    return size;
  }
  
  public boolean equals(Object obj) {
    if (obj == this)
      return true; 
    if (!(obj instanceof Rsp))
      return super.equals(obj); 
    Rsp other = (Rsp)obj;
    if (getA() != other
      .getA())
      return false; 
    if (getB() != other
      .getB())
      return false; 
    if (getC() != other
      .getC())
      return false; 
    if (!getD().equals(other.getD()))
      return false; 
    if (getE() != other
      .getE())
      return false; 
    if (hasF() != other.hasF())
      return false; 
    if (hasF() && 
      
      !getF().equals(other.getF()))
      return false; 
    if (!getG().equals(other.getG()))
      return false; 
    if (getH() != other
      .getH())
      return false; 
    if (getI() != other
      .getI())
      return false; 
    if (getJ() != other
      .getJ())
      return false; 
    if (!getUnknownFields().equals(other.getUnknownFields()))
      return false; 
    return true;
  }
  
  public int hashCode() {
    if (this.memoizedHashCode != 0)
      return this.memoizedHashCode; 
    int hash = 41;
    hash = 19 * hash + getDescriptor().hashCode();
    hash = 37 * hash + 1;
    hash = 53 * hash + getA();
    hash = 37 * hash + 2;
    hash = 53 * hash + getB();
    hash = 37 * hash + 3;
    hash = 53 * hash + getC();
    hash = 37 * hash + 4;
    hash = 53 * hash + getD().hashCode();
    hash = 37 * hash + 5;
    hash = 53 * hash + getE();
    if (hasF()) {
      hash = 37 * hash + 6;
      hash = 53 * hash + getF().hashCode();
    } 
    hash = 37 * hash + 7;
    hash = 53 * hash + getG().hashCode();
    hash = 37 * hash + 10;
    hash = 53 * hash + Internal.hashLong(
        getH());
    hash = 37 * hash + 11;
    hash = 53 * hash + Internal.hashLong(
        getI());
    hash = 37 * hash + 13;
    hash = 53 * hash + Internal.hashLong(
        getJ());
    hash = 29 * hash + getUnknownFields().hashCode();
    this.memoizedHashCode = hash;
    return hash;
  }
  
  public static Rsp parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
    return (Rsp)PARSER.parseFrom(data);
  }
  
  public static Rsp parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (Rsp)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static Rsp parseFrom(ByteString data) throws InvalidProtocolBufferException {
    return (Rsp)PARSER.parseFrom(data);
  }
  
  public static Rsp parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (Rsp)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static Rsp parseFrom(byte[] data) throws InvalidProtocolBufferException {
    return (Rsp)PARSER.parseFrom(data);
  }
  
  public static Rsp parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
    return (Rsp)PARSER.parseFrom(data, extensionRegistry);
  }
  
  public static Rsp parseFrom(InputStream input) throws IOException {
    return 
      (Rsp)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static Rsp parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (Rsp)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static Rsp parseDelimitedFrom(InputStream input) throws IOException {
    return 
      (Rsp)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }
  
  public static Rsp parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (Rsp)GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  
  public static Rsp parseFrom(CodedInputStream input) throws IOException {
    return 
      (Rsp)GeneratedMessageV3.parseWithIOException(PARSER, input);
  }
  
  public static Rsp parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
    return 
      (Rsp)GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
  }
  
  public Builder newBuilderForType() {
    return newBuilder();
  }
  
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  
  public static Builder newBuilder(Rsp prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  
  public Builder toBuilder() {
    return (this == DEFAULT_INSTANCE) ? 
      new Builder() : (new Builder()).mergeFrom(this);
  }
  
  protected Builder newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  
  public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements RspOrBuilder {
    private int bitField0_;
    
    private int a_;
    
    private int b_;
    
    private int c_;
    
    private Object d_;
    
    private int e_;
    
    private Rsp.F f_;
    
    private SingleFieldBuilderV3<Rsp.F, Rsp.F.Builder, Rsp.FOrBuilder> fBuilder_;
    
    private Object g_;
    
    private long h_;
    
    private long i_;
    
    private long j_;
    
    public static final Descriptors.Descriptor getDescriptor() {
      return Douyin.internal_static_Rsp_descriptor;
    }
    
    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
      return Douyin.internal_static_Rsp_fieldAccessorTable
        .ensureFieldAccessorsInitialized(Rsp.class, Builder.class);
    }
    
    private Builder() {
      this.d_ = "";
      this.g_ = "";
      maybeForceBuilderInitialization();
    }
    
    private Builder(GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      this.d_ = "";
      this.g_ = "";
      maybeForceBuilderInitialization();
    }
    
    private void maybeForceBuilderInitialization() {
      if (Rsp.alwaysUseFieldBuilders)
        getFFieldBuilder(); 
    }
    
    public Builder clear() {
      super.clear();
      this.bitField0_ = 0;
      this.a_ = 0;
      this.b_ = 0;
      this.c_ = 0;
      this.d_ = "";
      this.e_ = 0;
      this.f_ = null;
      if (this.fBuilder_ != null) {
        this.fBuilder_.dispose();
        this.fBuilder_ = null;
      } 
      this.g_ = "";
      this.h_ = 0L;
      this.i_ = 0L;
      this.j_ = 0L;
      return this;
    }
    
    public Descriptors.Descriptor getDescriptorForType() {
      return Douyin.internal_static_Rsp_descriptor;
    }
    
    public Rsp getDefaultInstanceForType() {
      return Rsp.getDefaultInstance();
    }
    
    public Rsp build() {
      Rsp result = buildPartial();
      if (!result.isInitialized())
        throw newUninitializedMessageException(result); 
      return result;
    }
    
    public Rsp buildPartial() {
      Rsp result = new Rsp(this);
      if (this.bitField0_ != 0)
        buildPartial0(result); 
      onBuilt();
      return result;
    }
    
    private void buildPartial0(Rsp result) {
      int from_bitField0_ = this.bitField0_;
      if ((from_bitField0_ & 0x1) != 0)
        result.a_ = this.a_; 
      if ((from_bitField0_ & 0x2) != 0)
        result.b_ = this.b_; 
      if ((from_bitField0_ & 0x4) != 0)
        result.c_ = this.c_; 
      if ((from_bitField0_ & 0x8) != 0)
        result.d_ = this.d_; 
      if ((from_bitField0_ & 0x10) != 0)
        result.e_ = this.e_; 
      int to_bitField0_ = 0;
      if ((from_bitField0_ & 0x20) != 0) {
        result.f_ = (this.fBuilder_ == null) ? this.f_ : (Rsp.F)this.fBuilder_.build();
        to_bitField0_ |= 0x1;
      } 
      if ((from_bitField0_ & 0x40) != 0)
        result.g_ = this.g_; 
      if ((from_bitField0_ & 0x80) != 0)
        result.h_ = this.h_; 
      if ((from_bitField0_ & 0x100) != 0)
        result.i_ = this.i_; 
      if ((from_bitField0_ & 0x200) != 0)
        result.j_ = this.j_; 
      result.bitField0_ |= to_bitField0_;
    }
    
    public Builder clone() {
      return (Builder)super.clone();
    }
    
    public Builder setField(Descriptors.FieldDescriptor field, Object value) {
      return (Builder)super.setField(field, value);
    }
    
    public Builder clearField(Descriptors.FieldDescriptor field) {
      return (Builder)super.clearField(field);
    }
    
    public Builder clearOneof(Descriptors.OneofDescriptor oneof) {
      return (Builder)super.clearOneof(oneof);
    }
    
    public Builder setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
      return (Builder)super.setRepeatedField(field, index, value);
    }
    
    public Builder addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
      return (Builder)super.addRepeatedField(field, value);
    }
    
    public Builder mergeFrom(Message other) {
      if (other instanceof Rsp)
        return mergeFrom((Rsp)other); 
      super.mergeFrom(other);
      return this;
    }
    
    public Builder mergeFrom(Rsp other) {
      if (other == Rsp.getDefaultInstance())
        return this; 
      if (other.getA() != 0)
        setA(other.getA()); 
      if (other.getB() != 0)
        setB(other.getB()); 
      if (other.getC() != 0)
        setC(other.getC()); 
      if (!other.getD().isEmpty()) {
        this.d_ = other.d_;
        this.bitField0_ |= 0x8;
        onChanged();
      } 
      if (other.getE() != 0)
        setE(other.getE()); 
      if (other.hasF())
        mergeF(other.getF()); 
      if (!other.getG().isEmpty()) {
        this.g_ = other.g_;
        this.bitField0_ |= 0x40;
        onChanged();
      } 
      if (other.getH() != 0L)
        setH(other.getH()); 
      if (other.getI() != 0L)
        setI(other.getI()); 
      if (other.getJ() != 0L)
        setJ(other.getJ()); 
      mergeUnknownFields(other.getUnknownFields());
      onChanged();
      return this;
    }
    
    public final boolean isInitialized() {
      return true;
    }
    
    public Builder mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
      if (extensionRegistry == null)
        throw new NullPointerException(); 
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              continue;
            case 8:
              this.a_ = input.readInt32();
              this.bitField0_ |= 0x1;
              continue;
            case 16:
              this.b_ = input.readInt32();
              this.bitField0_ |= 0x2;
              continue;
            case 24:
              this.c_ = input.readInt32();
              this.bitField0_ |= 0x4;
              continue;
            case 34:
              this.d_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x8;
              continue;
            case 40:
              this.e_ = input.readInt32();
              this.bitField0_ |= 0x10;
              continue;
            case 50:
              input.readMessage((MessageLite.Builder)getFFieldBuilder().getBuilder(), extensionRegistry);
              this.bitField0_ |= 0x20;
              continue;
            case 58:
              this.g_ = input.readStringRequireUtf8();
              this.bitField0_ |= 0x40;
              continue;
            case 80:
              this.h_ = input.readUInt64();
              this.bitField0_ |= 0x80;
              continue;
            case 88:
              this.i_ = input.readUInt64();
              this.bitField0_ |= 0x100;
              continue;
            case 104:
              this.j_ = input.readUInt64();
              this.bitField0_ |= 0x200;
              continue;
          } 
          if (!parseUnknownField(input, extensionRegistry, tag))
            done = true; 
        } 
      } catch (InvalidProtocolBufferException e) {
        throw e.unwrapIOException();
      } finally {
        onChanged();
      } 
      return this;
    }
    
    public int getA() {
      return this.a_;
    }
    
    public Builder setA(int value) {
      this.a_ = value;
      this.bitField0_ |= 0x1;
      onChanged();
      return this;
    }
    
    public Builder clearA() {
      this.bitField0_ &= 0xFFFFFFFE;
      this.a_ = 0;
      onChanged();
      return this;
    }
    
    public int getB() {
      return this.b_;
    }
    
    public Builder setB(int value) {
      this.b_ = value;
      this.bitField0_ |= 0x2;
      onChanged();
      return this;
    }
    
    public Builder clearB() {
      this.bitField0_ &= 0xFFFFFFFD;
      this.b_ = 0;
      onChanged();
      return this;
    }
    
    public int getC() {
      return this.c_;
    }
    
    public Builder setC(int value) {
      this.c_ = value;
      this.bitField0_ |= 0x4;
      onChanged();
      return this;
    }
    
    public Builder clearC() {
      this.bitField0_ &= 0xFFFFFFFB;
      this.c_ = 0;
      onChanged();
      return this;
    }
    
    public String getD() {
      Object ref = this.d_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.d_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getDBytes() {
      Object ref = this.d_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.d_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setD(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.d_ = value;
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public Builder clearD() {
      this.d_ = Rsp.getDefaultInstance().getD();
      this.bitField0_ &= 0xFFFFFFF7;
      onChanged();
      return this;
    }
    
    public Builder setDBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      Rsp.checkByteStringIsUtf8(value);
      this.d_ = value;
      this.bitField0_ |= 0x8;
      onChanged();
      return this;
    }
    
    public int getE() {
      return this.e_;
    }
    
    public Builder setE(int value) {
      this.e_ = value;
      this.bitField0_ |= 0x10;
      onChanged();
      return this;
    }
    
    public Builder clearE() {
      this.bitField0_ &= 0xFFFFFFEF;
      this.e_ = 0;
      onChanged();
      return this;
    }
    
    public boolean hasF() {
      return ((this.bitField0_ & 0x20) != 0);
    }
    
    public Rsp.F getF() {
      if (this.fBuilder_ == null)
        return (this.f_ == null) ? Rsp.F.getDefaultInstance() : this.f_; 
      return (Rsp.F)this.fBuilder_.getMessage();
    }
    
    public Builder setF(Rsp.F value) {
      if (this.fBuilder_ == null) {
        if (value == null)
          throw new NullPointerException(); 
        this.f_ = value;
      } else {
        this.fBuilder_.setMessage((AbstractMessage)value);
      } 
      this.bitField0_ |= 0x20;
      onChanged();
      return this;
    }
    
    public Builder setF(Rsp.F.Builder builderForValue) {
      if (this.fBuilder_ == null) {
        this.f_ = builderForValue.build();
      } else {
        this.fBuilder_.setMessage((AbstractMessage)builderForValue.build());
      } 
      this.bitField0_ |= 0x20;
      onChanged();
      return this;
    }
    
    public Builder mergeF(Rsp.F value) {
      if (this.fBuilder_ == null) {
        if ((this.bitField0_ & 0x20) != 0 && this.f_ != null && this.f_ != Rsp.F.getDefaultInstance()) {
          getFBuilder().mergeFrom(value);
        } else {
          this.f_ = value;
        } 
      } else {
        this.fBuilder_.mergeFrom((AbstractMessage)value);
      } 
      if (this.f_ != null) {
        this.bitField0_ |= 0x20;
        onChanged();
      } 
      return this;
    }
    
    public Builder clearF() {
      this.bitField0_ &= 0xFFFFFFDF;
      this.f_ = null;
      if (this.fBuilder_ != null) {
        this.fBuilder_.dispose();
        this.fBuilder_ = null;
      } 
      onChanged();
      return this;
    }
    
    public Rsp.F.Builder getFBuilder() {
      this.bitField0_ |= 0x20;
      onChanged();
      return (Rsp.F.Builder)getFFieldBuilder().getBuilder();
    }
    
    public Rsp.FOrBuilder getFOrBuilder() {
      if (this.fBuilder_ != null)
        return (Rsp.FOrBuilder)this.fBuilder_.getMessageOrBuilder(); 
      return (this.f_ == null) ? Rsp.F.getDefaultInstance() : this.f_;
    }
    
    private SingleFieldBuilderV3<Rsp.F, Rsp.F.Builder, Rsp.FOrBuilder> getFFieldBuilder() {
      if (this.fBuilder_ == null) {
        this.fBuilder_ = new SingleFieldBuilderV3((AbstractMessage)getF(), (AbstractMessage.BuilderParent)getParentForChildren(), isClean());
        this.f_ = null;
      } 
      return this.fBuilder_;
    }
    
    public String getG() {
      Object ref = this.g_;
      if (!(ref instanceof String)) {
        ByteString bs = (ByteString)ref;
        String s = bs.toStringUtf8();
        this.g_ = s;
        return s;
      } 
      return (String)ref;
    }
    
    public ByteString getGBytes() {
      Object ref = this.g_;
      if (ref instanceof String) {
        ByteString b = ByteString.copyFromUtf8((String)ref);
        this.g_ = b;
        return b;
      } 
      return (ByteString)ref;
    }
    
    public Builder setG(String value) {
      if (value == null)
        throw new NullPointerException(); 
      this.g_ = value;
      this.bitField0_ |= 0x40;
      onChanged();
      return this;
    }
    
    public Builder clearG() {
      this.g_ = Rsp.getDefaultInstance().getG();
      this.bitField0_ &= 0xFFFFFFBF;
      onChanged();
      return this;
    }
    
    public Builder setGBytes(ByteString value) {
      if (value == null)
        throw new NullPointerException(); 
      Rsp.checkByteStringIsUtf8(value);
      this.g_ = value;
      this.bitField0_ |= 0x40;
      onChanged();
      return this;
    }
    
    public long getH() {
      return this.h_;
    }
    
    public Builder setH(long value) {
      this.h_ = value;
      this.bitField0_ |= 0x80;
      onChanged();
      return this;
    }
    
    public Builder clearH() {
      this.bitField0_ &= 0xFFFFFF7F;
      this.h_ = 0L;
      onChanged();
      return this;
    }
    
    public long getI() {
      return this.i_;
    }
    
    public Builder setI(long value) {
      this.i_ = value;
      this.bitField0_ |= 0x100;
      onChanged();
      return this;
    }
    
    public Builder clearI() {
      this.bitField0_ &= 0xFFFFFEFF;
      this.i_ = 0L;
      onChanged();
      return this;
    }
    
    public long getJ() {
      return this.j_;
    }
    
    public Builder setJ(long value) {
      this.j_ = value;
      this.bitField0_ |= 0x200;
      onChanged();
      return this;
    }
    
    public Builder clearJ() {
      this.bitField0_ &= 0xFFFFFDFF;
      this.j_ = 0L;
      onChanged();
      return this;
    }
    
    public final Builder setUnknownFields(UnknownFieldSet unknownFields) {
      return (Builder)super.setUnknownFields(unknownFields);
    }
    
    public final Builder mergeUnknownFields(UnknownFieldSet unknownFields) {
      return (Builder)super.mergeUnknownFields(unknownFields);
    }
  }
  
  private static final Rsp DEFAULT_INSTANCE = new Rsp();
  
  public static Rsp getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }
  
  private static final Parser<Rsp> PARSER = (Parser<Rsp>)new AbstractParser<Rsp>() {
      public Rsp parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        Rsp.Builder builder = Rsp.newBuilder();
        try {
          builder.mergeFrom(input, extensionRegistry);
        } catch (InvalidProtocolBufferException e) {
          throw e.setUnfinishedMessage(builder.buildPartial());
        } catch (UninitializedMessageException e) {
          throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
        } catch (IOException e) {
          throw (new InvalidProtocolBufferException(e))
            .setUnfinishedMessage(builder.buildPartial());
        } 
        return builder.buildPartial();
      }
    };
  
  public static Parser<Rsp> parser() {
    return PARSER;
  }
  
  public Parser<Rsp> getParserForType() {
    return PARSER;
  }
  
  public Rsp getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
  
  public static interface FOrBuilder extends MessageOrBuilder {
    long getQ1();
    
    long getQ3();
    
    String getQ4();
    
    ByteString getQ4Bytes();
    
    long getQ5();
  }
}
