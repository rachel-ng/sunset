package com.alibaba.android.arouter.core;

import android.content.Context;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.enums.TypeKind;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IInterceptorGroup;
import com.alibaba.android.arouter.facade.template.ILogger;
import com.alibaba.android.arouter.facade.template.IProviderGroup;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.alibaba.android.arouter.facade.template.IRouteRoot;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.android.arouter.utils.TextUtils;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ThreadPoolExecutor;

public class LogisticsCenter {
    static ThreadPoolExecutor executor;
    private static Context mContext;
    private static boolean registerByPlugin;

    private static void loadRouterMap() {
        registerByPlugin = false;
    }

    private static void register(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                Object newInstance = Class.forName(str).getConstructor((Class[]) null).newInstance((Object[]) null);
                if (newInstance instanceof IRouteRoot) {
                    registerRouteRoot((IRouteRoot) newInstance);
                } else if (newInstance instanceof IProviderGroup) {
                    registerProvider((IProviderGroup) newInstance);
                } else if (newInstance instanceof IInterceptorGroup) {
                    registerInterceptor((IInterceptorGroup) newInstance);
                } else {
                    ILogger iLogger = ARouter.logger;
                    iLogger.info("ARouter::", "register failed, class name: " + str + " should implements one of IRouteRoot/IProviderGroup/IInterceptorGroup.");
                }
            } catch (Exception e) {
                ILogger iLogger2 = ARouter.logger;
                iLogger2.error("ARouter::", "register class error:" + str, e);
            }
        }
    }

    private static void registerRouteRoot(IRouteRoot iRouteRoot) {
        markRegisteredByPlugin();
        if (iRouteRoot != null) {
            iRouteRoot.loadInto(Warehouse.groupsIndex);
        }
    }

    private static void registerInterceptor(IInterceptorGroup iInterceptorGroup) {
        markRegisteredByPlugin();
        if (iInterceptorGroup != null) {
            iInterceptorGroup.loadInto(Warehouse.interceptorsIndex);
        }
    }

    private static void registerProvider(IProviderGroup iProviderGroup) {
        markRegisteredByPlugin();
        if (iProviderGroup != null) {
            iProviderGroup.loadInto(Warehouse.providersIndex);
        }
    }

    private static void markRegisteredByPlugin() {
        if (!registerByPlugin) {
            registerByPlugin = true;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x00b1 A[Catch:{ Exception -> 0x018a }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void init(android.content.Context r9, java.util.concurrent.ThreadPoolExecutor r10) throws com.alibaba.android.arouter.exception.HandlerException {
        /*
            java.lang.String r0 = "Find router map finished, map size = "
            java.lang.Class<com.alibaba.android.arouter.core.LogisticsCenter> r1 = com.alibaba.android.arouter.core.LogisticsCenter.class
            monitor-enter(r1)
            mContext = r9     // Catch:{ all -> 0x01ab }
            executor = r10     // Catch:{ all -> 0x01ab }
            long r2 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x018a }
            loadRouterMap()     // Catch:{ Exception -> 0x018a }
            boolean r10 = registerByPlugin     // Catch:{ Exception -> 0x018a }
            r4 = 0
            if (r10 == 0) goto L_0x0020
            com.alibaba.android.arouter.facade.template.ILogger r9 = com.alibaba.android.arouter.launcher.ARouter.logger     // Catch:{ Exception -> 0x018a }
            java.lang.String r10 = "ARouter::"
            java.lang.String r0 = "Load router map by arouter-auto-register plugin."
            r9.info(r10, r0)     // Catch:{ Exception -> 0x018a }
            goto L_0x0115
        L_0x0020:
            boolean r10 = com.alibaba.android.arouter.launcher.ARouter.debuggable()     // Catch:{ Exception -> 0x018a }
            if (r10 != 0) goto L_0x004d
            boolean r10 = com.alibaba.android.arouter.utils.PackageUtils.isNewVersion(r9)     // Catch:{ Exception -> 0x018a }
            if (r10 == 0) goto L_0x002d
            goto L_0x004d
        L_0x002d:
            com.alibaba.android.arouter.facade.template.ILogger r10 = com.alibaba.android.arouter.launcher.ARouter.logger     // Catch:{ Exception -> 0x018a }
            java.lang.String r5 = "ARouter::"
            java.lang.String r6 = "Load router map from cache."
            r10.info(r5, r6)     // Catch:{ Exception -> 0x018a }
            java.util.HashSet r10 = new java.util.HashSet     // Catch:{ Exception -> 0x018a }
            java.lang.String r5 = "SP_AROUTER_CACHE"
            android.content.SharedPreferences r9 = r9.getSharedPreferences(r5, r4)     // Catch:{ Exception -> 0x018a }
            java.lang.String r5 = "ROUTER_MAP"
            java.util.HashSet r6 = new java.util.HashSet     // Catch:{ Exception -> 0x018a }
            r6.<init>()     // Catch:{ Exception -> 0x018a }
            java.util.Set r9 = r9.getStringSet(r5, r6)     // Catch:{ Exception -> 0x018a }
            r10.<init>(r9)     // Catch:{ Exception -> 0x018a }
            goto L_0x007a
        L_0x004d:
            com.alibaba.android.arouter.facade.template.ILogger r10 = com.alibaba.android.arouter.launcher.ARouter.logger     // Catch:{ Exception -> 0x018a }
            java.lang.String r5 = "ARouter::"
            java.lang.String r6 = "Run with debug mode or new install, rebuild router map."
            r10.info(r5, r6)     // Catch:{ Exception -> 0x018a }
            android.content.Context r10 = mContext     // Catch:{ Exception -> 0x018a }
            java.lang.String r5 = "com.alibaba.android.arouter.routes"
            java.util.Set r10 = com.alibaba.android.arouter.utils.ClassUtils.getFileNameByPackageName(r10, r5)     // Catch:{ Exception -> 0x018a }
            boolean r5 = r10.isEmpty()     // Catch:{ Exception -> 0x018a }
            if (r5 != 0) goto L_0x0077
            java.lang.String r5 = "SP_AROUTER_CACHE"
            android.content.SharedPreferences r5 = r9.getSharedPreferences(r5, r4)     // Catch:{ Exception -> 0x018a }
            android.content.SharedPreferences$Editor r5 = r5.edit()     // Catch:{ Exception -> 0x018a }
            java.lang.String r6 = "ROUTER_MAP"
            android.content.SharedPreferences$Editor r5 = r5.putStringSet(r6, r10)     // Catch:{ Exception -> 0x018a }
            r5.apply()     // Catch:{ Exception -> 0x018a }
        L_0x0077:
            com.alibaba.android.arouter.utils.PackageUtils.updateVersion(r9)     // Catch:{ Exception -> 0x018a }
        L_0x007a:
            com.alibaba.android.arouter.facade.template.ILogger r9 = com.alibaba.android.arouter.launcher.ARouter.logger     // Catch:{ Exception -> 0x018a }
            java.lang.String r5 = "ARouter::"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x018a }
            r6.<init>(r0)     // Catch:{ Exception -> 0x018a }
            int r0 = r10.size()     // Catch:{ Exception -> 0x018a }
            r6.append(r0)     // Catch:{ Exception -> 0x018a }
            java.lang.String r0 = ", cost "
            r6.append(r0)     // Catch:{ Exception -> 0x018a }
            long r7 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x018a }
            long r7 = r7 - r2
            r6.append(r7)     // Catch:{ Exception -> 0x018a }
            java.lang.String r0 = " ms."
            r6.append(r0)     // Catch:{ Exception -> 0x018a }
            java.lang.String r0 = r6.toString()     // Catch:{ Exception -> 0x018a }
            r9.info(r5, r0)     // Catch:{ Exception -> 0x018a }
            long r2 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x018a }
            java.util.Iterator r9 = r10.iterator()     // Catch:{ Exception -> 0x018a }
        L_0x00ab:
            boolean r10 = r9.hasNext()     // Catch:{ Exception -> 0x018a }
            if (r10 == 0) goto L_0x0115
            java.lang.Object r10 = r9.next()     // Catch:{ Exception -> 0x018a }
            java.lang.String r10 = (java.lang.String) r10     // Catch:{ Exception -> 0x018a }
            java.lang.String r0 = "com.alibaba.android.arouter.routes.ARouter$$Root"
            boolean r0 = r10.startsWith(r0)     // Catch:{ Exception -> 0x018a }
            r5 = 0
            if (r0 == 0) goto L_0x00d7
            java.lang.Class r10 = java.lang.Class.forName(r10)     // Catch:{ Exception -> 0x018a }
            java.lang.reflect.Constructor r10 = r10.getConstructor(r5)     // Catch:{ Exception -> 0x018a }
            java.lang.Object r10 = r10.newInstance(r5)     // Catch:{ Exception -> 0x018a }
            com.alibaba.android.arouter.facade.template.IRouteRoot r10 = (com.alibaba.android.arouter.facade.template.IRouteRoot) r10     // Catch:{ Exception -> 0x018a }
            r0 = r10
            com.alibaba.android.arouter.facade.template.IRouteRoot r0 = (com.alibaba.android.arouter.facade.template.IRouteRoot) r0     // Catch:{ Exception -> 0x018a }
            java.util.Map<java.lang.String, java.lang.Class<? extends com.alibaba.android.arouter.facade.template.IRouteGroup>> r0 = com.alibaba.android.arouter.core.Warehouse.groupsIndex     // Catch:{ Exception -> 0x018a }
            r10.loadInto(r0)     // Catch:{ Exception -> 0x018a }
            goto L_0x00ab
        L_0x00d7:
            java.lang.String r0 = "com.alibaba.android.arouter.routes.ARouter$$Interceptors"
            boolean r0 = r10.startsWith(r0)     // Catch:{ Exception -> 0x018a }
            if (r0 == 0) goto L_0x00f6
            java.lang.Class r10 = java.lang.Class.forName(r10)     // Catch:{ Exception -> 0x018a }
            java.lang.reflect.Constructor r10 = r10.getConstructor(r5)     // Catch:{ Exception -> 0x018a }
            java.lang.Object r10 = r10.newInstance(r5)     // Catch:{ Exception -> 0x018a }
            com.alibaba.android.arouter.facade.template.IInterceptorGroup r10 = (com.alibaba.android.arouter.facade.template.IInterceptorGroup) r10     // Catch:{ Exception -> 0x018a }
            r0 = r10
            com.alibaba.android.arouter.facade.template.IInterceptorGroup r0 = (com.alibaba.android.arouter.facade.template.IInterceptorGroup) r0     // Catch:{ Exception -> 0x018a }
            java.util.Map<java.lang.Integer, java.lang.Class<? extends com.alibaba.android.arouter.facade.template.IInterceptor>> r0 = com.alibaba.android.arouter.core.Warehouse.interceptorsIndex     // Catch:{ Exception -> 0x018a }
            r10.loadInto(r0)     // Catch:{ Exception -> 0x018a }
            goto L_0x00ab
        L_0x00f6:
            java.lang.String r0 = "com.alibaba.android.arouter.routes.ARouter$$Providers"
            boolean r0 = r10.startsWith(r0)     // Catch:{ Exception -> 0x018a }
            if (r0 == 0) goto L_0x00ab
            java.lang.Class r10 = java.lang.Class.forName(r10)     // Catch:{ Exception -> 0x018a }
            java.lang.reflect.Constructor r10 = r10.getConstructor(r5)     // Catch:{ Exception -> 0x018a }
            java.lang.Object r10 = r10.newInstance(r5)     // Catch:{ Exception -> 0x018a }
            com.alibaba.android.arouter.facade.template.IProviderGroup r10 = (com.alibaba.android.arouter.facade.template.IProviderGroup) r10     // Catch:{ Exception -> 0x018a }
            r0 = r10
            com.alibaba.android.arouter.facade.template.IProviderGroup r0 = (com.alibaba.android.arouter.facade.template.IProviderGroup) r0     // Catch:{ Exception -> 0x018a }
            java.util.Map<java.lang.String, com.alibaba.android.arouter.facade.model.RouteMeta> r0 = com.alibaba.android.arouter.core.Warehouse.providersIndex     // Catch:{ Exception -> 0x018a }
            r10.loadInto(r0)     // Catch:{ Exception -> 0x018a }
            goto L_0x00ab
        L_0x0115:
            com.alibaba.android.arouter.facade.template.ILogger r9 = com.alibaba.android.arouter.launcher.ARouter.logger     // Catch:{ Exception -> 0x018a }
            java.lang.String r10 = "ARouter::"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x018a }
            r0.<init>()     // Catch:{ Exception -> 0x018a }
            java.lang.String r5 = "Load root element finished, cost "
            r0.append(r5)     // Catch:{ Exception -> 0x018a }
            long r5 = java.lang.System.currentTimeMillis()     // Catch:{ Exception -> 0x018a }
            long r5 = r5 - r2
            r0.append(r5)     // Catch:{ Exception -> 0x018a }
            java.lang.String r2 = " ms."
            r0.append(r2)     // Catch:{ Exception -> 0x018a }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x018a }
            r9.info(r10, r0)     // Catch:{ Exception -> 0x018a }
            java.util.Map<java.lang.String, java.lang.Class<? extends com.alibaba.android.arouter.facade.template.IRouteGroup>> r9 = com.alibaba.android.arouter.core.Warehouse.groupsIndex     // Catch:{ Exception -> 0x018a }
            int r9 = r9.size()     // Catch:{ Exception -> 0x018a }
            if (r9 != 0) goto L_0x0148
            com.alibaba.android.arouter.facade.template.ILogger r9 = com.alibaba.android.arouter.launcher.ARouter.logger     // Catch:{ Exception -> 0x018a }
            java.lang.String r10 = "ARouter::"
            java.lang.String r0 = "No mapping files were found, check your configuration please!"
            r9.error(r10, r0)     // Catch:{ Exception -> 0x018a }
        L_0x0148:
            boolean r9 = com.alibaba.android.arouter.launcher.ARouter.debuggable()     // Catch:{ Exception -> 0x018a }
            if (r9 == 0) goto L_0x0188
            com.alibaba.android.arouter.facade.template.ILogger r9 = com.alibaba.android.arouter.launcher.ARouter.logger     // Catch:{ Exception -> 0x018a }
            java.lang.String r10 = "ARouter::"
            java.util.Locale r0 = java.util.Locale.getDefault()     // Catch:{ Exception -> 0x018a }
            java.lang.String r2 = "LogisticsCenter has already been loaded, GroupIndex[%d], InterceptorIndex[%d], ProviderIndex[%d]"
            java.util.Map<java.lang.String, java.lang.Class<? extends com.alibaba.android.arouter.facade.template.IRouteGroup>> r3 = com.alibaba.android.arouter.core.Warehouse.groupsIndex     // Catch:{ Exception -> 0x018a }
            int r3 = r3.size()     // Catch:{ Exception -> 0x018a }
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)     // Catch:{ Exception -> 0x018a }
            java.util.Map<java.lang.Integer, java.lang.Class<? extends com.alibaba.android.arouter.facade.template.IInterceptor>> r5 = com.alibaba.android.arouter.core.Warehouse.interceptorsIndex     // Catch:{ Exception -> 0x018a }
            int r5 = r5.size()     // Catch:{ Exception -> 0x018a }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r5)     // Catch:{ Exception -> 0x018a }
            java.util.Map<java.lang.String, com.alibaba.android.arouter.facade.model.RouteMeta> r6 = com.alibaba.android.arouter.core.Warehouse.providersIndex     // Catch:{ Exception -> 0x018a }
            int r6 = r6.size()     // Catch:{ Exception -> 0x018a }
            java.lang.Integer r6 = java.lang.Integer.valueOf(r6)     // Catch:{ Exception -> 0x018a }
            r7 = 3
            java.lang.Object[] r7 = new java.lang.Object[r7]     // Catch:{ Exception -> 0x018a }
            r7[r4] = r3     // Catch:{ Exception -> 0x018a }
            r3 = 1
            r7[r3] = r5     // Catch:{ Exception -> 0x018a }
            r3 = 2
            r7[r3] = r6     // Catch:{ Exception -> 0x018a }
            java.lang.String r0 = java.lang.String.format(r0, r2, r7)     // Catch:{ Exception -> 0x018a }
            r9.debug(r10, r0)     // Catch:{ Exception -> 0x018a }
        L_0x0188:
            monitor-exit(r1)
            return
        L_0x018a:
            r9 = move-exception
            com.alibaba.android.arouter.exception.HandlerException r10 = new com.alibaba.android.arouter.exception.HandlerException     // Catch:{ all -> 0x01ab }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x01ab }
            r0.<init>()     // Catch:{ all -> 0x01ab }
            java.lang.String r2 = "ARouter::ARouter init logistics center exception! ["
            r0.append(r2)     // Catch:{ all -> 0x01ab }
            java.lang.String r9 = r9.getMessage()     // Catch:{ all -> 0x01ab }
            r0.append(r9)     // Catch:{ all -> 0x01ab }
            java.lang.String r9 = "]"
            r0.append(r9)     // Catch:{ all -> 0x01ab }
            java.lang.String r9 = r0.toString()     // Catch:{ all -> 0x01ab }
            r10.<init>(r9)     // Catch:{ all -> 0x01ab }
            throw r10     // Catch:{ all -> 0x01ab }
        L_0x01ab:
            r9 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x01ab }
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.android.arouter.core.LogisticsCenter.init(android.content.Context, java.util.concurrent.ThreadPoolExecutor):void");
    }

    public static Postcard buildProvider(String str) {
        RouteMeta routeMeta = Warehouse.providersIndex.get(str);
        if (routeMeta == null) {
            return null;
        }
        return new Postcard(routeMeta.getPath(), routeMeta.getGroup());
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x007d, code lost:
        r13 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0098, code lost:
        throw new com.alibaba.android.arouter.exception.HandlerException("ARouter::Fatal exception when loading group meta. [" + r13.getMessage() + "]");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0174, code lost:
        r13 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:?, code lost:
        com.alibaba.android.arouter.launcher.ARouter.logger.error("ARouter::", "Init provider failed!", r13);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x0185, code lost:
        throw new com.alibaba.android.arouter.exception.HandlerException("Init provider failed!");
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:8:0x0027, B:39:0x015f] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void completion(com.alibaba.android.arouter.facade.Postcard r13) {
        /*
            java.lang.String r0 = "ARouter::Fatal exception when loading group meta. ["
            java.lang.String r1 = "ARouter::There is no route match the path ["
            java.lang.Class<com.alibaba.android.arouter.core.LogisticsCenter> r2 = com.alibaba.android.arouter.core.LogisticsCenter.class
            monitor-enter(r2)
            if (r13 == 0) goto L_0x0190
            java.util.Map<java.lang.String, com.alibaba.android.arouter.facade.model.RouteMeta> r3 = com.alibaba.android.arouter.core.Warehouse.routes     // Catch:{ all -> 0x018e }
            java.lang.String r4 = r13.getPath()     // Catch:{ all -> 0x018e }
            java.lang.Object r3 = r3.get(r4)     // Catch:{ all -> 0x018e }
            com.alibaba.android.arouter.facade.model.RouteMeta r3 = (com.alibaba.android.arouter.facade.model.RouteMeta) r3     // Catch:{ all -> 0x018e }
            r4 = 0
            r5 = 2
            r6 = 1
            r7 = 0
            if (r3 != 0) goto L_0x00c0
            java.util.Map<java.lang.String, java.lang.Class<? extends com.alibaba.android.arouter.facade.template.IRouteGroup>> r3 = com.alibaba.android.arouter.core.Warehouse.groupsIndex     // Catch:{ all -> 0x018e }
            java.lang.String r8 = r13.getGroup()     // Catch:{ all -> 0x018e }
            boolean r3 = r3.containsKey(r8)     // Catch:{ all -> 0x018e }
            if (r3 == 0) goto L_0x0099
            boolean r1 = com.alibaba.android.arouter.launcher.ARouter.debuggable()     // Catch:{ Exception -> 0x007d }
            if (r1 == 0) goto L_0x004c
            com.alibaba.android.arouter.facade.template.ILogger r1 = com.alibaba.android.arouter.launcher.ARouter.logger     // Catch:{ Exception -> 0x007d }
            java.lang.String r3 = "ARouter::"
            java.util.Locale r8 = java.util.Locale.getDefault()     // Catch:{ Exception -> 0x007d }
            java.lang.String r9 = "The group [%s] starts loading, trigger by [%s]"
            java.lang.String r10 = r13.getGroup()     // Catch:{ Exception -> 0x007d }
            java.lang.String r11 = r13.getPath()     // Catch:{ Exception -> 0x007d }
            java.lang.Object[] r12 = new java.lang.Object[r5]     // Catch:{ Exception -> 0x007d }
            r12[r4] = r10     // Catch:{ Exception -> 0x007d }
            r12[r6] = r11     // Catch:{ Exception -> 0x007d }
            java.lang.String r8 = java.lang.String.format(r8, r9, r12)     // Catch:{ Exception -> 0x007d }
            r1.debug(r3, r8)     // Catch:{ Exception -> 0x007d }
        L_0x004c:
            java.lang.String r1 = r13.getGroup()     // Catch:{ Exception -> 0x007d }
            addRouteGroupDynamic(r1, r7)     // Catch:{ Exception -> 0x007d }
            boolean r1 = com.alibaba.android.arouter.launcher.ARouter.debuggable()     // Catch:{ Exception -> 0x007d }
            if (r1 == 0) goto L_0x0078
            com.alibaba.android.arouter.facade.template.ILogger r1 = com.alibaba.android.arouter.launcher.ARouter.logger     // Catch:{ Exception -> 0x007d }
            java.lang.String r3 = "ARouter::"
            java.util.Locale r7 = java.util.Locale.getDefault()     // Catch:{ Exception -> 0x007d }
            java.lang.String r8 = "The group [%s] has already been loaded, trigger by [%s]"
            java.lang.String r9 = r13.getGroup()     // Catch:{ Exception -> 0x007d }
            java.lang.String r10 = r13.getPath()     // Catch:{ Exception -> 0x007d }
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch:{ Exception -> 0x007d }
            r5[r4] = r9     // Catch:{ Exception -> 0x007d }
            r5[r6] = r10     // Catch:{ Exception -> 0x007d }
            java.lang.String r4 = java.lang.String.format(r7, r8, r5)     // Catch:{ Exception -> 0x007d }
            r1.debug(r3, r4)     // Catch:{ Exception -> 0x007d }
        L_0x0078:
            completion(r13)     // Catch:{ all -> 0x018e }
            goto L_0x018c
        L_0x007d:
            r13 = move-exception
            com.alibaba.android.arouter.exception.HandlerException r1 = new com.alibaba.android.arouter.exception.HandlerException     // Catch:{ all -> 0x018e }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x018e }
            r3.<init>(r0)     // Catch:{ all -> 0x018e }
            java.lang.String r13 = r13.getMessage()     // Catch:{ all -> 0x018e }
            r3.append(r13)     // Catch:{ all -> 0x018e }
            java.lang.String r13 = "]"
            r3.append(r13)     // Catch:{ all -> 0x018e }
            java.lang.String r13 = r3.toString()     // Catch:{ all -> 0x018e }
            r1.<init>(r13)     // Catch:{ all -> 0x018e }
            throw r1     // Catch:{ all -> 0x018e }
        L_0x0099:
            com.alibaba.android.arouter.exception.NoRouteFoundException r0 = new com.alibaba.android.arouter.exception.NoRouteFoundException     // Catch:{ all -> 0x018e }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x018e }
            r3.<init>(r1)     // Catch:{ all -> 0x018e }
            java.lang.String r1 = r13.getPath()     // Catch:{ all -> 0x018e }
            r3.append(r1)     // Catch:{ all -> 0x018e }
            java.lang.String r1 = "], in group ["
            r3.append(r1)     // Catch:{ all -> 0x018e }
            java.lang.String r13 = r13.getGroup()     // Catch:{ all -> 0x018e }
            r3.append(r13)     // Catch:{ all -> 0x018e }
            java.lang.String r13 = "]"
            r3.append(r13)     // Catch:{ all -> 0x018e }
            java.lang.String r13 = r3.toString()     // Catch:{ all -> 0x018e }
            r0.<init>(r13)     // Catch:{ all -> 0x018e }
            throw r0     // Catch:{ all -> 0x018e }
        L_0x00c0:
            java.lang.Class r0 = r3.getDestination()     // Catch:{ all -> 0x018e }
            r13.setDestination(r0)     // Catch:{ all -> 0x018e }
            com.alibaba.android.arouter.facade.enums.RouteType r0 = r3.getType()     // Catch:{ all -> 0x018e }
            r13.setType(r0)     // Catch:{ all -> 0x018e }
            int r0 = r3.getPriority()     // Catch:{ all -> 0x018e }
            r13.setPriority(r0)     // Catch:{ all -> 0x018e }
            int r0 = r3.getExtra()     // Catch:{ all -> 0x018e }
            r13.setExtra(r0)     // Catch:{ all -> 0x018e }
            android.net.Uri r0 = r13.getUri()     // Catch:{ all -> 0x018e }
            if (r0 == 0) goto L_0x013c
            java.util.Map r1 = com.alibaba.android.arouter.utils.TextUtils.splitQueryParameters(r0)     // Catch:{ all -> 0x018e }
            java.util.Map r8 = r3.getParamsType()     // Catch:{ all -> 0x018e }
            boolean r9 = com.alibaba.android.arouter.utils.MapUtils.isNotEmpty(r8)     // Catch:{ all -> 0x018e }
            if (r9 == 0) goto L_0x0133
            java.util.Set r9 = r8.entrySet()     // Catch:{ all -> 0x018e }
            java.util.Iterator r9 = r9.iterator()     // Catch:{ all -> 0x018e }
        L_0x00f8:
            boolean r10 = r9.hasNext()     // Catch:{ all -> 0x018e }
            if (r10 == 0) goto L_0x011e
            java.lang.Object r10 = r9.next()     // Catch:{ all -> 0x018e }
            java.util.Map$Entry r10 = (java.util.Map.Entry) r10     // Catch:{ all -> 0x018e }
            java.lang.Object r11 = r10.getValue()     // Catch:{ all -> 0x018e }
            java.lang.Integer r11 = (java.lang.Integer) r11     // Catch:{ all -> 0x018e }
            java.lang.Object r12 = r10.getKey()     // Catch:{ all -> 0x018e }
            java.lang.String r12 = (java.lang.String) r12     // Catch:{ all -> 0x018e }
            java.lang.Object r10 = r10.getKey()     // Catch:{ all -> 0x018e }
            java.lang.Object r10 = r1.get(r10)     // Catch:{ all -> 0x018e }
            java.lang.String r10 = (java.lang.String) r10     // Catch:{ all -> 0x018e }
            setValue(r13, r11, r12, r10)     // Catch:{ all -> 0x018e }
            goto L_0x00f8
        L_0x011e:
            android.os.Bundle r1 = r13.getExtras()     // Catch:{ all -> 0x018e }
            java.lang.String r9 = "wmHzgD4lOj5o4241"
            java.util.Set r8 = r8.keySet()     // Catch:{ all -> 0x018e }
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ all -> 0x018e }
            java.lang.Object[] r4 = r8.toArray(r4)     // Catch:{ all -> 0x018e }
            java.lang.String[] r4 = (java.lang.String[]) r4     // Catch:{ all -> 0x018e }
            r1.putStringArray(r9, r4)     // Catch:{ all -> 0x018e }
        L_0x0133:
            java.lang.String r1 = "NTeRQWvye18AkPd6G"
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x018e }
            r13.withString(r1, r0)     // Catch:{ all -> 0x018e }
        L_0x013c:
            int[] r0 = com.alibaba.android.arouter.core.LogisticsCenter.AnonymousClass1.$SwitchMap$com$alibaba$android$arouter$facade$enums$RouteType     // Catch:{ all -> 0x018e }
            com.alibaba.android.arouter.facade.enums.RouteType r1 = r3.getType()     // Catch:{ all -> 0x018e }
            int r1 = r1.ordinal()     // Catch:{ all -> 0x018e }
            r0 = r0[r1]     // Catch:{ all -> 0x018e }
            if (r0 == r6) goto L_0x0151
            if (r0 == r5) goto L_0x014d
            goto L_0x018c
        L_0x014d:
            r13.greenChannel()     // Catch:{ all -> 0x018e }
            goto L_0x018c
        L_0x0151:
            java.lang.Class r0 = r3.getDestination()     // Catch:{ all -> 0x018e }
            java.util.Map<java.lang.Class, com.alibaba.android.arouter.facade.template.IProvider> r1 = com.alibaba.android.arouter.core.Warehouse.providers     // Catch:{ all -> 0x018e }
            java.lang.Object r1 = r1.get(r0)     // Catch:{ all -> 0x018e }
            com.alibaba.android.arouter.facade.template.IProvider r1 = (com.alibaba.android.arouter.facade.template.IProvider) r1     // Catch:{ all -> 0x018e }
            if (r1 != 0) goto L_0x0186
            java.lang.reflect.Constructor r1 = r0.getConstructor(r7)     // Catch:{ Exception -> 0x0174 }
            java.lang.Object r1 = r1.newInstance(r7)     // Catch:{ Exception -> 0x0174 }
            com.alibaba.android.arouter.facade.template.IProvider r1 = (com.alibaba.android.arouter.facade.template.IProvider) r1     // Catch:{ Exception -> 0x0174 }
            android.content.Context r3 = mContext     // Catch:{ Exception -> 0x0174 }
            r1.init(r3)     // Catch:{ Exception -> 0x0174 }
            java.util.Map<java.lang.Class, com.alibaba.android.arouter.facade.template.IProvider> r3 = com.alibaba.android.arouter.core.Warehouse.providers     // Catch:{ Exception -> 0x0174 }
            r3.put(r0, r1)     // Catch:{ Exception -> 0x0174 }
            goto L_0x0186
        L_0x0174:
            r13 = move-exception
            com.alibaba.android.arouter.facade.template.ILogger r0 = com.alibaba.android.arouter.launcher.ARouter.logger     // Catch:{ all -> 0x018e }
            java.lang.String r1 = "ARouter::"
            java.lang.String r3 = "Init provider failed!"
            r0.error(r1, r3, r13)     // Catch:{ all -> 0x018e }
            com.alibaba.android.arouter.exception.HandlerException r13 = new com.alibaba.android.arouter.exception.HandlerException     // Catch:{ all -> 0x018e }
            java.lang.String r0 = "Init provider failed!"
            r13.<init>(r0)     // Catch:{ all -> 0x018e }
            throw r13     // Catch:{ all -> 0x018e }
        L_0x0186:
            r13.setProvider(r1)     // Catch:{ all -> 0x018e }
            r13.greenChannel()     // Catch:{ all -> 0x018e }
        L_0x018c:
            monitor-exit(r2)
            return
        L_0x018e:
            r13 = move-exception
            goto L_0x0198
        L_0x0190:
            com.alibaba.android.arouter.exception.NoRouteFoundException r13 = new com.alibaba.android.arouter.exception.NoRouteFoundException     // Catch:{ all -> 0x018e }
            java.lang.String r0 = "ARouter::No postcard!"
            r13.<init>(r0)     // Catch:{ all -> 0x018e }
            throw r13     // Catch:{ all -> 0x018e }
        L_0x0198:
            monitor-exit(r2)     // Catch:{ all -> 0x018e }
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.android.arouter.core.LogisticsCenter.completion(com.alibaba.android.arouter.facade.Postcard):void");
    }

    /* renamed from: com.alibaba.android.arouter.core.LogisticsCenter$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$alibaba$android$arouter$facade$enums$RouteType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|6) */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        static {
            /*
                com.alibaba.android.arouter.facade.enums.RouteType[] r0 = com.alibaba.android.arouter.facade.enums.RouteType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$alibaba$android$arouter$facade$enums$RouteType = r0
                com.alibaba.android.arouter.facade.enums.RouteType r1 = com.alibaba.android.arouter.facade.enums.RouteType.PROVIDER     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$alibaba$android$arouter$facade$enums$RouteType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.alibaba.android.arouter.facade.enums.RouteType r1 = com.alibaba.android.arouter.facade.enums.RouteType.FRAGMENT     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.alibaba.android.arouter.core.LogisticsCenter.AnonymousClass1.<clinit>():void");
        }
    }

    private static void setValue(Postcard postcard, Integer num, String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            if (num != null) {
                try {
                    if (num.intValue() == TypeKind.BOOLEAN.ordinal()) {
                        postcard.withBoolean(str, Boolean.parseBoolean(str2));
                    } else if (num.intValue() == TypeKind.BYTE.ordinal()) {
                        postcard.withByte(str, Byte.parseByte(str2));
                    } else if (num.intValue() == TypeKind.SHORT.ordinal()) {
                        postcard.withShort(str, Short.parseShort(str2));
                    } else if (num.intValue() == TypeKind.INT.ordinal()) {
                        postcard.withInt(str, Integer.parseInt(str2));
                    } else if (num.intValue() == TypeKind.LONG.ordinal()) {
                        postcard.withLong(str, Long.parseLong(str2));
                    } else if (num.intValue() == TypeKind.FLOAT.ordinal()) {
                        postcard.withFloat(str, Float.parseFloat(str2));
                    } else if (num.intValue() == TypeKind.DOUBLE.ordinal()) {
                        postcard.withDouble(str, Double.parseDouble(str2));
                    } else if (num.intValue() == TypeKind.STRING.ordinal()) {
                        postcard.withString(str, str2);
                    } else if (num.intValue() != TypeKind.PARCELABLE.ordinal()) {
                        if (num.intValue() == TypeKind.OBJECT.ordinal()) {
                            postcard.withString(str, str2);
                        } else {
                            postcard.withString(str, str2);
                        }
                    }
                } catch (Throwable th) {
                    ILogger iLogger = ARouter.logger;
                    iLogger.warning("ARouter::", "LogisticsCenter setValue failed! " + th.getMessage());
                }
            } else {
                postcard.withString(str, str2);
            }
        }
    }

    public static void suspend() {
        Warehouse.clear();
    }

    public static synchronized void addRouteGroupDynamic(String str, IRouteGroup iRouteGroup) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        synchronized (LogisticsCenter.class) {
            if (Warehouse.groupsIndex.containsKey(str)) {
                ((IRouteGroup) Warehouse.groupsIndex.get(str).getConstructor((Class[]) null).newInstance((Object[]) null)).loadInto(Warehouse.routes);
                Warehouse.groupsIndex.remove(str);
            }
            if (iRouteGroup != null) {
                iRouteGroup.loadInto(Warehouse.routes);
            }
        }
    }
}
