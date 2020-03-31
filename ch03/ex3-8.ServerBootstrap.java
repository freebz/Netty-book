// 예제 3-8 부모 스레드 설정을 위한 group 메서드 재정의

... package, import 생략 ...

public final class ServerBootstrap
extends AbstractBootstrap<ServerBootstrap, ServerChannel> {

... 생략 ...

   @Override
   public ServerBootstrap group(EventLoopGroup group) {
      return group(group, group);
   }

   public ServerBootstrap group(EventLoopGroup parentGroup,
				EventLoopGroup childGroup) {
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
   ... 생략 ...
}
