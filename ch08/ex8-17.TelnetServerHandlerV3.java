// 예제 8-17 TelnetServerHandler V3 데이터 핸들러

... package, import 생략 ...

@Sharable
public class TelnetServerHandlerV3 extends SimpleChannelInboundHandler<String> {

   @Override
   public void channelActive(ChannelHandlerContext ctx) throws Exception {
      ctx.write(ResponseGenerator.makeHello());
      ctx.flush();
   }

   @Override
   public void channelRead0(ChannelHandlerContext ctx, String request)
      throws Exception {
      ResponseGenerator generator = new ResponseGenerator(request);
      String response = generator.response();
      ChannelFuture future = ctx.write(response);

      if (generator.isClose()) {
	 future.addListener(ChannelFutureListener.CLOSE);
      }
   }

... 생략 ...

}
