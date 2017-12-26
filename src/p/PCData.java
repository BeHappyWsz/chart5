package p;
/**
 * 不变模式下的对象类
 * @author wsz
 * @date 2017年12月26日
 */
public final class PCData {

	private final int data;//私用且不可变

	public PCData(int data) {
		super();
		this.data = data;
	}
	
	public PCData(String data) {
		super();
		this.data = Integer.valueOf(data);
	}

	public int getData() {//只提供get方法
		return data;
	}

	@Override
	public String toString() {
		return "PCData [data=" + data + "]";
	}
}
