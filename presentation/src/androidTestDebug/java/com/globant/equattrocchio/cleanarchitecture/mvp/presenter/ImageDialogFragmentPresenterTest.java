package com.globant.equattrocchio.cleanarchitecture.mvp.presenter;

import com.globant.equattrocchio.cleanarchitecture.mvp.model.ImageDialogFragmentModel;
import com.globant.equattrocchio.cleanarchitecture.mvp.view.ImageDialogFragmentView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import io.reactivex.observers.DisposableObserver;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class ImageDialogFragmentPresenterTest {

    private ImageDialogFragmentPresenter presenter;
    @Mock ImageDialogFragmentView view;
    @Mock ImageDialogFragmentModel model;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        presenter = new ImageDialogFragmentPresenter(view, model);
    }

    @Test
    public void shouldLoadData(){
        presenter.loadData();
        verify(view).setCardImageIdText(Mockito.anyString());
        verify(view).setCardImageUrlText((String) eq(null));
        verify(view).loadImage((String) eq(null));
        verifyNoMoreInteractions(view);
    }

}
