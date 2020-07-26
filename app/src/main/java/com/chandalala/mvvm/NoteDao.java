package com.chandalala.mvvm;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/*
* This interface specifies all the sqlite database operations we want to perform
*
* */

@Dao
public interface NoteDao {

    @Insert // Using these annotation, room will automatically generate the required code in the background
    void insert(Note note);

    @Update
    void update(Note note);

    @Delete
    void delete(Note note);

    @Query("DELETE FROM note_table")
    void deleteAllNotes();


    @Query("SELECT * FROM note_table ORDER BY priority_column DESC")
    LiveData<List<Note>> getAllNotes(); // Because of livedata, as soon as there are changes in notetable this value will be updated immediately


}
