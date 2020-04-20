// 예제 7-1 텔넷 서버 부트스트랩 설정

... package, import 생략 ...

public class TelnetServer {
   private static final int listenPort = 8888;

   public static void main(String[] args) throws Exeption {
      EventLoopGroup bossGroup = new NioEventLoopGroup(1);
      EventLoopGroup workerGroup = new NioEventLoopGroup();
      try {
	 ServerBootstrap b = new ServerBootstrap();
	 b.group(bossGroup, workerGroup)
	  .channel(NioServerSocketChannel.class)
	  .childHandler(new TelnetServerInitializer());

	 b.bind(listenPort).sync().channel().closeFuture().sync();
      }
      finally {
	 bossGroup.shutdownGracefully();
	 workerGroup.shutdownGracefully();
      }
   }
}
