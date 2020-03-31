// 예제 3-13 클라이언트 소켓 채널 이벤트 핸들러 설정

... package, import 생략 ...

public final class EchoClient {
   public static void main(String[] args) throws Exception {
      EventLoopGroup group = new NioEventLoopGroup(1);
      
      try {
	 Bootstrap b = new Bootstrap();
	 b.group(group)
	  .channel(NioServerSocketChannel.class)	    
	  .handler(new ChannelInitializer<SocketChannel>() {
	    @Override
	    public void initChannel(SocketChannel ch) throws Exception {
	       ChannelPipeline p = ch.pipeline();
	       p.addLast(new EchoClientHandler());
	    }
	 });
... 생략 ...
      }
      finally {
	 group.shutdownGracefully();
      }
   }
}
