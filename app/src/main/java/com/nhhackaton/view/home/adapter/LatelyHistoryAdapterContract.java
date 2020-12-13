package com.nhhackaton.view.home.adapter;


import com.nhhackaton.data.LatelyHistory.LatelyHistory;

import java.util.List;

public interface LatelyHistoryAdapterContract {

    interface View {
        void notifyAdapter();
    }

    interface Model {
        LatelyHistory getItem(int position);
        void addItems(List<LatelyHistory> latelyHistories);
        void setIsLast(boolean isLast);
        void setMoreLoading(boolean isMoreLoading);
        void sortItems();
        void clearItems();
    }

}
