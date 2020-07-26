package com.chandalala.mvvm;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

// This class creates sqlite database using ROOMDATABASE

@Database(entities = {Note.class}, version = 1) // Specifies the tables in the database and sqlite database version
public abstract class NoteDatabase extends RoomDatabase {

    private static NoteDatabase instance; // To avoid creation of multiple instances of the same database we turn this class into a singleton
    public abstract NoteDao noteDao();

    //Synchronised means only one thread can access this method at any given time
    public static synchronized NoteDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),NoteDatabase.class,"note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDb(instance).execute();
        }
    };

    private static class PopulateDb extends AsyncTask<Void, Void, Void>{

        private NoteDao noteDao;

        public PopulateDb(NoteDatabase noteDatabase) {
            this.noteDao = noteDatabase.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new Note("Title 1", "Desciption 1", 1));
            noteDao.insert(new Note("Title 2", "Desciption 2", 2));
            noteDao.insert(new Note("Title 3", "Desciption 3", 3));

            return null;
        }
    }
}
