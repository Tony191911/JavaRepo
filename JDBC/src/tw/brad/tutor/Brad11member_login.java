package tw.brad.tutor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Scanner;

import tw.brad.apis.BCrypt;
import tw.brad.apis.Member;

public class Brad11member_login {
	private static final String URL = "jdbc:mysql://localhost:8889/iii";
	private static final Properties prop = new Properties();
	private static final String USER = "root";
	private static final String PASSWD = "root";
	private static final String SQL_LOGIN = """
			SELECT id, email, passwd, name
			FROM member
			WHERE email = ?
			""";
	private static final String SQL_CHPASSWD = """
			UPDATE member
			SET passwd = ?
			WHERE id = ?
			""";
	
	public static void main(String[] args) {
		prop.put("user", USER);
		prop.put("password", PASSWD);
		
		// 1. Login
		Member member = login();
		if (member != null) {
			System.out.printf("Welcome, %s\n", member.getName() );
			System.out.print("Do you want to change password? (Y/N): ");
			Scanner scanner = new Scanner(System.in);
			String toChange = scanner.nextLine();
			// 2. Change Password
			if (toChange.equalsIgnoreCase("Y")) {
				boolean changed = chPasswd(member);
			}else {
				System.out.println("Password unchanged.");
			}
		}
	}
	
	static Member login() {
		System.out.println("Member Login");
		Scanner scanner = new Scanner(System.in);
		System.out.print("Email: ");
		String email = scanner.nextLine();
		System.out.print("Password: ");
		String passwd = scanner.nextLine();
		// 向資料庫請求連線，放入預先編譯 SQL 語句
		try(Connection conn = DriverManager.getConnection(URL, prop);
			PreparedStatement pstmt = conn.prepareStatement(SQL_LOGIN);) 
		{
			// 將使用者輸入的 email 填入 SQL 語句中的第一個 ? 佔位符
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			// 第一層先判斷有沒有這個email
			// 第二層再判斷密碼對不對
			if (rs.next()) {
				if (BCrypt.checkpw(passwd, rs.getString("passwd"))) {
					// 建立一個Member物件，從rs取得執行完SQL_LOGIN的查詢結果(會員資料)
					Member member = new Member(rs.getLong("id"), 
							rs.getString("email"), 
							rs.getString("passwd"), 
							rs.getString("name") );
					return member;
				}else {
					System.out.println("Login Failure(2)");
				}
			}else {
				System.out.println("Login Failure(1)");
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	static boolean chPasswd(Member member) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Change Password: ");
		String chPasswd = scanner.nextLine();
		String hashedPasswd = BCrypt.hashpw(chPasswd, BCrypt.gensalt());
		
		try(Connection conn = DriverManager.getConnection(URL, prop);
			PreparedStatement pstmt = conn.prepareStatement(SQL_CHPASSWD);) 
		{
			pstmt.setString(1, hashedPasswd);
			pstmt.setLong(2, member.getId());
			int rowAffected = pstmt.executeUpdate();
			
			if (rowAffected > 0) {
				member.setPasswd(hashedPasswd);
				System.out.println("Change password success!");
				return true;
			}else {
				System.out.println("Password change failed!");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return false;
	}
}
