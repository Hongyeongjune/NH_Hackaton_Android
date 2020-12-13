package com.nhhackaton.view.loanhistory.presenter;

import com.nhhackaton.data.InvestHistory.Interest.InterestHistory;
import com.nhhackaton.data.InvestHistory.Interest.source.InterestHistoryRepository;
import com.nhhackaton.data.InvestHistory.Interest.source.InterestHistorySource;
import com.nhhackaton.data.LoanHistory.LoanHistory;
import com.nhhackaton.data.LoanHistory.LoanMoney.LoanMoney;
import com.nhhackaton.data.LoanHistory.LoanMoney.source.LoanMoneyRepository;
import com.nhhackaton.data.LoanHistory.LoanMoney.source.LoanMoneySource;
import com.nhhackaton.data.LoanHistory.repayment.Repayment;
import com.nhhackaton.data.LoanHistory.repayment.source.RepaymentRepository;
import com.nhhackaton.data.LoanHistory.repayment.source.RepaymentSource;
import com.nhhackaton.listener.OnBasicItemClickListener;
import com.nhhackaton.view.loanhistory.adapter.LoanHistoryAdapterContract;

import java.util.ArrayList;
import java.util.List;

public class LoanHistoryPresenter implements LoanHistoryContract.Presenter, OnBasicItemClickListener {

    private LoanHistoryContract.View view;
    private final LoanMoneyRepository loanMoneyRepository;
    private final RepaymentRepository repaymentRepository;

    private LoanHistoryAdapterContract.Model adapterModel;
    private LoanHistoryAdapterContract.View adapterView;

    public LoanHistoryPresenter(LoanHistoryContract.View view, LoanMoneyRepository loanMoneyRepository, RepaymentRepository repaymentRepository) {
        this.view = view;
        this.loanMoneyRepository = loanMoneyRepository;
        this.repaymentRepository = repaymentRepository;
    }


    @Override
    public void callReadLoanMoney(String identity) {
        loanMoneyRepository.callReadLoanMoney(identity, new LoanMoneySource.LoanMoneyApiListener() {
            @Override
            public void onSuccess(List<LoanMoney> loanMonies) {

                List<LoanHistory> loanHistories = new ArrayList<>();

                for(int i=0; i<loanMonies.size(); i++) {
                    loanHistories.add(new LoanHistory(
                            loanMonies.get(i).getDate(),
                            loanMonies.get(i).getType(),
                            loanMonies.get(i).getAmount(),
                            loanMonies.get(i).getRepayCount() + "/" + loanMonies.get(i).getTerm()
                    ));
                }
                adapterModel.addItems(loanHistories);
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
    public void callReadRepayment(String identity) {
        repaymentRepository.callReadInterest(identity, new RepaymentSource.RepaymentApiListener() {
            @Override
            public void onSuccess(List<Repayment> repaymentList) {
                List<LoanHistory> loanHistories = new ArrayList<>();

                for(int i=0; i<repaymentList.size(); i++) {
                    loanHistories.add(new LoanHistory(
                            repaymentList.get(i).getRepaymentDate(),
                            "이자 상환",
                            repaymentList.get(i).getRepaymentPrice(),
                            ""
                    ));
                }
                adapterModel.addItems(loanHistories);
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
