package com.chandalala.mvvm;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {

    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    /*
    * Data repository, not mandatory but mediates between the viewModel and different data sources
    * such as room or web service
    * */

    public NoteRepository(Application application) {

        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    public void insert(Note note){
        new InsertNote(noteDao).execute(note);
    }

    public void update(Note note){
        new UpdateNote(noteDao).execute(note);
    }

    public void delete(Note note){
        new DeleteNote(noteDao).execute(note);
    }

    public void deleteAllNotes(){
        new DeleteAllNote(noteDao).execute();
    }

    public LiveData<List<Note>> getAllNotes(){
        return allNotes;
    }

    private static class InsertNote extends AsyncTask<Note, Void, Void>{

        private NoteDao noteDao;

        private InsertNote(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    private static class DeleteAllNote extends AsyncTask<Void, Void, Void>{

        private NoteDao noteDao;

        private DeleteAllNote(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }

    private static class DeleteNote extends AsyncTask<Note, Void, Void>{

        private NoteDao noteDao;

        private DeleteNote(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }

    private static class UpdateNote extends AsyncTask<Note, Void, Void>{

        private NoteDao noteDao;

        private UpdateNote(NoteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }
}
