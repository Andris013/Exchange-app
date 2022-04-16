package com.example.exchange;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class DataMGMTActivity extends AppCompatActivity {

    private FirebaseFirestore mFirestore;
    private CollectionReference mItems;
    private MyCurrencyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_mgmt);

        mFirestore = FirebaseFirestore.getInstance();
        mItems = mFirestore.collection("Items");

    }

    public void insertData(View view) {

        mAdapter.notifyDataSetChanged();
    }

    public void updateData(View view) {
        mAdapter.notifyDataSetChanged();
    }

    public void deleteData(View view) {
        mAdapter.notifyDataSetChanged();
    }
}