package com.chandalala.mvvm;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

/*
* The job of the viewModel is to hold and prepare data for the UI and so we do not have to put any of
* it directly into the activities
* Activity or fragment connect to this viewModel and get necessary data and draw it to the screen
* and report user interactions back to the viewModel
*
*
* */

public class NoteViewModel extends AndroidViewModel {

    private NoteRepository noteRepository;

    /*
    * Live data is a wrapper which can hold any type of data including lists and can be observed
    * by the UI controller, this means when data in this livedata changes the ui automatically gets updated
    *
    * */

    private LiveData<List<Note>> allNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        noteRepository = new NoteRepository(application);
        allNotes = noteRepository.getAllNotes();
    }

    public void insert(Note note){
        noteRepository.insert(note);
    }

    public void update(Note note){
        noteRepository.update(note);
    }

    public void delete(Note note){
        noteRepository.delete(note);
    }

    public void deleteAllNotes(){
        noteRepository.deleteAllNotes();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }
}
