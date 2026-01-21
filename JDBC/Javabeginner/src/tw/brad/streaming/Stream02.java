package tw.brad.streaming;

import java.io.FileInputStream;
import java.io.FileOutputStream;


public class Stream02 {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		try {
			// 檔案複製
			FileInputStream fin = new FileInputStream("dir1/SOP_CORS.pdf");
			FileOutputStream fout = new FileOutputStream("dir2/brad.pdf");
			// 宣告len當作write需要的參數，就是一次要寫入byte的數量
			// buf是一個4KB的緩衝區，只要資料量不是剛好跟緩衝區大小一樣就需要用len，確保最後一次搬運回傳的數量正確
			// 因為read(buf)並不是讀取前把緩衝區的資料清空再裝新的資料，是直接把新讀到的資料覆蓋上去
			// 所以最後一次搬運會殘留部分的舊資料
			// read(buf)回傳實際讀到了幾個 byte，並存入len
			// write(buf, 0, len)寫入時，只寫入從 0 到 len 的有效範圍
			int len; 
			byte[] buf = new byte[4*1024];
			while ((len = fin.read(buf)) != -1) {
				fout.write(buf, 0, len);
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
