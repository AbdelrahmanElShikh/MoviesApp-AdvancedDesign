package com.aib.mobile.banking.cryptomanager.di;

import android.content.Context;


import com.aib.mobile.banking.cryptomanager.crypto.contract.Cryptography;
import com.aib.mobile.banking.cryptomanager.crypto.di.CryptographyRepoModule;

import dagger.BindsInstance;
import dagger.Component;

@CryptoScope
@Component(modules = {CryptographyRepoModule.class})
public interface CryptoComponent {

    Cryptography exposeCrypto();

    @Component.Builder
    interface Builder {

        @BindsInstance
        CryptoComponent.Builder appContext(Context appContext);

        CryptoComponent build();
    }
}
