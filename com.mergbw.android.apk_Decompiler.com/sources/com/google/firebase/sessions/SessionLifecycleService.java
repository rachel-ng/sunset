package com.google.firebase.sessions;

import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import androidx.core.app.NotificationCompat;
import com.google.firebase.sessions.settings.SessionsSettings;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.Duration;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 \u00132\u00020\u0001:\u0002\u0013\u0014B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0014\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0011H\u0016R\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/google/firebase/sessions/SessionLifecycleService;", "Landroid/app/Service;", "()V", "handlerThread", "Landroid/os/HandlerThread;", "getHandlerThread$com_google_firebase_firebase_sessions", "()Landroid/os/HandlerThread;", "messageHandler", "Lcom/google/firebase/sessions/SessionLifecycleService$MessageHandler;", "messenger", "Landroid/os/Messenger;", "getClientCallback", "intent", "Landroid/content/Intent;", "onBind", "Landroid/os/IBinder;", "onCreate", "", "onDestroy", "Companion", "MessageHandler", "com.google.firebase-firebase-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: SessionLifecycleService.kt */
public final class SessionLifecycleService extends Service {
    public static final int BACKGROUNDED = 2;
    private static final int CLIENT_BOUND = 4;
    public static final String CLIENT_CALLBACK_MESSENGER = "ClientCallbackMessenger";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final int FOREGROUNDED = 1;
    public static final int SESSION_UPDATED = 3;
    public static final String SESSION_UPDATE_EXTRA = "SessionUpdateExtra";
    public static final String TAG = "SessionLifecycleService";
    private final HandlerThread handlerThread = new HandlerThread("FirebaseSessions_HandlerThread");
    private MessageHandler messageHandler;
    private Messenger messenger;

    public final HandlerThread getHandlerThread$com_google_firebase_firebase_sessions() {
        return this.handlerThread;
    }

    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0010\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0010\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0010\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010\u0015\u001a\u00020\n2\u0006\u0010\u0016\u001a\u00020\fH\u0002J\u0010\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u0007H\u0002J\b\u0010\u0019\u001a\u00020\u000eH\u0002J\u0018\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u001b\u001a\u00020\u001cH\u0002R\u001e\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/google/firebase/sessions/SessionLifecycleService$MessageHandler;", "Landroid/os/Handler;", "looper", "Landroid/os/Looper;", "(Landroid/os/Looper;)V", "boundClients", "Ljava/util/ArrayList;", "Landroid/os/Messenger;", "Lkotlin/collections/ArrayList;", "hasForegrounded", "", "lastMsgTimeMs", "", "broadcastSession", "", "handleBackgrounding", "msg", "Landroid/os/Message;", "handleClientBound", "handleForegrounding", "handleMessage", "isSessionRestart", "foregroundTimeMs", "maybeSendSessionToClient", "client", "newSession", "sendSessionToClient", "sessionId", "", "com.google.firebase-firebase-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: SessionLifecycleService.kt */
    public static final class MessageHandler extends Handler {
        private final ArrayList<Messenger> boundClients = new ArrayList<>();
        private boolean hasForegrounded;
        private long lastMsgTimeMs;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public MessageHandler(Looper looper) {
            super(looper);
            Intrinsics.checkNotNullParameter(looper, "looper");
        }

        public void handleMessage(Message message) {
            Intrinsics.checkNotNullParameter(message, NotificationCompat.CATEGORY_MESSAGE);
            if (this.lastMsgTimeMs > message.getWhen()) {
                Log.d(SessionLifecycleService.TAG, "Ignoring old message from " + message.getWhen() + " which is older than " + this.lastMsgTimeMs + '.');
                return;
            }
            int i = message.what;
            if (i == 1) {
                handleForegrounding(message);
            } else if (i == 2) {
                handleBackgrounding(message);
            } else if (i != 4) {
                Log.w(SessionLifecycleService.TAG, "Received unexpected event from the SessionLifecycleClient: " + message);
                super.handleMessage(message);
            } else {
                handleClientBound(message);
            }
        }

        private final void handleForegrounding(Message message) {
            Log.d(SessionLifecycleService.TAG, "Activity foregrounding at " + message.getWhen() + '.');
            if (!this.hasForegrounded) {
                Log.d(SessionLifecycleService.TAG, "Cold start detected.");
                this.hasForegrounded = true;
                newSession();
            } else if (isSessionRestart(message.getWhen())) {
                Log.d(SessionLifecycleService.TAG, "Session too long in background. Creating new session.");
                newSession();
            }
            this.lastMsgTimeMs = message.getWhen();
        }

        private final void handleBackgrounding(Message message) {
            Log.d(SessionLifecycleService.TAG, "Activity backgrounding at " + message.getWhen());
            this.lastMsgTimeMs = message.getWhen();
        }

        private final void handleClientBound(Message message) {
            this.boundClients.add(message.replyTo);
            Messenger messenger = message.replyTo;
            Intrinsics.checkNotNullExpressionValue(messenger, "msg.replyTo");
            maybeSendSessionToClient(messenger);
            Log.d(SessionLifecycleService.TAG, "Client " + message.replyTo + " bound at " + message.getWhen() + ". Clients: " + this.boundClients.size());
        }

        private final void newSession() {
            SessionGenerator.Companion.getInstance().generateNewSession();
            Log.d(SessionLifecycleService.TAG, "Generated new session " + SessionGenerator.Companion.getInstance().getCurrentSession().getSessionId());
            broadcastSession();
            SessionDatastore.Companion.getInstance().updateSessionId(SessionGenerator.Companion.getInstance().getCurrentSession().getSessionId());
        }

        private final void broadcastSession() {
            Log.d(SessionLifecycleService.TAG, "Broadcasting new session: " + SessionGenerator.Companion.getInstance().getCurrentSession());
            SessionFirelogPublisher.Companion.getInstance().logSession(SessionGenerator.Companion.getInstance().getCurrentSession());
            for (Messenger messenger : new ArrayList(this.boundClients)) {
                Intrinsics.checkNotNullExpressionValue(messenger, "it");
                maybeSendSessionToClient(messenger);
            }
        }

        private final void maybeSendSessionToClient(Messenger messenger) {
            if (this.hasForegrounded) {
                sendSessionToClient(messenger, SessionGenerator.Companion.getInstance().getCurrentSession().getSessionId());
                return;
            }
            String currentSessionId = SessionDatastore.Companion.getInstance().getCurrentSessionId();
            Log.d(SessionLifecycleService.TAG, "App has not yet foregrounded. Using previously stored session: " + currentSessionId);
            if (currentSessionId != null) {
                sendSessionToClient(messenger, currentSessionId);
            }
        }

        private final void sendSessionToClient(Messenger messenger, String str) {
            try {
                Bundle bundle = new Bundle();
                bundle.putString(SessionLifecycleService.SESSION_UPDATE_EXTRA, str);
                Message obtain = Message.obtain((Handler) null, 3, 0, 0);
                obtain.setData(bundle);
                messenger.send(obtain);
            } catch (DeadObjectException unused) {
                Log.d(SessionLifecycleService.TAG, "Removing dead client from list: " + messenger);
                this.boundClients.remove(messenger);
            } catch (Exception e) {
                Log.w(SessionLifecycleService.TAG, "Unable to push new session to " + messenger + '.', e);
            }
        }

        private final boolean isSessionRestart(long j) {
            return j - this.lastMsgTimeMs > Duration.m170getInWholeMillisecondsimpl(SessionsSettings.Companion.getInstance().m687getSessionRestartTimeoutUwyO8pc());
        }
    }

    public void onCreate() {
        super.onCreate();
        this.handlerThread.start();
        Looper looper = this.handlerThread.getLooper();
        Intrinsics.checkNotNullExpressionValue(looper, "handlerThread.looper");
        this.messageHandler = new MessageHandler(looper);
        this.messenger = new Messenger(this.messageHandler);
    }

    public IBinder onBind(Intent intent) {
        if (intent == null) {
            Log.d(TAG, "Service bound with null intent. Ignoring.");
            return null;
        }
        Log.d(TAG, "Service bound to new client on process " + intent.getAction());
        Messenger clientCallback = getClientCallback(intent);
        if (clientCallback != null) {
            Message obtain = Message.obtain((Handler) null, 4, 0, 0);
            obtain.replyTo = clientCallback;
            MessageHandler messageHandler2 = this.messageHandler;
            if (messageHandler2 != null) {
                messageHandler2.sendMessage(obtain);
            }
        }
        Messenger messenger2 = this.messenger;
        if (messenger2 != null) {
            return messenger2.getBinder();
        }
        return null;
    }

    public void onDestroy() {
        super.onDestroy();
        this.handlerThread.quit();
    }

    private final Messenger getClientCallback(Intent intent) {
        if (Build.VERSION.SDK_INT >= 33) {
            return (Messenger) intent.getParcelableExtra(CLIENT_CALLBACK_MESSENGER, Messenger.class);
        }
        return (Messenger) intent.getParcelableExtra(CLIENT_CALLBACK_MESSENGER);
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0007XT¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/google/firebase/sessions/SessionLifecycleService$Companion;", "", "()V", "BACKGROUNDED", "", "CLIENT_BOUND", "CLIENT_CALLBACK_MESSENGER", "", "FOREGROUNDED", "SESSION_UPDATED", "SESSION_UPDATE_EXTRA", "TAG", "com.google.firebase-firebase-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: SessionLifecycleService.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
