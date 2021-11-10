package com.fitareq.noteme;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;


public class mRepository {

    private DataDao dataDao;
    private LiveData<List<DataClass>> data;
    private LiveData<DataClass> detailsData;

    public mRepository(Application application) {

        LocalDatabase db = LocalDatabase.getINSTANCE(application);
        dataDao = db.dataDao();
        data = dataDao.getAllNotes();

    }



    public LiveData<DataClass> getDetailsData(Long id){
        detailsData = dataDao.getNotesDetails(id);
        return detailsData;
    }
    public LiveData<List<DataClass>> getSpecificData(String status){

        return dataDao.getSpecificNotesDetails(status);
    }
    public LiveData<List<DataClass>> getData() {

        return data;
    }



    public void setData(DataClass data) {

        LocalDatabase.databaseWriteExecutors.execute(()->
        {
            dataDao.insetNote(data);
        });
    }
    public void deleteData(DataClass data) {

        LocalDatabase.databaseWriteExecutors.execute(()->
        {
            dataDao.deleteNote(data);
        });

    }
    public void deleteDetailsData(Long id) {

        LocalDatabase.databaseWriteExecutors.execute(()->
        {
            dataDao.deleteNotesDetails(id);
        });

    }
    public void updateData(DataClass data) {

        LocalDatabase.databaseWriteExecutors.execute(()->
        {
            dataDao.updateData(data);
        });

    }




}
