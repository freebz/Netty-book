// 예제 3-4 블로킹 입출력 모드를 지원하는 ServerBootstrap 초기화

... package, import 생략 ...

public class BlockingEchoServer {
   public static void main(String[] args) throws Exception {
      EventLoopGroup bossGroup = new OioEventLoopGroup(1);
      EventLoopGroup workerGroup = new OioEventLoopGroup();
      try {
	 ServerBootstrap b = new ServerBootstrap();
	 b.group(bossGroup, workerGroup)
	  .channel(OioServerSocketChannel.class);
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
