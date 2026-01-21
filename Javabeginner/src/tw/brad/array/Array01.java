package tw.brad.array;

public class Array01 {

	public static void main(String[] args) {
		int a;
		int[] b;
		a = 10;
		// 設定陣列要有三個值
		b = new int[3];
		System.out.println(a);
		System.out.println(b[0]);
		System.out.println(b[1]);
		System.out.println(b[2]);
		// System.out.println(b[3]);
		System.out.println(b);
		b[1] = 123;
		b[2] = 7;
		for (int i=0; i< b.length; i++) {
			System.out.println(b[i]);
		}
		System.out.println("-----");
		// for-each
		for (int v : b) {
			System.out.println(v);
		}
	}
	
}
