package tw.brad.streaming;

import java.io.File;
import java.io.FileReader;


public class Input02 {

	public static void main(String[] args) {
		File source = new File("dir1/file2.txt");
		try {
			// Reader專門處理純文字檔(.txt, .json, .csv)
			// Reader是抽象類別，需要用FileReader實作出來才能使用它的方法
			// FileReader會自動將byte轉換成char(1char = 16-bit = 2byte)
			FileReader reader = new FileReader(source);
			
			// 不使用char c，是因為char型別範圍是(0 ~ 65535)，它裝不下 -1
			// 改用int c，int(32位元)型別範圍是(-2,147,483,648 ~ 2,147,483,647)，既能裝下所有字元編碼，又可以裝下-1
			// reader(接通檔案的字元讀取器)，將檔案的位元組解碼成文元編碼放到c，然後再用(char)c 強制轉型成字元
			int c;
			while ((c = reader.read()) != -1) {
				System.out.print((char)c);
			}
			
			reader.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
