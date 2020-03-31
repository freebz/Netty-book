// 예제 3-1 ServerBootstrap과 빌더 패턴

... package, import 생략 ...

public final class ServerBootstrap extends AbstractBootstrap<ServerBootstrap, ServerChannel> {
   ... 생략 ...

   public ServerBootstrap group(EventLoopGroup group) {
      return group(group, group);
   }

   public ServerBootstrap group(EventLoopGroup parentGroup, EventLoopGroup childGroup) {
      super.group(parentGroup);
      if (childGroup == null) {
	 throw new NullPointerException("childGroup");
      }
      if (this.childGroup != null) {
	 throw new IllegalStateException("childGroup set already");
      }
      this.childGroup = childGroup;
      return this;
   }

   public ServerBootstrap childHandler(ChannelHandler childHandler) {
      if (childHandler == null) {
	 throw new NullPointerException("childHandler");
      }
      this.childHandler = childHandler;
      return this;
   }

   ... 생략 ...
}
