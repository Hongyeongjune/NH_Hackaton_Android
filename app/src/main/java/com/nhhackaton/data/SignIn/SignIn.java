package com.nhhackaton.data.SignIn;

import java.util.function.Predicate;
import java.util.stream.Stream;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SignIn {

    private String email;
    private String password;

    @Builder
    public SignIn(String email, String password) {
        this.email = email;
        this.password = password;
    }

}
