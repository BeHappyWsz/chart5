package t;

import java.nio.ByteBuffer;

import com.lmax.disruptor.RingBuffer;
/**
 * 生产者
 * @author wsz
 * @date 2017年12月26日
 */
public class Producer {

	private final RingBuffer<PCData> ringBuffer;//缓冲区,数据仓库
	public Producer(RingBuffer<PCData> ringBuffer) {
		super();
		this.ringBuffer = ringBuffer;
	}
	
	public void pushData(ByteBuffer bb) {//数据放入缓冲区仓库,参数为ByteBuffer对象,可以包装任何数据
										//此处用来存储long数据。
		long sequence = ringBuffer.next();//得到下一个可用的序列号,
		try {
			PCData event = ringBuffer.get(sequence);//根据序列号获取可用的数据对象
			event.setValue(bb.getLong(0));
		}finally {
			ringBuffer.publish(sequence);//数据发布,发布后消费者才能看见
		}
	}
}
