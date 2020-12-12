package com.nhhackaton.view.loan;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.nhhackaton.R;
import com.nhhackaton.data.loan.LoanApply;
import com.nhhackaton.util.LogUtils;
import com.nhhackaton.util.ToastUtils;
import com.nhhackaton.view.loan.presenter.LoanContract;
import com.nhhackaton.view.main.MainActivity;
import com.nhhackaton.view.signin.presenter.SignInContract;

import java.io.File;


public class LoanActivity extends AppCompatActivity implements View.OnClickListener, LoanContract.View {

    private Context context;

    private LinearLayout layoutType3, layoutType4, layoutType5, layoutType6, layoutType7, layoutType8;

    private TextView txtFile1, txtFile2, txtFile3, txtFile4, txtFile5, txtFile6, txtFile7, txtFile8, txtFile9, txtSelect;
    private EditText edtInput;
    private ImageButton btnGallery1, btnGallery2, btnGallery3, btnGallery4, btnGallery5, btnGallery6, btnGallery7, btnGallery8, btnGallery9;
    private Button btnLoanApply;

    private RadioGroup rdGrpMonth, rdGrpPaymentMethod, rdGrpWorkStat, rdGrpMarried;
    private RadioButton rdBtnInput, rdBtnSelect, rdBtnMonth12, rdBtnMonth24, rdBtnUnemployed, rdBtnEmployed, rdBtnUnmarried, rdBtnMarried;
    private CheckBox ckBusinessmanYn;

    private static final int PICK_FROM_CAMERA = 0;
    private static final int PICK_FROM_ALBUM = 1;
    private Uri mImageCaptureUri;

    private String selectedBtnNm;
    private Boolean isMarried;
    private Boolean isEmployed;

    private LoanContract.Presenter presenter;
    private LoanApply loanApply;    //TODO: send dto to server
    private StringBuilder sbFileUrl = new StringBuilder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan);

        init();
    }

    private void init() {

        context = getApplicationContext();

        //wrap
        layoutType3 = (LinearLayout) findViewById(R.id.layout_type3);
        layoutType4 = (LinearLayout) findViewById(R.id.layout_type4);
        layoutType5 = (LinearLayout) findViewById(R.id.layout_type5);
        layoutType6 = (LinearLayout) findViewById(R.id.layout_type6);
        layoutType7 = (LinearLayout) findViewById(R.id.layout_type7);
        layoutType8 = (LinearLayout) findViewById(R.id.layout_type8);

        //file layout
        txtFile1 = (TextView) findViewById(R.id.txt_file1);
        txtFile2 = (TextView) findViewById(R.id.txt_file2);
        txtFile3 = (TextView) findViewById(R.id.txt_file3);
        txtFile4 = (TextView) findViewById(R.id.txt_file4);
        txtFile5 = (TextView) findViewById(R.id.txt_file5);
        txtFile6 = (TextView) findViewById(R.id.txt_file6);
        txtFile7 = (TextView) findViewById(R.id.txt_file7);
        txtFile8 = (TextView) findViewById(R.id.txt_file8);
        txtFile9 = (TextView) findViewById(R.id.txt_file9);
        btnGallery1 = (ImageButton) findViewById(R.id.btn_gallery1);
        btnGallery2 = (ImageButton) findViewById(R.id.btn_gallery2);
        btnGallery3 = (ImageButton) findViewById(R.id.btn_gallery3);
        btnGallery4 = (ImageButton) findViewById(R.id.btn_gallery4);
        btnGallery5 = (ImageButton) findViewById(R.id.btn_gallery5);
        btnGallery6 = (ImageButton) findViewById(R.id.btn_gallery6);
        btnGallery7 = (ImageButton) findViewById(R.id.btn_gallery7);
        btnGallery8 = (ImageButton) findViewById(R.id.btn_gallery8);
        btnGallery9 = (ImageButton) findViewById(R.id.btn_gallery9);

        btnGallery1.setOnClickListener(this);
        btnGallery2.setOnClickListener(this);
        btnGallery3.setOnClickListener(this);
        btnGallery4.setOnClickListener(this);
        btnGallery5.setOnClickListener(this);
        btnGallery6.setOnClickListener(this);
        btnGallery7.setOnClickListener(this);
        btnGallery8.setOnClickListener(this);
        btnGallery9.setOnClickListener(this);

        //info layout

        txtSelect = (TextView) findViewById(R.id.txt_select);
        edtInput = (EditText) findViewById(R.id.edt_input);
        rdGrpMonth = (RadioGroup) findViewById(R.id.rd_grp_month);
        rdBtnMonth12 = (RadioButton) findViewById(R.id.rd_btn_month_12);
        rdBtnMonth24 = (RadioButton) findViewById(R.id.rd_btn_month_24);
        rdGrpPaymentMethod = (RadioGroup) findViewById(R.id.rd_grp_payment_method);
        rdBtnInput = (RadioButton) findViewById(R.id.rd_btn_input);
        rdBtnSelect = (RadioButton) findViewById(R.id.rd_btn_select);
        rdGrpWorkStat = (RadioGroup) findViewById(R.id.rd_grp_work_stat);
        rdBtnEmployed = (RadioButton) findViewById(R.id.rd_btn_employed);
        rdBtnUnemployed = (RadioButton) findViewById(R.id.rd_btn_unemployed);
        rdGrpMarried = (RadioGroup) findViewById(R.id.rd_grp_married);
        rdBtnMarried = (RadioButton) findViewById(R.id.rd_btn_married);
        rdBtnUnmarried = (RadioButton) findViewById(R.id.rd_btn_unmarried);
        ckBusinessmanYn = (CheckBox) findViewById(R.id.ck_businessmanYn);

        //rdGrp listener
        rdGrpMonth.setOnCheckedChangeListener(rdGrpMonthChangeListener);
        rdBtnMonth12.setOnClickListener(rdBtnClickListener);
        rdBtnMonth24.setOnClickListener(rdBtnClickListener);

        rdGrpPaymentMethod.setOnCheckedChangeListener(rdGrpPaymentMethodBtnChangeListener);
        rdBtnInput.setOnClickListener(rdBtnClickListener);
        rdBtnSelect.setOnClickListener(rdBtnClickListener);

        rdGrpWorkStat.setOnCheckedChangeListener(rdGrpWorkStatChangeListener);
        rdBtnEmployed.setOnClickListener(rdBtnClickListener);
        rdBtnUnemployed.setOnClickListener(rdBtnClickListener);
        ckBusinessmanYn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ckBusinessmanYn.isChecked()){
                    layoutType6.setVisibility(View.VISIBLE);
                } else {
                    layoutType6.setVisibility(View.GONE);
                }
            }
        });

        rdGrpMarried.setOnCheckedChangeListener(rdGrpMarriedChangeListener);
        rdBtnMarried.setOnClickListener(rdBtnClickListener);
        rdBtnUnmarried.setOnClickListener(rdBtnClickListener);

        //bottom layout
        btnLoanApply = (Button) findViewById(R.id.btn_loan_apply);
        btnLoanApply.setOnClickListener(v -> presenter.callLoanApply(
                //TODO: loanApply set
                loanApply


        ));

    }

    private void doTakeAlbumAction() {
        // 앨범 호출
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_FROM_ALBUM);
    }


    private String getRealPathFromURI(Uri contentURI) {
        String filePath;
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) {
            filePath = contentURI.getPath();

        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            filePath = cursor.getString(idx);
            cursor.close();
        }
        return filePath;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_OK) {
            return;
        }
        switch (requestCode) {

            case PICK_FROM_ALBUM: {
                mImageCaptureUri = data.getData();
            }

            case PICK_FROM_CAMERA: {
                Intent intent = new Intent("com.android.camera.action.CROP");
                intent.setDataAndType(mImageCaptureUri, "image/*");

                File file = new File(getRealPathFromURI(mImageCaptureUri));



                if("1".equals(selectedBtnNm)){
                    txtFile1.setText(file.getName());
                } else if("2".equals(selectedBtnNm)){
                    txtFile2.setText(file.getName());
                } else if("3".equals(selectedBtnNm)){
                    txtFile3.setText(file.getName());
                } else if("4".equals(selectedBtnNm)){
                    txtFile4.setText(file.getName());
                } else if("5".equals(selectedBtnNm)){
                    txtFile5.setText(file.getName());
                } else if("6".equals(selectedBtnNm)){
                    txtFile6.setText(file.getName());
                } else if("7".equals(selectedBtnNm)){
                    txtFile7.setText(file.getName());
                } else if("8".equals(selectedBtnNm)){
                    txtFile8.setText(file.getName());
                } else if("9".equals(selectedBtnNm)){
                    txtFile9.setText(file.getName());
                }

                break;
            }
        }
    }

    @Override
    public void onClick(View v) {
        DialogInterface.OnClickListener albumListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                doTakeAlbumAction();
            }
        };

        DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }

        };

        if(v.getId() == R.id.btn_gallery1) {
            selectedBtnNm = "1";
        } else if(v.getId() == R.id.btn_gallery2) {
            selectedBtnNm = "2";
        } else if(v.getId() == R.id.btn_gallery3) {
            selectedBtnNm = "3";
        } else if(v.getId() == R.id.btn_gallery4) {
            selectedBtnNm = "4";
        } else if(v.getId() == R.id.btn_gallery5) {
            selectedBtnNm = "5";
        } else if(v.getId() == R.id.btn_gallery6) {
            selectedBtnNm = "6";
        } else if(v.getId() == R.id.btn_gallery7) {
            selectedBtnNm = "7";
        } else if(v.getId() == R.id.btn_gallery8) {
            selectedBtnNm = "8";
        } else if(v.getId() == R.id.btn_gallery9) {
            selectedBtnNm = "9";
        }

        //다이얼로그 창
        new AlertDialog.Builder(this)
                .setTitle("사진 선택")
                .setNegativeButton("취소", cancelListener)
                .setPositiveButton("파일 선택", albumListener)
                .show();

    }

    RadioGroup.OnCheckedChangeListener rdGrpMonthChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if(checkedId == R.id.rd_btn_month_12){ //TODO: dto set

            } else if(checkedId == R.id.rd_btn_month_24){

            }
        }
    };

    RadioGroup.OnCheckedChangeListener rdGrpPaymentMethodBtnChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if(checkedId == R.id.rd_btn_input){
                txtSelect.setVisibility(View.GONE);
                edtInput.setVisibility(View.VISIBLE);
            } else if(checkedId == R.id.rd_btn_select){
                edtInput.setVisibility(View.GONE);
                txtSelect.setText("사용자 계좌 정보 자동입력"); //TODO: 사용자 계좌 정보 자동입력
                txtSelect.setVisibility(View.VISIBLE);
            }
        }
    };

    RadioGroup.OnCheckedChangeListener rdGrpWorkStatChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if(checkedId == R.id.rd_btn_employed){
                isEmployed = true;

                layoutType4.setVisibility(View.VISIBLE);
                layoutType5.setVisibility(View.VISIBLE);
                ckBusinessmanYn.setVisibility(View.VISIBLE);
                layoutType7.setVisibility(View.GONE);
                layoutType8.setVisibility(View.GONE);

            } else if(checkedId == R.id.rd_btn_unemployed){
                isEmployed = false;

                layoutType4.setVisibility(View.GONE);
                layoutType5.setVisibility(View.GONE);
                ckBusinessmanYn.setVisibility(View.INVISIBLE);
                layoutType7.setVisibility(View.VISIBLE);
                layoutType8.setVisibility(View.VISIBLE);

            }
        }



    };

    RadioGroup.OnCheckedChangeListener rdGrpMarriedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            if(checkedId == R.id.rd_btn_married){
                isMarried = true;
                layoutType3.setVisibility(View.VISIBLE);

            } else if(checkedId == R.id.rd_btn_unmarried){
                isMarried = false;
                layoutType3.setVisibility(View.GONE);
            }

        }
    };

    RadioButton.OnClickListener rdBtnClickListener = new RadioButton.OnClickListener(){

        @Override
        public void onClick(View v) {

        }
    };

    @Override
    public void startMainActivity() {
        Intent intent = new Intent(context, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void showErrorMessage(String message) {
        ToastUtils.showToast(context, message);
    }
}
