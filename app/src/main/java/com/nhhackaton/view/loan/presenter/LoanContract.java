package com.nhhackaton.view.loan.presenter;

import com.nhhackaton.data.document.DocumentRequests;
import com.nhhackaton.data.loan.LoanApply;

import java.io.File;

import okhttp3.MultipartBody;

public interface LoanContract {

    interface View {
        void showErrorMessage(String message);
        void startMainActivity();
        void setDocumentRequests(String uri);

    }

    interface Presenter {
        void callLoanApply(String term, String amount, String identity);
        void callGetUri(String identity, File file);
    }
}
