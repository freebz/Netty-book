// 예제 9-27 인증 토큰 발급 API

... package, import 생략 ...

@Service("tokenIssue")
@Scope("prototype")
public class TokenIssue extends ApiRequestTemplate {
   private static final JedisHelper helper = JedisHelper.getInstance();

   @Autowired
   private SqlSession sqlSession;

   public TokenIssue(Map<String, String> reqData) {
      super(reqData);
   }

   public void requestParamValidation() throws RequestParamException {
      if (StringUtils.isEmpty(this.reqData.get("userNo"))) {
	 throw new RequestParamException("userNo이 없습니다.");
      }

      if (StringUtils.isEmpty(this.reqData.get("password"))) {
	 throw new RequestParamException("password가 없습니다.");
      }
   }

   public void service() throws ServiceException {
      Jedis jedis = null;
      try {
	 Map<String, Object> result = sqlSession.selectOne("users.userInfoByPassword",
							 this.reqData);

	 if (result != null) {
	    final long threeHour = 60 * 60 *3;
	    long issueDate = System.currentTimeMillis() / 1000;
	    String email = String.valueOf(result.get("USERID"));

	    JsonObject token = new JsonObject();
	    token.addProperty("issueDate", issueDate);
	    token.addProperty("expireDate", issueDate + threeHour);
	    token.addProperty("email", email);
	    token.addProperty("userNo", reqData.get("userNo"));

	    // token 저장.
	    KeyMaker tokenKey = new TokenKey(email, issueDate);
	    jedis = helper.getConnection();
	    jedis.setex(tokenKey.getKey(), threeHour, token.toString());

	    // helper.
	    this.apiResult.addProperty("resultCode", "200");
	    this.apiResult.addProperty("message", "Success");
	    this.apiResult.addProperty("token", tokenKey.getKey());
	 }
	 else {
	    // 데이터 없음.
	    this.apiResult.addProperty("resultCode", "404");
	 }

	 helper.returnResource(jedis);
      }
      catch (Exception e) {
	 helper.returnResource(jedis);
      }
   }
}
