package com.nhhackaton.data.Invest;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Invest {

    private String money;

    @Builder
    public Invest(String money) {
        this.money = money;
    }
}
