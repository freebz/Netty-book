// 예제 1-5 에코 클라이언트

... package, import 생략 ...

public final class EchoClient {
   public static void main(String[] args) throws Exception {
      EventLoopGroup group = new NioEventLoopGroup();

      try {
	 Bootstrap b = new Bootstrap();
	 b.group(group)
	  .channel(NioSocketChannel.class)
	  .handler(new ChannelInitializer<SocketChannel>() {
	     @Override
	     public void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline p = ch.pipeline();
		p.addLast(new EchoClientHandler());
	     }
	 });
      
	 ChannelFuture f = b.connect("localhost", 8888).sync();
      
	 f.channel().closeFuture().sync();
      }
      finally {
	 group.shutdownGracefully();
      }
   }
}
