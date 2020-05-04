// 예제 9-21 API 서버 스프링 설정 변경

... package, import 생략 ...

@Configuration
@ImportResource("classpath:spring/hsqlApplicationContext.xml")
@ComponentScan("com.github.nettybook.ch9.core, "
      + "com.github.nettybook.ch9, com.github.nettybook.ch9.service")
@PropertySource("classpath:api-server.properties")
public class ApiServerConfig {
   @Value("${boss.thread.count}")
   private int bossThreadCount;

   ... 생략 ...
}
