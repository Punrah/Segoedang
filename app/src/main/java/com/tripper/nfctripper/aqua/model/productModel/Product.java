package com.tripper.nfctripper.aqua.model.productModel;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Product implements Parcelable {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("price")
    private double price;

    @SerializedName("qty")
    private double qty;

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", qty=" + qty +
                ", quantity=" + quantity +
                ", category_id=" + category_id +
                ", brand_id=" + brand_id +
                ", image='" + image + '\'' +
                ", user_id=" + user_id +
                '}';
    }

    @SerializedName("quantity")
    private double quantity;

    @SerializedName("category_id")
    private int category_id;

    @SerializedName("brand_id")
    private int brand_id;

    @SerializedName("image")
    private String image;

    @SerializedName("user_id")
    private int user_id;

    public double getSubTotal() {
        return price*qty;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(int brand_id) {
        this.brand_id = brand_id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Product() {
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeDouble(this.price);
        dest.writeDouble(this.qty);
        dest.writeDouble(this.quantity);
        dest.writeInt(this.category_id);
        dest.writeInt(this.brand_id);
        dest.writeString(this.image);
        dest.writeInt(this.user_id);
    }

    protected Product(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.price = in.readInt();
        this.qty = in.readInt();
        this.quantity = in.readInt();
        this.category_id = in.readInt();
        this.brand_id = in.readInt();
        this.image = in.readString();
        this.user_id = in.readInt();
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel source) {
            return new Product(source);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
