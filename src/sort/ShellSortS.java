package sort;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * 并行情况下的希尔排序
 * @author wsz
 * @date 2018年1月4日
 */
public class ShellSortS {

	static int[] arr= {25,48,65,87,123,233,456,666,777,8999,55555};
	
	static ExecutorService pool = Executors.newCachedThreadPool();
	
	static class ShellSortTask implements Runnable{

		int i = 0;
		int h = 0;
		CountDownLatch latch;
		
		public ShellSortTask(int i, int h, CountDownLatch latch) {
			super();
			this.i = i;
			this.h = h;
			this.latch = latch;
		}

		@Override
		public void run() {
			if(arr[i] < arr[i-h]) {
				int temp = arr[i];
				int j    = i - h;
				while(j >= 0 && arr[j] > temp) {
					arr[j+h] = arr[j];
					j -= h;
				}
				arr[j+h] = temp;
			}
			latch.countDown();
		}
		
	}
	
	public static void pShellSort(int[] arr) throws InterruptedException {
		int h = 1;
		CountDownLatch latch = null;
		while( h <= arr.length/3) {
			h = h*3+1;
		}
		while(h > 0) {
			if(h >= 4) 
				latch = new CountDownLatch(arr.length - h);
			for(int i = h; i < arr.length; i++) {
				if(h >= 4) {
					pool.execute(new ShellSortTask(i, h, latch));
				}else {
					if(arr[i] < arr[i-h]) {
						int temp = arr[i];
						int j = i -h;
						while(j >= 0 && arr[j] > temp) {
							arr[j+h] = arr[j];
							j -= h;
						}
						arr[j+h] = temp;
					}
				}
			}
			latch.await();
			h = (h-1) / 3;
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		pShellSort(arr);
		for (int  i : arr) {
			System.out.print(i+" ");
		}
	}

}
