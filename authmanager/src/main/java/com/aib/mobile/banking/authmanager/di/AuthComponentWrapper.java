package com.aib.mobile.banking.authmanager.di;

import android.content.Context;
import android.os.Bundle;

import com.aib.mobile.banking.authmanager.repo.AuthRepo;
import com.aib.mobile.banking.cryptomanager.di.CryptoComponentWrapper;

import javax.inject.Inject;

public class AuthComponentWrapper {
    private static AuthComponentWrapper INSTANCE;

    private final AuthComponent authComponent;

    private AuthComponentWrapper(Context context) {
        authComponent = DaggerAuthComponent.builder()
                .cryptoComponent(CryptoComponentWrapper.getInstance(context).getCryptoComponent())
                .appContext(context).build();
    }


    public static AuthComponentWrapper getInstance(Context context) {
        if (INSTANCE == null){
            INSTANCE = new AuthComponentWrapper(context);
        }
        return INSTANCE;
    }

    public AuthComponent getAuthComponent() {
        return authComponent;
    }
}
