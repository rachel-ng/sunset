package com.google.firebase.sessions;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000[\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\r\b\u0000\u0018\u0000 $2\u00020\u0001:\u0002#$B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u000f\u001a\u00020\u0010J\u000e\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0013J\u000e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00070\u0015H\u0002J\u0006\u0010\u0016\u001a\u00020\u0010J \u0010\u0017\u001a\u0004\u0018\u00010\u00072\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00070\u00192\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0010\u0010\u001c\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u0007H\u0002J\u0010\u0010\u001e\u001a\u00020\u00102\u0006\u0010\u001f\u001a\u00020\u001bH\u0002J\u0016\u0010 \u001a\u00020!2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00070\u0019H\u0003J\u0010\u0010\"\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u0007H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0004\n\u0002\u0010\u000e¨\u0006%"}, d2 = {"Lcom/google/firebase/sessions/SessionLifecycleClient;", "", "backgroundDispatcher", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlin/coroutines/CoroutineContext;)V", "queuedMessages", "Ljava/util/concurrent/LinkedBlockingDeque;", "Landroid/os/Message;", "service", "Landroid/os/Messenger;", "serviceBound", "", "serviceConnection", "com/google/firebase/sessions/SessionLifecycleClient$serviceConnection$1", "Lcom/google/firebase/sessions/SessionLifecycleClient$serviceConnection$1;", "backgrounded", "", "bindToService", "sessionLifecycleServiceBinder", "Lcom/google/firebase/sessions/SessionLifecycleServiceBinder;", "drainQueue", "", "foregrounded", "getLatestByCode", "messages", "", "msgCode", "", "queueMessage", "msg", "sendLifecycleEvent", "messageCode", "sendLifecycleEvents", "Lkotlinx/coroutines/Job;", "sendMessageToServer", "ClientUpdateHandler", "Companion", "com.google.firebase-firebase-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: SessionLifecycleClient.kt */
public final class SessionLifecycleClient {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final int MAX_QUEUED_MESSAGES = 20;
    public static final String TAG = "SessionLifecycleClient";
    private final CoroutineContext backgroundDispatcher;
    /* access modifiers changed from: private */
    public final LinkedBlockingDeque<Message> queuedMessages = new LinkedBlockingDeque<>(20);
    /* access modifiers changed from: private */
    public Messenger service;
    /* access modifiers changed from: private */
    public boolean serviceBound;
    private final SessionLifecycleClient$serviceConnection$1 serviceConnection = new SessionLifecycleClient$serviceConnection$1(this);

    public SessionLifecycleClient(CoroutineContext coroutineContext) {
        Intrinsics.checkNotNullParameter(coroutineContext, "backgroundDispatcher");
        this.backgroundDispatcher = coroutineContext;
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/google/firebase/sessions/SessionLifecycleClient$ClientUpdateHandler;", "Landroid/os/Handler;", "backgroundDispatcher", "Lkotlin/coroutines/CoroutineContext;", "(Lkotlin/coroutines/CoroutineContext;)V", "handleMessage", "", "msg", "Landroid/os/Message;", "handleSessionUpdate", "sessionId", "", "com.google.firebase-firebase-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: SessionLifecycleClient.kt */
    public static final class ClientUpdateHandler extends Handler {
        private final CoroutineContext backgroundDispatcher;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public ClientUpdateHandler(CoroutineContext coroutineContext) {
            super(Looper.getMainLooper());
            Intrinsics.checkNotNullParameter(coroutineContext, "backgroundDispatcher");
            this.backgroundDispatcher = coroutineContext;
        }

        public void handleMessage(Message message) {
            String str;
            Intrinsics.checkNotNullParameter(message, NotificationCompat.CATEGORY_MESSAGE);
            if (message.what == 3) {
                Bundle data = message.getData();
                if (data == null || (str = data.getString(SessionLifecycleService.SESSION_UPDATE_EXTRA)) == null) {
                    str = "";
                }
                handleSessionUpdate(str);
                return;
            }
            Log.w(SessionLifecycleClient.TAG, "Received unexpected event from the SessionLifecycleService: " + message);
            super.handleMessage(message);
        }

        private final void handleSessionUpdate(String str) {
            Log.d(SessionLifecycleClient.TAG, "Session update received: " + str);
            Job unused = BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(this.backgroundDispatcher), (CoroutineContext) null, (CoroutineStart) null, new SessionLifecycleClient$ClientUpdateHandler$handleSessionUpdate$1(str, (Continuation<? super SessionLifecycleClient$ClientUpdateHandler$handleSessionUpdate$1>) null), 3, (Object) null);
        }
    }

    public final void bindToService(SessionLifecycleServiceBinder sessionLifecycleServiceBinder) {
        Intrinsics.checkNotNullParameter(sessionLifecycleServiceBinder, "sessionLifecycleServiceBinder");
        sessionLifecycleServiceBinder.bindToService(new Messenger(new ClientUpdateHandler(this.backgroundDispatcher)), this.serviceConnection);
    }

    public final void foregrounded() {
        sendLifecycleEvent(1);
    }

    public final void backgrounded() {
        sendLifecycleEvent(2);
    }

    private final void sendLifecycleEvent(int i) {
        List<Message> drainQueue = drainQueue();
        Message obtain = Message.obtain((Handler) null, i, 0, 0);
        Intrinsics.checkNotNullExpressionValue(obtain, "obtain(null, messageCode, 0, 0)");
        drainQueue.add(obtain);
        sendLifecycleEvents(drainQueue);
    }

    /* access modifiers changed from: private */
    public final Job sendLifecycleEvents(List<Message> list) {
        return BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(this.backgroundDispatcher), (CoroutineContext) null, (CoroutineStart) null, new SessionLifecycleClient$sendLifecycleEvents$1(this, list, (Continuation<? super SessionLifecycleClient$sendLifecycleEvents$1>) null), 3, (Object) null);
    }

    /* access modifiers changed from: private */
    public final void sendMessageToServer(Message message) {
        if (this.service != null) {
            try {
                Log.d(TAG, "Sending lifecycle " + message.what + " to service");
                Messenger messenger = this.service;
                if (messenger != null) {
                    messenger.send(message);
                }
            } catch (RemoteException e) {
                Log.w(TAG, "Unable to deliver message: " + message.what, e);
                queueMessage(message);
            }
        } else {
            queueMessage(message);
        }
    }

    private final void queueMessage(Message message) {
        if (this.queuedMessages.offer(message)) {
            Log.d(TAG, "Queued message " + message.what + ". Queue size " + this.queuedMessages.size());
            return;
        }
        Log.d(TAG, "Failed to enqueue message " + message.what + ". Dropping.");
    }

    /* access modifiers changed from: private */
    public final List<Message> drainQueue() {
        List<Message> arrayList = new ArrayList<>();
        this.queuedMessages.drainTo(arrayList);
        return arrayList;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v5, resolved type: android.os.Message} */
    /* access modifiers changed from: private */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.os.Message getLatestByCode(java.util.List<android.os.Message> r7, int r8) {
        /*
            r6 = this;
            java.lang.Iterable r7 = (java.lang.Iterable) r7
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.Collection r0 = (java.util.Collection) r0
            java.util.Iterator r7 = r7.iterator()
        L_0x000d:
            boolean r1 = r7.hasNext()
            if (r1 == 0) goto L_0x0022
            java.lang.Object r1 = r7.next()
            r2 = r1
            android.os.Message r2 = (android.os.Message) r2
            int r2 = r2.what
            if (r2 != r8) goto L_0x000d
            r0.add(r1)
            goto L_0x000d
        L_0x0022:
            java.util.List r0 = (java.util.List) r0
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.Iterator r7 = r0.iterator()
            boolean r8 = r7.hasNext()
            if (r8 != 0) goto L_0x0032
            r7 = 0
            goto L_0x005d
        L_0x0032:
            java.lang.Object r8 = r7.next()
            boolean r0 = r7.hasNext()
            if (r0 != 0) goto L_0x003e
        L_0x003c:
            r7 = r8
            goto L_0x005d
        L_0x003e:
            r0 = r8
            android.os.Message r0 = (android.os.Message) r0
            long r0 = r0.getWhen()
        L_0x0045:
            java.lang.Object r2 = r7.next()
            r3 = r2
            android.os.Message r3 = (android.os.Message) r3
            long r3 = r3.getWhen()
            int r5 = (r0 > r3 ? 1 : (r0 == r3 ? 0 : -1))
            if (r5 >= 0) goto L_0x0056
            r8 = r2
            r0 = r3
        L_0x0056:
            boolean r2 = r7.hasNext()
            if (r2 != 0) goto L_0x0045
            goto L_0x003c
        L_0x005d:
            android.os.Message r7 = (android.os.Message) r7
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.sessions.SessionLifecycleClient.getLatestByCode(java.util.List, int):android.os.Message");
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006XT¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lcom/google/firebase/sessions/SessionLifecycleClient$Companion;", "", "()V", "MAX_QUEUED_MESSAGES", "", "TAG", "", "com.google.firebase-firebase-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: SessionLifecycleClient.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
