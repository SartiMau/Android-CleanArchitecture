package com.globant.equattrocchio.cleanarchitecture.mvp.presenter;

import com.globant.equattrocchio.cleanarchitecture.mvp.view.ImagesView;
import com.globant.equattrocchio.domain.GetLatestImagesUseCase;
import com.globant.equattrocchio.domain.GetSpecificImageUseCase;
import com.globant.equattrocchio.domain.LoadImagesUseCase;
import com.globant.equattrocchio.domain.SaveImagesUseCase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class ImagesPresenterTest {

    private ImagesPresenter presenter;
    @Mock GetLatestImagesUseCase getLatestImagesUseCase;
    @Mock GetSpecificImageUseCase getSpecificImageUseCase;
    @Mock SaveImagesUseCase saveImagesUseCase;
    @Mock LoadImagesUseCase loadImagesUseCase;
    @Mock ImagesView view;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        presenter = new ImagesPresenter(view, getLatestImagesUseCase, getSpecificImageUseCase, saveImagesUseCase, loadImagesUseCase);
    }

    @Test
    public void shouldCallService(){
        presenter.onCallServiceButtonPressed();
        verify(view).hideError();
        verify(getLatestImagesUseCase).execute(Mockito.any(DisposableObserver.class), (Void) eq(null));
        verifyNoMoreInteractions(getLatestImagesUseCase);
        verifyNoMoreInteractions(view);
    }

    @Test
    public void shouldGetSpecificImage(){
        int id = 8627;
        presenter.onCallServiceCardPressed(id);
        verify(getSpecificImageUseCase).execute(Mockito.any(DisposableObserver.class), eq(8627));
        verifyNoMoreInteractions(getLatestImagesUseCase);
    }

    @Test
    public void shouldSaveImages(){
        presenter.onSaveImageFabPressed();
        verify(saveImagesUseCase).execute(Mockito.any(DisposableObserver.class), (Void) eq(null));
        verifyNoMoreInteractions(saveImagesUseCase);
    }

    @Test
    public void shouldLoadImages(){
        presenter.onLoadImagesButtonPressed();
        verify(loadImagesUseCase).loadAllImages();
        verify(view).showImagesInCardView(Mockito.any(List.class));
        verify(view).showLoadImagesOk();
        verifyNoMoreInteractions(view);
    }

}
