package com.nhhackaton.data.InvestHistory.InvestFinish;

import lombok.Builder;
import lombok.Getter;

@Getter
public class InvestFinish {

    private String loanDate;
    private String loanPrice;

    @Builder
    public InvestFinish(String loanDate, String loanPrice) {
        this.loanDate = loanDate;
        this.loanPrice = loanPrice;
    }
}
