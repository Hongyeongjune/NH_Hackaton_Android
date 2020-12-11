package com.nhhackaton.data.LoanHistory.source;

import com.nhhackaton.data.LoanHistory.LoanPagingResponse;

public interface LoanHistorySource {

    interface LoanHistoryApiListener {
        void onSuccess(LoanPagingResponse investPagingResponse);
        void onFail(String message);
    }

    void callReadLoanHistory(int pageNo,
                               LoanHistorySource.LoanHistoryApiListener listener);


}
