// 예제 8-12 컨텍스트 생성 파일

... package, import 생략 ...

public class TelnetServerBySpring {
   public static void main(String[] args) {
      AbstractApplicationContext springContext = null;
      try {
	 springContext = new AnnotationConfigApplicationContext(
				     TelnetServerConfig.class);
	 springContext.registerShutdonwHook();

	 TelnetServerV2 server = springContext.getBean(TelnetServerV2.class);
	 server.start();
      }
      finally {
	 springContext.close();
      }
   }
}
