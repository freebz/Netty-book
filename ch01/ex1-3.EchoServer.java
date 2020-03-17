// 예제 1-3 에코 서버

... package, import 생략 ...

public class EchoServer {
   public static void main(String[] args) throws Exception {
      ... 생략 ...
         .childHandler(new ChannelInitializer<SocketChannel>() {
	    @Override
	    public void initChannel(SocketChannel ch) {
	       ChannelPipeline p = ch.pipeline();
	       p.addLast(new EchoServerHandler());
	    }
	 });

         ChannelFuture f = b.bind(8888).sync();
      ... 생략 ...
   }
}
