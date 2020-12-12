package com.nhhackaton.data.SignUp;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SignUp {
    private String identity;
    private String password;
    private String name;
    private String birth;

    @Builder
    public SignUp(String identity, String password, String name, String birth) {
        this.identity = identity;
        this.password = password;
        this.name = name;
        this.birth = birth;
    }
}
