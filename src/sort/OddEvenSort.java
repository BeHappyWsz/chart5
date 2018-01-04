package sort;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * ��ż��������
 * �潻�����Ƚ����������Լ������ڵĺ���Ԫ�ء�
 * ż�������Ƚ�ż�������������ڵĺ���Ԫ�ء�
 * ���ֽ����ɶԳ��֣���֤�ȽϺͽ����漰�������е�ÿһ��Ԫ�ء�
 * @author wsz
 * @date 2018��1��4��
 */
public class OddEvenSort {

	static int[] arr= {25,48,65,87,123,233,456,666,777,8999,55555};
	
	static ExecutorService pool = Executors.newCachedThreadPool();
	
	static int exchFlag = 1;	//��¼��ǰ�����Ƿ��������ݽ���

	public static synchronized int getExchFlag() {
		return exchFlag;
	}

	public static synchronized void setExchFlag(int exchFlag) {
		OddEvenSort.exchFlag = exchFlag;
	}
	
	public static void pOddEvenSort(int[] arr) throws InterruptedException {
		int start = 0;			//��¼�������͡�0Ϊż������1Ϊ�潻��
		
		//����ϴαȽϷ��������ݽ��������ߵ�ǰ���ڽ����潻����ѭ������ֹͣ��
		//ֱ�������ٷ������������ߵ�ǰ���е���ż����������ż�����Ѿ��ɶԳ��֡�
		while(getExchFlag() == 1 || start ==1) {
			setExchFlag(0);
			//ż������ֵ���ȣ���start=1ʱ��ֻ��len/2-1���̡߳�����ʱ�߳�����
			CountDownLatch latch = new CountDownLatch(arr.length/2 -(arr.length%2 == 0 ? start :0));
			for(int i =start; i < arr.length-1; i+=2) {
				pool.submit(new OddEvenSortTask(i,latch));
			}
			latch.await();//�ȴ������߳̽���
			start = start ==0 ? 1 : 0;
		}
	}
	
	static class OddEvenSortTask implements Runnable{
		int i;
		CountDownLatch latch;
		
		public OddEvenSortTask(int i, CountDownLatch latch) {
			super();
			this.i = i;
			this.latch = latch;
		}

		@Override
		public void run() {
			if(arr[i] > arr[i+1]) {
				int temp = arr[i];
				arr[i]   = arr[i+1];
				arr[i+1] = temp;
				setExchFlag(1); //���ݽ����˽���
			}
			latch.countDown();//������ǰ�̵߳����񣬵���ʱ��-1
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		pOddEvenSort(arr);
		for (int i : arr) {
			System.out.print(i+" ");
		}
	}
}
