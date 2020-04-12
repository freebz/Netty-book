// 예제 4-10 두 번째 이벤트 핸들러 ①

... package, import 생략 ...

public class EchoServerV3SecondHandler extends ChannelInboundHandlerAdapter {
   @Override
   public void channelReadComplete(ChannelHandlerContext ctx) {
      System.out.println("channelReadComplete 발생");
      ctx.flush();
   }

   @Override
   public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
      cause.printStackTrace();
      ctx.close();
   }
}
