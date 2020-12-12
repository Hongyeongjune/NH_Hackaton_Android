package com.nhhackaton.data.LoanHistory.source;

import com.nhhackaton.data.LoanHistory.LoanPagingResponse;

public interface LoanHistorySource {

    interface LoanHistoryApiListener {
        void onSuccess(LoanPagingResponse loanPagingResponse);
        void onFail(String message);
    }

    void callReadLoanHistory(int pageNo,
                               LoanHistorySource.LoanHistoryApiListener listener);


}
