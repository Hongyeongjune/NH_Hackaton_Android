package com.nhhackaton.data.DepositMoney;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DepositMoney {

    private String message;

    @Builder
    public DepositMoney(String message) {
        this.message = message;
    }


}
