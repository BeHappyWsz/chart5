package f1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
/**
 * @author wsz
 * @date 2018年1月3日
 */
public class FutureMain {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		//构造FutureTask
		FutureTask<String> future = new FutureTask<String>(new RealData("test"));
		ExecutorService executor = Executors.newFixedThreadPool(1);
		//执行FutureTask，相当于client.request("test")
		//开启线程进行RealData的call
		executor.submit(future);
		
		System.out.println("请求完毕");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(future.get());
	}
}
