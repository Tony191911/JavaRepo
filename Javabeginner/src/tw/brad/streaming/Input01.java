package tw.brad.streaming;

import java.io.File;
import java.io.FileInputStream;


public class Input01 {

	public static void main(String[] args) {
		File source = new File("dir1/file2.txt");
		try {
			// InputStream可以處理所有檔案，適合處理圖片、影片、二進位檔
			// InputStream是抽象類別，需要用FileInputStream實作出來才能使用它的方法
			// 宣告byte一維陣列，陣列長度=檔案byte總長度，根據檔案大小配置一樣大小的記憶體空間
			// source.length()回傳值是long型別，需轉型成int型別，才能放進陣列裡
			FileInputStream fin = new FileInputStream(source);
			byte[] buf = new byte[(int)source.length()];
			
			// read方法，FileInputStream 覆寫 InputStream(父類別)
			// fin(接通檔案到記憶體的輸入流)，透過read方法把檔案裡的位元組(byte)直接倒進buf(陣列容器)
			// 一次性將檔案資料全部倒進 buf 陣列容器(緩衝區)
			fin.read(buf);
			System.out.println(new String(buf));
			
			// 檔案讀進去不用flush，但還是要close
			// 沒有將輸入流close的話，輸入流會一直開著，而檔案會被鎖定就無法對它做任何動作
			fin.close();
			System.out.println("OK");
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
