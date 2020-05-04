// 예제 9-18 ApiRequest 인터페이스

... package, import 생략 ...

public interface ApiRequest {
   public void requestParamValidation() throws RequestParamException;

   public void service() throws ServiceException;

   public void executeService();

   public JsonObject getApiResult();
}
