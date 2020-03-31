// 예제 3-2 생성자 사용 예제

... package, import 생략 ...

public final class ServerBootstrap
extends AbstractBootstrap<ServerBootstrap, ServerChannel> {
   ... 생략 ...

   public ServerBootstrap(EventLoopGroup group) {
      this.parentGroup = group;
      this. childGroup = group;
   }

   public ServerBootstrap(EventLoopGroup parentGroup,
			  EventLoopGroup childGroup) {
      this.parentGroup = parentGroup;
      this. childGroup = childGroup;
   }

   ... 생략 ...
