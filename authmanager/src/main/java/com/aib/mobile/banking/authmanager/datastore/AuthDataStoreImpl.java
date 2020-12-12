package com.aib.mobile.banking.authmanager.datastore;

import android.content.SharedPreferences;

import com.aib.mobile.banking.authmanager.model.Auth;
import com.aib.mobile.banking.cryptomanager.crypto.contract.Cryptography;

import javax.inject.Inject;

import androidx.annotation.NonNull;

public class AuthDataStoreImpl implements AuthDataStore {

    private static final String TOKEN_KEY = "tokenKey";
    private static final String REFRESH_TOKEN_KEY = "refreshTokenKey";
    private static final String TOKEN_TYPE_KEY = "tokenTypeKey";

    private SharedPreferences sharedPreferences;
    private Cryptography cryptography;

    @Inject
    public AuthDataStoreImpl(SharedPreferences sharedPreferences, Cryptography cryptography) {
        this.sharedPreferences = sharedPreferences;
        this.cryptography = cryptography;
    }

    @Override
    public Auth getAuth(){
        String token = sharedPreferences.getString(TOKEN_KEY, null);

        if (token == null)
            return null;

        String refreshToken = sharedPreferences.getString(REFRESH_TOKEN_KEY, null);

        String tokenType = sharedPreferences.getString(TOKEN_TYPE_KEY, null);

        return new Auth(cryptography.decryptText(token),cryptography.decryptText(refreshToken), tokenType);
    }

    @Override
    public boolean setAuth(@NonNull Auth auth) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TOKEN_KEY, cryptography.encryptText(auth.getAccessToken()));
        editor.putString(REFRESH_TOKEN_KEY,cryptography.encryptText(auth.getRefreshToken()));
        editor.putString(TOKEN_TYPE_KEY, auth.getTokenType());
        return editor.commit();
    }

    @Override
    public boolean invalidate() {
        return sharedPreferences.edit().clear().commit();
    }


}
