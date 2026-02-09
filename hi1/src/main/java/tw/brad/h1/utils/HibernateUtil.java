package tw.brad.h1.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import tw.brad.h1.entity.Course;
import tw.brad.h1.entity.Member;
import tw.brad.h1.entity.MemberInfo;
import tw.brad.h1.entity.Order;
import tw.brad.h1.entity.OrderItem;
import tw.brad.h1.entity.Student;

public class HibernateUtil {
//	SessionFactory 屬性負責建立連線（Session）。
//	它的初始化非常耗費資源，因此設定為 static，讓它在記憶體中只存在一份。
	private static SessionFactory sessionFactory;

//	getSessionFactory()連線的進入點，這是（Lazy Loading）的機制。
//	只有在第一次呼叫這個方法時，才會去讀取設定檔並建立物件；之後再呼叫的人都是拿到已經建立好的那份。
	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {

//			Configuration: 負責蒐集所有的設定資訊。
//			configure("hibernate.cfg.xml"): 告訴 Hibernate 去讀取該 XML 檔案。
//			通常包含：資料庫 URL、帳號、密碼，以及驅動程式（Driver）。
			Configuration config = new Configuration();
			config.configure("hibernate.cfg.xml");

//			註冊實體類別（Entity Registration）
//			告訴 Hibernate：「哪些類別（Class）需要對應到資料庫的表（Table）。」
			config.addAnnotatedClass(Member.class);
			config.addAnnotatedClass(MemberInfo.class);
			
			config.addAnnotatedClass(Order.class);
			config.addAnnotatedClass(OrderItem.class);
			
			config.addAnnotatedClass(Student.class);
			config.addAnnotatedClass(Course.class);

//			建立 SessionFactory 實體
//			1️.讀取並解析 hibernate.cfg.xml 的內容
//			2.建立資料庫連線池，準備好一組可重複使用的連線（效能關鍵）
//			3.ORM註冊，解析所有類別裡的 @Entity, @Table, @Column註解
//			4️.建立快取與內部結構，SQL 產生機制、物件狀態追蹤機制、快取結構
//			5.最後才產生 SessionFactory
//			之後每次使用 openSession() 都是從這個工廠拿 Session。
			sessionFactory = config.buildSessionFactory();
		}
		
		return sessionFactory;
	}
}
