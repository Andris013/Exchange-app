package com.example.exchange;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.ktx.Firebase;

import java.util.ArrayList;

public class CurrenciesActivity extends AppCompatActivity {
    private static final String TAG = CurrenciesActivity.class.getName();
    private FirebaseUser user;

    private RecyclerView mRecyclerView;
    private ArrayList<MyCurrency> itemList;
    private MyCurrencyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currencies);

        user = FirebaseAuth.getInstance().getCurrentUser();

        if (user != null) {
            Log.d(TAG, "Auth user");
        } else {
            Log.d(TAG, "Not Auth user");
        }
    }
}