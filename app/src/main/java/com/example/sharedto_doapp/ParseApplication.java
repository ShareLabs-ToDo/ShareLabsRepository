package com.example.sharedto_doapp;

import android.app.Application;

import com.parse.Parse;
<<<<<<< HEAD
=======
import com.parse.ParseObject;
>>>>>>> a0b8624cddda8edb45380b8ae1c22ab96c4133d1

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

<<<<<<< HEAD
=======
        ParseObject.registerSubclass(Post.class);

>>>>>>> a0b8624cddda8edb45380b8ae1c22ab96c4133d1
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("UNyA2Y41Fx933IbCakVeZqlE9ikqR0hUKGCbaOcR")
                .clientKey("FZA8XxKS8oQS40aYeuAPHTpqFjU48wiDNMGBHyXK")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
