package com.aib.mobile.banking.authmanager.mapper;

import com.aib.mobile.banking.authmanager.model.AuthResource;
import com.aib.mobile.banking.authmanager.remote.model.AuthRemote;

import retrofit2.Response;

public class AuthResourceMapper {
   public static AuthResource map(Response<AuthRemote> remoteResponse){
       if (remoteResponse.code() == 200){
           if (remoteResponse.body() != null && remoteResponse.body().getAccessToken() != null && remoteResponse.body().getRefreshToken() !=null){
               return AuthResource.success(AuthMapper.map(remoteResponse.body()));
           }
       }
       return AuthResource.error(remoteResponse.message());
   }
}
