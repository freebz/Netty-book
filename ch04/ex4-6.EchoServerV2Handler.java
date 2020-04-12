// 예제 4-6 에코 서버 이벤트 핸들러

... package, import 생략 ...

public class EchoServerV2Handler extends ChannelInboundHandlerAdapter {
   @Override
   public void channelRead(ChannelHandlerContext ctx, Object msg) {
      ByteBuf readMessage = (ByteBuf) msg;
      System.out.println("channelRead : " + readMessage.toString(Charset.defaultCharset()));
      ctx.write(msg);
   }

   @Override
   public void channelReadComplete(ChannelHandlerContext ctx) {
      System.out.println("channelReadComplete 빌생");
      ctx.flush();
   }

...생략...
}
