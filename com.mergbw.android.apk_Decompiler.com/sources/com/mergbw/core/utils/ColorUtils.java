package com.mergbw.core.utils;

import android.graphics.Color;
import com.alibaba.fastjson.JSON;
import com.mergbw.core.bean.SubItemBean;
import com.mergbw.core.database.bean.ColorBean;
import java.util.ArrayList;
import java.util.List;

public class ColorUtils {
    public static List<ColorBean> getClassicColorList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new ColorBean("#FFFFFF"));
        arrayList.add(new ColorBean("#FF0000"));
        arrayList.add(new ColorBean("#FFFF00"));
        arrayList.add(new ColorBean("#00FF00"));
        arrayList.add(new ColorBean("#00FFFF"));
        arrayList.add(new ColorBean("#0000FF"));
        arrayList.add(new ColorBean("#FF00FF"));
        return arrayList;
    }

    public static List<ColorBean> getAtmosphereColorList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new ColorBean("#FF0000"));
        arrayList.add(new ColorBean("#FFFF00"));
        arrayList.add(new ColorBean("#00FF00"));
        arrayList.add(new ColorBean("#00FFFF"));
        arrayList.add(new ColorBean("#0000FF"));
        arrayList.add(new ColorBean("#FF00FF"));
        return arrayList;
    }

    public static List<ColorBean> getWarmColorList() {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new ColorBean("#FFFF96"));
        arrayList.add(new ColorBean("#FFFFAB"));
        arrayList.add(new ColorBean("#FFFFC0"));
        arrayList.add(new ColorBean("#FFFFD5"));
        arrayList.add(new ColorBean("#FFFFEA"));
        arrayList.add(new ColorBean("#FFFFFF"));
        return arrayList;
    }

    public static int[] getWarmColorLineList() {
        return new int[]{Color.parseColor("#FFFF96"), Color.parseColor("#FFFFAB"), Color.parseColor("#FFFFC0"), Color.parseColor("#FFFFD5"), Color.parseColor("#FFFFEA"), Color.parseColor("#FFFFFF")};
    }

    public static float[] rgbToHsv(int[] iArr) {
        float f;
        float f2 = ((float) iArr[0]) / 255.0f;
        float f3 = ((float) iArr[1]) / 255.0f;
        float f4 = ((float) iArr[2]) / 255.0f;
        float[] fArr = {f2, f3, f4};
        float f5 = fArr[0];
        float f6 = f5;
        for (int i = 0; i < 3; i++) {
            float f7 = fArr[i];
            if (f5 <= f7) {
                f5 = f7;
            }
            if (f6 >= f7) {
                f6 = f7;
            }
        }
        float f8 = f5 - f6;
        float f9 = 0.0f;
        if (f8 == 0.0f) {
            f = 0.0f;
        } else {
            f = f5 == f2 ? (((f3 - f4) / f8) % 6.0f) * 60.0f : 0.0f;
            if (f5 == f3) {
                f = (((f4 - f2) / f8) + 2.0f) * 60.0f;
            }
            if (f5 == f4) {
                f = (((f2 - f3) / f8) + 4.0f) * 60.0f;
            }
        }
        if (f5 != 0.0f) {
            f9 = f8 / f5;
        }
        return new float[]{f, f9, f5};
    }

    public static byte[] getHSVColorData(int i) {
        float[] fArr = new float[3];
        Color.colorToHSV(i, fArr);
        byte[] intToBytes = DataUtils.intToBytes((int) fArr[0]);
        byte[] intToBytes2 = DataUtils.intToBytes((int) (fArr[1] * 1000.0f));
        return new byte[]{intToBytes[2], intToBytes[3], intToBytes2[2], intToBytes2[3]};
    }

    public static List<SubItemBean> getSubColor(String str) {
        List<SubItemBean> parseArray = JSON.parseArray(str, SubItemBean.class);
        for (SubItemBean checked : parseArray) {
            checked.setChecked(false);
        }
        return parseArray;
    }
}
