package tw.brad.spring4.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.brad.spring4.entity.Member;

public interface MemberRepo extends JpaRepository<Member, Long>{
	Member findByEmail(String email);
}
