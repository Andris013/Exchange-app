package com.example.exchange;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistActivity extends AppCompatActivity {
    EditText editTextTextEmailAddressReg;
    EditText editTextTextPasswordReg;
    EditText editTextTextPasswordAgain;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        editTextTextEmailAddressReg = findViewById(R.id.editTextTextEmailAddressReg);
        editTextTextPasswordReg = findViewById(R.id.editTextTextPasswordReg);
        editTextTextPasswordAgain = findViewById(R.id.editTextTextPasswordAgain);

        mAuth = FirebaseAuth.getInstance();
    }



    public void cancel(View view) {
        finish();
    }

    public void registration(View view) {
        String email = editTextTextEmailAddressReg.getText().toString();
        String password = editTextTextPasswordReg.getText().toString();
        String passwordAgain = editTextTextPasswordAgain.getText().toString();

        if (!password.equals(passwordAgain)) {
            Log.e("MSG", "Nem egyenlő a jelszó és a megerősítése.");
            Toast.makeText(RegistActivity.this, "Nem egyenlő a két jelszó!", Toast.LENGTH_LONG).show();
            return;
        }
        if( email.equals("") || password.equals("") || passwordAgain.equals("")){
            Toast.makeText(RegistActivity.this, "Minden mező kitöltése kötelező", Toast.LENGTH_LONG).show();
        }
        else {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        startCurrencies();
                        finish();
                    } else {
                        Toast.makeText(RegistActivity.this, "Regisztráció sikertelen! ", Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

    }

    private void startCurrencies() {
        Intent intent = new Intent(this, CurrenciesActivity.class);
        startActivity(intent);
    }
}