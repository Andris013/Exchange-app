package com.example.exchange;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyCurrencyAdapter extends RecyclerView.Adapter<MyCurrencyAdapter.ViewHolder> {
    private ArrayList<MyCurrency> mCurrencyItemsData;
    private ArrayList<MyCurrency> mCurrencyItemsDataAll;
    private Context mcontext;
    private int lastPosition = -1;

    MyCurrencyAdapter(Context context, ArrayList<MyCurrency> itemsdata){
        this.mCurrencyItemsData = itemsdata;
        this.mCurrencyItemsDataAll = itemsdata;
        this.mcontext = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mcontext).inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyCurrencyAdapter.ViewHolder holder, int position) {
        MyCurrency currentItem = mCurrencyItemsData.get(position);
        
        holder.bindTo(currentItem);
    }

    @Override
    public int getItemCount() {
        return mCurrencyItemsData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView mNameText;
        private TextView mRateText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bindTo(MyCurrency currentItem) {
        }
    }
}

