package com.nhhackaton.data.LoanHistory;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoanHistory {

    private Long id;
    private String type;
    private String money;
    private String information;

    @Builder
    public LoanHistory(Long id, String type, String money, String information) {
        this.id = id;
        this.type = type;
        this.money = money;
        this.information = information;
    }
}
