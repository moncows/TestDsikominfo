package com.shiend.testdiskominfo.network;

import com.shiend.testdiskominfo.model.DataVideo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Services {
    @Headers("Accept: application/json")
    @FormUrlEncoded //masalah untuk penggunaan field buat php biasa(buka pyhton)
    @POST("api/video")
    Call<DataVideo> getVideo(@Field("asdf") String asdf
            , @Field("asdaaf") String asaadf);
}
