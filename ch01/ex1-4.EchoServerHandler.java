// 예제 1-4 에코 서버 데이터 핸들러

... package, import 생략 ...

public class EchoServerHandler extends ChannelInboundHandlerAdapter {
   @Override
   public void channelRead(ChannelHandlerContext ctx, Object msg) {
      String readMessage = ((ByteBuf) msg).toString(Charset.defaultCharset());
      
      System.out.println("수신한 문자열 [" + readMessage + ']');
      
      ctx.write(msg);
   }
   
   @Override
   public void channelReadComplete(ChannelHandlerContext ctx) {
      ctx.flush();
   }
... 생략 ...
}
