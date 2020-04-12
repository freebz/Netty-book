// 예제 4-15 Base64Encoder 코드(네티 코덱)

... package, import 생략 ...

public class Base64Encoder extends MessageToMessageEncoder<ByteBuf> {

   private final boolean breakLines;
   private final Base64Dialect dialect;

   public Base64Encoder() {
      this(true);
   }

   public Base64Encoder(boolean breakLines) {
      this(breakLines, Base64Dialect.STANDARD);
   }

   public Base64Encoder(boolean breakLines, Base64Dialect dialect) {
      if (dialect == null) {
	 throw new NullPointerException("dialect");
      }

      this.breakLines = breakLines;
      this.dialect = dialect;
   }

   @Override
   protected void encode(ChannelHandlerContext ctx, ByteBuf msg,
		     List<Object> out) throws Exception {
      out.add(Base64.encode(msg, msg.readerIndex(), msg.readableBytes(),
		  breakLines, dialect));
   }
}
