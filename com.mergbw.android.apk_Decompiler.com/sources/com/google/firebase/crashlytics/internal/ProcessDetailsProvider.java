package com.google.firebase.crashlytics.internal;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Process;
import androidx.work.Constraints$Builder$$ExternalSyntheticApiModelOutline0;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.io.path.PathTreeWalk$$ExternalSyntheticApiModelOutline0;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J.\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\b2\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0007J\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000fJ\b\u0010\u0011\u001a\u00020\u0006H\u0002¨\u0006\u0012"}, d2 = {"Lcom/google/firebase/crashlytics/internal/ProcessDetailsProvider;", "", "()V", "buildProcessDetails", "Lcom/google/firebase/crashlytics/internal/model/CrashlyticsReport$Session$Event$Application$ProcessDetails;", "processName", "", "pid", "", "importance", "isDefaultProcess", "", "getAppProcessDetails", "", "context", "Landroid/content/Context;", "getCurrentProcessDetails", "getProcessName", "com.google.firebase-firebase-crashlytics"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ProcessDetailsProvider.kt */
public final class ProcessDetailsProvider {
    public static final ProcessDetailsProvider INSTANCE = new ProcessDetailsProvider();

    public final CrashlyticsReport.Session.Event.Application.ProcessDetails buildProcessDetails(String str) {
        Intrinsics.checkNotNullParameter(str, "processName");
        return buildProcessDetails$default(this, str, 0, 0, false, 14, (Object) null);
    }

    public final CrashlyticsReport.Session.Event.Application.ProcessDetails buildProcessDetails(String str, int i) {
        Intrinsics.checkNotNullParameter(str, "processName");
        return buildProcessDetails$default(this, str, i, 0, false, 12, (Object) null);
    }

    public final CrashlyticsReport.Session.Event.Application.ProcessDetails buildProcessDetails(String str, int i, int i2) {
        Intrinsics.checkNotNullParameter(str, "processName");
        return buildProcessDetails$default(this, str, i, i2, false, 8, (Object) null);
    }

    private ProcessDetailsProvider() {
    }

    public final List<CrashlyticsReport.Session.Event.Application.ProcessDetails> getAppProcessDetails(Context context) {
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
            arrayList2.add(CrashlyticsReport.Session.Event.Application.ProcessDetails.builder().setProcessName(runningAppProcessInfo.processName).setPid(runningAppProcessInfo.pid).setImportance(runningAppProcessInfo.importance).setDefaultProcess(Intrinsics.areEqual((Object) runningAppProcessInfo.processName, (Object) str)).build());
        }
        return (List) arrayList2;
    }

    public final CrashlyticsReport.Session.Event.Application.ProcessDetails getCurrentProcessDetails(Context context) {
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
            if (((CrashlyticsReport.Session.Event.Application.ProcessDetails) obj).getPid() == myPid) {
                break;
            }
        }
        CrashlyticsReport.Session.Event.Application.ProcessDetails processDetails = (CrashlyticsReport.Session.Event.Application.ProcessDetails) obj;
        if (processDetails != null) {
            return processDetails;
        }
        return buildProcessDetails$default(this, getProcessName(), myPid, 0, false, 12, (Object) null);
    }

    public static /* synthetic */ CrashlyticsReport.Session.Event.Application.ProcessDetails buildProcessDetails$default(ProcessDetailsProvider processDetailsProvider, String str, int i, int i2, boolean z, int i3, Object obj) {
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

    public final CrashlyticsReport.Session.Event.Application.ProcessDetails buildProcessDetails(String str, int i, int i2, boolean z) {
        Intrinsics.checkNotNullParameter(str, "processName");
        CrashlyticsReport.Session.Event.Application.ProcessDetails build = CrashlyticsReport.Session.Event.Application.ProcessDetails.builder().setProcessName(str).setPid(i).setImportance(i2).setDefaultProcess(z).build();
        Intrinsics.checkNotNullExpressionValue(build, "builder()\n      .setProc…ltProcess)\n      .build()");
        return build;
    }

    private final String getProcessName() {
        String m;
        if (Build.VERSION.SDK_INT >= 33) {
            String m2 = PathTreeWalk$$ExternalSyntheticApiModelOutline0.m();
            Intrinsics.checkNotNullExpressionValue(m2, "{\n      Process.myProcessName()\n    }");
            return m2;
        } else if (Build.VERSION.SDK_INT < 28 || (m = Constraints$Builder$$ExternalSyntheticApiModelOutline0.m()) == null) {
            return "";
        } else {
            return m;
        }
    }
}
