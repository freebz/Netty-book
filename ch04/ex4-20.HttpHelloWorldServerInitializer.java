// 예제 4-20 Helloworld 웹 서버 채널 파이프라인 수정

... package, import 생략 ...

public class HttpHelloWorldServerInitializer extends ChannelInitializer<SocketChannel> {
   ... 생략 ...
   @Override
   public void initChannel(SocketChannel ch) {
      ChannelPipeline p = ch.pipeline();
      if (sslCtx != null) {
	 p.addLast(sslCtx.newHandler(ch.alloc()));
      }
      p.assLast(new HttpHelloWorldServerHandler());
      p.addLast(new HttpServerCodec());
   }
}
