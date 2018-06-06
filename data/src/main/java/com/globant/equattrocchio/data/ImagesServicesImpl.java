package com.globant.equattrocchio.data;

import com.globant.equattrocchio.data.response.ImageResponse;
import com.globant.equattrocchio.data.response.GetLatestImagesResponse;
import com.globant.equattrocchio.data.service.api.SplashbaseApi;
import com.globant.equattrocchio.domain.enities.Image;
import com.globant.equattrocchio.domain.service.ImagesServices;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImagesServicesImpl implements ImagesServices {

    private static final String URL= "http://splashbase.co/";

    @Override
    public void getLatestImages(final Observer<List<Image>> observer) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SplashbaseApi api  = retrofit.create(SplashbaseApi.class);

        Call<GetLatestImagesResponse> call = api.getImages();

        call.enqueue(new Callback<GetLatestImagesResponse>() {
            @Override
            public void onResponse(Call<GetLatestImagesResponse> call, Response<GetLatestImagesResponse> response) {
                observer.onNext(transform(response.body()));
            }

            @Override
            public void onFailure(Call<GetLatestImagesResponse> call, Throwable t) {
                observer.onError(t);
            }
        });
    }

    @Override
    public void getSpecificImage(final Observer<Image> observer, int id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SplashbaseApi api  = retrofit.create(SplashbaseApi.class);

        Call<ImageResponse> call = api.getImage(id);

        call.enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                observer.onNext(transformImage(response.body()));
            }

            @Override
            public void onFailure(Call<ImageResponse> call, Throwable t) {
                observer.onError(t);
            }
        });
    }

    @Override
    public void saveImage(final Observer<List<Image>> observer) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SplashbaseApi api  = retrofit.create(SplashbaseApi.class);

        Call<GetLatestImagesResponse> call = api.getImages();

        call.enqueue(new Callback<GetLatestImagesResponse>() {
            @Override
            public void onResponse(Call<GetLatestImagesResponse> call, Response<GetLatestImagesResponse> response) {
                List<Image> images = transformCompleteImages(response.body());
                ImageReporsitory.insertImages(images);
                observer.onNext(images);
            }

            @Override
            public void onFailure(Call<GetLatestImagesResponse> call, Throwable t) {
                observer.onError(t);
            }
        });
    }

    private Image transformImage(ImageResponse imageResponse) {
        Image image = new Image(imageResponse.getId(), imageResponse.getUrl(), imageResponse.getLargeUrl());

        return image;
    }

    private List<Image> transform(GetLatestImagesResponse getLatestImagesResponse) {
        List<Image> images = new ArrayList<Image>();
        for (ImageResponse imageResponse : getLatestImagesResponse.getImages()) {
            images.add(new Image(imageResponse.getId(), imageResponse.getUrl()));
        }

        return images;
    }

    private List<Image> transformCompleteImages(GetLatestImagesResponse getLatestImagesResponse) {
        List<Image> images = new ArrayList<Image>();
        for (ImageResponse imageResponse : getLatestImagesResponse.getImages()) {
            images.add(new Image(imageResponse.getId(), imageResponse.getUrl(), imageResponse.getLargeUrl()));
        }

        return images;
    }
}
