package com.nhhackaton.data.LatelyHistory.LatelyInvestDeposit;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LatelyInvestDeposit {

    private String investDate;
    private String investPrice;

    @Builder
    public LatelyInvestDeposit(String investDate, String investPrice) {
        this.investDate = investDate;
        this.investPrice = investPrice;
    }
}
