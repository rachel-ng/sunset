package androidx.activity;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.job.JobWorkItem;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.media.session.MediaSession;
import android.os.Bundle;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class ComponentDialog$$ExternalSyntheticApiModelOutline0 {
    public static /* synthetic */ NotificationChannel m(String str, CharSequence charSequence, int i) {
        return new NotificationChannel(str, charSequence, i);
    }

    public static /* synthetic */ JobWorkItem m(Intent intent) {
        return new JobWorkItem(intent);
    }

    public static /* synthetic */ ShortcutInfo.Builder m(Context context, String str) {
        return new ShortcutInfo.Builder(context, str);
    }

    public static /* synthetic */ MediaSession m(Context context, String str, Bundle bundle) {
        return new MediaSession(context, str, bundle);
    }

    public static /* bridge */ /* synthetic */ Class m$1() {
        return Notification.DecoratedCustomViewStyle.class;
    }
}
