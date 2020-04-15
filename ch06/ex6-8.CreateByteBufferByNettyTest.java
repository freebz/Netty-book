// 예제 6-8 네티 바이트 버퍼 생성

... package, import 생략 ...

public class CreateByteBufferByNettyTest {
   @Test
   public void createUnpooledHeapBufferTest() {
      ByteBuf buf = Unpooled.buffer(11);

      testBuffer(buf, false);
   }

   @Test
   public void createUnpooledDirectBufferTest() {
      ByteBuf buf = Unpooled.directBuffer(11);

      testBuffer(buf, true);
   }

   @Test
   public void createPooledHeapBufferTest() {
      ByteBuf buf = PooledByteBufAllocator.DEFAULT.heapBuffer(11);

      testBuffer(buf, false);
   }

   @Test
   public void createPooledDirectBufferTest() {
      ByteBuf buf = PooledByteBufAllocator.DEFAULT.directBuffer(11);

      testBuffer(buf, true);
   }

   private void testBuffer(ByteBuf buf, boolean isDirect) {
      assertEquals(11, buf.capacity());

      assertEquals(isDirect, buf.isDirect());

      assertEquals(0, buf.readableBytes());
      assertEquals(11, buf.writableBytes());
   }
}
