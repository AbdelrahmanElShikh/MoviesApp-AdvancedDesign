package com.aib.mobile.banking.authmanager.authenticator;

import com.aib.mobile.banking.authmanager.repo.AuthRepo;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static com.aib.mobile.banking.authmanager.remote.client.SettingsAPI.AUTH_TYPE;

public class APIClientAuthInterceptor implements Interceptor {

    private AuthRepo authRepo;

    @Inject
    public APIClientAuthInterceptor(AuthRepo authRepo) {
        this.authRepo = authRepo;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (authRepo.getAuth() != null){
            String authorization = AUTH_TYPE + " " + authRepo.getAuth().getAccessToken();
            request = request.newBuilder()
                .addHeader("Authorization",authorization)
                .build();
        }
        return chain.proceed(request);
    }
}
