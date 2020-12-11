package com.nhhackaton.data.InvestHistory;

import java.util.ArrayList;
import java.util.List;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InvestPagingResponse {

    private List<InvestHistory> deposit = new ArrayList<>();
    private List<InvestHistory> invest = new ArrayList<>();
    private List<InvestHistory> interest = new ArrayList<>();

    @Builder
    public InvestPagingResponse(List<InvestHistory> deposit, List<InvestHistory> invest, List<InvestHistory> interest) {
        this.deposit = deposit;
        this.invest = invest;
        this.interest = interest;
    }
}
