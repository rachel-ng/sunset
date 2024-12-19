package com.alibaba.android.arouter.core;

import android.content.Context;
import android.util.LruCache;
import com.alibaba.android.arouter.facade.service.AutowiredService;
import com.alibaba.android.arouter.facade.template.ISyringe;
import java.util.ArrayList;
import java.util.List;

public class AutowiredServiceImpl implements AutowiredService {
    private List<String> blackList;
    private LruCache<String, ISyringe> classCache;

    public void init(Context context) {
        this.classCache = new LruCache<>(50);
        this.blackList = new ArrayList();
    }

    public void autowire(Object obj) {
        doInject(obj, (Class<?>) null);
    }

    private void doInject(Object obj, Class<?> cls) {
        if (cls == null) {
            cls = obj.getClass();
        }
        ISyringe syringe = getSyringe(cls);
        if (syringe != null) {
            syringe.inject(obj);
        }
        Class<? super Object> superclass = cls.getSuperclass();
        if (superclass != null && !superclass.getName().startsWith("android")) {
            doInject(obj, superclass);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v7, resolved type: com.alibaba.android.arouter.facade.template.ISyringe} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.alibaba.android.arouter.facade.template.ISyringe getSyringe(java.lang.Class<?> r4) {
        /*
            r3 = this;
            java.lang.String r0 = r4.getName()
            r1 = 0
            java.util.List<java.lang.String> r2 = r3.blackList     // Catch:{ Exception -> 0x0041 }
            boolean r2 = r2.contains(r0)     // Catch:{ Exception -> 0x0041 }
            if (r2 != 0) goto L_0x0046
            android.util.LruCache<java.lang.String, com.alibaba.android.arouter.facade.template.ISyringe> r2 = r3.classCache     // Catch:{ Exception -> 0x0041 }
            java.lang.Object r2 = r2.get(r0)     // Catch:{ Exception -> 0x0041 }
            com.alibaba.android.arouter.facade.template.ISyringe r2 = (com.alibaba.android.arouter.facade.template.ISyringe) r2     // Catch:{ Exception -> 0x0041 }
            if (r2 != 0) goto L_0x003b
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0041 }
            r2.<init>()     // Catch:{ Exception -> 0x0041 }
            java.lang.String r4 = r4.getName()     // Catch:{ Exception -> 0x0041 }
            r2.append(r4)     // Catch:{ Exception -> 0x0041 }
            java.lang.String r4 = "$$ARouter$$Autowired"
            r2.append(r4)     // Catch:{ Exception -> 0x0041 }
            java.lang.String r4 = r2.toString()     // Catch:{ Exception -> 0x0041 }
            java.lang.Class r4 = java.lang.Class.forName(r4)     // Catch:{ Exception -> 0x0041 }
            java.lang.reflect.Constructor r4 = r4.getConstructor(r1)     // Catch:{ Exception -> 0x0041 }
            java.lang.Object r4 = r4.newInstance(r1)     // Catch:{ Exception -> 0x0041 }
            r2 = r4
            com.alibaba.android.arouter.facade.template.ISyringe r2 = (com.alibaba.android.arouter.facade.template.ISyringe) r2     // Catch:{ Exception -> 0x0041 }
        L_0x003b:
            android.util.LruCache<java.lang.String, com.alibaba.android.arouter.facade.template.ISyringe> r4 = r3.classCache     // Catch:{ Exception -> 0x0041 }
            r4.put(r0, r2)     // Catch:{ Exception -> 0x0041 }
            return r2
        L_0x0041:
            java.util.List<java.lang.String> r4 = r3.blackList
            r4.add(r0)
        L_0x0046:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.android.arouter.core.AutowiredServiceImpl.getSyringe(java.lang.Class):com.alibaba.android.arouter.facade.template.ISyringe");
    }
}
