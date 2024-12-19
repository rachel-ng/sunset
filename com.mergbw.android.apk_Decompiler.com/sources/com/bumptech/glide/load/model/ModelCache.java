package com.bumptech.glide.load.model;

import com.bumptech.glide.util.LruCache;
import com.bumptech.glide.util.Util;
import java.util.Queue;

public class ModelCache<A, B> {
    private static final int DEFAULT_SIZE = 250;
    private final LruCache<ModelKey<A>, B> cache;

    public ModelCache() {
        this(250);
    }

    public ModelCache(long j) {
        this.cache = new LruCache<ModelKey<A>, B>(j) {
            /* access modifiers changed from: protected */
            public void onItemEvicted(ModelKey<A> modelKey, B b2) {
                modelKey.release();
            }
        };
    }

    public B get(A a2, int i, int i2) {
        ModelKey modelKey = ModelKey.get(a2, i, i2);
        B b2 = this.cache.get(modelKey);
        modelKey.release();
        return b2;
    }

    public void put(A a2, int i, int i2, B b2) {
        this.cache.put(ModelKey.get(a2, i, i2), b2);
    }

    public void clear() {
        this.cache.clearMemory();
    }

    static final class ModelKey<A> {
        private static final Queue<ModelKey<?>> KEY_QUEUE = Util.createQueue(0);
        private int height;
        private A model;
        private int width;

        static <A> ModelKey<A> get(A a2, int i, int i2) {
            ModelKey<A> poll;
            Queue<ModelKey<?>> queue = KEY_QUEUE;
            synchronized (queue) {
                poll = queue.poll();
            }
            if (poll == null) {
                poll = new ModelKey<>();
            }
            poll.init(a2, i, i2);
            return poll;
        }

        private ModelKey() {
        }

        private void init(A a2, int i, int i2) {
            this.model = a2;
            this.width = i;
            this.height = i2;
        }

        public void release() {
            Queue<ModelKey<?>> queue = KEY_QUEUE;
            synchronized (queue) {
                queue.offer(this);
            }
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ModelKey)) {
                return false;
            }
            ModelKey modelKey = (ModelKey) obj;
            if (this.width == modelKey.width && this.height == modelKey.height && this.model.equals(modelKey.model)) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return (((this.height * 31) + this.width) * 31) + this.model.hashCode();
        }
    }
}
