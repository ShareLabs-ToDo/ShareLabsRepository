package com.example.sharedto_doapp.models;

import android.util.Log;

import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import java.util.Date;


@ParseClassName("Task")
public class Task extends ParseObject{

    public static final String TAG = "TaskClass";

    public static final String KEY_USER = "user";
    public static final String KEY_TITLE = "title";
    public static final String KEY_IS_DONE =  "isDone";
    public static final String KEY_DEADLINE = "deadline";

    public ParseUser getUser() { return getParseUser(KEY_USER); }
    public String getTitle() { return getString(KEY_TITLE); }
    public Boolean getIsDone() { return getBoolean(KEY_IS_DONE); }
    public Date getDeadline() { return getDate(KEY_DEADLINE); }

    public void setUser(ParseUser user) { put(KEY_USER, user); }
    public void setTitle(String title) { put(KEY_TITLE, title); }
    public void setIsDone(Boolean isDone) { put(KEY_IS_DONE, isDone); }
    public void setDeadline(Date deadline) { put(KEY_DEADLINE, deadline); }

    public void createTask(String user, String title, Boolean isDone, Date deadline) {
        ParseObject entity = new ParseObject("Task");

        entity.put(KEY_USER, ParseUser.getCurrentUser());
        entity.put(KEY_TITLE, title);
        entity.put(KEY_IS_DONE, isDone);
        entity.put(KEY_DEADLINE, deadline);

        entity.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                Log.e(TAG, "Problem saving task");
            }
        });
    }
}
