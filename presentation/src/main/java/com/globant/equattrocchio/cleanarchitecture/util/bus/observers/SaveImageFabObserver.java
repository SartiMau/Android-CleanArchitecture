package com.globant.equattrocchio.cleanarchitecture.util.bus.observers;

public abstract class SaveImageFabObserver extends BusObserver<SaveImageFabObserver.SaveImageFabPressed> {
    public SaveImageFabObserver() {
        super(SaveImageFabPressed.class);
    }

    public static class SaveImageFabPressed {

    }

}