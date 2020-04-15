// 예제 6-9 네티 바이트 버퍼 읽기 쓰기

... package, import 생략 ...

public class ReadWriteByteBufferByNettyTest {

... 생략 ...

   private void testBuffer(ByteBuf buf, boolean isDirect) {
      assertEquals(11, buf.capacity());
      
      assertEquals(isDirect, buf.isDirect());
      
      buf.writeInt(65537);
      assertEquals(4, buf.readableBytes());
      assertEquals(7, buf.writableBytes());
      
      assertEquals(1, buf.readShort());
      assertEquals(2, buf.readableBytes());
      assertEquals(7, buf.writableBytes());
      
      assertEquals(true, buf.isReadable());
      
      buf.clear();
      
      assertEquals(0, buf.readableBytes());
      assertEquals(11, buf.writableBytes());
   }
}
