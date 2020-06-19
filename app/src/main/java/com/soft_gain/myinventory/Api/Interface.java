package com.soft_gain.myinventory.Api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;

import com.soft_gain.myinventory.Model.Auth.Login;

public interface Interface {
    @FormUrlEncoded
    @POST("login")
    Call<Login> postLogin(@Field("email") String email,
                           @Field("password") String password);
}