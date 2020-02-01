package com.example.indianmedhistory.ScannerOptions;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.indianmedhistory.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class GetPatientActivity extends AppCompatActivity {
DatabaseReference userRef;
String id="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_patient);
        id=getIntent().getStringExtra("data");
        userRef= FirebaseDatabase.getInstance().getReference().child("value");
        userRef.setValue(id).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(GetPatientActivity.this, "Scanned Succesfully", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(GetPatientActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
