package com.example.uppersky_movil.test_rokk3r.data.realm;

import android.content.Context;

import com.example.uppersky_movil.test_rokk3r.data.realm.controller.ProductManager;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Dorian on 26/08/2016.
 */
public class RealmManager {
    private static final String TAG = RealmManager.class.getSimpleName();
    public static final int VERSION_DB = 2;

    private Realm mRealm;
    private Context context;

    public static void initRealmConfiguration(Context context) {
        RealmConfiguration realmConfiguration =
                new RealmConfiguration
                        .Builder(context)
                        .name("rokk3r")
                        .schemaVersion(VERSION_DB)
                        .deleteRealmIfMigrationNeeded()
                        .build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }

    public RealmManager() {
        mRealm = Realm.getDefaultInstance();
    }

    public void closeRealm() {
        mRealm.close();
    }

    public void cleanRealm(){
        ProductManager productManager = new ProductManager();
        productManager.removeAll();
    }
}
