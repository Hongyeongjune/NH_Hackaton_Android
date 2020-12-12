package com.nhhackaton.view.intro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.nhhackaton.R;
import com.nhhackaton.util.ToastUtils;
import com.nhhackaton.view.intro.presenter.IntroContract;
import com.nhhackaton.view.intro.presenter.IntroPresenter;
import com.nhhackaton.view.main.MainActivity;
import com.nhhackaton.view.signin.SignInActivity;

public class IntroActivity extends AppCompatActivity implements IntroContract.View{

    private Context context;
    private IntroContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        init();
    }

    private void init() {
        context = getApplicationContext();
        presenter = new IntroPresenter(this);

        loading();

        presenter.confirmAutoSignIn(context);
    }

    private void loading() {
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startSignInActivity() {
        ToastUtils.showToast(context, "자동 로그인 실패");
        Intent intent = new Intent(context, SignInActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void startMainActivity() {
        ToastUtils.showToast(context, "자동 로그인 성공");
        Intent intent = new Intent(context, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
