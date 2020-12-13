package com.nhhackaton.data.Invest;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Invest {

    private String investPrice;

    @Builder
    public Invest(String investPrice) {
        this.investPrice = investPrice;
    }
}
