package com.nhhackaton.data.document.source;

import com.nhhackaton.data.DepositMoney.DepositMoney;
import com.nhhackaton.data.DepositMoney.source.DepositMoneySource;
import com.nhhackaton.data.document.DocumentRequests;

import java.io.File;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.http.Multipart;

public interface DocumentSource {

    interface DocumentApiListener {
        void onSuccess(String uri);
        void onFail(String message);
        void onSuccessDocument();
    }

    void callGetUri(String identity, File file, DocumentSource.DocumentApiListener listener);
    void callSetDocument(List<DocumentRequests> documentRequests, DocumentSource.DocumentApiListener listener);
}
