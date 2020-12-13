package com.nhhackaton.data.LoanHistory.repayment.source;

import com.nhhackaton.data.LoanHistory.repayment.Repayment;

import java.util.List;

public interface RepaymentSource {

    interface RepaymentApiListener {
        void onSuccess(List<Repayment> repaymentList);
        void onFail(String message);
    }

    void callReadInterest(String identity,
                          RepaymentSource.RepaymentApiListener listener
    );
    
}
