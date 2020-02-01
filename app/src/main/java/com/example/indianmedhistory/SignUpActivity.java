package com.example.indianmedhistory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.wang.avi.AVLoadingIndicatorView;

public class SignUpActivity extends AppCompatActivity {
    TextView emailTextView, passwordTextView, confirmPasswordTextview;
    Button signUpBtn;
    FirebaseAuth firebaseAuth;
    AVLoadingIndicatorView avi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        emailTextView = findViewById(R.id.email);
        passwordTextView = findViewById(R.id.password);
        confirmPasswordTextview = findViewById(R.id.confirmPassword);
        signUpBtn = findViewById(R.id.signUpBtn);
        avi=findViewById(R.id.progressBar);


        firebaseAuth = FirebaseAuth.getInstance();


        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performSignUp();
            }
        });


    }
    private void goToLoginActivity() {
        onBackPressed();
    }

    private void performSignUp() {
        String email = emailTextView.getText().toString().trim();
        String password = passwordTextView.getText().toString().trim();
        String confirm_password = confirmPasswordTextview.getText().toString().trim();
        if (email.equals("") || password.equals("") || confirm_password.equals("")) {
            Toast.makeText(this, "Empty fields are not allowed", Toast.LENGTH_SHORT).show();
            return;
        } else if (!password.equals(confirm_password)) {
            Toast.makeText(this, "Please check your password", Toast.LENGTH_SHORT).show();
            return;
        }
        avi.setVisibility(View.VISIBLE);

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(SignUpActivity.this, "Successfully SignUp", Toast.LENGTH_SHORT).show();
                    sendVerifiactionEmail();

                    firebaseAuth.signOut();
                    goToLoginActivity();
                    avi.setVisibility(View.INVISIBLE);

                }
                else{
                    avi.setVisibility(View.INVISIBLE);

                    Toast.makeText(SignUpActivity.this, task.getException().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void sendVerifiactionEmail() {
        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null) {
            firebaseUser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(SignUpActivity.this, "Verifiaction Email was send!", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(SignUpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
