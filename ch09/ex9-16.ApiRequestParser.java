// 예제 9-16 HTTP 요청 처리 이벤트 메서드

... package, import 생략 ...

public class ApiRequestParser extends SimpleChannelInboundHandler<FullHttpMessage> {

... 생략 ...

   @Override
   protected void channelRead0(ChannelHandlerContext ctx, FullHttpMessage msg) {
      // Request header 처리.
      if (msg instanceof HttpRequest) {
	 this.request = (HttpRequest) msg;

	 if (HttpHeaders.is100ContinueExpected(request)) {
	    send100Continue(ctx);
	 }

	 HttpHeaders headers = request.headers();
	 if (!headers.isEmpty()) {
	    for (Map.Entry<String, String> h : headers) {
	       String key = h.getKey();
	       if (usingHeader.contains(key)) {
		  reqData.put(key, h.getValue());
	       }
	    }
	 }

	 reqData.put("REQUEST_URI", request.getUri());
	 request.put("REQUEST_METHOD", request.getMethod().name());
      }

      if (msg instanceof HttpContext) {
	 HttpContent httpContent = (HttpContent) msg;

	 ByteBuf content = httpContent.content();

	 if (msg instanceof LastHttpContent) {
	    logger.debug("LastHttpContent message received!!" + request.getUri());

	    LastHttpContent trailer = (LastHttpContent) msg;

	    readPostData();

	    ApiRequest service = ServiceDispatcher.dispatch(reqData);

	    try {
	       service.executeService();

	       apiResult = service.getApiResult();
	    }
	    finally {
	       reqData.clear();
	    }

	    if (!writeResponse(trailer, ctx)) {
	       ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
		  .addListener(ChannelFutureListener.CLOSE);
	    }
	    reset();
	 }
      }
   }

   @Override
   public void channelReadComplete(ChannelHandlerContext ctx) {
      logger.info("요청 처리 완료");
      ctx.flush();
   }

... 생략 ...
}
