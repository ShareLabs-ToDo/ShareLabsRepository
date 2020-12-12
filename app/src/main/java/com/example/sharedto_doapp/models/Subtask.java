package com.example.sharedto_doapp.models;

import com.parse.ParseClassName;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

@ParseClassName("Subtask")

public class Subtask {

    String title;
    Boolean isDone;

    public Subtask(String title, Boolean isDone) {
        this.title = title;
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "title='" + title + '\'' +
                ", isDone=" + isDone +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getDone() {
        return isDone;
    }

    public void setDone(Boolean done) {
        isDone = done;
    }

    public static List<Subtask> fromJSONSubtaskArray(JSONArray subtasks) throws JSONException {
        List<Subtask> subtaskList = new ArrayList<>();
        for(int i = 0; i < subtasks.length(); i++) {
            String title = (String) subtasks.getJSONObject(i).get("title");
            Boolean isDone = (Boolean) subtasks.getJSONObject(i).get("isDone");
            Subtask subtask = new Subtask(title, isDone);
            subtaskList.add(subtask);
        }

        return subtaskList;
    }
}
