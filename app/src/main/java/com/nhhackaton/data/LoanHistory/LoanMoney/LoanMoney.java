package com.nhhackaton.data.LoanHistory.LoanMoney;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoanMoney {

    //금액
    private String amount;
    //날짜
    private String date;
    private Long loanNo;
    //얼마 남았는지
    private Long repayCount;
    //기간
    private String term;
    //상환인지 대출인지
    private String type;

    @Builder
    public LoanMoney(String amount, String date, Long loanNo, Long repayCount, String term, String type) {
        this.amount = amount;
        this.date = date;
        this.loanNo = loanNo;
        this.repayCount = repayCount;
        this.term = term;
        this.type = type;
    }
}
