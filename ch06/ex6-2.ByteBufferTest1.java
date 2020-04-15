// 예제 6-2 자바의 바이트 버퍼 상태

... package, import 생략 ...

public class ByteBufferTest1 {
   public static void main(String[] args) {
      ByteBuffer firstBuffer = ByteBuffer.allocate(11);
      System.out.println("바이트 버퍼 초기값 : " + firstBuffer);

      byte[] source = "Hello world".getBytes();
      firstBuffer.put(source);
      System.out.println("11바이트 기록 후 : " + firstBuffer);
   }
}
