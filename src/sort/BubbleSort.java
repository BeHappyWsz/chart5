package sort;
/**
 * 冒泡排序
 * @author wsz
 * @date 2018年1月4日
 */
public class BubbleSort {

	public static void bubbleSort(int[] arr) {
		for(int i = arr.length -1; i > 0; i--) {
			for(int j = 0 ;j < i; j++) {
				if(arr[j] > arr[j+1]) {	//控制大小于便能调整排序
					int temp = arr[j];
					arr[j]   = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
	}
	
	public static void main(String[] args) {
		int[] arr = {564,4658,457,41,31,45,74,65,8,1,7,3,9,7,54,45455};
		bubbleSort(arr);
		for (int i : arr) {
			System.out.print(i+" ");
		}
	}
}
