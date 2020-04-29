// 예제 8-23 Base64Encoder 테스트

... package, import 생략 ...

public class Base64EncoderTest {
   @Test
   public void testEncoder() {
      String writeData = "안녕하세요";
      ByteBuf request = Unpooled.wrappedBuffer(writeData.getBytes());

      Base64Encoder encoder = new Base64Encoder();
      EmbeddedChannel embeddedChannel = new EmbeddedChannel(encoder);

      embeddedChannel.writeOutbound(request);

      ByteBuf response = (ByteBuf) embeddedChannel.readOutbound();

      String expect = "7JWI64WV7ZWY7IS47JqU";
      assertEquals(expect, response.toString(Charset.defaultCharset()));

      embeddedChannel.finish();
   }
}
