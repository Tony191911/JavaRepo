package tw.brad.streaming;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Output01 {

	public static void main(String[] args) {
		String mesg = "Hello, Brad\n";
		try {
			// OutputStream是抽象類別，需要用FileOutputStream實作出來才能使用它的方法
			// FileOutputStream的true參數是續寫、false參數是覆寫，如果檔案不存在會自動建立新的空白檔案
			// 但資料夾就不會自動建立，要手動建立
			// fout(將記憶體接通到檔案的輸出流)
			FileOutputStream fout = new FileOutputStream("dir1/file3.txt", true);
			
			// getBytes方法產生byte陣列把字串轉成byte並裝進去
			// write方法把在記憶體上的資料搬到硬碟的檔案上
			// flush方法強制緩衝區資料不管有多少，都馬上寫入檔案
			// 沒有用flush的話，系統會在緩衝區累積到一定量的資料才一次倒進硬碟
			fout.write(mesg.getBytes());
			fout.flush();
			fout.close();
			System.out.println("OK");
		} catch (FileNotFoundException e) {
			System.out.println("1:" + e);
		} catch (IOException e) {
			System.out.println("2:" + e);
		}

	}

}
