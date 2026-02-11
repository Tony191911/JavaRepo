package tw.brad.spring3.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.brad.spring3.entity.Member;

public interface MemberRepo extends JpaRepository<Member, Long>{
	/*
	 * 動詞 + By + 屬性名稱
	 * findByEmail(String email) => Optional<Member>
	 * findByAge(Integer age) => List<Member>
	 * 
	 * countByAge(Integer age) => long
	 */
	
}
