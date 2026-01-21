package tw.brad.streaming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class TCP02_ReceiveText {

	public static void main(String[] args) {
		try {
			// 建立 ServerSocket 物件，在本機的 9999 port上監聽TCP連線
			// 如果 port 已被佔用，則直接拋出例外
			// accept會阻塞程式執行直到有人連進來，連線成功就回傳一個socket(代表 Client 與 Server 的連線通道)
			// accept嚴格來說是「等待並接受連線」
			ServerSocket server = new ServerSocket(9999);
			Socket socket = server.accept();    // Listen
			// 從socket取得輸入串流，為 Client 傳來的 byte 資料
			// InputStreamReader 把 byte 轉成 char
			// BufferedReader 加上緩衝區，提供 readLine 方法，提高讀取效率
			
			BufferedReader reader = 
				new BufferedReader(
				new InputStreamReader( socket.getInputStream() )
				);
			
			// 宣告一個字串變數，用來存每次讀到的一行資料
			// 從輸入串流中讀取一行文字，成功讀到一行就回傳該字串，對方關閉輸出串流則回傳null
			// 同時指派給 line 儲存目前讀到的那一行資料，一次只能讀一行存一行
			String line;
			while ( (line = reader.readLine()) != null ) {
				System.out.println(line);
			}
			
			// 關閉socket，關閉與 Client 的連線並通知對方這條 TCP 連線結束
			// 關閉server，不再接受任何新的連線，伺服器停止服務
			socket.close();
			server.close();
			System.out.println("Server OK");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
