package com.fitareq.noteme;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface DataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insetNote(DataClass data);

    @Delete
    void deleteNote(DataClass data);

    @Update
    void updateData(DataClass data);

    @Query("SELECT * FROM note")
    LiveData<List<DataClass>> getAllNotes();

    @Query("SELECT * FROM note WHERE id = :i")
    LiveData<DataClass> getNotesDetails(Long i);

    @Query("DELETE FROM note WHERE id = :i")
    void deleteNotesDetails(Long i);

    @Query("SELECT * FROM note WHERE status = :i")
    LiveData<List<DataClass>> getSpecificNotesDetails(String i);

}
