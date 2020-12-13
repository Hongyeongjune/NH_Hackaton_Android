package com.nhhackaton.data.LatelyHistory.LatelyInvestDeposit.source;

import com.nhhackaton.data.LatelyHistory.LatelyInvestDeposit.LatelyInvestDeposit;

import java.util.List;

public interface LatelyInvestDepositSource {

    interface InvestDepositApiListener {
        void onSuccess(List<LatelyInvestDeposit> latelyInvestDeposit);
        void onFail(String message);
    }

    void callReadInvestDeposit(String identity,
                               LatelyInvestDepositSource.InvestDepositApiListener listener);
    
}
