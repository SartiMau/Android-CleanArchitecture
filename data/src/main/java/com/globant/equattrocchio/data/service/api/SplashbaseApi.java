package com.globant.equattrocchio.data.service.api;

import com.globant.equattrocchio.data.response.Result;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface SplashbaseApi {

//    This is from the second Module's item
    @GET("api/v1/images/latest")
    Call<ResponseBody> getJSON();

//    This is from the third Module's item
    @GET("api/v1/images/latest")
    Call<Result> getImages();
}
