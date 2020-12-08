package com.example.sharedto_doapp.models;

import android.util.Log;

import com.parse.GetCallback;
import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.parceler.Parcel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ParseClassName("Task")
@Parcel(analyze = Task.class)

public class Task extends ParseObject {

    public Task(){}

    public static final String TAG = "TaskClass";

    public static final String KEY_USER = "taskOwner";
    public static final String KEY_TITLE = "title";
    public static final String KEY_IS_DONE =  "isDone";
    public static final String KEY_DEADLINE = "deadline";
    public static final String KEY_SUBTASKS = "subtasks";

    public String getTitle() { return getString(KEY_TITLE); }
    public Boolean getIsDone() { return getBoolean(KEY_IS_DONE); }
    public String getDeadline() {
        Date date = getDate(KEY_DEADLINE);
        String pattern = "dd/MM/yyyy";

        SimpleDateFormat df = new SimpleDateFormat(pattern);
        String dateAsString = df.format(date);

        return dateAsString;
    }

    public List<String> getSubtasks() throws JSONException {

        JSONArray subtasksArray = getJSONArray(KEY_SUBTASKS);
        List<String> subtasks = new ArrayList<>();

        if (subtasksArray == null) {
            subtasks.add("empty,false");
            return subtasks;
        }

        for(int i = 0; i < subtasksArray.length(); i++) {
            subtasks.add((String) subtasksArray.get(i));
        }

        return  subtasks;
    }

    public void setUser(ParseUser user) { put(KEY_USER, user); }
    public void setTitle(String title) { put(KEY_TITLE, title); }
    public void setIsDone(Boolean isDone) { put(KEY_IS_DONE, isDone); }
    public void setDeadline(Date deadline) { put(KEY_DEADLINE, deadline); }

    public void setSubTasks(String subtaskString) {
        String[] subtaskTitles = subtaskString.split("\\r?\\n");
        List<String> subtasks = new ArrayList<>();

        for(String title : subtaskTitles) {
            String subtask = title + ",false";
            subtasks.add(subtask);
            Log.i(TAG, title);
        }

        put(KEY_SUBTASKS, subtasks);
    }

    public void updateIsDone(String objectId, final Boolean newState){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Task");

        query.getInBackground(objectId, new GetCallback<ParseObject>() {
            public void done(ParseObject entity, ParseException e) {
                if (e == null) {
                    entity.put(KEY_IS_DONE, newState);

                    entity.saveInBackground();
                }
            }
        });
    }

//    public void updateSubtasks(String objectId, final List<Subtask> subtasks) {
//        ParseQuery<ParseObject> query = ParseQuery.getQuery("Task");
//
//        query.getInBackground(objectId, new GetCallback<ParseObject>() {
//            public void done(ParseObject entity, ParseException e) {
//                if (e == null) {
//                    entity.addAll(KEY_CHECKED_SUBTASKS, subtasks);
//
//                    entity.saveInBackground();
//                }
//            }
//        });
//    }
}
