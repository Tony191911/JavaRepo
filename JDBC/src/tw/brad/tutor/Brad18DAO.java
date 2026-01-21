package tw.brad.tutor;

import java.util.List;

import tw.brad.apis.Member;
import tw.brad.apis.MemberDAO;
import tw.brad.apis.MemberDAOImpl;

public class Brad18DAO {

	public static void main(String[] args) {
		MemberDAO dao = new MemberDAOImpl();
		
//		Member member = new Member();
//		member.setEmail("b1@brad.tw");
//		member.setPasswd("12345678");
//		member.setName("B1");
//		try {
//			if(dao.addMember(member)) {
//				System.out.println("OK1");
//			}else {
//				System.out.println("XX1");
//			}
//		} catch (Exception e) {
//			System.out.println(e);
//		}
		
//		try {
//			Member member = dao.findById((long) 3);
//			System.out.println(member.getEmail() + ":" + member.getName());
//		} catch (Exception e) {
//			System.out.println(e);
//		}
		
//		try {
//			if (dao.delMember((long)3)) {
//				System.out.println("DELETE success");
//			}else {
//				System.out.println("DELETE failure");
//			}
//		} catch (Exception e) {
//			System.out.println(e);
//		}
		
//		try {
//			Member member = dao.findById((long)2);
//			System.out.println(member.getEmail() + ":" + member.getName());
//			member.setEmail("tony@brad.tw");
//			member.setPasswd("12345678");
//			if (dao.updateMember(member)) {
//				System.out.println("OK3");
//			}else {
//				System.out.println("XX3");
//			}
//		} catch (Exception e) {
//			System.out.println(e);
//		}
		
		try {
			List<Member> members = dao.findAll();
			for (Member member : members) {
				System.out.printf("%d:%s:%s\n", member.getId(), member.getEmail(), member.getName());
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}

}
