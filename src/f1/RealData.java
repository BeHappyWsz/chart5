package f1;

import java.util.concurrent.Callable;
/**
 * ��ʵ���ݣ�ʵ��Callable�ӿ�
 * @author wsz
 * @date 2018��1��3��
 */
public class RealData implements Callable<String>{

	private String para;
	
	public RealData(String para) {
		super();
		this.para = para;
	}

	@Override
	public String call() throws Exception {
		StringBuffer sb = new StringBuffer();
		for(int i =0 ;i <10; i++) {
			sb.append(para);
			Thread.sleep(100);
		}
		return sb.toString();
	}
}
