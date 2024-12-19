package androidx.core.provider;

import android.content.ContentProviderClient;
import android.content.ContentUris;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.content.pm.Signature;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.CancellationSignal;
import android.os.RemoteException;
import android.util.Log;
import androidx.core.content.res.FontResourcesParserCompat;
import androidx.core.provider.FontsContractCompat;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class FontProvider {
    private static final Comparator<byte[]> sByteArrayComparator = new FontProvider$$ExternalSyntheticLambda0();

    private FontProvider() {
    }

    static FontsContractCompat.FontFamilyResult getFontFamilyResult(Context context, FontRequest fontRequest, CancellationSignal cancellationSignal) throws PackageManager.NameNotFoundException {
        ProviderInfo provider = getProvider(context.getPackageManager(), fontRequest, context.getResources());
        if (provider == null) {
            return FontsContractCompat.FontFamilyResult.create(1, (FontsContractCompat.FontInfo[]) null);
        }
        return FontsContractCompat.FontFamilyResult.create(0, query(context, fontRequest, provider.authority, cancellationSignal));
    }

    static ProviderInfo getProvider(PackageManager packageManager, FontRequest fontRequest, Resources resources) throws PackageManager.NameNotFoundException {
        String providerAuthority = fontRequest.getProviderAuthority();
        ProviderInfo resolveContentProvider = packageManager.resolveContentProvider(providerAuthority, 0);
        if (resolveContentProvider == null) {
            throw new PackageManager.NameNotFoundException("No package found for authority: " + providerAuthority);
        } else if (resolveContentProvider.packageName.equals(fontRequest.getProviderPackage())) {
            List<byte[]> convertToByteArrayList = convertToByteArrayList(packageManager.getPackageInfo(resolveContentProvider.packageName, 64).signatures);
            Collections.sort(convertToByteArrayList, sByteArrayComparator);
            List<List<byte[]>> certificates = getCertificates(fontRequest, resources);
            for (int i = 0; i < certificates.size(); i++) {
                ArrayList arrayList = new ArrayList(certificates.get(i));
                Collections.sort(arrayList, sByteArrayComparator);
                if (equalsByteArrayList(convertToByteArrayList, arrayList)) {
                    return resolveContentProvider;
                }
            }
            return null;
        } else {
            throw new PackageManager.NameNotFoundException("Found content provider " + providerAuthority + ", but package was not " + fontRequest.getProviderPackage());
        }
    }

    /* JADX INFO: finally extract failed */
    static FontsContractCompat.FontInfo[] query(Context context, FontRequest fontRequest, String str, CancellationSignal cancellationSignal) {
        ArrayList arrayList;
        Uri uri;
        boolean z;
        String str2 = str;
        ArrayList arrayList2 = new ArrayList();
        Uri build = new Uri.Builder().scheme(FirebaseAnalytics.Param.CONTENT).authority(str2).build();
        Uri build2 = new Uri.Builder().scheme(FirebaseAnalytics.Param.CONTENT).authority(str2).appendPath("file").build();
        ContentQueryWrapper make = ContentQueryWrapper.CC.make(context, build);
        Cursor cursor = null;
        try {
            Cursor query = make.query(build, new String[]{"_id", FontsContractCompat.Columns.FILE_ID, FontsContractCompat.Columns.TTC_INDEX, FontsContractCompat.Columns.VARIATION_SETTINGS, FontsContractCompat.Columns.WEIGHT, FontsContractCompat.Columns.ITALIC, FontsContractCompat.Columns.RESULT_CODE}, "query = ?", new String[]{fontRequest.getQuery()}, (String) null, cancellationSignal);
            if (query != null && query.getCount() > 0) {
                int columnIndex = query.getColumnIndex(FontsContractCompat.Columns.RESULT_CODE);
                ArrayList arrayList3 = new ArrayList();
                int columnIndex2 = query.getColumnIndex("_id");
                int columnIndex3 = query.getColumnIndex(FontsContractCompat.Columns.FILE_ID);
                int columnIndex4 = query.getColumnIndex(FontsContractCompat.Columns.TTC_INDEX);
                int columnIndex5 = query.getColumnIndex(FontsContractCompat.Columns.WEIGHT);
                int columnIndex6 = query.getColumnIndex(FontsContractCompat.Columns.ITALIC);
                while (query.moveToNext()) {
                    int i = columnIndex != -1 ? query.getInt(columnIndex) : 0;
                    int i2 = columnIndex4 != -1 ? query.getInt(columnIndex4) : 0;
                    if (columnIndex3 == -1) {
                        arrayList = arrayList3;
                        uri = ContentUris.withAppendedId(build, query.getLong(columnIndex2));
                    } else {
                        arrayList = arrayList3;
                        uri = ContentUris.withAppendedId(build2, query.getLong(columnIndex3));
                    }
                    int i3 = columnIndex5 != -1 ? query.getInt(columnIndex5) : 400;
                    if (columnIndex6 != -1) {
                        z = true;
                        if (query.getInt(columnIndex6) == 1) {
                            FontsContractCompat.FontInfo create = FontsContractCompat.FontInfo.create(uri, i2, i3, z, i);
                            arrayList3 = arrayList;
                            arrayList3.add(create);
                        }
                    }
                    z = false;
                    FontsContractCompat.FontInfo create2 = FontsContractCompat.FontInfo.create(uri, i2, i3, z, i);
                    arrayList3 = arrayList;
                    arrayList3.add(create2);
                }
                arrayList2 = arrayList3;
            }
            if (query != null) {
                query.close();
            }
            make.close();
            return (FontsContractCompat.FontInfo[]) arrayList2.toArray(new FontsContractCompat.FontInfo[0]);
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            make.close();
            throw th;
        }
    }

    private static List<List<byte[]>> getCertificates(FontRequest fontRequest, Resources resources) {
        if (fontRequest.getCertificates() != null) {
            return fontRequest.getCertificates();
        }
        return FontResourcesParserCompat.readCerts(resources, fontRequest.getCertificatesArrayResId());
    }

    static /* synthetic */ int lambda$static$0(byte[] bArr, byte[] bArr2) {
        if (bArr.length != bArr2.length) {
            return bArr.length - bArr2.length;
        }
        for (int i = 0; i < bArr.length; i++) {
            byte b2 = bArr[i];
            byte b3 = bArr2[i];
            if (b2 != b3) {
                return b2 - b3;
            }
        }
        return 0;
    }

    private static boolean equalsByteArrayList(List<byte[]> list, List<byte[]> list2) {
        if (list.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            if (!Arrays.equals(list.get(i), list2.get(i))) {
                return false;
            }
        }
        return true;
    }

    private static List<byte[]> convertToByteArrayList(Signature[] signatureArr) {
        ArrayList arrayList = new ArrayList();
        for (Signature byteArray : signatureArr) {
            arrayList.add(byteArray.toByteArray());
        }
        return arrayList;
    }

    private interface ContentQueryWrapper {
        void close();

        Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2, CancellationSignal cancellationSignal);

        /* renamed from: androidx.core.provider.FontProvider$ContentQueryWrapper$-CC  reason: invalid class name */
        public final /* synthetic */ class CC {
            public static ContentQueryWrapper make(Context context, Uri uri) {
                if (Build.VERSION.SDK_INT < 24) {
                    return new ContentQueryWrapperApi16Impl(context, uri);
                }
                return new ContentQueryWrapperApi24Impl(context, uri);
            }
        }
    }

    private static class ContentQueryWrapperApi16Impl implements ContentQueryWrapper {
        private final ContentProviderClient mClient;

        ContentQueryWrapperApi16Impl(Context context, Uri uri) {
            this.mClient = context.getContentResolver().acquireUnstableContentProviderClient(uri);
        }

        public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2, CancellationSignal cancellationSignal) {
            ContentProviderClient contentProviderClient = this.mClient;
            if (contentProviderClient == null) {
                return null;
            }
            try {
                return contentProviderClient.query(uri, strArr, str, strArr2, str2, cancellationSignal);
            } catch (RemoteException e) {
                Log.w("FontsProvider", "Unable to query the content provider", e);
                return null;
            }
        }

        public void close() {
            ContentProviderClient contentProviderClient = this.mClient;
            if (contentProviderClient != null) {
                contentProviderClient.release();
            }
        }
    }

    private static class ContentQueryWrapperApi24Impl implements ContentQueryWrapper {
        private final ContentProviderClient mClient;

        ContentQueryWrapperApi24Impl(Context context, Uri uri) {
            this.mClient = context.getContentResolver().acquireUnstableContentProviderClient(uri);
        }

        public Cursor query(Uri uri, String[] strArr, String str, String[] strArr2, String str2, CancellationSignal cancellationSignal) {
            ContentProviderClient contentProviderClient = this.mClient;
            if (contentProviderClient == null) {
                return null;
            }
            try {
                return contentProviderClient.query(uri, strArr, str, strArr2, str2, cancellationSignal);
            } catch (RemoteException e) {
                Log.w("FontsProvider", "Unable to query the content provider", e);
                return null;
            }
        }

        public void close() {
            ContentProviderClient contentProviderClient = this.mClient;
            if (contentProviderClient != null) {
                contentProviderClient.release();
            }
        }
    }
}
