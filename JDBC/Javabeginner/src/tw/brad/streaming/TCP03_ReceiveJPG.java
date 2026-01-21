package tw.brad.streaming;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TCP03_ReceiveJPG {

	public static void main(String[] args) {
		try {
			// 在本機 7777 port 建立 TCP Server，開始監聽連線
			// accept 會阻塞程式直到有 Client 連進來，連線成功後回傳一個Socket(代表 Client 與 Server 之間的通訊通道)
			// 從 socket 取得輸入串流，使用 BufferedInputStream 加上緩衝區，用來接收檔案內容
			// readAllBytes一次把 Client 傳來的所有資料全部讀進記憶體，回傳一個 byte 陣列，裡面是整個檔案的內容
			ServerSocket server = new ServerSocket(7777);
			Socket socket = server.accept();
			BufferedInputStream bin = new BufferedInputStream(socket.getInputStream());
			byte[] data = bin.readAllBytes();
			bin.close();
			server.close();
			// ----------------------------------------
			// 取得 Client 的 IP 位址
			// 產生儲存檔名(動態檔名)，取得IP字串把":"換成"."，防止 IPv6 位址中的":"造成檔名不合法
			// 建立檔案輸出串流，寫入目標檔案的檔名跟路徑filename(路徑字串)，用 BufferedOutputStream 提升寫入效能
			InetAddress ip = socket.getInetAddress();
			String filename = String.format("upload/b%s.jpg", ip.getHostAddress().replace(':', '.'));
			BufferedOutputStream bout = new BufferedOutputStream(
										new FileOutputStream(filename));
			// 從 Client 收到的所有 byte 一次寫入檔案
			bout.write(data);
			bout.flush();
			bout.close();
			System.out.println("Upload Success");
		} catch (IOException e) {
			System.out.println(e);
		}
	}

}
