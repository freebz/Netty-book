// 예제 8-15 TelnetServer V3 부트스트랩 설정

... package, import 생략 ...

@Component
public final class TelnetServerV3 {
   @Autowired
   @Qualifier("tcpSocketAddress")
   private InetSocketAddress address;

   public void start() {

... 생략 ...
      try {
	 ServerBootstrap b = new ServerBootstrap();
	 b.group(bossGroup, workerGroup)
	       .channel(NioServerSocketChannel.class)
	       .childHandler(new TelnetServerInitializerV3());
... 생략 ...

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
