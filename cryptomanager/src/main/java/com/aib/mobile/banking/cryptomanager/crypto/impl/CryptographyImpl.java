package com.aib.mobile.banking.cryptomanager.crypto.impl;

import android.os.Build;
import android.util.Base64;
import android.util.Log;

import com.aib.mobile.banking.cryptomanager.crypto.contract.Cryptography;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.GCMParameterSpec;
import javax.inject.Inject;

public class CryptographyImpl implements Cryptography {

    static final String TAG = "CryptographyImpl";
    private static final String ANDROID_KEY_STORE_NAME = "AndroidKeyStore";
    private static final String AES_MODE_M_OR_GREATER = "AES/GCM/NoPadding";
    private static final String KEY_ALIAS = "YOUR-KeyAliasForEncryption";
    // TODO update these bytes to be random for IV of encryption
    private static final byte[] FIXED_IV = new byte[]{55, 54, 53, 52, 51, 50,
            49, 48, 47,
            46, 45, 44};
    private static final String CHARSET_NAME = "UTF-8";
    private Key secretKey;

    @Inject
    public CryptographyImpl(Key secretKey) {
        this.secretKey = secretKey;
    }

    @Override
    public String encryptText(String plainText) {
        Log.d(TAG, "plainDate: " + plainText);
        try {

            if (plainText == null) {
                throw new IllegalArgumentException("Data to be decrypted must be non null");
            }

            Cipher cipher = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                cipher = Cipher.getInstance(AES_MODE_M_OR_GREATER);
                cipher.init(Cipher.ENCRYPT_MODE,secretKey,
                        new GCMParameterSpec(128, FIXED_IV));
            }

            byte[] encodedBytes = cipher.doFinal(plainText.getBytes(CHARSET_NAME));
            String encryptedBase64Encoded = Base64.encodeToString(encodedBytes, Base64.DEFAULT);
            Log.d(TAG, "after encryption: "+ encryptedBase64Encoded);
            return encryptedBase64Encoded;

        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }  catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String decryptText(String encryptedText) {
        Log.d(TAG, "decryptData: " + encryptedText);
        try {

            if (encryptedText == null) {
                throw new IllegalArgumentException("Data to be decrypted must be non null");
            }

            byte[] encryptedDecodedData = Base64.decode(encryptedText, Base64.DEFAULT);

            Cipher c = null;
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    c = Cipher.getInstance(AES_MODE_M_OR_GREATER);
                    c.init(Cipher.DECRYPT_MODE, secretKey, new GCMParameterSpec(128, FIXED_IV));
                }
            } catch (InvalidKeyException e) {
                // Since the keys can become bad (perhaps because of lock screen change)
                // drop keys in this case.
                throw e;
            }

            byte[] decodedBytes = c.doFinal(encryptedDecodedData);
            Log.d(TAG, "decryptData: result " + new String(decodedBytes, CHARSET_NAME));
            return new String(decodedBytes, CHARSET_NAME);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }

        return null;
    }


    }

