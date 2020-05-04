// 예제 9-11 API 서버 스프링 설정

//... package, import 생략 ...

@Configuration
@ComponentScan("com.github.nettybook.ch9, com.github.nettybook.ch9.service")
@PropertySource("classpath:api-server.properties")
public class ApiServerConfig {
   @Value("#{boss.thread.count}")
   private int bossThreadCount;

   @Value("${worker.thread.count}")
   private int workerThreadCount;

   @Value("${tcp.port}")
   private int tcpPort;

   @Bean(name = "bossThreadCount")
   public int getBossThreadCount() {
      return bossThreadCount;
   }

   @Bean(name = "workerThreadCount")
   public int getWorkerThreadCount() {
      return workerThreadCount;
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
      return new PropertySourcesPlackholderConfigurer();
   }
}
