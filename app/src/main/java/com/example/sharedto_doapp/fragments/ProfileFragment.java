package com.example.sharedto_doapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.sharedto_doapp.PostsAdapter;
import com.example.sharedto_doapp.R;
import com.example.sharedto_doapp.TaskAdapter;
import com.example.sharedto_doapp.models.Post;
import com.example.sharedto_doapp.models.Task;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

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

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class ProfileFragment extends Fragment {

    public static final String TAG = "ProfileFragment";
    /*private RecyclerView rvPosts;
    protected PostsAdapter adapter;
    protected List<Post> allPosts;*/
    private RecyclerView rvPastTasks;
    protected TaskAdapter adapter;
    protected ArrayList<Task> allTasks;
    public SwipeRefreshLayout swipeContainer;
    private TextView tvNumFriends;
    private TextView tvNumTasksCompleted;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvPastTasks = view.findViewById(R.id.rvPastTasks);

        // Steps to use the recycler view:
        // 1. create the adapter
        allTasks = new ArrayList<>();
        adapter = new TaskAdapter(allTasks, getContext());

        // Lookup the swipe container view
        swipeContainer = view.findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                Log.i(TAG, "fetching new data!");

                queryTasks();
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_purple,
                android.R.color.holo_red_light);

        // Steps to use the recycler view:
        // 0. create layout for one row in the list

        // 1. create the adapter
        // 2. create the data source

        // 3. set the adapter on the recycleron view
        rvPastTasks.setAdapter(adapter);
        // 4. set the layout manager on the recycler view
        // by default the code below will provide a vertical LinLayoutMgr to the recycler view
        // which is what we want
        rvPastTasks.setLayoutManager(new LinearLayoutManager(getContext()));

        queryTasks();
    }

    /*//@Override
    protected void queryPosts() {
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.include(Post.KEY_USER);
        query.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());
        query.setLimit(20);
        query.addDescendingOrder(Post.KEY_CREATED_KEY);
        query.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "issue with getting posts", e);
                    return;
                }
                for (Post post : posts) {
                    //Log.i(TAG, "Post: " + post.getDescription() + ", username: " + post.getUser().getUsername());
                    Log.i(TAG, "Post: " + post.getCaption() + ", username: " + post.getUser().getUsername());
                }

                allPosts.addAll(posts);
                adapter.notifyDataSetChanged();
                swipeContainer.setRefreshing(false);

            }
        });

    }*/

    //@Override
    protected void queryTasks() {
        ParseQuery<Task> query = ParseQuery.getQuery(Task.class);
        query.include(Task.KEY_USER);
        query.whereEqualTo(Task.KEY_IS_DONE, true);
        query.setLimit(20);
        query.addDescendingOrder(Task.KEY_DEADLINE);
        query.findInBackground(new FindCallback<Task>() {
            @Override
            public void done(ArrayList<Task> tasks, ParseException e) {
                if (e != null) {
                    Log.e(TAG, "issue with getting tasks", e);
                    return;
                }
                for (Task task : tasks) {
                    //Log.i(TAG, "Post: " + post.getDescription() + ", username: " + post.getUser().getUsername());
                    Log.i(TAG, "Task: " + task.getTitle() + ", deadline: " + task.getDeadline() + ", done: " + task.getIsDone());
                }

                allTasks.addAll(tasks);
                adapter.notifyDataSetChanged();
                swipeContainer.setRefreshing(false);

            }
        });

    }
}


