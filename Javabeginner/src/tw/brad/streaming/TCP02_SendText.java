package tw.brad.streaming;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;

public class TCP02_SendText {

	public static void main(String[] args) {
		// 用 Socket 物件嘗試建立 TCP 連線，連線目標為 10.0.101.133:9999，對目標發送連線請求
		// 使用 try-with-resources，大括號區塊執行結束後 socket 會自動關閉
		// 若對方有在監聽該 port，連線成功；否則拋出例外
		try (Socket socket = new Socket(InetAddress.getByName("10.0.101.133"), 9999)) {
			// 從 socket 取得輸出串流，之後透過這個串流傳送資料給對方
			// OutputStream 只能寫出 byte
			// OutputStreamWriter 將 byte 轉換成 char 
			// BufferedWriter 加上緩衝區，提高寫入效率
			// writer.write() 只會寫入緩衝區，尚未真正送出
			// flush() 會強制將緩衝區資料送出
			// 最後關閉串流，writer 會連帶關閉 OutputStreamWriter、OutputStream，在 try-with-resources 中可省略
			OutputStream out = socket.getOutputStream();
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));
			writer.write("Hello, World");
			writer.flush();
			writer.close();
			
			System.out.println("TCP Send OK");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
