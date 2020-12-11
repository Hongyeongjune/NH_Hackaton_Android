package com.nhhackaton.view.loanhistory.presenter;

import com.nhhackaton.view.loanhistory.adapter.LoanHistoryAdapterContract;

public interface LoanHistoryContract {

    interface View {
        void showErrorMessage(String message);
        void startLoanDetailActivity(Long historyId);
    }

    interface Presenter {
        void callReadInvestHistory(int pageNo);
        void setLoanHistoryAdapterView(LoanHistoryAdapterContract.View adapterView);
        void setLoanHistoryAdapterModel(LoanHistoryAdapterContract.Model adapterModel);
    }
}
