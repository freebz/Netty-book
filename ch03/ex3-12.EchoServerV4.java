// 예제 3-12 SO_LINGER 옵션 설정

... package, import 생략 ...

public final class EchoServerV4 {
   public static void main(String[] args) throws Exception {
      EventLoopGroup bossGroup = new NioEventLoopGroup(1);
      EventLoopGroup workerGroup = new NioEventLoopGroup();      
      try {
	 ServerBootstrap b = new ServerBootstrap();
	 b.group(bossGroup, workerGroup)
	  .channel(NioServerSocketChannel.class)	    
	  .childOption(ChannelOption.SO_LINGER, 0)
	  .childHandler(new ChannelInitializer<SocketChannel>() {
	    @Override
	    public void initChannel(SocketChannel ch) {
	       ChannelPipeline p = ch.pipeline();
	       p.addLast(new EchoServerHandler());
	    }
	 });
... 생략 ...
      }
      finally {
	 workerGroup.shutdownGracefully();
	 bossGroup.shutdownGracefully();
      }
   }
}
