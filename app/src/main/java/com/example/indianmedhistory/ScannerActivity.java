package com.example.indianmedhistory;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.indianmedhistory.ScannerOptions.GetPatientActivity;
import com.google.firebase.database.DatabaseReference;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScannerActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    ZXingScannerView scannerView;
    int option;
    private static final String TAG = "MyTag";
    DatabaseReference userRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);
        option = getIntent().getIntExtra("data", 1);

    }

    @Override
    protected void onResume() {
        super.onResume();
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        scannerView.stopCamera();
    }

    @Override
    public void handleResult(Result rawResult) {
        String result = rawResult.getText();
        //Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        //String adhaar_id=result.substring(result.indexOf("uid=")+5,result.indexOf("uid=")+17);
        //Log.d(TAG,result.substring(result.indexOf("uid=")+5,result.indexOf("uid=")+17));


        if(option==1){
            Intent intent=new Intent(ScannerActivity.this, GetPatientActivity.class);
            intent.putExtra("data",result);

            startActivity(intent);

        }
        if(option==2){
            Intent intent=new Intent(ScannerActivity.this,GetPatientActivity.class);
            intent.putExtra("data",result);

            startActivity(intent);
        }

    }
}
