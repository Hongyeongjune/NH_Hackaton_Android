package com.nhhackaton.data.InvestHistory.InvestFinish.source;

import com.nhhackaton.data.InvestHistory.InvestFinish.InvestFinish;

import java.util.List;

public interface InvestFinishSource {
    interface InvestFinishApiListener {
        void onSuccess(List<InvestFinish> investFinish);
        void onFail(String message);
    }

    void callReadInvestFinish(String identity,
                               InvestFinishSource.InvestFinishApiListener listener);
}
