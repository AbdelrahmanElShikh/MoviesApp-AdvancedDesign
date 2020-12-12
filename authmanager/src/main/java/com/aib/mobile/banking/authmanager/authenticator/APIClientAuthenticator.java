package com.aib.mobile.banking.authmanager.authenticator;

import com.aib.mobile.banking.authmanager.model.Auth;
import com.aib.mobile.banking.authmanager.model.AuthResource;
import com.aib.mobile.banking.authmanager.repo.AuthRepo;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.inject.Inject;

import androidx.annotation.Nullable;
import okhttp3.Authenticator;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.Route;

import static com.aib.mobile.banking.authmanager.remote.client.SettingsAPI.AUTH_TYPE;

public class APIClientAuthenticator implements Authenticator {

    private AuthRepo authRepo;

    @Inject
    public APIClientAuthenticator(AuthRepo authRepo) {
        this.authRepo = authRepo;
    }

    @Nullable
    @Override
    public Request authenticate(@Nullable Route route, Response response) throws IOException {
        if (response.code() == 401) {
            Auth oldAuth = null;
            oldAuth = authRepo.getAuth();
            if (oldAuth != null) {
                AuthResource authResource = authRepo.refreshAuth(oldAuth.getRefreshToken());
                if (authResource.status == AuthResource.Status.SUCCESS) {
                    String authorization = AUTH_TYPE + " " + authResource.data.getAccessToken();
                    authRepo.setAuth(authResource.data);
                    return response.request().newBuilder().header("Authorization", authorization).build();
                }
            }

        }
        return null;

    }
}
