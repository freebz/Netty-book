// 예제 2-1 블로킹 모드의 서버

... package, import 생략 ...

public class BlockingServer {
   public static void main(String[] args) throws Exception {
      BlockingServer server = new BlockingServer();
      server.run();
   }
   
   private void run() throws IOException {
      ServerSocket server = new ServerSocket(8888);
      System.out.println("접속 대기중");

      while (true) {
	 Socket sock = sock.getOutputStream();
	 System.out.println("클라이언트 연결됨");

	 OutputStream out = sock.getOutputStream();
	 InputStream in = sock.getInputStream();

	 while (true) {
	    try {
	       int request = in.read();
	       out.write(request);
	    }
	    catch (IOException e) {
	       break;
	    }
	 }
      }
   }
}
