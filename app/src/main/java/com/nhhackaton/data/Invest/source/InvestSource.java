package com.nhhackaton.data.Invest.source;

import com.nhhackaton.data.Invest.Invest;

public interface InvestSource {
    interface InvestApiListener {
        void onSuccess();
        void onFail(String message);
    }

    void callApplyInvest(String identity, Invest invest, InvestApiListener listener);
}
