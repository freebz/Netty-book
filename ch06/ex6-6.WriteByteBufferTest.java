// 예제 6-6 바이트 버퍼의 데이터 쓰기 테스트

... package, import 생략 ...

public class WriteByteBufferTest {
   @Test
   public void writeTest() {
      ByteBuffer firstBuffer = ByteBuffer.allocateDirect(11);
      assertEquals(0, firstBuffer.position());
      assertEquals(11, firstBuffer.limit());

      firstBuffer.put((byte) 1);
      firstBuffer.put((byte) 2);
      firstBuffer.put((byte) 3);
      firstBuffer.put((byte) 4);
      assertEquals(4, firstBuffer.position());
      assertEquals(11, firstBuffer.limit());

      firstBuffer.flip();
      assertEquals(0, firstBuffer.position());
      assertEquals(4, firstBuffer.limit());
   }
}
