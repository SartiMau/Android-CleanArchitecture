package com.globant.equattrocchio.cleanarchitecture.mvp.view;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;

public class FragmentView <T extends Fragment>{
    private WeakReference<T> dialogFragmentRef;

    public FragmentView(T dialogFragment) {
        dialogFragmentRef = new WeakReference<>(dialogFragment);
    }

    @Nullable
    public T getFragment() {
        return dialogFragmentRef.get();
    }

    @Nullable
    public Context getContext() {
        return getFragment().getActivity();
    }

    @Nullable
    public FragmentManager getFragmentManager() {
        T fragment = getFragment();
        return (fragment != null) ? fragment.getFragmentManager() : null;
    }

}
