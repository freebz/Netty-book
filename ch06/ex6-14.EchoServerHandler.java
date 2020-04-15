// 예제 6-14 바이트 버퍼 풀 참조

... package, import 생략 ...

public class EchoServerHandler extends ChannelInboundHandlerAdapter {
   @Override
   public void channelRead(ChannelHandlerContext ctx, Object msg) {
      ByteBuf readMessage = (ByteBuf) msg;
      System.out.println("channelRead : " +
		        readMessage.toString(Charset.defaultCharset()));

      ByteBufAllocator byteBufAllocator = ctx.alloc();
      ByteBuf newBuffer = byteBufAllocator.buffer();

      // newBuffer 사용.

      ctx.writet(msg);
   }

   @Override
   public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
      cause.printStackTrace();
      ctx.close();
   }
}
