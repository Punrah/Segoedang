package com.tripper.nfctripper.aqua.apihelper;
import com.tripper.nfctripper.aqua.model.Result;
import com.tripper.nfctripper.aqua.model.testModel.ResultTest;
import com.tripper.nfctripper.aqua.model.userModel.ResultUser;

import junit.framework.TestResult;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;


public interface TestService {





    @Headers({ "Content-Type: application/json;charset=UTF-8"})
    @GET("tests")
    Call<ResultTest> getIndex(@Header("Authorization") String auth);



}