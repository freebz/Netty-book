// 예제 8-20 빈 문자열 테스트

... package, import 생략 ...

public class ResponseGeneratorTest {

   @Test
   public void testZeroLengthString() {
      String request = "";

      ResponseGenerator generator = new ResponseGenerator(request);
      assertNotNull(generator);

      assertNotNull(generator.response());
      assertEquals("명령을 입력해 주세요.\r\n", generator.response());
      assertFalse(generator.isClose());
   }
}
