package com.globant.equattrocchio.domain;

import com.globant.equattrocchio.domain.service.ImagesServices;

import io.reactivex.observers.DisposableObserver;

public class GetJsonUseCase extends UseCase<String ,Void> {

    private final ImagesServices imagesServices;

    public GetJsonUseCase(ImagesServices imagesServices) {
        this.imagesServices = imagesServices;
    }

    @Override
    void buildUseCaseObservable(DisposableObserver<String> observer, Void aVoid) {
        imagesServices.getJSON(observer);
    }
}
