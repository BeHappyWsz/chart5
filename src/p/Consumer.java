package p;

import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * 消费者
 * @author wsz
 * @date 2017年12月26日
 */
public class Consumer implements Runnable{
	private BlockingQueue<PCData> queue;//数据仓库
	private static final int SLEEPTIME = 1000;
	
	public Consumer(BlockingQueue<PCData> queue) {
		super();
		this.queue = queue;
	}

	@Override
	public void run() {
		System.out.println("start Consumer id="+Thread.currentThread().getId());
		Random r = new Random();
		try{
			while(true) {
				PCData data = queue.take();//Retrieves and removes the head of this queue, waiting if necessary until an element becomes available.
				if(null != data) {
					int re = data.getData()*data.getData();
					System.out.println(MessageFormat.format("{0}*{1}={2}", data.getData(),data.getData(),re));
					Thread.sleep(r.nextInt(SLEEPTIME));
				}
			}
		}catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
		
	}

}
