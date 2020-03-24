// 예제 2-2 논블로킹 모드 서버 (출저 ⎡자바7 NIO.2: 파일&네트워크 프로그래밍⎦)

... package, import 생략 ...

public class NonBlockingServer {
  private Map<SocketChannel, List<Byte[]>> keepDataTrack = new HashMap<>();
  private ByteBuffer buffer = ByteBuffer.allocate(2 * 1024);

  private void startEchoServer() {
    try (
      Selector selector = Selector.open();
      ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()
    ) {

      if ((serverSocketChannel.isOpen()) && (selector.isOpen())) {
	serverSocketChannel.configureBlocking(false);
	serverSocketChannel.bind(new InetSocketAddress(8888));

	serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
	System.out.println("접속 대기중");

	while (true) {
	  selector.select();
	  Iterator<SelectionKey> keys = selector.selectedKeys().iterator();

	  while (keys.hasNext()) {
	    SelectionKey key = (SelectionKey) keys.next();
	    keys.remove();

	    if (!key.isValid()) {
	      continue;
	    }

	    if (key.isAcceptable()) {
	      this.acceptOP(key, selector);
	    }
	    else if (key.isReadable()) {
	      this.readOP(key);
	    }
	    else if (key.isWritable()) {
	      this.writeOP(key);
	    }
	  }
	}
      }
      else {
	System.out.println("서버 소켓을 생성하지 못했습니다.");
      }
    }
    catch (IOException ex) {
      System.err.println(ex);
    }
  }

  private void acceptOP(SelectionKey key, Selector selector) throws IOException {
    ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
    SocketChannel socketChannel = serverChannel.accept();
    socketChannel.configureBlocking(false);

    System.out.println("클라이언트 연결됨 : " + socketChannel.getRemoveAddress());

    keepDataTrack.put(socketChannel, new ArrayList<byte[]>());
    socketChannel.register(selector, SelectionKey.OP_READ);
  }

  private void readOP(SelectionKey key) {
    try {
      SocketChannel socketChannel = (SocketChannel) key.channel();
      buffer.clear();
      int numRead = -1;
      try {
	numRead = socketChannel.read(buffer);
      }
      catch (IOException e) {
	Syste.err.println("데이터 읽기 에러");
      }

      if (numRead == -1) {
	this.keepDataTrack.remove(socketChannel);
	System.out.println("클라이언트 연결 종료 : "
		       + socketChannel.getRemoteAddress());
	socketChannel.close();
	key.cancle();
	return;
      }

      byte[] data = new byte[numRead];
      System.arraycopy(buffer.array(), 0, data, 0, numRead);
      System.out.println(new String(data, "UTF-8")
		     + " from " + socketChannel.getRemoteAddress());

      doEchoJob(key, data);
    }
    catch (IOException ex) {
      System.err.println(ex);
    }
  }

  private void writeOP(SelectionKey key) throws IOException {
    SocketChannel socketChannel = (SocketChannel) key.channel();

    List<byte[]> channelData = keepDataTrack.get(socketChannel);
    Iterator<byte[]> its = channelData.iterator();

    while (its.hasNext()) {
      byte[] it = its.next();
      its.remove();
      socketChannel.write(ByteBuffer.wrap(it));
    }

    key.interestOps(SelectionKey.OP_READ);
  }

  private void doEchoJob(SelectionKey key, byte[] data) {
    SocketChannel socketChannel = (SocketChannel) key.channel();
    List<byte[]> channelData = keepDataTrack.get(socketChannel);
    channelData.add(data);

    key.interestOps(SelectionKey.OP_WRITE);
  }

  public static void main(String[] args) {
    NonBlockingServer main = new NonBlockingServer();
    main.startEchoServer();
  }
}
