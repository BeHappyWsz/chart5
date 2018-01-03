package f;
/**
 * 真实数据RealData的代理，封装了获取RealData的等待过程 
 * @author wsz
 * @date 2018年1月3日
 */
public class FutureData implements Data{

	protected RealData realdata = null;   //进行包装RealData
	
	protected boolean isReady = false;
	
	public synchronized void setRealData(RealData realdata) {
		if(isReady) {
			return;
		}
		this.realdata = realdata;
		isReady = true;
		notifyAll();   //RealData已经注入，通知getResult()
	}
	
	@Override
	public synchronized String getResult() {
		while(!isReady) {
			try {
				wait();			//一直等待，直到RealData被注入并唤醒
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		return realdata.result;  //返回真正的RealData
	}
}
