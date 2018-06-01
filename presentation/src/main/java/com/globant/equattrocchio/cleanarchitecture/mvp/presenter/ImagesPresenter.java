package com.globant.equattrocchio.cleanarchitecture.mvp.presenter;

import android.app.Activity;

import com.globant.equattrocchio.cleanarchitecture.util.bus.RxBus;
import com.globant.equattrocchio.cleanarchitecture.mvp.view.ImagesView;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.CallServiceButtonObserver;
import com.globant.equattrocchio.data.response.Result;
import com.globant.equattrocchio.domain.GetLatestImagesUseCase;
import com.globant.equattrocchio.domain.enities.ImageEntity;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;

public class ImagesPresenter {

    private ImagesView view;

//    This is from the second Module's item
//    private GetJsonUseCase getJsonUseCase;

//    This is from the third Module's item
    private GetLatestImagesUseCase getLatestImagesUseCase;

//    This is from the second Module's item
//    public ImagesPresenter(ImagesView view, GetJsonUseCase getJsonUseCase) {
//        this.view = view;
//        this.getJsonUseCase = getJsonUseCase;
//    }

//    This is from the third Module's item
    public ImagesPresenter(ImagesView view, GetLatestImagesUseCase getLatestImagesUseCase) {
        this.view = view;
        this.getLatestImagesUseCase = getLatestImagesUseCase;
    }

    public void onCountButtonPressed() {
        view.showText(new String(""));//todo: aca va el string que me devuelva el execute del usecase
    }

    private void onCallServiceButtonPressed() {

//        This is from the second Module's item
//        getJsonUseCase.execute(new DisposableObserver<String>() {
//            @Override
//            public void onNext(@NonNull String s) {
//                view.showText(s);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                view.showError();
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        }, null);

//        This is from the third Module's item
        getLatestImagesUseCase.execute(new DisposableObserver<List<ImageEntity>>() {
            @Override
            public void onNext(@NonNull List<ImageEntity> images) {
                view.showImagesInCardView(images);
            }

            @Override
            public void onError(@NonNull Throwable e) {
               view.showError();
            }

            @Override
            public void onComplete() {

            }
        },null);

        //todo acá tengo que llamar a la domain layer para que llame a la data layer y haga el llamdo al servicio
    }









    public void register() {
        Activity activity = view.getActivity();

        if (activity==null){
            return;
        }

        RxBus.subscribe(activity, new CallServiceButtonObserver() {
            @Override
            public void onEvent(CallServiceButtonPressed event) {
                onCallServiceButtonPressed();
            }
        });

    }

    public void unregister() {
        Activity activity = view.getActivity();

        if (activity==null){
            return;
        }
        RxBus.clear(activity);
    }
}
