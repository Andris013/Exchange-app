package com.example.exchange;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.Locale;

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

    //Valuta beszúrása
    //Note: kötelező a valuta neve, és az árfolyama is
    public void insertData(View view) {
        EditText currName = findViewById(R.id.editTextCurrencyNameDB);
        EditText currRate = findViewById(R.id.editTextCurrencyRateDB);

        if(currName.getText().toString().equals("") || currRate.getText().toString().equals("")){
            Toast.makeText(DataMGMTActivity.this, "Mindkét mező kitöltése közelező!", Toast.LENGTH_LONG).show();
        }else {
            String name = currName.getText().toString();
            float rate = Float.parseFloat(currRate.getText().toString());

            mItems.add(new MyCurrency(name, rate)).addOnSuccessListener(success ->{
                Toast.makeText(this, "Beszúrás sikeres!", Toast.LENGTH_LONG).show();
            }).addOnFailureListener(failure -> {
                Toast.makeText(this, "Beszúrás sikertelen!", Toast.LENGTH_LONG).show();
            });
        }
    }

    //Valuta frissítése
    //Note: kötelező a valuta neve, és az árfolyam is
    public void updateData(View view) {
        EditText currName = findViewById(R.id.editTextCurrencyNameDB);
        EditText currRate = findViewById(R.id.editTextCurrencyRateDB);

        if(currName.getText().toString().equals("") || currRate.getText().toString().equals("")){
            Toast.makeText(DataMGMTActivity.this, "Mindkét mező kitöltése közelező!", Toast.LENGTH_LONG).show();
        }else {
            String name = currName.getText().toString();
            float rate = Float.parseFloat(currRate.getText().toString());

            mItems.orderBy("name", Query.Direction.ASCENDING).get().addOnSuccessListener(queryDocumentSnapshots -> {
                for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                    if(doc.toObject(MyCurrency.class).getName().toLowerCase().equals(name.toLowerCase())){
                        mItems.document(doc.getId()).set(new MyCurrency(doc.toObject(MyCurrency.class).getName(),rate)).addOnSuccessListener(success ->{
                            Toast.makeText(this, "Frissítés sikeres!", Toast.LENGTH_LONG).show();
                        }).addOnFailureListener(failure -> {
                            Toast.makeText(this, "Frissítés sikertelen!", Toast.LENGTH_LONG).show();
                        });

                    }
                }
            });
        }
    }

    //Valuta törlése
    //Note: csak a valuta neve kötelező
    public void deleteData(View view) {
        EditText currName = findViewById(R.id.editTextCurrencyNameDB);
        String name = currName.getText().toString();

        mItems.orderBy("name", Query.Direction.ASCENDING).get().addOnSuccessListener(queryDocumentSnapshots -> {
            for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                if(doc.toObject(MyCurrency.class).getName().toLowerCase().equals(name.toLowerCase())){
                    mItems.document(doc.getId()).delete().addOnSuccessListener(success ->{
                        Toast.makeText(this, "Törlés sikeres!", Toast.LENGTH_LONG).show();
                    }).addOnFailureListener(failure -> {
                        Toast.makeText(this, "Törlés sikertelen!", Toast.LENGTH_LONG).show();
                    });

                }
            }
        });

    }
}