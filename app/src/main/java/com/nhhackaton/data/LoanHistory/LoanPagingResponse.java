package com.nhhackaton.data.LoanHistory;

import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoanPagingResponse {

    List<LoanHistory> loan = new ArrayList<>();
    List<LoanHistory> interest = new ArrayList<>();

    @Builder
    public LoanPagingResponse(List<LoanHistory> loan, List<LoanHistory> interest) {
        this.loan = loan;
        this.interest = interest;
    }
}
