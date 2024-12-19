package com.franmontiel.persistentcookiejar.cache;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import okhttp3.Cookie;

public class SetCookieCache implements CookieCache {
    /* access modifiers changed from: private */
    public Set<IdentifiableCookie> cookies = new HashSet();

    public void addAll(Collection<Cookie> collection) {
        for (IdentifiableCookie next : IdentifiableCookie.decorateAll(collection)) {
            this.cookies.remove(next);
            this.cookies.add(next);
        }
    }

    public void clear() {
        this.cookies.clear();
    }

    public Iterator<Cookie> iterator() {
        return new SetCookieCacheIterator();
    }

    private class SetCookieCacheIterator implements Iterator<Cookie> {
        private Iterator<IdentifiableCookie> iterator;

        public SetCookieCacheIterator() {
            this.iterator = SetCookieCache.this.cookies.iterator();
        }

        public boolean hasNext() {
            return this.iterator.hasNext();
        }

        public Cookie next() {
            return this.iterator.next().getCookie();
        }

        public void remove() {
            this.iterator.remove();
        }
    }
}
