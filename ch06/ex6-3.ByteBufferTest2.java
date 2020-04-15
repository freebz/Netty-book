// 예제 6-3 바이트 버퍼 오버플로우

... package, import 생략 ...

public class ByteBufferTest2 {
   public static void main(String[] args) {
      ByteBuffer firstBuffer = ByteBuffer.allocate(11);
      System.out.println("초기 상태 : " + firstBuffer);
      
      byte[] source = "Hello world!".getBytes();
      
      for (byte item : source) {
	 firstBuffer.put(item);
	 System.out.println("현재 상태 : " + firstBuffer);
      }
   }
}
