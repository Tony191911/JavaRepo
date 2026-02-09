package com.example.spring1.dto;

import jakarta.validation.constraints.*;

public class Member {
    private Integer id;

    @Email
    private String email;
//  ^:開頭，$:結尾，.:任意字元，{n,}:至少n個以上
//
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}$")
    private String passwd;
    /*
    @Min(3)
    @Max(20)
    @NotBlank
    @Positive
    @PositiveOrZero
    @Negative
    @NegativeOrZero
    @Past
    @FutureOrPresent
    */
    @Size(min = 3, max = 20)
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
