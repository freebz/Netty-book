// 예제 7-4 HTTP 서버 부트스트랩 설정

... package, import 생략 ...

public final class HttpSnoopServer {
   private static final int ROOT = 8443;
   public static void main(String[] args) throws Exception {
      SslContext sslCtx = null;

      try {
	 File certChainFile = new File("netty.crt");
	 File keyFile = new File("privatekey.pem");

	 sslCtx = SslContext.newServerContext(certChainFile, keyFile,
		     "1234567890");
      }
      catch (SSLException e) {
	 e.printStackTrace();
      }

      EventLoopGroup bossGroup = new NioEventLoopGroup(1);
      EventLoopGroup workerGroup = new NioEventLoopGroup();
      try {
	 ServerBootstrap b = new ServerBootstrap();
	 b.group(bossGroup, worderGroup)
	  .channel(NioServerSocketChannel.class)
	  .handler(new LoggingHandler(LogLevel.INFO))
	  .childHandler(new HttpSnoopServerInitializer(sslCtx));

	 Channel ch = b.bind(PORT).sync().channel();

	 ch.closeFuture().sync();
      }
      finally {
	 bossGroup.shutdownGracefully();
	 workerGroup.shutdownGracefully();
      }
   }
}
