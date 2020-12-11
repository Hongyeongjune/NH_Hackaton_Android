package com.nhhackaton.view.main.presenter;

import com.nhhackaton.data.DepositMoney.source.DepositMoneyRepository;
import com.nhhackaton.data.DepositMoney.source.DepositMoneySource;

public class MainPresenter implements MainContract.Presenter{

    private MainContract.View view;
    private DepositMoneyRepository depositMoneyRepository;

    public MainPresenter(MainContract.View view, DepositMoneyRepository depositMoneyRepository) {
        this.view = view;
        this.depositMoneyRepository = depositMoneyRepository;
    }

    @Override
    public void callReadDepositMoney(String email) {
        depositMoneyRepository.callReadDepositMoney(email, new DepositMoneySource.VirtualAccountApiListener() {
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
