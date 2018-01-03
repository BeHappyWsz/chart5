package f;
/**
 * ��ʵ����RealData�Ĵ�����װ�˻�ȡRealData�ĵȴ����� 
 * @author wsz
 * @date 2018��1��3��
 */
public class FutureData implements Data{

	protected RealData realdata = null;   //���а�װRealData
	
	protected boolean isReady = false;
	
	public synchronized void setRealData(RealData realdata) {
		if(isReady) {
			return;
		}
		this.realdata = realdata;
		isReady = true;
		notifyAll();   //RealData�Ѿ�ע�룬֪ͨgetResult()
	}
	
	@Override
	public synchronized String getResult() {
		while(!isReady) {
			try {
				wait();			//һֱ�ȴ���ֱ��RealData��ע�벢����
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
		return realdata.result;  //����������RealData
	}
}
