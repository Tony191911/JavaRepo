package tw.brad.streaming;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONArray;
import org.json.JSONObject;


public class TCP06getJSON {

	public static void main(String[] args) {
		try {
			URL url = new URL("https://data.moa.gov.tw/Service/OpenData/ODwsv/ODwsvTravelStay.aspx");
			URLConnection conn = url.openConnection();
			
			
			BufferedReader reader = new BufferedReader(
								new InputStreamReader(conn.getInputStream()));
			String line; 
			StringBuffer sb = new StringBuffer();
			while ( (line = reader.readLine()) != null) {
				sb.append(line);
			}
			reader.close();
			System.out.println(sb.length());
			parseJSON(sb.toString());
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	static void parseJSON(String json) {
		// 將 JSON 字串轉成 JSONArray，最外層 JSON 結構是一個陣列
		// root.length()回傳 JSON 陣列中「元素的數量」(資料筆數)
		JSONArray root = new JSONArray(json);
		System.out.println(root.length());
		
		for (int i = 0; i <root.length() ; i++) {
			// 從 JSONArray 中取出第 i 個元素並轉型為 JSONObject
			JSONObject row = root.getJSONObject(i);
			String name = row.getString("Name");
			String addr = row.getString("Address");
			String tel = row.getString("Tel");
			System.out.printf("%s : %s :%s\n", name, addr, tel);
		}
	}

}
