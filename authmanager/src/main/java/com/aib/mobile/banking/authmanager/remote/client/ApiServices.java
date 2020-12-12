package com.aib.mobile.banking.authmanager.remote.client;

import androidx.annotation.Nullable;

import com.aib.mobile.banking.authmanager.remote.model.AuthRemote;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiServices {

    @FormUrlEncoded
    @POST("token")
    Call<AuthRemote> requestAuth(@Field("username") String userName,
                                 @Field("password") String password,
                                 @Field("grant_type") String grantType,
                                 @Field("client_id") String clientId);

    @FormUrlEncoded
    @POST("token")
    Call<AuthRemote> refreshAuth(@Field("grant_type") String grantType,
                                 @Field("client_id") String clientId,
                                 @Field("refresh_token") String refreshToken);

    /**
     * @param token token value that will be revoked either token or refresh token
     * @param type  optional optimization by specifying token type either
     *              access_token or refresh_token
     */
    @FormUrlEncoded
    @POST("revoke")
    Call<ResponseBody> revokeToken(@Field("token") String token,
                                   @Field("client_id") String clientId,
                                   @Field("token_type_hint") @Nullable String type);
}
