package com.example.exchange;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.ktx.Firebase;

public class AlarmReceiver extends BroadcastReceiver {

    private FirebaseFirestore mFirestore;
    private CollectionReference mItems;

    @Override
    public void onReceive(Context context, Intent intent) {
        mFirestore = FirebaseFirestore.getInstance();
        mItems = mFirestore.collection("Items");

        mItems.whereEqualTo("name","Euro").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshot) {
                float rate = queryDocumentSnapshot.toObjects(MyCurrency.class).get(0).getRate();
                new NotificationHandler(context).send("Az Euro áfrolyama: " + rate);
            }
        });
    }
}