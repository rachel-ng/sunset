package com.franmontiel.persistentcookiejar.persistence;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.webkit.ProxyConfig;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import okhttp3.Cookie;

public class SharedPrefsCookiePersistor implements CookiePersistor {
    private final SharedPreferences sharedPreferences;

    public SharedPrefsCookiePersistor(Context context) {
        this(context.getSharedPreferences("CookiePersistence", 0));
    }

    public SharedPrefsCookiePersistor(SharedPreferences sharedPreferences2) {
        this.sharedPreferences = sharedPreferences2;
    }

    public List<Cookie> loadAll() {
        ArrayList arrayList = new ArrayList(this.sharedPreferences.getAll().size());
        for (Map.Entry<String, ?> value : this.sharedPreferences.getAll().entrySet()) {
            Cookie decode = new SerializableCookie().decode((String) value.getValue());
            if (decode != null) {
                arrayList.add(decode);
            }
        }
        return arrayList;
    }

    public void saveAll(Collection<Cookie> collection) {
        SharedPreferences.Editor edit = this.sharedPreferences.edit();
        for (Cookie next : collection) {
            edit.putString(createCookieKey(next), new SerializableCookie().encode(next));
        }
        edit.commit();
    }

    public void removeAll(Collection<Cookie> collection) {
        SharedPreferences.Editor edit = this.sharedPreferences.edit();
        for (Cookie createCookieKey : collection) {
            edit.remove(createCookieKey(createCookieKey));
        }
        edit.commit();
    }

    private static String createCookieKey(Cookie cookie) {
        StringBuilder sb = new StringBuilder();
        sb.append(cookie.secure() ? ProxyConfig.MATCH_HTTPS : ProxyConfig.MATCH_HTTP);
        sb.append("://");
        sb.append(cookie.domain());
        sb.append(cookie.path());
        sb.append("|");
        sb.append(cookie.name());
        return sb.toString();
    }

    public void clear() {
        this.sharedPreferences.edit().clear().commit();
    }
}
