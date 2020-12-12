package com.aib.mobile.banking.authmanager.remote.client.di;

import com.aib.mobile.banking.authmanager.di.AuthScope;
import com.aib.mobile.banking.authmanager.remote.client.ApiServices;
import com.aib.mobile.banking.authmanager.remote.client.SettingsAPI;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class ClientModule {

    @AuthScope
    @Provides
    public static OkHttpClient provideOkHttp(){
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.readTimeout(SettingsAPI.TIME_OUT, TimeUnit.MILLISECONDS);
        httpClient.writeTimeout(SettingsAPI.TIME_OUT, TimeUnit.MILLISECONDS);

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        httpClient.addInterceptor(logging);

        return httpClient.build();
    }

    @AuthScope
    @Provides
    public static Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(SettingsAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    public static ApiServices createSettingsAPI(Retrofit retrofit){
        return retrofit.create(ApiServices.class);
    }

}
