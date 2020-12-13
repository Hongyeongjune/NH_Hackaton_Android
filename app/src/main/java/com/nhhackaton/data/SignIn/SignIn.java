package com.nhhackaton.data.SignIn;

import java.util.function.Predicate;
import java.util.stream.Stream;

import lombok.Builder;
import lombok.Getter;

@Getter
public class SignIn {

    private String identity;
    private String password;

    @Builder
    public SignIn(String identity, String password) {
        this.identity = identity;
        this.password = password;
    }

}
