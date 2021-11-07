package com.fitareq.noteme;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;


public class mRepository {

    private DataDao dataDao;
    private LiveData<List<DataClass>> data;

    public mRepository(Application application) {

        LocalDatabase db = LocalDatabase.getINSTANCE(application);
        dataDao = db.dataDao();
        data = dataDao.getAllNotes();

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
    public void updateData(DataClass data) {

        LocalDatabase.databaseWriteExecutors.execute(()->
        {
            dataDao.updateData(data);
        });

    }




}
