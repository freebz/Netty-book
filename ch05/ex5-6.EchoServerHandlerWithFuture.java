// 예제 5-6 사용자 정의 채널 리스너 설정

... package, import 생략 ...

public class EchoServerHandlerWithFuture extends ChannelInboundHandlerAdapter {
   @Override
   public void channelRead(ChannelHandlerContext ctx, Object msg) {
      ChannelFuture channelFuture = ctx.writeAndFlush(msg);

      final int writeMessageSize = ((ByteBuf)msg).readableBytes();

      channelFuture.addListener(new ChannelFutureListener() {
	 @Override
	 public void operationComplete(ChannelFuture future) throws Exception {
	    System.out.println("전송한 Bytes : " + writeMessageSize);
	    future.channel().close();
	 }
      });
   }

   @Override
   public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
      cause.printStackTrace();
      ctx.close();
   }
}
