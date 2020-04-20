// 예제 7-5 HTTP 서버 부트스트랩 설정

... package, import 생략 ...

public class HttpSnoopServerInitializer extends ChannelInitializer<SocketChannel> {

   private final SslContext sslCtx;

   public HttpSnoopServerInitializer(SslContext sslCtx) {
      this.sslCtx = sslCtx;
   }

   @Override
   public void initChannel(SocketChannel ch) {
      ChannelPipeline p = ch.pipeline();
      if (sslCtx != null) {
	 p.addLast(sslCtx.newHandler(ch.alloc()));
      }
      p.addLast(new HttpRequestDecoder());
      p.addLast(new HttpObjectAggregator(1048576));
      p.addLast(new HttpResponseEncoder());
      p.addLast(new HttpSnoopServerHandler());
   }
}
