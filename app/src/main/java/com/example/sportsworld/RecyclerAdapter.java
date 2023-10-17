package com.example.sportsworld;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> implements Filterable {

    private final ArrayList<String> itemName;
    private final ArrayList<String> description;
    private final ArrayList<String> price;
    private final ArrayList<String> brand;

    public RecyclerAdapter(ArrayList<String> itemName, ArrayList<String> description, ArrayList<String> price, ArrayList<String> brand) {
        this.itemName = itemName;
        this.description = description;
        this.price = price;
        this.brand = brand;
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{


        TextView itemUserName;
        TextView itemDescription;
        TextView itemStateId;
        TextView itemAddressCardView;
        public ViewHolder( View itemView) {
            super(itemView);

            try {
                itemUserName = itemView.findViewById(R.id.itemNameCardView);
                itemDescription = itemView.findViewById(R.id.descriptionCardView);
                itemStateId = itemView.findViewById(R.id.priceCardView);
                itemAddressCardView = itemView.findViewById(R.id.brandNameCardView);

            }catch (Exception e){
                Log.e("Error", e.getMessage());
            }
        }
    }
    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.recycleradapter, viewGroup, false));
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        //viewHolder.itemImage.setImageResource(images[i]);
        viewHolder.itemUserName.setText(itemName.get(i));
        viewHolder.itemDescription.setText(description.get(i));
        viewHolder.itemStateId.setText(price.get(i));
        viewHolder.itemAddressCardView.setText(brand.get(i));
    }

    @Override
    public int getItemCount() {
        return itemName.size();
    }
}
