// 예제 9-26 사용자 정보조회 API

... package, import 생략 ...

@Service("users")
@Scope("prototype")
public class UserInfo extends ApiRequestTemplate {
   @Autowired
   private SqlSession sqlSession;

   public UserInfo(Map<String, String> reqData) {
      super(reqData);
   }

   @Override
   public void requestParamValidation() throws RequestParamException {
      if (StringUtils.isEmpty(this.reqData.get("email"))) {
	 throw new RequestParamException("email이 없습니다.");
      }
   }

   @Override
   public void service() throws ServiceException {
      Map<String, Object> result =
	 sqlSession.selectOne("users.userInfoByEmail", this.reqData);

      if (result != null) {
	 String userNo = String.valueOf(result.get("USERNO"));

	 this.apiResult.addProperty("resultCode", "200");
	 this.apiResult.addProperty("message", "Success");
	 this.apiResult.addProperty("userNo", userNo);
      }
      else {
	 this.apiResult.addProperty("resultCode", "404");
	 this.apiResult.addProperty("message", "Fail");
      }
   }
}
