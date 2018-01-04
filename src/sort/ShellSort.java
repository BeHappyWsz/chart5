package sort;
/**
 * 希尔排序
 * @author wsz
 * @date 2018年1月4日
 */
public class ShellSort {
	/*
	 * 1.将n个元素的数组分成n/2个数字序列，第1个数据和第n/2+1个数据为一对...
	 * 2.一次循环使每一个序列对排好顺序
	 * 3.然后，再变为n/4个序列，再次排序。
	 * 4.重复上述过程，直到序列减为1个，结束
	 * @param arr
	 */
	public static void sort(int[] arr) {
		for(int r = arr.length/2; r >= 1; r/=2) {//分成2组，
			for(int i = r; i<arr.length; i++) {
				int temp = arr[i];
				int j    = i-r;
				
				while(j >= 0 && temp < arr[j]) {
					arr[j+r] = arr[j];
					j -= r;
				}
				
				arr[j+r] = temp;
			}
		}
	}
	
	/**
	 * @param arr
	 */
	public static void sort1(int[] arr) {
		int h = 1;
		while(h <= arr.length/2) {
			h = h*2+1;
		}
		while(h > 0) {
			for(int i = h; i< arr.length; i++) {
				if(arr[i] < arr[i-h]) {
					int temp = arr[i];
					int j    = i - h;
					while(j >= 0 && arr[j] > temp) {
						arr[j + h] = arr[j];
						j -= h;
					}
					arr[j+h] = temp;
				}
			}
			h = (h-1)/2;
		}
	}
	
	public static void main(String[] args) {
		int[] arr = {564,4658,457,41,31,45,74,65,8,1,7,3,9,7,54,45455};
		sort(arr);
//		sort1(arr);
		for (int i : arr) {
			System.out.print(i+" ");
		}
	}
}
