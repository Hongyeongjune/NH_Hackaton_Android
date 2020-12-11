package com.nhhackaton.data.InvestHistory.source;

import com.nhhackaton.data.InvestHistory.InvestPagingResponse;

public interface InvestHistorySource {

    interface InvestHistoryApiListener {
        void onSuccess(InvestPagingResponse investPagingResponse);
        void onFail(String message);
    }

    void callReadInvestHistory(int pageNo,
                          InvestHistoryApiListener listener);

}
