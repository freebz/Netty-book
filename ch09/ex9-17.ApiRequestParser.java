// 예제 9-17 HTTP 본문 데이터 수신 메서드

... package, import 생략 ...

public class ApiRequestParser extends SimpleChannelInboundHandler<FullHttpMessage> {

   ... 생략 ...

   private void readPostData() {
      try {
	 decoder = new HttpPostRequestDecoder(factory, request);
	 for (InterfaceHttpData data : decoder.getBodyHttpDatas()) {
	    if (HttpDataType.Attribute == data.getHttpDataType()) {
	       try {
		  Attribute attribute = (Attribute) data;
		  reqData.put(attribute.getName(), attribute.getValue());
	       }
	       catch (IOException e) {
		  logger.error("BODY Attribute: " + data.getHttpDataType().name(), e);
		  return;
	       }
	    }
	    else {
	       logger.info("BODY data : " + data.getHttpDataType().name() +
			                             ": " + data);
	    }
	 }
      }
      catch (ErrorDataDecoderException e) {
	 logger.error(e);
      }
      finally {
	 if (decoder != null) {
	    decoder.destroy();
	 }
      }
   }

... 생략 ...

}
