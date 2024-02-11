package com.shiend.testdiskominfo.network;

import com.google.gson.JsonObject;
import com.shiend.testdiskominfo.model.DataVideo;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface Services {
    @Headers("Accept: application/json")
    @GET("api/video")
    Call<JsonObject> getVideo();
}
