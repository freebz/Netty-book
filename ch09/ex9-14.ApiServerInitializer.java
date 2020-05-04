// 예제 9-14 API 서버 채널 파이프라인 설정

... package, import 생략 ...

public class ApiServerInitilizer extends ChannelInitializer<SocketChannel> {

   private final SslContext sslCtx;

   public ApiServerInitilizer(SslContext sslCtx) {
      this.sslCtx = sslCtx;
   }

   @Override
   public void initChannel(SocketChannel ch) {
      ChannelPipeline p = ch.pipeline();
      if (sslCtx != null) {
	 p.addLast(sslCtx.newHandler(ch.alloc()));
      }

      p.addLast(new HttpRequestDecoder());
      p.addLast(new HttpObjectAggregator(65536));
      p.addLast(new HttpResponseEncoder());
      p.addLast(new HttpContentCompressor());
      p.addLast(new ApiRequestParser());
   }
}
