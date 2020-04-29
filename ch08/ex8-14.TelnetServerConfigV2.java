// 예제 8-14 TelnetServerConfig V2 스프링 컨텍스트

... package, import 생략 ...

@Configuration
@ComponentScan("com.github.nettybook.ch8.junit")
@PropertySource("classpath:telnet-server.properties")
public class TelnetServerConfigV2 {

... 생략 ...
      
}
