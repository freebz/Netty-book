// 예제 4-1 에코 서버 부트스트랩 설정

... package, import 생략 ...

public class EchoServer {
   public static void main(String[] args) throws Exception {
      EventLoopGroup bossGroup = new NioEventLoopGroup(1);
      EventLoopGroup workerGroup = new NioEventLoopGroup();
      try {
	 ServerBootstrap b = new ServerBootstrap();
	 b.group(bossGroup, workerGroup)
	  .channel(NioServerSocketChannel.class)
	  .childHandler(new ChannelInitializer<SocketChannel>() {
	    @Override
	    public void initChannel(Socket<Channel ch) {
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
