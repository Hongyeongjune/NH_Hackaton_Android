package com.nhhackaton.view.loanhistory.adapter;

import com.nhhackaton.data.LoanHistory.LoanHistory;

import java.util.List;

public interface LoanHistoryAdapterContract {

    interface View {
        void notifyAdapter();
    }

    interface Model {
        LoanHistory getItem(int position);
        void addItems(List<LoanHistory> loanHistories);
        void setIsLast(boolean isLast);
        void setMoreLoading(boolean isMoreLoading);
    }

}
