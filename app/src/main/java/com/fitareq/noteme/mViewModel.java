package com.fitareq.noteme;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;


public class mViewModel extends AndroidViewModel {

    private mRepository repository;
    private LiveData<List<DataClass>> data;


    public mViewModel(@NonNull Application application) {

        super(application);
        repository = new mRepository(application);
        data = repository.getData();

    }

    public LiveData<List<DataClass>> getSpecificData(String status){

        return repository.getSpecificData(status);
    }
    public LiveData<DataClass> getDetailsData(Long id){

        return repository.getDetailsData(id);
    }

    public LiveData<List<DataClass>> getData() {

        return data;
    }
    public void setData(DataClass data) {
        repository.setData(data);
    }

    public void deleteData(DataClass data) {
        repository.deleteData(data);
    }
    public void deleteDetailsData(Long id) {
        repository.deleteDetailsData(id);
    }
    public void updateData(DataClass data) {
        repository.updateData(data);
    }



}
