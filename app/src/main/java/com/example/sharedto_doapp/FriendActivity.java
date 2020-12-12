package com.example.sharedto_doapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.example.sharedto_doapp.models.Task;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FriendActivity extends AppCompatActivity {

    public static final String TAG = "FriendActivity";
    public TaskAdapter taskAdapter;
    public List<Task> friendTasks;
    public ImageButton backButton;

    SwipeRefreshLayout swipeRefresh;
    ParseUser friend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);

        Log.i(TAG, "Friend tasks created");

        backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        RecyclerView taskListRV = findViewById(R.id.friend_tasks_rv);

        friendTasks = new ArrayList<>();
        taskAdapter = new TaskAdapter((ArrayList<Task>) friendTasks, this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        taskListRV.setLayoutManager(linearLayoutManager);
        taskListRV.setAdapter(taskAdapter);


        swipeRefresh = findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i(TAG, "Fetching new data...");
                queryTasks();
                swipeRefresh.setRefreshing(false);
            }
        });

//        findUsers("adetunjiakinola");
        queryTasks();
    }


    public void findUsers(String username) {

        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereEqualTo("username", username);
        query.findInBackground(new FindCallback<ParseUser>() {
            public void done(List<ParseUser> users, ParseException e) {
                if (e == null) {
                    Log.i(TAG, String.valueOf(users));
                    friend = users.get(0);
                } else {
                    Log.e(TAG, "Problem retrieving user");
                }
            }
        });
    }

    public void queryTasks() {
        Log.i(TAG, "Started");
        ParseQuery<Task> query = ParseQuery.getQuery(Task.class);
        query.whereEqualTo(Task.KEY_IS_DONE, false);
        query.setLimit(20);
        query.addDescendingOrder(Task.KEY_DEADLINE);
        query.whereEqualTo("createdBy", "adetunjiakinola");
        query.findInBackground(new FindCallback<Task>() {
            @Override
            public void done(List<Task> tasks, ParseException e) {
                if (e != null){
                    Log.e(TAG, "Issue retrieving tasks", e);
                    return;
                }
                for (Task task : tasks) {
                    try {
                        Log.i(TAG, "Task: " + task.getTitle() + task.getDeadline() + task.getSubtasks().toString());
                    } catch (JSONException ex) {
                        ex.printStackTrace();
                    }
                }
                friendTasks.clear();
                friendTasks.addAll(tasks);
                taskAdapter.notifyDataSetChanged();
            }
        });
    }

}

