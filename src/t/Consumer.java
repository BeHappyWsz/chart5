package t;

import com.lmax.disruptor.WorkHandler;
/**
 * ������
 * @author wsz
 * @date 2017��12��26��
 */
public class Consumer implements WorkHandler<PCData>{

	//��ܻص�����,�Զ���ȡ����
	@Override
	public void onEvent(PCData event) throws Exception {
		System.out.println(Thread.currentThread().getId()+":Event:--"+event.getValue()*event.getValue());
	}

}
