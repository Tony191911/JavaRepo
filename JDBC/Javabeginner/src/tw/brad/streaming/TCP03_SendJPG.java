package tw.brad.streaming;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.net.InetAddress;
import java.net.Socket;

public class TCP03_SendJPG {

	public static void main(String[] args) {
		try {
			// 一樣先建立 TCP 連線，然後再建立輸出串流(用BufferedOutputStream包起來)
			// 因為傳的是jpg檔案，所以還要建立一個「檔案輸入串流」把讀取到的資料寫入輸出串流
			Socket socket = new Socket(InetAddress.getByName("10.0.101.133"), 7777);
			BufferedOutputStream bout = new BufferedOutputStream(socket.getOutputStream());
			BufferedInputStream bin = 
				new BufferedInputStream(new FileInputStream("dir1/coffee.jpg"));
			
			// 建立一個大小為4MB的byte陣列，用來一次把最多4MB的資料載入記憶體
			// len 用來記錄「這次實際讀到多少 byte」
			// 從檔案讀資料到 buf，回傳值(這次讀到的byte數，-1：檔案讀完) 指派給 len
			// 把剛剛讀到的 len 個 byte從 buf 的前 len 個位置寫到輸出串流
			// bout 透過 TCP 把資料傳給 Server
			byte[] buf = new byte[4*1024*1024];
			int len;
			while ( (len = bin.read(buf)) != -1) {
				bout.write(buf, 0, len);
			}
			
			bin.close();
			bout.flush();
			bout.close();			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

}
