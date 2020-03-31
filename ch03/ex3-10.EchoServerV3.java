// 예제 3-10 클라이언트 소켓 채널 이벤트 핸들러 설정

... package, import 생략 ...

public class EchoServerV3 {
   public static void main(String[] args) throws Exception {
      EventLoopGroup bossGroup = new NioEventLoopGroup(1);
      EventLoopGroup workerGroup = new NioEventLoopGroup();
      try {
	 ServerBootstrap b = new ServerBootstrap();
	 b.group(bossGroup, workerGroup)
	  .channel(NioServerSocketChannel.class)	    
	  .childHandler(new ChannelInitializer<SocketChannel>() {
	    @Override
	    public void initChannel(SocketChannel ch) {
	       ChannelPipeline p = ch.pipeline();
	       p.addLast(new LoggingHandler(LogLevel.INFO));
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
