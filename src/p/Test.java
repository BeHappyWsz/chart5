package p;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
/**
 * ������
 * @author wsz
 * @date 2017��12��26��
 */
public class Test {

	public static void main(String[] args) throws InterruptedException {
		BlockingQueue<PCData>  queue = new LinkedBlockingQueue<PCData>(10);
		//������
		Producer p1 = new Producer(queue);
		Producer p2 = new Producer(queue);
		Producer p3 = new Producer(queue);
		//������
		Consumer s1 = new Consumer(queue);
		Consumer s2 = new Consumer(queue);
		Consumer s3 = new Consumer(queue);
		//�̳߳ز�����
		ExecutorService service = Executors.newCachedThreadPool();
		service.execute(p1);
		service.execute(p2);
		service.execute(p3);
		service.execute(s1);
		service.execute(s2);
		service.execute(s3);
		//���߲�ֹͣ����
		Thread.sleep(5000);
		p1.stop();
		p2.stop();
		p3.stop();
		Thread.sleep(2000);
		service.shutdown();
	}
}
