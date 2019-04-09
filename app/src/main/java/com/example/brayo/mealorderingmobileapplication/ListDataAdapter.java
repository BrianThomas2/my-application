package com.example.brayo.mealorderingmobileapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.brayo.mealorderingmobileapplication.Mdata.FoodProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Brayo on 27/04/2018.
 */

public class ListDataAdapter extends ArrayAdapter {

    List list = new ArrayList();

    public ListDataAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }
    static class LayoutHandler{
        TextView DESC,QUANTITY,TOTALPRICE;
    }

    @Override
    public void add(@Nullable Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row = convertView;
        LayoutHandler layoutHandler;
        if (row == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.cart_layout,parent,false);
            layoutHandler = new LayoutHandler();
            layoutHandler.DESC = row.findViewById(R.id.foodDescription);
            layoutHandler.QUANTITY = row.findViewById(R.id.quantity);
            layoutHandler.TOTALPRICE = row.findViewById(R.id.price);
            row.setTag(layoutHandler);
        }
        else {
            layoutHandler = (LayoutHandler) row.getTag();

        }
        FoodProvider foodProvider = (FoodProvider)this.getItem(position);
        layoutHandler.DESC.setText(foodProvider.getDesc());
        layoutHandler.QUANTITY.setText(foodProvider.getQuantity());
        layoutHandler.TOTALPRICE.setText(foodProvider.getTotalprice());


        return row;
    }
}
