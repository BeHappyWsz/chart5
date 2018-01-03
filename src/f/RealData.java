package f;
/**
 * @author wsz
 * @date 2018年1月3日
 */
public class RealData implements Data{

	protected final String result;
	
	public RealData(String para) {
		StringBuffer sb = new StringBuffer();
		for(int i =0 ;i< 10;i++) {
			sb.append(para);
			try {
				Thread.sleep(100);	//模拟耗时的构造过程，
			}catch (Exception e) {
				
			}
		}
		result = sb.toString();
	}

	@Override
	public String getResult() {
		return result;
	}

}
