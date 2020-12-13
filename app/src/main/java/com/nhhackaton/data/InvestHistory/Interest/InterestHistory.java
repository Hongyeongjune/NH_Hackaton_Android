package com.nhhackaton.data.InvestHistory.Interest;

import java.time.LocalDate;
import java.util.Date;

import lombok.Builder;
import lombok.Getter;

@Getter
public class InterestHistory {

    private String borrower;
    private Long id;
    private String investor;
    private String repaymentDate;
    private String repaymentPrice;

    @Builder
    public InterestHistory(String borrower, Long id, String investor, String repaymentDate, String repaymentPrice) {
        this.borrower = borrower;
        this.id = id;
        this.investor = investor;
        this.repaymentDate = repaymentDate;
        this.repaymentPrice = repaymentPrice;
    }
}
