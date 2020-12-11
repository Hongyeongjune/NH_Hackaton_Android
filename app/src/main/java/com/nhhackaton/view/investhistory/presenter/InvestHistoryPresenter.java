package com.nhhackaton.view.investhistory.presenter;

import com.nhhackaton.data.InvestHistory.InvestHistory;
import com.nhhackaton.data.InvestHistory.InvestPagingResponse;
import com.nhhackaton.data.InvestHistory.source.InvestHistoryRepository;
import com.nhhackaton.data.InvestHistory.source.InvestHistorySource;
import com.nhhackaton.listener.OnBasicItemClickListener;
import com.nhhackaton.view.investhistory.adapter.InvestHistoryAdapter;
import com.nhhackaton.view.investhistory.adapter.InvestHistoryAdapterContract;

public class InvestHistoryPresenter implements InvestHistoryContract.Presenter, OnBasicItemClickListener {


    private InvestHistoryContract.View view;
    private final InvestHistoryRepository investHistoryRepository;

    private InvestHistoryAdapterContract.View adapterView;
    private InvestHistoryAdapterContract.Model adapterModel;

    public InvestHistoryPresenter(InvestHistoryContract.View view, InvestHistoryRepository investHistoryRepository) {
        this.view = view;
        this.investHistoryRepository = investHistoryRepository;
    }

    @Override
    public void callReadInvestHistory(int pageNo) {

        investHistoryRepository.callReadInvestHistory(pageNo, new InvestHistorySource.InvestHistoryApiListener() {
            @Override
            public void onSuccess(InvestPagingResponse investPagingResponse) {
                adapterModel.addItems(investPagingResponse.getDeposit());
                adapterModel.addItems(investPagingResponse.getInvest());
                adapterModel.addItems(investPagingResponse.getInterest());
                adapterView.notifyAdapter();
                adapterModel.setMoreLoading(false);
            }

            @Override
            public void onFail(String message) {
                view.showErrorMessage(message);
            }
        });
    }

    @Override
    public void setInvestHistoryAdapterView(InvestHistoryAdapterContract.View adapterView) {
        this.adapterView = adapterView;
    }

    @Override
    public void setInvestHistoryAdapterModel(InvestHistoryAdapterContract.Model adapterModel) {
        this.adapterModel = adapterModel;
    }

    @Override
    public void onStartItemClick(int position) {
        view.startInvestDetailActivity(0L);
    }
}
