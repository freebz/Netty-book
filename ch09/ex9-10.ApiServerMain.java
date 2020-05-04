// 예제 9-10 API 서버 메인

... package, import 생략 ...

public class ApiServerMain {
   public static void main(String[] args) {
      AbstractApplicationContext springContext = null;
      try {
	 springContext = new AnnotationConfigApplicationContext(
					ApiServerConfig.class);
	 springContext.registerShutdownHook();

	 ApiServer server = springContext.getBean(ApiServer.class);
	 server.start();
      }
      finally {
	 springContext.close();
      }
   }
}
