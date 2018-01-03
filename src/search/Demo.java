package search;
/**
 * 有序数组之二分查找
 * @author wsz
 * @date 2018年1月3日
 */
public class Demo {
	//数组以及数据
	static int[] arr= {25,48,65,87,123,233,456,666,777,8999,55555};
	//二分查找返回下标
	static int search(int[] arr,int target) {
		int len = arr.length;
		//中间、开始、结束下标
		int mid,low,high;
		low = 0;
		high = len-1;
		while(low <= high) {
			mid = (low+high)/2;
			if(arr[mid] == target) 
				return mid;
			else if(arr[mid] > target)
				high = mid-1;
			else
				low = mid+1;
		}
		return -1;
	}
	public static void main(String[] args) {
		int search = search(arr,666);
		System.out.println(search);
	}
}
