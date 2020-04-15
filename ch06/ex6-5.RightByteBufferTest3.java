// 예제 6-5 정상적인 바이트 버퍼의 데이터 쓰기와 읽기 테스트

... package, import 생략 ...

public class RightByteBufferTest4 {
   @Test
   public void test() {
      ByteBuffer firstBuffer = ByteBuffer.allocate(11);
      System.out.println("초기 상태 : " + firstBuffer);

      firstBuffer.put((byte) 1);
      firstBuffer.put((byte) 2);
      assertEquals(2, firstBuffer.position());

      firstBuffer.rewind();
      assertEquals(0, firstBuffer.position());

      assertEquals(1, firstBuffer.get());
      assertEquals(1, firstBuffer.position());

      System.out.println(firstBuffer);
   }
}
