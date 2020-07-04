package com.dxp.netty.protocol.protobuf;

public final class MessageBase {
  private MessageBase() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface MessageOrBuilder extends
      // @@protoc_insertion_point(interface_extends:Message)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>string requestId = 1;</code>
     * @return The requestId.
     */
    String getRequestId();
    /**
     * <code>string requestId = 1;</code>
     * @return The bytes for requestId.
     */
    com.google.protobuf.ByteString
        getRequestIdBytes();

    /**
     * <code>.Message.CommandType cmd = 2;</code>
     * @return The enum numeric value on the wire for cmd.
     */
    int getCmdValue();
    /**
     * <code>.Message.CommandType cmd = 2;</code>
     * @return The cmd.
     */
    MessageBase.Message.CommandType getCmd();

    /**
     * <code>string content = 3;</code>
     * @return The content.
     */
    String getContent();
    /**
     * <code>string content = 3;</code>
     * @return The bytes for content.
     */
    com.google.protobuf.ByteString
        getContentBytes();
  }
  /**
   * Protobuf type {@code Message}
   */
  public static final class Message extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:Message)
      MessageOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use Message.newBuilder() to construct.
    private Message(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private Message() {
      requestId_ = "";
      cmd_ = 0;
      content_ = "";
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new Message();
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private Message(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 10: {
              java.lang.String s = input.readStringRequireUtf8();

              requestId_ = s;
              break;
            }
            case 16: {
              int rawValue = input.readEnum();

              cmd_ = rawValue;
              break;
            }
            case 26: {
              java.lang.String s = input.readStringRequireUtf8();

              content_ = s;
              break;
            }
            default: {
              if (!parseUnknownField(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return MessageBase.internal_static_Message_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return MessageBase.internal_static_Message_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              MessageBase.Message.class, MessageBase.Message.Builder.class);
    }

    /**
     * Protobuf enum {@code Message.CommandType}
     */
    public enum CommandType
        implements com.google.protobuf.ProtocolMessageEnum {
      /**
       * <pre>
       *常规业务消息
       * </pre>
       *
       * <code>NORMAL = 0;</code>
       */
      NORMAL(0),
      /**
       * <pre>
       *客户端心跳消息
       * </pre>
       *
       * <code>HEARTBEAT_REQUEST = 1;</code>
       */
      HEARTBEAT_REQUEST(1),
      /**
       * <pre>
       *服务端心跳消息
       * </pre>
       *
       * <code>HEARTBEAT_RESPONSE = 2;</code>
       */
      HEARTBEAT_RESPONSE(2),
      /**
       * <pre>
       *注册
       * </pre>
       *
       * <code>REGISTER_REQUEST = 3;</code>
       */
      REGISTER_REQUEST(3),
      /**
       * <pre>
       *注册响应
       * </pre>
       *
       * <code>REGISTER_RESPONSE = 4;</code>
       */
      REGISTER_RESPONSE(4),
      UNRECOGNIZED(-1),
      ;

      /**
       * <pre>
       *常规业务消息
       * </pre>
       *
       * <code>NORMAL = 0;</code>
       */
      public static final int NORMAL_VALUE = 0;
      /**
       * <pre>
       *客户端心跳消息
       * </pre>
       *
       * <code>HEARTBEAT_REQUEST = 1;</code>
       */
      public static final int HEARTBEAT_REQUEST_VALUE = 1;
      /**
       * <pre>
       *服务端心跳消息
       * </pre>
       *
       * <code>HEARTBEAT_RESPONSE = 2;</code>
       */
      public static final int HEARTBEAT_RESPONSE_VALUE = 2;
      /**
       * <pre>
       *注册
       * </pre>
       *
       * <code>REGISTER_REQUEST = 3;</code>
       */
      public static final int REGISTER_REQUEST_VALUE = 3;
      /**
       * <pre>
       *注册响应
       * </pre>
       *
       * <code>REGISTER_RESPONSE = 4;</code>
       */
      public static final int REGISTER_RESPONSE_VALUE = 4;


      @Override
      public final int getNumber() {
        if (this == UNRECOGNIZED) {
          throw new java.lang.IllegalArgumentException(
              "Can't get the number of an unknown enum value.");
        }
        return value;
      }

      /**
       * @param value The numeric wire value of the corresponding enum entry.
       * @return The enum associated with the given numeric wire value.
       * @deprecated Use {@link #forNumber(int)} instead.
       */
      @java.lang.Deprecated
      public static CommandType valueOf(int value) {
        return forNumber(value);
      }

      /**
       * @param value The numeric wire value of the corresponding enum entry.
       * @return The enum associated with the given numeric wire value.
       */
      public static CommandType forNumber(int value) {
        switch (value) {
          case 0: return NORMAL;
          case 1: return HEARTBEAT_REQUEST;
          case 2: return HEARTBEAT_RESPONSE;
          case 3: return REGISTER_REQUEST;
          case 4: return REGISTER_RESPONSE;
          default: return null;
        }
      }

      public static com.google.protobuf.Internal.EnumLiteMap<CommandType>
          internalGetValueMap() {
        return internalValueMap;
      }
      private static final com.google.protobuf.Internal.EnumLiteMap<
          CommandType> internalValueMap =
            new com.google.protobuf.Internal.EnumLiteMap<CommandType>() {
              @Override
              public CommandType findValueByNumber(int number) {
                return CommandType.forNumber(number);
              }
            };

      @Override
      public final com.google.protobuf.Descriptors.EnumValueDescriptor
          getValueDescriptor() {
        if (this == UNRECOGNIZED) {
          throw new java.lang.IllegalStateException(
              "Can't get the descriptor of an unrecognized enum value.");
        }
        return getDescriptor().getValues().get(ordinal());
      }
      @Override
      public final com.google.protobuf.Descriptors.EnumDescriptor
          getDescriptorForType() {
        return getDescriptor();
      }
      public static final com.google.protobuf.Descriptors.EnumDescriptor
          getDescriptor() {
        return MessageBase.Message.getDescriptor().getEnumTypes().get(0);
      }

      private static final CommandType[] VALUES = values();

      public static CommandType valueOf(
          com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
        if (desc.getType() != getDescriptor()) {
          throw new java.lang.IllegalArgumentException(
            "EnumValueDescriptor is not for this type.");
        }
        if (desc.getIndex() == -1) {
          return UNRECOGNIZED;
        }
        return VALUES[desc.getIndex()];
      }

      private final int value;

      private CommandType(int value) {
        this.value = value;
      }

      // @@protoc_insertion_point(enum_scope:Message.CommandType)
    }

    public static final int REQUESTID_FIELD_NUMBER = 1;
    private volatile java.lang.Object requestId_;
    /**
     * <code>string requestId = 1;</code>
     * @return The requestId.
     */
    @java.lang.Override
    public java.lang.String getRequestId() {
      java.lang.Object ref = requestId_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        requestId_ = s;
        return s;
      }
    }
    /**
     * <code>string requestId = 1;</code>
     * @return The bytes for requestId.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString
        getRequestIdBytes() {
      java.lang.Object ref = requestId_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        requestId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int CMD_FIELD_NUMBER = 2;
    private int cmd_;
    /**
     * <code>.Message.CommandType cmd = 2;</code>
     * @return The enum numeric value on the wire for cmd.
     */
    @java.lang.Override public int getCmdValue() {
      return cmd_;
    }
    /**
     * <code>.Message.CommandType cmd = 2;</code>
     * @return The cmd.
     */
    @java.lang.Override public MessageBase.Message.CommandType getCmd() {
      @SuppressWarnings("deprecation")
      MessageBase.Message.CommandType result = MessageBase.Message.CommandType.valueOf(cmd_);
      return result == null ? MessageBase.Message.CommandType.UNRECOGNIZED : result;
    }

    public static final int CONTENT_FIELD_NUMBER = 3;
    private volatile java.lang.Object content_;
    /**
     * <code>string content = 3;</code>
     * @return The content.
     */
    @java.lang.Override
    public java.lang.String getContent() {
      java.lang.Object ref = content_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        content_ = s;
        return s;
      }
    }
    /**
     * <code>string content = 3;</code>
     * @return The bytes for content.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString
        getContentBytes() {
      java.lang.Object ref = content_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        content_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    private byte memoizedIsInitialized = -1;
    @java.lang.Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) {
        return true;
      }
      if (isInitialized == 0) {
        return false;
      }

      memoizedIsInitialized = 1;
      return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (!getRequestIdBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, requestId_);
      }
      if (cmd_ != MessageBase.Message.CommandType.NORMAL.getNumber()) {
        output.writeEnum(2, cmd_);
      }
      if (!getContentBytes().isEmpty()) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 3, content_);
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) {
        return size;
      }

      size = 0;
      if (!getRequestIdBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, requestId_);
      }
      if (cmd_ != MessageBase.Message.CommandType.NORMAL.getNumber()) {
        size += com.google.protobuf.CodedOutputStream
          .computeEnumSize(2, cmd_);
      }
      if (!getContentBytes().isEmpty()) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, content_);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof MessageBase.Message)) {
        return super.equals(obj);
      }
      MessageBase.Message other = (MessageBase.Message) obj;

      if (!getRequestId()
          .equals(other.getRequestId())) {
        return false;
      }
      if (cmd_ != other.cmd_) {
        return false;
      }
      if (!getContent()
          .equals(other.getContent())) {
        return false;
      }
      if (!unknownFields.equals(other.unknownFields)) {
        return false;
      }
      return true;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + REQUESTID_FIELD_NUMBER;
      hash = (53 * hash) + getRequestId().hashCode();
      hash = (37 * hash) + CMD_FIELD_NUMBER;
      hash = (53 * hash) + cmd_;
      hash = (37 * hash) + CONTENT_FIELD_NUMBER;
      hash = (53 * hash) + getContent().hashCode();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static MessageBase.Message parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static MessageBase.Message parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static MessageBase.Message parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static MessageBase.Message parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static MessageBase.Message parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static MessageBase.Message parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static MessageBase.Message parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static MessageBase.Message parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static MessageBase.Message parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static MessageBase.Message parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static MessageBase.Message parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static MessageBase.Message parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(MessageBase.Message prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @java.lang.Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code Message}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:Message)
        MessageBase.MessageOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return MessageBase.internal_static_Message_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return MessageBase.internal_static_Message_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                MessageBase.Message.class, MessageBase.Message.Builder.class);
      }

      // Construct using MessageBase.Message.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        requestId_ = "";

        cmd_ = 0;

        content_ = "";

        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return MessageBase.internal_static_Message_descriptor;
      }

      @java.lang.Override
      public MessageBase.Message getDefaultInstanceForType() {
        return MessageBase.Message.getDefaultInstance();
      }

      @java.lang.Override
      public MessageBase.Message build() {
        MessageBase.Message result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public MessageBase.Message buildPartial() {
        MessageBase.Message result = new MessageBase.Message(this);
        result.requestId_ = requestId_;
        result.cmd_ = cmd_;
        result.content_ = content_;
        onBuilt();
        return result;
      }

      @java.lang.Override
      public Builder clone() {
        return super.clone();
      }
      @java.lang.Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.setField(field, value);
      }
      @java.lang.Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return super.clearField(field);
      }
      @java.lang.Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return super.clearOneof(oneof);
      }
      @java.lang.Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return super.setRepeatedField(field, index, value);
      }
      @java.lang.Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.addRepeatedField(field, value);
      }
      @java.lang.Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof MessageBase.Message) {
          return mergeFrom((MessageBase.Message)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(MessageBase.Message other) {
        if (other == MessageBase.Message.getDefaultInstance()) {
          return this;
        }
        if (!other.getRequestId().isEmpty()) {
          requestId_ = other.requestId_;
          onChanged();
        }
        if (other.cmd_ != 0) {
          setCmdValue(other.getCmdValue());
        }
        if (!other.getContent().isEmpty()) {
          content_ = other.content_;
          onChanged();
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      @java.lang.Override
      public final boolean isInitialized() {
        return true;
      }

      @java.lang.Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        MessageBase.Message parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (MessageBase.Message) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private java.lang.Object requestId_ = "";
      /**
       * <code>string requestId = 1;</code>
       * @return The requestId.
       */
      @Override
      public java.lang.String getRequestId() {
        java.lang.Object ref = requestId_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          requestId_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string requestId = 1;</code>
       * @return The bytes for requestId.
       */
      @Override
      public com.google.protobuf.ByteString
          getRequestIdBytes() {
        java.lang.Object ref = requestId_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          requestId_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string requestId = 1;</code>
       * @param value The requestId to set.
       * @return This builder for chaining.
       */
      public Builder setRequestId(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        requestId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string requestId = 1;</code>
       * @return This builder for chaining.
       */
      public Builder clearRequestId() {
        
        requestId_ = getDefaultInstance().getRequestId();
        onChanged();
        return this;
      }
      /**
       * <code>string requestId = 1;</code>
       * @param value The bytes for requestId to set.
       * @return This builder for chaining.
       */
      public Builder setRequestIdBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        requestId_ = value;
        onChanged();
        return this;
      }

      private int cmd_ = 0;
      /**
       * <code>.Message.CommandType cmd = 2;</code>
       * @return The enum numeric value on the wire for cmd.
       */
      @java.lang.Override public int getCmdValue() {
        return cmd_;
      }
      /**
       * <code>.Message.CommandType cmd = 2;</code>
       * @param value The enum numeric value on the wire for cmd to set.
       * @return This builder for chaining.
       */
      public Builder setCmdValue(int value) {
        
        cmd_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>.Message.CommandType cmd = 2;</code>
       * @return The cmd.
       */
      @java.lang.Override
      public MessageBase.Message.CommandType getCmd() {
        @SuppressWarnings("deprecation")
        MessageBase.Message.CommandType result = MessageBase.Message.CommandType.valueOf(cmd_);
        return result == null ? MessageBase.Message.CommandType.UNRECOGNIZED : result;
      }
      /**
       * <code>.Message.CommandType cmd = 2;</code>
       * @param value The cmd to set.
       * @return This builder for chaining.
       */
      public Builder setCmd(MessageBase.Message.CommandType value) {
        if (value == null) {
          throw new NullPointerException();
        }
        
        cmd_ = value.getNumber();
        onChanged();
        return this;
      }
      /**
       * <code>.Message.CommandType cmd = 2;</code>
       * @return This builder for chaining.
       */
      public Builder clearCmd() {
        
        cmd_ = 0;
        onChanged();
        return this;
      }

      private java.lang.Object content_ = "";
      /**
       * <code>string content = 3;</code>
       * @return The content.
       */
      @Override
      public java.lang.String getContent() {
        java.lang.Object ref = content_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          content_ = s;
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>string content = 3;</code>
       * @return The bytes for content.
       */
      @Override
      public com.google.protobuf.ByteString
          getContentBytes() {
        java.lang.Object ref = content_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          content_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string content = 3;</code>
       * @param value The content to set.
       * @return This builder for chaining.
       */
      public Builder setContent(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        content_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string content = 3;</code>
       * @return This builder for chaining.
       */
      public Builder clearContent() {
        
        content_ = getDefaultInstance().getContent();
        onChanged();
        return this;
      }
      /**
       * <code>string content = 3;</code>
       * @param value The bytes for content to set.
       * @return This builder for chaining.
       */
      public Builder setContentBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        content_ = value;
        onChanged();
        return this;
      }
      @java.lang.Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      @java.lang.Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:Message)
    }

    // @@protoc_insertion_point(class_scope:Message)
    private static final MessageBase.Message DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new MessageBase.Message();
    }

    public static MessageBase.Message getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<Message>
        PARSER = new com.google.protobuf.AbstractParser<Message>() {
      @java.lang.Override
      public Message parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new Message(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<Message> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<Message> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public MessageBase.Message getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_Message_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_Message_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\rMessage.proto\"\307\001\n\007Message\022\021\n\trequestId" +
      "\030\001 \001(\t\022!\n\003cmd\030\002 \001(\0162\024.Message.CommandTyp" +
      "e\022\017\n\007content\030\003 \001(\t\"u\n\013CommandType\022\n\n\006NOR" +
      "MAL\020\000\022\025\n\021HEARTBEAT_REQUEST\020\001\022\026\n\022HEARTBEA" +
      "T_RESPONSE\020\002\022\024\n\020REGISTER_REQUEST\020\003\022\025\n\021RE" +
      "GISTER_RESPONSE\020\004B\rB\013MessageBaseb\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_Message_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_Message_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_Message_descriptor,
        new java.lang.String[] { "RequestId", "Cmd", "Content", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
