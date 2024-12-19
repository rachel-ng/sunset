package com.google.firebase.crashlytics.internal;

import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import com.google.firebase.remoteconfig.interop.rollouts.RolloutAssignment;
import com.google.firebase.remoteconfig.interop.rollouts.RolloutsState;
import com.google.firebase.remoteconfig.interop.rollouts.RolloutsStateSubscriber;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/google/firebase/crashlytics/internal/CrashlyticsRemoteConfigListener;", "Lcom/google/firebase/remoteconfig/interop/rollouts/RolloutsStateSubscriber;", "userMetadata", "Lcom/google/firebase/crashlytics/internal/metadata/UserMetadata;", "(Lcom/google/firebase/crashlytics/internal/metadata/UserMetadata;)V", "onRolloutsStateChanged", "", "rolloutsState", "Lcom/google/firebase/remoteconfig/interop/rollouts/RolloutsState;", "com.google.firebase-firebase-crashlytics"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CrashlyticsRemoteConfigListener.kt */
public final class CrashlyticsRemoteConfigListener implements RolloutsStateSubscriber {
    private final UserMetadata userMetadata;

    public CrashlyticsRemoteConfigListener(UserMetadata userMetadata2) {
        Intrinsics.checkNotNullParameter(userMetadata2, "userMetadata");
        this.userMetadata = userMetadata2;
    }

    public void onRolloutsStateChanged(RolloutsState rolloutsState) {
        Intrinsics.checkNotNullParameter(rolloutsState, "rolloutsState");
        UserMetadata userMetadata2 = this.userMetadata;
        Set<RolloutAssignment> rolloutAssignments = rolloutsState.getRolloutAssignments();
        Intrinsics.checkNotNullExpressionValue(rolloutAssignments, "rolloutsState.rolloutAssignments");
        Iterable<RolloutAssignment> iterable = rolloutAssignments;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (RolloutAssignment rolloutAssignment : iterable) {
            arrayList.add(com.google.firebase.crashlytics.internal.metadata.RolloutAssignment.create(rolloutAssignment.getRolloutId(), rolloutAssignment.getParameterKey(), rolloutAssignment.getParameterValue(), rolloutAssignment.getVariantId(), rolloutAssignment.getTemplateVersion()));
        }
        userMetadata2.updateRolloutsState((List) arrayList);
        Logger.getLogger().d("Updated Crashlytics Rollout State");
    }
}
