package tw.brad.spring3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tw.brad.spring3.entity.Member;
import tw.brad.spring3.entity.Profile;
import tw.brad.spring3.repo.MemberRepo;
import tw.brad.spring3.service.MemberService;

import java.util.Map;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService service;
    /*
     * POST / members
     * {
     *      "email": xxx,
     *      "passwd": xxx,
     *      "profile": {
     *          "cname": xxx,
     *          "age": 18
     *      }
     * }
     *  => Member
     *
     */

    @PostMapping("")
    public ResponseEntity<Member> addMember(@RequestBody Map<String,Object> data) {
        Member member = new Member();
        member.setEmail((String) data.get("email"));
        member.setPasswd((String) data.get("passwd"));

        Profile profile = null;
        Map<String,Object> pData = (Map<String,Object>)data.get("profile");
        if (pData != null) {
            profile = new Profile();
            profile.setCname((String) pData.get("cname"));
            profile.setAge((Integer) pData.get("age"));
        }

        Member saveMember = service.save(member, profile);
        return ResponseEntity.ok(saveMember);
    }

    @Autowired
    private MemberRepo memberRepo;

    @Transactional
    @PutMapping("")
    public ResponseEntity<Member> update(@RequestBody Map<String,Object> body) {
        int temp = (Integer)body.get("id");
        long id = temp;
        Member member = memberRepo.findById(id).orElse(null);
        if (member != null) {
            member.setEmail((String)body.get("email"));
            Member save = memberRepo.save(member);
            return ResponseEntity.ok(save);
        }
        return ResponseEntity.ok(null);
    }


    @PutMapping("/{memberId}/profile")
    public ResponseEntity<Profile> saveProfile(@PathVariable Long memberId,
                                               @RequestBody Map<String,Object> pdata) {
        Profile profile = new Profile();
        profile.setCname((String)pdata.get("cname"));
        profile.setAge((Integer)pdata.get("age"));

        Profile saveProfile = service.setProfile2Member(profile, memberId);
        return ResponseEntity.ok(saveProfile);
    }

    @GetMapping("/{memberId}")
    public  ResponseEntity<Member> queryMember(@PathVariable Long memberId) {
        return ResponseEntity.ok(service.findMemberById(memberId));
    }

}
