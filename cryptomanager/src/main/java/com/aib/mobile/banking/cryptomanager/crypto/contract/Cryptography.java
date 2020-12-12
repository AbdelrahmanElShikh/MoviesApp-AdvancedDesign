package com.aib.mobile.banking.cryptomanager.crypto.contract;

public interface Cryptography {

    String encryptText(String plainText);
    String decryptText(String encryptedText);
}
