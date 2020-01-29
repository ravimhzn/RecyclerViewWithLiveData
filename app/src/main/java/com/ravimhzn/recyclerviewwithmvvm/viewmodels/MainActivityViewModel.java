package com.ravimhzn.recyclerviewwithmvvm.viewmodels;

import android.os.AsyncTask;

import com.ravimhzn.recyclerviewwithmvvm.models.Places;
import com.ravimhzn.recyclerviewwithmvvm.repositories.PlacesRepo;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<List<Places>> mPlaces;
    private PlacesRepo placesRepo;
    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();


    public void init() {
        if (mPlaces != null) {
            return;
        }
        placesRepo = PlacesRepo.getInstance();
        mPlaces = placesRepo.getPlaces();
    }

    public LiveData<List<Places>> getPlaces() {
        return mPlaces;
    }

    public void addNewValue(final Places places){
        mIsUpdating.setValue(true);

        new AsyncTask<Void, Void, Void>(){
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                List<Places> currentPlaces = mPlaces.getValue();
                currentPlaces.add(places);
                mPlaces.postValue(currentPlaces);
                mIsUpdating.postValue(false);
            }

            @Override
            protected Void doInBackground(Void... voids) {

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();
    }

    public LiveData<Boolean> getIsUpdating(){
        return mIsUpdating;
    }
}
