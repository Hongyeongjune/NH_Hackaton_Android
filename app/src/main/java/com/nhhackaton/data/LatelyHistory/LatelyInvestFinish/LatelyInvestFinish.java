package com.nhhackaton.data.LatelyHistory.LatelyInvestFinish;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LatelyInvestFinish {

    private String loanDate;
    private String loanPrice;

    @Builder
    public LatelyInvestFinish(String loanDate, String loanPrice) {
        this.loanDate = loanDate;
        this.loanPrice = loanPrice;
    }
}
