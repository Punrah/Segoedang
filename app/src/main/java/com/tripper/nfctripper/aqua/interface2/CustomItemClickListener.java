package com.tripper.nfctripper.aqua.interface2;
import android.view.View;

import com.tripper.nfctripper.aqua.model.productModel.ResultProduct;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface CustomItemClickListener {
    public void onItemClick(View v, int position);
}