// 예제 4-2 ChannelInboundHandler 코드

... package, import 생략 ...

public interface ChannelInboundHandler extends ChannelHandler {
   void channelRegistered(ChannelHandlerContext ctx) throws Exception;

   void channelUnregistered(ChannelHandlerContext ctx) throws Exception;

   void channelActive(ChannelHandlerContext ctx) throws Exception;

   void channelInactive(ChannelHandlerContext ctx) throws Exception;

   void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception;

   void channelReadComplete(ChannelHandlerContext ctx) throws Exception;

   void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception;

   void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception;

   void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception;
}
