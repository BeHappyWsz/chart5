package p;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * ������
 * @author wsz
 * @date 2017��12��26��
 */
public class Producer implements Runnable{

	private volatile boolean isRunning = true;//��������,����ɼ��ԡ������ԣ���ָֹ�������򣩣����޷���֤�����ϲ�����ԭ����
	private BlockingQueue<PCData> queue;		//�����ֿ�,�ڴ滺����;���ݹ���ͨ��
	private static AtomicInteger count = new AtomicInteger();//�������;ԭ�Ӳ���
	private static final int SLEEPTIME = 1000;
	
	public Producer(BlockingQueue<PCData> queue) {
		super();
		this.queue = queue;
	}

	@Override
	public void run() {
		PCData data = null;
		Random r = new Random();
		System.out.println("start Producer id="+Thread.currentThread().getId());
		try {
			while(isRunning) {
				Thread.sleep(r.nextInt(SLEEPTIME));
				data = new PCData(count.incrementAndGet());//��ǰֵ+1;����ģʽ,�ṩ�����Ĺ��캯��;
				System.out.println(data+" put in queue");
				if(!queue.offer(data, 2, TimeUnit.SECONDS)) {//���ȴ�2s�������ݷ���
					System.out.println("failed put in queue :"+data);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}
	
	//ֹͣ����
	public void stop() {
		isRunning = false;
	}
}
