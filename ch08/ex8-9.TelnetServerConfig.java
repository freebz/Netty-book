// 예제 8-9 TelnetServerConfig 스프링 컨텍스트 설정

... package, import 생략 ...

@Configuration
@ComponentScan("com.github.nettybook.ch7.spring")
@PropertySource("classpath:telnet-server.properties")
public class TelnetServerConfig {
   @Value("${boss.thread.count}")
   private int bosCount;

   @Value("${worker.thread.count}")
   private int workerCount;
   
   @Value("${tcp.port}")
   private int tcpPort;

   public int getBossCount() {
      return bossCount;
   }

   public int getWorkerCount() {
      return workerCount;
   }

   public int getTcpPort() {
      return tcpPort;
   }

   @Bean(name = "tcpSocketAddress")
   public InetSocketAddress tcpPort() {
      return new InetSocketAddress(tcpPort);
   }

   @Bean
   public static PropertySourcesPlaceholderConfigurer propertyPlaceholderConfigurer() {
      return new PropertySourcesPlaceholderConfigurer();
   }
}
