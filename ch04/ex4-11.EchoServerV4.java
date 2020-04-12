// 예제 4-11 다중 이벤트 핸들러 등록 ②

... package, import 생략 ...

public class EchoServerV4 {
   public static void main(String[] args) throws Exception {
      ... 생략 ...
	 b.group(bossGroup, workerGroup)
	  .channel(NioServerSocketChannel.class)
	  .childHandler(new ChannelInitializer<SocketChannel>() {
	    @Override
	    public void initChannel(SocketChannel ch) {
	       ChannelPipeline p = ch.pipeline();
	       p.addLast(new EchoServerV4FirstHandler());
	       p.addLast(new EchoServerVfSecondHandler());
	    }
	  });
... 생략 ...
}
