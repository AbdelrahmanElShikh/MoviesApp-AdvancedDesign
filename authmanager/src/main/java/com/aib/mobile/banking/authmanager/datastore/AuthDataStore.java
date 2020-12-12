package com.aib.mobile.banking.authmanager.datastore;

import com.aib.mobile.banking.authmanager.model.Auth;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableEntryException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public interface AuthDataStore {
    @Nullable
    Auth getAuth();
    boolean setAuth(@NonNull Auth authRemote);
    boolean invalidate();
}
