package t;

import com.lmax.disruptor.EventFactory;
/**
 * ������,��Disruptorϵͳ��ʼ��ʱ,�������л������е�ʵ������
 * @author wsz
 * @date 2017��12��26��
 */
public class PCDataFactory implements EventFactory<PCData>{

	@Override
	public PCData newInstance() {
		return new PCData();
	}
}
