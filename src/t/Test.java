package t;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
/**
 * ������
 * @author wsz
 * @date 2017��12��26��
 */
public class Test {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws InterruptedException {
		Executor executor = Executors.newCachedThreadPool();
		PCDataFactory factory = new PCDataFactory();
		int bufferSize = 1024;//���û�������С
		Disruptor<PCData> disruptor = new Disruptor<PCData>(factory, bufferSize, executor, ProducerType.MULTI, new BlockingWaitStrategy());
		disruptor.handleEventsWithWorkerPool(  //����3��������ʵ��,ϵͳ�Ὣÿһ��������ʵ��ӳ�䵽һ���߳���
				new Consumer(),
				new Consumer(),
				new Consumer()
				);
		disruptor.start();//��������ʼ��disruptorϵͳ
		
		//�����߲����򻺳����ֿ��������
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
