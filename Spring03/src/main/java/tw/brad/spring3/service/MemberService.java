package tw.brad.spring3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;
import tw.brad.spring3.entity.Member;
import tw.brad.spring3.entity.Profile;
import tw.brad.spring3.repo.MemberRepo;
import tw.brad.spring3.repo.ProfileRepo;
import tw.brad.spring3.utils.BCrypt;

@Service
public class MemberService {
    @Autowired private MemberRepo memberRepo;
    @Autowired private ProfileRepo profileRepo;

    @Transactional
    public Member save(Member member, Profile profile) {
        member.setPasswd(BCrypt.hashpw(member.getPasswd(), BCrypt.gensalt()));
        member.setProfile(profile);
        return memberRepo.save(member);
    }

    @Transactional
    public Profile setProfile2Member(Profile profile, Long memberId) {
        Member member = memberRepo.findById(memberId).orElse(null);
        if (member != null) {
            Profile p = member.getProfile();
            if (p != null) {
                profile.setId(p.getId());
            }
            member.setProfile(profile);
            Member save = memberRepo.save(member);
            return save.getProfile();
        }
        return null;
    }

    public Member findMemberById(Long memberId) {
        return memberRepo.findById(memberId).orElse(null);
    }

}
