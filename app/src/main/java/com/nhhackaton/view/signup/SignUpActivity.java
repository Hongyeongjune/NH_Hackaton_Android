package com.nhhackaton.view.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.nhhackaton.R;
import com.nhhackaton.data.SignIn.source.SignInRepository;
import com.nhhackaton.data.SignUp.source.SignUpRepository;
import com.nhhackaton.util.ToastUtils;
import com.nhhackaton.view.signin.SignInActivity;
import com.nhhackaton.view.signin.presenter.SignInContract;
import com.nhhackaton.view.signin.presenter.SignInPresenter;
import com.nhhackaton.view.signup.presenter.SignUpContract;
import com.nhhackaton.view.signup.presenter.SignUpPresenter;

public class SignUpActivity  extends AppCompatActivity implements SignUpContract.View {

    private Context context;
    private EditText etSignUpName, etIdentity, etPassword, etSignUpBirth;
    private Button btnSignUp;
    private CheckBox ckEmailValidate;
    private TextView txtEmailValidate;
    private SignUpContract.Presenter presenter;

    private Boolean isValidateIdentity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        init();
    }

    private void init() {

        context = getApplicationContext();

        etSignUpName = (EditText) findViewById(R.id.et_sign_up_name);
        etIdentity = (EditText) findViewById(R.id.et_sign_up_identity);
        etPassword = (EditText) findViewById(R.id.et_sign_up_password);
        etSignUpBirth = (EditText) findViewById(R.id.et_sign_up_birth);

        btnSignUp = (Button) findViewById(R.id.btn_sign_up);
        ckEmailValidate = (CheckBox) findViewById(R.id.ck_email_validate);
        txtEmailValidate = (TextView) findViewById(R.id.txt_email_validate);

        presenter = new SignUpPresenter(this, SignUpRepository.getInstance());

        if(ckEmailValidate.isChecked()){
            //TODO: validate service 호출

            /*
            if(true){
                txtEmailValidate.setVisibility(View.INVISIBLE);
                isValidateIdentity = true;
            } else {
                txtEmailValidate.setVisibility(View.VISIBLE);
                isValidateIdentity = false;
            }
             */
        }

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isValidateIdentity){
                    startSignInActivity();
                } else {
                    showErrorMessage("이메일 중복체크여부를 확인해주세요.");
                }
            }
        });

    }

    @Override
    public void showErrorMessage(String message) {
        ToastUtils.showToast(context, message);
    }

    @Override
    public void startSignInActivity() {
        Intent intent = new Intent(context, SignInActivity.class);
        startActivity(intent);
    }
}
