// 예제 6-12 바이트 버퍼의 엔디안 변환

... package, import 생략 ...

public class OrderedByteBufferTest {

   @Test
   public void pooledHeapBufferTest() {
      ByteBuf buf = Unpooled.buffer();
      assertEquals(ByteOrder.BIG_ENDIAN, buf.order());

      buf.writeShort(1);

      buf.markReaderIndex();
      assertEquals(1, buf.readShort());

      buf.resetReaderIndex();

      ByteBuf littleEndianBuf = buf.order(ByteOrder.LITTLE_ENDIAN);
      assertEquals(256, littleEndianBuf.readShort());
   }
}
