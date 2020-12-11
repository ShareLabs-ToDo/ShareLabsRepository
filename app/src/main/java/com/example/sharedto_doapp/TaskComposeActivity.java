package com.example.sharedto_doapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;


import com.example.sharedto_doapp.models.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class TaskComposeActivity extends AppCompatActivity {

    public static final String TAG = "TaskComposeActivity";

    TextInputEditText taskTitleField;
    TextInputEditText subTaskTitleField;
    ImageButton backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_compose);
        backButton = findViewById(R.id.back_button);

        taskTitleField = findViewById(R.id.compose_task_field);
        subTaskTitleField = findViewById(R.id.compose_sub_task_field);

        Button composeButton = findViewById(R.id.task_compose_button);
        composeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String task_title = taskTitleField.getText().toString();
                String subtasks = subTaskTitleField.getText().toString();

                createNewTask(task_title, ParseUser.getCurrentUser(), subtasks);
                finish();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void createNewTask(String title, ParseUser user, String subtasks) {
        Task task = new Task();
        task.setUser(user);
        task.setTitle(title);
        task.setIsDone(false);
        task.setDeadline(new Date());
        task.setSubTasks(subtasks);

        task.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e != null) {
                    Log.e(TAG, "Error attempting to create new task", e);
                    Toast.makeText(getApplicationContext(), "Sorry, problem saving task", Toast.LENGTH_SHORT).show();
                }
                Log.i(TAG, "New Task was Created");
                taskTitleField.setText("");
            }
        });
    }
}