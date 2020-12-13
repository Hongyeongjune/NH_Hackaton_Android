package com.nhhackaton.view.home.presenter;

import com.nhhackaton.data.DepositMoney.DepositMoney;
import com.nhhackaton.data.DepositMoney.source.DepositMoneyRepository;
import com.nhhackaton.data.DepositMoney.source.DepositMoneySource;
import com.nhhackaton.data.LatelyHistory.LatelyHistory;
import com.nhhackaton.data.LatelyHistory.LatelyInterest.LatelyInterestHistory;
import com.nhhackaton.data.LatelyHistory.LatelyInterest.source.LatelyInterestHistorySource;
import com.nhhackaton.data.LatelyHistory.LatelyInterest.source.LatelyLatelyInterestHistoryRepository;
import com.nhhackaton.data.LatelyHistory.LatelyInvestDeposit.LatelyInvestDeposit;
import com.nhhackaton.data.LatelyHistory.LatelyInvestDeposit.source.LatelyInvestDepositSource;
import com.nhhackaton.data.LatelyHistory.LatelyInvestDeposit.source.LatelyLatelyInvestDepositRepository;
import com.nhhackaton.data.LatelyHistory.LatelyInvestFinish.LatelyInvestFinish;
import com.nhhackaton.data.LatelyHistory.LatelyInvestFinish.source.LatelyInvestFinishSource;
import com.nhhackaton.data.LatelyHistory.LatelyInvestFinish.source.LatelyLatelyInvestFinishRepository;
import com.nhhackaton.data.LatelyHistory.LatelyPagingResponse;
import com.nhhackaton.data.LatelyHistory.source.LatelyHistoryRepository;
import com.nhhackaton.data.LatelyHistory.source.LatelyHistorySource;
import com.nhhackaton.listener.OnBasicItemClickListener;
import com.nhhackaton.view.home.adapter.LatelyHistoryAdapterContract;

import java.util.ArrayList;
import java.util.List;

public class HomePresenter implements HomeContract.Presenter, OnBasicItemClickListener {

    private HomeContract.View view;
    private final DepositMoneyRepository depositMoneyRepository;

    private final LatelyLatelyInterestHistoryRepository latelyLatelyInterestHistoryRepository;
    private final LatelyLatelyInvestFinishRepository latelyLatelyInvestFinishRepository;
    private final LatelyLatelyInvestDepositRepository latelyLatelyInvestDepositRepository;
    
    private LatelyHistoryAdapterContract.View adapterView;
    private LatelyHistoryAdapterContract.Model adapterModel;


    public HomePresenter(HomeContract.View view, DepositMoneyRepository depositMoneyRepository, LatelyLatelyInterestHistoryRepository latelyLatelyInterestHistoryRepository, LatelyLatelyInvestFinishRepository latelyLatelyInvestFinishRepository, LatelyLatelyInvestDepositRepository latelyLatelyInvestDepositRepository) {
        this.view = view;
        this.depositMoneyRepository = depositMoneyRepository;
        this.latelyLatelyInterestHistoryRepository = latelyLatelyInterestHistoryRepository;
        this.latelyLatelyInvestFinishRepository = latelyLatelyInvestFinishRepository;
        this.latelyLatelyInvestDepositRepository = latelyLatelyInvestDepositRepository;
    }

    @Override
    public void onStartItemClick(int position) {
        view.startLatelyDetailActivity(0L);
    }
    

    @Override
    public void callReadInvestFinish(String identity) {
        latelyLatelyInvestFinishRepository.callReadInvestFinish(identity, new LatelyInvestFinishSource.InvestFinishApiListener() {
            @Override
            public void onSuccess(List<LatelyInvestFinish> latelyInvestFinishes) {
                List<LatelyHistory> latelyHistories = new ArrayList<>();

                for(int i=0; i<latelyInvestFinishes.size(); i++) {
                    latelyHistories.add(
                            new LatelyHistory(
                                    "투자완료",
                                    latelyInvestFinishes.get(i).getLoanDate(),
                                    latelyInvestFinishes.get(i).getLoanPrice()));
                }


                adapterModel.addItems(latelyHistories);
                adapterModel.sortItems();
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
    public void callReadInvestDeposit(String identity) {
        latelyLatelyInvestDepositRepository.callReadInvestDeposit(identity, new LatelyInvestDepositSource.InvestDepositApiListener() {
            @Override
            public void onSuccess(List<LatelyInvestDeposit> latelyInvestDeposit) {
                List<LatelyHistory> latelyHistories = new ArrayList<>();

                for(int i=0; i<latelyInvestDeposit.size(); i++) {
                    latelyHistories.add(
                            new LatelyHistory(
                                    "투자완료",
                                    latelyInvestDeposit.get(i).getInvestDate(),
                                    latelyInvestDeposit.get(i).getInvestPrice()));
                }


                adapterModel.addItems(latelyHistories);
                adapterModel.sortItems();
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
    public void callReadInterest(String identity) {
        latelyLatelyInterestHistoryRepository.callReadInterest(identity, new LatelyInterestHistorySource.InterestHistoryApiListener() {
            @Override
            public void onSuccess(List<LatelyInterestHistory> latelyInterestHistoryList) {
                List<LatelyHistory> latelyHistories = new ArrayList<>();

                for(int i=0; i<latelyInterestHistoryList.size(); i++) {
                    latelyHistories.add(
                            new LatelyHistory(
                                    "투자완료",
                                    latelyInterestHistoryList.get(i).getRepaymentDate(),
                                    latelyInterestHistoryList.get(i).getRepaymentPrice()));
                }


                adapterModel.addItems(latelyHistories);
                adapterModel.sortItems();

                List<LatelyHistory> temp = new ArrayList<>();

                for(int i=0; i<5; i++) {
                    temp.add(adapterModel.getItem(i));
                }

                adapterModel.clearItems();

                adapterModel.addItems(temp);

                adapterView.notifyAdapter();
                adapterModel.setMoreLoading(false);
            }

            @Override
            public void onFail(String message) {

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
    public void callReadDepositMoney(String identity) {
        depositMoneyRepository.callReadDepositMoney(identity, new DepositMoneySource.VirtualAccountApiListener() {
            @Override
            public void onSuccess(DepositMoney depositMoney) {
                view.setVirtualAccount(depositMoney.getDepositPrice());
            }

            @Override
            public void onFail(String message) {
                view.showErrorMessage(message);
            }
        });
    }
}