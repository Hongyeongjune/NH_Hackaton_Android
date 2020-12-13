package com.nhhackaton.data.document.source;

import com.nhhackaton.data.document.MemberResponse;
import com.nhhackaton.network.api.RetrofitApiClient;
import com.nhhackaton.util.LogUtils;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DocumentRemoteDataSource implements DocumentSource {

    private static DocumentRemoteDataSource INSTANCE;

    public static DocumentRemoteDataSource getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new DocumentRemoteDataSource();
        }

        return INSTANCE;
    }
    
    @Override
    public void callGetUri(String identity, File file, DocumentApiListener listener) {

        LogUtils.logInfo("CALL BODY IN CALL : " + file.getName());
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part multipartFile = MultipartBody.Part.createFormData("multipartFile", file.getName(), requestFile);
        Call<MemberResponse> result = RetrofitApiClient.getInstance().getRetrofitApiService().callGetUri(identity, multipartFile);

        result.enqueue(new Callback<MemberResponse>() {
            @Override
            public void onResponse(Call<MemberResponse> call, Response<MemberResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String uri = response.body().getUrl();
                    LogUtils.logInfo("URI : " + uri);
                    listener.onSuccess(uri);
                    return;
                }
                listener.onFail("파일 업로드 실패");
            }

            @Override
            public void onFailure(Call<MemberResponse> call, Throwable t) {
                listener.onFail("통신 실패");
            }
        });
    }
}
