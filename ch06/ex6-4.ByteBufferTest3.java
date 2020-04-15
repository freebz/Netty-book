// 예제 6-4 바이트 버퍼의 데이터 쓰기와 읽기

... package, import 생략 ...

public class ByteBufferTest3 {
   @Test
   public void test() {
      ByteBuffer firstBuffer = ByteBuffer.allocate(11);
      System.out.println("초기 상태 : " + firstBuffer);

      firstBuffer.put((byte) 1);
      System.out.println(firstBuffer.get());
      System.out.println(firstBuffer);
   }
}
