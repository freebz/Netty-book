// 예제 6-10 버퍼 크기 변경 테스트

... package, import 생략 ...

public class DynamicByteBufferTest {

... 생략 ...

   private void testBuffer(ByteBuf buf, boolean isDirect) {
      assertEquals(11, buf.capacity());
      assertEquals(isDirect, buf.isDirect());

      String sourceData = "hello world";

      buf.writeBytes(sourceData.getBytes());
      assertEquals(11, buf.readableBytes());
      assertEquals(0, buf.writableBytes());

      assertEquals(sourceData, buf.toString(Charset.defaultCharset()));

      buf.capacity(6);
      assertEquals("hello ", buf.toString(Charset.defaultCharset()));
      assertEquals(6, buf.capacity());

      buf.capacity(13);
      assertEquals("hello ", buf.toString(Charset.defaultCharset()));

      buf.writeBytes("world".getBytes());
      assertEquals(sourceData, buf.toString(Charset.defaultCharset()));

      assertEquals(13, buf.capacity());
      assertEquals(12, buf.writableBytes());
   }
}
