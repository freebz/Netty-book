// 예제 4-13 두 번째 이벤트 핸들러 ②

... package, import 생략 ...

public class EchoServerV4SecondHandler extends ChannelInboundHandlerAdapter {
   @Override
   public void channelRead(ChannelHandlerContext ctx, Object msg) {
      ByteBuf readMessage = (ByteBuf) msg;
      System.out.println("SecondHandler channelRead : "
			 + readMessage.toString(Charset.defaultCharset()));
   }
   
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
