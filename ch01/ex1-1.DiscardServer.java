// 예제 1-1 Discard 서버

... package, import 생략 ...

public class DiscardServer {
 public static void main(String[] args) throws Exception {
  EventLoopGroup bossGroup = new NioEventLoopGroup(1);
  EventLoopGroup workerGroup = new NioEventLoopGroup();
  try {
     ServerBootstrap b = new ServerBootstrap();
     b.group(bossGroup, workerGroup)
      .channel(NioServerSocketChannel.class)
      .childHandler(new ChannelInitializer<SocketChannel>() {
	@Override
	public void initChannel(SocketChannel ch) {
	   ChannelPipeline p = ch.pipeline();
	   p.addLast(new DiscardServerHandler());
	}
     });

     ChannelFuture f = b.bind(8888).sync();

     f.channel().closeFuture().sync();
  }
  finally {
     workerGroup.shutdownGracefully();
     bossGroup.shutdownGracefully();
  }
 }
}
