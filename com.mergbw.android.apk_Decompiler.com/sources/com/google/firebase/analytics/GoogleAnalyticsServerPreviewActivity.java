package com.google.firebase.analytics;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.internal.measurement.zzdq;

/* compiled from: com.google.android.gms:play-services-measurement-api@@22.0.2 */
public class GoogleAnalyticsServerPreviewActivity extends Activity {
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        zzdq.zza((Context) this).zza(getIntent());
        finish();
    }
}
