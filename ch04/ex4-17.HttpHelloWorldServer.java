// 예제 4-17 Helloworld 웹 서버 메인 코드

... package, import 생략 ...

public final class HttpHelloWorldServer {

   static final boolean SSL = System.getProperty("ssl") != null;
   static final int ROOT = Integer.parseInt(System.getProperty("port", SSL? "8443" : "8080"));

   public static void main(String[] args) throws Exception {
      // Configure SSL.
      final SslContext sslCtx;
      if (SSL) {
	 SelfSignedCertificate ssc = new SelfSignedCertificate();
	 sslCtx = SslContext.newServerContext(ssc.certificate(),
	 ssc.privateKey());
      } else {
	 sslCtx = null;
      }

      // Configure the server.
      EventLoopGroup bossGroup = new NioEventLoopGroup(1);
      EventLoopGroup workerGroup = new NioEventLoopGroup();
      try {
	 ServerBootstrap b = new ServerBootstrap();
	 b.option(ChannelOption.SO_BACKLOG, 1024);
	 b.group(bossGroup, workerGroup)
	  .channel(NioServerSocketChannel.class)
	  .handler(new LoggingHandler(LogLevel.INFO))
	  .childHandler(new HttpHelloWorldServerInitializer(sslCtx));

	 Channel ch = b.bind(PORT).sync().channel();

	 System.err.println("Open your web browser and navigate to " +
	       (SSL? "https": "http") + "://127.0.0.1:" + PORT + '/');

	 ch.closeFuture().sync();
      } finally {
	 bossGroup.shutdownGracefully();
	 workerGroup.shutdownGracefully();
      }
   }
}
