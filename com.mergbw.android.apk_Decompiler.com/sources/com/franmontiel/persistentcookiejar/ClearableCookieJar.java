package com.franmontiel.persistentcookiejar;

import okhttp3.CookieJar;

public interface ClearableCookieJar extends CookieJar {
    void clear();

    void clearSession();
}
