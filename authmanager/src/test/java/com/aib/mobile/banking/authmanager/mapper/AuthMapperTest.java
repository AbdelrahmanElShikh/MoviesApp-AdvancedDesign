package com.aib.mobile.banking.authmanager.mapper;

import com.aib.mobile.banking.authmanager.model.Auth;
import com.aib.mobile.banking.authmanager.remote.model.AuthRemote;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class AuthMapperTest {

    private String accessToken = "23jddffdkkdjjjed#dkkssnnf";
    private String refreshToken = "asdkmlkklmsad23jddffdkkdjjjedasdkmlkklmsad23jddffdkkdjjjed";
    private String scope = "scope";
    private String tokenType = "type";
    private Integer expiresIn = 20;


    @Test
    public void map_with_nonnull_values_success() {
        AuthRemote authRemote = createAuthRemote();

        Auth auth = AuthMapper.map(authRemote);

        assertEquals(auth.getAccessToken(), accessToken);
        assertEquals(auth.getRefreshToken(), refreshToken);
    }

    @Test
    public void map_with_null_values_success(){
        AuthRemote authRemote = new AuthRemote(null, null, null, null, null);
        Auth auth = AuthMapper.map(authRemote);

        assertNull(auth.getAccessToken());
        assertNull(auth.getRefreshToken());
    }

    @Test(expected = NullPointerException.class)
    public void map_with_null_response_through_NullPointerException(){
        AuthMapper.map(null);
    }

    private AuthRemote createAuthRemote(){
        return new AuthRemote(accessToken, refreshToken, scope, tokenType, expiresIn);
    }
}