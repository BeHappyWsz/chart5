package search;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
/**
 * �����������������������ݣ�����λ���±ꣻû���򷵻�-1
 * @author wsz
 * @date 2018��1��3��
 */
public class Test implements Callable<Integer>{
	//����ʵ��Callable�ӿ�
	private int begin,end,search;
	
	public Test( int search,int begin, int end) {
		super();
		this.search = search;
		this.begin = begin;
		this.end = end;
	}

	@Override
	public Integer call() throws Exception {
		return search(search,begin,end);
	}
	
	//�����ѯ��������
	static int[] arr= {1,5,4,879,456465,1321,564,8798,154,45646,132,1321,48,7,87,8,8,7,87,98,89,546,46};
	//�̳߳�+�߳�����+��Ž������result
	static ExecutorService pool = Executors.newCachedThreadPool(); 
	static final int THREAD_NUM =2;
	static AtomicInteger result = new AtomicInteger(-1);
	
	public static int search(int search, int begin,int end) {
		int i = 0;
		for(i = begin; i<end ;i++) {
			if(result.get() > 0) 	//�ж������߳��Ƿ��Ѿ��õ�������ѵõ��򷵻�
				return result.get();
			
			if(arr[i] == search) {	//��ǰ�̵߳õ����
				if(!result.compareAndSet(-1, i)) //ʹ��CAS���������������ʧ���������߳�����һ����ȡ�������
					return result.get();
				return i;
			}
		}
		return -1;	//û���ҵ��򷵻�-1
	}

	public static int pSearch(int search) throws InterruptedException, ExecutionException {
		int subArrSize = arr.length /THREAD_NUM+1;  //�����߳���������arr����
		List<Future<Integer>> re = new ArrayList<Future<Integer>>();
		for(int i = 0; i<arr.length ; i+= subArrSize) {
			int end = i+subArrSize;
			if(end >= arr.length) end = arr.length;
			re.add(pool.submit(new Test(search,i,end)));//���ֺ󣬽��������ύ���ҡ�������
		}
		for (Future<Integer> future : re) {
			if(future.get() >= 0)	  //�����Ϊ-1�����ҵ����������
				return future.get();
		}
		return -1;
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		int pSearch = pSearch(46);   //����λ���±�22
//		int pSearch = pSearch(460);  -1��û���ҵ�
		System.out.println(pSearch);
	}
}
