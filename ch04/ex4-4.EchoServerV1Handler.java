// 예제 4-4 에코 서버 이벤트 핸들러

... package, import 생략 ...

public class EchoServerV1Handler extends ChannelInboundHandlerAdapter {
   @Override
   public void channelRead(ChannelHandlerContext ctx, Object msg) {
      ByteBuf readMessage = (ByteBuf) msg;
      System.out.println("channelRead : " + readMessage.toString(Charset.defaultCharset()));
      ctx.writeAndFlush(msg);
   }

   @Override
   public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
      cause.printStackTrace();
      ctx.close();
   }
}
