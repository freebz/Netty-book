// 예제 8-21 bye, hi 문자열 테스트

... package, import 생략 ...

public class ResponseGeneratorTest {

   ... 생략 ...

   @Test
   public void testHi() {
      String request = "hi";

      ResponseGenerator generator = new ResponseGenerator(request);
      assertNotNull(generator);

      assertNotNull(generator.response());
      assertEquals("입력하신 명령이 '" + request + "' 입니까?\r\n", generator.response());

      assertFalse(generator.isClose());
   }

   @Test
   public void testBye() {
      String request = "bye";

      ResponseGenerator generator = new ResponseGenerator(request);
      assertNotNull(generator);

      assertNotNull(generator.response());
      assertEquals("좋은 하루 되세요!\r\n", generator.response());
      assertTrue(generator.isClose());
   }
}
