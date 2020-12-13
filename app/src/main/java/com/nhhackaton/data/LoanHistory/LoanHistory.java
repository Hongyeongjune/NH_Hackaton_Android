package com.nhhackaton.data.LoanHistory;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoanHistory {

    private String date;
    private String type;
    private String money;
    private String count;

    @Builder
    public LoanHistory(String date, String type, String money, String count) {
        this.date = date;
        this.type = type;
        this.money = money;
        this.count = count;
    }
}
