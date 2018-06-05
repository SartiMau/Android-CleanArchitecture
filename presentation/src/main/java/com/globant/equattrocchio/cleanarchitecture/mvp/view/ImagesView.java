package com.globant.equattrocchio.cleanarchitecture.mvp.view;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.globant.equattrocchio.cleanarchitecture.R;
import com.globant.equattrocchio.cleanarchitecture.mvp.view.adapters.ImagesAdapter;
import com.globant.equattrocchio.cleanarchitecture.mvp.view.base.ImageDialogFragment;
import com.globant.equattrocchio.cleanarchitecture.util.bus.RxBus;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.CallServiceButtonObserver;
import com.globant.equattrocchio.cleanarchitecture.util.bus.observers.CallServiceCardObserver;
import com.globant.equattrocchio.domain.enities.Image;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImagesView extends ActivityView {

    @BindView(R.id.tv_incoming_json) TextView tvlabel;
    @BindView(R.id.recycler_view) RecyclerView recyclerView;

    public ImagesView(Activity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
    }

    public void showText(String text) {
        tvlabel.setText(text);
    }

    @OnClick(R.id.btn_call_service)
    public void callServiceBtnPressed() {
        RxBus.post(new CallServiceButtonObserver.CallServiceButtonPressed());
    }

    public void showError() {
        tvlabel.setEnabled(true);
        tvlabel.setText(R.string.connection_error);
    }

    public void hideError() {
        tvlabel.setEnabled(false);
        tvlabel.setText("");
    }

    public void showImagesInCardView(List<Image> images) {
        recyclerView.setAdapter(new ImagesAdapter(images));
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
    }

    public void showImagesInFragmentDialog(Image image){
        Context context = getContext();

        if(context != null){
            ImageDialogFragment.newInstance(image).show(getFragmentManager(), context.getResources().getString(R.string.image_dialog));
        }
    }
}



