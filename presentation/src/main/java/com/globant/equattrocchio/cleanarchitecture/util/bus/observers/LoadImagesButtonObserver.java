package com.globant.equattrocchio.cleanarchitecture.util.bus.observers;

public abstract class LoadImagesButtonObserver extends BusObserver<LoadImagesButtonObserver.LoadImagesButtonPressed> {
    public LoadImagesButtonObserver() {
        super(LoadImagesButtonObserver.LoadImagesButtonPressed.class);
    }

    public static class LoadImagesButtonPressed {

    }

}
