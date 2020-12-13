package com.nhhackaton.data.LoanHistory.repayment.source;


import com.nhhackaton.data.LoanHistory.repayment.Repayment;

public class RepaymentRepository implements RepaymentSource {

    private static RepaymentRepository repaymentRepository;
    private RepaymentRemoteDataSource repaymentRemoteDataSource;

    public static RepaymentRepository getInstance() {
        if(repaymentRepository == null) {
            repaymentRepository = new RepaymentRepository();
        }

        return repaymentRepository;
    }

    private RepaymentRepository() {
        repaymentRemoteDataSource = RepaymentRemoteDataSource.getInstance();
    }


    @Override
    public void callReadInterest(String identity, RepaymentApiListener listener) {
        repaymentRemoteDataSource.callReadInterest(identity, listener);
    }
}
