package com.chandalala.mvvm;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/*
* This class is an entity which represents a table in sqlite database
* */

@Entity(tableName = "note_table") //name of the table in sqlite
public class Note {


    //Instance variables are the table columns
    @PrimaryKey(autoGenerate = true) // Assigning an auto_incremented primary key in the table
    private int id;

    private String title, description;

    @ColumnInfo(name = "priority_column") //Assign another column name instead of the variable name
    private int priority;

    /*
    * The name of the table is "note_table" and has 4 columns id, title, description, priority
    * */

    public Note(String title, String description, int priority) {
        this.title = title;
        this.description = description;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
