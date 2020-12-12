package com.aib.mobile.banking.authmanager.mapper;

import com.aib.mobile.banking.authmanager.model.Auth;
import com.aib.mobile.banking.authmanager.model.AuthResource;
import com.aib.mobile.banking.authmanager.remote.model.AuthRemote;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Response;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class AuthResourceMapperTest {

    @Mock
    Response<AuthRemote> response;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void map_success_response() {
        when(response.code()).thenReturn(200);
        when(response.body()).thenReturn(new AuthRemote("", "", null, null, null));
        AuthResource authResource = AuthResourceMapper.map(response);
        assertEquals(authResource.status, AuthResource.Status.SUCCESS);
    }

    @Test
    public void map_error_response() {
        when(response.code()).thenReturn(401);
        AuthResource authResource = AuthResourceMapper.map(response);
        assertEquals(authResource.status, AuthResource.Status.ERROR);
    }

    @Test
    public void map_success_response_with_null_tokens() {
        when(response.code()).thenReturn(200);
        when(response.body()).thenReturn(new AuthRemote(null, null, null, null, null));
        AuthResource authResource = AuthResourceMapper.map(response);
        assertEquals(authResource.status, AuthResource.Status.ERROR);
    }


}