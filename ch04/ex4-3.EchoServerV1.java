// 예제 4-3 에코 서버 부트스트랩 설정

... package, import 생략 ...

public class EchoServerV1 {
   public static void main(String[] args) throws Exception {
      ... 생략 ...
	 b.group(bossGroup, workerGroup)
	  .channel(NioServerSocketChannel.class)
	  .childHandler(new ChannelInitializer<SocketChannel>() {
	    @Override
	    public void initChannel(SocketChannel ch) {
	       ChannelPipeline p = ch.pipeline();
	       p.addLast(new EchoServerV1Handler());
	    }
	  });
... 생략 ...
}
