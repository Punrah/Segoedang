package com.tripper.nfctripper.aqua.apihelper;
import com.tripper.nfctripper.aqua.model.Result;
import com.tripper.nfctripper.aqua.model.userModel.ResultUser;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;




public interface UserService {

    //The register call
    @FormUrlEncoded
    @POST("register")
    Call<Result> createUser(
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password,
            @Field("c_password") String c_password);

    //the signin call
    @FormUrlEncoded
    @POST("login")
    Call<ResultUser> userLogin(
            @Field("email") String email,
            @Field("password") String password
    );

}