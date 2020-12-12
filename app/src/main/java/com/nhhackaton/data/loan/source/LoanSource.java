package com.nhhackaton.data.loan.source;

import com.nhhackaton.data.loan.LoanApply;

public interface LoanSource {

    interface LoanApiListener {
        void onSuccess(LoanApply loanApply);
        void onFail(String message);
    }

    void callLoanApply(LoanApply loanApply, LoanApiListener listener);
}
