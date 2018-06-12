package com.globant.equattrocchio.cleanarchitecture.mvp.view.base;

import android.app.Activity;
import android.os.Bundle;

import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.cleanarchitecture.mvp.presenter.ImagesPresenter;
import com.globant.equattrocchio.cleanarchitecture.mvp.view.ImagesView;
import com.globant.equattrocchio.data.ImagesRepositoryImpl;
import com.globant.equattrocchio.data.ImagesServicesImpl;
import com.globant.equattrocchio.domain.GetLatestImagesUseCase;
import com.globant.equattrocchio.domain.GetSpecificImageUseCase;
import com.globant.equattrocchio.domain.LoadImagesUseCase;
import com.globant.equattrocchio.domain.SaveImagesUseCase;

import io.realm.Realm;

public class MainActivity extends Activity {

    private ImagesPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GetLatestImagesUseCase getLatestImagesUseCase = new GetLatestImagesUseCase(new ImagesServicesImpl());
        GetSpecificImageUseCase getSpecificImageUseCase = new GetSpecificImageUseCase(new ImagesServicesImpl());
        SaveImagesUseCase saveImagesUseCase = new SaveImagesUseCase(new ImagesServicesImpl());
        LoadImagesUseCase loadImagesUseCase = new LoadImagesUseCase(new ImagesRepositoryImpl());

        presenter = new ImagesPresenter(new ImagesView(this), getLatestImagesUseCase, getSpecificImageUseCase, saveImagesUseCase, loadImagesUseCase);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.register();
    }

    @Override
    protected void onPause() {
        super.onPause();
        presenter.unregister();
    }
}