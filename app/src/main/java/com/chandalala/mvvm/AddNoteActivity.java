package com.chandalala.mvvm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class AddNoteActivity extends AppCompatActivity {

    public static final String EXTRA_TITLE="com.chandalala.mvvm.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION="com.chandalala.mvvm.EXTRA_DESCRIPTION";
    public static final String EXTRA_PRIORITY="com.chandalala.mvvm.EXTRA_PRIORITY";


    private EditText editText_title, editText_Description;
    private NumberPicker numberPicker_priority;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editText_title=findViewById(R.id.editText_title);
        editText_Description=findViewById(R.id.editText_description);
        numberPicker_priority=findViewById(R.id.number_picker_priority);

        numberPicker_priority.setMinValue(1);
        numberPicker_priority.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Note");


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.save_note){
            saveNote();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveNote() {

        String title= editText_title.getText().toString().trim();
        String description = editText_Description.getText().toString().trim();
        int priority = numberPicker_priority.getValue();

        if (title.isEmpty() || description.isEmpty()){
            Toast.makeText(this, "Cannot save empty fields", Toast.LENGTH_LONG).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_PRIORITY, priority);

        setResult(RESULT_OK, data);
        finish();

    }

}
