package com.franmontiel.persistentcookiejar;

import com.franmontiel.persistentcookiejar.cache.CookieCache;
import com.franmontiel.persistentcookiejar.persistence.CookiePersistor;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import okhttp3.Cookie;
import okhttp3.HttpUrl;

public class PersistentCookieJar implements ClearableCookieJar {
    private CookieCache cache;
    private CookiePersistor persistor;

    public PersistentCookieJar(CookieCache cookieCache, CookiePersistor cookiePersistor) {
        this.cache = cookieCache;
        this.persistor = cookiePersistor;
        cookieCache.addAll(cookiePersistor.loadAll());
    }

    public synchronized void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
        this.cache.addAll(list);
        this.persistor.saveAll(filterPersistentCookies(list));
    }

    private static List<Cookie> filterPersistentCookies(List<Cookie> list) {
        ArrayList arrayList = new ArrayList();
        for (Cookie next : list) {
            if (next.persistent()) {
                arrayList.add(next);
            }
        }
        return arrayList;
    }

    public synchronized List<Cookie> loadForRequest(HttpUrl httpUrl) {
        ArrayList arrayList;
        ArrayList arrayList2 = new ArrayList();
        arrayList = new ArrayList();
        Iterator it = this.cache.iterator();
        while (it.hasNext()) {
            Cookie cookie = (Cookie) it.next();
            if (isCookieExpired(cookie)) {
                arrayList2.add(cookie);
                it.remove();
            } else if (cookie.matches(httpUrl)) {
                arrayList.add(cookie);
            }
        }
        this.persistor.removeAll(arrayList2);
        return arrayList;
    }

    private static boolean isCookieExpired(Cookie cookie) {
        return cookie.expiresAt() < System.currentTimeMillis();
    }

    public synchronized void clearSession() {
        this.cache.clear();
        this.cache.addAll(this.persistor.loadAll());
    }

    public synchronized void clear() {
        this.cache.clear();
        this.persistor.clear();
    }
}
