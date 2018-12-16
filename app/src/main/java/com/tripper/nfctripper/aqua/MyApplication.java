package com.tripper.nfctripper.aqua;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.tripper.nfctripper.aqua.model.userModel.User;
import com.uphyca.stetho_realm.RealmInspectorModulesProvider;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application {


    Realm realm;
    User user;

    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize Realm. Should only be done once when the application starts.
        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name(Realm.DEFAULT_REALM_NAME)
                .schemaVersion(0)
                //.deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());

        realm = Realm.getDefaultInstance();
        user = realm.where(User.class).findFirst();



        // In this example, no default configuration is set,
        // so by default, `RealmConfiguration.Builder().build()` is used.
    }


    public User getUser() {
        return user;
    }

}
