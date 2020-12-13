package com.nhhackaton.view.invest.presenter;

public interface InvestContract {
    interface View {
        void showErrorMessage(String message);
        void startAccountCreateActivity();
        void startHomeFragment();
    }

    interface Presenter {
        void callMoneyInvest(String identity, String money);
    }
}
