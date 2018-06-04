package com.globant.equattrocchio.domain;


import com.globant.equattrocchio.domain.enities.Image;
import com.globant.equattrocchio.domain.service.ImagesServices;
import io.reactivex.observers.DisposableObserver;

public class GetSpecificImageUseCase extends UseCase<Image,Integer> {

        private ImagesServices imagesServices;

        public GetSpecificImageUseCase(ImagesServices imagesServices) {
            super();
            this.imagesServices = imagesServices;
        }

        @Override
        void buildUseCaseObservable(DisposableObserver<Image> observer, Integer id) {
            imagesServices.getSpecificImage(observer, id);
        }

}
