package com.nhhackaton.data.InvestHistory.InvestDeposit;

import lombok.Builder;
import lombok.Getter;

@Getter
public class InvestDeposit {

    private String investDate;
    private String investPrice;

    @Builder
    public InvestDeposit(String investDate, String investPrice) {
        this.investDate = investDate;
        this.investPrice = investPrice;
    }
}
