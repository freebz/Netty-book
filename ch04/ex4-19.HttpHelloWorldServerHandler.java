// 예제 4-19 Helloworld 웹 서버 데이터 핸들러

... package, import 생략 ...

public class HttpHelloWorldServerHandler extends ChannelInboundHandlerAdapter {
   private static final byte[] CONTENT = { 'H', 'e', 'l', 'l', 'o', ' ', 'W', 'o', 'r',
	    'l', 'd' };

   private static final AsciiString CONTENT_TYPE = new AsciiString("Content-Type");
   private static final AsciiString CONTENT_LENGTH = new AsciiString("Content-Length");
   private static final AsciiString CONNECTION = new AsciiString("Connection");
   private static final AsciiString KEEP_ALIVE = new AsciiString("keep-alive");

   @Override
   public void channelReadComplete(ChannelHandlerContext ctx) {
      ctx.flush();
   }

   @Override
   public void channelRead(ChannelHandlerContext ctx, Object msg) {
      if (msg instanceof HttpRequest) {
	 HttpRequest req = (HttpRequest) msg;

	 if (HttpHeaders.is100ContinueExpected(req)) {
	    ctx.write(new DefaultFullHttpResponse(HTTP_1_1, CONTINUE));
	 }
	 boolean keepAlive = HttpHeaders.isKeepAlive(req);
	 FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK,
		 Unpooled.wrappedBuffer(CONTENT));
	 response.headers().set(CONTENT_TYPE, "text/plain");
	 response.headers().set(CONTENT_LENGTH, response.content().readableBytes());

	 if (!keepAlive) {
	    ctx.write(response).addListener(ChannelFutureListener.CLOSE);
	 } else {
	    response.headers().set(CONNECTION, KEEP_ALIVE);
	    ctx.write(response);
	 }
      }
   }

   @Override
   public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
      cause.printStackTrace();
      ctx.close();
   }
}
