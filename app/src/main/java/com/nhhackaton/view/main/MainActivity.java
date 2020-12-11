package com.nhhackaton.view.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.nhhackaton.R;
import com.nhhackaton.data.DepositMoney.source.DepositMoneyRepository;
import com.nhhackaton.util.SharedPreferencesUtils;
import com.nhhackaton.view.main.presenter.MainContract;
import com.nhhackaton.view.main.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, MainContract.View {

    private Context context;
    private Toolbar toolbar;
    private ImageView ivSideMenu;
    private DrawerLayout dlSideMenu;
    private NavigationView nvSideMenu;

    private TextView tvDepositMoney;
    private Button btnInvest;
    private Button btnInvestHistory;
    private Button btnLoan;
    private Button btnLoanHistory;

    private MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {

        context = getApplicationContext();

        setSupportActionBar((Toolbar) findViewById(R.id.tb_main));
        ivSideMenu = (ImageView) findViewById(R.id.iv_main_side_menu);
        dlSideMenu = (DrawerLayout) findViewById(R.id.dl_main_side_menu);
        nvSideMenu = (NavigationView) findViewById(R.id.nv_main_side_menu);
        nvSideMenu.setNavigationItemSelectedListener(this);

        ivSideMenu.setOnClickListener(v -> dlSideMenu.openDrawer(GravityCompat.START));

        tvDepositMoney = (TextView) findViewById(R.id.tv_main_deposit_money);
        btnInvest = (Button) findViewById(R.id.btn_main_invest);
        btnInvestHistory = (Button) findViewById(R.id.btn_main_invest_history);
        btnLoan = (Button) findViewById(R.id.btn_main_loan);
        btnLoanHistory = (Button) findViewById(R.id.btn_main_loan_history);

        presenter = new MainPresenter(this, DepositMoneyRepository.getInstance());
        presenter.callReadDepositMoney(SharedPreferencesUtils.readMemberFromEmail(context));

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public void setDepositMoney(String message) {
        tvDepositMoney.setText(message + "원");
    }
}
