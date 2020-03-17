// 예제 1-2 Discard 서버 데이터 핸들러

... package, import 생략 ...

public class DiscardServerHandler extends SimpleChannelInboundHandler<Object> {
   @Override
   public void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
     // 아무것도 하지 않음
  }

   @Override
   public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
      cause.printStackTrace();
      ctx.close();
   }
}
