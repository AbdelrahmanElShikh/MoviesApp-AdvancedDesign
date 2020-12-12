package com.aib.mobile.banking.cryptomanager.crypto.di;

import com.aib.mobile.banking.cryptomanager.crypto.contract.Cryptography;
import com.aib.mobile.banking.cryptomanager.crypto.impl.CryptographyImpl;

import dagger.Binds;
import dagger.Module;

@Module(includes = CryptoModule.class)
public interface CryptographyRepoModule {
    @Binds
    Cryptography bindCryptography(CryptographyImpl cryptography);
}
