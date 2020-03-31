// 예제 3-7 이벤트 루프 설정 API

@SuppressWarnings("unchecked")
public B group(EventLoopGroup group) {
   if (group == null) {
      throw new NullPointerException("group");
   }
   if (this.group != null) {
      throw new IllegalStateException("group set already");
   }
   this.group = group;
   return (B) this;
}
