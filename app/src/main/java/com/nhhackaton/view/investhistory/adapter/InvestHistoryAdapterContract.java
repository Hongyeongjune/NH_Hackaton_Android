package com.nhhackaton.view.investhistory.adapter;

import com.nhhackaton.data.InvestHistory.InvestHistory;

import java.util.List;

public interface InvestHistoryAdapterContract {

    interface View {
        void notifyAdapter();
    }

    interface Model {
        InvestHistory getItem(int position);
        void addItems(List<InvestHistory> investHistories);
        void setIsLast(boolean isLast);
        void setMoreLoading(boolean isMoreLoading);
    }

}
