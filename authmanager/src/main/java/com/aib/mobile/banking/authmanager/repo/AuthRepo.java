package com.aib.mobile.banking.authmanager.repo;

import com.aib.mobile.banking.authmanager.model.Auth;
import com.aib.mobile.banking.authmanager.model.AuthResource;
import com.aib.mobile.banking.authmanager.remote.model.AuthRemote;

import java.io.IOException;

import retrofit2.Response;

public interface AuthRepo {
    Response<AuthRemote> requestAuth(String userName, String password) throws IOException;

    AuthResource refreshAuth(String refreshAuth) throws IOException;

    boolean isLoggedIn();

    Auth getAuth();

    boolean setAuth(Auth auth);

    boolean invalidate();

    void revokeTokens(String token,
                      String type) throws IOException;
}
