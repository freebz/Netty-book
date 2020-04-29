// 예제 8-11 네티 부트스트랩 설정

... package, import 생략 ...

@Component
public final class TelnetServerV2 {
   @Autowired
   @Qualifier("tcpSocketAddress")
   private InetSocketAddress port;

   public void start() {
      EventLoopGroup bossGroup = new NioEventLoopGroup(1);
      EventLoopGroup workerGroup = new NioEventLoopGroup();

      try {
	 ServerBootstrap b = new ServerBootstrap();
	 b.group(bossGroup, workerGroup)
	        .channel(NioServerSocketChannel.class)
	        .childHandler(new TelnetServerInitializer());

	 ChannelFuture future = b.bind(port).sync();

	 future.channel().closeFuture().sync();
      }
      catch (InterruptedException e) {
	 e.printStackTrace();
      }
      finally {
	 bossGroup.shutdownGracefully();
	 workerGroup.shutdownGracefully();
      }
   }
}
