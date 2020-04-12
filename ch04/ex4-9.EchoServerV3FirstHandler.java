// 예제 4-9 첫 번째 이벤트 핸들러 ①

... package, import 생략 ...

public class EchoServerV3FirstHandler extends ChannelInboundHandlerAdapter {
   @Override
   public void channelRead(ChannelHandlerContext ctx, Object msg) {
      ByteBuf readMessage = (ByteBuf) msg;
      System.out.println("channelRead : " + readMessage.toString(Charset.defaultCharset()));
      ctx.write(msg);
   }
}
