package com.nhhackaton.data.LatelyHistory.LatelyInterest.source;

import com.nhhackaton.data.LatelyHistory.LatelyInterest.LatelyInterestHistory;

import java.util.List;

public interface LatelyInterestHistorySource {

    interface InterestHistoryApiListener {
        void onSuccess(List<LatelyInterestHistory> latelyInterestHistoryList);
        void onFail(String message);
    }

    void callReadInterest(String identity,
                          LatelyInterestHistorySource.InterestHistoryApiListener listener
    );
    
}
