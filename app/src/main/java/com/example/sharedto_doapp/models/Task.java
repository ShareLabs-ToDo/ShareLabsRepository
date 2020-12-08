package com.example.sharedto_doapp.models;

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
    public static final String KEY_CHECKED_SUBTASKS = "subTasks";

    public String getTitle() { return getString(KEY_TITLE); }
    public Boolean getIsDone() { return getBoolean(KEY_IS_DONE); }
    public String getDeadline() {
        Date date = getDate(KEY_DEADLINE);
        String pattern = "dd/MM/yyyy";

        SimpleDateFormat df = new SimpleDateFormat(pattern);
        String dateAsString = df.format(date);

        return dateAsString;
    }

    public List<Subtask> getSubtasks() throws JSONException {

        JSONArray subtasksArray = getJSONArray(KEY_SUBTASKS);

        return Subtask.fromJSONSubtaskArray(subtasksArray);
    }

    public void setUser(ParseUser user) { put(KEY_USER, user); }
    public void setTitle(String title) { put(KEY_TITLE, title); }
    public void setIsDone(Boolean isDone) { put(KEY_IS_DONE, isDone); }
    public void setDeadline(Date deadline) { put(KEY_DEADLINE, deadline); }

    public void setSubTasks(String subtasks) { put(KEY_SUBTASKS, subtasks);}
    public void setCheckedSubTasks(List<Subtask> subtasks) {put(KEY_CHECKED_SUBTASKS, subtasks); }

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

    public void updateSubtasks(String objectId, final List<Subtask> subtasks) {
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Task");

        query.getInBackground(objectId, new GetCallback<ParseObject>() {
            public void done(ParseObject entity, ParseException e) {
                if (e == null) {
                    entity.addAll(KEY_CHECKED_SUBTASKS, subtasks);

                    entity.saveInBackground();
                }
            }
        });
    }
}
