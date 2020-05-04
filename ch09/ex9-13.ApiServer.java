// 예제 9-13 API 서버 부트스트랩 설정

... package, import 생략 ...

@Component
public final class ApiServer {
   @Autowired
   @Qualifier("tcpSocketAddress")
   private InetSocketAddress address;

   @Autowired
   @Qualifier("workerThreadCount")
   private int workerThreadCount;

   @Autowired
   @Qualifier("bossThreadCount")
   private int bossThreadCount;

   public void start() {
      EventLoopGroup bossGroup = new NioEventLoopGroup(bossThreadCount);
      EventLoopGroup workerGroup = new NioEventLoopGroup(workerThreadCount);
      ChannelFuture channelFuture = null;

      try {
	 ServerBootstrap b = new ServerBootstrap();
	 b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
	       .handler(new LoggingHandler(LogLevel.INFO))
	       .childHandler(new ApiServerInitializer(null));

	 Channel ch = b.bind(8080).sync().channel();

	 channelFuture = ch.closeFuture();
	 channelFuture.sync();
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
