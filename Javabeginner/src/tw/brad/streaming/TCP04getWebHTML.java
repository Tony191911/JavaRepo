package tw.brad.streaming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


public class TCP04getWebHTML {

	public static void main(String[] args) {
		try {
			// 建立一個 URL 物件指向目標網站：https://www.pchome.com.tw/
			// 根據 url 建立一個網路連線物件，尚未連線(實際連線在讀取時才發生)
			// 建立輸入串流，從網路連線取得資料，把byte轉成char，加上緩衝區
			URL url = new URL("https://www.pchome.com.tw/");
			URLConnection conn = url.openConnection();
			BufferedReader reader = new BufferedReader(
								new InputStreamReader(conn.getInputStream()));
			// line用來存放「每次讀到的一行文字」
			// sb用來累積整個網頁內容
			// 逐行讀取網頁原始碼，同時把讀到的那一行指定給line
			// 把每一行網頁內容加到 StringBuffer 中
			String line; 
			StringBuffer sb = new StringBuffer();
			while ( (line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
			reader.close();

			// 將累積好的整個網頁原始碼轉成 String，一次印出來
			System.out.println(sb.toString());
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
