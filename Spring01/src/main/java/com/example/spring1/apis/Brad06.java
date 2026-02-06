package com.example.spring1.apis;

import com.example.spring1.dto.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class Brad06 {

    @GetMapping("")
    public void findAll() {
        System.out.println("findAll()");
    }

    @GetMapping("/{id}")
    public void findById(@PathVariable String id) {
        System.out.printf("findById: %s\n", id);
    }

    @GetMapping("/{name}/{gender}")
    public void findByNameWithGender(@PathVariable String name, @PathVariable String gender) {
        System.out.printf("findByNameWithGender: %s, %s\n", name, gender);
    }

    @PostMapping("")
    public void register(@RequestBody User user) {
        System.out.println("register:" + user.getName());
    }

    @PutMapping("")
    public void update(@RequestBody User user) {
        System.out.println("update:" + user.getName());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        System.out.println("delete:" + id);
    }

}
