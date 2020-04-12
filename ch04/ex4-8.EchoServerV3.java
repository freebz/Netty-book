// 예제 4-8 다중 이벤트 핸들러 등록 ①

... package, import 생략 ...

public class EchoServerV3 {
   public static void main(String[] args) throws Exception {
      ... 생략 ...
	 b.group(bossGroup, workerGroup)
	  .channel(NioServerSocketChannel.class)
	  .childHandler(new ChannelInitializer<SocketChannel>() {
	    @Override
	    public void initChannel(SocketChannel ch) {
	       ChannelPipeline p = ch.pipeline();
	       p.addLast(new EchoServerV3FirstHandler());
	       p.addLast(new EchoServerV3SecondHandler());
	    }
	  });
... 생략 ...
}
