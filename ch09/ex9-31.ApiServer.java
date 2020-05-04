// 예제 9-31 다중 서비스 포트 적용

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
	 b.group(bossGroup, workerGroup)
	       .channel(NioServerSocketChannel.class)
	       .handler(new LoggingHandler(LogLevel.INFO))
	       .childHandler(new ApiServerInitializer(null));

	 Channel ch = b.bind(8080).sync().channel();

	 channelFuture = ch.closeFuture();
	 // channelFuture.sync();

	 final SslContext sslCtx;
	 SelfSignedCertificate ssc = new SelfSignedCertificate();
	 sslCtx = SslContext.newServerContext(ssc.certificate(), ssc.privateKey());

	 ServerBootstrap b2 = new ServerBootstrap();
	 b2.group(bossGroup, workerGroup)
	       .channel(NioServerSocketChannel.class)
	       .handler(new LoggingHandler(LogLevel.INFO))
	       .childHandler(new ApiServerInitializer(sslCtx));

	 Channel ch2 = b2.bind(8443).sync().channel();

	 channelFuture = ch2.closeFuture();
	 channelFuture.sync();
      }
      catch (InterruptedException | CertificateException e) {
	 e.printStackTrace();
      }
      catch (SSLException e) {
	 // TODO Auto-generated catch block
	 e.printStackTrace();
      }
      finally {
	 bossGroup.shutdownGracefully();
	 workerGroup.shutdownGracefully();
      }
   }
}
