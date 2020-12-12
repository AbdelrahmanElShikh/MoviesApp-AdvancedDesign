package com.aib.mobile.banking.authmanager.remote.client;

public class SettingsAPI {
    public static String BASE_URL = "https://rp.intercom.com.eg:446/mga/sps/oauth/oauth20/";
//        public static String BASE_URL = "https://isam-test.intercom.com.eg:446/mga/sps/oauth/oauth20/";
    //    public static String BASE_URL = "https://mobapp.aib.com.eg:450/mga/sps/oauth/oauth20/";
    public static Long TIME_OUT = 30000L;
    public static String clientId = "09dp6vn4VLba4OFZ3dLT";
    public static String AUTH_TYPE = "Bearer";

    public enum GrantTypes {
        password, refresh_token
    }
}

