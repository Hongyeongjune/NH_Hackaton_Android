package com.nhhackaton.view.main.presenter;

public interface MainContract {

    interface View {
        void showErrorMessage(String message);
        void setDepositMoney(String message);
    }

    interface Presenter {
        void callReadDepositMoney(String email);
    }

}
