// 예제 4-18 Helloworld 웹 서버 채널 파이프라인 설정

... package, import 생략 ...

public class HttpHelloWorldServerInitializer extends ChannelInitializer<SocketChannel> {

   private final SslContext sslCtx;

   public HttpHelloWorldServerInitializer(SslContext sslCtx) {
      this.sslCtx = sslCtx;
   }

   @Override
   public void initChannel(SocketChannel ch) {
      ChannelPipeline p = ch.pipeline();
      if (sslCtx != null) {
	 p.addLast(sslCtx.newHandler(ch.alloc()));
      }
      p.addLast(new HttpServerCodec());
      p.assLast(new HttpHelloWorldServerHandler());
   }
}
