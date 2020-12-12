package com.aib.mobile.banking.cryptomanager.crypto.di;

import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;

import com.aib.mobile.banking.cryptomanager.crypto.contract.Cryptography;
import com.aib.mobile.banking.cryptomanager.crypto.impl.CryptographyImpl;
import com.aib.mobile.banking.cryptomanager.di.CryptoScope;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.Key;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.KeyGenerator;

import dagger.Module;
import dagger.Provides;

@Module
public class CryptoModule {
    private static final String ANDROID_KEY_STORE = "AndroidKeyStore";
    private static final String ALLIAS = "al";


    private static void initKey() {
        KeyGenerator keyGenerator = null;
        try {
            keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, ANDROID_KEY_STORE);
            keyGenerator.init(
                new KeyGenParameterSpec.Builder(ALLIAS,
                    KeyProperties.PURPOSE_ENCRYPT | KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                    // NOTE no Random IV. According to above this is less secure but acceptably so.
                    .setRandomizedEncryptionRequired(false)
                    .build());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }

        // Note according to [docs](https://developer.android.com/reference/android/security/keystore/KeyGenParameterSpec.html)
        // this generation will also add it to the keystore.
        keyGenerator.generateKey();
    }

    @Provides
    @CryptoScope
    static Key providesKey() {
        return KeyProvider.getKey();
    }

    public static class KeyProvider {
        static Key getKey() {
            KeyStore keyStore = null;
            try {
                keyStore = KeyStore.getInstance(ANDROID_KEY_STORE);
                keyStore.load(null);
                if (!keyStore.containsAlias(ALLIAS)) {
                    initKey();
                }
                Key key = keyStore.getKey(ALLIAS, null);
                return key;
            } catch (KeyStoreException e) {
                e.printStackTrace();
            } catch (CertificateException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (UnrecoverableKeyException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
