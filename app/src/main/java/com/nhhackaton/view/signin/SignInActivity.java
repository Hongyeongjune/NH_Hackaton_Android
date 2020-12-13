package com.nhhackaton.view.signin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.nhhackaton.R;
import com.nhhackaton.data.Account.Account;
import com.nhhackaton.data.SignIn.SignIn;
import com.nhhackaton.data.SignIn.source.SignInRepository;
import com.nhhackaton.util.LogUtils;
import com.nhhackaton.util.SharedPreferencesUtils;
import com.nhhackaton.util.ToastUtils;
import com.nhhackaton.view.account.AccountActivity;
import com.nhhackaton.view.main.MainActivity;
import com.nhhackaton.view.signin.presenter.SignInContract;
import com.nhhackaton.view.signin.presenter.SignInPresenter;
import com.nhhackaton.view.signup.SignUpActivity;

public class SignInActivity extends AppCompatActivity implements SignInContract.View {

    private Context context;
    private EditText etEmail;
    private EditText etPassword;
    private Button btnSignIn;
    private Button btnSignUp;
    private CheckBox cbAutoSignIn;
    private SignInContract.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        init();
    }

    private void init() {

        context = getApplicationContext();
        etEmail = (EditText) findViewById(R.id.et_sign_in_email);
        etPassword = (EditText) findViewById(R.id.et_sign_in_password);
        btnSignIn = (Button) findViewById(R.id.btn_sign_in);
        btnSignUp = (Button) findViewById(R.id.btn_sign_in_sign_up);
        cbAutoSignIn = (CheckBox) findViewById(R.id.cb_sign_in_auto_sign);

        presenter = new SignInPresenter(this, SignInRepository.getInstance());

//        btnSignIn.setOnClickListener(v -> presenter.callSignIn(
//                etEmail.getText().toString(), etPassword.getText().toString()
//        ));
        btnSignIn.setOnClickListener(v -> presenter.callSignIn(etEmail.getText().toString(), etPassword.getText().toString()));

        btnSignUp.setOnClickListener(v -> startSignUpActivity());
    }

    private void startSignInActivity() {
        Intent intent = new Intent(context, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void showErrorMessage(String message) {
        ToastUtils.showToast(context, message);
    }

    @Override
    public void startMainActivity(String identity) {

        LogUtils.logInfo(identity);
        SharedPreferencesUtils.writeMemberToInformation(context, identity);

        if(cbAutoSignIn.isChecked()) {
            SharedPreferencesUtils.writeAutoSignInTo(context);
        }

        Intent intent = new Intent(context, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void startSignUpActivity() {
        Intent intent = new Intent(context, SignUpActivity.class);
        startActivity(intent);
    }
}
