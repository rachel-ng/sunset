package com.google.android.gms.common.server.response;

import com.google.android.gms.common.server.response.FastParser;
import java.io.BufferedReader;
import java.io.IOException;

/* compiled from: com.google.android.gms:play-services-base@@18.0.0 */
final class zae implements zai<Boolean> {
    zae() {
    }

    public final /* bridge */ /* synthetic */ Object zaa(FastParser fastParser, BufferedReader bufferedReader) throws FastParser.ParseException, IOException {
        return Boolean.valueOf(fastParser.zay(bufferedReader, false));
    }
}
