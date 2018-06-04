package com.globant.equattrocchio.data.service.api;

import com.globant.equattrocchio.data.response.GetLatestImagesResponse;
import com.globant.equattrocchio.data.response.ImageResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SplashbaseApi {

    @GET("api/v1/images/latest")
    Call<GetLatestImagesResponse> getImages();

    @GET("api/v1/images/{id}")
    Call<ImageResponse> getImages(@Path("id") Integer id);
}
