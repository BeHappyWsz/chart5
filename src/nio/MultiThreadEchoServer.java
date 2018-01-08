package nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadEchoServer {

	//ʹ���̳߳ش�������ÿһ������
	private static ExecutorService tp = Executors.newCachedThreadPool();
	
	static class HandleMsg implements Runnable{

		Socket clientSocket;
		
		public HandleMsg(Socket clientSocket) {
			super();
			this.clientSocket = clientSocket;
		}

		@Override
		public void run() {
			BufferedReader is = null;
			PrintWriter os = null;
			try {
				is = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				os = new PrintWriter(clientSocket.getOutputStream(),true);
				String inputLine = null;
				long b = System.currentTimeMillis();
				while((inputLine = is.readLine()) != null) {
					os.println(inputLine);
				}
				long e = System.currentTimeMillis();
				System.out.println("spend:"+(e-b)+"ms");
			} catch (IOException e) {
				e.printStackTrace();
			}finally {
					try {
						if(is != null) is.close();
						if(os != null) os.close();
						clientSocket.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
			}
		}
	}
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ServerSocket echoserver = null;
		Socket clientSocket = null;
		
		try {
			echoserver = new ServerSocket(8000);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//��8000�˿ڽ��м����ȴ�
		while(true) {
			try {
				clientSocket = echoserver.accept();
				System.out.println(clientSocket.getRemoteSocketAddress() + " connect!");
				tp.execute(new HandleMsg(clientSocket));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
