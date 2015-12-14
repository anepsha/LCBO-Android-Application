package com.prog3210.ngalatsis.lcboapp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * Created by ttran2-cc on 12/8/2015.
 */
public class ItemsAdapter extends ArrayAdapter<Item>{
    Context context;
    int layoutResId;
    ArrayList<Item> itemsList = new ArrayList<Item>();
    Item data[] = null;

    public ItemsAdapter(Context context, int layoutResId, Item[] data) {
        super(context, layoutResId, data);
        this.layoutResId = layoutResId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemHolder holder = null;

        if(convertView == null)
        {
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            convertView = inflater.inflate(layoutResId, parent, false);

            holder = new ItemHolder();
            holder.itemImage = (ImageView)convertView.findViewById(R.id.imgBeer);
            holder.itemName = (TextView)convertView.findViewById(R.id.txtBeerTitle);
            holder.itemPackaging = (TextView)convertView.findViewById(R.id.txtBeerPackage);
            holder.itemInventory = (TextView)convertView.findViewById(R.id.txtInventoryCount);
            holder.itemPrice = (TextView)convertView.findViewById(R.id.txtBeerPrice);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ItemHolder)convertView.getTag();
        }

        Item history = data[position];
        holder.itemName.setText(history.getName());
        holder.itemPackaging.setText(history.getPackage_type());
        holder.itemInventory.setText(Integer.toString(history.getInventory_count()) );
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        holder.itemPrice.setText(formatter.format(history.getPrice() / 100.0));
        //http://stackoverflow.com/questions/23120238/using-picasso-library-with-listview
        //http://stackoverflow.com/questions/16608135/android-studio-add-jar-as-library
        Picasso.with(this.context).load(history.getImage_thumb_url()).into(holder.itemImage);

        return convertView;
    }

    static class ItemHolder
    {
        ImageView itemImage;
        TextView itemName;
        TextView itemPackaging;
        TextView itemInventory;
        TextView itemPrice;
    }
}
