package com.soft_gain.myinventory.Api;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

import com.soft_gain.myinventory.Model.Auth.Login;
import com.soft_gain.myinventory.Model.Barang.Barang;

public interface Interface {
    @FormUrlEncoded
    @POST("login")
    Call<Login> postLogin(@Field("email") String email,
                           @Field("password") String password);

    @POST("barang")
    Call<Barang> postBarang(@Header("Authorization") String Authorization);
}
