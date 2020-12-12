package com.nhhackaton.view.fcmalert;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.nhhackaton.R;

public class FcmAlertDialog extends Activity {

    private Context context;
    private TextView tvTitle;
    private TextView tvBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_fcm_alert);
        init();

    }

    private void init() {

        context = getApplicationContext();
        tvTitle = (TextView) findViewById(R.id.tv_fcm_alert_title);
        tvBody = (TextView) findViewById(R.id.tv_fcm_alert_body);

        String title = getIntent().getStringExtra("FCM_TITLE");
        String body = getIntent().getStringExtra("FCM_BODY");
        tvTitle.setText(title);
        tvBody.setText(body);
    }
}
