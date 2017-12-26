package p;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * 生产者
 * @author wsz
 * @date 2017年12月26日
 */
public class Producer implements Runnable{

	private volatile boolean isRunning = true;//生产开关,满足可见性、有序性（禁止指令重排序），但无法保证（复合操作）原子性
	private BlockingQueue<PCData> queue;		//生产仓库,内存缓冲区;数据共享通道
	private static AtomicInteger count = new AtomicInteger();//生产编号;原子操作
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
				data = new PCData(count.incrementAndGet());//当前值+1;不变模式,提供完整的构造函数;
				System.out.println(data+" put in queue");
				if(!queue.offer(data, 2, TimeUnit.SECONDS)) {//最多等待2s进行数据放入
					System.out.println("failed put in queue :"+data);
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}
	
	//停止生产
	public void stop() {
		isRunning = false;
	}
}
