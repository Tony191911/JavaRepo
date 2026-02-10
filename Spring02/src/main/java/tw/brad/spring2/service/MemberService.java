package tw.brad.spring2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tw.brad.spring2.repository.MemberRepository;

@Service
public class MemberService {
    @Autowired
    private MemberRepository repository;

    public boolean checkEmail(String email) {
        return repository.existsByEmail(email);
    }

}
