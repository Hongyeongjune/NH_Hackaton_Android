package com.nhhackaton.view.investhistory.presenter;

import com.nhhackaton.data.InvestHistory.Interest.InterestHistory;
import com.nhhackaton.data.InvestHistory.InvestDeposit.InvestDeposit;
import com.nhhackaton.data.InvestHistory.InvestFinish.InvestFinish;
import com.nhhackaton.view.investhistory.adapter.InvestHistoryAdapterContract;

import java.util.List;

public interface InvestHistoryContract {

    interface View {
        void showErrorMessage(String message);
        void startInvestDetailActivity(Long historyId);
    }

    interface Presenter {
        void callReadInvestFinish(String identity);
        void callReadInvestDeposit(String identity);
        void callReadInterest(String identity);
        void setInvestHistoryAdapterView(InvestHistoryAdapterContract.View adapterView);
        void setInvestHistoryAdapterModel(InvestHistoryAdapterContract.Model adapterModel);
    }

}
