package com.example.exchange;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SharedMemory;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.ktx.Firebase;

public class MainActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 13;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();


        GoogleSignInOptions signInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, signInOptions);
    }

    @Override
    public void onActivityResult(int requestcode, int resultcode, Intent data) {
        super.onActivityResult(requestcode, resultcode, data);

        if(requestcode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                GoogleSignInAccount acc = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(acc.getIdToken());

            }catch (ApiException e){
                Log.w("MSG", "Google Bejelentkezés sikertelen");
            }
        }
    }

    //Google bejelentkezés
    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential cred = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(cred).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startCurrencies();
                    finish();
                }else{
                    Toast.makeText(MainActivity.this, "Sikertelen bejelentkezés!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    //Emailes bejelentkezés
    public void login(View view) {
        EditText email = findViewById(R.id.editTextEmail);
        EditText password = findViewById(R.id.editTextPassword);

        String emailaddress = email.getText().toString();
        String pass = password.getText().toString();

        if(emailaddress.equals("") || pass.equals("")){
            Toast.makeText(MainActivity.this, "Mezők kitöltése közelező!", Toast.LENGTH_LONG).show();
        }else {
            mAuth.signInWithEmailAndPassword(emailaddress, pass).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        startCurrencies();
                        finish();
                    } else {
                        Toast.makeText(MainActivity.this, "Sikertelen bejelentkezés!\nHelytelen email, vagy jelszó!", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    //Regisztráció megnyitása
    public void openRegister(View view) {
        Intent intent = new Intent(this, RegistActivity.class);

        startActivity(intent);
    }

    //Bejelentkezés vendégként
    public void anonLogin(View view) {
        mAuth.signInAnonymously().addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    startCurrencies();
                } else {
                    Toast.makeText(MainActivity.this, "Sikertelen bejelentkezés!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void googLogin(View view) {
        Intent sIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(sIntent, RC_SIGN_IN);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void startCurrencies() {
        Intent intent = new Intent(this, CurrenciesActivity.class);
        startActivity(intent);
    }
}