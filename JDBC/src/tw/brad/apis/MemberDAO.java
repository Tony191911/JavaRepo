package tw.brad.apis;

import java.util.List;


public interface MemberDAO {
	boolean addMember(Member member) throws Exception;
	boolean updateMember(Member member) throws Exception;
	boolean delMember(Long id) throws Exception;
	Member findById(Long id) throws Exception;
	List<Member> findAll() throws Exception;
	Member Login(String email, String passwd) throws Exception;
}
