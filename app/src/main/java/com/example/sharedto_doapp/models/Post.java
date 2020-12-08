package com.example.sharedto_doapp.models;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;

import java.util.Date;

@ParseClassName("Post")
public class Post extends ParseObject {

    //getter and setter

    public static final String KEY_DESCRIPTION = "message";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_USER = "user";
    public static final String KEY_CREATED_KEY = "createdAt";
    public static final String KEY_LIKES_COUNT = "likesCount";

    public String getDescription() {
        return getString(KEY_DESCRIPTION);
    }

    public void setDescription(String description) {
        put(KEY_DESCRIPTION, description);
    }

    public ParseFile getImage() {
        return getParseFile(KEY_IMAGE);
    }

    public void setImage(ParseFile parseFile) {
        put(KEY_IMAGE, parseFile);
    }

    public ParseUser getUser() {
        return getParseUser(KEY_USER);
    }

    public void setUser(ParseUser user) {
        put(KEY_USER, user);
    }

    public Date getCreatedAt() {
        return getDate(KEY_CREATED_KEY);
    }

    public void setCreatedAt(Date createdKey) {
        put(KEY_CREATED_KEY, createdKey);
    }

    public int getLikesCount() {
        return getInt(KEY_LIKES_COUNT);
    }

    public void setLikesCount(int likesCount) {
        put(KEY_CREATED_KEY, likesCount);
    }
}
