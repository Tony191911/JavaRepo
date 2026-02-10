package tw.brad.spring2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.brad.spring2.entity.Member;
import tw.brad.spring2.repository.MemberRepository;
import tw.brad.spring2.utils.BCrypt;

@Service
public class MemberService {
    @Autowired
    private MemberRepository repository;

    public boolean checkEmail(String email) {
        return repository.existsByEmail(email);
    }

    public boolean register(Member member) {
        if (repository.existsByEmail(member.getEmail())) {
            return false;
        }

        member.setPasswd(BCrypt.hashpw(member.getPasswd(), BCrypt.gensalt()));
        Member m = repository.save(member);
        System.out.println(m.getId());
        return m != null;
    }

}
