package p;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
/**
 * 测试类
 * @author wsz
 * @date 2017年12月26日
 */
public class Test {

	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<PCData>  queue = new LinkedBlockingQueue<PCData>(10);
		//生产者
		Producer p1 = new Producer(queue);
		Producer p2 = new Producer(queue);
		Producer p3 = new Producer(queue);
		//消费者
		Consumer s1 = new Consumer(queue);
		Consumer s2 = new Consumer(queue);
		Consumer s3 = new Consumer(queue);
		//线程池并开启
		ExecutorService service = Executors.newCachedThreadPool();
		service.execute(p1);
		service.execute(p2);
		service.execute(p3);
		service.execute(s1);
		service.execute(s2);
		service.execute(s3);
		//休眠并停止生产
		Thread.sleep(5000);
		p1.stop();
		p2.stop();
		p3.stop();
		Thread.sleep(2000);
		service.shutdown();
	}
}
