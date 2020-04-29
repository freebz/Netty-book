// 예제 8-2 텔넷 서버 부트스트랩 설정

... package, import 생략 ...

public final class TelnetServer {
    private static final int PORT = 8023;

    public static void main(String[] args) throws Exception {
	EventLoopGroup bossGroup = new NioEventLoopGroup(1);
	EventLoopGroup workerGroup = new NioEventLoopGroup();
	
	try {
	    ServerBootstrap b = new ServerBootstrap();
	    b.group(bossGroup, workerGroup)
		.channel(NioServerSocketChannel.class)
		.handler(new LoggingHandler(LogLevel.INFO))
		.childHandler(new TelnetServerInitializer());

	    ChannelFuture future = b.bind(PORT).sync();
	    future.channel().closeFuture().sync();
	}
	finally {
	    bossGroup.shutdownGracefully();
	    workerGroup.shutdownGracefully();
	}
    }
}
