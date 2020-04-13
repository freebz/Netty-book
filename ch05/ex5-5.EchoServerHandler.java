// 예제 5-5 ChannelFuture 리스너가 설정된 에코 서버 핸들러

... package, import 생략 ...

public class EchoServerHandler extends ChannelInboundHandlerAdapter {
   @Override
   public void channelRead(ChannelHandlerContext ctx, Object msg) {
      ChannelFuture channelFuture = ctx.writeAndFlush(msg);
      channelFuture.addListener(ChannelFutureListener.CLOSE);
   }
   
   @Override
   public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
      cause.printStackTrace();
      ctx.close();
   }
}
