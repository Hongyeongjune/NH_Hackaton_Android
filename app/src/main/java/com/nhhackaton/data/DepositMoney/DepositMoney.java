package com.nhhackaton.data.DepositMoney;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DepositMoney {

    private String depositPrice;

    @Builder
    public DepositMoney(String depositPrice) {
        this.depositPrice = depositPrice;
    }
}
