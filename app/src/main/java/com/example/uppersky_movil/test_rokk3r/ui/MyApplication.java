package com.example.uppersky_movil.test_rokk3r.ui;

import android.app.Application;
import android.content.Context;

import com.example.uppersky_movil.test_rokk3r.data.realm.RealmManager;


/**
 * Created by Dorian on 26/08/2016.
 */
public class MyApplication extends Application {
    public static final String TAG = MyApplication.class.getSimpleName();


    @Override
    public void onCreate() {
        super.onCreate();

        Context context = this.getApplicationContext();
        RealmManager.initRealmConfiguration(context);
    }


}