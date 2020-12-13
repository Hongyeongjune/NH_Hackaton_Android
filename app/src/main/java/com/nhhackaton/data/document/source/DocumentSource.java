package com.nhhackaton.data.document.source;

import com.nhhackaton.data.DepositMoney.DepositMoney;
import com.nhhackaton.data.DepositMoney.source.DepositMoneySource;

import java.io.File;

import okhttp3.MultipartBody;
import retrofit2.http.Multipart;

public interface DocumentSource {

    interface DocumentApiListener {
        void onSuccess(String uri);
        void onFail(String message);
    }

    void callGetUri(String identity, File file, DocumentSource.DocumentApiListener listener);
    
}
