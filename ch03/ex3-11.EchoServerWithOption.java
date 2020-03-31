// 예제 3-11 SO_BACKLOG 옵션 설정 예제

... package, import 생략 ...

public final class EchoServerWithOption {
   public static void main(String[] args) throws Exception {
      EventLoopGroup bossGroup = new NioEventLoopGroup(1);
      EventLoopGroup workerGroup = new NioEventLoopGroup();
      
      try {
	 ServerBootstrap b = new ServerBootstrap();
	 b.group(bossGroup, workerGroup)
	  .channel(NioServerSocketChannel.class)	    
	  .option(ChannelOption.SO_BACKLOG, 1)
	  .handler(new LoggingHandler(LogLevel.INFO))
	  .childHandler(new ChannelInitializer<SocketChannel>() {
	    @Override
	    public void initChannel(SocketChannel ch) {
	       ChannelPipeline p = ch.pipeline();
	       p.addLast(new EchoServerHandler());
	    }
	 });
... 생략 ...
      }
      finally {
	 workerGroup.shutdownGracefully();
	 bossGroup.shutdownGracefully();
      }
   }
}
