package com.fitareq.noteme;

import android.app.Application;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {DataClass.class}, version = 1, exportSchema = false)
public abstract class LocalDatabase extends RoomDatabase {

    public abstract DataDao dataDao();

    private static volatile LocalDatabase INSTANCE;
    public static final int NUMBER_OF_THREAD = 4;
    public static final ExecutorService databaseWriteExecutors = Executors.newFixedThreadPool(NUMBER_OF_THREAD);

    public static LocalDatabase getINSTANCE(Application application) {

        if (INSTANCE == null) {
            synchronized (LocalDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(application, LocalDatabase.class, "NoteMe")
                                       .build();
                }
            }
        }
        return INSTANCE;
    }
}
