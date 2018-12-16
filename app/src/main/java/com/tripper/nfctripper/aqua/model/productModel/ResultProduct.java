package com.tripper.nfctripper.aqua.model.productModel;

import com.google.gson.annotations.SerializedName;
import com.tripper.nfctripper.aqua.model.Result;

public class ResultProduct extends Result {

    @SerializedName("data")
    private DataProduct dataProduct;

    public DataProduct getDataProduct() {
        return dataProduct;
    }

    public void setDataProduct(DataProduct dataProduct) {
        this.dataProduct = dataProduct;
    }
}
