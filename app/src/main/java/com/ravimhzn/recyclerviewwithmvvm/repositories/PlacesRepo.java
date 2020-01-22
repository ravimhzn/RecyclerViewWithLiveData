package com.ravimhzn.recyclerviewwithmvvm.repositories;

import com.ravimhzn.recyclerviewwithmvvm.models.Places;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;

public class PlacesRepo {

    private static PlacesRepo instance;
    private ArrayList<Places> dataSet = new ArrayList<>();

    public static PlacesRepo getInstance() {
        if (instance == null) {
            instance = new PlacesRepo();
        }
        return instance;
    }

    // Pretend to get data from a WEB API or online source
    public MutableLiveData<List<Places>> getPlaces() {
        setNicePlaces();
        MutableLiveData<List<Places>> mLiveData = new MutableLiveData<>();
        mLiveData.setValue(dataSet);
       // mLiveData.postValue(dataSet);
        return mLiveData;
    }

//    public MutableLiveData<List<Places>> updatePlaces(){
//
//    }


    private void setNicePlaces() {
        dataSet.clear();
        dataSet.add(
                new Places("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg",
                        "Havasu Falls")
        );
        dataSet.add(
                new Places("https://i.redd.it/tpsnoz5bzo501.jpg",
                        "Trondheim")
        );
        dataSet.add(
                new Places("https://i.redd.it/qn7f9oqu7o501.jpg",
                        "Portugal")
        );
        dataSet.add(
                new Places("https://i.redd.it/j6myfqglup501.jpg",
                        "Rocky Mountain National Park")
        );
        dataSet.add(
                new Places("https://i.redd.it/0h2gm1ix6p501.jpg",
                        "Havasu Falls")
        );
        dataSet.add(
                new Places("https://i.redd.it/k98uzl68eh501.jpg",
                        "Mahahual")
        );
        dataSet.add(
                new Places("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg",
                        "Frozen Lake")
        );
        dataSet.add(
                new Places("https://i.redd.it/obx4zydshg601.jpg",
                        "Austrailia")
        );
    }

}
