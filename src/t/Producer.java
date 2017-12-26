package t;

import java.nio.ByteBuffer;

import com.lmax.disruptor.RingBuffer;
/**
 * ������
 * @author wsz
 * @date 2017��12��26��
 */
public class Producer {

	private final RingBuffer<PCData> ringBuffer;//������,���ݲֿ�
	public Producer(RingBuffer<PCData> ringBuffer) {
		super();
		this.ringBuffer = ringBuffer;
	}
	
	public void pushData(ByteBuffer bb) {//���ݷ��뻺�����ֿ�,����ΪByteBuffer����,���԰�װ�κ�����
										//�˴������洢long���ݡ�
		long sequence = ringBuffer.next();//�õ���һ�����õ����к�,
		try {
			PCData event = ringBuffer.get(sequence);//�������кŻ�ȡ���õ����ݶ���
			event.setValue(bb.getLong(0));
		}finally {
			ringBuffer.publish(sequence);//���ݷ���,�����������߲��ܿ���
		}
	}
}
