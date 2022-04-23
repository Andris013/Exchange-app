package com.example.exchange;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyCurrencyAdapter extends RecyclerView.Adapter<MyCurrencyAdapter.ViewHolder> implements Filterable {
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

        //2 animáció az elemek megjelenítésére: jobbról, és balról beúszás felváltva
        if(holder.getAdapterPosition() > lastPosition){
            if(holder.getPosition() % 2 == 0) {
                Animation anim = AnimationUtils.loadAnimation(mcontext, R.anim.in_row);
                holder.itemView.startAnimation(anim);
                lastPosition = holder.getAdapterPosition();
            }else{
                Animation anim = AnimationUtils.loadAnimation(mcontext, R.anim.in_row_2);
                holder.itemView.startAnimation(anim);
                lastPosition = holder.getAdapterPosition();
            }

        }
    }

    @Override
    public int getItemCount() {
        return mCurrencyItemsData.size();
    }

    @Override
    public Filter getFilter() {
        return currencyFilter;
    }

    //Kereséshez szűrés karaktersorozatra
    private Filter currencyFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<MyCurrency> filteredList = new ArrayList<>();
            FilterResults results = new FilterResults();

            if(charSequence == null || charSequence.length() == 0){
                results.count = mCurrencyItemsDataAll.size();
                results.values = mCurrencyItemsDataAll;
            }else{
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for (MyCurrency curr: mCurrencyItemsDataAll){
                    if(curr.getName().toLowerCase().contains(filterPattern)){
                        filteredList.add(curr);
                    }
                }
                results.count = filteredList.size();
                results.values = filteredList;
            }


            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            mCurrencyItemsData = (ArrayList) filterResults.values;
            notifyDataSetChanged();

        }
    };

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView mNameText;
        private TextView mRateText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mNameText = itemView.findViewById(R.id.currencyName);
            mRateText = itemView.findViewById(R.id.currencyRate);
        }

        public void bindTo(MyCurrency currentItem) {
            String rate = Float.toString(currentItem.getRate());
            mNameText.setText(currentItem.getName());
            mRateText.setText(rate);
        }
    }
}

