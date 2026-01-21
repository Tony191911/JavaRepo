package tw.brad.tutor;

public class String02 {

	public static void main(String[] args) {
		// 1.charAt() 回傳指定索引的字元
		System.out.println("Brad".charAt(2));
		
		String s1 = "Brad";
		String s2 = "Brad";
		int a = 10, b = 3;
		// 2.a,b 皆為原始資料型別 (Primitive Data Types)，此時"=="為數值的比較
		System.out.println(a == b);
		
		// 3.s1,s2 皆為參考資料型別也就是"物件" (Non-Primitive Data Types)，此時"=="為記憶體位址的比較
		// Non-Primitive Data Types 又稱為 Reference Data Types 或 Object type
		System.out.println(s1 == s2);
		
		// 4.使用"new"會在建立時，記憶體會提取一個位置存放此物件
		// 即使內容一樣，s3,s4 會指向不同的記憶體位址
		String s3 = new String("Brad");
		String s4 = new String("Brad");
		System.out.println(s3 == s4);
		System.out.println(s1 == s3);
		
		// 5.如果要比較"字串內容"是否相同，必須使用 equals() 方法
		// 它不看位址，只看字元序列是否一模一樣
		System.out.println(s1.equals(s4));
	}

}