// 예제 8-22 DelimiterBasedFrameDecoder 테스트 케이스

... package, import 생략 ...

public class DelimiterBasedFrameDecoderTest {
   @Test
   public void testDecoder() {
      String writeData = "안녕하세요\r\n반갑습니다\r\n";
      String firstResponse = "안녕하세요\r\n";
      String secondResponse = "반갑습니다\r\n";

      DelimiterBasedFrameDecoder decoder = new DelimiterBasedFrameDecoder(8192,
				   false, Delimiters.lineDelimiter());
      EmbeddedChannel embeddedChannel = new EmbeddedChannel(decoder);

      ByteBuf request = Unpooled.wrappedBuffer(writeData.getBytes());
      boolean result = embeddedChannel.writeInbound(request);
      assertTrue(result);

      ByteBuf response = null;
      response = (ByteBuf) embeddedChannel.readInbound();
      assertEquals(firstResponse, response.toString(Charset.defaultCharset()));

      response = (ByteBuf) embeddedChannel.readInbound();
      assertEquals(secondResponse, response.toString(Charset.defaultCharset()));

      embeddedChannel.finish();
   }
}
