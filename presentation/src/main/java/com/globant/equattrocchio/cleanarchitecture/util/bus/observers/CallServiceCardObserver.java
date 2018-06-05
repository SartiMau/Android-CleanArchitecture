package com.globant.equattrocchio.cleanarchitecture.util.bus.observers;

public abstract class CallServiceCardObserver extends BusObserver<CallServiceCardObserver.CallServiceCardPressed> {

    public CallServiceCardObserver() {
        super(CallServiceCardPressed.class);
    }

    public static class CallServiceCardPressed {
        private int cardImageId;

        public CallServiceCardPressed(int cardImageId) {
            this.cardImageId = cardImageId;
        }

        public int getCardImageId() {
            return cardImageId;
        }
    }
}