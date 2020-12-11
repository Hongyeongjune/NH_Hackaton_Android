package com.nhhackaton.data.DepositMoney.source;

public class DepositMoneyRepository implements DepositMoneySource {

    private static DepositMoneyRepository depositMoneyRepository;
    private DepositMoneyRemoteDataSource depositMoneyRemoteDataSource;

    public static DepositMoneyRepository getInstance() {

        if(depositMoneyRepository == null) {
            depositMoneyRepository = new DepositMoneyRepository();
        }

        return depositMoneyRepository;

    }

    private DepositMoneyRepository() {

        depositMoneyRemoteDataSource = DepositMoneyRemoteDataSource.getInstance();
    }

    @Override
    public void callReadDepositMoney(String message, VirtualAccountApiListener listener) {
        depositMoneyRemoteDataSource.callReadDepositMoney(message, listener);
    }
}
