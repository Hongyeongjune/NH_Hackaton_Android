package com.nhhackaton.data.InvestHistory.InvestDeposit.source;

import com.nhhackaton.data.InvestHistory.InvestDeposit.InvestDeposit;

import java.util.List;

public interface InvestDepositSource {

    interface InvestDepositApiListener {
        void onSuccess(List<InvestDeposit> investDeposit);
        void onFail(String message);
    }

    void callReadInvestDeposit(String identity,
                               InvestDepositSource.InvestDepositApiListener listener);
    
}
