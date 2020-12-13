package com.nhhackaton.data.InvestHistory.Interest.source;

import com.nhhackaton.data.InvestHistory.Interest.InterestHistory;

import java.util.List;

public interface InterestHistorySource {

    interface InterestHistoryApiListener {
        void onSuccess(List<InterestHistory> interestHistoryList);
        void onFail(String message);
    }

    void callReadInterest(String identity,
            InterestHistorySource.InterestHistoryApiListener listener
    );
    
}
