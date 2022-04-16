package com.example.exchange;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.ktx.Firebase;

import java.util.ArrayList;
import java.util.Collection;

public class CurrenciesActivity extends AppCompatActivity {
    private static final String TAG = CurrenciesActivity.class.getName();
    private FirebaseUser user;

    private RecyclerView mRecyclerView;
    private ArrayList<MyCurrency> mitemList;
    private MyCurrencyAdapter mAdapter;
    private int gridNumber = 1;

    private boolean viewRow = true;

    private FirebaseFirestore mFirestore;
    private CollectionReference mItems;

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


        mRecyclerView = findViewById(R.id.recycleview);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, gridNumber));
        mitemList = new ArrayList<>();

        mAdapter = new MyCurrencyAdapter(this, mitemList);

        mRecyclerView.setAdapter(mAdapter);


        mFirestore = FirebaseFirestore.getInstance();
        mItems = mFirestore.collection("Items");

        queryData();

    }

    private void queryData() {
        mitemList.clear();
        mItems.orderBy("name").get().addOnSuccessListener(queryDocumentSnapshots -> {
            for (QueryDocumentSnapshot doc : queryDocumentSnapshots) {
                MyCurrency currency = doc.toObject(MyCurrency.class);
                mitemList.add(currency);
            }

            if (mitemList.size() == 0) {
                initializeData();
                queryData();
            }

            mAdapter.notifyDataSetChanged();
        });
    }


    @SuppressLint("NotifyDataSetChanged")
    private void initializeData() {
        String[] itemsList = getResources().getStringArray(R.array.currency_name_list);
        String[] itemsRate = getResources().getStringArray(R.array.currency_rate_list);

        for (int i = 0; i < itemsList.length; i++) {
            mItems.add(new MyCurrency(itemsList[i], Float.parseFloat(itemsRate[i])));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.currency_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.search_bar);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                mAdapter.getFilter().filter(s);
                return false;
            }
        });
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logoutButton:
                FirebaseAuth.getInstance().signOut();
                finish();
                return true;
            case R.id.view_selector:
                if (viewRow) {
                    changeSpanCount(item, R.drawable.ic_baseline_view_grid, 1);
                } else {
                    changeSpanCount(item, R.drawable.ic_baseline_view_row, 2);
                }
                return true;
            case R.id.database_manipulate:
                startDataMGMT();
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void changeSpanCount(MenuItem item, int drawableId, int spanCount) {
        viewRow = !viewRow;
        item.setIcon(drawableId);
        GridLayoutManager layoutManager = (GridLayoutManager) mRecyclerView.getLayoutManager();
        layoutManager.setSpanCount(spanCount);
    }

    private void startDataMGMT(){
        Intent intent = new Intent(this, DataMGMTActivity.class);
        startActivity(intent);
    }

}