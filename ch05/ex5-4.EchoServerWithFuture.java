// 예제 5-4 기존의 에코 서버 메인 코드 ②

... package, import 생략 ...

public class EchoServerWithFuture {
   public static void main(String[] args) throws Exception {
      EventLoopGroup bossGroup = new EventLoopGroup(1);
      EventLoopGroup workerGroup = new EventLoopGroup();
      try {
	 ... 생략 ...
	 ChannelFuture bindFuture = b.bind(8888);
	 bindFuture.sync();

	 Channel serverChannel = bindFuture.channel();
	 ChannelFuture closeFuture = serverChannel.closeFuture();
	 closeFuture.sync();
      }
      finally {
	 workerGroup.shutdownGracefully();
	 bossGroup.shutdownGracefully();
      }
   }
}
