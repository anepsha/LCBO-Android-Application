package com.prog3210.ngalatsis.lcboapp;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ttran2-cc on 11/19/2015.
 */
public class FragmentBeer extends Fragment implements AdapterView.OnItemClickListener{
    private static final String LCBO_API_KEY = "MDpjYjQ5YWQxOC05ODg3LTExZTUtYjkxZi0yMzdlZTk4YzU3ZGE6Y3FlWWVBS0U4Unc4aGM4NWJUMkh2YlNxYUhYT1ZIVzN6M2FR";

    // JSON Node names
    private static final String TAG_RESULT = "result";
    private static final String TAG_ID = "id";
    private static final String TAG_NAME = "name";
    private static final String TAG_TAGS = "tags";
    private static final String TAG_PRICE = "price_in_cents";
    private static final String TAG_STOCK_TYPE = "stock_type";
    private static final String TAG_CATEGORY = "primary_category";
    private static final String TAG_SECONDARY_CATEGORY = "secondary_category";
    private static final String TAG_ORIGIN = "origin";
    private static final String TAG_PACKAGE_TYPE = "package";
    private static final String TAG_PACKAGE_UNIT_TYPE = "package_unit_type";
    private static final String TAG_PACKAGE_UNIT_VOLUME_IN_MILLILITERS = "package_unit_volume_in_milliliters";
    private static final String TAG_TOTAL_PACKAGE_UNITS = "total_package_units";
    private static final String TAG_VOLUME_IN_MILLILITERS = "volume_in_milliliters";
    private static final String TAG_ALCOHOL_CONTENT = "alcohol_content";
    private static final String TAG_PRICE_PER_LITER_OF_ALCOHOL_IN_CENTS = "price_per_liter_of_alcohol_in_cents";
    private static final String TAG_PRICE_PER_LITER_IN_CENTS = "price_per_liter_in_cents";
    private static final String TAG_INVENTORY_COUNT = "inventory_count";
    private static final String TAG_PRODUCER_NAME = "producer_name";
    private static final String TAG_SERVING_SUGGESTION = "serving_suggestion";
    private static final String TAG_TASTING_NOTE = "tasting_note";
    private static final String TAG_UPDATED_AT = "updated_at";
    private static final String TAG_IMAGE_URL = "image_url";
    private static final String TAG_IMAGE_THUMB_URL = "image_thumb_url";
    private static final String TAG_VARIETAL = "varietal";
    private static final String TAG_STYLE = "style";
    private static final String TAG_TERTIARY_CATEGORY = "tertiary_category";
    private static final String TAG_PRODUCT_NO = "product_no";

    private static final String TAG_QUANTITY = "quantity";
    private static final String TAG_PRODUCT_ID = "product_id";

    private ListView listItems;
    private TextView txtStoreName;
    private int storeId;
    private String storeName;
    private String category;
    private OnFragmentInteractionListener mCallback;
    private GetInventoryTask getInventory;
    private GetBeers getItems;
    public ArrayList<Item> items;
    public ArrayList<HashMap<String, String>> data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_beer, container, false);

        storeId = getArguments().getInt("storeId");
        storeName = getArguments().getString("storeName");
        category = getArguments().getString("category");
        getActivity().setTitle(category);

        listItems = (ListView)view.findViewById(R.id.listBeers);
        listItems.setOnItemClickListener(this);
        txtStoreName = (TextView)view.findViewById(R.id.txtStoreName);
        txtStoreName.setText(storeName);

        String url = "https://lcboapi.com/inventories?store_id="+storeId+"&access_key="+LCBO_API_KEY;
        Log.d("Inventory", url);
        getInventory = new GetInventoryTask();
        getItems = new GetBeers();
        getInventory.execute(url);
        //new GetInventoryTask().execute(url);

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

    //http://stackoverflow.com/questions/11359604/stop-asynctask-in-fragments-when-back-button-is-pressed
    @Override
    public void onStop(){
        super.onStop();

        if (getInventory != null && getInventory.getStatus() == AsyncTask.Status.RUNNING){
            getInventory.cancel(true);
        } else if (getItems != null && getItems.getStatus() == AsyncTask.Status.RUNNING){
            getItems.cancel(true);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mCallback.onItemClicked(items.get(position));
    }

    public interface OnFragmentInteractionListener{
        public void onItemClicked(Item item);
    }

    class GetInventoryTask extends AsyncTask<String, Void, JSONArray> {
        private Exception exception;
        private ProgressDialog dialog = new ProgressDialog(FragmentBeer.this.getActivity());

        @Override
        protected void onPreExecute() {
            this.dialog.setMessage("Please wait");
            this.dialog.show();
        }

        protected JSONArray doInBackground(String... urls) {
            // Creating JSON Parser instance
            JSONParser jParser = new JSONParser();

            // getting JSON string from URL
            JSONObject json = jParser.getJSONFromUrl(urls[0]);

            JSONArray result = null;

            try {
                result = json.getJSONArray(TAG_RESULT);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return result;
        }

        protected void onPostExecute(JSONArray feed) {
            // TODO: check this.exception
            // TODO: do something with the feed
            if (dialog.isShowing()) {
                dialog.dismiss();
            }

            getItems.execute(feed);
            //new GetBeers().execute(feed);
        }

    }

    class GetBeers extends AsyncTask<JSONArray, Void, JSONObject> {
        private ProgressDialog dialog = new ProgressDialog(FragmentBeer.this.getActivity());

        @Override
        protected void onPreExecute() {
            this.dialog.setMessage("Please wait");
            this.dialog.show();
        }
        @Override
        protected JSONObject doInBackground(JSONArray... feed) {
            JSONParser jParser = new JSONParser();
            ArrayList<String> urls = new ArrayList<String>();
            items = new ArrayList<Item>();
            data = new ArrayList<HashMap<String, String>>();
            try {
                for (int i = 0; i < feed[0].length(); i++) {

                    JSONObject inventoryItem = feed[0].getJSONObject(i);
                    String url = "https://lcboapi.com/products/" + inventoryItem.getString(TAG_PRODUCT_ID) + "?access_key=" + LCBO_API_KEY;
                    urls.add(url);
                    JSONObject json = jParser.getJSONFromUrl(url).getJSONObject(TAG_RESULT);

                    if (json.getString(TAG_CATEGORY).equalsIgnoreCase(category)) {
                        Item beer = new Item(
                                Integer.parseInt(json.optString(TAG_ID)),
                                json.optString(TAG_NAME),
                                json.optString(TAG_TAGS),
                                Double.parseDouble(json.optString(TAG_PRICE, "0")),
                                json.optString(TAG_STOCK_TYPE),
                                json.optString(TAG_CATEGORY),
                                json.optString(TAG_SECONDARY_CATEGORY),
                                json.optString(TAG_ORIGIN),
                                json.optString(TAG_PACKAGE_TYPE),
                                json.optString(TAG_PACKAGE_UNIT_TYPE),
                                Integer.parseInt(json.optString(TAG_PACKAGE_UNIT_VOLUME_IN_MILLILITERS)),
                                Integer.parseInt(json.optString(TAG_TOTAL_PACKAGE_UNITS)),
                                Integer.parseInt(json.optString(TAG_VOLUME_IN_MILLILITERS)),
                                Integer.parseInt(json.optString(TAG_ALCOHOL_CONTENT)),
                                Integer.parseInt(json.optString(TAG_PRICE_PER_LITER_OF_ALCOHOL_IN_CENTS)),
                                Integer.parseInt(json.optString(TAG_PRICE_PER_LITER_IN_CENTS)),
                                Integer.parseInt(inventoryItem.getString(TAG_QUANTITY)),
                                json.optString(TAG_PRODUCER_NAME),
                                json.optString(TAG_SERVING_SUGGESTION),
                                json.optString(TAG_TASTING_NOTE),
                                json.optString(TAG_UPDATED_AT),
                                json.optString(TAG_IMAGE_THUMB_URL),
                                json.optString(TAG_IMAGE_URL),
                                json.optString(TAG_VARIETAL),
                                json.optString(TAG_STYLE),
                                json.optString(TAG_TERTIARY_CATEGORY),
                                Integer.parseInt(json.optString(TAG_PRODUCT_NO))
                                /*
                                Integer.parseInt(json.getString(TAG_ID)),
                                json.getString(TAG_NAME),
                                json.getString(TAG_TAGS),
                                Double.parseDouble(json.getString(TAG_PRICE)),
                                json.getString(TAG_STOCK_TYPE),
                                json.getString(TAG_CATEGORY),
                                json.getString(TAG_ORIGIN),
                                json.getString(TAG_PACKAGE_TYPE),
                                json.getString(TAG_PACKAGE_UNIT_TYPE),
                                Integer.parseInt(inventoryItem.getString(TAG_QUANTITY)),
                                json.getString(TAG_PRODUCER_NAME),
                                json.getString(TAG_SERVING_SUGGESTION),
                                json.getString(TAG_TASTING_NOTE),
                                json.getString(TAG_IMAGE_URL),
                                json.getString(TAG_IMAGE_THUMB_URL
                                */
                        );
                        items.add(beer);
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        protected void onPostExecute(JSONObject feed) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }

            NumberFormat formatter = NumberFormat.getCurrencyInstance();

            for (Item beer : items) {
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("beerInventoryCount", Integer.toString(beer.getInventory_count()));
                map.put("beerTitle", beer.getName());
                map.put("beerPackage", beer.getPackage_type());
                map.put("beerImgThumbUrl", beer.getImage_thumb_url());
                map.put("beerPrice", formatter.format(beer.getPrice() / 100.0));
                data.add(map);
            }

            int resource = R.layout.listview_beer_item;
            String[] from = {"beerInventoryCount", "beerTitle", "beerPackage", "beerImgThumbUrl", "beerPrice"};
            int[] to = {R.id.txtInventoryCount, R.id.txtBeerTitle, R.id.txtBeerPackage, 0, R.id.txtBeerPrice};

            //http://stackoverflow.com/questions/5374311/convert-arrayliststring-to-string-array
            Item[] itemArray = new Item[items.size()];
            itemArray = items.toArray(itemArray);

            ItemsAdapter itemsAdapter = new ItemsAdapter(FragmentBeer.this.getActivity(), resource, itemArray);
            listItems.setAdapter(itemsAdapter);
            //SimpleAdapter adapter = new SimpleAdapter(FragmentBeer.this.getActivity(), data, resource, from, to);
            //listItems.setAdapter(adapter);
        }
    }
}
