package com.prog3210.ngalatsis.lcboapp;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, StoreList.OnFragmentInteractionListener, FragmentCategories.OnFragmentInteractionListener, LocationListener, FragmentBeer.OnFragmentInteractionListener, FragmentItemDetails.OnFragmentInteractionListener{
    private Button btnGo;
    private Button btnLater;
    private LocationManager locationManager;
    private Location location;
    private ArrayList<Item> checklistItems = new ArrayList<Item>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGo = (Button)findViewById(R.id.btnGo);
        btnGo.setOnClickListener(this);
        btnLater = (Button)findViewById(R.id.btnLater);
        btnLater.setOnClickListener(this);

        locationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        //locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

        switch (v.getId()){
            case R.id.btnGo:
                break;
            case R.id.btnLater:
                Bundle bundle = new Bundle();
                bundle.putDouble("latitude", location.getLatitude());
                bundle.putDouble("longitude", location.getLongitude());
                StoreList storeList = new StoreList();
                storeList.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(android.R.id.content, storeList).addToBackStack(null).commit();
        }
    }

    @Override
    public void onBackPressed() {
        getFragmentManager().popBackStack();
    }

    @Override
    public void onStoreClicked(Store storeClicked) {

        Bundle bundle = new Bundle();
        bundle.putInt("storeId", storeClicked.getId());
        bundle.putString("storeName", storeClicked.getName());

        FragmentCategories fragmentCategories = new FragmentCategories();
        fragmentCategories.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(android.R.id.content, fragmentCategories).addToBackStack(null).commit();
    }

    @Override
    public void onCategoryClicked(String category, int storeId, String storeName) {
        Bundle bundle = new Bundle();
        bundle.putString("category", category);
        bundle.putInt("storeId", storeId);
        bundle.putString("storeName", storeName);

        FragmentBeer fragmentBeer = new FragmentBeer();
        fragmentBeer.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(android.R.id.content, fragmentBeer).addToBackStack(null).commit();
    }

    @Override
    public void onLocationChanged(Location location) {
        this.location = location;
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onItemClicked(Item item) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("item", item);
        /*
        bundle.putInt("itemId", item.getId());
        bundle.putString("itemName", item.getName());
        bundle.putString("itemImageUrl", item.getImage_url());
        bundle.putDouble("itemPrice", item.getPrice());
        bundle.putString("itemServingDescription", item.getServing_description());
        */
        FragmentItemDetails fragmentItemDetails = new FragmentItemDetails();
        fragmentItemDetails.setArguments(bundle);

        getFragmentManager().beginTransaction().replace(android.R.id.content, fragmentItemDetails).addToBackStack(null).commit();
    }

    @Override
    public void onAddToCart(Item item) {
        checklistItems.add(item);
        Log.d("Added item", item.getName() + " to checklist!");

        Bundle bundle = new Bundle();
        //http://stackoverflow.com/questions/23103028/pass-arraylist-from-fragment-to-another-fragmentextends-listfragment-using-bun
        bundle.putParcelableArrayList("checklistItems", checklistItems);
        FragmentChecklist fragmentChecklist = new FragmentChecklist();
        fragmentChecklist.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(android.R.id.content, fragmentChecklist).addToBackStack(null).commit();

    }
}
