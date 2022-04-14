package com.example.exchange;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login(View view){
        EditText email = findViewById(R.id.editTextEmail);
        EditText password = findViewById(R.id.editTextPassword);

        String emailaddress = email.getText().toString();
        String pass = password.getText().toString();

        Log.i("MSG", "Bejelentkezett: " +  emailaddress + " " + pass );
    }

    public void openRegister(View view){
        Intent intent = new Intent(this, RegistActivity.class);

        startActivity(intent);
    }
}