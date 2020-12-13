package com.nhhackaton.data.LatelyHistory.LatelyInterest;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LatelyInterestHistory {

    private String borrower;
    private Long id;
    private String investor;
    private String repaymentDate;
    private String repaymentPrice;

    @Builder
    public LatelyInterestHistory(String borrower, Long id, String investor, String repaymentDate, String repaymentPrice) {
        this.borrower = borrower;
        this.id = id;
        this.investor = investor;
        this.repaymentDate = repaymentDate;
        this.repaymentPrice = repaymentPrice;
    }
}
