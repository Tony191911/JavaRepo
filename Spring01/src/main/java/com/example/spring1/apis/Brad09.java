package com.example.spring1.apis;

import com.example.spring1.dto.Member;
import com.example.spring1.dto.MemberResponse;
import com.example.spring1.utils.BCrypt;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/brad09")
public class Brad09 {

    @Autowired
    private NamedParameterJdbcTemplate jdbc;

//  Autowired會留上一次的Id，所以宣告改在addMember裡面做
//  @Autowired
//  private MemberResponse response;

    @PostMapping("/test1")
    public void test1(@RequestBody Member member) {
        System.out.println(member.getEmail());
        System.out.println(member.getPasswd());
        System.out.println(member.getName());
    }

//  @Valid:加入驗證機制
    @PostMapping(value = {"/members", "/members/{isGetId}"})
    public MemberResponse addMember(@RequestBody @Valid Member member,
            @PathVariable(required = false) Boolean isGetId) {
//        System.out.println(member.getEmail());
//        System.out.println(member.getPasswd());
//        System.out.println(member.getName());
        System.out.println(isGetId);

//      isGetId == null：檢查 isGetId 這個變數是否為空
//      ?false：如果是 null，就把 isGetId 設定為 false
//      :isGetId：如果不是 null(代表前端有傳true或false過來)，就維持它原本的值
        isGetId = isGetId == null?false:isGetId;

        String sql = """
                INSERT INTO member
                    (email, passwd, name)
                VALUES
                    (:email, :passwd, :name)
                """;
        HashMap<String, Object> map = new HashMap<>();
        map.put("email", member.getEmail());
        map.put("passwd", BCrypt.hashpw(member.getPasswd(), BCrypt.gensalt()));
        map.put("name", member.getName());

//      int n = jdbc.update(sql, map);
//      System.out.println(n);

//      KeyHolder: 拿回資料庫自動產生的主鍵（Auto-increment ID）
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int n = jdbc.update(sql, new MapSqlParameterSource(map), keyHolder);
        System.out.println(n);
        System.out.println(keyHolder.getKey().intValue());

        MemberResponse response = new MemberResponse();
        if (n > 0) {
            int lastId = keyHolder.getKey().intValue();
            if (isGetId) {
                member.setId(lastId);
                response.setInsertId(member.getId());
            }

            response.setError(0);
            response.setMessage("新增成功");
            response.setMember(member);
        }else {
            response.setError(-1);
            response.setMessage("新增失敗");
        }

        return response;
    }

}
