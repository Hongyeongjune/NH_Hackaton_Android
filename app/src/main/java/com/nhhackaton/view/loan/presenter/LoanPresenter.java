package com.nhhackaton.view.loan.presenter;

import com.nhhackaton.data.document.DocumentRequests;
import com.nhhackaton.data.document.source.DocumentRepository;
import com.nhhackaton.data.document.source.DocumentSource;
import com.nhhackaton.data.loan.LoanApply;
import com.nhhackaton.data.loan.source.LoanRepository;
import com.nhhackaton.data.loan.source.LoanSource;

import java.io.File;
import java.util.List;

import okhttp3.MultipartBody;

public class LoanPresenter implements LoanContract.Presenter{

    private LoanContract.View view;
    private final LoanRepository loanRepository;
    private final DocumentRepository documentRepository;

    public LoanPresenter(LoanContract.View view, LoanRepository loanRepository, DocumentRepository documentRepository) {
        this.view = view;
        this.loanRepository = loanRepository;
        this.documentRepository = documentRepository;
    }

    @Override
    public void callLoanApply(String term, String amount, String identity) {

        LoanApply loanApply = new LoanApply(amount, identity, term);

        if(!loanApply.getLoanAmount().isEmpty()) {
            loanRepository.callLoanApply(loanApply, new LoanSource.LoanApiListener() {

                @Override
                public void onSuccess() {
                    view.startMainActivity();
                }

                @Override
                public void onFail(String message) {
                    view.showErrorMessage(message);
                }
            });
        }

        else view.showErrorMessage("내용을 입력하세요");

    }

    @Override
    public void callGetUri(String identity, File file) {

        documentRepository.callGetUri(identity, file, new DocumentSource.DocumentApiListener() {
            @Override
            public void onSuccess(String uri) {

                view.setDocumentRequests(uri);
            }

            @Override
            public void onFail(String message) {
                view.showErrorMessage(message);
            }

            @Override
            public void onSuccessDocument() {

            }

        });


    }

    @Override
    public void callSetDocument(List<DocumentRequests> documentRequests) {
        documentRepository.callSetDocument(documentRequests, new DocumentSource.DocumentApiListener() {

            @Override
            public void onSuccess(String uri) {

            }

            @Override
            public void onFail(String message) {
                view.showErrorMessage(message);
            }

            @Override
            public void onSuccessDocument() {
                view.startMainActivity();
            }
        });
    }
}