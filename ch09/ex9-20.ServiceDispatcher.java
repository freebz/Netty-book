// 예제 9-20 서비스 디스패처 클래스

... package, import 생략 ...

@Component
public class ServiceDispatcher {
   private static ApplicationContext springContext;

   @Autowired
   public void init(ApplicationContext springContext) {
      ServiceDispatcher.springContext = springContext;
   }

   protected Logger logger = LogManager.getLogger(this.getClass());

   public static ApiRequest dispatch(Map<String, String> requestMap) {
      String serviceUri = requestMap.get("REQUEST_RUI");
      String beanName = null;

      if (serviceUri == null) {
	 beanName = "notFound";
      }

      if (serviceUri.startsWith("/tokens")) {
	 String httpMethod = requestMap.get("REQUEST_METHOD");

	 switch (httpMethod) {
	 case "POST":
	    beanName = "tokenIssue";
	    break;
	 case "DELETE":
	    beanName = "tokenExpier";
	    break;
	 case "GET":
	    beanName = "tokenVerify";
	    break;

	 default:
	    beanName = "notFound";
	    break;
	 }
      }
      else if (serviceUri.startsWith("/users")) {
	 beanName = "users";
      }
      else {
	 beanName = "notFound";
      }

      ApiRequest service = null;
      try {
	 service = (ApiRequest) springContext.getBean(beanName, requestMap);
      }
      catch (Exception e) {
	 e.printStackTrace();
	 service = (ApiRequest) springContext.getBean("notFound", requestMap);
      }

      return service;
   }
}
