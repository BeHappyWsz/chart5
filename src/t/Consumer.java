package t;

import com.lmax.disruptor.WorkHandler;
/**
 * 消费者
 * @author wsz
 * @date 2017年12月26日
 */
public class Consumer implements WorkHandler<PCData>{

	//框架回调函数,自动读取数据
	@Override
	public void onEvent(PCData event) throws Exception {
		System.out.println(Thread.currentThread().getId()+":Event:--"+event.getValue()*event.getValue());
	}

}
