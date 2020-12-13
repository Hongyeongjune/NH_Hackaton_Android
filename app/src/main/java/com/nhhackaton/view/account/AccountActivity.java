package com.nhhackaton.view.account;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.nhhackaton.R;
import com.nhhackaton.data.Account.source.AccountRepository;
import com.nhhackaton.data.SignIn.SignIn;
import com.nhhackaton.util.SharedPreferencesUtils;
import com.nhhackaton.util.ToastUtils;
import com.nhhackaton.view.account.presenter.AccountContract;
import com.nhhackaton.view.account.presenter.AccountPresenter;
import com.nhhackaton.view.signin.SignInActivity;

public class AccountActivity extends AppCompatActivity implements AccountContract.View {

    private Context context;
    private Spinner spinner;
    private EditText etAccountNumber;
    private Button btnAccountNumber;
    private Button btnCancel;

    private AccountContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        init();
    }

    private void init() {

        context = getApplicationContext();
        spinner = (Spinner) findViewById(R.id.spinner_account);
        etAccountNumber = (EditText) findViewById(R.id.et_account_number);
        btnAccountNumber = (Button) findViewById(R.id.btn_register_account_number);
        btnCancel = (Button) findViewById(R.id.btn_cancel);

        presenter = new AccountPresenter(this, AccountRepository.getInstance());

        btnAccountNumber.setOnClickListener(v ->
                presenter.callSetAccount(
                        "dudwl", etAccountNumber.getText().toString(), "011"
                )
        );

        btnCancel.setOnClickListener(v -> {
            finish();
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
        finish();
    }
}
