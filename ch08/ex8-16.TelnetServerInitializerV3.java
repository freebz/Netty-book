// 예제 8-16 TelnetServerInitializer V3 채널 파이프라인

... package, import 생략 ...

public class TelnetServerInitializerV3 extends
      ChannelInitializer<SocketChannel> {

   private static final StringDecoder DECODER = new StringDecoder();
   private static final StringEncoder ENCODER = new StringEncoder();

   private static final TelnetServerHandlerV3 SERVER_HANDLER =
      new TelnetServerHandlerV3();

   @Override
   public void initChannel(SocketChannel ch) throws Exception {

... 생략 ...

      pipeline.addLast(SERVER_HANDLER);
   }
}
