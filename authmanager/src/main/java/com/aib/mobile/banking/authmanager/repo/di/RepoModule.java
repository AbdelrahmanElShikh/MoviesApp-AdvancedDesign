package com.aib.mobile.banking.authmanager.repo.di;


import com.aib.mobile.banking.authmanager.datastore.di.DataStoreModule;
import com.aib.mobile.banking.authmanager.remote.client.di.ClientModule;
import com.aib.mobile.banking.authmanager.repo.AuthRepo;
import com.aib.mobile.banking.authmanager.repo.AuthRepoImpl;
import com.aib.mobile.banking.cryptomanager.crypto.di.CryptoModule;

import dagger.Binds;
import dagger.Module;

@Module(includes = {CryptoModule.class, ClientModule.class, DataStoreModule.class})
public interface RepoModule {
    @Binds
    AuthRepo bindAuthRepo(AuthRepoImpl authRepo);
}
