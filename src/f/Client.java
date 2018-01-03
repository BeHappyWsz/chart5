package f;

public class Client {

	public Data request(final String queryStr) {
		final FutureData future = new FutureData();
		new Thread() {	//RealData的构造很慢，在单独的线程中进行
			public void run() {
				RealData realdata = new RealData(queryStr);
				future.setRealData(realdata);
			}
		}.start();;
		return future;
	}
	
	public static void main(String[] args) {
		Client client = new Client();
		Data data = client.request("test");//这里会立即返回，因为得到的是FutureData而不是RealData
		System.out.println("请求完毕");
//		System.out.println(data.getResult());构造较慢，有明显的延迟
		try {
			Thread.sleep(2000);  //模拟其他业务逻辑的处理过程，该过程中RealData被创建
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(data.getResult());//真实数据
	}
}
