package com.aib.mobile.banking.authmanager.repo;

import com.aib.mobile.banking.authmanager.datastore.AuthDataStore;
import com.aib.mobile.banking.authmanager.mapper.AuthResourceMapper;
import com.aib.mobile.banking.authmanager.model.Auth;
import com.aib.mobile.banking.authmanager.model.AuthResource;
import com.aib.mobile.banking.authmanager.remote.client.ApiServices;
import com.aib.mobile.banking.authmanager.remote.client.SettingsAPI;
import com.aib.mobile.banking.authmanager.remote.model.AuthRemote;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.Response;

public class AuthRepoImpl implements AuthRepo {

    private ApiServices apiServices;
    private AuthDataStore dataStore;

    @Inject
    public AuthRepoImpl(ApiServices apiServices, AuthDataStore dataStore) {
        this.apiServices = apiServices;
        this.dataStore = dataStore;
    }

    @Override
    public Response<AuthRemote> requestAuth(String userName, String password) throws IOException {
        return apiServices.requestAuth(userName, password, SettingsAPI.GrantTypes.password.name(), SettingsAPI.clientId).execute();
    }

    @Override
    public AuthResource refreshAuth(String refreshAuth) throws IOException {
        return AuthResourceMapper.map(apiServices.refreshAuth(SettingsAPI.GrantTypes.refresh_token.name(), SettingsAPI.clientId, refreshAuth).execute());
    }

    @Override
    public boolean isLoggedIn() {
        return dataStore.getAuth() != null;
    }

    @Override
    public Auth getAuth() {
        return dataStore.getAuth();
    }

    @Override
    public boolean setAuth(Auth auth) {
        return dataStore.setAuth(auth);
    }

    @Override
    public boolean invalidate() {
        return dataStore.invalidate();
    }

    @Override
    public void revokeTokens(String token, String type) throws IOException {
        apiServices.revokeToken(token, SettingsAPI.clientId, type).execute();
    }
}
