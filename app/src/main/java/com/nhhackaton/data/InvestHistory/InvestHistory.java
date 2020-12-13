package com.nhhackaton.data.InvestHistory;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InvestHistory {

    private String type;
    private String date;
    private String money;

    public InvestHistory(String type, String date, String money) {
        this.type = type;
        this.date = date;
        this.money = money;
    }
}
