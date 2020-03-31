// 예제 3-5 Epoll 입출력 모드를 지원하는 ServerBootstrap 초기화

... package, import 생략 ...

public class EpollEchoServer {
   public static void main(String[] args) throws Exception {
      EventLoopGroup bossGroup = new EpollEventLoopGroup(1);
      EventLoopGroup workerGroup = new EpollEventLoopGroup();
      try {
	 ServerBootstrap b = new ServerBootstrap();
	 b.group(bossGroup, workerGroup)
	  .channel(EpollServerSocketChannel.class);
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
