package com.example.sharedto_doapp;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("UNyA2Y41Fx933IbCakVeZqlE9ikqR0hUKGCbaOcR")
                .clientKey("FZA8XxKS8oQS40aYeuAPHTpqFjU48wiDNMGBHyXK")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
