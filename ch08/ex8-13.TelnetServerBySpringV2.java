// 예제 8-13 TelnetServerBySpringV2 스프링 컨텍스트 초기화

... package, import 생략 ...

public class TelnetServerBySpringV2 {
   public static void main(String[] args) {
      AbstractApplicationContext springContext = null;
      try {
	 springContext = new AnnotationConfigApplicationContext(
	       TelnetServerConfigV2.class);
	 springContext.registerShutdonwHook();

	 TelnetServerV3 server = springContext.getBean(TelnetServerV3.class);
	 server.start();
      }
      finally {
	 springContext.close();
      }
   }
}
