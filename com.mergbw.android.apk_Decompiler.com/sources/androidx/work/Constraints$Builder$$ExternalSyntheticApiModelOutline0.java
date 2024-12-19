package androidx.work;

import android.app.job.JobInfo;
import android.net.Uri;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/* compiled from: D8$$SyntheticClass */
public final /* synthetic */ class Constraints$Builder$$ExternalSyntheticApiModelOutline0 {
    public static /* synthetic */ JobInfo.TriggerContentUri m(Uri uri, int i) {
        return new JobInfo.TriggerContentUri(uri, i);
    }

    public static /* bridge */ /* synthetic */ Class m$1() {
        return ZonedDateTime.class;
    }

    public static /* bridge */ /* synthetic */ boolean m$1(Object obj) {
        return obj instanceof LocalDateTime;
    }

    public static /* bridge */ /* synthetic */ Class m$2() {
        return OffsetDateTime.class;
    }

    public static /* bridge */ /* synthetic */ Class m$3() {
        return OffsetTime.class;
    }

    public static /* bridge */ /* synthetic */ Class m$4() {
        return ZoneId.class;
    }

    public static /* bridge */ /* synthetic */ Class m$5() {
        return Period.class;
    }

    public static /* bridge */ /* synthetic */ Class m$6() {
        return Duration.class;
    }

    public static /* bridge */ /* synthetic */ Class m$7() {
        return Instant.class;
    }

    public static /* bridge */ /* synthetic */ Class m$8() {
        return LocalDate.class;
    }
}
