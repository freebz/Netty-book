// 예제 4-16 MessageToMessageEncoder

... package, import 생략 ...

public abstract class MessageToMessageEncoder<I> extends
 ChannelOutboundHandlerAdapter {

... 생략 ...
   
   @Override
   public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
      RecyclableArrayList out = null;
      try {
	 if (acceptOutboundMessage(msg)) {
	    out = RecyclableArrayList.newInstance();
	    @SuppressWarnings("unchecked")
	    I cast = (I) msg;
	    try {
	       encode(ctx, cast, out);
	    } finally {
	       ReferenceCountUtil.release(cast);
	    }

	    if (out.isEmpty()) {
	       out.recycle();
	       out = null;

	       throw new EncoderException(StringUtil.simpleClassName(this) +
		  " must produce at least one message.");
	    }
	 } else {
	    ctx.write(msg, promise);
	 }
      } catch (EncoderException e) {
	 throw e;
      } catch (Throwable t) {
	 throw new EncoderException(t);
      } finally {
	 if (out != null) {
	    final int sizeMinusOne = out.size() - 1;
	    if (sizeMinusOne == 0) {
	       ctx.write(out.get(0), promise);
	    } else if (sizeMinusOne > 0) {
	       ChannelPromise voidPromise = ctx.voidPromise();
	       boolean isVoidPromise = promise == voidPromise;
	       for (int i = 0; i < sizeMinusOne; i ++) {
		  ChannelPromise p;
		  if (isVoidPromise) {
		     p = voidPromise;
		  } else {
		     p = ctx.newPromise();
		  }
		  ctx.write(out.get(i), p);
	       }
	       ctx.write(out.get(sizeMinusOne), promise);
	    }
	    out.recycle();
	 }
      }
   }

   protected abstract void encode(ChannelHandlerContext ctx, I msg, List<Object> out) throw Exception;
}
