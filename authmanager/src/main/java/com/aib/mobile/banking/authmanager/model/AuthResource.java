package com.aib.mobile.banking.authmanager.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

// A generic class that contains data and status about loading this data.
public class AuthResource {
    @NonNull
    public final Status status;
    @Nullable
    public final Auth data;
    @Nullable
    public final String message;

    private AuthResource(@NonNull Status status, @Nullable Auth data,
                         @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static AuthResource success(@NonNull Auth data) {
        return new AuthResource(Status.SUCCESS, data, null);
    }

    public static AuthResource error(String msg) {
        return new AuthResource(Status.ERROR, null, msg);
    }

    public enum Status {SUCCESS, ERROR}

}