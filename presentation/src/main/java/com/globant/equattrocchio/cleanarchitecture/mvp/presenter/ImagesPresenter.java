package com.globant.equattrocchio.cleanarchitecture.mvp.presenter;

import android.app.Activity;

import com.globant.equattrocchio.cleanarchitecture.mvp.view.ImagesView;
import com.globant.equattrocchio.cleanarchitecture.util.bus.RxBus;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.CallServiceButtonObserver;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.CallServiceCardObserver;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.LoadImagesButtonObserver;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.SaveImageFabObserver;
import com.globant.equattrocchio.data.entities.ImageEntity;
import com.globant.equattrocchio.domain.GetLatestImagesUseCase;
import com.globant.equattrocchio.domain.GetSpecificImageUseCase;
import com.globant.equattrocchio.domain.LoadImagesUseCase;
import com.globant.equattrocchio.domain.SaveImagesUseCase;
import com.globant.equattrocchio.domain.enities.Image;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class ImagesPresenter {

    private ImagesView view;

    private GetLatestImagesUseCase getLatestImagesUseCase;
    private GetSpecificImageUseCase getSpecificImageUseCase;
    private SaveImagesUseCase saveImagesUseCase;
    private LoadImagesUseCase loadImagesUseCase;

    private RealmChangeListener changeListener;

    public ImagesPresenter(final ImagesView view, GetLatestImagesUseCase getLatestImagesUseCase, GetSpecificImageUseCase getSpecificImageUseCase, SaveImagesUseCase saveImagesUseCase, LoadImagesUseCase loadImagesUseCase) {
        this.view = view;
        this.getLatestImagesUseCase = getLatestImagesUseCase;
        this.getSpecificImageUseCase = getSpecificImageUseCase;
        this.saveImagesUseCase = saveImagesUseCase;
        this.loadImagesUseCase = loadImagesUseCase;

        changeListener = new RealmChangeListener<RealmResults<ImageEntity>>() {
            @Override
            public void onChange(RealmResults<ImageEntity> result) {
                view.showImagesInCardView(transform(result));
                view.showUpdate();
                view.showLoadImagesOk();
            }
        };
        loadImagesUseCase.addChangeListener(changeListener);
    }

    public void onCallServiceButtonPressed() {

        view.hideError();

        getLatestImagesUseCase.execute(new DisposableObserver<List<Image>>() {
            @Override
            public void onNext(@NonNull List<Image> images) {
                view.showImagesInCardView(images);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                view.showError();
            }

            @Override
            public void onComplete() {

            }
        }, null);
    }

    public void onCallServiceCardPressed(int id) {
        getSpecificImageUseCase.execute(new DisposableObserver<Image>() {
            @Override
            public void onNext(@NonNull Image images) {
                view.showImagesInFragmentDialog(images);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                view.showError();
            }

            @Override
            public void onComplete() {

            }
        }, id);
    }

    public void onSaveImageFabPressed() {
        saveImagesUseCase.execute(new DisposableObserver<List<Image>>() {
            @Override
            public void onNext(@NonNull List<Image> images) {
                view.showSaveImagesOk();
            }

            @Override
            public void onError(@NonNull Throwable e) {
                view.showSaveImagesError();
            }

            @Override
            public void onComplete() {

            }
        }, null);
    }

    public void onLoadImagesButtonPressed() {
        List<Image> images = loadImagesUseCase.loadAllImages();

        view.showImagesInCardView(images);
        view.showLoadImagesOk();
    }


    public void register() {
        Activity activity = view.getActivity();

        if (activity == null) {
            return;
        }

        RxBus.subscribe(activity, new CallServiceButtonObserver() {
            @Override
            public void onEvent(CallServiceButtonPressed event) {
                onCallServiceButtonPressed();
            }
        });

        RxBus.subscribe(activity, new CallServiceCardObserver() {
            @Override
            public void onEvent(CallServiceCardPressed event) {
                onCallServiceCardPressed(event.getCardImageId());
            }
        });

        RxBus.subscribe(activity, new SaveImageFabObserver() {
            @Override
            public void onEvent(SaveImageFabPressed event) {
                onSaveImageFabPressed();
            }
        });

        RxBus.subscribe(activity, new LoadImagesButtonObserver() {
            @Override
            public void onEvent(LoadImagesButtonPressed event) {
                onLoadImagesButtonPressed();
            }
        });
    }

    public void unregister() {
        Activity activity = view.getActivity();

        if (activity == null) {
            return;
        }
        RxBus.clear(activity);
    }

    private List<Image> transform(RealmResults<ImageEntity> realmImages) {
        List<Image> images = new ArrayList<Image>();
        for (ImageEntity image : realmImages) {
            images.add(new Image((int) image.getId(), image.getUrl(), image.getLargeUrl()));
        }
        return images;
    }
}
