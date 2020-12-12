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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.nhhackaton.R;
import com.nhhackaton.data.loan.LoanApply;
import com.nhhackaton.util.LogUtils;
import com.nhhackaton.view.loan.presenter.LoanContract;
import com.nhhackaton.view.main.MainActivity;
import com.nhhackaton.view.signin.presenter.SignInContract;

import java.io.File;


public class LoanActivity extends AppCompatActivity implements View.OnClickListener, LoanContract.View {

    private Context context;

    private TextView txtFile1, txtFile2, txtSelect;
    private EditText edtInput;
    private ImageButton btnGallery1, btnGallery2;
    private Button btnLoanApply;

    private RadioGroup rdGrpMonth, rdGrpPaymentMethod;
    private RadioButton rdBtnInput, rdBtnSelect, rdBtnMonth12, rdBtnMonth24;

    private static final int PICK_FROM_CAMERA = 0;
    private static final int PICK_FROM_ALBUM = 1;

    private Uri mImageCaptureUri;
    private String selectedBtnNm;   //TODO: btn 구분값 추가: A,B..

    private LoanContract.Presenter presenter;
    private LoanApply loanApply;    //TODO: send dto to server

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan);

        init();
    }

    private void init() {

        context = getApplicationContext();

        //file layout
        txtFile1 = (TextView) findViewById(R.id.txt_file1);
        txtFile2 = (TextView) findViewById(R.id.txt_file2);
        btnGallery1 = (ImageButton) findViewById(R.id.btn_gallery1);
        btnGallery2 = (ImageButton) findViewById(R.id.btn_gallery2);

        btnGallery1.setOnClickListener(this);
        btnGallery2.setOnClickListener(this);

        //info layout
        txtSelect = (TextView) findViewById(R.id.txt_select);
        edtInput = (EditText) findViewById(R.id.edt_input);
        rdGrpMonth = (RadioGroup) findViewById(R.id.rd_grp_month);
        rdBtnMonth12 = (RadioButton) findViewById(R.id.rd_btn_month_12);
        rdBtnMonth24 = (RadioButton) findViewById(R.id.rd_btn_month_24);
        rdBtnSelect = (RadioButton) findViewById(R.id.rd_btn_select);
        rdGrpPaymentMethod = (RadioGroup) findViewById(R.id.rd_grp_payment_method);
        rdBtnInput = (RadioButton) findViewById(R.id.rd_btn_input);
        rdBtnSelect = (RadioButton) findViewById(R.id.rd_btn_select);

        rdGrpPaymentMethod.setOnCheckedChangeListener(rdGrpBtnMonthChangeListener);
        rdBtnMonth12.setOnClickListener(rdBtnClickListener);
        rdBtnMonth24.setOnClickListener(rdBtnClickListener);

        rdGrpPaymentMethod.setOnCheckedChangeListener(rdGrpPaymentMethodBtnChangeListener);
        rdBtnInput.setOnClickListener(rdBtnClickListener);
        rdBtnSelect.setOnClickListener(rdBtnClickListener);

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
        intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
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

                //TODO: btn 구분값 추가
                if("A".equals(selectedBtnNm)){
                    txtFile1.setText(file.getName());
                } else if("B".equals(selectedBtnNm)){
                    txtFile2.setText(file.getName());
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

        //TODO: btn 구분값 추가
        if(v.getId() == R.id.btn_gallery1) {
            selectedBtnNm = "A";

        } else if(v.getId() == R.id.btn_gallery2) {
            selectedBtnNm = "B";
        }

        //다이얼로그 창
        new AlertDialog.Builder(this)
                .setTitle("사진 선택")
                .setNegativeButton("취소", cancelListener)
                .setPositiveButton("파일 선택", albumListener)
                .show();

    }

    RadioGroup.OnCheckedChangeListener rdGrpBtnMonthChangeListener = new RadioGroup.OnCheckedChangeListener() {
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
                txtSelect.setHeight(0);
                edtInput.setVisibility(View.VISIBLE);
            } else if(checkedId == R.id.rd_btn_select){
                edtInput.setHeight(0);
                txtSelect.setText("사용자 계좌 정보 자동입력"); //TODO: 사용자 계좌 정보 자동입력
                txtSelect.setVisibility(View.VISIBLE);
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

    }
}
