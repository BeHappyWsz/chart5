package p;
/**
 * ����ģʽ�µĶ�����
 * @author wsz
 * @date 2017��12��26��
 */
public final class PCData {

	private final int data;//˽���Ҳ��ɱ�

	public PCData(int data) {
		super();
		this.data = data;
	}
	
	public PCData(String data) {
		super();
		this.data = Integer.valueOf(data);
	}

	public int getData() {//ֻ�ṩget����
		return data;
	}

	@Override
	public String toString() {
		return "PCData [data=" + data + "]";
	}
}
