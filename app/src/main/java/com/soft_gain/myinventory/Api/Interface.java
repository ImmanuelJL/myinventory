package com.soft_gain.myinventory.Api;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import com.soft_gain.myinventory.Model.Auth.Login;
import com.soft_gain.myinventory.Model.Barang.Barang;
import com.soft_gain.myinventory.Model.Barang.SingleDataBarang;
import com.soft_gain.myinventory.Model.GetReponseSuccess;

public interface Interface {
    @FormUrlEncoded
    @POST("login")
    Call<Login> postLogin(@Field("email") String email,
                           @Field("password") String password);

    @POST("barang")
    Call<Barang> postBarang(@Header("Authorization") String Authorization);

    @FormUrlEncoded
    @PUT("barang/store")
    Call<GetReponseSuccess> postBarangStore(@Header("Authorization") String Authorization,
                                            @Field("nama_barang") String nama_barang,
                                            @Field("jumlah_barang") String jumlah_barang,
                                            @Field("keterangan") String keterangan);

    @POST("barang/edit/{id}")
    Call<SingleDataBarang> postBarangEdit(@Header("Authorization") String Authorization,
                                          @Path(value = "id", encoded = true) String id);

    @FormUrlEncoded
    @POST("barang/update/{id}")
    Call<GetReponseSuccess> postBarangUpdate(@Header("Authorization") String Authorization,
                                            @Field("nama_barang") String nama_barang,
                                            @Field("jumlah_barang") String jumlah_barang,
                                            @Field("keterangan") String keterangan,
                                             @Path(value = "id", encoded = true) String id);

    @DELETE("barang/delete/{id}")
    Call<GetReponseSuccess> postBarangDelete(@Header("Authorization") String Authorization,
                                             @Path(value = "id", encoded = true) String id);
}
