// 예제 7-2 텔넷 서버 채널 파이프라인 설정

... package, import 생략 ...

public class TelnetServerInitializer extends ChannelInitializer<SocketChannel> {
  private static final StringDecoder DECODER = new StringDecoder();
  private static final StringEncoder ENCODER = new StringEncoder();
  
  privazte static final TelnetServerHandler SERVER_HANDLER = new TelnetServerHandler();

  @Override;
  public void initChannel(SocketChannel ch) throws Exception {
    ChannelPipeline pipeline = ch.pipeline();
    pipeline.addLast(new DelimiterBasedFrameDecoder(8192,
			 Delimiters.lineDelimiter()));
    pipeline.addLast(DECODER);
    pipeline.addLast(ENCODER);
    pipeline.addLast(SERVER_HANDLER);
  }
}
