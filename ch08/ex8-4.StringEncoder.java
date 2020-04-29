// 예제 8-4 StringEncoder 코드

... package, import 생략 ...

@Sharable
public class StringEncoder extends MessageToMessageEncoder<CharSequence> {
   private final Charset charset;

   public StringEncoder() {
      this(Charset.defaultCharset());
   }

   public StringEncoder(Charset charset) {
      if (charset == null) {
	 throw new NullPointerException("charset");
      }
      this.charset = charset;
   }

   @Override
   protected void encode(ChannelHandlerContext ctx, CharSequence msg,
		     List<Object> out) throws Exception {
      if (msg.length() == 0) {
	 return;
      }

      out.add(ByteBufUtil.encodeString(ctx.alloc(), CharBuffer.wrap(msg), charset));
   }
}
