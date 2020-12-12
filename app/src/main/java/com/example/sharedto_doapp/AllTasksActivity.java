package com.example.sharedto_doapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class AllTasksActivity extends AppCompatActivity {

    ImageView profileImageButton;
    public static final String TAG = "AllTasksActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tasks);

        profileImageButton = findViewById(R.id.friend_image);
        profileImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FriendActivity.class);
                startActivity(intent);
            }
        });
    }
}