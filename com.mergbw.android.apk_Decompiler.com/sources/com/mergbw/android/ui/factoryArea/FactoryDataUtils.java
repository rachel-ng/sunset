package com.mergbw.android.ui.factoryArea;

import com.mergbw.android.R;
import java.util.HashMap;
import java.util.Map;

public class FactoryDataUtils {
    public static final Map<String, Integer> COUNTRY_FLAG;

    static {
        HashMap hashMap = new HashMap();
        COUNTRY_FLAG = hashMap;
        hashMap.put("US", Integer.valueOf(R.drawable.american));
        hashMap.put("AU", Integer.valueOf(R.drawable.australia));
        hashMap.put("BE", Integer.valueOf(R.drawable.belgium));
        hashMap.put("BR", Integer.valueOf(R.drawable.brazil));
        hashMap.put("CA", Integer.valueOf(R.drawable.canada));
        hashMap.put("EG", Integer.valueOf(R.drawable.egypt));
        hashMap.put("FR", Integer.valueOf(R.drawable.france));
        hashMap.put("DE", Integer.valueOf(R.drawable.germany));
        hashMap.put("IN", Integer.valueOf(R.drawable.india));
        hashMap.put("IT", Integer.valueOf(R.drawable.italy));
        hashMap.put("JP", Integer.valueOf(R.drawable.japan));
        hashMap.put("MX", Integer.valueOf(R.drawable.mexico));
        hashMap.put("NL", Integer.valueOf(R.drawable.netherlands));
        hashMap.put("PL", Integer.valueOf(R.drawable.poland));
        hashMap.put("SA", Integer.valueOf(R.drawable.saudi_arabia));
        hashMap.put("SG", Integer.valueOf(R.drawable.singapore));
        hashMap.put("ZA", Integer.valueOf(R.drawable.south_africa));
        hashMap.put("ES", Integer.valueOf(R.drawable.spain));
        hashMap.put("SE", Integer.valueOf(R.drawable.sweden));
        hashMap.put("TR", Integer.valueOf(R.drawable.turkey));
        hashMap.put("AE", Integer.valueOf(R.drawable.united_arab_emirates));
        hashMap.put("GB", Integer.valueOf(R.drawable.united_kindom));
    }
}
