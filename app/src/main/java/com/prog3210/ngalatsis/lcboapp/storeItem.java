package com.prog3210.ngalatsis.lcboapp;

/**
 * Created by NGalatsis-cc on 11/27/2015.
 */
public class storeItem {
    private String city = null;
    private String storeHours = null;
    private String address = null;

    public storeItem(){

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStoreHours() {
        return storeHours;
    }

    public void setstoreHours(String storeHours){
        this.storeHours = storeHours;
    }
}
