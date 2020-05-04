// 예제 9-15 HTTP 요청 처리 멤버 변수

... package, import 생략 ...

public class ApiRequestParser extends SimpleChannelInboundHandler<FullHttpMessage> {
   private static final Logger logger = LogManager.getLogger(ApiRequestParser.class);

   private HttpRequest request;
   private JsonObject apiResult;

   private static final HttpDataFactory factory =
                  new DefaultHttpDataFactory(DefaultHttpDataFactory.MINSIZE);

   private HttpPostRequestDecoder decoder;

   private Map<String, String> reqData = new HashMap<String, String>();

   private static final Set<String> usingHeader = new HashSet<String>();
   static {
      usingHeader.add("token");
      usingHeader.add("email");
   }

   ... 생략 ...

}
