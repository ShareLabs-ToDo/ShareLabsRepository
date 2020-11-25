package com.example.sharedto_doapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.sharedto_doapp.models.Task;

import com.google.android.material.textfield.TextInputEditText;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.Date;

public class TaskComposeActivity extends AppCompatActivity {

    public static final String TAG = "TaskComposeActivity";

    TextInputEditText taskTitleField = findViewById(R.id.compose_task_field);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_compose);

        Button composeButton = findViewById(R.id.task_compose_button);
        composeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String task_title = taskTitleField.getText().toString();
                createNewTask(task_title);
            }
        });

    }

    public void createNewTask(String title) {
        Task task = new Task();
        task.setUser(ParseUser.getCurrentUser());
        task.setTitle(title);
        task.setIsDone(false);
        task.setDeadline(new Date());

        task.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Error attempting to create new task");
                    Toast.makeText(getApplicationContext(), "Sorry, problem saving task", Toast.LENGTH_SHORT).show();
                }
                Log.i(TAG, "New Task was Created");
                taskTitleField.setText("");
            }
        });
    }
}