package com.globant.equattrocchio.data;

import com.globant.equattrocchio.data.response.Image;
import com.globant.equattrocchio.data.response.Result;
import com.globant.equattrocchio.data.service.api.SplashbaseApi;
import com.globant.equattrocchio.domain.enities.ImageEntity;
import com.globant.equattrocchio.domain.service.ImagesServices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observer;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ImagesServicesImpl implements ImagesServices {

    private static final String URL= "http://splashbase.co/";

    @Override
    public void getLatestImages(final Observer<List<ImageEntity>> observer) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        SplashbaseApi api  = retrofit.create(SplashbaseApi.class);

        Call<Result> call = api.getImages();

        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                observer.onNext(transform(response.body()));
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                observer.onError(t);
            }
        });


    }

    private List<ImageEntity> transform(Result result) {
        List<ImageEntity> images = new ArrayList<ImageEntity>();
        for (Image image : result.getImages()) {
            images.add(new ImageEntity(image.getId(), image.getUrl()));
        }

        return images;
    }
}
