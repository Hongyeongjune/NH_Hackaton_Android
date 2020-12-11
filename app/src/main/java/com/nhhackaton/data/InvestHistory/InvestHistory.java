package com.nhhackaton.data.InvestHistory;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InvestHistory {

    private Long id;
    private String type;
    private String money;
    private String information;

    @Builder
    public InvestHistory(Long id, String type, String money, String information) {
        this.id = id;
        this.type = type;
        this.money = money;
        this.information = information;
    }
}
