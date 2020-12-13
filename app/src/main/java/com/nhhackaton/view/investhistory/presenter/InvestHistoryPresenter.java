package com.nhhackaton.view.investhistory.presenter;

import com.nhhackaton.data.InvestHistory.Interest.InterestHistory;
import com.nhhackaton.data.InvestHistory.Interest.source.InterestHistoryRepository;
import com.nhhackaton.data.InvestHistory.Interest.source.InterestHistorySource;
import com.nhhackaton.data.InvestHistory.InvestDeposit.InvestDeposit;
import com.nhhackaton.data.InvestHistory.InvestDeposit.source.InvestDepositRepository;
import com.nhhackaton.data.InvestHistory.InvestDeposit.source.InvestDepositSource;
import com.nhhackaton.data.InvestHistory.InvestFinish.InvestFinish;
import com.nhhackaton.data.InvestHistory.InvestFinish.source.InvestFinishRepository;
import com.nhhackaton.data.InvestHistory.InvestFinish.source.InvestFinishSource;
import com.nhhackaton.data.InvestHistory.InvestHistory;
import com.nhhackaton.data.InvestHistory.InvestPagingResponse;
import com.nhhackaton.data.InvestHistory.source.InvestHistoryRepository;
import com.nhhackaton.data.InvestHistory.source.InvestHistorySource;
import com.nhhackaton.listener.OnBasicItemClickListener;
import com.nhhackaton.view.investhistory.adapter.InvestHistoryAdapter;
import com.nhhackaton.view.investhistory.adapter.InvestHistoryAdapterContract;

import java.util.ArrayList;
import java.util.List;

public class InvestHistoryPresenter implements InvestHistoryContract.Presenter, OnBasicItemClickListener {


    private InvestHistoryContract.View view;
    private final InvestFinishRepository investFinishRepository;
    private final InvestDepositRepository investDepositRepository;
    private final InterestHistoryRepository interestHistoryRepository;


    private InvestHistoryAdapterContract.View adapterView;
    private InvestHistoryAdapterContract.Model adapterModel;

    public InvestHistoryPresenter(InvestHistoryContract.View view, InvestFinishRepository investFinishRepository, InvestDepositRepository investDepositRepository, InterestHistoryRepository interestHistoryRepository) {
        this.view = view;
        this.investFinishRepository = investFinishRepository;
        this.investDepositRepository = investDepositRepository;
        this.interestHistoryRepository = interestHistoryRepository;
    }

    @Override
    public void callReadInvestFinish(String identity) {
        investFinishRepository.callReadInvestFinish(identity, new InvestFinishSource.InvestFinishApiListener() {
            @Override
            public void onSuccess(List<InvestFinish> investFinish) {
                List<InvestHistory> investHistories = new ArrayList<>();

                for(int i=0; i<investFinish.size(); i++) {
                    investHistories.add(
                            new InvestHistory(
                                    "투자완료",
                                    investFinish.get(i).getLoanDate(),
                                    investFinish.get(i).getLoanPrice()));
                }


                adapterModel.addItems(investHistories);
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
        investDepositRepository.callReadInvestDeposit(identity, new InvestDepositSource.InvestDepositApiListener() {
            @Override
            public void onSuccess(List<InvestDeposit> investDeposit) {
                List<InvestHistory> investHistories = new ArrayList<>();

                for(int i=0; i<investDeposit.size(); i++) {
                    investHistories.add(
                            new InvestHistory(
                                    "예치완료",
                                    investDeposit.get(i).getInvestDate(),
                                    investDeposit.get(i).getInvestPrice()));
                }

                adapterModel.addItems(investHistories);
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
        interestHistoryRepository.callReadInterest(identity, new InterestHistorySource.InterestHistoryApiListener() {
            @Override
            public void onSuccess(List<InterestHistory> interestHistoryList) {
                List<InvestHistory> investHistories = new ArrayList<>();

                for(int i=0; i<interestHistoryList.size(); i++) {
                    investHistories.add(
                            new InvestHistory(
                                    "이자상환",
                                    interestHistoryList.get(i).getRepaymentDate(),
                                    interestHistoryList.get(i).getRepaymentPrice()));
                }

                adapterModel.addItems(investHistories);
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
