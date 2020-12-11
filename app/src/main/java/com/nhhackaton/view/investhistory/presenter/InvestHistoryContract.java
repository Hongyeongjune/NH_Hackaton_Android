package com.nhhackaton.view.investhistory.presenter;

import com.nhhackaton.view.investhistory.adapter.InvestHistoryAdapterContract;

public interface InvestHistoryContract {

    interface View {
        void showErrorMessage(String message);
        void startInvestDetailActivity(Long historyId);
    }

    interface Presenter {
        void callReadInvestHistory(int pageNo);
        void setInvestHistoryAdapterView(InvestHistoryAdapterContract.View adapterView);
        void setInvestHistoryAdapterModel(InvestHistoryAdapterContract.Model adapterModel);
    }

}
