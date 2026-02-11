package tw.brad.spring3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tw.brad.spring3.entity.Member;
import tw.brad.spring3.entity.Profile;
import tw.brad.spring3.service.MemberService;
import tw.brad.spring3.utils.BCrypt;

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
        Map<String,Object> pDate = (Map<String,Object>)data.get("profile");
        if (pDate != null) {
            profile = new Profile();
            profile.setCname((String)pDate.get("cname"));
            profile.setAge((Integer)pDate.get("age"));
        }

        Member saveMember = service.save(member, profile);
        return ResponseEntity.ok(saveMember);
    }

}
