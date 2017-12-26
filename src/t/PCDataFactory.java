package t;

import com.lmax.disruptor.EventFactory;
/**
 * 工厂类,在Disruptor系统初始化时,构造所有缓冲区中的实例对象
 * @author wsz
 * @date 2017年12月26日
 */
public class PCDataFactory implements EventFactory<PCData>{

	@Override
	public PCData newInstance() {
		return new PCData();
	}
}
