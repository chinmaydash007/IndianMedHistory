package com.example.indianmedhistory;

import android.app.IntentService;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class MyService extends IntentService {
    DatabaseReference userRef;
    String id="sadsad";


    public MyService() {
        super("MyService");
        userRef= FirebaseDatabase.getInstance().getReference().child("value");


    }

    @Override
    protected void onHandleIntent(Intent intent) {
        id=intent.getStringExtra("value");
        userRef.setValue(id).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MyService.this, "Scanned Succesfully", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MyService.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}
