package com.example.sharedto_doapp.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import com.example.sharedto_doapp.R;
import com.example.sharedto_doapp.TaskAdapter;
import com.example.sharedto_doapp.TaskComposeActivity;
import com.example.sharedto_doapp.models.Task;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;


import java.util.ArrayList;
import java.util.List;

public class YourTasksFragment extends Fragment {
    
    public static final String TAG = "TaskFragment";
    public TaskAdapter taskAdapter;
    SwipeRefreshLayout swipeRefresh;
    public List<Task> userTasks;

    public YourTasksFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_your_tasks, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView taskListRV = view.findViewById(R.id.task_list_rv);

        ImageButton createButton = view.findViewById(R.id.task_create_button);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), TaskComposeActivity.class);
                startActivity(intent);
            }
        });
        
        userTasks = new ArrayList<>();
        taskAdapter = new TaskAdapter((ArrayList<Task>) userTasks, getContext());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        taskListRV.setLayoutManager(linearLayoutManager);
        taskListRV.setAdapter(taskAdapter);

        swipeRefresh = view.findViewById(R.id.swipe_refresh);
        swipeRefresh.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i(TAG, "Fetching new data...");
                queryTasks();
            }
        });

        queryTasks();

    }

    public void queryTasks() {
        ParseQuery<Task> query = ParseQuery.getQuery(Task.class);
        query.include(Task.KEY_USER);
        query.setLimit(20);
        query.addDescendingOrder(Task.KEY_DEADLINE);
        query.whereEqualTo(Task.KEY_USER, ParseUser.getCurrentUser());
        query.findInBackground(new FindCallback<Task>() {
            @Override
            public void done(List<Task> tasks, ParseException e) {
                if (e != null){
                    Log.e(TAG, "Issue retrieving tasks", e);
                    return;
                }
                for (Task task : tasks) {
                    Log.i(TAG, "Task: " + task.getTitle() + task.getDeadline());
                }
                userTasks.clear();
                userTasks.addAll(tasks);
                taskAdapter.notifyDataSetChanged();
            }
        });
    }
}