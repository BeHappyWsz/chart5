package f;

public class Client {

	public Data request(final String queryStr) {
		final FutureData future = new FutureData();
		new Thread() {	//RealData�Ĺ���������ڵ������߳��н���
			public void run() {
				RealData realdata = new RealData(queryStr);
				future.setRealData(realdata);
			}
		}.start();;
		return future;
	}
	
	public static void main(String[] args) {
		Client client = new Client();
		Data data = client.request("test");//������������أ���Ϊ�õ�����FutureData������RealData
		System.out.println("�������");
//		System.out.println(data.getResult());��������������Ե��ӳ�
		try {
			Thread.sleep(2000);  //ģ������ҵ���߼��Ĵ�����̣��ù�����RealData������
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(data.getResult());//��ʵ����
	}
}
