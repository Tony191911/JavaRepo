package tw.brad.spring7.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import tw.brad.spring7.entity.Member;
import java.util.Optional;


public interface MemberRepo extends JpaRepository<Member, Integer>{
	Optional<Member> findByEmail(String email);
}
