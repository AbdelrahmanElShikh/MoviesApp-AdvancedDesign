package com.aib.mobile.banking.cryptomanager.di;

import android.content.Context;


public class CryptoComponentWrapper {
    private static CryptoComponentWrapper INSTANCE;

    private final CryptoComponent cryptoComponent;

    private CryptoComponentWrapper(Context context) {
        cryptoComponent = DaggerCryptoComponent.builder()
                .appContext(context)
                .build();
    }


    public static CryptoComponentWrapper getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new CryptoComponentWrapper(context);
        }
        return INSTANCE;
    }

    public CryptoComponent getCryptoComponent() {
        return cryptoComponent;
    }
}
