package tw.brad.h1.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import tw.brad.h1.entity.Member;
import tw.brad.h1.utils.HibernateUtil;

public class MemberDao {

	public void addMember(Member member) {
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession();) {
			transaction = session.beginTransaction();
			session.persist(member);
			transaction.commit();
		}catch (Exception e) {
			System.out.println(e);
			if (transaction != null) {
//				rollback把剛剛做的資料庫變更全部取消，例如：
//				Email重複（違反唯一鍵）
//				欄位長度超過
//				網路斷線
//				SQL錯誤
				transaction.rollback();
			}
		}
	}
	public void delMember(Member member) {
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession();) {
			transaction = session.beginTransaction();
			session.remove(member);
			transaction.commit();
		}catch (Exception e) {
			System.out.println(e);
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}
	public void updateMember(Member member) {
		Transaction transaction = null;
		try(Session session = HibernateUtil.getSessionFactory().openSession();) {
			transaction = session.beginTransaction();
			session.merge(member);
			transaction.commit();
		}catch (Exception e) {
			System.out.println(e);
			if (transaction != null) {
				transaction.rollback();
			}
		}
	}
	
	public Member findById(long id) {
		try(Session session = HibernateUtil.getSessionFactory().openSession();) {
//			把查詢結果轉成 member物件回傳
			return session.get(Member.class, id);
		}catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	public List<Member> findAll() {
		try(Session session = HibernateUtil.getSessionFactory().openSession();) {
//			HQL語法: SELECT * FROM Member(類別名稱)
			String hql = "FROM Member";
//			建立 HQL物件(準備好查詢規格)，但還沒轉物件
//			Member.class表示查詢結果要轉成 Member 物件
			Query<Member> query = session.createQuery(hql, Member.class);
//			getResultList()會把每一列資料 new 成 Member 物件，塞進 List 回傳
			return query.getResultList();
		}catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	public List<Member> findByLike(String key) {
		try(Session session = HibernateUtil.getSessionFactory().openSession();) {
//			HQL: SELECT * FROM Member(類別名稱) WHERE email LIKE xx OR name LIKE xx
			String hql = "FROM Member m WHERE m.email LIKE :key OR name LIKE :key";
			Query<Member> query = session.createQuery(hql, Member.class);
			query.setParameter("key", "%" + key + "%");
			return query.getResultList();
		}catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
}
