package f1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
/**
 * @author wsz
 * @date 2018��1��3��
 */
public class FutureMain {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		//����FutureTask
		FutureTask<String> future = new FutureTask<String>(new RealData("test"));
		ExecutorService executor = Executors.newFixedThreadPool(1);
		//ִ��FutureTask���൱��client.request("test")
		//�����߳̽���RealData��call
		executor.submit(future);
		
		System.out.println("�������");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(future.get());
	}
}
