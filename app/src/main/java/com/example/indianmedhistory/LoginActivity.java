package com.example.indianmedhistory;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    GoogleSignInOptions signInOptions;
    GoogleSignInClient signInClient;

    SignInButton google_signInButton;
    private int RC_SIGN_IN = 100;
    private static final String TAG = "MyTag";
    FirebaseAuth firebaseAuth;

    FirebaseAuth.AuthStateListener authStateListener;

    TextView emailTextView,passwordTextView;
    Button signInBtn,signUpBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }
}
