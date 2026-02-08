package tw.brad.h1.tutor;

import tw.brad.h1.dao.MemberDao;
import tw.brad.h1.entity.Member;
import tw.brad.h1.entity.MemberInfo;
import tw.brad.h1.utils.BCrypt;

public class Brad07OneToOne {

	public static void main(String[] args) {
		MemberDao dao = new MemberDao();
		
		Member member = new Member();
		member.setEmail("email");
		member.setName("BradIII");
		member.setPasswd(BCrypt.hashpw("12345678", BCrypt.gensalt()));
		
		
		MemberInfo info = new MemberInfo();
		info.setBirthday("1999-01-02");
		info.setMale(false);
		
		member.setMemberInfo(info);
//		讓 Member擁有 MemberInfo
		dao.addMember(member);
//		開 transaction
//		session.persist(member)
//		Hibernate 看到有關聯 → 一起存 member_info
//		commit
	}

}
