package com.nhhackaton.view.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;
import com.nhhackaton.R;
import com.nhhackaton.view.home.HomeFragment;
import com.nhhackaton.view.investhistory.InvestHistoryFragment;
import com.nhhackaton.view.loan.LoanFragment;
import com.nhhackaton.view.loanhistory.LoanHistoryFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Context context;
    private Toolbar toolbar;
    private ImageView ivSideMenu;
    private DrawerLayout dlSideMenu;
    private NavigationView nvSideMenu;

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

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.ll_main, HomeFragment.createFragment())
                .commit();
        invalidateOptionsMenu();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.drawer_menu_home:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.ll_main, HomeFragment.createFragment())
                        .commit();
                invalidateOptionsMenu();
                dlSideMenu.closeDrawer(GravityCompat.START);
                break;
            case R.id.drawer_menu_information:
                break;
            case R.id.drawer_menu_invest:
                break;
            case R.id.drawer_menu_invest_history:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.ll_main, InvestHistoryFragment.createFragment())
                        .commit();
                invalidateOptionsMenu();
                dlSideMenu.closeDrawer(GravityCompat.START);
                break;
            case R.id.drawer_menu_loan:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.ll_main, LoanFragment.createFragment())
                        .commit();
                invalidateOptionsMenu();
                dlSideMenu.closeDrawer(GravityCompat.START);
                break;
            case R.id.drawer_menu_loan_history:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.ll_main, LoanHistoryFragment.createFragment())
                        .commit();
                invalidateOptionsMenu();
                dlSideMenu.closeDrawer(GravityCompat.START);
                break;
            case R.id.drawer_menu_logout:
                break;
        }

        return false;
    }
}
