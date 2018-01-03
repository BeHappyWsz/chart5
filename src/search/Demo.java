package search;
/**
 * ��������֮���ֲ���
 * @author wsz
 * @date 2018��1��3��
 */
public class Demo {
	//�����Լ�����
	static int[] arr= {25,48,65,87,123,233,456,666,777,8999,55555};
	//���ֲ��ҷ����±�
	static int search(int[] arr,int target) {
		int len = arr.length;
		//�м䡢��ʼ�������±�
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
