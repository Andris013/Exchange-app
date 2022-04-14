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
    EditText editTextTextUserName;
    EditText editTextTextEmailAddressReg;
    EditText editTextTextPasswordReg;
    EditText editTextTextPasswordAgain;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        editTextTextUserName = findViewById(R.id.editTextTextUserName);
        editTextTextEmailAddressReg = findViewById(R.id.editTextTextEmailAddressReg);
        editTextTextPasswordReg = findViewById(R.id.editTextTextPasswordReg);
        editTextTextPasswordAgain = findViewById(R.id.editTextTextPasswordAgain);

        mAuth = FirebaseAuth.getInstance();
    }



    public void cancel(View view) {
        finish();
    }

    public void registration(View view) {
        String userName = editTextTextUserName.getText().toString();
        String email = editTextTextEmailAddressReg.getText().toString();
        String password = editTextTextPasswordReg.getText().toString();
        String passwordAgain = editTextTextPasswordAgain.getText().toString();

        if (!password.equals(passwordAgain)) {
            Log.e("MSG", "Nem egyenlő a jelszó és a megerősítése.");
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Log.d("MSG", "User created successfully");
                    startCurrencies();
                } else {
                    Log.d("MSG", "User was't created successfully:", task.getException());
                    Toast.makeText(RegistActivity.this, "User was't created successfully:", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    private void startCurrencies() {
        Intent intent = new Intent(this, CurrenciesActivity.class);
        startActivity(intent);
    }
}