package com.tripper.nfctripper.aqua.apihelper;
import com.tripper.nfctripper.aqua.model.productModel.ResultProduct;
import com.tripper.nfctripper.aqua.model.testModel.ResultTest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;


public interface ProductService {





    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("products")
    Call<ResultProduct> getIndex(@Header("Authorization") String auth);





}