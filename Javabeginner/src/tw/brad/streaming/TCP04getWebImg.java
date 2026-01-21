package tw.brad.streaming;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


public class TCP04getWebImg {

	public static void main(String[] args) {
		try {
			URL url = new URL("https://cdn.royalcanin-weshare-online.io/hufi63oBaPOZra8q1bf7/v7/bp-lot-2-chihuahua-bw-ws-1?w=480&fm=jpg&auto=format%2Ccompress");
			URLConnection conn = url.openConnection();
			
			
			BufferedInputStream bin = new BufferedInputStream(conn.getInputStream());
			byte[] data = bin.readAllBytes();
			
			BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream("dir1/test.jpg"));
			bout.write(data);
			bout.flush();
			bout.close();
			System.out.println("OK");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
