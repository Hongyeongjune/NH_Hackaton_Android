package com.nhhackaton.view.home.presenter;

import com.nhhackaton.view.home.adapter.LatelyHistoryAdapterContract;

public interface HomeContract {

    interface View {
        void showErrorMessage(String message);
        void startLatelyDetailActivity(Long LatelyId);
        void setVirtualAccount(String message);
    }

    interface Presenter {

        void callReadInvestFinish(String identity);
        void callReadInvestDeposit(String identity);
        void callReadInterest(String identity);

        void setLatelyHistoryAdapterView(LatelyHistoryAdapterContract.View adapterView);
        void setLatelyHistoryAdapterModel(LatelyHistoryAdapterContract.Model adapterModel);
        void callReadDepositMoney(String identity);
    }

}
