package tw.brad.streaming;

import java.net.InetAddress;

public class Net01getAddress {

	public static void main(String[] args) {
		try {
			InetAddress ip = InetAddress.getByName("www.google.com");
			System.out.println(ip.getHostAddress());
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
