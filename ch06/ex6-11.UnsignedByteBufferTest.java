// 예제 6-11 부호 없는 값 읽기

... package, import 생략 ...

public class UnsignedByteBufferTest {
   final String source = "Hello world";

   @Test
   public void unsignedBufferToJavaBuffer() {
      ByteBuf buf = Unpooled.buffer(11);
      buf.writeShort(-1);

      assertEquals(65535, buf.getUnsignedShort(0));
   }
}
