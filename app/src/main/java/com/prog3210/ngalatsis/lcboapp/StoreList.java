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
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by NGalatsis-cc on 11/20/2015.
 */
public class StoreList extends Fragment implements AdapterView.OnItemClickListener {
    private static final String LCBO_API_KEY = "MDpjYjQ5YWQxOC05ODg3LTExZTUtYjkxZi0yMzdlZTk4YzU3ZGE6Y3FlWWVBS0U4Unc4aGM4NWJUMkh2YlNxYUhYT1ZIVzN6M2FR";
    private static final String TAG_RESULT = "result";
    private static final String TAG_ID = "id";
    private static final String TAG_IS_DEAD = "is_dead";
    private static final String TAG_NAME = "name";
    private static final String TAG_TAGS = "tags";
    private static final String TAG_ADDRESS_LINE_1 = "address_line_1";
    private static final String TAG_ADDRESS_LINE_2 = "address_line_2";
    private static final String TAG_CITY = "city";
    private static final String TAG_POSTAL_CODE = "postal_code";
    private static final String TAG_TELEPHONE = "telephone";
    private static final String TAG_FAX = "fax";
    private static final String TAG_LATITUDE = "latitude";
    private static final String TAG_LONGITUDE = "longitude";
    private static final String TAG_PRODUCTS_COUNT = "products_count";
    private static final String TAG_INVENTORY_COUNT = "inventory_count";
    private static final String TAG_INVENTORY_PRICE_IN_CENTS = "inventory_price_in_cents";
    private static final String TAG_INVENTORY_VOLUME_IN_MILLILITERS = "inventory_volume_in_milliliters";
    private static final String TAG_HAS_WHEELCHAIR_ACCESSABILITY = "has_wheelchair_accessability";
    private static final String TAG_HAS_BILINGUAL_SERVICES = "has_bilingual_services";
    private static final String TAG_HAS_PRODUCT_CONSULTANT = "has_product_consultant";
    private static final String TAG_HAS_TASTING_BAR = "has_tasting_bar";
    private static final String TAG_HAS_BEER_COLD_ROOM = "has_beer_cold_room";
    private static final String TAG_HAS_SPECIAL_OCCASION_PERMITS = "has_special_occasion_permits";
    private static final String TAG_HAS_VINTAGES_CORNER = "has_vintages_corner";
    private static final String TAG_HAS_PARKING = "has_parking";
    private static final String TAG_HAS_TRANSIT_ACCESS = "has_transit_access";
    private static final String TAG_SUNDAY_OPEN = "sunday_open";
    private static final String TAG_SUNDAY_CLOSE = "sunday_close";
    private static final String TAG_MONDAY_OPEN = "monday_open";
    private static final String TAG_MONDAY_CLOSE = "monday_close";
    private static final String TAG_TUESDAY_OPEN = "tuesday_open";
    private static final String TAG_TUESDAY_CLOSE = "tuesday_close";
    private static final String TAG_WEDNESDAY_OPEN = "wednesday_open";
    private static final String TAG_WEDNESDAY_CLOSE = "wednesday_close";
    private static final String TAG_THURSDAY_OPEN = "thursday_open";
    private static final String TAG_THURSDAY_CLOSE = "thursday_close";
    private static final String TAG_FRIDAY_OPEN = "friday_open";
    private static final String TAG_FRIDAY_CLOSE = "friday_close";
    private static final String TAG_SATURDAY_OPEN = "saturday_open";
    private static final String TAG_SATURDAY_CLOSE = "saturday_close";
    private static final String TAG_UPDATED_AT = "updated_at";
    private static final String TAG_DISTANCE_IN_METERS = "distance_in_meters";
    private static final String TAG_STORE_NO = "store_no";

    private ListView listStores;
    private ArrayList<HashMap<String, String>> data;
    private List<Store> stores;
    private OnFragmentInteractionListener mCallback;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_items, container, false);
        getActivity().setTitle("Select Store");

        listStores = (ListView) view.findViewById(R.id.itemsListView);
        listStores.setOnItemClickListener(this);

        double lat = getArguments().getDouble("latitude");
        double lng = getArguments().getDouble("longitude");

        String url = "https://lcboapi.com/stores?lat=" + lat + "&lon=" + lng + "&access_key=" + LCBO_API_KEY;
        Log.d("api string", url);
        new GetStoresTask().execute(url);

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
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mCallback.onStoreClicked(stores.get(position));
    }

    public interface OnFragmentInteractionListener{
        public void onStoreClicked(Store storeClicked);
    }

    class GetStoresTask extends AsyncTask<String, Void, JSONArray> {
        //http://stackoverflow.com/questions/11752961/how-to-show-a-progress-spinner-in-android-when-doinbackground-is-being-execut
        private ProgressDialog dialog = new ProgressDialog(StoreList.this.getActivity());

        @Override
        protected void onPreExecute() {
            this.dialog.setMessage("Please wait");
            this.dialog.show();

        }

        @Override
        protected JSONArray doInBackground(String... params) {
            // Creating JSON Parser instance
            JSONParser jParser = new JSONParser();

            // getting JSON string from URL
            // http://jsonviewer.stack.hu/
            JSONObject json = jParser.getJSONFromUrl(params[0]);

            JSONArray result = null;

            try {
                result = json.getJSONArray(TAG_RESULT);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return result;
        }

        protected void onPostExecute(JSONArray feed) {
            if (dialog.isShowing()) {
                dialog.dismiss();
            }

            stores = new ArrayList<Store>();

            try {
                for(int i = 0; i < feed.length(); i++) {
                    JSONObject storeObject = feed.getJSONObject(i);
                    int store_id = storeObject.optInt(TAG_ID);
                    int store_no = storeObject.optInt(TAG_STORE_NO);

                    // don't add store to list of stores to display if its store ID or number is null (0)
                    if (store_id != 0 && store_no != 0) {
                        // handle null values for store object properties

                        int sunday_open = storeObject.optInt(TAG_SUNDAY_OPEN, 0);
                        int sunday_close = storeObject.optInt(TAG_SUNDAY_CLOSE, 0);
                        Store store = new Store(
                                store_id,
                                storeObject.optBoolean(TAG_IS_DEAD),
                                storeObject.optString(TAG_NAME, "Unnamed store"),
                                storeObject.optString(TAG_TAGS, "No tags for this store"),
                                storeObject.optString(TAG_ADDRESS_LINE_1),
                                storeObject.getString(TAG_ADDRESS_LINE_2),
                                storeObject.optString(TAG_CITY),
                                storeObject.optString(TAG_POSTAL_CODE),
                                storeObject.optString(TAG_TELEPHONE),
                                storeObject.optString(TAG_FAX),
                                storeObject.optDouble(TAG_LATITUDE),
                                storeObject.optDouble(TAG_LONGITUDE),
                                storeObject.optInt(TAG_PRODUCTS_COUNT),
                                storeObject.optInt(TAG_INVENTORY_COUNT),
                                storeObject.optInt(TAG_INVENTORY_PRICE_IN_CENTS),
                                storeObject.optInt(TAG_INVENTORY_VOLUME_IN_MILLILITERS),
                                storeObject.optBoolean(TAG_HAS_WHEELCHAIR_ACCESSABILITY),
                                storeObject.optBoolean(TAG_HAS_BILINGUAL_SERVICES),
                                storeObject.optBoolean(TAG_HAS_PRODUCT_CONSULTANT),
                                storeObject.optBoolean(TAG_HAS_TASTING_BAR),
                                storeObject.optBoolean(TAG_HAS_BEER_COLD_ROOM),
                                storeObject.optBoolean(TAG_HAS_SPECIAL_OCCASION_PERMITS),
                                storeObject.optBoolean(TAG_HAS_VINTAGES_CORNER),
                                storeObject.optBoolean(TAG_HAS_PARKING),
                                storeObject.optBoolean(TAG_HAS_TRANSIT_ACCESS),
                                sunday_open,
                                sunday_close,
                                storeObject.optInt(TAG_MONDAY_OPEN),
                                storeObject.optInt(TAG_MONDAY_CLOSE),
                                storeObject.optInt(TAG_TUESDAY_OPEN),
                                storeObject.optInt(TAG_TUESDAY_CLOSE),
                                storeObject.optInt(TAG_WEDNESDAY_OPEN),
                                storeObject.optInt(TAG_WEDNESDAY_CLOSE),
                                storeObject.optInt(TAG_THURSDAY_OPEN),
                                storeObject.optInt(TAG_THURSDAY_CLOSE),
                                storeObject.optInt(TAG_FRIDAY_OPEN),
                                storeObject.optInt(TAG_FRIDAY_CLOSE),
                                storeObject.optInt(TAG_SATURDAY_OPEN),
                                storeObject.optInt(TAG_SATURDAY_CLOSE),
                                storeObject.optString(TAG_UPDATED_AT),
                                storeObject.optInt(TAG_DISTANCE_IN_METERS),
                                store_no
                        );
                        stores.add(store);
                    }
                }

                Calendar c = Calendar.getInstance();
                int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);

                data = new ArrayList<HashMap<String, String>>();

                for (Store s : stores) {
                    HashMap<String, String> map = new HashMap<>();
                    map.put("city", s.getCity());
                    map.put("address", s.getAddress_line_1());
                    map.put("distanceAway", Integer.toString(s.getDistance_in_meters()/1000) + "km");
                    switch (dayOfWeek){
                        case 1:
                            if (s.getSunday_open() == 0){
                                map.put("storeHours", "Today's Hours: CLOSED");
                            }else{
                                map.put("storeHours", "Today's Hours: " + msmTo24time(s.getSunday_open()) + " - " + msmTo24time(s.getSunday_close()));
                            }
                            break;
                        case 2:
                            if (s.getSunday_open() == 0){
                                map.put("storeHours", "Today's Hours: CLOSED");
                            }else {
                                map.put("storeHours", "Today's Hours: " + msmTo24time(s.getMonday_open()) + " - " + msmTo24time(s.getMonday_close()));
                            }
                            break;
                        case 3:
                            if (s.getSunday_open() == 0){
                                map.put("storeHours", "Today's Hours: CLOSED");
                            }else {
                                map.put("storeHours", "Today's Hours: " + msmTo24time(s.getTuesday_open()) + " - " + msmTo24time(s.getTuesday_close()));
                            }
                            break;
                        case 4:
                            if (s.getSunday_open() == 0){
                                map.put("storeHours", "Today's Hours: CLOSED");
                            }else {
                                map.put("storeHours", "Today's Hours: " + msmTo24time(s.getWednesday_open()) + " - " + msmTo24time(s.getWednesday_close()));
                            }
                            break;
                        case 5:
                            if (s.getSunday_open() == 0){
                                map.put("storeHours", "Today's Hours: CLOSED");
                            }else {
                                map.put("storeHours", "Today's Hours: " + msmTo24time(s.getThursday_open()) + " - " + msmTo24time(s.getThursday_close()));
                            }
                            break;
                        case 6:
                            if (s.getSunday_open() == 0){
                                map.put("storeHours", "Today's Hours: CLOSED");
                            }else {
                                map.put("storeHours", "Today's Hours: " + msmTo24time(s.getFriday_open()) + " - " + msmTo24time(s.getFriday_close()));
                            }
                            break;
                        case 7:
                            if (s.getSunday_open() == 0){
                                map.put("storeHours", "Today's Hours: CLOSED");
                            } else {
                                map.put("storeHours", "Today's Hours: " + msmTo24time(s.getSaturday_open()) + " - " + msmTo24time(s.getSaturday_close()));
                            }
                            break;
                    }
                    data.add(map);
                }

                //http://stackoverflow.com/questions/16661662/how-to-implement-pagination-in-android-listview
                int resource = R.layout.listview_storelist;
                String[] from = {"city", "address", "storeHours", "distanceAway"};
                int[] to = {R.id.txtCity, R.id.txtAddress, R.id.txtStoreHours, R.id.txtStoreDistance};

                SimpleAdapter adapter = new SimpleAdapter(StoreList.this.getActivity(), data, resource, from, to);
                listStores.setAdapter(adapter);
            }catch (JSONException e) {
                e.printStackTrace();
            }
        }

        private String msmTo24time(int time) {
            int hour = (time / 60) <= 11 ? time / 60 : (time / 60) - 12;
            int mins = time % 60;
            String minsString = mins < 10 ? "0" + Integer.toString(mins) : Integer.toString(mins);
            String ampm = (time / 60) <= 11 ? "AM" : "PM";
            String result = Integer.toString(hour) + ":" + minsString + " " + ampm;

            return result;
        }
    }
}
