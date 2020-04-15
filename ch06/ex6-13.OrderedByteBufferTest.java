// 예제 6-13 바이트 버퍼의 엔디안 변환

... package, import 생략 ...

public class OrderedByteBufferTest {
   final String source = "Hello world";

   @Test
   public void convertNettyBufferToJavaBuffer() {
      ByteBuf buf = Unpooled.buffer(11);

      buf.writeBytes(source.getBytes());
      assertEquals(source, buf.toString(Charset.defaultCharset()));

      ByteBuffer nioByteBuffer = buf.nioBuffer();
      assertNotNull(nioByteBuffer);
      assertEquals(source, new String(nioByteBuffer.array(),
	     nioByteBuffer.arrayOffset(), nioByteBuffer.remaining()));
   }

   @Test
   public void convertJavaBufferToNettyBuffer() {
      ByteBuffer byteBuffer = ByteBuffer.wrap(source.getBytes());
      ByteBuf nettyBuffer = Unpooled.wrappedBuffer(byteBuffer);

      assertEquals(source, nettyBuffer.toString(Charset.defaultCharset()));
   }
}
