package com.nhhackaton.data.LoanHistory.repayment;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Repayment {

    private String borrower;
    private Long id;
    private String investor;
    private String repaymentDate;
    private String repaymentPrice;

    @Builder
    public Repayment(String borrower, Long id, String investor, String repaymentDate, String repaymentPrice) {
        this.borrower = borrower;
        this.id = id;
        this.investor = investor;
        this.repaymentDate = repaymentDate;
        this.repaymentPrice = repaymentPrice;
    }
}
