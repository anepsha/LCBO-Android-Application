package com.prog3210.ngalatsis.lcboapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by ttran2-cc on 11/20/2015.
 */
public class Item implements Parcelable {
    private int id;
    private String name;
    private String tags;
    private double price;
    private String stock_type;
    private String category;
    private String secondary_category;
    private String origin;
    private String package_type;
    private String package_unit_type;
    private int package_unit_volume_in_milliliters;
    private int total_package_units;
    private int volume_in_milliliters;
    private int alcohol_content;
    private int price_per_liter_of_alcohol_in_cents;
    private int price_per_liter_in_cents;
    private int inventory_count;
    private String producer_name;
    private String serving_description;
    private String tasting_note;
    private String updated_at;
    private String image_thumb_url;
    private String image_url;
    private String varietal;
    private String style;
    private String tertiary_category;
    private int product_no;

    public Item(){

    }

    public Item(int id, String name, String tags, double price, String stock_type, String category, String secondary_category, String origin, String package_type, String package_unit_type, int package_unit_volume_in_milliliters, int total_package_units, int volume_in_milliliters, int alcohol_content, int price_per_liter_of_alcohol_in_cents, int price_per_liter_in_cents, int inventory_count, String producer_name, String serving_description, String tasting_note, String updated_at, String image_thumb_url, String image_url, String varietal, String style, String tertiary_category, int product_no) {
        this.id = id;
        this.name = name;
        this.tags = tags;
        this.price = price;
        this.stock_type = stock_type;
        this.category = category;
        this.secondary_category = secondary_category;
        this.origin = origin;
        this.package_type = package_type;
        this.package_unit_type = package_unit_type;
        this.package_unit_volume_in_milliliters = package_unit_volume_in_milliliters;
        this.total_package_units = total_package_units;
        this.volume_in_milliliters = volume_in_milliliters;
        this.alcohol_content = alcohol_content;
        this.price_per_liter_of_alcohol_in_cents = price_per_liter_of_alcohol_in_cents;
        this.price_per_liter_in_cents = price_per_liter_in_cents;
        this.inventory_count = inventory_count;
        this.producer_name = producer_name;
        this.serving_description = serving_description;
        this.tasting_note = tasting_note;
        this.updated_at = updated_at;
        this.image_thumb_url = image_thumb_url;
        this.image_url = image_url;
        this.varietal = varietal;
        this.style = style;
        this.tertiary_category = tertiary_category;
        this.product_no = product_no;
    }

    public Item(Parcel p){
        id = p.readInt();
        name = p.readString();
        tags = p.readString();
        price = p.readDouble();
        stock_type = p.readString();
        category = p.readString();
        secondary_category = p.readString();
        origin = p.readString();
        package_type = p.readString();
        package_unit_type = p.readString();
        package_unit_volume_in_milliliters = p.readInt();
        total_package_units = p.readInt();
        volume_in_milliliters = p.readInt();
        alcohol_content = p.readInt();
        price_per_liter_of_alcohol_in_cents = p.readInt();
        price_per_liter_in_cents = p.readInt();
        inventory_count = p.readInt();
        producer_name = p.readString();
        serving_description = p.readString();
        tasting_note = p.readString();
        updated_at = p.readString();
        image_thumb_url = p.readString();
        image_url = p.readString();
        varietal = p.readString();
        style = p.readString();
        tertiary_category = p.readString();
        product_no = p.readInt();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStock_type() {
        return stock_type;
    }

    public void setStock_type(String stock_type) {
        this.stock_type = stock_type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSecondary_category() {
        return secondary_category;
    }

    public void setSecondary_category(String secondary_category) {
        this.secondary_category = secondary_category;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getPackage_type() {
        return package_type;
    }

    public void setPackage_type(String package_type) {
        this.package_type = package_type;
    }

    public String getPackage_unit_type() {
        return package_unit_type;
    }

    public void setPackage_unit_type(String package_unit_type) {
        this.package_unit_type = package_unit_type;
    }

    public int getPackage_unit_volume_in_milliliters() {
        return package_unit_volume_in_milliliters;
    }

    public void setPackage_unit_volume_in_milliliters(int package_unit_volume_in_milliliters) {
        this.package_unit_volume_in_milliliters = package_unit_volume_in_milliliters;
    }

    public int getTotal_package_units() {
        return total_package_units;
    }

    public void setTotal_package_units(int total_package_units) {
        this.total_package_units = total_package_units;
    }

    public int getVolume_in_milliliters() {
        return volume_in_milliliters;
    }

    public void setVolume_in_milliliters(int volume_in_milliliters) {
        this.volume_in_milliliters = volume_in_milliliters;
    }

    public int getAlcohol_content() {
        return alcohol_content;
    }

    public void setAlcohol_content(int alcohol_content) {
        this.alcohol_content = alcohol_content;
    }

    public int getPrice_per_liter_of_alcohol_in_cents() {
        return price_per_liter_of_alcohol_in_cents;
    }

    public void setPrice_per_liter_of_alcohol_in_cents(int price_per_liter_of_alcohol_in_cents) {
        this.price_per_liter_of_alcohol_in_cents = price_per_liter_of_alcohol_in_cents;
    }

    public int getPrice_per_liter_in_cents() {
        return price_per_liter_in_cents;
    }

    public void setPrice_per_liter_in_cents(int price_per_liter_in_cents) {
        this.price_per_liter_in_cents = price_per_liter_in_cents;
    }

    public int getInventory_count() {
        return inventory_count;
    }

    public void setInventory_count(int inventory_count) {
        this.inventory_count = inventory_count;
    }

    public String getProducer_name() {
        return producer_name;
    }

    public void setProducer_name(String producer_name) {
        this.producer_name = producer_name;
    }

    public String getServing_description() {
        return serving_description;
    }

    public void setServing_description(String serving_description) {
        this.serving_description = serving_description;
    }

    public String getTasting_note() {
        return tasting_note;
    }

    public void setTasting_note(String tasting_note) {
        this.tasting_note = tasting_note;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getImage_thumb_url() {
        return image_thumb_url;
    }

    public void setImage_thumb_url(String image_thumb_url) {
        this.image_thumb_url = image_thumb_url;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getVarietal() {
        return varietal;
    }

    public void setVarietal(String varietal) {
        this.varietal = varietal;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getTertiary_category() {
        return tertiary_category;
    }

    public void setTertiary_category(String tertiary_category) {
        this.tertiary_category = tertiary_category;
    }

    public int getProduct_no() {
        return product_no;
    }

    public void setProduct_no(int product_no) {
        this.product_no = product_no;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(tags);
        dest.writeDouble(price);
        dest.writeString(stock_type);
        dest.writeString(category);
        dest.writeString(secondary_category);
        dest.writeString(origin);
        dest.writeString(package_type);
        dest.writeString(package_unit_type);
        dest.writeInt(package_unit_volume_in_milliliters);
        dest.writeInt(total_package_units);
        dest.writeInt(volume_in_milliliters);
        dest.writeInt(alcohol_content);
        dest.writeInt(price_per_liter_of_alcohol_in_cents);
        dest.writeInt(price_per_liter_in_cents);
        dest.writeInt(inventory_count);
        dest.writeString(producer_name);
        dest.writeString(serving_description);
        dest.writeString(tasting_note);
        dest.writeString(updated_at);
        dest.writeString(image_thumb_url);
        dest.writeString(image_url);
        dest.writeString(varietal);
        dest.writeString(style);
        dest.writeString(tertiary_category);
        dest.writeInt(product_no);
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel parcel) {
            return new Item(parcel);
        }

        @Override
        public Item[] newArray(int i) {
            return new Item[0];
        }
    };
}
