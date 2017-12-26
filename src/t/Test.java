package t;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
/**
 * 测试类
 * @author wsz
 * @date 2017年12月26日
 */
public class Test {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws InterruptedException {
		Executor executor = Executors.newCachedThreadPool();
		PCDataFactory factory = new PCDataFactory();
		int bufferSize = 1024;//设置缓冲区大小
		Disruptor<PCData> disruptor = new Disruptor<PCData>(factory, bufferSize, executor, ProducerType.MULTI, new BlockingWaitStrategy());
		disruptor.handleEventsWithWorkerPool(  //设置3个消费者实例,系统会将每一个消费者实例映射到一个线程中
				new Consumer(),
				new Consumer(),
				new Consumer()
				);
		disruptor.start();//启动并初始化disruptor系统
		
		//生产者不断向缓冲区仓库存入数据
		RingBuffer<PCData> ringBuffer = disruptor.getRingBuffer();
		Producer producer = new Producer(ringBuffer);
		ByteBuffer bb = ByteBuffer.allocate(8);
		for(int i =0;true;i++) {
			bb.putLong(0,i);
			producer.pushData(bb);
			Thread.sleep(500);
			System.out.println("add data "+i);
		}
	}
}
