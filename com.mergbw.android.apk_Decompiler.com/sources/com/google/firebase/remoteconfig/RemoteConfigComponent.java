package com.google.firebase.remoteconfig;

import android.app.Application;
import android.content.Context;
import androidx.lifecycle.LifecycleKt$$ExternalSyntheticBackportWithForwarding0;
import com.google.android.gms.common.api.internal.BackgroundDetector;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.FirebaseApp;
import com.google.firebase.abt.FirebaseABTesting;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import com.google.firebase.inject.Provider;
import com.google.firebase.installations.FirebaseInstallationsApi;
import com.google.firebase.remoteconfig.internal.ConfigCacheClient;
import com.google.firebase.remoteconfig.internal.ConfigFetchHandler;
import com.google.firebase.remoteconfig.internal.ConfigFetchHttpClient;
import com.google.firebase.remoteconfig.internal.ConfigGetParameterHandler;
import com.google.firebase.remoteconfig.internal.ConfigMetadataClient;
import com.google.firebase.remoteconfig.internal.ConfigRealtimeHandler;
import com.google.firebase.remoteconfig.internal.ConfigStorageClient;
import com.google.firebase.remoteconfig.internal.Personalization;
import com.google.firebase.remoteconfig.internal.rollouts.RolloutsStateFactory;
import com.google.firebase.remoteconfig.internal.rollouts.RolloutsStateSubscriptionsHandler;
import com.google.firebase.remoteconfig.interop.FirebaseRemoteConfigInterop;
import com.google.firebase.remoteconfig.interop.rollouts.RolloutsStateSubscriber;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicReference;

public class RemoteConfigComponent implements FirebaseRemoteConfigInterop {
    public static final String ACTIVATE_FILE_NAME = "activate";
    public static final long CONNECTION_TIMEOUT_IN_SECONDS = 60;
    public static final String DEFAULTS_FILE_NAME = "defaults";
    private static final Clock DEFAULT_CLOCK = DefaultClock.getInstance();
    public static final String DEFAULT_NAMESPACE = "firebase";
    private static final Random DEFAULT_RANDOM = new Random();
    public static final String FETCH_FILE_NAME = "fetch";
    private static final String FIREBASE_REMOTE_CONFIG_FILE_NAME_PREFIX = "frc";
    private static final String PREFERENCES_FILE_NAME = "settings";
    private static final Map<String, FirebaseRemoteConfig> frcNamespaceInstancesStatic = new HashMap();
    private final Provider<AnalyticsConnector> analyticsConnector;
    private final String appId;
    private final Context context;
    private Map<String, String> customHeaders;
    private final ScheduledExecutorService executor;
    private final FirebaseABTesting firebaseAbt;
    private final FirebaseApp firebaseApp;
    private final FirebaseInstallationsApi firebaseInstallations;
    private final Map<String, FirebaseRemoteConfig> frcNamespaceInstances;

    static /* synthetic */ AnalyticsConnector lambda$getFetchHandler$0() {
        return null;
    }

    RemoteConfigComponent(Context context2, ScheduledExecutorService scheduledExecutorService, FirebaseApp firebaseApp2, FirebaseInstallationsApi firebaseInstallationsApi, FirebaseABTesting firebaseABTesting, Provider<AnalyticsConnector> provider) {
        this(context2, scheduledExecutorService, firebaseApp2, firebaseInstallationsApi, firebaseABTesting, provider, true);
    }

    protected RemoteConfigComponent(Context context2, ScheduledExecutorService scheduledExecutorService, FirebaseApp firebaseApp2, FirebaseInstallationsApi firebaseInstallationsApi, FirebaseABTesting firebaseABTesting, Provider<AnalyticsConnector> provider, boolean z) {
        this.frcNamespaceInstances = new HashMap();
        this.customHeaders = new HashMap();
        this.context = context2;
        this.executor = scheduledExecutorService;
        this.firebaseApp = firebaseApp2;
        this.firebaseInstallations = firebaseInstallationsApi;
        this.firebaseAbt = firebaseABTesting;
        this.analyticsConnector = provider;
        this.appId = firebaseApp2.getOptions().getApplicationId();
        GlobalBackgroundListener.ensureBackgroundListenerIsRegistered(context2);
        if (z) {
            Tasks.call(scheduledExecutorService, new RemoteConfigComponent$$ExternalSyntheticLambda1(this));
        }
    }

    /* access modifiers changed from: package-private */
    public FirebaseRemoteConfig getDefault() {
        return get(DEFAULT_NAMESPACE);
    }

    public synchronized FirebaseRemoteConfig get(String str) {
        ConfigCacheClient cacheClient;
        ConfigCacheClient cacheClient2;
        ConfigCacheClient cacheClient3;
        ConfigMetadataClient metadataClient;
        ConfigGetParameterHandler getHandler;
        String str2;
        cacheClient = getCacheClient(str, FETCH_FILE_NAME);
        cacheClient2 = getCacheClient(str, ACTIVATE_FILE_NAME);
        cacheClient3 = getCacheClient(str, DEFAULTS_FILE_NAME);
        metadataClient = getMetadataClient(this.context, this.appId, str);
        getHandler = getGetHandler(cacheClient2, cacheClient3);
        Personalization personalization = getPersonalization(this.firebaseApp, str, this.analyticsConnector);
        if (personalization != null) {
            Objects.requireNonNull(personalization);
            getHandler.addListener(new RemoteConfigComponent$$ExternalSyntheticLambda0(personalization));
        }
        str2 = str;
        return get(this.firebaseApp, str2, this.firebaseInstallations, this.firebaseAbt, this.executor, cacheClient, cacheClient2, cacheClient3, getFetchHandler(str, cacheClient, metadataClient), getHandler, metadataClient, getRolloutsStateSubscriptionsHandler(cacheClient2, cacheClient3));
    }

    /* access modifiers changed from: package-private */
    public synchronized FirebaseRemoteConfig get(FirebaseApp firebaseApp2, String str, FirebaseInstallationsApi firebaseInstallationsApi, FirebaseABTesting firebaseABTesting, Executor executor2, ConfigCacheClient configCacheClient, ConfigCacheClient configCacheClient2, ConfigCacheClient configCacheClient3, ConfigFetchHandler configFetchHandler, ConfigGetParameterHandler configGetParameterHandler, ConfigMetadataClient configMetadataClient, RolloutsStateSubscriptionsHandler rolloutsStateSubscriptionsHandler) {
        FirebaseRemoteConfig firebaseRemoteConfig;
        String str2 = str;
        synchronized (this) {
            if (!this.frcNamespaceInstances.containsKey(str2)) {
                Context context2 = this.context;
                FirebaseABTesting firebaseABTesting2 = isAbtSupported(firebaseApp2, str) ? firebaseABTesting : null;
                FirebaseRemoteConfig firebaseRemoteConfig2 = r10;
                FirebaseRemoteConfig firebaseRemoteConfig3 = new FirebaseRemoteConfig(context2, firebaseApp2, firebaseInstallationsApi, firebaseABTesting2, executor2, configCacheClient, configCacheClient2, configCacheClient3, configFetchHandler, configGetParameterHandler, configMetadataClient, getRealtime(firebaseApp2, firebaseInstallationsApi, configFetchHandler, configCacheClient2, this.context, str, configMetadataClient), rolloutsStateSubscriptionsHandler);
                firebaseRemoteConfig2.startLoadingConfigsFromDisk();
                this.frcNamespaceInstances.put(str2, firebaseRemoteConfig2);
                frcNamespaceInstancesStatic.put(str2, firebaseRemoteConfig2);
            }
            firebaseRemoteConfig = this.frcNamespaceInstances.get(str2);
        }
        return firebaseRemoteConfig;
    }

    public synchronized void setCustomHeaders(Map<String, String> map) {
        this.customHeaders = map;
    }

    private ConfigCacheClient getCacheClient(String str, String str2) {
        return ConfigCacheClient.getInstance(this.executor, ConfigStorageClient.getInstance(this.context, String.format("%s_%s_%s_%s.json", new Object[]{"frc", this.appId, str, str2})));
    }

    /* access modifiers changed from: package-private */
    public ConfigFetchHttpClient getFrcBackendApiClient(String str, String str2, ConfigMetadataClient configMetadataClient) {
        return new ConfigFetchHttpClient(this.context, this.firebaseApp.getOptions().getApplicationId(), str, str2, configMetadataClient.getFetchTimeoutInSeconds(), configMetadataClient.getFetchTimeoutInSeconds());
    }

    /* access modifiers changed from: package-private */
    public synchronized ConfigFetchHandler getFetchHandler(String str, ConfigCacheClient configCacheClient, ConfigMetadataClient configMetadataClient) {
        FirebaseInstallationsApi firebaseInstallationsApi;
        Provider<AnalyticsConnector> provider;
        firebaseInstallationsApi = this.firebaseInstallations;
        if (isPrimaryApp(this.firebaseApp)) {
            provider = this.analyticsConnector;
        } else {
            new RemoteConfigComponent$$ExternalSyntheticLambda2
            /*  JADX ERROR: Method code generation error
                jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0012: CONSTRUCTOR  (r0v7 ? I:com.google.firebase.remoteconfig.RemoteConfigComponent$$ExternalSyntheticLambda2) =  call: com.google.firebase.remoteconfig.RemoteConfigComponent$$ExternalSyntheticLambda2.<init>():void type: CONSTRUCTOR in method: com.google.firebase.remoteconfig.RemoteConfigComponent.getFetchHandler(java.lang.String, com.google.firebase.remoteconfig.internal.ConfigCacheClient, com.google.firebase.remoteconfig.internal.ConfigMetadataClient):com.google.firebase.remoteconfig.internal.ConfigFetchHandler, dex: classes3.dex
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:256)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:221)
                	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:109)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:55)
                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:98)
                	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:156)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:62)
                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:92)
                	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:58)
                	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:211)
                	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:204)
                	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:318)
                	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:271)
                	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$2(ClassGen.java:240)
                	at java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:183)
                	at java.util.ArrayList.forEach(ArrayList.java:1259)
                	at java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                	at java.util.stream.Sink$ChainedReference.end(Sink.java:258)
                	at java.util.stream.AbstractPipeline.copyInto(AbstractPipeline.java:483)
                	at java.util.stream.AbstractPipeline.wrapAndCopyInto(AbstractPipeline.java:472)
                	at java.util.stream.ForEachOps$ForEachOp.evaluateSequential(ForEachOps.java:150)
                	at java.util.stream.ForEachOps$ForEachOp$OfRef.evaluateSequential(ForEachOps.java:173)
                	at java.util.stream.AbstractPipeline.evaluate(AbstractPipeline.java:234)
                	at java.util.stream.ReferencePipeline.forEach(ReferencePipeline.java:485)
                	at jadx.core.codegen.ClassGen.addInnerClsAndMethods(ClassGen.java:236)
                	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:227)
                	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:112)
                	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:78)
                	at jadx.core.codegen.CodeGen.wrapCodeGen(CodeGen.java:44)
                	at jadx.core.codegen.CodeGen.generateJavaCode(CodeGen.java:33)
                	at jadx.core.codegen.CodeGen.generate(CodeGen.java:21)
                	at jadx.core.ProcessClass.generateCode(ProcessClass.java:61)
                	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:273)
                Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r0v7 ?
                	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:189)
                	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:620)
                	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:364)
                	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:250)
                	... 38 more
                */
            /*
                this = this;
                monitor-enter(r11)
                com.google.firebase.remoteconfig.internal.ConfigFetchHandler r10 = new com.google.firebase.remoteconfig.internal.ConfigFetchHandler     // Catch:{ all -> 0x0034 }
                com.google.firebase.installations.FirebaseInstallationsApi r1 = r11.firebaseInstallations     // Catch:{ all -> 0x0034 }
                com.google.firebase.FirebaseApp r0 = r11.firebaseApp     // Catch:{ all -> 0x0034 }
                boolean r0 = isPrimaryApp(r0)     // Catch:{ all -> 0x0034 }
                if (r0 == 0) goto L_0x0010
                com.google.firebase.inject.Provider<com.google.firebase.analytics.connector.AnalyticsConnector> r0 = r11.analyticsConnector     // Catch:{ all -> 0x0034 }
                goto L_0x0015
            L_0x0010:
                com.google.firebase.remoteconfig.RemoteConfigComponent$$ExternalSyntheticLambda2 r0 = new com.google.firebase.remoteconfig.RemoteConfigComponent$$ExternalSyntheticLambda2     // Catch:{ all -> 0x0034 }
                r0.<init>()     // Catch:{ all -> 0x0034 }
            L_0x0015:
                r2 = r0
                java.util.concurrent.ScheduledExecutorService r3 = r11.executor     // Catch:{ all -> 0x0034 }
                com.google.android.gms.common.util.Clock r4 = DEFAULT_CLOCK     // Catch:{ all -> 0x0034 }
                java.util.Random r5 = DEFAULT_RANDOM     // Catch:{ all -> 0x0034 }
                com.google.firebase.FirebaseApp r0 = r11.firebaseApp     // Catch:{ all -> 0x0034 }
                com.google.firebase.FirebaseOptions r0 = r0.getOptions()     // Catch:{ all -> 0x0034 }
                java.lang.String r0 = r0.getApiKey()     // Catch:{ all -> 0x0034 }
                com.google.firebase.remoteconfig.internal.ConfigFetchHttpClient r7 = r11.getFrcBackendApiClient(r0, r12, r14)     // Catch:{ all -> 0x0034 }
                java.util.Map<java.lang.String, java.lang.String> r9 = r11.customHeaders     // Catch:{ all -> 0x0034 }
                r0 = r10
                r6 = r13
                r8 = r14
                r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9)     // Catch:{ all -> 0x0034 }
                monitor-exit(r11)
                return r10
            L_0x0034:
                r12 = move-exception
                monitor-exit(r11)     // Catch:{ all -> 0x0034 }
                throw r12
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.remoteconfig.RemoteConfigComponent.getFetchHandler(java.lang.String, com.google.firebase.remoteconfig.internal.ConfigCacheClient, com.google.firebase.remoteconfig.internal.ConfigMetadataClient):com.google.firebase.remoteconfig.internal.ConfigFetchHandler");
        }

        /* access modifiers changed from: package-private */
        public synchronized ConfigRealtimeHandler getRealtime(FirebaseApp firebaseApp2, FirebaseInstallationsApi firebaseInstallationsApi, ConfigFetchHandler configFetchHandler, ConfigCacheClient configCacheClient, Context context2, String str, ConfigMetadataClient configMetadataClient) {
            ConfigRealtimeHandler configRealtimeHandler;
            synchronized (this) {
                configRealtimeHandler = new ConfigRealtimeHandler(firebaseApp2, firebaseInstallationsApi, configFetchHandler, configCacheClient, context2, str, configMetadataClient, this.executor);
            }
            return configRealtimeHandler;
        }

        private ConfigGetParameterHandler getGetHandler(ConfigCacheClient configCacheClient, ConfigCacheClient configCacheClient2) {
            return new ConfigGetParameterHandler(this.executor, configCacheClient, configCacheClient2);
        }

        static ConfigMetadataClient getMetadataClient(Context context2, String str, String str2) {
            return new ConfigMetadataClient(context2.getSharedPreferences(String.format("%s_%s_%s_%s", new Object[]{"frc", str, str2, PREFERENCES_FILE_NAME}), 0));
        }

        private static Personalization getPersonalization(FirebaseApp firebaseApp2, String str, Provider<AnalyticsConnector> provider) {
            if (!isPrimaryApp(firebaseApp2) || !str.equals(DEFAULT_NAMESPACE)) {
                return null;
            }
            return new Personalization(provider);
        }

        private RolloutsStateSubscriptionsHandler getRolloutsStateSubscriptionsHandler(ConfigCacheClient configCacheClient, ConfigCacheClient configCacheClient2) {
            return new RolloutsStateSubscriptionsHandler(configCacheClient, RolloutsStateFactory.create(configCacheClient, configCacheClient2), this.executor);
        }

        private static boolean isAbtSupported(FirebaseApp firebaseApp2, String str) {
            return str.equals(DEFAULT_NAMESPACE) && isPrimaryApp(firebaseApp2);
        }

        private static boolean isPrimaryApp(FirebaseApp firebaseApp2) {
            return firebaseApp2.getName().equals(FirebaseApp.DEFAULT_APP_NAME);
        }

        /* access modifiers changed from: private */
        public static synchronized void notifyRCInstances(boolean z) {
            synchronized (RemoteConfigComponent.class) {
                for (FirebaseRemoteConfig configUpdateBackgroundState : frcNamespaceInstancesStatic.values()) {
                    configUpdateBackgroundState.setConfigUpdateBackgroundState(z);
                }
            }
        }

        public void registerRolloutsStateSubscriber(String str, RolloutsStateSubscriber rolloutsStateSubscriber) {
            get(str).getRolloutsStateSubscriptionsHandler().registerRolloutsStateSubscriber(rolloutsStateSubscriber);
        }

        private static class GlobalBackgroundListener implements BackgroundDetector.BackgroundStateChangeListener {
            private static final AtomicReference<GlobalBackgroundListener> INSTANCE = new AtomicReference<>();

            private GlobalBackgroundListener() {
            }

            /* access modifiers changed from: private */
            public static void ensureBackgroundListenerIsRegistered(Context context) {
                Application application = (Application) context.getApplicationContext();
                AtomicReference<GlobalBackgroundListener> atomicReference = INSTANCE;
                if (atomicReference.get() == null) {
                    GlobalBackgroundListener globalBackgroundListener = new GlobalBackgroundListener();
                    if (LifecycleKt$$ExternalSyntheticBackportWithForwarding0.m(atomicReference, (Object) null, globalBackgroundListener)) {
                        BackgroundDetector.initialize(application);
                        BackgroundDetector.getInstance().addListener(globalBackgroundListener);
                    }
                }
            }

            public void onBackgroundStateChanged(boolean z) {
                RemoteConfigComponent.notifyRCInstances(z);
            }
        }
    }
