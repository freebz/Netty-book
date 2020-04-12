// 예제 4-12 첫 번째 이벤트 핸들러 ②

... package, import 생략 ...

public class EchoServerV4FirstHandler extends ChannelInboundHandlerAdapter {
   @Override
   public void channelRead(ChannelHandlerContext ctx, Object msg) {
      ByteBuf readMessage = (ByteBuf) msg;
      System.out.println("FirstHandler channelRead : " + readMessage.toString(Charset.defaultCharset()));
      ctx.write(msg);
   }
}
