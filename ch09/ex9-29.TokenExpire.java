// 예제 9-29 인증 토큰 만료 API

... package, import 생략 ...

@Service("tokenExpire")
@Scope("prototype")
public class TokenExpire extends ApiRequestTemplate {
   private static final JedisHelper helper = JedisHelper.getInstance();

   public TokenExpire(Map<String, String> reqData) {
      super(reqData);
   }

   @Override
   public void requestParamValidation() throws RequestParamException {
      if (StringUtils.isEmpty(this.reqData.get("token"))) {
	 throw new RequestParamException("token이 없습니다.");
      }
   }

   @Override
   public void service() throws ServiceException {
      Jedis jedis = null;
      try {
	 jedis = helper.getConnection();
	 long result = jedis.del(this.reqData.get("token"));
	 System.out.println(result);

	 // helper.
	 this.apiResult.addProperty("resultCode", "200");
	 this.apiResult.addProperty("message", "Success");
	 this.apiResult.addProperty("token", this.reqData.get("token"));
      }
      catch (Exception e) {
	 helper.returnResource(jedis);
      }
   }
}
