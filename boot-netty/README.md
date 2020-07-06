# springboot-netty

springboot中使用netty网络框架.

本案例实现:

- TCP传输
- protobuf 协议编解码
  - protobuf传输的文本,体积小,效率高
- 心跳检测, 客户端网络异常断开时,server端是无法感知的,需要加心跳检测客户端是否是真的活跃.
- 客户端测试类
  - 断开重连
  - 协议解析
  - 心跳