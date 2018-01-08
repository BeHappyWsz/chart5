package nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {

	public static void main(String[] args) throws IOException {
		Socket client = null;
		PrintWriter writer = null;
		BufferedReader reader = null;
		
		try {
			client = new Socket();
			//连接8000端口
			client.connect(new InetSocketAddress("localhost", 8000));
			writer = new PrintWriter(client.getOutputStream(),true);
			writer.println("hello");
			writer.flush();
			//读取服务器的返回信息并进行输出
			reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
			System.out.println("from server:"+reader.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(writer != null) writer.close();
			if(reader != null) reader.close();
			if(client != null) client.close();
		}
	}
}
