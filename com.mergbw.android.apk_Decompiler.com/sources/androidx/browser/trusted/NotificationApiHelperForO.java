package androidx.browser.trusted;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import androidx.activity.ComponentDialog$$ExternalSyntheticApiModelOutline0;

class NotificationApiHelperForO {
    static boolean isChannelEnabled(NotificationManager notificationManager, String str) {
        NotificationChannel m = notificationManager.getNotificationChannel(str);
        return m == null || m.getImportance() != 0;
    }

    static Notification copyNotificationOntoChannel(Context context, NotificationManager notificationManager, Notification notification, String str, String str2) {
        notificationManager.createNotificationChannel(ComponentDialog$$ExternalSyntheticApiModelOutline0.m(str, (CharSequence) str2, 3));
        if (notificationManager.getNotificationChannel(str).getImportance() == 0) {
            return null;
        }
        Notification.Builder m = Notification.Builder.recoverBuilder(context, notification);
        Notification.Builder unused = m.setChannelId(str);
        return m.build();
    }

    private NotificationApiHelperForO() {
    }
}
