package com.aib.mobile.banking.authmanager.mapper;

import com.aib.mobile.banking.authmanager.model.Auth;
import com.aib.mobile.banking.authmanager.remote.model.AuthRemote;

import androidx.annotation.NonNull;

public class AuthMapper {
    public static Auth map(@NonNull AuthRemote authRemote){
        return new Auth(authRemote.getAccessToken(), authRemote.getRefreshToken(), authRemote.getTokenType());
    }
}
