package com.nhhackaton.data.LatelyHistory.LatelyInvestFinish.source;

import com.nhhackaton.data.LatelyHistory.LatelyInvestFinish.LatelyInvestFinish;

import java.util.List;

public interface LatelyInvestFinishSource {
    interface InvestFinishApiListener {
        void onSuccess(List<LatelyInvestFinish> latelyInvestFinishes);
        void onFail(String message);
    }

    void callReadInvestFinish(String identity,
                              LatelyInvestFinishSource.InvestFinishApiListener listener);
}
