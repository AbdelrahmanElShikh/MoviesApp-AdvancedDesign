package com.aib.mobile.banking.authmanager.di;

import android.content.Context;

import com.aib.mobile.banking.authmanager.datastore.di.DataStoreModule;
import com.aib.mobile.banking.authmanager.remote.client.di.ClientModule;
import com.aib.mobile.banking.authmanager.repo.AuthRepo;
import com.aib.mobile.banking.authmanager.repo.di.RepoModule;
import com.aib.mobile.banking.cryptomanager.crypto.contract.Cryptography;
import com.aib.mobile.banking.cryptomanager.di.CryptoComponent;

import dagger.BindsInstance;
import dagger.Component;

@Component(modules = {RepoModule.class}, dependencies = CryptoComponent.class)
@AuthScope
public interface AuthComponent {

    AuthRepo authRepo();

    Cryptography cryptography();

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder appContext(Context appContext);


        Builder cryptoComponent(CryptoComponent cryptoComponent);


        AuthComponent build();
    }
}
