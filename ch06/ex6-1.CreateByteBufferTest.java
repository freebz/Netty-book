// 예제 6-1 자바의 바이트 버터 생성

... package, import 생략 ...

public class CreateByteBufferTest {
   @Test
   public void createTest() {
      CharBuffer heapBuffer = CharBuffer.allocate(11);
      assertEquals(11, heapBuffer.capacity());
      assertEquals(false, heapBuffer.isDirect());

      ByteBuffer directBuffer = ByteBuffer.allocateDirect(11);
      assertEquals(11, directBuffer.capacity());
      assertEquals(true, directBuffer.isDirect());

      int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 0 };
      IntBuffer intHeapBuffer = IntBuffer.wrap(array);
      assertEquals(11, intHeapBuffer.capacity());
      assertEquals(false, intHeapBuffer.isDirect());
   }
}
