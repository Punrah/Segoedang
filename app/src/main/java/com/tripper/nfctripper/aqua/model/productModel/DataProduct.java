package com.tripper.nfctripper.aqua.model.productModel;

import com.google.gson.annotations.SerializedName;
import com.tripper.nfctripper.aqua.model.Data;

import java.util.ArrayList;

public class DataProduct extends Data {

    @SerializedName("data")
    private ArrayList<Product> productList;


    public ArrayList<Product> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }
}
