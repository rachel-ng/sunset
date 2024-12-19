package com.google.firebase.sessions;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import androidx.work.Constraints$Builder$$ExternalSyntheticApiModelOutline0;
import com.google.android.gms.common.util.ProcessUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.io.path.PathTreeWalk$$ExternalSyntheticApiModelOutline0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J.\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0002J\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000fJ\r\u0010\u0011\u001a\u00020\u0006H\u0000¢\u0006\u0002\b\u0012¨\u0006\u0013"}, d2 = {"Lcom/google/firebase/sessions/ProcessDetailsProvider;", "", "()V", "buildProcessDetails", "Lcom/google/firebase/sessions/ProcessDetails;", "processName", "", "pid", "", "importance", "isDefaultProcess", "", "getAppProcessDetails", "", "context", "Landroid/content/Context;", "getCurrentProcessDetails", "getProcessName", "getProcessName$com_google_firebase_firebase_sessions", "com.google.firebase-firebase-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ProcessDetailsProvider.kt */
public final class ProcessDetailsProvider {
    public static final ProcessDetailsProvider INSTANCE = new ProcessDetailsProvider();

    private ProcessDetailsProvider() {
    }

    public final List<ProcessDetails> getAppProcessDetails(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        int i = context.getApplicationInfo().uid;
        String str = context.getApplicationInfo().processName;
        Object systemService = context.getSystemService("activity");
        List<ActivityManager.RunningAppProcessInfo> list = null;
        ActivityManager activityManager = systemService instanceof ActivityManager ? (ActivityManager) systemService : null;
        if (activityManager != null) {
            list = activityManager.getRunningAppProcesses();
        }
        if (list == null) {
            list = CollectionsKt.emptyList();
        }
        Collection arrayList = new ArrayList();
        for (Object next : CollectionsKt.filterNotNull(list)) {
            if (((ActivityManager.RunningAppProcessInfo) next).uid == i) {
                arrayList.add(next);
            }
        }
        Iterable<ActivityManager.RunningAppProcessInfo> iterable = (List) arrayList;
        Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : iterable) {
            String str2 = runningAppProcessInfo.processName;
            Intrinsics.checkNotNullExpressionValue(str2, "runningAppProcessInfo.processName");
            arrayList2.add(new ProcessDetails(str2, runningAppProcessInfo.pid, runningAppProcessInfo.importance, Intrinsics.areEqual((Object) runningAppProcessInfo.processName, (Object) str)));
        }
        return (List) arrayList2;
    }

    public final ProcessDetails getCurrentProcessDetails(Context context) {
        Object obj;
        Intrinsics.checkNotNullParameter(context, "context");
        int myPid = Process.myPid();
        Iterator it = getAppProcessDetails(context).iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (((ProcessDetails) obj).getPid() == myPid) {
                break;
            }
        }
        ProcessDetails processDetails = (ProcessDetails) obj;
        if (processDetails != null) {
            return processDetails;
        }
        return buildProcessDetails$default(this, getProcessName$com_google_firebase_firebase_sessions(), myPid, 0, false, 12, (Object) null);
    }

    static /* synthetic */ ProcessDetails buildProcessDetails$default(ProcessDetailsProvider processDetailsProvider, String str, int i, int i2, boolean z, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = 0;
        }
        if ((i3 & 8) != 0) {
            z = false;
        }
        return processDetailsProvider.buildProcessDetails(str, i, i2, z);
    }

    private final ProcessDetails buildProcessDetails(String str, int i, int i2, boolean z) {
        return new ProcessDetails(str, i, i2, z);
    }

    public final String getProcessName$com_google_firebase_firebase_sessions() {
        String m;
        if (Build.VERSION.SDK_INT >= 33) {
            String m2 = PathTreeWalk$$ExternalSyntheticApiModelOutline0.m();
            Intrinsics.checkNotNullExpressionValue(m2, "myProcessName()");
            return m2;
        } else if (Build.VERSION.SDK_INT >= 28 && (m = Constraints$Builder$$ExternalSyntheticApiModelOutline0.m()) != null) {
            return m;
        } else {
            String myProcessName = ProcessUtils.getMyProcessName();
            if (myProcessName != null) {
                return myProcessName;
            }
            return "";
        }
    }
}
