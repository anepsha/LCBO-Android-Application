package com.prog3210.ngalatsis.lcboapp;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.net.URL;
import java.text.NumberFormat;

/**
 * Created by ttran2-cc on 12/4/2015.
 */
public class FragmentItemDetails extends Fragment implements View.OnClickListener {
    private TextView itemNameTextView;
    private ImageView itemImageView;
    private TextView itemPackageTextView;
    private TextView itemAlcoholTextView;
    private TextView itemOriginTextView;
    private TextView itemPriceTextView;
    private TextView itemDescriptionTextView;
    private Bitmap bitmap;
    private Button btnAddToChecklist;
    private OnFragmentInteractionListener mCallback;
    //String url="https://dx5vpyka4lqst.cloudfront.net/products/288506/images/full.jpeg";
    private int itemId;
    private String itemName;
    private String itemImageUrl;
    private String itemServingDescription;
    private double itemPrice;
    private String itemPackage;
    private String itemAlcohol;
    private String itemOrigin;
    Item item;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_details, container, false);

        item = getArguments().getParcelable("item");
        itemId = item.getId();
        itemName = item.getName();
        itemImageUrl = item.getImage_url();
        itemPrice = item.getPrice();
        itemPackage = item.getPackage_type();
        itemAlcohol = Integer.toString(item.getAlcohol_content() / 100);
        itemOrigin = item.getOrigin();
        itemServingDescription = item.getServing_description();
        /*
        itemId = getArguments().getInt("itemId");
        itemName = getArguments().getString("itemName");
        itemImageUrl = getArguments().getString("itemImageUrl");
        itemPrice = getArguments().getDouble("itemPrice");
        itemServingDescription = getArguments().getString("itemServingDescription");
        */
        itemNameTextView = (TextView)view.findViewById(R.id.itemNameTextView);
        itemNameTextView.setText(itemName);
        itemImageView = (ImageView)view.findViewById(R.id.itemImageView);
        itemPriceTextView = (TextView)view.findViewById(R.id.itemPriceTextView);
        itemDescriptionTextView = (TextView)view.findViewById(R.id.itemDescriptionTextView);
        itemDescriptionTextView.setText(itemServingDescription);
        NumberFormat formatter = NumberFormat.getCurrencyInstance();
        itemPriceTextView.setText(formatter.format(itemPrice / 100.0));
        itemPackageTextView = (TextView)view.findViewById(R.id.txtItemPackage);
        itemPackageTextView.setText(itemPackage);
        itemAlcoholTextView = (TextView)view.findViewById(R.id.txtItemAlcohol);
        itemAlcoholTextView.setText(itemAlcohol);
        itemOriginTextView  = (TextView)view.findViewById(R.id.txtItemOrigin);
        itemOriginTextView.setText(itemOrigin);
        getActivity().setTitle(itemName);
        btnAddToChecklist = (Button)view.findViewById(R.id.btnAddToChecklist);
        btnAddToChecklist.setOnClickListener(this);

        new LoadImage().execute(itemImageUrl);
        return view;
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        mCallback = (OnFragmentInteractionListener)activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View v) {
        Item item = new Item();
        item.setName(itemName);
        item.setPrice(itemPrice);
        mCallback.onAddToCart(item);
    }

    public interface OnFragmentInteractionListener{
        public void onAddToCart(Item item);
    }

    private class LoadImage extends AsyncTask<String, String, Bitmap> {
        private ProgressDialog dialog = new ProgressDialog(FragmentItemDetails.this.getActivity());

        @Override
        protected void onPreExecute() {
            this.dialog.setMessage("Please wait");
            this.dialog.show();
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            try {
                bitmap = BitmapFactory.decodeStream((InputStream) new URL(params[0]).getContent());
            }catch (Exception e){
                e.printStackTrace();
            }
            return bitmap;
        }
        protected void onPostExecute(Bitmap image){
            if (dialog.isShowing()) {
                dialog.dismiss();
            }

            if (image!=null){
                itemImageView.setImageBitmap(image);
            }else{
                Toast.makeText(FragmentItemDetails.this.getActivity(), "oops", Toast.LENGTH_LONG).show();
            }
        }
    }
}
