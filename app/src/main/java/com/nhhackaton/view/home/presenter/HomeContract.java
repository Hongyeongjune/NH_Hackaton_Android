package com.nhhackaton.view.home.presenter;

import com.nhhackaton.view.home.adapter.LatelyHistoryAdapterContract;

public interface HomeContract {

    interface View {
        void showErrorMessage(String message);
        void startLatelyDetailActivity(Long LatelyId);
        void setVirtualAccount(String message);
    }

    interface Presenter {
        void callReadLatelyHistory();
        void setLatelyHistoryAdapterView(LatelyHistoryAdapterContract.View adapterView);
        void setLatelyHistoryAdapterModel(LatelyHistoryAdapterContract.Model adapterModel);
        void callReadDepositMoney(String message);
    }

}
