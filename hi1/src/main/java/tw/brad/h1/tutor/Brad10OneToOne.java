package tw.brad.h1.tutor;

import java.util.List;

import tw.brad.h1.dao.MemberDao;
import tw.brad.h1.entity.Member;
import tw.brad.h1.entity.MemberInfo;

public class Brad10OneToOne {

	public static void main(String[] args) {
		MemberDao dao = new MemberDao();
		
		List<Member> members = dao.findAll();
		for (Member member : members) {
			System.out.printf("%d. %s(%s)\n", member.getId(), member.getName(), member.getEmail());
			MemberInfo mi = member.getMemberInfo();
			if (mi != null) {
				System.out.printf("\t%s:%s\n", mi.getBirthday(), mi.isMale()?"Male":"Female");
			}
		}
	}

}

