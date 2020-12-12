package com.aib.mobile.banking.authmanager.datastore.di;

import android.content.Context;
import android.content.SharedPreferences;

import com.aib.mobile.banking.authmanager.datastore.AuthDataStore;
import com.aib.mobile.banking.authmanager.datastore.AuthDataStoreImpl;
import com.aib.mobile.banking.authmanager.di.AuthScope;

import dagger.Module;
import dagger.Provides;

@Module
public class DataStoreModule {

    private final String AUTH_DATA_STORE_NAME = "authDataStore";

    @Provides
    public AuthDataStore bindsAuthDataStore(AuthDataStoreImpl authDataStore) {
        return authDataStore;
    }

    @AuthScope
    @Provides
    public SharedPreferences provideSharedPreference(Context context) {
        return context.getSharedPreferences(AUTH_DATA_STORE_NAME, Context.MODE_PRIVATE);
    }

}
