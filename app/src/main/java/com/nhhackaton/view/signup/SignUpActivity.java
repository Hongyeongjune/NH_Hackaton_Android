package com.nhhackaton.view.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.nhhackaton.R;
import com.nhhackaton.data.SignUp.duplicate.source.DuplicateRepository;
import com.nhhackaton.data.SignUp.source.SignUpRepository;
import com.nhhackaton.util.ToastUtils;
import com.nhhackaton.view.account.AccountActivity;
import com.nhhackaton.view.signup.presenter.SignUpContract;
import com.nhhackaton.view.signup.presenter.SignUpPresenter;

public class SignUpActivity  extends AppCompatActivity implements SignUpContract.View {

    private Context context;
    private EditText etSignUpName, etIdentity, etPassword, etSignUpBirth;
    private Button btnSignUp;
    private Button btnDuplicate;
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
        btnDuplicate = (Button) findViewById(R.id.btn_duplicate);
        btnSignUp = (Button) findViewById(R.id.btn_sign_up);

        txtEmailValidate = (TextView) findViewById(R.id.txt_email_validate);

        presenter = new SignUpPresenter(this, SignUpRepository.getInstance(), DuplicateRepository.getInstance());

        btnDuplicate.setOnClickListener(v -> presenter.callDuplication(etIdentity.getText().toString()));

        btnSignUp.setOnClickListener(v -> presenter.callSignUp(
                etIdentity.getText().toString(),
                etPassword.getText().toString(),
                etSignUpBirth.getText().toString(),
                etSignUpName.getText().toString()
        ));

    }

    @Override
    public void showErrorMessage(String message) {
        ToastUtils.showToast(context, message);
    }

    @Override
    public void showDuplicateMessage(String message) {
        ToastUtils.showToast(context, message);
    }

    @Override
    public void startAccountActivity() {
        Intent intent = new Intent(context, AccountActivity.class);
        startActivity(intent);
    }
}
