package com.nhhackaton.view.home.presenter;

import com.nhhackaton.data.DepositMoney.source.DepositMoneyRepository;
import com.nhhackaton.data.DepositMoney.source.DepositMoneySource;
import com.nhhackaton.data.LatelyHistory.LatelyPagingResponse;
import com.nhhackaton.data.LatelyHistory.source.LatelyHistoryRepository;
import com.nhhackaton.data.LatelyHistory.source.LatelyHistorySource;
import com.nhhackaton.listener.OnBasicItemClickListener;
import com.nhhackaton.view.home.adapter.LatelyHistoryAdapterContract;

public class HomePresenter implements HomeContract.Presenter, OnBasicItemClickListener {

    private HomeContract.View view;
    private final DepositMoneyRepository depositMoneyRepository;
    private final LatelyHistoryRepository latelyHistoryRepository;

    private LatelyHistoryAdapterContract.View adapterView;
    private LatelyHistoryAdapterContract.Model adapterModel;


    public HomePresenter(HomeContract.View view, DepositMoneyRepository depositMoneyRepository, LatelyHistoryRepository latelyHistoryRepository) {
        this.view = view;
        this.depositMoneyRepository = depositMoneyRepository;
        this.latelyHistoryRepository = latelyHistoryRepository;
    }

    @Override
    public void onStartItemClick(int position) {
        view.startLatelyDetailActivity(0L);
    }

    @Override
    public void callReadLatelyHistory() {
        latelyHistoryRepository.callReadLatelyHistory(new LatelyHistorySource.LatelyHistoryApiListener() {
            @Override
            public void onSuccess(LatelyPagingResponse latelyPagingResponse) {
                adapterModel.addItems(latelyPagingResponse.getLatelyHistories());
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
    public void setLatelyHistoryAdapterView(LatelyHistoryAdapterContract.View adapterView) {
        this.adapterView = adapterView;
    }

    @Override
    public void setLatelyHistoryAdapterModel(LatelyHistoryAdapterContract.Model adapterModel) {
        this.adapterModel = adapterModel;
    }

    @Override
    public void callReadDepositMoney(String message) {
        depositMoneyRepository.callReadDepositMoney(message, new DepositMoneySource.VirtualAccountApiListener() {
            @Override
            public void onSuccess(String message) {
                view.setVirtualAccount(message);
            }

            @Override
            public void onFail(String message) {
                view.showErrorMessage(message);
            }
        });
    }
}