package com.nhhackaton.view.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;
import com.nhhackaton.R;

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

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
