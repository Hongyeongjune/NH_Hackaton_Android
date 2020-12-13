package com.nhhackaton.data.LoanHistory.LoanMoney.source;


import com.nhhackaton.data.LoanHistory.LoanMoney.LoanMoney;

import java.util.List;

public interface LoanMoneySource {

    interface LoanMoneyApiListener {
        void onSuccess(List<LoanMoney> loanMonies);
        void onFail(String message);
    }

    void callReadLoanMoney(String identity,
                             LoanMoneySource.LoanMoneyApiListener listener);

    
}
