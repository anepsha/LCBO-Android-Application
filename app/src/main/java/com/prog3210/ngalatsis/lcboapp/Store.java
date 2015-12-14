package com.prog3210.ngalatsis.lcboapp;

/**
 * Created by NGalatsis-cc on 11/27/2015.
 */
public class Store {
    private int id;
    private boolean is_dead;
    private String name;
    private String tags;
    private String address_line_1;
    private String address_line_2;
    private String city;
    private String postal_code;
    private String telephone;
    private String fax;
    private double latitude;
    private double longitude;
    private int products_count;
    private int inventory_count;
    private int inventory_price_in_cents;
    private int inventory_volume_in_milliliters;
    private boolean has_wheelchair_accessability;
    private boolean has_bilingual_services;
    private boolean has_product_consultant;
    private boolean has_tasting_bar;
    private boolean has_beer_cold_room;
    private boolean has_special_occasion_permits;
    private boolean has_vintages_corner;
    private boolean has_parking;
    private boolean has_transit_access;
    private int sunday_open;
    private int sunday_close;
    private int monday_open;
    private int monday_close;
    private int tuesday_open;
    private int tuesday_close;
    private int wednesday_open;
    private int wednesday_close;
    private int thursday_open;
    private int thursday_close;
    private int friday_open;
    private int friday_close;
    private int saturday_open;
    private int saturday_close;
    private String updated_at;
    private int distance_in_meters;
    private int store_no;

    public Store(){
    }

    public Store(int id, boolean is_dead, String name, String tags, String address_line_1, String address_line_2, String city, String postal_code, String telephone, String fax, double latitude, double longitude, int products_count, int inventory_count, int inventory_price_in_cents, int inventory_volume_in_milliliters, boolean has_wheelchair_accessability, boolean has_bilingual_services, boolean has_product_consultant, boolean has_tasting_bar, boolean has_beer_cold_room, boolean has_special_occasion_permits, boolean has_vintages_corner, boolean has_parking, boolean has_transit_access, int sunday_open, int sunday_close, int monday_open, int monday_close, int tuesday_open, int tuesday_close, int wednesday_open, int wednesday_close, int thursday_open, int thursday_close, int friday_open, int friday_close, int saturday_open, int saturday_close, String updated_at, int distance_in_meters, int store_no) {
        this.id = id;
        this.is_dead = is_dead;
        this.name = name;
        this.tags = tags;
        this.address_line_1 = address_line_1;
        this.address_line_2 = address_line_2;
        this.city = city;
        this.postal_code = postal_code;
        this.telephone = telephone;
        this.fax = fax;
        this.latitude = latitude;
        this.longitude = longitude;
        this.products_count = products_count;
        this.inventory_count = inventory_count;
        this.inventory_price_in_cents = inventory_price_in_cents;
        this.inventory_volume_in_milliliters = inventory_volume_in_milliliters;
        this.has_wheelchair_accessability = has_wheelchair_accessability;
        this.has_bilingual_services = has_bilingual_services;
        this.has_product_consultant = has_product_consultant;
        this.has_tasting_bar = has_tasting_bar;
        this.has_beer_cold_room = has_beer_cold_room;
        this.has_special_occasion_permits = has_special_occasion_permits;
        this.has_vintages_corner = has_vintages_corner;
        this.has_parking = has_parking;
        this.has_transit_access = has_transit_access;
        this.sunday_open = sunday_open;
        this.sunday_close = sunday_close;
        this.monday_open = monday_open;
        this.monday_close = monday_close;
        this.tuesday_open = tuesday_open;
        this.tuesday_close = tuesday_close;
        this.wednesday_open = wednesday_open;
        this.wednesday_close = wednesday_close;
        this.thursday_open = thursday_open;
        this.thursday_close = thursday_close;
        this.friday_open = friday_open;
        this.friday_close = friday_close;
        this.saturday_open = saturday_open;
        this.saturday_close = saturday_close;
        this.updated_at = updated_at;
        this.distance_in_meters = distance_in_meters;
        this.store_no = store_no;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean is_dead() {
        return is_dead;
    }

    public void setIs_dead(boolean is_dead) {
        this.is_dead = is_dead;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getAddress_line_1() {
        return address_line_1;
    }

    public void setAddress_line_1(String address_line_1) {
        this.address_line_1 = address_line_1;
    }

    public String getAddress_line_2() {
        return address_line_2;
    }

    public void setAddress_line_2(String address_line_2) {
        this.address_line_2 = address_line_2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getProducts_count() {
        return products_count;
    }

    public void setProducts_count(int products_count) {
        this.products_count = products_count;
    }

    public int getInventory_count() {
        return inventory_count;
    }

    public void setInventory_count(int inventory_count) {
        this.inventory_count = inventory_count;
    }

    public int getInventory_price_in_cents() {
        return inventory_price_in_cents;
    }

    public void setInventory_price_in_cents(int inventory_price_in_cents) {
        this.inventory_price_in_cents = inventory_price_in_cents;
    }

    public int getInventory_volume_in_milliliters() {
        return inventory_volume_in_milliliters;
    }

    public void setInventory_volume_in_milliliters(int inventory_volume_in_milliliters) {
        this.inventory_volume_in_milliliters = inventory_volume_in_milliliters;
    }

    public boolean isHas_wheelchair_accessability() {
        return has_wheelchair_accessability;
    }

    public void setHas_wheelchair_accessability(boolean has_wheelchair_accessability) {
        this.has_wheelchair_accessability = has_wheelchair_accessability;
    }

    public boolean isHas_bilingual_services() {
        return has_bilingual_services;
    }

    public void setHas_bilingual_services(boolean has_bilingual_services) {
        this.has_bilingual_services = has_bilingual_services;
    }

    public boolean isHas_product_consultant() {
        return has_product_consultant;
    }

    public void setHas_product_consultant(boolean has_product_consultant) {
        this.has_product_consultant = has_product_consultant;
    }

    public boolean isHas_tasting_bar() {
        return has_tasting_bar;
    }

    public void setHas_tasting_bar(boolean has_tasting_bar) {
        this.has_tasting_bar = has_tasting_bar;
    }

    public boolean isHas_beer_cold_room() {
        return has_beer_cold_room;
    }

    public void setHas_beer_cold_room(boolean has_beer_cold_room) {
        this.has_beer_cold_room = has_beer_cold_room;
    }

    public boolean isHas_special_occasion_permits() {
        return has_special_occasion_permits;
    }

    public void setHas_special_occasion_permits(boolean has_special_occasion_permits) {
        this.has_special_occasion_permits = has_special_occasion_permits;
    }

    public boolean isHas_vintages_corner() {
        return has_vintages_corner;
    }

    public void setHas_vintages_corner(boolean has_vintages_corner) {
        this.has_vintages_corner = has_vintages_corner;
    }

    public boolean isHas_parking() {
        return has_parking;
    }

    public void setHas_parking(boolean has_parking) {
        this.has_parking = has_parking;
    }

    public boolean isHas_transit_access() {
        return has_transit_access;
    }

    public void setHas_transit_access(boolean has_transit_access) {
        this.has_transit_access = has_transit_access;
    }

    public int getSunday_open() {
        return sunday_open;
    }

    public void setSunday_open(int sunday_open) {
        this.sunday_open = sunday_open;
    }

    public int getSunday_close() {
        return sunday_close;
    }

    public void setSunday_close(int sunday_close) {
        this.sunday_close = sunday_close;
    }

    public int getMonday_open() {
        return monday_open;
    }

    public void setMonday_open(int monday_open) {
        this.monday_open = monday_open;
    }

    public int getMonday_close() {
        return monday_close;
    }

    public void setMonday_close(int monday_close) {
        this.monday_close = monday_close;
    }

    public int getTuesday_open() {
        return tuesday_open;
    }

    public void setTuesday_open(int tuesday_open) {
        this.tuesday_open = tuesday_open;
    }

    public int getTuesday_close() {
        return tuesday_close;
    }

    public void setTuesday_close(int tuesday_close) {
        this.tuesday_close = tuesday_close;
    }

    public int getWednesday_open() {
        return wednesday_open;
    }

    public void setWednesday_open(int wednesday_open) {
        this.wednesday_open = wednesday_open;
    }

    public int getWednesday_close() {
        return wednesday_close;
    }

    public void setWednesday_close(int wednesday_close) {
        this.wednesday_close = wednesday_close;
    }

    public int getThursday_open() {
        return thursday_open;
    }

    public void setThursday_open(int thursday_open) {
        this.thursday_open = thursday_open;
    }

    public int getThursday_close() {
        return thursday_close;
    }

    public void setThursday_close(int thursday_close) {
        this.thursday_close = thursday_close;
    }

    public int getFriday_open() {
        return friday_open;
    }

    public void setFriday_open(int friday_open) {
        this.friday_open = friday_open;
    }

    public int getFriday_close() {
        return friday_close;
    }

    public void setFriday_close(int friday_close) {
        this.friday_close = friday_close;
    }

    public int getSaturday_open() {
        return saturday_open;
    }

    public void setSaturday_open(int saturday_open) {
        this.saturday_open = saturday_open;
    }

    public int getSaturday_close() {
        return saturday_close;
    }

    public void setSaturday_close(int saturday_close) {
        this.saturday_close = saturday_close;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public int getDistance_in_meters() {
        return distance_in_meters;
    }

    public void setDistance_in_meters(int distance_in_meters) {
        this.distance_in_meters = distance_in_meters;
    }

    public int getStore_no() {
        return store_no;
    }

    public void setStore_no(int store_no) {
        this.store_no = store_no;
    }
}
