package tw.brad.streaming;

import java.io.FileInputStream;
import java.io.FileOutputStream;


public class Stream01 {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		try {
			// 檔案複製
			FileInputStream fin = new FileInputStream("dir1/SOP_CORS.pdf");
			FileOutputStream fout = new FileOutputStream("dir2/brad.pdf");
			// 一個byte一個byte讀取並寫入檔案
			int b;
			while ((b = fin.read()) != -1) {
				fout.write(b);
			}
			fout.flush();
			fout.close();
			fin.close();
			long time = System.currentTimeMillis() - start;
			System.out.println("OK" + time);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
