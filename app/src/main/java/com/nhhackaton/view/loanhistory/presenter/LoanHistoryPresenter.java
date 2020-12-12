package com.nhhackaton.view.loanhistory.presenter;

import com.nhhackaton.data.LoanHistory.LoanHistory;
import com.nhhackaton.data.LoanHistory.LoanPagingResponse;
import com.nhhackaton.data.LoanHistory.source.LoanHistoryRepository;
import com.nhhackaton.data.LoanHistory.source.LoanHistorySource;
import com.nhhackaton.listener.OnBasicItemClickListener;
import com.nhhackaton.view.loanhistory.adapter.LoanHistoryAdapter;
import com.nhhackaton.view.loanhistory.adapter.LoanHistoryAdapterContract;

public class LoanHistoryPresenter implements LoanHistoryContract.Presenter, OnBasicItemClickListener {

    private LoanHistoryContract.View view;
    private final LoanHistoryRepository loanHistoryRepository;

    private LoanHistoryAdapterContract.Model adapterModel;
    private LoanHistoryAdapterContract.View adapterView;

    public LoanHistoryPresenter(LoanHistoryContract.View view, LoanHistoryRepository loanHistoryRepository) {
        this.view = view;
        this.loanHistoryRepository = loanHistoryRepository;
    }

    @Override
    public void callReadInvestHistory(int pageNo) {
        loanHistoryRepository.callReadLoanHistory(pageNo, new LoanHistorySource.LoanHistoryApiListener() {
            @Override
            public void onSuccess(LoanPagingResponse loanPagingResponse) {
                adapterModel.addItems(loanPagingResponse.getLoan());
                adapterModel.addItems(loanPagingResponse.getInterest());
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
    public void setLoanHistoryAdapterView(LoanHistoryAdapterContract.View adapterView) {
        this.adapterView = adapterView;
    }

    @Override
    public void setLoanHistoryAdapterModel(LoanHistoryAdapterContract.Model adapterModel) {
        this.adapterModel = adapterModel;
    }

    @Override
    public void onStartItemClick(int position) {
        view.startLoanDetailActivity(0L);
    }
}
